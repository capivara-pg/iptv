package androidx.work.impl.background.systemalarm;

import a1.p;
import android.content.Context;
import s0.k;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class f implements t0.e {
    private static final String b = k.f("SystemAlarmScheduler");
    private final Context a;

    public f(Context context) {
        this.a = context.getApplicationContext();
    }

    private void b(p pVar) {
        k.c().a(b, String.format("Scheduling work with workSpecId %s", pVar.a), new Throwable[0]);
        this.a.startService(b.f(this.a, pVar.a));
    }

    public void a(p... pVarArr) {
        for (p pVar : pVarArr) {
            b(pVar);
        }
    }

    public boolean c() {
        return true;
    }

    public void cancel(String str) {
        this.a.startService(b.g(this.a, str));
    }
}
