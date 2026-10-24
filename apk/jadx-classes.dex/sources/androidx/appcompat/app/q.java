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
import androidx.appcompat.widget.i1;
import androidx.core.view.a2;
import androidx.core.view.b2;
import androidx.core.view.c1;
import androidx.core.view.c2;
import androidx.core.view.d2;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class q extends androidx.appcompat.app.a implements ActionBarOverlayLayout.d {
    private static final Interpolator E = new AccelerateInterpolator();
    private static final Interpolator F = new DecelerateInterpolator();
    boolean A;
    Context a;
    private Context b;
    private Activity c;
    private Dialog d;
    ActionBarOverlayLayout e;
    ActionBarContainer f;
    i1 g;
    ActionBarContextView h;
    View i;
    private boolean l;
    d m;
    f.b n;
    f.b.a o;
    private boolean p;
    private boolean r;
    boolean u;
    boolean v;
    private boolean w;
    f.h y;
    private boolean z;
    private ArrayList j = new ArrayList();
    private int k = -1;
    private ArrayList q = new ArrayList();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f6s = 0;
    boolean t = true;
    private boolean x = true;
    final b2 B = new a();
    final b2 C = new b();
    final d2 D = new c();

    class a extends c2 {
        a() {
        }

        @Override // androidx.core.view.b2
        public void b(View view) {
            View view2;
            q qVar = q.this;
            if (qVar.t && (view2 = qVar.i) != null) {
                view2.setTranslationY(0.0f);
                q.this.f.setTranslationY(0.0f);
            }
            q.this.f.setVisibility(8);
            q.this.f.setTransitioning(false);
            q qVar2 = q.this;
            qVar2.y = null;
            qVar2.B();
            ActionBarOverlayLayout actionBarOverlayLayout = q.this.e;
            if (actionBarOverlayLayout != null) {
                c1.h0(actionBarOverlayLayout);
            }
        }
    }

    class b extends c2 {
        b() {
        }

        @Override // androidx.core.view.b2
        public void b(View view) {
            q qVar = q.this;
            qVar.y = null;
            qVar.f.requestLayout();
        }
    }

    class c implements d2 {
        c() {
        }

        @Override // androidx.core.view.d2
        public void a(View view) {
            ((View) q.this.f.getParent()).invalidate();
        }
    }

    public class d extends f.b implements androidx.appcompat.view.menu.g.a {
        private final Context c;
        private final androidx.appcompat.view.menu.g d;
        private f.b.a e;
        private WeakReference f;

        public d(Context context, f.b.a aVar) {
            this.c = context;
            this.e = aVar;
            androidx.appcompat.view.menu.g defaultShowAsAction = new androidx.appcompat.view.menu.g(context).setDefaultShowAsAction(1);
            this.d = defaultShowAsAction;
            defaultShowAsAction.setCallback(this);
        }

        public void a() {
            q qVar = q.this;
            if (qVar.m != this) {
                return;
            }
            if (q.A(qVar.u, qVar.v, false)) {
                this.e.c(this);
            } else {
                q qVar2 = q.this;
                qVar2.n = this;
                qVar2.o = this.e;
            }
            this.e = null;
            q.this.z(false);
            q.this.h.g();
            q.this.g.n().sendAccessibilityEvent(32);
            q qVar3 = q.this;
            qVar3.e.setHideOnContentScrollEnabled(qVar3.A);
            q.this.m = null;
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
            return new f.g(this.c);
        }

        public CharSequence e() {
            return q.this.h.getSubtitle();
        }

        public CharSequence g() {
            return q.this.h.getTitle();
        }

        public void i() {
            if (q.this.m != this) {
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
            return q.this.h.j();
        }

        public void k(View view) {
            q.this.h.setCustomView(view);
            this.f = new WeakReference(view);
        }

        public void l(int i) {
            m(q.this.a.getResources().getString(i));
        }

        public void m(CharSequence charSequence) {
            q.this.h.setSubtitle(charSequence);
        }

        public void o(int i) {
            p(q.this.a.getResources().getString(i));
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean onMenuItemSelected(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            f.b.a aVar = this.e;
            if (aVar != null) {
                return aVar.d(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void onMenuModeChange(androidx.appcompat.view.menu.g gVar) {
            if (this.e == null) {
                return;
            }
            i();
            q.this.h.l();
        }

        public void p(CharSequence charSequence) {
            q.this.h.setTitle(charSequence);
        }

        public void q(boolean z) {
            super.q(z);
            q.this.h.setTitleOptional(z);
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

    public q(Activity activity, boolean z) {
        this.c = activity;
        View decorView = activity.getWindow().getDecorView();
        H(decorView);
        if (z) {
            return;
        }
        this.i = decorView.findViewById(R.id.content);
    }

    static boolean A(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private i1 E(View view) {
        if (view instanceof i1) {
            return (i1) view;
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
        if (this.w) {
            this.w = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.e;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            P(false);
        }
    }

    private void H(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R$id.decor_content_parent);
        this.e = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.g = E(view.findViewById(R$id.action_bar));
        this.h = (ActionBarContextView) view.findViewById(R$id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R$id.action_bar_container);
        this.f = actionBarContainer;
        i1 i1Var = this.g;
        if (i1Var == null || this.h == null || actionBarContainer == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.a = i1Var.getContext();
        boolean z = (this.g.x() & 4) != 0;
        if (z) {
            this.l = true;
        }
        f.a aVarB = f.a.b(this.a);
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
        this.r = z;
        if (z) {
            this.f.setTabContainer(null);
            this.g.s(null);
        } else {
            this.g.s(null);
            this.f.setTabContainer(null);
        }
        boolean z2 = F() == 2;
        this.g.q(!this.r && z2);
        this.e.setHasNonEmbeddedTabs(!this.r && z2);
    }

    private boolean N() {
        return c1.Q(this.f);
    }

    private void O() {
        if (this.w) {
            return;
        }
        this.w = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.e;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        P(false);
    }

    private void P(boolean z) {
        if (A(this.u, this.v, this.w)) {
            if (this.x) {
                return;
            }
            this.x = true;
            D(z);
            return;
        }
        if (this.x) {
            this.x = false;
            C(z);
        }
    }

    void B() {
        f.b.a aVar = this.o;
        if (aVar != null) {
            aVar.c(this.n);
            this.n = null;
            this.o = null;
        }
    }

    public void C(boolean z) {
        View view;
        f.h hVar = this.y;
        if (hVar != null) {
            hVar.a();
        }
        if (this.f6s != 0 || (!this.z && !z)) {
            this.B.b(null);
            return;
        }
        this.f.setAlpha(1.0f);
        this.f.setTransitioning(true);
        f.h hVar2 = new f.h();
        float f = -this.f.getHeight();
        if (z) {
            int[] iArr = {0, 0};
            this.f.getLocationInWindow(iArr);
            f -= iArr[1];
        }
        a2 a2VarK = c1.c(this.f).k(f);
        a2VarK.i(this.D);
        hVar2.c(a2VarK);
        if (this.t && (view = this.i) != null) {
            hVar2.c(c1.c(view).k(f));
        }
        hVar2.f(E);
        hVar2.e(250L);
        hVar2.g(this.B);
        this.y = hVar2;
        hVar2.h();
    }

    public void D(boolean z) {
        View view;
        View view2;
        f.h hVar = this.y;
        if (hVar != null) {
            hVar.a();
        }
        this.f.setVisibility(0);
        if (this.f6s == 0 && (this.z || z)) {
            this.f.setTranslationY(0.0f);
            float f = -this.f.getHeight();
            if (z) {
                int[] iArr = {0, 0};
                this.f.getLocationInWindow(iArr);
                f -= iArr[1];
            }
            this.f.setTranslationY(f);
            f.h hVar2 = new f.h();
            a2 a2VarK = c1.c(this.f).k(0.0f);
            a2VarK.i(this.D);
            hVar2.c(a2VarK);
            if (this.t && (view2 = this.i) != null) {
                view2.setTranslationY(f);
                hVar2.c(c1.c(this.i).k(0.0f));
            }
            hVar2.f(F);
            hVar2.e(250L);
            hVar2.g(this.C);
            this.y = hVar2;
            hVar2.h();
        } else {
            this.f.setAlpha(1.0f);
            this.f.setTranslationY(0.0f);
            if (this.t && (view = this.i) != null) {
                view.setTranslationY(0.0f);
            }
            this.C.b(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.e;
        if (actionBarOverlayLayout != null) {
            c1.h0(actionBarOverlayLayout);
        }
    }

    public int F() {
        return this.g.l();
    }

    public void I(int i, int i2) {
        int iX = this.g.x();
        if ((i2 & 4) != 0) {
            this.l = true;
        }
        this.g.i((i & i2) | ((i2 ^ (-1)) & iX));
    }

    public void J(float f) {
        c1.r0(this.f, f);
    }

    public void L(boolean z) {
        if (z && !this.e.q()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.A = z;
        this.e.setHideOnContentScrollEnabled(z);
    }

    public void M(boolean z) {
        this.g.o(z);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a() {
        if (this.v) {
            this.v = false;
            P(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b(int i) {
        this.f6s = i;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c() {
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d(boolean z) {
        this.t = z;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void e() {
        if (this.v) {
            return;
        }
        this.v = true;
        P(true);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void f() {
        f.h hVar = this.y;
        if (hVar != null) {
            hVar.a();
            this.y = null;
        }
    }

    @Override // androidx.appcompat.app.a
    public boolean h() {
        i1 i1Var = this.g;
        if (i1Var == null || !i1Var.h()) {
            return false;
        }
        this.g.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.a
    public void i(boolean z) {
        if (z == this.p) {
            return;
        }
        this.p = z;
        if (this.q.size() <= 0) {
            return;
        }
        m.a(this.q.get(0));
        throw null;
    }

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.g.x();
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
        K(f.a.b(this.a).e());
    }

    @Override // androidx.appcompat.app.a
    public boolean o(int i, KeyEvent keyEvent) {
        Menu menuC;
        d dVar = this.m;
        if (dVar == null || (menuC = dVar.c()) == null) {
            return false;
        }
        menuC.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuC.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.a
    public void r(boolean z) {
        if (this.l) {
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
        this.g.u(i);
    }

    @Override // androidx.appcompat.app.a
    public void u(boolean z) {
        f.h hVar;
        this.z = z;
        if (z || (hVar = this.y) == null) {
            return;
        }
        hVar.a();
    }

    @Override // androidx.appcompat.app.a
    public void v(CharSequence charSequence) {
        this.g.j(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void w(CharSequence charSequence) {
        this.g.setTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void x(CharSequence charSequence) {
        this.g.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public f.b y(f.b.a aVar) {
        d dVar = this.m;
        if (dVar != null) {
            dVar.a();
        }
        this.e.setHideOnContentScrollEnabled(false);
        this.h.k();
        d dVar2 = new d(this.h.getContext(), aVar);
        if (!dVar2.r()) {
            return null;
        }
        this.m = dVar2;
        dVar2.i();
        this.h.h(dVar2);
        z(true);
        this.h.sendAccessibilityEvent(32);
        return dVar2;
    }

    public void z(boolean z) {
        a2 a2VarM;
        a2 a2VarF;
        if (z) {
            O();
        } else {
            G();
        }
        if (!N()) {
            if (z) {
                this.g.w(4);
                this.h.setVisibility(0);
                return;
            } else {
                this.g.w(0);
                this.h.setVisibility(8);
                return;
            }
        }
        if (z) {
            a2VarF = this.g.m(4, 100L);
            a2VarM = this.h.f(0, 200L);
        } else {
            a2VarM = this.g.m(0, 200L);
            a2VarF = this.h.f(8, 100L);
        }
        f.h hVar = new f.h();
        hVar.d(a2VarF, a2VarM);
        hVar.h();
    }

    public q(Dialog dialog) {
        this.d = dialog;
        H(dialog.getWindow().getDecorView());
    }
}
