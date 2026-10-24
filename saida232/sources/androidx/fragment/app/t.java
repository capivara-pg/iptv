package androidx.fragment.app;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class t extends androidx.viewpager.widget.a {
    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;

    @Deprecated
    public static final int BEHAVIOR_SET_USER_VISIBLE_HINT = 0;
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentPagerAdapter";
    private final int mBehavior;
    private y mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private boolean mExecutingFinishUpdate;
    private final o mFragmentManager;

    public t(o oVar) {
        this(oVar, 0);
    }

    private static String a(int i, long j) {
        return "android:switcher:" + i + ":" + j;
    }

    @Override // androidx.viewpager.widget.a
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.m();
        }
        this.mCurTransaction.l(fragment);
        if (fragment.equals(this.mCurrentPrimaryItem)) {
            this.mCurrentPrimaryItem = null;
        }
    }

    @Override // androidx.viewpager.widget.a
    public void finishUpdate(ViewGroup viewGroup) {
        y yVar = this.mCurTransaction;
        if (yVar != null) {
            if (!this.mExecutingFinishUpdate) {
                try {
                    this.mExecutingFinishUpdate = true;
                    yVar.k();
                    this.mExecutingFinishUpdate = DEBUG;
                } catch (Throwable th) {
                    this.mExecutingFinishUpdate = DEBUG;
                    throw th;
                }
            }
            this.mCurTransaction = null;
        }
    }

    public abstract Fragment getItem(int i);

    public long getItemId(int i) {
        return i;
    }

    @Override // androidx.viewpager.widget.a
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.m();
        }
        long itemId = getItemId(i);
        Fragment fragmentH0 = this.mFragmentManager.h0(a(viewGroup.getId(), itemId));
        if (fragmentH0 != null) {
            this.mCurTransaction.g(fragmentH0);
        } else {
            fragmentH0 = getItem(i);
            this.mCurTransaction.c(viewGroup.getId(), fragmentH0, a(viewGroup.getId(), itemId));
        }
        if (fragmentH0 != this.mCurrentPrimaryItem) {
            fragmentH0.setMenuVisibility(DEBUG);
            if (this.mBehavior == 1) {
                this.mCurTransaction.s(fragmentH0, androidx.lifecycle.d.c.STARTED);
            } else {
                fragmentH0.setUserVisibleHint(DEBUG);
            }
        }
        return fragmentH0;
    }

    @Override // androidx.viewpager.widget.a
    public boolean isViewFromObject(View view, Object obj) {
        if (((Fragment) obj).getView() == view) {
            return true;
        }
        return DEBUG;
    }

    @Override // androidx.viewpager.widget.a
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    @Override // androidx.viewpager.widget.a
    public Parcelable saveState() {
        return null;
    }

    @Override // androidx.viewpager.widget.a
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.mCurrentPrimaryItem;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(DEBUG);
                if (this.mBehavior == 1) {
                    if (this.mCurTransaction == null) {
                        this.mCurTransaction = this.mFragmentManager.m();
                    }
                    this.mCurTransaction.s(this.mCurrentPrimaryItem, androidx.lifecycle.d.c.STARTED);
                } else {
                    this.mCurrentPrimaryItem.setUserVisibleHint(DEBUG);
                }
            }
            fragment.setMenuVisibility(true);
            if (this.mBehavior == 1) {
                if (this.mCurTransaction == null) {
                    this.mCurTransaction = this.mFragmentManager.m();
                }
                this.mCurTransaction.s(fragment, androidx.lifecycle.d.c.RESUMED);
            } else {
                fragment.setUserVisibleHint(true);
            }
            this.mCurrentPrimaryItem = fragment;
        }
    }

    @Override // androidx.viewpager.widget.a
    public void startUpdate(ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }

    public t(o oVar, int i) {
        this.mCurTransaction = null;
        this.mCurrentPrimaryItem = null;
        this.mFragmentManager = oVar;
        this.mBehavior = i;
    }
}
