package androidx.room;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class QueryInterceptorOpenHelper implements t0.i, DelegatingOpenHelper {
    private final t0.i delegate;
    private final RoomDatabase.QueryCallback queryCallback;
    private final Executor queryCallbackExecutor;

    public QueryInterceptorOpenHelper(t0.i iVar, Executor executor, RoomDatabase.QueryCallback queryCallback) {
        kotlin.jvm.internal.m.g(iVar, "delegate");
        kotlin.jvm.internal.m.g(executor, "queryCallbackExecutor");
        kotlin.jvm.internal.m.g(queryCallback, "queryCallback");
        this.delegate = iVar;
        this.queryCallbackExecutor = executor;
        this.queryCallback = queryCallback;
    }

    public void close() {
        this.delegate.close();
    }

    public String getDatabaseName() {
        return this.delegate.getDatabaseName();
    }

    @Override // androidx.room.DelegatingOpenHelper
    public t0.i getDelegate() {
        return this.delegate;
    }

    public t0.h getReadableDatabase() {
        return new QueryInterceptorDatabase(getDelegate().getReadableDatabase(), this.queryCallbackExecutor, this.queryCallback);
    }

    public t0.h getWritableDatabase() {
        return new QueryInterceptorDatabase(getDelegate().getWritableDatabase(), this.queryCallbackExecutor, this.queryCallback);
    }

    public void setWriteAheadLoggingEnabled(boolean z) {
        this.delegate.setWriteAheadLoggingEnabled(z);
    }
}
