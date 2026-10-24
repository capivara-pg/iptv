package androidx.room;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class QueryInterceptorOpenHelperFactory implements t0.i.c {
    private final t0.i.c delegate;
    private final RoomDatabase.QueryCallback queryCallback;
    private final Executor queryCallbackExecutor;

    public QueryInterceptorOpenHelperFactory(t0.i.c cVar, Executor executor, RoomDatabase.QueryCallback queryCallback) {
        kotlin.jvm.internal.m.g(cVar, "delegate");
        kotlin.jvm.internal.m.g(executor, "queryCallbackExecutor");
        kotlin.jvm.internal.m.g(queryCallback, "queryCallback");
        this.delegate = cVar;
        this.queryCallbackExecutor = executor;
        this.queryCallback = queryCallback;
    }

    public t0.i create(t0.i.b bVar) {
        kotlin.jvm.internal.m.g(bVar, "configuration");
        return new QueryInterceptorOpenHelper(this.delegate.create(bVar), this.queryCallbackExecutor, this.queryCallback);
    }
}
