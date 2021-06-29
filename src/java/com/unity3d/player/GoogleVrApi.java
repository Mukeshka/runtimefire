/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.concurrent.atomic.AtomicReference
 */
package com.unity3d.player;

import com.unity3d.player.GoogleVrProxy;
import com.unity3d.player.GoogleVrVideo;
import com.unity3d.player.f;
import java.util.concurrent.atomic.AtomicReference;

public class GoogleVrApi {
    private static AtomicReference a = new AtomicReference();

    private GoogleVrApi() {
    }

    static void a() {
        a.set(null);
    }

    static void a(f f2) {
        a.compareAndSet(null, (Object)new GoogleVrProxy(f2));
    }

    static GoogleVrProxy b() {
        return (GoogleVrProxy)a.get();
    }

    public static GoogleVrVideo getGoogleVrVideo() {
        return (GoogleVrVideo)a.get();
    }
}

