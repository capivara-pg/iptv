package androidx.work.impl.utils;

import a1.n;
import a1.p;
import a1.q;
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
import b1.c;
import b1.d;
import b1.e;
import java.util.List;
import java.util.concurrent.TimeUnit;
import s0.k;
import s0.s;
import t0.f;
import t0.h;
import t0.j;
import u.a;
import v0.f0;

/* JADX INFO: loaded from: classes.dex */
public class ForceStopRunnable implements Runnable {
    private static final String d = k.f("ForceStopRunnable");
    private static final long e = TimeUnit.DAYS.toMillis(3650);
    private final Context a;
    private final j b;
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

    public ForceStopRunnable(Context context, j jVar) {
        this.a = context.getApplicationContext();
        this.b = jVar;
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
        boolean zH = Build.VERSION.SDK_INT >= 23 ? f0.h(this.a, this.b) : false;
        WorkDatabase workDatabaseN = this.b.n();
        q qVarB = workDatabaseN.B();
        n nVarA = workDatabaseN.A();
        workDatabaseN.c();
        try {
            List<p> listP = qVarB.p();
            boolean z = (listP == null || listP.isEmpty()) ? false : true;
            if (z) {
                for (p pVar : listP) {
                    qVarB.e(s.a, new String[]{pVar.a});
                    qVarB.l(pVar.a, -1L);
                }
            }
            nVarA.b();
            workDatabaseN.r();
            workDatabaseN.g();
            return z || zH;
        } catch (Throwable th) {
            workDatabaseN.g();
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
        boolean zB = b1.j.b(this.a, aVarH);
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
                h.e(this.a);
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
