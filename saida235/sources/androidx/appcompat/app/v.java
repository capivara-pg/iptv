package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.c1;
import androidx.core.view.d1;
import androidx.core.view.e1;
import androidx.core.view.f1;
import androidx.core.view.m0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class v extends androidx.appcompat.app.a implements ActionBarOverlayLayout.d {
    private static final Interpolator D = new AccelerateInterpolator();
    private static final Interpolator E = new DecelerateInterpolator();
    Context a;
    private Context b;
    private Activity c;
    ActionBarOverlayLayout d;
    ActionBarContainer e;
    c1 f;
    ActionBarContextView g;
    View h;
    private boolean k;
    d l;
    h.b m;
    h.b.a n;
    private boolean o;
    private boolean q;
    boolean t;
    boolean u;
    private boolean v;
    h.h x;
    private boolean y;
    boolean z;
    private ArrayList i = new ArrayList();
    private int j = -1;
    private ArrayList p = new ArrayList();
    private int r = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    boolean f5s = true;
    private boolean w = true;
    final d1 A = new a();
    final d1 B = new b();
    final f1 C = new c();

    class a extends e1 {
        a() {
        }

        @Override // androidx.core.view.d1
        public void b(View view) {
            View view2;
            v vVar = v.this;
            if (vVar.f5s && (view2 = vVar.h) != null) {
                view2.setTranslationY(0.0f);
                v.this.e.setTranslationY(0.0f);
            }
            v.this.e.setVisibility(8);
            v.this.e.setTransitioning(false);
            v vVar2 = v.this;
            vVar2.x = null;
            vVar2.B();
            ActionBarOverlayLayout actionBarOverlayLayout = v.this.d;
            if (actionBarOverlayLayout != null) {
                m0.i0(actionBarOverlayLayout);
            }
        }
    }

    class b extends e1 {
        b() {
        }

        @Override // androidx.core.view.d1
        public void b(View view) {
            v vVar = v.this;
            vVar.x = null;
            vVar.e.requestLayout();
        }
    }

    class c implements f1 {
        c() {
        }

        @Override // androidx.core.view.f1
        public void a(View view) {
            ((View) v.this.e.getParent()).invalidate();
        }
    }

    public class d extends h.b implements androidx.appcompat.view.menu.g.a {
        private final Context c;
        private final androidx.appcompat.view.menu.g d;
        private h.b.a e;
        private WeakReference f;

        public d(Context context, h.b.a aVar) {
            this.c = context;
            this.e = aVar;
            androidx.appcompat.view.menu.g defaultShowAsAction = new androidx.appcompat.view.menu.g(context).setDefaultShowAsAction(1);
            this.d = defaultShowAsAction;
            defaultShowAsAction.setCallback(this);
        }

        public void a() {
            v vVar = v.this;
            if (vVar.l != this) {
                return;
            }
            if (v.A(vVar.t, vVar.u, false)) {
                this.e.d(this);
            } else {
                v vVar2 = v.this;
                vVar2.m = this;
                vVar2.n = this.e;
            }
            this.e = null;
            v.this.z(false);
            v.this.g.g();
            v.this.f.n().sendAccessibilityEvent(32);
            v vVar3 = v.this;
            vVar3.d.setHideOnContentScrollEnabled(vVar3.z);
            v.this.l = null;
        }

        public View b() {
            WeakReference weakReference = this.f;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }

        public Menu c() {
            return this.d;
        }

        public MenuInflater d() {
            return new h.g(this.c);
        }

        public CharSequence e() {
            return v.this.g.getSubtitle();
        }

        public CharSequence g() {
            return v.this.g.getTitle();
        }

        public void i() {
            if (v.this.l != this) {
                return;
            }
            this.d.stopDispatchingItemsChanged();
            try {
                this.e.b(this, this.d);
            } finally {
                this.d.startDispatchingItemsChanged();
            }
        }

        public boolean j() {
            return v.this.g.j();
        }

        public void k(View view) {
            v.this.g.setCustomView(view);
            this.f = new WeakReference(view);
        }

        public void l(int i) {
            m(v.this.a.getResources().getString(i));
        }

        public void m(CharSequence charSequence) {
            v.this.g.setSubtitle(charSequence);
        }

        public void o(int i) {
            p(v.this.a.getResources().getString(i));
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean onMenuItemSelected(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            h.b.a aVar = this.e;
            if (aVar != null) {
                return aVar.c(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void onMenuModeChange(androidx.appcompat.view.menu.g gVar) {
            if (this.e == null) {
                return;
            }
            i();
            v.this.g.l();
        }

        public void p(CharSequence charSequence) {
            v.this.g.setTitle(charSequence);
        }

        public void q(boolean z) {
            super.q(z);
            v.this.g.setTitleOptional(z);
        }

        public boolean r() {
            this.d.stopDispatchingItemsChanged();
            try {
                return this.e.a(this, this.d);
            } finally {
                this.d.startDispatchingItemsChanged();
            }
        }
    }

    public v(Activity activity, boolean z) {
        this.c = activity;
        View decorView = activity.getWindow().getDecorView();
        H(decorView);
        if (z) {
            return;
        }
        this.h = decorView.findViewById(R.id.content);
    }

    static boolean A(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private c1 E(View view) {
        if (view instanceof c1) {
            return (c1) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != 0 ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    private void G() {
        if (this.v) {
            this.v = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.d;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            P(false);
        }
    }

    private void H(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R$id.decor_content_parent);
        this.d = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f = E(view.findViewById(R$id.action_bar));
        this.g = (ActionBarContextView) view.findViewById(R$id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R$id.action_bar_container);
        this.e = actionBarContainer;
        c1 c1Var = this.f;
        if (c1Var == null || this.g == null || actionBarContainer == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.a = c1Var.getContext();
        boolean z = (this.f.x() & 4) != 0;
        if (z) {
            this.k = true;
        }
        h.a aVarB = h.a.b(this.a);
        M(aVarB.a() || z);
        K(aVarB.e());
        TypedArray typedArrayObtainStyledAttributes = this.a.obtainStyledAttributes(null, R$styleable.a, R$attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false)) {
            L(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            J(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void K(boolean z) {
        this.q = z;
        if (z) {
            this.e.setTabContainer(null);
            this.f.s(null);
        } else {
            this.f.s(null);
            this.e.setTabContainer(null);
        }
        boolean z2 = F() == 2;
        this.f.q(!this.q && z2);
        this.d.setHasNonEmbeddedTabs(!this.q && z2);
    }

    private boolean N() {
        return m0.R(this.e);
    }

    private void O() {
        if (this.v) {
            return;
        }
        this.v = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.d;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        P(false);
    }

    private void P(boolean z) {
        if (A(this.t, this.u, this.v)) {
            if (this.w) {
                return;
            }
            this.w = true;
            D(z);
            return;
        }
        if (this.w) {
            this.w = false;
            C(z);
        }
    }

    void B() {
        h.b.a aVar = this.n;
        if (aVar != null) {
            aVar.d(this.m);
            this.m = null;
            this.n = null;
        }
    }

    public void C(boolean z) {
        View view;
        h.h hVar = this.x;
        if (hVar != null) {
            hVar.a();
        }
        if (this.r != 0 || (!this.y && !z)) {
            this.A.b(null);
            return;
        }
        this.e.setAlpha(1.0f);
        this.e.setTransitioning(true);
        h.h hVar2 = new h.h();
        float f = -this.e.getHeight();
        if (z) {
            int[] iArr = {0, 0};
            this.e.getLocationInWindow(iArr);
            f -= iArr[1];
        }
        androidx.core.view.c1 c1VarK = m0.c(this.e).k(f);
        c1VarK.i(this.C);
        hVar2.c(c1VarK);
        if (this.f5s && (view = this.h) != null) {
            hVar2.c(m0.c(view).k(f));
        }
        hVar2.f(D);
        hVar2.e(250L);
        hVar2.g(this.A);
        this.x = hVar2;
        hVar2.h();
    }

    public void D(boolean z) {
        View view;
        View view2;
        h.h hVar = this.x;
        if (hVar != null) {
            hVar.a();
        }
        this.e.setVisibility(0);
        if (this.r == 0 && (this.y || z)) {
            this.e.setTranslationY(0.0f);
            float f = -this.e.getHeight();
            if (z) {
                int[] iArr = {0, 0};
                this.e.getLocationInWindow(iArr);
                f -= iArr[1];
            }
            this.e.setTranslationY(f);
            h.h hVar2 = new h.h();
            androidx.core.view.c1 c1VarK = m0.c(this.e).k(0.0f);
            c1VarK.i(this.C);
            hVar2.c(c1VarK);
            if (this.f5s && (view2 = this.h) != null) {
                view2.setTranslationY(f);
                hVar2.c(m0.c(this.h).k(0.0f));
            }
            hVar2.f(E);
            hVar2.e(250L);
            hVar2.g(this.B);
            this.x = hVar2;
            hVar2.h();
        } else {
            this.e.setAlpha(1.0f);
            this.e.setTranslationY(0.0f);
            if (this.f5s && (view = this.h) != null) {
                view.setTranslationY(0.0f);
            }
            this.B.b(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.d;
        if (actionBarOverlayLayout != null) {
            m0.i0(actionBarOverlayLayout);
        }
    }

    public int F() {
        return this.f.l();
    }

    public void I(int i, int i2) {
        int iX = this.f.x();
        if ((i2 & 4) != 0) {
            this.k = true;
        }
        this.f.i((i & i2) | ((~i2) & iX));
    }

    public void J(float f) {
        m0.s0(this.e, f);
    }

    public void L(boolean z) {
        if (z && !this.d.q()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.z = z;
        this.d.setHideOnContentScrollEnabled(z);
    }

    public void M(boolean z) {
        this.f.o(z);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a() {
        if (this.u) {
            this.u = false;
            P(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b(int i) {
        this.r = i;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c() {
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d(boolean z) {
        this.f5s = z;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void e() {
        if (this.u) {
            return;
        }
        this.u = true;
        P(true);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void f() {
        h.h hVar = this.x;
        if (hVar != null) {
            hVar.a();
            this.x = null;
        }
    }

    @Override // androidx.appcompat.app.a
    public boolean h() {
        c1 c1Var = this.f;
        if (c1Var == null || !c1Var.h()) {
            return false;
        }
        this.f.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.a
    public void i(boolean z) {
        if (z == this.o) {
            return;
        }
        this.o = z;
        if (this.p.size() <= 0) {
            return;
        }
        r.a(this.p.get(0));
        throw null;
    }

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.f.x();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        if (this.b == null) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.b = new ContextThemeWrapper(this.a, i);
            } else {
                this.b = this.a;
            }
        }
        return this.b;
    }

    @Override // androidx.appcompat.app.a
    public void m(Configuration configuration) {
        K(h.a.b(this.a).e());
    }

    @Override // androidx.appcompat.app.a
    public boolean o(int i, KeyEvent keyEvent) {
        Menu menuC;
        d dVar = this.l;
        if (dVar == null || (menuC = dVar.c()) == null) {
            return false;
        }
        menuC.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuC.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.a
    public void r(boolean z) {
        if (this.k) {
            return;
        }
        s(z);
    }

    @Override // androidx.appcompat.app.a
    public void s(boolean z) {
        I(z ? 4 : 0, 4);
    }

    @Override // androidx.appcompat.app.a
    public void t(int i) {
        this.f.u(i);
    }

    @Override // androidx.appcompat.app.a
    public void u(boolean z) {
        h.h hVar;
        this.y = z;
        if (z || (hVar = this.x) == null) {
            return;
        }
        hVar.a();
    }

    @Override // androidx.appcompat.app.a
    public void v(CharSequence charSequence) {
        this.f.j(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void w(CharSequence charSequence) {
        this.f.setTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void x(CharSequence charSequence) {
        this.f.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public h.b y(h.b.a aVar) {
        d dVar = this.l;
        if (dVar != null) {
            dVar.a();
        }
        this.d.setHideOnContentScrollEnabled(false);
        this.g.k();
        d dVar2 = new d(this.g.getContext(), aVar);
        if (!dVar2.r()) {
            return null;
        }
        this.l = dVar2;
        dVar2.i();
        this.g.h(dVar2);
        z(true);
        this.g.sendAccessibilityEvent(32);
        return dVar2;
    }

    public void z(boolean z) {
        androidx.core.view.c1 c1VarM;
        androidx.core.view.c1 c1VarF;
        if (z) {
            O();
        } else {
            G();
        }
        if (!N()) {
            if (z) {
                this.f.w(4);
                this.g.setVisibility(0);
                return;
            } else {
                this.f.w(0);
                this.g.setVisibility(8);
                return;
            }
        }
        if (z) {
            c1VarF = this.f.m(4, 100L);
            c1VarM = this.g.f(0, 200L);
        } else {
            c1VarM = this.f.m(0, 200L);
            c1VarF = this.g.f(8, 100L);
        }
        h.h hVar = new h.h();
        hVar.d(c1VarF, c1VarM);
        hVar.h();
    }

    public v(Dialog dialog) {
        H(dialog.getWindow().getDecorView());
    }
}
