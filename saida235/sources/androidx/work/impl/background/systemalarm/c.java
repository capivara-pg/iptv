package androidx.work.impl.background.systemalarm;

import a1.k;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class c {
    private static final String e = k.f("ConstraintsCmdHandler");
    private final Context a;
    private final int b;
    private final e c;
    private final d1.d d;

    c(Context context, int i, e eVar) {
        this.a = context;
        this.b = i;
        this.c = eVar;
        this.d = new d1.d(context, eVar.f(), (d1.c) null);
    }

    void a() {
        List<WorkSpec> scheduledWork = this.c.g().n().workSpecDao().getScheduledWork();
        ConstraintProxy.a(this.a, scheduledWork);
        this.d.d(scheduledWork);
        ArrayList arrayList = new ArrayList(scheduledWork.size());
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (WorkSpec workSpec : scheduledWork) {
            String str = workSpec.id;
            if (jCurrentTimeMillis >= workSpec.calculateNextRunTime() && (!workSpec.hasConstraints() || this.d.c(str))) {
                arrayList.add(workSpec);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = ((WorkSpec) it.next()).id;
            Intent intentB = b.b(this.a, str2);
            k.c().a(e, String.format("Creating a delay_met command for workSpec with id (%s)", str2), new Throwable[0]);
            e eVar = this.c;
            eVar.k(new e.b(eVar, intentB, this.b));
        }
        this.d.e();
    }
}
