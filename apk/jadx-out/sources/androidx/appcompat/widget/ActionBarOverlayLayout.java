package androidx.appcompat.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarOverlayLayout extends ViewGroup implements b1, androidx.core.view.x, androidx.core.view.y {
    static final int[] F = {R$attr.actionBarSize, R.attr.windowContentOverlay};
    ViewPropertyAnimator A;
    final AnimatorListenerAdapter B;
    private final Runnable C;
    private final Runnable D;
    private final androidx.core.view.z E;
    private int a;
    private int b;
    private ContentFrameLayout c;
    ActionBarContainer d;
    private c1 e;
    private Drawable f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    boolean k;
    private int l;
    private int m;
    private final Rect n;
    private final Rect o;
    private final Rect p;
    private final Rect q;
    private final Rect r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final Rect f13s;
    private final Rect t;
    private androidx.core.view.g1 u;
    private androidx.core.view.g1 v;
    private androidx.core.view.g1 w;
    private androidx.core.view.g1 x;
    private d y;
    private OverScroller z;

    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = null;
            actionBarOverlayLayout.k = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = null;
            actionBarOverlayLayout.k = false;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.o();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = actionBarOverlayLayout.d.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.B);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.o();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.A = actionBarOverlayLayout.d.animate().translationY(-ActionBarOverlayLayout.this.d.getHeight()).setListener(ActionBarOverlayLayout.this.B);
        }
    }

    public interface d {
        void a();

        void b(int i);

        void c();

        void d(boolean z);

        void e();

        void f();
    }

    public static class e extends ViewGroup.MarginLayoutParams {
        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(int i, int i2) {
            super(i, i2);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
        this.n = new Rect();
        this.o = new Rect();
        this.p = new Rect();
        this.q = new Rect();
        this.r = new Rect();
        this.f13s = new Rect();
        this.t = new Rect();
        androidx.core.view.g1 g1Var = androidx.core.view.g1.b;
        this.u = g1Var;
        this.v = g1Var;
        this.w = g1Var;
        this.x = g1Var;
        this.B = new a();
        this.C = new b();
        this.D = new c();
        p(context);
        this.E = new androidx.core.view.z(this);
    }

    private void j() {
        o();
        this.D.run();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    private boolean k(View view, Rect rect, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5;
        e eVar = (e) view.getLayoutParams();
        if (z) {
            int i = ((ViewGroup.MarginLayoutParams) eVar).leftMargin;
            int i2 = rect.left;
            if (i != i2) {
                ((ViewGroup.MarginLayoutParams) eVar).leftMargin = i2;
                z5 = true;
            } else {
                z5 = false;
            }
        } else {
            z5 = false;
        }
        if (z2) {
            int i3 = ((ViewGroup.MarginLayoutParams) eVar).topMargin;
            int i4 = rect.top;
            if (i3 != i4) {
                ((ViewGroup.MarginLayoutParams) eVar).topMargin = i4;
                z5 = true;
            }
        }
        if (z4) {
            int i5 = ((ViewGroup.MarginLayoutParams) eVar).rightMargin;
            int i6 = rect.right;
            if (i5 != i6) {
                ((ViewGroup.MarginLayoutParams) eVar).rightMargin = i6;
                z5 = true;
            }
        }
        if (z3) {
            int i7 = ((ViewGroup.MarginLayoutParams) eVar).bottomMargin;
            int i8 = rect.bottom;
            if (i7 != i8) {
                ((ViewGroup.MarginLayoutParams) eVar).bottomMargin = i8;
                return true;
            }
        }
        return z5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private c1 n(View view) {
        if (view instanceof c1) {
            return (c1) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    private void p(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(F);
        this.a = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.g = context.getApplicationInfo().targetSdkVersion < 19;
        this.z = new OverScroller(context);
    }

    private void r() {
        o();
        postDelayed(this.D, 600L);
    }

    private void s() {
        o();
        postDelayed(this.C, 600L);
    }

    private void u() {
        o();
        this.C.run();
    }

    private boolean v(float f) {
        this.z.fling(0, 0, 0, (int) f, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.z.getFinalY() > this.d.getHeight();
    }

    @Override // androidx.appcompat.widget.b1
    public boolean a() {
        t();
        return this.e.a();
    }

    @Override // androidx.appcompat.widget.b1
    public boolean b() {
        t();
        return this.e.b();
    }

    @Override // androidx.appcompat.widget.b1
    public boolean c() {
        t();
        return this.e.c();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // androidx.appcompat.widget.b1
    public void d(Menu menu, androidx.appcompat.view.menu.m.a aVar) {
        t();
        this.e.d(menu, aVar);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f == null || this.g) {
            return;
        }
        int bottom = this.d.getVisibility() == 0 ? (int) (this.d.getBottom() + this.d.getTranslationY() + 0.5f) : 0;
        this.f.setBounds(0, bottom, getWidth(), this.f.getIntrinsicHeight() + bottom);
        this.f.draw(canvas);
    }

    @Override // androidx.appcompat.widget.b1
    public boolean e() {
        t();
        return this.e.e();
    }

    @Override // androidx.appcompat.widget.b1
    public void f() {
        t();
        this.e.f();
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // androidx.appcompat.widget.b1
    public boolean g() {
        t();
        return this.e.g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.d;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.E.a();
    }

    public CharSequence getTitle() {
        t();
        return this.e.getTitle();
    }

    @Override // androidx.appcompat.widget.b1
    public void h(int i) {
        t();
        if (i == 2) {
            this.e.p();
        } else if (i == 5) {
            this.e.y();
        } else {
            if (i != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // androidx.appcompat.widget.b1
    public void i() {
        t();
        this.e.r();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public e generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    void o() {
        removeCallbacks(this.C);
        removeCallbacks(this.D);
        ViewPropertyAnimator viewPropertyAnimator = this.A;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        t();
        androidx.core.view.g1 g1VarV = androidx.core.view.g1.v(windowInsets);
        boolean zK = k(this.d, new Rect(g1VarV.i(), g1VarV.k(), g1VarV.j(), g1VarV.h()), true, true, false, true);
        androidx.core.view.m0.g(this, g1VarV, this.n);
        Rect rect = this.n;
        androidx.core.view.g1 g1VarM = g1VarV.m(rect.left, rect.top, rect.right, rect.bottom);
        this.u = g1VarM;
        boolean z = true;
        if (!this.v.equals(g1VarM)) {
            this.v = this.u;
            zK = true;
        }
        if (this.o.equals(this.n)) {
            z = zK;
        } else {
            this.o.set(this.n);
        }
        if (z) {
            requestLayout();
        }
        return g1VarV.a().c().b().u();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        p(getContext());
        androidx.core.view.m0.i0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        o();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredHeight;
        t();
        measureChildWithMargins(this.d, i, 0, i2, 0);
        e eVar = (e) this.d.getLayoutParams();
        int iMax = Math.max(0, this.d.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.d.getMeasuredState());
        boolean z = (androidx.core.view.m0.K(this) & 256) != 0;
        if (z) {
            measuredHeight = this.a;
            if (this.i && this.d.getTabContainer() != null) {
                measuredHeight += this.a;
            }
        } else {
            measuredHeight = this.d.getVisibility() != 8 ? this.d.getMeasuredHeight() : 0;
        }
        this.p.set(this.n);
        androidx.core.view.g1 g1Var = this.u;
        this.w = g1Var;
        if (this.h || z) {
            this.w = new androidx.core.view.g1.b(this.w).c(u.c.b(g1Var.i(), this.w.k() + measuredHeight, this.w.j(), this.w.h() + 0)).a();
        } else {
            Rect rect = this.p;
            rect.top += measuredHeight;
            rect.bottom += 0;
            this.w = g1Var.m(0, measuredHeight, 0, 0);
        }
        k(this.c, this.p, true, true, true, true);
        if (!this.x.equals(this.w)) {
            androidx.core.view.g1 g1Var2 = this.w;
            this.x = g1Var2;
            androidx.core.view.m0.h(this.c, g1Var2);
        }
        measureChildWithMargins(this.c, i, 0, i2, 0);
        e eVar2 = (e) this.c.getLayoutParams();
        int iMax3 = Math.max(iMax, this.c.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.c.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.c.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.j || !z) {
            return false;
        }
        if (v(f2)) {
            j();
        } else {
            u();
        }
        this.k = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // androidx.core.view.y
    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        onNestedScroll(view, i, i2, i3, i4, i5);
    }

    @Override // androidx.core.view.x
    public void onNestedScrollAccepted(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // androidx.core.view.x
    public boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    @Override // androidx.core.view.x
    public void onStopNestedScroll(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        t();
        int i2 = this.m ^ i;
        this.m = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & 256) != 0;
        d dVar = this.y;
        if (dVar != null) {
            dVar.d(!z2);
            if (z || !z2) {
                this.y.a();
            } else {
                this.y.e();
            }
        }
        if ((i2 & 256) == 0 || this.y == null) {
            return;
        }
        androidx.core.view.m0.i0(this);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.b = i;
        d dVar = this.y;
        if (dVar != null) {
            dVar.b(i);
        }
    }

    public boolean q() {
        return this.h;
    }

    public void setActionBarHideOffset(int i) {
        o();
        this.d.setTranslationY(-Math.max(0, Math.min(i, this.d.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.y = dVar;
        if (getWindowToken() != null) {
            this.y.b(this.b);
            int i = this.m;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                androidx.core.view.m0.i0(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.i = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.j) {
            this.j = z;
            if (z) {
                return;
            }
            o();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i) {
        t();
        this.e.setIcon(i);
    }

    public void setLogo(int i) {
        t();
        this.e.t(i);
    }

    public void setOverlayMode(boolean z) {
        this.h = z;
        this.g = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    @Override // androidx.appcompat.widget.b1
    public void setWindowCallback(Window.Callback callback) {
        t();
        this.e.setWindowCallback(callback);
    }

    @Override // androidx.appcompat.widget.b1
    public void setWindowTitle(CharSequence charSequence) {
        t();
        this.e.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    void t() {
        if (this.c == null) {
            this.c = (ContentFrameLayout) findViewById(R$id.action_bar_activity_content);
            this.d = (ActionBarContainer) findViewById(R$id.action_bar_container);
            this.e = n(findViewById(R$id.action_bar));
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    @Override // androidx.core.view.x
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    @Override // androidx.core.view.x
    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.E.b(view, view2, i);
        this.l = getActionBarHideOffset();
        o();
        d dVar = this.y;
        if (dVar != null) {
            dVar.f();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.d.getVisibility() != 0) {
            return false;
        }
        return this.j;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        if (this.j && !this.k) {
            if (this.l <= this.d.getHeight()) {
                s();
            } else {
                r();
            }
        }
        d dVar = this.y;
        if (dVar != null) {
            dVar.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.l + i2;
        this.l = i5;
        setActionBarHideOffset(i5);
    }

    public void setIcon(Drawable drawable) {
        t();
        this.e.setIcon(drawable);
    }
}
