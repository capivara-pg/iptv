package androidx.work.impl.background.systemalarm;

import a1.k;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;

/* JADX INFO: loaded from: classes.dex */
abstract class a {
    private static final String a = k.f("Alarms");

    public static void a(Context context, androidx.work.impl.k kVar, String str) {
        SystemIdInfoDao systemIdInfoDao = kVar.n().systemIdInfoDao();
        SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(str);
        if (systemIdInfo != null) {
            b(context, str, systemIdInfo.systemId);
            k.c().a(a, String.format("Removing SystemIdInfo for workSpecId (%s)", str), new Throwable[0]);
            systemIdInfoDao.removeSystemIdInfo(str);
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

    public static void c(Context context, androidx.work.impl.k kVar, String str, long j) {
        WorkDatabase workDatabaseN = kVar.n();
        SystemIdInfoDao systemIdInfoDao = workDatabaseN.systemIdInfoDao();
        SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(str);
        if (systemIdInfo != null) {
            b(context, str, systemIdInfo.systemId);
            d(context, str, systemIdInfo.systemId, j);
        } else {
            int iB = new i1.f(workDatabaseN).b();
            systemIdInfoDao.insertSystemIdInfo(new SystemIdInfo(str, iB));
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
