package androidx.room;

import androidx.lifecycle.LiveData;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class RoomTrackingLiveData<T> extends LiveData {
    private final Callable<T> computeFunction;
    private final AtomicBoolean computing;
    private final InvalidationLiveDataContainer container;
    private final RoomDatabase database;
    private final boolean inTransaction;
    private final AtomicBoolean invalid;
    private final Runnable invalidationRunnable;
    private final InvalidationTracker.Observer observer;
    private final Runnable refreshRunnable;
    private final AtomicBoolean registeredObserver;

    public RoomTrackingLiveData(RoomDatabase roomDatabase, InvalidationLiveDataContainer invalidationLiveDataContainer, boolean z, Callable<T> callable, final String[] strArr) {
        kotlin.jvm.internal.m.g(roomDatabase, "database");
        kotlin.jvm.internal.m.g(invalidationLiveDataContainer, "container");
        kotlin.jvm.internal.m.g(callable, "computeFunction");
        kotlin.jvm.internal.m.g(strArr, "tableNames");
        this.database = roomDatabase;
        this.container = invalidationLiveDataContainer;
        this.inTransaction = z;
        this.computeFunction = callable;
        this.observer = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.RoomTrackingLiveData$observer$1
            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(Set<String> set) {
                kotlin.jvm.internal.m.g(set, "tables");
                i.c.h().b(this.getInvalidationRunnable());
            }
        };
        this.invalid = new AtomicBoolean(true);
        this.computing = new AtomicBoolean(false);
        this.registeredObserver = new AtomicBoolean(false);
        this.refreshRunnable = new Runnable() { // from class: androidx.room.y
            @Override // java.lang.Runnable
            public final void run() {
                RoomTrackingLiveData.refreshRunnable$lambda$0(this.a);
            }
        };
        this.invalidationRunnable = new Runnable() { // from class: androidx.room.z
            @Override // java.lang.Runnable
            public final void run() {
                RoomTrackingLiveData.invalidationRunnable$lambda$1(this.a);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invalidationRunnable$lambda$1(RoomTrackingLiveData roomTrackingLiveData) {
        kotlin.jvm.internal.m.g(roomTrackingLiveData, "this$0");
        boolean zHasActiveObservers = roomTrackingLiveData.hasActiveObservers();
        if (roomTrackingLiveData.invalid.compareAndSet(false, true) && zHasActiveObservers) {
            roomTrackingLiveData.getQueryExecutor().execute(roomTrackingLiveData.refreshRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Bottom block not found for handler: all -> 0x0048 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void refreshRunnable$lambda$0(androidx.room.RoomTrackingLiveData r5) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.m.g(r5, r0)
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.registeredObserver
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r1, r2)
            if (r0 == 0) goto L1a
            androidx.room.RoomDatabase r0 = r5.database
            androidx.room.InvalidationTracker r0 = r0.getInvalidationTracker()
            androidx.room.InvalidationTracker$Observer r3 = r5.observer
            r0.addWeakObserver(r3)
        L1a:
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.computing
            boolean r0 = r0.compareAndSet(r1, r2)
            if (r0 == 0) goto L4f
            r0 = 0
            r3 = 0
        L24:
            java.util.concurrent.atomic.AtomicBoolean r4 = r5.invalid     // Catch: java.lang.Throwable -> L48
            boolean r4 = r4.compareAndSet(r2, r1)     // Catch: java.lang.Throwable -> L48
            if (r4 == 0) goto L3d
            java.util.concurrent.Callable<T> r0 = r5.computeFunction     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L48
            java.lang.Object r0 = r0.call()     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L48
            r3 = 1
            goto L24
        L34:
            r0 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L48
            java.lang.String r3 = "Exception while computing database live data."
            r2.<init>(r3, r0)     // Catch: java.lang.Throwable -> L48
            throw r2     // Catch: java.lang.Throwable -> L48
        L3d:
            if (r3 == 0) goto L42
            r5.postValue(r0)     // Catch: java.lang.Throwable -> L48
        L42:
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.computing
            r0.set(r1)
            goto L50
        L48:
            r0 = move-exception
            java.util.concurrent.atomic.AtomicBoolean r5 = r5.computing
            r5.set(r1)
            throw r0
        L4f:
            r3 = 0
        L50:
            if (r3 == 0) goto L5a
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.invalid
            boolean r0 = r0.get()
            if (r0 != 0) goto L1a
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomTrackingLiveData.refreshRunnable$lambda$0(androidx.room.RoomTrackingLiveData):void");
    }

    public final Callable<T> getComputeFunction() {
        return this.computeFunction;
    }

    public final AtomicBoolean getComputing() {
        return this.computing;
    }

    public final RoomDatabase getDatabase() {
        return this.database;
    }

    public final boolean getInTransaction() {
        return this.inTransaction;
    }

    public final AtomicBoolean getInvalid() {
        return this.invalid;
    }

    public final Runnable getInvalidationRunnable() {
        return this.invalidationRunnable;
    }

    public final InvalidationTracker.Observer getObserver() {
        return this.observer;
    }

    public final Executor getQueryExecutor() {
        return this.inTransaction ? this.database.getTransactionExecutor() : this.database.getQueryExecutor();
    }

    public final Runnable getRefreshRunnable() {
        return this.refreshRunnable;
    }

    public final AtomicBoolean getRegisteredObserver() {
        return this.registeredObserver;
    }

    @Override // androidx.lifecycle.LiveData
    protected void onActive() {
        super.onActive();
        InvalidationLiveDataContainer invalidationLiveDataContainer = this.container;
        kotlin.jvm.internal.m.e(this, "null cannot be cast to non-null type androidx.lifecycle.LiveData<kotlin.Any>");
        invalidationLiveDataContainer.onActive(this);
        getQueryExecutor().execute(this.refreshRunnable);
    }

    @Override // androidx.lifecycle.LiveData
    protected void onInactive() {
        super.onInactive();
        InvalidationLiveDataContainer invalidationLiveDataContainer = this.container;
        kotlin.jvm.internal.m.e(this, "null cannot be cast to non-null type androidx.lifecycle.LiveData<kotlin.Any>");
        invalidationLiveDataContainer.onInactive(this);
    }
}
