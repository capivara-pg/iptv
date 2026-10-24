package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;
import java.util.HashMap;
import p.h;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends View {
    protected int[] a;
    protected int b;
    protected Context c;
    protected h d;
    protected boolean e;
    protected String f;
    protected String g;
    private View[] h;
    protected HashMap i;

    public b(Context context) {
        super(context);
        this.a = new int[32];
        this.e = false;
        this.h = null;
        this.i = new HashMap();
        this.c = context;
        g(null);
    }

    private void a(String str) {
        if (str == null || str.length() == 0 || this.c == null) {
            return;
        }
        String strTrim = str.trim();
        if (getParent() instanceof ConstraintLayout) {
        }
        int iF = f(strTrim);
        if (iF != 0) {
            this.i.put(Integer.valueOf(iF), strTrim);
            b(iF);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not find id of \"");
            sb.append(strTrim);
            sb.append("\"");
        }
    }

    private void b(int i) {
        if (i == getId()) {
            return;
        }
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

    private void c(String str) {
        if (str == null || str.length() == 0 || this.c == null) {
            return;
        }
        String strTrim = str.trim();
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        if (constraintLayout == null) {
            return;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof ConstraintLayout.b) && strTrim.equals(((ConstraintLayout.b) layoutParams).c0)) {
                if (childAt.getId() == -1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("to use ConstraintTag view ");
                    sb.append(childAt.getClass().getSimpleName());
                    sb.append(" must have an ID");
                } else {
                    b(childAt.getId());
                }
            }
        }
    }

    private int e(ConstraintLayout constraintLayout, String str) {
        Resources resources;
        String resourceEntryName;
        if (str == null || constraintLayout == null || (resources = this.c.getResources()) == null) {
            return 0;
        }
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            if (childAt.getId() != -1) {
                try {
                    resourceEntryName = resources.getResourceEntryName(childAt.getId());
                } catch (Resources.NotFoundException unused) {
                    resourceEntryName = null;
                }
                if (str.equals(resourceEntryName)) {
                    return childAt.getId();
                }
            }
        }
        return 0;
    }

    private int f(String str) {
        ConstraintLayout constraintLayout = getParent() instanceof ConstraintLayout ? (ConstraintLayout) getParent() : null;
        int iE = 0;
        if (isInEditMode() && constraintLayout != null) {
            Object objF = constraintLayout.f(0, str);
            if (objF instanceof Integer) {
                iE = ((Integer) objF).intValue();
            }
        }
        if (iE == 0 && constraintLayout != null) {
            iE = e(constraintLayout, str);
        }
        if (iE == 0) {
            try {
                iE = R$id.class.getField(str).getInt(null);
            } catch (Exception unused) {
            }
        }
        return iE == 0 ? this.c.getResources().getIdentifier(str, "id", this.c.getPackageName()) : iE;
    }

    protected void d(ConstraintLayout constraintLayout) {
    }

    protected void g(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.u);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f = string;
                    setIds(string);
                } else if (index == R$styleable.ConstraintLayout_Layout_constraint_referenced_tags) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.g = string2;
                    setReferenceTags(string2);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.a, this.b);
    }

    public abstract void h(p.e eVar, boolean z);

    public void i(ConstraintLayout constraintLayout) {
    }

    public void j(ConstraintLayout constraintLayout) {
    }

    public void k(ConstraintLayout constraintLayout) {
    }

    public void l(ConstraintLayout constraintLayout) {
        String str;
        int iE;
        if (isInEditMode()) {
            setIds(this.f);
        }
        h hVar = this.d;
        if (hVar == null) {
            return;
        }
        hVar.b();
        for (int i = 0; i < this.b; i++) {
            int i2 = this.a[i];
            View viewH = constraintLayout.h(i2);
            if (viewH == null && (iE = e(constraintLayout, (str = (String) this.i.get(Integer.valueOf(i2))))) != 0) {
                this.a[i] = iE;
                this.i.put(Integer.valueOf(iE), str);
                viewH = constraintLayout.h(iE);
            }
            if (viewH != null) {
                this.d.a(constraintLayout.i(viewH));
            }
        }
        this.d.c(constraintLayout.c);
    }

    public void m() {
        if (this.d == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            ((ConstraintLayout.b) layoutParams).v0 = this.d;
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.f;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.g;
        if (str2 != null) {
            setReferenceTags(str2);
        }
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

    protected void setIds(String idList) {
        this.f = idList;
        if (idList == null) {
            return;
        }
        int i = 0;
        this.b = 0;
        while (true) {
            int iIndexOf = idList.indexOf(44, i);
            if (iIndexOf == -1) {
                a(idList.substring(i));
                return;
            } else {
                a(idList.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    protected void setReferenceTags(String tagList) {
        this.g = tagList;
        if (tagList == null) {
            return;
        }
        int i = 0;
        this.b = 0;
        while (true) {
            int iIndexOf = tagList.indexOf(44, i);
            if (iIndexOf == -1) {
                c(tagList.substring(i));
                return;
            } else {
                c(tagList.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    public void setReferencedIds(int[] ids) {
        this.f = null;
        this.b = 0;
        for (int i : ids) {
            b(i);
        }
    }

    @Override // android.view.View
    public void setTag(int i, Object obj) {
        super.setTag(i, obj);
        if (obj == null && this.f == null) {
            b(i);
        }
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new int[32];
        this.e = false;
        this.h = null;
        this.i = new HashMap();
        this.c = context;
        g(attributeSet);
    }
}
