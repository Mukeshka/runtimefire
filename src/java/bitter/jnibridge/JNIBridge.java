/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Integer
 *  java.lang.NoClassDefFoundError
 *  java.lang.NoSuchMethodError
 *  java.lang.NoSuchMethodException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.invoke.MethodHandle
 *  java.lang.invoke.MethodHandles
 *  java.lang.invoke.MethodHandles$Lookup
 *  java.lang.reflect.Constructor
 *  java.lang.reflect.InvocationHandler
 *  java.lang.reflect.Method
 *  java.lang.reflect.Proxy
 */
package bitter.jnibridge;

import java.io.PrintStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JNIBridge {
    static native void delete(long var0);

    static void disableInterfaceProxy(Object object) {
        if (object != null) {
            ((a)Proxy.getInvocationHandler((Object)object)).a();
        }
    }

    static native Object invoke(long var0, Class var2, Method var3, Object[] var4);

    static Object newInterfaceProxy(long l2, Class[] arrclass) {
        return Proxy.newProxyInstance((ClassLoader)JNIBridge.class.getClassLoader(), (Class[])arrclass, (InvocationHandler)new a(l2));
    }

    private static final class a
    implements InvocationHandler {
        private Object a = new Object[0];
        private long b;
        private Constructor c;

        public a(long l2) {
            this.b = l2;
            try {
                Class[] arrclass = new Class[]{Class.class, Integer.TYPE};
                this.c = MethodHandles.Lookup.class.getDeclaredConstructor(arrclass);
                this.c.setAccessible(true);
                return;
            }
            catch (NoSuchMethodException noSuchMethodException) {
                this.c = null;
                return;
            }
            catch (NoClassDefFoundError noClassDefFoundError) {
                this.c = null;
                return;
            }
        }

        private Object a(Object object, Method method, Object[] arrobject) {
            if (arrobject == null) {
                arrobject = new Object[]{};
            }
            Class class_ = method.getDeclaringClass();
            Constructor constructor = this.c;
            Object[] arrobject2 = new Object[]{class_, 2};
            return ((MethodHandles.Lookup)constructor.newInstance(arrobject2)).in(class_).unreflectSpecial(method, class_).bindTo(object).invokeWithArguments(arrobject);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public final void a() {
            Object object;
            Object object2 = object = this.a;
            synchronized (object2) {
                this.b = 0L;
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public final void finalize() {
            Object object;
            Object object2 = object = this.a;
            synchronized (object2) {
                if (this.b == 0L) {
                    return;
                }
                JNIBridge.delete(this.b);
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public final Object invoke(Object object, Method method, Object[] arrobject) {
            Object object2;
            Object object3 = object2 = this.a;
            synchronized (object3) {
                if (this.b == 0L) {
                    return null;
                }
                try {
                    return JNIBridge.invoke(this.b, method.getDeclaringClass(), method, arrobject);
                }
                catch (NoSuchMethodError noSuchMethodError) {
                    if (this.c == null) {
                        System.err.println("JNIBridge error: Java interface default methods are only supported since Android Oreo");
                        throw noSuchMethodError;
                    }
                    if ((1024 & method.getModifiers()) != 0) throw noSuchMethodError;
                    return this.a(object, method, arrobject);
                }
            }
        }
    }

}

