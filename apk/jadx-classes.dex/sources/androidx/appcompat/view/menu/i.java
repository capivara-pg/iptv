package androidx.appcompat.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.R$string;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public final class i implements r.b {
    private View A;
    private androidx.core.view.b B;
    private MenuItem.OnActionExpandListener C;
    private ContextMenu.ContextMenuInfo E;
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private CharSequence e;
    private CharSequence f;
    private Intent g;
    private char h;
    private char j;
    private Drawable l;
    g n;
    private r o;
    private Runnable p;
    private MenuItem.OnMenuItemClickListener q;
    private CharSequence r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private CharSequence f11s;
    private int z;
    private int i = RecyclerView.ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT;
    private int k = RecyclerView.ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT;
    private int m = 0;
    private ColorStateList t = null;
    private PorterDuff.Mode u = null;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private int y = 16;
    private boolean D = false;

    class a implements androidx.core.view.b.InterfaceC0023b {
        a() {
        }

        @Override // androidx.core.view.b.InterfaceC0023b
        public void onActionProviderVisibilityChanged(boolean z) {
            i iVar = i.this;
            iVar.n.onItemVisibleChanged(iVar);
        }
    }

    i(g gVar, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.n = gVar;
        this.a = i2;
        this.b = i;
        this.c = i3;
        this.d = i4;
        this.e = charSequence;
        this.z = i5;
    }

    private static void d(StringBuilder sb, int i, int i2, String str) {
        if ((i & i2) == i2) {
            sb.append(str);
        }
    }

    private Drawable e(Drawable drawable) {
        if (drawable != null && this.x && (this.v || this.w)) {
            drawable = q.h.r(drawable).mutate();
            if (this.v) {
                q.h.o(drawable, this.t);
            }
            if (this.w) {
                q.h.p(drawable, this.u);
            }
            this.x = false;
        }
        return drawable;
    }

    boolean A() {
        return this.n.isShortcutsVisible() && g() != 0;
    }

    public boolean B() {
        return (this.z & 4) == 4;
    }

    public androidx.core.view.b a() {
        return this.B;
    }

    public r.b b(androidx.core.view.b bVar) {
        androidx.core.view.b bVar2 = this.B;
        if (bVar2 != null) {
            bVar2.j();
        }
        this.A = null;
        this.B = bVar;
        this.n.onItemsChanged(true);
        androidx.core.view.b bVar3 = this.B;
        if (bVar3 != null) {
            bVar3.l(new a());
        }
        return this;
    }

    public void c() {
        this.n.onItemActionRequestChanged(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean collapseActionView() {
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.n.collapseItemActionView(this);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean expandActionView() {
        if (!j()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.n.expandItemActionView(this);
        }
        return false;
    }

    public int f() {
        return this.d;
    }

    char g() {
        return this.n.isQwertyMode() ? this.j : this.h;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View getActionView() {
        View view = this.A;
        if (view != null) {
            return view;
        }
        androidx.core.view.b bVar = this.B;
        if (bVar == null) {
            return null;
        }
        View viewE = bVar.e(this);
        this.A = viewE;
        return viewE;
    }

    public int getAlphabeticModifiers() {
        return this.k;
    }

    public char getAlphabeticShortcut() {
        return this.j;
    }

    public CharSequence getContentDescription() {
        return this.r;
    }

    public int getGroupId() {
        return this.b;
    }

    public Drawable getIcon() {
        Drawable drawable = this.l;
        if (drawable != null) {
            return e(drawable);
        }
        if (this.m == 0) {
            return null;
        }
        Drawable drawableD = d.b.d(this.n.getContext(), this.m);
        this.m = 0;
        this.l = drawableD;
        return e(drawableD);
    }

    public ColorStateList getIconTintList() {
        return this.t;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.u;
    }

    public Intent getIntent() {
        return this.g;
    }

    public int getItemId() {
        return this.a;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    public int getNumericModifiers() {
        return this.i;
    }

    public char getNumericShortcut() {
        return this.h;
    }

    public int getOrder() {
        return this.c;
    }

    public SubMenu getSubMenu() {
        return this.o;
    }

    public CharSequence getTitle() {
        return this.e;
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f;
        return charSequence != null ? charSequence : this.e;
    }

    public CharSequence getTooltipText() {
        return this.f11s;
    }

    String h() {
        char cG = g();
        if (cG == 0) {
            return "";
        }
        Resources resources = this.n.getContext().getResources();
        StringBuilder sb = new StringBuilder();
        if (ViewConfiguration.get(this.n.getContext()).hasPermanentMenuKey()) {
            sb.append(resources.getString(R$string.abc_prepend_shortcut_label));
        }
        int i = this.n.isQwertyMode() ? this.k : this.i;
        d(sb, i, 65536, resources.getString(R$string.abc_menu_meta_shortcut_label));
        d(sb, i, RecyclerView.ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT, resources.getString(R$string.abc_menu_ctrl_shortcut_label));
        d(sb, i, 2, resources.getString(R$string.abc_menu_alt_shortcut_label));
        d(sb, i, 1, resources.getString(R$string.abc_menu_shift_shortcut_label));
        d(sb, i, 4, resources.getString(R$string.abc_menu_sym_shortcut_label));
        d(sb, i, 8, resources.getString(R$string.abc_menu_function_shortcut_label));
        if (cG == '\b') {
            sb.append(resources.getString(R$string.abc_menu_delete_shortcut_label));
        } else if (cG == '\n') {
            sb.append(resources.getString(R$string.abc_menu_enter_shortcut_label));
        } else if (cG != ' ') {
            sb.append(cG);
        } else {
            sb.append(resources.getString(R$string.abc_menu_space_shortcut_label));
        }
        return sb.toString();
    }

    public boolean hasSubMenu() {
        return this.o != null;
    }

    CharSequence i(n.a aVar) {
        return (aVar == null || !aVar.prefersCondensedTitle()) ? getTitle() : getTitleCondensed();
    }

    public boolean isActionViewExpanded() {
        return this.D;
    }

    public boolean isCheckable() {
        return (this.y & 1) == 1;
    }

    public boolean isChecked() {
        return (this.y & 2) == 2;
    }

    public boolean isEnabled() {
        return (this.y & 16) != 0;
    }

    public boolean isVisible() {
        androidx.core.view.b bVar = this.B;
        if (bVar == null || !bVar.h()) {
            return (this.y & 8) == 0;
        }
        return (this.y & 8) == 0 && this.B.c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean j() {
        androidx.core.view.b bVar;
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null && (bVar = this.B) != null) {
            this.A = bVar.e(this);
        }
        return this.A != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean k() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.q;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        g gVar = this.n;
        if (gVar.dispatchMenuItemSelected(gVar, this)) {
            return true;
        }
        Runnable runnable = this.p;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.g != null) {
            try {
                this.n.getContext().startActivity(this.g);
                return true;
            } catch (ActivityNotFoundException e) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e);
            }
        }
        androidx.core.view.b bVar = this.B;
        return bVar != null && bVar.f();
    }

    public boolean l() {
        return (this.y & 32) == 32;
    }

    public boolean m() {
        return (this.y & 4) != 0;
    }

    public boolean n() {
        return (this.z & 1) == 1;
    }

    public boolean o() {
        return (this.z & 2) == 2;
    }

    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public r.b setActionView(int i) {
        Context context = this.n.getContext();
        setActionView(LayoutInflater.from(context).inflate(i, (ViewGroup) new LinearLayout(context), false));
        return this;
    }

    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public r.b setActionView(View view) {
        int i;
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && (i = this.a) > 0) {
            view.setId(i);
        }
        this.n.onItemActionRequestChanged(this);
        return this;
    }

    public void r(boolean z) {
        this.D = z;
        this.n.onItemsChanged(false);
    }

    void s(boolean z) {
        int i = this.y;
        int i2 = (z ? 2 : 0) | (i & (-3));
        this.y = i2;
        if (i != i2) {
            this.n.onItemsChanged(false);
        }
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setAlphabeticShortcut(char c) {
        if (this.j == c) {
            return this;
        }
        this.j = Character.toLowerCase(c);
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setCheckable(boolean z) {
        int i = this.y;
        int i2 = (z ? 1 : 0) | (i & (-2));
        this.y = i2;
        if (i != i2) {
            this.n.onItemsChanged(false);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setChecked(boolean z) {
        if ((this.y & 4) != 0) {
            this.n.setExclusiveItemChecked(this);
        } else {
            s(z);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setEnabled(boolean z) {
        if (z) {
            this.y |= 16;
        } else {
            this.y &= -17;
        }
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIcon(Drawable drawable) {
        this.m = 0;
        this.l = drawable;
        this.x = true;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.t = colorStateList;
        this.v = true;
        this.x = true;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.u = mode;
        this.w = true;
        this.x = true;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIntent(Intent intent) {
        this.g = intent;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setNumericShortcut(char c) {
        if (this.h == c) {
            return this;
        }
        this.h = c;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.C = onActionExpandListener;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.q = onMenuItemClickListener;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setShortcut(char c, char c2) {
        this.h = c;
        this.j = Character.toLowerCase(c2);
        this.n.onItemsChanged(false);
        return this;
    }

    public void setShowAsAction(int i) {
        int i2 = i & 3;
        if (i2 != 0 && i2 != 1 && i2 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.z = i;
        this.n.onItemActionRequestChanged(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitle(CharSequence charSequence) {
        this.e = charSequence;
        this.n.onItemsChanged(false);
        r rVar = this.o;
        if (rVar != null) {
            rVar.setHeaderTitle(charSequence);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f = charSequence;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setVisible(boolean z) {
        if (y(z)) {
            this.n.onItemVisibleChanged(this);
        }
        return this;
    }

    public void t(boolean z) {
        this.y = (z ? 4 : 0) | (this.y & (-5));
    }

    public String toString() {
        CharSequence charSequence = this.e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public void u(boolean z) {
        if (z) {
            this.y |= 32;
        } else {
            this.y &= -33;
        }
    }

    void v(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.E = contextMenuInfo;
    }

    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public r.b setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    public void x(r rVar) {
        this.o = rVar;
        rVar.setHeaderTitle(getTitle());
    }

    boolean y(boolean z) {
        int i = this.y;
        int i2 = (z ? 0 : 8) | (i & (-9));
        this.y = i2;
        return i != i2;
    }

    public boolean z() {
        return this.n.getOptionalIconsVisible();
    }

    public r.b setContentDescription(CharSequence charSequence) {
        this.r = charSequence;
        this.n.onItemsChanged(false);
        return this;
    }

    public r.b setTooltipText(CharSequence charSequence) {
        this.f11s = charSequence;
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setAlphabeticShortcut(char c, int i) {
        if (this.j == c && this.k == i) {
            return this;
        }
        this.j = Character.toLowerCase(c);
        this.k = KeyEvent.normalizeMetaState(i);
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setNumericShortcut(char c, int i) {
        if (this.h == c && this.i == i) {
            return this;
        }
        this.h = c;
        this.i = KeyEvent.normalizeMetaState(i);
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.h = c;
        this.i = KeyEvent.normalizeMetaState(i);
        this.j = Character.toLowerCase(c2);
        this.k = KeyEvent.normalizeMetaState(i2);
        this.n.onItemsChanged(false);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIcon(int i) {
        this.l = null;
        this.m = i;
        this.x = true;
        this.n.onItemsChanged(false);
        return this;
    }

    public MenuItem setTitle(int i) {
        return setTitle(this.n.getContext().getString(i));
    }
}
