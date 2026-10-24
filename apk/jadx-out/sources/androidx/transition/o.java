package androidx.transition;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class o extends k {
    int c;
    private ArrayList a = new ArrayList();
    private boolean b = true;
    boolean d = false;
    private int e = 0;

    class a extends l {
        final /* synthetic */ k a;

        a(k kVar) {
            this.a = kVar;
        }

        @Override // androidx.transition.k.g
        public void d(k kVar) {
            this.a.runAnimators();
            kVar.removeListener(this);
        }
    }

    static class b extends l {
        o a;

        b(o oVar) {
            this.a = oVar;
        }

        @Override // androidx.transition.l, androidx.transition.k.g
        public void b(k kVar) {
            o oVar = this.a;
            if (oVar.d) {
                return;
            }
            oVar.start();
            this.a.d = true;
        }

        @Override // androidx.transition.k.g
        public void d(k kVar) {
            o oVar = this.a;
            int i = oVar.c - 1;
            oVar.c = i;
            if (i == 0) {
                oVar.d = false;
                oVar.end();
            }
            kVar.removeListener(this);
        }
    }

    private void K() {
        b bVar = new b(this);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((k) it.next()).addListener(bVar);
        }
        this.c = this.a.size();
    }

    private void x(k kVar) {
        this.a.add(kVar);
        kVar.mParent = this;
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public o removeListener(k.g gVar) {
        return (o) super.removeListener(gVar);
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public o removeTarget(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            ((k) this.a.get(i2)).removeTarget(i);
        }
        return (o) super.removeTarget(i);
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public o removeTarget(View view) {
        for (int i = 0; i < this.a.size(); i++) {
            ((k) this.a.get(i)).removeTarget(view);
        }
        return (o) super.removeTarget(view);
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public o removeTarget(Class cls) {
        for (int i = 0; i < this.a.size(); i++) {
            ((k) this.a.get(i)).removeTarget((Class<?>) cls);
        }
        return (o) super.removeTarget((Class<?>) cls);
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public o removeTarget(String str) {
        for (int i = 0; i < this.a.size(); i++) {
            ((k) this.a.get(i)).removeTarget(str);
        }
        return (o) super.removeTarget(str);
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public o setDuration(long j) {
        ArrayList arrayList;
        super.setDuration(j);
        if (this.mDuration >= 0 && (arrayList = this.a) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((k) this.a.get(i)).setDuration(j);
            }
        }
        return this;
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public o setInterpolator(TimeInterpolator timeInterpolator) {
        this.e |= 1;
        ArrayList arrayList = this.a;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((k) this.a.get(i)).setInterpolator(timeInterpolator);
            }
        }
        return (o) super.setInterpolator(timeInterpolator);
    }

    public o H(int i) {
        if (i == 0) {
            this.b = true;
        } else {
            if (i != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i);
            }
            this.b = false;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.transition.k
    /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
    public o setSceneRoot(ViewGroup viewGroup) {
        super.setSceneRoot(viewGroup);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((k) this.a.get(i)).setSceneRoot(viewGroup);
        }
        return this;
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
    public o setStartDelay(long j) {
        return (o) super.setStartDelay(j);
    }

    @Override // androidx.transition.k
    protected void cancel() {
        super.cancel();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((k) this.a.get(i)).cancel();
        }
    }

    @Override // androidx.transition.k
    public void captureEndValues(r rVar) {
        if (isValidTarget(rVar.b)) {
            for (k kVar : this.a) {
                if (kVar.isValidTarget(rVar.b)) {
                    kVar.captureEndValues(rVar);
                    rVar.c.add(kVar);
                }
            }
        }
    }

    @Override // androidx.transition.k
    void capturePropagationValues(r rVar) {
        super.capturePropagationValues(rVar);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((k) this.a.get(i)).capturePropagationValues(rVar);
        }
    }

    @Override // androidx.transition.k
    public void captureStartValues(r rVar) {
        if (isValidTarget(rVar.b)) {
            for (k kVar : this.a) {
                if (kVar.isValidTarget(rVar.b)) {
                    kVar.captureStartValues(rVar);
                    rVar.c.add(kVar);
                }
            }
        }
    }

    @Override // androidx.transition.k
    protected void createAnimators(ViewGroup viewGroup, s sVar, s sVar2, ArrayList arrayList, ArrayList arrayList2) {
        long startDelay = getStartDelay();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            k kVar = (k) this.a.get(i);
            if (startDelay > 0 && (this.b || i == 0)) {
                long startDelay2 = kVar.getStartDelay();
                if (startDelay2 > 0) {
                    kVar.setStartDelay(startDelay2 + startDelay);
                } else {
                    kVar.setStartDelay(startDelay);
                }
            }
            kVar.createAnimators(viewGroup, sVar, sVar2, arrayList, arrayList2);
        }
    }

    @Override // androidx.transition.k
    public k excludeTarget(View view, boolean z) {
        for (int i = 0; i < this.a.size(); i++) {
            ((k) this.a.get(i)).excludeTarget(view, z);
        }
        return super.excludeTarget(view, z);
    }

    @Override // androidx.transition.k
    void forceToEnd(ViewGroup viewGroup) {
        super.forceToEnd(viewGroup);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((k) this.a.get(i)).forceToEnd(viewGroup);
        }
    }

    @Override // androidx.transition.k
    public void pause(View view) {
        super.pause(view);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((k) this.a.get(i)).pause(view);
        }
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public o addListener(k.g gVar) {
        return (o) super.addListener(gVar);
    }

    @Override // androidx.transition.k
    public void resume(View view) {
        super.resume(view);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((k) this.a.get(i)).resume(view);
        }
    }

    @Override // androidx.transition.k
    protected void runAnimators() {
        if (this.a.isEmpty()) {
            start();
            end();
            return;
        }
        K();
        if (this.b) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((k) it.next()).runAnimators();
            }
            return;
        }
        for (int i = 1; i < this.a.size(); i++) {
            ((k) this.a.get(i - 1)).addListener(new a((k) this.a.get(i)));
        }
        k kVar = (k) this.a.get(0);
        if (kVar != null) {
            kVar.runAnimators();
        }
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public o addTarget(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            ((k) this.a.get(i2)).addTarget(i);
        }
        return (o) super.addTarget(i);
    }

    @Override // androidx.transition.k
    void setCanRemoveViews(boolean z) {
        super.setCanRemoveViews(z);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((k) this.a.get(i)).setCanRemoveViews(z);
        }
    }

    @Override // androidx.transition.k
    public void setEpicenterCallback(k.f fVar) {
        super.setEpicenterCallback(fVar);
        this.e |= 8;
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((k) this.a.get(i)).setEpicenterCallback(fVar);
        }
    }

    @Override // androidx.transition.k
    public void setPathMotion(g gVar) {
        super.setPathMotion(gVar);
        this.e |= 4;
        if (this.a != null) {
            for (int i = 0; i < this.a.size(); i++) {
                ((k) this.a.get(i)).setPathMotion(gVar);
            }
        }
    }

    @Override // androidx.transition.k
    public void setPropagation(n nVar) {
        super.setPropagation(nVar);
        this.e |= 2;
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((k) this.a.get(i)).setPropagation(nVar);
        }
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public o addTarget(View view) {
        for (int i = 0; i < this.a.size(); i++) {
            ((k) this.a.get(i)).addTarget(view);
        }
        return (o) super.addTarget(view);
    }

    @Override // androidx.transition.k
    String toString(String str) {
        String string = super.toString(str);
        for (int i = 0; i < this.a.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append("\n");
            sb.append(((k) this.a.get(i)).toString(str + "  "));
            string = sb.toString();
        }
        return string;
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public o addTarget(Class cls) {
        for (int i = 0; i < this.a.size(); i++) {
            ((k) this.a.get(i)).addTarget((Class<?>) cls);
        }
        return (o) super.addTarget((Class<?>) cls);
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public o addTarget(String str) {
        for (int i = 0; i < this.a.size(); i++) {
            ((k) this.a.get(i)).addTarget(str);
        }
        return (o) super.addTarget(str);
    }

    public o w(k kVar) {
        x(kVar);
        long j = this.mDuration;
        if (j >= 0) {
            kVar.setDuration(j);
        }
        if ((this.e & 1) != 0) {
            kVar.setInterpolator(getInterpolator());
        }
        if ((this.e & 2) != 0) {
            getPropagation();
            kVar.setPropagation(null);
        }
        if ((this.e & 4) != 0) {
            kVar.setPathMotion(getPathMotion());
        }
        if ((this.e & 8) != 0) {
            kVar.setEpicenterCallback(getEpicenterCallback());
        }
        return this;
    }

    public k y(int i) {
        if (i < 0 || i >= this.a.size()) {
            return null;
        }
        return (k) this.a.get(i);
    }

    public int z() {
        return this.a.size();
    }

    @Override // androidx.transition.k
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public k mo6clone() {
        o oVar = (o) super.mo6clone();
        oVar.a = new ArrayList();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            oVar.x(((k) this.a.get(i)).mo6clone());
        }
        return oVar;
    }

    @Override // androidx.transition.k
    public k excludeTarget(String str, boolean z) {
        for (int i = 0; i < this.a.size(); i++) {
            ((k) this.a.get(i)).excludeTarget(str, z);
        }
        return super.excludeTarget(str, z);
    }

    @Override // androidx.transition.k
    public k excludeTarget(int i, boolean z) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            ((k) this.a.get(i2)).excludeTarget(i, z);
        }
        return super.excludeTarget(i, z);
    }

    @Override // androidx.transition.k
    public k excludeTarget(Class cls, boolean z) {
        for (int i = 0; i < this.a.size(); i++) {
            ((k) this.a.get(i)).excludeTarget((Class<?>) cls, z);
        }
        return super.excludeTarget((Class<?>) cls, z);
    }
}
