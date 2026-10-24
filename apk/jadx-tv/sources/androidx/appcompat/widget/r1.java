package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R$attr;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
class r1 extends ListView {
    private final Rect a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private Field g;
    private a h;
    private boolean i;
    private boolean j;
    private boolean k;
    private androidx.core.view.a2 l;
    private androidx.core.widget.o m;
    b n;

    private static class a extends androidx.appcompat.graphics.drawable.m {
        private boolean a;

        a(Drawable drawable) {
            super(drawable);
            this.a = true;
        }

        void a(boolean z) {
            this.a = z;
        }

        @Override // androidx.appcompat.graphics.drawable.m, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.a) {
                super.draw(canvas);
            }
        }

        @Override // androidx.appcompat.graphics.drawable.m, android.graphics.drawable.Drawable
        public void setHotspot(float f, float f2) {
            if (this.a) {
                super.setHotspot(f, f2);
            }
        }

        @Override // androidx.appcompat.graphics.drawable.m, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i, int i2, int i3, int i4) {
            if (this.a) {
                super.setHotspotBounds(i, i2, i3, i4);
            }
        }

        @Override // androidx.appcompat.graphics.drawable.m, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.a) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // androidx.appcompat.graphics.drawable.m, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z, boolean z2) {
            if (this.a) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    private class b implements Runnable {
        b() {
        }

        public void a() {
            r1 r1Var = r1.this;
            r1Var.n = null;
            r1Var.removeCallbacks(this);
        }

        public void b() {
            r1.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            r1 r1Var = r1.this;
            r1Var.n = null;
            r1Var.drawableStateChanged();
        }
    }

    r1(Context context, boolean z) {
        super(context, null, R$attr.dropDownListViewStyle);
        this.a = new Rect();
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.j = z;
        setCacheColorHint(0);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.g = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void a() {
        this.k = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.f - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        androidx.core.view.a2 a2Var = this.l;
        if (a2Var != null) {
            a2Var.b();
            this.l = null;
        }
    }

    private void b(View view, int i) {
        performItemClick(view, i, getItemIdAtPosition(i));
    }

    private void c(Canvas canvas) {
        Drawable selector;
        if (this.a.isEmpty() || (selector = getSelector()) == null) {
            return;
        }
        selector.setBounds(this.a);
        selector.draw(canvas);
    }

    private void f(int i, View view) {
        Rect rect = this.a;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.b;
        rect.top -= this.c;
        rect.right += this.d;
        rect.bottom += this.e;
        try {
            boolean z = this.g.getBoolean(this);
            if (view.isEnabled() != z) {
                this.g.set(this, Boolean.valueOf(!z));
                if (i != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void g(int i, View view) {
        Drawable selector = getSelector();
        boolean z = (selector == null || i == -1) ? false : true;
        if (z) {
            selector.setVisible(false, false);
        }
        f(i, view);
        if (z) {
            Rect rect = this.a;
            float fExactCenterX = rect.exactCenterX();
            float fExactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            q.h.k(selector, fExactCenterX, fExactCenterY);
        }
    }

    private void h(int i, View view, float f, float f2) {
        g(i, view);
        Drawable selector = getSelector();
        if (selector == null || i == -1) {
            return;
        }
        q.h.k(selector, f, f2);
    }

    private void i(View view, int i, float f, float f2) {
        View childAt;
        this.k = true;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            drawableHotspotChanged(f, f2);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i3 = this.f;
        if (i3 != -1 && (childAt = getChildAt(i3 - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.f = i;
        float left = f - view.getLeft();
        float top = f2 - view.getTop();
        if (i2 >= 21) {
            view.drawableHotspotChanged(left, top);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        h(i, view, f, f2);
        j(false);
        refreshDrawableState();
    }

    private void j(boolean z) {
        a aVar = this.h;
        if (aVar != null) {
            aVar.a(z);
        }
    }

    private boolean k() {
        return this.k;
    }

    private void l() {
        Drawable selector = getSelector();
        if (selector != null && k() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    public int d(int i, int i2, int i3, int i4, int i5) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int measuredHeight = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        View view = null;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < count) {
            int itemViewType = adapter.getItemViewType(i6);
            if (itemViewType != i7) {
                view = null;
                i7 = itemViewType;
            }
            view = adapter.getView(i6, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i9 = layoutParams.height;
            view.measure(i, i9 > 0 ? View.MeasureSpec.makeMeasureSpec(i9, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i6 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i4) {
                return (i5 < 0 || i6 <= i5 || i8 <= 0 || measuredHeight == i4) ? i4 : i8;
            }
            if (i5 >= 0 && i6 >= i5) {
                i8 = measuredHeight;
            }
            i6++;
        }
        return measuredHeight;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        c(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.n != null) {
            return;
        }
        super.drawableStateChanged();
        j(true);
        l();
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004a  */
    /* JADX WARN: Code duplicated, block: B:25:0x004f  */
    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0065  */
    /* JADX WARN: Code duplicated, block: B:31:0x0069  */
    /* JADX WARN: Code duplicated, block: B:9:0x0011  */
    public boolean e(MotionEvent motionEvent, int i) {
        boolean z;
        boolean z2;
        androidx.core.widget.o oVar;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            z = false;
        } else {
            if (actionMasked != 2) {
                if (actionMasked != 3) {
                    z2 = false;
                    z = true;
                } else {
                    z2 = false;
                    z = false;
                }
                if (z || z2) {
                    a();
                }
                if (z) {
                    if (this.m == null) {
                        this.m = new androidx.core.widget.o(this);
                    }
                    this.m.m(true);
                    this.m.onTouch(this, motionEvent);
                } else {
                    oVar = this.m;
                    if (oVar != null) {
                        oVar.m(false);
                    }
                }
                return z;
            }
            z = true;
        }
        int iFindPointerIndex = motionEvent.findPointerIndex(i);
        if (iFindPointerIndex < 0) {
            z2 = false;
            z = false;
        } else {
            int x = (int) motionEvent.getX(iFindPointerIndex);
            int y = (int) motionEvent.getY(iFindPointerIndex);
            int iPointToPosition = pointToPosition(x, y);
            if (iPointToPosition == -1) {
                z2 = true;
            } else {
                View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
                i(childAt, iPointToPosition, x, y);
                if (actionMasked == 1) {
                    b(childAt, iPointToPosition);
                }
                z2 = false;
                z = true;
            }
        }
        if (z) {
            a();
        } else {
            a();
        }
        if (z) {
            if (this.m == null) {
                this.m = new androidx.core.widget.o(this);
            }
            this.m.m(true);
            this.m.onTouch(this, motionEvent);
        } else {
            oVar = this.m;
            if (oVar != null) {
                oVar.m(false);
            }
        }
        return z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.j || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.j || super.hasWindowFocus();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.j || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.j && this.i) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.n = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.n == null) {
            b bVar = new b();
            this.n = bVar;
            bVar.b();
        }
        boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (iPointToPosition != -1 && iPointToPosition != getSelectedItemPosition()) {
                View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(iPointToPosition, childAt.getTop() - getTop());
                }
                l();
            }
        } else {
            setSelection(-1);
        }
        return zOnHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        b bVar = this.n;
        if (bVar != null) {
            bVar.a();
        }
        return super.onTouchEvent(motionEvent);
    }

    void setListSelectionHidden(boolean z) {
        this.i = z;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        a aVar = drawable != null ? new a(drawable) : null;
        this.h = aVar;
        super.setSelector(aVar);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.b = rect.left;
        this.c = rect.top;
        this.d = rect.right;
        this.e = rect.bottom;
    }
}
