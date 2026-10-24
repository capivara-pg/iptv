package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
abstract class a extends ViewGroup {
    protected final C0011a a;
    protected final Context b;
    protected ActionMenuView c;
    protected c d;
    protected int e;
    protected androidx.core.view.c1 f;
    private boolean g;
    private boolean h;

    /* JADX INFO: renamed from: androidx.appcompat.widget.a$a, reason: collision with other inner class name */
    protected class C0011a implements androidx.core.view.d1 {
        private boolean a = false;
        int b;

        protected C0011a() {
        }

        @Override // androidx.core.view.d1
        public void a(View view) {
            this.a = true;
        }

        @Override // androidx.core.view.d1
        public void b(View view) {
            if (this.a) {
                return;
            }
            a aVar = a.this;
            aVar.f = null;
            a.super.setVisibility(this.b);
        }

        @Override // androidx.core.view.d1
        public void c(View view) {
            a.super.setVisibility(0);
            this.a = false;
        }

        public C0011a d(androidx.core.view.c1 c1Var, int i) {
            a.this.f = c1Var;
            this.b = i;
            return this;
        }
    }

    a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new C0011a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.b = context;
        } else {
            this.b = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }

    protected static int d(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    protected int c(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    protected int e(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public androidx.core.view.c1 f(int i, long j) {
        androidx.core.view.c1 c1Var = this.f;
        if (c1Var != null) {
            c1Var.b();
        }
        if (i != 0) {
            androidx.core.view.c1 c1VarA = androidx.core.view.m0.c(this).a(0.0f);
            c1VarA.d(j);
            c1VarA.f(this.a.d(c1VarA, i));
            return c1VarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        androidx.core.view.c1 c1VarA2 = androidx.core.view.m0.c(this).a(1.0f);
        c1VarA2.d(j);
        c1VarA2.f(this.a.d(c1VarA2, i));
        return c1VarA2;
    }

    public int getAnimatedVisibility() {
        return this.f != null ? this.a.b : getVisibility();
    }

    public int getContentHeight() {
        return this.e;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R$styleable.a, R$attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        c cVar = this.d;
        if (cVar != null) {
            cVar.x(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.h = false;
        }
        if (!this.h) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.g = false;
        }
        if (!this.g) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.g = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i);

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            androidx.core.view.c1 c1Var = this.f;
            if (c1Var != null) {
                c1Var.b();
            }
            super.setVisibility(i);
        }
    }
}
