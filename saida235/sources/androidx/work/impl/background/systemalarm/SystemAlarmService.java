package androidx.work.impl.background.systemalarm;

import a1.k;
import android.content.Intent;
import androidx.lifecycle.p;
import i1.n;

/* JADX INFO: loaded from: classes.dex */
public class SystemAlarmService extends p implements e.c {
    private static final String d = k.f("SystemAlarmService");
    private e b;
    private boolean c;

    private void e() {
        e eVar = new e(this);
        this.b = eVar;
        eVar.m(this);
    }

    @Override // androidx.work.impl.background.systemalarm.e.c
    public void b() {
        this.c = true;
        k.c().a(d, "All commands completed in dispatcher", new Throwable[0]);
        n.a();
        stopSelf();
    }

    @Override // androidx.lifecycle.p, android.app.Service
    public void onCreate() {
        super.onCreate();
        e();
        this.c = false;
    }

    @Override // androidx.lifecycle.p, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.c = true;
        this.b.j();
    }

    @Override // androidx.lifecycle.p, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (this.c) {
            k.c().d(d, "Re-initializing SystemAlarmDispatcher after a request to shut-down.", new Throwable[0]);
            this.b.j();
            e();
            this.c = false;
        }
        if (intent == null) {
            return 3;
        }
        this.b.a(intent, i2);
        return 3;
    }
}
