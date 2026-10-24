package androidx.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class a extends g implements Map {
    f mCollections;

    /* JADX INFO: renamed from: androidx.collection.a$a, reason: collision with other inner class name */
    class C0015a extends f {
        C0015a() {
        }

        @Override // androidx.collection.f
        protected void a() {
            a.this.clear();
        }

        @Override // androidx.collection.f
        protected Object b(int i, int i2) {
            return a.this.mArray[(i << 1) + i2];
        }

        @Override // androidx.collection.f
        protected Map c() {
            return a.this;
        }

        @Override // androidx.collection.f
        protected int d() {
            return a.this.mSize;
        }

        @Override // androidx.collection.f
        protected int e(Object obj) {
            return a.this.indexOfKey(obj);
        }

        @Override // androidx.collection.f
        protected int f(Object obj) {
            return a.this.indexOfValue(obj);
        }

        @Override // androidx.collection.f
        protected void g(Object obj, Object obj2) {
            a.this.put(obj, obj2);
        }

        @Override // androidx.collection.f
        protected void h(int i) {
            a.this.removeAt(i);
        }

        @Override // androidx.collection.f
        protected Object i(int i, Object obj) {
            return a.this.setValueAt(i, obj);
        }
    }

    public a() {
    }

    private f d() {
        if (this.mCollections == null) {
            this.mCollections = new C0015a();
        }
        return this.mCollections;
    }

    public boolean containsAll(Collection<?> collection) {
        return f.j(this, collection);
    }

    @Override // java.util.Map
    public Set<Map.Entry<Object, Object>> entrySet() {
        return d().l();
    }

    @Override // java.util.Map
    public Set<Object> keySet() {
        return d().m();
    }

    @Override // java.util.Map
    public void putAll(Map<Object, Object> map) {
        ensureCapacity(this.mSize + map.size());
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean removeAll(Collection<?> collection) {
        return f.o(this, collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return f.p(this, collection);
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return d().n();
    }

    public a(int i) {
        super(i);
    }

    public a(g gVar) {
        super(gVar);
    }
}
