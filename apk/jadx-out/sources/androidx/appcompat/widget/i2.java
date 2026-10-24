package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$id;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class i2 implements c1 {
    Toolbar a;
    private int b;
    private View c;
    private View d;
    private Drawable e;
    private Drawable f;
    private Drawable g;
    private boolean h;
    CharSequence i;
    private CharSequence j;
    private CharSequence k;
    Window.Callback l;
    boolean m;
    private c n;
    private int o;
    private int p;
    private Drawable q;

    class a implements View.OnClickListener {
        final androidx.appcompat.view.menu.a a;

        a() {
            this.a = new androidx.appcompat.view.menu.a(i2.this.a.getContext(), 0, R.id.home, 0, 0, i2.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            i2 i2Var = i2.this;
            Window.Callback callback = i2Var.l;
            if (callback == null || !i2Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.a);
        }
    }

    class b extends androidx.core.view.e1 {
        private boolean a = false;
        final /* synthetic */ int b;

        b(int i) {
            this.b = i;
        }

        @Override // androidx.core.view.e1, androidx.core.view.d1
        public void a(View view) {
            this.a = true;
        }

        @Override // androidx.core.view.d1
        public void b(View view) {
            if (this.a) {
                return;
            }
            i2.this.a.setVisibility(this.b);
        }

        @Override // androidx.core.view.e1, androidx.core.view.d1
        public void c(View view) {
            i2.this.a.setVisibility(0);
        }
    }

    public i2(Toolbar toolbar, boolean z) {
        this(toolbar, z, R$string.abc_action_bar_up_description, R$drawable.abc_ic_ab_back_material);
    }

    private void G(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.b & 8) != 0) {
            this.a.setTitle(charSequence);
        }
    }

    private void H() {
        if ((this.b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.a.setNavigationContentDescription(this.p);
            } else {
                this.a.setNavigationContentDescription(this.k);
            }
        }
    }

    private void I() {
        if ((this.b & 4) == 0) {
            this.a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.a;
        Drawable drawable = this.g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void J() {
        Drawable drawable;
        int i = this.b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f) == null) {
            drawable = this.e;
        }
        this.a.setLogo(drawable);
    }

    private int z() {
        if (this.a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.a.getNavigationIcon();
        return 15;
    }

    public void A(View view) {
        View view2 = this.d;
        if (view2 != null && (this.b & 16) != 0) {
            this.a.removeView(view2);
        }
        this.d = view;
        if (view == null || (this.b & 16) == 0) {
            return;
        }
        this.a.addView(view);
    }

    public void B(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.a.getNavigationContentDescription())) {
            D(this.p);
        }
    }

    public void C(Drawable drawable) {
        this.f = drawable;
        J();
    }

    public void D(int i) {
        E(i == 0 ? null : getContext().getString(i));
    }

    public void E(CharSequence charSequence) {
        this.k = charSequence;
        H();
    }

    public void F(Drawable drawable) {
        this.g = drawable;
        I();
    }

    @Override // androidx.appcompat.widget.c1
    public boolean a() {
        return this.a.canShowOverflowMenu();
    }

    @Override // androidx.appcompat.widget.c1
    public boolean b() {
        return this.a.hideOverflowMenu();
    }

    @Override // androidx.appcompat.widget.c1
    public boolean c() {
        return this.a.showOverflowMenu();
    }

    @Override // androidx.appcompat.widget.c1
    public void collapseActionView() {
        this.a.collapseActionView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.widget.c1
    public void d(Menu menu, androidx.appcompat.view.menu.m.a aVar) {
        if (this.n == null) {
            c cVar = new c(this.a.getContext());
            this.n = cVar;
            cVar.h(R$id.action_menu_presenter);
        }
        this.n.setCallback(aVar);
        this.a.setMenu((androidx.appcompat.view.menu.g) menu, this.n);
    }

    @Override // androidx.appcompat.widget.c1
    public boolean e() {
        return this.a.isOverflowMenuShowing();
    }

    @Override // androidx.appcompat.widget.c1
    public void f() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.c1
    public boolean g() {
        return this.a.isOverflowMenuShowPending();
    }

    @Override // androidx.appcompat.widget.c1
    public Context getContext() {
        return this.a.getContext();
    }

    @Override // androidx.appcompat.widget.c1
    public CharSequence getTitle() {
        return this.a.getTitle();
    }

    @Override // androidx.appcompat.widget.c1
    public boolean h() {
        return this.a.hasExpandedActionView();
    }

    @Override // androidx.appcompat.widget.c1
    public void i(int i) {
        View view;
        int i2 = this.b ^ i;
        this.b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    H();
                }
                I();
            }
            if ((i2 & 3) != 0) {
                J();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.a.setTitle(this.i);
                    this.a.setSubtitle(this.j);
                } else {
                    this.a.setTitle((CharSequence) null);
                    this.a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.a.addView(view);
            } else {
                this.a.removeView(view);
            }
        }
    }

    @Override // androidx.appcompat.widget.c1
    public void j(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.b & 8) != 0) {
            this.a.setSubtitle(charSequence);
        }
    }

    @Override // androidx.appcompat.widget.c1
    public Menu k() {
        return this.a.getMenu();
    }

    @Override // androidx.appcompat.widget.c1
    public int l() {
        return this.o;
    }

    @Override // androidx.appcompat.widget.c1
    public androidx.core.view.c1 m(int i, long j) {
        return androidx.core.view.m0.c(this.a).a(i == 0 ? 1.0f : 0.0f).d(j).f(new b(i));
    }

    @Override // androidx.appcompat.widget.c1
    public ViewGroup n() {
        return this.a;
    }

    @Override // androidx.appcompat.widget.c1
    public void o(boolean z) {
    }

    @Override // androidx.appcompat.widget.c1
    public void p() {
    }

    @Override // androidx.appcompat.widget.c1
    public void q(boolean z) {
        this.a.setCollapsible(z);
    }

    @Override // androidx.appcompat.widget.c1
    public void r() {
        this.a.dismissPopupMenus();
    }

    @Override // androidx.appcompat.widget.c1
    public void s(z1 z1Var) {
        View view = this.c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.a;
            if (parent == toolbar) {
                toolbar.removeView(this.c);
            }
        }
        this.c = z1Var;
    }

    @Override // androidx.appcompat.widget.c1
    public void setIcon(int i) {
        setIcon(i != 0 ? f.b.d(getContext(), i) : null);
    }

    @Override // androidx.appcompat.widget.c1
    public void setTitle(CharSequence charSequence) {
        this.h = true;
        G(charSequence);
    }

    @Override // androidx.appcompat.widget.c1
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // androidx.appcompat.widget.c1
    public void setWindowTitle(CharSequence charSequence) {
        if (this.h) {
            return;
        }
        G(charSequence);
    }

    @Override // androidx.appcompat.widget.c1
    public void t(int i) {
        C(i != 0 ? f.b.d(getContext(), i) : null);
    }

    @Override // androidx.appcompat.widget.c1
    public void u(int i) {
        F(i != 0 ? f.b.d(getContext(), i) : null);
    }

    @Override // androidx.appcompat.widget.c1
    public void v(androidx.appcompat.view.menu.m.a aVar, androidx.appcompat.view.menu.g.a aVar2) {
        this.a.setMenuCallbacks(aVar, aVar2);
    }

    @Override // androidx.appcompat.widget.c1
    public void w(int i) {
        this.a.setVisibility(i);
    }

    @Override // androidx.appcompat.widget.c1
    public int x() {
        return this.b;
    }

    @Override // androidx.appcompat.widget.c1
    public void y() {
    }

    public i2(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        h2 h2VarV = h2.v(toolbar.getContext(), null, R$styleable.a, R$attr.actionBarStyle, 0);
        this.q = h2VarV.g(R$styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence charSequenceP = h2VarV.p(R$styleable.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceP)) {
                setTitle(charSequenceP);
            }
            CharSequence charSequenceP2 = h2VarV.p(R$styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceP2)) {
                j(charSequenceP2);
            }
            Drawable drawableG = h2VarV.g(R$styleable.ActionBar_logo);
            if (drawableG != null) {
                C(drawableG);
            }
            Drawable drawableG2 = h2VarV.g(R$styleable.ActionBar_icon);
            if (drawableG2 != null) {
                setIcon(drawableG2);
            }
            if (this.g == null && (drawable = this.q) != null) {
                F(drawable);
            }
            i(h2VarV.k(R$styleable.ActionBar_displayOptions, 0));
            int iN = h2VarV.n(R$styleable.ActionBar_customNavigationLayout, 0);
            if (iN != 0) {
                A(LayoutInflater.from(this.a.getContext()).inflate(iN, (ViewGroup) this.a, false));
                i(this.b | 16);
            }
            int iM = h2VarV.m(R$styleable.ActionBar_height, 0);
            if (iM > 0) {
                ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
                layoutParams.height = iM;
                this.a.setLayoutParams(layoutParams);
            }
            int iE = h2VarV.e(R$styleable.ActionBar_contentInsetStart, -1);
            int iE2 = h2VarV.e(R$styleable.ActionBar_contentInsetEnd, -1);
            if (iE >= 0 || iE2 >= 0) {
                this.a.setContentInsetsRelative(Math.max(iE, 0), Math.max(iE2, 0));
            }
            int iN2 = h2VarV.n(R$styleable.ActionBar_titleTextStyle, 0);
            if (iN2 != 0) {
                Toolbar toolbar2 = this.a;
                toolbar2.setTitleTextAppearance(toolbar2.getContext(), iN2);
            }
            int iN3 = h2VarV.n(R$styleable.ActionBar_subtitleTextStyle, 0);
            if (iN3 != 0) {
                Toolbar toolbar3 = this.a;
                toolbar3.setSubtitleTextAppearance(toolbar3.getContext(), iN3);
            }
            int iN4 = h2VarV.n(R$styleable.ActionBar_popupTheme, 0);
            if (iN4 != 0) {
                this.a.setPopupTheme(iN4);
            }
        } else {
            this.b = z();
        }
        h2VarV.w();
        B(i);
        this.k = this.a.getNavigationContentDescription();
        this.a.setNavigationOnClickListener(new a());
    }

    @Override // androidx.appcompat.widget.c1
    public void setIcon(Drawable drawable) {
        this.e = drawable;
        J();
    }
}
