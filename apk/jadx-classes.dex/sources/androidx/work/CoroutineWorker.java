package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import f8.w;
import i8.d;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.jvm.internal.j;
import p8.p;
import q8.g;
import q8.i;
import z8.c0;
import z8.d0;
import z8.e0;
import z8.f;
import z8.f1;
import z8.j1;
import z8.n0;
import z8.q;
import z8.y;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class CoroutineWorker extends ListenableWorker {
    private final q f;
    private final androidx.work.impl.utils.futures.c g;
    private final y h;

    static final class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (CoroutineWorker.this.v().isCancelled()) {
                f1.a.a(CoroutineWorker.this.w(), (CancellationException) null, 1, (Object) null);
            }
        }
    }

    static final class b extends j implements p {
        Object b;
        int c;
        final /* synthetic */ s0.j d;
        final /* synthetic */ CoroutineWorker e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(s0.j jVar, CoroutineWorker coroutineWorker, d dVar) {
            super(2, dVar);
            this.d = jVar;
            this.e = coroutineWorker;
        }

        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Object invoke(c0 c0Var, d dVar) {
            return create(c0Var, dVar).invokeSuspend(w.a);
        }

        public final d create(Object obj, d dVar) {
            return new b(this.d, this.e, dVar);
        }

        public final Object invokeSuspend(Object obj) {
            s0.j jVar;
            Object objC = j8.b.c();
            int i = this.c;
            if (i == 0) {
                f8.p.b(obj);
                s0.j jVar2 = this.d;
                CoroutineWorker coroutineWorker = this.e;
                this.b = jVar2;
                this.c = 1;
                Object objT = coroutineWorker.t(this);
                if (objT == objC) {
                    return objC;
                }
                jVar = jVar2;
                obj = objT;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                jVar = (s0.j) this.b;
                f8.p.b(obj);
            }
            jVar.b(obj);
            return w.a;
        }
    }

    static final class c extends j implements p {
        int b;

        c(d dVar) {
            super(2, dVar);
        }

        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Object invoke(c0 c0Var, d dVar) {
            return create(c0Var, dVar).invokeSuspend(w.a);
        }

        public final d create(Object obj, d dVar) {
            return CoroutineWorker.this.new c(dVar);
        }

        public final Object invokeSuspend(Object obj) {
            Object objC = j8.b.c();
            int i = this.b;
            try {
                if (i == 0) {
                    f8.p.b(obj);
                    CoroutineWorker coroutineWorker = CoroutineWorker.this;
                    this.b = 1;
                    obj = coroutineWorker.r(this);
                    if (obj == objC) {
                        return objC;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    f8.p.b(obj);
                }
                CoroutineWorker.this.v().o((ListenableWorker.a) obj);
            } catch (Throwable th) {
                CoroutineWorker.this.v().p(th);
            }
            return w.a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        i.g(context, "appContext");
        i.g(workerParameters, "params");
        this.f = j1.b((f1) null, 1, (Object) null);
        androidx.work.impl.utils.futures.c cVarS = androidx.work.impl.utils.futures.c.s();
        i.f(cVarS, "create()");
        this.g = cVarS;
        cVarS.addListener(new a(), h().c());
        this.h = n0.a();
    }

    static /* synthetic */ Object u(CoroutineWorker coroutineWorker, d dVar) {
        throw new IllegalStateException("Not implemented");
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture d() {
        q qVarB = j1.b((f1) null, 1, (Object) null);
        c0 c0VarA = d0.a(s().u(qVarB));
        s0.j jVar = new s0.j(qVarB, (androidx.work.impl.utils.futures.c) null, 2, (g) null);
        f.b(c0VarA, (i8.g) null, (e0) null, new b(jVar, this, null), 3, (Object) null);
        return jVar;
    }

    @Override // androidx.work.ListenableWorker
    public final void m() {
        super.m();
        this.g.cancel(false);
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture p() {
        f.b(d0.a(s().u(this.f)), (i8.g) null, (e0) null, new c(null), 3, (Object) null);
        return this.g;
    }

    public abstract Object r(d dVar);

    public y s() {
        return this.h;
    }

    public Object t(d dVar) {
        return u(this, dVar);
    }

    public final androidx.work.impl.utils.futures.c v() {
        return this.g;
    }

    public final q w() {
        return this.f;
    }
}
