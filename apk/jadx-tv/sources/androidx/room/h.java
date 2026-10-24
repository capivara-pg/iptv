package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.Looper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {
    protected volatile n0.b a;
    private Executor b;
    private Executor c;
    private n0.c d;
    private boolean f;
    boolean g;
    protected List h;
    private final ReentrantReadWriteLock i = new ReentrantReadWriteLock();
    private final ThreadLocal j = new ThreadLocal();
    private final Map k = new ConcurrentHashMap();
    private final e e = e();

    public static class a {
        private final Class a;
        private final String b;
        private final Context c;
        private ArrayList d;
        private Executor e;
        private Executor f;
        private n0.c.c g;
        private boolean h;
        private boolean j;
        private boolean l;
        private Set n;
        private Set o;
        private String p;
        private File q;
        private c i = c.AUTOMATIC;
        private boolean k = true;
        private final d m = new d();

        a(Context context, Class cls, String str) {
            this.c = context;
            this.a = cls;
            this.b = str;
        }

        public a a(b bVar) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add(bVar);
            return this;
        }

        public a b(l0.a... aVarArr) {
            if (this.o == null) {
                this.o = new HashSet();
            }
            for (l0.a aVar : aVarArr) {
                this.o.add(Integer.valueOf(aVar.a));
                this.o.add(Integer.valueOf(aVar.b));
            }
            this.m.b(aVarArr);
            return this;
        }

        public a c() {
            this.h = true;
            return this;
        }

        public h d() {
            Executor executor;
            if (this.c == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            }
            if (this.a == null) {
                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            }
            Executor executor2 = this.e;
            if (executor2 == null && this.f == null) {
                Executor executorD = g.a.d();
                this.f = executorD;
                this.e = executorD;
            } else if (executor2 != null && this.f == null) {
                this.f = executor2;
            } else if (executor2 == null && (executor = this.f) != null) {
                this.e = executor;
            }
            Set<Integer> set = this.o;
            if (set != null && this.n != null) {
                for (Integer num : set) {
                    if (this.n.contains(num)) {
                        throw new IllegalArgumentException("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + num);
                    }
                }
            }
            if (this.g == null) {
                this.g = new o0.d();
            }
            String str = this.p;
            if (str != null || this.q != null) {
                if (this.b == null) {
                    throw new IllegalArgumentException("Cannot create from asset or file for an in-memory database.");
                }
                if (str != null && this.q != null) {
                    throw new IllegalArgumentException("Both createFromAsset() and createFromFile() was called on this Builder but the database can only be created using one of the two configurations.");
                }
                this.g = new k(str, this.q, this.g);
            }
            Context context = this.c;
            androidx.room.a aVar = new androidx.room.a(context, this.b, this.g, this.m, this.d, this.h, this.i.b(context), this.e, this.f, this.j, this.k, this.l, this.n, this.p, this.q);
            h hVar = (h) g.b(this.a, "_Impl");
            hVar.l(aVar);
            return hVar;
        }

        public a e() {
            this.k = false;
            this.l = true;
            return this;
        }

        public a f(n0.c.c cVar) {
            this.g = cVar;
            return this;
        }

        public a g(Executor executor) {
            this.e = executor;
            return this;
        }
    }

    public static abstract class b {
        public void a(n0.b bVar) {
        }

        public void b(n0.b bVar) {
        }

        public void c(n0.b bVar) {
        }
    }

    public enum c {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        private static boolean a(ActivityManager activityManager) {
            return activityManager.isLowRamDevice();
        }

        c b(Context context) {
            if (this != AUTOMATIC) {
                return this;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            return (activityManager == null || a(activityManager)) ? TRUNCATE : WRITE_AHEAD_LOGGING;
        }
    }

    public static class d {
        private HashMap a = new HashMap();

        private void a(l0.a aVar) {
            int i = aVar.a;
            int i2 = aVar.b;
            TreeMap treeMap = (TreeMap) this.a.get(Integer.valueOf(i));
            if (treeMap == null) {
                treeMap = new TreeMap();
                this.a.put(Integer.valueOf(i), treeMap);
            }
            l0.a aVar2 = (l0.a) treeMap.get(Integer.valueOf(i2));
            if (aVar2 != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Overriding migration ");
                sb.append(aVar2);
                sb.append(" with ");
                sb.append(aVar);
            }
            treeMap.put(Integer.valueOf(i2), aVar);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0019  */
        /* JADX WARN: Code duplicated, block: B:11:0x001e  */
        /* JADX WARN: Code duplicated, block: B:15:0x002d  */
        /* JADX WARN: Code duplicated, block: B:17:0x003a  */
        /* JADX WARN: Code duplicated, block: B:19:0x003e  */
        /* JADX WARN: Code duplicated, block: B:20:0x0040  */
        /* JADX WARN: Code duplicated, block: B:30:0x0016 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:33:0x0054 A[EDGE_INSN: B:33:0x0054->B:25:0x0054 BREAK  A[LOOP:1: B:13:0x0026->B:41:?], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:35:0x0047 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:41:? A[LOOP:1: B:13:0x0026->B:41:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:9:0x0017 A[DONT_INVERT] */
        private List d(List list, boolean z, int i, int i2) {
            TreeMap treeMap;
            Set setKeySet;
            Iterator it;
            boolean z2;
            int iIntValue;
            do {
                if (!z) {
                    if (i <= i2) {
                        return list;
                    }
                    treeMap = (TreeMap) this.a.get(Integer.valueOf(i));
                    if (treeMap == null) {
                        if (z) {
                            setKeySet = treeMap.descendingKeySet();
                        } else {
                            setKeySet = treeMap.keySet();
                        }
                        it = setKeySet.iterator();
                        while (true) {
                            z2 = false;
                            if (it.hasNext()) {
                                break;
                                break;
                            }
                            iIntValue = ((Integer) it.next()).intValue();
                            if (z) {
                                if (iIntValue > i2) {
                                }
                            } else if (iIntValue < i2) {
                            }
                            if (z2) {
                                list.add(treeMap.get(Integer.valueOf(iIntValue)));
                                i = iIntValue;
                                z2 = true;
                                break;
                                break;
                            }
                        }
                    } else {
                        return null;
                    }
                } else {
                    if (i >= i2) {
                        return list;
                    }
                    treeMap = (TreeMap) this.a.get(Integer.valueOf(i));
                    if (treeMap == null) {
                        if (z) {
                            setKeySet = treeMap.descendingKeySet();
                        } else {
                            setKeySet = treeMap.keySet();
                        }
                        it = setKeySet.iterator();
                        while (true) {
                            z2 = false;
                            if (it.hasNext()) {
                                break;
                            }
                            iIntValue = ((Integer) it.next()).intValue();
                            if (z) {
                                if (iIntValue > i2 && iIntValue > i) {
                                    z2 = true;
                                }
                            } else if (iIntValue < i2 && iIntValue < i) {
                                z2 = true;
                            }
                            if (z2) {
                                list.add(treeMap.get(Integer.valueOf(iIntValue)));
                                i = iIntValue;
                                z2 = true;
                                break;
                            }
                        }
                    } else {
                        return null;
                    }
                }
            } while (z2);
            return null;
        }

        public void b(l0.a... aVarArr) {
            for (l0.a aVar : aVarArr) {
                a(aVar);
            }
        }

        public List c(int i, int i2) {
            if (i == i2) {
                return Collections.emptyList();
            }
            return d(new ArrayList(), i2 > i, i, i2);
        }
    }

    private static boolean n() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public void a() {
        if (!this.f && n()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void b() {
        if (!k() && this.j.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    public void c() {
        a();
        n0.b bVarX = this.d.x();
        this.e.m(bVarX);
        bVarX.beginTransaction();
    }

    public n0.f d(String str) {
        a();
        b();
        return this.d.x().compileStatement(str);
    }

    protected abstract e e();

    protected abstract n0.c f(androidx.room.a aVar);

    public void g() {
        this.d.x().endTransaction();
        if (k()) {
            return;
        }
        this.e.f();
    }

    Lock h() {
        return this.i.readLock();
    }

    public n0.c i() {
        return this.d;
    }

    public Executor j() {
        return this.b;
    }

    public boolean k() {
        return this.d.x().E();
    }

    public void l(androidx.room.a aVar) {
        n0.c cVarF = f(aVar);
        this.d = cVarF;
        if (cVarF instanceof j) {
            ((j) cVarF).b(aVar);
        }
        boolean z = aVar.g == c.WRITE_AHEAD_LOGGING;
        this.d.setWriteAheadLoggingEnabled(z);
        this.h = aVar.e;
        this.b = aVar.h;
        this.c = new l(aVar.i);
        this.f = aVar.f;
        this.g = z;
        if (aVar.j) {
            this.e.i(aVar.b, aVar.c);
        }
    }

    protected void m(n0.b bVar) {
        this.e.d(bVar);
    }

    public boolean o() {
        n0.b bVar = this.a;
        return bVar != null && bVar.isOpen();
    }

    public Cursor p(n0.e eVar) {
        return q(eVar, null);
    }

    public Cursor q(n0.e eVar, CancellationSignal cancellationSignal) {
        a();
        b();
        return cancellationSignal != null ? this.d.x().C(eVar, cancellationSignal) : this.d.x().H(eVar);
    }

    public void r() {
        this.d.x().setTransactionSuccessful();
    }
}
