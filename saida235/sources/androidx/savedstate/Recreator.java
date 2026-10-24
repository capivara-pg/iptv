package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.f;
import androidx.lifecycle.i;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.m;
import s0.c;

/* JADX INFO: loaded from: classes.dex */
public final class Recreator implements i {
    public static final a b = new a(null);
    private final c a;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }
    }

    public static final class b implements androidx.savedstate.a.c {
        private final Set a;

        public b(androidx.savedstate.a aVar) {
            m.g(aVar, "registry");
            this.a = new LinkedHashSet();
            aVar.h("androidx.savedstate.Restarter", this);
        }

        @Override // androidx.savedstate.a.c
        public Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.a));
            return bundle;
        }

        public final void b(String str) {
            m.g(str, "className");
            this.a.add(str);
        }
    }

    public Recreator(c cVar) {
        m.g(cVar, "owner");
        this.a = cVar;
    }

    private final void b(String str) {
        try {
            Class<? extends U> clsAsSubclass = Class.forName(str, false, Recreator.class.getClassLoader()).asSubclass(androidx.savedstate.a.InterfaceC0052a.class);
            m.f(clsAsSubclass, "{\n                Class.…class.java)\n            }");
            try {
                Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                try {
                    Object objNewInstance = declaredConstructor.newInstance(new Object[0]);
                    m.f(objNewInstance, "{\n                constr…wInstance()\n            }");
                    ((androidx.savedstate.a.InterfaceC0052a) objNewInstance).a(this.a);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to instantiate " + str, e);
                }
            } catch (NoSuchMethodException e2) {
                throw new IllegalStateException("Class " + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
            }
        } catch (ClassNotFoundException e3) {
            throw new RuntimeException("Class " + str + " wasn't found", e3);
        }
    }

    @Override // androidx.lifecycle.i
    public void a(androidx.lifecycle.m mVar, f.a aVar) {
        m.g(mVar, "source");
        m.g(aVar, "event");
        if (aVar != f.a.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        mVar.getLifecycle().d(this);
        Bundle bundleB = this.a.getSavedStateRegistry().b("androidx.savedstate.Restarter");
        if (bundleB == null) {
            return;
        }
        ArrayList<String> stringArrayList = bundleB.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        Iterator<String> it = stringArrayList.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }
}
