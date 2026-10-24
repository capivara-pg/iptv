package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class g0 {
    private final j0 a;
    private final b b;
    private final j0.a c;

    public interface b {
        f0 a(Class cls, j0.a aVar);

        f0 b(Class cls);
    }

    public static class c implements b {
        private static c b;
        public static final a a = new a(null);
        public static final j0.a.b c = a.C0038a.a;

        public static final class a {

            /* JADX INFO: renamed from: androidx.lifecycle.g0$c$a$a, reason: collision with other inner class name */
            private static final class C0038a implements j0.a.b {
                public static final C0038a a = new C0038a();

                private C0038a() {
                }
            }

            private a() {
            }

            public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
                this();
            }

            public final c a() {
                if (c.b == null) {
                    c.b = new c();
                }
                c cVar = c.b;
                kotlin.jvm.internal.m.d(cVar);
                return cVar;
            }
        }

        @Override // androidx.lifecycle.g0.b
        public /* synthetic */ f0 a(Class cls, j0.a aVar) {
            return h0.b(this, cls, aVar);
        }

        @Override // androidx.lifecycle.g0.b
        public f0 b(Class cls) throws InvocationTargetException {
            kotlin.jvm.internal.m.g(cls, "modelClass");
            try {
                Object objNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                kotlin.jvm.internal.m.f(objNewInstance, "{\n                modelC…wInstance()\n            }");
                return (f0) objNewInstance;
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot create an instance of " + cls, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            }
        }
    }

    public static class d {
        public abstract void c(f0 f0Var);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public g0(j0 j0Var, b bVar) {
        this(j0Var, bVar, null, 4, null);
        kotlin.jvm.internal.m.g(j0Var, "store");
        kotlin.jvm.internal.m.g(bVar, "factory");
    }

    public f0 a(Class cls) {
        kotlin.jvm.internal.m.g(cls, "modelClass");
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return b("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
    }

    public f0 b(String str, Class cls) {
        f0 f0VarB;
        kotlin.jvm.internal.m.g(str, "key");
        kotlin.jvm.internal.m.g(cls, "modelClass");
        f0 f0VarB2 = this.a.b(str);
        if (!cls.isInstance(f0VarB2)) {
            j0.a dVar = new j0.d(this.c);
            dVar.c(c.c, str);
            try {
                f0VarB = this.b.a(cls, dVar);
            } catch (AbstractMethodError unused) {
                f0VarB = this.b.b(cls);
            }
            this.a.d(str, f0VarB);
            return f0VarB;
        }
        Object obj = this.b;
        d dVar2 = obj instanceof d ? (d) obj : null;
        if (dVar2 != null) {
            kotlin.jvm.internal.m.d(f0VarB2);
            dVar2.c(f0VarB2);
        }
        kotlin.jvm.internal.m.e(f0VarB2, "null cannot be cast to non-null type T of androidx.lifecycle.ViewModelProvider.get");
        return f0VarB2;
    }

    public static class a extends c {
        private static a f;
        private final Application d;
        public static final C0036a e = new C0036a(null);
        public static final j0.a.b g = C0036a.C0037a.a;

        /* JADX INFO: renamed from: androidx.lifecycle.g0$a$a, reason: collision with other inner class name */
        public static final class C0036a {

            /* JADX INFO: renamed from: androidx.lifecycle.g0$a$a$a, reason: collision with other inner class name */
            private static final class C0037a implements j0.a.b {
                public static final C0037a a = new C0037a();

                private C0037a() {
                }
            }

            private C0036a() {
            }

            public /* synthetic */ C0036a(kotlin.jvm.internal.g gVar) {
                this();
            }

            public final a a(Application application) {
                kotlin.jvm.internal.m.g(application, "application");
                if (a.f == null) {
                    a.f = new a(application);
                }
                a aVar = a.f;
                kotlin.jvm.internal.m.d(aVar);
                return aVar;
            }
        }

        private a(Application application, int i) {
            this.d = application;
        }

        private final f0 g(Class cls, Application application) {
            if (!androidx.lifecycle.a.class.isAssignableFrom(cls)) {
                return super.b(cls);
            }
            try {
                f0 f0Var = (f0) cls.getConstructor(Application.class).newInstance(application);
                kotlin.jvm.internal.m.f(f0Var, "{\n                try {\n…          }\n            }");
                return f0Var;
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            } catch (NoSuchMethodException e4) {
                throw new RuntimeException("Cannot create an instance of " + cls, e4);
            } catch (InvocationTargetException e5) {
                throw new RuntimeException("Cannot create an instance of " + cls, e5);
            }
        }

        @Override // androidx.lifecycle.g0.c, androidx.lifecycle.g0.b
        public f0 a(Class cls, j0.a aVar) {
            kotlin.jvm.internal.m.g(cls, "modelClass");
            kotlin.jvm.internal.m.g(aVar, "extras");
            if (this.d != null) {
                return b(cls);
            }
            Application application = (Application) aVar.a(g);
            if (application != null) {
                return g(cls, application);
            }
            if (androidx.lifecycle.a.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
            }
            return super.b(cls);
        }

        @Override // androidx.lifecycle.g0.c, androidx.lifecycle.g0.b
        public f0 b(Class cls) {
            kotlin.jvm.internal.m.g(cls, "modelClass");
            Application application = this.d;
            if (application != null) {
                return g(cls, application);
            }
            throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
        }

        public a() {
            this(null, 0);
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public a(Application application) {
            this(application, 0);
            kotlin.jvm.internal.m.g(application, "application");
        }
    }

    public g0(j0 j0Var, b bVar, j0.a aVar) {
        kotlin.jvm.internal.m.g(j0Var, "store");
        kotlin.jvm.internal.m.g(bVar, "factory");
        kotlin.jvm.internal.m.g(aVar, "defaultCreationExtras");
        this.a = j0Var;
        this.b = bVar;
        this.c = aVar;
    }

    public /* synthetic */ g0(j0 j0Var, b bVar, j0.a aVar, int i, kotlin.jvm.internal.g gVar) {
        this(j0Var, bVar, (i & 4) != 0 ? j0.a.a.b : aVar);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public g0(k0 k0Var, b bVar) {
        this(k0Var.getViewModelStore(), bVar, i0.a(k0Var));
        kotlin.jvm.internal.m.g(k0Var, "owner");
        kotlin.jvm.internal.m.g(bVar, "factory");
    }
}
