package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleAttacher implements i {
    @Override // androidx.lifecycle.i
    public void a(m mVar, f.a aVar) {
        kotlin.jvm.internal.m.g(mVar, "source");
        kotlin.jvm.internal.m.g(aVar, "event");
        if (aVar == f.a.ON_CREATE) {
            mVar.getLifecycle().d(this);
            throw null;
        }
        throw new IllegalStateException(("Next event must be ON_CREATE, it was " + aVar).toString());
    }
}
