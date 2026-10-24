package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
abstract class c {
    final Context a;
    private Map b;
    private Map c;

    c(Context context) {
        this.a = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof r.b)) {
            return menuItem;
        }
        r.b bVar = (r.b) menuItem;
        if (this.b == null) {
            this.b = new androidx.collection.a();
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
        Map map = this.b;
        if (map != null) {
            map.clear();
        }
        Map map2 = this.c;
        if (map2 != null) {
            map2.clear();
        }
    }

    final void f(int i) {
        Map map = this.b;
        if (map == null) {
            return;
        }
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i == ((MenuItem) it.next()).getGroupId()) {
                it.remove();
            }
        }
    }

    final void g(int i) {
        Map map = this.b;
        if (map == null) {
            return;
        }
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i == ((MenuItem) it.next()).getItemId()) {
                it.remove();
                return;
            }
        }
    }
}
