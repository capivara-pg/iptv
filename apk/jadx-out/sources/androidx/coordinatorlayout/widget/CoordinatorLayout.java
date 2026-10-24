package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.R$attr;
import androidx.coordinatorlayout.R$style;
import androidx.coordinatorlayout.R$styleable;
import androidx.core.view.a0;
import androidx.core.view.g1;
import androidx.core.view.j;
import androidx.core.view.m0;
import androidx.core.view.x;
import androidx.core.view.y;
import androidx.core.view.z;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements x, y {
    static final Class<?>[] CONSTRUCTOR_PARAMS;
    static final int EVENT_NESTED_SCROLL = 1;
    static final int EVENT_PRE_DRAW = 0;
    static final int EVENT_VIEW_REMOVED = 2;
    static final String TAG = "CoordinatorLayout";
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal<Map<String, Constructor<c>>> sConstructors;
    private static final c0.e sRectPool;
    private a0 mApplyWindowInsetsListener;
    private final int[] mBehaviorConsumed;
    private View mBehaviorTouchView;
    private final androidx.coordinatorlayout.widget.b mChildDag;
    private final List<View> mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private g1 mLastInsets;
    private boolean mNeedsPreDrawListener;
    private final z mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    private final int[] mNestedScrollingV2ConsumedCompat;
    ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    private g mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempDependenciesList;
    private final List<View> mTempList1;

    class a implements a0 {
        a() {
        }

        @Override // androidx.core.view.a0
        public g1 onApplyWindowInsets(View view, g1 g1Var) {
            return CoordinatorLayout.this.setWindowInsets(g1Var);
        }
    }

    public interface b {
        c getBehavior();
    }

    public static abstract class c {
        public c() {
        }

        public static Object getTag(View view) {
            return ((f) view.getLayoutParams()).r;
        }

        public static void setTag(View view, Object obj) {
            ((f) view.getLayoutParams()).r = obj;
        }

        public boolean blocksInteractionBelow(CoordinatorLayout coordinatorLayout, View view) {
            return getScrimOpacity(coordinatorLayout, view) > 0.0f;
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
            return false;
        }

        public int getScrimColor(CoordinatorLayout coordinatorLayout, View view) {
            return -16777216;
        }

        public float getScrimOpacity(CoordinatorLayout coordinatorLayout, View view) {
            return 0.0f;
        }

        public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return false;
        }

        public g1 onApplyWindowInsets(CoordinatorLayout coordinatorLayout, View view, g1 g1Var) {
            return g1Var;
        }

        public void onAttachedToLayoutParams(f fVar) {
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return false;
        }

        public void onDependentViewRemoved(CoordinatorLayout coordinatorLayout, View view, View view2) {
        }

        public void onDetachedFromLayoutParams() {
        }

        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return false;
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            return false;
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            return false;
        }

        public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View view, View view2, float f, float f2, boolean z) {
            return false;
        }

        public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View view, View view2, float f, float f2) {
            return false;
        }

        @Deprecated
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr) {
        }

        @Deprecated
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4) {
        }

        @Deprecated
        public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i) {
        }

        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            return false;
        }

        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i) {
            return false;
        }

        @Deprecated
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2) {
        }

        public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return false;
        }

        public c(Context context, AttributeSet attributeSet) {
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
            if (i3 == 0) {
                onNestedPreScroll(coordinatorLayout, view, view2, i, i2, iArr);
            }
        }

        @Deprecated
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4, int i5) {
            if (i5 == 0) {
                onNestedScroll(coordinatorLayout, view, view2, i, i2, i3, i4);
            }
        }

        public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            if (i2 == 0) {
                onNestedScrollAccepted(coordinatorLayout, view, view2, view3, i);
            }
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            if (i2 == 0) {
                return onStartNestedScroll(coordinatorLayout, view, view2, view3, i);
            }
            return false;
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
            if (i == 0) {
                onStopNestedScroll(coordinatorLayout, view, view2);
            }
        }

        public void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4, int i5, int[] iArr) {
            iArr[0] = iArr[0] + i3;
            iArr[1] = iArr[1] + i4;
            onNestedScroll(coordinatorLayout, view, view2, i, i2, i3, i4, i5);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface d {
        Class value();
    }

    private class e implements ViewGroup.OnHierarchyChangeListener {
        e() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.mOnHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.onChildViewsChanged(2);
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.mOnHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    class g implements ViewTreeObserver.OnPreDrawListener {
        g() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            CoordinatorLayout.this.onChildViewsChanged(0);
            return true;
        }
    }

    static class i implements Comparator {
        i() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            float fL = m0.L(view);
            float fL2 = m0.L(view2);
            if (fL > fL2) {
                return -1;
            }
            return fL < fL2 ? 1 : 0;
        }
    }

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        WIDGET_PACKAGE_NAME = r0 != null ? r0.getName() : null;
        TOP_SORTED_CHILDREN_COMPARATOR = new i();
        CONSTRUCTOR_PARAMS = new Class[]{Context.class, AttributeSet.class};
        sConstructors = new ThreadLocal<>();
        sRectPool = new c0.g(12);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.coordinatorLayoutStyle);
    }

    private static Rect a() {
        Rect rect = (Rect) sRectPool.acquire();
        return rect == null ? new Rect() : rect;
    }

    private static int b(int i2, int i3, int i4) {
        if (i2 < i3) {
            return i3;
        }
        return i2 > i4 ? i4 : i2;
    }

    private void c(f fVar, Rect rect, int i2, int i3) {
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i2) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i3) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin));
        rect.set(iMax, iMax2, i2 + iMax, i3 + iMax2);
    }

    private g1 d(g1 g1Var) {
        c cVarF;
        if (g1Var.o()) {
            return g1Var;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (m0.x(childAt) && (cVarF = ((f) childAt.getLayoutParams()).f()) != null) {
                g1Var = cVarF.onApplyWindowInsets(this, childAt, g1Var);
                if (g1Var.o()) {
                    break;
                }
            }
        }
        return g1Var;
    }

    private void e(View view, int i2, Rect rect, Rect rect2, f fVar, int i3, int i4) {
        int iWidth;
        int iHeight;
        int iB = j.b(q(fVar.c), i2);
        int iB2 = j.b(r(fVar.d), i2);
        int i5 = iB & 7;
        int i6 = iB & 112;
        int i7 = iB2 & 7;
        int i8 = iB2 & 112;
        if (i7 != 1) {
            iWidth = i7 != 5 ? rect.left : rect.right;
        } else {
            iWidth = rect.left + (rect.width() / 2);
        }
        if (i8 != 16) {
            iHeight = i8 != 80 ? rect.top : rect.bottom;
        } else {
            iHeight = rect.top + (rect.height() / 2);
        }
        if (i5 == 1) {
            iWidth -= i3 / 2;
        } else if (i5 != 5) {
            iWidth -= i3;
        }
        if (i6 == 16) {
            iHeight -= i4 / 2;
        } else if (i6 != 80) {
            iHeight -= i4;
        }
        rect2.set(iWidth, iHeight, i3 + iWidth, i4 + iHeight);
    }

    private int f(int i2) {
        int[] iArr = this.mKeylines;
        if (iArr == null) {
            Log.e(TAG, "No keylines defined for " + this + " - attempted index lookup " + i2);
            return 0;
        }
        if (i2 >= 0 && i2 < iArr.length) {
            return iArr[i2];
        }
        Log.e(TAG, "Keyline index " + i2 + " out of range for " + this);
        return 0;
    }

    private void g(List list) {
        list.clear();
        boolean zIsChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            list.add(getChildAt(zIsChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i2) : i2));
        }
        Comparator<View> comparator = TOP_SORTED_CHILDREN_COMPARATOR;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
    }

    private boolean h(View view) {
        return this.mChildDag.j(view);
    }

    private void i(View view, int i2) {
        f fVar = (f) view.getLayoutParams();
        Rect rectA = a();
        rectA.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin);
        if (this.mLastInsets != null && m0.x(this) && !m0.x(view)) {
            rectA.left += this.mLastInsets.i();
            rectA.top += this.mLastInsets.k();
            rectA.right -= this.mLastInsets.j();
            rectA.bottom -= this.mLastInsets.h();
        }
        Rect rectA2 = a();
        j.a(r(fVar.c), view.getMeasuredWidth(), view.getMeasuredHeight(), rectA, rectA2, i2);
        view.layout(rectA2.left, rectA2.top, rectA2.right, rectA2.bottom);
        o(rectA);
        o(rectA2);
    }

    private void j(View view, View view2, int i2) {
        Rect rectA = a();
        Rect rectA2 = a();
        try {
            getDescendantRect(view2, rectA);
            getDesiredAnchoredChildRect(view, i2, rectA, rectA2);
            view.layout(rectA2.left, rectA2.top, rectA2.right, rectA2.bottom);
        } finally {
            o(rectA);
            o(rectA2);
        }
    }

    private void k(View view, int i2, int i3) {
        f fVar = (f) view.getLayoutParams();
        int iB = j.b(s(fVar.c), i3);
        int i4 = iB & 7;
        int i5 = iB & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (i3 == 1) {
            i2 = width - i2;
        }
        int iF = f(i2) - measuredWidth;
        if (i4 == 1) {
            iF += measuredWidth / 2;
        } else if (i4 == 5) {
            iF += measuredWidth;
        }
        int i6 = 0;
        if (i5 == 16) {
            i6 = 0 + (measuredHeight / 2);
        } else if (i5 == 80) {
            i6 = measuredHeight + 0;
        }
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) fVar).leftMargin, Math.min(iF, ((width - getPaddingRight()) - measuredWidth) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) fVar).topMargin, Math.min(i6, ((height - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin));
        view.layout(iMax, iMax2, measuredWidth + iMax, measuredHeight + iMax2);
    }

    private void l(View view, Rect rect, int i2) {
        boolean z;
        boolean z2;
        int width;
        int i3;
        int i4;
        int i5;
        int height;
        int i6;
        int i7;
        int i8;
        if (m0.R(view) && view.getWidth() > 0 && view.getHeight() > 0) {
            f fVar = (f) view.getLayoutParams();
            c cVarF = fVar.f();
            Rect rectA = a();
            Rect rectA2 = a();
            rectA2.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            if (cVarF == null || !cVarF.getInsetDodgeRect(this, view, rectA)) {
                rectA.set(rectA2);
            } else if (!rectA2.contains(rectA)) {
                throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + rectA.toShortString() + " | Bounds:" + rectA2.toShortString());
            }
            o(rectA2);
            if (rectA.isEmpty()) {
                o(rectA);
                return;
            }
            int iB = j.b(fVar.h, i2);
            boolean z3 = true;
            if ((iB & 48) != 48 || (i7 = (rectA.top - ((ViewGroup.MarginLayoutParams) fVar).topMargin) - fVar.j) >= (i8 = rect.top)) {
                z = false;
            } else {
                u(view, i8 - i7);
                z = true;
            }
            if ((iB & 80) == 80 && (height = ((getHeight() - rectA.bottom) - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin) + fVar.j) < (i6 = rect.bottom)) {
                u(view, height - i6);
                z = true;
            }
            if (!z) {
                u(view, 0);
            }
            if ((iB & 3) != 3 || (i4 = (rectA.left - ((ViewGroup.MarginLayoutParams) fVar).leftMargin) - fVar.i) >= (i5 = rect.left)) {
                z2 = false;
            } else {
                t(view, i5 - i4);
                z2 = true;
            }
            if ((iB & 5) != 5 || (width = ((getWidth() - rectA.right) - ((ViewGroup.MarginLayoutParams) fVar).rightMargin) + fVar.i) >= (i3 = rect.right)) {
                z3 = z2;
            } else {
                t(view, width - i3);
            }
            if (!z3) {
                t(view, 0);
            }
            o(rectA);
        }
    }

    private boolean m(MotionEvent motionEvent, int i2) {
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.mTempList1;
        g(list);
        int size = list.size();
        MotionEvent motionEventObtain = null;
        boolean zOnInterceptTouchEvent = false;
        boolean z = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view = list.get(i3);
            f fVar = (f) view.getLayoutParams();
            c cVarF = fVar.f();
            if (!(zOnInterceptTouchEvent || z) || actionMasked == 0) {
                if (!zOnInterceptTouchEvent && cVarF != null) {
                    if (i2 == 0) {
                        zOnInterceptTouchEvent = cVarF.onInterceptTouchEvent(this, view, motionEvent);
                    } else if (i2 == 1) {
                        zOnInterceptTouchEvent = cVarF.onTouchEvent(this, view, motionEvent);
                    }
                    if (zOnInterceptTouchEvent) {
                        this.mBehaviorTouchView = view;
                    }
                }
                boolean zC = fVar.c();
                boolean zI = fVar.i(this, view);
                z = zI && !zC;
                if (zI && !z) {
                    break;
                }
            } else if (cVarF != null) {
                if (motionEventObtain == null) {
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                if (i2 == 0) {
                    cVarF.onInterceptTouchEvent(this, view, motionEventObtain);
                } else if (i2 == 1) {
                    cVarF.onTouchEvent(this, view, motionEventObtain);
                }
            }
        }
        list.clear();
        return zOnInterceptTouchEvent;
    }

    private void n() {
        this.mDependencySortedChildren.clear();
        this.mChildDag.c();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            f resolvedLayoutParams = getResolvedLayoutParams(childAt);
            resolvedLayoutParams.d(this, childAt);
            this.mChildDag.b(childAt);
            for (int i3 = 0; i3 < childCount; i3++) {
                if (i3 != i2) {
                    View childAt2 = getChildAt(i3);
                    if (resolvedLayoutParams.b(this, childAt, childAt2)) {
                        if (!this.mChildDag.d(childAt2)) {
                            this.mChildDag.b(childAt2);
                        }
                        this.mChildDag.a(childAt2, childAt);
                    }
                }
            }
        }
        this.mDependencySortedChildren.addAll(this.mChildDag.i());
        Collections.reverse(this.mDependencySortedChildren);
    }

    private static void o(Rect rect) {
        rect.setEmpty();
        sRectPool.release(rect);
    }

    private void p(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            c cVarF = ((f) childAt.getLayoutParams()).f();
            if (cVarF != null) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z) {
                    cVarF.onInterceptTouchEvent(this, childAt, motionEventObtain);
                } else {
                    cVarF.onTouchEvent(this, childAt, motionEventObtain);
                }
                motionEventObtain.recycle();
            }
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            ((f) getChildAt(i3).getLayoutParams()).m();
        }
        this.mBehaviorTouchView = null;
        this.mDisallowInterceptReset = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static c parseBehavior(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0) {
            String str2 = WIDGET_PACKAGE_NAME;
            if (!TextUtils.isEmpty(str2)) {
                str = str2 + '.' + str;
            }
        }
        try {
            ThreadLocal<Map<String, Constructor<c>>> threadLocal = sConstructors;
            Map<String, Constructor<c>> map = threadLocal.get();
            if (map == null) {
                map = new HashMap<>();
                threadLocal.set(map);
            }
            Constructor<c> constructor = map.get(str);
            if (constructor == null) {
                constructor = Class.forName(str, false, context.getClassLoader()).getConstructor(CONSTRUCTOR_PARAMS);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return constructor.newInstance(context, attributeSet);
        } catch (Exception e2) {
            throw new RuntimeException("Could not inflate Behavior subclass " + str, e2);
        }
    }

    private static int q(int i2) {
        if (i2 == 0) {
            return 17;
        }
        return i2;
    }

    private static int r(int i2) {
        if ((i2 & 7) == 0) {
            i2 |= 8388611;
        }
        return (i2 & 112) == 0 ? i2 | 48 : i2;
    }

    private static int s(int i2) {
        if (i2 == 0) {
            return 8388661;
        }
        return i2;
    }

    private void t(View view, int i2) {
        f fVar = (f) view.getLayoutParams();
        int i3 = fVar.i;
        if (i3 != i2) {
            m0.W(view, i2 - i3);
            fVar.i = i2;
        }
    }

    private void u(View view, int i2) {
        f fVar = (f) view.getLayoutParams();
        int i3 = fVar.j;
        if (i3 != i2) {
            m0.X(view, i2 - i3);
            fVar.j = i2;
        }
    }

    private void v() {
        if (!m0.x(this)) {
            m0.y0(this, null);
            return;
        }
        if (this.mApplyWindowInsetsListener == null) {
            this.mApplyWindowInsetsListener = new a();
        }
        m0.y0(this, this.mApplyWindowInsetsListener);
        setSystemUiVisibility(1280);
    }

    void addPreDrawListener() {
        if (this.mIsAttachedToWindow) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = true;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof f) && super.checkLayoutParams(layoutParams);
    }

    public void dispatchDependentViewsChanged(View view) {
        List listG = this.mChildDag.g(view);
        if (listG == null || listG.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < listG.size(); i2++) {
            View view2 = (View) listG.get(i2);
            c cVarF = ((f) view2.getLayoutParams()).f();
            if (cVarF != null) {
                cVarF.onDependentViewChanged(this, view2, view);
            }
        }
    }

    public boolean doViewsOverlap(View view, View view2) {
        boolean z = false;
        if (view.getVisibility() != 0 || view2.getVisibility() != 0) {
            return false;
        }
        Rect rectA = a();
        getChildRect(view, view.getParent() != this, rectA);
        Rect rectA2 = a();
        getChildRect(view2, view2.getParent() != this, rectA2);
        try {
            if (rectA.left <= rectA2.right && rectA.top <= rectA2.bottom && rectA.right >= rectA2.left && rectA.bottom >= rectA2.top) {
                z = true;
            }
            return z;
        } finally {
            o(rectA);
            o(rectA2);
        }
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        f fVar = (f) view.getLayoutParams();
        c cVar = fVar.a;
        if (cVar != null) {
            float scrimOpacity = cVar.getScrimOpacity(this, view);
            if (scrimOpacity > 0.0f) {
                if (this.mScrimPaint == null) {
                    this.mScrimPaint = new Paint();
                }
                this.mScrimPaint.setColor(fVar.a.getScrimColor(this, view));
                this.mScrimPaint.setAlpha(b(Math.round(scrimOpacity * 255.0f), 0, 255));
                int iSave = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.mScrimPaint);
                canvas.restoreToCount(iSave);
            }
        }
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mStatusBarBackground;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    void ensurePreDrawListener() {
        int childCount = getChildCount();
        boolean z = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            if (h(getChildAt(i2))) {
                z = true;
                break;
            }
        }
        if (z != this.mNeedsPreDrawListener) {
            if (z) {
                addPreDrawListener();
            } else {
                removePreDrawListener();
            }
        }
    }

    void getChildRect(View view, boolean z, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z) {
            getDescendantRect(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public List<View> getDependencies(View view) {
        List listH = this.mChildDag.h(view);
        this.mTempDependenciesList.clear();
        if (listH != null) {
            this.mTempDependenciesList.addAll(listH);
        }
        return this.mTempDependenciesList;
    }

    final List<View> getDependencySortedChildren() {
        n();
        return Collections.unmodifiableList(this.mDependencySortedChildren);
    }

    public List<View> getDependents(View view) {
        List listG = this.mChildDag.g(view);
        this.mTempDependenciesList.clear();
        if (listG != null) {
            this.mTempDependenciesList.addAll(listG);
        }
        return this.mTempDependenciesList;
    }

    void getDescendantRect(View view, Rect rect) {
        androidx.coordinatorlayout.widget.c.a(this, view, rect);
    }

    void getDesiredAnchoredChildRect(View view, int i2, Rect rect, Rect rect2) {
        f fVar = (f) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        e(view, i2, rect, rect2, fVar, measuredWidth, measuredHeight);
        c(fVar, rect2, measuredWidth, measuredHeight);
    }

    void getLastChildRect(View view, Rect rect) {
        rect.set(((f) view.getLayoutParams()).h());
    }

    public final g1 getLastWindowInsets() {
        return this.mLastInsets;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    f getResolvedLayoutParams(View view) {
        f fVar = (f) view.getLayoutParams();
        if (!fVar.b) {
            if (view instanceof b) {
                c behavior = ((b) view).getBehavior();
                if (behavior == null) {
                    Log.e(TAG, "Attached behavior class is null");
                }
                fVar.o(behavior);
                fVar.b = true;
            } else {
                d dVar = null;
                for (Class<?> superclass = view.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                    dVar = (d) superclass.getAnnotation(d.class);
                    if (dVar != null) {
                        break;
                    }
                }
                if (dVar != null) {
                    try {
                        fVar.o((c) dVar.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception e2) {
                        Log.e(TAG, "Default behavior class " + dVar.value().getName() + " could not be instantiated. Did you forget a default constructor?", e2);
                    }
                }
                fVar.b = true;
            }
        }
        return fVar;
    }

    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    public boolean isPointInChildBounds(View view, int i2, int i3) {
        Rect rectA = a();
        getDescendantRect(view, rectA);
        try {
            return rectA.contains(i2, i3);
        } finally {
            o(rectA);
        }
    }

    void offsetChildToAnchor(View view, int i2) {
        c cVarF;
        f fVar = (f) view.getLayoutParams();
        if (fVar.k != null) {
            Rect rectA = a();
            Rect rectA2 = a();
            Rect rectA3 = a();
            getDescendantRect(fVar.k, rectA);
            getChildRect(view, false, rectA2);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            e(view, i2, rectA, rectA3, fVar, measuredWidth, measuredHeight);
            boolean z = (rectA3.left == rectA2.left && rectA3.top == rectA2.top) ? false : true;
            c(fVar, rectA3, measuredWidth, measuredHeight);
            int i3 = rectA3.left - rectA2.left;
            int i4 = rectA3.top - rectA2.top;
            if (i3 != 0) {
                m0.W(view, i3);
            }
            if (i4 != 0) {
                m0.X(view, i4);
            }
            if (z && (cVarF = fVar.f()) != null) {
                cVarF.onDependentViewChanged(this, view, fVar.k);
            }
            o(rectA);
            o(rectA2);
            o(rectA3);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        p(false);
        if (this.mNeedsPreDrawListener) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new g();
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        if (this.mLastInsets == null && m0.x(this)) {
            m0.i0(this);
        }
        this.mIsAttachedToWindow = true;
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00ca  */
    final void onChildViewsChanged(int i2) {
        int i3;
        c cVarF;
        boolean zOnDependentViewChanged;
        int iA = m0.A(this);
        int size = this.mDependencySortedChildren.size();
        Rect rectA = a();
        Rect rectA2 = a();
        Rect rectA3 = a();
        for (int i4 = 0; i4 < size; i4++) {
            View view = this.mDependencySortedChildren.get(i4);
            f fVar = (f) view.getLayoutParams();
            if (i2 != 0 || view.getVisibility() != 8) {
                for (int i5 = 0; i5 < i4; i5++) {
                    if (fVar.l == this.mDependencySortedChildren.get(i5)) {
                        offsetChildToAnchor(view, iA);
                    }
                }
                getChildRect(view, true, rectA2);
                if (fVar.g != 0 && !rectA2.isEmpty()) {
                    int iB = j.b(fVar.g, iA);
                    int i6 = iB & 112;
                    if (i6 == 48) {
                        rectA.top = Math.max(rectA.top, rectA2.bottom);
                    } else if (i6 == 80) {
                        rectA.bottom = Math.max(rectA.bottom, getHeight() - rectA2.top);
                    }
                    int i7 = iB & 7;
                    if (i7 == 3) {
                        rectA.left = Math.max(rectA.left, rectA2.right);
                    } else if (i7 == 5) {
                        rectA.right = Math.max(rectA.right, getWidth() - rectA2.left);
                    }
                }
                if (fVar.h != 0 && view.getVisibility() == 0) {
                    l(view, rectA, iA);
                }
                if (i2 != 2) {
                    getLastChildRect(view, rectA3);
                    if (!rectA3.equals(rectA2)) {
                        recordLastChildRect(view, rectA2);
                        for (i3 = i4 + 1; i3 < size; i3++) {
                            View view2 = this.mDependencySortedChildren.get(i3);
                            f fVar2 = (f) view2.getLayoutParams();
                            cVarF = fVar2.f();
                            if (cVarF == null && cVarF.layoutDependsOn(this, view2, view)) {
                                if (i2 == 0 && fVar2.g()) {
                                    fVar2.k();
                                } else {
                                    if (i2 != 2) {
                                        zOnDependentViewChanged = cVarF.onDependentViewChanged(this, view2, view);
                                    } else {
                                        cVarF.onDependentViewRemoved(this, view2, view);
                                        zOnDependentViewChanged = true;
                                    }
                                    if (i2 == 1) {
                                        fVar2.p(zOnDependentViewChanged);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    while (i3 < size) {
                        View view3 = this.mDependencySortedChildren.get(i3);
                        f fVar3 = (f) view3.getLayoutParams();
                        cVarF = fVar3.f();
                        if (cVarF == null) {
                        }
                    }
                }
            }
        }
        o(rectA);
        o(rectA2);
        o(rectA3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        p(false);
        if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        View view = this.mNestedScrollingTarget;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.mIsAttachedToWindow = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mDrawStatusBarBackground || this.mStatusBarBackground == null) {
            return;
        }
        g1 g1Var = this.mLastInsets;
        int iK = g1Var != null ? g1Var.k() : 0;
        if (iK > 0) {
            this.mStatusBarBackground.setBounds(0, 0, getWidth(), iK);
            this.mStatusBarBackground.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            p(true);
        }
        boolean zM = m(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            p(true);
        }
        return zM;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        c cVarF;
        int iA = m0.A(this);
        int size = this.mDependencySortedChildren.size();
        for (int i6 = 0; i6 < size; i6++) {
            View view = this.mDependencySortedChildren.get(i6);
            if (view.getVisibility() != 8 && ((cVarF = ((f) view.getLayoutParams()).f()) == null || !cVarF.onLayoutChild(this, view, iA))) {
                onLayoutChild(view, iA);
            }
        }
    }

    public void onLayoutChild(View view, int i2) {
        f fVar = (f) view.getLayoutParams();
        if (fVar.a()) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        View view2 = fVar.k;
        if (view2 != null) {
            j(view, view2, i2);
            return;
        }
        int i3 = fVar.e;
        if (i3 >= 0) {
            k(view, i3, i2);
        } else {
            i(view, i2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:39:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:42:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:45:0x011d  */
    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int iMax;
        int iMakeMeasureSpec;
        int iMakeMeasureSpec2;
        c cVarF;
        f fVar;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        n();
        ensurePreDrawListener();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int iA = m0.A(this);
        boolean z = iA == 1;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        int i11 = paddingLeft + paddingRight;
        int i12 = paddingTop + paddingBottom;
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        boolean z2 = this.mLastInsets != null && m0.x(this);
        int size3 = this.mDependencySortedChildren.size();
        int i13 = suggestedMinimumWidth;
        int i14 = suggestedMinimumHeight;
        int iCombineMeasuredStates = 0;
        int i15 = 0;
        while (i15 < size3) {
            View view = this.mDependencySortedChildren.get(i15);
            if (view.getVisibility() == 8) {
                i9 = i15;
                i6 = size3;
                i7 = paddingLeft;
            } else {
                f fVar2 = (f) view.getLayoutParams();
                int i16 = fVar2.e;
                if (i16 < 0 || mode == 0) {
                    i4 = iCombineMeasuredStates;
                } else {
                    int iF = f(i16);
                    int iB = j.b(s(fVar2.c), iA) & 7;
                    i4 = iCombineMeasuredStates;
                    if ((iB == 3 && !z) || (iB == 5 && z)) {
                        iMax = Math.max(0, (size - paddingRight) - iF);
                    } else if ((iB == 5 && !z) || (iB == 3 && z)) {
                        iMax = Math.max(0, iF - paddingLeft);
                    }
                    if (z2 || m0.x(view)) {
                        iMakeMeasureSpec = i2;
                        iMakeMeasureSpec2 = i3;
                    } else {
                        int i17 = this.mLastInsets.i() + this.mLastInsets.j();
                        int iK = this.mLastInsets.k() + this.mLastInsets.h();
                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size - i17, mode);
                        iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size2 - iK, mode2);
                    }
                    cVarF = fVar2.f();
                    if (cVarF != null) {
                        fVar = fVar2;
                        i8 = i4;
                        i9 = i15;
                        i5 = i14;
                        i7 = paddingLeft;
                        i10 = i13;
                        i6 = size3;
                        if (!cVarF.onMeasureChild(this, view, iMakeMeasureSpec, iMax, iMakeMeasureSpec2, 0)) {
                        }
                        f fVar3 = fVar;
                        int iMax2 = Math.max(i10, i11 + view.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) fVar3).leftMargin + ((ViewGroup.MarginLayoutParams) fVar3).rightMargin);
                        int iMax3 = Math.max(i5, i12 + view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) fVar3).topMargin + ((ViewGroup.MarginLayoutParams) fVar3).bottomMargin);
                        iCombineMeasuredStates = View.combineMeasuredStates(i8, view.getMeasuredState());
                        i13 = iMax2;
                        i14 = iMax3;
                    } else {
                        fVar = fVar2;
                        i5 = i14;
                        i6 = size3;
                        i7 = paddingLeft;
                        i8 = i4;
                        i9 = i15;
                        i10 = i13;
                    }
                    onMeasureChild(view, iMakeMeasureSpec, iMax, iMakeMeasureSpec2, 0);
                    f fVar4 = fVar;
                    int iMax4 = Math.max(i10, i11 + view.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) fVar4).leftMargin + ((ViewGroup.MarginLayoutParams) fVar4).rightMargin);
                    int iMax5 = Math.max(i5, i12 + view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) fVar4).topMargin + ((ViewGroup.MarginLayoutParams) fVar4).bottomMargin);
                    iCombineMeasuredStates = View.combineMeasuredStates(i8, view.getMeasuredState());
                    i13 = iMax4;
                    i14 = iMax5;
                }
                iMax = 0;
                if (z2) {
                    iMakeMeasureSpec = i2;
                    iMakeMeasureSpec2 = i3;
                } else {
                    iMakeMeasureSpec = i2;
                    iMakeMeasureSpec2 = i3;
                }
                cVarF = fVar2.f();
                if (cVarF != null) {
                    fVar = fVar2;
                    i8 = i4;
                    i9 = i15;
                    i5 = i14;
                    i7 = paddingLeft;
                    i10 = i13;
                    i6 = size3;
                    if (!cVarF.onMeasureChild(this, view, iMakeMeasureSpec, iMax, iMakeMeasureSpec2, 0)) {
                    }
                    f fVar5 = fVar;
                    int iMax6 = Math.max(i10, i11 + view.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) fVar5).leftMargin + ((ViewGroup.MarginLayoutParams) fVar5).rightMargin);
                    int iMax7 = Math.max(i5, i12 + view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) fVar5).topMargin + ((ViewGroup.MarginLayoutParams) fVar5).bottomMargin);
                    iCombineMeasuredStates = View.combineMeasuredStates(i8, view.getMeasuredState());
                    i13 = iMax6;
                    i14 = iMax7;
                } else {
                    fVar = fVar2;
                    i5 = i14;
                    i6 = size3;
                    i7 = paddingLeft;
                    i8 = i4;
                    i9 = i15;
                    i10 = i13;
                }
                onMeasureChild(view, iMakeMeasureSpec, iMax, iMakeMeasureSpec2, 0);
                f fVar6 = fVar;
                int iMax8 = Math.max(i10, i11 + view.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) fVar6).leftMargin + ((ViewGroup.MarginLayoutParams) fVar6).rightMargin);
                int iMax9 = Math.max(i5, i12 + view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) fVar6).topMargin + ((ViewGroup.MarginLayoutParams) fVar6).bottomMargin);
                iCombineMeasuredStates = View.combineMeasuredStates(i8, view.getMeasuredState());
                i13 = iMax8;
                i14 = iMax9;
            }
            i15 = i9 + 1;
            paddingLeft = i7;
            size3 = i6;
        }
        int i18 = iCombineMeasuredStates;
        setMeasuredDimension(View.resolveSizeAndState(i13, i2, (-16777216) & i18), View.resolveSizeAndState(i14, i3, i18 << 16));
    }

    public void onMeasureChild(View view, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        c cVarF;
        int childCount = getChildCount();
        boolean zOnNestedFling = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(0) && (cVarF = fVar.f()) != null) {
                    zOnNestedFling |= cVarF.onNestedFling(this, childAt, view, f2, f3, z);
                }
            }
        }
        if (zOnNestedFling) {
            onChildViewsChanged(1);
        }
        return zOnNestedFling;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        c cVarF;
        int childCount = getChildCount();
        boolean zOnNestedPreFling = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(0) && (cVarF = fVar.f()) != null) {
                    zOnNestedPreFling |= cVarF.onNestedPreFling(this, childAt, view, f2, f3);
                }
            }
        }
        return zOnNestedPreFling;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        onNestedPreScroll(view, i2, i3, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        onNestedScroll(view, i2, i3, i4, i5, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        onNestedScrollAccepted(view, view2, i2, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof h)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        h hVar = (h) parcelable;
        super.onRestoreInstanceState(hVar.getSuperState());
        SparseArray sparseArray = hVar.a;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int id = childAt.getId();
            c cVarF = getResolvedLayoutParams(childAt).f();
            if (id != -1 && cVarF != null && (parcelable2 = (Parcelable) sparseArray.get(id)) != null) {
                cVarF.onRestoreInstanceState(this, childAt, parcelable2);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.os.Parcelable, androidx.coordinatorlayout.widget.CoordinatorLayout$h] */
    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState;
        ?? hVar = new h(super.onSaveInstanceState());
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int id = childAt.getId();
            c cVarF = ((f) childAt.getLayoutParams()).f();
            if (id != -1 && cVarF != null && (parcelableOnSaveInstanceState = cVarF.onSaveInstanceState(this, childAt)) != null) {
                sparseArray.append(id, parcelableOnSaveInstanceState);
            }
        }
        hVar.a = sparseArray;
        return hVar;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return onStartNestedScroll(view, view2, i2, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002b A[PHI: r3
      0x002b: PHI (r3v4 boolean) = (r3v2 boolean), (r3v5 boolean) binds: [B:9:0x0022, B:5:0x0012] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:14:0x0031  */
    /* JADX WARN: Code duplicated, block: B:15:0x0037 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:16:0x0039  */
    /* JADX WARN: Code duplicated, block: B:18:0x004c  */
    /* JADX WARN: Code duplicated, block: B:22:0x0054  */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zM;
        boolean zOnTouchEvent;
        MotionEvent motionEventObtain;
        int actionMasked = motionEvent.getActionMasked();
        if (this.mBehaviorTouchView == null) {
            zM = m(motionEvent, 1);
            if (!zM) {
                zOnTouchEvent = false;
            }
            motionEventObtain = null;
            if (this.mBehaviorTouchView == null) {
                zOnTouchEvent |= super.onTouchEvent(motionEvent);
            } else if (zM) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                super.onTouchEvent(motionEventObtain);
            }
            if (motionEventObtain != null) {
                motionEventObtain.recycle();
            }
            if (actionMasked != 1 || actionMasked == 3) {
                p(false);
            }
            return zOnTouchEvent;
        }
        zM = false;
        c cVarF = ((f) this.mBehaviorTouchView.getLayoutParams()).f();
        if (cVarF != null) {
            zOnTouchEvent = cVarF.onTouchEvent(this, this.mBehaviorTouchView, motionEvent);
        } else {
            zOnTouchEvent = false;
        }
        motionEventObtain = null;
        if (this.mBehaviorTouchView == null) {
            zOnTouchEvent |= super.onTouchEvent(motionEvent);
        } else if (zM) {
            long jUptimeMillis2 = SystemClock.uptimeMillis();
            motionEventObtain = MotionEvent.obtain(jUptimeMillis2, jUptimeMillis2, 3, 0.0f, 0.0f, 0);
            super.onTouchEvent(motionEventObtain);
        }
        if (motionEventObtain != null) {
            motionEventObtain.recycle();
        }
        if (actionMasked != 1) {
            p(false);
        } else {
            p(false);
        }
        return zOnTouchEvent;
    }

    void recordLastChildRect(View view, Rect rect) {
        ((f) view.getLayoutParams()).q(rect);
    }

    void removePreDrawListener() {
        if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        c cVarF = ((f) view.getLayoutParams()).f();
        if (cVarF == null || !cVarF.onRequestChildRectangleOnScreen(this, view, rect, z)) {
            return super.requestChildRectangleOnScreen(view, rect, z);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (!z || this.mDisallowInterceptReset) {
            return;
        }
        p(false);
        this.mDisallowInterceptReset = true;
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        v();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.mOnHierarchyChangeListener = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.mStatusBarBackground;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.mStatusBarBackground = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.mStatusBarBackground.setState(getDrawableState());
                }
                v.b.m(this.mStatusBarBackground, m0.A(this));
                this.mStatusBarBackground.setVisible(getVisibility() == 0, false);
                this.mStatusBarBackground.setCallback(this);
            }
            m0.c0(this);
        }
    }

    public void setStatusBarBackgroundColor(int i2) {
        setStatusBarBackground(new ColorDrawable(i2));
    }

    public void setStatusBarBackgroundResource(int i2) {
        setStatusBarBackground(i2 != 0 ? androidx.core.content.a.getDrawable(getContext(), i2) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z = i2 == 0;
        Drawable drawable = this.mStatusBarBackground;
        if (drawable == null || drawable.isVisible() == z) {
            return;
        }
        this.mStatusBarBackground.setVisible(z, false);
    }

    final g1 setWindowInsets(g1 g1Var) {
        if (c0.c.a(this.mLastInsets, g1Var)) {
            return g1Var;
        }
        this.mLastInsets = g1Var;
        boolean z = g1Var != null && g1Var.k() > 0;
        this.mDrawStatusBarBackground = z;
        setWillNotDraw(!z && getBackground() == null);
        g1 g1VarD = d(g1Var);
        requestLayout();
        return g1VarD;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mStatusBarBackground;
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mDependencySortedChildren = new ArrayList();
        this.mChildDag = new androidx.coordinatorlayout.widget.b();
        this.mTempList1 = new ArrayList();
        this.mTempDependenciesList = new ArrayList();
        this.mBehaviorConsumed = new int[2];
        this.mNestedScrollingV2ConsumedCompat = new int[2];
        this.mNestedScrollingParentHelper = new z(this);
        TypedArray typedArrayObtainStyledAttributes = i2 == 0 ? context.obtainStyledAttributes(attributeSet, R$styleable.b, 0, R$style.Widget_Support_CoordinatorLayout) : context.obtainStyledAttributes(attributeSet, R$styleable.b, i2, 0);
        if (Build.VERSION.SDK_INT >= 29) {
            if (i2 == 0) {
                saveAttributeDataForStyleable(context, R$styleable.b, attributeSet, typedArrayObtainStyledAttributes, 0, R$style.Widget_Support_CoordinatorLayout);
            } else {
                saveAttributeDataForStyleable(context, R$styleable.b, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
            }
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_keylines, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.mKeylines = resources.getIntArray(resourceId);
            float f2 = resources.getDisplayMetrics().density;
            int length = this.mKeylines.length;
            for (int i3 = 0; i3 < length; i3++) {
                int[] iArr = this.mKeylines;
                iArr[i3] = (int) (iArr[i3] * f2);
            }
        }
        this.mStatusBarBackground = typedArrayObtainStyledAttributes.getDrawable(R$styleable.CoordinatorLayout_statusBarBackground);
        typedArrayObtainStyledAttributes.recycle();
        v();
        super.setOnHierarchyChangeListener(new e());
        if (m0.y(this) == 0) {
            m0.v0(this, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public f generateDefaultLayoutParams() {
        return new f(-2, -2);
    }

    @Override // androidx.core.view.x
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr, int i4) {
        c cVarF;
        int childCount = getChildCount();
        boolean z = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(i4) && (cVarF = fVar.f()) != null) {
                    int[] iArr2 = this.mBehaviorConsumed;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    cVarF.onNestedPreScroll(this, childAt, view, i2, i3, iArr2, i4);
                    int[] iArr3 = this.mBehaviorConsumed;
                    iMax = i2 > 0 ? Math.max(iMax, iArr3[0]) : Math.min(iMax, iArr3[0]);
                    int[] iArr4 = this.mBehaviorConsumed;
                    iMax2 = i3 > 0 ? Math.max(iMax2, iArr4[1]) : Math.min(iMax2, iArr4[1]);
                    z = true;
                }
            }
        }
        iArr[0] = iMax;
        iArr[1] = iMax2;
        if (z) {
            onChildViewsChanged(1);
        }
    }

    @Override // androidx.core.view.x
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5, int i6) {
        onNestedScroll(view, i2, i3, i4, i5, 0, this.mNestedScrollingV2ConsumedCompat);
    }

    @Override // androidx.core.view.x
    public void onNestedScrollAccepted(View view, View view2, int i2, int i3) {
        c cVarF;
        this.mNestedScrollingParentHelper.c(view, view2, i2, i3);
        this.mNestedScrollingTarget = view2;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            f fVar = (f) childAt.getLayoutParams();
            if (fVar.j(i3) && (cVarF = fVar.f()) != null) {
                cVarF.onNestedScrollAccepted(this, childAt, view, view2, i2, i3);
            }
        }
    }

    @Override // androidx.core.view.x
    public boolean onStartNestedScroll(View view, View view2, int i2, int i3) {
        int childCount = getChildCount();
        boolean z = false;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                c cVarF = fVar.f();
                if (cVarF != null) {
                    boolean zOnStartNestedScroll = cVarF.onStartNestedScroll(this, childAt, view, view2, i2, i3);
                    z |= zOnStartNestedScroll;
                    fVar.r(i3, zOnStartNestedScroll);
                } else {
                    fVar.r(i3, false);
                }
            }
        }
        return z;
    }

    @Override // androidx.core.view.x
    public void onStopNestedScroll(View view, int i2) {
        this.mNestedScrollingParentHelper.e(view, i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            f fVar = (f) childAt.getLayoutParams();
            if (fVar.j(i2)) {
                c cVarF = fVar.f();
                if (cVarF != null) {
                    cVarF.onStopNestedScroll(this, childAt, view, i2);
                }
                fVar.l(i2);
                fVar.k();
            }
        }
        this.mNestedScrollingTarget = null;
    }

    @Override // android.view.ViewGroup
    public f generateLayoutParams(AttributeSet attributeSet) {
        return new f(getContext(), attributeSet);
    }

    @Override // androidx.core.view.y
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        c cVarF;
        int iMin;
        int childCount = getChildCount();
        boolean z = false;
        int iMax = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                f fVar = (f) childAt.getLayoutParams();
                if (fVar.j(i6) && (cVarF = fVar.f()) != null) {
                    int[] iArr2 = this.mBehaviorConsumed;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    cVarF.onNestedScroll(this, childAt, view, i2, i3, i4, i5, i6, iArr2);
                    int[] iArr3 = this.mBehaviorConsumed;
                    iMax = i4 > 0 ? Math.max(iMax, iArr3[0]) : Math.min(iMax, iArr3[0]);
                    if (i5 > 0) {
                        iMin = Math.max(i7, this.mBehaviorConsumed[1]);
                    } else {
                        iMin = Math.min(i7, this.mBehaviorConsumed[1]);
                    }
                    i7 = iMin;
                    z = true;
                }
            }
        }
        iArr[0] = iArr[0] + iMax;
        iArr[1] = iArr[1] + i7;
        if (z) {
            onChildViewsChanged(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public f generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof f) {
            return new f((f) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new f((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new f(layoutParams);
    }

    protected static class h extends g0.a {
        public static final Parcelable.Creator<h> CREATOR = new a();
        SparseArray a;

        static class a implements Parcelable.ClassLoaderCreator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public h createFromParcel(Parcel parcel) {
                return new h(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public h createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new h(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public h[] newArray(int i) {
                return new h[i];
            }
        }

        public h(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int i = parcel.readInt();
            int[] iArr = new int[i];
            parcel.readIntArray(iArr);
            Parcelable[] parcelableArray = parcel.readParcelableArray(classLoader);
            this.a = new SparseArray(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.a.append(iArr[i2], parcelableArray[i2]);
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            SparseArray sparseArray = this.a;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = this.a.keyAt(i2);
                parcelableArr[i2] = (Parcelable) this.a.valueAt(i2);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }

        public h(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public static class f extends ViewGroup.MarginLayoutParams {
        c a;
        boolean b;
        public int c;
        public int d;
        public int e;
        int f;
        public int g;
        public int h;
        int i;
        int j;
        View k;
        View l;
        private boolean m;
        private boolean n;
        private boolean o;
        private boolean p;
        final Rect q;
        Object r;

        public f(int i, int i2) {
            super(i, i2);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        private void n(View view, CoordinatorLayout coordinatorLayout) {
            View viewFindViewById = coordinatorLayout.findViewById(this.f);
            this.k = viewFindViewById;
            if (viewFindViewById == null) {
                if (coordinatorLayout.isInEditMode()) {
                    this.l = null;
                    this.k = null;
                    return;
                }
                throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + coordinatorLayout.getResources().getResourceName(this.f) + " to anchor view " + view);
            }
            if (viewFindViewById == coordinatorLayout) {
                if (!coordinatorLayout.isInEditMode()) {
                    throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                }
                this.l = null;
                this.k = null;
                return;
            }
            for (ViewParent parent = viewFindViewById.getParent(); parent != coordinatorLayout && parent != null; parent = parent.getParent()) {
                if (parent == view) {
                    if (!coordinatorLayout.isInEditMode()) {
                        throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                    }
                    this.l = null;
                    this.k = null;
                    return;
                }
                if (parent instanceof View) {
                    viewFindViewById = parent;
                }
            }
            this.l = viewFindViewById;
        }

        private boolean s(View view, int i) {
            int iB = j.b(((f) view.getLayoutParams()).g, i);
            return iB != 0 && (j.b(this.h, i) & iB) == iB;
        }

        private boolean t(View view, CoordinatorLayout coordinatorLayout) {
            if (this.k.getId() != this.f) {
                return false;
            }
            View view2 = this.k;
            for (ViewParent parent = view2.getParent(); parent != coordinatorLayout; parent = parent.getParent()) {
                if (parent == null || parent == view) {
                    this.l = null;
                    this.k = null;
                    return false;
                }
                if (parent instanceof View) {
                    view2 = parent;
                }
            }
            this.l = view2;
            return true;
        }

        boolean a() {
            return this.k == null && this.f != -1;
        }

        boolean b(CoordinatorLayout coordinatorLayout, View view, View view2) {
            c cVar;
            return view2 == this.l || s(view2, m0.A(coordinatorLayout)) || ((cVar = this.a) != null && cVar.layoutDependsOn(coordinatorLayout, view, view2));
        }

        boolean c() {
            if (this.a == null) {
                this.m = false;
            }
            return this.m;
        }

        View d(CoordinatorLayout coordinatorLayout, View view) {
            if (this.f == -1) {
                this.l = null;
                this.k = null;
                return null;
            }
            if (this.k == null || !t(view, coordinatorLayout)) {
                n(view, coordinatorLayout);
            }
            return this.k;
        }

        public int e() {
            return this.f;
        }

        public c f() {
            return this.a;
        }

        boolean g() {
            return this.p;
        }

        Rect h() {
            return this.q;
        }

        boolean i(CoordinatorLayout coordinatorLayout, View view) {
            boolean z = this.m;
            if (z) {
                return true;
            }
            c cVar = this.a;
            boolean zBlocksInteractionBelow = (cVar != null ? cVar.blocksInteractionBelow(coordinatorLayout, view) : false) | z;
            this.m = zBlocksInteractionBelow;
            return zBlocksInteractionBelow;
        }

        boolean j(int i) {
            if (i == 0) {
                return this.n;
            }
            if (i != 1) {
                return false;
            }
            return this.o;
        }

        void k() {
            this.p = false;
        }

        void l(int i) {
            r(i, false);
        }

        void m() {
            this.m = false;
        }

        public void o(c cVar) {
            c cVar2 = this.a;
            if (cVar2 != cVar) {
                if (cVar2 != null) {
                    cVar2.onDetachedFromLayoutParams();
                }
                this.a = cVar;
                this.r = null;
                this.b = true;
                if (cVar != null) {
                    cVar.onAttachedToLayoutParams(this);
                }
            }
        }

        void p(boolean z) {
            this.p = z;
        }

        void q(Rect rect) {
            this.q.set(rect);
        }

        void r(int i, boolean z) {
            if (i == 0) {
                this.n = z;
            } else {
                if (i != 1) {
                    return;
                }
                this.o = z;
            }
        }

        f(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.c);
            this.c = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            this.f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            this.d = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            this.e = typedArrayObtainStyledAttributes.getInteger(R$styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            this.g = typedArrayObtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            this.h = typedArrayObtainStyledAttributes.getInt(R$styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            int i = R$styleable.CoordinatorLayout_Layout_layout_behavior;
            boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i);
            this.b = zHasValue;
            if (zHasValue) {
                this.a = CoordinatorLayout.parseBehavior(context, attributeSet, typedArrayObtainStyledAttributes.getString(i));
            }
            typedArrayObtainStyledAttributes.recycle();
            c cVar = this.a;
            if (cVar != null) {
                cVar.onAttachedToLayoutParams(this);
            }
        }

        public f(f fVar) {
            super((ViewGroup.MarginLayoutParams) fVar);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        public f(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }

        public f(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = -1;
            this.f = -1;
            this.g = 0;
            this.h = 0;
            this.q = new Rect();
        }
    }
}
