package androidx.lifecycle;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class ReflectiveGenericLifecycleObserver implements e {
    private final Object a;
    private final b.a b;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.a = obj;
        this.b = b.c.c(obj.getClass());
    }

    @Override // androidx.lifecycle.e
    public void a(g gVar, d.b bVar) {
        this.b.a(gVar, bVar, this.a);
    }
}
