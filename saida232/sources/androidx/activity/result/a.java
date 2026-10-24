package androidx.activity.result;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0006a();
    private final int a;
    private final Intent b;

    /* JADX INFO: renamed from: androidx.activity.result.a$a, reason: collision with other inner class name */
    class C0006a implements Parcelable.Creator {
        C0006a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i) {
            return new a[i];
        }
    }

    public a(int i, Intent intent) {
        this.a = i;
        this.b = intent;
    }

    public static String c(int i) {
        if (i != -1) {
            return i != 0 ? String.valueOf(i) : "RESULT_CANCELED";
        }
        return "RESULT_OK";
    }

    public Intent a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "ActivityResult{resultCode=" + c(this.a) + ", data=" + this.b + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b == null ? 0 : 1);
        Intent intent = this.b;
        if (intent != null) {
            intent.writeToParcel(parcel, i);
        }
    }

    a(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt() == 0 ? null : (Intent) Intent.CREATOR.createFromParcel(parcel);
    }
}
