package androidx.room;

import java.util.Map;
import za.e0;
import za.j1;

/* JADX INFO: loaded from: classes.dex */
public final class CoroutinesRoomKt {
    public static final e0 getQueryDispatcher(RoomDatabase roomDatabase) {
        kotlin.jvm.internal.m.g(roomDatabase, "<this>");
        Map<String, Object> backingFieldMap = roomDatabase.getBackingFieldMap();
        Object objA = backingFieldMap.get("QueryDispatcher");
        if (objA == null) {
            objA = j1.a(roomDatabase.getQueryExecutor());
            backingFieldMap.put("QueryDispatcher", objA);
        }
        kotlin.jvm.internal.m.e(objA, "null cannot be cast to non-null type kotlinx.coroutines.CoroutineDispatcher");
        return (e0) objA;
    }

    public static final e0 getTransactionDispatcher(RoomDatabase roomDatabase) {
        kotlin.jvm.internal.m.g(roomDatabase, "<this>");
        Map<String, Object> backingFieldMap = roomDatabase.getBackingFieldMap();
        Object objA = backingFieldMap.get("TransactionDispatcher");
        if (objA == null) {
            objA = j1.a(roomDatabase.getTransactionExecutor());
            backingFieldMap.put("TransactionDispatcher", objA);
        }
        kotlin.jvm.internal.m.e(objA, "null cannot be cast to non-null type kotlinx.coroutines.CoroutineDispatcher");
        return (e0) objA;
    }
}
