package androidx.work;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.work.impl.utils.futures.c;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;
import java.util.concurrent.Executor;
import s0.v;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class ListenableWorker {
    private Context a;
    private WorkerParameters b;
    private volatile boolean c;
    private boolean d;
    private boolean e;

    public static abstract class a {

        /* JADX INFO: renamed from: androidx.work.ListenableWorker$a$a, reason: collision with other inner class name */
        public static final class C0052a extends a {
            private final androidx.work.b a;

            public C0052a() {
                this(androidx.work.b.c);
            }

            public androidx.work.b e() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || C0052a.class != obj.getClass()) {
                    return false;
                }
                return this.a.equals(((C0052a) obj).a);
            }

            public int hashCode() {
                return (C0052a.class.getName().hashCode() * 31) + this.a.hashCode();
            }

            public String toString() {
                return "Failure {mOutputData=" + this.a + '}';
            }

            public C0052a(androidx.work.b bVar) {
                this.a = bVar;
            }
        }

        public static final class b extends a {
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && b.class == obj.getClass();
            }

            public int hashCode() {
                return b.class.getName().hashCode();
            }

            public String toString() {
                return "Retry";
            }
        }

        public static final class c extends a {
            private final androidx.work.b a;

            public c() {
                this(androidx.work.b.c);
            }

            public androidx.work.b e() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || c.class != obj.getClass()) {
                    return false;
                }
                return this.a.equals(((c) obj).a);
            }

            public int hashCode() {
                return (c.class.getName().hashCode() * 31) + this.a.hashCode();
            }

            public String toString() {
                return "Success {mOutputData=" + this.a + '}';
            }

            public c(androidx.work.b bVar) {
                this.a = bVar;
            }
        }

        a() {
        }

        public static a a() {
            return new C0052a();
        }

        public static a b() {
            return new b();
        }

        public static a c() {
            return new c();
        }

        public static a d(androidx.work.b bVar) {
            return new c(bVar);
        }
    }

    @Keep
    public ListenableWorker(Context context, WorkerParameters workerParameters) {
        if (context == null) {
            throw new IllegalArgumentException("Application Context is null");
        }
        if (workerParameters == null) {
            throw new IllegalArgumentException("WorkerParameters is null");
        }
        this.a = context;
        this.b = workerParameters;
    }

    public final Context a() {
        return this.a;
    }

    public Executor c() {
        return this.b.a();
    }

    public ListenableFuture d() {
        c cVarS = c.s();
        cVarS.p(new IllegalStateException("Expedited WorkRequests require a ListenableWorker to provide an implementation for `getForegroundInfoAsync()`"));
        return cVarS;
    }

    public final UUID f() {
        return this.b.c();
    }

    public final b g() {
        return this.b.d();
    }

    public c1.a h() {
        return this.b.e();
    }

    public v i() {
        return this.b.f();
    }

    public boolean j() {
        return this.e;
    }

    public final boolean k() {
        return this.c;
    }

    public final boolean l() {
        return this.d;
    }

    public void m() {
    }

    public void n(boolean z) {
        this.e = z;
    }

    public final void o() {
        this.d = true;
    }

    public abstract ListenableFuture p();

    public final void q() {
        this.c = true;
        m();
    }
}
