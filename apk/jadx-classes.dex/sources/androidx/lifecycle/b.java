package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
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
                d.b bVar = (d.b) entry.getValue();
                List arrayList = (List) this.a.get(bVar);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.a.put(bVar, arrayList);
                }
                arrayList.add(entry.getKey());
            }
        }

        private static void b(List list, g gVar, d.b bVar, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((C0032b) list.get(size)).a(gVar, bVar, obj);
                }
            }
        }

        void a(g gVar, d.b bVar, Object obj) {
            b((List) this.a.get(bVar), gVar, bVar, obj);
            b((List) this.a.get(d.b.ON_ANY), gVar, bVar, obj);
        }
    }

    /* JADX INFO: renamed from: androidx.lifecycle.b$b, reason: collision with other inner class name */
    static final class C0032b {
        final int a;
        final Method b;

        C0032b(int i, Method method) {
            this.a = i;
            this.b = method;
            method.setAccessible(true);
        }

        void a(g gVar, d.b bVar, Object obj) {
            try {
                int i = this.a;
                if (i == 0) {
                    this.b.invoke(obj, new Object[0]);
                } else if (i == 1) {
                    this.b.invoke(obj, gVar);
                } else {
                    if (i != 2) {
                        return;
                    }
                    this.b.invoke(obj, gVar, bVar);
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
            if (!(obj instanceof C0032b)) {
                return false;
            }
            C0032b c0032b = (C0032b) obj;
            return this.a == c0032b.a && this.b.getName().equals(c0032b.b.getName());
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
                e(map, (C0032b) entry.getKey(), (d.b) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = b(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            n nVar = (n) method.getAnnotation(n.class);
            if (nVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(g.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i = 1;
                }
                d.b bVarValue = nVar.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(d.b.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (bVarValue != d.b.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                e(map, new C0032b(i, method), bVarValue, cls);
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

    private void e(Map map, C0032b c0032b, d.b bVar, Class cls) {
        d.b bVar2 = (d.b) map.get(c0032b);
        if (bVar2 == null || bVar == bVar2) {
            if (bVar2 == null) {
                map.put(c0032b, bVar);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + c0032b.b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + bVar2 + ", new value " + bVar);
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
            if (((n) method.getAnnotation(n.class)) != null) {
                a(cls, methodArrB);
                return true;
            }
        }
        this.b.put(cls, Boolean.FALSE);
        return false;
    }
}
