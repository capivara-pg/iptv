package androidx.room;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.util.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class QueryInterceptorDatabase implements t0.h {
    private final t0.h delegate;
    private final RoomDatabase.QueryCallback queryCallback;
    private final Executor queryCallbackExecutor;

    public QueryInterceptorDatabase(t0.h hVar, Executor executor, RoomDatabase.QueryCallback queryCallback) {
        kotlin.jvm.internal.m.g(hVar, "delegate");
        kotlin.jvm.internal.m.g(executor, "queryCallbackExecutor");
        kotlin.jvm.internal.m.g(queryCallback, "queryCallback");
        this.delegate = hVar;
        this.queryCallbackExecutor = executor;
        this.queryCallback = queryCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void beginTransaction$lambda$0(QueryInterceptorDatabase queryInterceptorDatabase) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        queryInterceptorDatabase.queryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", fa.p.h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void beginTransactionNonExclusive$lambda$1(QueryInterceptorDatabase queryInterceptorDatabase) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        queryInterceptorDatabase.queryCallback.onQuery("BEGIN DEFERRED TRANSACTION", fa.p.h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void beginTransactionWithListener$lambda$2(QueryInterceptorDatabase queryInterceptorDatabase) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        queryInterceptorDatabase.queryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", fa.p.h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void beginTransactionWithListenerNonExclusive$lambda$3(QueryInterceptorDatabase queryInterceptorDatabase) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        queryInterceptorDatabase.queryCallback.onQuery("BEGIN DEFERRED TRANSACTION", fa.p.h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void endTransaction$lambda$4(QueryInterceptorDatabase queryInterceptorDatabase) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        queryInterceptorDatabase.queryCallback.onQuery("END TRANSACTION", fa.p.h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execSQL$lambda$10(QueryInterceptorDatabase queryInterceptorDatabase, String str) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        kotlin.jvm.internal.m.g(str, "$sql");
        queryInterceptorDatabase.queryCallback.onQuery(str, fa.p.h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execSQL$lambda$11(QueryInterceptorDatabase queryInterceptorDatabase, String str, List list) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        kotlin.jvm.internal.m.g(str, "$sql");
        kotlin.jvm.internal.m.g(list, "$inputArguments");
        queryInterceptorDatabase.queryCallback.onQuery(str, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void query$lambda$6(QueryInterceptorDatabase queryInterceptorDatabase, String str) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        kotlin.jvm.internal.m.g(str, "$query");
        queryInterceptorDatabase.queryCallback.onQuery(str, fa.p.h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void query$lambda$7(QueryInterceptorDatabase queryInterceptorDatabase, String str, Object[] objArr) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        kotlin.jvm.internal.m.g(str, "$query");
        kotlin.jvm.internal.m.g(objArr, "$bindArgs");
        queryInterceptorDatabase.queryCallback.onQuery(str, fa.i.H(objArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void query$lambda$8(QueryInterceptorDatabase queryInterceptorDatabase, t0.k kVar, QueryInterceptorProgram queryInterceptorProgram) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        kotlin.jvm.internal.m.g(kVar, "$query");
        kotlin.jvm.internal.m.g(queryInterceptorProgram, "$queryInterceptorProgram");
        queryInterceptorDatabase.queryCallback.onQuery(kVar.getSql(), queryInterceptorProgram.getBindArgsCache$room_runtime_release());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void query$lambda$9(QueryInterceptorDatabase queryInterceptorDatabase, t0.k kVar, QueryInterceptorProgram queryInterceptorProgram) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        kotlin.jvm.internal.m.g(kVar, "$query");
        kotlin.jvm.internal.m.g(queryInterceptorProgram, "$queryInterceptorProgram");
        queryInterceptorDatabase.queryCallback.onQuery(kVar.getSql(), queryInterceptorProgram.getBindArgsCache$room_runtime_release());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setTransactionSuccessful$lambda$5(QueryInterceptorDatabase queryInterceptorDatabase) {
        kotlin.jvm.internal.m.g(queryInterceptorDatabase, "this$0");
        queryInterceptorDatabase.queryCallback.onQuery("TRANSACTION SUCCESSFUL", fa.p.h());
    }

    public void beginTransaction() {
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.o
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.beginTransaction$lambda$0(this.a);
            }
        });
        this.delegate.beginTransaction();
    }

    public void beginTransactionNonExclusive() {
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.g
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.beginTransactionNonExclusive$lambda$1(this.a);
            }
        });
        this.delegate.beginTransactionNonExclusive();
    }

    public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
        kotlin.jvm.internal.m.g(sQLiteTransactionListener, "transactionListener");
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.i
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.beginTransactionWithListener$lambda$2(this.a);
            }
        });
        this.delegate.beginTransactionWithListener(sQLiteTransactionListener);
    }

    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
        kotlin.jvm.internal.m.g(sQLiteTransactionListener, "transactionListener");
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.m
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.beginTransactionWithListenerNonExclusive$lambda$3(this.a);
            }
        });
        this.delegate.beginTransactionWithListenerNonExclusive(sQLiteTransactionListener);
    }

    public void close() throws IOException {
        this.delegate.close();
    }

    public t0.l compileStatement(String str) {
        kotlin.jvm.internal.m.g(str, "sql");
        return new QueryInterceptorStatement(this.delegate.compileStatement(str), str, this.queryCallbackExecutor, this.queryCallback);
    }

    public int delete(String str, String str2, Object[] objArr) {
        kotlin.jvm.internal.m.g(str, "table");
        return this.delegate.delete(str, str2, objArr);
    }

    public void disableWriteAheadLogging() {
        this.delegate.disableWriteAheadLogging();
    }

    public boolean enableWriteAheadLogging() {
        return this.delegate.enableWriteAheadLogging();
    }

    public void endTransaction() {
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.q
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.endTransaction$lambda$4(this.a);
            }
        });
        this.delegate.endTransaction();
    }

    public void execPerConnectionSQL(String str, Object[] objArr) {
        kotlin.jvm.internal.m.g(str, "sql");
        this.delegate.execPerConnectionSQL(str, objArr);
    }

    public void execSQL(final String str) {
        kotlin.jvm.internal.m.g(str, "sql");
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.h
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.execSQL$lambda$10(this.a, str);
            }
        });
        this.delegate.execSQL(str);
    }

    public List<Pair<String, String>> getAttachedDbs() {
        return this.delegate.getAttachedDbs();
    }

    public long getMaximumSize() {
        return this.delegate.getMaximumSize();
    }

    public long getPageSize() {
        return this.delegate.getPageSize();
    }

    public String getPath() {
        return this.delegate.getPath();
    }

    public int getVersion() {
        return this.delegate.getVersion();
    }

    public boolean inTransaction() {
        return this.delegate.inTransaction();
    }

    public long insert(String str, int i, ContentValues contentValues) {
        kotlin.jvm.internal.m.g(str, "table");
        kotlin.jvm.internal.m.g(contentValues, "values");
        return this.delegate.insert(str, i, contentValues);
    }

    public boolean isDatabaseIntegrityOk() {
        return this.delegate.isDatabaseIntegrityOk();
    }

    public boolean isDbLockedByCurrentThread() {
        return this.delegate.isDbLockedByCurrentThread();
    }

    public boolean isExecPerConnectionSQLSupported() {
        return this.delegate.isExecPerConnectionSQLSupported();
    }

    public boolean isOpen() {
        return this.delegate.isOpen();
    }

    public boolean isReadOnly() {
        return this.delegate.isReadOnly();
    }

    public boolean isWriteAheadLoggingEnabled() {
        return this.delegate.isWriteAheadLoggingEnabled();
    }

    public boolean needUpgrade(int i) {
        return this.delegate.needUpgrade(i);
    }

    public Cursor query(final String str) {
        kotlin.jvm.internal.m.g(str, "query");
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.p
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.query$lambda$6(this.a, str);
            }
        });
        return this.delegate.query(str);
    }

    public void setForeignKeyConstraintsEnabled(boolean z) {
        this.delegate.setForeignKeyConstraintsEnabled(z);
    }

    public void setLocale(Locale locale) {
        kotlin.jvm.internal.m.g(locale, "locale");
        this.delegate.setLocale(locale);
    }

    public void setMaxSqlCacheSize(int i) {
        this.delegate.setMaxSqlCacheSize(i);
    }

    public long setMaximumSize(long j) {
        return this.delegate.setMaximumSize(j);
    }

    public void setPageSize(long j) {
        this.delegate.setPageSize(j);
    }

    public void setTransactionSuccessful() {
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.l
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.setTransactionSuccessful$lambda$5(this.a);
            }
        });
        this.delegate.setTransactionSuccessful();
    }

    public void setVersion(int i) {
        this.delegate.setVersion(i);
    }

    public int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        kotlin.jvm.internal.m.g(str, "table");
        kotlin.jvm.internal.m.g(contentValues, "values");
        return this.delegate.update(str, i, contentValues, str2, objArr);
    }

    public boolean yieldIfContendedSafely() {
        return this.delegate.yieldIfContendedSafely();
    }

    public boolean yieldIfContendedSafely(long j) {
        return this.delegate.yieldIfContendedSafely(j);
    }

    public void execSQL(final String str, Object[] objArr) {
        kotlin.jvm.internal.m.g(str, "sql");
        kotlin.jvm.internal.m.g(objArr, "bindArgs");
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(fa.p.e(objArr));
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.s
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.execSQL$lambda$11(this.a, str, arrayList);
            }
        });
        this.delegate.execSQL(str, new List[]{arrayList});
    }

    public Cursor query(final String str, final Object[] objArr) {
        kotlin.jvm.internal.m.g(str, "query");
        kotlin.jvm.internal.m.g(objArr, "bindArgs");
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.k
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.query$lambda$7(this.a, str, objArr);
            }
        });
        return this.delegate.query(str, objArr);
    }

    public Cursor query(final t0.k kVar) {
        kotlin.jvm.internal.m.g(kVar, "query");
        final QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        kVar.bindTo(queryInterceptorProgram);
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.n
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.query$lambda$8(this.a, kVar, queryInterceptorProgram);
            }
        });
        return this.delegate.query(kVar);
    }

    public Cursor query(final t0.k kVar, CancellationSignal cancellationSignal) {
        kotlin.jvm.internal.m.g(kVar, "query");
        final QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        kVar.bindTo(queryInterceptorProgram);
        this.queryCallbackExecutor.execute(new Runnable() { // from class: androidx.room.j
            @Override // java.lang.Runnable
            public final void run() {
                QueryInterceptorDatabase.query$lambda$9(this.a, kVar, queryInterceptorProgram);
            }
        });
        return this.delegate.query(kVar);
    }
}
