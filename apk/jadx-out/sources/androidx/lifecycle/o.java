package androidx.lifecycle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class o extends f {
    public static final a j = new a(null);
    private final boolean b;
    private j.a c;
    private f.b d;
    private final WeakReference e;
    private int f;
    private boolean g;
    private boolean h;
    private ArrayList i;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final f.b a(f.b bVar, f.b bVar2) {
            kotlin.jvm.internal.m.g(bVar, "state1");
            return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
        }
    }

    public static final class b {
        private f.b a;
        private i b;

        public b(l lVar, f.b bVar) {
            kotlin.jvm.internal.m.g(bVar, "initialState");
            kotlin.jvm.internal.m.d(lVar);
            this.b = q.f(lVar);
            this.a = bVar;
        }

        public final void a(m mVar, f.a aVar) {
            kotlin.jvm.internal.m.g(aVar, "event");
            f.b bVarB = aVar.b();
            this.a = o.j.a(this.a, bVarB);
            i iVar = this.b;
            kotlin.jvm.internal.m.d(mVar);
            iVar.a(mVar, aVar);
            this.a = bVarB;
        }

        public final f.b b() {
            return this.a;
        }
    }

    private o(m mVar, boolean z) {
        this.b = z;
        this.c = new j.a();
        this.d = f.b.INITIALIZED;
        this.i = new ArrayList();
        this.e = new WeakReference(mVar);
    }

    private final void e(m mVar) {
        Iterator itDescendingIterator = this.c.descendingIterator();
        kotlin.jvm.internal.m.f(itDescendingIterator, "observerMap.descendingIterator()");
        while (itDescendingIterator.hasNext() && !this.h) {
            Map.Entry entry = (Map.Entry) itDescendingIterator.next();
            kotlin.jvm.internal.m.f(entry, "next()");
            l lVar = (l) entry.getKey();
            b bVar = (b) entry.getValue();
            while (bVar.b().compareTo(this.d) > 0 && !this.h && this.c.contains(lVar)) {
                f.a aVarA = f.a.Companion.a(bVar.b());
                if (aVarA == null) {
                    throw new IllegalStateException("no event down from " + bVar.b());
                }
                n(aVarA.b());
                bVar.a(mVar, aVarA);
                m();
            }
        }
    }

    private final f.b f(l lVar) {
        b bVar;
        Map.Entry entryH = this.c.h(lVar);
        f.b bVar2 = null;
        f.b bVarB = (entryH == null || (bVar = (b) entryH.getValue()) == null) ? null : bVar.b();
        if (!this.i.isEmpty()) {
            ArrayList arrayList = this.i;
            bVar2 = (f.b) arrayList.get(arrayList.size() - 1);
        }
        a aVar = j;
        return aVar.a(aVar.a(this.d, bVarB), bVar2);
    }

    private final void g(String str) {
        if (!this.b || i.c.h().c()) {
            return;
        }
        throw new IllegalStateException(("Method " + str + " must be called on the main thread").toString());
    }

    private final void h(m mVar) {
        j.b.d dVarC = this.c.c();
        kotlin.jvm.internal.m.f(dVarC, "observerMap.iteratorWithAdditions()");
        while (dVarC.hasNext() && !this.h) {
            Map.Entry entry = (Map.Entry) dVarC.next();
            l lVar = (l) entry.getKey();
            b bVar = (b) entry.getValue();
            while (bVar.b().compareTo(this.d) < 0 && !this.h && this.c.contains(lVar)) {
                n(bVar.b());
                f.a aVarB = f.a.Companion.b(bVar.b());
                if (aVarB == null) {
                    throw new IllegalStateException("no event up from " + bVar.b());
                }
                bVar.a(mVar, aVarB);
                m();
            }
        }
    }

    private final boolean j() {
        if (this.c.size() == 0) {
            return true;
        }
        Map.Entry entryA = this.c.a();
        kotlin.jvm.internal.m.d(entryA);
        f.b bVarB = ((b) entryA.getValue()).b();
        Map.Entry entryD = this.c.d();
        kotlin.jvm.internal.m.d(entryD);
        f.b bVarB2 = ((b) entryD.getValue()).b();
        return bVarB == bVarB2 && this.d == bVarB2;
    }

    private final void l(f.b bVar) {
        f.b bVar2 = this.d;
        if (bVar2 == bVar) {
            return;
        }
        if (!((bVar2 == f.b.INITIALIZED && bVar == f.b.DESTROYED) ? false : true)) {
            throw new IllegalStateException(("no event down from " + this.d + " in component " + this.e.get()).toString());
        }
        this.d = bVar;
        if (this.g || this.f != 0) {
            this.h = true;
            return;
        }
        this.g = true;
        p();
        this.g = false;
        if (this.d == f.b.DESTROYED) {
            this.c = new j.a();
        }
    }

    private final void m() {
        ArrayList arrayList = this.i;
        arrayList.remove(arrayList.size() - 1);
    }

    private final void n(f.b bVar) {
        this.i.add(bVar);
    }

    private final void p() {
        m mVar = (m) this.e.get();
        if (mVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state.");
        }
        while (!j()) {
            this.h = false;
            f.b bVar = this.d;
            Map.Entry entryA = this.c.a();
            kotlin.jvm.internal.m.d(entryA);
            if (bVar.compareTo(((b) entryA.getValue()).b()) < 0) {
                e(mVar);
            }
            Map.Entry entryD = this.c.d();
            if (!this.h && entryD != null && this.d.compareTo(((b) entryD.getValue()).b()) > 0) {
                h(mVar);
            }
        }
        this.h = false;
    }

    @Override // androidx.lifecycle.f
    public void a(l lVar) {
        m mVar;
        kotlin.jvm.internal.m.g(lVar, "observer");
        g("addObserver");
        f.b bVar = this.d;
        f.b bVar2 = f.b.DESTROYED;
        if (bVar != bVar2) {
            bVar2 = f.b.INITIALIZED;
        }
        b bVar3 = new b(lVar, bVar2);
        if (((b) this.c.f(lVar, bVar3)) == null && (mVar = (m) this.e.get()) != null) {
            boolean z = this.f != 0 || this.g;
            f.b bVarF = f(lVar);
            this.f++;
            while (bVar3.b().compareTo(bVarF) < 0 && this.c.contains(lVar)) {
                n(bVar3.b());
                f.a aVarB = f.a.Companion.b(bVar3.b());
                if (aVarB == null) {
                    throw new IllegalStateException("no event up from " + bVar3.b());
                }
                bVar3.a(mVar, aVarB);
                m();
                bVarF = f(lVar);
            }
            if (!z) {
                p();
            }
            this.f--;
        }
    }

    @Override // androidx.lifecycle.f
    public f.b b() {
        return this.d;
    }

    @Override // androidx.lifecycle.f
    public void d(l lVar) {
        kotlin.jvm.internal.m.g(lVar, "observer");
        g("removeObserver");
        this.c.g(lVar);
    }

    public void i(f.a aVar) {
        kotlin.jvm.internal.m.g(aVar, "event");
        g("handleLifecycleEvent");
        l(aVar.b());
    }

    public void k(f.b bVar) {
        kotlin.jvm.internal.m.g(bVar, "state");
        g("markState");
        o(bVar);
    }

    public void o(f.b bVar) {
        kotlin.jvm.internal.m.g(bVar, "state");
        g("setCurrentState");
        l(bVar);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public o(m mVar) {
        this(mVar, true);
        kotlin.jvm.internal.m.g(mVar, "provider");
    }
}
