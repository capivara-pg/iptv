package androidx.work.impl.model;

import a1.s;
import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class b implements WorkSpecDao {
    private final RoomDatabase a;
    private final EntityInsertionAdapter b;
    private final SharedSQLiteStatement c;
    private final SharedSQLiteStatement d;
    private final SharedSQLiteStatement e;
    private final SharedSQLiteStatement f;
    private final SharedSQLiteStatement g;
    private final SharedSQLiteStatement h;
    private final SharedSQLiteStatement i;
    private final SharedSQLiteStatement j;

    class a implements Callable {
        final /* synthetic */ RoomSQLiteQuery a;

        a(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List call() {
            b.this.a.beginTransaction();
            try {
                Cursor cursorQuery = DBUtil.query(b.this.a, this.a, false, null);
                try {
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(cursorQuery.getString(0));
                    }
                    b.this.a.setTransactionSuccessful();
                    cursorQuery.close();
                    b.this.a.endTransaction();
                    return arrayList;
                } catch (Throwable th) {
                    cursorQuery.close();
                    throw th;
                }
            } catch (Throwable th2) {
                b.this.a.endTransaction();
                throw th2;
            }
        }

        protected void finalize() {
            this.a.release();
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.model.b$b, reason: collision with other inner class name */
    class CallableC0064b implements Callable {
        final /* synthetic */ RoomSQLiteQuery a;

        CallableC0064b(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List call() {
            b.this.a.beginTransaction();
            try {
                Cursor cursorQuery = DBUtil.query(b.this.a, this.a, true, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                    androidx.collection.a aVar = new androidx.collection.a();
                    androidx.collection.a aVar2 = new androidx.collection.a();
                    while (cursorQuery.moveToNext()) {
                        if (!cursorQuery.isNull(columnIndexOrThrow)) {
                            String string = cursorQuery.getString(columnIndexOrThrow);
                            if (((ArrayList) aVar.get(string)) == null) {
                                aVar.put(string, new ArrayList());
                            }
                        }
                        if (!cursorQuery.isNull(columnIndexOrThrow)) {
                            String string2 = cursorQuery.getString(columnIndexOrThrow);
                            if (((ArrayList) aVar2.get(string2)) == null) {
                                aVar2.put(string2, new ArrayList());
                            }
                        }
                    }
                    cursorQuery.moveToPosition(-1);
                    b.this.b(aVar);
                    b.this.a(aVar2);
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        ArrayList arrayList2 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar2.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        WorkSpec.c cVar = new WorkSpec.c();
                        cVar.a = cursorQuery.getString(columnIndexOrThrow);
                        cVar.b = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow2));
                        cVar.c = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow3));
                        cVar.d = cursorQuery.getInt(columnIndexOrThrow4);
                        cVar.e = arrayList2;
                        cVar.f = arrayList3;
                        arrayList.add(cVar);
                    }
                    b.this.a.setTransactionSuccessful();
                    cursorQuery.close();
                    b.this.a.endTransaction();
                    return arrayList;
                } catch (Throwable th) {
                    cursorQuery.close();
                    throw th;
                }
            } catch (Throwable th2) {
                b.this.a.endTransaction();
                throw th2;
            }
        }

        protected void finalize() {
            this.a.release();
        }
    }

    class c implements Callable {
        final /* synthetic */ RoomSQLiteQuery a;

        c(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List call() {
            b.this.a.beginTransaction();
            try {
                Cursor cursorQuery = DBUtil.query(b.this.a, this.a, true, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                    androidx.collection.a aVar = new androidx.collection.a();
                    androidx.collection.a aVar2 = new androidx.collection.a();
                    while (cursorQuery.moveToNext()) {
                        if (!cursorQuery.isNull(columnIndexOrThrow)) {
                            String string = cursorQuery.getString(columnIndexOrThrow);
                            if (((ArrayList) aVar.get(string)) == null) {
                                aVar.put(string, new ArrayList());
                            }
                        }
                        if (!cursorQuery.isNull(columnIndexOrThrow)) {
                            String string2 = cursorQuery.getString(columnIndexOrThrow);
                            if (((ArrayList) aVar2.get(string2)) == null) {
                                aVar2.put(string2, new ArrayList());
                            }
                        }
                    }
                    cursorQuery.moveToPosition(-1);
                    b.this.b(aVar);
                    b.this.a(aVar2);
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        ArrayList arrayList2 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar2.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        WorkSpec.c cVar = new WorkSpec.c();
                        cVar.a = cursorQuery.getString(columnIndexOrThrow);
                        cVar.b = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow2));
                        cVar.c = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow3));
                        cVar.d = cursorQuery.getInt(columnIndexOrThrow4);
                        cVar.e = arrayList2;
                        cVar.f = arrayList3;
                        arrayList.add(cVar);
                    }
                    b.this.a.setTransactionSuccessful();
                    cursorQuery.close();
                    b.this.a.endTransaction();
                    return arrayList;
                } catch (Throwable th) {
                    cursorQuery.close();
                    throw th;
                }
            } catch (Throwable th2) {
                b.this.a.endTransaction();
                throw th2;
            }
        }

        protected void finalize() {
            this.a.release();
        }
    }

    class d implements Callable {
        final /* synthetic */ RoomSQLiteQuery a;

        d(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List call() {
            b.this.a.beginTransaction();
            try {
                Cursor cursorQuery = DBUtil.query(b.this.a, this.a, true, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                    androidx.collection.a aVar = new androidx.collection.a();
                    androidx.collection.a aVar2 = new androidx.collection.a();
                    while (cursorQuery.moveToNext()) {
                        if (!cursorQuery.isNull(columnIndexOrThrow)) {
                            String string = cursorQuery.getString(columnIndexOrThrow);
                            if (((ArrayList) aVar.get(string)) == null) {
                                aVar.put(string, new ArrayList());
                            }
                        }
                        if (!cursorQuery.isNull(columnIndexOrThrow)) {
                            String string2 = cursorQuery.getString(columnIndexOrThrow);
                            if (((ArrayList) aVar2.get(string2)) == null) {
                                aVar2.put(string2, new ArrayList());
                            }
                        }
                    }
                    cursorQuery.moveToPosition(-1);
                    b.this.b(aVar);
                    b.this.a(aVar2);
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        ArrayList arrayList2 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar2.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        WorkSpec.c cVar = new WorkSpec.c();
                        cVar.a = cursorQuery.getString(columnIndexOrThrow);
                        cVar.b = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow2));
                        cVar.c = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow3));
                        cVar.d = cursorQuery.getInt(columnIndexOrThrow4);
                        cVar.e = arrayList2;
                        cVar.f = arrayList3;
                        arrayList.add(cVar);
                    }
                    b.this.a.setTransactionSuccessful();
                    cursorQuery.close();
                    b.this.a.endTransaction();
                    return arrayList;
                } catch (Throwable th) {
                    cursorQuery.close();
                    throw th;
                }
            } catch (Throwable th2) {
                b.this.a.endTransaction();
                throw th2;
            }
        }

        protected void finalize() {
            this.a.release();
        }
    }

    class e implements Callable {
        final /* synthetic */ RoomSQLiteQuery a;

        e(RoomSQLiteQuery roomSQLiteQuery) {
            this.a = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Long call() {
            Long lValueOf = null;
            Cursor cursorQuery = DBUtil.query(b.this.a, this.a, false, null);
            try {
                if (cursorQuery.moveToFirst() && !cursorQuery.isNull(0)) {
                    lValueOf = Long.valueOf(cursorQuery.getLong(0));
                }
                return lValueOf;
            } finally {
                cursorQuery.close();
            }
        }

        protected void finalize() {
            this.a.release();
        }
    }

    class f extends EntityInsertionAdapter {
        f(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void bind(t0.l lVar, WorkSpec workSpec) throws Throwable {
            String str = workSpec.id;
            if (str == null) {
                lVar.bindNull(1);
            } else {
                lVar.bindString(1, str);
            }
            lVar.bindLong(2, h1.g.stateToInt(workSpec.state));
            String str2 = workSpec.workerClassName;
            if (str2 == null) {
                lVar.bindNull(3);
            } else {
                lVar.bindString(3, str2);
            }
            String str3 = workSpec.inputMergerClassName;
            if (str3 == null) {
                lVar.bindNull(4);
            } else {
                lVar.bindString(4, str3);
            }
            byte[] byteArrayInternal = androidx.work.b.toByteArrayInternal(workSpec.input);
            if (byteArrayInternal == null) {
                lVar.bindNull(5);
            } else {
                lVar.bindBlob(5, byteArrayInternal);
            }
            byte[] byteArrayInternal2 = androidx.work.b.toByteArrayInternal(workSpec.output);
            if (byteArrayInternal2 == null) {
                lVar.bindNull(6);
            } else {
                lVar.bindBlob(6, byteArrayInternal2);
            }
            lVar.bindLong(7, workSpec.initialDelay);
            lVar.bindLong(8, workSpec.intervalDuration);
            lVar.bindLong(9, workSpec.flexDuration);
            lVar.bindLong(10, workSpec.runAttemptCount);
            lVar.bindLong(11, h1.g.backoffPolicyToInt(workSpec.backoffPolicy));
            lVar.bindLong(12, workSpec.backoffDelayDuration);
            lVar.bindLong(13, workSpec.periodStartTime);
            lVar.bindLong(14, workSpec.minimumRetentionDuration);
            lVar.bindLong(15, workSpec.scheduleRequestedAt);
            lVar.bindLong(16, workSpec.expedited ? 1L : 0L);
            lVar.bindLong(17, h1.g.outOfQuotaPolicyToInt(workSpec.outOfQuotaPolicy));
            a1.b bVar = workSpec.constraints;
            if (bVar == null) {
                lVar.bindNull(18);
                lVar.bindNull(19);
                lVar.bindNull(20);
                lVar.bindNull(21);
                lVar.bindNull(22);
                lVar.bindNull(23);
                lVar.bindNull(24);
                lVar.bindNull(25);
                return;
            }
            lVar.bindLong(18, h1.g.networkTypeToInt(bVar.b()));
            lVar.bindLong(19, bVar.g() ? 1L : 0L);
            lVar.bindLong(20, bVar.h() ? 1L : 0L);
            lVar.bindLong(21, bVar.f() ? 1L : 0L);
            lVar.bindLong(22, bVar.i() ? 1L : 0L);
            lVar.bindLong(23, bVar.c());
            lVar.bindLong(24, bVar.d());
            byte[] bArrContentUriTriggersToByteArray = h1.g.contentUriTriggersToByteArray(bVar.a());
            if (bArrContentUriTriggersToByteArray == null) {
                lVar.bindNull(25);
            } else {
                lVar.bindBlob(25, bArrContentUriTriggersToByteArray);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`period_start_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    class g extends SharedSQLiteStatement {
        g(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM workspec WHERE id=?";
        }
    }

    class h extends SharedSQLiteStatement {
        h(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE workspec SET output=? WHERE id=?";
        }
    }

    class i extends SharedSQLiteStatement {
        i(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE workspec SET period_start_time=? WHERE id=?";
        }
    }

    class j extends SharedSQLiteStatement {
        j(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
        }
    }

    class k extends SharedSQLiteStatement {
        k(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
        }
    }

    class l extends SharedSQLiteStatement {
        l(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
        }
    }

    class m extends SharedSQLiteStatement {
        m(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
        }
    }

    class n extends SharedSQLiteStatement {
        n(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
        }
    }

    public b(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new f(roomDatabase);
        this.c = new g(roomDatabase);
        this.d = new h(roomDatabase);
        this.e = new i(roomDatabase);
        this.f = new j(roomDatabase);
        this.g = new k(roomDatabase);
        this.h = new l(roomDatabase);
        this.i = new m(roomDatabase);
        this.j = new n(roomDatabase);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(androidx.collection.a aVar) {
        ArrayList arrayList;
        int i2;
        Set<Object> setKeySet = aVar.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (aVar.size() > 999) {
            androidx.collection.a aVar2 = new androidx.collection.a(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = aVar.size();
            int i3 = 0;
            loop0: while (true) {
                i2 = 0;
                do {
                    if (i3 >= size) {
                        break loop0;
                    }
                    aVar2.put((String) aVar.keyAt(i3), (ArrayList) aVar.valueAt(i3));
                    i3++;
                    i2++;
                } while (i2 != 999);
                a(aVar2);
                aVar2 = new androidx.collection.a(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            }
            if (i2 > 0) {
                a(aVar2);
                return;
            }
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int size2 = setKeySet.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size2);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size2 + 0);
        Iterator<Object> it = setKeySet.iterator();
        int i4 = 1;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i4);
            } else {
                roomSQLiteQueryAcquire.bindString(i4, str);
            }
            i4++;
        }
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "work_spec_id");
            if (columnIndex == -1) {
                cursorQuery.close();
                return;
            }
            while (cursorQuery.moveToNext()) {
                if (!cursorQuery.isNull(columnIndex) && (arrayList = (ArrayList) aVar.get(cursorQuery.getString(columnIndex))) != null) {
                    arrayList.add(androidx.work.b.fromByteArray(cursorQuery.getBlob(0)));
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(androidx.collection.a aVar) {
        ArrayList arrayList;
        int i2;
        Set<Object> setKeySet = aVar.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (aVar.size() > 999) {
            androidx.collection.a aVar2 = new androidx.collection.a(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = aVar.size();
            int i3 = 0;
            loop0: while (true) {
                i2 = 0;
                do {
                    if (i3 >= size) {
                        break loop0;
                    }
                    aVar2.put((String) aVar.keyAt(i3), (ArrayList) aVar.valueAt(i3));
                    i3++;
                    i2++;
                } while (i2 != 999);
                b(aVar2);
                aVar2 = new androidx.collection.a(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            }
            if (i2 > 0) {
                b(aVar2);
                return;
            }
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int size2 = setKeySet.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size2);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size2 + 0);
        Iterator<Object> it = setKeySet.iterator();
        int i4 = 1;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i4);
            } else {
                roomSQLiteQueryAcquire.bindString(i4, str);
            }
            i4++;
        }
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "work_spec_id");
            if (columnIndex == -1) {
                cursorQuery.close();
                return;
            }
            while (cursorQuery.moveToNext()) {
                if (!cursorQuery.isNull(columnIndex) && (arrayList = (ArrayList) aVar.get(cursorQuery.getString(columnIndex))) != null) {
                    arrayList.add(cursorQuery.getString(0));
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void delete(String str) {
        this.a.assertNotSuspendingTransaction();
        t0.l lVarAcquire = this.c.acquire();
        if (str == null) {
            lVarAcquire.bindNull(1);
        } else {
            lVarAcquire.bindString(1, str);
        }
        this.a.beginTransaction();
        try {
            lVarAcquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
            this.c.release(lVarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getAllEligibleWorkSpecsForScheduling(int i2) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 ORDER BY period_start_time LIMIT ?", 1);
        roomSQLiteQueryAcquire.bindLong(1, i2);
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "required_network_type");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_charging");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_device_idle");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_battery_not_low");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_storage_not_low");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_content_update_delay");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_max_content_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_uri_triggers");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "worker_class_name");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input_merger_class_name");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "initial_delay");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "interval_duration");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "flex_duration");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_policy");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_delay_duration");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "period_start_time");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "minimum_retention_duration");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "schedule_requested_at");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_in_foreground");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "out_of_quota_policy");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(columnIndexOrThrow9);
                    int i4 = columnIndexOrThrow9;
                    String string2 = cursorQuery.getString(columnIndexOrThrow11);
                    int i5 = columnIndexOrThrow11;
                    a1.b bVar = new a1.b();
                    int i6 = columnIndexOrThrow;
                    bVar.k(h1.g.intToNetworkType(cursorQuery.getInt(columnIndexOrThrow)));
                    bVar.m(cursorQuery.getInt(columnIndexOrThrow2) != 0);
                    bVar.n(cursorQuery.getInt(columnIndexOrThrow3) != 0);
                    bVar.l(cursorQuery.getInt(columnIndexOrThrow4) != 0);
                    bVar.o(cursorQuery.getInt(columnIndexOrThrow5) != 0);
                    int i7 = columnIndexOrThrow2;
                    int i8 = columnIndexOrThrow3;
                    bVar.p(cursorQuery.getLong(columnIndexOrThrow6));
                    bVar.q(cursorQuery.getLong(columnIndexOrThrow7));
                    bVar.j(h1.g.byteArrayToContentUriTriggers(cursorQuery.getBlob(columnIndexOrThrow8)));
                    WorkSpec workSpec = new WorkSpec(string, string2);
                    workSpec.state = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow10));
                    workSpec.inputMergerClassName = cursorQuery.getString(columnIndexOrThrow12);
                    workSpec.input = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow13));
                    int i9 = i3;
                    workSpec.output = androidx.work.b.fromByteArray(cursorQuery.getBlob(i9));
                    i3 = i9;
                    int i10 = columnIndexOrThrow15;
                    workSpec.initialDelay = cursorQuery.getLong(i10);
                    int i11 = columnIndexOrThrow12;
                    int i12 = columnIndexOrThrow16;
                    workSpec.intervalDuration = cursorQuery.getLong(i12);
                    int i13 = columnIndexOrThrow4;
                    int i14 = columnIndexOrThrow17;
                    workSpec.flexDuration = cursorQuery.getLong(i14);
                    int i15 = columnIndexOrThrow18;
                    workSpec.runAttemptCount = cursorQuery.getInt(i15);
                    int i16 = columnIndexOrThrow19;
                    workSpec.backoffPolicy = h1.g.intToBackoffPolicy(cursorQuery.getInt(i16));
                    columnIndexOrThrow17 = i14;
                    int i17 = columnIndexOrThrow20;
                    workSpec.backoffDelayDuration = cursorQuery.getLong(i17);
                    int i18 = columnIndexOrThrow21;
                    workSpec.periodStartTime = cursorQuery.getLong(i18);
                    columnIndexOrThrow21 = i18;
                    int i19 = columnIndexOrThrow22;
                    workSpec.minimumRetentionDuration = cursorQuery.getLong(i19);
                    int i20 = columnIndexOrThrow23;
                    workSpec.scheduleRequestedAt = cursorQuery.getLong(i20);
                    int i21 = columnIndexOrThrow24;
                    workSpec.expedited = cursorQuery.getInt(i21) != 0;
                    int i22 = columnIndexOrThrow25;
                    workSpec.outOfQuotaPolicy = h1.g.intToOutOfQuotaPolicy(cursorQuery.getInt(i22));
                    workSpec.constraints = bVar;
                    arrayList.add(workSpec);
                    columnIndexOrThrow25 = i22;
                    columnIndexOrThrow2 = i7;
                    columnIndexOrThrow12 = i11;
                    columnIndexOrThrow15 = i10;
                    columnIndexOrThrow16 = i12;
                    columnIndexOrThrow18 = i15;
                    columnIndexOrThrow23 = i20;
                    columnIndexOrThrow9 = i4;
                    columnIndexOrThrow11 = i5;
                    columnIndexOrThrow = i6;
                    columnIndexOrThrow24 = i21;
                    columnIndexOrThrow22 = i19;
                    columnIndexOrThrow3 = i8;
                    columnIndexOrThrow20 = i17;
                    columnIndexOrThrow4 = i13;
                    columnIndexOrThrow19 = i16;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getAllUnfinishedWork() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)", 0);
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(cursorQuery.getString(0));
            }
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            return arrayList;
        } catch (Throwable th) {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getAllWorkSpecIds() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec", 0);
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(cursorQuery.getString(0));
            }
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            return arrayList;
        } catch (Throwable th) {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData getAllWorkSpecIdsLiveData() {
        return this.a.getInvalidationTracker().createLiveData(new String[]{"workspec"}, true, new a(RoomSQLiteQuery.acquire("SELECT id FROM workspec", 0)));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getEligibleWorkForScheduling(int i2) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY period_start_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))", 1);
        roomSQLiteQueryAcquire.bindLong(1, i2);
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "required_network_type");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_charging");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_device_idle");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_battery_not_low");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_storage_not_low");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_content_update_delay");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_max_content_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_uri_triggers");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "worker_class_name");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input_merger_class_name");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "initial_delay");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "interval_duration");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "flex_duration");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_policy");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_delay_duration");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "period_start_time");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "minimum_retention_duration");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "schedule_requested_at");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_in_foreground");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "out_of_quota_policy");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(columnIndexOrThrow9);
                    int i4 = columnIndexOrThrow9;
                    String string2 = cursorQuery.getString(columnIndexOrThrow11);
                    int i5 = columnIndexOrThrow11;
                    a1.b bVar = new a1.b();
                    int i6 = columnIndexOrThrow;
                    bVar.k(h1.g.intToNetworkType(cursorQuery.getInt(columnIndexOrThrow)));
                    bVar.m(cursorQuery.getInt(columnIndexOrThrow2) != 0);
                    bVar.n(cursorQuery.getInt(columnIndexOrThrow3) != 0);
                    bVar.l(cursorQuery.getInt(columnIndexOrThrow4) != 0);
                    bVar.o(cursorQuery.getInt(columnIndexOrThrow5) != 0);
                    int i7 = columnIndexOrThrow2;
                    int i8 = columnIndexOrThrow3;
                    bVar.p(cursorQuery.getLong(columnIndexOrThrow6));
                    bVar.q(cursorQuery.getLong(columnIndexOrThrow7));
                    bVar.j(h1.g.byteArrayToContentUriTriggers(cursorQuery.getBlob(columnIndexOrThrow8)));
                    WorkSpec workSpec = new WorkSpec(string, string2);
                    workSpec.state = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow10));
                    workSpec.inputMergerClassName = cursorQuery.getString(columnIndexOrThrow12);
                    workSpec.input = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow13));
                    int i9 = i3;
                    workSpec.output = androidx.work.b.fromByteArray(cursorQuery.getBlob(i9));
                    i3 = i9;
                    int i10 = columnIndexOrThrow15;
                    workSpec.initialDelay = cursorQuery.getLong(i10);
                    int i11 = columnIndexOrThrow12;
                    int i12 = columnIndexOrThrow16;
                    workSpec.intervalDuration = cursorQuery.getLong(i12);
                    int i13 = columnIndexOrThrow4;
                    int i14 = columnIndexOrThrow17;
                    workSpec.flexDuration = cursorQuery.getLong(i14);
                    int i15 = columnIndexOrThrow18;
                    workSpec.runAttemptCount = cursorQuery.getInt(i15);
                    int i16 = columnIndexOrThrow19;
                    workSpec.backoffPolicy = h1.g.intToBackoffPolicy(cursorQuery.getInt(i16));
                    columnIndexOrThrow17 = i14;
                    int i17 = columnIndexOrThrow20;
                    workSpec.backoffDelayDuration = cursorQuery.getLong(i17);
                    int i18 = columnIndexOrThrow21;
                    workSpec.periodStartTime = cursorQuery.getLong(i18);
                    columnIndexOrThrow21 = i18;
                    int i19 = columnIndexOrThrow22;
                    workSpec.minimumRetentionDuration = cursorQuery.getLong(i19);
                    int i20 = columnIndexOrThrow23;
                    workSpec.scheduleRequestedAt = cursorQuery.getLong(i20);
                    int i21 = columnIndexOrThrow24;
                    workSpec.expedited = cursorQuery.getInt(i21) != 0;
                    int i22 = columnIndexOrThrow25;
                    workSpec.outOfQuotaPolicy = h1.g.intToOutOfQuotaPolicy(cursorQuery.getInt(i22));
                    workSpec.constraints = bVar;
                    arrayList.add(workSpec);
                    columnIndexOrThrow25 = i22;
                    columnIndexOrThrow2 = i7;
                    columnIndexOrThrow12 = i11;
                    columnIndexOrThrow15 = i10;
                    columnIndexOrThrow16 = i12;
                    columnIndexOrThrow18 = i15;
                    columnIndexOrThrow23 = i20;
                    columnIndexOrThrow9 = i4;
                    columnIndexOrThrow11 = i5;
                    columnIndexOrThrow = i6;
                    columnIndexOrThrow24 = i21;
                    columnIndexOrThrow22 = i19;
                    columnIndexOrThrow3 = i8;
                    columnIndexOrThrow20 = i17;
                    columnIndexOrThrow4 = i13;
                    columnIndexOrThrow19 = i16;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getInputsFromPrerequisites(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT output FROM workspec WHERE id IN (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(androidx.work.b.fromByteArray(cursorQuery.getBlob(0)));
            }
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            return arrayList;
        } catch (Throwable th) {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getRecentlyCompletedWork(long j2) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE period_start_time >= ? AND state IN (2, 3, 5) ORDER BY period_start_time DESC", 1);
        roomSQLiteQueryAcquire.bindLong(1, j2);
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "required_network_type");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_charging");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_device_idle");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_battery_not_low");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_storage_not_low");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_content_update_delay");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_max_content_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_uri_triggers");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "worker_class_name");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input_merger_class_name");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "initial_delay");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "interval_duration");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "flex_duration");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_policy");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_delay_duration");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "period_start_time");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "minimum_retention_duration");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "schedule_requested_at");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_in_foreground");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "out_of_quota_policy");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(columnIndexOrThrow9);
                    int i3 = columnIndexOrThrow9;
                    String string2 = cursorQuery.getString(columnIndexOrThrow11);
                    int i4 = columnIndexOrThrow11;
                    a1.b bVar = new a1.b();
                    int i5 = columnIndexOrThrow;
                    bVar.k(h1.g.intToNetworkType(cursorQuery.getInt(columnIndexOrThrow)));
                    bVar.m(cursorQuery.getInt(columnIndexOrThrow2) != 0);
                    bVar.n(cursorQuery.getInt(columnIndexOrThrow3) != 0);
                    bVar.l(cursorQuery.getInt(columnIndexOrThrow4) != 0);
                    bVar.o(cursorQuery.getInt(columnIndexOrThrow5) != 0);
                    int i6 = columnIndexOrThrow2;
                    int i7 = columnIndexOrThrow3;
                    bVar.p(cursorQuery.getLong(columnIndexOrThrow6));
                    bVar.q(cursorQuery.getLong(columnIndexOrThrow7));
                    bVar.j(h1.g.byteArrayToContentUriTriggers(cursorQuery.getBlob(columnIndexOrThrow8)));
                    WorkSpec workSpec = new WorkSpec(string, string2);
                    workSpec.state = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow10));
                    workSpec.inputMergerClassName = cursorQuery.getString(columnIndexOrThrow12);
                    workSpec.input = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow13));
                    int i8 = i2;
                    workSpec.output = androidx.work.b.fromByteArray(cursorQuery.getBlob(i8));
                    int i9 = columnIndexOrThrow15;
                    i2 = i8;
                    workSpec.initialDelay = cursorQuery.getLong(i9);
                    int i10 = columnIndexOrThrow12;
                    int i11 = columnIndexOrThrow16;
                    workSpec.intervalDuration = cursorQuery.getLong(i11);
                    int i12 = columnIndexOrThrow4;
                    int i13 = columnIndexOrThrow17;
                    workSpec.flexDuration = cursorQuery.getLong(i13);
                    int i14 = columnIndexOrThrow18;
                    workSpec.runAttemptCount = cursorQuery.getInt(i14);
                    int i15 = columnIndexOrThrow19;
                    workSpec.backoffPolicy = h1.g.intToBackoffPolicy(cursorQuery.getInt(i15));
                    columnIndexOrThrow17 = i13;
                    int i16 = columnIndexOrThrow20;
                    workSpec.backoffDelayDuration = cursorQuery.getLong(i16);
                    int i17 = columnIndexOrThrow21;
                    workSpec.periodStartTime = cursorQuery.getLong(i17);
                    columnIndexOrThrow21 = i17;
                    int i18 = columnIndexOrThrow22;
                    workSpec.minimumRetentionDuration = cursorQuery.getLong(i18);
                    int i19 = columnIndexOrThrow23;
                    workSpec.scheduleRequestedAt = cursorQuery.getLong(i19);
                    int i20 = columnIndexOrThrow24;
                    workSpec.expedited = cursorQuery.getInt(i20) != 0;
                    int i21 = columnIndexOrThrow25;
                    workSpec.outOfQuotaPolicy = h1.g.intToOutOfQuotaPolicy(cursorQuery.getInt(i21));
                    workSpec.constraints = bVar;
                    arrayList.add(workSpec);
                    columnIndexOrThrow2 = i6;
                    columnIndexOrThrow25 = i21;
                    columnIndexOrThrow12 = i10;
                    columnIndexOrThrow15 = i9;
                    columnIndexOrThrow16 = i11;
                    columnIndexOrThrow18 = i14;
                    columnIndexOrThrow23 = i19;
                    columnIndexOrThrow9 = i3;
                    columnIndexOrThrow11 = i4;
                    columnIndexOrThrow = i5;
                    columnIndexOrThrow24 = i20;
                    columnIndexOrThrow22 = i18;
                    columnIndexOrThrow3 = i7;
                    columnIndexOrThrow20 = i16;
                    columnIndexOrThrow4 = i12;
                    columnIndexOrThrow19 = i15;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getRunningWork() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=1", 0);
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "required_network_type");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_charging");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_device_idle");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_battery_not_low");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_storage_not_low");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_content_update_delay");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_max_content_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_uri_triggers");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "worker_class_name");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input_merger_class_name");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "initial_delay");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "interval_duration");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "flex_duration");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_policy");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_delay_duration");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "period_start_time");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "minimum_retention_duration");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "schedule_requested_at");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_in_foreground");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "out_of_quota_policy");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(columnIndexOrThrow9);
                    int i3 = columnIndexOrThrow9;
                    String string2 = cursorQuery.getString(columnIndexOrThrow11);
                    int i4 = columnIndexOrThrow11;
                    a1.b bVar = new a1.b();
                    int i5 = columnIndexOrThrow;
                    bVar.k(h1.g.intToNetworkType(cursorQuery.getInt(columnIndexOrThrow)));
                    bVar.m(cursorQuery.getInt(columnIndexOrThrow2) != 0);
                    bVar.n(cursorQuery.getInt(columnIndexOrThrow3) != 0);
                    bVar.l(cursorQuery.getInt(columnIndexOrThrow4) != 0);
                    bVar.o(cursorQuery.getInt(columnIndexOrThrow5) != 0);
                    int i6 = columnIndexOrThrow2;
                    int i7 = columnIndexOrThrow3;
                    bVar.p(cursorQuery.getLong(columnIndexOrThrow6));
                    bVar.q(cursorQuery.getLong(columnIndexOrThrow7));
                    bVar.j(h1.g.byteArrayToContentUriTriggers(cursorQuery.getBlob(columnIndexOrThrow8)));
                    WorkSpec workSpec = new WorkSpec(string, string2);
                    workSpec.state = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow10));
                    workSpec.inputMergerClassName = cursorQuery.getString(columnIndexOrThrow12);
                    workSpec.input = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow13));
                    int i8 = i2;
                    workSpec.output = androidx.work.b.fromByteArray(cursorQuery.getBlob(i8));
                    i2 = i8;
                    int i9 = columnIndexOrThrow15;
                    workSpec.initialDelay = cursorQuery.getLong(i9);
                    int i10 = columnIndexOrThrow13;
                    int i11 = columnIndexOrThrow16;
                    workSpec.intervalDuration = cursorQuery.getLong(i11);
                    int i12 = columnIndexOrThrow4;
                    int i13 = columnIndexOrThrow17;
                    workSpec.flexDuration = cursorQuery.getLong(i13);
                    int i14 = columnIndexOrThrow18;
                    workSpec.runAttemptCount = cursorQuery.getInt(i14);
                    int i15 = columnIndexOrThrow19;
                    workSpec.backoffPolicy = h1.g.intToBackoffPolicy(cursorQuery.getInt(i15));
                    columnIndexOrThrow17 = i13;
                    int i16 = columnIndexOrThrow20;
                    workSpec.backoffDelayDuration = cursorQuery.getLong(i16);
                    int i17 = columnIndexOrThrow21;
                    workSpec.periodStartTime = cursorQuery.getLong(i17);
                    columnIndexOrThrow21 = i17;
                    int i18 = columnIndexOrThrow22;
                    workSpec.minimumRetentionDuration = cursorQuery.getLong(i18);
                    int i19 = columnIndexOrThrow23;
                    workSpec.scheduleRequestedAt = cursorQuery.getLong(i19);
                    int i20 = columnIndexOrThrow24;
                    workSpec.expedited = cursorQuery.getInt(i20) != 0;
                    int i21 = columnIndexOrThrow25;
                    workSpec.outOfQuotaPolicy = h1.g.intToOutOfQuotaPolicy(cursorQuery.getInt(i21));
                    workSpec.constraints = bVar;
                    arrayList.add(workSpec);
                    columnIndexOrThrow25 = i21;
                    columnIndexOrThrow2 = i6;
                    columnIndexOrThrow13 = i10;
                    columnIndexOrThrow15 = i9;
                    columnIndexOrThrow16 = i11;
                    columnIndexOrThrow18 = i14;
                    columnIndexOrThrow23 = i19;
                    columnIndexOrThrow9 = i3;
                    columnIndexOrThrow11 = i4;
                    columnIndexOrThrow = i5;
                    columnIndexOrThrow24 = i20;
                    columnIndexOrThrow22 = i18;
                    columnIndexOrThrow3 = i7;
                    columnIndexOrThrow20 = i16;
                    columnIndexOrThrow4 = i12;
                    columnIndexOrThrow19 = i15;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData getScheduleRequestedAtLiveData(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT schedule_requested_at FROM workspec WHERE id=?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        return this.a.getInvalidationTracker().createLiveData(new String[]{"workspec"}, false, new e(roomSQLiteQueryAcquire));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getScheduledWork() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 AND schedule_requested_at<>-1", 0);
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "required_network_type");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_charging");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_device_idle");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_battery_not_low");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_storage_not_low");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_content_update_delay");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_max_content_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_uri_triggers");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "worker_class_name");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input_merger_class_name");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "initial_delay");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "interval_duration");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "flex_duration");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_policy");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_delay_duration");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "period_start_time");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "minimum_retention_duration");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "schedule_requested_at");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_in_foreground");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "out_of_quota_policy");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(columnIndexOrThrow9);
                    int i3 = columnIndexOrThrow9;
                    String string2 = cursorQuery.getString(columnIndexOrThrow11);
                    int i4 = columnIndexOrThrow11;
                    a1.b bVar = new a1.b();
                    int i5 = columnIndexOrThrow;
                    bVar.k(h1.g.intToNetworkType(cursorQuery.getInt(columnIndexOrThrow)));
                    bVar.m(cursorQuery.getInt(columnIndexOrThrow2) != 0);
                    bVar.n(cursorQuery.getInt(columnIndexOrThrow3) != 0);
                    bVar.l(cursorQuery.getInt(columnIndexOrThrow4) != 0);
                    bVar.o(cursorQuery.getInt(columnIndexOrThrow5) != 0);
                    int i6 = columnIndexOrThrow2;
                    int i7 = columnIndexOrThrow3;
                    bVar.p(cursorQuery.getLong(columnIndexOrThrow6));
                    bVar.q(cursorQuery.getLong(columnIndexOrThrow7));
                    bVar.j(h1.g.byteArrayToContentUriTriggers(cursorQuery.getBlob(columnIndexOrThrow8)));
                    WorkSpec workSpec = new WorkSpec(string, string2);
                    workSpec.state = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow10));
                    workSpec.inputMergerClassName = cursorQuery.getString(columnIndexOrThrow12);
                    workSpec.input = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow13));
                    int i8 = i2;
                    workSpec.output = androidx.work.b.fromByteArray(cursorQuery.getBlob(i8));
                    i2 = i8;
                    int i9 = columnIndexOrThrow15;
                    workSpec.initialDelay = cursorQuery.getLong(i9);
                    int i10 = columnIndexOrThrow13;
                    int i11 = columnIndexOrThrow16;
                    workSpec.intervalDuration = cursorQuery.getLong(i11);
                    int i12 = columnIndexOrThrow4;
                    int i13 = columnIndexOrThrow17;
                    workSpec.flexDuration = cursorQuery.getLong(i13);
                    int i14 = columnIndexOrThrow18;
                    workSpec.runAttemptCount = cursorQuery.getInt(i14);
                    int i15 = columnIndexOrThrow19;
                    workSpec.backoffPolicy = h1.g.intToBackoffPolicy(cursorQuery.getInt(i15));
                    columnIndexOrThrow17 = i13;
                    int i16 = columnIndexOrThrow20;
                    workSpec.backoffDelayDuration = cursorQuery.getLong(i16);
                    int i17 = columnIndexOrThrow21;
                    workSpec.periodStartTime = cursorQuery.getLong(i17);
                    columnIndexOrThrow21 = i17;
                    int i18 = columnIndexOrThrow22;
                    workSpec.minimumRetentionDuration = cursorQuery.getLong(i18);
                    int i19 = columnIndexOrThrow23;
                    workSpec.scheduleRequestedAt = cursorQuery.getLong(i19);
                    int i20 = columnIndexOrThrow24;
                    workSpec.expedited = cursorQuery.getInt(i20) != 0;
                    int i21 = columnIndexOrThrow25;
                    workSpec.outOfQuotaPolicy = h1.g.intToOutOfQuotaPolicy(cursorQuery.getInt(i21));
                    workSpec.constraints = bVar;
                    arrayList.add(workSpec);
                    columnIndexOrThrow25 = i21;
                    columnIndexOrThrow2 = i6;
                    columnIndexOrThrow13 = i10;
                    columnIndexOrThrow15 = i9;
                    columnIndexOrThrow16 = i11;
                    columnIndexOrThrow18 = i14;
                    columnIndexOrThrow23 = i19;
                    columnIndexOrThrow9 = i3;
                    columnIndexOrThrow11 = i4;
                    columnIndexOrThrow = i5;
                    columnIndexOrThrow24 = i20;
                    columnIndexOrThrow22 = i18;
                    columnIndexOrThrow3 = i7;
                    columnIndexOrThrow20 = i16;
                    columnIndexOrThrow4 = i12;
                    columnIndexOrThrow19 = i15;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public s.a getState(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT state FROM workspec WHERE id=?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? h1.g.intToState(cursorQuery.getInt(0)) : null;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getUnfinishedWorkWithName(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(cursorQuery.getString(0));
            }
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            return arrayList;
        } catch (Throwable th) {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getUnfinishedWorkWithTag(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(cursorQuery.getString(0));
            }
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            return arrayList;
        } catch (Throwable th) {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec getWorkSpec(String str) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        WorkSpec workSpec;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE id=?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "required_network_type");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_charging");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_device_idle");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_battery_not_low");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_storage_not_low");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_content_update_delay");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_max_content_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_uri_triggers");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "worker_class_name");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input_merger_class_name");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "initial_delay");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "interval_duration");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "flex_duration");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_policy");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_delay_duration");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "period_start_time");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "minimum_retention_duration");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "schedule_requested_at");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_in_foreground");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "out_of_quota_policy");
                if (cursorQuery.moveToFirst()) {
                    String string = cursorQuery.getString(columnIndexOrThrow9);
                    String string2 = cursorQuery.getString(columnIndexOrThrow11);
                    a1.b bVar = new a1.b();
                    bVar.k(h1.g.intToNetworkType(cursorQuery.getInt(columnIndexOrThrow)));
                    bVar.m(cursorQuery.getInt(columnIndexOrThrow2) != 0);
                    bVar.n(cursorQuery.getInt(columnIndexOrThrow3) != 0);
                    bVar.l(cursorQuery.getInt(columnIndexOrThrow4) != 0);
                    bVar.o(cursorQuery.getInt(columnIndexOrThrow5) != 0);
                    bVar.p(cursorQuery.getLong(columnIndexOrThrow6));
                    bVar.q(cursorQuery.getLong(columnIndexOrThrow7));
                    bVar.j(h1.g.byteArrayToContentUriTriggers(cursorQuery.getBlob(columnIndexOrThrow8)));
                    WorkSpec workSpec2 = new WorkSpec(string, string2);
                    workSpec2.state = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow10));
                    workSpec2.inputMergerClassName = cursorQuery.getString(columnIndexOrThrow12);
                    workSpec2.input = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow13));
                    workSpec2.output = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow14));
                    workSpec2.initialDelay = cursorQuery.getLong(columnIndexOrThrow15);
                    workSpec2.intervalDuration = cursorQuery.getLong(columnIndexOrThrow16);
                    workSpec2.flexDuration = cursorQuery.getLong(columnIndexOrThrow17);
                    workSpec2.runAttemptCount = cursorQuery.getInt(columnIndexOrThrow18);
                    workSpec2.backoffPolicy = h1.g.intToBackoffPolicy(cursorQuery.getInt(columnIndexOrThrow19));
                    workSpec2.backoffDelayDuration = cursorQuery.getLong(columnIndexOrThrow20);
                    workSpec2.periodStartTime = cursorQuery.getLong(columnIndexOrThrow21);
                    workSpec2.minimumRetentionDuration = cursorQuery.getLong(columnIndexOrThrow22);
                    workSpec2.scheduleRequestedAt = cursorQuery.getLong(columnIndexOrThrow23);
                    workSpec2.expedited = cursorQuery.getInt(columnIndexOrThrow24) != 0;
                    workSpec2.outOfQuotaPolicy = h1.g.intToOutOfQuotaPolicy(cursorQuery.getInt(columnIndexOrThrow25));
                    workSpec2.constraints = bVar;
                    workSpec = workSpec2;
                } else {
                    workSpec = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return workSpec;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getWorkSpecIdAndStatesForName(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                WorkSpec.b bVar = new WorkSpec.b();
                bVar.a = cursorQuery.getString(columnIndexOrThrow);
                bVar.b = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow2));
                arrayList.add(bVar);
            }
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            return arrayList;
        } catch (Throwable th) {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec[] getWorkSpecs(List list) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT ");
        sbNewStringBuilder.append("*");
        sbNewStringBuilder.append(" FROM workspec WHERE id IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size + 0);
        Iterator it = list.iterator();
        int i2 = 1;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i2);
            } else {
                roomSQLiteQueryAcquire.bindString(i2, str);
            }
            i2++;
        }
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "required_network_type");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_charging");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_device_idle");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_battery_not_low");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "requires_storage_not_low");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_content_update_delay");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger_max_content_delay");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "content_uri_triggers");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "worker_class_name");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input_merger_class_name");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "input");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "initial_delay");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "interval_duration");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "flex_duration");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_policy");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "backoff_delay_duration");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "period_start_time");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "minimum_retention_duration");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "schedule_requested_at");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_in_foreground");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "out_of_quota_policy");
                WorkSpec[] workSpecArr = new WorkSpec[cursorQuery.getCount()];
                int i3 = 0;
                while (cursorQuery.moveToNext()) {
                    WorkSpec[] workSpecArr2 = workSpecArr;
                    String string = cursorQuery.getString(columnIndexOrThrow9);
                    int i4 = columnIndexOrThrow9;
                    String string2 = cursorQuery.getString(columnIndexOrThrow11);
                    int i5 = columnIndexOrThrow11;
                    a1.b bVar = new a1.b();
                    int i6 = columnIndexOrThrow;
                    bVar.k(h1.g.intToNetworkType(cursorQuery.getInt(columnIndexOrThrow)));
                    bVar.m(cursorQuery.getInt(columnIndexOrThrow2) != 0);
                    bVar.n(cursorQuery.getInt(columnIndexOrThrow3) != 0);
                    bVar.l(cursorQuery.getInt(columnIndexOrThrow4) != 0);
                    bVar.o(cursorQuery.getInt(columnIndexOrThrow5) != 0);
                    int i7 = columnIndexOrThrow2;
                    int i8 = columnIndexOrThrow3;
                    bVar.p(cursorQuery.getLong(columnIndexOrThrow6));
                    bVar.q(cursorQuery.getLong(columnIndexOrThrow7));
                    bVar.j(h1.g.byteArrayToContentUriTriggers(cursorQuery.getBlob(columnIndexOrThrow8)));
                    WorkSpec workSpec = new WorkSpec(string, string2);
                    workSpec.state = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow10));
                    workSpec.inputMergerClassName = cursorQuery.getString(columnIndexOrThrow12);
                    workSpec.input = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow13));
                    workSpec.output = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow14));
                    int i9 = columnIndexOrThrow14;
                    int i10 = columnIndexOrThrow15;
                    workSpec.initialDelay = cursorQuery.getLong(i10);
                    columnIndexOrThrow15 = i10;
                    int i11 = columnIndexOrThrow16;
                    workSpec.intervalDuration = cursorQuery.getLong(i11);
                    int i12 = columnIndexOrThrow12;
                    int i13 = columnIndexOrThrow17;
                    workSpec.flexDuration = cursorQuery.getLong(i13);
                    int i14 = columnIndexOrThrow18;
                    workSpec.runAttemptCount = cursorQuery.getInt(i14);
                    int i15 = columnIndexOrThrow19;
                    workSpec.backoffPolicy = h1.g.intToBackoffPolicy(cursorQuery.getInt(i15));
                    columnIndexOrThrow17 = i13;
                    int i16 = columnIndexOrThrow20;
                    workSpec.backoffDelayDuration = cursorQuery.getLong(i16);
                    int i17 = columnIndexOrThrow21;
                    workSpec.periodStartTime = cursorQuery.getLong(i17);
                    columnIndexOrThrow21 = i17;
                    int i18 = columnIndexOrThrow22;
                    workSpec.minimumRetentionDuration = cursorQuery.getLong(i18);
                    columnIndexOrThrow22 = i18;
                    int i19 = columnIndexOrThrow23;
                    workSpec.scheduleRequestedAt = cursorQuery.getLong(i19);
                    int i20 = columnIndexOrThrow24;
                    workSpec.expedited = cursorQuery.getInt(i20) != 0;
                    int i21 = columnIndexOrThrow25;
                    workSpec.outOfQuotaPolicy = h1.g.intToOutOfQuotaPolicy(cursorQuery.getInt(i21));
                    workSpec.constraints = bVar;
                    workSpecArr2[i3] = workSpec;
                    i3++;
                    columnIndexOrThrow25 = i21;
                    columnIndexOrThrow2 = i7;
                    columnIndexOrThrow23 = i19;
                    workSpecArr = workSpecArr2;
                    columnIndexOrThrow9 = i4;
                    columnIndexOrThrow11 = i5;
                    columnIndexOrThrow = i6;
                    columnIndexOrThrow24 = i20;
                    columnIndexOrThrow14 = i9;
                    columnIndexOrThrow3 = i8;
                    columnIndexOrThrow20 = i16;
                    columnIndexOrThrow12 = i12;
                    columnIndexOrThrow16 = i11;
                    columnIndexOrThrow18 = i14;
                    columnIndexOrThrow19 = i15;
                }
                WorkSpec[] workSpecArr3 = workSpecArr;
                cursorQuery.close();
                roomSQLiteQuery.release();
                return workSpecArr3;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec.c getWorkStatusPojoForId(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id=?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            WorkSpec.c cVar = null;
            Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, true, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                androidx.collection.a aVar = new androidx.collection.a();
                androidx.collection.a aVar2 = new androidx.collection.a();
                while (cursorQuery.moveToNext()) {
                    if (!cursorQuery.isNull(columnIndexOrThrow)) {
                        String string = cursorQuery.getString(columnIndexOrThrow);
                        if (((ArrayList) aVar.get(string)) == null) {
                            aVar.put(string, new ArrayList());
                        }
                    }
                    if (!cursorQuery.isNull(columnIndexOrThrow)) {
                        String string2 = cursorQuery.getString(columnIndexOrThrow);
                        if (((ArrayList) aVar2.get(string2)) == null) {
                            aVar2.put(string2, new ArrayList());
                        }
                    }
                }
                cursorQuery.moveToPosition(-1);
                b(aVar);
                a(aVar2);
                if (cursorQuery.moveToFirst()) {
                    ArrayList arrayList = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    ArrayList arrayList2 = cursorQuery.isNull(columnIndexOrThrow) ? null : (ArrayList) aVar2.get(cursorQuery.getString(columnIndexOrThrow));
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    WorkSpec.c cVar2 = new WorkSpec.c();
                    cVar2.a = cursorQuery.getString(columnIndexOrThrow);
                    cVar2.b = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow2));
                    cVar2.c = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow3));
                    cVar2.d = cursorQuery.getInt(columnIndexOrThrow4);
                    cVar2.e = arrayList;
                    cVar2.f = arrayList2;
                    cVar = cVar2;
                }
                this.a.setTransactionSuccessful();
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                this.a.endTransaction();
                return cVar;
            } catch (Throwable th) {
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                throw th;
            }
        } catch (Throwable th2) {
            this.a.endTransaction();
            throw th2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getWorkStatusPojoForIds(List list) {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size + 0);
        Iterator it = list.iterator();
        int i2 = 1;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i2);
            } else {
                roomSQLiteQueryAcquire.bindString(i2, str);
            }
            i2++;
        }
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, true, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                androidx.collection.a aVar = new androidx.collection.a();
                androidx.collection.a aVar2 = new androidx.collection.a();
                while (cursorQuery.moveToNext()) {
                    if (!cursorQuery.isNull(columnIndexOrThrow)) {
                        String string = cursorQuery.getString(columnIndexOrThrow);
                        if (((ArrayList) aVar.get(string)) == null) {
                            aVar.put(string, new ArrayList());
                        }
                    }
                    if (!cursorQuery.isNull(columnIndexOrThrow)) {
                        String string2 = cursorQuery.getString(columnIndexOrThrow);
                        if (((ArrayList) aVar2.get(string2)) == null) {
                            aVar2.put(string2, new ArrayList());
                        }
                    }
                }
                cursorQuery.moveToPosition(-1);
                b(aVar);
                a(aVar2);
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    ArrayList arrayList2 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    ArrayList arrayList3 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar2.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                    if (arrayList3 == null) {
                        arrayList3 = new ArrayList();
                    }
                    WorkSpec.c cVar = new WorkSpec.c();
                    cVar.a = cursorQuery.getString(columnIndexOrThrow);
                    cVar.b = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow2));
                    cVar.c = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow3));
                    cVar.d = cursorQuery.getInt(columnIndexOrThrow4);
                    cVar.e = arrayList2;
                    cVar.f = arrayList3;
                    arrayList.add(cVar);
                }
                this.a.setTransactionSuccessful();
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                this.a.endTransaction();
                return arrayList;
            } catch (Throwable th) {
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                throw th;
            }
        } catch (Throwable th2) {
            this.a.endTransaction();
            throw th2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getWorkStatusPojoForName(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, true, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                androidx.collection.a aVar = new androidx.collection.a();
                androidx.collection.a aVar2 = new androidx.collection.a();
                while (cursorQuery.moveToNext()) {
                    if (!cursorQuery.isNull(columnIndexOrThrow)) {
                        String string = cursorQuery.getString(columnIndexOrThrow);
                        if (((ArrayList) aVar.get(string)) == null) {
                            aVar.put(string, new ArrayList());
                        }
                    }
                    if (!cursorQuery.isNull(columnIndexOrThrow)) {
                        String string2 = cursorQuery.getString(columnIndexOrThrow);
                        if (((ArrayList) aVar2.get(string2)) == null) {
                            aVar2.put(string2, new ArrayList());
                        }
                    }
                }
                cursorQuery.moveToPosition(-1);
                b(aVar);
                a(aVar2);
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    ArrayList arrayList2 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    ArrayList arrayList3 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar2.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                    if (arrayList3 == null) {
                        arrayList3 = new ArrayList();
                    }
                    WorkSpec.c cVar = new WorkSpec.c();
                    cVar.a = cursorQuery.getString(columnIndexOrThrow);
                    cVar.b = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow2));
                    cVar.c = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow3));
                    cVar.d = cursorQuery.getInt(columnIndexOrThrow4);
                    cVar.e = arrayList2;
                    cVar.f = arrayList3;
                    arrayList.add(cVar);
                }
                this.a.setTransactionSuccessful();
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                this.a.endTransaction();
                return arrayList;
            } catch (Throwable th) {
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                throw th;
            }
        } catch (Throwable th2) {
            this.a.endTransaction();
            throw th2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List getWorkStatusPojoForTag(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, true, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "output");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "run_attempt_count");
                androidx.collection.a aVar = new androidx.collection.a();
                androidx.collection.a aVar2 = new androidx.collection.a();
                while (cursorQuery.moveToNext()) {
                    if (!cursorQuery.isNull(columnIndexOrThrow)) {
                        String string = cursorQuery.getString(columnIndexOrThrow);
                        if (((ArrayList) aVar.get(string)) == null) {
                            aVar.put(string, new ArrayList());
                        }
                    }
                    if (!cursorQuery.isNull(columnIndexOrThrow)) {
                        String string2 = cursorQuery.getString(columnIndexOrThrow);
                        if (((ArrayList) aVar2.get(string2)) == null) {
                            aVar2.put(string2, new ArrayList());
                        }
                    }
                }
                cursorQuery.moveToPosition(-1);
                b(aVar);
                a(aVar2);
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    ArrayList arrayList2 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    ArrayList arrayList3 = !cursorQuery.isNull(columnIndexOrThrow) ? (ArrayList) aVar2.get(cursorQuery.getString(columnIndexOrThrow)) : null;
                    if (arrayList3 == null) {
                        arrayList3 = new ArrayList();
                    }
                    WorkSpec.c cVar = new WorkSpec.c();
                    cVar.a = cursorQuery.getString(columnIndexOrThrow);
                    cVar.b = h1.g.intToState(cursorQuery.getInt(columnIndexOrThrow2));
                    cVar.c = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndexOrThrow3));
                    cVar.d = cursorQuery.getInt(columnIndexOrThrow4);
                    cVar.e = arrayList2;
                    cVar.f = arrayList3;
                    arrayList.add(cVar);
                }
                this.a.setTransactionSuccessful();
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                this.a.endTransaction();
                return arrayList;
            } catch (Throwable th) {
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                throw th;
            }
        } catch (Throwable th2) {
            this.a.endTransaction();
            throw th2;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData getWorkStatusPojoLiveDataForIds(List list) {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size + 0);
        Iterator it = list.iterator();
        int i2 = 1;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i2);
            } else {
                roomSQLiteQueryAcquire.bindString(i2, str);
            }
            i2++;
        }
        return this.a.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec"}, true, new CallableC0064b(roomSQLiteQueryAcquire));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData getWorkStatusPojoLiveDataForName(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        return this.a.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec", "workname"}, true, new d(roomSQLiteQueryAcquire));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public LiveData getWorkStatusPojoLiveDataForTag(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        return this.a.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec", "worktag"}, true, new c(roomSQLiteQueryAcquire));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public boolean hasUnfinishedWork() {
        boolean z = false;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1", 0);
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, roomSQLiteQueryAcquire, false, null);
        try {
            if (cursorQuery.moveToFirst() && cursorQuery.getInt(0) != 0) {
                z = true;
            }
            return z;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int incrementWorkSpecRunAttemptCount(String str) {
        this.a.assertNotSuspendingTransaction();
        t0.l lVarAcquire = this.f.acquire();
        if (str == null) {
            lVarAcquire.bindNull(1);
        } else {
            lVarAcquire.bindString(1, str);
        }
        this.a.beginTransaction();
        try {
            int iExecuteUpdateDelete = lVarAcquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
            return iExecuteUpdateDelete;
        } finally {
            this.a.endTransaction();
            this.f.release(lVarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void insertWorkSpec(WorkSpec workSpec) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            this.b.insert(workSpec);
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int markWorkSpecScheduled(String str, long j2) {
        this.a.assertNotSuspendingTransaction();
        t0.l lVarAcquire = this.h.acquire();
        lVarAcquire.bindLong(1, j2);
        if (str == null) {
            lVarAcquire.bindNull(2);
        } else {
            lVarAcquire.bindString(2, str);
        }
        this.a.beginTransaction();
        try {
            int iExecuteUpdateDelete = lVarAcquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
            return iExecuteUpdateDelete;
        } finally {
            this.a.endTransaction();
            this.h.release(lVarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast() {
        this.a.assertNotSuspendingTransaction();
        t0.l lVarAcquire = this.j.acquire();
        this.a.beginTransaction();
        try {
            lVarAcquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
            this.j.release(lVarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetScheduledState() {
        this.a.assertNotSuspendingTransaction();
        t0.l lVarAcquire = this.i.acquire();
        this.a.beginTransaction();
        try {
            int iExecuteUpdateDelete = lVarAcquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
            return iExecuteUpdateDelete;
        } finally {
            this.a.endTransaction();
            this.i.release(lVarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetWorkSpecRunAttemptCount(String str) {
        this.a.assertNotSuspendingTransaction();
        t0.l lVarAcquire = this.g.acquire();
        if (str == null) {
            lVarAcquire.bindNull(1);
        } else {
            lVarAcquire.bindString(1, str);
        }
        this.a.beginTransaction();
        try {
            int iExecuteUpdateDelete = lVarAcquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
            return iExecuteUpdateDelete;
        } finally {
            this.a.endTransaction();
            this.g.release(lVarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setOutput(String str, androidx.work.b bVar) throws Throwable {
        this.a.assertNotSuspendingTransaction();
        t0.l lVarAcquire = this.d.acquire();
        byte[] byteArrayInternal = androidx.work.b.toByteArrayInternal(bVar);
        if (byteArrayInternal == null) {
            lVarAcquire.bindNull(1);
        } else {
            lVarAcquire.bindBlob(1, byteArrayInternal);
        }
        if (str == null) {
            lVarAcquire.bindNull(2);
        } else {
            lVarAcquire.bindString(2, str);
        }
        this.a.beginTransaction();
        try {
            lVarAcquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
            this.d.release(lVarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setPeriodStartTime(String str, long j2) {
        this.a.assertNotSuspendingTransaction();
        t0.l lVarAcquire = this.e.acquire();
        lVarAcquire.bindLong(1, j2);
        if (str == null) {
            lVarAcquire.bindNull(2);
        } else {
            lVarAcquire.bindString(2, str);
        }
        this.a.beginTransaction();
        try {
            lVarAcquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
        } finally {
            this.a.endTransaction();
            this.e.release(lVarAcquire);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int setState(s.a aVar, String... strArr) {
        this.a.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("UPDATE workspec SET state=");
        sbNewStringBuilder.append("?");
        sbNewStringBuilder.append(" WHERE id IN (");
        StringUtil.appendPlaceholders(sbNewStringBuilder, strArr.length);
        sbNewStringBuilder.append(")");
        t0.l lVarCompileStatement = this.a.compileStatement(sbNewStringBuilder.toString());
        lVarCompileStatement.bindLong(1, h1.g.stateToInt(aVar));
        int i2 = 2;
        for (String str : strArr) {
            if (str == null) {
                lVarCompileStatement.bindNull(i2);
            } else {
                lVarCompileStatement.bindString(i2, str);
            }
            i2++;
        }
        this.a.beginTransaction();
        try {
            int iExecuteUpdateDelete = lVarCompileStatement.executeUpdateDelete();
            this.a.setTransactionSuccessful();
            return iExecuteUpdateDelete;
        } finally {
            this.a.endTransaction();
        }
    }
}
