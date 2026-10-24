package androidx.room;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.lifecycle.LiveData;
import fa.h0;
import fa.n0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

/* JADX INFO: loaded from: classes.dex */
public class InvalidationTracker {
    private static final String CREATE_TRACKING_TABLE_SQL = "CREATE TEMP TABLE room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)";
    private static final String INVALIDATED_COLUMN_NAME = "invalidated";
    public static final String RESET_UPDATED_TABLES_SQL = "UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1";
    public static final String SELECT_UPDATED_TABLES_SQL = "SELECT * FROM room_table_modification_log WHERE invalidated = 1;";
    private static final String TABLE_ID_COLUMN_NAME = "table_id";
    private static final String UPDATE_TABLE_NAME = "room_table_modification_log";
    private AutoCloser autoCloser;
    private volatile t0.l cleanupStatement;
    private final RoomDatabase database;
    private volatile boolean initialized;
    private final InvalidationLiveDataContainer invalidationLiveDataContainer;
    private MultiInstanceInvalidationClient multiInstanceInvalidationClient;
    private final ObservedTableTracker observedTableTracker;
    private final j.b observerMap;
    private final AtomicBoolean pendingRefresh;
    public final Runnable refreshRunnable;
    private final Map<String, String> shadowTablesMap;
    private final Object syncTriggersLock;
    private final Map<String, Integer> tableIdLookup;
    private final String[] tablesNames;
    private final Object trackerLock;
    private final Map<String, Set<String>> viewTables;
    public static final Companion Companion = new Companion(null);
    private static final String[] TRIGGERS = {"UPDATE", "DELETE", "INSERT"};

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(kotlin.jvm.internal.g gVar) {
            this();
        }

        public static /* synthetic */ void getRESET_UPDATED_TABLES_SQL$room_runtime_release$annotations() {
        }

        public static /* synthetic */ void getSELECT_UPDATED_TABLES_SQL$room_runtime_release$annotations() {
        }

        public final void beginTransactionInternal$room_runtime_release(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "database");
            if (hVar.isWriteAheadLoggingEnabled()) {
                hVar.beginTransactionNonExclusive();
            } else {
                hVar.beginTransaction();
            }
        }

        public final String getTriggerName$room_runtime_release(String str, String str2) {
            kotlin.jvm.internal.m.g(str, "tableName");
            kotlin.jvm.internal.m.g(str2, "triggerType");
            return "`room_table_modification_trigger_" + str + '_' + str2 + '`';
        }
    }

    public static final class ObservedTableTracker {
        public static final int ADD = 1;
        public static final Companion Companion = new Companion(null);
        public static final int NO_OP = 0;
        public static final int REMOVE = 2;
        private boolean needsSync;
        private final long[] tableObservers;
        private final int[] triggerStateChanges;
        private final boolean[] triggerStates;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(kotlin.jvm.internal.g gVar) {
                this();
            }
        }

        public ObservedTableTracker(int i) {
            this.tableObservers = new long[i];
            this.triggerStates = new boolean[i];
            this.triggerStateChanges = new int[i];
        }

        public final boolean getNeedsSync() {
            return this.needsSync;
        }

        public final long[] getTableObservers() {
            return this.tableObservers;
        }

        public final int[] getTablesToSync() {
            synchronized (this) {
                if (!this.needsSync) {
                    return null;
                }
                long[] jArr = this.tableObservers;
                int length = jArr.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    int i3 = i2 + 1;
                    int i4 = 1;
                    boolean z = jArr[i] > 0;
                    boolean[] zArr = this.triggerStates;
                    if (z != zArr[i2]) {
                        int[] iArr = this.triggerStateChanges;
                        if (!z) {
                            i4 = 2;
                        }
                        iArr[i2] = i4;
                    } else {
                        this.triggerStateChanges[i2] = 0;
                    }
                    zArr[i2] = z;
                    i++;
                    i2 = i3;
                }
                this.needsSync = false;
                return (int[]) this.triggerStateChanges.clone();
            }
        }

        public final boolean onAdded(int... iArr) {
            boolean z;
            kotlin.jvm.internal.m.g(iArr, "tableIds");
            synchronized (this) {
                z = false;
                for (int i : iArr) {
                    long[] jArr = this.tableObservers;
                    long j = jArr[i];
                    jArr[i] = 1 + j;
                    if (j == 0) {
                        z = true;
                        this.needsSync = true;
                    }
                }
                ea.v vVar = ea.v.a;
            }
            return z;
        }

        public final boolean onRemoved(int... iArr) {
            boolean z;
            kotlin.jvm.internal.m.g(iArr, "tableIds");
            synchronized (this) {
                z = false;
                for (int i : iArr) {
                    long[] jArr = this.tableObservers;
                    long j = jArr[i];
                    jArr[i] = j - 1;
                    if (j == 1) {
                        z = true;
                        this.needsSync = true;
                    }
                }
                ea.v vVar = ea.v.a;
            }
            return z;
        }

        public final void resetTriggerState() {
            synchronized (this) {
                Arrays.fill(this.triggerStates, false);
                this.needsSync = true;
                ea.v vVar = ea.v.a;
            }
        }

        public final void setNeedsSync(boolean z) {
            this.needsSync = z;
        }
    }

    public static abstract class Observer {
        private final String[] tables;

        public Observer(String[] strArr) {
            kotlin.jvm.internal.m.g(strArr, "tables");
            this.tables = strArr;
        }

        public final String[] getTables$room_runtime_release() {
            return this.tables;
        }

        public boolean isRemote$room_runtime_release() {
            return false;
        }

        public abstract void onInvalidated(Set<String> set);

        /* JADX WARN: Illegal instructions before constructor call */
        protected Observer(String str, String... strArr) {
            kotlin.jvm.internal.m.g(str, "firstTable");
            kotlin.jvm.internal.m.g(strArr, "rest");
            List listC = fa.p.c();
            fa.p.u(listC, strArr);
            listC.add(str);
            Object[] array = fa.p.a(listC).toArray(new String[0]);
            kotlin.jvm.internal.m.e(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            this((String[]) array);
        }
    }

    public static final class ObserverWrapper {
        private final Observer observer;
        private final Set<String> singleTableSet;
        private final int[] tableIds;
        private final String[] tableNames;

        public ObserverWrapper(Observer observer, int[] iArr, String[] strArr) {
            kotlin.jvm.internal.m.g(observer, "observer");
            kotlin.jvm.internal.m.g(iArr, "tableIds");
            kotlin.jvm.internal.m.g(strArr, "tableNames");
            this.observer = observer;
            this.tableIds = iArr;
            this.tableNames = strArr;
            this.singleTableSet = (strArr.length == 0) ^ true ? n0.d(strArr[0]) : n0.e();
            if (!(iArr.length == strArr.length)) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }

        public final Observer getObserver$room_runtime_release() {
            return this.observer;
        }

        public final int[] getTableIds$room_runtime_release() {
            return this.tableIds;
        }

        public final void notifyByTableInvalidStatus$room_runtime_release(Set<Integer> set) {
            Set<String> setE;
            kotlin.jvm.internal.m.g(set, "invalidatedTablesIds");
            int[] iArr = this.tableIds;
            int length = iArr.length;
            if (length != 0) {
                int i = 0;
                if (length != 1) {
                    Set setB = n0.b();
                    int[] iArr2 = this.tableIds;
                    int length2 = iArr2.length;
                    int i2 = 0;
                    while (i < length2) {
                        int i3 = i2 + 1;
                        if (set.contains(Integer.valueOf(iArr2[i]))) {
                            setB.add(this.tableNames[i2]);
                        }
                        i++;
                        i2 = i3;
                    }
                    setE = n0.a(setB);
                } else {
                    setE = set.contains(Integer.valueOf(iArr[0])) ? this.singleTableSet : n0.e();
                }
            } else {
                setE = n0.e();
            }
            if (!setE.isEmpty()) {
                this.observer.onInvalidated(setE);
            }
        }

        public final void notifyByTableNames$room_runtime_release(String[] strArr) {
            Set<String> setE;
            kotlin.jvm.internal.m.g(strArr, "tables");
            int length = this.tableNames.length;
            if (length != 0) {
                boolean z = false;
                if (length != 1) {
                    Set setB = n0.b();
                    for (String str : strArr) {
                        for (String str2 : this.tableNames) {
                            if (ya.l.l(str2, str, true)) {
                                setB.add(str2);
                            }
                        }
                    }
                    setE = n0.a(setB);
                } else {
                    for (String str3 : strArr) {
                        if (ya.l.l(str3, this.tableNames[0], true)) {
                            z = true;
                            break;
                        }
                    }
                    setE = z ? this.singleTableSet : n0.e();
                }
            } else {
                setE = n0.e();
            }
            if (!setE.isEmpty()) {
                this.observer.onInvalidated(setE);
            }
        }
    }

    public static final class WeakObserver extends Observer {
        private final WeakReference<Observer> delegateRef;
        private final InvalidationTracker tracker;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WeakObserver(InvalidationTracker invalidationTracker, Observer observer) {
            super(observer.getTables$room_runtime_release());
            kotlin.jvm.internal.m.g(invalidationTracker, "tracker");
            kotlin.jvm.internal.m.g(observer, "delegate");
            this.tracker = invalidationTracker;
            this.delegateRef = new WeakReference<>(observer);
        }

        public final WeakReference<Observer> getDelegateRef() {
            return this.delegateRef;
        }

        public final InvalidationTracker getTracker() {
            return this.tracker;
        }

        @Override // androidx.room.InvalidationTracker.Observer
        public void onInvalidated(Set<String> set) {
            kotlin.jvm.internal.m.g(set, "tables");
            Observer observer = this.delegateRef.get();
            if (observer == null) {
                this.tracker.removeObserver(this);
            } else {
                observer.onInvalidated(set);
            }
        }
    }

    public InvalidationTracker(RoomDatabase roomDatabase, Map<String, String> map, Map<String, Set<String>> map2, String... strArr) {
        String lowerCase;
        kotlin.jvm.internal.m.g(roomDatabase, "database");
        kotlin.jvm.internal.m.g(map, "shadowTablesMap");
        kotlin.jvm.internal.m.g(map2, "viewTables");
        kotlin.jvm.internal.m.g(strArr, "tableNames");
        this.database = roomDatabase;
        this.shadowTablesMap = map;
        this.viewTables = map2;
        this.pendingRefresh = new AtomicBoolean(false);
        this.observedTableTracker = new ObservedTableTracker(strArr.length);
        this.invalidationLiveDataContainer = new InvalidationLiveDataContainer(roomDatabase);
        this.observerMap = new j.b();
        this.syncTriggersLock = new Object();
        this.trackerLock = new Object();
        this.tableIdLookup = new LinkedHashMap();
        int length = strArr.length;
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            Locale locale = Locale.US;
            kotlin.jvm.internal.m.f(locale, "US");
            String lowerCase2 = str.toLowerCase(locale);
            kotlin.jvm.internal.m.f(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            this.tableIdLookup.put(lowerCase2, Integer.valueOf(i));
            String str2 = this.shadowTablesMap.get(strArr[i]);
            if (str2 != null) {
                kotlin.jvm.internal.m.f(locale, "US");
                lowerCase = str2.toLowerCase(locale);
                kotlin.jvm.internal.m.f(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            } else {
                lowerCase = null;
            }
            if (lowerCase != null) {
                lowerCase2 = lowerCase;
            }
            strArr2[i] = lowerCase2;
        }
        this.tablesNames = strArr2;
        for (Map.Entry<String, String> entry : this.shadowTablesMap.entrySet()) {
            String value = entry.getValue();
            Locale locale2 = Locale.US;
            kotlin.jvm.internal.m.f(locale2, "US");
            String lowerCase3 = value.toLowerCase(locale2);
            kotlin.jvm.internal.m.f(lowerCase3, "this as java.lang.String).toLowerCase(locale)");
            if (this.tableIdLookup.containsKey(lowerCase3)) {
                String key = entry.getKey();
                kotlin.jvm.internal.m.f(locale2, "US");
                String lowerCase4 = key.toLowerCase(locale2);
                kotlin.jvm.internal.m.f(lowerCase4, "this as java.lang.String).toLowerCase(locale)");
                Map<String, Integer> map3 = this.tableIdLookup;
                map3.put(lowerCase4, (Integer) h0.i(map3, lowerCase3));
            }
        }
        this.refreshRunnable = new Runnable() { // from class: androidx.room.InvalidationTracker$refreshRunnable$1
            private final Set<Integer> checkUpdatedTable() {
                InvalidationTracker invalidationTracker = this.this$0;
                Set setB = n0.b();
                Cursor cursorQuery$default = RoomDatabase.query$default(invalidationTracker.getDatabase$room_runtime_release(), new t0.a(InvalidationTracker.SELECT_UPDATED_TABLES_SQL), null, 2, null);
                try {
                    Cursor cursor = cursorQuery$default;
                    while (cursor.moveToNext()) {
                        setB.add(Integer.valueOf(cursor.getInt(0)));
                    }
                    ea.v vVar = ea.v.a;
                    na.c.a(cursorQuery$default, (Throwable) null);
                    Set<Integer> setA = n0.a(setB);
                    if (!setA.isEmpty()) {
                        if (this.this$0.getCleanupStatement$room_runtime_release() == null) {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        t0.l cleanupStatement$room_runtime_release = this.this$0.getCleanupStatement$room_runtime_release();
                        if (cleanupStatement$room_runtime_release == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        cleanupStatement$room_runtime_release.executeUpdateDelete();
                    }
                    return setA;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        na.c.a(cursorQuery$default, th);
                        throw th2;
                    }
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                Set<Integer> setE;
                AutoCloser autoCloser;
                Lock closeLock$room_runtime_release = this.this$0.getDatabase$room_runtime_release().getCloseLock$room_runtime_release();
                closeLock$room_runtime_release.lock();
                try {
                    try {
                        if (!this.this$0.ensureInitialization$room_runtime_release()) {
                            closeLock$room_runtime_release.unlock();
                            AutoCloser autoCloser2 = this.this$0.autoCloser;
                            if (autoCloser2 != null) {
                                autoCloser2.decrementCountAndScheduleClose();
                                return;
                            }
                            return;
                        }
                        if (!this.this$0.getPendingRefresh().compareAndSet(true, false)) {
                            closeLock$room_runtime_release.unlock();
                            AutoCloser autoCloser3 = this.this$0.autoCloser;
                            if (autoCloser3 != null) {
                                autoCloser3.decrementCountAndScheduleClose();
                                return;
                            }
                            return;
                        }
                        if (this.this$0.getDatabase$room_runtime_release().inTransaction()) {
                            closeLock$room_runtime_release.unlock();
                            AutoCloser autoCloser4 = this.this$0.autoCloser;
                            if (autoCloser4 != null) {
                                autoCloser4.decrementCountAndScheduleClose();
                                return;
                            }
                            return;
                        }
                        t0.h writableDatabase = this.this$0.getDatabase$room_runtime_release().getOpenHelper().getWritableDatabase();
                        writableDatabase.beginTransactionNonExclusive();
                        try {
                            setE = checkUpdatedTable();
                            writableDatabase.setTransactionSuccessful();
                            writableDatabase.endTransaction();
                            closeLock$room_runtime_release.unlock();
                            autoCloser = this.this$0.autoCloser;
                            if (autoCloser != null) {
                                autoCloser.decrementCountAndScheduleClose();
                            }
                            if (!setE.isEmpty()) {
                                j.b observerMap$room_runtime_release = this.this$0.getObserverMap$room_runtime_release();
                                InvalidationTracker invalidationTracker = this.this$0;
                                synchronized (observerMap$room_runtime_release) {
                                    Iterator it = invalidationTracker.getObserverMap$room_runtime_release().iterator();
                                    while (it.hasNext()) {
                                        ((InvalidationTracker.ObserverWrapper) ((Map.Entry) it.next()).getValue()).notifyByTableInvalidStatus$room_runtime_release(setE);
                                    }
                                    ea.v vVar = ea.v.a;
                                }
                            }
                        } catch (Throwable th) {
                            writableDatabase.endTransaction();
                            throw th;
                        }
                    } catch (SQLiteException e) {
                        Log.e(Room.LOG_TAG, "Cannot run invalidation tracker. Is the db closed?", e);
                        setE = n0.e();
                        closeLock$room_runtime_release.unlock();
                        autoCloser = this.this$0.autoCloser;
                        if (autoCloser != null) {
                        }
                    } catch (IllegalStateException e2) {
                        Log.e(Room.LOG_TAG, "Cannot run invalidation tracker. Is the db closed?", e2);
                        setE = n0.e();
                        closeLock$room_runtime_release.unlock();
                        autoCloser = this.this$0.autoCloser;
                        if (autoCloser != null) {
                        }
                    }
                } catch (Throwable th2) {
                    closeLock$room_runtime_release.unlock();
                    AutoCloser autoCloser5 = this.this$0.autoCloser;
                    if (autoCloser5 != null) {
                        autoCloser5.decrementCountAndScheduleClose();
                    }
                    throw th2;
                }
            }
        };
    }

    public static /* synthetic */ void getRefreshRunnable$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onAutoCloseCallback() {
        synchronized (this.trackerLock) {
            this.initialized = false;
            this.observedTableTracker.resetTriggerState();
            t0.l lVar = this.cleanupStatement;
            if (lVar != null) {
                lVar.close();
                ea.v vVar = ea.v.a;
            }
        }
    }

    private final String[] resolveViews(String[] strArr) {
        Set setB = n0.b();
        for (String str : strArr) {
            Map<String, Set<String>> map = this.viewTables;
            Locale locale = Locale.US;
            kotlin.jvm.internal.m.f(locale, "US");
            String lowerCase = str.toLowerCase(locale);
            kotlin.jvm.internal.m.f(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (map.containsKey(lowerCase)) {
                Map<String, Set<String>> map2 = this.viewTables;
                kotlin.jvm.internal.m.f(locale, "US");
                String lowerCase2 = str.toLowerCase(locale);
                kotlin.jvm.internal.m.f(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                Set<String> set = map2.get(lowerCase2);
                kotlin.jvm.internal.m.d(set);
                setB.addAll(set);
            } else {
                setB.add(str);
            }
        }
        Object[] array = n0.a(setB).toArray(new String[0]);
        kotlin.jvm.internal.m.e(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return (String[]) array;
    }

    private final void startTrackingTable(t0.h hVar, int i) {
        hVar.execSQL("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i + ", 0)");
        String str = this.tablesNames[i];
        for (String str2 : TRIGGERS) {
            String str3 = "CREATE TEMP TRIGGER IF NOT EXISTS " + Companion.getTriggerName$room_runtime_release(str, str2) + " AFTER " + str2 + " ON `" + str + "` BEGIN UPDATE " + UPDATE_TABLE_NAME + " SET " + INVALIDATED_COLUMN_NAME + " = 1 WHERE " + TABLE_ID_COLUMN_NAME + " = " + i + " AND " + INVALIDATED_COLUMN_NAME + " = 0; END";
            kotlin.jvm.internal.m.f(str3, "StringBuilder().apply(builderAction).toString()");
            hVar.execSQL(str3);
        }
    }

    private final void stopTrackingTable(t0.h hVar, int i) {
        String str = this.tablesNames[i];
        for (String str2 : TRIGGERS) {
            String str3 = "DROP TRIGGER IF EXISTS " + Companion.getTriggerName$room_runtime_release(str, str2);
            kotlin.jvm.internal.m.f(str3, "StringBuilder().apply(builderAction).toString()");
            hVar.execSQL(str3);
        }
    }

    private final String[] validateAndResolveTableNames(String[] strArr) {
        String[] strArrResolveViews = resolveViews(strArr);
        for (String str : strArrResolveViews) {
            Map<String, Integer> map = this.tableIdLookup;
            Locale locale = Locale.US;
            kotlin.jvm.internal.m.f(locale, "US");
            String lowerCase = str.toLowerCase(locale);
            kotlin.jvm.internal.m.f(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (!map.containsKey(lowerCase)) {
                throw new IllegalArgumentException(("There is no table with name " + str).toString());
            }
        }
        return strArrResolveViews;
    }

    public void addObserver(Observer observer) {
        ObserverWrapper observerWrapper;
        kotlin.jvm.internal.m.g(observer, "observer");
        String[] strArrResolveViews = resolveViews(observer.getTables$room_runtime_release());
        ArrayList arrayList = new ArrayList(strArrResolveViews.length);
        for (String str : strArrResolveViews) {
            Map<String, Integer> map = this.tableIdLookup;
            Locale locale = Locale.US;
            kotlin.jvm.internal.m.f(locale, "US");
            String lowerCase = str.toLowerCase(locale);
            kotlin.jvm.internal.m.f(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            Integer num = map.get(lowerCase);
            if (num == null) {
                throw new IllegalArgumentException("There is no table with name " + str);
            }
            arrayList.add(Integer.valueOf(num.intValue()));
        }
        int[] iArrH0 = fa.p.h0(arrayList);
        ObserverWrapper observerWrapper2 = new ObserverWrapper(observer, iArrH0, strArrResolveViews);
        synchronized (this.observerMap) {
            observerWrapper = (ObserverWrapper) this.observerMap.f(observer, observerWrapper2);
        }
        if (observerWrapper == null && this.observedTableTracker.onAdded(Arrays.copyOf(iArrH0, iArrH0.length))) {
            syncTriggers$room_runtime_release();
        }
    }

    public void addWeakObserver(Observer observer) {
        kotlin.jvm.internal.m.g(observer, "observer");
        addObserver(new WeakObserver(this, observer));
    }

    public <T> LiveData createLiveData(String[] strArr, Callable<T> callable) {
        kotlin.jvm.internal.m.g(strArr, "tableNames");
        kotlin.jvm.internal.m.g(callable, "computeFunction");
        return createLiveData(strArr, false, callable);
    }

    public final boolean ensureInitialization$room_runtime_release() {
        if (!this.database.isOpenInternal()) {
            return false;
        }
        if (!this.initialized) {
            this.database.getOpenHelper().getWritableDatabase();
        }
        if (this.initialized) {
            return true;
        }
        Log.e(Room.LOG_TAG, "database is not initialized even though it is open");
        return false;
    }

    public final t0.l getCleanupStatement$room_runtime_release() {
        return this.cleanupStatement;
    }

    public final RoomDatabase getDatabase$room_runtime_release() {
        return this.database;
    }

    public final j.b getObserverMap$room_runtime_release() {
        return this.observerMap;
    }

    public final AtomicBoolean getPendingRefresh() {
        return this.pendingRefresh;
    }

    public final Map<String, Integer> getTableIdLookup$room_runtime_release() {
        return this.tableIdLookup;
    }

    public final String[] getTablesNames$room_runtime_release() {
        return this.tablesNames;
    }

    public final void internalInit$room_runtime_release(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "database");
        synchronized (this.trackerLock) {
            if (this.initialized) {
                Log.e(Room.LOG_TAG, "Invalidation tracker is initialized twice :/.");
                return;
            }
            hVar.execSQL("PRAGMA temp_store = MEMORY;");
            hVar.execSQL("PRAGMA recursive_triggers='ON';");
            hVar.execSQL(CREATE_TRACKING_TABLE_SQL);
            syncTriggers$room_runtime_release(hVar);
            this.cleanupStatement = hVar.compileStatement(RESET_UPDATED_TABLES_SQL);
            this.initialized = true;
            ea.v vVar = ea.v.a;
        }
    }

    public final void notifyObserversByTableNames(String... strArr) {
        kotlin.jvm.internal.m.g(strArr, "tables");
        synchronized (this.observerMap) {
            for (Map.Entry entry : this.observerMap) {
                kotlin.jvm.internal.m.f(entry, "(observer, wrapper)");
                Observer observer = (Observer) entry.getKey();
                ObserverWrapper observerWrapper = (ObserverWrapper) entry.getValue();
                if (!observer.isRemote$room_runtime_release()) {
                    observerWrapper.notifyByTableNames$room_runtime_release(strArr);
                }
            }
            ea.v vVar = ea.v.a;
        }
    }

    public void refreshVersionsAsync() {
        if (this.pendingRefresh.compareAndSet(false, true)) {
            AutoCloser autoCloser = this.autoCloser;
            if (autoCloser != null) {
                autoCloser.incrementCountAndEnsureDbIsOpen();
            }
            this.database.getQueryExecutor().execute(this.refreshRunnable);
        }
    }

    public void refreshVersionsSync() {
        AutoCloser autoCloser = this.autoCloser;
        if (autoCloser != null) {
            autoCloser.incrementCountAndEnsureDbIsOpen();
        }
        syncTriggers$room_runtime_release();
        this.refreshRunnable.run();
    }

    public void removeObserver(Observer observer) {
        ObserverWrapper observerWrapper;
        kotlin.jvm.internal.m.g(observer, "observer");
        synchronized (this.observerMap) {
            observerWrapper = (ObserverWrapper) this.observerMap.g(observer);
        }
        if (observerWrapper != null) {
            ObservedTableTracker observedTableTracker = this.observedTableTracker;
            int[] tableIds$room_runtime_release = observerWrapper.getTableIds$room_runtime_release();
            if (observedTableTracker.onRemoved(Arrays.copyOf(tableIds$room_runtime_release, tableIds$room_runtime_release.length))) {
                syncTriggers$room_runtime_release();
            }
        }
    }

    public final void setAutoCloser$room_runtime_release(AutoCloser autoCloser) {
        kotlin.jvm.internal.m.g(autoCloser, "autoCloser");
        this.autoCloser = autoCloser;
        autoCloser.setAutoCloseCallback(new Runnable() { // from class: androidx.room.c
            @Override // java.lang.Runnable
            public final void run() {
                this.a.onAutoCloseCallback();
            }
        });
    }

    public final void setCleanupStatement$room_runtime_release(t0.l lVar) {
        this.cleanupStatement = lVar;
    }

    public final void startMultiInstanceInvalidation$room_runtime_release(Context context, String str, Intent intent) {
        kotlin.jvm.internal.m.g(context, "context");
        kotlin.jvm.internal.m.g(str, "name");
        kotlin.jvm.internal.m.g(intent, "serviceIntent");
        this.multiInstanceInvalidationClient = new MultiInstanceInvalidationClient(context, str, intent, this, this.database.getQueryExecutor());
    }

    public final void stopMultiInstanceInvalidation$room_runtime_release() {
        MultiInstanceInvalidationClient multiInstanceInvalidationClient = this.multiInstanceInvalidationClient;
        if (multiInstanceInvalidationClient != null) {
            multiInstanceInvalidationClient.stop();
        }
        this.multiInstanceInvalidationClient = null;
    }

    public final void syncTriggers$room_runtime_release(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "database");
        if (hVar.inTransaction()) {
            return;
        }
        try {
            Lock closeLock$room_runtime_release = this.database.getCloseLock$room_runtime_release();
            closeLock$room_runtime_release.lock();
            try {
                synchronized (this.syncTriggersLock) {
                    int[] tablesToSync = this.observedTableTracker.getTablesToSync();
                    if (tablesToSync == null) {
                        closeLock$room_runtime_release.unlock();
                        return;
                    }
                    Companion.beginTransactionInternal$room_runtime_release(hVar);
                    try {
                        int length = tablesToSync.length;
                        int i = 0;
                        int i2 = 0;
                        while (i < length) {
                            int i3 = tablesToSync[i];
                            int i4 = i2 + 1;
                            if (i3 == 1) {
                                startTrackingTable(hVar, i2);
                            } else if (i3 == 2) {
                                stopTrackingTable(hVar, i2);
                            }
                            i++;
                            i2 = i4;
                        }
                        hVar.setTransactionSuccessful();
                        hVar.endTransaction();
                        ea.v vVar = ea.v.a;
                        closeLock$room_runtime_release.unlock();
                    } catch (Throwable th) {
                        hVar.endTransaction();
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                closeLock$room_runtime_release.unlock();
                throw th2;
            }
        } catch (SQLiteException e) {
            Log.e(Room.LOG_TAG, "Cannot run invalidation tracker. Is the db closed?", e);
        } catch (IllegalStateException e2) {
            Log.e(Room.LOG_TAG, "Cannot run invalidation tracker. Is the db closed?", e2);
        }
    }

    public <T> LiveData createLiveData(String[] strArr, boolean z, Callable<T> callable) {
        kotlin.jvm.internal.m.g(strArr, "tableNames");
        kotlin.jvm.internal.m.g(callable, "computeFunction");
        return this.invalidationLiveDataContainer.create(validateAndResolveTableNames(strArr), z, callable);
    }

    public final void syncTriggers$room_runtime_release() {
        if (this.database.isOpenInternal()) {
            syncTriggers$room_runtime_release(this.database.getOpenHelper().getWritableDatabase());
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InvalidationTracker(RoomDatabase roomDatabase, String... strArr) {
        this(roomDatabase, h0.h(), h0.h(), (String[]) Arrays.copyOf(strArr, strArr.length));
        kotlin.jvm.internal.m.g(roomDatabase, "database");
        kotlin.jvm.internal.m.g(strArr, "tableNames");
    }
}
