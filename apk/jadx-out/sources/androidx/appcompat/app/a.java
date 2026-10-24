package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    public static abstract class b {
    }

    public boolean g() {
        return false;
    }

    public abstract boolean h();

    public abstract void i(boolean z);

    public abstract int j();

    public abstract Context k();

    public boolean l() {
        return false;
    }

    public void m(Configuration configuration) {
    }

    void n() {
    }

    public abstract boolean o(int i, KeyEvent keyEvent);

    public boolean p(KeyEvent keyEvent) {
        return false;
    }

    public boolean q() {
        return false;
    }

    public abstract void r(boolean z);

    public abstract void s(boolean z);

    public abstract void t(int i);

    public abstract void u(boolean z);

    public abstract void v(CharSequence charSequence);

    public abstract void w(CharSequence charSequence);

    public abstract void x(CharSequence charSequence);

    public h.b y(h.b.a aVar) {
        return null;
    }

    /* JADX INFO: renamed from: androidx.appcompat.app.a$a, reason: collision with other inner class name */
    public static class C0007a extends ViewGroup.MarginLayoutParams {
        public int a;

        public C0007a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.b);
            this.a = typedArrayObtainStyledAttributes.getInt(R$styleable.ActionBarLayout_android_layout_gravity, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public C0007a(int i, int i2) {
            super(i, i2);
            this.a = 8388627;
        }

        public C0007a(C0007a c0007a) {
            super((ViewGroup.MarginLayoutParams) c0007a);
            this.a = 0;
            this.a = c0007a.a;
        }

        public C0007a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0;
        }
    }
}
