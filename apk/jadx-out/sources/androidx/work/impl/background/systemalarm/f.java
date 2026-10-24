package androidx.work.impl.background.systemalarm;

import a1.k;
import android.content.Context;
import androidx.work.impl.model.WorkSpec;

/* JADX INFO: loaded from: classes.dex */
public class f implements androidx.work.impl.e {
    private static final String b = k.f("SystemAlarmScheduler");
    private final Context a;

    public f(Context context) {
        this.a = context.getApplicationContext();
    }

    private void b(WorkSpec workSpec) {
        k.c().a(b, String.format("Scheduling work with workSpecId %s", workSpec.id), new Throwable[0]);
        this.a.startService(b.f(this.a, workSpec.id));
    }

    @Override // androidx.work.impl.e
    public void a(WorkSpec... workSpecArr) {
        for (WorkSpec workSpec : workSpecArr) {
            b(workSpec);
        }
    }

    @Override // androidx.work.impl.e
    public boolean c() {
        return true;
    }

    @Override // androidx.work.impl.e
    public void cancel(String str) {
        this.a.startService(b.g(this.a, str));
    }
}
