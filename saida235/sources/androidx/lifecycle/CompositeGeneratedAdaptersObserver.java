package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public final class CompositeGeneratedAdaptersObserver implements i {
    private final c[] a;

    public CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        kotlin.jvm.internal.m.g(cVarArr, "generatedAdapters");
        this.a = cVarArr;
    }

    @Override // androidx.lifecycle.i
    public void a(m mVar, f.a aVar) {
        kotlin.jvm.internal.m.g(mVar, "source");
        kotlin.jvm.internal.m.g(aVar, "event");
        r rVar = new r();
        for (c cVar : this.a) {
            cVar.callMethods(mVar, aVar, false, rVar);
        }
        for (c cVar2 : this.a) {
            cVar2.callMethods(mVar, aVar, true, rVar);
        }
    }
}
