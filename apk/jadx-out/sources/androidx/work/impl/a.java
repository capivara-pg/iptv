package androidx.work.impl;

import a1.q;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public class a implements q {
    private final Handler a = z.f.a(Looper.getMainLooper());

    public void a(Runnable runnable) {
        this.a.removeCallbacks(runnable);
    }

    public void b(long j, Runnable runnable) {
        this.a.postDelayed(runnable, j);
    }
}
