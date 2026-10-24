package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class d extends androidx.appcompat.view.menu.b implements androidx.core.view.b.a {
    c A;
    private b B;
    final f C;
    int D;
    C0012d k;
    private Drawable l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    private int r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f16s;
    private boolean t;
    private boolean u;
    private boolean v;
    private int w;
    private final SparseBooleanArray x;
    e y;
    a z;

    private class a extends androidx.appcompat.view.menu.l {
        public a(Context context, androidx.appcompat.view.menu.r rVar, View view) {
            super(context, rVar, view, false, R$attr.actionOverflowMenuStyle);
            if (!((androidx.appcompat.view.menu.i) rVar.getItem()).l()) {
                View view2 = d.this.k;
                f(view2 == null ? (View) ((androidx.appcompat.view.menu.b) d.this).i : view2);
            }
            j(d.this.C);
        }

        @Override // androidx.appcompat.view.menu.l
        protected void e() {
            d dVar = d.this;
            dVar.z = null;
            dVar.D = 0;
            super.e();
        }
    }

    private class b extends ActionMenuItemView.b {
        b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public androidx.appcompat.view.menu.p a() {
            a aVar = d.this.z;
            if (aVar != null) {
                return aVar.c();
            }
            return null;
        }
    }

    private class c implements Runnable {
        private e a;

        public c(e eVar) {
            this.a = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((androidx.appcompat.view.menu.b) d.this).c != null) {
                ((androidx.appcompat.view.menu.b) d.this).c.changeMenuMode();
            }
            View view = (View) ((androidx.appcompat.view.menu.b) d.this).i;
            if (view != null && view.getWindowToken() != null && this.a.m()) {
                d.this.y = this.a;
            }
            d.this.A = null;
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.widget.d$d, reason: collision with other inner class name */
    private class C0012d extends q implements ActionMenuView.a {
        private final float[] a;

        /* JADX INFO: renamed from: androidx.appcompat.widget.d$d$a */
        class a extends t1 {
            final /* synthetic */ d j;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(View view, d dVar) {
                super(view);
                this.j = dVar;
            }

            @Override // androidx.appcompat.widget.t1
            public androidx.appcompat.view.menu.p b() {
                e eVar = d.this.y;
                if (eVar == null) {
                    return null;
                }
                return eVar.c();
            }

            @Override // androidx.appcompat.widget.t1
            public boolean c() {
                d.this.B();
                return true;
            }

            @Override // androidx.appcompat.widget.t1
            public boolean d() {
                d dVar = d.this;
                if (dVar.A != null) {
                    return false;
                }
                dVar.s();
                return true;
            }
        }

        public C0012d(Context context) {
            super(context, null, R$attr.actionOverflowButtonStyle);
            this.a = new float[2];
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            u2.a(this, getContentDescription());
            setOnTouchListener(new a(this, d.this));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean a() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean b() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            d.this.B();
            return true;
        }

        @Override // android.widget.ImageView
        protected boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                q.h.l(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    private class e extends androidx.appcompat.view.menu.l {
        public e(Context context, androidx.appcompat.view.menu.g gVar, View view, boolean z) {
            super(context, gVar, view, z, R$attr.actionOverflowMenuStyle);
            h(8388613);
            j(d.this.C);
        }

        @Override // androidx.appcompat.view.menu.l
        protected void e() {
            if (((androidx.appcompat.view.menu.b) d.this).c != null) {
                ((androidx.appcompat.view.menu.b) d.this).c.close();
            }
            d.this.y = null;
            super.e();
        }
    }

    private class f implements androidx.appcompat.view.menu.m.a {
        f() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            if (gVar == null) {
                return false;
            }
            d.this.D = ((androidx.appcompat.view.menu.r) gVar).getItem().getItemId();
            androidx.appcompat.view.menu.m.a aVarE = d.this.e();
            if (aVarE != null) {
                return aVarE.a(gVar);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
            if (gVar instanceof androidx.appcompat.view.menu.r) {
                gVar.getRootMenu().close(false);
            }
            androidx.appcompat.view.menu.m.a aVarE = d.this.e();
            if (aVarE != null) {
                aVarE.onCloseMenu(gVar, z);
            }
        }
    }

    private static class g implements Parcelable {
        public static final Parcelable.Creator<g> CREATOR = new a();
        public int a;

        static class a implements Parcelable.Creator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public g createFromParcel(Parcel parcel) {
                return new g(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public g[] newArray(int i) {
                return new g[i];
            }
        }

        g() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
        }

        g(Parcel parcel) {
            this.a = parcel.readInt();
        }
    }

    public d(Context context) {
        super(context, R$layout.abc_action_menu_layout, R$layout.abc_action_menu_item_layout);
        this.x = new SparseBooleanArray();
        this.C = new f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View q(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.i;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof androidx.appcompat.view.menu.n.a) && ((androidx.appcompat.view.menu.n.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public void A(boolean z) {
        this.n = z;
        this.o = true;
    }

    public boolean B() {
        androidx.appcompat.view.menu.g gVar;
        if (!this.n || v() || (gVar = this.c) == null || this.i == null || this.A != null || gVar.getNonActionItems().isEmpty()) {
            return false;
        }
        c cVar = new c(new e(this.b, this.c, this.k, true));
        this.A = cVar;
        ((View) this.i).post(cVar);
        super.onSubMenuSelected(null);
        return true;
    }

    @Override // androidx.appcompat.view.menu.b
    public void b(androidx.appcompat.view.menu.i iVar, androidx.appcompat.view.menu.n.a aVar) {
        aVar.initialize(iVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.i);
        if (this.B == null) {
            this.B = new b();
        }
        actionMenuItemView.setPopupCallback(this.B);
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean d(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.k) {
            return false;
        }
        return super.d(viewGroup, i);
    }

    @Override // androidx.appcompat.view.menu.b
    public View f(androidx.appcompat.view.menu.i iVar, View view, ViewGroup viewGroup) {
        View actionView = iVar.getActionView();
        if (actionView == null || iVar.j()) {
            actionView = super.f(iVar, view, viewGroup);
        }
        actionView.setVisibility(iVar.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean flagActionItems() {
        ArrayList<androidx.appcompat.view.menu.i> visibleItems;
        int size;
        int i;
        int iQ;
        d dVar = this;
        androidx.appcompat.view.menu.g gVar = dVar.c;
        View view = null;
        int i2 = 0;
        if (gVar != null) {
            visibleItems = gVar.getVisibleItems();
            size = visibleItems.size();
        } else {
            visibleItems = null;
            size = 0;
        }
        int i3 = dVar.r;
        int i4 = dVar.q;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) dVar.i;
        boolean z = false;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            androidx.appcompat.view.menu.i iVar = visibleItems.get(i7);
            if (iVar.o()) {
                i5++;
            } else if (iVar.n()) {
                i6++;
            } else {
                z = true;
            }
            if (dVar.v && iVar.isActionViewExpanded()) {
                i3 = 0;
            }
        }
        if (dVar.n && (z || i6 + i5 > i3)) {
            i3--;
        }
        int i8 = i3 - i5;
        SparseBooleanArray sparseBooleanArray = dVar.x;
        sparseBooleanArray.clear();
        if (dVar.t) {
            int i9 = dVar.w;
            iQ = i4 / i9;
            i = i9 + ((i4 % i9) / iQ);
        } else {
            i = 0;
            iQ = 0;
        }
        int i10 = 0;
        int i11 = 0;
        while (i10 < size) {
            androidx.appcompat.view.menu.i iVar2 = visibleItems.get(i10);
            if (iVar2.o()) {
                View viewF = dVar.f(iVar2, view, viewGroup);
                if (dVar.t) {
                    iQ -= ActionMenuView.q(viewF, i, iQ, iMakeMeasureSpec, i2);
                } else {
                    viewF.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewF.getMeasuredWidth();
                i4 -= measuredWidth;
                if (i11 == 0) {
                    i11 = measuredWidth;
                }
                int groupId = iVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                iVar2.u(true);
            } else {
                if (iVar2.n()) {
                    int groupId2 = iVar2.getGroupId();
                    boolean z2 = sparseBooleanArray.get(groupId2);
                    boolean z3 = (i8 > 0 || z2) && i4 > 0 && (!dVar.t || iQ > 0);
                    boolean z4 = z3;
                    if (z3) {
                        View viewF2 = dVar.f(iVar2, null, viewGroup);
                        if (dVar.t) {
                            int iQ2 = ActionMenuView.q(viewF2, i, iQ, iMakeMeasureSpec, 0);
                            iQ -= iQ2;
                            if (iQ2 == 0) {
                                z4 = false;
                            }
                        } else {
                            viewF2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                        }
                        boolean z5 = z4;
                        int measuredWidth2 = viewF2.getMeasuredWidth();
                        i4 -= measuredWidth2;
                        if (i11 == 0) {
                            i11 = measuredWidth2;
                        }
                        z3 = z5 & (!dVar.t ? i4 + i11 <= 0 : i4 < 0);
                    }
                    if (z3 && groupId2 != 0) {
                        sparseBooleanArray.put(groupId2, true);
                    } else if (z2) {
                        sparseBooleanArray.put(groupId2, false);
                        for (int i12 = 0; i12 < i10; i12++) {
                            androidx.appcompat.view.menu.i iVar3 = visibleItems.get(i12);
                            if (iVar3.getGroupId() == groupId2) {
                                if (iVar3.l()) {
                                    i8++;
                                }
                                iVar3.u(false);
                            }
                        }
                    }
                    if (z3) {
                        i8--;
                    }
                    iVar2.u(z3);
                } else {
                    size = size;
                    iVar2.u(false);
                }
                i10++;
                view = null;
                dVar = this;
                size = size;
                i2 = 0;
            }
            i10++;
            view = null;
            dVar = this;
            size = size;
            i2 = 0;
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.b
    public androidx.appcompat.view.menu.n g(ViewGroup viewGroup) {
        androidx.appcompat.view.menu.n nVar = this.i;
        androidx.appcompat.view.menu.n nVarG = super.g(viewGroup);
        if (nVar != nVarG) {
            ((ActionMenuView) nVarG).setPresenter(this);
        }
        return nVarG;
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean i(int i, androidx.appcompat.view.menu.i iVar) {
        return iVar.l();
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void initForMenu(Context context, androidx.appcompat.view.menu.g gVar) {
        super.initForMenu(context, gVar);
        Resources resources = context.getResources();
        f.a aVarB = f.a.b(context);
        if (!this.o) {
            this.n = aVarB.f();
        }
        if (!this.u) {
            this.p = aVarB.c();
        }
        if (!this.f16s) {
            this.r = aVarB.d();
        }
        int measuredWidth = this.p;
        if (this.n) {
            if (this.k == null) {
                C0012d c0012d = new C0012d(this.a);
                this.k = c0012d;
                if (this.m) {
                    c0012d.setImageDrawable(this.l);
                    this.l = null;
                    this.m = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.k.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.k.getMeasuredWidth();
        } else {
            this.k = null;
        }
        this.q = measuredWidth;
        this.w = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
        p();
        super.onCloseMenu(gVar, z);
    }

    @Override // androidx.appcompat.view.menu.m
    public void onRestoreInstanceState(Parcelable parcelable) {
        int i;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof g) && (i = ((g) parcelable).a) > 0 && (menuItemFindItem = this.c.findItem(i)) != null) {
            onSubMenuSelected((androidx.appcompat.view.menu.r) menuItemFindItem.getSubMenu());
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable onSaveInstanceState() {
        g gVar = new g();
        gVar.a = this.D;
        return gVar;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public boolean onSubMenuSelected(androidx.appcompat.view.menu.r rVar) {
        boolean z = false;
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        androidx.appcompat.view.menu.r rVar2 = rVar;
        while (rVar2.getParentMenu() != this.c) {
            rVar2 = (androidx.appcompat.view.menu.r) rVar2.getParentMenu();
        }
        View viewQ = q(rVar2.getItem());
        if (viewQ == null) {
            return false;
        }
        this.D = rVar.getItem().getItemId();
        int size = rVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = rVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
        }
        a aVar = new a(this.b, rVar, viewQ);
        this.z = aVar;
        aVar.g(z);
        this.z.k();
        super.onSubMenuSelected(rVar);
        return true;
    }

    public boolean p() {
        return s() | t();
    }

    public Drawable r() {
        C0012d c0012d = this.k;
        if (c0012d != null) {
            return c0012d.getDrawable();
        }
        if (this.m) {
            return this.l;
        }
        return null;
    }

    public boolean s() {
        Object obj;
        c cVar = this.A;
        if (cVar != null && (obj = this.i) != null) {
            ((View) obj).removeCallbacks(cVar);
            this.A = null;
            return true;
        }
        e eVar = this.y;
        if (eVar == null) {
            return false;
        }
        eVar.b();
        return true;
    }

    public boolean t() {
        a aVar = this.z;
        if (aVar == null) {
            return false;
        }
        aVar.b();
        return true;
    }

    public boolean u() {
        return this.A != null || v();
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void updateMenuView(boolean z) {
        super.updateMenuView(z);
        ((View) this.i).requestLayout();
        androidx.appcompat.view.menu.g gVar = this.c;
        boolean z2 = false;
        if (gVar != null) {
            ArrayList<androidx.appcompat.view.menu.i> actionItems = gVar.getActionItems();
            int size = actionItems.size();
            for (int i = 0; i < size; i++) {
                androidx.core.view.b bVarA = actionItems.get(i).a();
                if (bVarA != null) {
                    bVarA.k(this);
                }
            }
        }
        androidx.appcompat.view.menu.g gVar2 = this.c;
        ArrayList<androidx.appcompat.view.menu.i> nonActionItems = gVar2 != null ? gVar2.getNonActionItems() : null;
        if (this.n && nonActionItems != null) {
            int size2 = nonActionItems.size();
            if (size2 == 1) {
                z2 = !nonActionItems.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.k == null) {
                this.k = new C0012d(this.a);
            }
            ViewGroup viewGroup = (ViewGroup) this.k.getParent();
            if (viewGroup != this.i) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.k);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.i;
                actionMenuView.addView(this.k, actionMenuView.k());
            }
        } else {
            C0012d c0012d = this.k;
            if (c0012d != null) {
                Object parent = c0012d.getParent();
                Object obj = this.i;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.k);
                }
            }
        }
        ((ActionMenuView) this.i).setOverflowReserved(this.n);
    }

    public boolean v() {
        e eVar = this.y;
        return eVar != null && eVar.d();
    }

    public void w(Configuration configuration) {
        if (!this.f16s) {
            this.r = f.a.b(this.b).d();
        }
        androidx.appcompat.view.menu.g gVar = this.c;
        if (gVar != null) {
            gVar.onItemsChanged(true);
        }
    }

    public void x(boolean z) {
        this.v = z;
    }

    public void y(ActionMenuView actionMenuView) {
        this.i = actionMenuView;
        actionMenuView.initialize(this.c);
    }

    public void z(Drawable drawable) {
        C0012d c0012d = this.k;
        if (c0012d != null) {
            c0012d.setImageDrawable(drawable);
        } else {
            this.m = true;
            this.l = drawable;
        }
    }
}
