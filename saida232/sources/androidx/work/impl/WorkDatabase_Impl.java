package androidx.work.impl;

import a1.b;
import a1.e;
import a1.h;
import a1.k;
import a1.l;
import a1.n;
import a1.o;
import a1.q;
import a1.r;
import a1.t;
import a1.u;
import androidx.room.i;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import m0.c;
import m0.f;

/* JADX INFO: loaded from: classes.dex */
public final class WorkDatabase_Impl extends WorkDatabase {
    private volatile q m;
    private volatile b n;
    private volatile t o;
    private volatile h p;
    private volatile k q;
    private volatile n r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private volatile e f32s;

    class a extends i.a {
        a(int i) {
            super(i);
        }

        @Override // androidx.room.i.a
        public void a(n0.b bVar) {
            bVar.execSQL("CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            bVar.execSQL("CREATE INDEX IF NOT EXISTS `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
            bVar.execSQL("CREATE INDEX IF NOT EXISTS `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
            bVar.execSQL("CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `period_start_time` INTEGER NOT NULL, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `run_in_foreground` INTEGER NOT NULL, `out_of_quota_policy` INTEGER NOT NULL, `required_network_type` INTEGER, `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `trigger_content_update_delay` INTEGER NOT NULL, `trigger_max_content_delay` INTEGER NOT NULL, `content_uri_triggers` BLOB, PRIMARY KEY(`id`))");
            bVar.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
            bVar.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkSpec_period_start_time` ON `WorkSpec` (`period_start_time`)");
            bVar.execSQL("CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            bVar.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
            bVar.execSQL("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            bVar.execSQL("CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            bVar.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
            bVar.execSQL("CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            bVar.execSQL("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
            bVar.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            bVar.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c103703e120ae8cc73c9248622f3cd1e')");
        }

        @Override // androidx.room.i.a
        public void b(n0.b bVar) {
            bVar.execSQL("DROP TABLE IF EXISTS `Dependency`");
            bVar.execSQL("DROP TABLE IF EXISTS `WorkSpec`");
            bVar.execSQL("DROP TABLE IF EXISTS `WorkTag`");
            bVar.execSQL("DROP TABLE IF EXISTS `SystemIdInfo`");
            bVar.execSQL("DROP TABLE IF EXISTS `WorkName`");
            bVar.execSQL("DROP TABLE IF EXISTS `WorkProgress`");
            bVar.execSQL("DROP TABLE IF EXISTS `Preference`");
            if (((androidx.room.h) WorkDatabase_Impl.this).h != null) {
                int size = ((androidx.room.h) WorkDatabase_Impl.this).h.size();
                for (int i = 0; i < size; i++) {
                    ((androidx.room.h.b) ((androidx.room.h) WorkDatabase_Impl.this).h.get(i)).b(bVar);
                }
            }
        }

        @Override // androidx.room.i.a
        protected void c(n0.b bVar) {
            if (((androidx.room.h) WorkDatabase_Impl.this).h != null) {
                int size = ((androidx.room.h) WorkDatabase_Impl.this).h.size();
                for (int i = 0; i < size; i++) {
                    ((androidx.room.h.b) ((androidx.room.h) WorkDatabase_Impl.this).h.get(i)).a(bVar);
                }
            }
        }

        @Override // androidx.room.i.a
        public void d(n0.b bVar) {
            ((androidx.room.h) WorkDatabase_Impl.this).a = bVar;
            bVar.execSQL("PRAGMA foreign_keys = ON");
            WorkDatabase_Impl.this.m(bVar);
            if (((androidx.room.h) WorkDatabase_Impl.this).h != null) {
                int size = ((androidx.room.h) WorkDatabase_Impl.this).h.size();
                for (int i = 0; i < size; i++) {
                    ((androidx.room.h.b) ((androidx.room.h) WorkDatabase_Impl.this).h.get(i)).c(bVar);
                }
            }
        }

        @Override // androidx.room.i.a
        public void e(n0.b bVar) {
        }

        @Override // androidx.room.i.a
        public void f(n0.b bVar) {
            c.a(bVar);
        }

        @Override // androidx.room.i.a
        protected i.b g(n0.b bVar) {
            HashMap map = new HashMap(2);
            map.put("work_spec_id", new f.a("work_spec_id", "TEXT", true, 1, (String) null, 1));
            map.put("prerequisite_id", new f.a("prerequisite_id", "TEXT", true, 2, (String) null, 1));
            HashSet hashSet = new HashSet(2);
            hashSet.add(new f.b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
            hashSet.add(new f.b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("prerequisite_id"), Arrays.asList("id")));
            HashSet hashSet2 = new HashSet(2);
            hashSet2.add(new f.d("index_Dependency_work_spec_id", false, Arrays.asList("work_spec_id")));
            hashSet2.add(new f.d("index_Dependency_prerequisite_id", false, Arrays.asList("prerequisite_id")));
            f fVar = new f("Dependency", map, hashSet, hashSet2);
            f fVarA = f.a(bVar, "Dependency");
            if (!fVar.equals(fVarA)) {
                return new i.b(false, "Dependency(androidx.work.impl.model.Dependency).\n Expected:\n" + fVar + "\n Found:\n" + fVarA);
            }
            HashMap map2 = new HashMap(25);
            map2.put("id", new f.a("id", "TEXT", true, 1, (String) null, 1));
            map2.put("state", new f.a("state", "INTEGER", true, 0, (String) null, 1));
            map2.put("worker_class_name", new f.a("worker_class_name", "TEXT", true, 0, (String) null, 1));
            map2.put("input_merger_class_name", new f.a("input_merger_class_name", "TEXT", false, 0, (String) null, 1));
            map2.put("input", new f.a("input", "BLOB", true, 0, (String) null, 1));
            map2.put("output", new f.a("output", "BLOB", true, 0, (String) null, 1));
            map2.put("initial_delay", new f.a("initial_delay", "INTEGER", true, 0, (String) null, 1));
            map2.put("interval_duration", new f.a("interval_duration", "INTEGER", true, 0, (String) null, 1));
            map2.put("flex_duration", new f.a("flex_duration", "INTEGER", true, 0, (String) null, 1));
            map2.put("run_attempt_count", new f.a("run_attempt_count", "INTEGER", true, 0, (String) null, 1));
            map2.put("backoff_policy", new f.a("backoff_policy", "INTEGER", true, 0, (String) null, 1));
            map2.put("backoff_delay_duration", new f.a("backoff_delay_duration", "INTEGER", true, 0, (String) null, 1));
            map2.put("period_start_time", new f.a("period_start_time", "INTEGER", true, 0, (String) null, 1));
            map2.put("minimum_retention_duration", new f.a("minimum_retention_duration", "INTEGER", true, 0, (String) null, 1));
            map2.put("schedule_requested_at", new f.a("schedule_requested_at", "INTEGER", true, 0, (String) null, 1));
            map2.put("run_in_foreground", new f.a("run_in_foreground", "INTEGER", true, 0, (String) null, 1));
            map2.put("out_of_quota_policy", new f.a("out_of_quota_policy", "INTEGER", true, 0, (String) null, 1));
            map2.put("required_network_type", new f.a("required_network_type", "INTEGER", false, 0, (String) null, 1));
            map2.put("requires_charging", new f.a("requires_charging", "INTEGER", true, 0, (String) null, 1));
            map2.put("requires_device_idle", new f.a("requires_device_idle", "INTEGER", true, 0, (String) null, 1));
            map2.put("requires_battery_not_low", new f.a("requires_battery_not_low", "INTEGER", true, 0, (String) null, 1));
            map2.put("requires_storage_not_low", new f.a("requires_storage_not_low", "INTEGER", true, 0, (String) null, 1));
            map2.put("trigger_content_update_delay", new f.a("trigger_content_update_delay", "INTEGER", true, 0, (String) null, 1));
            map2.put("trigger_max_content_delay", new f.a("trigger_max_content_delay", "INTEGER", true, 0, (String) null, 1));
            map2.put("content_uri_triggers", new f.a("content_uri_triggers", "BLOB", false, 0, (String) null, 1));
            HashSet hashSet3 = new HashSet(0);
            HashSet hashSet4 = new HashSet(2);
            hashSet4.add(new f.d("index_WorkSpec_schedule_requested_at", false, Arrays.asList("schedule_requested_at")));
            hashSet4.add(new f.d("index_WorkSpec_period_start_time", false, Arrays.asList("period_start_time")));
            f fVar2 = new f("WorkSpec", map2, hashSet3, hashSet4);
            f fVarA2 = f.a(bVar, "WorkSpec");
            if (!fVar2.equals(fVarA2)) {
                return new i.b(false, "WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n" + fVar2 + "\n Found:\n" + fVarA2);
            }
            HashMap map3 = new HashMap(2);
            map3.put("tag", new f.a("tag", "TEXT", true, 1, (String) null, 1));
            map3.put("work_spec_id", new f.a("work_spec_id", "TEXT", true, 2, (String) null, 1));
            HashSet hashSet5 = new HashSet(1);
            hashSet5.add(new f.b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
            HashSet hashSet6 = new HashSet(1);
            hashSet6.add(new f.d("index_WorkTag_work_spec_id", false, Arrays.asList("work_spec_id")));
            f fVar3 = new f("WorkTag", map3, hashSet5, hashSet6);
            f fVarA3 = f.a(bVar, "WorkTag");
            if (!fVar3.equals(fVarA3)) {
                return new i.b(false, "WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n" + fVar3 + "\n Found:\n" + fVarA3);
            }
            HashMap map4 = new HashMap(2);
            map4.put("work_spec_id", new f.a("work_spec_id", "TEXT", true, 1, (String) null, 1));
            map4.put("system_id", new f.a("system_id", "INTEGER", true, 0, (String) null, 1));
            HashSet hashSet7 = new HashSet(1);
            hashSet7.add(new f.b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
            f fVar4 = new f("SystemIdInfo", map4, hashSet7, new HashSet(0));
            f fVarA4 = f.a(bVar, "SystemIdInfo");
            if (!fVar4.equals(fVarA4)) {
                return new i.b(false, "SystemIdInfo(androidx.work.impl.model.SystemIdInfo).\n Expected:\n" + fVar4 + "\n Found:\n" + fVarA4);
            }
            HashMap map5 = new HashMap(2);
            map5.put("name", new f.a("name", "TEXT", true, 1, (String) null, 1));
            map5.put("work_spec_id", new f.a("work_spec_id", "TEXT", true, 2, (String) null, 1));
            HashSet hashSet8 = new HashSet(1);
            hashSet8.add(new f.b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
            HashSet hashSet9 = new HashSet(1);
            hashSet9.add(new f.d("index_WorkName_work_spec_id", false, Arrays.asList("work_spec_id")));
            f fVar5 = new f("WorkName", map5, hashSet8, hashSet9);
            f fVarA5 = f.a(bVar, "WorkName");
            if (!fVar5.equals(fVarA5)) {
                return new i.b(false, "WorkName(androidx.work.impl.model.WorkName).\n Expected:\n" + fVar5 + "\n Found:\n" + fVarA5);
            }
            HashMap map6 = new HashMap(2);
            map6.put("work_spec_id", new f.a("work_spec_id", "TEXT", true, 1, (String) null, 1));
            map6.put("progress", new f.a("progress", "BLOB", true, 0, (String) null, 1));
            HashSet hashSet10 = new HashSet(1);
            hashSet10.add(new f.b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
            f fVar6 = new f("WorkProgress", map6, hashSet10, new HashSet(0));
            f fVarA6 = f.a(bVar, "WorkProgress");
            if (!fVar6.equals(fVarA6)) {
                return new i.b(false, "WorkProgress(androidx.work.impl.model.WorkProgress).\n Expected:\n" + fVar6 + "\n Found:\n" + fVarA6);
            }
            HashMap map7 = new HashMap(2);
            map7.put("key", new f.a("key", "TEXT", true, 1, (String) null, 1));
            map7.put("long_value", new f.a("long_value", "INTEGER", false, 0, (String) null, 1));
            f fVar7 = new f("Preference", map7, new HashSet(0), new HashSet(0));
            f fVarA7 = f.a(bVar, "Preference");
            if (fVar7.equals(fVarA7)) {
                return new i.b(true, null);
            }
            return new i.b(false, "Preference(androidx.work.impl.model.Preference).\n Expected:\n" + fVar7 + "\n Found:\n" + fVarA7);
        }
    }

    @Override // androidx.work.impl.WorkDatabase
    public n A() {
        n nVar;
        if (this.r != null) {
            return this.r;
        }
        synchronized (this) {
            if (this.r == null) {
                this.r = new o(this);
            }
            nVar = this.r;
        }
        return nVar;
    }

    @Override // androidx.work.impl.WorkDatabase
    public q B() {
        q qVar;
        if (this.m != null) {
            return this.m;
        }
        synchronized (this) {
            if (this.m == null) {
                this.m = new r(this);
            }
            qVar = this.m;
        }
        return qVar;
    }

    @Override // androidx.work.impl.WorkDatabase
    public t C() {
        t tVar;
        if (this.o != null) {
            return this.o;
        }
        synchronized (this) {
            if (this.o == null) {
                this.o = new u(this);
            }
            tVar = this.o;
        }
        return tVar;
    }

    @Override // androidx.room.h
    protected androidx.room.e e() {
        return new androidx.room.e(this, new HashMap(0), new HashMap(0), "Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName", "WorkProgress", "Preference");
    }

    @Override // androidx.room.h
    protected n0.c f(androidx.room.a aVar) {
        return aVar.a.a(n0.c.b.a(aVar.b).c(aVar.c).b(new i(aVar, new a(12), "c103703e120ae8cc73c9248622f3cd1e", "49f946663a8deb7054212b8adda248c6")).a());
    }

    @Override // androidx.work.impl.WorkDatabase
    public b t() {
        b bVar;
        if (this.n != null) {
            return this.n;
        }
        synchronized (this) {
            if (this.n == null) {
                this.n = new a1.c(this);
            }
            bVar = this.n;
        }
        return bVar;
    }

    @Override // androidx.work.impl.WorkDatabase
    public e x() {
        e eVar;
        if (this.f32s != null) {
            return this.f32s;
        }
        synchronized (this) {
            if (this.f32s == null) {
                this.f32s = new a1.f(this);
            }
            eVar = this.f32s;
        }
        return eVar;
    }

    @Override // androidx.work.impl.WorkDatabase
    public h y() {
        h hVar;
        if (this.p != null) {
            return this.p;
        }
        synchronized (this) {
            if (this.p == null) {
                this.p = new a1.i(this);
            }
            hVar = this.p;
        }
        return hVar;
    }

    @Override // androidx.work.impl.WorkDatabase
    public k z() {
        k kVar;
        if (this.q != null) {
            return this.q;
        }
        synchronized (this) {
            if (this.q == null) {
                this.q = new l(this);
            }
            kVar = this.q;
        }
        return kVar;
    }
}
