package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.PersistableBundle;
import android.text.TextUtils;
import androidx.work.WorkerParameters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import s0.k;
import t0.b;
import t0.j;
import v0.b0;
import v0.c0;
import v0.g0;
import v0.h0;
import v0.i0;
import v0.j0;
import v0.k0;
import v0.l0;
import v0.m0;
import v0.n0;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class SystemJobService extends JobService implements b {
    private static final String c = k.f("SystemJobService");
    private j a;
    private final Map b = new HashMap();

    private static String a(JobParameters jobParameters) {
        try {
            PersistableBundle persistableBundleA = l0.a(jobParameters);
            if (persistableBundleA == null || !b0.a(persistableBundleA, "EXTRA_WORK_SPEC_ID")) {
                return null;
            }
            return c0.a(persistableBundleA, "EXTRA_WORK_SPEC_ID");
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public void d(String str, boolean z) {
        JobParameters jobParametersA;
        k.c().a(c, String.format("%s executed on JobScheduler", str), new Throwable[0]);
        synchronized (this.b) {
            jobParametersA = g0.a(this.b.remove(str));
        }
        if (jobParametersA != null) {
            h0.a(this, jobParametersA, z);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            j jVarJ = j.j(m0.a(this));
            this.a = jVarJ;
            jVarJ.l().c(this);
        } catch (IllegalStateException unused) {
            if (!Application.class.equals(n0.a(this).getClass())) {
                throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().");
            }
            k.c().h(c, "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.", new Throwable[0]);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        j jVar = this.a;
        if (jVar != null) {
            jVar.l().i(this);
        }
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        WorkerParameters.a aVar;
        if (this.a == null) {
            k.c().a(c, "WorkManager is not initialized; requesting retry.", new Throwable[0]);
            h0.a(this, jobParameters, true);
            return false;
        }
        String strA = a(jobParameters);
        if (TextUtils.isEmpty(strA)) {
            k.c().b(c, "WorkSpec id not found!", new Throwable[0]);
            return false;
        }
        synchronized (this.b) {
            if (this.b.containsKey(strA)) {
                k.c().a(c, String.format("Job is already being executed by SystemJobService: %s", strA), new Throwable[0]);
                return false;
            }
            k.c().a(c, String.format("onStartJob for %s", strA), new Throwable[0]);
            this.b.put(strA, jobParameters);
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                aVar = new WorkerParameters.a();
                if (i0.a(jobParameters) != null) {
                    aVar.b = Arrays.asList(i0.a(jobParameters));
                }
                if (j0.a(jobParameters) != null) {
                    aVar.a = Arrays.asList(j0.a(jobParameters));
                }
                if (i >= 28) {
                    aVar.c = k0.a(jobParameters);
                }
            } else {
                aVar = null;
            }
            this.a.u(strA, aVar);
            return true;
        }
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        if (this.a == null) {
            k.c().a(c, "WorkManager is not initialized; requesting retry.", new Throwable[0]);
            return true;
        }
        String strA = a(jobParameters);
        if (TextUtils.isEmpty(strA)) {
            k.c().b(c, "WorkSpec id not found!", new Throwable[0]);
            return false;
        }
        k.c().a(c, String.format("onStopJob for %s", strA), new Throwable[0]);
        synchronized (this.b) {
            this.b.remove(strA);
        }
        this.a.w(strA);
        return !this.a.l().f(strA);
    }
}
