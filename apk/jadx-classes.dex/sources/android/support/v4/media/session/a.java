package android.support.v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: android.support.v4.media.session.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0002a extends Binder implements a {

        /* JADX INFO: renamed from: android.support.v4.media.session.a$a$a, reason: collision with other inner class name */
        private static class C0003a implements a {
            public static a b;
            private IBinder a;

            C0003a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.support.v4.media.session.a
            public void G(ParcelableVolumeInfo parcelableVolumeInfo) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (parcelableVolumeInfo != null) {
                        parcelObtain.writeInt(1);
                        parcelableVolumeInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.a.transact(8, parcelObtain, null, 1) || AbstractBinderC0002a.j0() == null) {
                        return;
                    }
                    AbstractBinderC0002a.j0().G(parcelableVolumeInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.a
            public void P(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.a.transact(7, parcelObtain, null, 1) || AbstractBinderC0002a.j0() == null) {
                        return;
                    }
                    AbstractBinderC0002a.j0().P(bundle);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }

            @Override // android.support.v4.media.session.a
            public void h0(PlaybackStateCompat playbackStateCompat) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (playbackStateCompat != null) {
                        parcelObtain.writeInt(1);
                        playbackStateCompat.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.a.transact(3, parcelObtain, null, 1) || AbstractBinderC0002a.j0() == null) {
                        return;
                    }
                    AbstractBinderC0002a.j0().h0(playbackStateCompat);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.a
            public void t() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (this.a.transact(2, parcelObtain, null, 1) || AbstractBinderC0002a.j0() == null) {
                        return;
                    }
                    AbstractBinderC0002a.j0().t();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.a
            public void v(MediaMetadataCompat mediaMetadataCompat) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
                    if (mediaMetadataCompat != null) {
                        parcelObtain.writeInt(1);
                        mediaMetadataCompat.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.a.transact(4, parcelObtain, null, 1) || AbstractBinderC0002a.j0() == null) {
                        return;
                    }
                    AbstractBinderC0002a.j0().v(mediaMetadataCompat);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public AbstractBinderC0002a() {
            attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
        }

        public static a i0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0003a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a j0() {
            return C0003a.b;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("android.support.v4.media.session.IMediaControllerCallback");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    onEvent(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    t();
                    return true;
                case 3:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    h0(parcel.readInt() != 0 ? PlaybackStateCompat.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    v(parcel.readInt() != 0 ? MediaMetadataCompat.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    l(parcel.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR));
                    return true;
                case 6:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    W(parcel.readInt() != 0 ? (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    P(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    G(parcel.readInt() != 0 ? ParcelableVolumeInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    Z(parcel.readInt());
                    return true;
                case 10:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    V(parcel.readInt() != 0);
                    return true;
                case 11:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    T(parcel.readInt() != 0);
                    return true;
                case 12:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    z(parcel.readInt());
                    return true;
                case 13:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                    k();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void G(ParcelableVolumeInfo parcelableVolumeInfo);

    void P(Bundle bundle);

    void T(boolean z);

    void V(boolean z);

    void W(CharSequence charSequence);

    void Z(int i);

    void h0(PlaybackStateCompat playbackStateCompat);

    void k();

    void l(List list);

    void onEvent(String str, Bundle bundle);

    void t();

    void v(MediaMetadataCompat mediaMetadataCompat);

    void z(int i);
}
