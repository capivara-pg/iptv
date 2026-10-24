package androidx.room;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class EntityInsertionAdapter<T> extends SharedSQLiteStatement {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EntityInsertionAdapter(RoomDatabase roomDatabase) {
        super(roomDatabase);
        kotlin.jvm.internal.m.g(roomDatabase, "database");
    }

    protected abstract void bind(t0.l lVar, T t);

    public final void insert(T t) {
        t0.l lVarAcquire = acquire();
        try {
            bind(lVarAcquire, t);
            lVarAcquire.executeInsert();
        } finally {
            release(lVarAcquire);
        }
    }

    public final long insertAndReturnId(T t) {
        t0.l lVarAcquire = acquire();
        try {
            bind(lVarAcquire, t);
            return lVarAcquire.executeInsert();
        } finally {
            release(lVarAcquire);
        }
    }

    public final long[] insertAndReturnIdsArray(Collection<? extends T> collection) {
        kotlin.jvm.internal.m.g(collection, "entities");
        t0.l lVarAcquire = acquire();
        try {
            long[] jArr = new long[collection.size()];
            int i = 0;
            for (T t : collection) {
                int i2 = i + 1;
                if (i < 0) {
                    fa.p.p();
                }
                bind(lVarAcquire, t);
                jArr[i] = lVarAcquire.executeInsert();
                i = i2;
            }
            return jArr;
        } finally {
            release(lVarAcquire);
        }
    }

    public final Long[] insertAndReturnIdsArrayBox(Collection<? extends T> collection) {
        kotlin.jvm.internal.m.g(collection, "entities");
        t0.l lVarAcquire = acquire();
        Iterator<? extends T> it = collection.iterator();
        try {
            int size = collection.size();
            Long[] lArr = new Long[size];
            for (int i = 0; i < size; i++) {
                bind(lVarAcquire, it.next());
                lArr[i] = Long.valueOf(lVarAcquire.executeInsert());
            }
            release(lVarAcquire);
            return lArr;
        } catch (Throwable th) {
            release(lVarAcquire);
            throw th;
        }
    }

    public final List<Long> insertAndReturnIdsList(T[] tArr) {
        kotlin.jvm.internal.m.g(tArr, "entities");
        t0.l lVarAcquire = acquire();
        try {
            List listC = fa.p.c();
            for (T t : tArr) {
                bind(lVarAcquire, t);
                listC.add(Long.valueOf(lVarAcquire.executeInsert()));
            }
            return fa.p.a(listC);
        } finally {
            release(lVarAcquire);
        }
    }

    public final void insert(T[] tArr) {
        kotlin.jvm.internal.m.g(tArr, "entities");
        t0.l lVarAcquire = acquire();
        try {
            for (T t : tArr) {
                bind(lVarAcquire, t);
                lVarAcquire.executeInsert();
            }
            release(lVarAcquire);
        } catch (Throwable th) {
            release(lVarAcquire);
            throw th;
        }
    }

    public final long[] insertAndReturnIdsArray(T[] tArr) {
        kotlin.jvm.internal.m.g(tArr, "entities");
        t0.l lVarAcquire = acquire();
        try {
            long[] jArr = new long[tArr.length];
            int length = tArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                bind(lVarAcquire, tArr[i]);
                jArr[i2] = lVarAcquire.executeInsert();
                i++;
                i2 = i3;
            }
            release(lVarAcquire);
            return jArr;
        } catch (Throwable th) {
            release(lVarAcquire);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Long[] insertAndReturnIdsArrayBox(T[] tArr) {
        kotlin.jvm.internal.m.g(tArr, "entities");
        t0.l lVarAcquire = acquire();
        Iterator itA = kotlin.jvm.internal.b.a(tArr);
        try {
            int length = tArr.length;
            Long[] lArr = new Long[length];
            for (int i = 0; i < length; i++) {
                bind(lVarAcquire, itA.next());
                lArr[i] = Long.valueOf(lVarAcquire.executeInsert());
            }
            release(lVarAcquire);
            return lArr;
        } catch (Throwable th) {
            release(lVarAcquire);
            throw th;
        }
    }

    public final List<Long> insertAndReturnIdsList(Collection<? extends T> collection) {
        kotlin.jvm.internal.m.g(collection, "entities");
        t0.l lVarAcquire = acquire();
        try {
            List listC = fa.p.c();
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                bind(lVarAcquire, it.next());
                listC.add(Long.valueOf(lVarAcquire.executeInsert()));
            }
            return fa.p.a(listC);
        } finally {
            release(lVarAcquire);
        }
    }

    public final void insert(Iterable<? extends T> iterable) {
        kotlin.jvm.internal.m.g(iterable, "entities");
        t0.l lVarAcquire = acquire();
        try {
            Iterator<? extends T> it = iterable.iterator();
            while (it.hasNext()) {
                bind(lVarAcquire, it.next());
                lVarAcquire.executeInsert();
            }
            release(lVarAcquire);
        } catch (Throwable th) {
            release(lVarAcquire);
            throw th;
        }
    }
}
