package androidx.core.view;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class b {
    private final Context a;
    private a b;
    private InterfaceC0023b c;

    public interface a {
    }

    /* JADX INFO: renamed from: androidx.core.view.b$b, reason: collision with other inner class name */
    public interface InterfaceC0023b {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public b(Context context) {
        this.a = context;
    }

    public Context a() {
        return this.a;
    }

    public boolean b() {
        return false;
    }

    public abstract boolean c();

    public abstract View d();

    public View e(MenuItem menuItem) {
        return d();
    }

    public abstract boolean f();

    public void g(SubMenu subMenu) {
    }

    public abstract boolean h();

    public void i() {
        if (this.c == null || !h()) {
            return;
        }
        this.c.onActionProviderVisibilityChanged(c());
    }

    public void j() {
        this.c = null;
        this.b = null;
    }

    public void k(a aVar) {
        this.b = aVar;
    }

    public void l(InterfaceC0023b interfaceC0023b) {
        if (this.c != null && interfaceC0023b != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this ");
            sb.append(getClass().getSimpleName());
            sb.append(" instance while it is still in use somewhere else?");
        }
        this.c = interfaceC0023b;
    }
}
