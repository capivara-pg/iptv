package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import h0.j0;

/* JADX INFO: loaded from: classes.dex */
public class d extends androidx.fragment.app.d {
    private boolean a = false;
    private Dialog b;
    private j0 c;

    public d() {
        setCancelable(true);
    }

    private void N3() {
        if (this.c == null) {
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.c = j0.d(arguments.getBundle("selector"));
            }
            if (this.c == null) {
                this.c = j0.c;
            }
        }
    }

    public c O3(Context context, Bundle bundle) {
        return new c(context);
    }

    public h P3(Context context) {
        return new h(context);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog = this.b;
        if (dialog != null) {
            if (this.a) {
                ((h) dialog).updateLayout();
            } else {
                ((c) dialog).updateLayout();
            }
        }
    }

    @Override // androidx.fragment.app.d
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.a) {
            h hVarP3 = P3(getContext());
            this.b = hVarP3;
            hVarP3.setRouteSelector(this.c);
        } else {
            this.b = O3(getContext(), bundle);
        }
        return this.b;
    }

    @Override // androidx.fragment.app.d, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Dialog dialog = this.b;
        if (dialog == null || this.a) {
            return;
        }
        ((c) dialog).e(false);
    }

    public void setRouteSelector(j0 j0Var) {
        if (j0Var == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        N3();
        if (this.c.equals(j0Var)) {
            return;
        }
        this.c = j0Var;
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putBundle("selector", j0Var.a());
        setArguments(arguments);
        Dialog dialog = this.b;
        if (dialog == null || !this.a) {
            return;
        }
        ((h) dialog).setRouteSelector(j0Var);
    }

    void setUseDynamicGroup(boolean z) {
        if (this.b != null) {
            throw new IllegalStateException("This must be called before creating dialog");
        }
        this.a = z;
    }
}
