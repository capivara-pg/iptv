package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.f;
import androidx.lifecycle.m;
import java.util.Map;
import kotlin.jvm.internal.g;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    private static final b g = new b(null);
    private boolean b;
    private Bundle c;
    private boolean d;
    private Recreator.b e;
    private final j.b a = new j.b();
    private boolean f = true;

    /* JADX INFO: renamed from: androidx.savedstate.a$a, reason: collision with other inner class name */
    public interface InterfaceC0052a {
        void a(s0.c cVar);
    }

    private static final class b {
        private b() {
        }

        public /* synthetic */ b(g gVar) {
            this();
        }
    }

    public interface c {
        Bundle a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(a aVar, m mVar, f.a aVar2) {
        kotlin.jvm.internal.m.g(aVar, "this$0");
        kotlin.jvm.internal.m.g(mVar, "<anonymous parameter 0>");
        kotlin.jvm.internal.m.g(aVar2, "event");
        if (aVar2 == f.a.ON_START) {
            aVar.f = true;
        } else if (aVar2 == f.a.ON_STOP) {
            aVar.f = false;
        }
    }

    public final Bundle b(String str) {
        kotlin.jvm.internal.m.g(str, "key");
        if (!this.d) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component".toString());
        }
        Bundle bundle = this.c;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle != null ? bundle.getBundle(str) : null;
        Bundle bundle3 = this.c;
        if (bundle3 != null) {
            bundle3.remove(str);
        }
        Bundle bundle4 = this.c;
        boolean z = false;
        if (bundle4 != null && !bundle4.isEmpty()) {
            z = true;
        }
        if (!z) {
            this.c = null;
        }
        return bundle2;
    }

    public final c c(String str) {
        kotlin.jvm.internal.m.g(str, "key");
        for (Map.Entry entry : this.a) {
            kotlin.jvm.internal.m.f(entry, "components");
            String str2 = (String) entry.getKey();
            c cVar = (c) entry.getValue();
            if (kotlin.jvm.internal.m.b(str2, str)) {
                return cVar;
            }
        }
        return null;
    }

    public final void e(f fVar) {
        kotlin.jvm.internal.m.g(fVar, "lifecycle");
        if (!(!this.b)) {
            throw new IllegalStateException("SavedStateRegistry was already attached.".toString());
        }
        fVar.a(new s0.a(this));
        this.b = true;
    }

    public final void f(Bundle bundle) {
        if (!this.b) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).".toString());
        }
        if (!(!this.d)) {
            throw new IllegalStateException("SavedStateRegistry was already restored.".toString());
        }
        this.c = bundle != null ? bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key") : null;
        this.d = true;
    }

    public final void g(Bundle bundle) {
        kotlin.jvm.internal.m.g(bundle, "outBundle");
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.c;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        j.b.d dVarC = this.a.c();
        kotlin.jvm.internal.m.f(dVarC, "this.components.iteratorWithAdditions()");
        while (dVarC.hasNext()) {
            Map.Entry entry = (Map.Entry) dVarC.next();
            bundle2.putBundle((String) entry.getKey(), ((c) entry.getValue()).a());
        }
        if (bundle2.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    public final void h(String str, c cVar) {
        kotlin.jvm.internal.m.g(str, "key");
        kotlin.jvm.internal.m.g(cVar, "provider");
        if (!(((c) this.a.f(str, cVar)) == null)) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered".toString());
        }
    }

    public final void i(Class cls) {
        kotlin.jvm.internal.m.g(cls, "clazz");
        if (!this.f) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState".toString());
        }
        Recreator.b bVar = this.e;
        if (bVar == null) {
            bVar = new Recreator.b(this);
        }
        this.e = bVar;
        try {
            cls.getDeclaredConstructor(new Class[0]);
            Recreator.b bVar2 = this.e;
            if (bVar2 != null) {
                String name = cls.getName();
                kotlin.jvm.internal.m.f(name, "clazz.name");
                bVar2.b(name);
            }
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class " + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
        }
    }
}
