/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnBufferingUpdateListener
 *  android.media.MediaPlayer$OnCompletionListener
 *  android.media.MediaPlayer$OnPreparedListener
 *  android.media.MediaPlayer$OnVideoSizeChangedListener
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.Display
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.SurfaceHolder
 *  android.view.SurfaceHolder$Callback
 *  android.view.SurfaceView
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.Window
 *  android.view.WindowManager
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.MediaController
 *  android.widget.MediaController$MediaPlayerControl
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuilder
 */
package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.MediaController;

public final class p
extends FrameLayout
implements MediaPlayer.OnBufferingUpdateListener,
MediaPlayer.OnCompletionListener,
MediaPlayer.OnPreparedListener,
MediaPlayer.OnVideoSizeChangedListener,
SurfaceHolder.Callback,
MediaController.MediaPlayerControl {
    private static boolean a;
    private final Context b;
    private final SurfaceView c;
    private final SurfaceHolder d;
    private final String e;
    private final int f;
    private final int g;
    private final boolean h;
    private final long i;
    private final long j;
    private final FrameLayout k;
    private final Display l;
    private int m;
    private int n;
    private int o;
    private int p;
    private MediaPlayer q;
    private MediaController r;
    private boolean s = false;
    private boolean t = false;
    private int u = 0;
    private boolean v = false;
    private boolean w = false;
    private a x;
    private b y;
    private volatile int z = 0;

    protected p(Context context, String string2, int n2, int n3, int n4, boolean bl, long l2, long l3, a a2) {
        super(context);
        this.x = a2;
        this.b = context;
        this.k = this;
        this.c = new SurfaceView(context);
        this.d = this.c.getHolder();
        this.d.addCallback((SurfaceHolder.Callback)this);
        this.k.setBackgroundColor(n2);
        this.k.addView((View)this.c);
        this.l = ((WindowManager)this.b.getSystemService("window")).getDefaultDisplay();
        this.e = string2;
        this.f = n3;
        this.g = n4;
        this.h = bl;
        this.i = l2;
        this.j = l3;
        if (a) {
            StringBuilder stringBuilder = new StringBuilder("fileName: ");
            stringBuilder.append(this.e);
            p.b(stringBuilder.toString());
        }
        if (a) {
            StringBuilder stringBuilder = new StringBuilder("backgroundColor: ");
            stringBuilder.append(n2);
            p.b(stringBuilder.toString());
        }
        if (a) {
            StringBuilder stringBuilder = new StringBuilder("controlMode: ");
            stringBuilder.append(this.f);
            p.b(stringBuilder.toString());
        }
        if (a) {
            StringBuilder stringBuilder = new StringBuilder("scalingMode: ");
            stringBuilder.append(this.g);
            p.b(stringBuilder.toString());
        }
        if (a) {
            StringBuilder stringBuilder = new StringBuilder("isURL: ");
            stringBuilder.append(this.h);
            p.b(stringBuilder.toString());
        }
        if (a) {
            StringBuilder stringBuilder = new StringBuilder("videoOffset: ");
            stringBuilder.append(this.i);
            p.b(stringBuilder.toString());
        }
        if (a) {
            StringBuilder stringBuilder = new StringBuilder("videoLength: ");
            stringBuilder.append(this.j);
            p.b(stringBuilder.toString());
        }
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
    }

    private void a(int n2) {
        this.z = n2;
        a a2 = this.x;
        if (a2 != null) {
            a2.a(this.z);
        }
    }

    static /* synthetic */ void a(String string2) {
        p.b(string2);
    }

    private static void b(String string2) {
        StringBuilder stringBuilder = new StringBuilder("VideoPlayer: ");
        stringBuilder.append(string2);
        Log.i((String)"Video", (String)stringBuilder.toString());
    }

    static /* synthetic */ boolean b() {
        return a;
    }

    /*
     * Exception decompiling
     */
    private void c() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Invalid stack depths @ lbl63 : ALOAD : trying to set 1 previously set to 0
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

    private void d() {
        if (this.isPlaying()) {
            return;
        }
        this.a(1);
        if (a) {
            p.b("startVideoPlayback");
        }
        this.updateVideoLayout();
        if (!this.v) {
            this.start();
        }
    }

    public final void CancelOnPrepare() {
        this.a(2);
    }

    final boolean a() {
        return this.v;
    }

    public final boolean canPause() {
        return true;
    }

    public final boolean canSeekBackward() {
        return true;
    }

    public final boolean canSeekForward() {
        return true;
    }

    protected final void destroyPlayer() {
        if (a) {
            p.b("destroyPlayer");
        }
        if (!this.v) {
            this.pause();
        }
        this.doCleanUp();
    }

    protected final void doCleanUp() {
        MediaPlayer mediaPlayer;
        b b2 = this.y;
        if (b2 != null) {
            b2.a();
            this.y = null;
        }
        if ((mediaPlayer = this.q) != null) {
            mediaPlayer.release();
            this.q = null;
        }
        this.o = 0;
        this.p = 0;
        this.t = false;
        this.s = false;
    }

    public final int getAudioSessionId() {
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getAudioSessionId();
    }

    public final int getBufferPercentage() {
        if (this.h) {
            return this.u;
        }
        return 100;
    }

    public final int getCurrentPosition() {
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }

    public final int getDuration() {
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getDuration();
    }

    public final boolean isPlaying() {
        boolean bl = this.t && this.s;
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer == null) {
            return !bl;
        }
        if (!mediaPlayer.isPlaying()) {
            return !bl;
        }
        return true;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int n2) {
        if (a) {
            StringBuilder stringBuilder = new StringBuilder("onBufferingUpdate percent:");
            stringBuilder.append(n2);
            p.b(stringBuilder.toString());
        }
        this.u = n2;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (a) {
            p.b("onCompletion called");
        }
        this.destroyPlayer();
        this.a(3);
    }

    public final boolean onKeyDown(int n2, KeyEvent keyEvent) {
        if (n2 != 4 && (this.f != 2 || n2 == 0 || keyEvent.isSystem())) {
            MediaController mediaController = this.r;
            if (mediaController != null) {
                return mediaController.onKeyDown(n2, keyEvent);
            }
            return super.onKeyDown(n2, keyEvent);
        }
        this.destroyPlayer();
        this.a(3);
        return true;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        b b2;
        int n2;
        if (a) {
            p.b("onPrepared called");
        }
        if ((b2 = this.y) != null) {
            b2.a();
            this.y = null;
        }
        if ((n2 = this.f) == 0 || n2 == 1) {
            this.r = new MediaController(this.b);
            this.r.setMediaPlayer((MediaController.MediaPlayerControl)this);
            this.r.setAnchorView((View)this);
            this.r.setEnabled(true);
            Context context = this.b;
            if (context instanceof Activity) {
                Activity activity = (Activity)context;
                this.r.setSystemUiVisibility(activity.getWindow().getDecorView().getSystemUiVisibility());
            }
            this.r.show();
        }
        this.t = true;
        if (this.t && this.s) {
            this.d();
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int n2 = 255 & motionEvent.getAction();
        if (this.f == 2 && n2 == 0) {
            this.destroyPlayer();
            this.a(3);
            return true;
        }
        MediaController mediaController = this.r;
        if (mediaController != null) {
            return mediaController.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int n2, int n3) {
        if (a) {
            StringBuilder stringBuilder = new StringBuilder("onVideoSizeChanged called ");
            stringBuilder.append(n2);
            stringBuilder.append("x");
            stringBuilder.append(n3);
            p.b(stringBuilder.toString());
        }
        if (n2 != 0 && n3 != 0) {
            this.s = true;
            this.o = n2;
            this.p = n3;
            if (this.t && this.s) {
                this.d();
            }
            return;
        }
        if (a) {
            StringBuilder stringBuilder = new StringBuilder("invalid video width(");
            stringBuilder.append(n2);
            stringBuilder.append(") or height(");
            stringBuilder.append(n3);
            stringBuilder.append(")");
            p.b(stringBuilder.toString());
        }
    }

    public final void pause() {
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer == null) {
            return;
        }
        if (this.w) {
            mediaPlayer.pause();
        }
        this.v = true;
    }

    public final void seekTo(int n2) {
        MediaPlayer mediaPlayer = this.q;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.seekTo(n2);
    }

    public final void start() {
        MediaPlayer mediaPlayer;
        if (a) {
            p.b("Start");
        }
        if ((mediaPlayer = this.q) == null) {
            return;
        }
        if (this.w) {
            mediaPlayer.start();
        }
        this.v = false;
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int n2, int n3, int n4) {
        if (a) {
            StringBuilder stringBuilder = new StringBuilder("surfaceChanged called ");
            stringBuilder.append(n2);
            stringBuilder.append(" ");
            stringBuilder.append(n3);
            stringBuilder.append("x");
            stringBuilder.append(n4);
            p.b(stringBuilder.toString());
        }
        if (this.m != n3 || this.n != n4) {
            this.m = n3;
            this.n = n4;
            if (this.w) {
                this.updateVideoLayout();
            }
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (a) {
            p.b("surfaceCreated called");
        }
        this.w = true;
        this.c();
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (a) {
            p.b("surfaceDestroyed called");
        }
        this.w = false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected final void updateVideoLayout() {
        block13 : {
            block10 : {
                block12 : {
                    block11 : {
                        if (p.a) {
                            p.b("updateVideoLayout");
                        }
                        if (this.q == null) {
                            return;
                        }
                        if (this.m == 0 || this.n == 0) {
                            var1_1 = (WindowManager)this.b.getSystemService("window");
                            var2_2 = new DisplayMetrics();
                            var1_1.getDefaultDisplay().getMetrics(var2_2);
                            this.m = var2_2.widthPixels;
                            this.n = var2_2.heightPixels;
                        }
                        var3_3 = this.m;
                        var4_4 = this.n;
                        if (!this.s) break block10;
                        var10_5 = this.o;
                        var11_6 = var10_5;
                        var12_7 = this.p;
                        var13_8 = var11_6 / (float)var12_7;
                        var14_9 = (float)var3_3 / (float)var4_4;
                        var15_10 = this.g;
                        if (var15_10 != 1) break block11;
                        if (!(var14_9 <= var13_8)) ** GOTO lbl-1000
                        ** GOTO lbl-1000
                    }
                    if (var15_10 == 2) {
                        ** if (!(var14_9 >= var13_8)) goto lbl-1000
                    }
                    break block12;
lbl-1000: // 2 sources:
                    {
                        var4_4 = (int)((float)var3_3 / var13_8);
                        ** GOTO lbl39
                    }
lbl-1000: // 2 sources:
                    {
                        var3_3 = (int)(var13_8 * (float)var4_4);
                    }
                    break block13;
                }
                if (var15_10 == 0) {
                    var3_3 = var10_5;
                    var4_4 = var12_7;
                }
                break block13;
            }
            if (p.a) {
                p.b("updateVideoLayout: Video size is not known yet");
            }
        }
        if (this.m == var3_3) {
            if (this.n == var4_4) return;
        }
        if (p.a) {
            var5_11 = new StringBuilder("frameWidth = ");
            var5_11.append(var3_3);
            var5_11.append("; frameHeight = ");
            var5_11.append(var4_4);
            p.b(var5_11.toString());
        }
        var9_12 = new FrameLayout.LayoutParams(var3_3, var4_4, 17);
        this.k.updateViewLayout((View)this.c, (ViewGroup.LayoutParams)var9_12);
    }

    public static interface a {
        public void a(int var1);
    }

    public final class b
    implements Runnable {
        private p b;
        private boolean c;

        public b(p p3) {
            this.b = p3;
            this.c = false;
        }

        public final void a() {
            this.c = true;
        }

        /*
         * Exception decompiling
         */
        public final void run() {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Invalid stack depths @ lbl6 : ALOAD_0 : trying to set 1 previously set to 0
            // org.benf.cfr.reader.b.a.a.g.a(Op02WithProcessedDataAndRefs.java:203)
            // org.benf.cfr.reader.b.a.a.g.a(Op02WithProcessedDataAndRefs.java:1489)
            // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:308)
            // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:182)
            // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:127)
            // org.benf.cfr.reader.entities.attributes.f.c(AttributeCode.java:96)
            // org.benf.cfr.reader.entities.g.p(Method.java:396)
            // org.benf.cfr.reader.entities.d.e(ClassFile.java:890)
            // org.benf.cfr.reader.entities.d.c(ClassFile.java:773)
            // org.benf.cfr.reader.entities.d.e(ClassFile.java:870)
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
    }

}

