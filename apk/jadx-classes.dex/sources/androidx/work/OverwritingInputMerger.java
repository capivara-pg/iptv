package androidx.work;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import s0.h;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public final class OverwritingInputMerger extends h {
    public b b(List list) {
        b.a aVar = new b.a();
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            map.putAll(((b) it.next()).h());
        }
        aVar.d(map);
        return aVar.a();
    }
}
