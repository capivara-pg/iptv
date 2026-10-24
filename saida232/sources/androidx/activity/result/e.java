package androidx.activity.result;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Parcelable {
    public static final Parcelable.Creator<e> CREATOR = new a();
    private final IntentSender a;
    private final Intent b;
    private final int c;
    private final int d;

    class a implements Parcelable.Creator {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public e[] newArray(int i) {
            return new e[i];
        }
    }

    public static final class b {
        private IntentSender a;
        private Intent b;
        private int c;
        private int d;

        public b(IntentSender intentSender) {
            this.a = intentSender;
        }

        public e a() {
            return new e(this.a, this.b, this.c, this.d);
        }

        public b b(Intent intent) {
            this.b = intent;
            return this;
        }

        public b c(int i, int i2) {
            this.d = i;
            this.c = i2;
            return this;
        }
    }

    e(IntentSender intentSender, Intent intent, int i, int i2) {
        this.a = intentSender;
        this.b = intent;
        this.c = i;
        this.d = i2;
    }

    public Intent a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public IntentSender d() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeParcelable(this.b, i);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
    }

    e(Parcel parcel) {
        this.a = (IntentSender) parcel.readParcelable(IntentSender.class.getClassLoader());
        this.b = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
        this.c = parcel.readInt();
        this.d = parcel.readInt();
    }
}
