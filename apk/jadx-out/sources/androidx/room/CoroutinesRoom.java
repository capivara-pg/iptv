package androidx.room;

import android.os.CancellationSignal;
import java.util.concurrent.Callable;
import kotlin.coroutines.Continuation;
import za.k0;
import za.k1;

/* JADX INFO: loaded from: classes.dex */
public final class CoroutinesRoom {
    public static final Companion Companion = new Companion(null);

    private CoroutinesRoom() {
    }

    public static final <R> kotlinx.coroutines.flow.b createFlow(RoomDatabase roomDatabase, boolean z, String[] strArr, Callable<R> callable) {
        return Companion.createFlow(roomDatabase, z, strArr, callable);
    }

    public static final <R> Object execute(RoomDatabase roomDatabase, boolean z, CancellationSignal cancellationSignal, Callable<R> callable, Continuation<? super R> continuation) {
        return Companion.execute(roomDatabase, z, cancellationSignal, callable, continuation);
    }

    public static final <R> Object execute(RoomDatabase roomDatabase, boolean z, Callable<R> callable, Continuation<? super R> continuation) {
        return Companion.execute(roomDatabase, z, callable, continuation);
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final <R> kotlinx.coroutines.flow.b createFlow(RoomDatabase roomDatabase, boolean z, String[] strArr, Callable<R> callable) {
            kotlin.jvm.internal.m.g(roomDatabase, "db");
            kotlin.jvm.internal.m.g(strArr, "tableNames");
            kotlin.jvm.internal.m.g(callable, "callable");
            return kotlinx.coroutines.flow.d.g(new CoroutinesRoom$Companion$createFlow$1(z, roomDatabase, strArr, callable, null));
        }

        public final <R> Object execute(RoomDatabase roomDatabase, boolean z, Callable<R> callable, Continuation<? super R> continuation) {
            ia.d transactionDispatcher;
            if (roomDatabase.isOpenInternal() && roomDatabase.inTransaction()) {
                return callable.call();
            }
            TransactionElement transactionElement = (TransactionElement) continuation.getContext().get(TransactionElement.Key);
            if (transactionElement == null || (transactionDispatcher = transactionElement.getTransactionDispatcher$room_ktx_release()) == null) {
                transactionDispatcher = z ? CoroutinesRoomKt.getTransactionDispatcher(roomDatabase) : CoroutinesRoomKt.getQueryDispatcher(roomDatabase);
            }
            return za.h.f(transactionDispatcher, new CoroutinesRoom$Companion$execute$2(callable, null), continuation);
        }

        public final <R> Object execute(RoomDatabase roomDatabase, boolean z, CancellationSignal cancellationSignal, Callable<R> callable, Continuation<? super R> continuation) {
            ia.d transactionDispatcher;
            if (roomDatabase.isOpenInternal() && roomDatabase.inTransaction()) {
                return callable.call();
            }
            TransactionElement transactionElement = (TransactionElement) continuation.getContext().get(TransactionElement.Key);
            if (transactionElement == null || (transactionDispatcher = transactionElement.getTransactionDispatcher$room_ktx_release()) == null) {
                transactionDispatcher = z ? CoroutinesRoomKt.getTransactionDispatcher(roomDatabase) : CoroutinesRoomKt.getQueryDispatcher(roomDatabase);
            }
            ia.d dVar = transactionDispatcher;
            za.o oVar = new za.o(ja.b.b(continuation), 1);
            oVar.z();
            oVar.q(new CoroutinesRoom$Companion$execute$4$1(cancellationSignal, za.h.d(k1.a, dVar, (k0) null, new CoroutinesRoom$Companion$execute$4$job$1(callable, oVar, null), 2, (Object) null)));
            Object objW = oVar.w();
            if (objW == ja.b.c()) {
                kotlin.coroutines.jvm.internal.h.c(continuation);
            }
            return objW;
        }
    }
}
