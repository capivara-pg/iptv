package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends u1 implements androidx.appcompat.view.menu.g.b, androidx.appcompat.view.menu.n {
    private androidx.appcompat.view.menu.g a;
    private Context b;
    private int c;
    private boolean d;
    private androidx.appcompat.widget.d e;
    private androidx.appcompat.view.menu.m.a f;
    androidx.appcompat.view.menu.g.a g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    e l;

    public interface a {
        boolean a();

        boolean b();
    }

    private static class b implements androidx.appcompat.view.menu.m.a {
        b() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
        }
    }

    public static class c extends u1.a {
        public boolean c;
        public int d;
        public int e;
        public boolean f;
        public boolean g;
        boolean h;

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(c cVar) {
            super(cVar);
            this.c = cVar.c;
        }

        public c(int i, int i2) {
            super(i, i2);
            this.c = false;
        }
    }

    private class d implements androidx.appcompat.view.menu.g.a {
        d() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean onMenuItemSelected(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            e eVar = ActionMenuView.this.l;
            return eVar != null && eVar.onMenuItemClick(menuItem);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void onMenuModeChange(androidx.appcompat.view.menu.g gVar) {
            androidx.appcompat.view.menu.g.a aVar = ActionMenuView.this.g;
            if (aVar != null) {
                aVar.onMenuModeChange(gVar);
            }
        }
    }

    public interface e {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004d  */
    static int q(View view, int i, int i2, int i3, int i4) {
        int i5;
        c cVar = (c) view.getLayoutParams();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3) - i4, View.MeasureSpec.getMode(i3));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z = actionMenuItemView != null && actionMenuItemView.d();
        if (i2 > 0) {
            i5 = 2;
            if (!z || i2 >= 2) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i2 * i, Integer.MIN_VALUE), iMakeMeasureSpec);
                int measuredWidth = view.getMeasuredWidth();
                int i6 = measuredWidth / i;
                if (measuredWidth % i != 0) {
                    i6++;
                }
                if (!z || i6 >= 2) {
                    i5 = i6;
                }
            } else {
                i5 = 0;
            }
        } else {
            i5 = 0;
        }
        cVar.f = !cVar.c && z;
        cVar.d = i5;
        view.measure(View.MeasureSpec.makeMeasureSpec(i * i5, 1073741824), iMakeMeasureSpec);
        return i5;
    }

    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9, types: [boolean, int] */
    private void r(int i, int i2) {
        boolean z;
        int i3;
        boolean z2;
        ?? r14;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
        int i4 = size - paddingLeft;
        int i5 = this.j;
        int i6 = i4 / i5;
        int i7 = i4 % i5;
        if (i6 == 0) {
            setMeasuredDimension(i4, 0);
            return;
        }
        int i8 = i5 + (i7 / i6);
        int childCount = getChildCount();
        int iMax = 0;
        int i9 = 0;
        boolean z3 = false;
        int i10 = 0;
        int iMax2 = 0;
        int i11 = 0;
        long j = 0;
        while (i9 < childCount) {
            View childAt = getChildAt(i9);
            int i12 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z4 = childAt instanceof ActionMenuItemView;
                int i13 = i10 + 1;
                if (z4) {
                    int i14 = this.k;
                    r14 = 0;
                    childAt.setPadding(i14, 0, i14, 0);
                } else {
                    r14 = 0;
                }
                c cVar = (c) childAt.getLayoutParams();
                cVar.h = r14;
                cVar.e = r14;
                cVar.d = r14;
                cVar.f = r14;
                ((ViewGroup.MarginLayoutParams) cVar).leftMargin = r14;
                ((ViewGroup.MarginLayoutParams) cVar).rightMargin = r14;
                cVar.g = z4 && ((ActionMenuItemView) childAt).d();
                int iQ = q(childAt, i8, cVar.c ? 1 : i6, childMeasureSpec, paddingTop);
                iMax2 = Math.max(iMax2, iQ);
                if (cVar.f) {
                    i11++;
                }
                if (cVar.c) {
                    z3 = true;
                }
                i6 -= iQ;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (iQ == 1) {
                    j |= (long) (1 << i9);
                    iMax = iMax;
                }
                i10 = i13;
            }
            i9++;
            size2 = i12;
        }
        int i15 = size2;
        boolean z5 = z3 && i10 == 2;
        boolean z6 = false;
        while (true) {
            if (i11 <= 0 || i6 <= 0) {
                z = z6;
                i3 = iMax;
                break;
            }
            int i16 = 0;
            int i17 = 0;
            int i18 = Integer.MAX_VALUE;
            long j2 = 0;
            while (i17 < childCount) {
                boolean z7 = z6;
                c cVar2 = (c) getChildAt(i17).getLayoutParams();
                int i19 = iMax;
                if (cVar2.f) {
                    int i20 = cVar2.d;
                    if (i20 < i18) {
                        j2 = 1 << i17;
                        i18 = i20;
                        i16 = 1;
                    } else if (i20 == i18) {
                        i16++;
                        j2 |= 1 << i17;
                    }
                }
                i17++;
                iMax = i19;
                z6 = z7;
            }
            z = z6;
            i3 = iMax;
            j |= j2;
            if (i16 > i6) {
                break;
            }
            int i21 = i18 + 1;
            int i22 = 0;
            while (i22 < childCount) {
                View childAt2 = getChildAt(i22);
                c cVar3 = (c) childAt2.getLayoutParams();
                int i23 = i4;
                int i24 = mode;
                long j3 = 1 << i22;
                if ((j2 & j3) == 0) {
                    if (cVar3.d == i21) {
                        j |= j3;
                    }
                    z5 = z5;
                } else {
                    if (z5 && cVar3.g && i6 == 1) {
                        int i25 = this.k;
                        childAt2.setPadding(i25 + i8, 0, i25, 0);
                    }
                    cVar3.d++;
                    cVar3.h = true;
                    i6--;
                }
                i22++;
                mode = i24;
                i4 = i23;
                z5 = z5;
            }
            iMax = i3;
            z6 = true;
        }
        boolean z8 = !z3 && i10 == 1;
        if (i6 <= 0 || j == 0 || (i6 >= i10 - 1 && !z8 && iMax2 <= 1)) {
            z2 = z;
        } else {
            float fBitCount = Long.bitCount(j);
            if (!z8) {
                if ((j & 1) != 0 && !((c) getChildAt(0).getLayoutParams()).g) {
                    fBitCount -= 0.5f;
                }
                int i26 = childCount - 1;
                if ((j & ((long) (1 << i26))) != 0 && !((c) getChildAt(i26).getLayoutParams()).g) {
                    fBitCount -= 0.5f;
                }
            }
            int i27 = fBitCount > 0.0f ? (int) ((i6 * i8) / fBitCount) : 0;
            z2 = z;
            for (int i28 = 0; i28 < childCount; i28++) {
                if ((j & ((long) (1 << i28))) != 0) {
                    View childAt3 = getChildAt(i28);
                    c cVar4 = (c) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        cVar4.e = i27;
                        cVar4.h = true;
                        if (i28 == 0 && !cVar4.g) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = (-i27) / 2;
                        }
                    } else if (cVar4.c) {
                        cVar4.e = i27;
                        cVar4.h = true;
                        ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = (-i27) / 2;
                    } else {
                        if (i28 != 0) {
                            ((ViewGroup.MarginLayoutParams) cVar4).leftMargin = i27 / 2;
                        }
                        if (i28 != childCount - 1) {
                            ((ViewGroup.MarginLayoutParams) cVar4).rightMargin = i27 / 2;
                        }
                    }
                    z2 = true;
                }
            }
        }
        if (z2) {
            for (int i29 = 0; i29 < childCount; i29++) {
                View childAt4 = getChildAt(i29);
                c cVar5 = (c) childAt4.getLayoutParams();
                if (cVar5.h) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((cVar5.d * i8) + cVar5.e, 1073741824), childMeasureSpec);
                }
            }
        }
        setMeasuredDimension(i4, mode != 1073741824 ? i3 : i15);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.g.b
    public boolean b(androidx.appcompat.view.menu.i iVar) {
        return this.a.performItemAction(iVar, 0);
    }

    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof c;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public void g() {
        androidx.appcompat.widget.d dVar = this.e;
        if (dVar != null) {
            dVar.p();
        }
    }

    public Menu getMenu() {
        if (this.a == null) {
            Context context = getContext();
            androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
            this.a = gVar;
            gVar.setCallback(new d());
            androidx.appcompat.widget.d dVar = new androidx.appcompat.widget.d(context);
            this.e = dVar;
            dVar.A(true);
            androidx.appcompat.widget.d dVar2 = this.e;
            androidx.appcompat.view.menu.m.a bVar = this.f;
            if (bVar == null) {
                bVar = new b();
            }
            dVar2.setCallback(bVar);
            this.a.addMenuPresenter(this.e, this.b);
            this.e.y(this);
        }
        return this.a;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.e.r();
    }

    public int getPopupTheme() {
        return this.c;
    }

    public int getWindowAnimations() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public c generateDefaultLayoutParams() {
        c cVar = new c(-2, -2);
        cVar.b = 16;
        return cVar;
    }

    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.view.menu.n
    public void initialize(androidx.appcompat.view.menu.g gVar) {
        this.a = gVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public c generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        c cVar = layoutParams instanceof c ? new c((c) layoutParams) : new c(layoutParams);
        if (cVar.b <= 0) {
            cVar.b = 16;
        }
        return cVar;
    }

    public c k() {
        c cVarGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        cVarGenerateDefaultLayoutParams.c = true;
        return cVarGenerateDefaultLayoutParams;
    }

    protected boolean l(int i) {
        boolean zA = false;
        if (i == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i - 1);
        KeyEvent.Callback childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof a)) {
            zA = false | ((a) childAt).a();
        }
        return (i <= 0 || !(childAt2 instanceof a)) ? zA : zA | ((a) childAt2).b();
    }

    public boolean m() {
        androidx.appcompat.widget.d dVar = this.e;
        return dVar != null && dVar.s();
    }

    public boolean n() {
        androidx.appcompat.widget.d dVar = this.e;
        return dVar != null && dVar.u();
    }

    public boolean o() {
        androidx.appcompat.widget.d dVar = this.e;
        return dVar != null && dVar.v();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        androidx.appcompat.widget.d dVar = this.e;
        if (dVar != null) {
            dVar.updateMenuView(false);
            if (this.e.v()) {
                this.e.s();
                this.e.B();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        g();
    }

    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width;
        int paddingLeft;
        if (!this.h) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i5 = (i4 - i2) / 2;
        int dividerWidth = getDividerWidth();
        int i6 = i3 - i;
        int paddingRight = (i6 - getPaddingRight()) - getPaddingLeft();
        boolean zB = y2.b(this);
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                c cVar = (c) childAt.getLayoutParams();
                if (cVar.c) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (l(i9)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zB) {
                        paddingLeft = getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i10 = i5 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i10, width, measuredHeight + i10);
                    paddingRight -= measuredWidth;
                    i7 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin) + ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
                    l(i9);
                    i8++;
                }
            }
        }
        if (childCount == 1 && i7 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i11 = (i6 / 2) - (measuredWidth2 / 2);
            int i12 = i5 - (measuredHeight2 / 2);
            childAt2.layout(i11, i12, measuredWidth2 + i11, measuredHeight2 + i12);
            return;
        }
        int i13 = i8 - (i7 ^ 1);
        int iMax = Math.max(0, i13 > 0 ? paddingRight / i13 : 0);
        if (zB) {
            int width2 = getWidth() - getPaddingRight();
            for (int i14 = 0; i14 < childCount; i14++) {
                View childAt3 = getChildAt(i14);
                c cVar2 = (c) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !cVar2.c) {
                    int i15 = width2 - ((ViewGroup.MarginLayoutParams) cVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i16 = i5 - (measuredHeight3 / 2);
                    childAt3.layout(i15 - measuredWidth3, i16, i15, measuredHeight3 + i16);
                    width2 = i15 - ((measuredWidth3 + ((ViewGroup.MarginLayoutParams) cVar2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i17 = 0; i17 < childCount; i17++) {
            View childAt4 = getChildAt(i17);
            c cVar3 = (c) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !cVar3.c) {
                int i18 = paddingLeft2 + ((ViewGroup.MarginLayoutParams) cVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i19 = i5 - (measuredHeight4 / 2);
                childAt4.layout(i18, i19, i18 + measuredWidth4, measuredHeight4 + i19);
                paddingLeft2 = i18 + measuredWidth4 + ((ViewGroup.MarginLayoutParams) cVar3).rightMargin + iMax;
            }
        }
    }

    @Override // androidx.appcompat.widget.u1, android.view.View
    protected void onMeasure(int i, int i2) {
        androidx.appcompat.view.menu.g gVar;
        boolean z = this.h;
        boolean z2 = View.MeasureSpec.getMode(i) == 1073741824;
        this.h = z2;
        if (z != z2) {
            this.i = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.h && (gVar = this.a) != null && size != this.i) {
            this.i = size;
            gVar.onItemsChanged(true);
        }
        int childCount = getChildCount();
        if (this.h && childCount > 0) {
            r(i, i2);
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            c cVar = (c) getChildAt(i3).getLayoutParams();
            ((ViewGroup.MarginLayoutParams) cVar).rightMargin = 0;
            ((ViewGroup.MarginLayoutParams) cVar).leftMargin = 0;
        }
        super.onMeasure(i, i2);
    }

    public boolean p() {
        return this.d;
    }

    public androidx.appcompat.view.menu.g s() {
        return this.a;
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.e.x(z);
    }

    public void setOnMenuItemClickListener(e eVar) {
        this.l = eVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.e.z(drawable);
    }

    public void setOverflowReserved(boolean z) {
        this.d = z;
    }

    public void setPopupTheme(int i) {
        if (this.c != i) {
            this.c = i;
            if (i == 0) {
                this.b = getContext();
            } else {
                this.b = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setPresenter(androidx.appcompat.widget.d dVar) {
        this.e = dVar;
        dVar.y(this);
    }

    public void t(androidx.appcompat.view.menu.m.a aVar, androidx.appcompat.view.menu.g.a aVar2) {
        this.f = aVar;
        this.g = aVar2;
    }

    public boolean u() {
        androidx.appcompat.widget.d dVar = this.e;
        return dVar != null && dVar.B();
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.j = (int) (56.0f * f);
        this.k = (int) (f * 4.0f);
        this.b = context;
        this.c = 0;
    }
}
