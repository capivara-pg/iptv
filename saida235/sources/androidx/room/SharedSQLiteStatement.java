package androidx.room;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class SharedSQLiteStatement {
    private final RoomDatabase database;
    private final AtomicBoolean lock;
    private final ea.g stmt$delegate;

    public SharedSQLiteStatement(RoomDatabase roomDatabase) {
        kotlin.jvm.internal.m.g(roomDatabase, "database");
        this.database = roomDatabase;
        this.lock = new AtomicBoolean(false);
        this.stmt$delegate = ea.h.b(new SharedSQLiteStatement$stmt$2(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final t0.l createNewStatement() {
        return this.database.compileStatement(createQuery());
    }

    private final t0.l getStmt() {
        return (t0.l) this.stmt$delegate.getValue();
    }

    public t0.l acquire() {
        assertNotMainThread();
        return getStmt(this.lock.compareAndSet(false, true));
    }

    protected void assertNotMainThread() {
        this.database.assertNotMainThread();
    }

    protected abstract String createQuery();

    public void release(t0.l lVar) {
        kotlin.jvm.internal.m.g(lVar, "statement");
        if (lVar == getStmt()) {
            this.lock.set(false);
        }
    }

    private final t0.l getStmt(boolean z) {
        return z ? getStmt() : createNewStatement();
    }
}
