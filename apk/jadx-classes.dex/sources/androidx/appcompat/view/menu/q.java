package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.widget.d2;
import androidx.core.view.c1;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
final class q extends k implements PopupWindow.OnDismissListener, View.OnKeyListener {
    private static final int v = R$layout.abc_popup_menu_item_layout;
    private final Context b;
    private final g c;
    private final f d;
    private final boolean e;
    private final int f;
    private final int g;
    private final int h;
    final d2 i;
    private PopupWindow.OnDismissListener l;
    private View m;
    View n;
    private m.a o;
    ViewTreeObserver p;
    private boolean q;
    private boolean r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f12s;
    private boolean u;
    final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    private final View.OnAttachStateChangeListener k = new b();
    private int t = 0;

    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!q.this.isShowing() || q.this.i.u()) {
                return;
            }
            View view = q.this.n;
            if (view == null || !view.isShown()) {
                q.this.dismiss();
            } else {
                q.this.i.show();
            }
        }
    }

    class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = q.this.p;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    q.this.p = view.getViewTreeObserver();
                }
                q qVar = q.this;
                qVar.p.removeGlobalOnLayoutListener(qVar.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public q(Context context, g gVar, View view, int i, int i2, boolean z) {
        this.b = context;
        this.c = gVar;
        this.e = z;
        this.d = new f(gVar, LayoutInflater.from(context), z, v);
        this.g = i;
        this.h = i2;
        Resources resources = context.getResources();
        this.f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.m = view;
        this.i = new d2(context, null, i, i2);
        gVar.addMenuPresenter(this, context);
    }

    private boolean p() {
        View view;
        if (isShowing()) {
            return true;
        }
        if (this.q || (view = this.m) == null) {
            return false;
        }
        this.n = view;
        this.i.D(this);
        this.i.E(this);
        this.i.C(true);
        View view2 = this.n;
        boolean z = this.p == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.p = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.j);
        }
        view2.addOnAttachStateChangeListener(this.k);
        this.i.w(view2);
        this.i.z(this.t);
        if (!this.r) {
            this.f12s = k.d(this.d, null, this.b, this.f);
            this.r = true;
        }
        this.i.y(this.f12s);
        this.i.B(2);
        this.i.A(c());
        this.i.show();
        ListView listViewM = this.i.m();
        listViewM.setOnKeyListener(this);
        if (this.u && this.c.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.b).inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewM, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.c.getHeaderTitle());
            }
            frameLayout.setEnabled(false);
            listViewM.addHeaderView(frameLayout, null, false);
        }
        this.i.k(this.d);
        this.i.show();
        return true;
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(g gVar) {
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        if (isShowing()) {
            this.i.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void e(View view) {
        this.m = view;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.k
    public void g(boolean z) {
        this.d.d(z);
    }

    @Override // androidx.appcompat.view.menu.k
    public void h(int i) {
        this.t = i;
    }

    @Override // androidx.appcompat.view.menu.k
    public void i(int i) {
        this.i.c(i);
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean isShowing() {
        return !this.q && this.i.isShowing();
    }

    @Override // androidx.appcompat.view.menu.k
    public void j(PopupWindow.OnDismissListener onDismissListener) {
        this.l = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.k
    public void k(boolean z) {
        this.u = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void l(int i) {
        this.i.g(i);
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView m() {
        return this.i.m();
    }

    @Override // androidx.appcompat.view.menu.m
    public void onCloseMenu(g gVar, boolean z) {
        if (gVar != this.c) {
            return;
        }
        dismiss();
        m.a aVar = this.o;
        if (aVar != null) {
            aVar.onCloseMenu(gVar, z);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.q = true;
        this.c.close();
        ViewTreeObserver viewTreeObserver = this.p;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.p = this.n.getViewTreeObserver();
            }
            this.p.removeGlobalOnLayoutListener(this.j);
            this.p = null;
        }
        this.n.removeOnAttachStateChangeListener(this.k);
        PopupWindow.OnDismissListener onDismissListener = this.l;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean onSubMenuSelected(r rVar) {
        if (rVar.hasVisibleItems()) {
            l lVar = new l(this.b, rVar, this.n, this.e, this.g, this.h);
            lVar.j(this.o);
            lVar.g(k.n(rVar));
            lVar.i(this.l);
            this.l = null;
            this.c.close(false);
            int iA = this.i.a();
            int iJ = this.i.j();
            if ((Gravity.getAbsoluteGravity(this.t, c1.z(this.m)) & 7) == 5) {
                iA += this.m.getWidth();
            }
            if (lVar.n(iA, iJ)) {
                m.a aVar = this.o;
                if (aVar == null) {
                    return true;
                }
                aVar.a(rVar);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void setCallback(m.a aVar) {
        this.o = aVar;
    }

    @Override // androidx.appcompat.view.menu.p
    public void show() {
        if (!p()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void updateMenuView(boolean z) {
        this.r = false;
        f fVar = this.d;
        if (fVar != null) {
            fVar.notifyDataSetChanged();
        }
    }
}
