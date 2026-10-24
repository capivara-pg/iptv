package androidx.work.impl.background.systemalarm;

import a1.p;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import s0.k;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class c {
    private static final String e = k.f("ConstraintsCmdHandler");
    private final Context a;
    private final int b;
    private final e c;
    private final w0.d d;

    c(Context context, int i, e eVar) {
        this.a = context;
        this.b = i;
        this.c = eVar;
        this.d = new w0.d(context, eVar.f(), (w0.c) null);
    }

    void a() {
        List<p> listD = this.c.g().n().B().d();
        ConstraintProxy.a(this.a, listD);
        this.d.d(listD);
        ArrayList arrayList = new ArrayList(listD.size());
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (p pVar : listD) {
            String str = pVar.a;
            if (jCurrentTimeMillis >= pVar.a() && (!pVar.b() || this.d.c(str))) {
                arrayList.add(pVar);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = ((p) it.next()).a;
            Intent intentB = b.b(this.a, str2);
            k.c().a(e, String.format("Creating a delay_met command for workSpec with id (%s)", str2), new Throwable[0]);
            e eVar = this.c;
            eVar.k(new e.b(eVar, intentB, this.b));
        }
        this.d.e();
    }
}
