/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 */
package com.unity3d.player;

import android.os.Build;
import com.unity3d.player.e;
import com.unity3d.player.h;

public final class j {
    static final boolean a;
    static final boolean b;
    static final boolean c;
    static final e d;

    static {
        int n2 = Build.VERSION.SDK_INT;
        boolean bl = true;
        boolean bl2 = n2 >= 21;
        a = bl2;
        boolean bl3 = Build.VERSION.SDK_INT >= 23;
        b = bl3;
        if (Build.VERSION.SDK_INT < 24) {
            bl = false;
        }
        c = bl;
        h h2 = b ? new h() : null;
        d = h2;
    }
}

