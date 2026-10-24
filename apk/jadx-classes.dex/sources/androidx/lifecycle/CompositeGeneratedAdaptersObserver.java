package androidx.lifecycle;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class CompositeGeneratedAdaptersObserver implements e {
    private final c[] a;

    CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.a = cVarArr;
    }

    @Override // androidx.lifecycle.e
    public void a(g gVar, d.b bVar) {
        k kVar = new k();
        for (c cVar : this.a) {
            cVar.callMethods(gVar, bVar, false, kVar);
        }
        for (c cVar2 : this.a) {
            cVar2.callMethods(gVar, bVar, true, kVar);
        }
    }
}
