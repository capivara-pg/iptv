package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.Looper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import fa.h0;
import fa.n0;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public abstract class RoomDatabase {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean allowMainThreadQueries;
    private AutoCloser autoCloser;
    private final Map<String, Object> backingFieldMap;
    private t0.i internalOpenHelper;
    private Executor internalQueryExecutor;
    private Executor internalTransactionExecutor;
    protected List<? extends Callback> mCallbacks;
    protected volatile t0.h mDatabase;
    private final Map<Class<?>, Object> typeConverters;
    private boolean writeAheadLoggingEnabled;
    private final InvalidationTracker invalidationTracker = createInvalidationTracker();
    private Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs = new LinkedHashMap();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ThreadLocal<Integer> suspendingTransactionId = new ThreadLocal<>();

    public static class Builder<T extends RoomDatabase> {
        private boolean allowDestructiveMigrationOnDowngrade;
        private boolean allowMainThreadQueries;
        private TimeUnit autoCloseTimeUnit;
        private long autoCloseTimeout;
        private List<AutoMigrationSpec> autoMigrationSpecs;
        private final List<Callback> callbacks;
        private final Context context;
        private String copyFromAssetPath;
        private File copyFromFile;
        private Callable<InputStream> copyFromInputStream;
        private t0.i.c factory;
        private JournalMode journalMode;
        private final Class<T> klass;
        private final MigrationContainer migrationContainer;
        private Set<Integer> migrationStartAndEndVersions;
        private Set<Integer> migrationsNotRequiredFrom;
        private Intent multiInstanceInvalidationIntent;
        private final String name;
        private PrepackagedDatabaseCallback prepackagedDatabaseCallback;
        private QueryCallback queryCallback;
        private Executor queryCallbackExecutor;
        private Executor queryExecutor;
        private boolean requireMigration;
        private Executor transactionExecutor;
        private final List<Object> typeConverters;

        public Builder(Context context, Class<T> cls, String str) {
            kotlin.jvm.internal.m.g(context, "context");
            kotlin.jvm.internal.m.g(cls, "klass");
            this.context = context;
            this.klass = cls;
            this.name = str;
            this.callbacks = new ArrayList();
            this.typeConverters = new ArrayList();
            this.autoMigrationSpecs = new ArrayList();
            this.journalMode = JournalMode.AUTOMATIC;
            this.requireMigration = true;
            this.autoCloseTimeout = -1L;
            this.migrationContainer = new MigrationContainer();
            this.migrationsNotRequiredFrom = new LinkedHashSet();
        }

        public Builder<T> addAutoMigrationSpec(AutoMigrationSpec autoMigrationSpec) {
            kotlin.jvm.internal.m.g(autoMigrationSpec, "autoMigrationSpec");
            this.autoMigrationSpecs.add(autoMigrationSpec);
            return this;
        }

        public Builder<T> addCallback(Callback callback) {
            kotlin.jvm.internal.m.g(callback, "callback");
            this.callbacks.add(callback);
            return this;
        }

        public Builder<T> addMigrations(Migration... migrationArr) {
            kotlin.jvm.internal.m.g(migrationArr, "migrations");
            if (this.migrationStartAndEndVersions == null) {
                this.migrationStartAndEndVersions = new HashSet();
            }
            for (Migration migration : migrationArr) {
                Set<Integer> set = this.migrationStartAndEndVersions;
                kotlin.jvm.internal.m.d(set);
                set.add(Integer.valueOf(migration.startVersion));
                Set<Integer> set2 = this.migrationStartAndEndVersions;
                kotlin.jvm.internal.m.d(set2);
                set2.add(Integer.valueOf(migration.endVersion));
            }
            this.migrationContainer.addMigrations((Migration[]) Arrays.copyOf(migrationArr, migrationArr.length));
            return this;
        }

        public Builder<T> addTypeConverter(Object obj) {
            kotlin.jvm.internal.m.g(obj, "typeConverter");
            this.typeConverters.add(obj);
            return this;
        }

        public Builder<T> allowMainThreadQueries() {
            this.allowMainThreadQueries = true;
            return this;
        }

        public T build() {
            u0.f queryInterceptorOpenHelperFactory;
            Executor executor = this.queryExecutor;
            if (executor == null && this.transactionExecutor == null) {
                Executor executorG = i.c.g();
                this.transactionExecutor = executorG;
                this.queryExecutor = executorG;
            } else if (executor != null && this.transactionExecutor == null) {
                this.transactionExecutor = executor;
            } else if (executor == null) {
                this.queryExecutor = this.transactionExecutor;
            }
            Set<Integer> set = this.migrationStartAndEndVersions;
            if (set != null) {
                kotlin.jvm.internal.m.d(set);
                Iterator<Integer> it = set.iterator();
                while (it.hasNext()) {
                    int iIntValue = it.next().intValue();
                    if (!(!this.migrationsNotRequiredFrom.contains(Integer.valueOf(iIntValue)))) {
                        throw new IllegalArgumentException(("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + iIntValue).toString());
                    }
                }
            }
            u0.f sQLiteCopyOpenHelperFactory = this.factory;
            if (sQLiteCopyOpenHelperFactory == null) {
                sQLiteCopyOpenHelperFactory = new u0.f();
            }
            if (sQLiteCopyOpenHelperFactory != null) {
                if (this.autoCloseTimeout > 0) {
                    if (this.name == null) {
                        throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.".toString());
                    }
                    long j = this.autoCloseTimeout;
                    TimeUnit timeUnit = this.autoCloseTimeUnit;
                    if (timeUnit == null) {
                        throw new IllegalArgumentException("Required value was null.".toString());
                    }
                    Executor executor2 = this.queryExecutor;
                    if (executor2 == null) {
                        throw new IllegalArgumentException("Required value was null.".toString());
                    }
                    sQLiteCopyOpenHelperFactory = new AutoClosingRoomOpenHelperFactory(sQLiteCopyOpenHelperFactory, new AutoCloser(j, timeUnit, executor2));
                }
                String str = this.copyFromAssetPath;
                if (str != null || this.copyFromFile != null || this.copyFromInputStream != null) {
                    if (this.name == null) {
                        throw new IllegalArgumentException("Cannot create from asset or file for an in-memory database.".toString());
                    }
                    int i = str == null ? 0 : 1;
                    File file = this.copyFromFile;
                    int i2 = file == null ? 0 : 1;
                    Callable<InputStream> callable = this.copyFromInputStream;
                    if (!((i + i2) + (callable == null ? 0 : 1) == 1)) {
                        throw new IllegalArgumentException("More than one of createFromAsset(), createFromInputStream(), and createFromFile() were called on this Builder, but the database can only be created using one of the three configurations.".toString());
                    }
                    sQLiteCopyOpenHelperFactory = new SQLiteCopyOpenHelperFactory(str, file, callable, sQLiteCopyOpenHelperFactory);
                }
            } else {
                sQLiteCopyOpenHelperFactory = null;
            }
            if (sQLiteCopyOpenHelperFactory == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            QueryCallback queryCallback = this.queryCallback;
            if (queryCallback != null) {
                Executor executor3 = this.queryCallbackExecutor;
                if (executor3 == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                if (queryCallback == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                queryInterceptorOpenHelperFactory = new QueryInterceptorOpenHelperFactory(sQLiteCopyOpenHelperFactory, executor3, queryCallback);
            } else {
                queryInterceptorOpenHelperFactory = sQLiteCopyOpenHelperFactory;
            }
            Context context = this.context;
            String str2 = this.name;
            MigrationContainer migrationContainer = this.migrationContainer;
            List<Callback> list = this.callbacks;
            boolean z = this.allowMainThreadQueries;
            JournalMode journalModeResolve$room_runtime_release = this.journalMode.resolve$room_runtime_release(context);
            Executor executor4 = this.queryExecutor;
            if (executor4 == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            Executor executor5 = this.transactionExecutor;
            if (executor5 == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(context, str2, (t0.i.c) queryInterceptorOpenHelperFactory, migrationContainer, (List<? extends Callback>) list, z, journalModeResolve$room_runtime_release, executor4, executor5, this.multiInstanceInvalidationIntent, this.requireMigration, this.allowDestructiveMigrationOnDowngrade, this.migrationsNotRequiredFrom, this.copyFromAssetPath, this.copyFromFile, this.copyFromInputStream, this.prepackagedDatabaseCallback, (List<? extends Object>) this.typeConverters, (List<? extends AutoMigrationSpec>) this.autoMigrationSpecs);
            T t = (T) Room.getGeneratedImplementation(this.klass, "_Impl");
            t.init(databaseConfiguration);
            return t;
        }

        public Builder<T> createFromAsset(String str) {
            kotlin.jvm.internal.m.g(str, "databaseFilePath");
            this.copyFromAssetPath = str;
            return this;
        }

        public Builder<T> createFromFile(File file) {
            kotlin.jvm.internal.m.g(file, "databaseFile");
            this.copyFromFile = file;
            return this;
        }

        public Builder<T> createFromInputStream(Callable<InputStream> callable) {
            kotlin.jvm.internal.m.g(callable, "inputStreamCallable");
            this.copyFromInputStream = callable;
            return this;
        }

        public Builder<T> enableMultiInstanceInvalidation() {
            this.multiInstanceInvalidationIntent = this.name != null ? new Intent(this.context, (Class<?>) MultiInstanceInvalidationService.class) : null;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigration() {
            this.requireMigration = false;
            this.allowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public Builder<T> fallbackToDestructiveMigrationFrom(int... iArr) {
            kotlin.jvm.internal.m.g(iArr, "startVersions");
            for (int i : iArr) {
                this.migrationsNotRequiredFrom.add(Integer.valueOf(i));
            }
            return this;
        }

        public Builder<T> fallbackToDestructiveMigrationOnDowngrade() {
            this.requireMigration = true;
            this.allowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public Builder<T> openHelperFactory(t0.i.c cVar) {
            this.factory = cVar;
            return this;
        }

        @ExperimentalRoomApi
        public Builder<T> setAutoCloseTimeout(long j, TimeUnit timeUnit) {
            kotlin.jvm.internal.m.g(timeUnit, "autoCloseTimeUnit");
            if (!(j >= 0)) {
                throw new IllegalArgumentException("autoCloseTimeout must be >= 0".toString());
            }
            this.autoCloseTimeout = j;
            this.autoCloseTimeUnit = timeUnit;
            return this;
        }

        public Builder<T> setJournalMode(JournalMode journalMode) {
            kotlin.jvm.internal.m.g(journalMode, "journalMode");
            this.journalMode = journalMode;
            return this;
        }

        @ExperimentalRoomApi
        public Builder<T> setMultiInstanceInvalidationServiceIntent(Intent intent) {
            kotlin.jvm.internal.m.g(intent, "invalidationServiceIntent");
            if (this.name == null) {
                intent = null;
            }
            this.multiInstanceInvalidationIntent = intent;
            return this;
        }

        public Builder<T> setQueryCallback(QueryCallback queryCallback, Executor executor) {
            kotlin.jvm.internal.m.g(queryCallback, "queryCallback");
            kotlin.jvm.internal.m.g(executor, "executor");
            this.queryCallback = queryCallback;
            this.queryCallbackExecutor = executor;
            return this;
        }

        public Builder<T> setQueryExecutor(Executor executor) {
            kotlin.jvm.internal.m.g(executor, "executor");
            this.queryExecutor = executor;
            return this;
        }

        public Builder<T> setTransactionExecutor(Executor executor) {
            kotlin.jvm.internal.m.g(executor, "executor");
            this.transactionExecutor = executor;
            return this;
        }

        public Builder<T> createFromAsset(String str, PrepackagedDatabaseCallback prepackagedDatabaseCallback) {
            kotlin.jvm.internal.m.g(str, "databaseFilePath");
            kotlin.jvm.internal.m.g(prepackagedDatabaseCallback, "callback");
            this.prepackagedDatabaseCallback = prepackagedDatabaseCallback;
            this.copyFromAssetPath = str;
            return this;
        }

        public Builder<T> createFromFile(File file, PrepackagedDatabaseCallback prepackagedDatabaseCallback) {
            kotlin.jvm.internal.m.g(file, "databaseFile");
            kotlin.jvm.internal.m.g(prepackagedDatabaseCallback, "callback");
            this.prepackagedDatabaseCallback = prepackagedDatabaseCallback;
            this.copyFromFile = file;
            return this;
        }

        public Builder<T> createFromInputStream(Callable<InputStream> callable, PrepackagedDatabaseCallback prepackagedDatabaseCallback) {
            kotlin.jvm.internal.m.g(callable, "inputStreamCallable");
            kotlin.jvm.internal.m.g(prepackagedDatabaseCallback, "callback");
            this.prepackagedDatabaseCallback = prepackagedDatabaseCallback;
            this.copyFromInputStream = callable;
            return this;
        }
    }

    public static abstract class Callback {
        public void onCreate(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "db");
        }

        public void onDestructiveMigration(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "db");
        }

        public void onOpen(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "db");
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        private final boolean isLowRamDevice(ActivityManager activityManager) {
            return t0.c.b(activityManager);
        }

        public final JournalMode resolve$room_runtime_release(Context context) {
            kotlin.jvm.internal.m.g(context, "context");
            if (this != AUTOMATIC) {
                return this;
            }
            Object systemService = context.getSystemService("activity");
            ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
            return (activityManager == null || isLowRamDevice(activityManager)) ? TRUNCATE : WRITE_AHEAD_LOGGING;
        }
    }

    public static abstract class PrepackagedDatabaseCallback {
        public void onOpenPrepackagedDatabase(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "db");
        }
    }

    public interface QueryCallback {
        void onQuery(String str, List<? extends Object> list);
    }

    /* JADX INFO: renamed from: androidx.room.RoomDatabase$beginTransaction$1, reason: invalid class name */
    static final class AnonymousClass1 extends kotlin.jvm.internal.n implements pa.l {
        AnonymousClass1() {
            super(1);
        }

        public final Object invoke(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "it");
            RoomDatabase.this.internalBeginTransaction();
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.room.RoomDatabase$endTransaction$1, reason: invalid class name and case insensitive filesystem */
    static final class C00671 extends kotlin.jvm.internal.n implements pa.l {
        C00671() {
            super(1);
        }

        public final Object invoke(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "it");
            RoomDatabase.this.internalEndTransaction();
            return null;
        }
    }

    public RoomDatabase() {
        Map<String, Object> mapSynchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        kotlin.jvm.internal.m.f(mapSynchronizedMap, "synchronizedMap(mutableMapOf())");
        this.backingFieldMap = mapSynchronizedMap;
        this.typeConverters = new LinkedHashMap();
    }

    protected static /* synthetic */ void getMCallbacks$annotations() {
    }

    protected static /* synthetic */ void getMDatabase$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void internalBeginTransaction() {
        assertNotMainThread();
        t0.h writableDatabase = getOpenHelper().getWritableDatabase();
        getInvalidationTracker().syncTriggers$room_runtime_release(writableDatabase);
        if (writableDatabase.isWriteAheadLoggingEnabled()) {
            writableDatabase.beginTransactionNonExclusive();
        } else {
            writableDatabase.beginTransaction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void internalEndTransaction() {
        getOpenHelper().getWritableDatabase().endTransaction();
        if (inTransaction()) {
            return;
        }
        getInvalidationTracker().refreshVersionsAsync();
    }

    public static /* synthetic */ void isOpen$annotations() {
    }

    public static /* synthetic */ void isOpenInternal$annotations() {
    }

    public static /* synthetic */ Cursor query$default(RoomDatabase roomDatabase, t0.k kVar, CancellationSignal cancellationSignal, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: query");
        }
        if ((i & 2) != 0) {
            cancellationSignal = null;
        }
        return roomDatabase.query(kVar, cancellationSignal);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> T unwrapOpenHelper(Class<T> cls, t0.i iVar) {
        if (cls.isInstance(iVar)) {
            return iVar;
        }
        if (iVar instanceof DelegatingOpenHelper) {
            return (T) unwrapOpenHelper(cls, ((DelegatingOpenHelper) iVar).getDelegate());
        }
        return null;
    }

    public void assertNotMainThread() {
        if (!this.allowMainThreadQueries && !(!isMainThread$room_runtime_release())) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.".toString());
        }
    }

    public void assertNotSuspendingTransaction() {
        if (!(inTransaction() || this.suspendingTransactionId.get() == null)) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.".toString());
        }
    }

    public void beginTransaction() {
        assertNotMainThread();
        AutoCloser autoCloser = this.autoCloser;
        if (autoCloser == null) {
            internalBeginTransaction();
        } else {
            autoCloser.executeRefCountingFunction(new AnonymousClass1());
        }
    }

    public abstract void clearAllTables();

    public void close() {
        if (isOpen()) {
            ReentrantReadWriteLock.WriteLock writeLock = this.readWriteLock.writeLock();
            kotlin.jvm.internal.m.f(writeLock, "readWriteLock.writeLock()");
            writeLock.lock();
            try {
                getInvalidationTracker().stopMultiInstanceInvalidation$room_runtime_release();
                getOpenHelper().close();
            } finally {
                writeLock.unlock();
            }
        }
    }

    public t0.l compileStatement(String str) {
        kotlin.jvm.internal.m.g(str, "sql");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return getOpenHelper().getWritableDatabase().compileStatement(str);
    }

    protected abstract InvalidationTracker createInvalidationTracker();

    protected abstract t0.i createOpenHelper(DatabaseConfiguration databaseConfiguration);

    public void endTransaction() {
        AutoCloser autoCloser = this.autoCloser;
        if (autoCloser == null) {
            internalEndTransaction();
        } else {
            autoCloser.executeRefCountingFunction(new C00671());
        }
    }

    protected final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> getAutoMigrationSpecs() {
        return this.autoMigrationSpecs;
    }

    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        kotlin.jvm.internal.m.g(map, "autoMigrationSpecs");
        return fa.p.h();
    }

    public final Map<String, Object> getBackingFieldMap() {
        return this.backingFieldMap;
    }

    public final Lock getCloseLock$room_runtime_release() {
        ReentrantReadWriteLock.ReadLock lock = this.readWriteLock.readLock();
        kotlin.jvm.internal.m.f(lock, "readWriteLock.readLock()");
        return lock;
    }

    public InvalidationTracker getInvalidationTracker() {
        return this.invalidationTracker;
    }

    public t0.i getOpenHelper() {
        t0.i iVar = this.internalOpenHelper;
        if (iVar != null) {
            return iVar;
        }
        kotlin.jvm.internal.m.x("internalOpenHelper");
        return null;
    }

    public Executor getQueryExecutor() {
        Executor executor = this.internalQueryExecutor;
        if (executor != null) {
            return executor;
        }
        kotlin.jvm.internal.m.x("internalQueryExecutor");
        return null;
    }

    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return n0.e();
    }

    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        return h0.h();
    }

    public final ThreadLocal<Integer> getSuspendingTransactionId() {
        return this.suspendingTransactionId;
    }

    public Executor getTransactionExecutor() {
        Executor executor = this.internalTransactionExecutor;
        if (executor != null) {
            return executor;
        }
        kotlin.jvm.internal.m.x("internalTransactionExecutor");
        return null;
    }

    public <T> T getTypeConverter(Class<T> cls) {
        kotlin.jvm.internal.m.g(cls, "klass");
        return (T) this.typeConverters.get(cls);
    }

    public boolean inTransaction() {
        return getOpenHelper().getWritableDatabase().inTransaction();
    }

    public void init(DatabaseConfiguration databaseConfiguration) {
        kotlin.jvm.internal.m.g(databaseConfiguration, "configuration");
        this.internalOpenHelper = createOpenHelper(databaseConfiguration);
        Set<Class<? extends AutoMigrationSpec>> requiredAutoMigrationSpecs = getRequiredAutoMigrationSpecs();
        BitSet bitSet = new BitSet();
        Iterator<Class<? extends AutoMigrationSpec>> it = requiredAutoMigrationSpecs.iterator();
        while (true) {
            int i = -1;
            if (it.hasNext()) {
                Class<? extends AutoMigrationSpec> next = it.next();
                int size = databaseConfiguration.autoMigrationSpecs.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i2 = size - 1;
                        if (next.isAssignableFrom(databaseConfiguration.autoMigrationSpecs.get(size).getClass())) {
                            bitSet.set(size);
                            i = size;
                            break;
                        } else if (i2 < 0) {
                            break;
                        } else {
                            size = i2;
                        }
                    }
                }
                if (!(i >= 0)) {
                    throw new IllegalArgumentException(("A required auto migration spec (" + next.getCanonicalName() + ") is missing in the database configuration.").toString());
                }
                this.autoMigrationSpecs.put(next, databaseConfiguration.autoMigrationSpecs.get(i));
            } else {
                int size2 = databaseConfiguration.autoMigrationSpecs.size() - 1;
                if (size2 >= 0) {
                    while (true) {
                        int i3 = size2 - 1;
                        if (!bitSet.get(size2)) {
                            throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.".toString());
                        }
                        if (i3 < 0) {
                            break;
                        } else {
                            size2 = i3;
                        }
                    }
                }
                for (Migration migration : getAutoMigrations(this.autoMigrationSpecs)) {
                    if (!databaseConfiguration.migrationContainer.contains(migration.startVersion, migration.endVersion)) {
                        databaseConfiguration.migrationContainer.addMigrations(migration);
                    }
                }
                SQLiteCopyOpenHelper sQLiteCopyOpenHelper = (SQLiteCopyOpenHelper) unwrapOpenHelper(SQLiteCopyOpenHelper.class, getOpenHelper());
                if (sQLiteCopyOpenHelper != null) {
                    sQLiteCopyOpenHelper.setDatabaseConfiguration(databaseConfiguration);
                }
                AutoClosingRoomOpenHelper autoClosingRoomOpenHelper = (AutoClosingRoomOpenHelper) unwrapOpenHelper(AutoClosingRoomOpenHelper.class, getOpenHelper());
                if (autoClosingRoomOpenHelper != null) {
                    this.autoCloser = autoClosingRoomOpenHelper.autoCloser;
                    getInvalidationTracker().setAutoCloser$room_runtime_release(autoClosingRoomOpenHelper.autoCloser);
                }
                boolean z = databaseConfiguration.journalMode == JournalMode.WRITE_AHEAD_LOGGING;
                getOpenHelper().setWriteAheadLoggingEnabled(z);
                this.mCallbacks = databaseConfiguration.callbacks;
                this.internalQueryExecutor = databaseConfiguration.queryExecutor;
                this.internalTransactionExecutor = new TransactionExecutor(databaseConfiguration.transactionExecutor);
                this.allowMainThreadQueries = databaseConfiguration.allowMainThreadQueries;
                this.writeAheadLoggingEnabled = z;
                if (databaseConfiguration.multiInstanceInvalidationServiceIntent != null) {
                    if (databaseConfiguration.name == null) {
                        throw new IllegalArgumentException("Required value was null.".toString());
                    }
                    getInvalidationTracker().startMultiInstanceInvalidation$room_runtime_release(databaseConfiguration.context, databaseConfiguration.name, databaseConfiguration.multiInstanceInvalidationServiceIntent);
                }
                Map<Class<?>, List<Class<?>>> requiredTypeConverters = getRequiredTypeConverters();
                BitSet bitSet2 = new BitSet();
                for (Map.Entry<Class<?>, List<Class<?>>> entry : requiredTypeConverters.entrySet()) {
                    Class<?> key = entry.getKey();
                    for (Class<?> cls : entry.getValue()) {
                        int size3 = databaseConfiguration.typeConverters.size() - 1;
                        if (size3 < 0) {
                            size3 = -1;
                            break;
                        }
                        while (true) {
                            int i4 = size3 - 1;
                            if (cls.isAssignableFrom(databaseConfiguration.typeConverters.get(size3).getClass())) {
                                bitSet2.set(size3);
                                break;
                            } else {
                                if (i4 < 0) {
                                    size3 = -1;
                                    break;
                                }
                                size3 = i4;
                            }
                        }
                        if (!(size3 >= 0)) {
                            throw new IllegalArgumentException(("A required type converter (" + cls + ") for " + key.getCanonicalName() + " is missing in the database configuration.").toString());
                        }
                        this.typeConverters.put(cls, databaseConfiguration.typeConverters.get(size3));
                    }
                }
                int size4 = databaseConfiguration.typeConverters.size() - 1;
                if (size4 < 0) {
                    return;
                }
                while (true) {
                    int i5 = size4 - 1;
                    if (!bitSet2.get(size4)) {
                        throw new IllegalArgumentException("Unexpected type converter " + databaseConfiguration.typeConverters.get(size4) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
                    }
                    if (i5 < 0) {
                        return;
                    } else {
                        size4 = i5;
                    }
                }
            }
        }
    }

    protected void internalInitInvalidationTracker(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        getInvalidationTracker().internalInit$room_runtime_release(hVar);
    }

    public final boolean isMainThread$room_runtime_release() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public boolean isOpen() {
        Boolean boolValueOf;
        boolean zIsOpen;
        AutoCloser autoCloser = this.autoCloser;
        if (autoCloser == null) {
            t0.h hVar = this.mDatabase;
            if (hVar != null) {
                zIsOpen = hVar.isOpen();
            } else {
                boolValueOf = null;
            }
            return kotlin.jvm.internal.m.b(boolValueOf, Boolean.TRUE);
        }
        zIsOpen = autoCloser.isActive();
        boolValueOf = Boolean.valueOf(zIsOpen);
        return kotlin.jvm.internal.m.b(boolValueOf, Boolean.TRUE);
    }

    public final boolean isOpenInternal() {
        t0.h hVar = this.mDatabase;
        return hVar != null && hVar.isOpen();
    }

    public final Cursor query(t0.k kVar) {
        kotlin.jvm.internal.m.g(kVar, "query");
        return query$default(this, kVar, null, 2, null);
    }

    public void runInTransaction(Runnable runnable) {
        kotlin.jvm.internal.m.g(runnable, "body");
        beginTransaction();
        try {
            runnable.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    protected final void setAutoMigrationSpecs(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        kotlin.jvm.internal.m.g(map, "<set-?>");
        this.autoMigrationSpecs = map;
    }

    public void setTransactionSuccessful() {
        getOpenHelper().getWritableDatabase().setTransactionSuccessful();
    }

    public static class MigrationContainer {
        private final Map<Integer, TreeMap<Integer, Migration>> migrations = new LinkedHashMap();

        private final void addMigration(Migration migration) {
            int i = migration.startVersion;
            int i2 = migration.endVersion;
            Map<Integer, TreeMap<Integer, Migration>> map = this.migrations;
            Integer numValueOf = Integer.valueOf(i);
            TreeMap<Integer, Migration> treeMap = map.get(numValueOf);
            if (treeMap == null) {
                treeMap = new TreeMap<>();
                map.put(numValueOf, treeMap);
            }
            TreeMap<Integer, Migration> treeMap2 = treeMap;
            if (treeMap2.containsKey(Integer.valueOf(i2))) {
                StringBuilder sb = new StringBuilder();
                sb.append("Overriding migration ");
                sb.append(treeMap2.get(Integer.valueOf(i2)));
                sb.append(" with ");
                sb.append(migration);
            }
            treeMap2.put(Integer.valueOf(i2), migration);
        }

        /* JADX WARN: Code duplicated, block: B:28:0x0056  */
        /* JADX WARN: Code duplicated, block: B:29:0x0058  */
        private final List<Migration> findUpMigrationPath(List<Migration> list, boolean z, int i, int i2) {
            boolean z2;
            boolean z3;
            do {
                z2 = true;
                if (!(!z ? i <= i2 : i >= i2)) {
                    return list;
                }
                TreeMap<Integer, Migration> treeMap = this.migrations.get(Integer.valueOf(i));
                if (treeMap != null) {
                    Iterator<Integer> it = (z ? treeMap.descendingKeySet() : treeMap.keySet()).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z2 = false;
                            break;
                        }
                        Integer next = it.next();
                        if (z) {
                            int i3 = i + 1;
                            kotlin.jvm.internal.m.f(next, "targetVersion");
                            int iIntValue = next.intValue();
                            if (i3 > iIntValue || iIntValue > i2) {
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                        } else {
                            kotlin.jvm.internal.m.f(next, "targetVersion");
                            int iIntValue2 = next.intValue();
                            if (i2 > iIntValue2 || iIntValue2 >= i) {
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                        }
                        if (z3) {
                            Migration migration = treeMap.get(next);
                            kotlin.jvm.internal.m.d(migration);
                            list.add(migration);
                            i = next.intValue();
                            break;
                        }
                    }
                } else {
                    return null;
                }
            } while (z2);
            return null;
        }

        public void addMigrations(Migration... migrationArr) {
            kotlin.jvm.internal.m.g(migrationArr, "migrations");
            for (Migration migration : migrationArr) {
                addMigration(migration);
            }
        }

        public final boolean contains(int i, int i2) {
            Map<Integer, Map<Integer, Migration>> migrations = getMigrations();
            if (!migrations.containsKey(Integer.valueOf(i))) {
                return false;
            }
            Map<Integer, Migration> mapH = migrations.get(Integer.valueOf(i));
            if (mapH == null) {
                mapH = h0.h();
            }
            return mapH.containsKey(Integer.valueOf(i2));
        }

        public List<Migration> findMigrationPath(int i, int i2) {
            if (i == i2) {
                return fa.p.h();
            }
            return findUpMigrationPath(new ArrayList(), i2 > i, i, i2);
        }

        public Map<Integer, Map<Integer, Migration>> getMigrations() {
            return this.migrations;
        }

        public void addMigrations(List<? extends Migration> list) {
            kotlin.jvm.internal.m.g(list, "migrations");
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                addMigration((Migration) it.next());
            }
        }
    }

    public Cursor query(String str, Object[] objArr) {
        kotlin.jvm.internal.m.g(str, "query");
        return getOpenHelper().getWritableDatabase().query(new t0.a(str, objArr));
    }

    public Cursor query(t0.k kVar, CancellationSignal cancellationSignal) {
        kotlin.jvm.internal.m.g(kVar, "query");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        if (cancellationSignal != null) {
            return getOpenHelper().getWritableDatabase().query(kVar, cancellationSignal);
        }
        return getOpenHelper().getWritableDatabase().query(kVar);
    }

    public <V> V runInTransaction(Callable<V> callable) {
        kotlin.jvm.internal.m.g(callable, "body");
        beginTransaction();
        try {
            V vCall = callable.call();
            setTransactionSuccessful();
            return vCall;
        } finally {
            endTransaction();
        }
    }
}
