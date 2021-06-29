/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.BroadcastReceiver
 *  android.content.ClipData
 *  android.content.ClipData$Item
 *  android.content.ClipboardManager
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageManager
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.graphics.Rect
 *  android.hardware.Sensor
 *  android.hardware.SensorEvent
 *  android.hardware.SensorEventListener
 *  android.hardware.SensorManager
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Handler$Callback
 *  android.os.Looper
 *  android.os.Message
 *  android.os.Process
 *  android.telephony.PhoneStateListener
 *  android.telephony.TelephonyManager
 *  android.util.TypedValue
 *  android.view.Display
 *  android.view.InputEvent
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.OrientationEventListener
 *  android.view.Surface
 *  android.view.SurfaceHolder
 *  android.view.SurfaceHolder$Callback
 *  android.view.SurfaceView
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewParent
 *  android.view.Window
 *  android.view.WindowManager
 *  android.widget.FrameLayout
 *  java.io.UnsupportedEncodingException
 *  java.lang.CharSequence
 *  java.lang.Enum
 *  java.lang.Exception
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.SecurityException
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.UnsatisfiedLinkError
 *  java.util.concurrent.ConcurrentLinkedQueue
 *  java.util.concurrent.Semaphore
 *  java.util.concurrent.TimeUnit
 */
package com.unity3d.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.Display;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.unity3d.player.AudioVolumeHandler;
import com.unity3d.player.Camera2Wrapper;
import com.unity3d.player.GoogleARCoreApi;
import com.unity3d.player.GoogleVrApi;
import com.unity3d.player.GoogleVrProxy;
import com.unity3d.player.HFPStatus;
import com.unity3d.player.IUnityPlayerLifecycleEvents;
import com.unity3d.player.NativeLoader;
import com.unity3d.player.NetworkConnectivity;
import com.unity3d.player.g;
import com.unity3d.player.j;
import com.unity3d.player.k;
import com.unity3d.player.l;
import com.unity3d.player.m;
import com.unity3d.player.n;
import com.unity3d.player.q;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class UnityPlayer
extends FrameLayout
implements IUnityPlayerLifecycleEvents,
com.unity3d.player.f {
    private static final int ANR_TIMEOUT_SECONDS = 4;
    private static final int RUN_STATE_CHANGED_MSG_CODE = 2269;
    private static final String SPLASH_ENABLE_METADATA_NAME = "unity.splash-enable";
    private static final String SPLASH_MODE_METADATA_NAME = "unity.splash-mode";
    private static final String TANGO_ENABLE_METADATA_NAME = "unity.tango-enable";
    public static Activity currentActivity;
    private Context mContext;
    private SurfaceView mGlView;
    private int mInitialScreenOrientation = -1;
    private boolean mIsFullscreen = true;
    private BroadcastReceiver mKillingIsMyBusiness = null;
    private boolean mMainDisplayOverride = false;
    private int mNaturalOrientation;
    private OrientationEventListener mOrientationListener = null;
    private boolean mProcessKillRequested = true;
    private boolean mQuitting;
    k mSoftInputDialog = null;
    private n mState = new n();
    private q mVideoPlayerProxy;
    private GoogleARCoreApi m_ARCoreApi = null;
    private boolean m_AddPhoneCallListener = false;
    private AudioVolumeHandler m_AudioVolumeHandler = null;
    private Camera2Wrapper m_Camera2Wrapper = null;
    private ClipboardManager m_ClipboardManager;
    private final ConcurrentLinkedQueue m_Events = new ConcurrentLinkedQueue();
    private a m_FakeListener = new a();
    private HFPStatus m_HFPStatus = null;
    e m_MainThread = new e(0);
    private NetworkConnectivity m_NetworkConnectivity = null;
    private c m_PhoneCallListener = new c(0);
    private l m_SplashScreen;
    private TelephonyManager m_TelephonyManager;
    private IUnityPlayerLifecycleEvents m_UnityPlayerLifecycleEvents = null;
    private Uri m_launchUri = null;

    static {
        new m().a();
    }

    public UnityPlayer(Context context) {
        this(context, null);
    }

    public UnityPlayer(Context context, IUnityPlayerLifecycleEvents iUnityPlayerLifecycleEvents) {
        super(context);
        this.m_UnityPlayerLifecycleEvents = iUnityPlayerLifecycleEvents;
        if (context instanceof Activity) {
            currentActivity = (Activity)context;
            this.mInitialScreenOrientation = currentActivity.getRequestedOrientation();
            this.m_launchUri = currentActivity.getIntent().getData();
        }
        this.EarlyEnableFullScreenIfVrLaunched(currentActivity);
        this.mContext = context;
        this.mNaturalOrientation = this.getNaturalOrientation(this.getResources().getConfiguration().orientation);
        if (currentActivity != null && this.getSplashEnabled()) {
            this.m_SplashScreen = new l(this.mContext, l.a.a()[this.getSplashMode()]);
            this.addView((View)this.m_SplashScreen);
        }
        String string2 = UnityPlayer.loadNative(this.mContext.getApplicationInfo());
        if (!n.c()) {
            g.Log(6, "Your hardware does not support this application.");
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext).setTitle((CharSequence)"Failure to initialize!").setPositiveButton((CharSequence)"OK", new DialogInterface.OnClickListener(){

                public final void onClick(DialogInterface dialogInterface, int n2) {
                    UnityPlayer.this.finish();
                }
            });
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Your hardware does not support this application.");
            stringBuilder.append("\n\n");
            stringBuilder.append(string2);
            stringBuilder.append("\n\n Press OK to quit.");
            AlertDialog alertDialog = builder.setMessage((CharSequence)stringBuilder.toString()).create();
            alertDialog.setCancelable(false);
            alertDialog.show();
            return;
        }
        this.initJni(context);
        this.mState.c(true);
        this.mGlView = this.CreateGlView();
        this.mGlView.setContentDescription((CharSequence)this.GetGlViewContentDescription(context));
        this.addView((View)this.mGlView);
        this.bringChildToFront((View)this.m_SplashScreen);
        this.mQuitting = false;
        this.hideStatusBar();
        this.m_TelephonyManager = (TelephonyManager)this.mContext.getSystemService("phone");
        this.m_ClipboardManager = (ClipboardManager)this.mContext.getSystemService("clipboard");
        this.m_Camera2Wrapper = new Camera2Wrapper(this.mContext);
        this.m_HFPStatus = new HFPStatus(this.mContext);
        this.m_MainThread.start();
    }

    private SurfaceView CreateGlView() {
        SurfaceView surfaceView = new SurfaceView(this.mContext);
        surfaceView.setId(this.mContext.getResources().getIdentifier("unitySurfaceView", "id", this.mContext.getPackageName()));
        if (this.IsWindowTranslucent()) {
            surfaceView.getHolder().setFormat(-3);
            surfaceView.setZOrderOnTop(true);
        } else {
            surfaceView.getHolder().setFormat(-1);
        }
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback(){

            public final void surfaceChanged(SurfaceHolder surfaceHolder, int n2, int n3, int n4) {
                UnityPlayer.this.updateGLDisplay(0, surfaceHolder.getSurface());
                UnityPlayer.this.sendSurfaceChangedEvent();
            }

            public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                UnityPlayer.this.updateGLDisplay(0, surfaceHolder.getSurface());
            }

            public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                UnityPlayer.this.updateGLDisplay(0, null);
            }
        });
        surfaceView.setFocusable(true);
        surfaceView.setFocusableInTouchMode(true);
        return surfaceView;
    }

    private void DisableStaticSplashScreen() {
        this.runOnUiThread(new Runnable(){

            public final void run() {
                UnityPlayer unityPlayer = UnityPlayer.this;
                unityPlayer.removeView((View)unityPlayer.m_SplashScreen);
                UnityPlayer.this.m_SplashScreen = null;
            }
        });
    }

    private void EarlyEnableFullScreenIfVrLaunched(Activity activity) {
        View view;
        if (activity != null && activity.getIntent().getBooleanExtra("android.intent.extra.VR_LAUNCH", false) && activity.getWindow() != null && (view = activity.getWindow().getDecorView()) != null) {
            view.setSystemUiVisibility(7);
        }
    }

    private String GetGlViewContentDescription(Context context) {
        return context.getResources().getString(context.getResources().getIdentifier("game_view_content_description", "string", context.getPackageName()));
    }

    private boolean IsWindowTranslucent() {
        if (currentActivity == null) {
            return false;
        }
        TypedValue typedValue = new TypedValue();
        return currentActivity.getTheme().resolveAttribute(16842840, typedValue, true) && typedValue.type == 18 && typedValue.data != 0;
    }

    public static void UnitySendMessage(String string2, String string3, String string4) {
        if (!n.c()) {
            StringBuilder stringBuilder = new StringBuilder("Native libraries not loaded - dropping message for ");
            stringBuilder.append(string2);
            stringBuilder.append(".");
            stringBuilder.append(string3);
            g.Log(5, stringBuilder.toString());
            return;
        }
        try {
            UnityPlayer.nativeUnitySendMessage(string2, string3, string4.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {}
    }

    static /* synthetic */ boolean access$100(UnityPlayer unityPlayer) {
        return unityPlayer.getSplashEnabled();
    }

    static /* synthetic */ void access$200(UnityPlayer unityPlayer) {
        unityPlayer.DisableStaticSplashScreen();
    }

    static /* synthetic */ boolean access$300(UnityPlayer unityPlayer) {
        return unityPlayer.nativeRender();
    }

    static /* synthetic */ void access$500(UnityPlayer unityPlayer, String string2) {
        unityPlayer.nativeSetLaunchURL(string2);
    }

    static /* synthetic */ void access$600(UnityPlayer unityPlayer, int n2, int n3) {
        unityPlayer.nativeOrientationChanged(n2, n3);
    }

    private void checkResumePlayer() {
        if (!this.mState.f()) {
            return;
        }
        this.mState.d(true);
        this.queueGLThreadEvent(new Runnable(){

            public final void run() {
                UnityPlayer.this.nativeResume();
            }
        });
        this.m_MainThread.b();
    }

    private void finish() {
        Context context = this.mContext;
        if (context instanceof Activity && !((Activity)context).isFinishing()) {
            ((Activity)this.mContext).finish();
        }
    }

    private ApplicationInfo getApplicationInfo() {
        return this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
    }

    private int getNaturalOrientation(int n2) {
        int n3 = ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        return (n3 != 0 && n3 != 2 || n2 != 2) && (n3 != 1 && n3 != 3 || n2 != 1);
        {
        }
    }

    private boolean getSplashEnabled() {
        try {
            boolean bl = this.getApplicationInfo().metaData.getBoolean(SPLASH_ENABLE_METADATA_NAME);
            return bl;
        }
        catch (Exception exception) {
            return false;
        }
    }

    private boolean getTangoEnabled() {
        try {
            boolean bl = this.getApplicationInfo().metaData.getBoolean(TANGO_ENABLE_METADATA_NAME);
            return bl;
        }
        catch (Exception exception) {
            return false;
        }
    }

    private void hideStatusBar() {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity)context).getWindow().setFlags(1024, 1024);
        }
    }

    private final native void initJni(Context var1);

    /*
     * Exception decompiling
     */
    protected static boolean loadLibraryStatic(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Invalid stack depths @ lbl15 : BIPUSH : trying to set 1 previously set to 0
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

    private static String loadNative(ApplicationInfo applicationInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(applicationInfo.nativeLibraryDir);
        stringBuilder.append("/libmain.so");
        String string2 = stringBuilder.toString();
        try {
            System.loadLibrary((String)"main");
        }
        catch (SecurityException securityException) {
            return UnityPlayer.logLoadLibMainError(string2, securityException.toString());
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            return UnityPlayer.logLoadLibMainError(string2, unsatisfiedLinkError.toString());
        }
        if (NativeLoader.load(applicationInfo.nativeLibraryDir)) {
            n.a();
            return "";
        }
        g.Log(6, "NativeLoader.load failure, Unity libraries were not loaded.");
        return "NativeLoader.load failure, Unity libraries were not loaded.";
    }

    private static String logLoadLibMainError(String string2, String string3) {
        StringBuilder stringBuilder = new StringBuilder("Failed to load 'libmain.so'\n\n");
        stringBuilder.append(string3);
        String string4 = stringBuilder.toString();
        g.Log(6, string4);
        return string4;
    }

    private final native void nativeApplicationUnload();

    private final native boolean nativeDone();

    private final native void nativeFocusChanged(boolean var1);

    private final native boolean nativeInjectEvent(InputEvent var1);

    private final native boolean nativeIsAutorotationOn();

    private final native void nativeLowMemory();

    private final native void nativeMuteMasterAudio(boolean var1);

    private final native void nativeOrientationChanged(int var1, int var2);

    private final native boolean nativePause();

    private final native void nativeRecreateGfxState(int var1, Surface var2);

    private final native boolean nativeRender();

    private final native void nativeReportKeyboardConfigChanged();

    private final native void nativeRestartActivityIndicator();

    private final native void nativeResume();

    private final native void nativeSendSurfaceChangedEvent();

    private final native void nativeSetInputArea(int var1, int var2, int var3, int var4);

    private final native void nativeSetInputSelection(int var1, int var2);

    private final native void nativeSetInputString(String var1);

    private final native void nativeSetKeyboardIsVisible(boolean var1);

    private final native void nativeSetLaunchURL(String var1);

    private final native void nativeSoftInputCanceled();

    private final native void nativeSoftInputClosed();

    private final native void nativeSoftInputLostFocus();

    private static native void nativeUnitySendMessage(String var0, String var1, byte[] var2);

    /*
     * Exception decompiling
     */
    private void pauseUnity() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Invalid stack depths @ lbl50 : ALOAD_1 : trying to set 1 previously set to 0
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

    private void queueGLThreadEvent(f f2) {
        if (this.isFinishing()) {
            return;
        }
        this.queueGLThreadEvent((Runnable)f2);
    }

    private void queueGLThreadEvent(Runnable runnable) {
        if (!n.c()) {
            return;
        }
        if (Thread.currentThread() == this.m_MainThread) {
            runnable.run();
            return;
        }
        this.m_Events.add((Object)runnable);
    }

    private void sendSurfaceChangedEvent() {
        if (n.c()) {
            if (!this.mState.e()) {
                return;
            }
            Runnable runnable = new Runnable(){

                public final void run() {
                    UnityPlayer.this.nativeSendSurfaceChangedEvent();
                }
            };
            this.m_MainThread.d(runnable);
        }
    }

    private void shutdown() {
        this.mProcessKillRequested = this.nativeDone();
        this.mState.c(false);
    }

    private void swapViews(View view, View view2) {
        ViewParent viewParent;
        boolean bl;
        if (!this.mState.d()) {
            this.pause();
            bl = true;
        } else {
            bl = false;
        }
        if (!(view == null || (viewParent = view.getParent()) instanceof UnityPlayer && (UnityPlayer)viewParent == this)) {
            if (viewParent instanceof ViewGroup) {
                ((ViewGroup)viewParent).removeView(view);
            }
            this.addView(view);
            this.bringChildToFront(view);
            view.setVisibility(0);
        }
        if (view2 != null && view2.getParent() == this) {
            view2.setVisibility(8);
            this.removeView(view2);
        }
        if (bl) {
            this.resume();
        }
    }

    private static void unloadNative() {
        if (!n.c()) {
            return;
        }
        if (NativeLoader.unload()) {
            n.b();
            return;
        }
        throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
    }

    /*
     * Exception decompiling
     */
    private boolean updateDisplayInternal(int var1, Surface var2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Invalid stack depths @ lbl54 : ICONST_1 : trying to set 1 previously set to 0
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

    private void updateGLDisplay(int n2, Surface surface) {
        if (this.mMainDisplayOverride) {
            return;
        }
        this.updateDisplayInternal(n2, surface);
    }

    protected void addPhoneCallListener() {
        this.m_AddPhoneCallListener = true;
        this.m_TelephonyManager.listen((PhoneStateListener)this.m_PhoneCallListener, 32);
    }

    @Override
    public boolean addViewToPlayer(View view, boolean bl) {
        SurfaceView surfaceView = bl ? this.mGlView : null;
        this.swapViews(view, (View)surfaceView);
        ViewParent viewParent = view.getParent();
        boolean bl2 = true;
        boolean bl3 = viewParent == this;
        boolean bl4 = bl && this.mGlView.getParent() == null;
        boolean bl5 = this.mGlView.getParent() == this;
        if (!bl3 || !bl4 && !bl5) {
            bl2 = false;
        }
        if (!bl2) {
            if (!bl3) {
                g.Log(6, "addViewToPlayer: Failure adding view to hierarchy");
            }
            if (!bl4 && !bl5) {
                g.Log(6, "addViewToPlayer: Failure removing old view from hierarchy");
            }
        }
        return bl2;
    }

    public void configurationChanged(Configuration configuration) {
        q q2;
        GoogleVrProxy googleVrProxy;
        SurfaceView surfaceView = this.mGlView;
        if (surfaceView instanceof SurfaceView) {
            surfaceView.getHolder().setSizeFromLayout();
        }
        if ((q2 = this.mVideoPlayerProxy) != null) {
            q2.c();
        }
        if ((googleVrProxy = GoogleVrApi.b()) != null) {
            googleVrProxy.c();
        }
    }

    /*
     * Exception decompiling
     */
    public void destroy() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Invalid stack depths @ lbl54 : ALOAD_0 : trying to set 1 previously set to 0
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

    protected void disableLogger() {
        g.a = true;
    }

    public boolean displayChanged(int n2, Surface surface) {
        if (n2 == 0) {
            boolean bl = surface != null;
            this.mMainDisplayOverride = bl;
            this.runOnUiThread(new Runnable(){

                public final void run() {
                    if (UnityPlayer.this.mMainDisplayOverride) {
                        UnityPlayer unityPlayer = UnityPlayer.this;
                        unityPlayer.removeView((View)unityPlayer.mGlView);
                        return;
                    }
                    UnityPlayer unityPlayer = UnityPlayer.this;
                    unityPlayer.addView((View)unityPlayer.mGlView);
                }
            });
        }
        return this.updateDisplayInternal(n2, surface);
    }

    protected void executeGLThreadJobs() {
        Runnable runnable;
        while ((runnable = (Runnable)this.m_Events.poll()) != null) {
            runnable.run();
        }
    }

    protected String getClipboardText() {
        ClipData clipData = this.m_ClipboardManager.getPrimaryClip();
        if (clipData != null) {
            return clipData.getItemAt(0).coerceToText(this.mContext).toString();
        }
        return "";
    }

    protected String getKeyboardLayout() {
        k k2 = this.mSoftInputDialog;
        if (k2 == null) {
            return null;
        }
        return k2.a();
    }

    protected String getLaunchURL() {
        Uri uri = this.m_launchUri;
        if (uri != null) {
            return uri.toString();
        }
        return null;
    }

    protected int getNetworkConnectivity() {
        if (j.c) {
            if (this.m_NetworkConnectivity == null) {
                this.m_NetworkConnectivity = new NetworkConnectivity(this.mContext);
            }
            return this.m_NetworkConnectivity.a();
        }
        return 0;
    }

    public String getNetworkProxySettings(String string2) {
        block9 : {
            String string3;
            String string4;
            block8 : {
                block7 : {
                    if (!string2.startsWith("http:")) break block7;
                    string4 = "http.proxyHost";
                    string3 = "http.proxyPort";
                    break block8;
                }
                if (!string2.startsWith("https:")) break block9;
                string4 = "https.proxyHost";
                string3 = "https.proxyPort";
            }
            String string5 = System.getProperties().getProperty(string4);
            if (string5 != null) {
                String string6;
                if ("".equals((Object)string5)) {
                    return null;
                }
                StringBuilder stringBuilder = new StringBuilder(string5);
                String string7 = System.getProperties().getProperty(string3);
                if (string7 != null && !"".equals((Object)string7)) {
                    stringBuilder.append(":");
                    stringBuilder.append(string7);
                }
                if ((string6 = System.getProperties().getProperty("http.nonProxyHosts")) != null && !"".equals((Object)string6)) {
                    stringBuilder.append('\n');
                    stringBuilder.append(string6);
                }
                return stringBuilder.toString();
            }
        }
        return null;
    }

    public Bundle getSettings() {
        return Bundle.EMPTY;
    }

    protected int getSplashMode() {
        try {
            int n2 = this.getApplicationInfo().metaData.getInt("unity.splash-mode");
            return n2;
        }
        catch (Exception exception) {
            return 0;
        }
    }

    public View getView() {
        return this;
    }

    protected void hideSoftInput() {
        this.postOnUiThread(new Runnable(){

            public final void run() {
                UnityPlayer.this.reportSoftInputArea(new Rect());
                UnityPlayer.this.reportSoftInputIsVisible(false);
                if (UnityPlayer.this.mSoftInputDialog != null) {
                    UnityPlayer.this.mSoftInputDialog.dismiss();
                    UnityPlayer unityPlayer = UnityPlayer.this;
                    unityPlayer.mSoftInputDialog = null;
                    unityPlayer.nativeReportKeyboardConfigChanged();
                }
            }
        });
    }

    public void init(int n2, boolean bl) {
    }

    protected boolean initializeGoogleAr() {
        if (this.m_ARCoreApi == null && currentActivity != null && this.getTangoEnabled()) {
            this.m_ARCoreApi = new GoogleARCoreApi();
            this.m_ARCoreApi.initializeARCore(currentActivity);
            if (!this.mState.d()) {
                this.m_ARCoreApi.resumeARCore();
            }
        }
        return false;
    }

    protected boolean initializeGoogleVr() {
        GoogleVrProxy googleVrProxy;
        block4 : {
            googleVrProxy = GoogleVrApi.b();
            if (googleVrProxy == null) {
                GoogleVrApi.a(this);
                googleVrProxy = GoogleVrApi.b();
                if (googleVrProxy == null) {
                    g.Log(6, "Unable to create Google VR subsystem.");
                    return false;
                }
            }
            Semaphore semaphore = new Semaphore(0);
            this.runOnUiThread(new Runnable(new Runnable(){

                public final void run() {
                    UnityPlayer.this.injectEvent((InputEvent)new KeyEvent(0, 4));
                    UnityPlayer.this.injectEvent((InputEvent)new KeyEvent(1, 4));
                }
            }, semaphore){
                final /* synthetic */ Runnable b;
                final /* synthetic */ Semaphore c;
                {
                    this.b = runnable;
                    this.c = semaphore;
                }

                public final void run() {
                    if (!googleVrProxy.a(UnityPlayer.currentActivity, UnityPlayer.this.mContext, UnityPlayer.this.CreateGlView(), this.b)) {
                        g.Log(6, "Unable to initialize Google VR subsystem.");
                    }
                    if (UnityPlayer.currentActivity != null) {
                        googleVrProxy.a(UnityPlayer.currentActivity.getIntent());
                    }
                    this.c.release();
                }
            });
            try {
                if (semaphore.tryAcquire(4L, TimeUnit.SECONDS)) break block4;
                g.Log(5, "Timeout while trying to initialize Google VR.");
                return false;
            }
            catch (InterruptedException interruptedException) {
                StringBuilder stringBuilder = new StringBuilder("UI thread was interrupted while initializing Google VR. ");
                stringBuilder.append(interruptedException.getLocalizedMessage());
                g.Log(5, stringBuilder.toString());
                return false;
            }
        }
        return googleVrProxy.a();
    }

    public boolean injectEvent(InputEvent inputEvent) {
        if (!n.c()) {
            return false;
        }
        return this.nativeInjectEvent(inputEvent);
    }

    protected boolean isFinishing() {
        if (!this.mQuitting) {
            Context context = this.mContext;
            boolean bl = context instanceof Activity && ((Activity)context).isFinishing();
            this.mQuitting = bl;
            return bl;
        }
        return true;
    }

    protected void kill() {
        Process.killProcess((int)Process.myPid());
    }

    protected boolean loadLibrary(String string2) {
        return UnityPlayer.loadLibraryStatic(string2);
    }

    public void lowMemory() {
        if (!n.c()) {
            return;
        }
        this.queueGLThreadEvent(new Runnable(){

            public final void run() {
                UnityPlayer.this.nativeLowMemory();
            }
        });
    }

    public void newIntent(Intent intent) {
        this.m_launchUri = intent.getData();
        this.m_MainThread.e();
    }

    protected void notifyOnUnityPlayerUnloaded() {
        this.runOnUiThread(new Runnable(){

            public final void run() {
                UnityPlayer.this.pause();
                UnityPlayer.this.windowFocusChanged(false);
                if (UnityPlayer.this.m_UnityPlayerLifecycleEvents != null) {
                    UnityPlayer.this.m_UnityPlayerLifecycleEvents.onUnityPlayerUnloaded();
                    return;
                }
                UnityPlayer.this.onUnityPlayerUnloaded();
            }
        });
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return this.injectEvent((InputEvent)motionEvent);
    }

    public boolean onKeyDown(int n2, KeyEvent keyEvent) {
        return this.injectEvent((InputEvent)keyEvent);
    }

    public boolean onKeyLongPress(int n2, KeyEvent keyEvent) {
        return this.injectEvent((InputEvent)keyEvent);
    }

    public boolean onKeyMultiple(int n2, int n3, KeyEvent keyEvent) {
        return this.injectEvent((InputEvent)keyEvent);
    }

    public boolean onKeyUp(int n2, KeyEvent keyEvent) {
        return this.injectEvent((InputEvent)keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.injectEvent((InputEvent)motionEvent);
    }

    @Override
    public void onUnityPlayerQuitted() {
    }

    @Override
    public void onUnityPlayerUnloaded() {
    }

    public void pause() {
        GoogleVrProxy googleVrProxy;
        q q2;
        AudioVolumeHandler audioVolumeHandler;
        GoogleARCoreApi googleARCoreApi = this.m_ARCoreApi;
        if (googleARCoreApi != null) {
            googleARCoreApi.pauseARCore();
        }
        if ((q2 = this.mVideoPlayerProxy) != null) {
            q2.a();
        }
        if ((googleVrProxy = GoogleVrApi.b()) != null) {
            googleVrProxy.pauseGvrLayout();
        }
        if ((audioVolumeHandler = this.m_AudioVolumeHandler) != null) {
            audioVolumeHandler.a();
            this.m_AudioVolumeHandler = null;
        }
        this.pauseUnity();
    }

    void postOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public void quit() {
        this.destroy();
    }

    @Override
    public void removeViewFromPlayer(View view) {
        this.swapViews((View)this.mGlView, view);
        ViewParent viewParent = view.getParent();
        boolean bl = true;
        boolean bl2 = viewParent == null;
        boolean bl3 = this.mGlView.getParent() == this;
        if (!bl2 || !bl3) {
            bl = false;
        }
        if (!bl) {
            if (!bl2) {
                g.Log(6, "removeViewFromPlayer: Failure removing view from hierarchy");
            }
            if (!bl3) {
                g.Log(6, "removeVireFromPlayer: Failure agging old view to hierarchy");
            }
        }
    }

    @Override
    public void reportError(String string2, String string3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string2);
        stringBuilder.append(": ");
        stringBuilder.append(string3);
        g.Log(6, stringBuilder.toString());
    }

    protected void reportSoftInputArea(final Rect rect) {
        this.queueGLThreadEvent(new f(){

            @Override
            public final void a() {
                UnityPlayer.this.nativeSetInputArea(rect.left, rect.top, rect.right, rect.bottom);
            }
        });
    }

    protected void reportSoftInputIsVisible(final boolean bl) {
        this.queueGLThreadEvent(new f(){

            @Override
            public final void a() {
                UnityPlayer.this.nativeSetKeyboardIsVisible(bl);
            }
        });
    }

    protected void reportSoftInputSelection(final int n2, final int n3) {
        this.queueGLThreadEvent(new f(){

            @Override
            public final void a() {
                UnityPlayer.this.nativeSetInputSelection(n2, n3);
            }
        });
    }

    protected void reportSoftInputStr(final String string2, final int n2, final boolean bl) {
        if (n2 == 1) {
            this.hideSoftInput();
        }
        this.queueGLThreadEvent(new f(){

            @Override
            public final void a() {
                if (bl) {
                    UnityPlayer.this.nativeSoftInputCanceled();
                } else {
                    String string22 = string2;
                    if (string22 != null) {
                        UnityPlayer.this.nativeSetInputString(string22);
                    }
                }
                if (n2 == 1) {
                    UnityPlayer.this.nativeSoftInputClosed();
                }
            }
        });
    }

    protected void requestUserAuthorization(String string2) {
        if (j.b && string2 != null && !string2.isEmpty() && currentActivity != null) {
            j.d.a(currentActivity, string2);
        }
    }

    public void resume() {
        GoogleVrProxy googleVrProxy;
        GoogleARCoreApi googleARCoreApi = this.m_ARCoreApi;
        if (googleARCoreApi != null) {
            googleARCoreApi.resumeARCore();
        }
        this.mState.b(false);
        q q2 = this.mVideoPlayerProxy;
        if (q2 != null) {
            q2.b();
        }
        this.checkResumePlayer();
        if (n.c()) {
            this.nativeRestartActivityIndicator();
        }
        if ((googleVrProxy = GoogleVrApi.b()) != null) {
            googleVrProxy.b();
        }
        this.m_AudioVolumeHandler = new AudioVolumeHandler(this.mContext);
    }

    void runOnAnonymousThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    void runOnUiThread(Runnable runnable) {
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity)context).runOnUiThread(runnable);
            return;
        }
        g.Log(5, "Not running Unity from an Activity; ignored...");
    }

    protected void setCharacterLimit(final int n2) {
        this.runOnUiThread(new Runnable(){

            public final void run() {
                if (UnityPlayer.this.mSoftInputDialog != null) {
                    UnityPlayer.this.mSoftInputDialog.a(n2);
                }
            }
        });
    }

    protected void setClipboardText(String string2) {
        ClipData clipData = ClipData.newPlainText((CharSequence)"Text", (CharSequence)string2);
        this.m_ClipboardManager.setPrimaryClip(clipData);
    }

    protected void setHideInputField(final boolean bl) {
        this.runOnUiThread(new Runnable(){

            public final void run() {
                if (UnityPlayer.this.mSoftInputDialog != null) {
                    UnityPlayer.this.mSoftInputDialog.a(bl);
                }
            }
        });
    }

    protected void setSelection(final int n2, final int n3) {
        this.runOnUiThread(new Runnable(){

            public final void run() {
                if (UnityPlayer.this.mSoftInputDialog != null) {
                    UnityPlayer.this.mSoftInputDialog.a(n2, n3);
                }
            }
        });
    }

    protected void setSoftInputStr(final String string2) {
        this.runOnUiThread(new Runnable(){

            public final void run() {
                if (UnityPlayer.this.mSoftInputDialog != null && string2 != null) {
                    UnityPlayer.this.mSoftInputDialog.a(string2);
                }
            }
        });
    }

    protected void showSoftInput(final String string2, final int n2, final boolean bl, final boolean bl2, final boolean bl3, final boolean bl4, final String string3, final int n3, final boolean bl5) {
        Runnable runnable = new Runnable(){

            public final void run() {
                k k2;
                UnityPlayer unityPlayer = UnityPlayer.this;
                unityPlayer.mSoftInputDialog = k2 = new k(unityPlayer.mContext, this, string2, n2, bl, bl2, bl3, string3, n3, bl5);
                UnityPlayer.this.mSoftInputDialog.setOnCancelListener(new DialogInterface.OnCancelListener(){

                    public final void onCancel(DialogInterface dialogInterface) {
                        UnityPlayer.this.nativeSoftInputLostFocus();
                        UnityPlayer.this.reportSoftInputStr(null, 1, false);
                    }
                });
                UnityPlayer.this.mSoftInputDialog.show();
                UnityPlayer.this.nativeReportKeyboardConfigChanged();
            }

        };
        this.postOnUiThread(runnable);
    }

    protected boolean showVideoPlayer(String string2, int n2, int n3, int n4, boolean bl, int n5, int n6) {
        boolean bl2;
        if (this.mVideoPlayerProxy == null) {
            this.mVideoPlayerProxy = new q(this);
        }
        if (bl2 = this.mVideoPlayerProxy.a(this.mContext, string2, n2, n3, n4, bl, n5, n6, new q.a(){

            @Override
            public final void a() {
                UnityPlayer.this.mVideoPlayerProxy = null;
            }
        })) {
            this.runOnUiThread(new Runnable(){

                public final void run() {
                    if (UnityPlayer.this.nativeIsAutorotationOn() && UnityPlayer.this.mContext instanceof Activity) {
                        ((Activity)UnityPlayer.this.mContext).setRequestedOrientation(UnityPlayer.this.mInitialScreenOrientation);
                    }
                }
            });
        }
        return bl2;
    }

    protected boolean skipPermissionsDialog() {
        if (j.b && currentActivity != null) {
            return j.d.a(currentActivity);
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean startOrientationListener(int n2) {
        String string2;
        if (this.mOrientationListener != null) {
            string2 = "Orientation Listener already started.";
        } else {
            this.mOrientationListener = new OrientationEventListener(this.mContext, n2){

                public final void onOrientationChanged(int n2) {
                    UnityPlayer.this.m_MainThread.a(UnityPlayer.this.mNaturalOrientation, n2);
                }
            };
            if (this.mOrientationListener.canDetectOrientation()) {
                this.mOrientationListener.enable();
                return true;
            }
            string2 = "Orientation Listener cannot detect orientation.";
        }
        g.Log(5, string2);
        return false;
    }

    public boolean stopOrientationListener() {
        OrientationEventListener orientationEventListener = this.mOrientationListener;
        if (orientationEventListener == null) {
            g.Log(5, "Orientation Listener was not started.");
            return false;
        }
        orientationEventListener.disable();
        this.mOrientationListener = null;
        return true;
    }

    protected void toggleGyroscopeSensor(boolean bl) {
        SensorManager sensorManager = (SensorManager)this.mContext.getSystemService("sensor");
        Sensor sensor = sensorManager.getDefaultSensor(11);
        if (bl) {
            sensorManager.registerListener((SensorEventListener)this.m_FakeListener, sensor, 1);
            return;
        }
        sensorManager.unregisterListener((SensorEventListener)this.m_FakeListener);
    }

    public void unload() {
        this.nativeApplicationUnload();
    }

    public void windowFocusChanged(boolean bl) {
        this.mState.a(bl);
        if (!this.mState.e()) {
            return;
        }
        if (bl) {
            this.m_MainThread.c();
        } else {
            this.m_MainThread.d();
        }
        this.checkResumePlayer();
    }

    final class a
    implements SensorEventListener {
        a() {
        }

        public final void onAccuracyChanged(Sensor sensor, int n2) {
        }

        public final void onSensorChanged(SensorEvent sensorEvent) {
        }
    }

    static final class b
    extends Enum {
        public static final /* enum */ int a = 1;
        public static final /* enum */ int b = 2;
        public static final /* enum */ int c = 3;
        private static final /* synthetic */ int[] d;

        static {
            int[] arrn = new int[]{a, b, c};
            d = arrn;
        }
    }

    private final class c
    extends PhoneStateListener {
        private c() {
        }

        /* synthetic */ c(byte by) {
            this();
        }

        public final void onCallStateChanged(int n2, String string2) {
            UnityPlayer unityPlayer = UnityPlayer.this;
            int n3 = 1;
            if (n2 != n3) {
                n3 = 0;
            }
            unityPlayer.nativeMuteMasterAudio((boolean)n3);
        }
    }

    static final class d
    extends Enum {
        public static final /* enum */ d a = new d("PAUSE", 0);
        public static final /* enum */ d b = new d("RESUME", 1);
        public static final /* enum */ d c = new d("QUIT", 2);
        public static final /* enum */ d d = new d("SURFACE_LOST", 3);
        public static final /* enum */ d e = new d("SURFACE_ACQUIRED", 4);
        public static final /* enum */ d f = new d("FOCUS_LOST", 5);
        public static final /* enum */ d g = new d("FOCUS_GAINED", 6);
        public static final /* enum */ d h = new d("NEXT_FRAME", 7);
        public static final /* enum */ d i = new d("URL_ACTIVATED", 8);
        public static final /* enum */ d j = new d("ORIENTATION_ANGLE_CHANGE", 9);
        private static final /* synthetic */ d[] k;

        static {
            d[] arrd = new d[]{a, b, c, d, e, f, g, h, i, j};
            k = arrd;
        }

        /*
         * Exception decompiling
         */
        private d() {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // java.lang.IllegalStateException
            // org.benf.cfr.reader.b.a.f.g.a(VariableFactory.java:53)
            // org.benf.cfr.reader.b.a.a.g.b(Op02WithProcessedDataAndRefs.java:911)
            // org.benf.cfr.reader.b.a.a.g.a(Op02WithProcessedDataAndRefs.java:959)
            // org.benf.cfr.reader.b.a.a.g.a(Op02WithProcessedDataAndRefs.java:56)
            // org.benf.cfr.reader.b.a.a.g$3.a(Op02WithProcessedDataAndRefs.java:2010)
            // org.benf.cfr.reader.b.a.a.g$3.a(Op02WithProcessedDataAndRefs.java:2007)
            // org.benf.cfr.reader.util.d.a.d(AbstractGraphVisitorFI.java:60)
            // org.benf.cfr.reader.b.a.a.g.a(Op02WithProcessedDataAndRefs.java:2019)
            // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:340)
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

    private final class e
    extends Thread {
        Handler a;
        boolean b = false;
        boolean c = false;
        int d = b.b;
        int e = 0;
        int f;
        int g;
        int h = 5;

        private e() {
        }

        /* synthetic */ e(byte by) {
            this();
        }

        private void a(d d2) {
            Handler handler = this.a;
            if (handler != null) {
                Message.obtain((Handler)handler, (int)2269, (Object)((Object)d2)).sendToTarget();
            }
        }

        public final void a() {
            this.a(d.c);
        }

        public final void a(int n2, int n3) {
            this.f = n2;
            this.g = n3;
            this.a(d.j);
        }

        public final void a(Runnable runnable) {
            if (this.a == null) {
                return;
            }
            this.a(d.a);
            Message.obtain((Handler)this.a, (Runnable)runnable).sendToTarget();
        }

        public final void b() {
            this.a(d.b);
        }

        public final void b(Runnable runnable) {
            if (this.a == null) {
                return;
            }
            this.a(d.d);
            Message.obtain((Handler)this.a, (Runnable)runnable).sendToTarget();
        }

        public final void c() {
            this.a(d.g);
        }

        public final void c(Runnable runnable) {
            Handler handler = this.a;
            if (handler == null) {
                return;
            }
            Message.obtain((Handler)handler, (Runnable)runnable).sendToTarget();
            this.a(d.e);
        }

        public final void d() {
            this.a(d.f);
        }

        public final void d(Runnable runnable) {
            Handler handler = this.a;
            if (handler != null) {
                Message.obtain((Handler)handler, (Runnable)runnable).sendToTarget();
            }
        }

        public final void e() {
            this.a(d.i);
        }

        public final void run() {
            this.setName("UnityMain");
            Looper.prepare();
            this.a = new Handler(new Handler.Callback(){

                private void a() {
                    if (e.this.d == b.c && e.this.c) {
                        UnityPlayer.this.nativeFocusChanged(true);
                        e.this.d = b.a;
                    }
                }

                /*
                 * Unable to fully structure code
                 * Enabled aggressive block sorting
                 * Lifted jumps to return sites
                 */
                public final boolean handleMessage(Message var1_1) {
                    block15 : {
                        block20 : {
                            block19 : {
                                block18 : {
                                    block17 : {
                                        block16 : {
                                            block14 : {
                                                if (var1_1.what != 2269) {
                                                    return false;
                                                }
                                                var2_2 = (d)var1_1.obj;
                                                if (var2_2 != d.h) break block14;
                                                var4_3 = e.this;
                                                --var4_3.e;
                                                e.this.UnityPlayer.this.executeGLThreadJobs();
                                                if (!e.this.b) {
                                                    return true;
                                                }
                                                if (!e.this.c) {
                                                    return true;
                                                }
                                                if (e.this.h >= 0) {
                                                    if (e.this.h == 0 && UnityPlayer.access$100(e.this.UnityPlayer.this)) {
                                                        UnityPlayer.access$200(e.this.UnityPlayer.this);
                                                    }
                                                    var5_4 = e.this;
                                                    --var5_4.h;
                                                }
                                                if (!e.this.UnityPlayer.this.isFinishing() && !UnityPlayer.access$300(e.this.UnityPlayer.this)) {
                                                    UnityPlayer.access$400(e.this.UnityPlayer.this);
                                                }
                                                break block15;
                                            }
                                            if (var2_2 != d.c) break block16;
                                            Looper.myLooper().quit();
                                            break block15;
                                        }
                                        if (var2_2 != d.b) break block17;
                                        e.this.b = true;
                                        break block15;
                                    }
                                    if (var2_2 != d.a) break block18;
                                    e.this.b = false;
                                    break block15;
                                }
                                if (var2_2 != d.d) break block19;
                                e.this.c = false;
                                break block15;
                            }
                            if (var2_2 != d.e) break block20;
                            e.this.c = true;
                            ** GOTO lbl48
                        }
                        if (var2_2 == d.f) {
                            if (e.this.d == b.a) {
                                UnityPlayer.access$000(e.this.UnityPlayer.this, false);
                            }
                            e.this.d = b.b;
                        } else if (var2_2 == d.g) {
                            e.this.d = b.c;
lbl48: // 2 sources:
                            this.a();
                        } else if (var2_2 == d.i) {
                            UnityPlayer.access$500(e.this.UnityPlayer.this, e.this.UnityPlayer.this.getLaunchURL());
                        } else if (var2_2 == d.j) {
                            UnityPlayer.access$600(e.this.UnityPlayer.this, e.this.f, e.this.g);
                        }
                    }
                    if (e.this.b == false) return true;
                    if (e.this.e > 0) return true;
                    Message.obtain((Handler)e.this.a, (int)2269, (Object)d.h).sendToTarget();
                    var3_5 = e.this;
                    var3_5.e = 1 + var3_5.e;
                    return true;
                }
            });
            Looper.loop();
        }

    }

    private abstract class f
    implements Runnable {
        private f() {
        }

        /* synthetic */ f(byte by) {
            this();
        }

        public abstract void a();

        public final void run() {
            if (!UnityPlayer.this.isFinishing()) {
                this.a();
            }
        }
    }

}

