package androidx.coordinatorlayout.widget;

import androidx.collection.g;
import c0.e;
import c0.f;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    private final e a = new f(10);
    private final g b = new g();
    private final ArrayList c = new ArrayList();
    private final HashSet d = new HashSet();

    private void e(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (hashSet.contains(obj)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(obj);
        ArrayList arrayList2 = (ArrayList) this.b.get(obj);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                e(arrayList2.get(i), arrayList, hashSet);
            }
        }
        hashSet.remove(obj);
        arrayList.add(obj);
    }

    private ArrayList f() {
        ArrayList arrayList = (ArrayList) this.a.acquire();
        return arrayList == null ? new ArrayList() : arrayList;
    }

    private void k(ArrayList arrayList) {
        arrayList.clear();
        this.a.release(arrayList);
    }

    public void a(Object obj, Object obj2) {
        if (!this.b.containsKey(obj) || !this.b.containsKey(obj2)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList arrayListF = (ArrayList) this.b.get(obj);
        if (arrayListF == null) {
            arrayListF = f();
            this.b.put(obj, arrayListF);
        }
        arrayListF.add(obj2);
    }

    public void b(Object obj) {
        if (this.b.containsKey(obj)) {
            return;
        }
        this.b.put(obj, null);
    }

    public void c() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = (ArrayList) this.b.valueAt(i);
            if (arrayList != null) {
                k(arrayList);
            }
        }
        this.b.clear();
    }

    public boolean d(Object obj) {
        return this.b.containsKey(obj);
    }

    public List g(Object obj) {
        return (List) this.b.get(obj);
    }

    public List h(Object obj) {
        int size = this.b.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            ArrayList arrayList2 = (ArrayList) this.b.valueAt(i);
            if (arrayList2 != null && arrayList2.contains(obj)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.b.keyAt(i));
            }
        }
        return arrayList;
    }

    public ArrayList i() {
        this.c.clear();
        this.d.clear();
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            e(this.b.keyAt(i), this.c, this.d);
        }
        return this.c;
    }

    public boolean j(Object obj) {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = (ArrayList) this.b.valueAt(i);
            if (arrayList != null && arrayList.contains(obj)) {
                return true;
            }
        }
        return false;
    }
}
