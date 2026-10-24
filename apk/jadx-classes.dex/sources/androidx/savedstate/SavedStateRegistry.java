package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.d;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import java.util.Map;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public final class SavedStateRegistry {
    private Bundle b;
    private boolean c;
    private Recreator.a d;
    private h.b a = new h.b();
    boolean e = true;

    public interface a {
        void a(androidx.savedstate.b bVar);
    }

    public interface b {
        Bundle a();
    }

    SavedStateRegistry() {
    }

    public Bundle a(String str) {
        if (!this.c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.b.remove(str);
        if (this.b.isEmpty()) {
            this.b = null;
        }
        return bundle2;
    }

    void b(d dVar, Bundle bundle) {
        if (this.c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            this.b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        dVar.a(new e() { // from class: androidx.savedstate.SavedStateRegistry.1
            @Override // androidx.lifecycle.e
            public void a(g gVar, d.b bVar) {
                if (bVar == d.b.ON_START) {
                    SavedStateRegistry.this.e = true;
                } else if (bVar == d.b.ON_STOP) {
                    SavedStateRegistry.this.e = false;
                }
            }
        });
        this.c = true;
    }

    void c(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        h.b.d dVarC = this.a.c();
        while (dVarC.hasNext()) {
            Map.Entry entry = (Map.Entry) dVarC.next();
            bundle2.putBundle((String) entry.getKey(), ((b) entry.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    public void d(String str, b bVar) {
        if (((b) this.a.f(str, bVar)) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public void e(Class cls) {
        if (!this.e) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.d == null) {
            this.d = new Recreator.a(this);
        }
        try {
            cls.getDeclaredConstructor(new Class[0]);
            this.d.b(cls.getName());
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class" + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
        }
    }
}
