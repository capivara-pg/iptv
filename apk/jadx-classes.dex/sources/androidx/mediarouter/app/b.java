package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import h0.j0;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class b extends androidx.fragment.app.d {
    private static final String ARGUMENT_SELECTOR = "selector";
    private Dialog mDialog;
    private j0 mSelector;
    private boolean mUseDynamicGroup = false;

    public b() {
        setCancelable(true);
    }

    private void N3() {
        if (this.mSelector == null) {
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.mSelector = j0.d(arguments.getBundle(ARGUMENT_SELECTOR));
            }
            if (this.mSelector == null) {
                this.mSelector = j0.c;
            }
        }
    }

    public j0 getRouteSelector() {
        N3();
        return this.mSelector;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog = this.mDialog;
        if (dialog == null) {
            return;
        }
        if (this.mUseDynamicGroup) {
            ((g) dialog).updateLayout();
        } else {
            ((a) dialog).updateLayout();
        }
    }

    public a onCreateChooserDialog(Context context, Bundle bundle) {
        return new a(context);
    }

    @Override // androidx.fragment.app.d
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.mUseDynamicGroup) {
            g gVarOnCreateDynamicChooserDialog = onCreateDynamicChooserDialog(getContext());
            this.mDialog = gVarOnCreateDynamicChooserDialog;
            gVarOnCreateDynamicChooserDialog.setRouteSelector(getRouteSelector());
        } else {
            a aVarOnCreateChooserDialog = onCreateChooserDialog(getContext(), bundle);
            this.mDialog = aVarOnCreateChooserDialog;
            aVarOnCreateChooserDialog.setRouteSelector(getRouteSelector());
        }
        return this.mDialog;
    }

    public g onCreateDynamicChooserDialog(Context context) {
        return new g(context);
    }

    public void setRouteSelector(j0 j0Var) {
        if (j0Var == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        N3();
        if (this.mSelector.equals(j0Var)) {
            return;
        }
        this.mSelector = j0Var;
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putBundle(ARGUMENT_SELECTOR, j0Var.a());
        setArguments(arguments);
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            if (this.mUseDynamicGroup) {
                ((g) dialog).setRouteSelector(j0Var);
            } else {
                ((a) dialog).setRouteSelector(j0Var);
            }
        }
    }

    void setUseDynamicGroup(boolean z) {
        if (this.mDialog != null) {
            throw new IllegalStateException("This must be called before creating dialog");
        }
        this.mUseDynamicGroup = z;
    }
}
