/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.graphics.Rect
 *  android.hardware.Camera
 *  android.hardware.Camera$Area
 *  java.lang.Math
 *  java.lang.Object
 */
package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.b;
import com.unity3d.player.d;
import com.unity3d.player.j;

public class Camera2Wrapper
implements d {
    private Context a;
    private b b = null;
    private final int c = 100;

    public Camera2Wrapper(Context context) {
        this.a = context;
        this.initCamera2Jni();
    }

    private static int a(float f2) {
        return (int)Math.min((float)Math.max((float)(-1000.0f + f2 * 2000.0f), (float)-900.0f), (float)900.0f);
    }

    private final native void deinitCamera2Jni();

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object var1, Object var2, Object var3, int var4, int var5, int var6);

    private final native void nativeSurfaceTextureReady(Object var1);

    public final void a() {
        this.deinitCamera2Jni();
        this.closeCamera2();
    }

    @Override
    public final void a(Object object) {
        this.nativeSurfaceTextureReady(object);
    }

    @Override
    public final void a(Object object, Object object2, Object object3, int n2, int n3, int n4) {
        this.nativeFrameReady(object, object2, object3, n2, n3, n4);
    }

    protected void closeCamera2() {
        b b2 = this.b;
        if (b2 != null) {
            b2.b();
        }
        this.b = null;
    }

    protected int getCamera2Count() {
        if (j.a) {
            return b.a(this.a);
        }
        return 0;
    }

    protected int[] getCamera2Resolutions(int n2) {
        if (j.a) {
            return b.d(this.a, n2);
        }
        return null;
    }

    protected int getCamera2SensorOrientation(int n2) {
        if (j.a) {
            return b.a(this.a, n2);
        }
        return 0;
    }

    protected Object getCameraFocusArea(float f2, float f3) {
        int n2 = Camera2Wrapper.a(f2);
        int n3 = Camera2Wrapper.a(1.0f - f3);
        return new Camera.Area(new Rect(n2 - 100, n3 - 100, n2 + 100, n3 + 100), 1000);
    }

    protected Rect getFrameSizeCamera2() {
        b b2 = this.b;
        if (b2 != null) {
            return b2.a();
        }
        return new Rect();
    }

    protected boolean initializeCamera2(int n2, int n3, int n4, int n5, int n6) {
        if (j.a && this.b == null && UnityPlayer.currentActivity != null) {
            this.b = new b(this);
            return this.b.a(this.a, n2, n3, n4, n5, n6);
        }
        return false;
    }

    protected boolean isCamera2AutoFocusPointSupported(int n2) {
        if (j.a) {
            return b.c(this.a, n2);
        }
        return false;
    }

    protected boolean isCamera2FrontFacing(int n2) {
        if (j.a) {
            return b.b(this.a, n2);
        }
        return false;
    }

    protected void pauseCamera2() {
        b b2 = this.b;
        if (b2 != null) {
            b2.d();
        }
    }

    protected boolean setAutoFocusPoint(float f2, float f3) {
        b b2;
        if (j.a && (b2 = this.b) != null) {
            return b2.a(f2, f3);
        }
        return false;
    }

    protected void startCamera2() {
        b b2 = this.b;
        if (b2 != null) {
            b2.c();
        }
    }

    protected void stopCamera2() {
        b b2 = this.b;
        if (b2 != null) {
            b2.e();
        }
    }
}

