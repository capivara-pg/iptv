package androidx.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.result.ActivityResultRegistry;
import androidx.lifecycle.a0;
import androidx.lifecycle.g;
import androidx.lifecycle.h;
import androidx.lifecycle.o;
import androidx.lifecycle.t;
import androidx.lifecycle.w;
import androidx.lifecycle.x;
import androidx.lifecycle.y;
import androidx.lifecycle.z;
import androidx.savedstate.SavedStateRegistry;
import java.util.concurrent.atomic.AtomicInteger;
import n.j;
import n.p;

/* JADX INFO: loaded from: classes.dex */
public abstract class ComponentActivity extends p implements y, androidx.savedstate.b, androidx.activity.c, androidx.activity.result.d {
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private int mContentLayoutId;
    private w.b mDefaultFactory;
    private x mViewModelStore;
    final b.a mContextAwareHelper = new b.a();
    private final h mLifecycleRegistry = new h(this);
    final androidx.savedstate.a mSavedStateRegistryController = androidx.savedstate.a.a(this);
    private final OnBackPressedDispatcher mOnBackPressedDispatcher = new OnBackPressedDispatcher(new a());
    private final AtomicInteger mNextLocalRequestCode = new AtomicInteger();
    private final ActivityResultRegistry mActivityResultRegistry = new b();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                super/*android.app.Activity*/.onBackPressed();
            } catch (IllegalStateException e) {
                if (!TextUtils.equals(e.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                    throw e;
                }
            }
        }
    }

    class b extends ActivityResultRegistry {

        class a implements Runnable {
            final /* synthetic */ int a;
            final /* synthetic */ c.a.a b;

            a(int i, c.a.a aVar) {
                this.a = i;
                this.b = aVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.c(this.a, this.b.a());
            }
        }

        /* JADX INFO: renamed from: androidx.activity.ComponentActivity$b$b, reason: collision with other inner class name */
        class RunnableC0005b implements Runnable {
            final /* synthetic */ int a;
            final /* synthetic */ IntentSender.SendIntentException b;

            RunnableC0005b(int i, IntentSender.SendIntentException sendIntentException) {
                this.a = i;
                this.b = sendIntentException;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.b(this.a, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", this.b));
            }
        }

        b() {
        }

        @Override // androidx.activity.result.ActivityResultRegistry
        public void f(int i, c.a aVar, Object obj, j jVar) {
            Bundle bundle;
            p pVar = ComponentActivity.this;
            c.a.a aVarB = aVar.b(pVar, obj);
            if (aVarB != null) {
                new Handler(Looper.getMainLooper()).post(new a(i, aVarB));
                return;
            }
            Intent intentA = aVar.a(pVar, obj);
            if (intentA.getExtras() != null && intentA.getExtras().getClassLoader() == null) {
                intentA.setExtrasClassLoader(pVar.getClassLoader());
            }
            if (intentA.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
                Bundle bundleExtra = intentA.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                intentA.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                bundle = bundleExtra;
            } else {
                bundle = null;
            }
            if ("androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(intentA.getAction())) {
                String[] stringArrayExtra = intentA.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                if (stringArrayExtra == null) {
                    stringArrayExtra = new String[0];
                }
                n.h.f(pVar, stringArrayExtra, i);
                return;
            }
            if (!"androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(intentA.getAction())) {
                n.h.j(pVar, intentA, i, bundle);
                return;
            }
            androidx.activity.result.e eVar = (androidx.activity.result.e) intentA.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
            try {
                n.h.k(pVar, eVar.d(), i, eVar.a(), eVar.b(), eVar.c(), 0, bundle);
            } catch (IntentSender.SendIntentException e) {
                new Handler(Looper.getMainLooper()).post(new RunnableC0005b(i, e));
            }
        }
    }

    class c implements SavedStateRegistry.b {
        c() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.b
        public Bundle a() {
            Bundle bundle = new Bundle();
            ComponentActivity.this.mActivityResultRegistry.h(bundle);
            return bundle;
        }
    }

    class d implements b.b {
        d() {
        }

        public void a(Context context) {
            Bundle bundleA = ComponentActivity.this.getSavedStateRegistry().a(ComponentActivity.ACTIVITY_RESULT_TAG);
            if (bundleA != null) {
                ComponentActivity.this.mActivityResultRegistry.g(bundleA);
            }
        }
    }

    static final class e {
        Object a;
        x b;

        e() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ComponentActivity() {
        if (getLifecycle() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        int i = Build.VERSION.SDK_INT;
        getLifecycle().a(new androidx.lifecycle.e() { // from class: androidx.activity.ComponentActivity.3
            @Override // androidx.lifecycle.e
            public void a(g gVar, androidx.lifecycle.d.b bVar) {
                if (bVar == androidx.lifecycle.d.b.ON_STOP) {
                    Window window = ComponentActivity.this.getWindow();
                    View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                    if (viewPeekDecorView != null) {
                        viewPeekDecorView.cancelPendingInputEvents();
                    }
                }
            }
        });
        getLifecycle().a(new androidx.lifecycle.e() { // from class: androidx.activity.ComponentActivity.4
            @Override // androidx.lifecycle.e
            public void a(g gVar, androidx.lifecycle.d.b bVar) {
                if (bVar == androidx.lifecycle.d.b.ON_DESTROY) {
                    ComponentActivity.this.mContextAwareHelper.b();
                    if (ComponentActivity.this.isChangingConfigurations()) {
                        return;
                    }
                    ComponentActivity.this.getViewModelStore().a();
                }
            }
        });
        getLifecycle().a(new androidx.lifecycle.e() { // from class: androidx.activity.ComponentActivity.5
            @Override // androidx.lifecycle.e
            public void a(g gVar, androidx.lifecycle.d.b bVar) {
                ComponentActivity.this.ensureViewModelStore();
                ComponentActivity.this.getLifecycle().c(this);
            }
        });
        if (i <= 23) {
            getLifecycle().a(new ImmLeaksCleaner(this));
        }
        getSavedStateRegistry().d(ACTIVITY_RESULT_TAG, new c());
        addOnContextAvailableListener(new d());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void r2() {
        z.a(getWindow().getDecorView(), this);
        a0.a(getWindow().getDecorView(), this);
        androidx.savedstate.c.a(getWindow().getDecorView(), this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        r2();
        super/*android.app.Activity*/.addContentView(view, layoutParams);
    }

    public final void addOnContextAvailableListener(b.b bVar) {
        this.mContextAwareHelper.a(bVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    void ensureViewModelStore() {
        if (this.mViewModelStore == null) {
            e eVar = (e) getLastNonConfigurationInstance();
            if (eVar != null) {
                this.mViewModelStore = eVar.b;
            }
            if (this.mViewModelStore == null) {
                this.mViewModelStore = new x();
            }
        }
    }

    @Override // androidx.activity.result.d
    public final ActivityResultRegistry getActivityResultRegistry() {
        return this.mActivityResultRegistry;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public w.b getDefaultViewModelProviderFactory() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.mDefaultFactory == null) {
            this.mDefaultFactory = new t(getApplication(), this, getIntent() != null ? getIntent().getExtras() : null);
        }
        return this.mDefaultFactory;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        e eVar = (e) getLastNonConfigurationInstance();
        if (eVar != null) {
            return eVar.a;
        }
        return null;
    }

    @Override // androidx.lifecycle.g
    public androidx.lifecycle.d getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // androidx.activity.c
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.lifecycle.y
    public x getViewModelStore() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        ensureViewModelStore();
        return this.mViewModelStore;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.mActivityResultRegistry.b(i, i2, intent)) {
            return;
        }
        super/*android.app.Activity*/.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        this.mOnBackPressedDispatcher.c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        this.mSavedStateRegistryController.c(bundle);
        this.mContextAwareHelper.c(this);
        super.onCreate(bundle);
        o.f(this);
        int i = this.mContentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.mActivityResultRegistry.b(i, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr)) || Build.VERSION.SDK_INT < 23) {
            return;
        }
        super/*android.app.Activity*/.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object onRetainNonConfigurationInstance() {
        e eVar;
        Object objOnRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        x xVar = this.mViewModelStore;
        if (xVar == null && (eVar = (e) getLastNonConfigurationInstance()) != null) {
            xVar = eVar.b;
        }
        if (xVar == null && objOnRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        e eVar2 = new e();
        eVar2.a = objOnRetainCustomNonConfigurationInstance;
        eVar2.b = xVar;
        return eVar2;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        androidx.lifecycle.d lifecycle = getLifecycle();
        if (lifecycle instanceof h) {
            ((h) lifecycle).o(androidx.lifecycle.d.c.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.d(bundle);
    }

    public Context peekAvailableContext() {
        return this.mContextAwareHelper.d();
    }

    public final <I, O> androidx.activity.result.c registerForActivityResult(c.a aVar, ActivityResultRegistry activityResultRegistry, androidx.activity.result.b bVar) {
        return activityResultRegistry.i("activity_rq#" + this.mNextLocalRequestCode.getAndIncrement(), this, aVar, bVar);
    }

    public final void removeOnContextAvailableListener(b.b bVar) {
        this.mContextAwareHelper.e(bVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void reportFullyDrawn() {
        try {
            if (q0.b.d()) {
                q0.b.a("reportFullyDrawn() for " + getComponentName());
            }
            int i = Build.VERSION.SDK_INT;
            if (i > 19) {
                super/*android.app.Activity*/.reportFullyDrawn();
            } else if (i == 19 && androidx.core.content.a.checkSelfPermission(this, "android.permission.UPDATE_DEVICE_STATS") == 0) {
                super/*android.app.Activity*/.reportFullyDrawn();
            }
        } finally {
            q0.b.b();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setContentView(int i) {
        r2();
        super/*android.app.Activity*/.setContentView(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void startActivityForResult(Intent intent, int i) {
        super/*android.app.Activity*/.startActivityForResult(intent, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        super/*android.app.Activity*/.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        super/*android.app.Activity*/.startActivityForResult(intent, i, bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        super/*android.app.Activity*/.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setContentView(View view) {
        r2();
        super/*android.app.Activity*/.setContentView(view);
    }

    public final <I, O> androidx.activity.result.c registerForActivityResult(c.a aVar, androidx.activity.result.b bVar) {
        return registerForActivityResult(aVar, this.mActivityResultRegistry, bVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        r2();
        super/*android.app.Activity*/.setContentView(view, layoutParams);
    }
}
