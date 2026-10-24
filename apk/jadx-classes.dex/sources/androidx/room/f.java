package androidx.room;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class f {
    final Context a;
    final String b;
    int c;
    final androidx.room.e d;
    final androidx.room.e.c e;
    androidx.room.c f;
    final Executor g;
    final androidx.room.b h = new a();
    final AtomicBoolean i = new AtomicBoolean(false);
    final ServiceConnection j;
    final Runnable k;
    final Runnable l;
    private final Runnable m;

    class a extends androidx.room.b.a {

        /* JADX INFO: renamed from: androidx.room.f$a$a, reason: collision with other inner class name */
        class RunnableC0046a implements Runnable {
            final /* synthetic */ String[] a;

            RunnableC0046a(String[] strArr) {
                this.a = strArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.d.e(this.a);
            }
        }

        a() {
        }

        @Override // androidx.room.b
        public void a(String[] strArr) {
            f.this.g.execute(new RunnableC0046a(strArr));
        }
    }

    class b implements ServiceConnection {
        b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            f.this.f = androidx.room.c.a.i0(iBinder);
            f fVar = f.this;
            fVar.g.execute(fVar.k);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            f fVar = f.this;
            fVar.g.execute(fVar.l);
            f.this.f = null;
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                f fVar = f.this;
                androidx.room.c cVar = fVar.f;
                if (cVar != null) {
                    fVar.c = cVar.d(fVar.h, fVar.b);
                    f fVar2 = f.this;
                    fVar2.d.a(fVar2.e);
                }
            } catch (RemoteException unused) {
            }
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f fVar = f.this;
            fVar.d.g(fVar.e);
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f fVar = f.this;
            fVar.d.g(fVar.e);
            try {
                f fVar2 = f.this;
                androidx.room.c cVar = fVar2.f;
                if (cVar != null) {
                    cVar.g0(fVar2.h, fVar2.c);
                }
            } catch (RemoteException unused) {
            }
            f fVar3 = f.this;
            fVar3.a.unbindService(fVar3.j);
        }
    }

    /* JADX INFO: renamed from: androidx.room.f$f, reason: collision with other inner class name */
    class C0047f extends androidx.room.e.c {
        C0047f(String[] strArr) {
            super(strArr);
        }

        @Override // androidx.room.e.c
        boolean a() {
            return true;
        }

        @Override // androidx.room.e.c
        public void b(Set set) {
            if (f.this.i.get()) {
                return;
            }
            try {
                f fVar = f.this;
                androidx.room.c cVar = fVar.f;
                if (cVar != null) {
                    cVar.b(fVar.c, (String[]) set.toArray(new String[0]));
                }
            } catch (RemoteException unused) {
            }
        }
    }

    f(Context context, String str, androidx.room.e eVar, Executor executor) {
        b bVar = new b();
        this.j = bVar;
        this.k = new c();
        this.l = new d();
        this.m = new e();
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.b = str;
        this.d = eVar;
        this.g = executor;
        this.e = new C0047f((String[]) eVar.a.keySet().toArray(new String[0]));
        applicationContext.bindService(new Intent(applicationContext, (Class<?>) MultiInstanceInvalidationService.class), bVar, 1);
    }
}
