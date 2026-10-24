package androidx.appcompat.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.n2;
import s.p0;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends androidx.fragment.app.e implements e, p0.a {
    private f mDelegate;
    private Resources mResources;

    /* JADX WARN: Multi-variable type inference failed */
    private boolean a4(KeyEvent keyEvent) {
        Window window;
        return (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) ? false : true;
    }

    @Override // androidx.activity.ComponentActivity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().b(view, layoutParams);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void attachBaseContext(Context context) {
        super/*android.app.Activity*/.attachBaseContext(getDelegate().d(context));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void closeOptionsMenu() {
        a supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.g()) {
                super/*android.app.Activity*/.closeOptionsMenu();
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        a supportActionBar = getSupportActionBar();
        if (keyCode == 82 && supportActionBar != null && supportActionBar.p(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public <T extends View> T findViewById(int i) {
        return (T) getDelegate().g(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public f getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = f.e(this, this);
        }
        return this.mDelegate;
    }

    public b getDrawerToggleDelegate() {
        return getDelegate().i();
    }

    public MenuInflater getMenuInflater() {
        return getDelegate().k();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Resources getResources() {
        if (this.mResources == null && n2.b()) {
            this.mResources = new n2(this, super/*android.app.Activity*/.getResources());
        }
        Resources resources = this.mResources;
        return resources == null ? super/*android.app.Activity*/.getResources() : resources;
    }

    public a getSupportActionBar() {
        return getDelegate().l();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Intent getSupportParentActivityIntent() {
        return s.l.a(this);
    }

    public void invalidateOptionsMenu() {
        getDelegate().n();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.e
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mResources != null) {
            this.mResources.updateConfiguration(configuration, super/*android.app.Activity*/.getResources().getDisplayMetrics());
        }
        getDelegate().o(configuration);
    }

    public void onContentChanged() {
        onSupportContentChanged();
    }

    @Override // androidx.fragment.app.e, androidx.activity.ComponentActivity
    protected void onCreate(Bundle bundle) {
        f delegate = getDelegate();
        delegate.m();
        delegate.p(bundle);
        super.onCreate(bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onCreateSupportNavigateUpTaskStack(p0 p0Var) {
        p0Var.c(this);
    }

    @Override // androidx.fragment.app.e
    protected void onDestroy() {
        super.onDestroy();
        getDelegate().q();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (a4(keyEvent)) {
            return true;
        }
        return super/*android.app.Activity*/.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.e
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        a supportActionBar = getSupportActionBar();
        if (menuItem.getItemId() != 16908332 || supportActionBar == null || (supportActionBar.j() & 4) == 0) {
            return false;
        }
        return onSupportNavigateUp();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onMenuOpened(int i, Menu menu) {
        return super/*android.app.Activity*/.onMenuOpened(i, menu);
    }

    protected void onNightModeChanged(int i) {
    }

    @Override // androidx.fragment.app.e
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onPostCreate(Bundle bundle) {
        super/*android.app.Activity*/.onPostCreate(bundle);
        getDelegate().r(bundle);
    }

    @Override // androidx.fragment.app.e
    protected void onPostResume() {
        super.onPostResume();
        getDelegate().s();
    }

    public void onPrepareSupportNavigateUpTaskStack(p0 p0Var) {
    }

    @Override // androidx.activity.ComponentActivity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getDelegate().t(bundle);
    }

    @Override // androidx.fragment.app.e
    protected void onStart() {
        super.onStart();
        getDelegate().u();
    }

    @Override // androidx.fragment.app.e
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

    @Deprecated
    public void onSupportContentChanged() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onSupportNavigateUp() {
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            return false;
        }
        if (!supportShouldUpRecreateTask(supportParentActivityIntent)) {
            supportNavigateUpTo(supportParentActivityIntent);
            return true;
        }
        p0 p0VarE = p0.e(this);
        onCreateSupportNavigateUpTaskStack(p0VarE);
        onPrepareSupportNavigateUpTaskStack(p0VarE);
        p0VarE.h();
        try {
            s.c.a(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onTitleChanged(CharSequence charSequence, int i) {
        super/*android.app.Activity*/.onTitleChanged(charSequence, i);
        getDelegate().E(charSequence);
    }

    @Override // androidx.appcompat.app.e
    public h.b onWindowStartingSupportActionMode(h.b.a aVar) {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void openOptionsMenu() {
        a supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.q()) {
                super/*android.app.Activity*/.openOptionsMenu();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity
    public void setContentView(int i) {
        getDelegate().z(i);
    }

    public void setSupportActionBar(Toolbar toolbar) {
        getDelegate().C(toolbar);
    }

    @Deprecated
    public void setSupportProgress(int i) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminate(boolean z) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(boolean z) {
    }

    @Deprecated
    public void setSupportProgressBarVisibility(boolean z) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTheme(int i) {
        super/*android.app.Activity*/.setTheme(i);
        getDelegate().D(i);
    }

    public h.b startSupportActionMode(h.b.a aVar) {
        return getDelegate().F(aVar);
    }

    @Override // androidx.fragment.app.e
    public void supportInvalidateOptionsMenu() {
        getDelegate().n();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void supportNavigateUpTo(Intent intent) {
        s.l.e(this, intent);
    }

    public boolean supportRequestWindowFeature(int i) {
        return getDelegate().y(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean supportShouldUpRecreateTask(Intent intent) {
        return s.l.f(this, intent);
    }

    @Override // androidx.activity.ComponentActivity
    public void setContentView(View view) {
        getDelegate().A(view);
    }

    @Override // androidx.activity.ComponentActivity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().B(view, layoutParams);
    }
}
