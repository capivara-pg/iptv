package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;
import l.j;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public abstract class a extends View {
    protected int[] a;
    protected int b;
    protected Context c;
    protected j d;
    protected boolean e;
    private String f;

    public a(Context context) {
        super(context);
        this.a = new int[32];
        this.e = false;
        this.c = context;
        b(null);
    }

    private void a(String str) {
        int iIntValue;
        Object objC;
        if (str == null || this.c == null) {
            return;
        }
        String strTrim = str.trim();
        try {
            iIntValue = R$id.class.getField(strTrim).getInt(null);
        } catch (Exception unused) {
            iIntValue = 0;
        }
        if (iIntValue == 0) {
            iIntValue = this.c.getResources().getIdentifier(strTrim, "id", this.c.getPackageName());
        }
        if (iIntValue == 0 && isInEditMode() && (getParent() instanceof ConstraintLayout) && (objC = ((ConstraintLayout) getParent()).c(0, strTrim)) != null && (objC instanceof Integer)) {
            iIntValue = ((Integer) objC).intValue();
        }
        if (iIntValue != 0) {
            setTag(iIntValue, null);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Could not find id of \"");
        sb.append(strTrim);
        sb.append("\"");
    }

    private void setIds(String str) {
        if (str == null) {
            return;
        }
        int i = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                a(str.substring(i));
                return;
            } else {
                a(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    protected void b(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.a);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f = string;
                    setIds(string);
                }
            }
        }
    }

    public void c(ConstraintLayout constraintLayout) {
    }

    public void d(ConstraintLayout constraintLayout) {
    }

    public void e(ConstraintLayout constraintLayout) {
        if (isInEditMode()) {
            setIds(this.f);
        }
        j jVar = this.d;
        if (jVar == null) {
            return;
        }
        jVar.J0();
        for (int i = 0; i < this.b; i++) {
            View viewE = constraintLayout.e(this.a[i]);
            if (viewE != null) {
                this.d.I0(constraintLayout.f(viewE));
            }
        }
    }

    public void f() {
        if (this.d == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.a) {
            ((ConstraintLayout.a) layoutParams).l0 = this.d;
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.a, this.b);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.e) {
            super.onMeasure(i, i2);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void setReferencedIds(int[] iArr) {
        this.b = 0;
        for (int i : iArr) {
            setTag(i, null);
        }
    }

    @Override // android.view.View
    public void setTag(int i, Object obj) {
        int i2 = this.b + 1;
        int[] iArr = this.a;
        if (i2 > iArr.length) {
            this.a = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.a;
        int i3 = this.b;
        iArr2[i3] = i;
        this.b = i3 + 1;
    }
}
