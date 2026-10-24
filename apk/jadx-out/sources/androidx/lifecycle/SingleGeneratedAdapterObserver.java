package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public final class SingleGeneratedAdapterObserver implements i {
    private final c a;

    public SingleGeneratedAdapterObserver(c cVar) {
        kotlin.jvm.internal.m.g(cVar, "generatedAdapter");
        this.a = cVar;
    }

    @Override // androidx.lifecycle.i
    public void a(m mVar, f.a aVar) {
        kotlin.jvm.internal.m.g(mVar, "source");
        kotlin.jvm.internal.m.g(aVar, "event");
        this.a.callMethods(mVar, aVar, false, null);
        this.a.callMethods(mVar, aVar, true, null);
    }
}
