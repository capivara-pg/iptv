package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.q0;
import androidx.appcompat.widget.t1;
import androidx.appcompat.widget.u2;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends q0 implements n.a, View.OnClickListener, ActionMenuView.a {
    i a;
    private CharSequence b;
    private Drawable c;
    g.b d;
    private t1 e;
    b f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private int k;

    private class a extends t1 {
        public a() {
            super(ActionMenuItemView.this);
        }

        @Override // androidx.appcompat.widget.t1
        public p b() {
            b bVar = ActionMenuItemView.this.f;
            if (bVar != null) {
                return bVar.a();
            }
            return null;
        }

        @Override // androidx.appcompat.widget.t1
        protected boolean c() {
            p pVarB;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            g.b bVar = actionMenuItemView.d;
            return bVar != null && bVar.b(actionMenuItemView.a) && (pVarB = b()) != null && pVarB.isShowing();
        }
    }

    public static abstract class b {
        public abstract p a();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private boolean e() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        return i >= 480 || (i >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    private void f() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.b);
        if (this.c != null && (!this.a.B() || (!this.g && !this.h))) {
            z = false;
        }
        boolean z3 = z2 & z;
        setText(z3 ? this.b : null);
        CharSequence contentDescription = this.a.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            setContentDescription(z3 ? null : this.a.getTitle());
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.a.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            u2.a(this, z3 ? null : this.a.getTitle());
        } else {
            u2.a(this, tooltipText);
        }
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean a() {
        return d();
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public boolean b() {
        return d() && this.a.getIcon() == null;
    }

    public boolean d() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // androidx.appcompat.view.menu.n.a
    public i getItemData() {
        return this.a;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public void initialize(i iVar, int i) {
        this.a = iVar;
        setIcon(iVar.getIcon());
        setTitle(iVar.i(this));
        setId(iVar.getItemId());
        setVisibility(iVar.isVisible() ? 0 : 8);
        setEnabled(iVar.isEnabled());
        if (iVar.hasSubMenu() && this.e == null) {
            this.e = new a();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        g.b bVar = this.d;
        if (bVar != null) {
            bVar.b(this.a);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.g = e();
        f();
    }

    @Override // androidx.appcompat.widget.q0, android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        boolean zD = d();
        if (zD && (i3 = this.j) >= 0) {
            super.setPadding(i3, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, this.i) : this.i;
        if (mode != 1073741824 && this.i > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i2);
        }
        if (zD || this.c == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.c.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        t1 t1Var;
        if (this.a.hasSubMenu() && (t1Var = this.e) != null && t1Var.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.appcompat.view.menu.n.a
    public boolean prefersCondensedTitle() {
        return true;
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.h != z) {
            this.h = z;
            i iVar = this.a;
            if (iVar != null) {
                iVar.c();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.c = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i = this.k;
            if (intrinsicWidth > i) {
                intrinsicHeight = (int) (intrinsicHeight * (i / intrinsicWidth));
                intrinsicWidth = i;
            }
            if (intrinsicHeight > i) {
                intrinsicWidth = (int) (intrinsicWidth * (i / intrinsicHeight));
            } else {
                i = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i);
        }
        setCompoundDrawables(drawable, null, null, null);
        f();
    }

    public void setItemInvoker(g.b bVar) {
        this.d = bVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.j = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setPopupCallback(b bVar) {
        this.f = bVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.b = charSequence;
        f();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = context.getResources();
        this.g = e();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.c, i, 0);
        this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionMenuItemView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.k = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.j = -1;
        setSaveEnabled(false);
    }
}
