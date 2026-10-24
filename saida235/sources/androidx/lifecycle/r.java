package androidx.lifecycle;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class r {
    private final Map a = new HashMap();

    public boolean a(String str, int i) {
        kotlin.jvm.internal.m.g(str, "name");
        Integer num = (Integer) this.a.get(str);
        int iIntValue = num != null ? num.intValue() : 0;
        boolean z = (iIntValue & i) != 0;
        this.a.put(str, Integer.valueOf(i | iIntValue));
        return !z;
    }
}
