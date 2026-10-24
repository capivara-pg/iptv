package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import b1.n;
import b1.r;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import s0.k;
import t0.j;

/* JADX INFO: loaded from: classes.dex */
public class e implements t0.b {
    static final String k = k.f("SystemAlarmDispatcher");
    final Context a;
    private final c1.a b;
    private final r c;
    private final t0.d d;
    private final j e;
    final androidx.work.impl.background.systemalarm.b f;
    private final Handler g;
    final List h;
    Intent i;
    private c j;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e eVar;
            d dVar;
            synchronized (e.this.h) {
                e eVar2 = e.this;
                eVar2.i = (Intent) eVar2.h.get(0);
            }
            Intent intent = e.this.i;
            if (intent != null) {
                String action = intent.getAction();
                int intExtra = e.this.i.getIntExtra("KEY_START_ID", 0);
                k kVarC = k.c();
                String str = e.k;
                kVarC.a(str, String.format("Processing command %s, %s", e.this.i, Integer.valueOf(intExtra)), new Throwable[0]);
                PowerManager.WakeLock wakeLockB = n.b(e.this.a, String.format("%s (%s)", action, Integer.valueOf(intExtra)));
                try {
                    k.c().a(str, String.format("Acquiring operation wake lock (%s) %s", action, wakeLockB), new Throwable[0]);
                    wakeLockB.acquire();
                    e eVar3 = e.this;
                    eVar3.f.p(eVar3.i, intExtra, eVar3);
                    k.c().a(str, String.format("Releasing operation wake lock (%s) %s", action, wakeLockB), new Throwable[0]);
                    wakeLockB.release();
                    eVar = e.this;
                    dVar = new d(eVar);
                } catch (Throwable th) {
                    try {
                        k kVarC2 = k.c();
                        String str2 = e.k;
                        kVarC2.b(str2, "Unexpected error in onHandleIntent", new Throwable[]{th});
                        k.c().a(str2, String.format("Releasing operation wake lock (%s) %s", action, wakeLockB), new Throwable[0]);
                        wakeLockB.release();
                        eVar = e.this;
                        dVar = new d(eVar);
                    } catch (Throwable th2) {
                        k.c().a(e.k, String.format("Releasing operation wake lock (%s) %s", action, wakeLockB), new Throwable[0]);
                        wakeLockB.release();
                        e eVar4 = e.this;
                        eVar4.k(new d(eVar4));
                        throw th2;
                    }
                }
                eVar.k(dVar);
            }
        }
    }

    static class b implements Runnable {
        private final e a;
        private final Intent b;
        private final int c;

        b(e eVar, Intent intent, int i) {
            this.a = eVar;
            this.b = intent;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, this.c);
        }
    }

    interface c {
        void b();
    }

    static class d implements Runnable {
        private final e a;

        d(e eVar) {
            this.a = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.c();
        }
    }

    e(Context context) {
        this(context, null, null);
    }

    private void b() {
        if (this.g.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }

    private boolean i(String str) {
        b();
        synchronized (this.h) {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                if (str.equals(((Intent) it.next()).getAction())) {
                    return true;
                }
            }
            return false;
        }
    }

    private void l() {
        b();
        PowerManager.WakeLock wakeLockB = n.b(this.a, "ProcessCommand");
        try {
            wakeLockB.acquire();
            this.e.o().b(new a());
        } finally {
            wakeLockB.release();
        }
    }

    public boolean a(Intent intent, int i) {
        k kVarC = k.c();
        String str = k;
        kVarC.a(str, String.format("Adding command %s (%s)", intent, Integer.valueOf(i)), new Throwable[0]);
        b();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            k.c().h(str, "Unknown command. Ignoring", new Throwable[0]);
            return false;
        }
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action) && i("ACTION_CONSTRAINTS_CHANGED")) {
            return false;
        }
        intent.putExtra("KEY_START_ID", i);
        synchronized (this.h) {
            boolean z = this.h.isEmpty() ? false : true;
            this.h.add(intent);
            if (!z) {
                l();
            }
        }
        return true;
    }

    void c() {
        k kVarC = k.c();
        String str = k;
        kVarC.a(str, "Checking if commands are complete.", new Throwable[0]);
        b();
        synchronized (this.h) {
            if (this.i != null) {
                k.c().a(str, String.format("Removing command %s", this.i), new Throwable[0]);
                if (!((Intent) this.h.remove(0)).equals(this.i)) {
                    throw new IllegalStateException("Dequeue-d command is not the first.");
                }
                this.i = null;
            }
            b1.k kVarC2 = this.b.c();
            if (!this.f.o() && this.h.isEmpty() && !kVarC2.a()) {
                k.c().a(str, "No more commands & intents.", new Throwable[0]);
                c cVar = this.j;
                if (cVar != null) {
                    cVar.b();
                }
            } else if (!this.h.isEmpty()) {
                l();
            }
        }
    }

    public void d(String str, boolean z) {
        k(new b(this, androidx.work.impl.background.systemalarm.b.c(this.a, str, z), 0));
    }

    t0.d e() {
        return this.d;
    }

    c1.a f() {
        return this.b;
    }

    j g() {
        return this.e;
    }

    r h() {
        return this.c;
    }

    void j() {
        k.c().a(k, "Destroying SystemAlarmDispatcher", new Throwable[0]);
        this.d.i(this);
        this.c.a();
        this.j = null;
    }

    void k(Runnable runnable) {
        this.g.post(runnable);
    }

    void m(c cVar) {
        if (this.j != null) {
            k.c().b(k, "A completion listener for SystemAlarmDispatcher already exists.", new Throwable[0]);
        } else {
            this.j = cVar;
        }
    }

    e(Context context, t0.d dVar, j jVar) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.f = new androidx.work.impl.background.systemalarm.b(applicationContext);
        this.c = new r();
        jVar = jVar == null ? j.j(context) : jVar;
        this.e = jVar;
        dVar = dVar == null ? jVar.l() : dVar;
        this.d = dVar;
        this.b = jVar.o();
        dVar.c(this);
        this.h = new ArrayList();
        this.i = null;
        this.g = new Handler(Looper.getMainLooper());
    }
}
