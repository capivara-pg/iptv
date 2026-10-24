package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class y {
    public static final a f = new a(null);
    private static final Class[] g = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};
    private final Map a;
    private final Map b;
    private final Map c;
    private final Map d;
    private final androidx.savedstate.a.c e;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final y a(Bundle bundle, Bundle bundle2) {
            if (bundle == null) {
                if (bundle2 == null) {
                    return new y();
                }
                HashMap map = new HashMap();
                for (String str : bundle2.keySet()) {
                    kotlin.jvm.internal.m.f(str, "key");
                    map.put(str, bundle2.get(str));
                }
                return new y(map);
            }
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
            if (!((parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) ? false : true)) {
                throw new IllegalStateException("Invalid bundle passed as restored state".toString());
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int size = parcelableArrayList.size();
            for (int i = 0; i < size; i++) {
                Object obj = parcelableArrayList.get(i);
                kotlin.jvm.internal.m.e(obj, "null cannot be cast to non-null type kotlin.String");
                linkedHashMap.put((String) obj, parcelableArrayList2.get(i));
            }
            return new y(linkedHashMap);
        }

        public final boolean b(Object obj) {
            if (obj == null) {
                return true;
            }
            for (Class cls : y.g) {
                kotlin.jvm.internal.m.d(cls);
                if (cls.isInstance(obj)) {
                    return true;
                }
            }
            return false;
        }
    }

    public y(Map map) {
        kotlin.jvm.internal.m.g(map, "initialState");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.a = linkedHashMap;
        this.b = new LinkedHashMap();
        this.c = new LinkedHashMap();
        this.d = new LinkedHashMap();
        this.e = new androidx.savedstate.a.c() { // from class: androidx.lifecycle.x
            @Override // androidx.savedstate.a.c
            public final Bundle a() {
                return y.d(this.a);
            }
        };
        linkedHashMap.putAll(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bundle d(y yVar) {
        kotlin.jvm.internal.m.g(yVar, "this$0");
        for (Map.Entry entry : fa.h0.s(yVar.b).entrySet()) {
            yVar.e((String) entry.getKey(), ((androidx.savedstate.a.c) entry.getValue()).a());
        }
        Set<String> setKeySet = yVar.a.keySet();
        ArrayList arrayList = new ArrayList(setKeySet.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : setKeySet) {
            arrayList.add(str);
            arrayList2.add(yVar.a.get(str));
        }
        return z.b.a(new ea.m[]{ea.r.a("keys", arrayList), ea.r.a("values", arrayList2)});
    }

    public final androidx.savedstate.a.c c() {
        return this.e;
    }

    public final void e(String str, Object obj) {
        kotlin.jvm.internal.m.g(str, "key");
        if (!f.b(obj)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can't put value with type ");
            kotlin.jvm.internal.m.d(obj);
            sb.append(obj.getClass());
            sb.append(" into saved state");
            throw new IllegalArgumentException(sb.toString());
        }
        Object obj2 = this.c.get(str);
        s sVar = obj2 instanceof s ? (s) obj2 : null;
        if (sVar != null) {
            sVar.setValue(obj);
        } else {
            this.a.put(str, obj);
        }
        kotlinx.coroutines.flow.k kVar = (kotlinx.coroutines.flow.k) this.d.get(str);
        if (kVar == null) {
            return;
        }
        kVar.setValue(obj);
    }

    public y() {
        this.a = new LinkedHashMap();
        this.b = new LinkedHashMap();
        this.c = new LinkedHashMap();
        this.d = new LinkedHashMap();
        this.e = new androidx.savedstate.a.c() { // from class: androidx.lifecycle.x
            @Override // androidx.savedstate.a.c
            public final Bundle a() {
                return y.d(this.a);
            }
        };
    }
}
