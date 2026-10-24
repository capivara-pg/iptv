package androidx.fragment.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class r extends androidx.lifecycle.v {
    private static final androidx.lifecycle.w.b j = new a();
    private final boolean f;
    private final HashMap c = new HashMap();
    private final HashMap d = new HashMap();
    private final HashMap e = new HashMap();
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;

    class a implements androidx.lifecycle.w.b {
        a() {
        }

        @Override // androidx.lifecycle.w.b
        public androidx.lifecycle.v a(Class cls) {
            return new r(true);
        }
    }

    r(boolean z) {
        this.f = z;
    }

    static r j(androidx.lifecycle.x xVar) {
        return (r) new androidx.lifecycle.w(xVar, j).a(r.class);
    }

    @Override // androidx.lifecycle.v
    protected void d() {
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("onCleared called for ");
            sb.append(this);
        }
        this.g = true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || r.class != obj.getClass()) {
            return false;
        }
        r rVar = (r) obj;
        return this.c.equals(rVar.c) && this.d.equals(rVar.d) && this.e.equals(rVar.e);
    }

    void f(Fragment fragment) {
        if (this.i) {
            o.F0(2);
            return;
        }
        if (this.c.containsKey(fragment.mWho)) {
            return;
        }
        this.c.put(fragment.mWho, fragment);
        if (o.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Updating retained Fragments: Added ");
            sb.append(fragment);
        }
    }

    void g(Fragment fragment) {
        if (o.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Clearing non-config state for ");
            sb.append(fragment);
        }
        r rVar = (r) this.d.get(fragment.mWho);
        if (rVar != null) {
            rVar.d();
            this.d.remove(fragment.mWho);
        }
        androidx.lifecycle.x xVar = (androidx.lifecycle.x) this.e.get(fragment.mWho);
        if (xVar != null) {
            xVar.a();
            this.e.remove(fragment.mWho);
        }
    }

    Fragment h(String str) {
        return (Fragment) this.c.get(str);
    }

    public int hashCode() {
        return (((this.c.hashCode() * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
    }

    r i(Fragment fragment) {
        r rVar = (r) this.d.get(fragment.mWho);
        if (rVar != null) {
            return rVar;
        }
        r rVar2 = new r(this.f);
        this.d.put(fragment.mWho, rVar2);
        return rVar2;
    }

    Collection k() {
        return new ArrayList(this.c.values());
    }

    androidx.lifecycle.x l(Fragment fragment) {
        androidx.lifecycle.x xVar = (androidx.lifecycle.x) this.e.get(fragment.mWho);
        if (xVar != null) {
            return xVar;
        }
        androidx.lifecycle.x xVar2 = new androidx.lifecycle.x();
        this.e.put(fragment.mWho, xVar2);
        return xVar2;
    }

    boolean m() {
        return this.g;
    }

    void n(Fragment fragment) {
        if (this.i) {
            o.F0(2);
            return;
        }
        if ((this.c.remove(fragment.mWho) != null) && o.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Updating retained Fragments: Removed ");
            sb.append(fragment);
        }
    }

    void o(boolean z) {
        this.i = z;
    }

    boolean p(Fragment fragment) {
        if (this.c.containsKey(fragment.mWho)) {
            return this.f ? this.g : !this.h;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.c.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.d.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.e.keySet().iterator();
        while (it3.hasNext()) {
            sb.append((String) it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
