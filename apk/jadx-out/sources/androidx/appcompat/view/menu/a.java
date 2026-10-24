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

/* JADX INFO: loaded from: classes.dex */
public class a implements w.b {
    private final int a;
    private final int b;
    private final int c;
    private CharSequence d;
    private CharSequence e;
    private Intent f;
    private char g;
    private char i;
    private Drawable k;
    private Context l;
    private MenuItem.OnMenuItemClickListener m;
    private CharSequence n;
    private CharSequence o;
    private int h = RecyclerView.ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT;
    private int j = RecyclerView.ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT;
    private ColorStateList p = null;
    private PorterDuff.Mode q = null;
    private boolean r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f8s = false;
    private int t = 16;

    public a(Context context, int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.l = context;
        this.a = i2;
        this.b = i;
        this.c = i4;
        this.d = charSequence;
    }

    private void c() {
        Drawable drawable = this.k;
        if (drawable != null) {
            if (this.r || this.f8s) {
                Drawable drawableR = v.b.r(drawable);
                this.k = drawableR;
                Drawable drawableMutate = drawableR.mutate();
                this.k = drawableMutate;
                if (this.r) {
                    v.b.o(drawableMutate, this.p);
                }
                if (this.f8s) {
                    v.b.p(this.k, this.q);
                }
            }
        }
    }

    public androidx.core.view.b a() {
        return null;
    }

    public w.b b(androidx.core.view.b bVar) {
        throw new UnsupportedOperationException();
    }

    public boolean collapseActionView() {
        return false;
    }

    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public w.b setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public w.b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public boolean expandActionView() {
        return false;
    }

    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public w.b setShowAsActionFlags(int i) {
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
        return this.j;
    }

    public char getAlphabeticShortcut() {
        return this.i;
    }

    public CharSequence getContentDescription() {
        return this.n;
    }

    public int getGroupId() {
        return this.b;
    }

    public Drawable getIcon() {
        return this.k;
    }

    public ColorStateList getIconTintList() {
        return this.p;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.q;
    }

    public Intent getIntent() {
        return this.f;
    }

    public int getItemId() {
        return this.a;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public int getNumericModifiers() {
        return this.h;
    }

    public char getNumericShortcut() {
        return this.g;
    }

    public int getOrder() {
        return this.c;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public CharSequence getTitle() {
        return this.d;
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.e;
        return charSequence != null ? charSequence : this.d;
    }

    public CharSequence getTooltipText() {
        return this.o;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public boolean isCheckable() {
        return (this.t & 1) != 0;
    }

    public boolean isChecked() {
        return (this.t & 2) != 0;
    }

    public boolean isEnabled() {
        return (this.t & 16) != 0;
    }

    public boolean isVisible() {
        return (this.t & 8) == 0;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setAlphabeticShortcut(char c) {
        this.i = Character.toLowerCase(c);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setCheckable(boolean z) {
        this.t = (z ? 1 : 0) | (this.t & (-2));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setChecked(boolean z) {
        this.t = (z ? 2 : 0) | (this.t & (-3));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setEnabled(boolean z) {
        this.t = (z ? 16 : 0) | (this.t & (-17));
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIcon(Drawable drawable) {
        this.k = drawable;
        c();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.p = colorStateList;
        this.r = true;
        c();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.q = mode;
        this.f8s = true;
        c();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIntent(Intent intent) {
        this.f = intent;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setNumericShortcut(char c) {
        this.g = c;
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.m = onMenuItemClickListener;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setShortcut(char c, char c2) {
        this.g = c;
        this.i = Character.toLowerCase(c2);
        return this;
    }

    public void setShowAsAction(int i) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitle(CharSequence charSequence) {
        this.d = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.e = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setVisible(boolean z) {
        this.t = (this.t & 8) | (z ? 0 : 8);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setAlphabeticShortcut(char c, int i) {
        this.i = Character.toLowerCase(c);
        this.j = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public w.b setContentDescription(CharSequence charSequence) {
        this.n = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setNumericShortcut(char c, int i) {
        this.g = c;
        this.h = KeyEvent.normalizeMetaState(i);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setTitle(int i) {
        this.d = this.l.getResources().getString(i);
        return this;
    }

    public w.b setTooltipText(CharSequence charSequence) {
        this.o = charSequence;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setIcon(int i) {
        this.k = androidx.core.content.a.getDrawable(this.l, i);
        c();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.g = c;
        this.h = KeyEvent.normalizeMetaState(i);
        this.i = Character.toLowerCase(c2);
        this.j = KeyEvent.normalizeMetaState(i2);
        return this;
    }
}
