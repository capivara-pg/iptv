package androidx.fragment.app;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.a1;
import androidx.core.view.m0;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class a0 {

    class a implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ ArrayList b;
        final /* synthetic */ ArrayList c;
        final /* synthetic */ ArrayList d;
        final /* synthetic */ ArrayList e;

        a(int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
            this.a = i;
            this.b = arrayList;
            this.c = arrayList2;
            this.d = arrayList3;
            this.e = arrayList4;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (int i = 0; i < this.a; i++) {
                m0.C0((View) this.b.get(i), (String) this.c.get(i));
                m0.C0((View) this.d.get(i), (String) this.e.get(i));
            }
        }
    }

    class b implements Runnable {
        final /* synthetic */ ArrayList a;
        final /* synthetic */ Map b;

        b(ArrayList arrayList, Map map) {
            this.a = arrayList;
            this.b = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                View view = (View) this.a.get(i);
                String strJ = m0.J(view);
                if (strJ != null) {
                    m0.C0(view, a0.i(this.b, strJ));
                }
            }
        }
    }

    class c implements Runnable {
        final /* synthetic */ ArrayList a;
        final /* synthetic */ Map b;

        c(ArrayList arrayList, Map map) {
            this.a = arrayList;
            this.b = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                View view = (View) this.a.get(i);
                m0.C0(view, (String) this.b.get(m0.J(view)));
            }
        }
    }

    protected static void d(List list, View view) {
        int size = list.size();
        if (h(list, view, size)) {
            return;
        }
        if (m0.J(view) != null) {
            list.add(view);
        }
        for (int i = size; i < list.size(); i++) {
            View view2 = (View) list.get(i);
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = viewGroup.getChildAt(i2);
                    if (!h(list, childAt, size) && m0.J(childAt) != null) {
                        list.add(childAt);
                    }
                }
            }
        }
    }

    private static boolean h(List list, View view, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (list.get(i2) == view) {
                return true;
            }
        }
        return false;
    }

    static String i(Map map, String str) {
        for (Map.Entry entry : map.entrySet()) {
            if (str.equals(entry.getValue())) {
                return (String) entry.getKey();
            }
        }
        return null;
    }

    protected static boolean l(List list) {
        return list == null || list.isEmpty();
    }

    public abstract void A(Object obj, ArrayList arrayList, ArrayList arrayList2);

    public abstract Object B(Object obj);

    public abstract void a(Object obj, View view);

    public abstract void b(Object obj, ArrayList arrayList);

    public abstract void c(ViewGroup viewGroup, Object obj);

    public abstract boolean e(Object obj);

    void f(ArrayList arrayList, View view) {
        if (view.getVisibility() == 0) {
            if (!(view instanceof ViewGroup)) {
                arrayList.add(view);
                return;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            if (a1.a(viewGroup)) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                f(arrayList, viewGroup.getChildAt(i));
            }
        }
    }

    public abstract Object g(Object obj);

    void j(Map map, View view) {
        if (view.getVisibility() == 0) {
            String strJ = m0.J(view);
            if (strJ != null) {
                map.put(strJ, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    j(map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    protected void k(View view, Rect rect) {
        if (m0.Q(view)) {
            RectF rectF = new RectF();
            rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
            view.getMatrix().mapRect(rectF);
            rectF.offset(view.getLeft(), view.getTop());
            Object parent = view.getParent();
            while (parent instanceof View) {
                View view2 = (View) parent;
                rectF.offset(-view2.getScrollX(), -view2.getScrollY());
                view2.getMatrix().mapRect(rectF);
                rectF.offset(view2.getLeft(), view2.getTop());
                parent = view2.getParent();
            }
            int[] iArr = new int[2];
            view.getRootView().getLocationOnScreen(iArr);
            rectF.offset(iArr[0], iArr[1]);
            rect.set(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
        }
    }

    public abstract Object m(Object obj, Object obj2, Object obj3);

    public abstract Object n(Object obj, Object obj2, Object obj3);

    ArrayList o(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = (View) arrayList.get(i);
            arrayList2.add(m0.J(view));
            m0.C0(view, null);
        }
        return arrayList2;
    }

    public abstract void p(Object obj, View view);

    public abstract void q(Object obj, ArrayList arrayList, ArrayList arrayList2);

    public abstract void r(Object obj, View view, ArrayList arrayList);

    void s(ViewGroup viewGroup, ArrayList arrayList, Map map) {
        androidx.core.view.c0.a(viewGroup, new c(arrayList, map));
    }

    public abstract void t(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3);

    public abstract void u(Object obj, Rect rect);

    public abstract void v(Object obj, View view);

    public void w(Fragment fragment, Object obj, z.c cVar, Runnable runnable) {
        runnable.run();
    }

    void x(View view, ArrayList arrayList, Map map) {
        androidx.core.view.c0.a(view, new b(arrayList, map));
    }

    void y(View view, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Map map) {
        int size = arrayList2.size();
        ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < size; i++) {
            View view2 = (View) arrayList.get(i);
            String strJ = m0.J(view2);
            arrayList4.add(strJ);
            if (strJ != null) {
                m0.C0(view2, null);
                String str = (String) map.get(strJ);
                for (int i2 = 0; i2 < size; i2++) {
                    if (str.equals(arrayList3.get(i2))) {
                        m0.C0((View) arrayList2.get(i2), strJ);
                        break;
                    }
                }
            }
        }
        androidx.core.view.c0.a(view, new a(size, arrayList2, arrayList3, arrayList, arrayList4));
    }

    public abstract void z(Object obj, View view, ArrayList arrayList);
}
