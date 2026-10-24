package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.core.view.c1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class n implements Cloneable {
    static final boolean DBG = false;
    private static final String LOG_TAG = "Transition";
    private static final int MATCH_FIRST = 1;
    public static final int MATCH_ID = 3;
    private static final String MATCH_ID_STR = "id";
    public static final int MATCH_INSTANCE = 1;
    private static final String MATCH_INSTANCE_STR = "instance";
    public static final int MATCH_ITEM_ID = 4;
    private static final String MATCH_ITEM_ID_STR = "itemId";
    private static final int MATCH_LAST = 4;
    public static final int MATCH_NAME = 2;
    private static final String MATCH_NAME_STR = "name";
    private ArrayList<u> mEndValuesList;
    private f mEpicenterCallback;
    private androidx.collection.a mNameOverrides;
    q mPropagation;
    private ArrayList<u> mStartValuesList;
    private static final int[] DEFAULT_MATCH_ORDER = {2, 1, 3, 4};
    private static final h STRAIGHT_PATH_MOTION = new a();
    private static ThreadLocal<androidx.collection.a> sRunningAnimators = new ThreadLocal<>();
    private String mName = getClass().getName();
    private long mStartDelay = -1;
    long mDuration = -1;
    private TimeInterpolator mInterpolator = null;
    ArrayList<Integer> mTargetIds = new ArrayList<>();
    ArrayList<View> mTargets = new ArrayList<>();
    private ArrayList<String> mTargetNames = null;
    private ArrayList<Class<?>> mTargetTypes = null;
    private ArrayList<Integer> mTargetIdExcludes = null;
    private ArrayList<View> mTargetExcludes = null;
    private ArrayList<Class<?>> mTargetTypeExcludes = null;
    private ArrayList<String> mTargetNameExcludes = null;
    private ArrayList<Integer> mTargetIdChildExcludes = null;
    private ArrayList<View> mTargetChildExcludes = null;
    private ArrayList<Class<?>> mTargetTypeChildExcludes = null;
    private v mStartValues = new v();
    private v mEndValues = new v();
    r mParent = null;
    private int[] mMatchOrder = DEFAULT_MATCH_ORDER;
    private ViewGroup mSceneRoot = null;
    boolean mCanRemoveViews = DBG;
    ArrayList<Animator> mCurrentAnimators = new ArrayList<>();
    private int mNumInstances = 0;
    private boolean mPaused = DBG;
    private boolean mEnded = DBG;
    private ArrayList<g> mListeners = null;
    private ArrayList<Animator> mAnimators = new ArrayList<>();
    private h mPathMotion = STRAIGHT_PATH_MOTION;

    static class a extends h {
        a() {
        }

        @Override // androidx.transition.h
        public Path a(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    }

    class b extends AnimatorListenerAdapter {
        final /* synthetic */ androidx.collection.a a;

        b(androidx.collection.a aVar) {
            this.a = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.remove(animator);
            n.this.mCurrentAnimators.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            n.this.mCurrentAnimators.add(animator);
        }
    }

    class c extends AnimatorListenerAdapter {
        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            n.this.end();
            animator.removeListener(this);
        }
    }

    private static class d {
        View a;
        String b;
        u c;
        r0 d;
        n e;

        d(View view, String str, n nVar, r0 r0Var, u uVar) {
            this.a = view;
            this.b = str;
            this.c = uVar;
            this.d = r0Var;
            this.e = nVar;
        }
    }

    private static class e {
        static ArrayList a(ArrayList arrayList, Object obj) {
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            if (!arrayList.contains(obj)) {
                arrayList.add(obj);
            }
            return arrayList;
        }

        static ArrayList b(ArrayList arrayList, Object obj) {
            if (arrayList == null) {
                return arrayList;
            }
            arrayList.remove(obj);
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList;
        }
    }

    public static abstract class f {
        public abstract Rect a(n nVar);
    }

    public interface g {
        void a(n nVar);

        void b(n nVar);

        void c(n nVar);

        void d(n nVar);

        void e(n nVar);
    }

    private void a(androidx.collection.a aVar, androidx.collection.a aVar2) {
        for (int i = 0; i < aVar.size(); i++) {
            u uVar = (u) aVar.valueAt(i);
            if (isValidTarget(uVar.b)) {
                this.mStartValuesList.add(uVar);
                this.mEndValuesList.add(null);
            }
        }
        for (int i2 = 0; i2 < aVar2.size(); i2++) {
            u uVar2 = (u) aVar2.valueAt(i2);
            if (isValidTarget(uVar2.b)) {
                this.mEndValuesList.add(uVar2);
                this.mStartValuesList.add(null);
            }
        }
    }

    private static void b(v vVar, View view, u uVar) {
        vVar.a.put(view, uVar);
        int id = view.getId();
        if (id >= 0) {
            if (vVar.b.indexOfKey(id) >= 0) {
                vVar.b.put(id, null);
            } else {
                vVar.b.put(id, view);
            }
        }
        String strI = c1.I(view);
        if (strI != null) {
            if (vVar.d.containsKey(strI)) {
                vVar.d.put(strI, null);
            } else {
                vVar.d.put(strI, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (vVar.c.h(itemIdAtPosition) < 0) {
                    c1.t0(view, true);
                    vVar.c.j(itemIdAtPosition, view);
                    return;
                }
                View view2 = (View) vVar.c.f(itemIdAtPosition);
                if (view2 != null) {
                    c1.t0(view2, DBG);
                    vVar.c.j(itemIdAtPosition, null);
                }
            }
        }
    }

    private static boolean c(int[] iArr, int i) {
        int i2 = iArr[i];
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return true;
            }
        }
        return DBG;
    }

    private void d(View view, boolean z) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList<Integer> arrayList = this.mTargetIdExcludes;
        if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
            ArrayList<View> arrayList2 = this.mTargetExcludes;
            if (arrayList2 == null || !arrayList2.contains(view)) {
                ArrayList<Class<?>> arrayList3 = this.mTargetTypeExcludes;
                if (arrayList3 != null) {
                    int size = arrayList3.size();
                    for (int i = 0; i < size; i++) {
                        if (this.mTargetTypeExcludes.get(i).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    u uVar = new u(view);
                    if (z) {
                        captureStartValues(uVar);
                    } else {
                        captureEndValues(uVar);
                    }
                    uVar.c.add(this);
                    capturePropagationValues(uVar);
                    if (z) {
                        b(this.mStartValues, view, uVar);
                    } else {
                        b(this.mEndValues, view, uVar);
                    }
                }
                if (view instanceof ViewGroup) {
                    ArrayList<Integer> arrayList4 = this.mTargetIdChildExcludes;
                    if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                        ArrayList<View> arrayList5 = this.mTargetChildExcludes;
                        if (arrayList5 == null || !arrayList5.contains(view)) {
                            ArrayList<Class<?>> arrayList6 = this.mTargetTypeChildExcludes;
                            if (arrayList6 != null) {
                                int size2 = arrayList6.size();
                                for (int i2 = 0; i2 < size2; i2++) {
                                    if (this.mTargetTypeChildExcludes.get(i2).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                                d(viewGroup.getChildAt(i3), z);
                            }
                        }
                    }
                }
            }
        }
    }

    private ArrayList e(ArrayList arrayList, int i, boolean z) {
        if (i > 0) {
            return z ? e.a(arrayList, Integer.valueOf(i)) : e.b(arrayList, Integer.valueOf(i));
        }
        return arrayList;
    }

    private static ArrayList f(ArrayList arrayList, Object obj, boolean z) {
        if (obj != null) {
            return z ? e.a(arrayList, obj) : e.b(arrayList, obj);
        }
        return arrayList;
    }

    private ArrayList g(ArrayList arrayList, Class cls, boolean z) {
        if (cls != null) {
            return z ? e.a(arrayList, cls) : e.b(arrayList, cls);
        }
        return arrayList;
    }

    private ArrayList h(ArrayList arrayList, View view, boolean z) {
        if (view != null) {
            return z ? e.a(arrayList, view) : e.b(arrayList, view);
        }
        return arrayList;
    }

    private static androidx.collection.a i() {
        androidx.collection.a aVar = sRunningAnimators.get();
        if (aVar != null) {
            return aVar;
        }
        androidx.collection.a aVar2 = new androidx.collection.a();
        sRunningAnimators.set(aVar2);
        return aVar2;
    }

    private static boolean j(int i) {
        if (i < 1 || i > 4) {
            return DBG;
        }
        return true;
    }

    private static boolean k(u uVar, u uVar2, String str) {
        Object obj = uVar.a.get(str);
        Object obj2 = uVar2.a.get(str);
        if (obj == null && obj2 == null) {
            return DBG;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    private void l(androidx.collection.a aVar, androidx.collection.a aVar2, SparseArray sparseArray, SparseArray sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            View view2 = (View) sparseArray.valueAt(i);
            if (view2 != null && isValidTarget(view2) && (view = (View) sparseArray2.get(sparseArray.keyAt(i))) != null && isValidTarget(view)) {
                u uVar = (u) aVar.get(view2);
                u uVar2 = (u) aVar2.get(view);
                if (uVar != null && uVar2 != null) {
                    this.mStartValuesList.add(uVar);
                    this.mEndValuesList.add(uVar2);
                    aVar.remove(view2);
                    aVar2.remove(view);
                }
            }
        }
    }

    private void m(androidx.collection.a aVar, androidx.collection.a aVar2) {
        u uVar;
        for (int size = aVar.size() - 1; size >= 0; size--) {
            View view = (View) aVar.keyAt(size);
            if (view != null && isValidTarget(view) && (uVar = (u) aVar2.remove(view)) != null && isValidTarget(uVar.b)) {
                this.mStartValuesList.add((u) aVar.removeAt(size));
                this.mEndValuesList.add(uVar);
            }
        }
    }

    private void n(androidx.collection.a aVar, androidx.collection.a aVar2, androidx.collection.d dVar, androidx.collection.d dVar2) {
        View view;
        int iM = dVar.m();
        for (int i = 0; i < iM; i++) {
            View view2 = (View) dVar.n(i);
            if (view2 != null && isValidTarget(view2) && (view = (View) dVar2.f(dVar.i(i))) != null && isValidTarget(view)) {
                u uVar = (u) aVar.get(view2);
                u uVar2 = (u) aVar2.get(view);
                if (uVar != null && uVar2 != null) {
                    this.mStartValuesList.add(uVar);
                    this.mEndValuesList.add(uVar2);
                    aVar.remove(view2);
                    aVar2.remove(view);
                }
            }
        }
    }

    private void o(androidx.collection.a aVar, androidx.collection.a aVar2, androidx.collection.a aVar3, androidx.collection.a aVar4) {
        View view;
        int size = aVar3.size();
        for (int i = 0; i < size; i++) {
            View view2 = (View) aVar3.valueAt(i);
            if (view2 != null && isValidTarget(view2) && (view = (View) aVar4.get(aVar3.keyAt(i))) != null && isValidTarget(view)) {
                u uVar = (u) aVar.get(view2);
                u uVar2 = (u) aVar2.get(view);
                if (uVar != null && uVar2 != null) {
                    this.mStartValuesList.add(uVar);
                    this.mEndValuesList.add(uVar2);
                    aVar.remove(view2);
                    aVar2.remove(view);
                }
            }
        }
    }

    private void p(v vVar, v vVar2) {
        androidx.collection.a aVar = new androidx.collection.a(vVar.a);
        androidx.collection.a aVar2 = new androidx.collection.a(vVar2.a);
        int i = 0;
        while (true) {
            int[] iArr = this.mMatchOrder;
            if (i >= iArr.length) {
                a(aVar, aVar2);
                return;
            }
            int i2 = iArr[i];
            if (i2 == 1) {
                m(aVar, aVar2);
            } else if (i2 == 2) {
                o(aVar, aVar2, vVar.d, vVar2.d);
            } else if (i2 == 3) {
                l(aVar, aVar2, vVar.b, vVar2.b);
            } else if (i2 == 4) {
                n(aVar, aVar2, vVar.c, vVar2.c);
            }
            i++;
        }
    }

    private void q(Animator animator, androidx.collection.a aVar) {
        if (animator != null) {
            animator.addListener(new b(aVar));
            animate(animator);
        }
    }

    public n addListener(g gVar) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(gVar);
        return this;
    }

    public n addTarget(View view) {
        this.mTargets.add(view);
        return this;
    }

    protected void animate(Animator animator) {
        if (animator == null) {
            end();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(getStartDelay() + animator.getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new c());
        animator.start();
    }

    protected void cancel() {
        for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
            this.mCurrentAnimators.get(size).cancel();
        }
        ArrayList<g> arrayList = this.mListeners;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
        int size2 = arrayList2.size();
        for (int i = 0; i < size2; i++) {
            ((g) arrayList2.get(i)).e(this);
        }
    }

    public abstract void captureEndValues(u uVar);

    void capturePropagationValues(u uVar) {
    }

    public abstract void captureStartValues(u uVar);

    void captureValues(ViewGroup viewGroup, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        androidx.collection.a aVar;
        clearValues(z);
        if ((this.mTargetIds.size() > 0 || this.mTargets.size() > 0) && (((arrayList = this.mTargetNames) == null || arrayList.isEmpty()) && ((arrayList2 = this.mTargetTypes) == null || arrayList2.isEmpty()))) {
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                View viewFindViewById = viewGroup.findViewById(this.mTargetIds.get(i).intValue());
                if (viewFindViewById != null) {
                    u uVar = new u(viewFindViewById);
                    if (z) {
                        captureStartValues(uVar);
                    } else {
                        captureEndValues(uVar);
                    }
                    uVar.c.add(this);
                    capturePropagationValues(uVar);
                    if (z) {
                        b(this.mStartValues, viewFindViewById, uVar);
                    } else {
                        b(this.mEndValues, viewFindViewById, uVar);
                    }
                }
            }
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                View view = this.mTargets.get(i2);
                u uVar2 = new u(view);
                if (z) {
                    captureStartValues(uVar2);
                } else {
                    captureEndValues(uVar2);
                }
                uVar2.c.add(this);
                capturePropagationValues(uVar2);
                if (z) {
                    b(this.mStartValues, view, uVar2);
                } else {
                    b(this.mEndValues, view, uVar2);
                }
            }
        } else {
            d(viewGroup, z);
        }
        if (z || (aVar = this.mNameOverrides) == null) {
            return;
        }
        int size = aVar.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i3 = 0; i3 < size; i3++) {
            arrayList3.add(this.mStartValues.d.remove((String) this.mNameOverrides.keyAt(i3)));
        }
        for (int i4 = 0; i4 < size; i4++) {
            View view2 = (View) arrayList3.get(i4);
            if (view2 != null) {
                this.mStartValues.d.put((String) this.mNameOverrides.valueAt(i4), view2);
            }
        }
    }

    void clearValues(boolean z) {
        if (z) {
            this.mStartValues.a.clear();
            this.mStartValues.b.clear();
            this.mStartValues.c.b();
        } else {
            this.mEndValues.a.clear();
            this.mEndValues.b.clear();
            this.mEndValues.c.b();
        }
    }

    public Animator createAnimator(ViewGroup viewGroup, u uVar, u uVar2) {
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x003c  */
    protected void createAnimators(ViewGroup viewGroup, v vVar, v vVar2, ArrayList<u> arrayList, ArrayList<u> arrayList2) {
        View view;
        Animator animator;
        u uVar;
        Animator animator2;
        u uVar2;
        androidx.collection.a aVarI = i();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int i = 0;
        for (int size = arrayList.size(); i < size; size = size) {
            u uVar3 = arrayList.get(i);
            u uVar4 = arrayList2.get(i);
            if (uVar3 != null && !uVar3.c.contains(this)) {
                uVar3 = null;
            }
            if (uVar4 != null && !uVar4.c.contains(this)) {
                uVar4 = null;
            }
            if (uVar3 != null || uVar4 != null) {
                if ((uVar3 == null || uVar4 == null || isTransitionRequired(uVar3, uVar4)) ? true : DBG) {
                    Animator animatorCreateAnimator = createAnimator(viewGroup, uVar3, uVar4);
                    if (animatorCreateAnimator != null) {
                        if (uVar4 != null) {
                            View view2 = uVar4.b;
                            String[] transitionProperties = getTransitionProperties();
                            if (transitionProperties != null && transitionProperties.length > 0) {
                                uVar2 = new u(view2);
                                u uVar5 = (u) vVar2.a.get(view2);
                                if (uVar5 != null) {
                                    int i2 = 0;
                                    while (i2 < transitionProperties.length) {
                                        Map map = uVar2.a;
                                        Animator animator3 = animatorCreateAnimator;
                                        String str = transitionProperties[i2];
                                        map.put(str, uVar5.a.get(str));
                                        i2++;
                                        animatorCreateAnimator = animator3;
                                        transitionProperties = transitionProperties;
                                    }
                                }
                                Animator animator4 = animatorCreateAnimator;
                                int size2 = aVarI.size();
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= size2) {
                                        animator2 = animator4;
                                        break;
                                    }
                                    d dVar = (d) aVarI.get((Animator) aVarI.keyAt(i3));
                                    if (dVar.c != null && dVar.a == view2 && dVar.b.equals(getName()) && dVar.c.equals(uVar2)) {
                                        animator2 = null;
                                        break;
                                    }
                                    i3++;
                                }
                            } else {
                                animator2 = animatorCreateAnimator;
                                uVar2 = null;
                            }
                            view = view2;
                            animator = animator2;
                            uVar = uVar2;
                        } else {
                            view = uVar3.b;
                            animator = animatorCreateAnimator;
                            uVar = null;
                        }
                        if (animator != null) {
                            aVarI.put(animator, new d(view, getName(), this, c0.d(viewGroup), uVar));
                            this.mAnimators.add(animator);
                        }
                    }
                }
            }
            i++;
        }
        if (sparseIntArray.size() != 0) {
            for (int i4 = 0; i4 < sparseIntArray.size(); i4++) {
                Animator animator5 = this.mAnimators.get(sparseIntArray.keyAt(i4));
                animator5.setStartDelay((((long) sparseIntArray.valueAt(i4)) - Long.MAX_VALUE) + animator5.getStartDelay());
            }
        }
    }

    protected void end() {
        int i = this.mNumInstances - 1;
        this.mNumInstances = i;
        if (i == 0) {
            ArrayList<g> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((g) arrayList2.get(i2)).d(this);
                }
            }
            for (int i3 = 0; i3 < this.mStartValues.c.m(); i3++) {
                View view = (View) this.mStartValues.c.n(i3);
                if (view != null) {
                    c1.t0(view, DBG);
                }
            }
            for (int i4 = 0; i4 < this.mEndValues.c.m(); i4++) {
                View view2 = (View) this.mEndValues.c.n(i4);
                if (view2 != null) {
                    c1.t0(view2, DBG);
                }
            }
            this.mEnded = true;
        }
    }

    public n excludeChildren(View view, boolean z) {
        this.mTargetChildExcludes = h(this.mTargetChildExcludes, view, z);
        return this;
    }

    public n excludeTarget(View view, boolean z) {
        this.mTargetExcludes = h(this.mTargetExcludes, view, z);
        return this;
    }

    void forceToEnd(ViewGroup viewGroup) {
        androidx.collection.a aVarI = i();
        int size = aVarI.size();
        if (viewGroup == null || size == 0) {
            return;
        }
        r0 r0VarD = c0.d(viewGroup);
        androidx.collection.a aVar = new androidx.collection.a(aVarI);
        aVarI.clear();
        for (int i = size - 1; i >= 0; i--) {
            d dVar = (d) aVar.valueAt(i);
            if (dVar.a != null && r0VarD != null && r0VarD.equals(dVar.d)) {
                ((Animator) aVar.keyAt(i)).end();
            }
        }
    }

    public long getDuration() {
        return this.mDuration;
    }

    public Rect getEpicenter() {
        f fVar = this.mEpicenterCallback;
        if (fVar == null) {
            return null;
        }
        return fVar.a(this);
    }

    public f getEpicenterCallback() {
        return this.mEpicenterCallback;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    u getMatchedTransitionValues(View view, boolean z) {
        r rVar = this.mParent;
        if (rVar != null) {
            return rVar.getMatchedTransitionValues(view, z);
        }
        ArrayList<u> arrayList = z ? this.mStartValuesList : this.mEndValuesList;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            }
            u uVar = arrayList.get(i);
            if (uVar == null) {
                return null;
            }
            if (uVar.b == view) {
                break;
            }
            i++;
        }
        if (i >= 0) {
            return (z ? this.mEndValuesList : this.mStartValuesList).get(i);
        }
        return null;
    }

    public String getName() {
        return this.mName;
    }

    public h getPathMotion() {
        return this.mPathMotion;
    }

    public q getPropagation() {
        return null;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public List<Integer> getTargetIds() {
        return this.mTargetIds;
    }

    public List<String> getTargetNames() {
        return this.mTargetNames;
    }

    public List<Class<?>> getTargetTypes() {
        return this.mTargetTypes;
    }

    public List<View> getTargets() {
        return this.mTargets;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public u getTransitionValues(View view, boolean z) {
        r rVar = this.mParent;
        if (rVar != null) {
            return rVar.getTransitionValues(view, z);
        }
        return (u) (z ? this.mStartValues : this.mEndValues).a.get(view);
    }

    public boolean isTransitionRequired(u uVar, u uVar2) {
        if (uVar == null || uVar2 == null) {
            return DBG;
        }
        String[] transitionProperties = getTransitionProperties();
        if (transitionProperties == null) {
            Iterator it = uVar.a.keySet().iterator();
            while (it.hasNext()) {
                if (k(uVar, uVar2, (String) it.next())) {
                }
            }
            return DBG;
        }
        for (String str : transitionProperties) {
            if (!k(uVar, uVar2, str)) {
            }
        }
        return DBG;
        return true;
    }

    boolean isValidTarget(View view) {
        ArrayList<Class<?>> arrayList;
        ArrayList<String> arrayList2;
        int id = view.getId();
        ArrayList<Integer> arrayList3 = this.mTargetIdExcludes;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return DBG;
        }
        ArrayList<View> arrayList4 = this.mTargetExcludes;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return DBG;
        }
        ArrayList<Class<?>> arrayList5 = this.mTargetTypeExcludes;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i = 0; i < size; i++) {
                if (this.mTargetTypeExcludes.get(i).isInstance(view)) {
                    return DBG;
                }
            }
        }
        if (this.mTargetNameExcludes != null && c1.I(view) != null && this.mTargetNameExcludes.contains(c1.I(view))) {
            return DBG;
        }
        if ((this.mTargetIds.size() == 0 && this.mTargets.size() == 0 && (((arrayList = this.mTargetTypes) == null || arrayList.isEmpty()) && ((arrayList2 = this.mTargetNames) == null || arrayList2.isEmpty()))) || this.mTargetIds.contains(Integer.valueOf(id)) || this.mTargets.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.mTargetNames;
        if (arrayList6 != null && arrayList6.contains(c1.I(view))) {
            return true;
        }
        if (this.mTargetTypes != null) {
            for (int i2 = 0; i2 < this.mTargetTypes.size(); i2++) {
                if (this.mTargetTypes.get(i2).isInstance(view)) {
                    return true;
                }
            }
        }
        return DBG;
    }

    public void pause(View view) {
        if (this.mEnded) {
            return;
        }
        androidx.collection.a aVarI = i();
        int size = aVarI.size();
        r0 r0VarD = c0.d(view);
        for (int i = size - 1; i >= 0; i--) {
            d dVar = (d) aVarI.valueAt(i);
            if (dVar.a != null && r0VarD.equals(dVar.d)) {
                androidx.transition.a.b((Animator) aVarI.keyAt(i));
            }
        }
        ArrayList<g> arrayList = this.mListeners;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
            int size2 = arrayList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((g) arrayList2.get(i2)).c(this);
            }
        }
        this.mPaused = true;
    }

    void playTransition(ViewGroup viewGroup) {
        d dVar;
        this.mStartValuesList = new ArrayList<>();
        this.mEndValuesList = new ArrayList<>();
        p(this.mStartValues, this.mEndValues);
        androidx.collection.a aVarI = i();
        int size = aVarI.size();
        r0 r0VarD = c0.d(viewGroup);
        for (int i = size - 1; i >= 0; i--) {
            Animator animator = (Animator) aVarI.keyAt(i);
            if (animator != null && (dVar = (d) aVarI.get(animator)) != null && dVar.a != null && r0VarD.equals(dVar.d)) {
                u uVar = dVar.c;
                View view = dVar.a;
                u transitionValues = getTransitionValues(view, true);
                u matchedTransitionValues = getMatchedTransitionValues(view, true);
                if (transitionValues == null && matchedTransitionValues == null) {
                    matchedTransitionValues = (u) this.mEndValues.a.get(view);
                }
                if ((!(transitionValues == null && matchedTransitionValues == null) && dVar.e.isTransitionRequired(uVar, matchedTransitionValues)) ? true : DBG) {
                    if (animator.isRunning() || animator.isStarted()) {
                        animator.cancel();
                    } else {
                        aVarI.remove(animator);
                    }
                }
            }
        }
        createAnimators(viewGroup, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
        runAnimators();
    }

    public n removeListener(g gVar) {
        ArrayList<g> arrayList = this.mListeners;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(gVar);
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
        return this;
    }

    public n removeTarget(View view) {
        this.mTargets.remove(view);
        return this;
    }

    public void resume(View view) {
        if (this.mPaused) {
            if (!this.mEnded) {
                androidx.collection.a aVarI = i();
                int size = aVarI.size();
                r0 r0VarD = c0.d(view);
                for (int i = size - 1; i >= 0; i--) {
                    d dVar = (d) aVarI.valueAt(i);
                    if (dVar.a != null && r0VarD.equals(dVar.d)) {
                        androidx.transition.a.c((Animator) aVarI.keyAt(i));
                    }
                }
                ArrayList<g> arrayList = this.mListeners;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                    int size2 = arrayList2.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ((g) arrayList2.get(i2)).a(this);
                    }
                }
            }
            this.mPaused = DBG;
        }
    }

    protected void runAnimators() {
        start();
        androidx.collection.a aVarI = i();
        for (Animator animator : this.mAnimators) {
            if (aVarI.containsKey(animator)) {
                start();
                q(animator, aVarI);
            }
        }
        this.mAnimators.clear();
        end();
    }

    void setCanRemoveViews(boolean z) {
        this.mCanRemoveViews = z;
    }

    public n setDuration(long j) {
        this.mDuration = j;
        return this;
    }

    public void setEpicenterCallback(f fVar) {
        this.mEpicenterCallback = fVar;
    }

    public n setInterpolator(TimeInterpolator timeInterpolator) {
        this.mInterpolator = timeInterpolator;
        return this;
    }

    public void setMatchOrder(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            this.mMatchOrder = DEFAULT_MATCH_ORDER;
            return;
        }
        for (int i = 0; i < iArr.length; i++) {
            if (!j(iArr[i])) {
                throw new IllegalArgumentException("matches contains invalid value");
            }
            if (c(iArr, i)) {
                throw new IllegalArgumentException("matches contains a duplicate value");
            }
        }
        this.mMatchOrder = (int[]) iArr.clone();
    }

    public void setPathMotion(h hVar) {
        if (hVar == null) {
            this.mPathMotion = STRAIGHT_PATH_MOTION;
        } else {
            this.mPathMotion = hVar;
        }
    }

    public void setPropagation(q qVar) {
    }

    n setSceneRoot(ViewGroup viewGroup) {
        this.mSceneRoot = viewGroup;
        return this;
    }

    public n setStartDelay(long j) {
        this.mStartDelay = j;
        return this;
    }

    protected void start() {
        if (this.mNumInstances == 0) {
            ArrayList<g> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((g) arrayList2.get(i)).b(this);
                }
            }
            this.mEnded = DBG;
        }
        this.mNumInstances++;
    }

    public String toString() {
        return toString("");
    }

    public n addTarget(int i) {
        if (i != 0) {
            this.mTargetIds.add(Integer.valueOf(i));
        }
        return this;
    }

    @Override // 
    /* JADX INFO: renamed from: clone */
    public n mo4clone() {
        try {
            n nVar = (n) super.clone();
            nVar.mAnimators = new ArrayList<>();
            nVar.mStartValues = new v();
            nVar.mEndValues = new v();
            nVar.mStartValuesList = null;
            nVar.mEndValuesList = null;
            return nVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public n excludeChildren(int i, boolean z) {
        this.mTargetIdChildExcludes = e(this.mTargetIdChildExcludes, i, z);
        return this;
    }

    public n excludeTarget(int i, boolean z) {
        this.mTargetIdExcludes = e(this.mTargetIdExcludes, i, z);
        return this;
    }

    public n removeTarget(int i) {
        if (i != 0) {
            this.mTargetIds.remove(Integer.valueOf(i));
        }
        return this;
    }

    String toString(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.mDuration != -1) {
            str2 = str2 + "dur(" + this.mDuration + ") ";
        }
        if (this.mStartDelay != -1) {
            str2 = str2 + "dly(" + this.mStartDelay + ") ";
        }
        if (this.mInterpolator != null) {
            str2 = str2 + "interp(" + this.mInterpolator + ") ";
        }
        if (this.mTargetIds.size() <= 0 && this.mTargets.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.mTargetIds.size() > 0) {
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                if (i > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.mTargetIds.get(i);
            }
        }
        if (this.mTargets.size() > 0) {
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                if (i2 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.mTargets.get(i2);
            }
        }
        return str3 + ")";
    }

    public n addTarget(String str) {
        if (this.mTargetNames == null) {
            this.mTargetNames = new ArrayList<>();
        }
        this.mTargetNames.add(str);
        return this;
    }

    public n excludeChildren(Class<?> cls, boolean z) {
        this.mTargetTypeChildExcludes = g(this.mTargetTypeChildExcludes, cls, z);
        return this;
    }

    public n excludeTarget(String str, boolean z) {
        this.mTargetNameExcludes = f(this.mTargetNameExcludes, str, z);
        return this;
    }

    public n removeTarget(String str) {
        ArrayList<String> arrayList = this.mTargetNames;
        if (arrayList != null) {
            arrayList.remove(str);
        }
        return this;
    }

    public n excludeTarget(Class<?> cls, boolean z) {
        this.mTargetTypeExcludes = g(this.mTargetTypeExcludes, cls, z);
        return this;
    }

    public n removeTarget(Class<?> cls) {
        ArrayList<Class<?>> arrayList = this.mTargetTypes;
        if (arrayList != null) {
            arrayList.remove(cls);
        }
        return this;
    }

    public n addTarget(Class<?> cls) {
        if (this.mTargetTypes == null) {
            this.mTargetTypes = new ArrayList<>();
        }
        this.mTargetTypes.add(cls);
        return this;
    }
}
