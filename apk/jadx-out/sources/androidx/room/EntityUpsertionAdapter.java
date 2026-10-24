package androidx.room;

import android.database.sqlite.SQLiteConstraintException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class EntityUpsertionAdapter<T> {
    private final EntityInsertionAdapter<T> insertionAdapter;
    private final EntityDeletionOrUpdateAdapter<T> updateAdapter;

    public EntityUpsertionAdapter(EntityInsertionAdapter<T> entityInsertionAdapter, EntityDeletionOrUpdateAdapter<T> entityDeletionOrUpdateAdapter) {
        kotlin.jvm.internal.m.g(entityInsertionAdapter, "insertionAdapter");
        kotlin.jvm.internal.m.g(entityDeletionOrUpdateAdapter, "updateAdapter");
        this.insertionAdapter = entityInsertionAdapter;
        this.updateAdapter = entityDeletionOrUpdateAdapter;
    }

    private final void checkUniquenessException(SQLiteConstraintException sQLiteConstraintException) {
        String message = sQLiteConstraintException.getMessage();
        if (message == null || !ya.l.w(message, "1555", true)) {
            throw sQLiteConstraintException;
        }
    }

    public final void upsert(T t) {
        try {
            this.insertionAdapter.insert(t);
        } catch (SQLiteConstraintException e) {
            checkUniquenessException(e);
            this.updateAdapter.handle(t);
        }
    }

    public final long upsertAndReturnId(T t) {
        try {
            return this.insertionAdapter.insertAndReturnId(t);
        } catch (SQLiteConstraintException e) {
            checkUniquenessException(e);
            this.updateAdapter.handle(t);
            return -1L;
        }
    }

    public final long[] upsertAndReturnIdsArray(T[] tArr) {
        long jInsertAndReturnId;
        kotlin.jvm.internal.m.g(tArr, "entities");
        int length = tArr.length;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            try {
                jInsertAndReturnId = this.insertionAdapter.insertAndReturnId(tArr[i]);
            } catch (SQLiteConstraintException e) {
                checkUniquenessException(e);
                this.updateAdapter.handle(tArr[i]);
                jInsertAndReturnId = -1;
            }
            jArr[i] = jInsertAndReturnId;
        }
        return jArr;
    }

    public final Long[] upsertAndReturnIdsArrayBox(T[] tArr) {
        long jInsertAndReturnId;
        kotlin.jvm.internal.m.g(tArr, "entities");
        int length = tArr.length;
        Long[] lArr = new Long[length];
        for (int i = 0; i < length; i++) {
            try {
                jInsertAndReturnId = this.insertionAdapter.insertAndReturnId(tArr[i]);
            } catch (SQLiteConstraintException e) {
                checkUniquenessException(e);
                this.updateAdapter.handle(tArr[i]);
                jInsertAndReturnId = -1;
            }
            lArr[i] = Long.valueOf(jInsertAndReturnId);
        }
        return lArr;
    }

    public final List<Long> upsertAndReturnIdsList(T[] tArr) {
        kotlin.jvm.internal.m.g(tArr, "entities");
        List listC = fa.p.c();
        for (T t : tArr) {
            try {
                listC.add(Long.valueOf(this.insertionAdapter.insertAndReturnId(t)));
            } catch (SQLiteConstraintException e) {
                checkUniquenessException(e);
                this.updateAdapter.handle(t);
                listC.add(-1L);
            }
        }
        return fa.p.a(listC);
    }

    public final void upsert(T[] tArr) {
        kotlin.jvm.internal.m.g(tArr, "entities");
        for (T t : tArr) {
            try {
                this.insertionAdapter.insert(t);
            } catch (SQLiteConstraintException e) {
                checkUniquenessException(e);
                this.updateAdapter.handle(t);
            }
        }
    }

    public final long[] upsertAndReturnIdsArray(Collection<? extends T> collection) {
        long jInsertAndReturnId;
        kotlin.jvm.internal.m.g(collection, "entities");
        Iterator<? extends T> it = collection.iterator();
        int size = collection.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            T next = it.next();
            try {
                jInsertAndReturnId = this.insertionAdapter.insertAndReturnId(next);
            } catch (SQLiteConstraintException e) {
                checkUniquenessException(e);
                this.updateAdapter.handle(next);
                jInsertAndReturnId = -1;
            }
            jArr[i] = jInsertAndReturnId;
        }
        return jArr;
    }

    public final Long[] upsertAndReturnIdsArrayBox(Collection<? extends T> collection) {
        long jInsertAndReturnId;
        kotlin.jvm.internal.m.g(collection, "entities");
        Iterator<? extends T> it = collection.iterator();
        int size = collection.size();
        Long[] lArr = new Long[size];
        for (int i = 0; i < size; i++) {
            T next = it.next();
            try {
                jInsertAndReturnId = this.insertionAdapter.insertAndReturnId(next);
            } catch (SQLiteConstraintException e) {
                checkUniquenessException(e);
                this.updateAdapter.handle(next);
                jInsertAndReturnId = -1;
            }
            lArr[i] = Long.valueOf(jInsertAndReturnId);
        }
        return lArr;
    }

    public final void upsert(Iterable<? extends T> iterable) {
        kotlin.jvm.internal.m.g(iterable, "entities");
        for (T t : iterable) {
            try {
                this.insertionAdapter.insert(t);
            } catch (SQLiteConstraintException e) {
                checkUniquenessException(e);
                this.updateAdapter.handle(t);
            }
        }
    }

    public final List<Long> upsertAndReturnIdsList(Collection<? extends T> collection) {
        kotlin.jvm.internal.m.g(collection, "entities");
        List listC = fa.p.c();
        for (T t : collection) {
            try {
                listC.add(Long.valueOf(this.insertionAdapter.insertAndReturnId(t)));
            } catch (SQLiteConstraintException e) {
                checkUniquenessException(e);
                this.updateAdapter.handle(t);
                listC.add(-1L);
            }
        }
        return fa.p.a(listC);
    }
}
