package androidx.collection;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class d implements Cloneable {
    private static final Object e = new Object();
    private boolean a;
    private long[] b;
    private Object[] c;
    private int d;

    public d() {
        this(10);
    }

    private void e() {
        int i = this.d;
        long[] jArr = this.b;
        Object[] objArr = this.c;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != e) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.a = false;
        this.d = i2;
    }

    public void a(long j, Object obj) {
        int i = this.d;
        if (i != 0 && j <= this.b[i - 1]) {
            j(j, obj);
            return;
        }
        if (this.a && i >= this.b.length) {
            e();
        }
        int i2 = this.d;
        if (i2 >= this.b.length) {
            int iF = c.f(i2 + 1);
            long[] jArr = new long[iF];
            Object[] objArr = new Object[iF];
            long[] jArr2 = this.b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.b = jArr;
            this.c = objArr;
        }
        this.b[i2] = j;
        this.c[i2] = obj;
        this.d = i2 + 1;
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
    public d clone() {
        try {
            d dVar = (d) super.clone();
            dVar.b = (long[]) this.b.clone();
            dVar.c = (Object[]) this.c.clone();
            return dVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public boolean d(long j) {
        return h(j) >= 0;
    }

    public Object f(long j) {
        return g(j, null);
    }

    public Object g(long j, Object obj) {
        Object obj2;
        int iB = c.b(this.b, this.d, j);
        return (iB < 0 || (obj2 = this.c[iB]) == e) ? obj : obj2;
    }

    public int h(long j) {
        if (this.a) {
            e();
        }
        return c.b(this.b, this.d, j);
    }

    public long i(int i) {
        if (this.a) {
            e();
        }
        return this.b[i];
    }

    public void j(long j, Object obj) {
        int iB = c.b(this.b, this.d, j);
        if (iB >= 0) {
            this.c[iB] = obj;
            return;
        }
        int iB2 = iB ^ (-1);
        int i = this.d;
        if (iB2 < i) {
            Object[] objArr = this.c;
            if (objArr[iB2] == e) {
                this.b[iB2] = j;
                objArr[iB2] = obj;
                return;
            }
        }
        if (this.a && i >= this.b.length) {
            e();
            iB2 = c.b(this.b, this.d, j) ^ (-1);
        }
        int i2 = this.d;
        if (i2 >= this.b.length) {
            int iF = c.f(i2 + 1);
            long[] jArr = new long[iF];
            Object[] objArr2 = new Object[iF];
            long[] jArr2 = this.b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.b = jArr;
            this.c = objArr2;
        }
        int i3 = this.d;
        if (i3 - iB2 != 0) {
            long[] jArr3 = this.b;
            int i4 = iB2 + 1;
            System.arraycopy(jArr3, iB2, jArr3, i4, i3 - iB2);
            Object[] objArr4 = this.c;
            System.arraycopy(objArr4, iB2, objArr4, i4, this.d - iB2);
        }
        this.b[iB2] = j;
        this.c[iB2] = obj;
        this.d++;
    }

    public void k(long j) {
        int iB = c.b(this.b, this.d, j);
        if (iB >= 0) {
            Object[] objArr = this.c;
            Object obj = objArr[iB];
            Object obj2 = e;
            if (obj != obj2) {
                objArr[iB] = obj2;
                this.a = true;
            }
        }
    }

    public void l(int i) {
        Object[] objArr = this.c;
        Object obj = objArr[i];
        Object obj2 = e;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.a = true;
        }
    }

    public int m() {
        if (this.a) {
            e();
        }
        return this.d;
    }

    public Object n(int i) {
        if (this.a) {
            e();
        }
        return this.c[i];
    }

    public String toString() {
        if (m() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.d * 28);
        sb.append('{');
        for (int i = 0; i < this.d; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(i(i));
            sb.append('=');
            Object objN = n(i);
            if (objN != this) {
                sb.append(objN);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public d(int i) {
        this.a = false;
        if (i == 0) {
            this.b = c.b;
            this.c = c.c;
        } else {
            int iF = c.f(i);
            this.b = new long[iF];
            this.c = new Object[iF];
        }
    }
}
