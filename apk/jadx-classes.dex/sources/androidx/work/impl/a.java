package androidx.work.impl;

import android.content.Context;
import android.os.Build;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class a {
    public static l0.a a = new C0054a(1, 2);
    public static l0.a b = new b(3, 4);
    public static l0.a c = new c(4, 5);
    public static l0.a d = new d(6, 7);
    public static l0.a e = new e(7, 8);
    public static l0.a f = new f(8, 9);
    public static l0.a g = new g(11, 12);

    /* JADX INFO: renamed from: androidx.work.impl.a$a, reason: collision with other inner class name */
    class C0054a extends l0.a {
        C0054a(int i, int i2) {
            super(i, i2);
        }

        public void a(n0.b bVar) {
            bVar.execSQL("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            bVar.execSQL("INSERT INTO SystemIdInfo(work_spec_id, system_id) SELECT work_spec_id, alarm_id AS system_id FROM alarmInfo");
            bVar.execSQL("DROP TABLE IF EXISTS alarmInfo");
            bVar.execSQL("INSERT OR IGNORE INTO worktag(tag, work_spec_id) SELECT worker_class_name AS tag, id AS work_spec_id FROM workspec");
        }
    }

    class b extends l0.a {
        b(int i, int i2) {
            super(i, i2);
        }

        public void a(n0.b bVar) {
            if (Build.VERSION.SDK_INT >= 23) {
                bVar.execSQL("UPDATE workspec SET schedule_requested_at=0 WHERE state NOT IN (2, 3, 5) AND schedule_requested_at=-1 AND interval_duration<>0");
            }
        }
    }

    class c extends l0.a {
        c(int i, int i2) {
            super(i, i2);
        }

        public void a(n0.b bVar) {
            bVar.execSQL("ALTER TABLE workspec ADD COLUMN `trigger_content_update_delay` INTEGER NOT NULL DEFAULT -1");
            bVar.execSQL("ALTER TABLE workspec ADD COLUMN `trigger_max_content_delay` INTEGER NOT NULL DEFAULT -1");
        }
    }

    class d extends l0.a {
        d(int i, int i2) {
            super(i, i2);
        }

        public void a(n0.b bVar) {
            bVar.execSQL("CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        }
    }

    class e extends l0.a {
        e(int i, int i2) {
            super(i, i2);
        }

        public void a(n0.b bVar) {
            bVar.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkSpec_period_start_time` ON `workspec` (`period_start_time`)");
        }
    }

    class f extends l0.a {
        f(int i, int i2) {
            super(i, i2);
        }

        public void a(n0.b bVar) {
            bVar.execSQL("ALTER TABLE workspec ADD COLUMN `run_in_foreground` INTEGER NOT NULL DEFAULT 0");
        }
    }

    class g extends l0.a {
        g(int i, int i2) {
            super(i, i2);
        }

        public void a(n0.b bVar) {
            bVar.execSQL("ALTER TABLE workspec ADD COLUMN `out_of_quota_policy` INTEGER NOT NULL DEFAULT 0");
        }
    }

    public static class h extends l0.a {
        final Context c;

        public h(Context context, int i, int i2) {
            super(i, i2);
            this.c = context;
        }

        public void a(n0.b bVar) {
            if (((l0.a) this).b >= 10) {
                bVar.k("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"reschedule_needed", 1});
            } else {
                this.c.getSharedPreferences("androidx.work.util.preferences", 0).edit().putBoolean("reschedule_needed", true).apply();
            }
        }
    }

    public static class i extends l0.a {
        final Context c;

        public i(Context context) {
            super(9, 10);
            this.c = context;
        }

        public void a(n0.b bVar) {
            bVar.execSQL("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
            b1.h.b(this.c, bVar);
            b1.f.a(this.c, bVar);
        }
    }
}
