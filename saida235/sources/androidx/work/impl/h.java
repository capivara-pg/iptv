package androidx.work.impl;

import android.content.Context;
import android.os.Build;
import androidx.room.migration.Migration;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {
    public static Migration a = new a(1, 2);
    public static Migration b = new b(3, 4);
    public static Migration c = new c(4, 5);
    public static Migration d = new d(6, 7);
    public static Migration e = new e(7, 8);
    public static Migration f = new f(8, 9);
    public static Migration g = new g(11, 12);

    class a extends Migration {
        a(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(t0.h hVar) {
            hVar.execSQL("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            hVar.execSQL("INSERT INTO SystemIdInfo(work_spec_id, system_id) SELECT work_spec_id, alarm_id AS system_id FROM alarmInfo");
            hVar.execSQL("DROP TABLE IF EXISTS alarmInfo");
            hVar.execSQL("INSERT OR IGNORE INTO worktag(tag, work_spec_id) SELECT worker_class_name AS tag, id AS work_spec_id FROM workspec");
        }
    }

    class b extends Migration {
        b(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(t0.h hVar) {
            if (Build.VERSION.SDK_INT >= 23) {
                hVar.execSQL("UPDATE workspec SET schedule_requested_at=0 WHERE state NOT IN (2, 3, 5) AND schedule_requested_at=-1 AND interval_duration<>0");
            }
        }
    }

    class c extends Migration {
        c(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(t0.h hVar) {
            hVar.execSQL("ALTER TABLE workspec ADD COLUMN `trigger_content_update_delay` INTEGER NOT NULL DEFAULT -1");
            hVar.execSQL("ALTER TABLE workspec ADD COLUMN `trigger_max_content_delay` INTEGER NOT NULL DEFAULT -1");
        }
    }

    class d extends Migration {
        d(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(t0.h hVar) {
            hVar.execSQL("CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        }
    }

    class e extends Migration {
        e(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(t0.h hVar) {
            hVar.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkSpec_period_start_time` ON `workspec` (`period_start_time`)");
        }
    }

    class f extends Migration {
        f(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(t0.h hVar) {
            hVar.execSQL("ALTER TABLE workspec ADD COLUMN `run_in_foreground` INTEGER NOT NULL DEFAULT 0");
        }
    }

    class g extends Migration {
        g(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(t0.h hVar) {
            hVar.execSQL("ALTER TABLE workspec ADD COLUMN `out_of_quota_policy` INTEGER NOT NULL DEFAULT 0");
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.h$h, reason: collision with other inner class name */
    public static class C0062h extends Migration {
        final Context a;

        public C0062h(Context context, int i, int i2) {
            super(i, i2);
            this.a = context;
        }

        @Override // androidx.room.migration.Migration
        public void migrate(t0.h hVar) {
            if (this.endVersion >= 10) {
                hVar.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"reschedule_needed", 1});
            } else {
                this.a.getSharedPreferences("androidx.work.util.preferences", 0).edit().putBoolean("reschedule_needed", true).apply();
            }
        }
    }

    public static class i extends Migration {
        final Context a;

        public i(Context context) {
            super(9, 10);
            this.a = context;
        }

        @Override // androidx.room.migration.Migration
        public void migrate(t0.h hVar) {
            hVar.execSQL("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
            i1.h.b(this.a, hVar);
            i1.f.a(this.a, hVar);
        }
    }
}
