package androidx.mediarouter.app;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import o0.k0;
import o0.l0;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteActionProvider extends androidx.core.view.b {
    private final l0 d;
    private final a e;
    private k0 f;
    private e g;
    private MediaRouteButton h;
    private boolean i;

    private static final class a extends l0.a {
        private final WeakReference a;

        public a(MediaRouteActionProvider mediaRouteActionProvider) {
            this.a = new WeakReference(mediaRouteActionProvider);
        }

        private void a(l0 l0Var) {
            MediaRouteActionProvider mediaRouteActionProvider = (MediaRouteActionProvider) this.a.get();
            if (mediaRouteActionProvider != null) {
                mediaRouteActionProvider.n();
            } else {
                l0Var.s(this);
            }
        }

        public void onProviderAdded(l0 l0Var, l0.g gVar) {
            a(l0Var);
        }

        public void onProviderChanged(l0 l0Var, l0.g gVar) {
            a(l0Var);
        }

        public void onProviderRemoved(l0 l0Var, l0.g gVar) {
            a(l0Var);
        }

        public void onRouteAdded(l0 l0Var, l0.h hVar) {
            a(l0Var);
        }

        public void onRouteChanged(l0 l0Var, l0.h hVar) {
            a(l0Var);
        }

        public void onRouteRemoved(l0 l0Var, l0.h hVar) {
            a(l0Var);
        }
    }

    public MediaRouteActionProvider(Context context) {
        super(context);
        this.f = k0.c;
        this.g = e.getDefault();
        this.d = l0.j(context);
        this.e = new a(this);
    }

    @Override // androidx.core.view.b
    public boolean c() {
        return this.i || this.d.q(this.f, 1);
    }

    @Override // androidx.core.view.b
    public View d() {
        if (this.h != null) {
            Log.e("MRActionProvider", "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old menu item...");
        }
        MediaRouteButton mediaRouteButtonM = m();
        this.h = mediaRouteButtonM;
        mediaRouteButtonM.setCheatSheetEnabled(true);
        this.h.setRouteSelector(this.f);
        this.h.setAlwaysVisible(this.i);
        this.h.setDialogFactory(this.g);
        this.h.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
        return this.h;
    }

    @Override // androidx.core.view.b
    public boolean f() {
        MediaRouteButton mediaRouteButton = this.h;
        if (mediaRouteButton != null) {
            return mediaRouteButton.d();
        }
        return false;
    }

    @Override // androidx.core.view.b
    public boolean h() {
        return true;
    }

    public MediaRouteButton m() {
        return new MediaRouteButton(a());
    }

    void n() {
        i();
    }

    public void o(boolean z) {
        if (this.i != z) {
            this.i = z;
            i();
            MediaRouteButton mediaRouteButton = this.h;
            if (mediaRouteButton != null) {
                mediaRouteButton.setAlwaysVisible(this.i);
            }
        }
    }

    public void p(e eVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("factory must not be null");
        }
        if (this.g != eVar) {
            this.g = eVar;
            MediaRouteButton mediaRouteButton = this.h;
            if (mediaRouteButton != null) {
                mediaRouteButton.setDialogFactory(eVar);
            }
        }
    }

    public void q(k0 k0Var) {
        if (k0Var == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.f.equals(k0Var)) {
            return;
        }
        if (!this.f.f()) {
            this.d.s(this.e);
        }
        if (!k0Var.f()) {
            this.d.a(k0Var, this.e);
        }
        this.f = k0Var;
        n();
        MediaRouteButton mediaRouteButton = this.h;
        if (mediaRouteButton != null) {
            mediaRouteButton.setRouteSelector(k0Var);
        }
    }
}
