package androidx.work.impl.background.systemalarm;

import a1.k;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkSpec;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class b implements androidx.work.impl.b {
    private static final String d = k.f("CommandHandler");
    private final Context a;
    private final Map b = new HashMap();
    private final Object c = new Object();

    b(Context context) {
        this.a = context;
    }

    static Intent a(Context context) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_CONSTRAINTS_CHANGED");
        return intent;
    }

    static Intent b(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_DELAY_MET");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    static Intent c(Context context, String str, boolean z) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_EXECUTION_COMPLETED");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        intent.putExtra("KEY_NEEDS_RESCHEDULE", z);
        return intent;
    }

    static Intent e(Context context) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_RESCHEDULE");
        return intent;
    }

    static Intent f(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_SCHEDULE_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    static Intent g(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_STOP_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    private void h(Intent intent, int i, e eVar) {
        k.c().a(d, String.format("Handling constraints changed %s", intent), new Throwable[0]);
        new c(this.a, i, eVar).a();
    }

    private void i(Intent intent, int i, e eVar) {
        Bundle extras = intent.getExtras();
        synchronized (this.c) {
            String string = extras.getString("KEY_WORKSPEC_ID");
            k kVarC = k.c();
            String str = d;
            kVarC.a(str, String.format("Handing delay met for %s", string), new Throwable[0]);
            if (this.b.containsKey(string)) {
                k.c().a(str, String.format("WorkSpec %s is already being handled for ACTION_DELAY_MET", string), new Throwable[0]);
            } else {
                d dVar = new d(this.a, i, string, eVar);
                this.b.put(string, dVar);
                dVar.f();
            }
        }
    }

    private void j(Intent intent, int i) {
        Bundle extras = intent.getExtras();
        String string = extras.getString("KEY_WORKSPEC_ID");
        boolean z = extras.getBoolean("KEY_NEEDS_RESCHEDULE");
        k.c().a(d, String.format("Handling onExecutionCompleted %s, %s", intent, Integer.valueOf(i)), new Throwable[0]);
        d(string, z);
    }

    private void k(Intent intent, int i, e eVar) {
        k.c().a(d, String.format("Handling reschedule %s, %s", intent, Integer.valueOf(i)), new Throwable[0]);
        eVar.g().r();
    }

    private void l(Intent intent, int i, e eVar) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        k kVarC = k.c();
        String str = d;
        kVarC.a(str, String.format("Handling schedule work for %s", string), new Throwable[0]);
        WorkDatabase workDatabaseN = eVar.g().n();
        workDatabaseN.beginTransaction();
        try {
            WorkSpec workSpec = workDatabaseN.workSpecDao().getWorkSpec(string);
            if (workSpec == null) {
                k.c().h(str, "Skipping scheduling " + string + " because it's no longer in the DB", new Throwable[0]);
                return;
            }
            if (workSpec.state.a()) {
                k.c().h(str, "Skipping scheduling " + string + "because it is finished.", new Throwable[0]);
                return;
            }
            long jCalculateNextRunTime = workSpec.calculateNextRunTime();
            if (workSpec.hasConstraints()) {
                k.c().a(str, String.format("Opportunistically setting an alarm for %s at %s", string, Long.valueOf(jCalculateNextRunTime)), new Throwable[0]);
                a.c(this.a, eVar.g(), string, jCalculateNextRunTime);
                eVar.k(new e.b(eVar, a(this.a), i));
            } else {
                k.c().a(str, String.format("Setting up Alarms for %s at %s", string, Long.valueOf(jCalculateNextRunTime)), new Throwable[0]);
                a.c(this.a, eVar.g(), string, jCalculateNextRunTime);
            }
            workDatabaseN.setTransactionSuccessful();
        } finally {
            workDatabaseN.endTransaction();
        }
    }

    private void m(Intent intent, e eVar) {
        String string = intent.getExtras().getString("KEY_WORKSPEC_ID");
        k.c().a(d, String.format("Handing stopWork work for %s", string), new Throwable[0]);
        eVar.g().w(string);
        a.a(this.a, eVar.g(), string);
        eVar.d(string, false);
    }

    private static boolean n(Bundle bundle, String... strArr) {
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        for (String str : strArr) {
            if (bundle.get(str) == null) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.work.impl.b
    public void d(String str, boolean z) {
        synchronized (this.c) {
            androidx.work.impl.b bVar = (androidx.work.impl.b) this.b.remove(str);
            if (bVar != null) {
                bVar.d(str, z);
            }
        }
    }

    boolean o() {
        boolean z;
        synchronized (this.c) {
            z = !this.b.isEmpty();
        }
        return z;
    }

    void p(Intent intent, int i, e eVar) {
        String action = intent.getAction();
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            h(intent, i, eVar);
            return;
        }
        if ("ACTION_RESCHEDULE".equals(action)) {
            k(intent, i, eVar);
            return;
        }
        if (!n(intent.getExtras(), "KEY_WORKSPEC_ID")) {
            k.c().b(d, String.format("Invalid request for %s, requires %s.", action, "KEY_WORKSPEC_ID"), new Throwable[0]);
            return;
        }
        if ("ACTION_SCHEDULE_WORK".equals(action)) {
            l(intent, i, eVar);
            return;
        }
        if ("ACTION_DELAY_MET".equals(action)) {
            i(intent, i, eVar);
            return;
        }
        if ("ACTION_STOP_WORK".equals(action)) {
            m(intent, eVar);
        } else if ("ACTION_EXECUTION_COMPLETED".equals(action)) {
            j(intent, i);
        } else {
            k.c().h(d, String.format("Ignoring intent %s", intent), new Throwable[0]);
        }
    }
}
