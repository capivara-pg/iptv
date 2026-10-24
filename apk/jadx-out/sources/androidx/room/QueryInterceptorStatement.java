package androidx.room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class QueryInterceptorStatement implements t0.l {
    private final List<Object> bindArgsCache;
    private final t0.l delegate;
    private final RoomDatabase.QueryCallback queryCallback;
    private final Executor queryCallbackExecutor;
    private final String sqlStatement;

    public QueryInterceptorStatement(t0.l lVar, String str, Executor executor, RoomDatabase.QueryCallback queryCallback) {
        kotlin.jvm.internal.m.g(lVar, "delegate");
        kotlin.jvm.internal.m.g(str, "sqlStatement");
        kotlin.jvm.internal.m.g(executor, "queryCallbackExecutor");
        kotlin.jvm.internal.m.g(queryCallback, "queryCallback");
        this.delegate = lVar;
        this.sqlStatement = str;
        this.queryCallbackExecutor = executor;
        this.queryCallback = queryCallback;
        this.bindArgsCache = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$0(QueryInterceptorStatement queryInterceptorStatement) {
        kotlin.jvm.internal.m.g(queryInterceptorStatement, "this$0");
        queryInterceptorStatement.queryCallback.onQuery(queryInterceptorStatement.sqlStatement, queryInterceptorStatement.bindArgsCache);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeInsert$lambda$2(QueryInterceptorStatement queryInterceptorStatement) {
        kotlin.jvm.internal.m.g(queryInterceptorStatement, "this$0");
        queryInterceptorStatement.queryCallback.onQuery(queryInterceptorStatement.sqlStatement, queryInterceptorStatement.bindArgsCache);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeUpdateDelete$lambda$1(QueryInterceptorStatement queryInterceptorStatement) {
        kotlin.jvm.internal.m.g(queryInterceptorStatement, "this$0");
        queryInterceptorStatement.queryCallback.onQuery(queryInterceptorStatement.sqlStatement, queryInterceptorStatement.bindArgsCache);
    }

    private final void saveArgsToCache(int i, Object obj) {
        int i2 = i - 1;
        if (i2 >= this.bindArgsCache.size()) {
            int size = (i2 - this.bindArgsCache.size()) + 1;
            for (int i3 = 0; i3 < size; i3++) {
                this.bindArgsCache.add(null);
            }
        }
        this.bindArgsCache.set(i2, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void simpleQueryForLong$lambda$3(QueryInterceptorStatement queryInterceptorStatement) {
        kotlin.jvm.internal.m.g(queryInterceptorStatement, "this$0");
        queryInterceptorStatement.queryCallback.onQuery(queryInterceptorStatement.sqlStatement, queryInterceptorStatement.bindArgsCache);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void simpleQueryForString$lambda$4(QueryInterceptorStatement queryInterceptorStatement) {
        kotlin.jvm.internal.m.g(queryInterceptorStatement, "this$0");
        queryInterceptorStatement.queryCallback.onQuery(queryInterceptorStatement.sqlStatement, queryInterceptorStatement.bindArgsCache);
    }

    public void bindBlob(int i, byte[] bArr) {
        kotlin.jvm.internal.m.g(bArr, "value");
        saveArgsToCache(i, bArr);
        this.delegate.bindBlob(i, bArr);
    }

    public void bindDouble(int i, double d) {
        saveArgsToCache(i, Double.valueOf(d));
        this.delegate.bindDouble(i, d);
    }

    public void bindLong(int i, long j) {
        saveArgsToCache(i, Long.valueOf(j));
        this.delegate.bindLong(i, j);
    }

    public void bindNull(int i) {
        Object[] array = this.bindArgsCache.toArray(new Object[0]);
        kotlin.jvm.internal.m.e(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        saveArgsToCache(i, Arrays.copyOf(array, array.length));
        this.delegate.bindNull(i);
    }

    public void bindString(int i, String str) {
        kotlin.jvm.internal.m.g(str, "value");
        saveArgsToCache(i, str);
        this.delegate.bindString(i, str);
    }

    public void clearBindings() {
        this.bindArgsCache.clear();
        this.delegate.clearBindings();
    }

    public void close() throws IOException {
        this.delegate.close();
    }

    public void execute() {
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.u
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.execute$lambda$0(this.a);
            }
        });
        this.delegate.execute();
    }

    public long executeInsert() {
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.v
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.executeInsert$lambda$2(this.a);
            }
        });
        return this.delegate.executeInsert();
    }

    public int executeUpdateDelete() {
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.t
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.executeUpdateDelete$lambda$1(this.a);
            }
        });
        return this.delegate.executeUpdateDelete();
    }

    public long simpleQueryForLong() {
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.x
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.simpleQueryForLong$lambda$3(this.a);
            }
        });
        return this.delegate.simpleQueryForLong();
    }

    public String simpleQueryForString() {
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.w
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorStatement.simpleQueryForString$lambda$4(this.a);
            }
        });
        return this.delegate.simpleQueryForString();
    }
}
