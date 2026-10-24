package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class v extends androidx.viewpager.widget.a {
    private final n a;
    private final int b;
    private x c;
    private ArrayList d;
    private ArrayList e;
    private Fragment f;
    private boolean g;

    public v(n nVar) {
        this(nVar, 0);
    }

    @Override // androidx.viewpager.widget.a
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.c == null) {
            this.c = this.a.m();
        }
        while (this.d.size() <= i) {
            this.d.add(null);
        }
        this.d.set(i, fragment.isAdded() ? this.a.l1(fragment) : null);
        this.e.set(i, null);
        this.c.q(fragment);
        if (fragment.equals(this.f)) {
            this.f = null;
        }
    }

    @Override // androidx.viewpager.widget.a
    public void finishUpdate(ViewGroup viewGroup) {
        x xVar = this.c;
        if (xVar != null) {
            if (!this.g) {
                try {
                    this.g = true;
                    xVar.k();
                    this.g = false;
                } catch (Throwable th) {
                    this.g = false;
                    throw th;
                }
            }
            this.c = null;
        }
    }

    public abstract Fragment getItem(int i);

    @Override // androidx.viewpager.widget.a
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment.m mVar;
        Fragment fragment;
        if (this.e.size() > i && (fragment = (Fragment) this.e.get(i)) != null) {
            return fragment;
        }
        if (this.c == null) {
            this.c = this.a.m();
        }
        Fragment item = getItem(i);
        if (this.d.size() > i && (mVar = (Fragment.m) this.d.get(i)) != null) {
            item.setInitialSavedState(mVar);
        }
        while (this.e.size() <= i) {
            this.e.add(null);
        }
        item.setMenuVisibility(false);
        if (this.b == 0) {
            item.setUserVisibleHint(false);
        }
        this.e.set(i, item);
        this.c.b(viewGroup.getId(), item);
        if (this.b == 1) {
            this.c.t(item, androidx.lifecycle.f.b.STARTED);
        }
        return item;
    }

    @Override // androidx.viewpager.widget.a
    public boolean isViewFromObject(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    @Override // androidx.viewpager.widget.a
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Parcelable[] parcelableArray = bundle.getParcelableArray("states");
            this.d.clear();
            this.e.clear();
            if (parcelableArray != null) {
                for (Parcelable parcelable2 : parcelableArray) {
                    this.d.add((Fragment.m) parcelable2);
                }
            }
            for (String str : bundle.keySet()) {
                if (str.startsWith("f")) {
                    int i = Integer.parseInt(str.substring(1));
                    Fragment fragmentO0 = this.a.o0(bundle, str);
                    if (fragmentO0 != null) {
                        while (this.e.size() <= i) {
                            this.e.add(null);
                        }
                        fragmentO0.setMenuVisibility(false);
                        this.e.set(i, fragmentO0);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Bad fragment at key ");
                        sb.append(str);
                    }
                }
            }
        }
    }

    @Override // androidx.viewpager.widget.a
    public Parcelable saveState() {
        Bundle bundle;
        if (this.d.size() > 0) {
            bundle = new Bundle();
            Fragment.m[] mVarArr = new Fragment.m[this.d.size()];
            this.d.toArray(mVarArr);
            bundle.putParcelableArray("states", mVarArr);
        } else {
            bundle = null;
        }
        for (int i = 0; i < this.e.size(); i++) {
            Fragment fragment = (Fragment) this.e.get(i);
            if (fragment != null && fragment.isAdded()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                this.a.b1(bundle, "f" + i, fragment);
            }
        }
        return bundle;
    }

    @Override // androidx.viewpager.widget.a
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.f;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(false);
                if (this.b == 1) {
                    if (this.c == null) {
                        this.c = this.a.m();
                    }
                    this.c.t(this.f, androidx.lifecycle.f.b.STARTED);
                } else {
                    this.f.setUserVisibleHint(false);
                }
            }
            fragment.setMenuVisibility(true);
            if (this.b == 1) {
                if (this.c == null) {
                    this.c = this.a.m();
                }
                this.c.t(fragment, androidx.lifecycle.f.b.RESUMED);
            } else {
                fragment.setUserVisibleHint(true);
            }
            this.f = fragment;
        }
    }

    @Override // androidx.viewpager.widget.a
    public void startUpdate(ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }

    public v(n nVar, int i) {
        this.c = null;
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = null;
        this.a = nVar;
        this.b = i;
    }
}
