package androidx.preference;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;
import t.i;

/* JADX INFO: loaded from: classes.dex */
public class Preference implements Comparable<Preference> {
    private List A;
    private b B;
    private final View.OnClickListener C;
    private Context a;
    private int b;
    private int c;
    private CharSequence d;
    private CharSequence e;
    private int f;
    private String g;
    private Intent h;
    private String i;
    private boolean j;
    private boolean k;
    private boolean l;
    private String m;
    private Object n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f31s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private int y;
    private int z;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Preference.this.y(view);
        }
    }

    public interface b {
        CharSequence a(Preference preference);
    }

    public Preference(Context context, AttributeSet attributeSet, int i, int i2) {
        this.b = Integer.MAX_VALUE;
        this.c = 0;
        this.j = true;
        this.k = true;
        this.l = true;
        this.o = true;
        this.p = true;
        this.q = true;
        this.r = true;
        this.f31s = true;
        this.u = true;
        this.x = true;
        int i3 = R$layout.preference;
        this.y = i3;
        this.C = new a();
        this.a = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.N, i, i2);
        this.f = i.n(typedArrayObtainStyledAttributes, R$styleable.Preference_icon, R$styleable.Preference_android_icon, 0);
        this.g = i.o(typedArrayObtainStyledAttributes, R$styleable.Preference_key, R$styleable.Preference_android_key);
        this.d = i.p(typedArrayObtainStyledAttributes, R$styleable.Preference_title, R$styleable.Preference_android_title);
        this.e = i.p(typedArrayObtainStyledAttributes, R$styleable.Preference_summary, R$styleable.Preference_android_summary);
        this.b = i.d(typedArrayObtainStyledAttributes, R$styleable.Preference_order, R$styleable.Preference_android_order, Integer.MAX_VALUE);
        this.i = i.o(typedArrayObtainStyledAttributes, R$styleable.Preference_fragment, R$styleable.Preference_android_fragment);
        this.y = i.n(typedArrayObtainStyledAttributes, R$styleable.Preference_layout, R$styleable.Preference_android_layout, i3);
        this.z = i.n(typedArrayObtainStyledAttributes, R$styleable.Preference_widgetLayout, R$styleable.Preference_android_widgetLayout, 0);
        this.j = i.b(typedArrayObtainStyledAttributes, R$styleable.Preference_enabled, R$styleable.Preference_android_enabled, true);
        this.k = i.b(typedArrayObtainStyledAttributes, R$styleable.Preference_selectable, R$styleable.Preference_android_selectable, true);
        this.l = i.b(typedArrayObtainStyledAttributes, R$styleable.Preference_persistent, R$styleable.Preference_android_persistent, true);
        this.m = i.o(typedArrayObtainStyledAttributes, R$styleable.Preference_dependency, R$styleable.Preference_android_dependency);
        int i4 = R$styleable.Preference_allowDividerAbove;
        this.r = i.b(typedArrayObtainStyledAttributes, i4, i4, this.k);
        int i5 = R$styleable.Preference_allowDividerBelow;
        this.f31s = i.b(typedArrayObtainStyledAttributes, i5, i5, this.k);
        int i6 = R$styleable.Preference_defaultValue;
        if (typedArrayObtainStyledAttributes.hasValue(i6)) {
            this.n = v(typedArrayObtainStyledAttributes, i6);
        } else {
            int i7 = R$styleable.Preference_android_defaultValue;
            if (typedArrayObtainStyledAttributes.hasValue(i7)) {
                this.n = v(typedArrayObtainStyledAttributes, i7);
            }
        }
        this.x = i.b(typedArrayObtainStyledAttributes, R$styleable.Preference_shouldDisableView, R$styleable.Preference_android_shouldDisableView, true);
        int i8 = R$styleable.Preference_singleLineTitle;
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i8);
        this.t = zHasValue;
        if (zHasValue) {
            this.u = i.b(typedArrayObtainStyledAttributes, i8, R$styleable.Preference_android_singleLineTitle, true);
        }
        this.v = i.b(typedArrayObtainStyledAttributes, R$styleable.Preference_iconSpaceReserved, R$styleable.Preference_android_iconSpaceReserved, false);
        int i9 = R$styleable.Preference_isPreferenceVisible;
        this.q = i.b(typedArrayObtainStyledAttributes, i9, i9, true);
        int i10 = R$styleable.Preference_enableCopying;
        this.w = i.b(typedArrayObtainStyledAttributes, i10, i10, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    protected boolean A(int i) {
        if (!E()) {
            return false;
        }
        if (i == h(~i)) {
            return true;
        }
        j();
        throw null;
    }

    protected boolean B(String str) {
        if (!E()) {
            return false;
        }
        if (TextUtils.equals(str, i(null))) {
            return true;
        }
        j();
        throw null;
    }

    public final void C(b bVar) {
        this.B = bVar;
        r();
    }

    public boolean D() {
        return !p();
    }

    protected boolean E() {
        return false;
    }

    public boolean a(Object obj) {
        return true;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(Preference preference) {
        int i = this.b;
        int i2 = preference.b;
        if (i != i2) {
            return i - i2;
        }
        CharSequence charSequence = this.d;
        CharSequence charSequence2 = preference.d;
        if (charSequence == charSequence2) {
            return 0;
        }
        if (charSequence == null) {
            return 1;
        }
        if (charSequence2 == null) {
            return -1;
        }
        return charSequence.toString().compareToIgnoreCase(preference.d.toString());
    }

    public Context c() {
        return this.a;
    }

    StringBuilder d() {
        StringBuilder sb = new StringBuilder();
        CharSequence charSequenceN = n();
        if (!TextUtils.isEmpty(charSequenceN)) {
            sb.append(charSequenceN);
            sb.append(' ');
        }
        CharSequence charSequenceL = l();
        if (!TextUtils.isEmpty(charSequenceL)) {
            sb.append(charSequenceL);
            sb.append(' ');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb;
    }

    public String e() {
        return this.i;
    }

    public Intent f() {
        return this.h;
    }

    protected boolean g(boolean z) {
        if (!E()) {
            return z;
        }
        j();
        throw null;
    }

    protected int h(int i) {
        if (!E()) {
            return i;
        }
        j();
        throw null;
    }

    protected String i(String str) {
        if (!E()) {
            return str;
        }
        j();
        throw null;
    }

    public r0.a j() {
        return null;
    }

    public r0.b k() {
        return null;
    }

    public CharSequence l() {
        return m() != null ? m().a(this) : this.e;
    }

    public final b m() {
        return this.B;
    }

    public CharSequence n() {
        return this.d;
    }

    public boolean o() {
        return !TextUtils.isEmpty(this.g);
    }

    public boolean p() {
        return this.j && this.o && this.p;
    }

    public boolean q() {
        return this.k;
    }

    protected void r() {
    }

    public void s(boolean z) {
        List list = this.A;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((Preference) list.get(i)).u(this, z);
        }
    }

    protected void t() {
    }

    public String toString() {
        return d().toString();
    }

    public void u(Preference preference, boolean z) {
        if (this.o == z) {
            this.o = !z;
            s(D());
            r();
        }
    }

    protected Object v(TypedArray typedArray, int i) {
        return null;
    }

    public void w(Preference preference, boolean z) {
        if (this.p == z) {
            this.p = !z;
            s(D());
            r();
        }
    }

    public void x() {
        if (p() && q()) {
            t();
            k();
            if (this.h != null) {
                c().startActivity(this.h);
            }
        }
    }

    protected void y(View view) {
        x();
    }

    protected boolean z(boolean z) {
        if (!E()) {
            return false;
        }
        if (z == g(!z)) {
            return true;
        }
        j();
        throw null;
    }

    public Preference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public Preference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, i.a(context, R$attr.preferenceStyle, R.attr.preferenceStyle));
    }
}
