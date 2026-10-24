package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleController implements i {
    private final String a;
    private final y b;
    private boolean c;

    public SavedStateHandleController(String str, y yVar) {
        kotlin.jvm.internal.m.g(str, "key");
        kotlin.jvm.internal.m.g(yVar, "handle");
        this.a = str;
        this.b = yVar;
    }

    @Override // androidx.lifecycle.i
    public void a(m mVar, f.a aVar) {
        kotlin.jvm.internal.m.g(mVar, "source");
        kotlin.jvm.internal.m.g(aVar, "event");
        if (aVar == f.a.ON_DESTROY) {
            this.c = false;
            mVar.getLifecycle().d(this);
        }
    }

    public final void b(androidx.savedstate.a aVar, f fVar) {
        kotlin.jvm.internal.m.g(aVar, "registry");
        kotlin.jvm.internal.m.g(fVar, "lifecycle");
        if (!(!this.c)) {
            throw new IllegalStateException("Already attached to lifecycleOwner".toString());
        }
        this.c = true;
        fVar.a(this);
        aVar.h(this.a, this.b.c());
    }

    public final y c() {
        return this.b;
    }

    public final boolean d() {
        return this.c;
    }
}
