package androidx.fragment.app;

import android.view.ViewGroup;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class y {
    private final k a;
    private final ClassLoader b;
    int d;
    int e;
    int f;
    int g;
    int h;
    boolean i;
    String k;
    int l;
    CharSequence m;
    int n;
    CharSequence o;
    ArrayList p;
    ArrayList q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    ArrayList f27s;
    ArrayList c = new ArrayList();
    boolean j = true;
    boolean r = false;

    static final class a {
        int a;
        Fragment b;
        int c;
        int d;
        int e;
        int f;
        androidx.lifecycle.d.c g;
        androidx.lifecycle.d.c h;

        a() {
        }

        a(int i, Fragment fragment) {
            this.a = i;
            this.b = fragment;
            androidx.lifecycle.d.c cVar = androidx.lifecycle.d.c.RESUMED;
            this.g = cVar;
            this.h = cVar;
        }

        a(int i, Fragment fragment, androidx.lifecycle.d.c cVar) {
            this.a = i;
            this.b = fragment;
            this.g = fragment.mMaxState;
            this.h = cVar;
        }
    }

    y(k kVar, ClassLoader classLoader) {
        this.a = kVar;
        this.b = classLoader;
    }

    public y b(int i, Fragment fragment) {
        n(i, fragment, null, 1);
        return this;
    }

    public y c(int i, Fragment fragment, String str) {
        n(i, fragment, str, 1);
        return this;
    }

    y d(ViewGroup viewGroup, Fragment fragment, String str) {
        fragment.mContainer = viewGroup;
        return c(viewGroup.getId(), fragment, str);
    }

    public y e(Fragment fragment, String str) {
        n(0, fragment, str, 1);
        return this;
    }

    void f(a aVar) {
        this.c.add(aVar);
        aVar.c = this.d;
        aVar.d = this.e;
        aVar.e = this.f;
        aVar.f = this.g;
    }

    public y g(Fragment fragment) {
        f(new a(7, fragment));
        return this;
    }

    public abstract int h();

    public abstract int i();

    public abstract void j();

    public abstract void k();

    public y l(Fragment fragment) {
        f(new a(6, fragment));
        return this;
    }

    public y m() {
        if (this.i) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.j = false;
        return this;
    }

    void n(int i, Fragment fragment, String str, int i2) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = fragment.mTag;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
            fragment.mTag = str;
        }
        if (i != 0) {
            if (i == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i3 = fragment.mFragmentId;
            if (i3 != 0 && i3 != i) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i);
            }
            fragment.mFragmentId = i;
            fragment.mContainerId = i;
        }
        f(new a(i2, fragment));
    }

    public y o(Fragment fragment) {
        f(new a(4, fragment));
        return this;
    }

    public y p(Fragment fragment) {
        f(new a(3, fragment));
        return this;
    }

    public y q(int i, Fragment fragment) {
        return r(i, fragment, null);
    }

    public y r(int i, Fragment fragment, String str) {
        if (i == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        n(i, fragment, str, 2);
        return this;
    }

    public y s(Fragment fragment, androidx.lifecycle.d.c cVar) {
        f(new a(10, fragment, cVar));
        return this;
    }

    public y t(boolean z) {
        this.r = z;
        return this;
    }

    public y u(Fragment fragment) {
        f(new a(5, fragment));
        return this;
    }
}
