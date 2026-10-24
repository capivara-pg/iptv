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
import androidx.lifecycle.c0;
import androidx.lifecycle.f;
import androidx.lifecycle.g0;
import androidx.lifecycle.i;
import androidx.lifecycle.j0;
import androidx.lifecycle.k0;
import androidx.lifecycle.l0;
import androidx.lifecycle.m;
import androidx.lifecycle.m0;
import androidx.lifecycle.o;
import androidx.lifecycle.v;
import java.util.concurrent.atomic.AtomicInteger;
import s.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class ComponentActivity extends k implements k0, androidx.lifecycle.e, s0.c, androidx.activity.c, androidx.activity.result.d {
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private int mContentLayoutId;
    private g0.b mDefaultFactory;
    private j0 mViewModelStore;
    final d.a mContextAwareHelper = new d.a();
    private final o mLifecycleRegistry = new o(this);
    final s0.b mSavedStateRegistryController = s0.b.a(this);
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
            final /* synthetic */ e.a.a b;

            a(int i, e.a.a aVar) {
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
        public void f(int i, e.a aVar, Object obj, s.e eVar) {
            Bundle bundle;
            k kVar = ComponentActivity.this;
            e.a.a synchronousResult = aVar.getSynchronousResult(kVar, obj);
            if (synchronousResult != null) {
                new Handler(Looper.getMainLooper()).post(new a(i, synchronousResult));
                return;
            }
            Intent intentCreateIntent = aVar.createIntent(kVar, obj);
            if (intentCreateIntent.getExtras() != null && intentCreateIntent.getExtras().getClassLoader() == null) {
                intentCreateIntent.setExtrasClassLoader(kVar.getClassLoader());
            }
            if (intentCreateIntent.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
                Bundle bundleExtra = intentCreateIntent.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                intentCreateIntent.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                bundle = bundleExtra;
            } else {
                bundle = null;
            }
            if ("androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(intentCreateIntent.getAction())) {
                String[] stringArrayExtra = intentCreateIntent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                if (stringArrayExtra == null) {
                    stringArrayExtra = new String[0];
                }
                s.c.e(kVar, stringArrayExtra, i);
                return;
            }
            if (!"androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(intentCreateIntent.getAction())) {
                s.c.i(kVar, intentCreateIntent, i, bundle);
                return;
            }
            androidx.activity.result.e eVar2 = (androidx.activity.result.e) intentCreateIntent.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
            try {
                s.c.j(kVar, eVar2.d(), i, eVar2.a(), eVar2.b(), eVar2.c(), 0, bundle);
            } catch (IntentSender.SendIntentException e) {
                new Handler(Looper.getMainLooper()).post(new RunnableC0005b(i, e));
            }
        }
    }

    class c implements androidx.savedstate.a.c {
        c() {
        }

        @Override // androidx.savedstate.a.c
        public Bundle a() {
            Bundle bundle = new Bundle();
            ComponentActivity.this.mActivityResultRegistry.h(bundle);
            return bundle;
        }
    }

    class d implements d.b {
        d() {
        }

        public void a(Context context) {
            Bundle bundleB = ComponentActivity.this.getSavedStateRegistry().b(ComponentActivity.ACTIVITY_RESULT_TAG);
            if (bundleB != null) {
                ComponentActivity.this.mActivityResultRegistry.g(bundleB);
            }
        }
    }

    static final class e {
        Object a;
        j0 b;

        e() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ComponentActivity() {
        if (getLifecycle() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        int i = Build.VERSION.SDK_INT;
        getLifecycle().a(new i() { // from class: androidx.activity.ComponentActivity.3
            @Override // androidx.lifecycle.i
            public void a(m mVar, f.a aVar) {
                if (aVar == f.a.ON_STOP) {
                    Window window = ComponentActivity.this.getWindow();
                    View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                    if (viewPeekDecorView != null) {
                        viewPeekDecorView.cancelPendingInputEvents();
                    }
                }
            }
        });
        getLifecycle().a(new i() { // from class: androidx.activity.ComponentActivity.4
            @Override // androidx.lifecycle.i
            public void a(m mVar, f.a aVar) {
                if (aVar == f.a.ON_DESTROY) {
                    ComponentActivity.this.mContextAwareHelper.b();
                    if (ComponentActivity.this.isChangingConfigurations()) {
                        return;
                    }
                    ComponentActivity.this.getViewModelStore().a();
                }
            }
        });
        getLifecycle().a(new i() { // from class: androidx.activity.ComponentActivity.5
            @Override // androidx.lifecycle.i
            public void a(m mVar, f.a aVar) {
                ComponentActivity.this.ensureViewModelStore();
                ComponentActivity.this.getLifecycle().d(this);
            }
        });
        if (i <= 23) {
            getLifecycle().a(new ImmLeaksCleaner(this));
        }
        getSavedStateRegistry().h(ACTIVITY_RESULT_TAG, new c());
        addOnContextAvailableListener(new d());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void o3() {
        l0.a(getWindow().getDecorView(), this);
        m0.a(getWindow().getDecorView(), this);
        s0.d.a(getWindow().getDecorView(), this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        o3();
        super/*android.app.Activity*/.addContentView(view, layoutParams);
    }

    public final void addOnContextAvailableListener(d.b bVar) {
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
                this.mViewModelStore = new j0();
            }
        }
    }

    @Override // androidx.activity.result.d
    public final ActivityResultRegistry getActivityResultRegistry() {
        return this.mActivityResultRegistry;
    }

    @Override // androidx.lifecycle.e
    public /* synthetic */ j0.a getDefaultViewModelCreationExtras() {
        return androidx.lifecycle.d.a(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public g0.b getDefaultViewModelProviderFactory() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.mDefaultFactory == null) {
            this.mDefaultFactory = new c0(getApplication(), this, getIntent() != null ? getIntent().getExtras() : null);
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

    @Override // androidx.lifecycle.m
    public f getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // androidx.activity.c
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    public final androidx.savedstate.a getSavedStateRegistry() {
        return this.mSavedStateRegistryController.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.lifecycle.k0
    public j0 getViewModelStore() {
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
        this.mSavedStateRegistryController.d(bundle);
        this.mContextAwareHelper.c(this);
        super.onCreate(bundle);
        v.e(this);
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
        j0 j0Var = this.mViewModelStore;
        if (j0Var == null && (eVar = (e) getLastNonConfigurationInstance()) != null) {
            j0Var = eVar.b;
        }
        if (j0Var == null && objOnRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        e eVar2 = new e();
        eVar2.a = objOnRetainCustomNonConfigurationInstance;
        eVar2.b = j0Var;
        return eVar2;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        f lifecycle = getLifecycle();
        if (lifecycle instanceof o) {
            ((o) lifecycle).o(f.b.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.e(bundle);
    }

    public Context peekAvailableContext() {
        return this.mContextAwareHelper.d();
    }

    public final <I, O> androidx.activity.result.c registerForActivityResult(e.a aVar, ActivityResultRegistry activityResultRegistry, androidx.activity.result.b bVar) {
        return activityResultRegistry.i("activity_rq#" + this.mNextLocalRequestCode.getAndIncrement(), this, aVar, bVar);
    }

    public final void removeOnContextAvailableListener(d.b bVar) {
        this.mContextAwareHelper.e(bVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void reportFullyDrawn() {
        try {
            if (x0.b.d()) {
                x0.b.a("reportFullyDrawn() for " + getComponentName());
            }
            super/*android.app.Activity*/.reportFullyDrawn();
        } finally {
            x0.b.b();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setContentView(int i) {
        o3();
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
        o3();
        super/*android.app.Activity*/.setContentView(view);
    }

    public final <I, O> androidx.activity.result.c registerForActivityResult(e.a aVar, androidx.activity.result.b bVar) {
        return registerForActivityResult(aVar, this.mActivityResultRegistry, bVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        o3();
        super/*android.app.Activity*/.setContentView(view, layoutParams);
    }
}
