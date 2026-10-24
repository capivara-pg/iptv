package androidx.lifecycle;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class SavedStateHandleController implements e {
    private final String a;
    private boolean b = false;
    private final s c;

    static final class a implements SavedStateRegistry.a {
        a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.a
        public void a(androidx.savedstate.b bVar) {
            if (!(bVar instanceof y)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
            }
            x viewModelStore = ((y) bVar).getViewModelStore();
            SavedStateRegistry savedStateRegistry = bVar.getSavedStateRegistry();
            Iterator it = viewModelStore.c().iterator();
            while (it.hasNext()) {
                SavedStateHandleController.b(viewModelStore.b((String) it.next()), savedStateRegistry, bVar.getLifecycle());
            }
            if (viewModelStore.c().isEmpty()) {
                return;
            }
            savedStateRegistry.e(a.class);
        }
    }

    SavedStateHandleController(String str, s sVar) {
        this.a = str;
        this.c = sVar;
    }

    static void b(v vVar, SavedStateRegistry savedStateRegistry, d dVar) {
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) vVar.c("androidx.lifecycle.savedstate.vm.tag");
        if (savedStateHandleController == null || savedStateHandleController.f()) {
            return;
        }
        savedStateHandleController.c(savedStateRegistry, dVar);
        g(savedStateRegistry, dVar);
    }

    static SavedStateHandleController d(SavedStateRegistry savedStateRegistry, d dVar, String str, Bundle bundle) {
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, s.a(savedStateRegistry.a(str), bundle));
        savedStateHandleController.c(savedStateRegistry, dVar);
        g(savedStateRegistry, dVar);
        return savedStateHandleController;
    }

    private static void g(final SavedStateRegistry savedStateRegistry, final d dVar) {
        d.c cVarB = dVar.b();
        if (cVarB == d.c.INITIALIZED || cVarB.a(d.c.STARTED)) {
            savedStateRegistry.e(a.class);
        } else {
            dVar.a(new e() { // from class: androidx.lifecycle.SavedStateHandleController.1
                @Override // androidx.lifecycle.e
                public void a(g gVar, d.b bVar) {
                    if (bVar == d.b.ON_START) {
                        dVar.c(this);
                        savedStateRegistry.e(a.class);
                    }
                }
            });
        }
    }

    @Override // androidx.lifecycle.e
    public void a(g gVar, d.b bVar) {
        if (bVar == d.b.ON_DESTROY) {
            this.b = false;
            gVar.getLifecycle().c(this);
        }
    }

    void c(SavedStateRegistry savedStateRegistry, d dVar) {
        if (this.b) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        this.b = true;
        dVar.a(this);
        savedStateRegistry.d(this.a, this.c.b());
    }

    s e() {
        return this.c;
    }

    boolean f() {
        return this.b;
    }
}
