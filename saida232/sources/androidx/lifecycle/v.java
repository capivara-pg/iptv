package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class v {
    private final Map a = new HashMap();
    private volatile boolean b = false;

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
        this.b = true;
        Map map = this.a;
        if (map != null) {
            synchronized (map) {
                Iterator it = this.a.values().iterator();
                while (it.hasNext()) {
                    b(it.next());
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
        if (this.b) {
            b(obj);
        }
        return obj;
    }
}
