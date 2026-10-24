package androidx.work.impl.workers;

import a1.k;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class DiagnosticsWorker extends Worker {
    private static final String g = k.f("DiagnosticsWrkr");

    public DiagnosticsWorker(Context context, WorkerParameters parameters) {
        super(context, parameters);
    }

    private static String s(WorkSpec workSpec, String str, Integer num, String str2) {
        return String.format("\n%s\t %s\t %s\t %s\t %s\t %s\t", workSpec.id, workSpec.workerClassName, num, workSpec.state.name(), str, str2);
    }

    private static String t(WorkNameDao workNameDao, WorkTagDao workTagDao, SystemIdInfoDao systemIdInfoDao, List list) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n Id \t Class Name\t %s\t State\t Unique Name\t Tags\t", Build.VERSION.SDK_INT >= 23 ? "Job Id" : "Alarm Id"));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WorkSpec workSpec = (WorkSpec) it.next();
            SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(workSpec.id);
            sb.append(s(workSpec, TextUtils.join(",", workNameDao.getNamesForWorkSpecId(workSpec.id)), systemIdInfo != null ? Integer.valueOf(systemIdInfo.systemId) : null, TextUtils.join(",", workTagDao.getTagsForWorkSpecId(workSpec.id))));
        }
        return sb.toString();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.a r() {
        WorkDatabase workDatabaseN = androidx.work.impl.k.j(a()).n();
        WorkSpecDao workSpecDao = workDatabaseN.workSpecDao();
        WorkNameDao workNameDao = workDatabaseN.workNameDao();
        WorkTagDao workTagDao = workDatabaseN.workTagDao();
        SystemIdInfoDao systemIdInfoDao = workDatabaseN.systemIdInfoDao();
        List<WorkSpec> recentlyCompletedWork = workSpecDao.getRecentlyCompletedWork(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1L));
        List<WorkSpec> runningWork = workSpecDao.getRunningWork();
        List<WorkSpec> allEligibleWorkSpecsForScheduling = workSpecDao.getAllEligibleWorkSpecsForScheduling(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        if (recentlyCompletedWork != null && !recentlyCompletedWork.isEmpty()) {
            k kVarC = k.c();
            String str = g;
            kVarC.d(str, "Recently completed work:\n\n", new Throwable[0]);
            k.c().d(str, t(workNameDao, workTagDao, systemIdInfoDao, recentlyCompletedWork), new Throwable[0]);
        }
        if (runningWork != null && !runningWork.isEmpty()) {
            k kVarC2 = k.c();
            String str2 = g;
            kVarC2.d(str2, "Running work:\n\n", new Throwable[0]);
            k.c().d(str2, t(workNameDao, workTagDao, systemIdInfoDao, runningWork), new Throwable[0]);
        }
        if (allEligibleWorkSpecsForScheduling != null && !allEligibleWorkSpecsForScheduling.isEmpty()) {
            k kVarC3 = k.c();
            String str3 = g;
            kVarC3.d(str3, "Enqueued work:\n\n", new Throwable[0]);
            k.c().d(str3, t(workNameDao, workTagDao, systemIdInfoDao, allEligibleWorkSpecsForScheduling), new Throwable[0]);
        }
        return ListenableWorker.a.c();
    }
}
