package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.widget.d2;
import androidx.appcompat.widget.z1;
import androidx.core.view.c1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class d extends k implements View.OnKeyListener, PopupWindow.OnDismissListener {
    private static final int B = R$layout.abc_cascading_menu_item_layout;
    boolean A;
    private final Context b;
    private final int c;
    private final int d;
    private final int e;
    private final boolean f;
    final Handler g;
    private View o;
    View p;
    private boolean r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f10s;
    private int t;
    private int u;
    private boolean w;
    private m.a x;
    ViewTreeObserver y;
    private PopupWindow.OnDismissListener z;
    private final List h = new ArrayList();
    final List i = new ArrayList();
    final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    private final View.OnAttachStateChangeListener k = new b();
    private final z1 l = new c();
    private int m = 0;
    private int n = 0;
    private boolean v = false;
    private int q = t();

    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.isShowing() || d.this.i.size() <= 0 || ((C0010d) d.this.i.get(0)).a.u()) {
                return;
            }
            View view = d.this.p;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
                return;
            }
            Iterator it = d.this.i.iterator();
            while (it.hasNext()) {
                ((C0010d) it.next()).a.show();
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
            ViewTreeObserver viewTreeObserver = d.this.y;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    d.this.y = view.getViewTreeObserver();
                }
                d dVar = d.this;
                dVar.y.removeGlobalOnLayoutListener(dVar.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    class c implements z1 {

        class a implements Runnable {
            final /* synthetic */ C0010d a;
            final /* synthetic */ MenuItem b;
            final /* synthetic */ g c;

            a(C0010d c0010d, MenuItem menuItem, g gVar) {
                this.a = c0010d;
                this.b = menuItem;
                this.c = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0010d c0010d = this.a;
                if (c0010d != null) {
                    d.this.A = true;
                    c0010d.b.close(false);
                    d.this.A = false;
                }
                if (this.b.isEnabled() && this.b.hasSubMenu()) {
                    this.c.performItemAction(this.b, 4);
                }
            }
        }

        c() {
        }

        @Override // androidx.appcompat.widget.z1
        public void b(g gVar, MenuItem menuItem) {
            d.this.g.removeCallbacksAndMessages(null);
            int size = d.this.i.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (gVar == ((C0010d) d.this.i.get(i)).b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            d.this.g.postAtTime(new a(i2 < d.this.i.size() ? (C0010d) d.this.i.get(i2) : null, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.z1
        public void l(g gVar, MenuItem menuItem) {
            d.this.g.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.view.menu.d$d, reason: collision with other inner class name */
    private static class C0010d {
        public final d2 a;
        public final g b;
        public final int c;

        public C0010d(d2 d2Var, g gVar, int i) {
            this.a = d2Var;
            this.b = gVar;
            this.c = i;
        }

        public ListView a() {
            return this.a.m();
        }
    }

    public d(Context context, View view, int i, int i2, boolean z) {
        this.b = context;
        this.o = view;
        this.d = i;
        this.e = i2;
        this.f = z;
        Resources resources = context.getResources();
        this.c = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.g = new Handler();
    }

    private d2 p() {
        d2 d2Var = new d2(this.b, null, this.d, this.e);
        d2Var.M(this.l);
        d2Var.E(this);
        d2Var.D(this);
        d2Var.w(this.o);
        d2Var.z(this.n);
        d2Var.C(true);
        d2Var.B(2);
        return d2Var;
    }

    private int q(g gVar) {
        int size = this.i.size();
        for (int i = 0; i < size; i++) {
            if (gVar == ((C0010d) this.i.get(i)).b) {
                return i;
            }
        }
        return -1;
    }

    private MenuItem r(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = gVar.getItem(i);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    private View s(C0010d c0010d, g gVar) {
        f fVar;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemR = r(c0010d.b, gVar);
        if (menuItemR == null) {
            return null;
        }
        ListView listViewA = c0010d.a();
        ListAdapter adapter = listViewA.getAdapter();
        int i = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            fVar = (f) headerViewListAdapter.getWrappedAdapter();
        } else {
            fVar = (f) adapter;
            headersCount = 0;
        }
        int count = fVar.getCount();
        while (true) {
            if (i >= count) {
                i = -1;
                break;
            }
            if (menuItemR == fVar.getItem(i)) {
                break;
            }
            i++;
        }
        if (i != -1 && (firstVisiblePosition = (i + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    private int t() {
        return c1.z(this.o) == 1 ? 0 : 1;
    }

    private int u(int i) {
        List list = this.i;
        ListView listViewA = ((C0010d) list.get(list.size() - 1)).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.p.getWindowVisibleDisplayFrame(rect);
        if (this.q == 1) {
            return (iArr[0] + listViewA.getWidth()) + i > rect.right ? 0 : 1;
        }
        return iArr[0] - i < 0 ? 1 : 0;
    }

    private void v(g gVar) {
        C0010d c0010d;
        View viewS;
        int i;
        int i2;
        int i3;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.b);
        f fVar = new f(gVar, layoutInflaterFrom, this.f, B);
        if (!isShowing() && this.v) {
            fVar.d(true);
        } else if (isShowing()) {
            fVar.d(k.n(gVar));
        }
        int iD = k.d(fVar, null, this.b, this.c);
        d2 d2VarP = p();
        d2VarP.k(fVar);
        d2VarP.y(iD);
        d2VarP.z(this.n);
        if (this.i.size() > 0) {
            List list = this.i;
            c0010d = (C0010d) list.get(list.size() - 1);
            viewS = s(c0010d, gVar);
        } else {
            c0010d = null;
            viewS = null;
        }
        if (viewS != null) {
            d2VarP.N(false);
            d2VarP.K(null);
            int iU = u(iD);
            boolean z = iU == 1;
            this.q = iU;
            if (Build.VERSION.SDK_INT >= 26) {
                d2VarP.w(viewS);
                i2 = 0;
                i = 0;
            } else {
                int[] iArr = new int[2];
                this.o.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                viewS.getLocationOnScreen(iArr2);
                if ((this.n & 7) == 5) {
                    iArr[0] = iArr[0] + this.o.getWidth();
                    iArr2[0] = iArr2[0] + viewS.getWidth();
                }
                i = iArr2[0] - iArr[0];
                i2 = iArr2[1] - iArr[1];
            }
            if ((this.n & 5) == 5) {
                if (z) {
                    i3 = i + iD;
                } else {
                    iD = viewS.getWidth();
                    i3 = i - iD;
                }
            } else if (z) {
                iD = viewS.getWidth();
                i3 = i + iD;
            } else {
                i3 = i - iD;
            }
            d2VarP.c(i3);
            d2VarP.F(true);
            d2VarP.g(i2);
        } else {
            if (this.r) {
                d2VarP.c(this.t);
            }
            if (this.f10s) {
                d2VarP.g(this.u);
            }
            d2VarP.A(c());
        }
        this.i.add(new C0010d(d2VarP, gVar, this.q));
        d2VarP.show();
        ListView listViewM = d2VarP.m();
        listViewM.setOnKeyListener(this);
        if (c0010d == null && this.w && gVar.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewM, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(gVar.getHeaderTitle());
            listViewM.addHeaderView(frameLayout, null, false);
            d2VarP.show();
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(g gVar) {
        gVar.addMenuPresenter(this, this.b);
        if (isShowing()) {
            v(gVar);
        } else {
            this.h.add(gVar);
        }
    }

    @Override // androidx.appcompat.view.menu.k
    protected boolean b() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        int size = this.i.size();
        if (size > 0) {
            C0010d[] c0010dArr = (C0010d[]) this.i.toArray(new C0010d[size]);
            for (int i = size - 1; i >= 0; i--) {
                C0010d c0010d = c0010dArr[i];
                if (c0010d.a.isShowing()) {
                    c0010d.a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void e(View view) {
        if (this.o != view) {
            this.o = view;
            this.n = androidx.core.view.j.b(this.m, c1.z(view));
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.k
    public void g(boolean z) {
        this.v = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void h(int i) {
        if (this.m != i) {
            this.m = i;
            this.n = androidx.core.view.j.b(i, c1.z(this.o));
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void i(int i) {
        this.r = true;
        this.t = i;
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean isShowing() {
        return this.i.size() > 0 && ((C0010d) this.i.get(0)).a.isShowing();
    }

    @Override // androidx.appcompat.view.menu.k
    public void j(PopupWindow.OnDismissListener onDismissListener) {
        this.z = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.k
    public void k(boolean z) {
        this.w = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void l(int i) {
        this.f10s = true;
        this.u = i;
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView m() {
        if (this.i.isEmpty()) {
            return null;
        }
        List list = this.i;
        return ((C0010d) list.get(list.size() - 1)).a();
    }

    @Override // androidx.appcompat.view.menu.m
    public void onCloseMenu(g gVar, boolean z) {
        int iQ = q(gVar);
        if (iQ < 0) {
            return;
        }
        int i = iQ + 1;
        if (i < this.i.size()) {
            ((C0010d) this.i.get(i)).b.close(false);
        }
        C0010d c0010d = (C0010d) this.i.remove(iQ);
        c0010d.b.removeMenuPresenter(this);
        if (this.A) {
            c0010d.a.L(null);
            c0010d.a.x(0);
        }
        c0010d.a.dismiss();
        int size = this.i.size();
        if (size > 0) {
            this.q = ((C0010d) this.i.get(size - 1)).c;
        } else {
            this.q = t();
        }
        if (size != 0) {
            if (z) {
                ((C0010d) this.i.get(0)).b.close(false);
                return;
            }
            return;
        }
        dismiss();
        m.a aVar = this.x;
        if (aVar != null) {
            aVar.onCloseMenu(gVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.y;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.y.removeGlobalOnLayoutListener(this.j);
            }
            this.y = null;
        }
        this.p.removeOnAttachStateChangeListener(this.k);
        this.z.onDismiss();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0010d c0010d;
        int size = this.i.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                c0010d = null;
                break;
            }
            c0010d = (C0010d) this.i.get(i);
            if (!c0010d.a.isShowing()) {
                break;
            } else {
                i++;
            }
        }
        if (c0010d != null) {
            c0010d.b.close(false);
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
        for (C0010d c0010d : this.i) {
            if (rVar == c0010d.b) {
                c0010d.a().requestFocus();
                return true;
            }
        }
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        a(rVar);
        m.a aVar = this.x;
        if (aVar != null) {
            aVar.a(rVar);
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public void setCallback(m.a aVar) {
        this.x = aVar;
    }

    @Override // androidx.appcompat.view.menu.p
    public void show() {
        if (isShowing()) {
            return;
        }
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            v((g) it.next());
        }
        this.h.clear();
        View view = this.o;
        this.p = view;
        if (view != null) {
            boolean z = this.y == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.y = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.j);
            }
            this.p.addOnAttachStateChangeListener(this.k);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void updateMenuView(boolean z) {
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            k.o(((C0010d) it.next()).a().getAdapter()).notifyDataSetChanged();
        }
    }
}
