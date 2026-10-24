package androidx.lifecycle;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class z {
    public static final j0.a.b a = new b();
    public static final j0.a.b b = new c();
    public static final j0.a.b c = new a();

    public static final class a implements j0.a.b {
        a() {
        }
    }

    public static final class b implements j0.a.b {
        b() {
        }
    }

    public static final class c implements j0.a.b {
        c() {
        }
    }

    static final class d extends kotlin.jvm.internal.n implements pa.l {
        public static final d e = new d();

        d() {
            super(1);
        }

        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b0 invoke(j0.a aVar) {
            kotlin.jvm.internal.m.g(aVar, "$this$initializer");
            return new b0();
        }
    }

    public static final y a(j0.a aVar) {
        kotlin.jvm.internal.m.g(aVar, "<this>");
        s0.c cVar = (s0.c) aVar.a(a);
        if (cVar == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
        }
        k0 k0Var = (k0) aVar.a(b);
        if (k0Var == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        }
        Bundle bundle = (Bundle) aVar.a(c);
        String str = (String) aVar.a(g0.c.c);
        if (str != null) {
            return b(cVar, k0Var, str, bundle);
        }
        throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_KEY`");
    }

    private static final y b(s0.c cVar, k0 k0Var, String str, Bundle bundle) {
        c(cVar);
        y yVar = (y) d(k0Var).f().get(str);
        if (yVar != null) {
            return yVar;
        }
        y.a aVar = y.f;
        throw null;
    }

    public static final a0 c(s0.c cVar) {
        kotlin.jvm.internal.m.g(cVar, "<this>");
        cVar.getSavedStateRegistry().c("androidx.lifecycle.internal.SavedStateHandlesProvider");
        throw new IllegalStateException("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
    }

    public static final b0 d(k0 k0Var) {
        kotlin.jvm.internal.m.g(k0Var, "<this>");
        j0.c cVar = new j0.c();
        cVar.a(kotlin.jvm.internal.b0.b(b0.class), d.e);
        return (b0) new g0(k0Var, cVar.b()).b("androidx.lifecycle.internal.SavedStateHandlesVM", b0.class);
    }
}
