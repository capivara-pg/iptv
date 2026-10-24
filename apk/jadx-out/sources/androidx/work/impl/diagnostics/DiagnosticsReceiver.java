package androidx.work.impl.diagnostics;

import a1.k;
import a1.m;
import a1.t;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.workers.DiagnosticsWorker;

/* JADX INFO: loaded from: classes.dex */
public class DiagnosticsReceiver extends BroadcastReceiver {
    private static final String a = k.f("DiagnosticsRcvr");

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        k.c().a(a, "Requesting diagnostics", new Throwable[0]);
        try {
            t.c(context).a(m.d(DiagnosticsWorker.class));
        } catch (IllegalStateException e) {
            k.c().b(a, "WorkManager is not initialized", new Throwable[]{e});
        }
    }
}
