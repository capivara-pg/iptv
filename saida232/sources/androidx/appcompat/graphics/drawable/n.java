package androidx.appcompat.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;

/* JADX INFO: loaded from: classes.dex */
abstract class n extends h {
    private a m;
    private boolean n;

    static class a extends h.c {
        int[][] J;

        a(a aVar, n nVar, Resources resources) {
            super(aVar, nVar, resources);
            if (aVar != null) {
                this.J = aVar.J;
            } else {
                this.J = new int[f()][];
            }
        }

        int A(int[] iArr) {
            int[][] iArr2 = this.J;
            int iH = h();
            for (int i = 0; i < iH; i++) {
                if (StateSet.stateSetMatches(iArr2[i], iArr)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // androidx.appcompat.graphics.drawable.h.c
        public void o(int i, int i2) {
            super.o(i, i2);
            int[][] iArr = new int[i2][];
            System.arraycopy(this.J, 0, iArr, 0, i);
            this.J = iArr;
        }

        @Override // androidx.appcompat.graphics.drawable.h.c
        abstract void r();

        int z(int[] iArr, Drawable drawable) {
            int iA = a(drawable);
            this.J[iA] = iArr;
            return iA;
        }
    }

    n(a aVar) {
        if (aVar != null) {
            h(aVar);
        }
    }

    @Override // androidx.appcompat.graphics.drawable.h, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    @Override // androidx.appcompat.graphics.drawable.h
    void h(h.c cVar) {
        super.h(cVar);
        if (cVar instanceof a) {
            this.m = (a) cVar;
        }
    }

    int[] j(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i = 0;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i2);
            if (attributeNameResource != 0 && attributeNameResource != 16842960 && attributeNameResource != 16843161) {
                int i3 = i + 1;
                if (!attributeSet.getAttributeBooleanValue(i2, false)) {
                    attributeNameResource = -attributeNameResource;
                }
                iArr[i] = attributeNameResource;
                i = i3;
            }
        }
        return StateSet.trimStateSet(iArr, i);
    }

    @Override // androidx.appcompat.graphics.drawable.h, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.n && super.mutate() == this) {
            this.m.r();
            this.n = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected abstract boolean onStateChange(int[] iArr);
}
