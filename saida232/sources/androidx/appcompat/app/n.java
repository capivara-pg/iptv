package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.i1;
import androidx.appcompat.widget.s2;
import androidx.core.view.c1;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class n extends androidx.appcompat.app.a {
    i1 a;
    boolean b;
    Window.Callback c;
    private boolean d;
    private boolean e;
    private ArrayList f = new ArrayList();
    private final Runnable g = new a();
    private final Toolbar.f h;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            n.this.B();
        }
    }

    class b implements Toolbar.f {
        b() {
        }

        @Override // androidx.appcompat.widget.Toolbar.f
        public boolean onMenuItemClick(MenuItem menuItem) {
            return n.this.c.onMenuItemSelected(0, menuItem);
        }
    }

    private final class c implements androidx.appcompat.view.menu.m.a {
        private boolean a;

        c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callback = n.this.c;
            if (callback == null) {
                return false;
            }
            callback.onMenuOpened(R$styleable.AppCompatTheme_textColorAlertDialogListItem, gVar);
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.m.a
        public void onCloseMenu(androidx.appcompat.view.menu.g gVar, boolean z) {
            if (this.a) {
                return;
            }
            this.a = true;
            n.this.a.r();
            Window.Callback callback = n.this.c;
            if (callback != null) {
                callback.onPanelClosed(R$styleable.AppCompatTheme_textColorAlertDialogListItem, gVar);
            }
            this.a = false;
        }
    }

    private final class d implements androidx.appcompat.view.menu.g.a {
        d() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean onMenuItemSelected(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.appcompat.view.menu.g.a
        public void onMenuModeChange(androidx.appcompat.view.menu.g gVar) {
            n nVar = n.this;
            if (nVar.c != null) {
                if (nVar.a.e()) {
                    n.this.c.onPanelClosed(R$styleable.AppCompatTheme_textColorAlertDialogListItem, gVar);
                } else if (n.this.c.onPreparePanel(0, null, gVar)) {
                    n.this.c.onMenuOpened(R$styleable.AppCompatTheme_textColorAlertDialogListItem, gVar);
                }
            }
        }
    }

    private class e extends f.m {
        public e(Window.Callback callback) {
            super(callback);
        }

        public View onCreatePanelView(int i) {
            return i == 0 ? new View(n.this.a.getContext()) : super.onCreatePanelView(i);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (zOnPreparePanel) {
                n nVar = n.this;
                if (!nVar.b) {
                    nVar.a.f();
                    n.this.b = true;
                }
            }
            return zOnPreparePanel;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    n(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        b bVar = new b();
        this.h = bVar;
        this.a = new s2(toolbar, false);
        f.m eVar = new e(callback);
        this.c = eVar;
        this.a.setWindowCallback(eVar);
        toolbar.setOnMenuItemClickListener(bVar);
        this.a.setWindowTitle(charSequence);
    }

    private Menu z() {
        if (!this.d) {
            this.a.v(new c(), new d());
            this.d = true;
        }
        return this.a.k();
    }

    public Window.Callback A() {
        return this.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void B() {
        Menu menuZ = z();
        androidx.appcompat.view.menu.g gVar = menuZ instanceof androidx.appcompat.view.menu.g ? (androidx.appcompat.view.menu.g) menuZ : null;
        if (gVar != null) {
            gVar.stopDispatchingItemsChanged();
        }
        try {
            menuZ.clear();
            if (!this.c.onCreatePanelMenu(0, menuZ) || !this.c.onPreparePanel(0, null, menuZ)) {
                menuZ.clear();
            }
        } finally {
            if (gVar != null) {
                gVar.startDispatchingItemsChanged();
            }
        }
    }

    public void C(int i, int i2) {
        this.a.i((i & i2) | ((i2 ^ (-1)) & this.a.x()));
    }

    @Override // androidx.appcompat.app.a
    public boolean g() {
        return this.a.b();
    }

    @Override // androidx.appcompat.app.a
    public boolean h() {
        if (!this.a.h()) {
            return false;
        }
        this.a.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.a
    public void i(boolean z) {
        if (z == this.e) {
            return;
        }
        this.e = z;
        if (this.f.size() <= 0) {
            return;
        }
        m.a(this.f.get(0));
        throw null;
    }

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.a.x();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        return this.a.getContext();
    }

    @Override // androidx.appcompat.app.a
    public boolean l() {
        this.a.n().removeCallbacks(this.g);
        c1.c0(this.a.n(), this.g);
        return true;
    }

    @Override // androidx.appcompat.app.a
    public void m(Configuration configuration) {
        super.m(configuration);
    }

    @Override // androidx.appcompat.app.a
    void n() {
        this.a.n().removeCallbacks(this.g);
    }

    @Override // androidx.appcompat.app.a
    public boolean o(int i, KeyEvent keyEvent) {
        Menu menuZ = z();
        if (menuZ == null) {
            return false;
        }
        menuZ.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuZ.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.a
    public boolean p(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            q();
        }
        return true;
    }

    @Override // androidx.appcompat.app.a
    public boolean q() {
        return this.a.c();
    }

    @Override // androidx.appcompat.app.a
    public void r(boolean z) {
    }

    @Override // androidx.appcompat.app.a
    public void s(boolean z) {
        C(z ? 4 : 0, 4);
    }

    @Override // androidx.appcompat.app.a
    public void t(int i) {
        this.a.u(i);
    }

    @Override // androidx.appcompat.app.a
    public void u(boolean z) {
    }

    @Override // androidx.appcompat.app.a
    public void v(CharSequence charSequence) {
        this.a.j(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void w(CharSequence charSequence) {
        this.a.setTitle(charSequence);
    }

    @Override // androidx.appcompat.app.a
    public void x(CharSequence charSequence) {
        this.a.setWindowTitle(charSequence);
    }
}
