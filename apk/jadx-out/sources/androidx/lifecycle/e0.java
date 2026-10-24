package androidx.lifecycle;

import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public class e0 {
    private final o a;
    private final Handler b;
    private a c;

    public static final class a implements Runnable {
        private final o a;
        private final f.a b;
        private boolean c;

        public a(o oVar, f.a aVar) {
            kotlin.jvm.internal.m.g(oVar, "registry");
            kotlin.jvm.internal.m.g(aVar, "event");
            this.a = oVar;
            this.b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.c) {
                return;
            }
            this.a.i(this.b);
            this.c = true;
        }
    }

    public e0(m mVar) {
        kotlin.jvm.internal.m.g(mVar, "provider");
        this.a = new o(mVar);
        this.b = new Handler();
    }

    private final void f(f.a aVar) {
        a aVar2 = this.c;
        if (aVar2 != null) {
            aVar2.run();
        }
        a aVar3 = new a(this.a, aVar);
        this.c = aVar3;
        Handler handler = this.b;
        kotlin.jvm.internal.m.d(aVar3);
        handler.postAtFrontOfQueue(aVar3);
    }

    public f a() {
        return this.a;
    }

    public void b() {
        f(f.a.ON_START);
    }

    public void c() {
        f(f.a.ON_CREATE);
    }

    public void d() {
        f(f.a.ON_STOP);
        f(f.a.ON_DESTROY);
    }

    public void e() {
        f(f.a.ON_START);
    }
}
