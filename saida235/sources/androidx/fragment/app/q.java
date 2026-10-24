package androidx.fragment.app;

import androidx.lifecycle.g0;
import androidx.lifecycle.h0;
import androidx.lifecycle.j0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class q extends androidx.lifecycle.f0 {
    private static final g0.b k = new a();
    private final boolean g;
    private final HashMap d = new HashMap();
    private final HashMap e = new HashMap();
    private final HashMap f = new HashMap();
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;

    class a implements g0.b {
        a() {
        }

        @Override // androidx.lifecycle.g0.b
        public /* synthetic */ androidx.lifecycle.f0 a(Class cls, j0.a aVar) {
            return h0.b(this, cls, aVar);
        }

        @Override // androidx.lifecycle.g0.b
        public androidx.lifecycle.f0 b(Class cls) {
            return new q(true);
        }
    }

    q(boolean z) {
        this.g = z;
    }

    static q j(j0 j0Var) {
        return (q) new g0(j0Var, k).a(q.class);
    }

    @Override // androidx.lifecycle.f0
    protected void d() {
        if (n.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("onCleared called for ");
            sb.append(this);
        }
        this.h = true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || q.class != obj.getClass()) {
            return false;
        }
        q qVar = (q) obj;
        return this.d.equals(qVar.d) && this.e.equals(qVar.e) && this.f.equals(qVar.f);
    }

    void f(Fragment fragment) {
        if (this.j) {
            n.F0(2);
            return;
        }
        if (this.d.containsKey(fragment.mWho)) {
            return;
        }
        this.d.put(fragment.mWho, fragment);
        if (n.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Updating retained Fragments: Added ");
            sb.append(fragment);
        }
    }

    void g(Fragment fragment) {
        if (n.F0(3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Clearing non-config state for ");
            sb.append(fragment);
        }
        q qVar = (q) this.e.get(fragment.mWho);
        if (qVar != null) {
            qVar.d();
            this.e.remove(fragment.mWho);
        }
        j0 j0Var = (j0) this.f.get(fragment.mWho);
        if (j0Var != null) {
            j0Var.a();
            this.f.remove(fragment.mWho);
        }
    }

    Fragment h(String str) {
        return (Fragment) this.d.get(str);
    }

    public int hashCode() {
        return (((this.d.hashCode() * 31) + this.e.hashCode()) * 31) + this.f.hashCode();
    }

    q i(Fragment fragment) {
        q qVar = (q) this.e.get(fragment.mWho);
        if (qVar != null) {
            return qVar;
        }
        q qVar2 = new q(this.g);
        this.e.put(fragment.mWho, qVar2);
        return qVar2;
    }

    Collection k() {
        return new ArrayList(this.d.values());
    }

    j0 l(Fragment fragment) {
        j0 j0Var = (j0) this.f.get(fragment.mWho);
        if (j0Var != null) {
            return j0Var;
        }
        j0 j0Var2 = new j0();
        this.f.put(fragment.mWho, j0Var2);
        return j0Var2;
    }

    boolean m() {
        return this.h;
    }

    void n(Fragment fragment) {
        if (this.j) {
            n.F0(2);
            return;
        }
        if ((this.d.remove(fragment.mWho) != null) && n.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Updating retained Fragments: Removed ");
            sb.append(fragment);
        }
    }

    void o(boolean z) {
        this.j = z;
    }

    boolean p(Fragment fragment) {
        if (this.d.containsKey(fragment.mWho)) {
            return this.g ? this.h : !this.i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.d.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.e.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.f.keySet().iterator();
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
