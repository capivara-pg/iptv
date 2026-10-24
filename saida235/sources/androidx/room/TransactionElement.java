package androidx.room;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class TransactionElement implements ia.f.b {
    public static final Key Key = new Key(null);
    private final AtomicInteger referenceCount;
    private final ia.d transactionDispatcher;

    public static final class Key implements ia.f.c {
        private Key() {
        }

        public /* synthetic */ Key(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public TransactionElement(ia.d dVar) {
        kotlin.jvm.internal.m.g(dVar, "transactionDispatcher");
        this.transactionDispatcher = dVar;
        this.referenceCount = new AtomicInteger(0);
    }

    public final void acquire() {
        this.referenceCount.incrementAndGet();
    }

    public <R> R fold(R r, pa.p pVar) {
        return (R) ia.f.b.a.a(this, r, pVar);
    }

    public <E extends ia.f.b> E get(ia.f.c cVar) {
        return (E) ia.f.b.a.b(this, cVar);
    }

    public ia.f.c getKey() {
        return Key;
    }

    public final ia.d getTransactionDispatcher$room_ktx_release() {
        return this.transactionDispatcher;
    }

    public ia.f minusKey(ia.f.c cVar) {
        return ia.f.b.a.c(this, cVar);
    }

    public ia.f plus(ia.f fVar) {
        return ia.f.b.a.d(this, fVar);
    }

    public final void release() {
        if (this.referenceCount.decrementAndGet() < 0) {
            throw new IllegalStateException("Transaction was never started or was already released.");
        }
    }
}
