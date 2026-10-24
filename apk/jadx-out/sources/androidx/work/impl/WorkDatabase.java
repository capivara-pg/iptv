package androidx.work.impl;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.Preference;
import androidx.work.impl.model.PreferenceDao;
import androidx.work.impl.model.RawWorkInfoDao;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkProgress;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTag;
import androidx.work.impl.model.WorkTagDao;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@TypeConverters({androidx.work.b.class, h1.g.class})
@Database(entities = {Dependency.class, WorkSpec.class, WorkTag.class, SystemIdInfo.class, WorkName.class, WorkProgress.class, Preference.class}, version = 12)
public abstract class WorkDatabase extends RoomDatabase {
    private static final String PRUNE_SQL_FORMAT_PREFIX = "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (period_start_time + minimum_retention_duration) < ";
    private static final String PRUNE_SQL_FORMAT_SUFFIX = " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    private static final long PRUNE_THRESHOLD_MILLIS = TimeUnit.DAYS.toMillis(1);

    class a implements t0.i.c {
        final /* synthetic */ Context a;

        a(Context context) {
            this.a = context;
        }

        public t0.i create(t0.i.b bVar) {
            t0.i.b.a aVarA = t0.i.b.a(this.a);
            aVarA.c(bVar.b).b(bVar.c).d(true);
            return new u0.f().create(aVarA.a());
        }
    }

    class b extends RoomDatabase.Callback {
        b() {
        }

        @Override // androidx.room.RoomDatabase.Callback
        public void onOpen(t0.h hVar) {
            super.onOpen(hVar);
            hVar.beginTransaction();
            try {
                hVar.execSQL(WorkDatabase.getPruneSQL());
                hVar.setTransactionSuccessful();
            } finally {
                hVar.endTransaction();
            }
        }
    }

    public static WorkDatabase create(final Context context, Executor queryExecutor, boolean useTestDatabase) {
        RoomDatabase.Builder builderDatabaseBuilder;
        if (useTestDatabase) {
            builderDatabaseBuilder = Room.inMemoryDatabaseBuilder(context, WorkDatabase.class).allowMainThreadQueries();
        } else {
            builderDatabaseBuilder = Room.databaseBuilder(context, WorkDatabase.class, i.d());
            builderDatabaseBuilder.openHelperFactory(new a(context));
        }
        return (WorkDatabase) builderDatabaseBuilder.setQueryExecutor(queryExecutor).addCallback(generateCleanupCallback()).addMigrations(h.a).addMigrations(new h.C0062h(context, 2, 3)).addMigrations(h.b).addMigrations(h.c).addMigrations(new h.C0062h(context, 5, 6)).addMigrations(h.d).addMigrations(h.e).addMigrations(h.f).addMigrations(new h.i(context)).addMigrations(new h.C0062h(context, 10, 11)).addMigrations(h.g).fallbackToDestructiveMigration().build();
    }

    static RoomDatabase.Callback generateCleanupCallback() {
        return new b();
    }

    static long getPruneDate() {
        return System.currentTimeMillis() - PRUNE_THRESHOLD_MILLIS;
    }

    static String getPruneSQL() {
        return PRUNE_SQL_FORMAT_PREFIX + getPruneDate() + PRUNE_SQL_FORMAT_SUFFIX;
    }

    public abstract DependencyDao dependencyDao();

    public abstract PreferenceDao preferenceDao();

    public abstract RawWorkInfoDao rawWorkInfoDao();

    public abstract SystemIdInfoDao systemIdInfoDao();

    public abstract WorkNameDao workNameDao();

    public abstract WorkProgressDao workProgressDao();

    public abstract WorkSpecDao workSpecDao();

    public abstract WorkTagDao workTagDao();
}
