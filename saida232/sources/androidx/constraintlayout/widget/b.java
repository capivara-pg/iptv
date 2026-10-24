package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private static final int[] b = {0, 4, 8};
    private static SparseIntArray c;
    private HashMap a = new HashMap();

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.b$b, reason: collision with other inner class name */
    private static class C0020b {
        public int A;
        public int B;
        public int C;
        public int D;
        public int E;
        public int F;
        public int G;
        public int H;
        public int I;
        public int J;
        public int K;
        public int L;
        public int M;
        public int N;
        public int O;
        public int P;
        public float Q;
        public float R;
        public int S;
        public int T;
        public float U;
        public boolean V;
        public float W;
        public float X;
        public float Y;
        public float Z;
        boolean a;
        public float a0;
        public int b;
        public float b0;
        public int c;
        public float c0;
        int d;
        public float d0;
        public int e;
        public float e0;
        public int f;
        public float f0;
        public float g;
        public float g0;
        public int h;
        public boolean h0;
        public int i;
        public boolean i0;
        public int j;
        public int j0;
        public int k;
        public int k0;
        public int l;
        public int l0;
        public int m;
        public int m0;
        public int n;
        public int n0;
        public int o;
        public int o0;
        public int p;
        public float p0;
        public int q;
        public float q0;
        public int r;
        public boolean r0;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f21s;
        public int s0;
        public int t;
        public int t0;
        public float u;
        public int[] u0;
        public float v;
        public String v0;
        public String w;
        public int x;
        public int y;
        public float z;

        private C0020b() {
            this.a = false;
            this.e = -1;
            this.f = -1;
            this.g = -1.0f;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.f21s = -1;
            this.t = -1;
            this.u = 0.5f;
            this.v = 0.5f;
            this.w = null;
            this.x = -1;
            this.y = 0;
            this.z = 0.0f;
            this.A = -1;
            this.B = -1;
            this.C = -1;
            this.D = -1;
            this.E = -1;
            this.F = -1;
            this.G = -1;
            this.H = -1;
            this.I = -1;
            this.J = 0;
            this.K = -1;
            this.L = -1;
            this.M = -1;
            this.N = -1;
            this.O = -1;
            this.P = -1;
            this.Q = 0.0f;
            this.R = 0.0f;
            this.S = 0;
            this.T = 0;
            this.U = 1.0f;
            this.V = false;
            this.W = 0.0f;
            this.X = 0.0f;
            this.Y = 0.0f;
            this.Z = 0.0f;
            this.a0 = 1.0f;
            this.b0 = 1.0f;
            this.c0 = Float.NaN;
            this.d0 = Float.NaN;
            this.e0 = 0.0f;
            this.f0 = 0.0f;
            this.g0 = 0.0f;
            this.h0 = false;
            this.i0 = false;
            this.j0 = 0;
            this.k0 = 0;
            this.l0 = -1;
            this.m0 = -1;
            this.n0 = -1;
            this.o0 = -1;
            this.p0 = 1.0f;
            this.q0 = 1.0f;
            this.r0 = false;
            this.s0 = -1;
            this.t0 = -1;
        }

        public void a(ConstraintLayout.a aVar) {
            aVar.d = this.h;
            aVar.e = this.i;
            aVar.f = this.j;
            aVar.g = this.k;
            aVar.h = this.l;
            aVar.i = this.m;
            aVar.j = this.n;
            aVar.k = this.o;
            aVar.l = this.p;
            aVar.p = this.q;
            aVar.q = this.r;
            aVar.r = this.f21s;
            aVar.f20s = this.t;
            ((ViewGroup.MarginLayoutParams) aVar).leftMargin = this.D;
            ((ViewGroup.MarginLayoutParams) aVar).rightMargin = this.E;
            ((ViewGroup.MarginLayoutParams) aVar).topMargin = this.F;
            ((ViewGroup.MarginLayoutParams) aVar).bottomMargin = this.G;
            aVar.x = this.P;
            aVar.y = this.O;
            aVar.z = this.u;
            aVar.A = this.v;
            aVar.m = this.x;
            aVar.n = this.y;
            aVar.o = this.z;
            aVar.B = this.w;
            aVar.Q = this.A;
            aVar.R = this.B;
            aVar.F = this.Q;
            aVar.E = this.R;
            aVar.H = this.T;
            aVar.G = this.S;
            aVar.T = this.h0;
            aVar.U = this.i0;
            aVar.I = this.j0;
            aVar.J = this.k0;
            aVar.M = this.l0;
            aVar.N = this.m0;
            aVar.K = this.n0;
            aVar.L = this.o0;
            aVar.O = this.p0;
            aVar.P = this.q0;
            aVar.S = this.C;
            aVar.c = this.g;
            aVar.a = this.e;
            aVar.b = this.f;
            ((ViewGroup.MarginLayoutParams) aVar).width = this.b;
            ((ViewGroup.MarginLayoutParams) aVar).height = this.c;
            aVar.setMarginStart(this.I);
            aVar.setMarginEnd(this.H);
            aVar.a();
        }

        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public C0020b clone() {
            C0020b c0020b = new C0020b();
            c0020b.a = this.a;
            c0020b.b = this.b;
            c0020b.c = this.c;
            c0020b.e = this.e;
            c0020b.f = this.f;
            c0020b.g = this.g;
            c0020b.h = this.h;
            c0020b.i = this.i;
            c0020b.j = this.j;
            c0020b.k = this.k;
            c0020b.l = this.l;
            c0020b.m = this.m;
            c0020b.n = this.n;
            c0020b.o = this.o;
            c0020b.p = this.p;
            c0020b.q = this.q;
            c0020b.r = this.r;
            c0020b.f21s = this.f21s;
            c0020b.t = this.t;
            c0020b.u = this.u;
            c0020b.v = this.v;
            c0020b.w = this.w;
            c0020b.A = this.A;
            c0020b.B = this.B;
            c0020b.u = this.u;
            c0020b.u = this.u;
            c0020b.u = this.u;
            c0020b.u = this.u;
            c0020b.u = this.u;
            c0020b.C = this.C;
            c0020b.D = this.D;
            c0020b.E = this.E;
            c0020b.F = this.F;
            c0020b.G = this.G;
            c0020b.H = this.H;
            c0020b.I = this.I;
            c0020b.J = this.J;
            c0020b.K = this.K;
            c0020b.L = this.L;
            c0020b.M = this.M;
            c0020b.N = this.N;
            c0020b.O = this.O;
            c0020b.P = this.P;
            c0020b.Q = this.Q;
            c0020b.R = this.R;
            c0020b.S = this.S;
            c0020b.T = this.T;
            c0020b.U = this.U;
            c0020b.V = this.V;
            c0020b.W = this.W;
            c0020b.X = this.X;
            c0020b.Y = this.Y;
            c0020b.Z = this.Z;
            c0020b.a0 = this.a0;
            c0020b.b0 = this.b0;
            c0020b.c0 = this.c0;
            c0020b.d0 = this.d0;
            c0020b.e0 = this.e0;
            c0020b.f0 = this.f0;
            c0020b.g0 = this.g0;
            c0020b.h0 = this.h0;
            c0020b.i0 = this.i0;
            c0020b.j0 = this.j0;
            c0020b.k0 = this.k0;
            c0020b.l0 = this.l0;
            c0020b.m0 = this.m0;
            c0020b.n0 = this.n0;
            c0020b.o0 = this.o0;
            c0020b.p0 = this.p0;
            c0020b.q0 = this.q0;
            c0020b.s0 = this.s0;
            c0020b.t0 = this.t0;
            int[] iArr = this.u0;
            if (iArr != null) {
                c0020b.u0 = Arrays.copyOf(iArr, iArr.length);
            }
            c0020b.x = this.x;
            c0020b.y = this.y;
            c0020b.z = this.z;
            c0020b.r0 = this.r0;
            return c0020b;
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        c = sparseIntArray;
        sparseIntArray.append(R$styleable.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
        c.append(R$styleable.ConstraintSet_layout_constraintLeft_toRightOf, 26);
        c.append(R$styleable.ConstraintSet_layout_constraintRight_toLeftOf, 29);
        c.append(R$styleable.ConstraintSet_layout_constraintRight_toRightOf, 30);
        c.append(R$styleable.ConstraintSet_layout_constraintTop_toTopOf, 36);
        c.append(R$styleable.ConstraintSet_layout_constraintTop_toBottomOf, 35);
        c.append(R$styleable.ConstraintSet_layout_constraintBottom_toTopOf, 4);
        c.append(R$styleable.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
        c.append(R$styleable.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
        c.append(R$styleable.ConstraintSet_layout_editor_absoluteX, 6);
        c.append(R$styleable.ConstraintSet_layout_editor_absoluteY, 7);
        c.append(R$styleable.ConstraintSet_layout_constraintGuide_begin, 17);
        c.append(R$styleable.ConstraintSet_layout_constraintGuide_end, 18);
        c.append(R$styleable.ConstraintSet_layout_constraintGuide_percent, 19);
        c.append(R$styleable.ConstraintSet_android_orientation, 27);
        c.append(R$styleable.ConstraintSet_layout_constraintStart_toEndOf, 32);
        c.append(R$styleable.ConstraintSet_layout_constraintStart_toStartOf, 33);
        c.append(R$styleable.ConstraintSet_layout_constraintEnd_toStartOf, 10);
        c.append(R$styleable.ConstraintSet_layout_constraintEnd_toEndOf, 9);
        c.append(R$styleable.ConstraintSet_layout_goneMarginLeft, 13);
        c.append(R$styleable.ConstraintSet_layout_goneMarginTop, 16);
        c.append(R$styleable.ConstraintSet_layout_goneMarginRight, 14);
        c.append(R$styleable.ConstraintSet_layout_goneMarginBottom, 11);
        c.append(R$styleable.ConstraintSet_layout_goneMarginStart, 15);
        c.append(R$styleable.ConstraintSet_layout_goneMarginEnd, 12);
        c.append(R$styleable.ConstraintSet_layout_constraintVertical_weight, 40);
        c.append(R$styleable.ConstraintSet_layout_constraintHorizontal_weight, 39);
        c.append(R$styleable.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
        c.append(R$styleable.ConstraintSet_layout_constraintVertical_chainStyle, 42);
        c.append(R$styleable.ConstraintSet_layout_constraintHorizontal_bias, 20);
        c.append(R$styleable.ConstraintSet_layout_constraintVertical_bias, 37);
        c.append(R$styleable.ConstraintSet_layout_constraintDimensionRatio, 5);
        c.append(R$styleable.ConstraintSet_layout_constraintLeft_creator, 75);
        c.append(R$styleable.ConstraintSet_layout_constraintTop_creator, 75);
        c.append(R$styleable.ConstraintSet_layout_constraintRight_creator, 75);
        c.append(R$styleable.ConstraintSet_layout_constraintBottom_creator, 75);
        c.append(R$styleable.ConstraintSet_layout_constraintBaseline_creator, 75);
        c.append(R$styleable.ConstraintSet_android_layout_marginLeft, 24);
        c.append(R$styleable.ConstraintSet_android_layout_marginRight, 28);
        c.append(R$styleable.ConstraintSet_android_layout_marginStart, 31);
        c.append(R$styleable.ConstraintSet_android_layout_marginEnd, 8);
        c.append(R$styleable.ConstraintSet_android_layout_marginTop, 34);
        c.append(R$styleable.ConstraintSet_android_layout_marginBottom, 2);
        c.append(R$styleable.ConstraintSet_android_layout_width, 23);
        c.append(R$styleable.ConstraintSet_android_layout_height, 21);
        c.append(R$styleable.ConstraintSet_android_visibility, 22);
        c.append(R$styleable.ConstraintSet_android_alpha, 43);
        c.append(R$styleable.ConstraintSet_android_elevation, 44);
        c.append(R$styleable.ConstraintSet_android_rotationX, 45);
        c.append(R$styleable.ConstraintSet_android_rotationY, 46);
        c.append(R$styleable.ConstraintSet_android_rotation, 60);
        c.append(R$styleable.ConstraintSet_android_scaleX, 47);
        c.append(R$styleable.ConstraintSet_android_scaleY, 48);
        c.append(R$styleable.ConstraintSet_android_transformPivotX, 49);
        c.append(R$styleable.ConstraintSet_android_transformPivotY, 50);
        c.append(R$styleable.ConstraintSet_android_translationX, 51);
        c.append(R$styleable.ConstraintSet_android_translationY, 52);
        c.append(R$styleable.ConstraintSet_android_translationZ, 53);
        c.append(R$styleable.ConstraintSet_layout_constraintWidth_default, 54);
        c.append(R$styleable.ConstraintSet_layout_constraintHeight_default, 55);
        c.append(R$styleable.ConstraintSet_layout_constraintWidth_max, 56);
        c.append(R$styleable.ConstraintSet_layout_constraintHeight_max, 57);
        c.append(R$styleable.ConstraintSet_layout_constraintWidth_min, 58);
        c.append(R$styleable.ConstraintSet_layout_constraintHeight_min, 59);
        c.append(R$styleable.ConstraintSet_layout_constraintCircle, 61);
        c.append(R$styleable.ConstraintSet_layout_constraintCircleRadius, 62);
        c.append(R$styleable.ConstraintSet_layout_constraintCircleAngle, 63);
        c.append(R$styleable.ConstraintSet_android_id, 38);
        c.append(R$styleable.ConstraintSet_layout_constraintWidth_percent, 69);
        c.append(R$styleable.ConstraintSet_layout_constraintHeight_percent, 70);
        c.append(R$styleable.ConstraintSet_chainUseRtl, 71);
        c.append(R$styleable.ConstraintSet_barrierDirection, 72);
        c.append(R$styleable.ConstraintSet_constraint_referenced_ids, 73);
        c.append(R$styleable.ConstraintSet_barrierAllowsGoneWidgets, 74);
    }

    private int[] b(View view, String str) {
        int iIntValue;
        Object objC;
        String[] strArrSplit = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i = 0;
        int i2 = 0;
        while (i < strArrSplit.length) {
            String strTrim = strArrSplit[i].trim();
            try {
                iIntValue = R$id.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (objC = ((ConstraintLayout) view.getParent()).c(0, strTrim)) != null && (objC instanceof Integer)) {
                iIntValue = ((Integer) objC).intValue();
            }
            iArr[i2] = iIntValue;
            i++;
            i2++;
        }
        return i2 != strArrSplit.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    private C0020b c(Context context, AttributeSet attributeSet) {
        C0020b c0020b = new C0020b();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.c);
        f(c0020b, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return c0020b;
    }

    private static int e(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    private void f(C0020b c0020b, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            int i2 = c.get(index);
            switch (i2) {
                case 1:
                    c0020b.p = e(typedArray, index, c0020b.p);
                    break;
                case 2:
                    c0020b.G = typedArray.getDimensionPixelSize(index, c0020b.G);
                    break;
                case 3:
                    c0020b.o = e(typedArray, index, c0020b.o);
                    break;
                case 4:
                    c0020b.n = e(typedArray, index, c0020b.n);
                    break;
                case 5:
                    c0020b.w = typedArray.getString(index);
                    break;
                case 6:
                    c0020b.A = typedArray.getDimensionPixelOffset(index, c0020b.A);
                    break;
                case 7:
                    c0020b.B = typedArray.getDimensionPixelOffset(index, c0020b.B);
                    break;
                case 8:
                    c0020b.H = typedArray.getDimensionPixelSize(index, c0020b.H);
                    break;
                case 9:
                    c0020b.t = e(typedArray, index, c0020b.t);
                    break;
                case 10:
                    c0020b.f21s = e(typedArray, index, c0020b.f21s);
                    break;
                case 11:
                    c0020b.N = typedArray.getDimensionPixelSize(index, c0020b.N);
                    break;
                case 12:
                    c0020b.O = typedArray.getDimensionPixelSize(index, c0020b.O);
                    break;
                case 13:
                    c0020b.K = typedArray.getDimensionPixelSize(index, c0020b.K);
                    break;
                case 14:
                    c0020b.M = typedArray.getDimensionPixelSize(index, c0020b.M);
                    break;
                case 15:
                    c0020b.P = typedArray.getDimensionPixelSize(index, c0020b.P);
                    break;
                case 16:
                    c0020b.L = typedArray.getDimensionPixelSize(index, c0020b.L);
                    break;
                case 17:
                    c0020b.e = typedArray.getDimensionPixelOffset(index, c0020b.e);
                    break;
                case 18:
                    c0020b.f = typedArray.getDimensionPixelOffset(index, c0020b.f);
                    break;
                case 19:
                    c0020b.g = typedArray.getFloat(index, c0020b.g);
                    break;
                case 20:
                    c0020b.u = typedArray.getFloat(index, c0020b.u);
                    break;
                case 21:
                    c0020b.c = typedArray.getLayoutDimension(index, c0020b.c);
                    break;
                case 22:
                    c0020b.J = b[typedArray.getInt(index, c0020b.J)];
                    break;
                case 23:
                    c0020b.b = typedArray.getLayoutDimension(index, c0020b.b);
                    break;
                case 24:
                    c0020b.D = typedArray.getDimensionPixelSize(index, c0020b.D);
                    break;
                case 25:
                    c0020b.h = e(typedArray, index, c0020b.h);
                    break;
                case 26:
                    c0020b.i = e(typedArray, index, c0020b.i);
                    break;
                case 27:
                    c0020b.C = typedArray.getInt(index, c0020b.C);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_actionModeStyle /* 28 */:
                    c0020b.E = typedArray.getDimensionPixelSize(index, c0020b.E);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_actionModeWebSearchDrawable /* 29 */:
                    c0020b.j = e(typedArray, index, c0020b.j);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_actionOverflowButtonStyle /* 30 */:
                    c0020b.k = e(typedArray, index, c0020b.k);
                    break;
                case 31:
                    c0020b.I = typedArray.getDimensionPixelSize(index, c0020b.I);
                    break;
                case 32:
                    c0020b.q = e(typedArray, index, c0020b.q);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_alertDialogButtonGroupStyle /* 33 */:
                    c0020b.r = e(typedArray, index, c0020b.r);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_alertDialogCenterButtons /* 34 */:
                    c0020b.F = typedArray.getDimensionPixelSize(index, c0020b.F);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_alertDialogStyle /* 35 */:
                    c0020b.m = e(typedArray, index, c0020b.m);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_alertDialogTheme /* 36 */:
                    c0020b.l = e(typedArray, index, c0020b.l);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_autoCompleteTextViewStyle /* 37 */:
                    c0020b.v = typedArray.getFloat(index, c0020b.v);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_borderlessButtonStyle /* 38 */:
                    c0020b.d = typedArray.getResourceId(index, c0020b.d);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_buttonBarButtonStyle /* 39 */:
                    c0020b.R = typedArray.getFloat(index, c0020b.R);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_buttonBarNegativeButtonStyle /* 40 */:
                    c0020b.Q = typedArray.getFloat(index, c0020b.Q);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_buttonBarNeutralButtonStyle /* 41 */:
                    c0020b.S = typedArray.getInt(index, c0020b.S);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_buttonBarPositiveButtonStyle /* 42 */:
                    c0020b.T = typedArray.getInt(index, c0020b.T);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_buttonBarStyle /* 43 */:
                    c0020b.U = typedArray.getFloat(index, c0020b.U);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_buttonStyle /* 44 */:
                    c0020b.V = true;
                    c0020b.W = typedArray.getDimension(index, c0020b.W);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_buttonStyleSmall /* 45 */:
                    c0020b.Y = typedArray.getFloat(index, c0020b.Y);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_checkboxStyle /* 46 */:
                    c0020b.Z = typedArray.getFloat(index, c0020b.Z);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_checkedTextViewStyle /* 47 */:
                    c0020b.a0 = typedArray.getFloat(index, c0020b.a0);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_colorAccent /* 48 */:
                    c0020b.b0 = typedArray.getFloat(index, c0020b.b0);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_colorBackgroundFloating /* 49 */:
                    c0020b.c0 = typedArray.getFloat(index, c0020b.c0);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_colorButtonNormal /* 50 */:
                    c0020b.d0 = typedArray.getFloat(index, c0020b.d0);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_colorControlActivated /* 51 */:
                    c0020b.e0 = typedArray.getDimension(index, c0020b.e0);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_colorControlHighlight /* 52 */:
                    c0020b.f0 = typedArray.getDimension(index, c0020b.f0);
                    break;
                case androidx.appcompat.R$styleable.AppCompatTheme_colorControlNormal /* 53 */:
                    c0020b.g0 = typedArray.getDimension(index, c0020b.g0);
                    break;
                default:
                    switch (i2) {
                        case androidx.appcompat.R$styleable.AppCompatTheme_dialogPreferredPadding /* 60 */:
                            c0020b.X = typedArray.getFloat(index, c0020b.X);
                            break;
                        case androidx.appcompat.R$styleable.AppCompatTheme_dialogTheme /* 61 */:
                            c0020b.x = e(typedArray, index, c0020b.x);
                            break;
                        case androidx.appcompat.R$styleable.AppCompatTheme_dividerHorizontal /* 62 */:
                            c0020b.y = typedArray.getDimensionPixelSize(index, c0020b.y);
                            break;
                        case androidx.appcompat.R$styleable.AppCompatTheme_dividerVertical /* 63 */:
                            c0020b.z = typedArray.getFloat(index, c0020b.z);
                            break;
                        default:
                            switch (i2) {
                                case androidx.appcompat.R$styleable.AppCompatTheme_homeAsUpIndicator /* 69 */:
                                    c0020b.p0 = typedArray.getFloat(index, 1.0f);
                                    break;
                                case androidx.appcompat.R$styleable.AppCompatTheme_imageButtonStyle /* 70 */:
                                    c0020b.q0 = typedArray.getFloat(index, 1.0f);
                                    break;
                                case androidx.appcompat.R$styleable.AppCompatTheme_listChoiceBackgroundIndicator /* 71 */:
                                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                    break;
                                case androidx.appcompat.R$styleable.AppCompatTheme_listChoiceIndicatorMultipleAnimated /* 72 */:
                                    c0020b.s0 = typedArray.getInt(index, c0020b.s0);
                                    break;
                                case androidx.appcompat.R$styleable.AppCompatTheme_listChoiceIndicatorSingleAnimated /* 73 */:
                                    c0020b.v0 = typedArray.getString(index);
                                    break;
                                case androidx.appcompat.R$styleable.AppCompatTheme_listDividerAlertDialog /* 74 */:
                                    c0020b.r0 = typedArray.getBoolean(index, c0020b.r0);
                                    break;
                                case androidx.appcompat.R$styleable.AppCompatTheme_listMenuViewStyle /* 75 */:
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("unused attribute 0x");
                                    sb.append(Integer.toHexString(index));
                                    sb.append("   ");
                                    sb.append(c.get(index));
                                    break;
                                default:
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("Unknown attribute 0x");
                                    sb2.append(Integer.toHexString(index));
                                    sb2.append("   ");
                                    sb2.append(c.get(index));
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
    }

    void a(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.a.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (this.a.containsKey(Integer.valueOf(id))) {
                hashSet.remove(Integer.valueOf(id));
                C0020b c0020b = (C0020b) this.a.get(Integer.valueOf(id));
                if (childAt instanceof m.a) {
                    c0020b.t0 = 1;
                }
                int i2 = c0020b.t0;
                if (i2 != -1 && i2 == 1) {
                    m.a aVar = (m.a) childAt;
                    aVar.setId(id);
                    aVar.setType(c0020b.s0);
                    aVar.setAllowsGoneWidget(c0020b.r0);
                    int[] iArr = c0020b.u0;
                    if (iArr != null) {
                        aVar.setReferencedIds(iArr);
                    } else {
                        String str = c0020b.v0;
                        if (str != null) {
                            int[] iArrB = b(aVar, str);
                            c0020b.u0 = iArrB;
                            aVar.setReferencedIds(iArrB);
                        }
                    }
                }
                ConstraintLayout.a aVar2 = (ConstraintLayout.a) childAt.getLayoutParams();
                c0020b.a(aVar2);
                childAt.setLayoutParams(aVar2);
                childAt.setVisibility(c0020b.J);
                int i3 = Build.VERSION.SDK_INT;
                childAt.setAlpha(c0020b.U);
                childAt.setRotation(c0020b.X);
                childAt.setRotationX(c0020b.Y);
                childAt.setRotationY(c0020b.Z);
                childAt.setScaleX(c0020b.a0);
                childAt.setScaleY(c0020b.b0);
                if (!Float.isNaN(c0020b.c0)) {
                    childAt.setPivotX(c0020b.c0);
                }
                if (!Float.isNaN(c0020b.d0)) {
                    childAt.setPivotY(c0020b.d0);
                }
                childAt.setTranslationX(c0020b.e0);
                childAt.setTranslationY(c0020b.f0);
                if (i3 >= 21) {
                    m.b.a(childAt, c0020b.g0);
                    if (c0020b.V) {
                        m.c.a(childAt, c0020b.W);
                    }
                }
            }
        }
        for (Integer num : hashSet) {
            C0020b c0020b2 = (C0020b) this.a.get(num);
            int i4 = c0020b2.t0;
            if (i4 != -1 && i4 == 1) {
                m.a aVar3 = new m.a(constraintLayout.getContext());
                aVar3.setId(num.intValue());
                int[] iArr2 = c0020b2.u0;
                if (iArr2 != null) {
                    aVar3.setReferencedIds(iArr2);
                } else {
                    String str2 = c0020b2.v0;
                    if (str2 != null) {
                        int[] iArrB2 = b(aVar3, str2);
                        c0020b2.u0 = iArrB2;
                        aVar3.setReferencedIds(iArrB2);
                    }
                }
                aVar3.setType(c0020b2.s0);
                ConstraintLayout.a aVarA = constraintLayout.generateDefaultLayoutParams();
                aVar3.f();
                c0020b2.a(aVarA);
                constraintLayout.addView((View) aVar3, (ViewGroup.LayoutParams) aVarA);
            }
            if (c0020b2.a) {
                View cVar = new c(constraintLayout.getContext());
                cVar.setId(num.intValue());
                ConstraintLayout.a aVarA2 = constraintLayout.generateDefaultLayoutParams();
                c0020b2.a(aVarA2);
                constraintLayout.addView(cVar, aVarA2);
            }
        }
    }

    public void d(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    C0020b c0020bC = c(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        c0020bC.a = true;
                    }
                    this.a.put(Integer.valueOf(c0020bC.d), c0020bC);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }
}
