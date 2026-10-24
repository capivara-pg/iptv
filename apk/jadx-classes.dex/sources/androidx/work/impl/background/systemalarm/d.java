package androidx.work.impl.background.systemalarm;

import a1.p;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import b1.n;
import b1.r;
import java.util.Collections;
import java.util.List;
import s0.k;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class d implements w0.c, t0.b, r.b {
    private static final String j = k.f("DelayMetCommandHandler");
    private final Context a;
    private final int b;
    private final String c;
    private final e d;
    private final w0.d e;
    private PowerManager.WakeLock h;
    private boolean i = false;
    private int g = 0;
    private final Object f = new Object();

    d(Context context, int i, String str, e eVar) {
        this.a = context;
        this.b = i;
        this.d = eVar;
        this.c = str;
        this.e = new w0.d(context, eVar.f(), this);
    }

    private void c() {
        synchronized (this.f) {
            this.e.e();
            this.d.h().c(this.c);
            PowerManager.WakeLock wakeLock = this.h;
            if (wakeLock != null && wakeLock.isHeld()) {
                k.c().a(j, String.format("Releasing wakelock %s for WorkSpec %s", this.h, this.c), new Throwable[0]);
                this.h.release();
            }
        }
    }

    private void g() {
        synchronized (this.f) {
            if (this.g < 2) {
                this.g = 2;
                k kVarC = k.c();
                String str = j;
                kVarC.a(str, String.format("Stopping work for WorkSpec %s", this.c), new Throwable[0]);
                Intent intentG = b.g(this.a, this.c);
                e eVar = this.d;
                eVar.k(new e.b(eVar, intentG, this.b));
                if (this.d.e().g(this.c)) {
                    k.c().a(str, String.format("WorkSpec %s needs to be rescheduled", this.c), new Throwable[0]);
                    Intent intentF = b.f(this.a, this.c);
                    e eVar2 = this.d;
                    eVar2.k(new e.b(eVar2, intentF, this.b));
                } else {
                    k.c().a(str, String.format("Processor does not have WorkSpec %s. No need to reschedule ", this.c), new Throwable[0]);
                }
            } else {
                k.c().a(j, String.format("Already stopped work for %s", this.c), new Throwable[0]);
            }
        }
    }

    public void a(String str) {
        k.c().a(j, String.format("Exceeded time limits on execution for %s", str), new Throwable[0]);
        g();
    }

    public void b(List list) {
        g();
    }

    public void d(String str, boolean z) {
        k.c().a(j, String.format("onExecuted %s, %s", str, Boolean.valueOf(z)), new Throwable[0]);
        c();
        if (z) {
            Intent intentF = b.f(this.a, this.c);
            e eVar = this.d;
            eVar.k(new e.b(eVar, intentF, this.b));
        }
        if (this.i) {
            Intent intentA = b.a(this.a);
            e eVar2 = this.d;
            eVar2.k(new e.b(eVar2, intentA, this.b));
        }
    }

    public void e(List list) {
        if (list.contains(this.c)) {
            synchronized (this.f) {
                if (this.g == 0) {
                    this.g = 1;
                    k.c().a(j, String.format("onAllConstraintsMet for %s", this.c), new Throwable[0]);
                    if (this.d.e().j(this.c)) {
                        this.d.h().b(this.c, 600000L, this);
                    } else {
                        c();
                    }
                } else {
                    k.c().a(j, String.format("Already started work for %s", this.c), new Throwable[0]);
                }
            }
        }
    }

    void f() {
        this.h = n.b(this.a, String.format("%s (%s)", this.c, Integer.valueOf(this.b)));
        k kVarC = k.c();
        String str = j;
        kVarC.a(str, String.format("Acquiring wakelock %s for WorkSpec %s", this.h, this.c), new Throwable[0]);
        this.h.acquire();
        p pVarH = this.d.g().n().B().h(this.c);
        if (pVarH == null) {
            g();
            return;
        }
        boolean zB = pVarH.b();
        this.i = zB;
        if (zB) {
            this.e.d(Collections.singletonList(pVarH));
        } else {
            k.c().a(str, String.format("No constraints for %s", this.c), new Throwable[0]);
            e(Collections.singletonList(this.c));
        }
    }
}
