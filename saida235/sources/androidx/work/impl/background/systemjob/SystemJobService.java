package androidx.work.impl.background.systemjob;

import a1.k;
import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.PersistableBundle;
import android.text.TextUtils;
import androidx.work.WorkerParameters;
import androidx.work.impl.b;
import c1.n;
import c1.o;
import c1.p;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SystemJobService extends JobService implements b {
    private static final String c = k.f("SystemJobService");
    private androidx.work.impl.k a;
    private final Map b = new HashMap();

    private static String a(JobParameters jobParameters) {
        try {
            PersistableBundle extras = jobParameters.getExtras();
            if (extras == null || !extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return null;
            }
            return extras.getString("EXTRA_WORK_SPEC_ID");
        } catch (NullPointerException unused) {
            return null;
        }
    }

    @Override // androidx.work.impl.b
    public void d(String str, boolean z) {
        JobParameters jobParameters;
        k.c().a(c, String.format("%s executed on JobScheduler", str), new Throwable[0]);
        synchronized (this.b) {
            jobParameters = (JobParameters) this.b.remove(str);
        }
        if (jobParameters != null) {
            jobFinished(jobParameters, z);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            androidx.work.impl.k kVarJ = androidx.work.impl.k.j(getApplicationContext());
            this.a = kVarJ;
            kVarJ.l().c(this);
        } catch (IllegalStateException unused) {
            if (!Application.class.equals(getApplication().getClass())) {
                throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().");
            }
            k.c().h(c, "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.", new Throwable[0]);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        androidx.work.impl.k kVar = this.a;
        if (kVar != null) {
            kVar.l().i(this);
        }
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        WorkerParameters.a aVar;
        if (this.a == null) {
            k.c().a(c, "WorkManager is not initialized; requesting retry.", new Throwable[0]);
            jobFinished(jobParameters, true);
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
                if (n.a(jobParameters) != null) {
                    aVar.b = Arrays.asList(n.a(jobParameters));
                }
                if (o.a(jobParameters) != null) {
                    aVar.a = Arrays.asList(o.a(jobParameters));
                }
                if (i >= 28) {
                    aVar.c = p.a(jobParameters);
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
