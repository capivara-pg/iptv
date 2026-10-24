package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class p implements Parcelable {
    public static final Parcelable.Creator<p> CREATOR = new a();
    ArrayList a;
    ArrayList b;
    b[] c;
    int d;
    String e;
    ArrayList f;
    ArrayList g;
    ArrayList h;

    class a implements Parcelable.Creator {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public p createFromParcel(Parcel parcel) {
            return new p(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public p[] newArray(int i) {
            return new p[i];
        }
    }

    public p() {
        this.e = null;
        this.f = new ArrayList();
        this.g = new ArrayList();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.a);
        parcel.writeStringList(this.b);
        parcel.writeTypedArray(this.c, i);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
        parcel.writeStringList(this.f);
        parcel.writeTypedList(this.g);
        parcel.writeTypedList(this.h);
    }

    public p(Parcel parcel) {
        this.e = null;
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.a = parcel.createTypedArrayList(t.CREATOR);
        this.b = parcel.createStringArrayList();
        this.c = (b[]) parcel.createTypedArray(b.CREATOR);
        this.d = parcel.readInt();
        this.e = parcel.readString();
        this.f = parcel.createStringArrayList();
        this.g = parcel.createTypedArrayList(Bundle.CREATOR);
        this.h = parcel.createTypedArrayList(n.m.CREATOR);
    }
}
