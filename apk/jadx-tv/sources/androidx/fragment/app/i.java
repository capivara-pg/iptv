package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.core.view.c1;
import androidx.core.view.f2;
import androidx.fragment.R$styleable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class i extends FrameLayout {
    private ArrayList a;
    private ArrayList b;
    private View.OnApplyWindowInsetsListener c;
    private boolean d;

    i(Context context, AttributeSet attributeSet, o oVar) {
        String str;
        super(context, attributeSet);
        this.d = true;
        String classAttribute = attributeSet.getClassAttribute();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.e);
        classAttribute = classAttribute == null ? typedArrayObtainStyledAttributes.getString(R$styleable.FragmentContainerView_android_name) : classAttribute;
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.FragmentContainerView_android_tag);
        typedArrayObtainStyledAttributes.recycle();
        int id = getId();
        Fragment fragmentG0 = oVar.g0(id);
        if (classAttribute != null && fragmentG0 == null) {
            if (id <= 0) {
                if (string != null) {
                    str = " with tag " + string;
                } else {
                    str = "";
                }
                throw new IllegalStateException("FragmentContainerView must have an android:id to add Fragment " + classAttribute + str);
            }
            Fragment fragmentA = oVar.q0().a(context.getClassLoader(), classAttribute);
            fragmentA.onInflate(context, attributeSet, (Bundle) null);
            oVar.m().t(true).d(this, fragmentA, string).k();
        }
        oVar.U0(this);
    }

    private void a(View view) {
        ArrayList arrayList = this.b;
        if (arrayList == null || !arrayList.contains(view)) {
            return;
        }
        if (this.a == null) {
            this.a = new ArrayList();
        }
        this.a.add(view);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (o.z0(view) != null) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    @Override // android.view.ViewGroup
    protected boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        if (o.z0(view) != null) {
            return super.addViewInLayout(view, i, layoutParams, z);
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    @Override // android.view.ViewGroup, android.view.View
    public WindowInsets dispatchApplyWindowInsets(WindowInsets windowInsets) {
        f2 f2VarT = f2.t(windowInsets);
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = this.c;
        f2 f2VarT2 = onApplyWindowInsetsListener != null ? f2.t(onApplyWindowInsetsListener.onApplyWindowInsets(this, windowInsets)) : c1.X(this, f2VarT);
        if (!f2VarT2.m()) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                c1.g(getChildAt(i), f2VarT2);
            }
        }
        return windowInsets;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.d && this.a != null) {
            for (int i = 0; i < this.a.size(); i++) {
                super.drawChild(canvas, (View) this.a.get(i), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        ArrayList arrayList;
        if (!this.d || (arrayList = this.a) == null || arrayList.size() <= 0 || !this.a.contains(view)) {
            return super.drawChild(canvas, view, j);
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public void endViewTransition(View view) {
        ArrayList arrayList = this.b;
        if (arrayList != null) {
            arrayList.remove(view);
            ArrayList arrayList2 = this.a;
            if (arrayList2 != null && arrayList2.remove(view)) {
                this.d = true;
            }
        }
        super.endViewTransition(view);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        return windowInsets;
    }

    @Override // android.view.ViewGroup
    public void removeAllViewsInLayout() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            a(getChildAt(childCount));
        }
        super.removeAllViewsInLayout();
    }

    @Override // android.view.ViewGroup
    protected void removeDetachedView(View view, boolean z) {
        if (z) {
            a(view);
        }
        super.removeDetachedView(view, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        a(view);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i) {
        a(getChildAt(i));
        super.removeViewAt(i);
    }

    @Override // android.view.ViewGroup
    public void removeViewInLayout(View view) {
        a(view);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public void removeViews(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            a(getChildAt(i3));
        }
        super.removeViews(i, i2);
    }

    @Override // android.view.ViewGroup
    public void removeViewsInLayout(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            a(getChildAt(i3));
        }
        super.removeViewsInLayout(i, i2);
    }

    void setDrawDisappearingViewsLast(boolean z) {
        this.d = z;
    }

    @Override // android.view.ViewGroup
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    @Override // android.view.View
    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        this.c = onApplyWindowInsetsListener;
    }

    @Override // android.view.ViewGroup
    public void startViewTransition(View view) {
        if (view.getParent() == this) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            this.b.add(view);
        }
        super.startViewTransition(view);
    }
}
