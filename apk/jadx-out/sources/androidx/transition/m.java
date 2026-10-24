package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {
    private static k a = new b();
    private static ThreadLocal b = new ThreadLocal();
    static ArrayList c = new ArrayList();

    private static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        k a;
        ViewGroup b;

        /* JADX INFO: renamed from: androidx.transition.m$a$a, reason: collision with other inner class name */
        class C0056a extends l {
            final /* synthetic */ androidx.collection.a a;

            C0056a(androidx.collection.a aVar) {
                this.a = aVar;
            }

            @Override // androidx.transition.k.g
            public void d(k kVar) {
                ((ArrayList) this.a.get(a.this.b)).remove(kVar);
                kVar.removeListener(this);
            }
        }

        a(k kVar, ViewGroup viewGroup) {
            this.a = kVar;
            this.b = viewGroup;
        }

        private void a() {
            this.b.getViewTreeObserver().removeOnPreDrawListener(this);
            this.b.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!m.c.remove(this.b)) {
                return true;
            }
            androidx.collection.a aVarB = m.b();
            ArrayList arrayList = (ArrayList) aVarB.get(this.b);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList();
                aVarB.put(this.b, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.a);
            this.a.addListener(new C0056a(aVarB));
            this.a.captureValues(this.b, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((k) it.next()).resume(this.b);
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
            m.c.remove(this.b);
            ArrayList arrayList = (ArrayList) m.b().get(this.b);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((k) it.next()).resume(this.b);
                }
            }
            this.a.clearValues(true);
        }
    }

    public static void a(ViewGroup viewGroup, k kVar) {
        if (c.contains(viewGroup) || !androidx.core.view.m0.R(viewGroup)) {
            return;
        }
        c.add(viewGroup);
        if (kVar == null) {
            kVar = a;
        }
        k kVarMo6clone = kVar.mo6clone();
        d(viewGroup, kVarMo6clone);
        j.b(viewGroup, null);
        c(viewGroup, kVarMo6clone);
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

    private static void c(ViewGroup viewGroup, k kVar) {
        if (kVar == null || viewGroup == null) {
            return;
        }
        a aVar = new a(kVar, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    private static void d(ViewGroup viewGroup, k kVar) {
        ArrayList arrayList = (ArrayList) b().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((k) it.next()).pause(viewGroup);
            }
        }
        if (kVar != null) {
            kVar.captureValues(viewGroup, true);
        }
        j.a(viewGroup);
    }
}
