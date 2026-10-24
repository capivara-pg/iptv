package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import n.j;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class ActivityResultRegistry {
    private Random a = new Random();
    private final Map b = new HashMap();
    final Map c = new HashMap();
    private final Map d = new HashMap();
    ArrayList e = new ArrayList();
    final transient Map f = new HashMap();
    final Map g = new HashMap();
    final Bundle h = new Bundle();

    class a extends androidx.activity.result.c {
        final /* synthetic */ String a;
        final /* synthetic */ int b;
        final /* synthetic */ c.a c;

        a(String str, int i, c.a aVar) {
            this.a = str;
            this.b = i;
            this.c = aVar;
        }

        @Override // androidx.activity.result.c
        public void b(Object obj, j jVar) {
            ActivityResultRegistry.this.e.add(this.a);
            Integer num = (Integer) ActivityResultRegistry.this.c.get(this.a);
            ActivityResultRegistry.this.f(num != null ? num.intValue() : this.b, this.c, obj, jVar);
        }

        @Override // androidx.activity.result.c
        public void c() {
            ActivityResultRegistry.this.l(this.a);
        }
    }

    class b extends androidx.activity.result.c {
        final /* synthetic */ String a;
        final /* synthetic */ int b;
        final /* synthetic */ c.a c;

        b(String str, int i, c.a aVar) {
            this.a = str;
            this.b = i;
            this.c = aVar;
        }

        @Override // androidx.activity.result.c
        public void b(Object obj, j jVar) {
            ActivityResultRegistry.this.e.add(this.a);
            Integer num = (Integer) ActivityResultRegistry.this.c.get(this.a);
            ActivityResultRegistry.this.f(num != null ? num.intValue() : this.b, this.c, obj, jVar);
        }

        @Override // androidx.activity.result.c
        public void c() {
            ActivityResultRegistry.this.l(this.a);
        }
    }

    private static class c {
        final androidx.activity.result.b a;
        final c.a b;

        c(androidx.activity.result.b bVar, c.a aVar) {
            this.a = bVar;
            this.b = aVar;
        }
    }

    private static class d {
        final androidx.lifecycle.d a;
        private final ArrayList b = new ArrayList();

        d(androidx.lifecycle.d dVar) {
            this.a = dVar;
        }

        void a(androidx.lifecycle.e eVar) {
            this.a.a(eVar);
            this.b.add(eVar);
        }

        void b() {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                this.a.c((androidx.lifecycle.e) it.next());
            }
            this.b.clear();
        }
    }

    private void a(int i, String str) {
        this.b.put(Integer.valueOf(i), str);
        this.c.put(str, Integer.valueOf(i));
    }

    private void d(String str, int i, Intent intent, c cVar) {
        androidx.activity.result.b bVar;
        if (cVar != null && (bVar = cVar.a) != null) {
            bVar.a(cVar.b.c(i, intent));
        } else {
            this.g.remove(str);
            this.h.putParcelable(str, new androidx.activity.result.a(i, intent));
        }
    }

    private int e() {
        int iNextInt = this.a.nextInt(2147418112);
        while (true) {
            int i = iNextInt + 65536;
            if (!this.b.containsKey(Integer.valueOf(i))) {
                return i;
            }
            iNextInt = this.a.nextInt(2147418112);
        }
    }

    private int k(String str) {
        Integer num = (Integer) this.c.get(str);
        if (num != null) {
            return num.intValue();
        }
        int iE = e();
        a(iE, str);
        return iE;
    }

    public final boolean b(int i, int i2, Intent intent) {
        String str = (String) this.b.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        this.e.remove(str);
        d(str, i2, intent, (c) this.f.get(str));
        return true;
    }

    public final boolean c(int i, Object obj) {
        androidx.activity.result.b bVar;
        String str = (String) this.b.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        this.e.remove(str);
        c cVar = (c) this.f.get(str);
        if (cVar != null && (bVar = cVar.a) != null) {
            bVar.a(obj);
            return true;
        }
        this.h.remove(str);
        this.g.put(str, obj);
        return true;
    }

    public abstract void f(int i, c.a aVar, Object obj, j jVar);

    public final void g(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
        if (stringArrayList == null || integerArrayList == null) {
            return;
        }
        this.e = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
        this.a = (Random) bundle.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
        this.h.putAll(bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
        for (int i = 0; i < stringArrayList.size(); i++) {
            String str = stringArrayList.get(i);
            if (this.c.containsKey(str)) {
                Integer num = (Integer) this.c.remove(str);
                if (!this.h.containsKey(str)) {
                    this.b.remove(num);
                }
            }
            a(integerArrayList.get(i).intValue(), stringArrayList.get(i));
        }
    }

    public final void h(Bundle bundle) {
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(this.c.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(this.c.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(this.e));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) this.h.clone());
        bundle.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", this.a);
    }

    public final androidx.activity.result.c i(final String str, g gVar, final c.a aVar, final androidx.activity.result.b bVar) {
        androidx.lifecycle.d lifecycle = gVar.getLifecycle();
        if (lifecycle.b().a(androidx.lifecycle.d.c.STARTED)) {
            throw new IllegalStateException("LifecycleOwner " + gVar + " is attempting to register while current state is " + lifecycle.b() + ". LifecycleOwners must call register before they are STARTED.");
        }
        int iK = k(str);
        d dVar = (d) this.d.get(str);
        if (dVar == null) {
            dVar = new d(lifecycle);
        }
        dVar.a(new androidx.lifecycle.e() { // from class: androidx.activity.result.ActivityResultRegistry.1
            @Override // androidx.lifecycle.e
            public void a(g gVar2, androidx.lifecycle.d.b bVar2) {
                if (!androidx.lifecycle.d.b.ON_START.equals(bVar2)) {
                    if (androidx.lifecycle.d.b.ON_STOP.equals(bVar2)) {
                        ActivityResultRegistry.this.f.remove(str);
                        return;
                    } else {
                        if (androidx.lifecycle.d.b.ON_DESTROY.equals(bVar2)) {
                            ActivityResultRegistry.this.l(str);
                            return;
                        }
                        return;
                    }
                }
                ActivityResultRegistry.this.f.put(str, new c(bVar, aVar));
                if (ActivityResultRegistry.this.g.containsKey(str)) {
                    Object obj = ActivityResultRegistry.this.g.get(str);
                    ActivityResultRegistry.this.g.remove(str);
                    bVar.a(obj);
                }
                androidx.activity.result.a aVar2 = (androidx.activity.result.a) ActivityResultRegistry.this.h.getParcelable(str);
                if (aVar2 != null) {
                    ActivityResultRegistry.this.h.remove(str);
                    bVar.a(aVar.c(aVar2.b(), aVar2.a()));
                }
            }
        });
        this.d.put(str, dVar);
        return new a(str, iK, aVar);
    }

    public final androidx.activity.result.c j(String str, c.a aVar, androidx.activity.result.b bVar) {
        int iK = k(str);
        this.f.put(str, new c(bVar, aVar));
        if (this.g.containsKey(str)) {
            Object obj = this.g.get(str);
            this.g.remove(str);
            bVar.a(obj);
        }
        androidx.activity.result.a aVar2 = (androidx.activity.result.a) this.h.getParcelable(str);
        if (aVar2 != null) {
            this.h.remove(str);
            bVar.a(aVar.c(aVar2.b(), aVar2.a()));
        }
        return new b(str, iK, aVar);
    }

    final void l(String str) {
        Integer num;
        if (!this.e.contains(str) && (num = (Integer) this.c.remove(str)) != null) {
            this.b.remove(num);
        }
        this.f.remove(str);
        if (this.g.containsKey(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Dropping pending result for request ");
            sb.append(str);
            sb.append(": ");
            sb.append(this.g.get(str));
            this.g.remove(str);
        }
        if (this.h.containsKey(str)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Dropping pending result for request ");
            sb2.append(str);
            sb2.append(": ");
            sb2.append(this.h.getParcelable(str));
            this.h.remove(str);
        }
        d dVar = (d) this.d.get(str);
        if (dVar != null) {
            dVar.b();
            this.d.remove(str);
        }
    }
}
