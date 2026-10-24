package androidx.room;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteTransactionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class AutoClosingRoomOpenHelper implements t0.i, DelegatingOpenHelper {
    public final AutoCloser autoCloser;
    private final AutoClosingSupportSQLiteDatabase autoClosingDb;
    private final t0.i delegate;

    public static final class AutoClosingSupportSQLiteDatabase implements t0.h {
        private final AutoCloser autoCloser;

        public AutoClosingSupportSQLiteDatabase(AutoCloser autoCloser) {
            kotlin.jvm.internal.m.g(autoCloser, "autoCloser");
            this.autoCloser = autoCloser;
        }

        public void beginTransaction() {
            try {
                this.autoCloser.incrementCountAndEnsureDbIsOpen().beginTransaction();
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void beginTransactionNonExclusive() {
            try {
                this.autoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionNonExclusive();
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
            kotlin.jvm.internal.m.g(sQLiteTransactionListener, "transactionListener");
            try {
                this.autoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionWithListener(sQLiteTransactionListener);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
            kotlin.jvm.internal.m.g(sQLiteTransactionListener, "transactionListener");
            try {
                this.autoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionWithListenerNonExclusive(sQLiteTransactionListener);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void close() {
            this.autoCloser.closeDatabaseIfOpen();
        }

        public t0.l compileStatement(String str) {
            kotlin.jvm.internal.m.g(str, "sql");
            return new AutoClosingSupportSqliteStatement(str, this.autoCloser);
        }

        public int delete(String str, String str2, Object[] objArr) {
            kotlin.jvm.internal.m.g(str, "table");
            return ((Number) this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$delete$1(str, str2, objArr))).intValue();
        }

        public void disableWriteAheadLogging() {
            throw new UnsupportedOperationException("Enable/disable write ahead logging on the OpenHelper instead of on the database directly.");
        }

        public boolean enableWriteAheadLogging() {
            throw new UnsupportedOperationException("Enable/disable write ahead logging on the OpenHelper instead of on the database directly.");
        }

        public void endTransaction() {
            if (this.autoCloser.getDelegateDatabase$room_runtime_release() == null) {
                throw new IllegalStateException("End transaction called but delegateDb is null".toString());
            }
            try {
                t0.h delegateDatabase$room_runtime_release = this.autoCloser.getDelegateDatabase$room_runtime_release();
                kotlin.jvm.internal.m.d(delegateDatabase$room_runtime_release);
                delegateDatabase$room_runtime_release.endTransaction();
            } finally {
                this.autoCloser.decrementCountAndScheduleClose();
            }
        }

        public /* bridge */ /* synthetic */ void execPerConnectionSQL(String str, Object[] objArr) {
            t0.g.a(this, str, objArr);
        }

        public void execSQL(String str) {
            kotlin.jvm.internal.m.g(str, "sql");
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$execSQL$1(str));
        }

        public List<Pair<String, String>> getAttachedDbs() {
            return (List) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$attachedDbs$1.INSTANCE);
        }

        public long getMaximumSize() {
            return ((Number) this.autoCloser.executeRefCountingFunction(new kotlin.jvm.internal.v() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$maximumSize$1
                public Object get(Object obj) {
                    return Long.valueOf(((t0.h) obj).getMaximumSize());
                }
            })).longValue();
        }

        public long getPageSize() {
            return ((Number) this.autoCloser.executeRefCountingFunction(new kotlin.jvm.internal.p() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$pageSize$1
                public Object get(Object obj) {
                    return Long.valueOf(((t0.h) obj).getPageSize());
                }

                public void set(Object obj, Object obj2) {
                    ((t0.h) obj).setPageSize(((Number) obj2).longValue());
                }
            })).longValue();
        }

        public String getPath() {
            return (String) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$path$1.INSTANCE);
        }

        public int getVersion() {
            return ((Number) this.autoCloser.executeRefCountingFunction(new kotlin.jvm.internal.p() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$version$1
                public Object get(Object obj) {
                    return Integer.valueOf(((t0.h) obj).getVersion());
                }

                public void set(Object obj, Object obj2) {
                    ((t0.h) obj).setVersion(((Number) obj2).intValue());
                }
            })).intValue();
        }

        public boolean inTransaction() {
            if (this.autoCloser.getDelegateDatabase$room_runtime_release() == null) {
                return false;
            }
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$inTransaction$1.INSTANCE)).booleanValue();
        }

        public long insert(String str, int i, ContentValues contentValues) {
            kotlin.jvm.internal.m.g(str, "table");
            kotlin.jvm.internal.m.g(contentValues, "values");
            return ((Number) this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$insert$1(str, i, contentValues))).longValue();
        }

        public boolean isDatabaseIntegrityOk() {
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isDatabaseIntegrityOk$1.INSTANCE)).booleanValue();
        }

        public boolean isDbLockedByCurrentThread() {
            if (this.autoCloser.getDelegateDatabase$room_runtime_release() == null) {
                return false;
            }
            return ((Boolean) this.autoCloser.executeRefCountingFunction(new kotlin.jvm.internal.v() { // from class: androidx.room.AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isDbLockedByCurrentThread$1
                public Object get(Object obj) {
                    return Boolean.valueOf(((t0.h) obj).isDbLockedByCurrentThread());
                }
            })).booleanValue();
        }

        public /* bridge */ /* synthetic */ boolean isExecPerConnectionSQLSupported() {
            return t0.g.b(this);
        }

        public boolean isOpen() {
            t0.h delegateDatabase$room_runtime_release = this.autoCloser.getDelegateDatabase$room_runtime_release();
            if (delegateDatabase$room_runtime_release == null) {
                return false;
            }
            return delegateDatabase$room_runtime_release.isOpen();
        }

        public boolean isReadOnly() {
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isReadOnly$1.INSTANCE)).booleanValue();
        }

        public boolean isWriteAheadLoggingEnabled() {
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isWriteAheadLoggingEnabled$1.INSTANCE)).booleanValue();
        }

        public boolean needUpgrade(int i) {
            return ((Boolean) this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$needUpgrade$1(i))).booleanValue();
        }

        public final void pokeOpen() {
            this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$pokeOpen$1.INSTANCE);
        }

        public Cursor query(String str) {
            kotlin.jvm.internal.m.g(str, "query");
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(str), this.autoCloser);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void setForeignKeyConstraintsEnabled(boolean z) {
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setForeignKeyConstraintsEnabled$1(z));
        }

        public void setLocale(Locale locale) {
            kotlin.jvm.internal.m.g(locale, "locale");
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setLocale$1(locale));
        }

        public void setMaxSqlCacheSize(int i) {
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setMaxSqlCacheSize$1(i));
        }

        public long setMaximumSize(long j) {
            return ((Number) this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setMaximumSize$1(j))).longValue();
        }

        public void setPageSize(long j) {
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$pageSize$2(j));
        }

        public void setTransactionSuccessful() {
            ea.v vVar;
            t0.h delegateDatabase$room_runtime_release = this.autoCloser.getDelegateDatabase$room_runtime_release();
            if (delegateDatabase$room_runtime_release != null) {
                delegateDatabase$room_runtime_release.setTransactionSuccessful();
                vVar = ea.v.a;
            } else {
                vVar = null;
            }
            if (vVar == null) {
                throw new IllegalStateException("setTransactionSuccessful called but delegateDb is null".toString());
            }
        }

        public void setVersion(int i) {
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$version$2(i));
        }

        public int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
            kotlin.jvm.internal.m.g(str, "table");
            kotlin.jvm.internal.m.g(contentValues, "values");
            return ((Number) this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$update$1(str, i, contentValues, str2, objArr))).intValue();
        }

        public boolean yieldIfContendedSafely() {
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$yieldIfContendedSafely$1.INSTANCE)).booleanValue();
        }

        public void execSQL(String str, Object[] objArr) {
            kotlin.jvm.internal.m.g(str, "sql");
            kotlin.jvm.internal.m.g(objArr, "bindArgs");
            this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$execSQL$2(str, objArr));
        }

        public boolean yieldIfContendedSafely(long j) {
            return ((Boolean) this.autoCloser.executeRefCountingFunction(AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$yieldIfContendedSafely$2.INSTANCE)).booleanValue();
        }

        public Cursor query(String str, Object[] objArr) {
            kotlin.jvm.internal.m.g(str, "query");
            kotlin.jvm.internal.m.g(objArr, "bindArgs");
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(str, objArr), this.autoCloser);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public Cursor query(t0.k kVar) {
            kotlin.jvm.internal.m.g(kVar, "query");
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(kVar), this.autoCloser);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public Cursor query(t0.k kVar, CancellationSignal cancellationSignal) {
            kotlin.jvm.internal.m.g(kVar, "query");
            try {
                return new KeepAliveCursor(this.autoCloser.incrementCountAndEnsureDbIsOpen().query(kVar, cancellationSignal), this.autoCloser);
            } catch (Throwable th) {
                this.autoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class AutoClosingSupportSqliteStatement implements t0.l {
        private final AutoCloser autoCloser;
        private final ArrayList<Object> binds;
        private final String sql;

        public AutoClosingSupportSqliteStatement(String str, AutoCloser autoCloser) {
            kotlin.jvm.internal.m.g(str, "sql");
            kotlin.jvm.internal.m.g(autoCloser, "autoCloser");
            this.sql = str;
            this.autoCloser = autoCloser;
            this.binds = new ArrayList<>();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void doBinds(t0.l lVar) {
            Iterator<T> it = this.binds.iterator();
            int i = 0;
            while (it.hasNext()) {
                it.next();
                int i2 = i + 1;
                if (i < 0) {
                    fa.p.p();
                }
                Object obj = this.binds.get(i);
                if (obj == null) {
                    lVar.bindNull(i2);
                } else if (obj instanceof Long) {
                    lVar.bindLong(i2, ((Number) obj).longValue());
                } else if (obj instanceof Double) {
                    lVar.bindDouble(i2, ((Number) obj).doubleValue());
                } else if (obj instanceof String) {
                    lVar.bindString(i2, (String) obj);
                } else if (obj instanceof byte[]) {
                    lVar.bindBlob(i2, (byte[]) obj);
                }
                i = i2;
            }
        }

        private final <T> T executeSqliteStatementWithRefCount(pa.l lVar) {
            return (T) this.autoCloser.executeRefCountingFunction(new AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeSqliteStatementWithRefCount$1(this, lVar));
        }

        private final void saveBinds(int i, Object obj) {
            int size;
            int i2 = i - 1;
            if (i2 >= this.binds.size() && (size = this.binds.size()) <= i2) {
                while (true) {
                    this.binds.add(null);
                    if (size == i2) {
                        break;
                    } else {
                        size++;
                    }
                }
            }
            this.binds.set(i2, obj);
        }

        public void bindBlob(int i, byte[] bArr) {
            kotlin.jvm.internal.m.g(bArr, "value");
            saveBinds(i, bArr);
        }

        public void bindDouble(int i, double d) {
            saveBinds(i, Double.valueOf(d));
        }

        public void bindLong(int i, long j) {
            saveBinds(i, Long.valueOf(j));
        }

        public void bindNull(int i) {
            saveBinds(i, null);
        }

        public void bindString(int i, String str) {
            kotlin.jvm.internal.m.g(str, "value");
            saveBinds(i, str);
        }

        public void clearBindings() {
            this.binds.clear();
        }

        public void close() {
        }

        public void execute() {
            executeSqliteStatementWithRefCount(AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$execute$1.INSTANCE);
        }

        public long executeInsert() {
            return ((Number) executeSqliteStatementWithRefCount(AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1.INSTANCE)).longValue();
        }

        public int executeUpdateDelete() {
            return ((Number) executeSqliteStatementWithRefCount(AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeUpdateDelete$1.INSTANCE)).intValue();
        }

        public long simpleQueryForLong() {
            return ((Number) executeSqliteStatementWithRefCount(AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$simpleQueryForLong$1.INSTANCE)).longValue();
        }

        public String simpleQueryForString() {
            return (String) executeSqliteStatementWithRefCount(AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$simpleQueryForString$1.INSTANCE);
        }
    }

    private static final class KeepAliveCursor implements Cursor {
        private final AutoCloser autoCloser;
        private final Cursor delegate;

        public KeepAliveCursor(Cursor cursor, AutoCloser autoCloser) {
            kotlin.jvm.internal.m.g(cursor, "delegate");
            kotlin.jvm.internal.m.g(autoCloser, "autoCloser");
            this.delegate = cursor;
            this.autoCloser = autoCloser;
        }

        @Override // android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
            this.autoCloser.decrementCountAndScheduleClose();
        }

        @Override // android.database.Cursor
        public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {
            this.delegate.copyStringToBuffer(i, charArrayBuffer);
        }

        @Override // android.database.Cursor
        public void deactivate() {
            this.delegate.deactivate();
        }

        @Override // android.database.Cursor
        public byte[] getBlob(int i) {
            return this.delegate.getBlob(i);
        }

        @Override // android.database.Cursor
        public int getColumnCount() {
            return this.delegate.getColumnCount();
        }

        @Override // android.database.Cursor
        public int getColumnIndex(String str) {
            return this.delegate.getColumnIndex(str);
        }

        @Override // android.database.Cursor
        public int getColumnIndexOrThrow(String str) {
            return this.delegate.getColumnIndexOrThrow(str);
        }

        @Override // android.database.Cursor
        public String getColumnName(int i) {
            return this.delegate.getColumnName(i);
        }

        @Override // android.database.Cursor
        public String[] getColumnNames() {
            return this.delegate.getColumnNames();
        }

        @Override // android.database.Cursor
        public int getCount() {
            return this.delegate.getCount();
        }

        @Override // android.database.Cursor
        public double getDouble(int i) {
            return this.delegate.getDouble(i);
        }

        @Override // android.database.Cursor
        public Bundle getExtras() {
            return this.delegate.getExtras();
        }

        @Override // android.database.Cursor
        public float getFloat(int i) {
            return this.delegate.getFloat(i);
        }

        @Override // android.database.Cursor
        public int getInt(int i) {
            return this.delegate.getInt(i);
        }

        @Override // android.database.Cursor
        public long getLong(int i) {
            return this.delegate.getLong(i);
        }

        @Override // android.database.Cursor
        public Uri getNotificationUri() {
            return t0.c.a(this.delegate);
        }

        @Override // android.database.Cursor
        public List<Uri> getNotificationUris() {
            return t0.f.a(this.delegate);
        }

        @Override // android.database.Cursor
        public int getPosition() {
            return this.delegate.getPosition();
        }

        @Override // android.database.Cursor
        public short getShort(int i) {
            return this.delegate.getShort(i);
        }

        @Override // android.database.Cursor
        public String getString(int i) {
            return this.delegate.getString(i);
        }

        @Override // android.database.Cursor
        public int getType(int i) {
            return this.delegate.getType(i);
        }

        @Override // android.database.Cursor
        public boolean getWantsAllOnMoveCalls() {
            return this.delegate.getWantsAllOnMoveCalls();
        }

        @Override // android.database.Cursor
        public boolean isAfterLast() {
            return this.delegate.isAfterLast();
        }

        @Override // android.database.Cursor
        public boolean isBeforeFirst() {
            return this.delegate.isBeforeFirst();
        }

        @Override // android.database.Cursor
        public boolean isClosed() {
            return this.delegate.isClosed();
        }

        @Override // android.database.Cursor
        public boolean isFirst() {
            return this.delegate.isFirst();
        }

        @Override // android.database.Cursor
        public boolean isLast() {
            return this.delegate.isLast();
        }

        @Override // android.database.Cursor
        public boolean isNull(int i) {
            return this.delegate.isNull(i);
        }

        @Override // android.database.Cursor
        public boolean move(int i) {
            return this.delegate.move(i);
        }

        @Override // android.database.Cursor
        public boolean moveToFirst() {
            return this.delegate.moveToFirst();
        }

        @Override // android.database.Cursor
        public boolean moveToLast() {
            return this.delegate.moveToLast();
        }

        @Override // android.database.Cursor
        public boolean moveToNext() {
            return this.delegate.moveToNext();
        }

        @Override // android.database.Cursor
        public boolean moveToPosition(int i) {
            return this.delegate.moveToPosition(i);
        }

        @Override // android.database.Cursor
        public boolean moveToPrevious() {
            return this.delegate.moveToPrevious();
        }

        @Override // android.database.Cursor
        public void registerContentObserver(ContentObserver contentObserver) {
            this.delegate.registerContentObserver(contentObserver);
        }

        @Override // android.database.Cursor
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            this.delegate.registerDataSetObserver(dataSetObserver);
        }

        @Override // android.database.Cursor
        public boolean requery() {
            return this.delegate.requery();
        }

        @Override // android.database.Cursor
        public Bundle respond(Bundle bundle) {
            return this.delegate.respond(bundle);
        }

        @Override // android.database.Cursor
        public void setExtras(Bundle bundle) {
            kotlin.jvm.internal.m.g(bundle, "extras");
            t0.e.a(this.delegate, bundle);
        }

        @Override // android.database.Cursor
        public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
            this.delegate.setNotificationUri(contentResolver, uri);
        }

        @Override // android.database.Cursor
        public void setNotificationUris(ContentResolver contentResolver, List<? extends Uri> list) {
            kotlin.jvm.internal.m.g(contentResolver, "cr");
            kotlin.jvm.internal.m.g(list, "uris");
            t0.f.b(this.delegate, contentResolver, list);
        }

        @Override // android.database.Cursor
        public void unregisterContentObserver(ContentObserver contentObserver) {
            this.delegate.unregisterContentObserver(contentObserver);
        }

        @Override // android.database.Cursor
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            this.delegate.unregisterDataSetObserver(dataSetObserver);
        }
    }

    public AutoClosingRoomOpenHelper(t0.i iVar, AutoCloser autoCloser) {
        kotlin.jvm.internal.m.g(iVar, "delegate");
        kotlin.jvm.internal.m.g(autoCloser, "autoCloser");
        this.delegate = iVar;
        this.autoCloser = autoCloser;
        autoCloser.init(getDelegate());
        this.autoClosingDb = new AutoClosingSupportSQLiteDatabase(autoCloser);
    }

    public void close() {
        this.autoClosingDb.close();
    }

    public String getDatabaseName() {
        return this.delegate.getDatabaseName();
    }

    @Override // androidx.room.DelegatingOpenHelper
    public t0.i getDelegate() {
        return this.delegate;
    }

    public t0.h getReadableDatabase() {
        this.autoClosingDb.pokeOpen();
        return this.autoClosingDb;
    }

    public t0.h getWritableDatabase() {
        this.autoClosingDb.pokeOpen();
        return this.autoClosingDb;
    }

    public void setWriteAheadLoggingEnabled(boolean z) {
        this.delegate.setWriteAheadLoggingEnabled(z);
    }
}
