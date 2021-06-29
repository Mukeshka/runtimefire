/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.media.AudioTrack
 *  android.util.Log
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.Thread
 *  java.nio.Buffer
 *  java.nio.ByteBuffer
 */
package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.fmod.a;

public class FMODAudioDevice
implements Runnable {
    private static int h = 0;
    private static int i = 1;
    private static int j = 2;
    private static int k = 3;
    private volatile Thread a = null;
    private volatile boolean b = false;
    private AudioTrack c = null;
    private boolean d = false;
    private ByteBuffer e = null;
    private byte[] f = null;
    private volatile a g;

    private native int fmodGetInfo(int var1);

    private native int fmodProcess(ByteBuffer var1);

    private void releaseAudioTrack() {
        AudioTrack audioTrack = this.c;
        if (audioTrack != null) {
            if (audioTrack.getState() == 1) {
                this.c.stop();
            }
            this.c.release();
            this.c = null;
        }
        this.e = null;
        this.f = null;
        this.d = false;
    }

    public void close() {
        FMODAudioDevice fMODAudioDevice = this;
        synchronized (fMODAudioDevice) {
            this.stop();
            return;
        }
    }

    native int fmodProcessMicData(ByteBuffer var1, int var2);

    public boolean isRunning() {
        return this.a != null && this.a.isAlive();
    }

    public void run() {
        int n2 = 3;
        while (this.b) {
            if (!this.d && n2 > 0) {
                AudioTrack audioTrack;
                this.releaseAudioTrack();
                int n3 = this.fmodGetInfo(h);
                int n4 = -4 & Math.round((float)(1.1f * (float)AudioTrack.getMinBufferSize((int)n3, (int)3, (int)2)));
                int n5 = this.fmodGetInfo(i);
                int n6 = 4 * (n5 * this.fmodGetInfo(j));
                int n7 = n6 > n4 ? n6 : n4;
                this.c = audioTrack = new AudioTrack(3, n3, 3, 2, n7, 1);
                boolean bl = this.c.getState() == 1;
                this.d = bl;
                if (this.d) {
                    this.e = ByteBuffer.allocateDirect((int)(2 * (n5 * 2)));
                    this.f = new byte[this.e.capacity()];
                    this.c.play();
                    n2 = 3;
                } else {
                    StringBuilder stringBuilder = new StringBuilder("AudioTrack failed to initialize (status ");
                    stringBuilder.append(this.c.getState());
                    stringBuilder.append(")");
                    Log.e((String)"FMOD", (String)stringBuilder.toString());
                    this.releaseAudioTrack();
                    --n2;
                }
            }
            if (!this.d) continue;
            if (this.fmodGetInfo(k) == 1) {
                this.fmodProcess(this.e);
                ByteBuffer byteBuffer = this.e;
                byteBuffer.get(this.f, 0, byteBuffer.capacity());
                this.c.write(this.f, 0, this.e.capacity());
                this.e.position(0);
                continue;
            }
            this.releaseAudioTrack();
        }
        this.releaseAudioTrack();
    }

    public void start() {
        FMODAudioDevice fMODAudioDevice = this;
        synchronized (fMODAudioDevice) {
            if (this.a != null) {
                this.stop();
            }
            this.a = new Thread((Runnable)this, "FMODAudioDevice");
            this.a.setPriority(10);
            this.b = true;
            this.a.start();
            if (this.g != null) {
                this.g.b();
            }
            return;
        }
    }

    public int startAudioRecord(int n2, int n3, int n4) {
        FMODAudioDevice fMODAudioDevice = this;
        synchronized (fMODAudioDevice) {
            if (this.g == null) {
                this.g = new a(this, n2, n3);
                this.g.b();
            }
            int n5 = this.g.a();
            return n5;
        }
    }

    /*
     * Exception decompiling
     */
    public void stop() {
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

    public void stopAudioRecord() {
        FMODAudioDevice fMODAudioDevice = this;
        synchronized (fMODAudioDevice) {
            if (this.g != null) {
                this.g.c();
                this.g = null;
            }
            return;
        }
    }
}

