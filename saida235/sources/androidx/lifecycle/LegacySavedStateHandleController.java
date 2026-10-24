package androidx.lifecycle;

import android.os.Bundle;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class LegacySavedStateHandleController {
    public static final LegacySavedStateHandleController a = new LegacySavedStateHandleController();

    public static final class a implements androidx.savedstate.a.InterfaceC0052a {
        @Override // androidx.savedstate.a.InterfaceC0052a
        public void a(s0.c cVar) {
            kotlin.jvm.internal.m.g(cVar, "owner");
            if (!(cVar instanceof k0)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner".toString());
            }
            j0 viewModelStore = ((k0) cVar).getViewModelStore();
            androidx.savedstate.a savedStateRegistry = cVar.getSavedStateRegistry();
            Iterator it = viewModelStore.c().iterator();
            while (it.hasNext()) {
                f0 f0VarB = viewModelStore.b((String) it.next());
                kotlin.jvm.internal.m.d(f0VarB);
                LegacySavedStateHandleController.a(f0VarB, savedStateRegistry, cVar.getLifecycle());
            }
            if (!viewModelStore.c().isEmpty()) {
                savedStateRegistry.i(a.class);
            }
        }
    }

    private LegacySavedStateHandleController() {
    }

    public static final void a(f0 f0Var, androidx.savedstate.a aVar, f fVar) {
        kotlin.jvm.internal.m.g(f0Var, "viewModel");
        kotlin.jvm.internal.m.g(aVar, "registry");
        kotlin.jvm.internal.m.g(fVar, "lifecycle");
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) f0Var.c("androidx.lifecycle.savedstate.vm.tag");
        if (savedStateHandleController == null || savedStateHandleController.d()) {
            return;
        }
        savedStateHandleController.b(aVar, fVar);
        a.c(aVar, fVar);
    }

    public static final SavedStateHandleController b(androidx.savedstate.a aVar, f fVar, String str, Bundle bundle) {
        kotlin.jvm.internal.m.g(aVar, "registry");
        kotlin.jvm.internal.m.g(fVar, "lifecycle");
        kotlin.jvm.internal.m.d(str);
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, y.f.a(aVar.b(str), bundle));
        savedStateHandleController.b(aVar, fVar);
        a.c(aVar, fVar);
        return savedStateHandleController;
    }

    private final void c(final androidx.savedstate.a aVar, final f fVar) {
        f.b bVarB = fVar.b();
        if (bVarB == f.b.INITIALIZED || bVarB.b(f.b.STARTED)) {
            aVar.i(a.class);
        } else {
            fVar.a(new i() { // from class: androidx.lifecycle.LegacySavedStateHandleController$tryToAddRecreator$1
                @Override // androidx.lifecycle.i
                public void a(m mVar, f.a aVar2) {
                    kotlin.jvm.internal.m.g(mVar, "source");
                    kotlin.jvm.internal.m.g(aVar2, "event");
                    if (aVar2 == f.a.ON_START) {
                        fVar.d(this);
                        aVar.i(LegacySavedStateHandleController.a.class);
                    }
                }
            });
        }
    }
}
