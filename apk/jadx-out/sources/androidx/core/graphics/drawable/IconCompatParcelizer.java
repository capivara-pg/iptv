package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import androidx.versionedparcelable.a;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(a parcel) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.a = parcel.p(iconCompat.a, 1);
        iconCompat.c = parcel.j(iconCompat.c, 2);
        iconCompat.d = parcel.r(iconCompat.d, 3);
        iconCompat.e = parcel.p(iconCompat.e, 4);
        iconCompat.f = parcel.p(iconCompat.f, 5);
        iconCompat.g = (ColorStateList) parcel.r(iconCompat.g, 6);
        iconCompat.i = parcel.t(iconCompat.i, 7);
        iconCompat.j = parcel.t(iconCompat.j, 8);
        iconCompat.n();
        return iconCompat;
    }

    public static void write(IconCompat obj, a parcel) {
        parcel.x(true, true);
        obj.o(parcel.f());
        int i = obj.a;
        if (-1 != i) {
            parcel.F(i, 1);
        }
        byte[] bArr = obj.c;
        if (bArr != null) {
            parcel.B(bArr, 2);
        }
        Parcelable parcelable = obj.d;
        if (parcelable != null) {
            parcel.H(parcelable, 3);
        }
        int i2 = obj.e;
        if (i2 != 0) {
            parcel.F(i2, 4);
        }
        int i3 = obj.f;
        if (i3 != 0) {
            parcel.F(i3, 5);
        }
        ColorStateList colorStateList = obj.g;
        if (colorStateList != null) {
            parcel.H(colorStateList, 6);
        }
        String str = obj.i;
        if (str != null) {
            parcel.J(str, 7);
        }
        String str2 = obj.j;
        if (str2 != null) {
            parcel.J(str2, 8);
        }
    }
}
