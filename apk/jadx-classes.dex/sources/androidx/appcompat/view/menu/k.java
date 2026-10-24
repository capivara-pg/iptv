package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class k implements p, m, AdapterView.OnItemClickListener {
    private Rect a;

    k() {
    }

    protected static int d(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        View view = null;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = listAdapter.getItemViewType(i4);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            if (viewGroup == null) {
                viewGroup = new FrameLayout(context);
            }
            view = listAdapter.getView(i4, view, viewGroup);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i) {
                return i;
            }
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    protected static boolean n(g gVar) {
        int size = gVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = gVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    protected static f o(ListAdapter listAdapter) {
        return listAdapter instanceof HeaderViewListAdapter ? (f) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter() : (f) listAdapter;
    }

    public abstract void a(g gVar);

    protected boolean b() {
        return true;
    }

    public Rect c() {
        return this.a;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean collapseItemActionView(g gVar, i iVar) {
        return false;
    }

    public abstract void e(View view);

    @Override // androidx.appcompat.view.menu.m
    public boolean expandItemActionView(g gVar, i iVar) {
        return false;
    }

    public void f(Rect rect) {
        this.a = rect;
    }

    public abstract void g(boolean z);

    @Override // androidx.appcompat.view.menu.m
    public int getId() {
        return 0;
    }

    public abstract void h(int i);

    public abstract void i(int i);

    @Override // androidx.appcompat.view.menu.m
    public void initForMenu(Context context, g gVar) {
    }

    public abstract void j(PopupWindow.OnDismissListener onDismissListener);

    public abstract void k(boolean z);

    public abstract void l(int i);

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        o(listAdapter).a.performItemAction((MenuItem) listAdapter.getItem(i), this, b() ? 0 : 4);
    }
}
