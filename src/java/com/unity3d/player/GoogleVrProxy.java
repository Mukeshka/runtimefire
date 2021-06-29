/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  F
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 *  android.view.Surface
 *  android.view.SurfaceHolder
 *  android.view.SurfaceView
 *  android.view.View
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.Thread
 *  java.lang.reflect.Array
 *  java.lang.reflect.Constructor
 *  java.util.Iterator
 *  java.util.Vector
 *  java.util.concurrent.atomic.AtomicLong
 */
package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.unity3d.player.GoogleVrVideo;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.c;
import com.unity3d.player.f;
import com.unity3d.player.o;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

class GoogleVrProxy
extends c
implements GoogleVrVideo {
    private boolean f = false;
    private boolean g = false;
    private Runnable h = null;
    private Vector i = new Vector();
    private SurfaceView j = null;
    private a k = new a();
    private Thread l = null;
    private Handler m = new Handler(Looper.getMainLooper()){

        public final void handleMessage(Message message) {
            if (message.what != 135711) {
                super.handleMessage(message);
                return;
            }
            switch (message.arg1) {
                default: {
                    super.handleMessage(message);
                    return;
                }
                case 2147483646: {
                    Surface surface = (Surface)message.obj;
                    Iterator iterator = GoogleVrProxy.this.i.iterator();
                    while (iterator.hasNext()) {
                        ((GoogleVrVideo.GoogleVrVideoCallbacks)iterator.next()).onSurfaceAvailable(surface);
                    }
                    return;
                }
                case 2147483645: 
            }
            Iterator iterator = GoogleVrProxy.this.i.iterator();
            while (iterator.hasNext()) {
                ((GoogleVrVideo.GoogleVrVideoCallbacks)iterator.next()).onFrameAvailable();
            }
        }
    };

    public GoogleVrProxy(f f2) {
        super("Google VR", f2);
        this.initVrJni();
    }

    private void a(boolean bl) {
        this.k.d = bl;
    }

    private boolean a(ClassLoader classLoader) {
        try {
            Class class_ = classLoader.loadClass("com.unity3d.unitygvr.GoogleVR");
            o o2 = new o(class_, class_.getConstructor(new Class[0]).newInstance(new Object[0]));
            Class[] arrclass = new Class[]{Activity.class, Context.class, SurfaceView.class, Boolean.TYPE, Handler.class};
            o2.a("initialize", arrclass);
            o2.a("deinitialize", new Class[0]);
            Class[] arrclass2 = new Class[]{Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Runnable.class};
            o2.a("load", arrclass2);
            Class[] arrclass3 = new Class[]{Boolean.TYPE};
            o2.a("enable", arrclass3);
            o2.a("unload", new Class[0]);
            o2.a("pause", new Class[0]);
            o2.a("resume", new Class[0]);
            o2.a("getGvrLayout", new Class[0]);
            o2.a("getVideoSurfaceId", new Class[0]);
            o2.a("getVideoSurface", new Class[0]);
            this.a = o2;
            return true;
        }
        catch (Exception exception) {
            StringBuilder stringBuilder = new StringBuilder("Exception initializing GoogleVR from Unity library. ");
            stringBuilder.append(exception.getLocalizedMessage());
            this.reportError(stringBuilder.toString());
            return false;
        }
    }

    static /* synthetic */ a c(GoogleVrProxy googleVrProxy) {
        return googleVrProxy.k;
    }

    private boolean d() {
        return this.k.d;
    }

    private void e() {
        Activity activity = (Activity)this.c;
        if (this.g && !this.k.f && activity != null) {
            this.k.f = true;
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(268435456);
            activity.startActivity(intent);
        }
    }

    private static boolean f() {
        return Build.VERSION.SDK_INT >= 24;
    }

    private final native void initVrJni();

    private final native boolean isQuiting();

    private final native void setVrVideoTransform(float[][] var1);

    public final void a(Intent intent) {
        if (intent != null && intent.getBooleanExtra("android.intent.extra.VR_LAUNCH", false)) {
            this.g = true;
        }
    }

    public final boolean a() {
        return this.k.a;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final boolean a(Activity activity, Context context, SurfaceView surfaceView, Runnable runnable) {
        String string2;
        if (activity != null && context != null && surfaceView != null && runnable != null) {
            this.k.b();
            this.c = context;
            this.h = runnable;
            if (this.g && !GoogleVrProxy.f()) {
                string2 = "Daydream requires a device that supports an api version of 24 (Nougat) or better.";
            } else {
                boolean bl;
                if (!this.a(UnityPlayer.class.getClassLoader())) {
                    return false;
                }
                try {
                    o o2 = this.a;
                    Object[] arrobject = new Object[]{activity, context, surfaceView, this.g, this.m};
                    bl = (Boolean)o2.a("initialize", arrobject);
                }
                catch (Exception exception) {
                    StringBuilder stringBuilder = new StringBuilder("Exception while trying to initialize Unity Google VR Library. ");
                    stringBuilder.append(exception.getLocalizedMessage());
                    this.reportError(stringBuilder.toString());
                    bl = false;
                }
                if (bl) {
                    this.j = surfaceView;
                    this.k.a = true;
                    this.d = "";
                    return true;
                }
                string2 = "Unable to initialize GoogleVR library.";
            }
        } else {
            string2 = "Invalid parameters passed to Google VR initialization.";
        }
        this.reportError(string2);
        return false;
    }

    public final void b() {
        this.resumeGvrLayout();
    }

    public final void c() {
        SurfaceView surfaceView = this.j;
        if (surfaceView != null) {
            surfaceView.getHolder().setSizeFromLayout();
        }
    }

    @Override
    public void deregisterGoogleVrVideoListener(GoogleVrVideo.GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (this.i.contains((Object)googleVrVideoCallbacks)) {
            googleVrVideoCallbacks.onSurfaceUnavailable();
            this.i.remove((Object)googleVrVideoCallbacks);
        }
    }

    protected Object getVideoSurface() {
        if (this.d()) {
            if (this.k.e) {
                return null;
            }
            try {
                Object object = this.a.a("getVideoSurface", new Object[0]);
                return object;
            }
            catch (Exception exception) {
                StringBuilder stringBuilder = new StringBuilder("Exception caught while Getting GoogleVR Video Surface. ");
                stringBuilder.append(exception.getLocalizedMessage());
                this.reportError(stringBuilder.toString());
            }
        }
        return null;
    }

    protected int getVideoSurfaceId() {
        if (this.d()) {
            if (this.k.e) {
                return -1;
            }
            try {
                int n2 = (Integer)this.a.a("getVideoSurfaceId", new Object[0]);
                return n2;
            }
            catch (Exception exception) {
                StringBuilder stringBuilder = new StringBuilder("Exception caught while getting Video Surface ID from GoogleVR. ");
                stringBuilder.append(exception.getLocalizedMessage());
                this.reportError(stringBuilder.toString());
            }
        }
        return -1;
    }

    protected long loadGoogleVr(final boolean bl, final boolean bl2, final boolean bl3, final boolean bl4, final boolean bl5) {
        if (!this.k.a) {
            return 0L;
        }
        final AtomicLong atomicLong = new AtomicLong(0L);
        String string2 = !bl && !bl2 ? "Cardboard" : "Daydream";
        this.d = string2;
        Runnable runnable = new Runnable(){

            public final void run() {
                try {
                    AtomicLong atomicLong2 = atomicLong;
                    o o2 = GoogleVrProxy.this.a;
                    Object[] arrobject = new Object[]{bl, bl2, bl3, bl4, bl5, GoogleVrProxy.this.h};
                    atomicLong2.set(((Long)o2.a("load", arrobject)).longValue());
                    GoogleVrProxy.c((GoogleVrProxy)GoogleVrProxy.this).b = true;
                    return;
                }
                catch (Exception exception) {
                    GoogleVrProxy googleVrProxy = GoogleVrProxy.this;
                    StringBuilder stringBuilder = new StringBuilder("Exception caught while loading GoogleVR. ");
                    stringBuilder.append(exception.getLocalizedMessage());
                    googleVrProxy.reportError(stringBuilder.toString());
                    atomicLong.set(0L);
                    return;
                }
            }
        };
        if (!this.runOnUiThreadWithSync(runnable) || atomicLong.longValue() == 0L) {
            this.reportError("Google VR had a fatal issue while loading. VR will not be available.");
        }
        return atomicLong.longValue();
    }

    protected void pauseGvrLayout() {
        if (!this.k.a()) {
            return;
        }
        if (!this.k.e) {
            if (this.d()) {
                Iterator iterator = this.i.iterator();
                while (iterator.hasNext()) {
                    ((GoogleVrVideo.GoogleVrVideoCallbacks)iterator.next()).onSurfaceUnavailable();
                }
            }
            if (this.a != null) {
                this.a.a("pause", new Object[0]);
            }
            this.k.e = true;
        }
    }

    @Override
    public void registerGoogleVrVideoListener(GoogleVrVideo.GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (!this.i.contains((Object)googleVrVideoCallbacks)) {
            this.i.add((Object)googleVrVideoCallbacks);
            Surface surface = (Surface)this.getVideoSurface();
            if (surface != null) {
                googleVrVideoCallbacks.onSurfaceAvailable(surface);
            }
        }
    }

    protected void resumeGvrLayout() {
        if (!this.k.a()) {
            return;
        }
        if (this.k.e) {
            if (this.a != null) {
                this.a.a("resume", new Object[0]);
            }
            this.k.e = false;
        }
    }

    protected void setGoogleVrModeEnabled(final boolean bl) {
        if (!this.k.a()) {
            return;
        }
        if (this.b != null) {
            if (this.c == null) {
                return;
            }
            if (!bl && this.isQuiting()) {
                this.e();
            }
            this.runOnUiThread(new Runnable(){

                /*
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 */
                public final void run() {
                    if (bl == GoogleVrProxy.this.d()) {
                        return;
                    }
                    try {
                        boolean bl2 = bl;
                        if (bl2 && !GoogleVrProxy.this.d()) {
                            if (GoogleVrProxy.this.a != null && GoogleVrProxy.this.b != null && !GoogleVrProxy.this.b.addViewToPlayer((View)GoogleVrProxy.this.a.a("getGvrLayout", new Object[0]), true)) {
                                GoogleVrProxy.this.reportError("Unable to add Google VR to view hierarchy.");
                                return;
                            }
                            if (GoogleVrProxy.this.a != null) {
                                o o2 = GoogleVrProxy.this.a;
                                Object[] arrobject = new Object[]{true};
                                o2.a("enable", arrobject);
                            }
                            GoogleVrProxy.this.a(true);
                            return;
                        }
                        if (!bl && GoogleVrProxy.this.d()) {
                            GoogleVrProxy.this.a(false);
                            if (GoogleVrProxy.this.a != null) {
                                o o3 = GoogleVrProxy.this.a;
                                Object[] arrobject = new Object[]{false};
                                o3.a("enable", arrobject);
                            }
                            if (GoogleVrProxy.this.a != null && GoogleVrProxy.this.b != null) {
                                GoogleVrProxy.this.b.removeViewFromPlayer((View)GoogleVrProxy.this.a.a("getGvrLayout", new Object[0]));
                            }
                        }
                        return;
                    }
                    catch (Exception exception) {
                        GoogleVrProxy googleVrProxy = GoogleVrProxy.this;
                        StringBuilder stringBuilder = new StringBuilder("Exception enabling Google VR on UI Thread. ");
                        stringBuilder.append(exception.getLocalizedMessage());
                        googleVrProxy.reportError(stringBuilder.toString());
                        return;
                    }
                }
            });
        }
    }

    @Override
    public void setVideoLocationTransform(float[] arrf) {
        float[][] arrf2 = (float[][])Array.newInstance(F.class, (int[])new int[]{4, 4});
        for (int i2 = 0; i2 < 4; ++i2) {
            for (int i3 = 0; i3 < 4; ++i3) {
                arrf2[i2][i3] = arrf[i3 + i2 * 4];
            }
        }
        this.setVrVideoTransform(arrf2);
    }

    protected void unloadGoogleVr() {
        if (this.k.d) {
            this.setGoogleVrModeEnabled(false);
        }
        if (this.k.c) {
            this.k.c = false;
        }
        this.j = null;
        this.runOnUiThread(new Runnable(){

            public final void run() {
                try {
                    if (GoogleVrProxy.this.a != null) {
                        GoogleVrProxy.this.a.a("unload", new Object[0]);
                        GoogleVrProxy.this.a.a("deinitialize", new Object[0]);
                        GoogleVrProxy.this.a = null;
                    }
                    GoogleVrProxy.c((GoogleVrProxy)GoogleVrProxy.this).b = false;
                    return;
                }
                catch (Exception exception) {
                    GoogleVrProxy googleVrProxy = GoogleVrProxy.this;
                    StringBuilder stringBuilder = new StringBuilder("Exception unloading Google VR on UI Thread. ");
                    stringBuilder.append(exception.getLocalizedMessage());
                    googleVrProxy.reportError(stringBuilder.toString());
                    return;
                }
            }
        });
    }

    final class a {
        public boolean a = false;
        public boolean b = false;
        public boolean c = false;
        public boolean d = false;
        public boolean e = true;
        public boolean f = false;

        a() {
        }

        public final boolean a() {
            return this.a && this.b;
        }

        public final void b() {
            this.a = false;
            this.b = false;
            this.d = false;
            this.e = true;
            this.f = false;
        }
    }

}

