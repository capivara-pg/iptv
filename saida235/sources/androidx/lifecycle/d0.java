package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class d0 {
    private static final List a = fa.p.k(new Class[]{Application.class, y.class});
    private static final List b = fa.p.e(y.class);

    public static final Constructor c(Class cls, List list) {
        kotlin.jvm.internal.m.g(cls, "modelClass");
        kotlin.jvm.internal.m.g(list, "signature");
        Constructor<?>[] constructors = cls.getConstructors();
        kotlin.jvm.internal.m.f(constructors, "modelClass.constructors");
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            kotlin.jvm.internal.m.f(parameterTypes, "constructor.parameterTypes");
            List listH = fa.i.H(parameterTypes);
            if (kotlin.jvm.internal.m.b(list, listH)) {
                kotlin.jvm.internal.m.e(constructor, "null cannot be cast to non-null type java.lang.reflect.Constructor<T of androidx.lifecycle.SavedStateViewModelFactoryKt.findMatchingConstructor>");
                return constructor;
            }
            if (list.size() == listH.size() && listH.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final f0 d(Class cls, Constructor constructor, Object... objArr) {
        kotlin.jvm.internal.m.g(cls, "modelClass");
        kotlin.jvm.internal.m.g(constructor, "constructor");
        kotlin.jvm.internal.m.g(objArr, "params");
        try {
            return (f0) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access " + cls, e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e3.getCause());
        }
    }
}
