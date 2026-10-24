package androidx.appcompat.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;

/* JADX INFO: loaded from: classes.dex */
public class n extends Dialog implements e {
    private f mDelegate;
    private final androidx.core.view.k.a mKeyDispatcher;

    class a implements androidx.core.view.k.a {
        a() {
        }

        @Override // androidx.core.view.k.a
        public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
            return n.this.superDispatchKeyEvent(keyEvent);
        }
    }

    public n(Context context, int i) {
        super(context, getThemeResId(context, i));
        this.mKeyDispatcher = new a();
        f delegate = getDelegate();
        delegate.D(getThemeResId(context, i));
        delegate.p(null);
    }

    private static int getThemeResId(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().b(view, layoutParams);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        getDelegate().q();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return androidx.core.view.k.e(this.mKeyDispatcher, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        return (T) getDelegate().g(i);
    }

    public f getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = f.f(this, this);
        }
        return this.mDelegate;
    }

    public androidx.appcompat.app.a getSupportActionBar() {
        return getDelegate().l();
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        getDelegate().n();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        getDelegate().m();
        super.onCreate(bundle);
        getDelegate().p(bundle);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        getDelegate().v();
    }

    @Override // androidx.appcompat.app.e
    public void onSupportActionModeFinished(h.b bVar) {
    }

    @Override // androidx.appcompat.app.e
    public void onSupportActionModeStarted(h.b bVar) {
    }

    @Override // androidx.appcompat.app.e
    public h.b onWindowStartingSupportActionMode(h.b.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        getDelegate().z(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        getDelegate().E(charSequence);
    }

    boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean supportRequestWindowFeature(int i) {
        return getDelegate().y(i);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        getDelegate().A(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().B(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        getDelegate().E(getContext().getString(i));
    }

    protected n(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mKeyDispatcher = new a();
    }
}
