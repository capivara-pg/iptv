package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements m {
    protected Context a;
    protected Context b;
    protected g c;
    protected LayoutInflater d;
    protected LayoutInflater e;
    private m.a f;
    private int g;
    private int h;
    protected n i;
    private int j;

    public b(Context context, int i, int i2) {
        this.a = context;
        this.d = LayoutInflater.from(context);
        this.g = i;
        this.h = i2;
    }

    protected void a(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.i).addView(view, i);
    }

    public abstract void b(i iVar, n.a aVar);

    public n.a c(ViewGroup viewGroup) {
        return (n.a) this.d.inflate(this.h, viewGroup, false);
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean collapseItemActionView(g gVar, i iVar) {
        return false;
    }

    protected boolean d(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public m.a e() {
        return this.f;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean expandItemActionView(g gVar, i iVar) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View f(i iVar, View view, ViewGroup viewGroup) {
        n.a aVarC = view instanceof n.a ? (n.a) view : c(viewGroup);
        b(iVar, aVarC);
        return (View) aVarC;
    }

    public n g(ViewGroup viewGroup) {
        if (this.i == null) {
            n nVar = (n) this.d.inflate(this.g, viewGroup, false);
            this.i = nVar;
            nVar.initialize(this.c);
            updateMenuView(true);
        }
        return this.i;
    }

    @Override // androidx.appcompat.view.menu.m
    public int getId() {
        return this.j;
    }

    public void h(int i) {
        this.j = i;
    }

    public abstract boolean i(int i, i iVar);

    @Override // androidx.appcompat.view.menu.m
    public void initForMenu(Context context, g gVar) {
        this.b = context;
        this.e = LayoutInflater.from(context);
        this.c = gVar;
    }

    @Override // androidx.appcompat.view.menu.m
    public void onCloseMenu(g gVar, boolean z) {
        m.a aVar = this.f;
        if (aVar != null) {
            aVar.onCloseMenu(gVar, z);
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
    @Override // androidx.appcompat.view.menu.m
    public boolean onSubMenuSelected(r rVar) {
        m.a aVar = this.f;
        g gVar = rVar;
        if (aVar == null) {
            return false;
        }
        if (rVar == null) {
            gVar = this.c;
        }
        return aVar.a(gVar);
    }

    @Override // androidx.appcompat.view.menu.m
    public void setCallback(m.a aVar) {
        this.f = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.m
    public void updateMenuView(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.i;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.c;
        int i = 0;
        if (gVar != null) {
            gVar.flagActionItems();
            ArrayList<i> visibleItems = this.c.getVisibleItems();
            int size = visibleItems.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                i iVar = visibleItems.get(i3);
                if (i(i2, iVar)) {
                    View childAt = viewGroup.getChildAt(i2);
                    i itemData = childAt instanceof n.a ? ((n.a) childAt).getItemData() : null;
                    View viewF = f(iVar, childAt, viewGroup);
                    if (iVar != itemData) {
                        viewF.setPressed(false);
                        viewF.jumpDrawablesToCurrentState();
                    }
                    if (viewF != childAt) {
                        a(viewF, i2);
                    }
                    i2++;
                }
            }
            i = i2;
        }
        while (i < viewGroup.getChildCount()) {
            if (!d(viewGroup, i)) {
                i++;
            }
        }
    }
}
