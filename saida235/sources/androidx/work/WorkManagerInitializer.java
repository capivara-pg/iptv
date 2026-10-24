package androidx.work;

import a1.k;
import a1.t;
import android.content.Context;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class WorkManagerInitializer implements w0.a {
    private static final String a = k.f("WrkMgrInitializer");

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public t create(Context context) {
        k.c().a(a, "Initializing WorkManager with default configuration.", new Throwable[0]);
        t.d(context, new a.b().a());
        return t.c(context);
    }

    public List dependencies() {
        return Collections.emptyList();
    }
}
