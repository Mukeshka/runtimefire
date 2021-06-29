/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.LayerDrawable
 *  android.util.DisplayMetrics
 *  android.view.View
 *  java.lang.Enum
 *  java.lang.NoSuchFieldError
 *  java.lang.Object
 *  java.lang.String
 */
package com.unity3d.player;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.DisplayMetrics;
import android.view.View;

public final class l
extends View {
    final int a;
    final int b;
    Bitmap c;
    Bitmap d;

    public l(Context context, int n2) {
        super(context);
        this.a = n2;
        this.b = this.getResources().getIdentifier("unity_static_splash", "drawable", this.getContext().getPackageName());
        if (this.b != 0) {
            this.forceLayout();
        }
    }

    public final void onDetachedFromWindow() {
        Bitmap bitmap;
        super.onDetachedFromWindow();
        Bitmap bitmap2 = this.c;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.c = null;
        }
        if ((bitmap = this.d) != null) {
            bitmap.recycle();
            this.d = null;
        }
    }

    public final void onLayout(boolean bl, int n2, int n3, int n4, int n5) {
        block11 : {
            int n6;
            int n7;
            Bitmap bitmap;
            block13 : {
                float f2;
                int n8;
                float f3;
                block14 : {
                    float f4;
                    int n9;
                    block12 : {
                        if (this.b == 0) {
                            return;
                        }
                        if (this.c == null) {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inScaled = false;
                            this.c = BitmapFactory.decodeResource((Resources)this.getResources(), (int)this.b, (BitmapFactory.Options)options);
                        }
                        n6 = this.c.getWidth();
                        n7 = this.c.getHeight();
                        n9 = this.getWidth();
                        n8 = this.getHeight();
                        if (n9 == 0) break block11;
                        if (n8 == 0) {
                            return;
                        }
                        f4 = n9;
                        f3 = n8;
                        f2 = (float)n6 / (float)n7;
                        boolean bl2 = f4 / f3 <= f2;
                        int n10 = 1.a[this.a - 1];
                        if (n10 == 1) break block12;
                        if (n10 != 2 && n10 != 3) break block13;
                        boolean bl3 = this.a == a.c;
                        if (!(bl3 ^ bl2)) break block14;
                        n7 = (int)(f4 / f2);
                        n6 = n9;
                        break block13;
                    }
                    if (n9 < n6) {
                        n7 = (int)(f4 / f2);
                        n6 = n9;
                    }
                    if (n8 >= n7) break block13;
                }
                n6 = (int)(f3 * f2);
                n7 = n8;
            }
            if ((bitmap = this.d) != null) {
                if (bitmap.getWidth() == n6 && this.d.getHeight() == n7) {
                    return;
                }
                Bitmap bitmap2 = this.d;
                if (bitmap2 != this.c) {
                    bitmap2.recycle();
                    this.d = null;
                }
            }
            this.d = Bitmap.createScaledBitmap((Bitmap)this.c, (int)n6, (int)n7, (boolean)true);
            this.d.setDensity(this.getResources().getDisplayMetrics().densityDpi);
            ColorDrawable colorDrawable = new ColorDrawable(-16777216);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.getResources(), this.d);
            bitmapDrawable.setGravity(17);
            this.setBackground((Drawable)new LayerDrawable(new Drawable[]{colorDrawable, bitmapDrawable}));
        }
    }

    static final class a
    extends Enum {
        public static final /* enum */ int a = 1;
        public static final /* enum */ int b = 2;
        public static final /* enum */ int c = 3;
        private static final /* synthetic */ int[] d;

        static {
            int[] arrn = new int[]{a, b, c};
            d = arrn;
        }

        public static int[] a() {
            return (int[])d.clone();
        }
    }

}

