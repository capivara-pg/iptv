package androidx.mediarouter.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import o0.k0;

/* JADX INFO: loaded from: classes.dex */
public class b extends androidx.fragment.app.d {
    private static final String ARGUMENT_SELECTOR = "selector";
    private Dialog mDialog;
    private k0 mSelector;
    private boolean mUseDynamicGroup = false;

    public b() {
        setCancelable(true);
    }

    private void i4() {
        if (this.mSelector == null) {
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.mSelector = k0.d(arguments.getBundle(ARGUMENT_SELECTOR));
            }
            if (this.mSelector == null) {
                this.mSelector = k0.c;
            }
        }
    }

    public k0 getRouteSelector() {
        i4();
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

    public void setRouteSelector(k0 k0Var) {
        if (k0Var == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        i4();
        if (this.mSelector.equals(k0Var)) {
            return;
        }
        this.mSelector = k0Var;
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putBundle(ARGUMENT_SELECTOR, k0Var.a());
        setArguments(arguments);
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            if (this.mUseDynamicGroup) {
                ((g) dialog).setRouteSelector(k0Var);
            } else {
                ((a) dialog).setRouteSelector(k0Var);
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
