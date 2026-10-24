package androidx.work.impl;

import a1.n;
import a1.r;
import a1.u;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class g extends r {
    private static final String j = a1.k.f("WorkContinuationImpl");
    private final k a;
    private final String b;
    private final a1.d c;
    private final List d;
    private final List e;
    private final List f;
    private final List g;
    private boolean h;
    private n i;

    public g(k kVar, List list) {
        this(kVar, null, a1.d.b, list, null);
    }

    private static boolean i(g gVar, Set set) {
        set.addAll(gVar.c());
        Set setL = l(gVar);
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (setL.contains((String) it.next())) {
                return true;
            }
        }
        List listE = gVar.e();
        if (listE != null && !listE.isEmpty()) {
            Iterator it2 = listE.iterator();
            while (it2.hasNext()) {
                if (i((g) it2.next(), set)) {
                    return true;
                }
            }
        }
        set.removeAll(gVar.c());
        return false;
    }

    public static Set l(g gVar) {
        HashSet hashSet = new HashSet();
        List listE = gVar.e();
        if (listE != null && !listE.isEmpty()) {
            Iterator it = listE.iterator();
            while (it.hasNext()) {
                hashSet.addAll(((g) it.next()).c());
            }
        }
        return hashSet;
    }

    public n a() {
        if (this.h) {
            a1.k.c().h(j, String.format("Already enqueued work ids (%s)", TextUtils.join(", ", this.e)), new Throwable[0]);
        } else {
            i1.b bVar = new i1.b(this);
            this.a.o().b(bVar);
            this.i = bVar.d();
        }
        return this.i;
    }

    public a1.d b() {
        return this.c;
    }

    public List c() {
        return this.e;
    }

    public String d() {
        return this.b;
    }

    public List e() {
        return this.g;
    }

    public List f() {
        return this.d;
    }

    public k g() {
        return this.a;
    }

    public boolean h() {
        return i(this, new HashSet());
    }

    public boolean j() {
        return this.h;
    }

    public void k() {
        this.h = true;
    }

    public g(k kVar, String str, a1.d dVar, List list, List list2) {
        this.a = kVar;
        this.b = str;
        this.c = dVar;
        this.d = list;
        this.g = list2;
        this.e = new ArrayList(list.size());
        this.f = new ArrayList();
        if (list2 != null) {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                this.f.addAll(((g) it.next()).f);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String strA = ((u) list.get(i)).a();
            this.e.add(strA);
            this.f.add(strA);
        }
    }
}
