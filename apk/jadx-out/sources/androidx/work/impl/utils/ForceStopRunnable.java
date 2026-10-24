package androidx.work.impl.utils;

import a1.k;
import a1.s;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import android.text.TextUtils;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.f;
import androidx.work.impl.i;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import c1.m;
import i1.c;
import i1.d;
import i1.e;
import i1.j;
import java.util.List;
import java.util.concurrent.TimeUnit;
import z.a;

/* JADX INFO: loaded from: classes.dex */
public class ForceStopRunnable implements Runnable {
    private static final String d = k.f("ForceStopRunnable");
    private static final long e = TimeUnit.DAYS.toMillis(3650);
    private final Context a;
    private final androidx.work.impl.k b;
    private int c = 0;

    public static class BroadcastReceiver extends android.content.BroadcastReceiver {
        private static final String a = k.f("ForceStopRunnable$Rcvr");

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"ACTION_FORCE_STOP_RESCHEDULE".equals(intent.getAction())) {
                return;
            }
            k.c().g(a, "Rescheduling alarm that keeps track of force-stops.", new Throwable[0]);
            ForceStopRunnable.g(context);
        }
    }

    public ForceStopRunnable(Context context, androidx.work.impl.k kVar) {
        this.a = context.getApplicationContext();
        this.b = kVar;
    }

    static Intent c(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, (Class<?>) BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return intent;
    }

    private static PendingIntent d(Context context, int i) {
        return PendingIntent.getBroadcast(context, -1, c(context), i);
    }

    static void g(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent pendingIntentD = d(context, a.c() ? 167772160 : 134217728);
        long jCurrentTimeMillis = System.currentTimeMillis() + e;
        if (alarmManager != null) {
            alarmManager.setExact(0, jCurrentTimeMillis, pendingIntentD);
        }
    }

    public boolean a() {
        boolean zH = Build.VERSION.SDK_INT >= 23 ? m.h(this.a, this.b) : false;
        WorkDatabase workDatabaseN = this.b.n();
        WorkSpecDao workSpecDao = workDatabaseN.workSpecDao();
        WorkProgressDao workProgressDao = workDatabaseN.workProgressDao();
        workDatabaseN.beginTransaction();
        try {
            List<WorkSpec> runningWork = workSpecDao.getRunningWork();
            boolean z = (runningWork == null || runningWork.isEmpty()) ? false : true;
            if (z) {
                for (WorkSpec workSpec : runningWork) {
                    workSpecDao.setState(s.a.a, workSpec.id);
                    workSpecDao.markWorkSpecScheduled(workSpec.id, -1L);
                }
            }
            workProgressDao.deleteAll();
            workDatabaseN.setTransactionSuccessful();
            workDatabaseN.endTransaction();
            return z || zH;
        } catch (Throwable th) {
            workDatabaseN.endTransaction();
            throw th;
        }
    }

    public void b() {
        boolean zA = a();
        if (h()) {
            k.c().a(d, "Rescheduling Workers.", new Throwable[0]);
            this.b.r();
            this.b.k().c(false);
        } else if (e()) {
            k.c().a(d, "Application was force-stopped, rescheduling.", new Throwable[0]);
            this.b.r();
        } else if (zA) {
            k.c().a(d, "Found unfinished work, scheduling it.", new Throwable[0]);
            f.b(this.b.h(), this.b.n(), this.b.m());
        }
    }

    public boolean e() {
        try {
            PendingIntent pendingIntentD = d(this.a, a.c() ? 570425344 : 536870912);
            if (Build.VERSION.SDK_INT >= 30) {
                if (pendingIntentD != null) {
                    pendingIntentD.cancel();
                }
                List listA = c.a((ActivityManager) this.a.getSystemService("activity"), (String) null, 0, 0);
                if (listA != null && !listA.isEmpty()) {
                    for (int i = 0; i < listA.size(); i++) {
                        if (e.a(d.a(listA.get(i))) == 10) {
                            return true;
                        }
                    }
                }
            } else if (pendingIntentD == null) {
                g(this.a);
                return true;
            }
            return false;
        } catch (IllegalArgumentException | SecurityException e2) {
            k.c().h(d, "Ignoring exception", new Throwable[]{e2});
            return true;
        }
    }

    public boolean f() {
        androidx.work.a aVarH = this.b.h();
        if (TextUtils.isEmpty(aVarH.c())) {
            k.c().a(d, "The default process name was not specified.", new Throwable[0]);
            return true;
        }
        boolean zB = j.b(this.a, aVarH);
        k.c().a(d, String.format("Is default app process = %s", Boolean.valueOf(zB)), new Throwable[0]);
        return zB;
    }

    boolean h() {
        return this.b.k().a();
    }

    public void i(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        try {
            if (!f()) {
                this.b.q();
                return;
            }
            while (true) {
                i.e(this.a);
                k.c().a(d, "Performing cleanup operations.", new Throwable[0]);
                try {
                    b();
                    this.b.q();
                    return;
                } catch (SQLiteAccessPermException | SQLiteCantOpenDatabaseException | SQLiteConstraintException | SQLiteDatabaseCorruptException | SQLiteDatabaseLockedException | SQLiteTableLockedException e2) {
                    i = this.c + 1;
                    this.c = i;
                    if (i >= 3) {
                        k.c().b(d, "The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", new Throwable[]{e2});
                        IllegalStateException illegalStateException = new IllegalStateException("The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", e2);
                        this.b.h().d();
                        throw illegalStateException;
                    }
                    k.c().a(d, String.format("Retrying after %s", Long.valueOf(((long) i) * 300)), new Throwable[]{e2});
                    i(((long) this.c) * 300);
                }
                k.c().a(d, String.format("Retrying after %s", Long.valueOf(((long) i) * 300)), new Throwable[]{e2});
                i(((long) this.c) * 300);
            }
        } catch (Throwable th) {
            this.b.q();
            throw th;
        }
    }
}
