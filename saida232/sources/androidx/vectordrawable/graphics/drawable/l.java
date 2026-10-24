package androidx.vectordrawable.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class l implements Interpolator {
    private float[] a;
    private float[] b;

    public l(Context context, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        this(context.getResources(), context.getTheme(), attributeSet, xmlPullParser);
    }

    private void a(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f, f2, f3, f4, 1.0f, 1.0f);
        b(path);
    }

    private void b(Path path) {
        int i = 0;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int iMin = Math.min(3000, ((int) (length / 0.002f)) + 1);
        if (iMin <= 0) {
            throw new IllegalArgumentException("The Path has a invalid length " + length);
        }
        this.a = new float[iMin];
        this.b = new float[iMin];
        float[] fArr = new float[2];
        for (int i2 = 0; i2 < iMin; i2++) {
            pathMeasure.getPosTan((i2 * length) / (iMin - 1), fArr, null);
            this.a[i2] = fArr[0];
            this.b[i2] = fArr[1];
        }
        if (Math.abs(this.a[0]) <= 1.0E-5d && Math.abs(this.b[0]) <= 1.0E-5d) {
            int i3 = iMin - 1;
            if (Math.abs(this.a[i3] - 1.0f) <= 1.0E-5d && Math.abs(this.b[i3] - 1.0f) <= 1.0E-5d) {
                float f = 0.0f;
                int i4 = 0;
                while (i < iMin) {
                    float[] fArr2 = this.a;
                    int i5 = i4 + 1;
                    float f2 = fArr2[i4];
                    if (f2 < f) {
                        throw new IllegalArgumentException("The Path cannot loop back on itself, x :" + f2);
                    }
                    fArr2[i] = f2;
                    i++;
                    f = f2;
                    i4 = i5;
                }
                if (pathMeasure.nextContour()) {
                    throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
                }
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The Path must start at (0,0) and end at (1,1) start: ");
        sb.append(this.a[0]);
        sb.append(",");
        sb.append(this.b[0]);
        sb.append(" end:");
        int i6 = iMin - 1;
        sb.append(this.a[i6]);
        sb.append(",");
        sb.append(this.b[i6]);
        throw new IllegalArgumentException(sb.toString());
    }

    private void c(float f, float f2) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f, f2, 1.0f, 1.0f);
        b(path);
    }

    private void d(TypedArray typedArray, XmlPullParser xmlPullParser) {
        if (o.i.j(xmlPullParser, "pathData")) {
            String strI = o.i.i(typedArray, xmlPullParser, "pathData", 4);
            Path pathE = p.d.e(strI);
            if (pathE != null) {
                b(pathE);
                return;
            }
            throw new InflateException("The path is null, which is created from " + strI);
        }
        if (!o.i.j(xmlPullParser, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        }
        if (!o.i.j(xmlPullParser, "controlY1")) {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        }
        float f = o.i.f(typedArray, xmlPullParser, "controlX1", 0, 0.0f);
        float f2 = o.i.f(typedArray, xmlPullParser, "controlY1", 1, 0.0f);
        boolean zJ = o.i.j(xmlPullParser, "controlX2");
        if (zJ != o.i.j(xmlPullParser, "controlY2")) {
            throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
        }
        if (zJ) {
            a(f, f2, o.i.f(typedArray, xmlPullParser, "controlX2", 2, 0.0f), o.i.f(typedArray, xmlPullParser, "controlY2", 3, 0.0f));
        } else {
            c(f, f2);
        }
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int length = this.a.length - 1;
        int i = 0;
        while (length - i > 1) {
            int i2 = (i + length) / 2;
            if (f < this.a[i2]) {
                length = i2;
            } else {
                i = i2;
            }
        }
        float[] fArr = this.a;
        float f2 = fArr[length];
        float f3 = fArr[i];
        float f4 = f2 - f3;
        if (f4 == 0.0f) {
            return this.b[i];
        }
        float f5 = (f - f3) / f4;
        float[] fArr2 = this.b;
        float f6 = fArr2[i];
        return f6 + (f5 * (fArr2[length] - f6));
    }

    public l(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        TypedArray typedArrayK = o.i.k(resources, theme, attributeSet, a.l);
        d(typedArrayK, xmlPullParser);
        typedArrayK.recycle();
    }
}
