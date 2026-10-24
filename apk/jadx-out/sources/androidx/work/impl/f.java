package androidx.work.impl;

import android.content.Context;
import android.os.Build;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import c1.m;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    private static final String a = a1.k.f("Schedulers");

    static e a(Context context, k kVar) {
        if (Build.VERSION.SDK_INT >= 23) {
            m mVar = new m(context, kVar);
            i1.g.a(context, SystemJobService.class, true);
            a1.k.c().a(a, "Created SystemJobScheduler and enabled SystemJobService", new Throwable[0]);
            return mVar;
        }
        e eVarC = c(context);
        if (eVarC != null) {
            return eVarC;
        }
        androidx.work.impl.background.systemalarm.f fVar = new androidx.work.impl.background.systemalarm.f(context);
        i1.g.a(context, SystemAlarmService.class, true);
        a1.k.c().a(a, "Created SystemAlarmScheduler", new Throwable[0]);
        return fVar;
    }

    public static void b(androidx.work.a aVar, WorkDatabase workDatabase, List list) {
        if (list == null || list.size() == 0) {
            return;
        }
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        workDatabase.beginTransaction();
        try {
            List<WorkSpec> eligibleWorkForScheduling = workSpecDao.getEligibleWorkForScheduling(aVar.h());
            List<WorkSpec> allEligibleWorkSpecsForScheduling = workSpecDao.getAllEligibleWorkSpecsForScheduling(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
            if (eligibleWorkForScheduling != null && eligibleWorkForScheduling.size() > 0) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Iterator<WorkSpec> it = eligibleWorkForScheduling.iterator();
                while (it.hasNext()) {
                    workSpecDao.markWorkSpecScheduled(it.next().id, jCurrentTimeMillis);
                }
            }
            workDatabase.setTransactionSuccessful();
            workDatabase.endTransaction();
            if (eligibleWorkForScheduling != null && eligibleWorkForScheduling.size() > 0) {
                WorkSpec[] workSpecArr = (WorkSpec[]) eligibleWorkForScheduling.toArray(new WorkSpec[eligibleWorkForScheduling.size()]);
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    e eVar = (e) it2.next();
                    if (eVar.c()) {
                        eVar.a(workSpecArr);
                    }
                }
            }
            if (allEligibleWorkSpecsForScheduling == null || allEligibleWorkSpecsForScheduling.size() <= 0) {
                return;
            }
            WorkSpec[] workSpecArr2 = (WorkSpec[]) allEligibleWorkSpecsForScheduling.toArray(new WorkSpec[allEligibleWorkSpecsForScheduling.size()]);
            Iterator it3 = list.iterator();
            while (it3.hasNext()) {
                e eVar2 = (e) it3.next();
                if (!eVar2.c()) {
                    eVar2.a(workSpecArr2);
                }
            }
        } catch (Throwable th) {
            workDatabase.endTransaction();
            throw th;
        }
    }

    private static e c(Context context) {
        try {
            e eVar = (e) Class.forName("androidx.work.impl.background.gcm.GcmScheduler").getConstructor(Context.class).newInstance(context);
            a1.k.c().a(a, String.format("Created %s", "androidx.work.impl.background.gcm.GcmScheduler"), new Throwable[0]);
            return eVar;
        } catch (Throwable th) {
            a1.k.c().a(a, "Unable to create GCM Scheduler", new Throwable[]{th});
            return null;
        }
    }
}
