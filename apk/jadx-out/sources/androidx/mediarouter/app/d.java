package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import o0.k0;

/* JADX INFO: loaded from: classes.dex */
public class d extends androidx.fragment.app.d {
    private boolean a = false;
    private Dialog b;
    private k0 c;

    public d() {
        setCancelable(true);
    }

    private void i4() {
        if (this.c == null) {
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.c = k0.d(arguments.getBundle("selector"));
            }
            if (this.c == null) {
                this.c = k0.c;
            }
        }
    }

    public c j4(Context context, Bundle bundle) {
        return new c(context);
    }

    public h k4(Context context) {
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
            h hVarK4 = k4(getContext());
            this.b = hVarK4;
            hVarK4.setRouteSelector(this.c);
        } else {
            this.b = j4(getContext(), bundle);
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

    public void setRouteSelector(k0 k0Var) {
        if (k0Var == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        i4();
        if (this.c.equals(k0Var)) {
            return;
        }
        this.c = k0Var;
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putBundle("selector", k0Var.a());
        setArguments(arguments);
        Dialog dialog = this.b;
        if (dialog == null || !this.a) {
            return;
        }
        ((h) dialog).setRouteSelector(k0Var);
    }

    void setUseDynamicGroup(boolean z) {
        if (this.b != null) {
            throw new IllegalStateException("This must be called before creating dialog");
        }
        this.a = z;
    }
}
