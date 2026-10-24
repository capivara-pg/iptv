package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
class m {
    private final CopyOnWriteArrayList a = new CopyOnWriteArrayList();
    private final n b;

    private static final class a {
        final n.l a;
        final boolean b;

        a(n.l lVar, boolean z) {
            this.a = lVar;
            this.b = z;
        }
    }

    m(n nVar) {
        this.b = nVar;
    }

    void a(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().a(fragment, bundle, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentActivityCreated(this.b, fragment, bundle);
            }
        }
    }

    void b(Fragment fragment, boolean z) {
        Context contextF = this.b.t0().f();
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().b(fragment, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentAttached(this.b, fragment, contextF);
            }
        }
    }

    void c(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().c(fragment, bundle, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentCreated(this.b, fragment, bundle);
            }
        }
    }

    void d(Fragment fragment, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().d(fragment, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentDestroyed(this.b, fragment);
            }
        }
    }

    void e(Fragment fragment, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().e(fragment, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentDetached(this.b, fragment);
            }
        }
    }

    void f(Fragment fragment, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().f(fragment, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentPaused(this.b, fragment);
            }
        }
    }

    void g(Fragment fragment, boolean z) {
        Context contextF = this.b.t0().f();
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().g(fragment, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentPreAttached(this.b, fragment, contextF);
            }
        }
    }

    void h(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().h(fragment, bundle, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentPreCreated(this.b, fragment, bundle);
            }
        }
    }

    void i(Fragment fragment, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().i(fragment, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentResumed(this.b, fragment);
            }
        }
    }

    void j(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().j(fragment, bundle, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentSaveInstanceState(this.b, fragment, bundle);
            }
        }
    }

    void k(Fragment fragment, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().k(fragment, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentStarted(this.b, fragment);
            }
        }
    }

    void l(Fragment fragment, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().l(fragment, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentStopped(this.b, fragment);
            }
        }
    }

    void m(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().m(fragment, view, bundle, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentViewCreated(this.b, fragment, view, bundle);
            }
        }
    }

    void n(Fragment fragment, boolean z) {
        Fragment fragmentW0 = this.b.w0();
        if (fragmentW0 != null) {
            fragmentW0.getParentFragmentManager().v0().n(fragment, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.onFragmentViewDestroyed(this.b, fragment);
            }
        }
    }

    public void o(n.l lVar, boolean z) {
        this.a.add(new a(lVar, z));
    }

    public void p(n.l lVar) {
        synchronized (this.a) {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                if (((a) this.a.get(i)).a == lVar) {
                    this.a.remove(i);
                    break;
                }
            }
        }
    }
}
