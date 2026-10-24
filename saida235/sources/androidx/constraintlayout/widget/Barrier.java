package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends b {
    private int j;
    private int k;
    private p.a l;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    private void n(p.e eVar, int i, boolean z) {
        this.k = i;
        if (z) {
            int i2 = this.j;
            if (i2 == 5) {
                this.k = 1;
            } else if (i2 == 6) {
                this.k = 0;
            }
        } else {
            int i3 = this.j;
            if (i3 == 5) {
                this.k = 0;
            } else if (i3 == 6) {
                this.k = 1;
            }
        }
        if (eVar instanceof p.a) {
            ((p.a) eVar).w1(this.k);
        }
    }

    @Override // androidx.constraintlayout.widget.b
    protected void g(AttributeSet attributeSet) {
        super.g(attributeSet);
        this.l = new p.a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.u);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.l.v1(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.l.x1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.d = this.l;
        m();
    }

    public boolean getAllowsGoneWidget() {
        return this.l.q1();
    }

    public int getMargin() {
        return this.l.s1();
    }

    public int getType() {
        return this.j;
    }

    @Override // androidx.constraintlayout.widget.b
    public void h(p.e eVar, boolean z) {
        n(eVar, this.j, z);
    }

    public void setAllowsGoneWidget(boolean supportGone) {
        this.l.v1(supportGone);
    }

    public void setDpMargin(int margin) {
        this.l.x1((int) ((margin * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int margin) {
        this.l.x1(margin);
    }

    public void setType(int type) {
        this.j = type;
    }

    public Barrier(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setVisibility(8);
    }
}
