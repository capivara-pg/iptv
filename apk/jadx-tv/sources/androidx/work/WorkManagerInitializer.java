package androidx.work;

import android.content.Context;
import java.util.Collections;
import java.util.List;
import s0.k;
import s0.t;

/* JADX INFO: loaded from: classes.dex */
public final class WorkManagerInitializer implements p0.a {
    private static final String a = k.f("WrkMgrInitializer");

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public t a(Context context) {
        k.c().a(a, "Initializing WorkManager with default configuration.", new Throwable[0]);
        t.d(context, new a.b().a());
        return t.c(context);
    }

    public List dependencies() {
        return Collections.emptyList();
    }
}
