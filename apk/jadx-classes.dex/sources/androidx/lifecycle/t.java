package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public final class t extends w.c {
    private static final Class[] f = {Application.class, s.class};
    private static final Class[] g = {s.class};
    private final Application a;
    private final w.b b;
    private final Bundle c;
    private final d d;
    private final SavedStateRegistry e;

    public t(Application application, androidx.savedstate.b bVar, Bundle bundle) {
        this.e = bVar.getSavedStateRegistry();
        this.d = bVar.getLifecycle();
        this.c = bundle;
        this.a = application;
        this.b = application != null ? w.a.c(application) : w.d.b();
    }

    private static Constructor d(Class cls, Class[] clsArr) {
        for (Constructor<?> constructor : cls.getConstructors()) {
            if (Arrays.equals(clsArr, constructor.getParameterTypes())) {
                return constructor;
            }
        }
        return null;
    }

    @Override // androidx.lifecycle.w.b
    public v a(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return c(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.lifecycle.w.e
    void b(v vVar) {
        SavedStateHandleController.b(vVar, this.e, this.d);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0046 A[Catch: InvocationTargetException -> 0x005a, InstantiationException -> 0x0076, IllegalAccessException -> 0x0093, TryCatch #2 {IllegalAccessException -> 0x0093, InstantiationException -> 0x0076, InvocationTargetException -> 0x005a, blocks: (B:13:0x0030, B:15:0x0034, B:17:0x0054, B:16:0x0046), top: B:28:0x0030 }] */
    @Override // androidx.lifecycle.w.c
    public v c(String str, Class cls) {
        v vVar;
        boolean zIsAssignableFrom = a.class.isAssignableFrom(cls);
        Constructor constructorD = (!zIsAssignableFrom || this.a == null) ? d(cls, g) : d(cls, f);
        if (constructorD == null) {
            return this.b.a(cls);
        }
        SavedStateHandleController savedStateHandleControllerD = SavedStateHandleController.d(this.e, this.d, str, this.c);
        if (zIsAssignableFrom) {
            try {
                Application application = this.a;
                if (application != null) {
                    vVar = (v) constructorD.newInstance(application, savedStateHandleControllerD.e());
                } else {
                    vVar = (v) constructorD.newInstance(savedStateHandleControllerD.e());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access " + cls, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("A " + cls + " cannot be instantiated.", e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("An exception happened in constructor of " + cls, e3.getCause());
            }
        } else {
            vVar = (v) constructorD.newInstance(savedStateHandleControllerD.e());
        }
        vVar.e("androidx.lifecycle.savedstate.vm.tag", savedStateHandleControllerD);
        return vVar;
    }
}
