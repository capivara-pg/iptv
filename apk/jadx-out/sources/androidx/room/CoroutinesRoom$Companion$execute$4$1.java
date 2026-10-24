package androidx.room;

import android.os.CancellationSignal;
import java.util.concurrent.CancellationException;
import za.r1;

/* JADX INFO: loaded from: classes.dex */
final class CoroutinesRoom$Companion$execute$4$1 extends kotlin.jvm.internal.n implements pa.l {
    final /* synthetic */ CancellationSignal $cancellationSignal;
    final /* synthetic */ r1 $job;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoroutinesRoom$Companion$execute$4$1(CancellationSignal cancellationSignal, r1 r1Var) {
        super(1);
        this.$cancellationSignal = cancellationSignal;
        this.$job = r1Var;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return ea.v.a;
    }

    public final void invoke(Throwable th) {
        t0.b.a(this.$cancellationSignal);
        r1.a.a(this.$job, (CancellationException) null, 1, (Object) null);
    }
}
