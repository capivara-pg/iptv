package androidx.lifecycle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class j0 {
    private final Map a = new LinkedHashMap();

    public final void a() {
        Iterator it = this.a.values().iterator();
        while (it.hasNext()) {
            ((f0) it.next()).a();
        }
        this.a.clear();
    }

    public final f0 b(String str) {
        kotlin.jvm.internal.m.g(str, "key");
        return (f0) this.a.get(str);
    }

    public final Set c() {
        return new HashSet(this.a.keySet());
    }

    public final void d(String str, f0 f0Var) {
        kotlin.jvm.internal.m.g(str, "key");
        kotlin.jvm.internal.m.g(f0Var, "viewModel");
        f0 f0Var2 = (f0) this.a.put(str, f0Var);
        if (f0Var2 != null) {
            f0Var2.d();
        }
    }
}
