package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();
    final int[] a;
    final ArrayList b;
    final int[] c;
    final int[] d;
    final int e;
    final String f;
    final int g;
    final int h;
    final CharSequence i;
    final int j;
    final CharSequence k;
    final ArrayList l;
    final ArrayList m;
    final boolean n;

    class a implements Parcelable.Creator {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    public b(androidx.fragment.app.a aVar) {
        int size = aVar.c.size();
        this.a = new int[size * 5];
        if (!aVar.i) {
            throw new IllegalStateException("Not on back stack");
        }
        this.b = new ArrayList(size);
        this.c = new int[size];
        this.d = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            y.a aVar2 = (y.a) aVar.c.get(i);
            int i3 = i2 + 1;
            this.a[i2] = aVar2.a;
            ArrayList arrayList = this.b;
            Fragment fragment = aVar2.b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.a;
            int i4 = i3 + 1;
            iArr[i3] = aVar2.c;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.d;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.e;
            iArr[i6] = aVar2.f;
            this.c[i] = aVar2.g.ordinal();
            this.d[i] = aVar2.h.ordinal();
            i++;
            i2 = i6 + 1;
        }
        this.e = aVar.h;
        this.f = aVar.k;
        this.g = aVar.v;
        this.h = aVar.l;
        this.i = aVar.m;
        this.j = aVar.n;
        this.k = aVar.o;
        this.l = aVar.p;
        this.m = aVar.q;
        this.n = aVar.r;
    }

    public androidx.fragment.app.a a(o oVar) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(oVar);
        int i = 0;
        int i2 = 0;
        while (i < this.a.length) {
            y.a aVar2 = new y.a();
            int i3 = i + 1;
            aVar2.a = this.a[i];
            if (o.F0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Instantiate ");
                sb.append(aVar);
                sb.append(" op #");
                sb.append(i2);
                sb.append(" base fragment #");
                sb.append(this.a[i3]);
            }
            String str = (String) this.b.get(i2);
            if (str != null) {
                aVar2.b = oVar.f0(str);
            } else {
                aVar2.b = null;
            }
            aVar2.g = androidx.lifecycle.d.c.values()[this.c[i2]];
            aVar2.h = androidx.lifecycle.d.c.values()[this.d[i2]];
            int[] iArr = this.a;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            aVar2.c = i5;
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            aVar2.d = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar2.e = i9;
            int i10 = iArr[i8];
            aVar2.f = i10;
            aVar.d = i5;
            aVar.e = i7;
            aVar.f = i9;
            aVar.g = i10;
            aVar.f(aVar2);
            i2++;
            i = i8 + 1;
        }
        aVar.h = this.e;
        aVar.k = this.f;
        aVar.v = this.g;
        aVar.i = true;
        aVar.l = this.h;
        aVar.m = this.i;
        aVar.n = this.j;
        aVar.o = this.k;
        aVar.p = this.l;
        aVar.q = this.m;
        aVar.r = this.n;
        aVar.v(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.a);
        parcel.writeStringList(this.b);
        parcel.writeIntArray(this.c);
        parcel.writeIntArray(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, parcel, 0);
        parcel.writeInt(this.j);
        TextUtils.writeToParcel(this.k, parcel, 0);
        parcel.writeStringList(this.l);
        parcel.writeStringList(this.m);
        parcel.writeInt(this.n ? 1 : 0);
    }

    public b(Parcel parcel) {
        this.a = parcel.createIntArray();
        this.b = parcel.createStringArrayList();
        this.c = parcel.createIntArray();
        this.d = parcel.createIntArray();
        this.e = parcel.readInt();
        this.f = parcel.readString();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = parcel.readInt();
        this.k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.l = parcel.createStringArrayList();
        this.m = parcel.createStringArrayList();
        this.n = parcel.readInt() != 0;
    }
}
