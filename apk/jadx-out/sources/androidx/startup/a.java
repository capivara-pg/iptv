package androidx.startup;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import x0.b;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    private static volatile a d;
    private static final Object e = new Object();
    final Context c;
    final Set b = new HashSet();
    final Map a = new HashMap();

    a(Context context) {
        this.c = context.getApplicationContext();
    }

    private Object c(Class cls, Set set) {
        Object objCreate;
        if (b.d()) {
            try {
                b.a(cls.getSimpleName());
            } catch (Throwable th) {
                b.b();
                throw th;
            }
        }
        if (set.contains(cls)) {
            throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", cls.getName()));
        }
        if (this.a.containsKey(cls)) {
            objCreate = this.a.get(cls);
        } else {
            set.add(cls);
            try {
                w0.a aVar = (w0.a) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                List<Class> listDependencies = aVar.dependencies();
                if (!listDependencies.isEmpty()) {
                    for (Class cls2 : listDependencies) {
                        if (!this.a.containsKey(cls2)) {
                            c(cls2, set);
                        }
                    }
                }
                objCreate = aVar.create(this.c);
                set.remove(cls);
                this.a.put(cls, objCreate);
            } catch (Throwable th2) {
                throw new w0.b(th2);
            }
        }
        b.b();
        return objCreate;
    }

    public static a d(Context context) {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new a(context);
                }
            }
        }
        return d;
    }

    void a() {
        try {
            try {
                b.a("Startup");
                b(this.c.getPackageManager().getProviderInfo(new ComponentName(this.c.getPackageName(), InitializationProvider.class.getName()), 128).metaData);
                b.b();
            } catch (PackageManager.NameNotFoundException e2) {
                throw new w0.b(e2);
            }
        } catch (Throwable th) {
            b.b();
            throw th;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: w0.b */
    void b(Bundle bundle) throws w0.b {
        String string = this.c.getString(R$string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet = new HashSet();
                for (String str : bundle.keySet()) {
                    if (string.equals(bundle.getString(str, null))) {
                        Class<?> cls = Class.forName(str);
                        if (w0.a.class.isAssignableFrom(cls)) {
                            this.b.add(cls);
                        }
                    }
                }
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    c((Class) it.next(), hashSet);
                }
            } catch (ClassNotFoundException e2) {
                throw new w0.b(e2);
            }
        }
    }
}
