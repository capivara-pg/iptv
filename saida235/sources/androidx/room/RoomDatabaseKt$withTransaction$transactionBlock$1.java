package androidx.room;

import kotlin.coroutines.Continuation;
import za.i0;

/* JADX INFO: loaded from: classes.dex */
@kotlin.coroutines.jvm.internal.f(c = "androidx.room.RoomDatabaseKt$withTransaction$transactionBlock$1", f = "RoomDatabaseExt.kt", l = {56}, m = "invokeSuspend")
final class RoomDatabaseKt$withTransaction$transactionBlock$1 extends kotlin.coroutines.jvm.internal.k implements pa.p {
    final /* synthetic */ pa.l $block;
    final /* synthetic */ RoomDatabase $this_withTransaction;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RoomDatabaseKt$withTransaction$transactionBlock$1(RoomDatabase roomDatabase, pa.l lVar, Continuation<? super RoomDatabaseKt$withTransaction$transactionBlock$1> continuation) {
        super(2, continuation);
        this.$this_withTransaction = roomDatabase;
        this.$block = lVar;
    }

    public final Continuation<ea.v> create(Object obj, Continuation<?> continuation) {
        RoomDatabaseKt$withTransaction$transactionBlock$1 roomDatabaseKt$withTransaction$transactionBlock$1 = new RoomDatabaseKt$withTransaction$transactionBlock$1(this.$this_withTransaction, this.$block, continuation);
        roomDatabaseKt$withTransaction$transactionBlock$1.L$0 = obj;
        return roomDatabaseKt$withTransaction$transactionBlock$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v3 */
    public final Object invokeSuspend(Object obj) throws Throwable {
        Throwable th;
        TransactionElement transactionElement;
        TransactionElement transactionElementC = ja.b.c();
        int i = this.label;
        try {
            if (i == 0) {
                ea.o.b(obj);
                ia.f.b bVar = ((i0) this.L$0).w().get(TransactionElement.Key);
                kotlin.jvm.internal.m.d(bVar);
                TransactionElement transactionElement2 = (TransactionElement) bVar;
                transactionElement2.acquire();
                try {
                    this.$this_withTransaction.beginTransaction();
                    try {
                        pa.l lVar = this.$block;
                        this.L$0 = transactionElement2;
                        this.label = 1;
                        Object objInvoke = lVar.invoke(this);
                        if (objInvoke == transactionElementC) {
                            return transactionElementC;
                        }
                        transactionElement = transactionElement2;
                        obj = objInvoke;
                    } catch (Throwable th2) {
                        th = th2;
                        this.$this_withTransaction.endTransaction();
                        throw th;
                    }
                } catch (Throwable th3) {
                    transactionElementC = transactionElement2;
                    th = th3;
                    transactionElementC.release();
                    throw th;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                transactionElement = (TransactionElement) this.L$0;
                try {
                    ea.o.b(obj);
                } catch (Throwable th4) {
                    th = th4;
                    this.$this_withTransaction.endTransaction();
                    throw th;
                }
            }
            this.$this_withTransaction.setTransactionSuccessful();
            this.$this_withTransaction.endTransaction();
            transactionElement.release();
            return obj;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    public final Object invoke(i0 i0Var, Continuation<? super R> continuation) {
        return create(i0Var, continuation).invokeSuspend(ea.v.a);
    }
}
