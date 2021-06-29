/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Rect
 *  android.graphics.SurfaceTexture
 *  android.graphics.SurfaceTexture$OnFrameAvailableListener
 *  android.hardware.camera2.CameraAccessException
 *  android.hardware.camera2.CameraCaptureSession
 *  android.hardware.camera2.CameraCaptureSession$CaptureCallback
 *  android.hardware.camera2.CameraCaptureSession$StateCallback
 *  android.hardware.camera2.CameraCharacteristics
 *  android.hardware.camera2.CameraCharacteristics$Key
 *  android.hardware.camera2.CameraDevice
 *  android.hardware.camera2.CameraDevice$StateCallback
 *  android.hardware.camera2.CameraManager
 *  android.hardware.camera2.CaptureFailure
 *  android.hardware.camera2.CaptureRequest
 *  android.hardware.camera2.CaptureRequest$Builder
 *  android.hardware.camera2.CaptureRequest$Key
 *  android.hardware.camera2.TotalCaptureResult
 *  android.hardware.camera2.params.MeteringRectangle
 *  android.hardware.camera2.params.StreamConfigurationMap
 *  android.media.Image
 *  android.media.Image$Plane
 *  android.media.ImageReader
 *  android.media.ImageReader$OnImageAvailableListener
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.Looper
 *  android.util.Range
 *  android.util.Size
 *  android.view.Surface
 *  java.lang.Comparable
 *  java.lang.Double
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.InterruptedException
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.nio.ByteBuffer
 *  java.util.Arrays
 *  java.util.List
 *  java.util.concurrent.Semaphore
 *  java.util.concurrent.TimeUnit
 */
package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import com.unity3d.player.d;
import com.unity3d.player.g;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class b {
    private static CameraManager b;
    private static String[] c;
    private static Semaphore e;
    private CameraCaptureSession.CaptureCallback A = new CameraCaptureSession.CaptureCallback(){

        public final void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            b.this.a(captureRequest.getTag());
        }

        public final void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: Capture session failed ");
            stringBuilder.append(captureRequest.getTag());
            stringBuilder.append(" reason ");
            stringBuilder.append(captureFailure.getReason());
            g.Log(5, stringBuilder.toString());
            b.this.a(captureRequest.getTag());
        }

        public final void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int n2) {
        }

        public final void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int n2, long l2) {
        }
    };
    private final CameraDevice.StateCallback B = new CameraDevice.StateCallback(){

        public final void onClosed(CameraDevice cameraDevice) {
            e.release();
        }

        public final void onDisconnected(CameraDevice cameraDevice) {
            g.Log(5, "Camera2: CameraDevice disconnected.");
            b.this.a(cameraDevice);
            e.release();
        }

        public final void onError(CameraDevice cameraDevice, int n2) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: Error opeining CameraDevice ");
            stringBuilder.append(n2);
            g.Log(6, stringBuilder.toString());
            b.this.a(cameraDevice);
            e.release();
        }

        public final void onOpened(CameraDevice cameraDevice) {
            b.this.d = cameraDevice;
            e.release();
        }
    };
    private final ImageReader.OnImageAvailableListener C = new ImageReader.OnImageAvailableListener(){

        public final void onImageAvailable(ImageReader imageReader) {
            if (e.tryAcquire()) {
                Image image = imageReader.acquireNextImage();
                if (image != null) {
                    Image.Plane[] arrplane = image.getPlanes();
                    if (image.getFormat() == 35 && arrplane != null && arrplane.length == 3) {
                        b.this.a.a((Object)arrplane[0].getBuffer(), (Object)arrplane[1].getBuffer(), (Object)arrplane[2].getBuffer(), arrplane[0].getRowStride(), arrplane[1].getRowStride(), arrplane[1].getPixelStride());
                    } else {
                        g.Log(6, "Camera2: Wrong image format.");
                    }
                    if (b.this.s != null) {
                        b.this.s.close();
                    }
                    b.this.s = image;
                }
                e.release();
            }
        }
    };
    private final SurfaceTexture.OnFrameAvailableListener D = new SurfaceTexture.OnFrameAvailableListener(){

        public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
            b.this.a.a((Object)surfaceTexture);
        }
    };
    private d a = null;
    private CameraDevice d;
    private HandlerThread f;
    private Handler g;
    private Rect h;
    private Rect i;
    private int j;
    private int k;
    private float l = -1.0f;
    private float m = -1.0f;
    private int n;
    private int o;
    private boolean p = false;
    private Range q;
    private ImageReader r = null;
    private Image s;
    private CaptureRequest.Builder t;
    private CameraCaptureSession u = null;
    private Object v = new Object();
    private int w;
    private SurfaceTexture x;
    private Surface y = null;
    private int z = a.c;

    static {
        e = new Semaphore(1);
    }

    protected b(d d2) {
        this.a = d2;
        this.g();
    }

    public static int a(Context context) {
        return b.c(context).length;
    }

    public static int a(Context context, int n2) {
        try {
            int n3 = (Integer)b.b(context).getCameraCharacteristics(b.c(context)[n2]).get(CameraCharacteristics.SENSOR_ORIENTATION);
            return n3;
        }
        catch (CameraAccessException cameraAccessException) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append((Object)cameraAccessException);
            g.Log(6, stringBuilder.toString());
            return 0;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static int a(Range[] arrrange, int n2) {
        Comparable comparable;
        int n3 = -1;
        double d2 = Double.MAX_VALUE;
        for (int i2 = 0; i2 < arrrange.length; ++i2) {
            int n4 = (Integer)arrrange[i2].getLower();
            int n5 = (Integer)arrrange[i2].getUpper();
            float f2 = n2;
            if (f2 + 0.1f > (float)n4 && f2 - 0.1f < (float)n5) {
                return n2;
            }
            double d3 = Math.min((int)Math.abs((int)(n2 - n4)), (int)Math.abs((int)(n2 - n5)));
            if (!(d3 < d2)) continue;
            n3 = i2;
            d2 = d3;
        }
        if (n2 > (Integer)arrrange[n3].getUpper()) {
            comparable = arrrange[n3].getUpper();
            do {
                return (Integer)comparable;
                break;
            } while (true);
        }
        comparable = arrrange[n3].getLower();
        return (Integer)comparable;
    }

    private static Rect a(Size[] arrsize, double d2, double d3) {
        double d4 = Double.MAX_VALUE;
        int n2 = 0;
        int n3 = 0;
        for (int i2 = 0; i2 < arrsize.length; ++i2) {
            int n4 = arrsize[i2].getWidth();
            int n5 = arrsize[i2].getHeight();
            double d5 = n4;
            Double.isNaN((double)d5);
            double d6 = Math.abs((double)Math.log((double)(d2 / d5)));
            double d7 = n5;
            Double.isNaN((double)d7);
            double d8 = d6 + Math.abs((double)Math.log((double)(d3 / d7)));
            if (!(d8 < d4)) continue;
            n2 = n4;
            n3 = n5;
            d4 = d8;
        }
        return new Rect(0, 0, n2, n3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(CameraDevice cameraDevice) {
        Object object;
        Object object2 = object = this.v;
        synchronized (object2) {
            this.u = null;
        }
        cameraDevice.close();
        this.d = null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(Object object) {
        Object object2;
        if (object == "Focus") {
            Object object3;
            this.p = false;
            Object object4 = object3 = this.v;
            synchronized (object4) {
                CameraCaptureSession cameraCaptureSession = this.u;
                if (cameraCaptureSession != null) {
                    try {
                        this.t.set(CaptureRequest.CONTROL_AF_TRIGGER, (Object)0);
                        this.t.setTag((Object)"Regular");
                        this.u.setRepeatingRequest(this.t.build(), this.A, this.g);
                    }
                    catch (CameraAccessException cameraAccessException) {
                        StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                        stringBuilder.append((Object)cameraAccessException);
                        g.Log(6, stringBuilder.toString());
                    }
                }
                return;
            }
        }
        if (object != "Cancel focus") {
            return;
        }
        Object object5 = object2 = this.v;
        synchronized (object5) {
            if (this.u != null) {
                this.j();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private static Size[] a(CameraCharacteristics cameraCharacteristics) {
        String string2;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap)cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null) {
            string2 = "Camera2: configuration map is not available.";
        } else {
            Size[] arrsize = streamConfigurationMap.getOutputSizes(35);
            if (arrsize != null && arrsize.length != 0) {
                return arrsize;
            }
            string2 = "Camera2: output sizes for YUV_420_888 format are not avialable.";
        }
        g.Log(6, string2);
        return null;
    }

    private static CameraManager b(Context context) {
        if (b == null) {
            b = (CameraManager)context.getSystemService("camera");
        }
        return b;
    }

    private void b(CameraCharacteristics cameraCharacteristics) {
        this.k = (Integer)cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
        if (this.k > 0) {
            this.i = (Rect)cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
            float f2 = (float)this.i.width() / (float)this.i.height();
            float f3 = (float)this.h.width() / (float)this.h.height();
            if (f3 > f2) {
                this.n = 0;
                this.o = (int)(((float)this.i.height() - (float)this.i.width() / f3) / 2.0f);
            } else {
                this.o = 0;
                this.n = (int)(((float)this.i.width() - f3 * (float)this.i.height()) / 2.0f);
            }
            this.j = Math.min((int)this.i.width(), (int)this.i.height()) / 20;
        }
    }

    public static boolean b(Context context, int n2) {
        try {
            int n3 = (Integer)b.b(context).getCameraCharacteristics(b.c(context)[n2]).get(CameraCharacteristics.LENS_FACING);
            return n3 == 0;
        }
        catch (CameraAccessException cameraAccessException) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append((Object)cameraAccessException);
            g.Log(6, stringBuilder.toString());
            return false;
        }
    }

    public static boolean c(Context context, int n2) {
        try {
            int n3 = (Integer)b.b(context).getCameraCharacteristics(b.c(context)[n2]).get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
            return n3 > 0;
        }
        catch (CameraAccessException cameraAccessException) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append((Object)cameraAccessException);
            g.Log(6, stringBuilder.toString());
            return false;
        }
    }

    private static String[] c(Context context) {
        if (c == null) {
            try {
                c = b.b(context).getCameraIdList();
            }
            catch (CameraAccessException cameraAccessException) {
                StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                stringBuilder.append((Object)cameraAccessException);
                g.Log(6, stringBuilder.toString());
                c = new String[0];
            }
        }
        return c;
    }

    public static int[] d(Context context, int n2) {
        CameraCharacteristics cameraCharacteristics;
        try {
            cameraCharacteristics = b.b(context).getCameraCharacteristics(b.c(context)[n2]);
        }
        catch (CameraAccessException cameraAccessException) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append((Object)cameraAccessException);
            g.Log(6, stringBuilder.toString());
            return null;
        }
        Size[] arrsize = b.a(cameraCharacteristics);
        if (arrsize != null) {
            int[] arrn = new int[2 * arrsize.length];
            for (int i2 = 0; i2 < arrsize.length; ++i2) {
                int n3 = i2 * 2;
                arrn[n3] = arrsize[i2].getWidth();
                arrn[n3 + 1] = arrsize[i2].getHeight();
            }
            return arrn;
        }
        return null;
    }

    private void g() {
        this.f = new HandlerThread("CameraBackground");
        this.f.start();
        this.g = new Handler(this.f.getLooper());
    }

    private void h() {
        this.f.quit();
        try {
            this.f.join(4000L);
            this.f = null;
            this.g = null;
            return;
        }
        catch (InterruptedException interruptedException) {
            this.f.interrupt();
            StringBuilder stringBuilder = new StringBuilder("Camera2: Interrupted while waiting for the background thread to finish ");
            stringBuilder.append((Object)interruptedException);
            g.Log(6, stringBuilder.toString());
            return;
        }
    }

    private void i() {
        block5 : {
            try {
                if (e.tryAcquire(4L, TimeUnit.SECONDS)) break block5;
                g.Log(5, "Camera2: Timeout waiting to lock camera for closing.");
                return;
            }
            catch (InterruptedException interruptedException) {
                StringBuilder stringBuilder = new StringBuilder("Camera2: Interrupted while trying to lock camera for closing ");
                stringBuilder.append((Object)interruptedException);
                g.Log(6, stringBuilder.toString());
                return;
            }
        }
        this.d.close();
        try {
            if (!e.tryAcquire(4L, TimeUnit.SECONDS)) {
                g.Log(5, "Camera2: Timeout waiting to close camera.");
            }
        }
        catch (InterruptedException interruptedException) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: Interrupted while waiting to close camera ");
            stringBuilder.append((Object)interruptedException);
            g.Log(6, stringBuilder.toString());
        }
        this.d = null;
        e.release();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void j() {
        try {
            if (this.k == 0 || this.l < 0.0f || this.l > 1.0f || this.m < 0.0f || this.m > 1.0f) ** GOTO lbl35
            this.p = true;
            var5_1 = (int)((float)(this.i.width() - 2 * this.n) * this.l + (float)this.n);
            var6_2 = this.i.height() - 2 * this.o;
            var8_3 = this.m;
        }
        catch (CameraAccessException var1_16) {
            var2_17 = new StringBuilder("Camera2: CameraAccessException ");
            var2_17.append((Object)var1_16);
            g.Log(6, var2_17.toString());
            return;
        }
        var9_4 = var8_3;
        Double.isNaN((double)var9_4);
        var12_5 = 1.0 - var9_4;
        Double.isNaN((double)var6_2);
        var15_6 = var6_2 * var12_5;
        var17_7 = this.o;
        var18_8 = var17_7;
        Double.isNaN((double)var18_8);
        var21_9 = (int)(var15_6 + var18_8);
        var22_10 = Math.max((int)(1 + this.j), (int)Math.min((int)var5_1, (int)(this.i.width() - this.j - 1)));
        var23_11 = Math.max((int)(1 + this.j), (int)Math.min((int)var21_9, (int)(this.i.height() - this.j - 1)));
        var24_12 = this.t;
        var25_13 = CaptureRequest.CONTROL_AF_REGIONS;
        var27_15 = new MeteringRectangle(var22_10 - this.j, var23_11 - this.j, 2 * this.j, 2 * this.j, 999);
        var26_14 = new MeteringRectangle[]{var27_15};
        var24_12.set(var25_13, (Object)var26_14);
        this.t.set(CaptureRequest.CONTROL_AF_MODE, (Object)1);
        this.t.set(CaptureRequest.CONTROL_AF_TRIGGER, (Object)1);
        this.t.setTag((Object)"Focus");
        this.u.capture(this.t.build(), this.A, this.g);
        return;
lbl35: // 1 sources:
        this.t.set(CaptureRequest.CONTROL_AF_MODE, (Object)4);
        this.t.setTag((Object)"Regular");
        if (this.u == null) return;
        this.u.setRepeatingRequest(this.t.build(), this.A, this.g);
    }

    private void k() {
        try {
            if (this.u != null) {
                this.u.stopRepeating();
                this.t.set(CaptureRequest.CONTROL_AF_TRIGGER, (Object)2);
                this.t.set(CaptureRequest.CONTROL_AF_MODE, (Object)0);
                this.t.setTag((Object)"Cancel focus");
                this.u.capture(this.t.build(), this.A, this.g);
            }
            return;
        }
        catch (CameraAccessException cameraAccessException) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append((Object)cameraAccessException);
            g.Log(6, stringBuilder.toString());
            return;
        }
    }

    public final Rect a() {
        return this.h;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final boolean a(float f2, float f3) {
        if (this.k > 0) {
            if (!this.p) {
                Object object;
                this.l = f2;
                this.m = f3;
                Object object2 = object = this.v;
                synchronized (object2) {
                    if (this.u != null && this.z != a.b) {
                        this.k();
                    }
                    return true;
                }
            }
            g.Log(5, "Camera2: Setting manual focus point already started.");
        }
        return false;
    }

    public final boolean a(Context context, int n2, int n3, int n4, int n5, int n6) {
        CameraCharacteristics cameraCharacteristics;
        try {
            cameraCharacteristics = b.getCameraCharacteristics(b.c(context)[n2]);
        }
        catch (CameraAccessException cameraAccessException) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append((Object)cameraAccessException);
            g.Log(6, stringBuilder.toString());
            return false;
        }
        if ((Integer)cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL) == 2) {
            g.Log(5, "Camera2: only LEGACY hardware level is supported.");
            return false;
        }
        Size[] arrsize = b.a(cameraCharacteristics);
        if (arrsize != null) {
            if (arrsize.length == 0) {
                return false;
            }
            this.h = b.a(arrsize, n3, n4);
            Range[] arrrange = (Range[])cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
            if (arrrange != null && arrrange.length != 0) {
                block14 : {
                    block13 : {
                        int n7 = b.a(arrrange, n5);
                        this.q = new Range((Comparable)Integer.valueOf((int)n7), (Comparable)Integer.valueOf((int)n7));
                        try {
                            if (e.tryAcquire(4L, TimeUnit.SECONDS)) break block13;
                            g.Log(5, "Camera2: Timeout waiting to lock camera for opening.");
                            return false;
                        }
                        catch (InterruptedException interruptedException) {
                            StringBuilder stringBuilder = new StringBuilder("Camera2: Interrupted while trying to lock camera for opening ");
                            stringBuilder.append((Object)interruptedException);
                            g.Log(6, stringBuilder.toString());
                            return false;
                        }
                    }
                    try {
                        b.openCamera(b.c(context)[n2], this.B, this.g);
                    }
                    catch (CameraAccessException cameraAccessException) {
                        StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                        stringBuilder.append((Object)cameraAccessException);
                        g.Log(6, stringBuilder.toString());
                        e.release();
                        return false;
                    }
                    if (e.tryAcquire(4L, TimeUnit.SECONDS)) break block14;
                    g.Log(5, "Camera2: Timeout waiting to open camera.");
                    return false;
                }
                try {
                    e.release();
                }
                catch (InterruptedException interruptedException) {
                    StringBuilder stringBuilder = new StringBuilder("Camera2: Interrupted while waiting to open camera ");
                    stringBuilder.append((Object)interruptedException);
                    g.Log(6, stringBuilder.toString());
                }
                this.w = n6;
                this.b(cameraCharacteristics);
                return this.d != null;
            }
            g.Log(6, "Camera2: target FPS ranges are not avialable.");
        }
        return false;
    }

    public final void b() {
        if (this.d != null) {
            ImageReader imageReader;
            this.e();
            this.i();
            this.A = null;
            this.y = null;
            this.x = null;
            Image image = this.s;
            if (image != null) {
                image.close();
                this.s = null;
            }
            if ((imageReader = this.r) != null) {
                imageReader.close();
                this.r = null;
            }
        }
        this.h();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void c() {
        if (this.r == null) {
            this.r = ImageReader.newInstance((int)this.h.width(), (int)this.h.height(), (int)35, (int)2);
            this.r.setOnImageAvailableListener(this.C, this.g);
            this.s = null;
            int n2 = this.w;
            if (n2 != 0) {
                this.x = new SurfaceTexture(n2);
                this.x.setDefaultBufferSize(this.h.width(), this.h.height());
                this.x.setOnFrameAvailableListener(this.D, this.g);
                this.y = new Surface(this.x);
            }
        }
        try {
            if (this.u != null) {
                if (this.z == a.b) {
                    this.u.setRepeatingRequest(this.t.build(), this.A, this.g);
                }
            } else {
                CameraDevice cameraDevice = this.d;
                Object[] arrobject = this.y != null ? new Surface[]{this.y, this.r.getSurface()} : new Surface[]{this.r.getSurface()};
                List list = Arrays.asList((Object[])arrobject);
                cameraDevice.createCaptureSession(list, new CameraCaptureSession.StateCallback(){

                    public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                        g.Log(6, "Camera2: CaptureSession configuration failed.");
                    }

                    /*
                     * Enabled aggressive block sorting
                     * Enabled unnecessary exception pruning
                     * Enabled aggressive exception aggregation
                     */
                    public final void onConfigured(CameraCaptureSession cameraCaptureSession) {
                        Object object;
                        if (b.this.d == null) {
                            return;
                        }
                        Object object2 = object = b.this.v;
                        synchronized (object2) {
                            b.this.u = cameraCaptureSession;
                            try {
                                b.this.t = b.this.d.createCaptureRequest(1);
                                if (b.this.y != null) {
                                    b.this.t.addTarget(b.this.y);
                                }
                                b.this.t.addTarget(b.this.r.getSurface());
                                b.this.t.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, (Object)b.this.q);
                                b.this.j();
                            }
                            catch (CameraAccessException cameraAccessException) {
                                StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                                stringBuilder.append((Object)cameraAccessException);
                                g.Log(6, stringBuilder.toString());
                            }
                            return;
                        }
                    }
                }, this.g);
            }
            this.z = a.a;
            return;
        }
        catch (CameraAccessException cameraAccessException) {
            StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
            stringBuilder.append((Object)cameraAccessException);
            g.Log(6, stringBuilder.toString());
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void d() {
        Object object;
        Object object2 = object = this.v;
        synchronized (object2) {
            CameraCaptureSession cameraCaptureSession = this.u;
            if (cameraCaptureSession != null) {
                try {
                    this.u.stopRepeating();
                    this.z = a.b;
                }
                catch (CameraAccessException cameraAccessException) {
                    StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                    stringBuilder.append((Object)cameraAccessException);
                    g.Log(6, stringBuilder.toString());
                }
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void e() {
        Object object;
        Object object2 = object = this.v;
        synchronized (object2) {
            CameraCaptureSession cameraCaptureSession = this.u;
            if (cameraCaptureSession != null) {
                try {
                    this.u.abortCaptures();
                }
                catch (CameraAccessException cameraAccessException) {
                    StringBuilder stringBuilder = new StringBuilder("Camera2: CameraAccessException ");
                    stringBuilder.append((Object)cameraAccessException);
                    g.Log(6, stringBuilder.toString());
                }
                this.u.close();
                this.u = null;
                this.z = a.c;
            }
            return;
        }
    }

    private static final class a
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

}

