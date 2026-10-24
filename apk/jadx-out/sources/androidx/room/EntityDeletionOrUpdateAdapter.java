package androidx.room;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class EntityDeletionOrUpdateAdapter<T> extends SharedSQLiteStatement {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EntityDeletionOrUpdateAdapter(RoomDatabase roomDatabase) {
        super(roomDatabase);
        kotlin.jvm.internal.m.g(roomDatabase, "database");
    }

    protected abstract void bind(t0.l lVar, T t);

    @Override // androidx.room.SharedSQLiteStatement
    protected abstract String createQuery();

    public final int handle(T t) {
        t0.l lVarAcquire = acquire();
        try {
            bind(lVarAcquire, t);
            return lVarAcquire.executeUpdateDelete();
        } finally {
            release(lVarAcquire);
        }
    }

    public final int handleMultiple(Iterable<? extends T> iterable) {
        kotlin.jvm.internal.m.g(iterable, "entities");
        t0.l lVarAcquire = acquire();
        try {
            Iterator<? extends T> it = iterable.iterator();
            int iExecuteUpdateDelete = 0;
            while (it.hasNext()) {
                bind(lVarAcquire, it.next());
                iExecuteUpdateDelete += lVarAcquire.executeUpdateDelete();
            }
            release(lVarAcquire);
            return iExecuteUpdateDelete;
        } catch (Throwable th) {
            release(lVarAcquire);
            throw th;
        }
    }

    public final int handleMultiple(T[] tArr) {
        kotlin.jvm.internal.m.g(tArr, "entities");
        t0.l lVarAcquire = acquire();
        try {
            int iExecuteUpdateDelete = 0;
            for (T t : tArr) {
                bind(lVarAcquire, t);
                iExecuteUpdateDelete += lVarAcquire.executeUpdateDelete();
            }
            release(lVarAcquire);
            return iExecuteUpdateDelete;
        } catch (Throwable th) {
            release(lVarAcquire);
            throw th;
        }
    }
}
