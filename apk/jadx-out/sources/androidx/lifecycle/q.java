package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class q {
    public static final q a = new q();
    private static final Map b = new HashMap();
    private static final Map c = new HashMap();

    private q() {
    }

    private final c a(Constructor constructor, Object obj) {
        try {
            Object objNewInstance = constructor.newInstance(obj);
            kotlin.jvm.internal.m.f(objNewInstance, "{\n            constructo…tance(`object`)\n        }");
            return (c) objNewInstance;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Constructor b(Class cls) {
        try {
            Package r0 = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String name = r0 != null ? r0.getName() : "";
            kotlin.jvm.internal.m.f(name, "fullPackage");
            if (!(name.length() == 0)) {
                kotlin.jvm.internal.m.f(canonicalName, "name");
                canonicalName = canonicalName.substring(name.length() + 1);
                kotlin.jvm.internal.m.f(canonicalName, "this as java.lang.String).substring(startIndex)");
            }
            kotlin.jvm.internal.m.f(canonicalName, "if (fullPackage.isEmpty(…g(fullPackage.length + 1)");
            String strC = c(canonicalName);
            if (!(name.length() == 0)) {
                strC = name + '.' + strC;
            }
            Class<?> cls2 = Class.forName(strC);
            kotlin.jvm.internal.m.e(cls2, "null cannot be cast to non-null type java.lang.Class<out androidx.lifecycle.GeneratedAdapter>");
            Constructor declaredConstructor = cls2.getDeclaredConstructor(cls);
            if (declaredConstructor.isAccessible()) {
                return declaredConstructor;
            }
            declaredConstructor.setAccessible(true);
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String c(String str) {
        kotlin.jvm.internal.m.g(str, "className");
        return ya.l.s(str, ".", "_", false, 4, (Object) null) + "_LifecycleAdapter";
    }

    private final int d(Class cls) {
        Map map = b;
        Integer num = (Integer) map.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int iG = g(cls);
        map.put(cls, Integer.valueOf(iG));
        return iG;
    }

    private final boolean e(Class cls) {
        return cls != null && l.class.isAssignableFrom(cls);
    }

    public static final i f(Object obj) {
        kotlin.jvm.internal.m.g(obj, "object");
        if (obj instanceof i) {
            return (i) obj;
        }
        Class<?> cls = obj.getClass();
        q qVar = a;
        if (qVar.d(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        Object obj2 = c.get(cls);
        kotlin.jvm.internal.m.d(obj2);
        List list = (List) obj2;
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(qVar.a((Constructor) list.get(0), obj));
        }
        int size = list.size();
        c[] cVarArr = new c[size];
        for (int i = 0; i < size; i++) {
            cVarArr[i] = a.a((Constructor) list.get(i), obj);
        }
        return new CompositeGeneratedAdaptersObserver(cVarArr);
    }

    private final int g(Class cls) {
        ArrayList arrayList;
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor constructorB = b(cls);
        if (constructorB != null) {
            c.put(cls, fa.p.e(constructorB));
            return 2;
        }
        if (b.c.d(cls)) {
            return 1;
        }
        Class superclass = cls.getSuperclass();
        if (e(superclass)) {
            kotlin.jvm.internal.m.f(superclass, "superclass");
            if (d(superclass) == 1) {
                return 1;
            }
            Object obj = c.get(superclass);
            kotlin.jvm.internal.m.d(obj);
            arrayList = new ArrayList((Collection) obj);
        } else {
            arrayList = null;
        }
        Class<?>[] interfaces = cls.getInterfaces();
        kotlin.jvm.internal.m.f(interfaces, "klass.interfaces");
        for (Class<?> cls2 : interfaces) {
            if (e(cls2)) {
                kotlin.jvm.internal.m.f(cls2, "intrface");
                if (d(cls2) == 1) {
                    return 1;
                }
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                Object obj2 = c.get(cls2);
                kotlin.jvm.internal.m.d(obj2);
                arrayList.addAll((Collection) obj2);
            }
        }
        if (arrayList == null) {
            return 1;
        }
        c.put(cls, arrayList);
        return 2;
    }
}
