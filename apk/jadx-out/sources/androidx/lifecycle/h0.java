package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class h0 {
    public static f0 a(g0.b bVar, Class cls) {
        kotlin.jvm.internal.m.g(cls, "modelClass");
        throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
    }

    public static f0 b(g0.b bVar, Class cls, j0.a aVar) {
        kotlin.jvm.internal.m.g(cls, "modelClass");
        kotlin.jvm.internal.m.g(aVar, "extras");
        return bVar.b(cls);
    }
}
