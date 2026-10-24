package androidx.room;

import java.util.concurrent.Callable;
import kotlin.coroutines.Continuation;
import za.i0;

/* JADX INFO: loaded from: classes.dex */
@kotlin.coroutines.jvm.internal.f(c = "androidx.room.CoroutinesRoom$Companion$execute$4$job$1", f = "CoroutinesRoom.kt", l = {}, m = "invokeSuspend")
final class CoroutinesRoom$Companion$execute$4$job$1 extends kotlin.coroutines.jvm.internal.k implements pa.p {
    final /* synthetic */ Callable<R> $callable;
    final /* synthetic */ za.n $continuation;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoroutinesRoom$Companion$execute$4$job$1(Callable<R> callable, za.n nVar, Continuation<? super CoroutinesRoom$Companion$execute$4$job$1> continuation) {
        super(2, continuation);
        this.$callable = callable;
        this.$continuation = nVar;
    }

    public final Continuation<ea.v> create(Object obj, Continuation<?> continuation) {
        return new CoroutinesRoom$Companion$execute$4$job$1(this.$callable, this.$continuation, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        ja.b.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ea.o.b(obj);
        try {
            this.$continuation.resumeWith(ea.n.a(this.$callable.call()));
        } catch (Throwable th) {
            za.n nVar = this.$continuation;
            ea.n.a aVar = ea.n.a;
            nVar.resumeWith(ea.n.a(ea.o.a(th)));
        }
        return ea.v.a;
    }

    public final Object invoke(i0 i0Var, Continuation<? super ea.v> continuation) {
        return create(i0Var, continuation).invokeSuspend(ea.v.a);
    }
}
