package androidx.work.impl;

import android.content.Context;
import android.os.PowerManager;
import androidx.work.WorkerParameters;
import com.google.common.util.concurrent.ListenableFuture;
import i1.n;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes.dex */
public class d implements b, g1.a {
    private static final String l = a1.k.f("Processor");
    private Context b;
    private androidx.work.a c;
    private j1.a d;
    private WorkDatabase e;
    private List h;
    private Map g = new HashMap();
    private Map f = new HashMap();
    private Set i = new HashSet();
    private final List j = new ArrayList();
    private PowerManager.WakeLock a = null;
    private final Object k = new Object();

    private static class a implements Runnable {
        private b a;
        private String b;
        private ListenableFuture c;

        a(b bVar, String str, ListenableFuture listenableFuture) {
            this.a = bVar;
            this.b = str;
            this.c = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean zBooleanValue;
            try {
                zBooleanValue = ((Boolean) this.c.get()).booleanValue();
            } catch (InterruptedException | ExecutionException unused) {
                zBooleanValue = true;
            }
            this.a.d(this.b, zBooleanValue);
        }
    }

    public d(Context context, androidx.work.a aVar, j1.a aVar2, WorkDatabase workDatabase, List list) {
        this.b = context;
        this.c = aVar;
        this.d = aVar2;
        this.e = workDatabase;
        this.h = list;
    }

    private static boolean e(String str, l lVar) {
        if (lVar == null) {
            a1.k.c().a(l, String.format("WorkerWrapper could not be found for %s", str), new Throwable[0]);
            return false;
        }
        lVar.d();
        a1.k.c().a(l, String.format("WorkerWrapper interrupted for %s", str), new Throwable[0]);
        return true;
    }

    private void m() {
        synchronized (this.k) {
            if (!(!this.f.isEmpty())) {
                try {
                    this.b.startService(androidx.work.impl.foreground.a.f(this.b));
                } catch (Throwable th) {
                    a1.k.c().b(l, "Unable to stop foreground service", new Throwable[]{th});
                }
                PowerManager.WakeLock wakeLock = this.a;
                if (wakeLock != null) {
                    wakeLock.release();
                    this.a = null;
                }
            }
        }
    }

    public void a(String str) {
        synchronized (this.k) {
            this.f.remove(str);
            m();
        }
    }

    public void b(String str, a1.e eVar) {
        synchronized (this.k) {
            a1.k.c().d(l, String.format("Moving WorkSpec (%s) to the foreground", str), new Throwable[0]);
            l lVar = (l) this.g.remove(str);
            if (lVar != null) {
                if (this.a == null) {
                    PowerManager.WakeLock wakeLockB = n.b(this.b, "ProcessorForegroundLck");
                    this.a = wakeLockB;
                    wakeLockB.acquire();
                }
                this.f.put(str, lVar);
                androidx.core.content.a.startForegroundService(this.b, androidx.work.impl.foreground.a.c(this.b, str, eVar));
            }
        }
    }

    public void c(b bVar) {
        synchronized (this.k) {
            this.j.add(bVar);
        }
    }

    @Override // androidx.work.impl.b
    public void d(String str, boolean z) {
        synchronized (this.k) {
            this.g.remove(str);
            a1.k.c().a(l, String.format("%s %s executed; reschedule = %s", getClass().getSimpleName(), str, Boolean.valueOf(z)), new Throwable[0]);
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                ((b) it.next()).d(str, z);
            }
        }
    }

    public boolean f(String str) {
        boolean zContains;
        synchronized (this.k) {
            zContains = this.i.contains(str);
        }
        return zContains;
    }

    public boolean g(String str) {
        boolean z;
        synchronized (this.k) {
            z = this.g.containsKey(str) || this.f.containsKey(str);
        }
        return z;
    }

    public boolean h(String str) {
        boolean zContainsKey;
        synchronized (this.k) {
            zContainsKey = this.f.containsKey(str);
        }
        return zContainsKey;
    }

    public void i(b bVar) {
        synchronized (this.k) {
            this.j.remove(bVar);
        }
    }

    public boolean j(String str) {
        return k(str, null);
    }

    public boolean k(String str, WorkerParameters.a aVar) {
        synchronized (this.k) {
            if (g(str)) {
                a1.k.c().a(l, String.format("Work %s is already enqueued for processing", str), new Throwable[0]);
                return false;
            }
            l lVarA = new l.c(this.b, this.c, this.d, this, this.e, str).c(this.h).b(aVar).a();
            ListenableFuture listenableFutureB = lVarA.b();
            listenableFutureB.addListener(new a(this, str, listenableFutureB), this.d.a());
            this.g.put(str, lVarA);
            this.d.c().execute(lVarA);
            a1.k.c().a(l, String.format("%s: processing %s", getClass().getSimpleName(), str), new Throwable[0]);
            return true;
        }
    }

    public boolean l(String str) {
        boolean zE;
        synchronized (this.k) {
            boolean z = true;
            a1.k.c().a(l, String.format("Processor cancelling %s", str), new Throwable[0]);
            this.i.add(str);
            l lVar = (l) this.f.remove(str);
            if (lVar == null) {
                z = false;
            }
            if (lVar == null) {
                lVar = (l) this.g.remove(str);
            }
            zE = e(str, lVar);
            if (z) {
                m();
            }
        }
        return zE;
    }

    public boolean n(String str) {
        boolean zE;
        synchronized (this.k) {
            a1.k.c().a(l, String.format("Processor stopping foreground work %s", str), new Throwable[0]);
            zE = e(str, (l) this.f.remove(str));
        }
        return zE;
    }

    public boolean o(String str) {
        boolean zE;
        synchronized (this.k) {
            a1.k.c().a(l, String.format("Processor stopping background work %s", str), new Throwable[0]);
            zE = e(str, (l) this.g.remove(str));
        }
        return zE;
    }
}
