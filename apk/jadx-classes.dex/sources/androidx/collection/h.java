package androidx.collection;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class h implements Cloneable {
    private static final Object e = new Object();
    private boolean a;
    private int[] b;
    private Object[] c;
    private int d;

    public h() {
        this(10);
    }

    private void d() {
        int i = this.d;
        int[] iArr = this.b;
        Object[] objArr = this.c;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != e) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.a = false;
        this.d = i2;
    }

    public void a(int i, Object obj) {
        int i2 = this.d;
        if (i2 != 0 && i <= this.b[i2 - 1]) {
            i(i, obj);
            return;
        }
        if (this.a && i2 >= this.b.length) {
            d();
        }
        int i3 = this.d;
        if (i3 >= this.b.length) {
            int iE = c.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr = new Object[iE];
            int[] iArr2 = this.b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.b = iArr;
            this.c = objArr;
        }
        this.b[i3] = i;
        this.c[i3] = obj;
        this.d = i3 + 1;
    }

    public void b() {
        int i = this.d;
        Object[] objArr = this.c;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.d = 0;
        this.a = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public h clone() {
        try {
            h hVar = (h) super.clone();
            hVar.b = (int[]) this.b.clone();
            hVar.c = (Object[]) this.c.clone();
            return hVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public Object e(int i) {
        return f(i, null);
    }

    public Object f(int i, Object obj) {
        Object obj2;
        int iA = c.a(this.b, this.d, i);
        return (iA < 0 || (obj2 = this.c[iA]) == e) ? obj : obj2;
    }

    public int g(Object obj) {
        if (this.a) {
            d();
        }
        for (int i = 0; i < this.d; i++) {
            if (this.c[i] == obj) {
                return i;
            }
        }
        return -1;
    }

    public int h(int i) {
        if (this.a) {
            d();
        }
        return this.b[i];
    }

    public void i(int i, Object obj) {
        int iA = c.a(this.b, this.d, i);
        if (iA >= 0) {
            this.c[iA] = obj;
            return;
        }
        int iA2 = iA ^ (-1);
        int i2 = this.d;
        if (iA2 < i2) {
            Object[] objArr = this.c;
            if (objArr[iA2] == e) {
                this.b[iA2] = i;
                objArr[iA2] = obj;
                return;
            }
        }
        if (this.a && i2 >= this.b.length) {
            d();
            iA2 = c.a(this.b, this.d, i) ^ (-1);
        }
        int i3 = this.d;
        if (i3 >= this.b.length) {
            int iE = c.e(i3 + 1);
            int[] iArr = new int[iE];
            Object[] objArr2 = new Object[iE];
            int[] iArr2 = this.b;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.b = iArr;
            this.c = objArr2;
        }
        int i4 = this.d;
        if (i4 - iA2 != 0) {
            int[] iArr3 = this.b;
            int i5 = iA2 + 1;
            System.arraycopy(iArr3, iA2, iArr3, i5, i4 - iA2);
            Object[] objArr4 = this.c;
            System.arraycopy(objArr4, iA2, objArr4, i5, this.d - iA2);
        }
        this.b[iA2] = i;
        this.c[iA2] = obj;
        this.d++;
    }

    public int j() {
        if (this.a) {
            d();
        }
        return this.d;
    }

    public Object k(int i) {
        if (this.a) {
            d();
        }
        return this.c[i];
    }

    public String toString() {
        if (j() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.d * 28);
        sb.append('{');
        for (int i = 0; i < this.d; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(h(i));
            sb.append('=');
            Object objK = k(i);
            if (objK != this) {
                sb.append(objK);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public h(int i) {
        this.a = false;
        if (i == 0) {
            this.b = c.a;
            this.c = c.c;
        } else {
            int iE = c.e(i);
            this.b = new int[iE];
            this.c = new Object[iE];
        }
    }
}
