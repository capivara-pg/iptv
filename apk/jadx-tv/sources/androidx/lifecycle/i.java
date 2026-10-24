package androidx.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends Service implements g {
    private final u a = new u(this);

    @Override // androidx.lifecycle.g
    public d getLifecycle() {
        return this.a.a();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        this.a.b();
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.a.c();
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.a.d();
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        this.a.e();
        super.onStart(intent, i);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
