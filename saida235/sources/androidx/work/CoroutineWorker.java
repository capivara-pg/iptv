package androidx.work;

import a1.j;
import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import ea.o;
import ea.v;
import ia.f;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.k;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.m;
import pa.p;
import za.e0;
import za.h;
import za.i0;
import za.j0;
import za.k0;
import za.r1;
import za.v1;
import za.w;
import za.x0;

/* JADX INFO: loaded from: classes.dex */
public abstract class CoroutineWorker extends ListenableWorker {
    private final w f;
    private final androidx.work.impl.utils.futures.c g;
    private final e0 h;

    static final class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (CoroutineWorker.this.v().isCancelled()) {
                r1.a.a(CoroutineWorker.this.w(), (CancellationException) null, 1, (Object) null);
            }
        }
    }

    static final class b extends k implements p {
        Object a;
        int b;
        final /* synthetic */ j c;
        final /* synthetic */ CoroutineWorker d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(j jVar, CoroutineWorker coroutineWorker, Continuation continuation) {
            super(2, continuation);
            this.c = jVar;
            this.d = coroutineWorker;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new b(this.c, this.d, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            j jVar;
            Object objC = ja.b.c();
            int i = this.b;
            if (i == 0) {
                o.b(obj);
                j jVar2 = this.c;
                CoroutineWorker coroutineWorker = this.d;
                this.a = jVar2;
                this.b = 1;
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
                jVar = (j) this.a;
                o.b(obj);
            }
            jVar.b(obj);
            return v.a;
        }

        public final Object invoke(i0 i0Var, Continuation continuation) {
            return create(i0Var, continuation).invokeSuspend(v.a);
        }
    }

    static final class c extends k implements p {
        int a;

        c(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return CoroutineWorker.this.new c(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object objC = ja.b.c();
            int i = this.a;
            try {
                if (i == 0) {
                    o.b(obj);
                    CoroutineWorker coroutineWorker = CoroutineWorker.this;
                    this.a = 1;
                    obj = coroutineWorker.r(this);
                    if (obj == objC) {
                        return objC;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    o.b(obj);
                }
                CoroutineWorker.this.v().o((ListenableWorker.a) obj);
            } catch (Throwable th) {
                CoroutineWorker.this.v().p(th);
            }
            return v.a;
        }

        public final Object invoke(i0 i0Var, Continuation continuation) {
            return create(i0Var, continuation).invokeSuspend(v.a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        m.g(context, "appContext");
        m.g(workerParameters, "params");
        this.f = v1.b((r1) null, 1, (Object) null);
        androidx.work.impl.utils.futures.c cVarS = androidx.work.impl.utils.futures.c.s();
        m.f(cVarS, "create()");
        this.g = cVarS;
        cVarS.addListener(new a(), h().c());
        this.h = x0.a();
    }

    static /* synthetic */ Object u(CoroutineWorker coroutineWorker, Continuation continuation) {
        throw new IllegalStateException("Not implemented");
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture d() {
        w wVarB = v1.b((r1) null, 1, (Object) null);
        i0 i0VarA = j0.a(s().plus(wVarB));
        j jVar = new j(wVarB, (androidx.work.impl.utils.futures.c) null, 2, (g) null);
        h.d(i0VarA, (f) null, (k0) null, new b(jVar, this, null), 3, (Object) null);
        return jVar;
    }

    @Override // androidx.work.ListenableWorker
    public final void m() {
        super.m();
        this.g.cancel(false);
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture p() {
        h.d(j0.a(s().plus(this.f)), (f) null, (k0) null, new c(null), 3, (Object) null);
        return this.g;
    }

    public abstract Object r(Continuation continuation);

    public e0 s() {
        return this.h;
    }

    public Object t(Continuation continuation) {
        return u(this, continuation);
    }

    public final androidx.work.impl.utils.futures.c v() {
        return this.g;
    }

    public final w w() {
        return this.f;
    }
}
