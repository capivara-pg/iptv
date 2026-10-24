package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public abstract class u1 extends ViewGroup {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.widget.LinearLayoutCompat";
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    public u1(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void d(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                a aVar = (a) virtualChildAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).height == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) aVar).width;
                    ((ViewGroup.MarginLayoutParams) aVar).width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, iMakeMeasureSpec, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).width = i4;
                }
            }
        }
    }

    private void e(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                a aVar = (a) virtualChildAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).width == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) aVar).height;
                    ((ViewGroup.MarginLayoutParams) aVar).height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, iMakeMeasureSpec, 0, i2, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).height = i4;
                }
            }
        }
    }

    private void f(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    void drawDividersHorizontal(Canvas canvas) {
        int right;
        int left;
        int i;
        int virtualChildCount = getVirtualChildCount();
        boolean zB = y2.b(this);
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View virtualChildAt = getVirtualChildAt(i2);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i2)) {
                a aVar = (a) virtualChildAt.getLayoutParams();
                drawVerticalDivider(canvas, zB ? virtualChildAt.getRight() + ((ViewGroup.MarginLayoutParams) aVar).rightMargin : (virtualChildAt.getLeft() - ((ViewGroup.MarginLayoutParams) aVar).leftMargin) - this.mDividerWidth);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                a aVar2 = (a) virtualChildAt2.getLayoutParams();
                if (zB) {
                    left = virtualChildAt2.getLeft() - ((ViewGroup.MarginLayoutParams) aVar2).leftMargin;
                    i = this.mDividerWidth;
                    right = left - i;
                } else {
                    right = virtualChildAt2.getRight() + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin;
                }
            } else if (zB) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i = this.mDividerWidth;
                right = left - i;
            }
            drawVerticalDivider(canvas, right);
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; i++) {
            View virtualChildAt = getVirtualChildAt(i);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((ViewGroup.MarginLayoutParams) ((a) virtualChildAt.getLayoutParams())).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            drawHorizontalDivider(canvas, virtualChildAt2 == null ? (getHeight() - getPaddingBottom()) - this.mDividerHeight : virtualChildAt2.getBottom() + ((ViewGroup.MarginLayoutParams) ((a) virtualChildAt2.getLayoutParams())).bottomMargin);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.mBaselineAlignedChildIndex;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (i = this.mGravity & R$styleable.AppCompatTheme_tooltipForegroundColor) != 48) {
            if (i == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
            } else if (i == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
            }
        }
        return bottom + ((ViewGroup.MarginLayoutParams) ((a) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    protected boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:32:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:36:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:37:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:39:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:40:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:42:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:44:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:47:0x00f7  */
    void layoutHorizontal(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int i6;
        int i7;
        int baseline;
        int i8;
        int i9;
        int measuredHeight;
        boolean zB = y2.b(this);
        int paddingTop = getPaddingTop();
        int i10 = i4 - i2;
        int paddingBottom = i10 - getPaddingBottom();
        int paddingBottom2 = (i10 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i11 = this.mGravity;
        int i12 = i11 & R$styleable.AppCompatTheme_tooltipForegroundColor;
        boolean z = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        int iB = androidx.core.view.j.b(8388615 & i11, androidx.core.view.c1.z(this));
        if (iB != 1) {
            paddingLeft = iB != 5 ? getPaddingLeft() : ((getPaddingLeft() + i3) - i) - this.mTotalLength;
        } else {
            paddingLeft = getPaddingLeft() + (((i3 - i) - this.mTotalLength) / 2);
        }
        if (zB) {
            i5 = virtualChildCount - 1;
            i6 = -1;
        } else {
            i5 = 0;
            i6 = 1;
        }
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            int i13 = i5 + (i6 * childrenSkipCount);
            View virtualChildAt = getVirtualChildAt(i13);
            if (virtualChildAt == null) {
                paddingLeft += measureNullChild(i13);
            } else {
                if (virtualChildAt.getVisibility() != 8) {
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    int measuredHeight2 = virtualChildAt.getMeasuredHeight();
                    a aVar = (a) virtualChildAt.getLayoutParams();
                    int i14 = childrenSkipCount;
                    if (z) {
                        i7 = virtualChildCount;
                        baseline = ((ViewGroup.MarginLayoutParams) aVar).height != -1 ? virtualChildAt.getBaseline() : -1;
                        i8 = aVar.b;
                        if (i8 < 0) {
                            i8 = i12;
                        }
                        i9 = i8 & R$styleable.AppCompatTheme_tooltipForegroundColor;
                        if (i9 != 16) {
                            measuredHeight = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + ((ViewGroup.MarginLayoutParams) aVar).topMargin) - ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                        } else if (i9 != 48) {
                            measuredHeight = ((ViewGroup.MarginLayoutParams) aVar).topMargin + paddingTop;
                            if (baseline != -1) {
                                measuredHeight += iArr[1] - baseline;
                            }
                        } else if (i9 != 80) {
                            measuredHeight = paddingTop;
                        } else {
                            measuredHeight = (paddingBottom - measuredHeight2) - ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                            if (baseline != -1) {
                                measuredHeight -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - baseline);
                            }
                        }
                        if (hasDividerBeforeChildAt(i13)) {
                            paddingLeft += this.mDividerWidth;
                        }
                        int i15 = ((ViewGroup.MarginLayoutParams) aVar).leftMargin + paddingLeft;
                        f(virtualChildAt, i15 + getLocationOffset(virtualChildAt), measuredHeight, measuredWidth, measuredHeight2);
                        int nextLocationOffset = i15 + measuredWidth + ((ViewGroup.MarginLayoutParams) aVar).rightMargin + getNextLocationOffset(virtualChildAt);
                        childrenSkipCount = i14 + getChildrenSkipCount(virtualChildAt, i13);
                        paddingLeft = nextLocationOffset;
                    } else {
                        i7 = virtualChildCount;
                    }
                    i8 = aVar.b;
                    if (i8 < 0) {
                        i8 = i12;
                    }
                    i9 = i8 & R$styleable.AppCompatTheme_tooltipForegroundColor;
                    if (i9 != 16) {
                        measuredHeight = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + ((ViewGroup.MarginLayoutParams) aVar).topMargin) - ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                    } else if (i9 != 48) {
                        measuredHeight = ((ViewGroup.MarginLayoutParams) aVar).topMargin + paddingTop;
                        if (baseline != -1) {
                            measuredHeight += iArr[1] - baseline;
                        }
                    } else if (i9 != 80) {
                        measuredHeight = paddingTop;
                    } else {
                        measuredHeight = (paddingBottom - measuredHeight2) - ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                        if (baseline != -1) {
                            measuredHeight -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - baseline);
                        }
                    }
                    if (hasDividerBeforeChildAt(i13)) {
                        paddingLeft += this.mDividerWidth;
                    }
                    int i16 = ((ViewGroup.MarginLayoutParams) aVar).leftMargin + paddingLeft;
                    f(virtualChildAt, i16 + getLocationOffset(virtualChildAt), measuredHeight, measuredWidth, measuredHeight2);
                    int nextLocationOffset2 = i16 + measuredWidth + ((ViewGroup.MarginLayoutParams) aVar).rightMargin + getNextLocationOffset(virtualChildAt);
                    childrenSkipCount = i14 + getChildrenSkipCount(virtualChildAt, i13);
                    paddingLeft = nextLocationOffset2;
                }
                childrenSkipCount++;
                virtualChildCount = i7;
                i12 = i12;
                paddingTop = paddingTop;
            }
            i7 = virtualChildCount;
            childrenSkipCount++;
            virtualChildCount = i7;
            i12 = i12;
            paddingTop = paddingTop;
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x009f  */
    void layoutVertical(int i, int i2, int i3, int i4) {
        int paddingTop;
        int i5;
        int i6;
        int i7;
        int paddingLeft = getPaddingLeft();
        int i8 = i3 - i;
        int paddingRight = i8 - getPaddingRight();
        int paddingRight2 = (i8 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i9 = this.mGravity;
        int i10 = i9 & R$styleable.AppCompatTheme_tooltipForegroundColor;
        int i11 = i9 & 8388615;
        if (i10 != 16) {
            paddingTop = i10 != 80 ? getPaddingTop() : ((getPaddingTop() + i4) - i2) - this.mTotalLength;
        } else {
            paddingTop = getPaddingTop() + (((i4 - i2) - this.mTotalLength) / 2);
        }
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(childrenSkipCount);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                a aVar = (a) virtualChildAt.getLayoutParams();
                int i12 = aVar.b;
                if (i12 < 0) {
                    i12 = i11;
                }
                int iB = androidx.core.view.j.b(i12, androidx.core.view.c1.z(this)) & 7;
                if (iB != 1) {
                    if (iB != 5) {
                        i7 = ((ViewGroup.MarginLayoutParams) aVar).leftMargin + paddingLeft;
                    } else {
                        i5 = paddingRight - measuredWidth;
                        i6 = ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                    }
                    int i13 = i7;
                    if (hasDividerBeforeChildAt(childrenSkipCount)) {
                        paddingTop += this.mDividerHeight;
                    }
                    int i14 = paddingTop + ((ViewGroup.MarginLayoutParams) aVar).topMargin;
                    f(virtualChildAt, i13, i14 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                    int nextLocationOffset = i14 + measuredHeight + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin + getNextLocationOffset(virtualChildAt);
                    childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                    paddingTop = nextLocationOffset;
                } else {
                    i5 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + ((ViewGroup.MarginLayoutParams) aVar).leftMargin;
                    i6 = ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                }
                i7 = i5 - i6;
                int i15 = i7;
                if (hasDividerBeforeChildAt(childrenSkipCount)) {
                    paddingTop += this.mDividerHeight;
                }
                int i16 = paddingTop + ((ViewGroup.MarginLayoutParams) aVar).topMargin;
                f(virtualChildAt, i15, i16 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                int nextLocationOffset2 = i16 + measuredHeight + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin + getNextLocationOffset(virtualChildAt);
                childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                paddingTop = nextLocationOffset2;
            }
            childrenSkipCount++;
        }
    }

    void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* JADX WARN: Code duplicated, block: B:197:0x044c  */
    /* JADX WARN: Code duplicated, block: B:60:0x0173  */
    /* JADX WARN: Code duplicated, block: B:67:0x0195  */
    /* JADX WARN: Code duplicated, block: B:74:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:77:0x01c9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:81:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:84:0x01d9  */
    void measureHorizontal(int i, int i2) {
        int[] iArr;
        int i3;
        int iMax;
        int iMax2;
        int i4;
        int i5;
        int baseline;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z;
        boolean z2;
        View view;
        int i10;
        boolean z3;
        int i11;
        int measuredHeight;
        int childrenSkipCount;
        int baseline2;
        int i12;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] iArr2 = this.mMaxAscent;
        int[] iArr3 = this.mMaxDescent;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z4 = this.mBaselineAligned;
        boolean z5 = this.mUseLargestChild;
        int i13 = 1073741824;
        boolean z6 = mode == 1073741824;
        float f = 0.0f;
        int childrenSkipCount2 = 0;
        int iMax3 = 0;
        int iMax4 = 0;
        int iMax5 = 0;
        int iMax6 = 0;
        boolean z7 = false;
        int iCombineMeasuredStates = 0;
        boolean z8 = true;
        boolean z9 = false;
        while (true) {
            iArr = iArr3;
            if (childrenSkipCount2 >= virtualChildCount) {
                break;
            }
            View virtualChildAt = getVirtualChildAt(childrenSkipCount2);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(childrenSkipCount2);
            } else {
                if (virtualChildAt.getVisibility() == 8) {
                    childrenSkipCount2 += getChildrenSkipCount(virtualChildAt, childrenSkipCount2);
                } else {
                    if (hasDividerBeforeChildAt(childrenSkipCount2)) {
                        this.mTotalLength += this.mDividerWidth;
                    }
                    a aVar = (a) virtualChildAt.getLayoutParams();
                    float f2 = aVar.a;
                    float f3 = f + f2;
                    if (mode == i13 && ((ViewGroup.MarginLayoutParams) aVar).width == 0 && f2 > 0.0f) {
                        if (z6) {
                            this.mTotalLength += ((ViewGroup.MarginLayoutParams) aVar).leftMargin + ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                        } else {
                            int i14 = this.mTotalLength;
                            this.mTotalLength = Math.max(i14, ((ViewGroup.MarginLayoutParams) aVar).leftMargin + i14 + ((ViewGroup.MarginLayoutParams) aVar).rightMargin);
                        }
                        if (z4) {
                            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                            virtualChildAt.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                            i9 = childrenSkipCount2;
                            z = z5;
                            z2 = z4;
                            view = virtualChildAt;
                        } else {
                            i9 = childrenSkipCount2;
                            z = z5;
                            z2 = z4;
                            view = virtualChildAt;
                            i10 = 1073741824;
                            z7 = true;
                        }
                        if (mode2 == i10 && ((ViewGroup.MarginLayoutParams) aVar).height == -1) {
                            z3 = true;
                            z9 = true;
                        } else {
                            z3 = false;
                        }
                        i11 = ((ViewGroup.MarginLayoutParams) aVar).topMargin + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i11;
                        iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view.getMeasuredState());
                        if (z2 && (baseline2 = view.getBaseline()) != -1) {
                            i12 = aVar.b;
                            if (i12 < 0) {
                                i12 = this.mGravity;
                            }
                            int i15 = (((i12 & R$styleable.AppCompatTheme_tooltipForegroundColor) >> 4) & (-2)) >> 1;
                            iArr2[i15] = Math.max(iArr2[i15], baseline2);
                            iArr[i15] = Math.max(iArr[i15], measuredHeight - baseline2);
                        }
                        iMax4 = Math.max(iMax4, measuredHeight);
                        if (z8 || ((ViewGroup.MarginLayoutParams) aVar).height != -1) {
                            z8 = false;
                        } else {
                            z8 = true;
                        }
                        if (aVar.a > 0.0f) {
                            if (!z3) {
                                i11 = measuredHeight;
                            }
                            iMax6 = Math.max(iMax6, i11);
                        } else {
                            int i16 = iMax6;
                            if (!z3) {
                                i11 = measuredHeight;
                            }
                            iMax5 = Math.max(iMax5, i11);
                            iMax6 = i16;
                        }
                        int i17 = i9;
                        childrenSkipCount = getChildrenSkipCount(view, i17) + i17;
                        f = f3;
                    } else {
                        if (((ViewGroup.MarginLayoutParams) aVar).width != 0 || f2 <= 0.0f) {
                            i8 = Integer.MIN_VALUE;
                        } else {
                            ((ViewGroup.MarginLayoutParams) aVar).width = -2;
                            i8 = 0;
                        }
                        i9 = childrenSkipCount2;
                        int i18 = i8;
                        z = z5;
                        z2 = z4;
                        measureChildBeforeLayout(virtualChildAt, i9, i, f3 == 0.0f ? this.mTotalLength : 0, i2, 0);
                        if (i18 != Integer.MIN_VALUE) {
                            ((ViewGroup.MarginLayoutParams) aVar).width = i18;
                        }
                        int measuredWidth = virtualChildAt.getMeasuredWidth();
                        if (z6) {
                            view = virtualChildAt;
                            this.mTotalLength += ((ViewGroup.MarginLayoutParams) aVar).leftMargin + measuredWidth + ((ViewGroup.MarginLayoutParams) aVar).rightMargin + getNextLocationOffset(view);
                        } else {
                            view = virtualChildAt;
                            int i19 = this.mTotalLength;
                            this.mTotalLength = Math.max(i19, i19 + measuredWidth + ((ViewGroup.MarginLayoutParams) aVar).leftMargin + ((ViewGroup.MarginLayoutParams) aVar).rightMargin + getNextLocationOffset(view));
                        }
                        if (z) {
                            iMax3 = Math.max(measuredWidth, iMax3);
                        }
                    }
                    i10 = 1073741824;
                    if (mode2 == i10) {
                        z3 = false;
                    } else {
                        z3 = false;
                    }
                    i11 = ((ViewGroup.MarginLayoutParams) aVar).topMargin + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                    measuredHeight = view.getMeasuredHeight() + i11;
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view.getMeasuredState());
                    if (z2) {
                        i12 = aVar.b;
                        if (i12 < 0) {
                            i12 = this.mGravity;
                        }
                        int i110 = (((i12 & R$styleable.AppCompatTheme_tooltipForegroundColor) >> 4) & (-2)) >> 1;
                        iArr2[i110] = Math.max(iArr2[i110], baseline2);
                        iArr[i110] = Math.max(iArr[i110], measuredHeight - baseline2);
                    }
                    iMax4 = Math.max(iMax4, measuredHeight);
                    if (z8) {
                        z8 = false;
                    } else {
                        z8 = false;
                    }
                    if (aVar.a > 0.0f) {
                        if (!z3) {
                            i11 = measuredHeight;
                        }
                        iMax6 = Math.max(iMax6, i11);
                    } else {
                        int i111 = iMax6;
                        if (!z3) {
                            i11 = measuredHeight;
                        }
                        iMax5 = Math.max(iMax5, i11);
                        iMax6 = i111;
                    }
                    int i112 = i9;
                    childrenSkipCount = getChildrenSkipCount(view, i112) + i112;
                    f = f3;
                }
                childrenSkipCount2 = childrenSkipCount + 1;
                iArr3 = iArr;
                z5 = z;
                z4 = z2;
                i13 = 1073741824;
            }
            childrenSkipCount = childrenSkipCount2;
            z = z5;
            z2 = z4;
            childrenSkipCount2 = childrenSkipCount + 1;
            iArr3 = iArr;
            z5 = z;
            z4 = z2;
            i13 = 1073741824;
        }
        boolean z10 = z5;
        boolean z11 = z4;
        int i20 = iMax4;
        int i21 = iMax5;
        int i22 = iMax6;
        int i23 = iCombineMeasuredStates;
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        int i24 = iArr2[1];
        int iMax7 = (i24 == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) ? i20 : Math.max(i20, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(i24, iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        if (z10 && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.mTotalLength = 0;
            int childrenSkipCount3 = 0;
            while (childrenSkipCount3 < virtualChildCount) {
                View virtualChildAt2 = getVirtualChildAt(childrenSkipCount3);
                if (virtualChildAt2 == null) {
                    this.mTotalLength += measureNullChild(childrenSkipCount3);
                } else if (virtualChildAt2.getVisibility() == 8) {
                    childrenSkipCount3 += getChildrenSkipCount(virtualChildAt2, childrenSkipCount3);
                } else {
                    a aVar2 = (a) virtualChildAt2.getLayoutParams();
                    if (z6) {
                        this.mTotalLength += ((ViewGroup.MarginLayoutParams) aVar2).leftMargin + iMax3 + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin + getNextLocationOffset(virtualChildAt2);
                    } else {
                        int i25 = this.mTotalLength;
                        this.mTotalLength = Math.max(i25, i25 + iMax3 + ((ViewGroup.MarginLayoutParams) aVar2).leftMargin + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin + getNextLocationOffset(virtualChildAt2));
                    }
                    childrenSkipCount3++;
                    iMax7 = iMax7;
                }
                childrenSkipCount3++;
                iMax7 = iMax7;
            }
        }
        int i26 = iMax7;
        int paddingLeft = this.mTotalLength + getPaddingLeft() + getPaddingRight();
        this.mTotalLength = paddingLeft;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i, 0);
        int i27 = (16777215 & iResolveSizeAndState) - this.mTotalLength;
        if (z7 || (i27 != 0 && f > 0.0f)) {
            float f4 = this.mWeightSum;
            if (f4 > 0.0f) {
                f = f4;
            }
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            this.mTotalLength = 0;
            int i28 = i21;
            int iCombineMeasuredStates2 = i23;
            int iMax8 = -1;
            int i29 = 0;
            while (i29 < virtualChildCount) {
                View virtualChildAt3 = getVirtualChildAt(i29);
                if (virtualChildAt3 == null || virtualChildAt3.getVisibility() == 8) {
                    i4 = i27;
                    virtualChildCount = virtualChildCount;
                } else {
                    a aVar3 = (a) virtualChildAt3.getLayoutParams();
                    float f5 = aVar3.a;
                    if (f5 > 0.0f) {
                        int i30 = (int) ((i27 * f5) / f);
                        float f6 = f - f5;
                        int i31 = i27 - i30;
                        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + ((ViewGroup.MarginLayoutParams) aVar3).topMargin + ((ViewGroup.MarginLayoutParams) aVar3).bottomMargin, ((ViewGroup.MarginLayoutParams) aVar3).height);
                        if (((ViewGroup.MarginLayoutParams) aVar3).width == 0) {
                            i7 = 1073741824;
                            if (mode == 1073741824) {
                                if (i30 <= 0) {
                                    i30 = 0;
                                }
                                virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(i30, 1073741824), childMeasureSpec);
                            }
                            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, virtualChildAt3.getMeasuredState() & (-16777216));
                            f = f6;
                            i4 = i31;
                        } else {
                            i7 = 1073741824;
                        }
                        int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i30;
                        if (measuredWidth2 < 0) {
                            measuredWidth2 = 0;
                        }
                        virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, i7), childMeasureSpec);
                        iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, virtualChildAt3.getMeasuredState() & (-16777216));
                        f = f6;
                        i4 = i31;
                    } else {
                        i4 = i27;
                    }
                    if (z6) {
                        this.mTotalLength += virtualChildAt3.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) aVar3).leftMargin + ((ViewGroup.MarginLayoutParams) aVar3).rightMargin + getNextLocationOffset(virtualChildAt3);
                    } else {
                        int i32 = this.mTotalLength;
                        this.mTotalLength = Math.max(i32, virtualChildAt3.getMeasuredWidth() + i32 + ((ViewGroup.MarginLayoutParams) aVar3).leftMargin + ((ViewGroup.MarginLayoutParams) aVar3).rightMargin + getNextLocationOffset(virtualChildAt3));
                    }
                    boolean z12 = mode2 != 1073741824 && ((ViewGroup.MarginLayoutParams) aVar3).height == -1;
                    int i33 = ((ViewGroup.MarginLayoutParams) aVar3).topMargin + ((ViewGroup.MarginLayoutParams) aVar3).bottomMargin;
                    int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i33;
                    iMax8 = Math.max(iMax8, measuredHeight2);
                    if (!z12) {
                        i33 = measuredHeight2;
                    }
                    int iMax9 = Math.max(i28, i33);
                    if (z8) {
                        i5 = -1;
                        boolean z13 = ((ViewGroup.MarginLayoutParams) aVar3).height == -1;
                        if (z11 && (baseline = virtualChildAt3.getBaseline()) != i5) {
                            i6 = aVar3.b;
                            if (i6 < 0) {
                                i6 = this.mGravity;
                            }
                            int i34 = (((i6 & R$styleable.AppCompatTheme_tooltipForegroundColor) >> 4) & (-2)) >> 1;
                            iArr2[i34] = Math.max(iArr2[i34], baseline);
                            iArr[i34] = Math.max(iArr[i34], measuredHeight2 - baseline);
                        }
                        z8 = z13;
                        i28 = iMax9;
                        f = f;
                    } else {
                        i5 = -1;
                    }
                    if (z11) {
                        i6 = aVar3.b;
                        if (i6 < 0) {
                            i6 = this.mGravity;
                        }
                        int i35 = (((i6 & R$styleable.AppCompatTheme_tooltipForegroundColor) >> 4) & (-2)) >> 1;
                        iArr2[i35] = Math.max(iArr2[i35], baseline);
                        iArr[i35] = Math.max(iArr[i35], measuredHeight2 - baseline);
                    }
                    z8 = z13;
                    i28 = iMax9;
                    f = f;
                }
                i29++;
                i27 = i4;
                virtualChildCount = virtualChildCount;
            }
            i3 = virtualChildCount;
            this.mTotalLength += getPaddingLeft() + getPaddingRight();
            int i36 = iArr2[1];
            iMax = (i36 == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) ? iMax8 : Math.max(iMax8, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(i36, iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
            iMax2 = i28;
            i23 = iCombineMeasuredStates2;
        } else {
            iMax2 = Math.max(i21, i22);
            if (z10 && mode != 1073741824) {
                for (int i37 = 0; i37 < virtualChildCount; i37++) {
                    View virtualChildAt4 = getVirtualChildAt(i37);
                    if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((a) virtualChildAt4.getLayoutParams()).a > 0.0f) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(iMax3, 1073741824), View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i3 = virtualChildCount;
            iMax = i26;
        }
        if (z8 || mode2 == 1073741824) {
            iMax2 = iMax;
        }
        setMeasuredDimension(iResolveSizeAndState | (i23 & (-16777216)), View.resolveSizeAndState(Math.max(iMax2 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, i23 << 16));
        if (z9) {
            d(i3, i);
        }
    }

    int measureNullChild(int i) {
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:150:0x0323  */
    /* JADX WARN: Code duplicated, block: B:156:0x0330  */
    void measureVertical(int i, int i2) {
        int i3;
        int iCombineMeasuredStates;
        int iMax;
        int i4;
        int i5;
        int i6;
        boolean z;
        boolean z2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        View view;
        int iMax2;
        boolean z3;
        int iMax3;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i16 = this.mBaselineAlignedChildIndex;
        boolean z4 = this.mUseLargestChild;
        float f = 0.0f;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int iMax4 = 0;
        int i20 = 0;
        int childrenSkipCount = 0;
        boolean z5 = false;
        boolean z6 = true;
        boolean z7 = false;
        while (true) {
            int i21 = 8;
            int i22 = iMax4;
            if (childrenSkipCount >= virtualChildCount) {
                int i23 = i17;
                int i24 = i19;
                int i25 = i20;
                int i26 = virtualChildCount;
                int i27 = mode2;
                int iMax5 = i18;
                if (this.mTotalLength > 0) {
                    i3 = i26;
                    if (hasDividerBeforeChildAt(i3)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                } else {
                    i3 = i26;
                }
                if (z4 && (i27 == Integer.MIN_VALUE || i27 == 0)) {
                    this.mTotalLength = 0;
                    int childrenSkipCount2 = 0;
                    while (childrenSkipCount2 < i3) {
                        View virtualChildAt = getVirtualChildAt(childrenSkipCount2);
                        if (virtualChildAt == null) {
                            this.mTotalLength += measureNullChild(childrenSkipCount2);
                        } else if (virtualChildAt.getVisibility() == i21) {
                            childrenSkipCount2 += getChildrenSkipCount(virtualChildAt, childrenSkipCount2);
                        } else {
                            a aVar = (a) virtualChildAt.getLayoutParams();
                            int i28 = this.mTotalLength;
                            this.mTotalLength = Math.max(i28, i28 + i24 + ((ViewGroup.MarginLayoutParams) aVar).topMargin + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin + getNextLocationOffset(virtualChildAt));
                        }
                        childrenSkipCount2++;
                        i21 = 8;
                    }
                }
                int paddingTop = this.mTotalLength + getPaddingTop() + getPaddingBottom();
                this.mTotalLength = paddingTop;
                int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i2, 0);
                int i29 = (16777215 & iResolveSizeAndState) - this.mTotalLength;
                if (z5 || (i29 != 0 && f > 0.0f)) {
                    float f2 = this.mWeightSum;
                    if (f2 > 0.0f) {
                        f = f2;
                    }
                    this.mTotalLength = 0;
                    int i30 = i29;
                    int i31 = i25;
                    iCombineMeasuredStates = i23;
                    int i32 = 0;
                    while (i32 < i3) {
                        View virtualChildAt2 = getVirtualChildAt(i32);
                        if (virtualChildAt2.getVisibility() == 8) {
                            i4 = i30;
                        } else {
                            a aVar2 = (a) virtualChildAt2.getLayoutParams();
                            float f3 = aVar2.a;
                            if (f3 > 0.0f) {
                                int i33 = (int) ((i30 * f3) / f);
                                float f4 = f - f3;
                                i4 = i30 - i33;
                                int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + ((ViewGroup.MarginLayoutParams) aVar2).leftMargin + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin, ((ViewGroup.MarginLayoutParams) aVar2).width);
                                if (((ViewGroup.MarginLayoutParams) aVar2).height == 0) {
                                    i7 = 1073741824;
                                    if (i27 == 1073741824) {
                                        if (i33 <= 0) {
                                            i33 = 0;
                                        }
                                        virtualChildAt2.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(i33, 1073741824));
                                    }
                                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt2.getMeasuredState() & (-256));
                                    f = f4;
                                } else {
                                    i7 = 1073741824;
                                }
                                int measuredHeight = virtualChildAt2.getMeasuredHeight() + i33;
                                if (measuredHeight < 0) {
                                    measuredHeight = 0;
                                }
                                virtualChildAt2.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight, i7));
                                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, virtualChildAt2.getMeasuredState() & (-256));
                                f = f4;
                            } else {
                                i4 = i30;
                            }
                            int i34 = ((ViewGroup.MarginLayoutParams) aVar2).leftMargin + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin;
                            int measuredWidth = virtualChildAt2.getMeasuredWidth() + i34;
                            iMax5 = Math.max(iMax5, measuredWidth);
                            float f5 = f;
                            if (mode != 1073741824) {
                                i5 = iCombineMeasuredStates;
                                i6 = -1;
                                z = ((ViewGroup.MarginLayoutParams) aVar2).width == -1;
                                if (!z) {
                                    i34 = measuredWidth;
                                }
                                int iMax6 = Math.max(i31, i34);
                                if (z6 || ((ViewGroup.MarginLayoutParams) aVar2).width != i6) {
                                    z2 = false;
                                } else {
                                    z2 = true;
                                }
                                int i35 = this.mTotalLength;
                                this.mTotalLength = Math.max(i35, virtualChildAt2.getMeasuredHeight() + i35 + ((ViewGroup.MarginLayoutParams) aVar2).topMargin + ((ViewGroup.MarginLayoutParams) aVar2).bottomMargin + getNextLocationOffset(virtualChildAt2));
                                z6 = z2;
                                iCombineMeasuredStates = i5;
                                i31 = iMax6;
                                f = f5;
                            } else {
                                i5 = iCombineMeasuredStates;
                                i6 = -1;
                            }
                            if (!z) {
                                i34 = measuredWidth;
                            }
                            int iMax7 = Math.max(i31, i34);
                            if (z6) {
                                z2 = false;
                            } else {
                                z2 = false;
                            }
                            int i36 = this.mTotalLength;
                            this.mTotalLength = Math.max(i36, virtualChildAt2.getMeasuredHeight() + i36 + ((ViewGroup.MarginLayoutParams) aVar2).topMargin + ((ViewGroup.MarginLayoutParams) aVar2).bottomMargin + getNextLocationOffset(virtualChildAt2));
                            z6 = z2;
                            iCombineMeasuredStates = i5;
                            i31 = iMax7;
                            f = f5;
                        }
                        i32++;
                        i30 = i4;
                    }
                    this.mTotalLength += getPaddingTop() + getPaddingBottom();
                    iMax = i31;
                } else {
                    iMax = Math.max(i25, i22);
                    if (z4 && i27 != 1073741824) {
                        for (int i37 = 0; i37 < i3; i37++) {
                            View virtualChildAt3 = getVirtualChildAt(i37);
                            if (virtualChildAt3 != null && virtualChildAt3.getVisibility() != 8 && ((a) virtualChildAt3.getLayoutParams()).a > 0.0f) {
                                virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(virtualChildAt3.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i24, 1073741824));
                            }
                        }
                    }
                    iCombineMeasuredStates = i23;
                }
                if (z6 || mode == 1073741824) {
                    iMax = iMax5;
                }
                setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, iCombineMeasuredStates), iResolveSizeAndState);
                if (z7) {
                    e(i3, i2);
                    return;
                }
                return;
            }
            View virtualChildAt4 = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt4 == null) {
                this.mTotalLength += measureNullChild(childrenSkipCount);
                i11 = virtualChildCount;
                i12 = mode2;
                iMax4 = i22;
            } else {
                int i38 = i17;
                if (virtualChildAt4.getVisibility() == 8) {
                    childrenSkipCount += getChildrenSkipCount(virtualChildAt4, childrenSkipCount);
                    i11 = virtualChildCount;
                    iMax4 = i22;
                    i17 = i38;
                    i12 = mode2;
                } else {
                    if (hasDividerBeforeChildAt(childrenSkipCount)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                    a aVar3 = (a) virtualChildAt4.getLayoutParams();
                    float f6 = aVar3.a;
                    float f7 = f + f6;
                    if (mode2 == 1073741824 && ((ViewGroup.MarginLayoutParams) aVar3).height == 0 && f6 > 0.0f) {
                        int i39 = this.mTotalLength;
                        this.mTotalLength = Math.max(i39, ((ViewGroup.MarginLayoutParams) aVar3).topMargin + i39 + ((ViewGroup.MarginLayoutParams) aVar3).bottomMargin);
                        iMax2 = i19;
                        view = virtualChildAt4;
                        i14 = i20;
                        i9 = i38;
                        i10 = i18;
                        z5 = true;
                        i11 = virtualChildCount;
                        i12 = mode2;
                        i13 = i22;
                        i15 = childrenSkipCount;
                    } else {
                        int i40 = i18;
                        if (((ViewGroup.MarginLayoutParams) aVar3).height != 0 || f6 <= 0.0f) {
                            i8 = Integer.MIN_VALUE;
                        } else {
                            ((ViewGroup.MarginLayoutParams) aVar3).height = -2;
                            i8 = 0;
                        }
                        i9 = i38;
                        int i41 = i8;
                        i10 = i40;
                        int i42 = i19;
                        i11 = virtualChildCount;
                        i12 = mode2;
                        i13 = i22;
                        i14 = i20;
                        i15 = childrenSkipCount;
                        measureChildBeforeLayout(virtualChildAt4, childrenSkipCount, i, 0, i2, f7 == 0.0f ? this.mTotalLength : 0);
                        if (i41 != Integer.MIN_VALUE) {
                            ((ViewGroup.MarginLayoutParams) aVar3).height = i41;
                        }
                        int measuredHeight2 = virtualChildAt4.getMeasuredHeight();
                        int i43 = this.mTotalLength;
                        view = virtualChildAt4;
                        this.mTotalLength = Math.max(i43, i43 + measuredHeight2 + ((ViewGroup.MarginLayoutParams) aVar3).topMargin + ((ViewGroup.MarginLayoutParams) aVar3).bottomMargin + getNextLocationOffset(view));
                        iMax2 = z4 ? Math.max(measuredHeight2, i42) : i42;
                    }
                    if (i16 >= 0 && i16 == i15 + 1) {
                        this.mBaselineChildTop = this.mTotalLength;
                    }
                    if (i15 < i16 && aVar3.a > 0.0f) {
                        throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                    }
                    if (mode == 1073741824 || ((ViewGroup.MarginLayoutParams) aVar3).width != -1) {
                        z3 = false;
                    } else {
                        z3 = true;
                        z7 = true;
                    }
                    int i44 = ((ViewGroup.MarginLayoutParams) aVar3).leftMargin + ((ViewGroup.MarginLayoutParams) aVar3).rightMargin;
                    int measuredWidth2 = view.getMeasuredWidth() + i44;
                    int iMax8 = Math.max(i10, measuredWidth2);
                    int iCombineMeasuredStates2 = View.combineMeasuredStates(i9, view.getMeasuredState());
                    z6 = z6 && ((ViewGroup.MarginLayoutParams) aVar3).width == -1;
                    if (aVar3.a > 0.0f) {
                        if (!z3) {
                            i44 = measuredWidth2;
                        }
                        iMax4 = Math.max(i13, i44);
                        iMax3 = i14;
                    } else {
                        if (!z3) {
                            i44 = measuredWidth2;
                        }
                        iMax3 = Math.max(i14, i44);
                        iMax4 = i13;
                    }
                    int childrenSkipCount3 = getChildrenSkipCount(view, i15) + i15;
                    i19 = iMax2;
                    f = f7;
                    i20 = iMax3;
                    i17 = iCombineMeasuredStates2;
                    childrenSkipCount = childrenSkipCount3;
                    i18 = iMax8;
                }
            }
            childrenSkipCount++;
            mode2 = i12;
            virtualChildCount = i11;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i >= 0 && i < getChildCount()) {
            this.mBaselineAlignedChildIndex = i;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.mDivider) {
            return;
        }
        this.mDivider = drawable;
        if (drawable != null) {
            this.mDividerWidth = drawable.getIntrinsicWidth();
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & R$styleable.AppCompatTheme_tooltipForegroundColor) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.mGravity;
        if ((8388615 & i3) != i2) {
            this.mGravity = i2 | ((-8388616) & i3);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & R$styleable.AppCompatTheme_tooltipForegroundColor;
        int i3 = this.mGravity;
        if ((i3 & R$styleable.AppCompatTheme_tooltipForegroundColor) != i2) {
            this.mGravity = i2 | (i3 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public u1(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        r2 r2VarU = r2.u(context, attributeSet, R$styleable.x, i, 0);
        int iK = r2VarU.k(R$styleable.LinearLayoutCompat_android_orientation, -1);
        if (iK >= 0) {
            setOrientation(iK);
        }
        int iK2 = r2VarU.k(R$styleable.LinearLayoutCompat_android_gravity, -1);
        if (iK2 >= 0) {
            setGravity(iK2);
        }
        boolean zA = r2VarU.a(R$styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!zA) {
            setBaselineAligned(zA);
        }
        this.mWeightSum = r2VarU.i(R$styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = r2VarU.k(R$styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = r2VarU.a(R$styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(r2VarU.g(R$styleable.LinearLayoutCompat_divider));
        this.mShowDividers = r2VarU.k(R$styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = r2VarU.f(R$styleable.LinearLayoutCompat_dividerPadding, 0);
        r2VarU.v();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public a generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new a(-2, -2);
        }
        if (i == 1) {
            return new a(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    public static class a extends ViewGroup.MarginLayoutParams {
        public float a;
        public int b;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.b = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.y);
            this.a = typedArrayObtainStyledAttributes.getFloat(R$styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.b = typedArrayObtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public a(int i, int i2) {
            super(i, i2);
            this.b = -1;
            this.a = 0.0f;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.b = -1;
        }
    }
}
