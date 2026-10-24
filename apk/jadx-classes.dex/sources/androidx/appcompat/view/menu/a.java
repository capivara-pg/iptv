package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class a implements r.b {
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
    private Context n;
    private MenuItem.OnMenuItemClickListener o;
    private CharSequence p;
    private CharSequence q;
    private int i = RecyclerView.ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT;
    private int k = RecyclerView.ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT;
    private int m = 0;
    private ColorStateList r = null;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private PorterDuff.Mode f9s = null;
    private boolean t = false;
    private boolean u = false;
    private int v = 16;

    public a(Context context, int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.n = context;
        this.a = i2;
        this.b = i;
        this.c = i3;
        this.d = i4;
        this.e = charSequence;
    }

    private void c() {
        Drawable drawable = this.l;
        if (drawable != null) {
            if (this.t || this.u) {
                Drawable drawableR = q.h.r(drawable);
                this.l = drawableR;
                Drawable drawableMutate = drawableR.mutate();
                this.l = drawableMutate;
                if (this.t) {
                    q.h.o(drawableMutate, this.r);
                }
                if (this.u) {
                    q.h.p(this.l, this.f9s);
                }
            }
        }
    }

    public androidx.core.view.b a() {
        return null;
    }

    public r.b b(androidx.core.view.b bVar) {
        throw new UnsupportedOperationException();
    }

    public boolean collapseActionView() {
        return false;
    }

    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public r.b setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public r.b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public boolean expandActionView() {
        return false;
    }

    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public r.b setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public View getActionView() {
        return null;
    }

    public int getAlphabeticModifiers() {
        return this.k;
    }

    public char getAlphabeticShortcut() {
        return this.j;
    }

    public CharSequence getContentDescription() {
        return this.p;
    }

    public int getGroupId() {
        return this.b;
    }

    public Drawable getIcon() {
        return this.l;
    }

    public ColorStateList getIconTintList() {
        return this.r;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f9s;
    }

    public Intent getIntent() {
        return this.g;
    }

    public int getItemId() {
        return this.a;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public int getNumericModifiers() {
        return this.i;
    }

    public char getNumericShortcut() {
        return this.h;
    }

    public int getOrder() {
        return this.d;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public CharSequence getTitle() {
        return this.e;
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f;
        return charSequence != null ? charSequence : this.e;
    }

    public CharSequence getTooltipText() {
        return this.q;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public boolean isCheckable() {
        return (this.v & 1) != 0;
    }

    public boolean isChecked() {
        return (this.v & 2) != 0;
    }

    public boolean isEnabled() {
        return (this.v & 16) != 0;
    }

    public boolean isVisible() {
        return (this.v & 8) == 0;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setAlphabeticShortcut(char c) {
        this.j = Character.toLowerCase(c);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setCheckable(boolean z) {
        this.v = (z ? 1 : 0) | (this.v & (-2));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setChecked(boolean z) {
        this.v = (z ? 2 : 0) | (this.v & (-3));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setEnabled(boolean z) {
        this.v = (z ? 16 : 0) | (this.v & (-17));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIcon(Drawable drawable) {
        this.l = drawable;
        this.m = 0;
        c();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.r = colorStateList;
        this.t = true;
        c();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f9s = mode;
        this.u = true;
        c();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIntent(Intent intent) {
        this.g = intent;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setNumericShortcut(char c) {
        this.h = c;
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.o = onMenuItemClickListener;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setShortcut(char c, char c2) {
        this.h = c;
        this.j = Character.toLowerCase(c2);
        return this;
    }

    public void setShowAsAction(int i) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitle(CharSequence charSequence) {
        this.e = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setVisible(boolean z) {
        this.v = (this.v & 8) | (z ? 0 : 8);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setAlphabeticShortcut(char c, int i) {
        this.j = Character.toLowerCase(c);
        this.k = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public r.b setContentDescription(CharSequence charSequence) {
        this.p = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setNumericShortcut(char c, int i) {
        this.h = c;
        this.i = KeyEvent.normalizeMetaState(i);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitle(int i) {
        this.e = this.n.getResources().getString(i);
        return this;
    }

    public r.b setTooltipText(CharSequence charSequence) {
        this.q = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.h = c;
        this.i = KeyEvent.normalizeMetaState(i);
        this.j = Character.toLowerCase(c2);
        this.k = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIcon(int i) {
        this.m = i;
        this.l = androidx.core.content.a.getDrawable(this.n, i);
        c();
        return this;
    }
}
