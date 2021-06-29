/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.view.Surface
 *  java.lang.Object
 */
package com.unity3d.player;

import android.view.Surface;

public interface GoogleVrVideo {
    public void deregisterGoogleVrVideoListener(GoogleVrVideoCallbacks var1);

    public void registerGoogleVrVideoListener(GoogleVrVideoCallbacks var1);

    public void setVideoLocationTransform(float[] var1);

    public static interface GoogleVrVideoCallbacks {
        public void onFrameAvailable();

        public void onSurfaceAvailable(Surface var1);

        public void onSurfaceUnavailable();
    }

}

