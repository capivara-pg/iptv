package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class w {
    private final b a;
    private final x b;

    public static class a extends d {
        private static a c;
        private Application b;

        public a(Application application) {
            this.b = application;
        }

        public static a c(Application application) {
            if (c == null) {
                c = new a(application);
            }
            return c;
        }

        @Override // androidx.lifecycle.w.d, androidx.lifecycle.w.b
        public v a(Class cls) {
            if (!androidx.lifecycle.a.class.isAssignableFrom(cls)) {
                return super.a(cls);
            }
            try {
                return (v) cls.getConstructor(Application.class).newInstance(this.b);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + cls, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Cannot create an instance of " + cls, e4);
            }
        }
    }

    public interface b {
        v a(Class cls);
    }

    static abstract class c extends e implements b {
        c() {
        }

        public abstract v c(String str, Class cls);
    }

    public static class d implements b {
        private static d a;

        static d b() {
            if (a == null) {
                a = new d();
            }
            return a;
        }

        @Override // androidx.lifecycle.w.b
        public v a(Class cls) {
            try {
                return (v) cls.newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + cls, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            }
        }
    }

    static class e {
        e() {
        }

        abstract void b(v vVar);
    }

    public w(x xVar, b bVar) {
        this.a = bVar;
        this.b = xVar;
    }

    public v a(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return b("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
    }

    public v b(String str, Class cls) {
        v vVarB = this.b.b(str);
        if (cls.isInstance(vVarB)) {
            Object obj = this.a;
            if (obj instanceof e) {
                ((e) obj).b(vVarB);
            }
            return vVarB;
        }
        b bVar = this.a;
        v vVarC = bVar instanceof c ? ((c) bVar).c(str, cls) : bVar.a(cls);
        this.b.d(str, vVarC);
        return vVarC;
    }
}
