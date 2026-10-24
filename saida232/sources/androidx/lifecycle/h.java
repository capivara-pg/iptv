package androidx.lifecycle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class h extends d {
    private h.a b;
    private d.c c;
    private final WeakReference d;
    private int e;
    private boolean f;
    private boolean g;
    private ArrayList h;
    private final boolean i;

    static class a {
        d.c a;
        e b;

        a(f fVar, d.c cVar) {
            this.b = j.f(fVar);
            this.a = cVar;
        }

        void a(g gVar, d.b bVar) {
            d.c cVarB = bVar.b();
            this.a = h.k(this.a, cVarB);
            this.b.a(gVar, bVar);
            this.a = cVarB;
        }
    }

    public h(g gVar) {
        this(gVar, true);
    }

    private void d(g gVar) {
        Iterator itDescendingIterator = this.b.descendingIterator();
        while (itDescendingIterator.hasNext() && !this.g) {
            Map.Entry entry = (Map.Entry) itDescendingIterator.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.c) > 0 && !this.g && this.b.contains(entry.getKey())) {
                d.b bVarA = d.b.a(aVar.a);
                if (bVarA == null) {
                    throw new IllegalStateException("no event down from " + aVar.a);
                }
                n(bVarA.b());
                aVar.a(gVar, bVarA);
                m();
            }
        }
    }

    private d.c e(f fVar) {
        Map.Entry entryH = this.b.h(fVar);
        d.c cVar = null;
        d.c cVar2 = entryH != null ? ((a) entryH.getValue()).a : null;
        if (!this.h.isEmpty()) {
            ArrayList arrayList = this.h;
            cVar = (d.c) arrayList.get(arrayList.size() - 1);
        }
        return k(k(this.c, cVar2), cVar);
    }

    private void f(String str) {
        if (!this.i || g.a.e().b()) {
            return;
        }
        throw new IllegalStateException("Method " + str + " must be called on the main thread");
    }

    private void g(g gVar) {
        h.b.d dVarC = this.b.c();
        while (dVarC.hasNext() && !this.g) {
            Map.Entry entry = (Map.Entry) dVarC.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.c) < 0 && !this.g && this.b.contains(entry.getKey())) {
                n(aVar.a);
                d.b bVarC = d.b.c(aVar.a);
                if (bVarC == null) {
                    throw new IllegalStateException("no event up from " + aVar.a);
                }
                aVar.a(gVar, bVarC);
                m();
            }
        }
    }

    private boolean i() {
        if (this.b.size() == 0) {
            return true;
        }
        d.c cVar = ((a) this.b.a().getValue()).a;
        d.c cVar2 = ((a) this.b.d().getValue()).a;
        return cVar == cVar2 && this.c == cVar2;
    }

    static d.c k(d.c cVar, d.c cVar2) {
        return (cVar2 == null || cVar2.compareTo(cVar) >= 0) ? cVar : cVar2;
    }

    private void l(d.c cVar) {
        if (this.c == cVar) {
            return;
        }
        this.c = cVar;
        if (this.f || this.e != 0) {
            this.g = true;
            return;
        }
        this.f = true;
        p();
        this.f = false;
    }

    private void m() {
        ArrayList arrayList = this.h;
        arrayList.remove(arrayList.size() - 1);
    }

    private void n(d.c cVar) {
        this.h.add(cVar);
    }

    private void p() {
        g gVar = (g) this.d.get();
        if (gVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!i()) {
            this.g = false;
            if (this.c.compareTo(((a) this.b.a().getValue()).a) < 0) {
                d(gVar);
            }
            Map.Entry entryD = this.b.d();
            if (!this.g && entryD != null && this.c.compareTo(((a) entryD.getValue()).a) > 0) {
                g(gVar);
            }
        }
        this.g = false;
    }

    @Override // androidx.lifecycle.d
    public void a(f fVar) {
        g gVar;
        f("addObserver");
        d.c cVar = this.c;
        d.c cVar2 = d.c.DESTROYED;
        if (cVar != cVar2) {
            cVar2 = d.c.INITIALIZED;
        }
        a aVar = new a(fVar, cVar2);
        if (((a) this.b.f(fVar, aVar)) == null && (gVar = (g) this.d.get()) != null) {
            boolean z = this.e != 0 || this.f;
            d.c cVarE = e(fVar);
            this.e++;
            while (aVar.a.compareTo(cVarE) < 0 && this.b.contains(fVar)) {
                n(aVar.a);
                d.b bVarC = d.b.c(aVar.a);
                if (bVarC == null) {
                    throw new IllegalStateException("no event up from " + aVar.a);
                }
                aVar.a(gVar, bVarC);
                m();
                cVarE = e(fVar);
            }
            if (!z) {
                p();
            }
            this.e--;
        }
    }

    @Override // androidx.lifecycle.d
    public d.c b() {
        return this.c;
    }

    @Override // androidx.lifecycle.d
    public void c(f fVar) {
        f("removeObserver");
        this.b.g(fVar);
    }

    public void h(d.b bVar) {
        f("handleLifecycleEvent");
        l(bVar.b());
    }

    public void j(d.c cVar) {
        f("markState");
        o(cVar);
    }

    public void o(d.c cVar) {
        f("setCurrentState");
        l(cVar);
    }

    private h(g gVar, boolean z) {
        this.b = new h.a();
        this.e = 0;
        this.f = false;
        this.g = false;
        this.h = new ArrayList();
        this.d = new WeakReference(gVar);
        this.c = d.c.INITIALIZED;
        this.i = z;
    }
}
