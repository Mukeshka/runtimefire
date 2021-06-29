/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.reflect.Method
 *  java.util.HashMap
 */
package com.unity3d.player;

import com.unity3d.player.g;
import java.lang.reflect.Method;
import java.util.HashMap;

final class o {
    private HashMap a = new HashMap();
    private Class b = null;
    private Object c = null;

    public o(Class class_, Object object) {
        this.b = class_;
        this.c = object;
    }

    private void a(String string2, a a2) {
        try {
            a2.b = this.b.getMethod(string2, a2.a);
            return;
        }
        catch (Exception exception) {
            StringBuilder stringBuilder = new StringBuilder("Exception while trying to get method ");
            stringBuilder.append(string2);
            stringBuilder.append(". ");
            stringBuilder.append(exception.getLocalizedMessage());
            g.Log(6, stringBuilder.toString());
            a2.b = null;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final /* varargs */ Object a(String string2, Object ... arrobject) {
        a a2;
        block8 : {
            StringBuilder stringBuilder;
            block7 : {
                block6 : {
                    if (this.a.containsKey((Object)string2)) break block6;
                    stringBuilder = new StringBuilder("No definition for method ");
                    stringBuilder.append(string2);
                    string2 = " can be found";
                    break block7;
                }
                a2 = (a)this.a.get((Object)string2);
                if (a2.b == null) {
                    this.a(string2, a2);
                }
                if (a2.b != null) break block8;
                stringBuilder = new StringBuilder("Unable to create method: ");
            }
            stringBuilder.append(string2);
            g.Log(6, stringBuilder.toString());
            return null;
        }
        try {
            if (arrobject.length != 0) return a2.b.invoke(this.c, arrobject);
            return a2.b.invoke(this.c, new Object[0]);
        }
        catch (Exception exception) {
            StringBuilder stringBuilder = new StringBuilder("Error trying to call delegated method ");
            stringBuilder.append(string2);
            stringBuilder.append(". ");
            stringBuilder.append(exception.getLocalizedMessage());
            g.Log(6, stringBuilder.toString());
            return null;
        }
    }

    public final void a(String string2, Class[] arrclass) {
        this.a.put((Object)string2, (Object)new a(arrclass));
    }

    final class a {
        public Class[] a;
        public Method b;

        public a(Class[] arrclass) {
            this.a = arrclass;
            this.b = null;
        }
    }

}

