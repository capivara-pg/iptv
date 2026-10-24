package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class d {
    private static final int[] f = {0, 4, 8};
    private static SparseIntArray g = new SparseIntArray();
    private static SparseIntArray h = new SparseIntArray();
    public String a = "";
    public int b = 0;
    private HashMap c = new HashMap();
    private boolean d = true;
    private HashMap e = new HashMap();

    public static class a {
        int a;
        String b;
        public final C0019d c = new C0019d();
        public final c d = new c();
        public final b e = new b();
        public final e f = new e();
        public HashMap g = new HashMap();
        C0018a h;

        /* JADX INFO: renamed from: androidx.constraintlayout.widget.d$a$a, reason: collision with other inner class name */
        static class C0018a {
            int[] a = new int[10];
            int[] b = new int[10];
            int c = 0;
            int[] d = new int[10];
            float[] e = new float[10];
            int f = 0;
            int[] g = new int[5];
            String[] h = new String[5];
            int i = 0;
            int[] j = new int[4];
            boolean[] k = new boolean[4];
            int l = 0;

            C0018a() {
            }

            void a(int i, float f) {
                int i2 = this.f;
                int[] iArr = this.d;
                if (i2 >= iArr.length) {
                    this.d = Arrays.copyOf(iArr, iArr.length * 2);
                    float[] fArr = this.e;
                    this.e = Arrays.copyOf(fArr, fArr.length * 2);
                }
                int[] iArr2 = this.d;
                int i3 = this.f;
                iArr2[i3] = i;
                float[] fArr2 = this.e;
                this.f = i3 + 1;
                fArr2[i3] = f;
            }

            void b(int i, int i2) {
                int i3 = this.c;
                int[] iArr = this.a;
                if (i3 >= iArr.length) {
                    this.a = Arrays.copyOf(iArr, iArr.length * 2);
                    int[] iArr2 = this.b;
                    this.b = Arrays.copyOf(iArr2, iArr2.length * 2);
                }
                int[] iArr3 = this.a;
                int i4 = this.c;
                iArr3[i4] = i;
                int[] iArr4 = this.b;
                this.c = i4 + 1;
                iArr4[i4] = i2;
            }

            void c(int i, String str) {
                int i2 = this.i;
                int[] iArr = this.g;
                if (i2 >= iArr.length) {
                    this.g = Arrays.copyOf(iArr, iArr.length * 2);
                    String[] strArr = this.h;
                    this.h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                }
                int[] iArr2 = this.g;
                int i3 = this.i;
                iArr2[i3] = i;
                String[] strArr2 = this.h;
                this.i = i3 + 1;
                strArr2[i3] = str;
            }

            void d(int i, boolean z) {
                int i2 = this.l;
                int[] iArr = this.j;
                if (i2 >= iArr.length) {
                    this.j = Arrays.copyOf(iArr, iArr.length * 2);
                    boolean[] zArr = this.k;
                    this.k = Arrays.copyOf(zArr, zArr.length * 2);
                }
                int[] iArr2 = this.j;
                int i3 = this.l;
                iArr2[i3] = i;
                boolean[] zArr2 = this.k;
                this.l = i3 + 1;
                zArr2[i3] = z;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(int i, ConstraintLayout.b bVar) {
            this.a = i;
            b bVar2 = this.e;
            bVar2.j = bVar.e;
            bVar2.k = bVar.f;
            bVar2.l = bVar.g;
            bVar2.m = bVar.h;
            bVar2.n = bVar.i;
            bVar2.o = bVar.j;
            bVar2.p = bVar.k;
            bVar2.q = bVar.l;
            bVar2.r = bVar.m;
            bVar2.f21s = bVar.n;
            bVar2.t = bVar.o;
            bVar2.u = bVar.f19s;
            bVar2.v = bVar.t;
            bVar2.w = bVar.u;
            bVar2.x = bVar.v;
            bVar2.y = bVar.G;
            bVar2.z = bVar.H;
            bVar2.A = bVar.I;
            bVar2.B = bVar.p;
            bVar2.C = bVar.q;
            bVar2.D = bVar.r;
            bVar2.E = bVar.X;
            bVar2.F = bVar.Y;
            bVar2.G = bVar.Z;
            bVar2.h = bVar.c;
            bVar2.f = bVar.a;
            bVar2.g = bVar.b;
            bVar2.d = ((ViewGroup.MarginLayoutParams) bVar).width;
            bVar2.e = ((ViewGroup.MarginLayoutParams) bVar).height;
            bVar2.H = ((ViewGroup.MarginLayoutParams) bVar).leftMargin;
            bVar2.I = ((ViewGroup.MarginLayoutParams) bVar).rightMargin;
            bVar2.J = ((ViewGroup.MarginLayoutParams) bVar).topMargin;
            bVar2.K = ((ViewGroup.MarginLayoutParams) bVar).bottomMargin;
            bVar2.N = bVar.D;
            bVar2.V = bVar.M;
            bVar2.W = bVar.L;
            bVar2.Y = bVar.O;
            bVar2.X = bVar.N;
            bVar2.n0 = bVar.a0;
            bVar2.o0 = bVar.b0;
            bVar2.Z = bVar.P;
            bVar2.a0 = bVar.Q;
            bVar2.b0 = bVar.T;
            bVar2.c0 = bVar.U;
            bVar2.d0 = bVar.R;
            bVar2.e0 = bVar.S;
            bVar2.f0 = bVar.V;
            bVar2.g0 = bVar.W;
            bVar2.m0 = bVar.c0;
            bVar2.P = bVar.x;
            bVar2.R = bVar.z;
            bVar2.O = bVar.w;
            bVar2.Q = bVar.y;
            bVar2.T = bVar.A;
            bVar2.S = bVar.B;
            bVar2.U = bVar.C;
            bVar2.q0 = bVar.d0;
            bVar2.L = bVar.getMarginEnd();
            this.e.M = bVar.getMarginStart();
        }

        public void b(ConstraintLayout.b bVar) {
            b bVar2 = this.e;
            bVar.e = bVar2.j;
            bVar.f = bVar2.k;
            bVar.g = bVar2.l;
            bVar.h = bVar2.m;
            bVar.i = bVar2.n;
            bVar.j = bVar2.o;
            bVar.k = bVar2.p;
            bVar.l = bVar2.q;
            bVar.m = bVar2.r;
            bVar.n = bVar2.f21s;
            bVar.o = bVar2.t;
            bVar.f19s = bVar2.u;
            bVar.t = bVar2.v;
            bVar.u = bVar2.w;
            bVar.v = bVar2.x;
            ((ViewGroup.MarginLayoutParams) bVar).leftMargin = bVar2.H;
            ((ViewGroup.MarginLayoutParams) bVar).rightMargin = bVar2.I;
            ((ViewGroup.MarginLayoutParams) bVar).topMargin = bVar2.J;
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin = bVar2.K;
            bVar.A = bVar2.T;
            bVar.B = bVar2.S;
            bVar.x = bVar2.P;
            bVar.z = bVar2.R;
            bVar.G = bVar2.y;
            bVar.H = bVar2.z;
            bVar.p = bVar2.B;
            bVar.q = bVar2.C;
            bVar.r = bVar2.D;
            bVar.I = bVar2.A;
            bVar.X = bVar2.E;
            bVar.Y = bVar2.F;
            bVar.M = bVar2.V;
            bVar.L = bVar2.W;
            bVar.O = bVar2.Y;
            bVar.N = bVar2.X;
            bVar.a0 = bVar2.n0;
            bVar.b0 = bVar2.o0;
            bVar.P = bVar2.Z;
            bVar.Q = bVar2.a0;
            bVar.T = bVar2.b0;
            bVar.U = bVar2.c0;
            bVar.R = bVar2.d0;
            bVar.S = bVar2.e0;
            bVar.V = bVar2.f0;
            bVar.W = bVar2.g0;
            bVar.Z = bVar2.G;
            bVar.c = bVar2.h;
            bVar.a = bVar2.f;
            bVar.b = bVar2.g;
            ((ViewGroup.MarginLayoutParams) bVar).width = bVar2.d;
            ((ViewGroup.MarginLayoutParams) bVar).height = bVar2.e;
            String str = bVar2.m0;
            if (str != null) {
                bVar.c0 = str;
            }
            bVar.d0 = bVar2.q0;
            bVar.setMarginStart(bVar2.M);
            bVar.setMarginEnd(this.e.L);
            bVar.a();
        }

        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a();
            aVar.e.a(this.e);
            aVar.d.a(this.d);
            aVar.c.a(this.c);
            aVar.f.a(this.f);
            aVar.a = this.a;
            aVar.h = this.h;
            return aVar;
        }
    }

    public static class b {
        private static SparseIntArray r0;
        public int d;
        public int e;
        public int[] k0;
        public String l0;
        public String m0;
        public boolean a = false;
        public boolean b = false;
        public boolean c = false;
        public int f = -1;
        public int g = -1;
        public float h = -1.0f;
        public boolean i = true;
        public int j = -1;
        public int k = -1;
        public int l = -1;
        public int m = -1;
        public int n = -1;
        public int o = -1;
        public int p = -1;
        public int q = -1;
        public int r = -1;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f21s = -1;
        public int t = -1;
        public int u = -1;
        public int v = -1;
        public int w = -1;
        public int x = -1;
        public float y = 0.5f;
        public float z = 0.5f;
        public String A = null;
        public int B = -1;
        public int C = 0;
        public float D = 0.0f;
        public int E = -1;
        public int F = -1;
        public int G = -1;
        public int H = 0;
        public int I = 0;
        public int J = 0;
        public int K = 0;
        public int L = 0;
        public int M = 0;
        public int N = 0;
        public int O = Integer.MIN_VALUE;
        public int P = Integer.MIN_VALUE;
        public int Q = Integer.MIN_VALUE;
        public int R = Integer.MIN_VALUE;
        public int S = Integer.MIN_VALUE;
        public int T = Integer.MIN_VALUE;
        public int U = Integer.MIN_VALUE;
        public float V = -1.0f;
        public float W = -1.0f;
        public int X = 0;
        public int Y = 0;
        public int Z = 0;
        public int a0 = 0;
        public int b0 = 0;
        public int c0 = 0;
        public int d0 = 0;
        public int e0 = 0;
        public float f0 = 1.0f;
        public float g0 = 1.0f;
        public int h0 = -1;
        public int i0 = 0;
        public int j0 = -1;
        public boolean n0 = false;
        public boolean o0 = false;
        public boolean p0 = true;
        public int q0 = 0;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            r0 = sparseIntArray;
            sparseIntArray.append(R$styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            r0.append(R$styleable.Layout_layout_constraintLeft_toRightOf, 25);
            r0.append(R$styleable.Layout_layout_constraintRight_toLeftOf, 28);
            r0.append(R$styleable.Layout_layout_constraintRight_toRightOf, 29);
            r0.append(R$styleable.Layout_layout_constraintTop_toTopOf, 35);
            r0.append(R$styleable.Layout_layout_constraintTop_toBottomOf, 34);
            r0.append(R$styleable.Layout_layout_constraintBottom_toTopOf, 4);
            r0.append(R$styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            r0.append(R$styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            r0.append(R$styleable.Layout_layout_editor_absoluteX, 6);
            r0.append(R$styleable.Layout_layout_editor_absoluteY, 7);
            r0.append(R$styleable.Layout_layout_constraintGuide_begin, 17);
            r0.append(R$styleable.Layout_layout_constraintGuide_end, 18);
            r0.append(R$styleable.Layout_layout_constraintGuide_percent, 19);
            r0.append(R$styleable.Layout_guidelineUseRtl, 90);
            r0.append(R$styleable.Layout_android_orientation, 26);
            r0.append(R$styleable.Layout_layout_constraintStart_toEndOf, 31);
            r0.append(R$styleable.Layout_layout_constraintStart_toStartOf, 32);
            r0.append(R$styleable.Layout_layout_constraintEnd_toStartOf, 10);
            r0.append(R$styleable.Layout_layout_constraintEnd_toEndOf, 9);
            r0.append(R$styleable.Layout_layout_goneMarginLeft, 13);
            r0.append(R$styleable.Layout_layout_goneMarginTop, 16);
            r0.append(R$styleable.Layout_layout_goneMarginRight, 14);
            r0.append(R$styleable.Layout_layout_goneMarginBottom, 11);
            r0.append(R$styleable.Layout_layout_goneMarginStart, 15);
            r0.append(R$styleable.Layout_layout_goneMarginEnd, 12);
            r0.append(R$styleable.Layout_layout_constraintVertical_weight, 38);
            r0.append(R$styleable.Layout_layout_constraintHorizontal_weight, 37);
            r0.append(R$styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            r0.append(R$styleable.Layout_layout_constraintVertical_chainStyle, 40);
            r0.append(R$styleable.Layout_layout_constraintHorizontal_bias, 20);
            r0.append(R$styleable.Layout_layout_constraintVertical_bias, 36);
            r0.append(R$styleable.Layout_layout_constraintDimensionRatio, 5);
            r0.append(R$styleable.Layout_layout_constraintLeft_creator, 91);
            r0.append(R$styleable.Layout_layout_constraintTop_creator, 91);
            r0.append(R$styleable.Layout_layout_constraintRight_creator, 91);
            r0.append(R$styleable.Layout_layout_constraintBottom_creator, 91);
            r0.append(R$styleable.Layout_layout_constraintBaseline_creator, 91);
            r0.append(R$styleable.Layout_android_layout_marginLeft, 23);
            r0.append(R$styleable.Layout_android_layout_marginRight, 27);
            r0.append(R$styleable.Layout_android_layout_marginStart, 30);
            r0.append(R$styleable.Layout_android_layout_marginEnd, 8);
            r0.append(R$styleable.Layout_android_layout_marginTop, 33);
            r0.append(R$styleable.Layout_android_layout_marginBottom, 2);
            r0.append(R$styleable.Layout_android_layout_width, 22);
            r0.append(R$styleable.Layout_android_layout_height, 21);
            r0.append(R$styleable.Layout_layout_constraintWidth, 41);
            r0.append(R$styleable.Layout_layout_constraintHeight, 42);
            r0.append(R$styleable.Layout_layout_constrainedWidth, 41);
            r0.append(R$styleable.Layout_layout_constrainedHeight, 42);
            r0.append(R$styleable.Layout_layout_wrapBehaviorInParent, 76);
            r0.append(R$styleable.Layout_layout_constraintCircle, 61);
            r0.append(R$styleable.Layout_layout_constraintCircleRadius, 62);
            r0.append(R$styleable.Layout_layout_constraintCircleAngle, 63);
            r0.append(R$styleable.Layout_layout_constraintWidth_percent, 69);
            r0.append(R$styleable.Layout_layout_constraintHeight_percent, 70);
            r0.append(R$styleable.Layout_chainUseRtl, 71);
            r0.append(R$styleable.Layout_barrierDirection, 72);
            r0.append(R$styleable.Layout_barrierMargin, 73);
            r0.append(R$styleable.Layout_constraint_referenced_ids, 74);
            r0.append(R$styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        public void a(b bVar) {
            this.a = bVar.a;
            this.d = bVar.d;
            this.b = bVar.b;
            this.e = bVar.e;
            this.f = bVar.f;
            this.g = bVar.g;
            this.h = bVar.h;
            this.i = bVar.i;
            this.j = bVar.j;
            this.k = bVar.k;
            this.l = bVar.l;
            this.m = bVar.m;
            this.n = bVar.n;
            this.o = bVar.o;
            this.p = bVar.p;
            this.q = bVar.q;
            this.r = bVar.r;
            this.f21s = bVar.f21s;
            this.t = bVar.t;
            this.u = bVar.u;
            this.v = bVar.v;
            this.w = bVar.w;
            this.x = bVar.x;
            this.y = bVar.y;
            this.z = bVar.z;
            this.A = bVar.A;
            this.B = bVar.B;
            this.C = bVar.C;
            this.D = bVar.D;
            this.E = bVar.E;
            this.F = bVar.F;
            this.G = bVar.G;
            this.H = bVar.H;
            this.I = bVar.I;
            this.J = bVar.J;
            this.K = bVar.K;
            this.L = bVar.L;
            this.M = bVar.M;
            this.N = bVar.N;
            this.O = bVar.O;
            this.P = bVar.P;
            this.Q = bVar.Q;
            this.R = bVar.R;
            this.S = bVar.S;
            this.T = bVar.T;
            this.U = bVar.U;
            this.V = bVar.V;
            this.W = bVar.W;
            this.X = bVar.X;
            this.Y = bVar.Y;
            this.Z = bVar.Z;
            this.a0 = bVar.a0;
            this.b0 = bVar.b0;
            this.c0 = bVar.c0;
            this.d0 = bVar.d0;
            this.e0 = bVar.e0;
            this.f0 = bVar.f0;
            this.g0 = bVar.g0;
            this.h0 = bVar.h0;
            this.i0 = bVar.i0;
            this.j0 = bVar.j0;
            this.m0 = bVar.m0;
            int[] iArr = bVar.k0;
            if (iArr == null || bVar.l0 != null) {
                this.k0 = null;
            } else {
                this.k0 = Arrays.copyOf(iArr, iArr.length);
            }
            this.l0 = bVar.l0;
            this.n0 = bVar.n0;
            this.o0 = bVar.o0;
            this.p0 = bVar.p0;
            this.q0 = bVar.q0;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.O);
            this.b = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                int i2 = r0.get(index);
                switch (i2) {
                    case 1:
                        this.r = d.j(typedArrayObtainStyledAttributes, index, this.r);
                        break;
                    case 2:
                        this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.K);
                        break;
                    case 3:
                        this.q = d.j(typedArrayObtainStyledAttributes, index, this.q);
                        break;
                    case 4:
                        this.p = d.j(typedArrayObtainStyledAttributes, index, this.p);
                        break;
                    case 5:
                        this.A = typedArrayObtainStyledAttributes.getString(index);
                        break;
                    case 6:
                        this.E = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.E);
                        break;
                    case 7:
                        this.F = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.F);
                        break;
                    case 8:
                        this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                        break;
                    case 9:
                        this.x = d.j(typedArrayObtainStyledAttributes, index, this.x);
                        break;
                    case 10:
                        this.w = d.j(typedArrayObtainStyledAttributes, index, this.w);
                        break;
                    case 11:
                        this.R = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.R);
                        break;
                    case 12:
                        this.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.S);
                        break;
                    case 13:
                        this.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.O);
                        break;
                    case 14:
                        this.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.Q);
                        break;
                    case 15:
                        this.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.T);
                        break;
                    case 16:
                        this.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.P);
                        break;
                    case 17:
                        this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f);
                        break;
                    case 18:
                        this.g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.g);
                        break;
                    case 19:
                        this.h = typedArrayObtainStyledAttributes.getFloat(index, this.h);
                        break;
                    case 20:
                        this.y = typedArrayObtainStyledAttributes.getFloat(index, this.y);
                        break;
                    case 21:
                        this.e = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.e);
                        break;
                    case 22:
                        this.d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.d);
                        break;
                    case 23:
                        this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                        break;
                    case 24:
                        this.j = d.j(typedArrayObtainStyledAttributes, index, this.j);
                        break;
                    case 25:
                        this.k = d.j(typedArrayObtainStyledAttributes, index, this.k);
                        break;
                    case 26:
                        this.G = typedArrayObtainStyledAttributes.getInt(index, this.G);
                        break;
                    case 27:
                        this.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.I);
                        break;
                    case 28:
                        this.l = d.j(typedArrayObtainStyledAttributes, index, this.l);
                        break;
                    case 29:
                        this.m = d.j(typedArrayObtainStyledAttributes, index, this.m);
                        break;
                    case 30:
                        this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                        break;
                    case 31:
                        this.u = d.j(typedArrayObtainStyledAttributes, index, this.u);
                        break;
                    case 32:
                        this.v = d.j(typedArrayObtainStyledAttributes, index, this.v);
                        break;
                    case 33:
                        this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                        break;
                    case 34:
                        this.o = d.j(typedArrayObtainStyledAttributes, index, this.o);
                        break;
                    case 35:
                        this.n = d.j(typedArrayObtainStyledAttributes, index, this.n);
                        break;
                    case 36:
                        this.z = typedArrayObtainStyledAttributes.getFloat(index, this.z);
                        break;
                    case 37:
                        this.W = typedArrayObtainStyledAttributes.getFloat(index, this.W);
                        break;
                    case 38:
                        this.V = typedArrayObtainStyledAttributes.getFloat(index, this.V);
                        break;
                    case 39:
                        this.X = typedArrayObtainStyledAttributes.getInt(index, this.X);
                        break;
                    case 40:
                        this.Y = typedArrayObtainStyledAttributes.getInt(index, this.Y);
                        break;
                    case 41:
                        d.k(this, typedArrayObtainStyledAttributes, index, 0);
                        break;
                    case 42:
                        d.k(this, typedArrayObtainStyledAttributes, index, 1);
                        break;
                    default:
                        switch (i2) {
                            case 61:
                                this.B = d.j(typedArrayObtainStyledAttributes, index, this.B);
                                break;
                            case 62:
                                this.C = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.C);
                                break;
                            case 63:
                                this.D = typedArrayObtainStyledAttributes.getFloat(index, this.D);
                                break;
                            default:
                                switch (i2) {
                                    case 69:
                                        this.f0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 70:
                                        this.g0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 71:
                                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                        break;
                                    case 72:
                                        this.h0 = typedArrayObtainStyledAttributes.getInt(index, this.h0);
                                        break;
                                    case 73:
                                        this.i0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.i0);
                                        break;
                                    case 74:
                                        this.l0 = typedArrayObtainStyledAttributes.getString(index);
                                        break;
                                    case 75:
                                        this.p0 = typedArrayObtainStyledAttributes.getBoolean(index, this.p0);
                                        break;
                                    case 76:
                                        this.q0 = typedArrayObtainStyledAttributes.getInt(index, this.q0);
                                        break;
                                    case 77:
                                        this.f21s = d.j(typedArrayObtainStyledAttributes, index, this.f21s);
                                        break;
                                    case 78:
                                        this.t = d.j(typedArrayObtainStyledAttributes, index, this.t);
                                        break;
                                    case 79:
                                        this.U = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.U);
                                        break;
                                    case 80:
                                        this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.N);
                                        break;
                                    case 81:
                                        this.Z = typedArrayObtainStyledAttributes.getInt(index, this.Z);
                                        break;
                                    case 82:
                                        this.a0 = typedArrayObtainStyledAttributes.getInt(index, this.a0);
                                        break;
                                    case 83:
                                        this.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.c0);
                                        break;
                                    case 84:
                                        this.b0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.b0);
                                        break;
                                    case 85:
                                        this.e0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.e0);
                                        break;
                                    case 86:
                                        this.d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.d0);
                                        break;
                                    case 87:
                                        this.n0 = typedArrayObtainStyledAttributes.getBoolean(index, this.n0);
                                        break;
                                    case 88:
                                        this.o0 = typedArrayObtainStyledAttributes.getBoolean(index, this.o0);
                                        break;
                                    case 89:
                                        this.m0 = typedArrayObtainStyledAttributes.getString(index);
                                        break;
                                    case 90:
                                        this.i = typedArrayObtainStyledAttributes.getBoolean(index, this.i);
                                        break;
                                    case 91:
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("unused attribute 0x");
                                        sb.append(Integer.toHexString(index));
                                        sb.append("   ");
                                        sb.append(r0.get(index));
                                        break;
                                    default:
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append("Unknown attribute 0x");
                                        sb2.append(Integer.toHexString(index));
                                        sb2.append("   ");
                                        sb2.append(r0.get(index));
                                        break;
                                }
                                break;
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static class c {
        private static SparseIntArray o;
        public boolean a = false;
        public int b = -1;
        public int c = 0;
        public String d = null;
        public int e = -1;
        public int f = 0;
        public float g = Float.NaN;
        public int h = -1;
        public float i = Float.NaN;
        public float j = Float.NaN;
        public int k = -1;
        public String l = null;
        public int m = -3;
        public int n = -1;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            o = sparseIntArray;
            sparseIntArray.append(R$styleable.Motion_motionPathRotate, 1);
            o.append(R$styleable.Motion_pathMotionArc, 2);
            o.append(R$styleable.Motion_transitionEasing, 3);
            o.append(R$styleable.Motion_drawPath, 4);
            o.append(R$styleable.Motion_animateRelativeTo, 5);
            o.append(R$styleable.Motion_animateCircleAngleTo, 6);
            o.append(R$styleable.Motion_motionStagger, 7);
            o.append(R$styleable.Motion_quantizeMotionSteps, 8);
            o.append(R$styleable.Motion_quantizeMotionPhase, 9);
            o.append(R$styleable.Motion_quantizeMotionInterpolator, 10);
        }

        public void a(c cVar) {
            this.a = cVar.a;
            this.b = cVar.b;
            this.d = cVar.d;
            this.e = cVar.e;
            this.f = cVar.f;
            this.i = cVar.i;
            this.g = cVar.g;
            this.h = cVar.h;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.W);
            this.a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (o.get(index)) {
                    case 1:
                        this.i = typedArrayObtainStyledAttributes.getFloat(index, this.i);
                        break;
                    case 2:
                        this.e = typedArrayObtainStyledAttributes.getInt(index, this.e);
                        break;
                    case 3:
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            this.d = typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            this.d = n.a.c[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                        }
                        break;
                    case 4:
                        this.f = typedArrayObtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.b = d.j(typedArrayObtainStyledAttributes, index, this.b);
                        break;
                    case 6:
                        this.c = typedArrayObtainStyledAttributes.getInteger(index, this.c);
                        break;
                    case 7:
                        this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                        break;
                    case 8:
                        this.k = typedArrayObtainStyledAttributes.getInteger(index, this.k);
                        break;
                    case 9:
                        this.j = typedArrayObtainStyledAttributes.getFloat(index, this.j);
                        break;
                    case 10:
                        int i2 = typedArrayObtainStyledAttributes.peekValue(index).type;
                        if (i2 == 1) {
                            int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                            this.n = resourceId;
                            if (resourceId != -1) {
                                this.m = -2;
                            }
                        } else if (i2 == 3) {
                            String string = typedArrayObtainStyledAttributes.getString(index);
                            this.l = string;
                            if (string.indexOf("/") > 0) {
                                this.n = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                this.m = -2;
                            } else {
                                this.m = -1;
                            }
                        } else {
                            this.m = typedArrayObtainStyledAttributes.getInteger(index, this.n);
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.d$d, reason: collision with other inner class name */
    public static class C0019d {
        public boolean a = false;
        public int b = 0;
        public int c = 0;
        public float d = 1.0f;
        public float e = Float.NaN;

        public void a(C0019d c0019d) {
            this.a = c0019d.a;
            this.b = c0019d.b;
            this.d = c0019d.d;
            this.e = c0019d.e;
            this.c = c0019d.c;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.h0);
            this.a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.PropertySet_android_alpha) {
                    this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
                } else if (index == R$styleable.PropertySet_android_visibility) {
                    this.b = typedArrayObtainStyledAttributes.getInt(index, this.b);
                    this.b = d.f[this.b];
                } else if (index == R$styleable.PropertySet_visibilityMode) {
                    this.c = typedArrayObtainStyledAttributes.getInt(index, this.c);
                } else if (index == R$styleable.PropertySet_motionProgress) {
                    this.e = typedArrayObtainStyledAttributes.getFloat(index, this.e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static class e {
        private static SparseIntArray o;
        public boolean a = false;
        public float b = 0.0f;
        public float c = 0.0f;
        public float d = 0.0f;
        public float e = 1.0f;
        public float f = 1.0f;
        public float g = Float.NaN;
        public float h = Float.NaN;
        public int i = -1;
        public float j = 0.0f;
        public float k = 0.0f;
        public float l = 0.0f;
        public boolean m = false;
        public float n = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            o = sparseIntArray;
            sparseIntArray.append(R$styleable.Transform_android_rotation, 1);
            o.append(R$styleable.Transform_android_rotationX, 2);
            o.append(R$styleable.Transform_android_rotationY, 3);
            o.append(R$styleable.Transform_android_scaleX, 4);
            o.append(R$styleable.Transform_android_scaleY, 5);
            o.append(R$styleable.Transform_android_transformPivotX, 6);
            o.append(R$styleable.Transform_android_transformPivotY, 7);
            o.append(R$styleable.Transform_android_translationX, 8);
            o.append(R$styleable.Transform_android_translationY, 9);
            o.append(R$styleable.Transform_android_translationZ, 10);
            o.append(R$styleable.Transform_android_elevation, 11);
            o.append(R$styleable.Transform_transformPivotTarget, 12);
        }

        public void a(e eVar) {
            this.a = eVar.a;
            this.b = eVar.b;
            this.c = eVar.c;
            this.d = eVar.d;
            this.e = eVar.e;
            this.f = eVar.f;
            this.g = eVar.g;
            this.h = eVar.h;
            this.i = eVar.i;
            this.j = eVar.j;
            this.k = eVar.k;
            this.l = eVar.l;
            this.m = eVar.m;
            this.n = eVar.n;
        }

        void b(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.t0);
            this.a = true;
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                switch (o.get(index)) {
                    case 1:
                        this.b = typedArrayObtainStyledAttributes.getFloat(index, this.b);
                        break;
                    case 2:
                        this.c = typedArrayObtainStyledAttributes.getFloat(index, this.c);
                        break;
                    case 3:
                        this.d = typedArrayObtainStyledAttributes.getFloat(index, this.d);
                        break;
                    case 4:
                        this.e = typedArrayObtainStyledAttributes.getFloat(index, this.e);
                        break;
                    case 5:
                        this.f = typedArrayObtainStyledAttributes.getFloat(index, this.f);
                        break;
                    case 6:
                        this.g = typedArrayObtainStyledAttributes.getDimension(index, this.g);
                        break;
                    case 7:
                        this.h = typedArrayObtainStyledAttributes.getDimension(index, this.h);
                        break;
                    case 8:
                        this.j = typedArrayObtainStyledAttributes.getDimension(index, this.j);
                        break;
                    case 9:
                        this.k = typedArrayObtainStyledAttributes.getDimension(index, this.k);
                        break;
                    case 10:
                        this.l = typedArrayObtainStyledAttributes.getDimension(index, this.l);
                        break;
                    case 11:
                        this.m = true;
                        this.n = typedArrayObtainStyledAttributes.getDimension(index, this.n);
                        break;
                    case 12:
                        this.i = d.j(typedArrayObtainStyledAttributes, index, this.i);
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    static {
        g.append(R$styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        g.append(R$styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        g.append(R$styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        g.append(R$styleable.Constraint_layout_constraintRight_toRightOf, 30);
        g.append(R$styleable.Constraint_layout_constraintTop_toTopOf, 36);
        g.append(R$styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        g.append(R$styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        g.append(R$styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        g.append(R$styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        g.append(R$styleable.Constraint_layout_constraintBaseline_toTopOf, 91);
        g.append(R$styleable.Constraint_layout_constraintBaseline_toBottomOf, 92);
        g.append(R$styleable.Constraint_layout_editor_absoluteX, 6);
        g.append(R$styleable.Constraint_layout_editor_absoluteY, 7);
        g.append(R$styleable.Constraint_layout_constraintGuide_begin, 17);
        g.append(R$styleable.Constraint_layout_constraintGuide_end, 18);
        g.append(R$styleable.Constraint_layout_constraintGuide_percent, 19);
        g.append(R$styleable.Constraint_guidelineUseRtl, 99);
        g.append(R$styleable.Constraint_android_orientation, 27);
        g.append(R$styleable.Constraint_layout_constraintStart_toEndOf, 32);
        g.append(R$styleable.Constraint_layout_constraintStart_toStartOf, 33);
        g.append(R$styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        g.append(R$styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        g.append(R$styleable.Constraint_layout_goneMarginLeft, 13);
        g.append(R$styleable.Constraint_layout_goneMarginTop, 16);
        g.append(R$styleable.Constraint_layout_goneMarginRight, 14);
        g.append(R$styleable.Constraint_layout_goneMarginBottom, 11);
        g.append(R$styleable.Constraint_layout_goneMarginStart, 15);
        g.append(R$styleable.Constraint_layout_goneMarginEnd, 12);
        g.append(R$styleable.Constraint_layout_constraintVertical_weight, 40);
        g.append(R$styleable.Constraint_layout_constraintHorizontal_weight, 39);
        g.append(R$styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        g.append(R$styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        g.append(R$styleable.Constraint_layout_constraintHorizontal_bias, 20);
        g.append(R$styleable.Constraint_layout_constraintVertical_bias, 37);
        g.append(R$styleable.Constraint_layout_constraintDimensionRatio, 5);
        g.append(R$styleable.Constraint_layout_constraintLeft_creator, 87);
        g.append(R$styleable.Constraint_layout_constraintTop_creator, 87);
        g.append(R$styleable.Constraint_layout_constraintRight_creator, 87);
        g.append(R$styleable.Constraint_layout_constraintBottom_creator, 87);
        g.append(R$styleable.Constraint_layout_constraintBaseline_creator, 87);
        g.append(R$styleable.Constraint_android_layout_marginLeft, 24);
        g.append(R$styleable.Constraint_android_layout_marginRight, 28);
        g.append(R$styleable.Constraint_android_layout_marginStart, 31);
        g.append(R$styleable.Constraint_android_layout_marginEnd, 8);
        g.append(R$styleable.Constraint_android_layout_marginTop, 34);
        g.append(R$styleable.Constraint_android_layout_marginBottom, 2);
        g.append(R$styleable.Constraint_android_layout_width, 23);
        g.append(R$styleable.Constraint_android_layout_height, 21);
        g.append(R$styleable.Constraint_layout_constraintWidth, 95);
        g.append(R$styleable.Constraint_layout_constraintHeight, 96);
        g.append(R$styleable.Constraint_android_visibility, 22);
        g.append(R$styleable.Constraint_android_alpha, 43);
        g.append(R$styleable.Constraint_android_elevation, 44);
        g.append(R$styleable.Constraint_android_rotationX, 45);
        g.append(R$styleable.Constraint_android_rotationY, 46);
        g.append(R$styleable.Constraint_android_rotation, 60);
        g.append(R$styleable.Constraint_android_scaleX, 47);
        g.append(R$styleable.Constraint_android_scaleY, 48);
        g.append(R$styleable.Constraint_android_transformPivotX, 49);
        g.append(R$styleable.Constraint_android_transformPivotY, 50);
        g.append(R$styleable.Constraint_android_translationX, 51);
        g.append(R$styleable.Constraint_android_translationY, 52);
        g.append(R$styleable.Constraint_android_translationZ, 53);
        g.append(R$styleable.Constraint_layout_constraintWidth_default, 54);
        g.append(R$styleable.Constraint_layout_constraintHeight_default, 55);
        g.append(R$styleable.Constraint_layout_constraintWidth_max, 56);
        g.append(R$styleable.Constraint_layout_constraintHeight_max, 57);
        g.append(R$styleable.Constraint_layout_constraintWidth_min, 58);
        g.append(R$styleable.Constraint_layout_constraintHeight_min, 59);
        g.append(R$styleable.Constraint_layout_constraintCircle, 61);
        g.append(R$styleable.Constraint_layout_constraintCircleRadius, 62);
        g.append(R$styleable.Constraint_layout_constraintCircleAngle, 63);
        g.append(R$styleable.Constraint_animateRelativeTo, 64);
        g.append(R$styleable.Constraint_transitionEasing, 65);
        g.append(R$styleable.Constraint_drawPath, 66);
        g.append(R$styleable.Constraint_transitionPathRotate, 67);
        g.append(R$styleable.Constraint_motionStagger, 79);
        g.append(R$styleable.Constraint_android_id, 38);
        g.append(R$styleable.Constraint_motionProgress, 68);
        g.append(R$styleable.Constraint_layout_constraintWidth_percent, 69);
        g.append(R$styleable.Constraint_layout_constraintHeight_percent, 70);
        g.append(R$styleable.Constraint_layout_wrapBehaviorInParent, 97);
        g.append(R$styleable.Constraint_chainUseRtl, 71);
        g.append(R$styleable.Constraint_barrierDirection, 72);
        g.append(R$styleable.Constraint_barrierMargin, 73);
        g.append(R$styleable.Constraint_constraint_referenced_ids, 74);
        g.append(R$styleable.Constraint_barrierAllowsGoneWidgets, 75);
        g.append(R$styleable.Constraint_pathMotionArc, 76);
        g.append(R$styleable.Constraint_layout_constraintTag, 77);
        g.append(R$styleable.Constraint_visibilityMode, 78);
        g.append(R$styleable.Constraint_layout_constrainedWidth, 80);
        g.append(R$styleable.Constraint_layout_constrainedHeight, 81);
        g.append(R$styleable.Constraint_polarRelativeTo, 82);
        g.append(R$styleable.Constraint_transformPivotTarget, 83);
        g.append(R$styleable.Constraint_quantizeMotionSteps, 84);
        g.append(R$styleable.Constraint_quantizeMotionPhase, 85);
        g.append(R$styleable.Constraint_quantizeMotionInterpolator, 86);
        SparseIntArray sparseIntArray = h;
        int i = R$styleable.ConstraintOverride_layout_editor_absoluteY;
        sparseIntArray.append(i, 6);
        h.append(i, 7);
        h.append(R$styleable.ConstraintOverride_android_orientation, 27);
        h.append(R$styleable.ConstraintOverride_layout_goneMarginLeft, 13);
        h.append(R$styleable.ConstraintOverride_layout_goneMarginTop, 16);
        h.append(R$styleable.ConstraintOverride_layout_goneMarginRight, 14);
        h.append(R$styleable.ConstraintOverride_layout_goneMarginBottom, 11);
        h.append(R$styleable.ConstraintOverride_layout_goneMarginStart, 15);
        h.append(R$styleable.ConstraintOverride_layout_goneMarginEnd, 12);
        h.append(R$styleable.ConstraintOverride_layout_constraintVertical_weight, 40);
        h.append(R$styleable.ConstraintOverride_layout_constraintHorizontal_weight, 39);
        h.append(R$styleable.ConstraintOverride_layout_constraintHorizontal_chainStyle, 41);
        h.append(R$styleable.ConstraintOverride_layout_constraintVertical_chainStyle, 42);
        h.append(R$styleable.ConstraintOverride_layout_constraintHorizontal_bias, 20);
        h.append(R$styleable.ConstraintOverride_layout_constraintVertical_bias, 37);
        h.append(R$styleable.ConstraintOverride_layout_constraintDimensionRatio, 5);
        h.append(R$styleable.ConstraintOverride_layout_constraintLeft_creator, 87);
        h.append(R$styleable.ConstraintOverride_layout_constraintTop_creator, 87);
        h.append(R$styleable.ConstraintOverride_layout_constraintRight_creator, 87);
        h.append(R$styleable.ConstraintOverride_layout_constraintBottom_creator, 87);
        h.append(R$styleable.ConstraintOverride_layout_constraintBaseline_creator, 87);
        h.append(R$styleable.ConstraintOverride_android_layout_marginLeft, 24);
        h.append(R$styleable.ConstraintOverride_android_layout_marginRight, 28);
        h.append(R$styleable.ConstraintOverride_android_layout_marginStart, 31);
        h.append(R$styleable.ConstraintOverride_android_layout_marginEnd, 8);
        h.append(R$styleable.ConstraintOverride_android_layout_marginTop, 34);
        h.append(R$styleable.ConstraintOverride_android_layout_marginBottom, 2);
        h.append(R$styleable.ConstraintOverride_android_layout_width, 23);
        h.append(R$styleable.ConstraintOverride_android_layout_height, 21);
        h.append(R$styleable.ConstraintOverride_layout_constraintWidth, 95);
        h.append(R$styleable.ConstraintOverride_layout_constraintHeight, 96);
        h.append(R$styleable.ConstraintOverride_android_visibility, 22);
        h.append(R$styleable.ConstraintOverride_android_alpha, 43);
        h.append(R$styleable.ConstraintOverride_android_elevation, 44);
        h.append(R$styleable.ConstraintOverride_android_rotationX, 45);
        h.append(R$styleable.ConstraintOverride_android_rotationY, 46);
        h.append(R$styleable.ConstraintOverride_android_rotation, 60);
        h.append(R$styleable.ConstraintOverride_android_scaleX, 47);
        h.append(R$styleable.ConstraintOverride_android_scaleY, 48);
        h.append(R$styleable.ConstraintOverride_android_transformPivotX, 49);
        h.append(R$styleable.ConstraintOverride_android_transformPivotY, 50);
        h.append(R$styleable.ConstraintOverride_android_translationX, 51);
        h.append(R$styleable.ConstraintOverride_android_translationY, 52);
        h.append(R$styleable.ConstraintOverride_android_translationZ, 53);
        h.append(R$styleable.ConstraintOverride_layout_constraintWidth_default, 54);
        h.append(R$styleable.ConstraintOverride_layout_constraintHeight_default, 55);
        h.append(R$styleable.ConstraintOverride_layout_constraintWidth_max, 56);
        h.append(R$styleable.ConstraintOverride_layout_constraintHeight_max, 57);
        h.append(R$styleable.ConstraintOverride_layout_constraintWidth_min, 58);
        h.append(R$styleable.ConstraintOverride_layout_constraintHeight_min, 59);
        h.append(R$styleable.ConstraintOverride_layout_constraintCircleRadius, 62);
        h.append(R$styleable.ConstraintOverride_layout_constraintCircleAngle, 63);
        h.append(R$styleable.ConstraintOverride_animateRelativeTo, 64);
        h.append(R$styleable.ConstraintOverride_transitionEasing, 65);
        h.append(R$styleable.ConstraintOverride_drawPath, 66);
        h.append(R$styleable.ConstraintOverride_transitionPathRotate, 67);
        h.append(R$styleable.ConstraintOverride_motionStagger, 79);
        h.append(R$styleable.ConstraintOverride_android_id, 38);
        h.append(R$styleable.ConstraintOverride_motionTarget, 98);
        h.append(R$styleable.ConstraintOverride_motionProgress, 68);
        h.append(R$styleable.ConstraintOverride_layout_constraintWidth_percent, 69);
        h.append(R$styleable.ConstraintOverride_layout_constraintHeight_percent, 70);
        h.append(R$styleable.ConstraintOverride_chainUseRtl, 71);
        h.append(R$styleable.ConstraintOverride_barrierDirection, 72);
        h.append(R$styleable.ConstraintOverride_barrierMargin, 73);
        h.append(R$styleable.ConstraintOverride_constraint_referenced_ids, 74);
        h.append(R$styleable.ConstraintOverride_barrierAllowsGoneWidgets, 75);
        h.append(R$styleable.ConstraintOverride_pathMotionArc, 76);
        h.append(R$styleable.ConstraintOverride_layout_constraintTag, 77);
        h.append(R$styleable.ConstraintOverride_visibilityMode, 78);
        h.append(R$styleable.ConstraintOverride_layout_constrainedWidth, 80);
        h.append(R$styleable.ConstraintOverride_layout_constrainedHeight, 81);
        h.append(R$styleable.ConstraintOverride_polarRelativeTo, 82);
        h.append(R$styleable.ConstraintOverride_transformPivotTarget, 83);
        h.append(R$styleable.ConstraintOverride_quantizeMotionSteps, 84);
        h.append(R$styleable.ConstraintOverride_quantizeMotionPhase, 85);
        h.append(R$styleable.ConstraintOverride_quantizeMotionInterpolator, 86);
        h.append(R$styleable.ConstraintOverride_layout_wrapBehaviorInParent, 97);
    }

    private int[] f(View view, String str) {
        int iIntValue;
        Object objF;
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
            if (iIntValue == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (objF = ((ConstraintLayout) view.getParent()).f(0, strTrim)) != null && (objF instanceof Integer)) {
                iIntValue = ((Integer) objF).intValue();
            }
            iArr[i2] = iIntValue;
            i++;
            i2++;
        }
        return i2 != strArrSplit.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    private a g(Context context, AttributeSet attributeSet, boolean z) {
        a aVar = new a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z ? R$styleable.x : R$styleable.t);
        n(context, aVar, typedArrayObtainStyledAttributes, z);
        typedArrayObtainStyledAttributes.recycle();
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int j(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x002f  */
    /* JADX WARN: Code duplicated, block: B:24:0x0033  */
    /* JADX WARN: Code duplicated, block: B:25:0x0038  */
    /* JADX WARN: Code duplicated, block: B:26:0x003d  */
    /* JADX WARN: Code duplicated, block: B:28:0x0041  */
    /* JADX WARN: Code duplicated, block: B:30:0x0045  */
    /* JADX WARN: Code duplicated, block: B:31:0x004a  */
    /* JADX WARN: Code duplicated, block: B:32:0x004f  */
    /* JADX WARN: Code duplicated, block: B:34:0x0053  */
    /* JADX WARN: Code duplicated, block: B:36:0x0057  */
    /* JADX WARN: Code duplicated, block: B:37:0x0062  */
    /* JADX WARN: Code duplicated, block: B:45:? A[RETURN, SYNTHETIC] */
    static void k(Object obj, TypedArray typedArray, int i, int i2) {
        int dimensionPixelSize;
        boolean z;
        a.C0018a c0018a;
        b bVar;
        ConstraintLayout.b bVar2;
        if (obj == null) {
            return;
        }
        int i3 = typedArray.peekValue(i).type;
        if (i3 == 3) {
            l(obj, typedArray.getString(i), i2);
            return;
        }
        int i4 = 0;
        if (i3 != 5) {
            dimensionPixelSize = typedArray.getInt(i, 0);
            if (dimensionPixelSize != -4) {
                if (dimensionPixelSize != -3 && (dimensionPixelSize == -2 || dimensionPixelSize == -1)) {
                }
                z = false;
            } else {
                z = true;
                i4 = -2;
            }
            if (obj instanceof ConstraintLayout.b) {
                bVar2 = (ConstraintLayout.b) obj;
                if (i2 == 0) {
                    ((ViewGroup.MarginLayoutParams) bVar2).width = i4;
                    bVar2.a0 = z;
                    return;
                } else {
                    ((ViewGroup.MarginLayoutParams) bVar2).height = i4;
                    bVar2.b0 = z;
                    return;
                }
            }
            if (obj instanceof b) {
                bVar = (b) obj;
                if (i2 == 0) {
                    bVar.d = i4;
                    bVar.n0 = z;
                    return;
                } else {
                    bVar.e = i4;
                    bVar.o0 = z;
                    return;
                }
            }
            if (obj instanceof a.C0018a) {
                c0018a = (a.C0018a) obj;
                if (i2 == 0) {
                    c0018a.b(23, i4);
                    c0018a.d(80, z);
                } else {
                    c0018a.b(21, i4);
                    c0018a.d(81, z);
                }
            }
        }
        dimensionPixelSize = typedArray.getDimensionPixelSize(i, 0);
        i4 = dimensionPixelSize;
        z = false;
        if (obj instanceof ConstraintLayout.b) {
            bVar2 = (ConstraintLayout.b) obj;
            if (i2 == 0) {
                ((ViewGroup.MarginLayoutParams) bVar2).width = i4;
                bVar2.a0 = z;
                return;
            } else {
                ((ViewGroup.MarginLayoutParams) bVar2).height = i4;
                bVar2.b0 = z;
                return;
            }
        }
        if (obj instanceof b) {
            bVar = (b) obj;
            if (i2 == 0) {
                bVar.d = i4;
                bVar.n0 = z;
                return;
            } else {
                bVar.e = i4;
                bVar.o0 = z;
                return;
            }
        }
        if (obj instanceof a.C0018a) {
            c0018a = (a.C0018a) obj;
            if (i2 == 0) {
                c0018a.b(23, i4);
                c0018a.d(80, z);
            } else {
                c0018a.b(21, i4);
                c0018a.d(81, z);
            }
        }
    }

    static void l(Object obj, String str, int i) {
        if (str == null) {
            return;
        }
        int iIndexOf = str.indexOf(61);
        int length = str.length();
        if (iIndexOf <= 0 || iIndexOf >= length - 1) {
            return;
        }
        String strSubstring = str.substring(0, iIndexOf);
        String strSubstring2 = str.substring(iIndexOf + 1);
        if (strSubstring2.length() > 0) {
            String strTrim = strSubstring.trim();
            String strTrim2 = strSubstring2.trim();
            if ("ratio".equalsIgnoreCase(strTrim)) {
                if (obj instanceof ConstraintLayout.b) {
                    ConstraintLayout.b bVar = (ConstraintLayout.b) obj;
                    if (i == 0) {
                        ((ViewGroup.MarginLayoutParams) bVar).width = 0;
                    } else {
                        ((ViewGroup.MarginLayoutParams) bVar).height = 0;
                    }
                    m(bVar, strTrim2);
                    return;
                }
                if (obj instanceof b) {
                    ((b) obj).A = strTrim2;
                    return;
                } else {
                    if (obj instanceof a.C0018a) {
                        ((a.C0018a) obj).c(5, strTrim2);
                        return;
                    }
                    return;
                }
            }
            try {
                if ("weight".equalsIgnoreCase(strTrim)) {
                    float f2 = Float.parseFloat(strTrim2);
                    if (obj instanceof ConstraintLayout.b) {
                        ConstraintLayout.b bVar2 = (ConstraintLayout.b) obj;
                        if (i == 0) {
                            ((ViewGroup.MarginLayoutParams) bVar2).width = 0;
                            bVar2.L = f2;
                        } else {
                            ((ViewGroup.MarginLayoutParams) bVar2).height = 0;
                            bVar2.M = f2;
                        }
                    } else if (obj instanceof b) {
                        b bVar3 = (b) obj;
                        if (i == 0) {
                            bVar3.d = 0;
                            bVar3.W = f2;
                        } else {
                            bVar3.e = 0;
                            bVar3.V = f2;
                        }
                    } else if (obj instanceof a.C0018a) {
                        a.C0018a c0018a = (a.C0018a) obj;
                        if (i == 0) {
                            c0018a.b(23, 0);
                            c0018a.a(39, f2);
                        } else {
                            c0018a.b(21, 0);
                            c0018a.a(40, f2);
                        }
                    }
                } else {
                    if (!"parent".equalsIgnoreCase(strTrim)) {
                        return;
                    }
                    float fMax = Math.max(0.0f, Math.min(1.0f, Float.parseFloat(strTrim2)));
                    if (obj instanceof ConstraintLayout.b) {
                        ConstraintLayout.b bVar4 = (ConstraintLayout.b) obj;
                        if (i == 0) {
                            ((ViewGroup.MarginLayoutParams) bVar4).width = 0;
                            bVar4.V = fMax;
                            bVar4.P = 2;
                        } else {
                            ((ViewGroup.MarginLayoutParams) bVar4).height = 0;
                            bVar4.W = fMax;
                            bVar4.Q = 2;
                        }
                    } else if (obj instanceof b) {
                        b bVar5 = (b) obj;
                        if (i == 0) {
                            bVar5.d = 0;
                            bVar5.f0 = fMax;
                            bVar5.Z = 2;
                        } else {
                            bVar5.e = 0;
                            bVar5.g0 = fMax;
                            bVar5.a0 = 2;
                        }
                    } else if (obj instanceof a.C0018a) {
                        a.C0018a c0018a2 = (a.C0018a) obj;
                        if (i == 0) {
                            c0018a2.b(23, 0);
                            c0018a2.b(54, 2);
                        } else {
                            c0018a2.b(21, 0);
                            c0018a2.b(55, 2);
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
    }

    static void m(ConstraintLayout.b bVar, String str) {
        float fAbs = Float.NaN;
        int i = -1;
        if (str != null) {
            int length = str.length();
            int iIndexOf = str.indexOf(44);
            int i2 = 0;
            if (iIndexOf > 0 && iIndexOf < length - 1) {
                String strSubstring = str.substring(0, iIndexOf);
                if (strSubstring.equalsIgnoreCase("W")) {
                    i = 0;
                } else if (strSubstring.equalsIgnoreCase("H")) {
                    i = 1;
                }
                i2 = iIndexOf + 1;
            }
            int iIndexOf2 = str.indexOf(58);
            try {
                if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                    String strSubstring2 = str.substring(i2);
                    if (strSubstring2.length() > 0) {
                        fAbs = Float.parseFloat(strSubstring2);
                    }
                } else {
                    String strSubstring3 = str.substring(i2, iIndexOf2);
                    String strSubstring4 = str.substring(iIndexOf2 + 1);
                    if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                        float f2 = Float.parseFloat(strSubstring3);
                        float f3 = Float.parseFloat(strSubstring4);
                        if (f2 > 0.0f && f3 > 0.0f) {
                            fAbs = i == 1 ? Math.abs(f3 / f2) : Math.abs(f2 / f3);
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        bVar.I = str;
        bVar.J = fAbs;
        bVar.K = i;
    }

    private void n(Context context, a aVar, TypedArray typedArray, boolean z) {
        if (z) {
            o(context, aVar, typedArray);
            return;
        }
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index != R$styleable.Constraint_android_id && R$styleable.Constraint_android_layout_marginStart != index && R$styleable.Constraint_android_layout_marginEnd != index) {
                aVar.d.a = true;
                aVar.e.b = true;
                aVar.c.a = true;
                aVar.f.a = true;
            }
            switch (g.get(index)) {
                case 1:
                    b bVar = aVar.e;
                    bVar.r = j(typedArray, index, bVar.r);
                    break;
                case 2:
                    b bVar2 = aVar.e;
                    bVar2.K = typedArray.getDimensionPixelSize(index, bVar2.K);
                    break;
                case 3:
                    b bVar3 = aVar.e;
                    bVar3.q = j(typedArray, index, bVar3.q);
                    break;
                case 4:
                    b bVar4 = aVar.e;
                    bVar4.p = j(typedArray, index, bVar4.p);
                    break;
                case 5:
                    aVar.e.A = typedArray.getString(index);
                    break;
                case 6:
                    b bVar5 = aVar.e;
                    bVar5.E = typedArray.getDimensionPixelOffset(index, bVar5.E);
                    break;
                case 7:
                    b bVar6 = aVar.e;
                    bVar6.F = typedArray.getDimensionPixelOffset(index, bVar6.F);
                    break;
                case 8:
                    b bVar7 = aVar.e;
                    bVar7.L = typedArray.getDimensionPixelSize(index, bVar7.L);
                    break;
                case 9:
                    b bVar8 = aVar.e;
                    bVar8.x = j(typedArray, index, bVar8.x);
                    break;
                case 10:
                    b bVar9 = aVar.e;
                    bVar9.w = j(typedArray, index, bVar9.w);
                    break;
                case 11:
                    b bVar10 = aVar.e;
                    bVar10.R = typedArray.getDimensionPixelSize(index, bVar10.R);
                    break;
                case 12:
                    b bVar11 = aVar.e;
                    bVar11.S = typedArray.getDimensionPixelSize(index, bVar11.S);
                    break;
                case 13:
                    b bVar12 = aVar.e;
                    bVar12.O = typedArray.getDimensionPixelSize(index, bVar12.O);
                    break;
                case 14:
                    b bVar13 = aVar.e;
                    bVar13.Q = typedArray.getDimensionPixelSize(index, bVar13.Q);
                    break;
                case 15:
                    b bVar14 = aVar.e;
                    bVar14.T = typedArray.getDimensionPixelSize(index, bVar14.T);
                    break;
                case 16:
                    b bVar15 = aVar.e;
                    bVar15.P = typedArray.getDimensionPixelSize(index, bVar15.P);
                    break;
                case 17:
                    b bVar16 = aVar.e;
                    bVar16.f = typedArray.getDimensionPixelOffset(index, bVar16.f);
                    break;
                case 18:
                    b bVar17 = aVar.e;
                    bVar17.g = typedArray.getDimensionPixelOffset(index, bVar17.g);
                    break;
                case 19:
                    b bVar18 = aVar.e;
                    bVar18.h = typedArray.getFloat(index, bVar18.h);
                    break;
                case 20:
                    b bVar19 = aVar.e;
                    bVar19.y = typedArray.getFloat(index, bVar19.y);
                    break;
                case 21:
                    b bVar20 = aVar.e;
                    bVar20.e = typedArray.getLayoutDimension(index, bVar20.e);
                    break;
                case 22:
                    C0019d c0019d = aVar.c;
                    c0019d.b = typedArray.getInt(index, c0019d.b);
                    C0019d c0019d2 = aVar.c;
                    c0019d2.b = f[c0019d2.b];
                    break;
                case 23:
                    b bVar21 = aVar.e;
                    bVar21.d = typedArray.getLayoutDimension(index, bVar21.d);
                    break;
                case 24:
                    b bVar22 = aVar.e;
                    bVar22.H = typedArray.getDimensionPixelSize(index, bVar22.H);
                    break;
                case 25:
                    b bVar23 = aVar.e;
                    bVar23.j = j(typedArray, index, bVar23.j);
                    break;
                case 26:
                    b bVar24 = aVar.e;
                    bVar24.k = j(typedArray, index, bVar24.k);
                    break;
                case 27:
                    b bVar25 = aVar.e;
                    bVar25.G = typedArray.getInt(index, bVar25.G);
                    break;
                case 28:
                    b bVar26 = aVar.e;
                    bVar26.I = typedArray.getDimensionPixelSize(index, bVar26.I);
                    break;
                case 29:
                    b bVar27 = aVar.e;
                    bVar27.l = j(typedArray, index, bVar27.l);
                    break;
                case 30:
                    b bVar28 = aVar.e;
                    bVar28.m = j(typedArray, index, bVar28.m);
                    break;
                case 31:
                    b bVar29 = aVar.e;
                    bVar29.M = typedArray.getDimensionPixelSize(index, bVar29.M);
                    break;
                case 32:
                    b bVar30 = aVar.e;
                    bVar30.u = j(typedArray, index, bVar30.u);
                    break;
                case 33:
                    b bVar31 = aVar.e;
                    bVar31.v = j(typedArray, index, bVar31.v);
                    break;
                case 34:
                    b bVar32 = aVar.e;
                    bVar32.J = typedArray.getDimensionPixelSize(index, bVar32.J);
                    break;
                case 35:
                    b bVar33 = aVar.e;
                    bVar33.o = j(typedArray, index, bVar33.o);
                    break;
                case 36:
                    b bVar34 = aVar.e;
                    bVar34.n = j(typedArray, index, bVar34.n);
                    break;
                case 37:
                    b bVar35 = aVar.e;
                    bVar35.z = typedArray.getFloat(index, bVar35.z);
                    break;
                case 38:
                    aVar.a = typedArray.getResourceId(index, aVar.a);
                    break;
                case 39:
                    b bVar36 = aVar.e;
                    bVar36.W = typedArray.getFloat(index, bVar36.W);
                    break;
                case 40:
                    b bVar37 = aVar.e;
                    bVar37.V = typedArray.getFloat(index, bVar37.V);
                    break;
                case 41:
                    b bVar38 = aVar.e;
                    bVar38.X = typedArray.getInt(index, bVar38.X);
                    break;
                case 42:
                    b bVar39 = aVar.e;
                    bVar39.Y = typedArray.getInt(index, bVar39.Y);
                    break;
                case 43:
                    C0019d c0019d3 = aVar.c;
                    c0019d3.d = typedArray.getFloat(index, c0019d3.d);
                    break;
                case 44:
                    e eVar = aVar.f;
                    eVar.m = true;
                    eVar.n = typedArray.getDimension(index, eVar.n);
                    break;
                case 45:
                    e eVar2 = aVar.f;
                    eVar2.c = typedArray.getFloat(index, eVar2.c);
                    break;
                case 46:
                    e eVar3 = aVar.f;
                    eVar3.d = typedArray.getFloat(index, eVar3.d);
                    break;
                case 47:
                    e eVar4 = aVar.f;
                    eVar4.e = typedArray.getFloat(index, eVar4.e);
                    break;
                case 48:
                    e eVar5 = aVar.f;
                    eVar5.f = typedArray.getFloat(index, eVar5.f);
                    break;
                case 49:
                    e eVar6 = aVar.f;
                    eVar6.g = typedArray.getDimension(index, eVar6.g);
                    break;
                case 50:
                    e eVar7 = aVar.f;
                    eVar7.h = typedArray.getDimension(index, eVar7.h);
                    break;
                case 51:
                    e eVar8 = aVar.f;
                    eVar8.j = typedArray.getDimension(index, eVar8.j);
                    break;
                case 52:
                    e eVar9 = aVar.f;
                    eVar9.k = typedArray.getDimension(index, eVar9.k);
                    break;
                case 53:
                    e eVar10 = aVar.f;
                    eVar10.l = typedArray.getDimension(index, eVar10.l);
                    break;
                case 54:
                    b bVar40 = aVar.e;
                    bVar40.Z = typedArray.getInt(index, bVar40.Z);
                    break;
                case 55:
                    b bVar41 = aVar.e;
                    bVar41.a0 = typedArray.getInt(index, bVar41.a0);
                    break;
                case 56:
                    b bVar42 = aVar.e;
                    bVar42.b0 = typedArray.getDimensionPixelSize(index, bVar42.b0);
                    break;
                case 57:
                    b bVar43 = aVar.e;
                    bVar43.c0 = typedArray.getDimensionPixelSize(index, bVar43.c0);
                    break;
                case 58:
                    b bVar44 = aVar.e;
                    bVar44.d0 = typedArray.getDimensionPixelSize(index, bVar44.d0);
                    break;
                case 59:
                    b bVar45 = aVar.e;
                    bVar45.e0 = typedArray.getDimensionPixelSize(index, bVar45.e0);
                    break;
                case 60:
                    e eVar11 = aVar.f;
                    eVar11.b = typedArray.getFloat(index, eVar11.b);
                    break;
                case 61:
                    b bVar46 = aVar.e;
                    bVar46.B = j(typedArray, index, bVar46.B);
                    break;
                case 62:
                    b bVar47 = aVar.e;
                    bVar47.C = typedArray.getDimensionPixelSize(index, bVar47.C);
                    break;
                case 63:
                    b bVar48 = aVar.e;
                    bVar48.D = typedArray.getFloat(index, bVar48.D);
                    break;
                case 64:
                    c cVar = aVar.d;
                    cVar.b = j(typedArray, index, cVar.b);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        aVar.d.d = typedArray.getString(index);
                    } else {
                        aVar.d.d = n.a.c[typedArray.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    aVar.d.f = typedArray.getInt(index, 0);
                    break;
                case 67:
                    c cVar2 = aVar.d;
                    cVar2.i = typedArray.getFloat(index, cVar2.i);
                    break;
                case 68:
                    C0019d c0019d4 = aVar.c;
                    c0019d4.e = typedArray.getFloat(index, c0019d4.e);
                    break;
                case 69:
                    aVar.e.f0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    aVar.e.g0 = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    b bVar49 = aVar.e;
                    bVar49.h0 = typedArray.getInt(index, bVar49.h0);
                    break;
                case 73:
                    b bVar50 = aVar.e;
                    bVar50.i0 = typedArray.getDimensionPixelSize(index, bVar50.i0);
                    break;
                case 74:
                    aVar.e.l0 = typedArray.getString(index);
                    break;
                case 75:
                    b bVar51 = aVar.e;
                    bVar51.p0 = typedArray.getBoolean(index, bVar51.p0);
                    break;
                case 76:
                    c cVar3 = aVar.d;
                    cVar3.e = typedArray.getInt(index, cVar3.e);
                    break;
                case 77:
                    aVar.e.m0 = typedArray.getString(index);
                    break;
                case 78:
                    C0019d c0019d5 = aVar.c;
                    c0019d5.c = typedArray.getInt(index, c0019d5.c);
                    break;
                case 79:
                    c cVar4 = aVar.d;
                    cVar4.g = typedArray.getFloat(index, cVar4.g);
                    break;
                case 80:
                    b bVar52 = aVar.e;
                    bVar52.n0 = typedArray.getBoolean(index, bVar52.n0);
                    break;
                case 81:
                    b bVar53 = aVar.e;
                    bVar53.o0 = typedArray.getBoolean(index, bVar53.o0);
                    break;
                case 82:
                    c cVar5 = aVar.d;
                    cVar5.c = typedArray.getInteger(index, cVar5.c);
                    break;
                case 83:
                    e eVar12 = aVar.f;
                    eVar12.i = j(typedArray, index, eVar12.i);
                    break;
                case 84:
                    c cVar6 = aVar.d;
                    cVar6.k = typedArray.getInteger(index, cVar6.k);
                    break;
                case 85:
                    c cVar7 = aVar.d;
                    cVar7.j = typedArray.getFloat(index, cVar7.j);
                    break;
                case 86:
                    int i2 = typedArray.peekValue(index).type;
                    if (i2 == 1) {
                        aVar.d.n = typedArray.getResourceId(index, -1);
                        c cVar8 = aVar.d;
                        if (cVar8.n != -1) {
                            cVar8.m = -2;
                        }
                    } else if (i2 == 3) {
                        aVar.d.l = typedArray.getString(index);
                        if (aVar.d.l.indexOf("/") > 0) {
                            aVar.d.n = typedArray.getResourceId(index, -1);
                            aVar.d.m = -2;
                        } else {
                            aVar.d.m = -1;
                        }
                    } else {
                        c cVar9 = aVar.d;
                        cVar9.m = typedArray.getInteger(index, cVar9.n);
                    }
                    break;
                case 87:
                    StringBuilder sb = new StringBuilder();
                    sb.append("unused attribute 0x");
                    sb.append(Integer.toHexString(index));
                    sb.append("   ");
                    sb.append(g.get(index));
                    break;
                case 88:
                case 89:
                case 90:
                default:
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Unknown attribute 0x");
                    sb2.append(Integer.toHexString(index));
                    sb2.append("   ");
                    sb2.append(g.get(index));
                    break;
                case 91:
                    b bVar54 = aVar.e;
                    bVar54.f21s = j(typedArray, index, bVar54.f21s);
                    break;
                case 92:
                    b bVar55 = aVar.e;
                    bVar55.t = j(typedArray, index, bVar55.t);
                    break;
                case 93:
                    b bVar56 = aVar.e;
                    bVar56.N = typedArray.getDimensionPixelSize(index, bVar56.N);
                    break;
                case 94:
                    b bVar57 = aVar.e;
                    bVar57.U = typedArray.getDimensionPixelSize(index, bVar57.U);
                    break;
                case 95:
                    k(aVar.e, typedArray, index, 0);
                    break;
                case 96:
                    k(aVar.e, typedArray, index, 1);
                    break;
                case 97:
                    b bVar58 = aVar.e;
                    bVar58.q0 = typedArray.getInt(index, bVar58.q0);
                    break;
            }
        }
        b bVar59 = aVar.e;
        if (bVar59.l0 != null) {
            bVar59.k0 = null;
        }
    }

    private static void o(Context context, a aVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        a.C0018a c0018a = new a.C0018a();
        aVar.h = c0018a;
        aVar.d.a = false;
        aVar.e.b = false;
        aVar.c.a = false;
        aVar.f.a = false;
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (h.get(index)) {
                case 2:
                    c0018a.b(2, typedArray.getDimensionPixelSize(index, aVar.e.K));
                    break;
                case 3:
                case 4:
                case 9:
                case 10:
                case 25:
                case 26:
                case 29:
                case 30:
                case 32:
                case 33:
                case 35:
                case 36:
                case 61:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unknown attribute 0x");
                    sb.append(Integer.toHexString(index));
                    sb.append("   ");
                    sb.append(g.get(index));
                    break;
                case 5:
                    c0018a.c(5, typedArray.getString(index));
                    break;
                case 6:
                    c0018a.b(6, typedArray.getDimensionPixelOffset(index, aVar.e.E));
                    break;
                case 7:
                    c0018a.b(7, typedArray.getDimensionPixelOffset(index, aVar.e.F));
                    break;
                case 8:
                    c0018a.b(8, typedArray.getDimensionPixelSize(index, aVar.e.L));
                    break;
                case 11:
                    c0018a.b(11, typedArray.getDimensionPixelSize(index, aVar.e.R));
                    break;
                case 12:
                    c0018a.b(12, typedArray.getDimensionPixelSize(index, aVar.e.S));
                    break;
                case 13:
                    c0018a.b(13, typedArray.getDimensionPixelSize(index, aVar.e.O));
                    break;
                case 14:
                    c0018a.b(14, typedArray.getDimensionPixelSize(index, aVar.e.Q));
                    break;
                case 15:
                    c0018a.b(15, typedArray.getDimensionPixelSize(index, aVar.e.T));
                    break;
                case 16:
                    c0018a.b(16, typedArray.getDimensionPixelSize(index, aVar.e.P));
                    break;
                case 17:
                    c0018a.b(17, typedArray.getDimensionPixelOffset(index, aVar.e.f));
                    break;
                case 18:
                    c0018a.b(18, typedArray.getDimensionPixelOffset(index, aVar.e.g));
                    break;
                case 19:
                    c0018a.a(19, typedArray.getFloat(index, aVar.e.h));
                    break;
                case 20:
                    c0018a.a(20, typedArray.getFloat(index, aVar.e.y));
                    break;
                case 21:
                    c0018a.b(21, typedArray.getLayoutDimension(index, aVar.e.e));
                    break;
                case 22:
                    c0018a.b(22, f[typedArray.getInt(index, aVar.c.b)]);
                    break;
                case 23:
                    c0018a.b(23, typedArray.getLayoutDimension(index, aVar.e.d));
                    break;
                case 24:
                    c0018a.b(24, typedArray.getDimensionPixelSize(index, aVar.e.H));
                    break;
                case 27:
                    c0018a.b(27, typedArray.getInt(index, aVar.e.G));
                    break;
                case 28:
                    c0018a.b(28, typedArray.getDimensionPixelSize(index, aVar.e.I));
                    break;
                case 31:
                    c0018a.b(31, typedArray.getDimensionPixelSize(index, aVar.e.M));
                    break;
                case 34:
                    c0018a.b(34, typedArray.getDimensionPixelSize(index, aVar.e.J));
                    break;
                case 37:
                    c0018a.a(37, typedArray.getFloat(index, aVar.e.z));
                    break;
                case 38:
                    int resourceId = typedArray.getResourceId(index, aVar.a);
                    aVar.a = resourceId;
                    c0018a.b(38, resourceId);
                    break;
                case 39:
                    c0018a.a(39, typedArray.getFloat(index, aVar.e.W));
                    break;
                case 40:
                    c0018a.a(40, typedArray.getFloat(index, aVar.e.V));
                    break;
                case 41:
                    c0018a.b(41, typedArray.getInt(index, aVar.e.X));
                    break;
                case 42:
                    c0018a.b(42, typedArray.getInt(index, aVar.e.Y));
                    break;
                case 43:
                    c0018a.a(43, typedArray.getFloat(index, aVar.c.d));
                    break;
                case 44:
                    c0018a.d(44, true);
                    c0018a.a(44, typedArray.getDimension(index, aVar.f.n));
                    break;
                case 45:
                    c0018a.a(45, typedArray.getFloat(index, aVar.f.c));
                    break;
                case 46:
                    c0018a.a(46, typedArray.getFloat(index, aVar.f.d));
                    break;
                case 47:
                    c0018a.a(47, typedArray.getFloat(index, aVar.f.e));
                    break;
                case 48:
                    c0018a.a(48, typedArray.getFloat(index, aVar.f.f));
                    break;
                case 49:
                    c0018a.a(49, typedArray.getDimension(index, aVar.f.g));
                    break;
                case 50:
                    c0018a.a(50, typedArray.getDimension(index, aVar.f.h));
                    break;
                case 51:
                    c0018a.a(51, typedArray.getDimension(index, aVar.f.j));
                    break;
                case 52:
                    c0018a.a(52, typedArray.getDimension(index, aVar.f.k));
                    break;
                case 53:
                    c0018a.a(53, typedArray.getDimension(index, aVar.f.l));
                    break;
                case 54:
                    c0018a.b(54, typedArray.getInt(index, aVar.e.Z));
                    break;
                case 55:
                    c0018a.b(55, typedArray.getInt(index, aVar.e.a0));
                    break;
                case 56:
                    c0018a.b(56, typedArray.getDimensionPixelSize(index, aVar.e.b0));
                    break;
                case 57:
                    c0018a.b(57, typedArray.getDimensionPixelSize(index, aVar.e.c0));
                    break;
                case 58:
                    c0018a.b(58, typedArray.getDimensionPixelSize(index, aVar.e.d0));
                    break;
                case 59:
                    c0018a.b(59, typedArray.getDimensionPixelSize(index, aVar.e.e0));
                    break;
                case 60:
                    c0018a.a(60, typedArray.getFloat(index, aVar.f.b));
                    break;
                case 62:
                    c0018a.b(62, typedArray.getDimensionPixelSize(index, aVar.e.C));
                    break;
                case 63:
                    c0018a.a(63, typedArray.getFloat(index, aVar.e.D));
                    break;
                case 64:
                    c0018a.b(64, j(typedArray, index, aVar.d.b));
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        c0018a.c(65, typedArray.getString(index));
                    } else {
                        c0018a.c(65, n.a.c[typedArray.getInteger(index, 0)]);
                    }
                    break;
                case 66:
                    c0018a.b(66, typedArray.getInt(index, 0));
                    break;
                case 67:
                    c0018a.a(67, typedArray.getFloat(index, aVar.d.i));
                    break;
                case 68:
                    c0018a.a(68, typedArray.getFloat(index, aVar.c.e));
                    break;
                case 69:
                    c0018a.a(69, typedArray.getFloat(index, 1.0f));
                    break;
                case 70:
                    c0018a.a(70, typedArray.getFloat(index, 1.0f));
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    c0018a.b(72, typedArray.getInt(index, aVar.e.h0));
                    break;
                case 73:
                    c0018a.b(73, typedArray.getDimensionPixelSize(index, aVar.e.i0));
                    break;
                case 74:
                    c0018a.c(74, typedArray.getString(index));
                    break;
                case 75:
                    c0018a.d(75, typedArray.getBoolean(index, aVar.e.p0));
                    break;
                case 76:
                    c0018a.b(76, typedArray.getInt(index, aVar.d.e));
                    break;
                case 77:
                    c0018a.c(77, typedArray.getString(index));
                    break;
                case 78:
                    c0018a.b(78, typedArray.getInt(index, aVar.c.c));
                    break;
                case 79:
                    c0018a.a(79, typedArray.getFloat(index, aVar.d.g));
                    break;
                case 80:
                    c0018a.d(80, typedArray.getBoolean(index, aVar.e.n0));
                    break;
                case 81:
                    c0018a.d(81, typedArray.getBoolean(index, aVar.e.o0));
                    break;
                case 82:
                    c0018a.b(82, typedArray.getInteger(index, aVar.d.c));
                    break;
                case 83:
                    c0018a.b(83, j(typedArray, index, aVar.f.i));
                    break;
                case 84:
                    c0018a.b(84, typedArray.getInteger(index, aVar.d.k));
                    break;
                case 85:
                    c0018a.a(85, typedArray.getFloat(index, aVar.d.j));
                    break;
                case 86:
                    int i2 = typedArray.peekValue(index).type;
                    if (i2 == 1) {
                        aVar.d.n = typedArray.getResourceId(index, -1);
                        c0018a.b(89, aVar.d.n);
                        c cVar = aVar.d;
                        if (cVar.n != -1) {
                            cVar.m = -2;
                            c0018a.b(88, -2);
                        }
                    } else if (i2 == 3) {
                        aVar.d.l = typedArray.getString(index);
                        c0018a.c(90, aVar.d.l);
                        if (aVar.d.l.indexOf("/") > 0) {
                            aVar.d.n = typedArray.getResourceId(index, -1);
                            c0018a.b(89, aVar.d.n);
                            aVar.d.m = -2;
                            c0018a.b(88, -2);
                        } else {
                            aVar.d.m = -1;
                            c0018a.b(88, -1);
                        }
                    } else {
                        c cVar2 = aVar.d;
                        cVar2.m = typedArray.getInteger(index, cVar2.n);
                        c0018a.b(88, aVar.d.m);
                    }
                    break;
                case 87:
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("unused attribute 0x");
                    sb2.append(Integer.toHexString(index));
                    sb2.append("   ");
                    sb2.append(g.get(index));
                    break;
                case 93:
                    c0018a.b(93, typedArray.getDimensionPixelSize(index, aVar.e.N));
                    break;
                case 94:
                    c0018a.b(94, typedArray.getDimensionPixelSize(index, aVar.e.U));
                    break;
                case 95:
                    k(c0018a, typedArray, index, 0);
                    break;
                case 96:
                    k(c0018a, typedArray, index, 1);
                    break;
                case 97:
                    c0018a.b(97, typedArray.getInt(index, aVar.e.q0));
                    break;
                case 98:
                    if (r.b.y) {
                        int resourceId2 = typedArray.getResourceId(index, aVar.a);
                        aVar.a = resourceId2;
                        if (resourceId2 == -1) {
                            aVar.b = typedArray.getString(index);
                        }
                    } else if (typedArray.peekValue(index).type == 3) {
                        aVar.b = typedArray.getString(index);
                    } else {
                        aVar.a = typedArray.getResourceId(index, aVar.a);
                    }
                    break;
                case 99:
                    c0018a.d(99, typedArray.getBoolean(index, aVar.e.i));
                    break;
            }
        }
    }

    void c(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.e.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.e.containsKey(Integer.valueOf(id))) {
                StringBuilder sb = new StringBuilder();
                sb.append("id unknown ");
                sb.append(r.a.a(childAt));
            } else {
                if (this.d && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.e.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        a aVar = (a) this.e.get(Integer.valueOf(id));
                        if (aVar != null) {
                            if (childAt instanceof Barrier) {
                                aVar.e.j0 = 1;
                                Barrier barrier = (Barrier) childAt;
                                barrier.setId(id);
                                barrier.setType(aVar.e.h0);
                                barrier.setMargin(aVar.e.i0);
                                barrier.setAllowsGoneWidget(aVar.e.p0);
                                b bVar = aVar.e;
                                int[] iArr = bVar.k0;
                                if (iArr != null) {
                                    barrier.setReferencedIds(iArr);
                                } else {
                                    String str = bVar.l0;
                                    if (str != null) {
                                        bVar.k0 = f(barrier, str);
                                        barrier.setReferencedIds(aVar.e.k0);
                                    }
                                }
                            }
                            ConstraintLayout.b bVar2 = (ConstraintLayout.b) childAt.getLayoutParams();
                            bVar2.a();
                            aVar.b(bVar2);
                            if (z) {
                                androidx.constraintlayout.widget.a.c(childAt, aVar.g);
                            }
                            childAt.setLayoutParams(bVar2);
                            C0019d c0019d = aVar.c;
                            if (c0019d.c == 0) {
                                childAt.setVisibility(c0019d.b);
                            }
                            childAt.setAlpha(aVar.c.d);
                            childAt.setRotation(aVar.f.b);
                            childAt.setRotationX(aVar.f.c);
                            childAt.setRotationY(aVar.f.d);
                            childAt.setScaleX(aVar.f.e);
                            childAt.setScaleY(aVar.f.f);
                            e eVar = aVar.f;
                            if (eVar.i != -1) {
                                View viewFindViewById = ((View) childAt.getParent()).findViewById(aVar.f.i);
                                if (viewFindViewById != null) {
                                    float top = (viewFindViewById.getTop() + viewFindViewById.getBottom()) / 2.0f;
                                    float left = (viewFindViewById.getLeft() + viewFindViewById.getRight()) / 2.0f;
                                    if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                        float left2 = left - childAt.getLeft();
                                        float top2 = top - childAt.getTop();
                                        childAt.setPivotX(left2);
                                        childAt.setPivotY(top2);
                                    }
                                }
                            } else {
                                if (!Float.isNaN(eVar.g)) {
                                    childAt.setPivotX(aVar.f.g);
                                }
                                if (!Float.isNaN(aVar.f.h)) {
                                    childAt.setPivotY(aVar.f.h);
                                }
                            }
                            childAt.setTranslationX(aVar.f.j);
                            childAt.setTranslationY(aVar.f.k);
                            childAt.setTranslationZ(aVar.f.l);
                            e eVar2 = aVar.f;
                            if (eVar2.m) {
                                childAt.setElevation(eVar2.n);
                            }
                        }
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("WARNING NO CONSTRAINTS for view ");
                        sb2.append(id);
                    }
                }
            }
        }
        for (Integer num : hashSet) {
            a aVar2 = (a) this.e.get(num);
            if (aVar2 != null) {
                if (aVar2.e.j0 == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    b bVar3 = aVar2.e;
                    int[] iArr2 = bVar3.k0;
                    if (iArr2 != null) {
                        barrier2.setReferencedIds(iArr2);
                    } else {
                        String str2 = bVar3.l0;
                        if (str2 != null) {
                            bVar3.k0 = f(barrier2, str2);
                            barrier2.setReferencedIds(aVar2.e.k0);
                        }
                    }
                    barrier2.setType(aVar2.e.h0);
                    barrier2.setMargin(aVar2.e.i0);
                    ConstraintLayout.b bVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                    barrier2.m();
                    aVar2.b(bVarGenerateDefaultLayoutParams);
                    constraintLayout.addView(barrier2, bVarGenerateDefaultLayoutParams);
                }
                if (aVar2.e.a) {
                    View fVar = new f(constraintLayout.getContext());
                    fVar.setId(num.intValue());
                    ConstraintLayout.b bVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                    aVar2.b(bVarGenerateDefaultLayoutParams2);
                    constraintLayout.addView(fVar, bVarGenerateDefaultLayoutParams2);
                }
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt2 = constraintLayout.getChildAt(i2);
            if (childAt2 instanceof androidx.constraintlayout.widget.b) {
                ((androidx.constraintlayout.widget.b) childAt2).d(constraintLayout);
            }
        }
    }

    public void d(Context context, int i) {
        e((ConstraintLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null));
    }

    public void e(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.e.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.b bVar = (ConstraintLayout.b) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.d && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.e.containsKey(Integer.valueOf(id))) {
                this.e.put(Integer.valueOf(id), new a());
            }
            a aVar = (a) this.e.get(Integer.valueOf(id));
            if (aVar != null) {
                aVar.g = androidx.constraintlayout.widget.a.a(this.c, childAt);
                aVar.d(id, bVar);
                aVar.c.b = childAt.getVisibility();
                aVar.c.d = childAt.getAlpha();
                aVar.f.b = childAt.getRotation();
                aVar.f.c = childAt.getRotationX();
                aVar.f.d = childAt.getRotationY();
                aVar.f.e = childAt.getScaleX();
                aVar.f.f = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    e eVar = aVar.f;
                    eVar.g = pivotX;
                    eVar.h = pivotY;
                }
                aVar.f.j = childAt.getTranslationX();
                aVar.f.k = childAt.getTranslationY();
                aVar.f.l = childAt.getTranslationZ();
                e eVar2 = aVar.f;
                if (eVar2.m) {
                    eVar2.n = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    aVar.e.p0 = barrier.getAllowsGoneWidget();
                    aVar.e.k0 = barrier.getReferencedIds();
                    aVar.e.h0 = barrier.getType();
                    aVar.e.i0 = barrier.getMargin();
                }
            }
        }
    }

    public void h(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    a aVarG = g(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        aVarG.e.a = true;
                    }
                    this.e.put(Integer.valueOf(aVarG.a), aVarG);
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void i(Context context, XmlPullParser xmlPullParser) {
        a aVarG;
        try {
            int eventType = xmlPullParser.getEventType();
            a aVar = null;
            while (eventType != 1) {
                if (eventType == 0) {
                    xmlPullParser.getName();
                } else if (eventType == 2) {
                    switch (xmlPullParser.getName()) {
                        case "Constraint":
                            aVarG = g(context, Xml.asAttributeSet(xmlPullParser), false);
                            break;
                        case "ConstraintOverride":
                            aVarG = g(context, Xml.asAttributeSet(xmlPullParser), true);
                            break;
                        case "Guideline":
                            aVarG = g(context, Xml.asAttributeSet(xmlPullParser), false);
                            b bVar = aVarG.e;
                            bVar.a = true;
                            bVar.b = true;
                            break;
                        case "Barrier":
                            aVarG = g(context, Xml.asAttributeSet(xmlPullParser), false);
                            aVarG.e.j0 = 1;
                            break;
                        case "PropertySet":
                            if (aVar == null) {
                                throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser.getLineNumber());
                            }
                            aVar.c.b(context, Xml.asAttributeSet(xmlPullParser));
                            continue;
                            break;
                            break;
                        case "Transform":
                            if (aVar == null) {
                                throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser.getLineNumber());
                            }
                            aVar.f.b(context, Xml.asAttributeSet(xmlPullParser));
                            continue;
                            break;
                            break;
                        case "Layout":
                            if (aVar == null) {
                                throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser.getLineNumber());
                            }
                            aVar.e.b(context, Xml.asAttributeSet(xmlPullParser));
                            continue;
                            break;
                            break;
                        case "Motion":
                            if (aVar == null) {
                                throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser.getLineNumber());
                            }
                            aVar.d.b(context, Xml.asAttributeSet(xmlPullParser));
                            continue;
                            break;
                            break;
                        case "CustomAttribute":
                        case "CustomMethod":
                            if (aVar == null) {
                                throw new RuntimeException("XML parser error must be within a Constraint " + xmlPullParser.getLineNumber());
                            }
                            androidx.constraintlayout.widget.a.b(context, xmlPullParser, aVar.g);
                            continue;
                            break;
                            break;
                        default:
                            continue;
                            break;
                    }
                    aVar = aVarG;
                } else if (eventType == 3) {
                    String lowerCase = xmlPullParser.getName().toLowerCase(Locale.ROOT);
                    switch (lowerCase.hashCode()) {
                        case -2075718416:
                            if (lowerCase.equals("guideline")) {
                            }
                            break;
                        case -190376483:
                            if (lowerCase.equals("constraint")) {
                            }
                            break;
                        case 426575017:
                            if (lowerCase.equals("constraintoverride")) {
                            }
                            break;
                        case 2146106725:
                            if (lowerCase.equals("constraintset")) {
                            }
                            break;
                    }
                    if (r4 == 0) {
                        return;
                    }
                    if (r4 == 1 || r4 == 2 || r4 == 3) {
                        this.e.put(Integer.valueOf(aVar.a), aVar);
                        aVar = null;
                    }
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }
}
