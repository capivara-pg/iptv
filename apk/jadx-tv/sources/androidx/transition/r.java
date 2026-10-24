package androidx.transition;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class r extends n {
    int c;
    private ArrayList a = new ArrayList();
    private boolean b = true;
    boolean d = false;
    private int e = 0;

    class a extends o {
        final /* synthetic */ n a;

        a(n nVar) {
            this.a = nVar;
        }

        @Override // androidx.transition.n.g
        public void d(n nVar) {
            this.a.runAnimators();
            nVar.removeListener(this);
        }
    }

    static class b extends o {
        r a;

        b(r rVar) {
            this.a = rVar;
        }

        @Override // androidx.transition.o, androidx.transition.n.g
        public void b(n nVar) {
            r rVar = this.a;
            if (rVar.d) {
                return;
            }
            rVar.start();
            this.a.d = true;
        }

        @Override // androidx.transition.n.g
        public void d(n nVar) {
            r rVar = this.a;
            int i = rVar.c - 1;
            rVar.c = i;
            if (i == 0) {
                rVar.d = false;
                rVar.end();
            }
            nVar.removeListener(this);
        }
    }

    private void K() {
        b bVar = new b(this);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((n) it.next()).addListener(bVar);
        }
        this.c = this.a.size();
    }

    private void x(n nVar) {
        this.a.add(nVar);
        nVar.mParent = this;
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public r removeListener(n.g gVar) {
        return (r) super.removeListener(gVar);
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public r removeTarget(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            ((n) this.a.get(i2)).removeTarget(i);
        }
        return (r) super.removeTarget(i);
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public r removeTarget(View view) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).removeTarget(view);
        }
        return (r) super.removeTarget(view);
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public r removeTarget(Class cls) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).removeTarget((Class<?>) cls);
        }
        return (r) super.removeTarget((Class<?>) cls);
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public r removeTarget(String str) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).removeTarget(str);
        }
        return (r) super.removeTarget(str);
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public r setDuration(long j) {
        ArrayList arrayList;
        super.setDuration(j);
        if (this.mDuration >= 0 && (arrayList = this.a) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((n) this.a.get(i)).setDuration(j);
            }
        }
        return this;
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public r setInterpolator(TimeInterpolator timeInterpolator) {
        this.e |= 1;
        ArrayList arrayList = this.a;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((n) this.a.get(i)).setInterpolator(timeInterpolator);
            }
        }
        return (r) super.setInterpolator(timeInterpolator);
    }

    public r H(int i) {
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
    @Override // androidx.transition.n
    /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
    public r setSceneRoot(ViewGroup viewGroup) {
        super.setSceneRoot(viewGroup);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).setSceneRoot(viewGroup);
        }
        return this;
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
    public r setStartDelay(long j) {
        return (r) super.setStartDelay(j);
    }

    @Override // androidx.transition.n
    protected void cancel() {
        super.cancel();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).cancel();
        }
    }

    @Override // androidx.transition.n
    public void captureEndValues(u uVar) {
        if (isValidTarget(uVar.b)) {
            for (n nVar : this.a) {
                if (nVar.isValidTarget(uVar.b)) {
                    nVar.captureEndValues(uVar);
                    uVar.c.add(nVar);
                }
            }
        }
    }

    @Override // androidx.transition.n
    void capturePropagationValues(u uVar) {
        super.capturePropagationValues(uVar);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).capturePropagationValues(uVar);
        }
    }

    @Override // androidx.transition.n
    public void captureStartValues(u uVar) {
        if (isValidTarget(uVar.b)) {
            for (n nVar : this.a) {
                if (nVar.isValidTarget(uVar.b)) {
                    nVar.captureStartValues(uVar);
                    uVar.c.add(nVar);
                }
            }
        }
    }

    @Override // androidx.transition.n
    protected void createAnimators(ViewGroup viewGroup, v vVar, v vVar2, ArrayList arrayList, ArrayList arrayList2) {
        long startDelay = getStartDelay();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            n nVar = (n) this.a.get(i);
            if (startDelay > 0 && (this.b || i == 0)) {
                long startDelay2 = nVar.getStartDelay();
                if (startDelay2 > 0) {
                    nVar.setStartDelay(startDelay2 + startDelay);
                } else {
                    nVar.setStartDelay(startDelay);
                }
            }
            nVar.createAnimators(viewGroup, vVar, vVar2, arrayList, arrayList2);
        }
    }

    @Override // androidx.transition.n
    public n excludeTarget(View view, boolean z) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).excludeTarget(view, z);
        }
        return super.excludeTarget(view, z);
    }

    @Override // androidx.transition.n
    void forceToEnd(ViewGroup viewGroup) {
        super.forceToEnd(viewGroup);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).forceToEnd(viewGroup);
        }
    }

    @Override // androidx.transition.n
    public void pause(View view) {
        super.pause(view);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).pause(view);
        }
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public r addListener(n.g gVar) {
        return (r) super.addListener(gVar);
    }

    @Override // androidx.transition.n
    public void resume(View view) {
        super.resume(view);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).resume(view);
        }
    }

    @Override // androidx.transition.n
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
                ((n) it.next()).runAnimators();
            }
            return;
        }
        for (int i = 1; i < this.a.size(); i++) {
            ((n) this.a.get(i - 1)).addListener(new a((n) this.a.get(i)));
        }
        n nVar = (n) this.a.get(0);
        if (nVar != null) {
            nVar.runAnimators();
        }
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public r addTarget(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            ((n) this.a.get(i2)).addTarget(i);
        }
        return (r) super.addTarget(i);
    }

    @Override // androidx.transition.n
    void setCanRemoveViews(boolean z) {
        super.setCanRemoveViews(z);
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).setCanRemoveViews(z);
        }
    }

    @Override // androidx.transition.n
    public void setEpicenterCallback(n.f fVar) {
        super.setEpicenterCallback(fVar);
        this.e |= 8;
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).setEpicenterCallback(fVar);
        }
    }

    @Override // androidx.transition.n
    public void setPathMotion(h hVar) {
        super.setPathMotion(hVar);
        this.e |= 4;
        if (this.a != null) {
            for (int i = 0; i < this.a.size(); i++) {
                ((n) this.a.get(i)).setPathMotion(hVar);
            }
        }
    }

    @Override // androidx.transition.n
    public void setPropagation(q qVar) {
        super.setPropagation(qVar);
        this.e |= 2;
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ((n) this.a.get(i)).setPropagation(qVar);
        }
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public r addTarget(View view) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).addTarget(view);
        }
        return (r) super.addTarget(view);
    }

    @Override // androidx.transition.n
    String toString(String str) {
        String string = super.toString(str);
        for (int i = 0; i < this.a.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append("\n");
            sb.append(((n) this.a.get(i)).toString(str + "  "));
            string = sb.toString();
        }
        return string;
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public r addTarget(Class cls) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).addTarget((Class<?>) cls);
        }
        return (r) super.addTarget((Class<?>) cls);
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public r addTarget(String str) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).addTarget(str);
        }
        return (r) super.addTarget(str);
    }

    public r w(n nVar) {
        x(nVar);
        long j = this.mDuration;
        if (j >= 0) {
            nVar.setDuration(j);
        }
        if ((this.e & 1) != 0) {
            nVar.setInterpolator(getInterpolator());
        }
        if ((this.e & 2) != 0) {
            getPropagation();
            nVar.setPropagation(null);
        }
        if ((this.e & 4) != 0) {
            nVar.setPathMotion(getPathMotion());
        }
        if ((this.e & 8) != 0) {
            nVar.setEpicenterCallback(getEpicenterCallback());
        }
        return this;
    }

    public n y(int i) {
        if (i < 0 || i >= this.a.size()) {
            return null;
        }
        return (n) this.a.get(i);
    }

    public int z() {
        return this.a.size();
    }

    @Override // androidx.transition.n
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public n mo4clone() {
        r rVar = (r) super.mo4clone();
        rVar.a = new ArrayList();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            rVar.x(((n) this.a.get(i)).mo4clone());
        }
        return rVar;
    }

    @Override // androidx.transition.n
    public n excludeTarget(String str, boolean z) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).excludeTarget(str, z);
        }
        return super.excludeTarget(str, z);
    }

    @Override // androidx.transition.n
    public n excludeTarget(int i, boolean z) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            ((n) this.a.get(i2)).excludeTarget(i, z);
        }
        return super.excludeTarget(i, z);
    }

    @Override // androidx.transition.n
    public n excludeTarget(Class cls, boolean z) {
        for (int i = 0; i < this.a.size(); i++) {
            ((n) this.a.get(i)).excludeTarget((Class<?>) cls, z);
        }
        return super.excludeTarget((Class<?>) cls, z);
    }
}
