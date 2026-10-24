package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class ReflectiveGenericLifecycleObserver implements i {
    private final Object a;
    private final b.a b;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.a = obj;
        this.b = b.c.c(obj.getClass());
    }

    @Override // androidx.lifecycle.i
    public void a(m mVar, f.a aVar) {
        this.b.a(mVar, aVar, this.a);
    }
}
