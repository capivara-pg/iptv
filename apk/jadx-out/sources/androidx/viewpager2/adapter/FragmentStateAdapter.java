package androidx.viewpager2.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.core.view.m0;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.e;
import androidx.fragment.app.n;
import androidx.fragment.app.x;
import androidx.lifecycle.f;
import androidx.lifecycle.i;
import androidx.lifecycle.m;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import c0.h;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class FragmentStateAdapter extends RecyclerView.Adapter implements androidx.viewpager2.adapter.b {
    final f a;
    final n b;
    final androidx.collection.d c;
    private final androidx.collection.d d;
    private final androidx.collection.d e;
    private FragmentMaxLifecycleEnforcer f;
    boolean g;
    private boolean h;

    class FragmentMaxLifecycleEnforcer {
        private ViewPager2.OnPageChangeCallback a;
        private RecyclerView.AdapterDataObserver b;
        private i c;
        private ViewPager2 d;
        private long e = -1;

        class a extends ViewPager2.OnPageChangeCallback {
            a() {
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i) {
                FragmentMaxLifecycleEnforcer.this.d(false);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                FragmentMaxLifecycleEnforcer.this.d(false);
            }
        }

        class b extends d {
            b() {
                super(null);
            }

            @Override // androidx.viewpager2.adapter.FragmentStateAdapter.d, androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                FragmentMaxLifecycleEnforcer.this.d(true);
            }
        }

        FragmentMaxLifecycleEnforcer() {
        }

        private ViewPager2 a(RecyclerView recyclerView) {
            ViewParent parent = recyclerView.getParent();
            if (parent instanceof ViewPager2) {
                return (ViewPager2) parent;
            }
            throw new IllegalStateException("Expected ViewPager2 instance. Got: " + parent);
        }

        void b(RecyclerView recyclerView) {
            this.d = a(recyclerView);
            a aVar = new a();
            this.a = aVar;
            this.d.registerOnPageChangeCallback(aVar);
            b bVar = new b();
            this.b = bVar;
            FragmentStateAdapter.this.registerAdapterDataObserver(bVar);
            i iVar = new i() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.3
                @Override // androidx.lifecycle.i
                public void a(m mVar, f.a aVar2) {
                    FragmentMaxLifecycleEnforcer.this.d(false);
                }
            };
            this.c = iVar;
            FragmentStateAdapter.this.a.a(iVar);
        }

        void c(RecyclerView recyclerView) {
            a(recyclerView).unregisterOnPageChangeCallback(this.a);
            FragmentStateAdapter.this.unregisterAdapterDataObserver(this.b);
            FragmentStateAdapter.this.a.d(this.c);
            this.d = null;
        }

        void d(boolean z) {
            int currentItem;
            Fragment fragment;
            if (FragmentStateAdapter.this.v() || this.d.getScrollState() != 0 || FragmentStateAdapter.this.c.i() || FragmentStateAdapter.this.getItemCount() == 0 || (currentItem = this.d.getCurrentItem()) >= FragmentStateAdapter.this.getItemCount()) {
                return;
            }
            long itemId = FragmentStateAdapter.this.getItemId(currentItem);
            if ((itemId != this.e || z) && (fragment = (Fragment) FragmentStateAdapter.this.c.f(itemId)) != null && fragment.isAdded()) {
                this.e = itemId;
                x xVarM = FragmentStateAdapter.this.b.m();
                Fragment fragment2 = null;
                for (int i = 0; i < FragmentStateAdapter.this.c.n(); i++) {
                    long j = FragmentStateAdapter.this.c.j(i);
                    Fragment fragment3 = (Fragment) FragmentStateAdapter.this.c.o(i);
                    if (fragment3.isAdded()) {
                        if (j != this.e) {
                            xVarM.t(fragment3, f.b.STARTED);
                        } else {
                            fragment2 = fragment3;
                        }
                        fragment3.setMenuVisibility(j == this.e);
                    }
                }
                if (fragment2 != null) {
                    xVarM.t(fragment2, f.b.RESUMED);
                }
                if (xVarM.p()) {
                    return;
                }
                xVarM.j();
            }
        }
    }

    class a implements View.OnLayoutChangeListener {
        final /* synthetic */ FrameLayout a;
        final /* synthetic */ androidx.viewpager2.adapter.a b;

        a(FrameLayout frameLayout, androidx.viewpager2.adapter.a aVar) {
            this.a = frameLayout;
            this.b = aVar;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.a.getParent() != null) {
                this.a.removeOnLayoutChangeListener(this);
                FragmentStateAdapter.this.r(this.b);
            }
        }
    }

    class b extends n.l {
        final /* synthetic */ Fragment a;
        final /* synthetic */ FrameLayout b;

        b(Fragment fragment, FrameLayout frameLayout) {
            this.a = fragment;
            this.b = frameLayout;
        }

        @Override // androidx.fragment.app.n.l
        public void onFragmentViewCreated(n nVar, Fragment fragment, View view, Bundle bundle) {
            if (fragment == this.a) {
                nVar.u1(this);
                FragmentStateAdapter.this.c(view, this.b);
            }
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentStateAdapter fragmentStateAdapter = FragmentStateAdapter.this;
            fragmentStateAdapter.g = false;
            fragmentStateAdapter.h();
        }
    }

    private static abstract class d extends RecyclerView.AdapterDataObserver {
        private d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public abstract void onChanged();

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeInserted(int i, int i2) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeMoved(int i, int i2, int i3) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeRemoved(int i, int i2) {
            onChanged();
        }

        /* synthetic */ d(a aVar) {
            this();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2, Object obj) {
            onChanged();
        }
    }

    public FragmentStateAdapter(e eVar) {
        this(eVar.getSupportFragmentManager(), eVar.getLifecycle());
    }

    private static String f(String str, long j) {
        return str + j;
    }

    private void g(int i) {
        long itemId = getItemId(i);
        if (this.c.d(itemId)) {
            return;
        }
        Fragment fragmentE = e(i);
        fragmentE.setInitialSavedState((Fragment.m) this.d.f(itemId));
        this.c.k(itemId, fragmentE);
    }

    private boolean i(long j) {
        View view;
        if (this.e.d(j)) {
            return true;
        }
        Fragment fragment = (Fragment) this.c.f(j);
        return (fragment == null || (view = fragment.getView()) == null || view.getParent() == null) ? false : true;
    }

    private static boolean j(String str, String str2) {
        return str.startsWith(str2) && str.length() > str2.length();
    }

    private Long k(int i) {
        Long lValueOf = null;
        for (int i2 = 0; i2 < this.e.n(); i2++) {
            if (((Integer) this.e.o(i2)).intValue() == i) {
                if (lValueOf != null) {
                    throw new IllegalStateException("Design assumption violated: a ViewHolder can only be bound to one item at a time.");
                }
                lValueOf = Long.valueOf(this.e.j(i2));
            }
        }
        return lValueOf;
    }

    private static long q(String str, String str2) {
        return Long.parseLong(str.substring(str2.length()));
    }

    private void s(long j) {
        ViewParent parent;
        Fragment fragment = (Fragment) this.c.f(j);
        if (fragment == null) {
            return;
        }
        if (fragment.getView() != null && (parent = fragment.getView().getParent()) != null) {
            ((FrameLayout) parent).removeAllViews();
        }
        if (!d(j)) {
            this.d.l(j);
        }
        if (!fragment.isAdded()) {
            this.c.l(j);
            return;
        }
        if (v()) {
            this.h = true;
            return;
        }
        if (fragment.isAdded() && d(j)) {
            this.d.k(j, this.b.l1(fragment));
        }
        this.b.m().q(fragment).j();
        this.c.l(j);
    }

    private void t() {
        final Handler handler = new Handler(Looper.getMainLooper());
        final c cVar = new c();
        this.a.a(new i() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.5
            @Override // androidx.lifecycle.i
            public void a(m mVar, f.a aVar) {
                if (aVar == f.a.ON_DESTROY) {
                    handler.removeCallbacks(cVar);
                    mVar.getLifecycle().d(this);
                }
            }
        });
        handler.postDelayed(cVar, 10000L);
    }

    private void u(Fragment fragment, FrameLayout frameLayout) {
        this.b.c1(new b(fragment, frameLayout), false);
    }

    @Override // androidx.viewpager2.adapter.b
    public final Parcelable a() {
        Bundle bundle = new Bundle(this.c.n() + this.d.n());
        for (int i = 0; i < this.c.n(); i++) {
            long j = this.c.j(i);
            Fragment fragment = (Fragment) this.c.f(j);
            if (fragment != null && fragment.isAdded()) {
                this.b.b1(bundle, f("f#", j), fragment);
            }
        }
        for (int i2 = 0; i2 < this.d.n(); i2++) {
            long j2 = this.d.j(i2);
            if (d(j2)) {
                bundle.putParcelable(f("s#", j2), (Parcelable) this.d.f(j2));
            }
        }
        return bundle;
    }

    @Override // androidx.viewpager2.adapter.b
    public final void b(Parcelable parcelable) {
        if (!this.d.i() || !this.c.i()) {
            throw new IllegalStateException("Expected the adapter to be 'fresh' while restoring state.");
        }
        Bundle bundle = (Bundle) parcelable;
        if (bundle.getClassLoader() == null) {
            bundle.setClassLoader(getClass().getClassLoader());
        }
        for (String str : bundle.keySet()) {
            if (j(str, "f#")) {
                this.c.k(q(str, "f#"), this.b.o0(bundle, str));
            } else {
                if (!j(str, "s#")) {
                    throw new IllegalArgumentException("Unexpected key in savedState: " + str);
                }
                long jQ = q(str, "s#");
                Fragment.m mVar = (Fragment.m) bundle.getParcelable(str);
                if (d(jQ)) {
                    this.d.k(jQ, mVar);
                }
            }
        }
        if (this.c.i()) {
            return;
        }
        this.h = true;
        this.g = true;
        h();
        t();
    }

    void c(View view, FrameLayout frameLayout) {
        if (frameLayout.getChildCount() > 1) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (view.getParent() == frameLayout) {
            return;
        }
        if (frameLayout.getChildCount() > 0) {
            frameLayout.removeAllViews();
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        frameLayout.addView(view);
    }

    public boolean d(long j) {
        return j >= 0 && j < ((long) getItemCount());
    }

    public abstract Fragment e(int i);

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    void h() {
        if (!this.h || v()) {
            return;
        }
        androidx.collection.b bVar = new androidx.collection.b();
        for (int i = 0; i < this.c.n(); i++) {
            long j = this.c.j(i);
            if (!d(j)) {
                bVar.add(Long.valueOf(j));
                this.e.l(j);
            }
        }
        if (!this.g) {
            this.h = false;
            for (int i2 = 0; i2 < this.c.n(); i2++) {
                long j2 = this.c.j(i2);
                if (!i(j2)) {
                    bVar.add(Long.valueOf(j2));
                }
            }
        }
        Iterator it = bVar.iterator();
        while (it.hasNext()) {
            s(((Long) it.next()).longValue());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final void onBindViewHolder(androidx.viewpager2.adapter.a aVar, int i) {
        long itemId = aVar.getItemId();
        int id = aVar.b().getId();
        Long lK = k(id);
        if (lK != null && lK.longValue() != itemId) {
            s(lK.longValue());
            this.e.l(lK.longValue());
        }
        this.e.k(itemId, Integer.valueOf(id));
        g(i);
        FrameLayout frameLayoutB = aVar.b();
        if (m0.Q(frameLayoutB)) {
            if (frameLayoutB.getParent() != null) {
                throw new IllegalStateException("Design assumption violated.");
            }
            frameLayoutB.addOnLayoutChangeListener(new a(frameLayoutB, aVar));
        }
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final androidx.viewpager2.adapter.a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return androidx.viewpager2.adapter.a.a(viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public final boolean onFailedToRecycleView(androidx.viewpager2.adapter.a aVar) {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public final void onViewAttachedToWindow(androidx.viewpager2.adapter.a aVar) {
        r(aVar);
        h();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        h.a(this.f == null);
        FragmentMaxLifecycleEnforcer fragmentMaxLifecycleEnforcer = new FragmentMaxLifecycleEnforcer();
        this.f = fragmentMaxLifecycleEnforcer;
        fragmentMaxLifecycleEnforcer.b(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.f.c(recyclerView);
        this.f = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public final void onViewRecycled(androidx.viewpager2.adapter.a aVar) {
        Long lK = k(aVar.b().getId());
        if (lK != null) {
            s(lK.longValue());
            this.e.l(lK.longValue());
        }
    }

    void r(final androidx.viewpager2.adapter.a aVar) {
        Fragment fragment = (Fragment) this.c.f(aVar.getItemId());
        if (fragment == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        FrameLayout frameLayoutB = aVar.b();
        View view = fragment.getView();
        if (!fragment.isAdded() && view != null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (fragment.isAdded() && view == null) {
            u(fragment, frameLayoutB);
            return;
        }
        if (fragment.isAdded() && view.getParent() != null) {
            if (view.getParent() != frameLayoutB) {
                c(view, frameLayoutB);
                return;
            }
            return;
        }
        if (fragment.isAdded()) {
            c(view, frameLayoutB);
            return;
        }
        if (v()) {
            if (this.b.E0()) {
                return;
            }
            this.a.a(new i() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.2
                @Override // androidx.lifecycle.i
                public void a(m mVar, f.a aVar2) {
                    if (FragmentStateAdapter.this.v()) {
                        return;
                    }
                    mVar.getLifecycle().d(this);
                    if (m0.Q(aVar.b())) {
                        FragmentStateAdapter.this.r(aVar);
                    }
                }
            });
            return;
        }
        u(fragment, frameLayoutB);
        this.b.m().e(fragment, "f" + aVar.getItemId()).t(fragment, f.b.STARTED).j();
        this.f.d(false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void setHasStableIds(boolean z) {
        throw new UnsupportedOperationException("Stable Ids are required for the adapter to function properly, and the adapter takes care of setting the flag.");
    }

    boolean v() {
        return this.b.K0();
    }

    public FragmentStateAdapter(n nVar, f fVar) {
        this.c = new androidx.collection.d();
        this.d = new androidx.collection.d();
        this.e = new androidx.collection.d();
        this.g = false;
        this.h = false;
        this.b = nVar;
        this.a = fVar;
        super.setHasStableIds(true);
    }
}
