package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import java.lang.reflect.Constructor;

/* JADX INFO: loaded from: classes.dex */
public final class c0 extends g0.d implements g0.b {
    private Application a;
    private final g0.b b;
    private Bundle c;
    private f d;
    private androidx.savedstate.a e;

    public c0(Application application, s0.c cVar, Bundle bundle) {
        kotlin.jvm.internal.m.g(cVar, "owner");
        this.e = cVar.getSavedStateRegistry();
        this.d = cVar.getLifecycle();
        this.c = bundle;
        this.a = application;
        this.b = application != null ? g0.a.e.a(application) : new g0.a();
    }

    @Override // androidx.lifecycle.g0.b
    public f0 a(Class cls, j0.a aVar) {
        kotlin.jvm.internal.m.g(cls, "modelClass");
        kotlin.jvm.internal.m.g(aVar, "extras");
        String str = (String) aVar.a(g0.c.c);
        if (str == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        }
        if (aVar.a(z.a) == null || aVar.a(z.b) == null) {
            if (this.d != null) {
                return d(str, cls);
            }
            throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
        }
        Application application = (Application) aVar.a(g0.a.g);
        boolean zIsAssignableFrom = a.class.isAssignableFrom(cls);
        Constructor constructorC = (!zIsAssignableFrom || application == null) ? d0.c(cls, d0.b) : d0.c(cls, d0.a);
        if (constructorC == null) {
            return this.b.a(cls, aVar);
        }
        return (!zIsAssignableFrom || application == null) ? d0.d(cls, constructorC, z.a(aVar)) : d0.d(cls, constructorC, application, z.a(aVar));
    }

    @Override // androidx.lifecycle.g0.b
    public f0 b(Class cls) {
        kotlin.jvm.internal.m.g(cls, "modelClass");
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return d(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.lifecycle.g0.d
    public void c(f0 f0Var) {
        kotlin.jvm.internal.m.g(f0Var, "viewModel");
        if (this.d != null) {
            androidx.savedstate.a aVar = this.e;
            kotlin.jvm.internal.m.d(aVar);
            f fVar = this.d;
            kotlin.jvm.internal.m.d(fVar);
            LegacySavedStateHandleController.a(f0Var, aVar, fVar);
        }
    }

    public final f0 d(String str, Class cls) {
        f0 f0VarD;
        Application application;
        kotlin.jvm.internal.m.g(str, "key");
        kotlin.jvm.internal.m.g(cls, "modelClass");
        f fVar = this.d;
        if (fVar == null) {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        boolean zIsAssignableFrom = a.class.isAssignableFrom(cls);
        Constructor constructorC = (!zIsAssignableFrom || this.a == null) ? d0.c(cls, d0.b) : d0.c(cls, d0.a);
        if (constructorC == null) {
            return this.a != null ? this.b.b(cls) : g0.c.a.a().b(cls);
        }
        androidx.savedstate.a aVar = this.e;
        kotlin.jvm.internal.m.d(aVar);
        SavedStateHandleController savedStateHandleControllerB = LegacySavedStateHandleController.b(aVar, fVar, str, this.c);
        if (!zIsAssignableFrom || (application = this.a) == null) {
            f0VarD = d0.d(cls, constructorC, savedStateHandleControllerB.c());
        } else {
            kotlin.jvm.internal.m.d(application);
            f0VarD = d0.d(cls, constructorC, application, savedStateHandleControllerB.c());
        }
        f0VarD.e("androidx.lifecycle.savedstate.vm.tag", savedStateHandleControllerB);
        return f0VarD;
    }
}
