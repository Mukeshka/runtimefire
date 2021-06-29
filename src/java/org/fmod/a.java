/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.media.AudioRecord
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.Thread
 *  java.nio.Buffer
 *  java.nio.ByteBuffer
 */
package org.fmod;

import android.media.AudioRecord;
import android.util.Log;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.fmod.FMODAudioDevice;

final class a
implements Runnable {
    private final FMODAudioDevice a;
    private final ByteBuffer b;
    private final int c;
    private final int d;
    private final int e;
    private volatile Thread f;
    private volatile boolean g;
    private AudioRecord h;
    private boolean i;

    a(FMODAudioDevice fMODAudioDevice, int n2, int n3) {
        this.a = fMODAudioDevice;
        this.c = n2;
        this.d = n3;
        this.e = 2;
        this.b = ByteBuffer.allocateDirect((int)AudioRecord.getMinBufferSize((int)n2, (int)n3, (int)2));
    }

    private void d() {
        AudioRecord audioRecord = this.h;
        if (audioRecord != null) {
            if (audioRecord.getState() == 1) {
                this.h.stop();
            }
            this.h.release();
            this.h = null;
        }
        this.b.position(0);
        this.i = false;
    }

    public final int a() {
        return this.b.capacity();
    }

    public final void b() {
        if (this.f != null) {
            this.c();
        }
        this.g = true;
        this.f = new Thread((Runnable)this);
        this.f.start();
    }

    /*
     * Exception decompiling
     */
    public final void c() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Invalid stack depths @ lbl1 : ALOAD_0 : trying to set 1 previously set to 0
        // org.benf.cfr.reader.b.a.a.g.a(Op02WithProcessedDataAndRefs.java:203)
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

    public final void run() {
        int n2 = 3;
        while (this.g) {
            if (!this.i && n2 > 0) {
                AudioRecord audioRecord;
                this.d();
                this.h = audioRecord = new AudioRecord(1, this.c, this.d, this.e, this.b.capacity());
                int n3 = this.h.getState();
                int n4 = 1;
                if (n3 != n4) {
                    n4 = 0;
                }
                this.i = n4;
                if (this.i) {
                    this.b.position(0);
                    this.h.startRecording();
                    n2 = 3;
                } else {
                    StringBuilder stringBuilder = new StringBuilder("AudioRecord failed to initialize (status ");
                    stringBuilder.append(this.h.getState());
                    stringBuilder.append(")");
                    Log.e((String)"FMOD", (String)stringBuilder.toString());
                    --n2;
                    this.d();
                }
            }
            if (!this.i || this.h.getRecordingState() != 3) continue;
            AudioRecord audioRecord = this.h;
            ByteBuffer byteBuffer = this.b;
            int n5 = audioRecord.read(byteBuffer, byteBuffer.capacity());
            this.a.fmodProcessMicData(this.b, n5);
            this.b.position(0);
        }
        this.d();
    }
}

