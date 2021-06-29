/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.Looper
 *  java.lang.Exception
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.concurrent.Semaphore
 *  java.util.concurrent.TimeUnit
 */
package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.unity3d.player.f;
import com.unity3d.player.g;
import com.unity3d.player.o;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class c {
    protected o a = null;
    protected f b = null;
    protected Context c = null;
    protected String d = null;
    protected String e = "";

    c(String string2, f f2) {
        this.e = string2;
        this.b = f2;
    }

    protected void reportError(String string2) {
        f f2 = this.b;
        if (f2 != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.e);
            stringBuilder.append(" Error [");
            stringBuilder.append(this.d);
            stringBuilder.append("]");
            f2.reportError(stringBuilder.toString(), string2);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.e);
        stringBuilder.append(" Error [");
        stringBuilder.append(this.d);
        stringBuilder.append("]: ");
        stringBuilder.append(string2);
        g.Log(6, stringBuilder.toString());
    }

    protected void runOnUiThread(Runnable runnable) {
        Context context = this.c;
        if (context instanceof Activity) {
            ((Activity)context).runOnUiThread(runnable);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("Not running ");
        stringBuilder.append(this.e);
        stringBuilder.append(" from an Activity; Ignoring execution request...");
        g.Log(5, stringBuilder.toString());
    }

    protected boolean runOnUiThreadWithSync(final Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
            return true;
        }
        final Semaphore semaphore = new Semaphore(0);
        this.runOnUiThread(new Runnable(){

            /*
             * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            public final void run() {
                runnable.run();
lbl3: // 2 sources:
                do {
                    semaphore.release();
                    return;
                    break;
                } while (true);
                {
                    catch (Throwable var5_1) {
                    }
                    catch (Exception var1_2) {}
                    {
                        var2_3 = c.this;
                        var3_4 = new StringBuilder("Exception unloading Google VR on UI Thread. ");
                        var3_4.append(var1_2.getLocalizedMessage());
                        var2_3.reportError(var3_4.toString());
                        ** continue;
                    }
                }
                semaphore.release();
                throw var5_1;
            }
        });
        try {
            if (!semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                this.reportError("Timeout waiting for vr state change!");
                return false;
            }
            return true;
        }
        catch (InterruptedException interruptedException) {
            StringBuilder stringBuilder = new StringBuilder("Interrupted while trying to acquire sync lock. ");
            stringBuilder.append(interruptedException.getLocalizedMessage());
            this.reportError(stringBuilder.toString());
            return false;
        }
    }

}

