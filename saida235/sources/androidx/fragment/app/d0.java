package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.m0;
import androidx.fragment.R$id;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class d0 {
    private final ViewGroup a;
    final ArrayList b = new ArrayList();
    final ArrayList c = new ArrayList();
    boolean d = false;
    boolean e = false;

    class a implements Runnable {
        final /* synthetic */ d a;

        a(d dVar) {
            this.a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (d0.this.b.contains(this.a)) {
                this.a.e().a(this.a.f().mView);
            }
        }
    }

    class b implements Runnable {
        final /* synthetic */ d a;

        b(d dVar) {
            this.a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            d0.this.b.remove(this.a);
            d0.this.c.remove(this.a);
        }
    }

    static /* synthetic */ class c {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[e.b.values().length];
            b = iArr;
            try {
                iArr[e.b.ADDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[e.b.REMOVING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[e.b.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[e.c.values().length];
            a = iArr2;
            try {
                iArr2[e.c.REMOVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[e.c.VISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[e.c.GONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[e.c.INVISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static class d extends e {
        private final u h;

        d(e.c cVar, e.b bVar, u uVar, z.c cVar2) {
            super(cVar, bVar, uVar.k(), cVar2);
            this.h = uVar;
        }

        @Override // androidx.fragment.app.d0.e
        public void c() {
            super.c();
            this.h.m();
        }

        @Override // androidx.fragment.app.d0.e
        void l() {
            if (g() == e.b.ADDING) {
                Fragment fragmentK = this.h.k();
                View viewFindFocus = fragmentK.mView.findFocus();
                if (viewFindFocus != null) {
                    fragmentK.setFocusedView(viewFindFocus);
                    if (n.F0(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("requestFocus: Saved focused view ");
                        sb.append(viewFindFocus);
                        sb.append(" for Fragment ");
                        sb.append(fragmentK);
                    }
                }
                View viewRequireView = f().requireView();
                if (viewRequireView.getParent() == null) {
                    this.h.b();
                    viewRequireView.setAlpha(0.0f);
                }
                if (viewRequireView.getAlpha() == 0.0f && viewRequireView.getVisibility() == 0) {
                    viewRequireView.setVisibility(4);
                }
                viewRequireView.setAlpha(fragmentK.getPostOnViewCreatedAlpha());
            }
        }
    }

    static class e {
        private c a;
        private b b;
        private final Fragment c;
        private final List d = new ArrayList();
        private final HashSet e = new HashSet();
        private boolean f = false;
        private boolean g = false;

        class a implements z.c.a {
            a() {
            }

            public void onCancel() {
                e.this.b();
            }
        }

        enum b {
            NONE,
            ADDING,
            REMOVING
        }

        enum c {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;

            static c b(int i) {
                if (i == 0) {
                    return VISIBLE;
                }
                if (i == 4) {
                    return INVISIBLE;
                }
                if (i == 8) {
                    return GONE;
                }
                throw new IllegalArgumentException("Unknown visibility " + i);
            }

            static c c(View view) {
                return (view.getAlpha() == 0.0f && view.getVisibility() == 0) ? INVISIBLE : b(view.getVisibility());
            }

            void a(View view) {
                int i = c.a[ordinal()];
                if (i == 1) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        if (n.F0(2)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("SpecialEffectsController: Removing view ");
                            sb.append(view);
                            sb.append(" from container ");
                            sb.append(viewGroup);
                        }
                        viewGroup.removeView(view);
                        return;
                    }
                    return;
                }
                if (i == 2) {
                    if (n.F0(2)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("SpecialEffectsController: Setting view ");
                        sb2.append(view);
                        sb2.append(" to VISIBLE");
                    }
                    view.setVisibility(0);
                    return;
                }
                if (i == 3) {
                    if (n.F0(2)) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("SpecialEffectsController: Setting view ");
                        sb3.append(view);
                        sb3.append(" to GONE");
                    }
                    view.setVisibility(8);
                    return;
                }
                if (i != 4) {
                    return;
                }
                if (n.F0(2)) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("SpecialEffectsController: Setting view ");
                    sb4.append(view);
                    sb4.append(" to INVISIBLE");
                }
                view.setVisibility(4);
            }
        }

        e(c cVar, b bVar, Fragment fragment, z.c cVar2) {
            this.a = cVar;
            this.b = bVar;
            this.c = fragment;
            cVar2.c(new a());
        }

        final void a(Runnable runnable) {
            this.d.add(runnable);
        }

        final void b() {
            if (h()) {
                return;
            }
            this.f = true;
            if (this.e.isEmpty()) {
                c();
                return;
            }
            Iterator it = new ArrayList(this.e).iterator();
            while (it.hasNext()) {
                ((z.c) it.next()).a();
            }
        }

        public void c() {
            if (this.g) {
                return;
            }
            if (n.F0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("SpecialEffectsController: ");
                sb.append(this);
                sb.append(" has called complete.");
            }
            this.g = true;
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }

        public final void d(z.c cVar) {
            if (this.e.remove(cVar) && this.e.isEmpty()) {
                c();
            }
        }

        public c e() {
            return this.a;
        }

        public final Fragment f() {
            return this.c;
        }

        b g() {
            return this.b;
        }

        final boolean h() {
            return this.f;
        }

        final boolean i() {
            return this.g;
        }

        public final void j(z.c cVar) {
            l();
            this.e.add(cVar);
        }

        final void k(c cVar, b bVar) {
            int i = c.b[bVar.ordinal()];
            if (i == 1) {
                if (this.a == c.REMOVED) {
                    if (n.F0(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("SpecialEffectsController: For fragment ");
                        sb.append(this.c);
                        sb.append(" mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = ");
                        sb.append(this.b);
                        sb.append(" to ADDING.");
                    }
                    this.a = c.VISIBLE;
                    this.b = b.ADDING;
                    return;
                }
                return;
            }
            if (i == 2) {
                if (n.F0(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("SpecialEffectsController: For fragment ");
                    sb2.append(this.c);
                    sb2.append(" mFinalState = ");
                    sb2.append(this.a);
                    sb2.append(" -> REMOVED. mLifecycleImpact  = ");
                    sb2.append(this.b);
                    sb2.append(" to REMOVING.");
                }
                this.a = c.REMOVED;
                this.b = b.REMOVING;
                return;
            }
            if (i == 3 && this.a != c.REMOVED) {
                if (n.F0(2)) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("SpecialEffectsController: For fragment ");
                    sb3.append(this.c);
                    sb3.append(" mFinalState = ");
                    sb3.append(this.a);
                    sb3.append(" -> ");
                    sb3.append(cVar);
                    sb3.append(". ");
                }
                this.a = cVar;
            }
        }

        abstract void l();

        public String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.a + "} {mLifecycleImpact = " + this.b + "} {mFragment = " + this.c + "}";
        }
    }

    d0(ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    private void a(e.c cVar, e.b bVar, u uVar) {
        synchronized (this.b) {
            z.c cVar2 = new z.c();
            e eVarH = h(uVar.k());
            if (eVarH != null) {
                eVarH.k(cVar, bVar);
                return;
            }
            d dVar = new d(cVar, bVar, uVar, cVar2);
            this.b.add(dVar);
            dVar.a(new a(dVar));
            dVar.a(new b(dVar));
        }
    }

    private e h(Fragment fragment) {
        for (e eVar : this.b) {
            if (eVar.f().equals(fragment) && !eVar.h()) {
                return eVar;
            }
        }
        return null;
    }

    private e i(Fragment fragment) {
        for (e eVar : this.c) {
            if (eVar.f().equals(fragment) && !eVar.h()) {
                return eVar;
            }
        }
        return null;
    }

    static d0 n(ViewGroup viewGroup, n nVar) {
        return o(viewGroup, nVar.y0());
    }

    static d0 o(ViewGroup viewGroup, e0 e0Var) {
        int i = R$id.special_effects_controller_view_tag;
        Object tag = viewGroup.getTag(i);
        if (tag instanceof d0) {
            return (d0) tag;
        }
        d0 d0VarA = e0Var.a(viewGroup);
        viewGroup.setTag(i, d0VarA);
        return d0VarA;
    }

    private void q() {
        for (e eVar : this.b) {
            if (eVar.g() == e.b.ADDING) {
                eVar.k(e.c.b(eVar.f().requireView().getVisibility()), e.b.NONE);
            }
        }
    }

    void b(e.c cVar, u uVar) {
        if (n.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("SpecialEffectsController: Enqueuing add operation for fragment ");
            sb.append(uVar.k());
        }
        a(cVar, e.b.ADDING, uVar);
    }

    void c(u uVar) {
        if (n.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("SpecialEffectsController: Enqueuing hide operation for fragment ");
            sb.append(uVar.k());
        }
        a(e.c.GONE, e.b.NONE, uVar);
    }

    void d(u uVar) {
        if (n.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("SpecialEffectsController: Enqueuing remove operation for fragment ");
            sb.append(uVar.k());
        }
        a(e.c.REMOVED, e.b.REMOVING, uVar);
    }

    void e(u uVar) {
        if (n.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("SpecialEffectsController: Enqueuing show operation for fragment ");
            sb.append(uVar.k());
        }
        a(e.c.VISIBLE, e.b.NONE, uVar);
    }

    abstract void f(List list, boolean z);

    void g() {
        if (this.e) {
            return;
        }
        if (!m0.Q(this.a)) {
            j();
            this.d = false;
            return;
        }
        synchronized (this.b) {
            if (!this.b.isEmpty()) {
                ArrayList<e> arrayList = new ArrayList(this.c);
                this.c.clear();
                for (e eVar : arrayList) {
                    if (n.F0(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("SpecialEffectsController: Cancelling operation ");
                        sb.append(eVar);
                    }
                    eVar.b();
                    if (!eVar.i()) {
                        this.c.add(eVar);
                    }
                }
                q();
                ArrayList arrayList2 = new ArrayList(this.b);
                this.b.clear();
                this.c.addAll(arrayList2);
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((e) it.next()).l();
                }
                f(arrayList2, this.d);
                this.d = false;
            }
        }
    }

    void j() {
        boolean zQ = m0.Q(this.a);
        synchronized (this.b) {
            q();
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                ((e) it.next()).l();
            }
            for (e eVar : new ArrayList(this.c)) {
                if (n.F0(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("SpecialEffectsController: ");
                    sb.append(zQ ? "" : "Container " + this.a + " is not attached to window. ");
                    sb.append("Cancelling running operation ");
                    sb.append(eVar);
                }
                eVar.b();
            }
            for (e eVar2 : new ArrayList(this.b)) {
                if (n.F0(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("SpecialEffectsController: ");
                    sb2.append(zQ ? "" : "Container " + this.a + " is not attached to window. ");
                    sb2.append("Cancelling pending operation ");
                    sb2.append(eVar2);
                }
                eVar2.b();
            }
        }
    }

    void k() {
        if (this.e) {
            this.e = false;
            g();
        }
    }

    e.b l(u uVar) {
        e eVarH = h(uVar.k());
        e.b bVarG = eVarH != null ? eVarH.g() : null;
        e eVarI = i(uVar.k());
        return (eVarI == null || !(bVarG == null || bVarG == e.b.NONE)) ? bVarG : eVarI.g();
    }

    public ViewGroup m() {
        return this.a;
    }

    void p() {
        synchronized (this.b) {
            q();
            this.e = false;
            for (int size = this.b.size() - 1; size >= 0; size--) {
                e eVar = (e) this.b.get(size);
                e.c cVarC = e.c.c(eVar.f().mView);
                e.c cVarE = eVar.e();
                e.c cVar = e.c.VISIBLE;
                if (cVarE == cVar && cVarC != cVar) {
                    this.e = eVar.f().isPostponed();
                    break;
                }
            }
        }
    }

    void r(boolean z) {
        this.d = z;
    }
}
