package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class x {
    private final HashMap a = new HashMap();

    public final void a() {
        Iterator it = this.a.values().iterator();
        while (it.hasNext()) {
            ((v) it.next()).a();
        }
        this.a.clear();
    }

    final v b(String str) {
        return (v) this.a.get(str);
    }

    Set c() {
        return new HashSet(this.a.keySet());
    }

    final void d(String str, v vVar) {
        v vVar2 = (v) this.a.put(str, vVar);
        if (vVar2 != null) {
            vVar2.d();
        }
    }
}
