package androidx.room;

import java.util.concurrent.RejectedExecutionException;
import kotlin.coroutines.Continuation;
import za.i0;
import za.p2;

/* JADX INFO: loaded from: classes.dex */
public final class RoomDatabaseKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final ia.f createTransactionContext(RoomDatabase roomDatabase, ia.d dVar) {
        TransactionElement transactionElement = new TransactionElement(dVar);
        return dVar.plus(transactionElement).plus(p2.a(roomDatabase.getSuspendingTransactionId(), Integer.valueOf(System.identityHashCode(transactionElement))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <R> Object startTransactionCoroutine(final RoomDatabase roomDatabase, final ia.f fVar, final pa.p pVar, Continuation<? super R> continuation) {
        final za.o oVar = new za.o(ja.b.b(continuation), 1);
        oVar.z();
        try {
            roomDatabase.getTransactionExecutor().execute(new Runnable() { // from class: androidx.room.RoomDatabaseKt$startTransactionCoroutine$2$1

                /* JADX INFO: renamed from: androidx.room.RoomDatabaseKt$startTransactionCoroutine$2$1$1, reason: invalid class name */
                @kotlin.coroutines.jvm.internal.f(c = "androidx.room.RoomDatabaseKt$startTransactionCoroutine$2$1$1", f = "RoomDatabaseExt.kt", l = {97}, m = "invokeSuspend")
                static final class AnonymousClass1 extends kotlin.coroutines.jvm.internal.k implements pa.p {
                    final /* synthetic */ za.n $continuation;
                    final /* synthetic */ RoomDatabase $this_startTransactionCoroutine;
                    final /* synthetic */ pa.p $transactionBlock;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(RoomDatabase roomDatabase, za.n nVar, pa.p pVar, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$this_startTransactionCoroutine = roomDatabase;
                        this.$continuation = nVar;
                        this.$transactionBlock = pVar;
                    }

                    public final Continuation<ea.v> create(Object obj, Continuation<?> continuation) {
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_startTransactionCoroutine, this.$continuation, this.$transactionBlock, continuation);
                        anonymousClass1.L$0 = obj;
                        return anonymousClass1;
                    }

                    public final Object invokeSuspend(Object obj) {
                        Continuation continuation;
                        Object objC = ja.b.c();
                        int i = this.label;
                        if (i == 0) {
                            ea.o.b(obj);
                            ia.d dVar = ((i0) this.L$0).w().get(ia.d.R);
                            kotlin.jvm.internal.m.d(dVar);
                            ia.f fVarCreateTransactionContext = RoomDatabaseKt.createTransactionContext(this.$this_startTransactionCoroutine, dVar);
                            Continuation continuation2 = this.$continuation;
                            pa.p pVar = this.$transactionBlock;
                            this.L$0 = continuation2;
                            this.label = 1;
                            obj = za.h.f(fVarCreateTransactionContext, pVar, this);
                            if (obj == objC) {
                                return objC;
                            }
                            continuation = continuation2;
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            continuation = (Continuation) this.L$0;
                            ea.o.b(obj);
                        }
                        continuation.resumeWith(ea.n.a(obj));
                        return ea.v.a;
                    }

                    public final Object invoke(i0 i0Var, Continuation<? super ea.v> continuation) {
                        return create(i0Var, continuation).invokeSuspend(ea.v.a);
                    }
                }

                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        za.h.e(fVar.minusKey(ia.d.R), new AnonymousClass1(roomDatabase, oVar, pVar, null));
                    } catch (Throwable th) {
                        oVar.k(th);
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            oVar.k(new IllegalStateException("Unable to acquire a thread to perform the database transaction.", e));
        }
        Object objW = oVar.w();
        if (objW == ja.b.c()) {
            kotlin.coroutines.jvm.internal.h.c(continuation);
        }
        return objW;
    }

    public static final <R> Object withTransaction(RoomDatabase roomDatabase, pa.l lVar, Continuation<? super R> continuation) {
        RoomDatabaseKt$withTransaction$transactionBlock$1 roomDatabaseKt$withTransaction$transactionBlock$1 = new RoomDatabaseKt$withTransaction$transactionBlock$1(roomDatabase, lVar, null);
        TransactionElement transactionElement = (TransactionElement) continuation.getContext().get(TransactionElement.Key);
        ia.d transactionDispatcher$room_ktx_release = transactionElement != null ? transactionElement.getTransactionDispatcher$room_ktx_release() : null;
        return transactionDispatcher$room_ktx_release != null ? za.h.f(transactionDispatcher$room_ktx_release, roomDatabaseKt$withTransaction$transactionBlock$1, continuation) : startTransactionCoroutine(roomDatabase, continuation.getContext(), roomDatabaseKt$withTransaction$transactionBlock$1, continuation);
    }
}
