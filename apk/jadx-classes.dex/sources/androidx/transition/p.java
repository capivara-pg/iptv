package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.core.view.c1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class p {
    private static n a = new b();
    private static ThreadLocal b = new ThreadLocal();
    static ArrayList c = new ArrayList();

    private static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        n a;
        ViewGroup b;

        /* JADX INFO: renamed from: androidx.transition.p$a$a, reason: collision with other inner class name */
        class C0051a extends o {
            final /* synthetic */ androidx.collection.a a;

            C0051a(androidx.collection.a aVar) {
                this.a = aVar;
            }

            @Override // androidx.transition.n.g
            public void d(n nVar) {
                ((ArrayList) this.a.get(a.this.b)).remove(nVar);
                nVar.removeListener(this);
            }
        }

        a(n nVar, ViewGroup viewGroup) {
            this.a = nVar;
            this.b = viewGroup;
        }

        private void a() {
            this.b.getViewTreeObserver().removeOnPreDrawListener(this);
            this.b.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!p.c.remove(this.b)) {
                return true;
            }
            androidx.collection.a aVarB = p.b();
            ArrayList arrayList = (ArrayList) aVarB.get(this.b);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList();
                aVarB.put(this.b, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.a);
            this.a.addListener(new C0051a(aVarB));
            this.a.captureValues(this.b, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((n) it.next()).resume(this.b);
                }
            }
            this.a.playTransition(this.b);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            p.c.remove(this.b);
            ArrayList arrayList = (ArrayList) p.b().get(this.b);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((n) it.next()).resume(this.b);
                }
            }
            this.a.clearValues(true);
        }
    }

    public static void a(ViewGroup viewGroup, n nVar) {
        if (c.contains(viewGroup) || !c1.Q(viewGroup)) {
            return;
        }
        c.add(viewGroup);
        if (nVar == null) {
            nVar = a;
        }
        n nVarMo4clone = nVar.mo4clone();
        d(viewGroup, nVarMo4clone);
        m.b(viewGroup, null);
        c(viewGroup, nVarMo4clone);
    }

    static androidx.collection.a b() {
        androidx.collection.a aVar;
        WeakReference weakReference = (WeakReference) b.get();
        if (weakReference != null && (aVar = (androidx.collection.a) weakReference.get()) != null) {
            return aVar;
        }
        androidx.collection.a aVar2 = new androidx.collection.a();
        b.set(new WeakReference(aVar2));
        return aVar2;
    }

    private static void c(ViewGroup viewGroup, n nVar) {
        if (nVar == null || viewGroup == null) {
            return;
        }
        a aVar = new a(nVar, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    private static void d(ViewGroup viewGroup, n nVar) {
        ArrayList arrayList = (ArrayList) b().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((n) it.next()).pause(viewGroup);
            }
        }
        if (nVar != null) {
            nVar.captureValues(viewGroup, true);
        }
        m.a(viewGroup);
    }
}
