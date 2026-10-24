package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class x {
    private final ArrayList a = new ArrayList();
    private final HashMap b = new HashMap();
    private r c;

    x() {
    }

    void a(Fragment fragment) {
        if (this.a.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.a) {
            this.a.add(fragment);
        }
        fragment.mAdded = true;
    }

    void b() {
        this.b.values().removeAll(Collections.singleton(null));
    }

    boolean c(String str) {
        return this.b.get(str) != null;
    }

    void d(int i) {
        for (v vVar : this.b.values()) {
            if (vVar != null) {
                vVar.u(i);
            }
        }
    }

    void e(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str + "    ";
        if (!this.b.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (v vVar : this.b.values()) {
                printWriter.print(str);
                if (vVar != null) {
                    Fragment fragmentK = vVar.k();
                    printWriter.println(fragmentK);
                    fragmentK.dump(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size = this.a.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size; i++) {
                Fragment fragment = (Fragment) this.a.get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
    }

    Fragment f(String str) {
        v vVar = (v) this.b.get(str);
        if (vVar != null) {
            return vVar.k();
        }
        return null;
    }

    Fragment g(int i) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) this.a.get(size);
            if (fragment != null && fragment.mFragmentId == i) {
                return fragment;
            }
        }
        for (v vVar : this.b.values()) {
            if (vVar != null) {
                Fragment fragmentK = vVar.k();
                if (fragmentK.mFragmentId == i) {
                    return fragmentK;
                }
            }
        }
        return null;
    }

    Fragment h(String str) {
        if (str != null) {
            for (int size = this.a.size() - 1; size >= 0; size--) {
                Fragment fragment = (Fragment) this.a.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (v vVar : this.b.values()) {
            if (vVar != null) {
                Fragment fragmentK = vVar.k();
                if (str.equals(fragmentK.mTag)) {
                    return fragmentK;
                }
            }
        }
        return null;
    }

    Fragment i(String str) {
        Fragment fragmentFindFragmentByWho;
        for (v vVar : this.b.values()) {
            if (vVar != null && (fragmentFindFragmentByWho = vVar.k().findFragmentByWho(str)) != null) {
                return fragmentFindFragmentByWho;
            }
        }
        return null;
    }

    int j(Fragment fragment) {
        View view;
        View view2;
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup == null) {
            return -1;
        }
        int iIndexOf = this.a.indexOf(fragment);
        for (int i = iIndexOf - 1; i >= 0; i--) {
            Fragment fragment2 = (Fragment) this.a.get(i);
            if (fragment2.mContainer == viewGroup && (view2 = fragment2.mView) != null) {
                return viewGroup.indexOfChild(view2) + 1;
            }
        }
        while (true) {
            iIndexOf++;
            if (iIndexOf >= this.a.size()) {
                return -1;
            }
            Fragment fragment3 = (Fragment) this.a.get(iIndexOf);
            if (fragment3.mContainer == viewGroup && (view = fragment3.mView) != null) {
                return viewGroup.indexOfChild(view);
            }
        }
    }

    List k() {
        ArrayList arrayList = new ArrayList();
        for (v vVar : this.b.values()) {
            if (vVar != null) {
                arrayList.add(vVar);
            }
        }
        return arrayList;
    }

    List l() {
        ArrayList arrayList = new ArrayList();
        for (v vVar : this.b.values()) {
            if (vVar != null) {
                arrayList.add(vVar.k());
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    v m(String str) {
        return (v) this.b.get(str);
    }

    List n() {
        ArrayList arrayList;
        if (this.a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.a) {
            arrayList = new ArrayList(this.a);
        }
        return arrayList;
    }

    r o() {
        return this.c;
    }

    void p(v vVar) {
        Fragment fragmentK = vVar.k();
        if (c(fragmentK.mWho)) {
            return;
        }
        this.b.put(fragmentK.mWho, vVar);
        if (fragmentK.mRetainInstanceChangedWhileDetached) {
            if (fragmentK.mRetainInstance) {
                this.c.f(fragmentK);
            } else {
                this.c.n(fragmentK);
            }
            fragmentK.mRetainInstanceChangedWhileDetached = false;
        }
        if (o.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Added fragment to active set ");
            sb.append(fragmentK);
        }
    }

    void q(v vVar) {
        Fragment fragmentK = vVar.k();
        if (fragmentK.mRetainInstance) {
            this.c.n(fragmentK);
        }
        if (((v) this.b.put(fragmentK.mWho, null)) != null && o.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Removed fragment from active set ");
            sb.append(fragmentK);
        }
    }

    void r() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            v vVar = (v) this.b.get(((Fragment) it.next()).mWho);
            if (vVar != null) {
                vVar.m();
            }
        }
        for (v vVar2 : this.b.values()) {
            if (vVar2 != null) {
                vVar2.m();
                Fragment fragmentK = vVar2.k();
                if (fragmentK.mRemoving && !fragmentK.isInBackStack()) {
                    q(vVar2);
                }
            }
        }
    }

    void s(Fragment fragment) {
        synchronized (this.a) {
            this.a.remove(fragment);
        }
        fragment.mAdded = false;
    }

    void t() {
        this.b.clear();
    }

    void u(List list) {
        this.a.clear();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                Fragment fragmentF = f(str);
                if (fragmentF == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
                if (o.F0(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("restoreSaveState: added (");
                    sb.append(str);
                    sb.append("): ");
                    sb.append(fragmentF);
                }
                a(fragmentF);
            }
        }
    }

    ArrayList v() {
        ArrayList arrayList = new ArrayList(this.b.size());
        for (v vVar : this.b.values()) {
            if (vVar != null) {
                Fragment fragmentK = vVar.k();
                u uVarS = vVar.s();
                arrayList.add(uVarS);
                if (o.F0(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Saved state of ");
                    sb.append(fragmentK);
                    sb.append(": ");
                    sb.append(uVarS.m);
                }
            }
        }
        return arrayList;
    }

    ArrayList w() {
        synchronized (this.a) {
            if (this.a.isEmpty()) {
                return null;
            }
            ArrayList arrayList = new ArrayList(this.a.size());
            for (Fragment fragment : this.a) {
                arrayList.add(fragment.mWho);
                if (o.F0(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("saveAllState: adding fragment (");
                    sb.append(fragment.mWho);
                    sb.append("): ");
                    sb.append(fragment);
                }
            }
            return arrayList;
        }
    }

    void x(r rVar) {
        this.c = rVar;
    }
}
