package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.a;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a parcel) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.a = (IconCompat) parcel.v(remoteActionCompat.a, 1);
        remoteActionCompat.b = parcel.l(remoteActionCompat.b, 2);
        remoteActionCompat.c = parcel.l(remoteActionCompat.c, 3);
        remoteActionCompat.d = (PendingIntent) parcel.r(remoteActionCompat.d, 4);
        remoteActionCompat.e = parcel.h(remoteActionCompat.e, 5);
        remoteActionCompat.f = parcel.h(remoteActionCompat.f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat obj, a parcel) {
        parcel.x(false, false);
        parcel.M(obj.a, 1);
        parcel.D(obj.b, 2);
        parcel.D(obj.c, 3);
        parcel.H(obj.d, 4);
        parcel.z(obj.e, 5);
        parcel.z(obj.f, 6);
    }
}
