package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class b {
    static b c = new b();
    private final Map a = new HashMap();
    private final Map b = new HashMap();

    static class a {
        final Map a = new HashMap();
        final Map b;

        a(Map map) {
            this.b = map;
            for (Map.Entry entry : map.entrySet()) {
                f.a aVar = (f.a) entry.getValue();
                List arrayList = (List) this.a.get(aVar);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.a.put(aVar, arrayList);
                }
                arrayList.add((C0033b) entry.getKey());
            }
        }

        private static void b(List list, m mVar, f.a aVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((C0033b) list.get(size)).a(mVar, aVar, obj);
                }
            }
        }

        void a(m mVar, f.a aVar, Object obj) {
            b((List) this.a.get(aVar), mVar, aVar, obj);
            b((List) this.a.get(f.a.ON_ANY), mVar, aVar, obj);
        }
    }

    /* JADX INFO: renamed from: androidx.lifecycle.b$b, reason: collision with other inner class name */
    static final class C0033b {
        final int a;
        final Method b;

        C0033b(int i, Method method) {
            this.a = i;
            this.b = method;
            method.setAccessible(true);
        }

        void a(m mVar, f.a aVar, Object obj) {
            try {
                int i = this.a;
                if (i == 0) {
                    this.b.invoke(obj, new Object[0]);
                } else if (i == 1) {
                    this.b.invoke(obj, mVar);
                } else {
                    if (i != 2) {
                        return;
                    }
                    this.b.invoke(obj, mVar, aVar);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException("Failed to call observer method", e2.getCause());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C0033b)) {
                return false;
            }
            C0033b c0033b = (C0033b) obj;
            return this.a == c0033b.a && this.b.getName().equals(c0033b.b.getName());
        }

        public int hashCode() {
            return (this.a * 31) + this.b.getName().hashCode();
        }
    }

    b() {
    }

    private a a(Class cls, Method[] methodArr) {
        int i;
        a aVarC;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null && (aVarC = c(superclass)) != null) {
            map.putAll(aVarC.b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry entry : c(cls2).b.entrySet()) {
                e(map, (C0033b) entry.getKey(), (f.a) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = b(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            u uVar = (u) method.getAnnotation(u.class);
            if (uVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else {
                    if (!m.class.isAssignableFrom(parameterTypes[0])) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i = 1;
                }
                f.a aVarValue = uVar.value();
                if (parameterTypes.length > 1) {
                    if (!f.a.class.isAssignableFrom(parameterTypes[1])) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (aVarValue != f.a.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                e(map, new C0033b(i, method), aVarValue, cls);
                z = true;
            }
        }
        a aVar = new a(map);
        this.a.put(cls, aVar);
        this.b.put(cls, Boolean.valueOf(z));
        return aVar;
    }

    private Method[] b(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e);
        }
    }

    private void e(Map map, C0033b c0033b, f.a aVar, Class cls) {
        f.a aVar2 = (f.a) map.get(c0033b);
        if (aVar2 == null || aVar == aVar2) {
            if (aVar2 == null) {
                map.put(c0033b, aVar);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + c0033b.b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + aVar2 + ", new value " + aVar);
    }

    a c(Class cls) {
        a aVar = (a) this.a.get(cls);
        return aVar != null ? aVar : a(cls, null);
    }

    boolean d(Class cls) {
        Boolean bool = (Boolean) this.b.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] methodArrB = b(cls);
        for (Method method : methodArrB) {
            if (((u) method.getAnnotation(u.class)) != null) {
                a(cls, methodArrB);
                return true;
            }
        }
        this.b.put(cls, Boolean.FALSE);
        return false;
    }
}
