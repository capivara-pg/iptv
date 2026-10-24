package androidx.work.impl.workers;

import a1.g;
import a1.h;
import a1.p;
import a1.q;
import a1.t;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import s0.k;
import t0.j;

/* JADX INFO: loaded from: classes.dex */
public class DiagnosticsWorker extends Worker {
    private static final String g = k.f("DiagnosticsWrkr");

    public DiagnosticsWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    private static String s(p pVar, String str, Integer num, String str2) {
        return String.format("\n%s\t %s\t %s\t %s\t %s\t %s\t", pVar.a, pVar.c, num, pVar.b.name(), str, str2);
    }

    private static String t(a1.k kVar, t tVar, h hVar, List list) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n Id \t Class Name\t %s\t State\t Unique Name\t Tags\t", Build.VERSION.SDK_INT >= 23 ? "Job Id" : "Alarm Id"));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            p pVar = (p) it.next();
            g gVarB = hVar.b(pVar.a);
            sb.append(s(pVar, TextUtils.join(",", kVar.a(pVar.a)), gVarB != null ? Integer.valueOf(gVarB.b) : null, TextUtils.join(",", tVar.a(pVar.a))));
        }
        return sb.toString();
    }

    @Override // androidx.work.Worker
    public ListenableWorker.a r() {
        WorkDatabase workDatabaseN = j.j(a()).n();
        q qVarB = workDatabaseN.B();
        a1.k kVarZ = workDatabaseN.z();
        t tVarC = workDatabaseN.C();
        h hVarY = workDatabaseN.y();
        List listC = qVarB.c(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1L));
        List listP = qVarB.p();
        List listJ = qVarB.j(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        if (listC != null && !listC.isEmpty()) {
            k kVarC = k.c();
            String str = g;
            kVarC.d(str, "Recently completed work:\n\n", new Throwable[0]);
            k.c().d(str, t(kVarZ, tVarC, hVarY, listC), new Throwable[0]);
        }
        if (listP != null && !listP.isEmpty()) {
            k kVarC2 = k.c();
            String str2 = g;
            kVarC2.d(str2, "Running work:\n\n", new Throwable[0]);
            k.c().d(str2, t(kVarZ, tVarC, hVarY, listP), new Throwable[0]);
        }
        if (listJ != null && !listJ.isEmpty()) {
            k kVarC3 = k.c();
            String str3 = g;
            kVarC3.d(str3, "Enqueued work:\n\n", new Throwable[0]);
            k.c().d(str3, t(kVarZ, tVarC, hVarY, listJ), new Throwable[0]);
        }
        return ListenableWorker.a.c();
    }
}
