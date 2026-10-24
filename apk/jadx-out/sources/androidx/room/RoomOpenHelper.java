package androidx.room;

import android.database.Cursor;
import androidx.room.migration.Migration;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RoomOpenHelper extends t0.i.a {
    public static final Companion Companion = new Companion(null);
    private DatabaseConfiguration configuration;
    private final Delegate delegate;
    private final String identityHash;
    private final String legacyHash;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final boolean hasEmptySchema$room_runtime_release(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "db");
            Cursor cursorQuery = hVar.query("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
            try {
                Cursor cursor = cursorQuery;
                boolean z = false;
                if (cursor.moveToFirst() && cursor.getInt(0) == 0) {
                    z = true;
                }
                na.c.a(cursorQuery, (Throwable) null);
                return z;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    na.c.a(cursorQuery, th);
                    throw th2;
                }
            }
        }

        public final boolean hasRoomMasterTable$room_runtime_release(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "db");
            Cursor cursorQuery = hVar.query("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'");
            try {
                Cursor cursor = cursorQuery;
                boolean z = false;
                if (cursor.moveToFirst() && cursor.getInt(0) != 0) {
                    z = true;
                }
                na.c.a(cursorQuery, (Throwable) null);
                return z;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    na.c.a(cursorQuery, th);
                    throw th2;
                }
            }
        }
    }

    public static abstract class Delegate {
        public final int version;

        public Delegate(int i) {
            this.version = i;
        }

        public abstract void createAllTables(t0.h hVar);

        public abstract void dropAllTables(t0.h hVar);

        public abstract void onCreate(t0.h hVar);

        public abstract void onOpen(t0.h hVar);

        public void onPostMigrate(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "database");
        }

        public void onPreMigrate(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "database");
        }

        public ValidationResult onValidateSchema(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "db");
            validateMigration(hVar);
            return new ValidationResult(true, null);
        }

        protected void validateMigration(t0.h hVar) {
            kotlin.jvm.internal.m.g(hVar, "db");
            throw new UnsupportedOperationException("validateMigration is deprecated");
        }
    }

    public static class ValidationResult {
        public final String expectedFoundMsg;
        public final boolean isValid;

        public ValidationResult(boolean z, String str) {
            this.isValid = z;
            this.expectedFoundMsg = str;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoomOpenHelper(DatabaseConfiguration databaseConfiguration, Delegate delegate, String str, String str2) {
        super(delegate.version);
        kotlin.jvm.internal.m.g(databaseConfiguration, "configuration");
        kotlin.jvm.internal.m.g(delegate, "delegate");
        kotlin.jvm.internal.m.g(str, "identityHash");
        kotlin.jvm.internal.m.g(str2, "legacyHash");
        this.configuration = databaseConfiguration;
        this.delegate = delegate;
        this.identityHash = str;
        this.legacyHash = str2;
    }

    private final void checkIdentity(t0.h hVar) {
        if (!Companion.hasRoomMasterTable$room_runtime_release(hVar)) {
            ValidationResult validationResultOnValidateSchema = this.delegate.onValidateSchema(hVar);
            if (validationResultOnValidateSchema.isValid) {
                this.delegate.onPostMigrate(hVar);
                updateIdentity(hVar);
                return;
            } else {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + validationResultOnValidateSchema.expectedFoundMsg);
            }
        }
        Cursor cursorQuery = hVar.query(new t0.a(RoomMasterTable.READ_QUERY));
        try {
            Cursor cursor = cursorQuery;
            String string = cursor.moveToFirst() ? cursor.getString(0) : null;
            na.c.a(cursorQuery, (Throwable) null);
            if (kotlin.jvm.internal.m.b(this.identityHash, string) || kotlin.jvm.internal.m.b(this.legacyHash, string)) {
                return;
            }
            throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: " + this.identityHash + ", found: " + string);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                na.c.a(cursorQuery, th);
                throw th2;
            }
        }
    }

    private final void createMasterTableIfNotExists(t0.h hVar) {
        hVar.execSQL(RoomMasterTable.CREATE_QUERY);
    }

    private final void updateIdentity(t0.h hVar) {
        createMasterTableIfNotExists(hVar);
        hVar.execSQL(RoomMasterTable.createInsertQuery(this.identityHash));
    }

    public void onConfigure(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        super.onConfigure(hVar);
    }

    public void onCreate(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        boolean zHasEmptySchema$room_runtime_release = Companion.hasEmptySchema$room_runtime_release(hVar);
        this.delegate.createAllTables(hVar);
        if (!zHasEmptySchema$room_runtime_release) {
            ValidationResult validationResultOnValidateSchema = this.delegate.onValidateSchema(hVar);
            if (!validationResultOnValidateSchema.isValid) {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + validationResultOnValidateSchema.expectedFoundMsg);
            }
        }
        updateIdentity(hVar);
        this.delegate.onCreate(hVar);
    }

    public void onDowngrade(t0.h hVar, int i, int i2) {
        kotlin.jvm.internal.m.g(hVar, "db");
        onUpgrade(hVar, i, i2);
    }

    public void onOpen(t0.h hVar) {
        kotlin.jvm.internal.m.g(hVar, "db");
        super.onOpen(hVar);
        checkIdentity(hVar);
        this.delegate.onOpen(hVar);
        this.configuration = null;
    }

    public void onUpgrade(t0.h hVar, int i, int i2) {
        List<Migration> listFindMigrationPath;
        kotlin.jvm.internal.m.g(hVar, "db");
        DatabaseConfiguration databaseConfiguration = this.configuration;
        boolean z = false;
        if (databaseConfiguration != null && (listFindMigrationPath = databaseConfiguration.migrationContainer.findMigrationPath(i, i2)) != null) {
            this.delegate.onPreMigrate(hVar);
            Iterator<T> it = listFindMigrationPath.iterator();
            while (it.hasNext()) {
                ((Migration) it.next()).migrate(hVar);
            }
            ValidationResult validationResultOnValidateSchema = this.delegate.onValidateSchema(hVar);
            if (!validationResultOnValidateSchema.isValid) {
                throw new IllegalStateException("Migration didn't properly handle: " + validationResultOnValidateSchema.expectedFoundMsg);
            }
            this.delegate.onPostMigrate(hVar);
            updateIdentity(hVar);
            z = true;
        }
        if (z) {
            return;
        }
        DatabaseConfiguration databaseConfiguration2 = this.configuration;
        if (databaseConfiguration2 != null && !databaseConfiguration2.isMigrationRequired(i, i2)) {
            this.delegate.dropAllTables(hVar);
            this.delegate.createAllTables(hVar);
            return;
        }
        throw new IllegalStateException("A migration from " + i + " to " + i2 + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RoomOpenHelper(DatabaseConfiguration databaseConfiguration, Delegate delegate, String str) {
        this(databaseConfiguration, delegate, "", str);
        kotlin.jvm.internal.m.g(databaseConfiguration, "configuration");
        kotlin.jvm.internal.m.g(delegate, "delegate");
        kotlin.jvm.internal.m.g(str, "legacyHash");
    }
}
