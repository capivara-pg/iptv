package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.R$layout;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class h implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, m.a {
    private g a;
    private androidx.appcompat.app.c b;
    e c;
    private m.a d;

    public h(g gVar) {
        this.a = gVar;
    }

    @Override // androidx.appcompat.view.menu.m.a
    public boolean a(g gVar) {
        m.a aVar = this.d;
        if (aVar != null) {
            return aVar.a(gVar);
        }
        return false;
    }

    public void b() {
        androidx.appcompat.app.c cVar = this.b;
        if (cVar != null) {
            cVar.dismiss();
        }
    }

    public void c(IBinder iBinder) {
        g gVar = this.a;
        androidx.appcompat.app.c.a aVar = new androidx.appcompat.app.c.a(gVar.getContext());
        e eVar = new e(aVar.getContext(), R$layout.abc_list_menu_item_layout);
        this.c = eVar;
        eVar.setCallback(this);
        this.a.addMenuPresenter(this.c);
        aVar.setAdapter(this.c.a(), this);
        View headerView = gVar.getHeaderView();
        if (headerView != null) {
            aVar.setCustomTitle(headerView);
        } else {
            aVar.setIcon(gVar.getHeaderIcon()).setTitle(gVar.getHeaderTitle());
        }
        aVar.setOnKeyListener(this);
        androidx.appcompat.app.c cVarCreate = aVar.create();
        this.b = cVarCreate;
        cVarCreate.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.b.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.b.show();
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
    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.performItemAction((i) this.c.a().getItem(i), 0);
    }

    @Override // androidx.appcompat.view.menu.m.a
    public void onCloseMenu(g gVar, boolean z) {
        if (z || gVar == this.a) {
            b();
        }
        m.a aVar = this.d;
        if (aVar != null) {
            aVar.onCloseMenu(gVar, z);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.c.onCloseMenu(this.a, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.b.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.b.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.a.close(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.a.performShortcut(i, keyEvent, 0);
    }
}
