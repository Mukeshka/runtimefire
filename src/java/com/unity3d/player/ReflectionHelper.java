/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Integer
 *  java.lang.NoClassDefFoundError
 *  java.lang.NoSuchFieldError
 *  java.lang.NoSuchMethodError
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.Void
 *  java.lang.invoke.MethodHandle
 *  java.lang.invoke.MethodHandles
 *  java.lang.invoke.MethodHandles$Lookup
 *  java.lang.reflect.Constructor
 *  java.lang.reflect.Field
 *  java.lang.reflect.InvocationHandler
 *  java.lang.reflect.Member
 *  java.lang.reflect.Method
 *  java.lang.reflect.Modifier
 *  java.lang.reflect.Proxy
 *  java.util.ArrayList
 */
package com.unity3d.player;

import com.unity3d.player.g;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

final class ReflectionHelper {
    protected static boolean LOG;
    protected static final boolean LOGV;
    private static a[] a;
    private static long b;

    static {
        a = new a[4096];
        b = 0L;
    }

    ReflectionHelper() {
    }

    /*
     * Exception decompiling
     */
    private static float a(Class var0, Class var1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Invalid stack depths @ lbl29.1 : FCONST_0 : trying to set 1 previously set to 0
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

    private static float a(Class class_, Class[] arrclass, Class[] arrclass2) {
        if (arrclass2.length == 0) {
            return 0.1f;
        }
        int n2 = arrclass == null ? 0 : arrclass.length;
        if (n2 + 1 != arrclass2.length) {
            return 0.0f;
        }
        float f2 = 1.0f;
        if (arrclass != null) {
            int n3 = arrclass.length;
            int n4 = 0;
            for (int i2 = 0; i2 < n3; ++i2) {
                Class class_2 = arrclass[i2];
                int n5 = n4 + 1;
                f2 *= ReflectionHelper.a(class_2, arrclass2[n4]);
                n4 = n5;
            }
        }
        return f2 * ReflectionHelper.a(class_, arrclass2[-1 + arrclass2.length]);
    }

    /*
     * Exception decompiling
     */
    private static Class a(String var0, int[] var1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Underrun type stack
        // org.benf.cfr.reader.b.a.c.e.a(StackSim.java:35)
        // org.benf.cfr.reader.b.b.af.a(OperationFactoryPop.java:20)
        // org.benf.cfr.reader.b.b.e.a(JVMInstr.java:315)
        // org.benf.cfr.reader.b.a.a.g.a(Op02WithProcessedDataAndRefs.java:195)
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

    private static void a(a a2, Member member) {
        Class<ReflectionHelper> class_ = ReflectionHelper.class;
        synchronized (ReflectionHelper.class) {
            a2.a = member;
            ReflectionHelper.a[a2.hashCode() & -1 + ReflectionHelper.a.length] = a2;
            // ** MonitorExit[var3_2] (shouldn't be in output)
            return;
        }
    }

    private static boolean a(a a2) {
        Class<ReflectionHelper> class_ = ReflectionHelper.class;
        synchronized (ReflectionHelper.class) {
            a a3;
            block4 : {
                a3 = a[a2.hashCode() & a.length - 1];
                boolean bl = a2.equals(a3);
                if (bl) break block4;
                return false;
            }
            a2.a = a3.a;
            // ** MonitorExit[var4_1] (shouldn't be in output)
            return true;
        }
    }

    private static Class[] a(String string2) {
        Class class_;
        int[] arrn = new int[1];
        int n2 = 0;
        arrn[0] = 0;
        ArrayList arrayList = new ArrayList();
        while (arrn[0] < string2.length() && (class_ = ReflectionHelper.a(string2, arrn)) != null) {
            arrayList.add((Object)class_);
        }
        Class[] arrclass = new Class[arrayList.size()];
        for (Class class_2 : arrayList) {
            int n3 = n2 + 1;
            arrclass[n2] = class_2;
            n2 = n3;
        }
        return arrclass;
    }

    protected static void endUnityLaunch() {
        b = 1L + b;
    }

    protected static Constructor getConstructorID(Class class_, String string2) {
        Constructor constructor;
        a a2 = new a(class_, "", string2);
        if (ReflectionHelper.a(a2)) {
            constructor = (Constructor)a2.a;
        } else {
            Class[] arrclass = ReflectionHelper.a(string2);
            float f2 = 0.0f;
            Constructor[] arrconstructor = class_.getConstructors();
            int n2 = arrconstructor.length;
            Constructor constructor2 = null;
            for (int i2 = 0; i2 < n2; ++i2) {
                Constructor constructor3 = arrconstructor[i2];
                float f3 = ReflectionHelper.a(Void.TYPE, constructor3.getParameterTypes(), arrclass);
                if (!(f3 > f2)) continue;
                if (f3 != 1.0f) {
                    constructor2 = constructor3;
                    f2 = f3;
                    continue;
                }
                constructor2 = constructor3;
                break;
            }
            ReflectionHelper.a(a2, constructor2);
            constructor = constructor2;
        }
        if (constructor != null) {
            return constructor;
        }
        StringBuilder stringBuilder = new StringBuilder("<init>");
        stringBuilder.append(string2);
        stringBuilder.append(" in class ");
        stringBuilder.append(class_.getName());
        throw new NoSuchMethodError(stringBuilder.toString());
    }

    protected static Field getFieldID(Class class_, String string2, String string3, boolean bl) {
        Field field;
        Class class_2 = class_;
        a a2 = new a(class_2, string2, string3);
        if (ReflectionHelper.a(a2)) {
            field = (Field)a2.a;
        } else {
            Class[] arrclass = ReflectionHelper.a(string3);
            float f2 = 0.0f;
            Field field2 = null;
            while (class_2 != null) {
                block6 : {
                    Field[] arrfield = class_2.getDeclaredFields();
                    int n2 = arrfield.length;
                    Field field3 = field2;
                    float f3 = f2;
                    for (int i2 = 0; i2 < n2; ++i2) {
                        float f4;
                        Field field4 = arrfield[i2];
                        if (bl != Modifier.isStatic((int)field4.getModifiers()) || field4.getName().compareTo(string2) != 0 || !((f4 = ReflectionHelper.a(field4.getType(), null, arrclass)) > f3)) continue;
                        if (f4 != 1.0f) {
                            field3 = field4;
                            f3 = f4;
                            continue;
                        }
                        field2 = field4;
                        f2 = f4;
                        break block6;
                    }
                    f2 = f3;
                    field2 = field3;
                }
                if (f2 == 1.0f || class_2.isPrimitive() || class_2.isInterface() || class_2.equals(Object.class) || class_2.equals((Object)Void.TYPE)) break;
                class_2 = class_2.getSuperclass();
            }
            ReflectionHelper.a(a2, field2);
            field = field2;
        }
        if (field == null) {
            Object[] arrobject = new Object[4];
            String string4 = bl ? "static" : "non-static";
            arrobject[0] = string4;
            arrobject[1] = string2;
            arrobject[2] = string3;
            arrobject[3] = class_2.getName();
            throw new NoSuchFieldError(String.format((String)"no %s field with name='%s' signature='%s' in class L%s;", (Object[])arrobject));
        }
        return field;
    }

    protected static String getFieldSignature(Field field) {
        Class class_ = field.getType();
        if (class_.isPrimitive()) {
            String string2 = class_.getName();
            if ("boolean".equals((Object)string2)) {
                return "Z";
            }
            if ("byte".equals((Object)string2)) {
                return "B";
            }
            if ("char".equals((Object)string2)) {
                return "C";
            }
            if ("double".equals((Object)string2)) {
                return "D";
            }
            if ("float".equals((Object)string2)) {
                return "F";
            }
            if ("int".equals((Object)string2)) {
                return "I";
            }
            if ("long".equals((Object)string2)) {
                return "J";
            }
            if ("short".equals((Object)string2)) {
                string2 = "S";
            }
            return string2;
        }
        if (class_.isArray()) {
            return class_.getName().replace('.', '/');
        }
        StringBuilder stringBuilder = new StringBuilder("L");
        stringBuilder.append(class_.getName().replace('.', '/'));
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    protected static Method getMethodID(Class class_, String string2, String string3, boolean bl) {
        Method method;
        a a2 = new a(class_, string2, string3);
        if (ReflectionHelper.a(a2)) {
            method = (Method)a2.a;
        } else {
            Class[] arrclass = ReflectionHelper.a(string3);
            float f2 = 0.0f;
            Method method2 = null;
            while (class_ != null) {
                block6 : {
                    Method[] arrmethod = class_.getDeclaredMethods();
                    int n2 = arrmethod.length;
                    Method method3 = method2;
                    float f3 = f2;
                    for (int i2 = 0; i2 < n2; ++i2) {
                        float f4;
                        Method method4 = arrmethod[i2];
                        if (bl != Modifier.isStatic((int)method4.getModifiers()) || method4.getName().compareTo(string2) != 0 || !((f4 = ReflectionHelper.a(method4.getReturnType(), method4.getParameterTypes(), arrclass)) > f3)) continue;
                        if (f4 != 1.0f) {
                            method3 = method4;
                            f3 = f4;
                            continue;
                        }
                        method2 = method4;
                        f2 = f4;
                        break block6;
                    }
                    f2 = f3;
                    method2 = method3;
                }
                if (f2 == 1.0f || class_.isPrimitive() || class_.isInterface() || class_.equals(Object.class) || class_.equals((Object)Void.TYPE)) break;
                class_ = class_.getSuperclass();
            }
            ReflectionHelper.a(a2, method2);
            method = method2;
        }
        if (method == null) {
            Object[] arrobject = new Object[4];
            String string4 = bl ? "static" : "non-static";
            arrobject[0] = string4;
            arrobject[1] = string2;
            arrobject[2] = string3;
            arrobject[3] = class_.getName();
            throw new NoSuchMethodError(String.format((String)"no %s method with name='%s' signature='%s' in class L%s;", (Object[])arrobject));
        }
        return method;
    }

    private static native void nativeProxyFinalize(long var0);

    private static native Object nativeProxyInvoke(long var0, String var2, Object[] var3);

    private static native void nativeProxyLogJNIInvokeException(long var0);

    protected static Object newProxyInstance(long l2, Class class_) {
        return ReflectionHelper.newProxyInstance(l2, new Class[]{class_});
    }

    protected static Object newProxyInstance(final long l2, final Class[] arrclass) {
        return Proxy.newProxyInstance((ClassLoader)ReflectionHelper.class.getClassLoader(), (Class[])arrclass, (InvocationHandler)new b(){
            private long c = ReflectionHelper.a();
            private long d;
            private boolean e;

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            private Object a(Object var1_1, Method var2_2, Object[] var3_3) {
                if (var3_3 != null) ** GOTO lbl4
                try {
                    var3_3 = new Object[]{};
lbl4: // 2 sources:
                    var4_4 = var2_2.getDeclaringClass();
                    var5_5 = new Class[]{Class.class, Integer.TYPE};
                    var6_6 = MethodHandles.Lookup.class.getDeclaredConstructor(var5_5);
                    var6_6.setAccessible(true);
                    var7_7 = new Object[]{var4_4, 2};
                    return ((MethodHandles.Lookup)var6_6.newInstance(var7_7)).in(var4_4).unreflectSpecial(var2_2, var4_4).bindTo(var1_1).invokeWithArguments(var3_3);
                }
                catch (NoClassDefFoundError v0) {
                    g.Log(6, String.format((String)"Java interface default methods are only supported since Android Oreo", (Object[])new Object[0]));
                    ReflectionHelper.a(this.d);
                    return null;
                }
            }

            @Override
            public final void a(long l22, boolean bl) {
                this.d = l22;
                this.e = bl;
            }

            protected final void finalize() {
                try {
                    if (this.c == b) {
                        ReflectionHelper.nativeProxyFinalize(l2);
                    }
                    return;
                }
                finally {
                    super.finalize();
                }
            }

            public final Object invoke(Object object, Method method, Object[] arrobject) {
                Object object2;
                block7 : {
                    long l22;
                    block6 : {
                        block5 : {
                            if (this.c != b) {
                                g.Log(6, "Scripting proxy object was destroyed, because Unity player was unloaded.");
                                return null;
                            }
                            this.d = 0L;
                            this.e = false;
                            object2 = ReflectionHelper.nativeProxyInvoke(l2, method.getName(), arrobject);
                            if (!this.e) break block5;
                            if ((1024 & method.getModifiers()) == 0) {
                                return this.a(object, method, arrobject);
                            }
                            l22 = this.d;
                            break block6;
                        }
                        l22 = this.d;
                        if (l22 == 0L) break block7;
                    }
                    ReflectionHelper.nativeProxyLogJNIInvokeException(l22);
                }
                return object2;
            }
        });
    }

    protected static void setNativeExceptionOnProxy(Object object, long l2, boolean bl) {
        ((b)Proxy.getInvocationHandler((Object)object)).a(l2, bl);
    }

    private static final class a {
        public volatile Member a;
        private final Class b;
        private final String c;
        private final String d;
        private final int e;

        a(Class class_, String string2, String string3) {
            this.b = class_;
            this.c = string2;
            this.d = string3;
            this.e = 31 * (31 * (527 + this.b.hashCode()) + this.c.hashCode()) + this.d.hashCode();
        }

        public final boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (object instanceof a) {
                a a2 = (a)object;
                if (this.e == a2.e && this.d.equals((Object)a2.d) && this.c.equals((Object)a2.c) && this.b.equals((Object)a2.b)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return this.e;
        }
    }

    protected static interface b
    extends InvocationHandler {
        public void a(long var1, boolean var3);
    }

}

