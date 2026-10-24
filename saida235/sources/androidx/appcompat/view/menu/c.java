package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: loaded from: classes.dex */
abstract class c {
    final Context a;
    private androidx.collection.g b;
    private androidx.collection.g c;

    c(Context context) {
        this.a = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof w.b)) {
            return menuItem;
        }
        w.b bVar = (w.b) menuItem;
        if (this.b == null) {
            this.b = new androidx.collection.g();
        }
        MenuItem menuItem2 = (MenuItem) this.b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.a, bVar);
        this.b.put(bVar, jVar);
        return jVar;
    }

    final SubMenu d(SubMenu subMenu) {
        return subMenu;
    }

    final void e() {
        androidx.collection.g gVar = this.b;
        if (gVar != null) {
            gVar.clear();
        }
        androidx.collection.g gVar2 = this.c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    final void f(int i) {
        if (this.b == null) {
            return;
        }
        int i2 = 0;
        while (i2 < this.b.size()) {
            if (((w.b) this.b.keyAt(i2)).getGroupId() == i) {
                this.b.removeAt(i2);
                i2--;
            }
            i2++;
        }
    }

    final void g(int i) {
        if (this.b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (((w.b) this.b.keyAt(i2)).getItemId() == i) {
                this.b.removeAt(i2);
                return;
            }
        }
    }
}
