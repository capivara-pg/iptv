package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class f extends BaseAdapter {
    g a;
    private int b = -1;
    private boolean c;
    private final boolean d;
    private final LayoutInflater e;
    private final int f;

    public f(g gVar, LayoutInflater layoutInflater, boolean z, int i) {
        this.d = z;
        this.e = layoutInflater;
        this.a = gVar;
        this.f = i;
        a();
    }

    void a() {
        i expandedItem = this.a.getExpandedItem();
        if (expandedItem != null) {
            ArrayList<i> nonActionItems = this.a.getNonActionItems();
            int size = nonActionItems.size();
            for (int i = 0; i < size; i++) {
                if (nonActionItems.get(i) == expandedItem) {
                    this.b = i;
                    return;
                }
            }
        }
        this.b = -1;
    }

    public g b() {
        return this.a;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public i getItem(int i) {
        ArrayList<i> nonActionItems = this.d ? this.a.getNonActionItems() : this.a.getVisibleItems();
        int i2 = this.b;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return nonActionItems.get(i);
    }

    public void d(boolean z) {
        this.c = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<i> nonActionItems = this.d ? this.a.getNonActionItems() : this.a.getVisibleItems();
        return this.b < 0 ? nonActionItems.size() : nonActionItems.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.e.inflate(this.f, viewGroup, false);
        }
        int groupId = getItem(i).getGroupId();
        int i2 = i - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.a.isGroupDividerEnabled() && groupId != (i2 >= 0 ? getItem(i2).getGroupId() : groupId));
        n.a aVar = (n.a) view;
        if (this.c) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.initialize(getItem(i), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
