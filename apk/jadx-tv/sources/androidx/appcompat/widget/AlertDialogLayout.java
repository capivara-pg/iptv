package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class AlertDialogLayout extends u1 {
    public AlertDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void e(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                u1.a aVar = (u1.a) childAt.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).width == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) aVar).height;
                    ((ViewGroup.MarginLayoutParams) aVar).height = childAt.getMeasuredHeight();
                    measureChildWithMargins(childAt, iMakeMeasureSpec, 0, i2, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).height = i4;
                }
            }
        }
    }

    private void f(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    private static int g(View view) {
        int iA = androidx.core.view.c1.A(view);
        if (iA > 0) {
            return iA;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return g(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }

    private boolean h(int i, int i2) {
        int iCombineMeasuredStates;
        int iG;
        int measuredHeight;
        int measuredHeight2;
        int childCount = getChildCount();
        View view = null;
        View view2 = null;
        View view3 = null;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                int id = childAt.getId();
                if (id == R$id.topPanel) {
                    view = childAt;
                } else if (id == R$id.buttonPanel) {
                    view2 = childAt;
                } else {
                    if ((id != R$id.contentPanel && id != R$id.customPanel) || view3 != null) {
                        return false;
                    }
                    view3 = childAt;
                }
            }
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (view != null) {
            view.measure(i, 0);
            paddingTop += view.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(0, view.getMeasuredState());
        } else {
            iCombineMeasuredStates = 0;
        }
        if (view2 != null) {
            view2.measure(i, 0);
            iG = g(view2);
            measuredHeight = view2.getMeasuredHeight() - iG;
            paddingTop += iG;
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
        } else {
            iG = 0;
            measuredHeight = 0;
        }
        if (view3 != null) {
            view3.measure(i, mode == 0 ? 0 : View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingTop), mode));
            measuredHeight2 = view3.getMeasuredHeight();
            paddingTop += measuredHeight2;
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
        } else {
            measuredHeight2 = 0;
        }
        int i4 = size - paddingTop;
        if (view2 != null) {
            int i5 = paddingTop - iG;
            int iMin = Math.min(i4, measuredHeight);
            if (iMin > 0) {
                i4 -= iMin;
                iG += iMin;
            }
            view2.measure(i, View.MeasureSpec.makeMeasureSpec(iG, 1073741824));
            paddingTop = i5 + view2.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
        }
        if (view3 != null && i4 > 0) {
            view3.measure(i, View.MeasureSpec.makeMeasureSpec(measuredHeight2 + i4, mode));
            paddingTop = (paddingTop - measuredHeight2) + view3.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
        }
        int iMax = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt2 = getChildAt(i6);
            if (childAt2.getVisibility() != 8) {
                iMax = Math.max(iMax, childAt2.getMeasuredWidth());
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(iMax + getPaddingLeft() + getPaddingRight(), i, iCombineMeasuredStates), View.resolveSizeAndState(paddingTop, i2, 0));
        if (mode2 == 1073741824) {
            return true;
        }
        e(childCount, i2);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00a8  */
    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int paddingLeft = getPaddingLeft();
        int i8 = i3 - i;
        int paddingRight = i8 - getPaddingRight();
        int paddingRight2 = (i8 - paddingLeft) - getPaddingRight();
        int measuredHeight = getMeasuredHeight();
        int childCount = getChildCount();
        int gravity = getGravity();
        int i9 = gravity & R$styleable.AppCompatTheme_tooltipForegroundColor;
        int i10 = gravity & 8388615;
        int paddingTop = i9 != 16 ? i9 != 80 ? getPaddingTop() : ((getPaddingTop() + i4) - i2) - measuredHeight : getPaddingTop() + (((i4 - i2) - measuredHeight) / 2);
        Drawable dividerDrawable = getDividerDrawable();
        int intrinsicHeight = dividerDrawable == null ? 0 : dividerDrawable.getIntrinsicHeight();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt != null && childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight2 = childAt.getMeasuredHeight();
                u1.a aVar = (u1.a) childAt.getLayoutParams();
                int i12 = aVar.b;
                if (i12 < 0) {
                    i12 = i10;
                }
                int iB = androidx.core.view.j.b(i12, androidx.core.view.c1.z(this)) & 7;
                if (iB != 1) {
                    if (iB != 5) {
                        i7 = ((ViewGroup.MarginLayoutParams) aVar).leftMargin + paddingLeft;
                    } else {
                        i5 = paddingRight - measuredWidth;
                        i6 = ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                    }
                    if (hasDividerBeforeChildAt(i11)) {
                        paddingTop += intrinsicHeight;
                    }
                    int i13 = paddingTop + ((ViewGroup.MarginLayoutParams) aVar).topMargin;
                    f(childAt, i7, i13, measuredWidth, measuredHeight2);
                    paddingTop = i13 + measuredHeight2 + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
                } else {
                    i5 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + ((ViewGroup.MarginLayoutParams) aVar).leftMargin;
                    i6 = ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
                }
                i7 = i5 - i6;
                if (hasDividerBeforeChildAt(i11)) {
                    paddingTop += intrinsicHeight;
                }
                int i14 = paddingTop + ((ViewGroup.MarginLayoutParams) aVar).topMargin;
                f(childAt, i7, i14, measuredWidth, measuredHeight2);
                paddingTop = i14 + measuredHeight2 + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
            }
        }
    }

    @Override // androidx.appcompat.widget.u1, android.view.View
    protected void onMeasure(int i, int i2) {
        if (h(i, i2)) {
            return;
        }
        super.onMeasure(i, i2);
    }
}
