package androidx.lifecycle;

import java.util.Map;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class LiveData {
    static final Object k = new Object();
    final Object a = new Object();
    private h.b b = new h.b();
    int c = 0;
    private boolean d;
    private volatile Object e;
    volatile Object f;
    private int g;
    private boolean h;
    private boolean i;
    private final Runnable j;

    class LifecycleBoundObserver extends androidx.lifecycle.LiveData.c implements e {
        final g e;

        LifecycleBoundObserver(g gVar, m mVar) {
            super(mVar);
            this.e = gVar;
        }

        @Override // androidx.lifecycle.e
        public void a(g gVar, d.b bVar) {
            d.c cVarB = this.e.getLifecycle().b();
            if (cVarB == d.c.DESTROYED) {
                LiveData.this.m(this.a);
                return;
            }
            d.c cVar = null;
            while (cVar != cVarB) {
                b(e());
                cVar = cVarB;
                cVarB = this.e.getLifecycle().b();
            }
        }

        void c() {
            this.e.getLifecycle().c(this);
        }

        boolean d(g gVar) {
            return this.e == gVar;
        }

        boolean e() {
            return this.e.getLifecycle().b().a(d.c.STARTED);
        }
    }

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.a) {
                obj = LiveData.this.f;
                LiveData.this.f = LiveData.k;
            }
            LiveData.this.n(obj);
        }
    }

    private class b extends c {
        b(m mVar) {
            super(mVar);
        }

        @Override // androidx.lifecycle.LiveData.c
        boolean e() {
            return true;
        }
    }

    private abstract class c {
        final m a;
        boolean b;
        int c = -1;

        c(m mVar) {
            this.a = mVar;
        }

        void b(boolean z) {
            if (z == this.b) {
                return;
            }
            this.b = z;
            LiveData.this.c(z ? 1 : -1);
            if (this.b) {
                LiveData.this.e(this);
            }
        }

        void c() {
        }

        boolean d(g gVar) {
            return false;
        }

        abstract boolean e();
    }

    public LiveData() {
        Object obj = k;
        this.f = obj;
        this.j = new a();
        this.e = obj;
        this.g = -1;
    }

    static void b(String str) {
        if (g.a.e().b()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    private void d(c cVar) {
        if (cVar.b) {
            if (!cVar.e()) {
                cVar.b(false);
                return;
            }
            int i = cVar.c;
            int i2 = this.g;
            if (i >= i2) {
                return;
            }
            cVar.c = i2;
            cVar.a.a(this.e);
        }
    }

    void c(int i) {
        int i2 = this.c;
        this.c = i + i2;
        if (this.d) {
            return;
        }
        this.d = true;
        while (true) {
            try {
                int i3 = this.c;
                if (i2 == i3) {
                    this.d = false;
                    return;
                }
                boolean z = i2 == 0 && i3 > 0;
                boolean z2 = i2 > 0 && i3 == 0;
                if (z) {
                    j();
                } else if (z2) {
                    k();
                }
                i2 = i3;
            } catch (Throwable th) {
                this.d = false;
                throw th;
            }
        }
    }

    void e(c cVar) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            if (cVar != null) {
                d(cVar);
                cVar = null;
            } else {
                h.b.d dVarC = this.b.c();
                while (dVarC.hasNext()) {
                    d((c) ((Map.Entry) dVarC.next()).getValue());
                    if (this.i) {
                        break;
                    }
                }
            }
        } while (this.i);
        this.h = false;
    }

    public Object f() {
        Object obj = this.e;
        if (obj != k) {
            return obj;
        }
        return null;
    }

    public boolean g() {
        return this.c > 0;
    }

    public void h(g gVar, m mVar) {
        b("observe");
        if (gVar.getLifecycle().b() == d.c.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(gVar, mVar);
        c cVar = (c) this.b.f(mVar, lifecycleBoundObserver);
        if (cVar != null && !cVar.d(gVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVar != null) {
            return;
        }
        gVar.getLifecycle().a(lifecycleBoundObserver);
    }

    public void i(m mVar) {
        b("observeForever");
        b bVar = new b(mVar);
        c cVar = (c) this.b.f(mVar, bVar);
        if (cVar instanceof LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (cVar != null) {
            return;
        }
        bVar.b(true);
    }

    protected void j() {
    }

    protected void k() {
    }

    protected void l(Object obj) {
        boolean z;
        synchronized (this.a) {
            z = this.f == k;
            this.f = obj;
        }
        if (z) {
            g.a.e().c(this.j);
        }
    }

    public void m(m mVar) {
        b("removeObserver");
        c cVar = (c) this.b.g(mVar);
        if (cVar == null) {
            return;
        }
        cVar.c();
        cVar.b(false);
    }

    protected void n(Object obj) {
        b("setValue");
        this.g++;
        this.e = obj;
        e(null);
    }
}
