package androidx.lifecycle;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import za.v1;
import za.x0;

/* JADX INFO: loaded from: classes.dex */
public final class LifecycleCoroutineScopeImpl extends g implements i {
    private final f a;
    private final ia.f b;

    static final class a extends kotlin.coroutines.jvm.internal.k implements pa.p {
        int a;
        private /* synthetic */ Object b;

        a(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            a aVar = LifecycleCoroutineScopeImpl.this.new a(continuation);
            aVar.b = obj;
            return aVar;
        }

        public final Object invokeSuspend(Object obj) {
            ja.b.c();
            if (this.a != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ea.o.b(obj);
            za.i0 i0Var = (za.i0) this.b;
            if (LifecycleCoroutineScopeImpl.this.b().b().compareTo(f.b.INITIALIZED) >= 0) {
                LifecycleCoroutineScopeImpl.this.b().a(LifecycleCoroutineScopeImpl.this);
            } else {
                v1.d(i0Var.w(), (CancellationException) null, 1, (Object) null);
            }
            return ea.v.a;
        }

        public final Object invoke(za.i0 i0Var, Continuation continuation) {
            return create(i0Var, continuation).invokeSuspend(ea.v.a);
        }
    }

    public LifecycleCoroutineScopeImpl(f fVar, ia.f fVar2) {
        kotlin.jvm.internal.m.g(fVar, "lifecycle");
        kotlin.jvm.internal.m.g(fVar2, "coroutineContext");
        this.a = fVar;
        this.b = fVar2;
        if (b().b() == f.b.DESTROYED) {
            v1.d(w(), (CancellationException) null, 1, (Object) null);
        }
    }

    @Override // androidx.lifecycle.i
    public void a(m mVar, f.a aVar) {
        kotlin.jvm.internal.m.g(mVar, "source");
        kotlin.jvm.internal.m.g(aVar, "event");
        if (b().b().compareTo(f.b.DESTROYED) <= 0) {
            b().d(this);
            v1.d(w(), (CancellationException) null, 1, (Object) null);
        }
    }

    public f b() {
        return this.a;
    }

    public final void c() {
        za.h.d(this, x0.c().H(), (za.k0) null, new a(null), 2, (Object) null);
    }

    public ia.f w() {
        return this.b;
    }
}
