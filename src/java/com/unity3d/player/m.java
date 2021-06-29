/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  java.lang.Error
 *  java.lang.Object
 *  java.lang.StackTraceElement
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.Thread
 *  java.lang.Thread$UncaughtExceptionHandler
 *  java.lang.Throwable
 */
package com.unity3d.player;

import android.os.Build;

final class m
implements Thread.UncaughtExceptionHandler {
    private volatile Thread.UncaughtExceptionHandler a;

    m() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    final boolean a() {
        m m2 = this;
        synchronized (m2) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
            block4 : {
                uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
                if (uncaughtExceptionHandler != this) break block4;
                return false;
            }
            this.a = uncaughtExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)this);
            return true;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void uncaughtException(Thread thread, Throwable throwable) {
        m m2 = this;
        synchronized (m2) {
            try {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    Object[] arrobject = new Object[]{thread.getName()};
                    stringBuilder.append(String.format((String)"FATAL EXCEPTION [%s]\n", (Object[])arrobject));
                    stringBuilder.append(String.format((String)"Unity version     : %s\n", (Object[])new Object[]{"2019.4.17f1"}));
                    Object[] arrobject2 = new Object[]{Build.MANUFACTURER, Build.MODEL};
                    stringBuilder.append(String.format((String)"Device model      : %s %s\n", (Object[])arrobject2));
                    Object[] arrobject3 = new Object[]{Build.FINGERPRINT};
                    stringBuilder.append(String.format((String)"Device fingerprint: %s\n", (Object[])arrobject3));
                    Error error = new Error(stringBuilder.toString());
                    error.setStackTrace(new StackTraceElement[0]);
                    error.initCause(throwable);
                    this.a.uncaughtException(thread, (Throwable)error);
                    return;
                }
                catch (Throwable throwable2) {
                    this.a.uncaughtException(thread, throwable);
                    return;
                }
            }
            catch (Throwable throwable22) {}
            throw throwable22;
        }
    }
}

