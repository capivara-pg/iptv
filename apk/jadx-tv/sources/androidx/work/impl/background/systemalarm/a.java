package androidx.work.impl.background.systemalarm;

import a1.g;
import a1.h;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import androidx.work.impl.WorkDatabase;
import s0.k;
import t0.j;

/* JADX INFO: loaded from: classes.dex */
abstract class a {
    private static final String a = k.f("Alarms");

    public static void a(Context context, j jVar, String str) {
        h hVarY = jVar.n().y();
        g gVarB = hVarY.b(str);
        if (gVarB != null) {
            b(context, str, gVarB.b);
            k.c().a(a, String.format("Removing SystemIdInfo for workSpecId (%s)", str), new Throwable[0]);
            hVarY.d(str);
        }
    }

    private static void b(Context context, String str, int i) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, b.b(context, str), Build.VERSION.SDK_INT >= 23 ? 603979776 : 536870912);
        if (service == null || alarmManager == null) {
            return;
        }
        k.c().a(a, String.format("Cancelling existing alarm with (workSpecId, systemId) (%s, %s)", str, Integer.valueOf(i)), new Throwable[0]);
        alarmManager.cancel(service);
    }

    public static void c(Context context, j jVar, String str, long j) {
        WorkDatabase workDatabaseN = jVar.n();
        h hVarY = workDatabaseN.y();
        g gVarB = hVarY.b(str);
        if (gVarB != null) {
            b(context, str, gVarB.b);
            d(context, str, gVarB.b, j);
        } else {
            int iB = new b1.f(workDatabaseN).b();
            hVarY.a(new g(str, iB));
            d(context, str, iB, j);
        }
    }

    private static void d(Context context, String str, int i, long j) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, b.b(context, str), Build.VERSION.SDK_INT >= 23 ? 201326592 : 134217728);
        if (alarmManager != null) {
            alarmManager.setExact(0, j, service);
        }
    }
}
