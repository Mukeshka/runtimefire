/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.unity3d.player;

final class n {
    private static boolean a;
    private boolean b = false;
    private boolean c = false;
    private boolean d = true;
    private boolean e = false;

    n() {
    }

    static void a() {
        a = true;
    }

    static void b() {
        a = false;
    }

    static boolean c() {
        return a;
    }

    final void a(boolean bl) {
        this.b = bl;
    }

    final void b(boolean bl) {
        this.d = bl;
    }

    final void c(boolean bl) {
        this.e = bl;
    }

    final void d(boolean bl) {
        this.c = bl;
    }

    final boolean d() {
        return this.d;
    }

    final boolean e() {
        return this.e;
    }

    final boolean f() {
        return a && this.b && !this.d && !this.c;
    }

    final boolean g() {
        return this.c;
    }

    public final String toString() {
        return super.toString();
    }
}

