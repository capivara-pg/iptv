package androidx.appcompat.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class ActionBarContextView extends androidx.appcompat.widget.a {
    private CharSequence i;
    private CharSequence j;
    private View k;
    private View l;
    private LinearLayout m;
    private TextView n;
    private TextView o;
    private int p;
    private int q;
    private boolean r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f13s;

    class a implements View.OnClickListener {
        final /* synthetic */ f.b a;

        a(f.b bVar) {
            this.a = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.a();
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    private void i() {
        if (this.m == null) {
            LayoutInflater.from(getContext()).inflate(R$layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.m = linearLayout;
            this.n = (TextView) linearLayout.findViewById(R$id.action_bar_title);
            this.o = (TextView) this.m.findViewById(R$id.action_bar_subtitle);
            if (this.p != 0) {
                this.n.setTextAppearance(getContext(), this.p);
            }
            if (this.q != 0) {
                this.o.setTextAppearance(getContext(), this.q);
            }
        }
        this.n.setText(this.i);
        this.o.setText(this.j);
        boolean z = !TextUtils.isEmpty(this.i);
        boolean z2 = !TextUtils.isEmpty(this.j);
        int i = 0;
        this.o.setVisibility(z2 ? 0 : 8);
        LinearLayout linearLayout2 = this.m;
        if (!z && !z2) {
            i = 8;
        }
        linearLayout2.setVisibility(i);
        if (this.m.getParent() == null) {
            addView(this.m);
        }
    }

    @Override // androidx.appcompat.widget.a
    public /* bridge */ /* synthetic */ androidx.core.view.a2 f(int i, long j) {
        return super.f(i, j);
    }

    public void g() {
        if (this.k == null) {
            k();
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // androidx.appcompat.widget.a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.j;
    }

    public CharSequence getTitle() {
        return this.i;
    }

    public void h(f.b bVar) {
        View view = this.k;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.f13s, (ViewGroup) this, false);
            this.k = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.k);
        }
        this.k.findViewById(R$id.action_mode_close_button).setOnClickListener(new a(bVar));
        androidx.appcompat.view.menu.g gVar = (androidx.appcompat.view.menu.g) bVar.c();
        d dVar = this.d;
        if (dVar != null) {
            dVar.p();
        }
        d dVar2 = new d(getContext());
        this.d = dVar2;
        dVar2.A(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        gVar.addMenuPresenter(this.d, this.b);
        ActionMenuView actionMenuView = (ActionMenuView) this.d.g(this);
        this.c = actionMenuView;
        androidx.core.view.c1.n0(actionMenuView, null);
        addView(this.c, layoutParams);
    }

    public boolean j() {
        return this.r;
    }

    public void k() {
        removeAllViews();
        this.l = null;
        this.c = null;
    }

    public boolean l() {
        d dVar = this.d;
        if (dVar != null) {
            return dVar.B();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d dVar = this.d;
        if (dVar != null) {
            dVar.s();
            this.d.t();
        }
    }

    @Override // androidx.appcompat.widget.a, android.view.View
    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() != 32) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            return;
        }
        accessibilityEvent.setSource(this);
        accessibilityEvent.setClassName(getClass().getName());
        accessibilityEvent.setPackageName(getContext().getPackageName());
        accessibilityEvent.setContentDescription(this.i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean zB = y2.b(this);
        int paddingRight = zB ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.k;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.k.getLayoutParams();
            int i5 = zB ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i6 = zB ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int iD = androidx.appcompat.widget.a.d(paddingRight, i5, zB);
            paddingRight = androidx.appcompat.widget.a.d(iD + e(this.k, iD, paddingTop, paddingTop2, zB), i6, zB);
        }
        int iE = paddingRight;
        LinearLayout linearLayout = this.m;
        if (linearLayout != null && this.l == null && linearLayout.getVisibility() != 8) {
            iE += e(this.m, iE, paddingTop, paddingTop2, zB);
        }
        int i7 = iE;
        View view2 = this.l;
        if (view2 != null) {
            e(view2, i7, paddingTop, paddingTop2, zB);
        }
        int paddingLeft = zB ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        ActionMenuView actionMenuView = this.c;
        if (actionMenuView != null) {
            e(actionMenuView, paddingLeft, paddingTop, paddingTop2, !zB);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View.MeasureSpec.getMode(i2) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
        int size = View.MeasureSpec.getSize(i);
        int size2 = this.e;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i2);
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingTop;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.k;
        if (view != null) {
            int iC = c(view, paddingLeft, iMakeMeasureSpec, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.k.getLayoutParams();
            paddingLeft = iC - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.c;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = c(this.c, paddingLeft, iMakeMeasureSpec, 0);
        }
        LinearLayout linearLayout = this.m;
        if (linearLayout != null && this.l == null) {
            if (this.r) {
                this.m.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.m.getMeasuredWidth();
                boolean z = measuredWidth <= paddingLeft;
                if (z) {
                    paddingLeft -= measuredWidth;
                }
                this.m.setVisibility(z ? 0 : 8);
            } else {
                paddingLeft = c(linearLayout, paddingLeft, iMakeMeasureSpec, 0);
            }
        }
        View view2 = this.l;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i3 = layoutParams.width;
            int i4 = i3 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i3 >= 0) {
                paddingLeft = Math.min(i3, paddingLeft);
            }
            int i5 = layoutParams.height;
            int i6 = i5 == -2 ? Integer.MIN_VALUE : 1073741824;
            if (i5 >= 0) {
                iMin = Math.min(i5, iMin);
            }
            this.l.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i4), View.MeasureSpec.makeMeasureSpec(iMin, i6));
        }
        if (this.e > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            int measuredHeight = getChildAt(i8).getMeasuredHeight() + paddingTop;
            if (measuredHeight > i7) {
                i7 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i7);
    }

    @Override // androidx.appcompat.widget.a, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.appcompat.widget.a
    public void setContentHeight(int i) {
        this.e = i;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.l;
        if (view2 != null) {
            removeView(view2);
        }
        this.l = view;
        if (view != null && (linearLayout = this.m) != null) {
            removeView(linearLayout);
            this.m = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.j = charSequence;
        i();
    }

    public void setTitle(CharSequence charSequence) {
        this.i = charSequence;
        i();
    }

    public void setTitleOptional(boolean z) {
        if (z != this.r) {
            requestLayout();
        }
        this.r = z;
    }

    @Override // androidx.appcompat.widget.a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        r2 r2VarU = r2.u(context, attributeSet, R$styleable.e, i, 0);
        androidx.core.view.c1.n0(this, r2VarU.g(R$styleable.ActionMode_background));
        this.p = r2VarU.n(R$styleable.ActionMode_titleTextStyle, 0);
        this.q = r2VarU.n(R$styleable.ActionMode_subtitleTextStyle, 0);
        this.e = r2VarU.m(R$styleable.ActionMode_height, 0);
        this.f13s = r2VarU.n(R$styleable.ActionMode_closeItemLayout, R$layout.abc_action_mode_close_item_material);
        r2VarU.v();
    }
}
