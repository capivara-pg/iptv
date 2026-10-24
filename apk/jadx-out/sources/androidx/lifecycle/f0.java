package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class f0 {
    private final Map a = new HashMap();
    private final Set b = new LinkedHashSet();
    private volatile boolean c = false;

    private static void b(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    final void a() {
        this.c = true;
        Map map = this.a;
        if (map != null) {
            synchronized (map) {
                Iterator it = this.a.values().iterator();
                while (it.hasNext()) {
                    b(it.next());
                }
            }
        }
        Set set = this.b;
        if (set != null) {
            synchronized (set) {
                Iterator it2 = this.b.iterator();
                while (it2.hasNext()) {
                    b((Closeable) it2.next());
                }
            }
        }
        d();
    }

    Object c(String str) {
        Object obj;
        Map map = this.a;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            obj = this.a.get(str);
        }
        return obj;
    }

    protected void d() {
    }

    Object e(String str, Object obj) {
        Object obj2;
        synchronized (this.a) {
            obj2 = this.a.get(str);
            if (obj2 == null) {
                this.a.put(str, obj);
            }
        }
        if (obj2 != null) {
            obj = obj2;
        }
        if (this.c) {
            b(obj);
        }
        return obj;
    }
}
