package androidx.work.impl.model;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import h1.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import t0.k;

/* JADX INFO: loaded from: classes.dex */
public final class a implements RawWorkInfoDao {
    private final RoomDatabase a;

    /* JADX INFO: renamed from: androidx.work.impl.model.a$a, reason: collision with other inner class name */
    class CallableC0063a implements Callable {
        final /* synthetic */ k a;

        CallableC0063a(k kVar) {
            this.a = kVar;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List call() {
            Cursor cursorQuery = DBUtil.query(a.this.a, this.a, true, null);
            try {
                int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "id");
                int columnIndex2 = CursorUtil.getColumnIndex(cursorQuery, "state");
                int columnIndex3 = CursorUtil.getColumnIndex(cursorQuery, "output");
                int columnIndex4 = CursorUtil.getColumnIndex(cursorQuery, "run_attempt_count");
                androidx.collection.a aVar = new androidx.collection.a();
                androidx.collection.a aVar2 = new androidx.collection.a();
                while (cursorQuery.moveToNext()) {
                    if (!cursorQuery.isNull(columnIndex)) {
                        String string = cursorQuery.getString(columnIndex);
                        if (((ArrayList) aVar.get(string)) == null) {
                            aVar.put(string, new ArrayList());
                        }
                    }
                    if (!cursorQuery.isNull(columnIndex)) {
                        String string2 = cursorQuery.getString(columnIndex);
                        if (((ArrayList) aVar2.get(string2)) == null) {
                            aVar2.put(string2, new ArrayList());
                        }
                    }
                }
                cursorQuery.moveToPosition(-1);
                a.this.b(aVar);
                a.this.a(aVar2);
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    ArrayList arrayList2 = !cursorQuery.isNull(columnIndex) ? (ArrayList) aVar.get(cursorQuery.getString(columnIndex)) : null;
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    ArrayList arrayList3 = !cursorQuery.isNull(columnIndex) ? (ArrayList) aVar2.get(cursorQuery.getString(columnIndex)) : null;
                    if (arrayList3 == null) {
                        arrayList3 = new ArrayList();
                    }
                    WorkSpec.c cVar = new WorkSpec.c();
                    if (columnIndex != -1) {
                        cVar.a = cursorQuery.getString(columnIndex);
                    }
                    if (columnIndex2 != -1) {
                        cVar.b = g.intToState(cursorQuery.getInt(columnIndex2));
                    }
                    if (columnIndex3 != -1) {
                        cVar.c = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndex3));
                    }
                    if (columnIndex4 != -1) {
                        cVar.d = cursorQuery.getInt(columnIndex4);
                    }
                    cVar.e = arrayList2;
                    cVar.f = arrayList3;
                    arrayList.add(cVar);
                }
                return arrayList;
            } finally {
                cursorQuery.close();
            }
        }
    }

    public a(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(androidx.collection.a aVar) {
        ArrayList arrayList;
        int i;
        Set<Object> setKeySet = aVar.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (aVar.size() > 999) {
            androidx.collection.a aVar2 = new androidx.collection.a(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = aVar.size();
            int i2 = 0;
            loop0: while (true) {
                i = 0;
                do {
                    if (i2 >= size) {
                        break loop0;
                    }
                    aVar2.put((String) aVar.keyAt(i2), (ArrayList) aVar.valueAt(i2));
                    i2++;
                    i++;
                } while (i != 999);
                a(aVar2);
                aVar2 = new androidx.collection.a(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            }
            if (i > 0) {
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
        int i3 = 1;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i3);
            } else {
                roomSQLiteQueryAcquire.bindString(i3, str);
            }
            i3++;
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
        int i;
        Set<Object> setKeySet = aVar.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (aVar.size() > 999) {
            androidx.collection.a aVar2 = new androidx.collection.a(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = aVar.size();
            int i2 = 0;
            loop0: while (true) {
                i = 0;
                do {
                    if (i2 >= size) {
                        break loop0;
                    }
                    aVar2.put((String) aVar.keyAt(i2), (ArrayList) aVar.valueAt(i2));
                    i2++;
                    i++;
                } while (i != 999);
                b(aVar2);
                aVar2 = new androidx.collection.a(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            }
            if (i > 0) {
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
        int i3 = 1;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i3);
            } else {
                roomSQLiteQueryAcquire.bindString(i3, str);
            }
            i3++;
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

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public List getWorkInfoPojos(k kVar) {
        this.a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.a, kVar, true, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "id");
            int columnIndex2 = CursorUtil.getColumnIndex(cursorQuery, "state");
            int columnIndex3 = CursorUtil.getColumnIndex(cursorQuery, "output");
            int columnIndex4 = CursorUtil.getColumnIndex(cursorQuery, "run_attempt_count");
            androidx.collection.a aVar = new androidx.collection.a();
            androidx.collection.a aVar2 = new androidx.collection.a();
            while (cursorQuery.moveToNext()) {
                if (!cursorQuery.isNull(columnIndex)) {
                    String string = cursorQuery.getString(columnIndex);
                    if (((ArrayList) aVar.get(string)) == null) {
                        aVar.put(string, new ArrayList());
                    }
                }
                if (!cursorQuery.isNull(columnIndex)) {
                    String string2 = cursorQuery.getString(columnIndex);
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
                ArrayList arrayList2 = !cursorQuery.isNull(columnIndex) ? (ArrayList) aVar.get(cursorQuery.getString(columnIndex)) : null;
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                ArrayList arrayList3 = !cursorQuery.isNull(columnIndex) ? (ArrayList) aVar2.get(cursorQuery.getString(columnIndex)) : null;
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList();
                }
                WorkSpec.c cVar = new WorkSpec.c();
                if (columnIndex != -1) {
                    cVar.a = cursorQuery.getString(columnIndex);
                }
                if (columnIndex2 != -1) {
                    cVar.b = g.intToState(cursorQuery.getInt(columnIndex2));
                }
                if (columnIndex3 != -1) {
                    cVar.c = androidx.work.b.fromByteArray(cursorQuery.getBlob(columnIndex3));
                }
                if (columnIndex4 != -1) {
                    cVar.d = cursorQuery.getInt(columnIndex4);
                }
                cVar.e = arrayList2;
                cVar.f = arrayList3;
                arrayList.add(cVar);
            }
            return arrayList;
        } finally {
            cursorQuery.close();
        }
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public LiveData getWorkInfoPojosLiveData(k kVar) {
        return this.a.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, false, new CallableC0063a(kVar));
    }
}
