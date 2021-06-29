/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.util.concurrent.Semaphore
 *  java.util.concurrent.locks.Lock
 *  java.util.concurrent.locks.ReentrantLock
 */
package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.g;
import com.unity3d.player.p;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class q {
    private UnityPlayer a = null;
    private Context b = null;
    private a c;
    private final Semaphore d = new Semaphore(0);
    private final Lock e = new ReentrantLock();
    private p f = null;
    private int g = 2;
    private boolean h = false;
    private boolean i = false;

    q(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
    }

    private void d() {
        p p2 = this.f;
        if (p2 != null) {
            this.a.removeViewFromPlayer((View)p2);
            this.i = false;
            this.f.destroyPlayer();
            this.f = null;
            a a2 = this.c;
            if (a2 != null) {
                a2.a();
            }
        }
    }

    public final void a() {
        this.e.lock();
        p p2 = this.f;
        if (p2 != null) {
            if (this.g == 0) {
                p2.CancelOnPrepare();
            } else if (this.i) {
                this.h = p2.a();
                if (!this.h) {
                    this.f.pause();
                }
            }
        }
        this.e.unlock();
    }

    /*
     * Exception decompiling
     */
    public final boolean a(Context var1, String var2, int var3, int var4, int var5, boolean var6, long var7, long var9, a var11) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Underrun type stack
        // org.benf.cfr.reader.b.a.c.e.a(StackSim.java:35)
        // org.benf.cfr.reader.b.b.af.a(OperationFactoryPop.java:20)
        // org.benf.cfr.reader.b.b.e.a(JVMInstr.java:315)
        // org.benf.cfr.reader.b.a.a.g.a(Op02WithProcessedDataAndRefs.java:195)
        // org.benf.cfr.reader.b.a.a.g.a(Op02WithProcessedDataAndRefs.java:1489)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:308)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:182)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:127)
        // org.benf.cfr.reader.entities.attributes.f.c(AttributeCode.java:96)
        // org.benf.cfr.reader.entities.g.p(Method.java:396)
        // org.benf.cfr.reader.entities.d.e(ClassFile.java:890)
        // org.benf.cfr.reader.entities.d.b(ClassFile.java:792)
        // org.benf.cfr.reader.b.a(Driver.java:128)
        // org.benf.cfr.reader.a.a(CfrDriverImpl.java:63)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.decompileWithCFR(JavaExtractionWorker.kt:61)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.doWork(JavaExtractionWorker.kt:130)
        // com.njlabs.showjava.decompilers.BaseDecompiler.withAttempt(BaseDecompiler.kt:108)
        // com.njlabs.showjava.workers.DecompilerWorker$b.run(DecompilerWorker.kt:118)
        // java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        // java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        // java.lang.Thread.run(Thread.java:919)
        throw new IllegalStateException("Decompilation failed");
    }

    public final void b() {
        this.e.lock();
        p p2 = this.f;
        if (p2 != null && this.i && !this.h) {
            p2.start();
        }
        this.e.unlock();
    }

    public final void c() {
        this.e.lock();
        p p2 = this.f;
        if (p2 != null) {
            p2.updateVideoLayout();
        }
        this.e.unlock();
    }

    protected final void runOnUiThread(Runnable runnable) {
        Context context = this.b;
        if (context instanceof Activity) {
            ((Activity)context).runOnUiThread(runnable);
            return;
        }
        g.Log(5, "Not running from an Activity; Ignoring execution request...");
    }

    public static interface a {
        public void a();
    }

}

