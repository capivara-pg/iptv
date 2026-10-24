package androidx.fragment.app;

import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class a extends y implements o.m {
    final o t;
    boolean u;
    int v;

    a(o oVar) {
        super(oVar.q0(), oVar.t0() != null ? oVar.t0().f().getClassLoader() : null);
        this.v = -1;
        this.t = oVar;
    }

    private static boolean F(y.a aVar) {
        Fragment fragment = aVar.b;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    void A(boolean z) {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            y.a aVar = (y.a) this.c.get(size);
            Fragment fragment = aVar.b;
            if (fragment != null) {
                fragment.setPopDirection(true);
                fragment.setNextTransition(o.i1(this.h));
                fragment.setSharedElementNames(this.q, this.p);
            }
            switch (aVar.a) {
                case 1:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.m1(fragment, true);
                    this.t.d1(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.a);
                case 3:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.g(fragment);
                    break;
                case 4:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.q1(fragment);
                    break;
                case 5:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.m1(fragment, true);
                    this.t.C0(fragment);
                    break;
                case 6:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.l(fragment);
                    break;
                case 7:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.m1(fragment, true);
                    this.t.x(fragment);
                    break;
                case 8:
                    this.t.o1(null);
                    break;
                case 9:
                    this.t.o1(fragment);
                    break;
                case 10:
                    this.t.n1(fragment, aVar.g);
                    break;
            }
            if (!this.r && aVar.a != 3 && fragment != null && !o.P) {
                this.t.P0(fragment);
            }
        }
        if (this.r || !z || o.P) {
            return;
        }
        o oVar = this.t;
        oVar.Q0(oVar.q, true);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x00b2  */
    Fragment B(ArrayList arrayList, Fragment fragment) {
        Fragment fragment2 = fragment;
        int i = 0;
        while (i < this.c.size()) {
            y.a aVar = (y.a) this.c.get(i);
            int i2 = aVar.a;
            if (i2 == 1) {
                arrayList.add(aVar.b);
            } else if (i2 == 2) {
                Fragment fragment3 = aVar.b;
                int i3 = fragment3.mContainerId;
                boolean z = false;
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    Fragment fragment4 = (Fragment) arrayList.get(size);
                    if (fragment4.mContainerId == i3) {
                        if (fragment4 == fragment3) {
                            z = true;
                        } else {
                            if (fragment4 == fragment2) {
                                this.c.add(i, new y.a(9, fragment4));
                                i++;
                                fragment2 = null;
                            }
                            y.a aVar2 = new y.a(3, fragment4);
                            aVar2.c = aVar.c;
                            aVar2.e = aVar.e;
                            aVar2.d = aVar.d;
                            aVar2.f = aVar.f;
                            this.c.add(i, aVar2);
                            arrayList.remove(fragment4);
                            i++;
                        }
                    }
                }
                if (z) {
                    this.c.remove(i);
                    i--;
                } else {
                    aVar.a = 1;
                    arrayList.add(fragment3);
                }
            } else if (i2 == 3 || i2 == 6) {
                arrayList.remove(aVar.b);
                Fragment fragment5 = aVar.b;
                if (fragment5 == fragment2) {
                    this.c.add(i, new y.a(9, fragment5));
                    i++;
                    fragment2 = null;
                }
            } else if (i2 == 7) {
                arrayList.add(aVar.b);
            } else if (i2 == 8) {
                this.c.add(i, new y.a(9, fragment2));
                i++;
                fragment2 = aVar.b;
            }
            i++;
        }
        return fragment2;
    }

    public String C() {
        return this.k;
    }

    boolean D(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = ((y.a) this.c.get(i2)).b;
            int i3 = fragment != null ? fragment.mContainerId : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    boolean E(ArrayList arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.c.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = ((y.a) this.c.get(i4)).b;
            int i5 = fragment != null ? fragment.mContainerId : 0;
            if (i5 != 0 && i5 != i3) {
                for (int i6 = i; i6 < i2; i6++) {
                    a aVar = (a) arrayList.get(i6);
                    int size2 = aVar.c.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        Fragment fragment2 = ((y.a) aVar.c.get(i7)).b;
                        if ((fragment2 != null ? fragment2.mContainerId : 0) == i5) {
                            return true;
                        }
                    }
                }
                i3 = i5;
            }
        }
        return false;
    }

    boolean G() {
        for (int i = 0; i < this.c.size(); i++) {
            if (F((y.a) this.c.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void H() {
        if (this.f27s != null) {
            for (int i = 0; i < this.f27s.size(); i++) {
                ((Runnable) this.f27s.get(i)).run();
            }
            this.f27s = null;
        }
    }

    void I(Fragment.l lVar) {
        for (int i = 0; i < this.c.size(); i++) {
            y.a aVar = (y.a) this.c.get(i);
            if (F(aVar)) {
                aVar.b.setOnStartEnterTransitionListener(lVar);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0027  */
    /* JADX WARN: Code duplicated, block: B:14:0x002d  */
    Fragment J(ArrayList arrayList, Fragment fragment) {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            y.a aVar = (y.a) this.c.get(size);
            int i = aVar.a;
            if (i == 1) {
                arrayList.remove(aVar.b);
            } else if (i != 3) {
                switch (i) {
                    case 6:
                        arrayList.add(aVar.b);
                        break;
                    case 7:
                        arrayList.remove(aVar.b);
                        break;
                    case 8:
                        fragment = null;
                        break;
                    case 9:
                        fragment = aVar.b;
                        break;
                    case 10:
                        aVar.h = aVar.g;
                        break;
                }
            } else {
                arrayList.add(aVar.b);
            }
        }
        return fragment;
    }

    @Override // androidx.fragment.app.o.m
    public boolean a(ArrayList arrayList, ArrayList arrayList2) {
        if (o.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Run: ");
            sb.append(this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.i) {
            return true;
        }
        this.t.e(this);
        return true;
    }

    @Override // androidx.fragment.app.y
    public int h() {
        return w(false);
    }

    @Override // androidx.fragment.app.y
    public int i() {
        return w(true);
    }

    @Override // androidx.fragment.app.y
    public void j() {
        m();
        this.t.b0(this, false);
    }

    @Override // androidx.fragment.app.y
    public void k() {
        m();
        this.t.b0(this, true);
    }

    @Override // androidx.fragment.app.y
    public y l(Fragment fragment) {
        o oVar = fragment.mFragmentManager;
        if (oVar == null || oVar == this.t) {
            return super.l(fragment);
        }
        throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.y
    void n(int i, Fragment fragment, String str, int i2) {
        super.n(i, fragment, str, i2);
        fragment.mFragmentManager = this.t;
    }

    @Override // androidx.fragment.app.y
    public y o(Fragment fragment) {
        o oVar = fragment.mFragmentManager;
        if (oVar == null || oVar == this.t) {
            return super.o(fragment);
        }
        throw new IllegalStateException("Cannot hide Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.y
    public y p(Fragment fragment) {
        o oVar = fragment.mFragmentManager;
        if (oVar == null || oVar == this.t) {
            return super.p(fragment);
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.y
    public y s(Fragment fragment, androidx.lifecycle.d.c cVar) {
        if (fragment.mFragmentManager != this.t) {
            throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.t);
        }
        if (cVar == androidx.lifecycle.d.c.INITIALIZED && fragment.mState > -1) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + cVar + " after the Fragment has been created");
        }
        if (cVar != androidx.lifecycle.d.c.DESTROYED) {
            return super.s(fragment, cVar);
        }
        throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + cVar + ". Use remove() to remove the fragment from the FragmentManager and trigger its destruction.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.v >= 0) {
            sb.append(" #");
            sb.append(this.v);
        }
        if (this.k != null) {
            sb.append(" ");
            sb.append(this.k);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // androidx.fragment.app.y
    public y u(Fragment fragment) {
        o oVar = fragment.mFragmentManager;
        if (oVar == null || oVar == this.t) {
            return super.u(fragment);
        }
        throw new IllegalStateException("Cannot show Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    void v(int i) {
        if (this.i) {
            if (o.F0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Bump nesting in ");
                sb.append(this);
                sb.append(" by ");
                sb.append(i);
            }
            int size = this.c.size();
            for (int i2 = 0; i2 < size; i2++) {
                y.a aVar = (y.a) this.c.get(i2);
                Fragment fragment = aVar.b;
                if (fragment != null) {
                    fragment.mBackStackNesting += i;
                    if (o.F0(2)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Bump nesting of ");
                        sb2.append(aVar.b);
                        sb2.append(" to ");
                        sb2.append(aVar.b.mBackStackNesting);
                    }
                }
            }
        }
    }

    int w(boolean z) {
        if (this.u) {
            throw new IllegalStateException("commit already called");
        }
        if (o.F0(2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Commit: ");
            sb.append(this);
            PrintWriter printWriter = new PrintWriter(new i0("FragmentManager"));
            x("  ", printWriter);
            printWriter.close();
        }
        this.u = true;
        if (this.i) {
            this.v = this.t.j();
        } else {
            this.v = -1;
        }
        this.t.Y(this, z);
        return this.v;
    }

    public void x(String str, PrintWriter printWriter) {
        y(str, printWriter, true);
    }

    public void y(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.k);
            printWriter.print(" mIndex=");
            printWriter.print(this.v);
            printWriter.print(" mCommitted=");
            printWriter.println(this.u);
            if (this.h != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.h));
            }
            if (this.d != 0 || this.e != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.d));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.e));
            }
            if (this.f != 0 || this.g != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.g));
            }
            if (this.l != 0 || this.m != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.l));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.m);
            }
            if (this.n != 0 || this.o != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.n));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.o);
            }
        }
        if (this.c.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            y.a aVar = (y.a) this.c.get(i);
            switch (aVar.a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + aVar.a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.b);
            if (z) {
                if (aVar.c != 0 || aVar.d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.d));
                }
                if (aVar.e != 0 || aVar.f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f));
                }
            }
        }
    }

    void z() {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            y.a aVar = (y.a) this.c.get(i);
            Fragment fragment = aVar.b;
            if (fragment != null) {
                fragment.setPopDirection(false);
                fragment.setNextTransition(this.h);
                fragment.setSharedElementNames(this.p, this.q);
            }
            switch (aVar.a) {
                case 1:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.m1(fragment, false);
                    this.t.g(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.a);
                case 3:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.d1(fragment);
                    break;
                case 4:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.C0(fragment);
                    break;
                case 5:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.m1(fragment, false);
                    this.t.q1(fragment);
                    break;
                case 6:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.x(fragment);
                    break;
                case 7:
                    fragment.setAnimations(aVar.c, aVar.d, aVar.e, aVar.f);
                    this.t.m1(fragment, false);
                    this.t.l(fragment);
                    break;
                case 8:
                    this.t.o1(fragment);
                    break;
                case 9:
                    this.t.o1(null);
                    break;
                case 10:
                    this.t.n1(fragment, aVar.h);
                    break;
            }
            if (!this.r && aVar.a != 1 && fragment != null && !o.P) {
                this.t.P0(fragment);
            }
        }
        if (this.r || o.P) {
            return;
        }
        o oVar = this.t;
        oVar.Q0(oVar.q, true);
    }
}
