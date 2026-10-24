package androidx.work.impl;

import a1.e;
import a1.k;
import a1.n;
import a1.q;
import a1.t;
import android.content.Context;
import androidx.room.g;
import androidx.room.h;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import n0.c;
import o0.d;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class WorkDatabase extends h {
    private static final long l = TimeUnit.DAYS.toMillis(1);

    class a implements c.c {
        final /* synthetic */ Context a;

        a(Context context) {
            this.a = context;
        }

        public c a(c.b bVar) {
            c.b.a aVarA = c.b.a(this.a);
            aVarA.c(bVar.b).b(bVar.c).d(true);
            return new d().a(aVarA.a());
        }
    }

    class b extends h.b {
        b() {
        }

        @Override // androidx.room.h.b
        public void c(n0.b bVar) {
            super.c(bVar);
            bVar.beginTransaction();
            try {
                bVar.execSQL(WorkDatabase.w());
                bVar.setTransactionSuccessful();
            } finally {
                bVar.endTransaction();
            }
        }
    }

    public static WorkDatabase s(Context context, Executor executor, boolean z) {
        h.a aVarA;
        if (z) {
            aVarA = g.c(context, WorkDatabase.class).c();
        } else {
            aVarA = g.a(context, WorkDatabase.class, t0.h.d());
            aVarA.f(new a(context));
        }
        return (WorkDatabase) aVarA.g(executor).a(u()).b(androidx.work.impl.a.a).b(new androidx.work.impl.a.h(context, 2, 3)).b(androidx.work.impl.a.b).b(androidx.work.impl.a.c).b(new androidx.work.impl.a.h(context, 5, 6)).b(androidx.work.impl.a.d).b(androidx.work.impl.a.e).b(androidx.work.impl.a.f).b(new androidx.work.impl.a.i(context)).b(new androidx.work.impl.a.h(context, 10, 11)).b(androidx.work.impl.a.g).e().d();
    }

    static h.b u() {
        return new b();
    }

    static long v() {
        return System.currentTimeMillis() - l;
    }

    static String w() {
        return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (period_start_time + minimum_retention_duration) < " + v() + " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    }

    public abstract n A();

    public abstract q B();

    public abstract t C();

    public abstract a1.b t();

    public abstract e x();

    public abstract a1.h y();

    public abstract k z();
}
