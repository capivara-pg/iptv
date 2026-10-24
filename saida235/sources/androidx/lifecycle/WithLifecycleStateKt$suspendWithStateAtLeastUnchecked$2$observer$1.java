package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public final class WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$1 implements i {
    final /* synthetic */ f.b a;
    final /* synthetic */ f b;
    final /* synthetic */ za.n c;
    final /* synthetic */ pa.a d;

    @Override // androidx.lifecycle.i
    public void a(m mVar, f.a aVar) {
        Object objA;
        kotlin.jvm.internal.m.g(mVar, "source");
        kotlin.jvm.internal.m.g(aVar, "event");
        if (aVar != f.a.Companion.c(this.a)) {
            if (aVar == f.a.ON_DESTROY) {
                this.b.d(this);
                za.n nVar = this.c;
                ea.n.a aVar2 = ea.n.a;
                nVar.resumeWith(ea.n.a(ea.o.a(new h())));
                return;
            }
            return;
        }
        this.b.d(this);
        za.n nVar2 = this.c;
        pa.a aVar3 = this.d;
        try {
            ea.n.a aVar4 = ea.n.a;
            objA = ea.n.a(aVar3.invoke());
        } catch (Throwable th) {
            ea.n.a aVar5 = ea.n.a;
            objA = ea.n.a(ea.o.a(th));
        }
        nVar2.resumeWith(objA);
    }
}
