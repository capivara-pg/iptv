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
import androidx.lifecycle.j0;
import androidx.lifecycle.k0;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import s.o0;

/* JADX INFO: loaded from: classes.dex */
public abstract class e extends ComponentActivity implements s.c.c, s.c.d {
    static final String FRAGMENTS_TAG = "android:support:fragments";
    boolean mCreated;
    boolean mResumed;
    final i mFragments = i.b(new c());
    final androidx.lifecycle.o mFragmentLifecycleRegistry = new androidx.lifecycle.o(this);
    boolean mStopped = true;

    class a implements androidx.savedstate.a.c {
        a() {
        }

        @Override // androidx.savedstate.a.c
        public Bundle a() {
            Bundle bundle = new Bundle();
            e.this.markFragmentsCreated();
            e.this.mFragmentLifecycleRegistry.i(androidx.lifecycle.f.a.ON_STOP);
            Parcelable parcelableX = e.this.mFragments.x();
            if (parcelableX != null) {
                bundle.putParcelable(e.FRAGMENTS_TAG, parcelableX);
            }
            return bundle;
        }
    }

    class b implements d.b {
        b() {
        }

        public void a(Context context) {
            e.this.mFragments.a(null);
            Bundle bundleB = e.this.getSavedStateRegistry().b(e.FRAGMENTS_TAG);
            if (bundleB != null) {
                e.this.mFragments.w(bundleB.getParcelable(e.FRAGMENTS_TAG));
            }
        }
    }

    class c extends k implements k0, androidx.activity.c, androidx.activity.result.d, r {
        public c() {
            super(e.this);
        }

        @Override // androidx.fragment.app.r
        public void a(n nVar, Fragment fragment) {
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

        @Override // androidx.lifecycle.m
        public androidx.lifecycle.f getLifecycle() {
            return e.this.mFragmentLifecycleRegistry;
        }

        @Override // androidx.activity.c
        public OnBackPressedDispatcher getOnBackPressedDispatcher() {
            return e.this.getOnBackPressedDispatcher();
        }

        @Override // androidx.lifecycle.k0
        public j0 getViewModelStore() {
            return e.this.getViewModelStore();
        }

        @Override // androidx.fragment.app.k
        public void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            e.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // androidx.fragment.app.k
        public LayoutInflater j() {
            return e.this.getLayoutInflater().cloneInContext(e.this);
        }

        @Override // androidx.fragment.app.k
        public boolean l(Fragment fragment) {
            return !e.this.isFinishing();
        }

        @Override // androidx.fragment.app.k
        public boolean m(String str) {
            return s.c.h(e.this, str);
        }

        @Override // androidx.fragment.app.k
        public void p() {
            e.this.supportInvalidateOptionsMenu();
        }

        @Override // androidx.fragment.app.k
        /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
        public e i() {
            return e.this;
        }
    }

    public e() {
        A3();
    }

    private void A3() {
        getSavedStateRegistry().h(FRAGMENTS_TAG, new a());
        addOnContextAvailableListener(new b());
    }

    private static boolean Z3(n nVar, androidx.lifecycle.f.b bVar) {
        boolean zZ3 = false;
        for (Fragment fragment : nVar.s0()) {
            if (fragment != null) {
                if (fragment.getHost() != null) {
                    zZ3 |= Z3(fragment.getChildFragmentManager(), bVar);
                }
                b0 b0Var = fragment.mViewLifecycleOwner;
                if (b0Var != null && b0Var.getLifecycle().b().b(androidx.lifecycle.f.b.STARTED)) {
                    fragment.mViewLifecycleOwner.f(bVar);
                    zZ3 = true;
                }
                if (fragment.mLifecycleRegistry.b().b(androidx.lifecycle.f.b.STARTED)) {
                    fragment.mLifecycleRegistry.o(bVar);
                    zZ3 = true;
                }
            }
        }
        return zZ3;
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

    public n getSupportFragmentManager() {
        return this.mFragments.t();
    }

    @Deprecated
    public androidx.loader.app.a getSupportLoaderManager() {
        return androidx.loader.app.a.b(this);
    }

    void markFragmentsCreated() {
        while (Z3(getSupportFragmentManager(), androidx.lifecycle.f.b.CREATED)) {
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
        this.mFragmentLifecycleRegistry.i(androidx.lifecycle.f.a.ON_CREATE);
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
        this.mFragmentLifecycleRegistry.i(androidx.lifecycle.f.a.ON_DESTROY);
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
        this.mFragmentLifecycleRegistry.i(androidx.lifecycle.f.a.ON_PAUSE);
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
        this.mFragmentLifecycleRegistry.i(androidx.lifecycle.f.a.ON_RESUME);
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
        this.mFragmentLifecycleRegistry.i(androidx.lifecycle.f.a.ON_START);
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
        this.mFragmentLifecycleRegistry.i(androidx.lifecycle.f.a.ON_STOP);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setEnterSharedElementCallback(o0 o0Var) {
        s.c.f(this, o0Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setExitSharedElementCallback(o0 o0Var) {
        s.c.g(this, o0Var);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i) {
        startActivityFromFragment(fragment, intent, i, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void startIntentSenderFromFragment(Fragment fragment, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (i == -1) {
            s.c.j(this, intentSender, i, intent, i2, i3, i4, bundle);
        } else {
            fragment.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void supportFinishAfterTransition() {
        s.c.b(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void supportPostponeEnterTransition() {
        s.c.c(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void supportStartPostponedEnterTransition() {
        s.c.k(this);
    }

    @Deprecated
    public final void validateRequestPermissionsRequestCode(int i) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle) {
        if (i == -1) {
            s.c.i(this, intent, -1, bundle);
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
