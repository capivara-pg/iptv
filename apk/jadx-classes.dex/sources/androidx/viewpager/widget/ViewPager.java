package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.appcompat.R$styleable;
import androidx.core.view.b0;
import androidx.core.view.c1;
import androidx.core.view.f2;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import y.h0;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class ViewPager extends ViewGroup {
    private static final int CLOSE_ENOUGH = 2;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private int mActivePointerId;
    androidx.viewpager.widget.a mAdapter;
    private List<i> mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private j mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList<f> mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffect mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private l mObserver;
    private int mOffscreenPageLimit;
    private j mOnPageChangeListener;
    private List<j> mOnPageChangeListeners;
    private int mPageMargin;
    private k mPageTransformer;
    private int mPageTransformerLayerType;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffect mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final f mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    static final int[] LAYOUT_ATTRS = {R.attr.layout_gravity};
    private static final Comparator<f> COMPARATOR = new a();
    private static final Interpolator sInterpolator = new b();
    private static final o sPositionComparator = new o();

    static class a implements Comparator {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(f fVar, f fVar2) {
            return fVar.b - fVar2.b;
        }
    }

    static class b implements Interpolator {
        b() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewPager.this.setScrollState(0);
            ViewPager.this.populate();
        }
    }

    class d implements b0 {
        private final Rect a = new Rect();

        d() {
        }

        @Override // androidx.core.view.b0
        public f2 onApplyWindowInsets(View view, f2 f2Var) {
            f2 f2VarX = c1.X(view, f2Var);
            if (f2VarX.m()) {
                return f2VarX;
            }
            Rect rect = this.a;
            rect.left = f2VarX.i();
            rect.top = f2VarX.k();
            rect.right = f2VarX.j();
            rect.bottom = f2VarX.h();
            int childCount = ViewPager.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                f2 f2VarG = c1.g(ViewPager.this.getChildAt(i), f2VarX);
                rect.left = Math.min(f2VarG.i(), rect.left);
                rect.top = Math.min(f2VarG.k(), rect.top);
                rect.right = Math.min(f2VarG.j(), rect.right);
                rect.bottom = Math.min(f2VarG.h(), rect.bottom);
            }
            return f2VarX.n(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface e {
    }

    static class f {
        Object a;
        int b;
        boolean c;
        float d;
        float e;

        f() {
        }
    }

    class h extends androidx.core.view.a {
        h() {
        }

        private boolean canScroll() {
            androidx.viewpager.widget.a aVar = ViewPager.this.mAdapter;
            if (aVar == null || aVar.getCount() <= 1) {
                return ViewPager.DEBUG;
            }
            return true;
        }

        @Override // androidx.core.view.a
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            androidx.viewpager.widget.a aVar;
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            accessibilityEvent.setScrollable(canScroll());
            if (accessibilityEvent.getEventType() != 4096 || (aVar = ViewPager.this.mAdapter) == null) {
                return;
            }
            accessibilityEvent.setItemCount(aVar.getCount());
            accessibilityEvent.setFromIndex(ViewPager.this.mCurItem);
            accessibilityEvent.setToIndex(ViewPager.this.mCurItem);
        }

        @Override // androidx.core.view.a
        public void onInitializeAccessibilityNodeInfo(View view, h0 h0Var) {
            super.onInitializeAccessibilityNodeInfo(view, h0Var);
            h0Var.X(ViewPager.class.getName());
            h0Var.p0(canScroll());
            if (ViewPager.this.canScrollHorizontally(1)) {
                h0Var.a(RecyclerView.ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                h0Var.a(8192);
            }
        }

        @Override // androidx.core.view.a
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            if (i == 4096) {
                if (!ViewPager.this.canScrollHorizontally(1)) {
                    return ViewPager.DEBUG;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.mCurItem + 1);
                return true;
            }
            if (i != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                return ViewPager.DEBUG;
            }
            ViewPager viewPager2 = ViewPager.this;
            viewPager2.setCurrentItem(viewPager2.mCurItem - 1);
            return true;
        }
    }

    public interface i {
        void onAdapterChanged(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2);
    }

    public interface j {
        void onPageScrollStateChanged(int i);

        void onPageScrolled(int i, float f, int i2);

        void onPageSelected(int i);
    }

    public interface k {
    }

    private class l extends DataSetObserver {
        l() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            ViewPager.this.dataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ViewPager.this.dataSetChanged();
        }
    }

    public static class m extends b0.a {
        public static final Parcelable.Creator<m> CREATOR = new a();
        int a;
        Parcelable b;
        ClassLoader c;

        static class a implements Parcelable.ClassLoaderCreator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public m createFromParcel(Parcel parcel) {
                return new m(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public m createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new m(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public m[] newArray(int i) {
                return new m[i];
            }
        }

        public m(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.a + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeParcelable(this.b, i);
        }

        m(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }
    }

    public static class n implements j {
        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrolled(int i, float f, int i2) {
        }
    }

    static class o implements Comparator {
        o() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            g gVar = (g) view.getLayoutParams();
            g gVar2 = (g) view2.getLayoutParams();
            boolean z = gVar.a;
            if (z != gVar2.a) {
                return z ? 1 : -1;
            }
            return gVar.e - gVar2.e;
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.mItems = new ArrayList<>();
        this.mTempItem = new f();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = DEBUG;
        this.mEndScrollRunnable = new c();
        this.mScrollState = 0;
        initViewPager();
    }

    private void a(f fVar, int i2, f fVar2) {
        int i3;
        int i4;
        f fVar3;
        f fVar4;
        int count = this.mAdapter.getCount();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? this.mPageMargin / clientWidth : 0.0f;
        if (fVar2 != null) {
            int i5 = fVar2.b;
            int i6 = fVar.b;
            if (i5 < i6) {
                float pageWidth = fVar2.e + fVar2.d + f2;
                int i7 = i5 + 1;
                int i8 = 0;
                while (i7 <= fVar.b && i8 < this.mItems.size()) {
                    f fVar5 = this.mItems.get(i8);
                    while (true) {
                        fVar4 = fVar5;
                        if (i7 <= fVar4.b || i8 >= this.mItems.size() - 1) {
                            break;
                        }
                        i8++;
                        fVar5 = this.mItems.get(i8);
                    }
                    while (i7 < fVar4.b) {
                        pageWidth += this.mAdapter.getPageWidth(i7) + f2;
                        i7++;
                    }
                    fVar4.e = pageWidth;
                    pageWidth += fVar4.d + f2;
                    i7++;
                }
            } else if (i5 > i6) {
                int size = this.mItems.size() - 1;
                float pageWidth2 = fVar2.e;
                while (true) {
                    i5--;
                    if (i5 < fVar.b || size < 0) {
                        break;
                    }
                    f fVar6 = this.mItems.get(size);
                    while (true) {
                        fVar3 = fVar6;
                        if (i5 >= fVar3.b || size <= 0) {
                            break;
                        }
                        size--;
                        fVar6 = this.mItems.get(size);
                    }
                    while (i5 > fVar3.b) {
                        pageWidth2 -= this.mAdapter.getPageWidth(i5) + f2;
                        i5--;
                    }
                    pageWidth2 -= fVar3.d + f2;
                    fVar3.e = pageWidth2;
                }
            }
        }
        int size2 = this.mItems.size();
        float pageWidth3 = fVar.e;
        int i9 = fVar.b;
        int i10 = i9 - 1;
        this.mFirstOffset = i9 == 0 ? pageWidth3 : -3.4028235E38f;
        int i11 = count - 1;
        this.mLastOffset = i9 == i11 ? (fVar.d + pageWidth3) - 1.0f : Float.MAX_VALUE;
        int i12 = i2 - 1;
        while (i12 >= 0) {
            f fVar7 = this.mItems.get(i12);
            while (true) {
                i4 = fVar7.b;
                if (i10 <= i4) {
                    break;
                }
                pageWidth3 -= this.mAdapter.getPageWidth(i10) + f2;
                i10--;
            }
            pageWidth3 -= fVar7.d + f2;
            fVar7.e = pageWidth3;
            if (i4 == 0) {
                this.mFirstOffset = pageWidth3;
            }
            i12--;
            i10--;
        }
        float pageWidth4 = fVar.e + fVar.d + f2;
        int i13 = fVar.b + 1;
        int i14 = i2 + 1;
        while (i14 < size2) {
            f fVar8 = this.mItems.get(i14);
            while (true) {
                i3 = fVar8.b;
                if (i13 >= i3) {
                    break;
                }
                pageWidth4 += this.mAdapter.getPageWidth(i13) + f2;
                i13++;
            }
            if (i3 == i11) {
                this.mLastOffset = (fVar8.d + pageWidth4) - 1.0f;
            }
            fVar8.e = pageWidth4;
            pageWidth4 += fVar8.d + f2;
            i14++;
            i13++;
        }
        this.mNeedCalculatePageOffsets = DEBUG;
    }

    private void b(boolean z) {
        boolean z2 = this.mScrollState == 2 ? true : DEBUG;
        if (z2) {
            setScrollingCacheEnabled(DEBUG);
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        m(currX);
                    }
                }
            }
        }
        this.mPopulatePending = DEBUG;
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            f fVar = this.mItems.get(i2);
            if (fVar.c) {
                fVar.c = DEBUG;
                z2 = true;
            }
        }
        if (z2) {
            if (z) {
                c1.c0(this, this.mEndScrollRunnable);
            } else {
                this.mEndScrollRunnable.run();
            }
        }
    }

    private int c(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.mFlingDistance || Math.abs(i3) <= this.mMinimumVelocity) {
            i2 += (int) (f2 + (i2 >= this.mCurItem ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        if (this.mItems.size() <= 0) {
            return i2;
        }
        f fVar = this.mItems.get(0);
        ArrayList<f> arrayList = this.mItems;
        return Math.max(fVar.b, Math.min(i2, arrayList.get(arrayList.size() - 1).b));
    }

    private void d(int i2, float f2, int i3) {
        j jVar = this.mOnPageChangeListener;
        if (jVar != null) {
            jVar.onPageScrolled(i2, f2, i3);
        }
        List<j> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                j jVar2 = this.mOnPageChangeListeners.get(i4);
                if (jVar2 != null) {
                    jVar2.onPageScrolled(i2, f2, i3);
                }
            }
        }
        j jVar3 = this.mInternalPageChangeListener;
        if (jVar3 != null) {
            jVar3.onPageScrolled(i2, f2, i3);
        }
    }

    private void e(int i2) {
        j jVar = this.mOnPageChangeListener;
        if (jVar != null) {
            jVar.onPageSelected(i2);
        }
        List<j> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = this.mOnPageChangeListeners.get(i3);
                if (jVar2 != null) {
                    jVar2.onPageSelected(i2);
                }
            }
        }
        j jVar3 = this.mInternalPageChangeListener;
        if (jVar3 != null) {
            jVar3.onPageSelected(i2);
        }
    }

    private void f(int i2) {
        j jVar = this.mOnPageChangeListener;
        if (jVar != null) {
            jVar.onPageScrollStateChanged(i2);
        }
        List<j> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = this.mOnPageChangeListeners.get(i3);
                if (jVar2 != null) {
                    jVar2.onPageScrollStateChanged(i2);
                }
            }
        }
        j jVar3 = this.mInternalPageChangeListener;
        if (jVar3 != null) {
            jVar3.onPageScrollStateChanged(i2);
        }
    }

    private void g() {
        this.mIsBeingDragged = DEBUG;
        this.mIsUnableToDrag = DEBUG;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private Rect h(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    private f i() {
        int i2;
        int clientWidth = getClientWidth();
        float f2 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f3 = clientWidth > 0 ? this.mPageMargin / clientWidth : 0.0f;
        f fVar = null;
        float f4 = 0.0f;
        int i3 = -1;
        int i4 = 0;
        boolean z = true;
        while (i4 < this.mItems.size()) {
            f fVar2 = this.mItems.get(i4);
            if (!z && fVar2.b != (i2 = i3 + 1)) {
                fVar2 = this.mTempItem;
                fVar2.e = f2 + f4 + f3;
                fVar2.b = i2;
                fVar2.d = this.mAdapter.getPageWidth(i2);
                i4--;
            }
            f fVar3 = fVar2;
            f2 = fVar3.e;
            float f5 = fVar3.d + f2 + f3;
            if (!z && scrollX < f2) {
                return fVar;
            }
            if (scrollX < f5 || i4 == this.mItems.size() - 1) {
                return fVar3;
            }
            int i5 = fVar3.b;
            float f6 = fVar3.d;
            i4++;
            z = DEBUG;
            i3 = i5;
            f4 = f6;
            fVar = fVar3;
        }
        return fVar;
    }

    private static boolean j(View view) {
        if (view.getClass().getAnnotation(e.class) != null) {
            return true;
        }
        return DEBUG;
    }

    private boolean k(float f2, float f3) {
        if ((f2 >= this.mGutterSize || f3 <= 0.0f) && (f2 <= getWidth() - this.mGutterSize || f3 >= 0.0f)) {
            return DEBUG;
        }
        return true;
    }

    private void l(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.mLastMotionX = motionEvent.getX(i2);
            this.mActivePointerId = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private boolean m(int i2) {
        if (this.mItems.size() == 0) {
            if (this.mFirstLayout) {
                return DEBUG;
            }
            this.mCalledSuper = DEBUG;
            onPageScrolled(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return DEBUG;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        f fVarI = i();
        int clientWidth = getClientWidth();
        int i3 = this.mPageMargin;
        int i4 = clientWidth + i3;
        float f2 = clientWidth;
        int i5 = fVarI.b;
        float f3 = ((i2 / f2) - fVarI.e) / (fVarI.d + (i3 / f2));
        this.mCalledSuper = DEBUG;
        onPageScrolled(i5, f3, (int) (i4 * f3));
        if (this.mCalledSuper) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private boolean n(float f2) {
        boolean z;
        boolean z2;
        float f3 = this.mLastMotionX - f2;
        this.mLastMotionX = f2;
        float scrollX = getScrollX() + f3;
        float clientWidth = getClientWidth();
        float f4 = this.mFirstOffset * clientWidth;
        float f5 = this.mLastOffset * clientWidth;
        ArrayList<f> arrayList = this.mItems;
        boolean z3 = DEBUG;
        f fVar = arrayList.get(0);
        ArrayList<f> arrayList2 = this.mItems;
        f fVar2 = arrayList2.get(arrayList2.size() - 1);
        if (fVar.b != 0) {
            f4 = fVar.e * clientWidth;
            z = DEBUG;
        } else {
            z = true;
        }
        if (fVar2.b != this.mAdapter.getCount() - 1) {
            f5 = fVar2.e * clientWidth;
            z2 = DEBUG;
        } else {
            z2 = true;
        }
        if (scrollX < f4) {
            if (z) {
                this.mLeftEdge.onPull(Math.abs(f4 - scrollX) / clientWidth);
                z3 = true;
            }
            scrollX = f4;
        } else if (scrollX > f5) {
            if (z2) {
                this.mRightEdge.onPull(Math.abs(scrollX - f5) / clientWidth);
                z3 = true;
            }
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.mLastMotionX += scrollX - i2;
        scrollTo(i2, getScrollY());
        m(i2);
        return z3;
    }

    private void o(int i2, int i3, int i4, int i5) {
        if (i3 > 0 && !this.mItems.isEmpty()) {
            if (!this.mScroller.isFinished()) {
                this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)) * (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)), getScrollY());
                return;
            }
        }
        f fVarInfoForPosition = infoForPosition(this.mCurItem);
        int iMin = (int) ((fVarInfoForPosition != null ? Math.min(fVarInfoForPosition.e, this.mLastOffset) : 0.0f) * ((i2 - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            b(DEBUG);
            scrollTo(iMin, getScrollY());
        }
    }

    private void p() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((g) getChildAt(i2).getLayoutParams()).a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    private void q(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean r() {
        this.mActivePointerId = -1;
        g();
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        if (this.mLeftEdge.isFinished() || this.mRightEdge.isFinished()) {
            return true;
        }
        return DEBUG;
    }

    private void s(int i2, boolean z, int i3, boolean z2) {
        f fVarInfoForPosition = infoForPosition(i2);
        int clientWidth = fVarInfoForPosition != null ? (int) (getClientWidth() * Math.max(this.mFirstOffset, Math.min(fVarInfoForPosition.e, this.mLastOffset))) : 0;
        if (z) {
            smoothScrollTo(clientWidth, 0, i3);
            if (z2) {
                e(i2);
                return;
            }
            return;
        }
        if (z2) {
            e(i2);
        }
        b(DEBUG);
        scrollTo(clientWidth, 0);
        m(clientWidth);
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    private void t() {
        if (this.mDrawingOrder != 0) {
            ArrayList<View> arrayList = this.mDrawingOrderedChildren;
            if (arrayList == null) {
                this.mDrawingOrderedChildren = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.mDrawingOrderedChildren.add(getChildAt(i2));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        f fVarInfoForChild;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (fVarInfoForChild = infoForChild(childAt)) != null && fVarInfoForChild.b == this.mCurItem) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i3 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    f addNewItem(int i2, int i3) {
        f fVar = new f();
        fVar.b = i2;
        fVar.a = this.mAdapter.instantiateItem((ViewGroup) this, i2);
        fVar.d = this.mAdapter.getPageWidth(i2);
        if (i3 < 0 || i3 >= this.mItems.size()) {
            this.mItems.add(fVar);
        } else {
            this.mItems.add(i3, fVar);
        }
        return fVar;
    }

    public void addOnAdapterChangeListener(i iVar) {
        if (this.mAdapterChangeListeners == null) {
            this.mAdapterChangeListeners = new ArrayList();
        }
        this.mAdapterChangeListeners.add(iVar);
    }

    public void addOnPageChangeListener(j jVar) {
        if (this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }
        this.mOnPageChangeListeners.add(jVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        f fVarInfoForChild;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (fVarInfoForChild = infoForChild(childAt)) != null && fVarInfoForChild.b == this.mCurItem) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        g gVar = (g) layoutParams;
        boolean zJ = gVar.a | j(view);
        gVar.a = zJ;
        if (!this.mInLayout) {
            super.addView(view, i2, layoutParams);
        } else {
            if (zJ) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            gVar.d = true;
            addViewInLayout(view, i2, layoutParams);
        }
    }

    public boolean arrowScroll(int i2) {
        boolean z;
        boolean zRequestFocus;
        View viewFindFocus = findFocus();
        boolean zPageLeft = DEBUG;
        if (viewFindFocus == this) {
            viewFindFocus = null;
        } else if (viewFindFocus != null) {
            ViewParent parent = viewFindFocus.getParent();
            while (true) {
                if (!(parent instanceof ViewGroup)) {
                    z = DEBUG;
                    break;
                }
                if (parent == this) {
                    z = true;
                    break;
                }
                parent = parent.getParent();
            }
            if (!z) {
                StringBuilder sb = new StringBuilder();
                sb.append(viewFindFocus.getClass().getSimpleName());
                for (ViewParent parent2 = viewFindFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                    sb.append(" => ");
                    sb.append(parent2.getClass().getSimpleName());
                }
                Log.e(TAG, "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                viewFindFocus = null;
            }
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i2);
        if (viewFindNextFocus != null && viewFindNextFocus != viewFindFocus) {
            if (i2 == 17) {
                zRequestFocus = (viewFindFocus == null || h(this.mTempRect, viewFindNextFocus).left < h(this.mTempRect, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : pageLeft();
            } else if (i2 == 66) {
                zRequestFocus = (viewFindFocus == null || h(this.mTempRect, viewFindNextFocus).left > h(this.mTempRect, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : pageRight();
            }
            zPageLeft = zRequestFocus;
        } else if (i2 == 17 || i2 == 1) {
            zPageLeft = pageLeft();
        } else if (i2 == 66 || i2 == 2) {
            zPageLeft = pageRight();
        }
        if (zPageLeft) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i2));
        }
        return zPageLeft;
    }

    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged) {
            return DEBUG;
        }
        this.mFakeDragging = true;
        setScrollState(1);
        this.mLastMotionX = 0.0f;
        this.mInitialMotionX = 0.0f;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, 0.0f, 0.0f, 0);
        this.mVelocityTracker.addMovement(motionEventObtain);
        motionEventObtain.recycle();
        this.mFakeDragBeginTime = jUptimeMillis;
        return true;
    }

    protected boolean canScroll(View view, boolean z, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && canScroll(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                    return true;
                }
            }
        }
        if (z && view.canScrollHorizontally(-i2)) {
            return true;
        }
        return DEBUG;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        if (this.mAdapter == null) {
            return DEBUG;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i2 < 0) {
            if (scrollX > ((int) (clientWidth * this.mFirstOffset))) {
                return true;
            }
            return DEBUG;
        }
        if (i2 <= 0 || scrollX >= ((int) (clientWidth * this.mLastOffset))) {
            return DEBUG;
        }
        return true;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if ((layoutParams instanceof g) && super.checkLayoutParams(layoutParams)) {
            return true;
        }
        return DEBUG;
    }

    public void clearOnPageChangeListeners() {
        List<j> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            b(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.mScroller.getCurrX();
        int currY = this.mScroller.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!m(currX)) {
                this.mScroller.abortAnimation();
                scrollTo(0, currY);
            }
        }
        c1.b0(this);
    }

    void dataSetChanged() {
        int count = this.mAdapter.getCount();
        this.mExpectedAdapterCount = count;
        boolean z = (this.mItems.size() >= (this.mOffscreenPageLimit * 2) + 1 || this.mItems.size() >= count) ? DEBUG : true;
        int iMax = this.mCurItem;
        int i2 = 0;
        boolean z2 = DEBUG;
        while (i2 < this.mItems.size()) {
            f fVar = this.mItems.get(i2);
            int itemPosition = this.mAdapter.getItemPosition(fVar.a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.mItems.remove(i2);
                    i2--;
                    if (!z2) {
                        this.mAdapter.startUpdate((ViewGroup) this);
                        z2 = true;
                    }
                    this.mAdapter.destroyItem((ViewGroup) this, fVar.b, fVar.a);
                    int i3 = this.mCurItem;
                    if (i3 == fVar.b) {
                        iMax = Math.max(0, Math.min(i3, count - 1));
                    }
                } else {
                    int i4 = fVar.b;
                    if (i4 != itemPosition) {
                        if (i4 == this.mCurItem) {
                            iMax = itemPosition;
                        }
                        fVar.b = itemPosition;
                    }
                }
                z = true;
            }
            i2++;
        }
        if (z2) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (z) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                g gVar = (g) getChildAt(i5).getLayoutParams();
                if (!gVar.a) {
                    gVar.c = 0.0f;
                }
            }
            setCurrentItemInternal(iMax, DEBUG, true);
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent)) {
            return true;
        }
        return DEBUG;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        f fVarInfoForChild;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (fVarInfoForChild = infoForChild(childAt)) != null && fVarInfoForChild.b == this.mCurItem && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return DEBUG;
    }

    float distanceInfluenceForSnapDuration(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        androidx.viewpager.widget.a aVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zDraw = DEBUG;
        if (overScrollMode == 0 || (overScrollMode == 1 && (aVar = this.mAdapter) != null && aVar.getCount() > 1)) {
            if (!this.mLeftEdge.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.mFirstOffset * width);
                this.mLeftEdge.setSize(height, width);
                zDraw = false | this.mLeftEdge.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.mRightEdge.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.mLastOffset + 1.0f)) * width2);
                this.mRightEdge.setSize(height2, width2);
                zDraw |= this.mRightEdge.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        if (zDraw) {
            c1.b0(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mMarginDrawable;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public void endFakeDrag() {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.mAdapter != null) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
            int xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
            this.mPopulatePending = true;
            int clientWidth = getClientWidth();
            int scrollX = getScrollX();
            f fVarI = i();
            setCurrentItemInternal(c(fVarI.b, ((scrollX / clientWidth) - fVarI.e) / fVarI.d, xVelocity, (int) (this.mLastMotionX - this.mInitialMotionX)), true, true, xVelocity);
        }
        g();
        this.mFakeDragging = DEBUG;
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                return keyEvent.hasModifiers(2) ? pageLeft() : arrowScroll(17);
            }
            if (keyCode == 22) {
                return keyEvent.hasModifiers(2) ? pageRight() : arrowScroll(66);
            }
            if (keyCode == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return arrowScroll(2);
                }
                if (keyEvent.hasModifiers(1)) {
                    return arrowScroll(1);
                }
            }
        }
        return DEBUG;
    }

    public void fakeDragBy(float f2) {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.mAdapter == null) {
            return;
        }
        this.mLastMotionX += f2;
        float scrollX = getScrollX() - f2;
        float clientWidth = getClientWidth();
        float f3 = this.mFirstOffset * clientWidth;
        float f4 = this.mLastOffset * clientWidth;
        f fVar = this.mItems.get(0);
        ArrayList<f> arrayList = this.mItems;
        f fVar2 = arrayList.get(arrayList.size() - 1);
        if (fVar.b != 0) {
            f3 = fVar.e * clientWidth;
        }
        if (fVar2.b != this.mAdapter.getCount() - 1) {
            f4 = fVar2.e * clientWidth;
        }
        if (scrollX < f3) {
            scrollX = f3;
        } else if (scrollX > f4) {
            scrollX = f4;
        }
        int i2 = (int) scrollX;
        this.mLastMotionX += scrollX - i2;
        scrollTo(i2, getScrollY());
        m(i2);
        MotionEvent motionEventObtain = MotionEvent.obtain(this.mFakeDragBeginTime, SystemClock.uptimeMillis(), 2, this.mLastMotionX, 0.0f, 0);
        this.mVelocityTracker.addMovement(motionEventObtain);
        motionEventObtain.recycle();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new g();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public androidx.viewpager.widget.a getAdapter() {
        return this.mAdapter;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        if (this.mDrawingOrder == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((g) this.mDrawingOrderedChildren.get(i3).getLayoutParams()).f;
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    f infoForAnyChild(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent == this) {
                return infoForChild(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    f infoForChild(View view) {
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            f fVar = this.mItems.get(i2);
            if (this.mAdapter.isViewFromObject(view, fVar.a)) {
                return fVar;
            }
        }
        return null;
    }

    f infoForPosition(int i2) {
        for (int i3 = 0; i3 < this.mItems.size(); i3++) {
            f fVar = this.mItems.get(i3);
            if (fVar.b == i2) {
                return fVar;
            }
        }
        return null;
    }

    void initViewPager() {
        setWillNotDraw(DEBUG);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * f2);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(context);
        this.mRightEdge = new EdgeEffect(context);
        this.mFlingDistance = (int) (25.0f * f2);
        this.mCloseEnough = (int) (2.0f * f2);
        this.mDefaultGutterSize = (int) (f2 * 16.0f);
        c1.k0(this, new h());
        if (c1.x(this) == 0) {
            c1.u0(this, 1);
        }
        c1.x0(this, new d());
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        float f2;
        super.onDraw(canvas);
        if (this.mPageMargin <= 0 || this.mMarginDrawable == null || this.mItems.size() <= 0 || this.mAdapter == null) {
            return;
        }
        int scrollX = getScrollX();
        int width = getWidth();
        float f3 = width;
        float f4 = this.mPageMargin / f3;
        int i3 = 0;
        f fVar = this.mItems.get(0);
        float f5 = fVar.e;
        int size = this.mItems.size();
        int i4 = fVar.b;
        int i5 = this.mItems.get(size - 1).b;
        while (i4 < i5) {
            while (true) {
                i2 = fVar.b;
                if (i4 <= i2 || i3 >= size) {
                    break;
                }
                i3++;
                fVar = this.mItems.get(i3);
            }
            if (i4 == i2) {
                float f6 = fVar.e;
                float f7 = fVar.d;
                f2 = (f6 + f7) * f3;
                f5 = f6 + f7 + f4;
            } else {
                float pageWidth = this.mAdapter.getPageWidth(i4);
                f2 = (f5 + pageWidth) * f3;
                f5 += pageWidth + f4;
            }
            if (this.mPageMargin + f2 > scrollX) {
                this.mMarginDrawable.setBounds(Math.round(f2), this.mTopPageBounds, Math.round(this.mPageMargin + f2), this.mBottomPageBounds);
                this.mMarginDrawable.draw(canvas);
            }
            if (f2 > scrollX + width) {
                return;
            }
            i4++;
            f4 = f4;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            r();
            return DEBUG;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag) {
                return DEBUG;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            this.mInitialMotionX = x;
            this.mLastMotionX = x;
            float y = motionEvent.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.mIsUnableToDrag = DEBUG;
            this.mIsScrollStarted = true;
            this.mScroller.computeScrollOffset();
            if (this.mScrollState != 2 || Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) <= this.mCloseEnough) {
                b(DEBUG);
                this.mIsBeingDragged = DEBUG;
            } else {
                this.mScroller.abortAnimation();
                this.mPopulatePending = DEBUG;
                populate();
                this.mIsBeingDragged = true;
                q(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i2 = this.mActivePointerId;
            if (i2 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i2);
                float x2 = motionEvent.getX(iFindPointerIndex);
                float f2 = x2 - this.mLastMotionX;
                float fAbs = Math.abs(f2);
                float y2 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y2 - this.mInitialMotionY);
                if (f2 != 0.0f && !k(this.mLastMotionX, f2) && canScroll(this, DEBUG, (int) f2, (int) x2, (int) y2)) {
                    this.mLastMotionX = x2;
                    this.mLastMotionY = y2;
                    this.mIsUnableToDrag = true;
                    return DEBUG;
                }
                int i3 = this.mTouchSlop;
                if (fAbs > i3 && fAbs * 0.5f > fAbs2) {
                    this.mIsBeingDragged = true;
                    q(true);
                    setScrollState(1);
                    this.mLastMotionX = f2 > 0.0f ? this.mInitialMotionX + this.mTouchSlop : this.mInitialMotionX - this.mTouchSlop;
                    this.mLastMotionY = y2;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > i3) {
                    this.mIsUnableToDrag = true;
                }
                if (this.mIsBeingDragged && n(x2)) {
                    c1.b0(this);
                }
            }
        } else if (action == 6) {
            l(motionEvent);
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        return this.mIsBeingDragged;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0071  */
    /* JADX WARN: Code duplicated, block: B:24:0x0075  */
    /* JADX WARN: Code duplicated, block: B:26:0x0079  */
    /* JADX WARN: Code duplicated, block: B:27:0x007b  */
    /* JADX WARN: Code duplicated, block: B:28:0x0088  */
    /* JADX WARN: Code duplicated, block: B:29:0x008e  */
    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        boolean z2;
        f fVarInfoForChild;
        int iMax;
        int measuredWidth;
        int iMax2;
        int measuredHeight;
        int childCount = getChildCount();
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                g gVar = (g) childAt.getLayoutParams();
                if (gVar.a) {
                    int i10 = gVar.b;
                    int i11 = i10 & 7;
                    int i12 = i10 & R$styleable.AppCompatTheme_tooltipForegroundColor;
                    if (i11 != 1) {
                        if (i11 == 3) {
                            measuredWidth = childAt.getMeasuredWidth() + paddingLeft;
                        } else if (i11 != 5) {
                            measuredWidth = paddingLeft;
                        } else {
                            iMax = (i6 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                        }
                        if (i12 != 16) {
                            if (i12 != 48) {
                                measuredHeight = childAt.getMeasuredHeight() + paddingTop;
                            } else if (i12 != 80) {
                                measuredHeight = paddingTop;
                            } else {
                                iMax2 = (i7 - paddingBottom) - childAt.getMeasuredHeight();
                                paddingBottom += childAt.getMeasuredHeight();
                            }
                            int i13 = paddingLeft + scrollX;
                            childAt.layout(i13, paddingTop, childAt.getMeasuredWidth() + i13, paddingTop + childAt.getMeasuredHeight());
                            i8++;
                            paddingTop = measuredHeight;
                            paddingLeft = measuredWidth;
                        } else {
                            iMax2 = Math.max((i7 - childAt.getMeasuredHeight()) / 2, paddingTop);
                        }
                        int i14 = iMax2;
                        measuredHeight = paddingTop;
                        paddingTop = i14;
                        int i15 = paddingLeft + scrollX;
                        childAt.layout(i15, paddingTop, childAt.getMeasuredWidth() + i15, paddingTop + childAt.getMeasuredHeight());
                        i8++;
                        paddingTop = measuredHeight;
                        paddingLeft = measuredWidth;
                    } else {
                        iMax = Math.max((i6 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                    }
                    int i16 = iMax;
                    measuredWidth = paddingLeft;
                    paddingLeft = i16;
                    if (i12 != 16) {
                        if (i12 != 48) {
                            measuredHeight = childAt.getMeasuredHeight() + paddingTop;
                        } else if (i12 != 80) {
                            measuredHeight = paddingTop;
                        } else {
                            iMax2 = (i7 - paddingBottom) - childAt.getMeasuredHeight();
                            paddingBottom += childAt.getMeasuredHeight();
                        }
                        int i17 = paddingLeft + scrollX;
                        childAt.layout(i17, paddingTop, childAt.getMeasuredWidth() + i17, paddingTop + childAt.getMeasuredHeight());
                        i8++;
                        paddingTop = measuredHeight;
                        paddingLeft = measuredWidth;
                    } else {
                        iMax2 = Math.max((i7 - childAt.getMeasuredHeight()) / 2, paddingTop);
                    }
                    int i18 = iMax2;
                    measuredHeight = paddingTop;
                    paddingTop = i18;
                    int i19 = paddingLeft + scrollX;
                    childAt.layout(i19, paddingTop, childAt.getMeasuredWidth() + i19, paddingTop + childAt.getMeasuredHeight());
                    i8++;
                    paddingTop = measuredHeight;
                    paddingLeft = measuredWidth;
                }
            }
        }
        int i20 = (i6 - paddingLeft) - paddingRight;
        for (int i21 = 0; i21 < childCount; i21++) {
            View childAt2 = getChildAt(i21);
            if (childAt2.getVisibility() != 8) {
                g gVar2 = (g) childAt2.getLayoutParams();
                if (!gVar2.a && (fVarInfoForChild = infoForChild(childAt2)) != null) {
                    float f2 = i20;
                    int i22 = ((int) (fVarInfoForChild.e * f2)) + paddingLeft;
                    if (gVar2.d) {
                        gVar2.d = DEBUG;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (f2 * gVar2.c), 1073741824), View.MeasureSpec.makeMeasureSpec((i7 - paddingTop) - paddingBottom, 1073741824));
                    }
                    childAt2.layout(i22, paddingTop, childAt2.getMeasuredWidth() + i22, childAt2.getMeasuredHeight() + paddingTop);
                }
            }
        }
        this.mTopPageBounds = paddingTop;
        this.mBottomPageBounds = i7 - paddingBottom;
        this.mDecorChildCount = i8;
        if (this.mFirstLayout) {
            int i23 = this.mCurItem;
            z2 = DEBUG;
            s(i23, DEBUG, 0, DEBUG);
        } else {
            z2 = DEBUG;
        }
        this.mFirstLayout = z2;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0082 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x0085  */
    /* JADX WARN: Code duplicated, block: B:36:0x0089  */
    /* JADX WARN: Code duplicated, block: B:39:0x008e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x0091  */
    /* JADX WARN: Code duplicated, block: B:42:0x0093  */
    /* JADX WARN: Code duplicated, block: B:45:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:46:0x00a8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:47:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:66:0x00af A[SYNTHETIC] */
    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        g gVar;
        g gVar2;
        int i4;
        int i5;
        int i6;
        setMeasuredDimension(View.getDefaultSize(0, i2), View.getDefaultSize(0, i3));
        int measuredWidth = getMeasuredWidth();
        this.mGutterSize = Math.min(measuredWidth / 10, this.mDefaultGutterSize);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i7 = 0;
        while (true) {
            boolean z = true;
            int i8 = 1073741824;
            if (i7 >= childCount) {
                break;
            }
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8 && (gVar2 = (g) childAt.getLayoutParams()) != null && gVar2.a) {
                int i9 = gVar2.b;
                int i10 = i9 & 7;
                int i11 = i9 & R$styleable.AppCompatTheme_tooltipForegroundColor;
                boolean z2 = (i11 == 48 || i11 == 80) ? true : DEBUG;
                if (i10 != 3 && i10 != 5) {
                    z = DEBUG;
                }
                int i12 = Integer.MIN_VALUE;
                if (z2) {
                    i12 = 1073741824;
                } else {
                    i4 = z ? 1073741824 : Integer.MIN_VALUE;
                    i5 = ((ViewGroup.LayoutParams) gVar2).width;
                    if (i5 != -2) {
                        if (i5 == -1) {
                            i5 = paddingLeft;
                        }
                        i12 = 1073741824;
                    } else {
                        i5 = paddingLeft;
                    }
                    i6 = ((ViewGroup.LayoutParams) gVar2).height;
                    if (i6 != -2) {
                        i6 = measuredHeight;
                        i8 = i4;
                    } else if (i6 == -1) {
                        i6 = measuredHeight;
                    }
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i5, i12), View.MeasureSpec.makeMeasureSpec(i6, i8));
                    if (z2) {
                        measuredHeight -= childAt.getMeasuredHeight();
                    } else if (z) {
                        paddingLeft -= childAt.getMeasuredWidth();
                    }
                }
                i5 = ((ViewGroup.LayoutParams) gVar2).width;
                if (i5 != -2) {
                    if (i5 == -1) {
                        i5 = paddingLeft;
                    }
                    i12 = 1073741824;
                } else {
                    i5 = paddingLeft;
                }
                i6 = ((ViewGroup.LayoutParams) gVar2).height;
                if (i6 != -2) {
                    i6 = measuredHeight;
                    i8 = i4;
                } else if (i6 == -1) {
                    i6 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i5, i12), View.MeasureSpec.makeMeasureSpec(i6, i8));
                if (z2) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i7++;
        }
        this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.mInLayout = true;
        populate();
        this.mInLayout = DEBUG;
        int childCount2 = getChildCount();
        for (int i13 = 0; i13 < childCount2; i13++) {
            View childAt2 = getChildAt(i13);
            if (childAt2.getVisibility() != 8 && ((gVar = (g) childAt2.getLayoutParams()) == null || !gVar.a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * gVar.c), 1073741824), this.mChildHeightMeasureSpec);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0063  */
    protected void onPageScrolled(int i2, float f2, int i3) {
        int iMax;
        int width;
        int left;
        if (this.mDecorChildCount > 0) {
            int scrollX = getScrollX();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int width2 = getWidth();
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                g gVar = (g) childAt.getLayoutParams();
                if (gVar.a) {
                    int i5 = gVar.b & 7;
                    if (i5 != 1) {
                        if (i5 == 3) {
                            width = childAt.getWidth() + paddingLeft;
                        } else if (i5 != 5) {
                            width = paddingLeft;
                        } else {
                            iMax = (width2 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                        }
                        left = (paddingLeft + scrollX) - childAt.getLeft();
                        if (left != 0) {
                            childAt.offsetLeftAndRight(left);
                        }
                        paddingLeft = width;
                    } else {
                        iMax = Math.max((width2 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                    }
                    int i6 = iMax;
                    width = paddingLeft;
                    paddingLeft = i6;
                    left = (paddingLeft + scrollX) - childAt.getLeft();
                    if (left != 0) {
                        childAt.offsetLeftAndRight(left);
                    }
                    paddingLeft = width;
                }
            }
        }
        d(i2, f2, i3);
        this.mCalledSuper = true;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        int i5;
        f fVarInfoForChild;
        int childCount = getChildCount();
        if ((i2 & 2) != 0) {
            i4 = childCount;
            i3 = 0;
            i5 = 1;
        } else {
            i3 = childCount - 1;
            i4 = -1;
            i5 = -1;
        }
        while (i3 != i4) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (fVarInfoForChild = infoForChild(childAt)) != null && fVarInfoForChild.b == this.mCurItem && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i3 += i5;
        }
        return DEBUG;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof m)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        m mVar = (m) parcelable;
        super.onRestoreInstanceState(mVar.getSuperState());
        androidx.viewpager.widget.a aVar = this.mAdapter;
        if (aVar != null) {
            aVar.restoreState(mVar.b, mVar.c);
            setCurrentItemInternal(mVar.a, DEBUG, true);
        } else {
            this.mRestoredCurItem = mVar.a;
            this.mRestoredAdapterState = mVar.b;
            this.mRestoredClassLoader = mVar.c;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.os.Parcelable, androidx.viewpager.widget.ViewPager$m] */
    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        ?? mVar = new m(super.onSaveInstanceState());
        mVar.a = this.mCurItem;
        androidx.viewpager.widget.a aVar = this.mAdapter;
        if (aVar != null) {
            mVar.b = aVar.saveState();
        }
        return mVar;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.mPageMargin;
            o(i2, i4, i6, i6);
        }
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00dc  */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        androidx.viewpager.widget.a aVar;
        if (this.mFakeDragging) {
            return true;
        }
        int action = motionEvent.getAction();
        boolean zR = DEBUG;
        if ((action == 0 && motionEvent.getEdgeFlags() != 0) || (aVar = this.mAdapter) == null || aVar.getCount() == 0) {
            return DEBUG;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int action2 = motionEvent.getAction() & 255;
        if (action2 == 0) {
            this.mScroller.abortAnimation();
            this.mPopulatePending = DEBUG;
            populate();
            float x = motionEvent.getX();
            this.mInitialMotionX = x;
            this.mLastMotionX = x;
            float y = motionEvent.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mActivePointerId = motionEvent.getPointerId(0);
        } else if (action2 != 1) {
            if (action2 != 2) {
                if (action2 != 3) {
                    if (action2 == 5) {
                        int actionIndex = motionEvent.getActionIndex();
                        this.mLastMotionX = motionEvent.getX(actionIndex);
                        this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                    } else if (action2 == 6) {
                        l(motionEvent);
                        this.mLastMotionX = motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId));
                    }
                } else if (this.mIsBeingDragged) {
                    s(this.mCurItem, true, 0, DEBUG);
                    zR = r();
                }
            } else if (!this.mIsBeingDragged) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                if (iFindPointerIndex == -1) {
                    zR = r();
                } else {
                    float x2 = motionEvent.getX(iFindPointerIndex);
                    float fAbs = Math.abs(x2 - this.mLastMotionX);
                    float y2 = motionEvent.getY(iFindPointerIndex);
                    float fAbs2 = Math.abs(y2 - this.mLastMotionY);
                    if (fAbs > this.mTouchSlop && fAbs > fAbs2) {
                        this.mIsBeingDragged = true;
                        q(true);
                        float f2 = this.mInitialMotionX;
                        this.mLastMotionX = x2 - f2 > 0.0f ? f2 + this.mTouchSlop : f2 - this.mTouchSlop;
                        this.mLastMotionY = y2;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                    if (this.mIsBeingDragged) {
                        zR = false | n(motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)));
                    }
                }
            } else if (this.mIsBeingDragged) {
                zR = false | n(motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)));
            }
        } else if (this.mIsBeingDragged) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
            int xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
            this.mPopulatePending = true;
            int clientWidth = getClientWidth();
            int scrollX = getScrollX();
            f fVarI = i();
            float f3 = clientWidth;
            setCurrentItemInternal(c(fVarI.b, ((scrollX / f3) - fVarI.e) / (fVarI.d + (this.mPageMargin / f3)), xVelocity, (int) (motionEvent.getX(motionEvent.findPointerIndex(this.mActivePointerId)) - this.mInitialMotionX)), true, true, xVelocity);
            zR = r();
        }
        if (zR) {
            c1.b0(this);
        }
        return true;
    }

    boolean pageLeft() {
        int i2 = this.mCurItem;
        if (i2 <= 0) {
            return DEBUG;
        }
        setCurrentItem(i2 - 1, true);
        return true;
    }

    boolean pageRight() {
        androidx.viewpager.widget.a aVar = this.mAdapter;
        if (aVar == null || this.mCurItem >= aVar.getCount() - 1) {
            return DEBUG;
        }
        setCurrentItem(this.mCurItem + 1, true);
        return true;
    }

    void populate() {
        populate(this.mCurItem);
    }

    public void removeOnAdapterChangeListener(i iVar) {
        List<i> list = this.mAdapterChangeListeners;
        if (list != null) {
            list.remove(iVar);
        }
    }

    public void removeOnPageChangeListener(j jVar) {
        List<j> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.remove(jVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(androidx.viewpager.widget.a aVar) {
        androidx.viewpager.widget.a aVar2 = this.mAdapter;
        if (aVar2 != null) {
            aVar2.setViewPagerObserver(null);
            this.mAdapter.startUpdate((ViewGroup) this);
            for (int i2 = 0; i2 < this.mItems.size(); i2++) {
                f fVar = this.mItems.get(i2);
                this.mAdapter.destroyItem((ViewGroup) this, fVar.b, fVar.a);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            p();
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        androidx.viewpager.widget.a aVar3 = this.mAdapter;
        this.mAdapter = aVar;
        this.mExpectedAdapterCount = 0;
        if (aVar != null) {
            if (this.mObserver == null) {
                this.mObserver = new l();
            }
            this.mAdapter.setViewPagerObserver(this.mObserver);
            this.mPopulatePending = DEBUG;
            boolean z = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                setCurrentItemInternal(this.mRestoredCurItem, DEBUG, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (z) {
                requestLayout();
            } else {
                populate();
            }
        }
        List<i> list = this.mAdapterChangeListeners;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.mAdapterChangeListeners.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.mAdapterChangeListeners.get(i3).onAdapterChanged(this, aVar3, aVar);
        }
    }

    public void setCurrentItem(int i2) {
        this.mPopulatePending = DEBUG;
        setCurrentItemInternal(i2, !this.mFirstLayout, DEBUG);
    }

    void setCurrentItemInternal(int i2, boolean z, boolean z2) {
        setCurrentItemInternal(i2, z, z2, 0);
    }

    j setInternalPageChangeListener(j jVar) {
        j jVar2 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = jVar;
        return jVar2;
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Requested offscreen page limit ");
            sb.append(i2);
            sb.append(" too small; defaulting to ");
            sb.append(1);
            i2 = 1;
        }
        if (i2 != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = i2;
            populate();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(j jVar) {
        this.mOnPageChangeListener = jVar;
    }

    public void setPageMargin(int i2) {
        int i3 = this.mPageMargin;
        this.mPageMargin = i2;
        int width = getWidth();
        o(width, width, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.mMarginDrawable = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null ? true : DEBUG);
        invalidate();
    }

    public void setPageTransformer(boolean z, k kVar) {
        setPageTransformer(z, kVar, 2);
    }

    void setScrollState(int i2) {
        if (this.mScrollState == i2) {
            return;
        }
        this.mScrollState = i2;
        f(i2);
    }

    void smoothScrollTo(int i2, int i3) {
        smoothScrollTo(i2, i3, 0);
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        if (super.verifyDrawable(drawable) || drawable == this.mMarginDrawable) {
            return true;
        }
        return DEBUG;
    }

    public static class g extends ViewGroup.LayoutParams {
        public boolean a;
        public int b;
        float c;
        boolean d;
        int e;
        int f;

        public g() {
            super(-1, -1);
            this.c = 0.0f;
        }

        public g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.c = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.LAYOUT_ATTRS);
            this.b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    /* JADX WARN: Code duplicated, block: B:64:0x00f7 A[PHI: r7 r10 r15
      0x00f7: PHI (r7v6 int) = (r7v5 int), (r7v4 int), (r7v9 int) binds: [B:62:0x00ec, B:59:0x00d6, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r10v9 int) = (r10v1 int), (r10v8 int), (r10v12 int) binds: [B:62:0x00ec, B:59:0x00d6, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]
      0x00f7: PHI (r15v7 float) = (r15v5 float), (r15v6 float), (r15v4 float) binds: [B:62:0x00ec, B:59:0x00d6, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]] */
    void populate(int i2) {
        f fVarInfoForPosition;
        String hexString;
        f fVarAddNewItem;
        f fVarInfoForChild;
        f fVar;
        int i3 = this.mCurItem;
        if (i3 != i2) {
            fVarInfoForPosition = infoForPosition(i3);
            this.mCurItem = i2;
        } else {
            fVarInfoForPosition = null;
        }
        if (this.mAdapter == null) {
            t();
            return;
        }
        if (this.mPopulatePending) {
            t();
            return;
        }
        if (getWindowToken() == null) {
            return;
        }
        this.mAdapter.startUpdate((ViewGroup) this);
        int i4 = this.mOffscreenPageLimit;
        int iMax = Math.max(0, this.mCurItem - i4);
        int count = this.mAdapter.getCount();
        int iMin = Math.min(count - 1, this.mCurItem + i4);
        if (count != this.mExpectedAdapterCount) {
            try {
                hexString = getResources().getResourceName(getId());
            } catch (Resources.NotFoundException unused) {
                hexString = Integer.toHexString(getId());
            }
            throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.mExpectedAdapterCount + ", found: " + count + " Pager id: " + hexString + " Pager class: " + getClass() + " Problematic adapter: " + this.mAdapter.getClass());
        }
        int i5 = 0;
        while (true) {
            if (i5 < this.mItems.size()) {
                fVarAddNewItem = this.mItems.get(i5);
                int i6 = fVarAddNewItem.b;
                int i7 = this.mCurItem;
                if (i6 >= i7) {
                    if (i6 != i7) {
                        break;
                    } else {
                        break;
                    }
                }
                i5++;
            }
            fVarAddNewItem = null;
            break;
        }
        if (fVarAddNewItem == null && count > 0) {
            fVarAddNewItem = addNewItem(this.mCurItem, i5);
        }
        if (fVarAddNewItem != null) {
            int i8 = i5 - 1;
            f fVar2 = i8 >= 0 ? this.mItems.get(i8) : null;
            int clientWidth = getClientWidth();
            float paddingLeft = clientWidth <= 0 ? 0.0f : (2.0f - fVarAddNewItem.d) + (getPaddingLeft() / clientWidth);
            float f2 = 0.0f;
            for (int i9 = this.mCurItem - 1; i9 >= 0; i9--) {
                if (f2 < paddingLeft || i9 >= iMax) {
                    if (fVar2 == null || i9 != fVar2.b) {
                        f2 += addNewItem(i9, i8 + 1).d;
                        i5++;
                        if (i8 >= 0) {
                            fVar = this.mItems.get(i8);
                        } else {
                            fVar = null;
                        }
                    } else {
                        f2 += fVar2.d;
                        i8--;
                        if (i8 >= 0) {
                            fVar = this.mItems.get(i8);
                        } else {
                            fVar = null;
                        }
                    }
                    fVar2 = fVar;
                } else {
                    if (fVar2 == null) {
                        break;
                    }
                    if (i9 == fVar2.b && !fVar2.c) {
                        this.mItems.remove(i8);
                        this.mAdapter.destroyItem((ViewGroup) this, i9, fVar2.a);
                        i8--;
                        i5--;
                        if (i8 >= 0) {
                            fVar = this.mItems.get(i8);
                        } else {
                            fVar = null;
                        }
                        fVar2 = fVar;
                    }
                }
            }
            float f3 = fVarAddNewItem.d;
            int i10 = i5 + 1;
            if (f3 < 2.0f) {
                f fVar3 = i10 < this.mItems.size() ? this.mItems.get(i10) : null;
                float paddingRight = clientWidth <= 0 ? 0.0f : (getPaddingRight() / clientWidth) + 2.0f;
                int i11 = this.mCurItem;
                while (true) {
                    i11++;
                    if (i11 >= count) {
                        break;
                    }
                    if (f3 >= paddingRight && i11 > iMin) {
                        if (fVar3 == null) {
                            break;
                        }
                        if (i11 == fVar3.b && !fVar3.c) {
                            this.mItems.remove(i10);
                            this.mAdapter.destroyItem((ViewGroup) this, i11, fVar3.a);
                            if (i10 < this.mItems.size()) {
                                fVar3 = this.mItems.get(i10);
                            }
                        }
                    } else if (fVar3 == null || i11 != fVar3.b) {
                        f fVarAddNewItem2 = addNewItem(i11, i10);
                        i10++;
                        f3 += fVarAddNewItem2.d;
                        fVar3 = i10 < this.mItems.size() ? this.mItems.get(i10) : null;
                    } else {
                        f3 += fVar3.d;
                        i10++;
                        if (i10 < this.mItems.size()) {
                            fVar3 = this.mItems.get(i10);
                        }
                    }
                }
            }
            a(fVarAddNewItem, i5, fVarInfoForPosition);
            this.mAdapter.setPrimaryItem((ViewGroup) this, this.mCurItem, fVarAddNewItem.a);
        }
        this.mAdapter.finishUpdate((ViewGroup) this);
        int childCount = getChildCount();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            g gVar = (g) childAt.getLayoutParams();
            gVar.f = i12;
            if (!gVar.a && gVar.c == 0.0f && (fVarInfoForChild = infoForChild(childAt)) != null) {
                gVar.c = fVarInfoForChild.d;
                gVar.e = fVarInfoForChild.b;
            }
        }
        t();
        if (hasFocus()) {
            View viewFindFocus = findFocus();
            f fVarInfoForAnyChild = viewFindFocus != null ? infoForAnyChild(viewFindFocus) : null;
            if (fVarInfoForAnyChild == null || fVarInfoForAnyChild.b != this.mCurItem) {
                for (int i13 = 0; i13 < getChildCount(); i13++) {
                    View childAt2 = getChildAt(i13);
                    f fVarInfoForChild2 = infoForChild(childAt2);
                    if (fVarInfoForChild2 != null && fVarInfoForChild2.b == this.mCurItem && childAt2.requestFocus(2)) {
                        return;
                    }
                }
            }
        }
    }

    void setCurrentItemInternal(int i2, boolean z, boolean z2, int i3) {
        androidx.viewpager.widget.a aVar = this.mAdapter;
        boolean z3 = DEBUG;
        if (aVar == null || aVar.getCount() <= 0) {
            setScrollingCacheEnabled(DEBUG);
            return;
        }
        if (!z2 && this.mCurItem == i2 && this.mItems.size() != 0) {
            setScrollingCacheEnabled(DEBUG);
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= this.mAdapter.getCount()) {
            i2 = this.mAdapter.getCount() - 1;
        }
        int i4 = this.mOffscreenPageLimit;
        int i5 = this.mCurItem;
        if (i2 > i5 + i4 || i2 < i5 - i4) {
            for (int i6 = 0; i6 < this.mItems.size(); i6++) {
                this.mItems.get(i6).c = true;
            }
        }
        if (this.mCurItem != i2) {
            z3 = true;
        }
        if (!this.mFirstLayout) {
            populate(i2);
            s(i2, z, i3, z3);
        } else {
            this.mCurItem = i2;
            if (z3) {
                e(i2);
            }
            requestLayout();
        }
    }

    public void setPageTransformer(boolean z, k kVar, int i2) {
        boolean z2 = kVar != null ? true : DEBUG;
        setChildrenDrawingOrderEnabled(z2);
        if (z2) {
            this.mDrawingOrder = z ? 2 : 1;
            this.mPageTransformerLayerType = i2;
        } else {
            this.mDrawingOrder = 0;
        }
        if (z2) {
            populate();
        }
    }

    void smoothScrollTo(int i2, int i3, int i4) {
        int scrollX;
        int iAbs;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(DEBUG);
            return;
        }
        Scroller scroller = this.mScroller;
        if ((scroller == null || scroller.isFinished()) ? DEBUG : true) {
            scrollX = this.mIsScrollStarted ? this.mScroller.getCurrX() : this.mScroller.getStartX();
            this.mScroller.abortAnimation();
            setScrollingCacheEnabled(DEBUG);
        } else {
            scrollX = getScrollX();
        }
        int i5 = scrollX;
        int scrollY = getScrollY();
        int i6 = i2 - i5;
        int i7 = i3 - scrollY;
        if (i6 == 0 && i7 == 0) {
            b(DEBUG);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i8 = clientWidth / 2;
        float f2 = clientWidth;
        float f3 = i8;
        float fDistanceInfluenceForSnapDuration = f3 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (Math.abs(i6) * 1.0f) / f2)) * f3);
        int iAbs2 = Math.abs(i4);
        if (iAbs2 > 0) {
            iAbs = Math.round(Math.abs(fDistanceInfluenceForSnapDuration / iAbs2) * 1000.0f) * 4;
        } else {
            iAbs = (int) (((Math.abs(i6) / ((f2 * this.mAdapter.getPageWidth(this.mCurItem)) + this.mPageMargin)) + 1.0f) * 100.0f);
        }
        int iMin = Math.min(iAbs, MAX_SETTLE_DURATION);
        this.mIsScrollStarted = DEBUG;
        this.mScroller.startScroll(i5, scrollY, i6, i7, iMin);
        c1.b0(this);
    }

    public void setCurrentItem(int i2, boolean z) {
        this.mPopulatePending = DEBUG;
        setCurrentItemInternal(i2, z, DEBUG);
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(androidx.core.content.a.getDrawable(getContext(), i2));
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mItems = new ArrayList<>();
        this.mTempItem = new f();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = DEBUG;
        this.mEndScrollRunnable = new c();
        this.mScrollState = 0;
        initViewPager();
    }
}
