package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.savedstate.SavedStateRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import n.k1;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class e extends ComponentActivity implements n.h.c, n.h.d {
    static final String FRAGMENTS_TAG = "android:support:fragments";
    boolean mCreated;
    boolean mResumed;
    final j mFragments = j.b(new c());
    final androidx.lifecycle.h mFragmentLifecycleRegistry = new androidx.lifecycle.h(this);
    boolean mStopped = true;

    class a implements SavedStateRegistry.b {
        a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.b
        public Bundle a() {
            Bundle bundle = new Bundle();
            e.this.markFragmentsCreated();
            e.this.mFragmentLifecycleRegistry.h(androidx.lifecycle.d.b.ON_STOP);
            Parcelable parcelableX = e.this.mFragments.x();
            if (parcelableX != null) {
                bundle.putParcelable(e.FRAGMENTS_TAG, parcelableX);
            }
            return bundle;
        }
    }

    class b implements b.b {
        b() {
        }

        public void a(Context context) {
            e.this.mFragments.a(null);
            Bundle bundleA = e.this.getSavedStateRegistry().a(e.FRAGMENTS_TAG);
            if (bundleA != null) {
                e.this.mFragments.w(bundleA.getParcelable(e.FRAGMENTS_TAG));
            }
        }
    }

    class c extends l implements androidx.lifecycle.y, androidx.activity.c, androidx.activity.result.d, s {
        public c() {
            super(e.this);
        }

        @Override // androidx.fragment.app.s
        public void a(o oVar, Fragment fragment) {
            e.this.onAttachFragment(fragment);
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // androidx.fragment.app.g
        public View c(int i) {
            return e.this.findViewById(i);
        }

        @Override // androidx.fragment.app.g
        public boolean d() {
            Window window = e.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // androidx.activity.result.d
        public ActivityResultRegistry getActivityResultRegistry() {
            return e.this.getActivityResultRegistry();
        }

        @Override // androidx.lifecycle.g
        public androidx.lifecycle.d getLifecycle() {
            return e.this.mFragmentLifecycleRegistry;
        }

        @Override // androidx.activity.c
        public OnBackPressedDispatcher getOnBackPressedDispatcher() {
            return e.this.getOnBackPressedDispatcher();
        }

        @Override // androidx.lifecycle.y
        public androidx.lifecycle.x getViewModelStore() {
            return e.this.getViewModelStore();
        }

        @Override // androidx.fragment.app.l
        public void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            e.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // androidx.fragment.app.l
        public LayoutInflater j() {
            return e.this.getLayoutInflater().cloneInContext(e.this);
        }

        @Override // androidx.fragment.app.l
        public boolean l(Fragment fragment) {
            return !e.this.isFinishing();
        }

        @Override // androidx.fragment.app.l
        public boolean m(String str) {
            return n.h.i(e.this, str);
        }

        @Override // androidx.fragment.app.l
        public void p() {
            e.this.supportInvalidateOptionsMenu();
        }

        @Override // androidx.fragment.app.l
        /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
        public e i() {
            return e.this;
        }
    }

    public e() {
        s2();
    }

    private void s2() {
        getSavedStateRegistry().d(FRAGMENTS_TAG, new a());
        addOnContextAvailableListener(new b());
    }

    private static boolean u2(o oVar, androidx.lifecycle.d.c cVar) {
        boolean zU2 = false;
        for (Fragment fragment : oVar.s0()) {
            if (fragment != null) {
                if (fragment.getHost() != null) {
                    zU2 |= u2(fragment.getChildFragmentManager(), cVar);
                }
                h0 h0Var = fragment.mViewLifecycleOwner;
                if (h0Var != null && h0Var.getLifecycle().b().a(androidx.lifecycle.d.c.STARTED)) {
                    fragment.mViewLifecycleOwner.f(cVar);
                    zU2 = true;
                }
                if (fragment.mLifecycleRegistry.b().a(androidx.lifecycle.d.c.STARTED)) {
                    fragment.mLifecycleRegistry.o(cVar);
                    zU2 = true;
                }
            }
        }
        return zU2;
    }

    final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.v(view, str, context, attributeSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super/*android.app.Activity*/.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print(" mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        if (getApplication() != null) {
            androidx.loader.app.a.b(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.mFragments.t().W(str, fileDescriptor, printWriter, strArr);
    }

    public o getSupportFragmentManager() {
        return this.mFragments.t();
    }

    @Deprecated
    public androidx.loader.app.a getSupportLoaderManager() {
        return androidx.loader.app.a.b(this);
    }

    void markFragmentsCreated() {
        while (u2(getSupportFragmentManager(), androidx.lifecycle.d.c.CREATED)) {
        }
    }

    @Override // androidx.activity.ComponentActivity
    protected void onActivityResult(int i, int i2, Intent intent) {
        this.mFragments.u();
        super.onActivityResult(i, i2, intent);
    }

    @Deprecated
    public void onAttachFragment(Fragment fragment) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        this.mFragments.u();
        super/*android.app.Activity*/.onConfigurationChanged(configuration);
        this.mFragments.d(configuration);
    }

    @Override // androidx.activity.ComponentActivity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.h(androidx.lifecycle.d.b.ON_CREATE);
        this.mFragments.f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onCreatePanelMenu(int i, Menu menu) {
        return i == 0 ? super/*android.app.Activity*/.onCreatePanelMenu(i, menu) | this.mFragments.g(menu, getMenuInflater()) : super/*android.app.Activity*/.onCreatePanelMenu(i, menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super/*android.app.Activity*/.onCreateView(view, str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onDestroy() {
        super/*android.app.Activity*/.onDestroy();
        this.mFragments.h();
        this.mFragmentLifecycleRegistry.h(androidx.lifecycle.d.b.ON_DESTROY);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onLowMemory() {
        super/*android.app.Activity*/.onLowMemory();
        this.mFragments.i();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super/*android.app.Activity*/.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.mFragments.k(menuItem);
        }
        if (i != 6) {
            return false;
        }
        return this.mFragments.e(menuItem);
    }

    public void onMultiWindowModeChanged(boolean z) {
        this.mFragments.j(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onNewIntent(Intent intent) {
        this.mFragments.u();
        super/*android.app.Activity*/.onNewIntent(intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            this.mFragments.l(menu);
        }
        super/*android.app.Activity*/.onPanelClosed(i, menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onPause() {
        super/*android.app.Activity*/.onPause();
        this.mResumed = false;
        this.mFragments.m();
        this.mFragmentLifecycleRegistry.h(androidx.lifecycle.d.b.ON_PAUSE);
    }

    public void onPictureInPictureModeChanged(boolean z) {
        this.mFragments.n(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onPostResume() {
        super/*android.app.Activity*/.onPostResume();
        onResumeFragments();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super/*android.app.Activity*/.onPreparePanel(0, view, menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onPreparePanel(int i, View view, Menu menu) {
        return i == 0 ? onPrepareOptionsPanel(view, menu) | this.mFragments.o(menu) : super/*android.app.Activity*/.onPreparePanel(i, view, menu);
    }

    @Override // androidx.activity.ComponentActivity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mFragments.u();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onResume() {
        this.mFragments.u();
        super/*android.app.Activity*/.onResume();
        this.mResumed = true;
        this.mFragments.s();
    }

    protected void onResumeFragments() {
        this.mFragmentLifecycleRegistry.h(androidx.lifecycle.d.b.ON_RESUME);
        this.mFragments.p();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onStart() {
        this.mFragments.u();
        super/*android.app.Activity*/.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.c();
        }
        this.mFragments.s();
        this.mFragmentLifecycleRegistry.h(androidx.lifecycle.d.b.ON_START);
        this.mFragments.q();
    }

    public void onStateNotSaved() {
        this.mFragments.u();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onStop() {
        super/*android.app.Activity*/.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        this.mFragments.r();
        this.mFragmentLifecycleRegistry.h(androidx.lifecycle.d.b.ON_STOP);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setEnterSharedElementCallback(k1 k1Var) {
        n.h.g(this, k1Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setExitSharedElementCallback(k1 k1Var) {
        n.h.h(this, k1Var);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i) {
        startActivityFromFragment(fragment, intent, i, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void startIntentSenderFromFragment(Fragment fragment, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (i == -1) {
            n.h.k(this, intentSender, i, intent, i2, i3, i4, bundle);
        } else {
            fragment.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void supportFinishAfterTransition() {
        n.h.c(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void supportPostponeEnterTransition() {
        n.h.d(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void supportStartPostponedEnterTransition() {
        n.h.l(this);
    }

    @Deprecated
    public final void validateRequestPermissionsRequestCode(int i) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle) {
        if (i == -1) {
            n.h.j(this, intent, -1, bundle);
        } else {
            fragment.startActivityForResult(intent, i, bundle);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super/*android.app.Activity*/.onCreateView(str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }
}
