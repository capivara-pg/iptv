package androidx.work.impl;

import a1.n;
import a1.t;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Build;
import androidx.work.R$bool;
import androidx.work.WorkerParameters;
import androidx.work.impl.utils.ForceStopRunnable;
import c1.m;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class k extends t {
    private static final String j = a1.k.f("WorkManagerImpl");
    private static k k = null;
    private static k l = null;
    private static final Object m = new Object();
    private Context a;
    private androidx.work.a b;
    private WorkDatabase c;
    private j1.a d;
    private List e;
    private d f;
    private i1.h g;
    private boolean h;
    private BroadcastReceiver.PendingResult i;

    public k(Context context, androidx.work.a aVar, j1.a aVar2) {
        this(context, aVar, aVar2, context.getResources().getBoolean(R$bool.workmanager_test_configuration));
    }

    public static void d(Context context, androidx.work.a aVar) {
        synchronized (m) {
            k kVar = k;
            if (kVar != null && l != null) {
                throw new IllegalStateException("WorkManager is already initialized.  Did you try to initialize it manually without disabling WorkManagerInitializer? See WorkManager#initialize(Context, Configuration) or the class level Javadoc for more information.");
            }
            if (kVar == null) {
                Context applicationContext = context.getApplicationContext();
                if (l == null) {
                    l = new k(applicationContext, aVar, new j1.b(aVar.l()));
                }
                k = l;
            }
        }
    }

    public static k i() {
        synchronized (m) {
            k kVar = k;
            if (kVar != null) {
                return kVar;
            }
            return l;
        }
    }

    public static k j(Context context) {
        k kVarI;
        synchronized (m) {
            kVarI = i();
            if (kVarI == null) {
                context.getApplicationContext();
                throw new IllegalStateException("WorkManager is not initialized properly.  You have explicitly disabled WorkManagerInitializer in your manifest, have not manually called WorkManager#initialize at this point, and your Application does not implement Configuration.Provider.");
            }
        }
        return kVarI;
    }

    private void p(Context context, androidx.work.a aVar, j1.a aVar2, WorkDatabase workDatabase, List list, d dVar) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.b = aVar;
        this.d = aVar2;
        this.c = workDatabase;
        this.e = list;
        this.f = dVar;
        this.g = new i1.h(workDatabase);
        this.h = false;
        if (Build.VERSION.SDK_INT >= 24 && applicationContext.isDeviceProtectedStorage()) {
            throw new IllegalStateException("Cannot initialize WorkManager in direct boot mode");
        }
        this.d.b(new ForceStopRunnable(applicationContext, this));
    }

    public n b(List list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("enqueue needs at least one WorkRequest.");
        }
        return new g(this, list).a();
    }

    public n e(UUID uuid) {
        i1.a aVarB = i1.a.b(uuid, this);
        this.d.b(aVarB);
        return aVarB.d();
    }

    public List f(Context context, androidx.work.a aVar, j1.a aVar2) {
        return Arrays.asList(f.a(context, this), new b1.b(context, aVar, aVar2, this));
    }

    public Context g() {
        return this.a;
    }

    public androidx.work.a h() {
        return this.b;
    }

    public i1.h k() {
        return this.g;
    }

    public d l() {
        return this.f;
    }

    public List m() {
        return this.e;
    }

    public WorkDatabase n() {
        return this.c;
    }

    public j1.a o() {
        return this.d;
    }

    public void q() {
        synchronized (m) {
            this.h = true;
            BroadcastReceiver.PendingResult pendingResult = this.i;
            if (pendingResult != null) {
                pendingResult.finish();
                this.i = null;
            }
        }
    }

    public void r() {
        if (Build.VERSION.SDK_INT >= 23) {
            m.b(g());
        }
        n().workSpecDao().resetScheduledState();
        f.b(h(), n(), m());
    }

    public void s(BroadcastReceiver.PendingResult pendingResult) {
        synchronized (m) {
            this.i = pendingResult;
            if (this.h) {
                pendingResult.finish();
                this.i = null;
            }
        }
    }

    public void t(String str) {
        u(str, null);
    }

    public void u(String str, WorkerParameters.a aVar) {
        this.d.b(new i1.l(this, str, aVar));
    }

    public void v(String str) {
        this.d.b(new i1.m(this, str, true));
    }

    public void w(String str) {
        this.d.b(new i1.m(this, str, false));
    }

    public k(Context context, androidx.work.a aVar, j1.a aVar2, boolean z) {
        this(context, aVar, aVar2, WorkDatabase.create(context.getApplicationContext(), aVar2.c(), z));
    }

    public k(Context context, androidx.work.a aVar, j1.a aVar2, WorkDatabase workDatabase) {
        Context applicationContext = context.getApplicationContext();
        a1.k.e(new a1.k.a(aVar.j()));
        List listF = f(applicationContext, aVar, aVar2);
        p(context, aVar, aVar2, workDatabase, listF, new d(context, aVar, aVar2, workDatabase, listF));
    }
}
