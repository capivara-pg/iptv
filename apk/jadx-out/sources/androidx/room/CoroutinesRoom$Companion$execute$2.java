package androidx.room;

import java.util.concurrent.Callable;
import kotlin.coroutines.Continuation;
import za.i0;

/* JADX INFO: loaded from: classes.dex */
@kotlin.coroutines.jvm.internal.f(c = "androidx.room.CoroutinesRoom$Companion$execute$2", f = "CoroutinesRoom.kt", l = {}, m = "invokeSuspend")
final class CoroutinesRoom$Companion$execute$2 extends kotlin.coroutines.jvm.internal.k implements pa.p {
    final /* synthetic */ Callable<R> $callable;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoroutinesRoom$Companion$execute$2(Callable<R> callable, Continuation<? super CoroutinesRoom$Companion$execute$2> continuation) {
        super(2, continuation);
        this.$callable = callable;
    }

    public final Continuation<ea.v> create(Object obj, Continuation<?> continuation) {
        return new CoroutinesRoom$Companion$execute$2(this.$callable, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        ja.b.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ea.o.b(obj);
        return this.$callable.call();
    }

    public final Object invoke(i0 i0Var, Continuation<? super R> continuation) {
        return create(i0Var, continuation).invokeSuspend(ea.v.a);
    }
}
