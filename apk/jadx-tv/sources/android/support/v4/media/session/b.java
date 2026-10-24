package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.appcompat.R$styleable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: android.support.v4.media.session.b$a$a, reason: collision with other inner class name */
        private static class C0004a implements b {
            public static b b;
            private IBinder a;

            C0004a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.support.v4.media.session.b
            public void H(android.support.v4.media.session.a aVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.a.transact(3, parcelObtain, parcelObtain2, 0) || a.j0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.j0().H(aVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public void R(android.support.v4.media.session.a aVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.a.transact(4, parcelObtain, parcelObtain2, 0) || a.j0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.j0().R(aVar);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public void a0() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.a.transact(13, parcelObtain, parcelObtain2, 0) || a.j0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.j0().a0();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }

            @Override // android.support.v4.media.session.b
            public PlaybackStateCompat e() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.a.transact(28, parcelObtain, parcelObtain2, 0) && a.j0() != null) {
                        return a.j0().e();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PlaybackStateCompat.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public MediaMetadataCompat getMetadata() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.a.transact(27, parcelObtain, parcelObtain2, 0) && a.j0() != null) {
                        return a.j0().getMetadata();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? MediaMetadataCompat.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public PendingIntent i() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.a.transact(8, parcelObtain, parcelObtain2, 0) && a.j0() != null) {
                        return a.j0().i();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public void pause() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.a.transact(18, parcelObtain, parcelObtain2, 0) || a.j0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.j0().pause();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.support.v4.media.session.b
            public void stop() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.a.transact(19, parcelObtain, parcelObtain2, 0) || a.j0() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.j0().stop();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "android.support.v4.media.session.IMediaSession");
        }

        public static b i0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0004a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b j0() {
            return C0004a.b;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("android.support.v4.media.session.IMediaSession");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    A(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? MediaSessionCompat.ResultReceiverWrapper.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zR = r(parcel.readInt() != 0 ? (KeyEvent) KeyEvent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zR ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    H(android.support.v4.media.session.a.AbstractBinderC0002a.i0(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    R(android.support.v4.media.session.a.AbstractBinderC0002a.i0(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zL = L();
                    parcel2.writeNoException();
                    parcel2.writeInt(zL ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String strF = F();
                    parcel2.writeNoException();
                    parcel2.writeString(strF);
                    return true;
                case 7:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    String tag = getTag();
                    parcel2.writeNoException();
                    parcel2.writeString(tag);
                    return true;
                case 8:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PendingIntent pendingIntentI = i();
                    parcel2.writeNoException();
                    if (pendingIntentI != null) {
                        parcel2.writeInt(1);
                        pendingIntentI.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    long jD0 = d0();
                    parcel2.writeNoException();
                    parcel2.writeLong(jD0);
                    return true;
                case 10:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    ParcelableVolumeInfo parcelableVolumeInfoF0 = f0();
                    parcel2.writeNoException();
                    if (parcelableVolumeInfoF0 != null) {
                        parcel2.writeInt(1);
                        parcelableVolumeInfoF0.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    X(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    J(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    a0();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    o(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    p(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    q(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    C(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    pause();
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    stop();
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    next();
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    previous();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    S();
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    B();
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    seekTo(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    I(parcel.readInt() != 0 ? RatingCompat.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    f(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    MediaMetadataCompat metadata = getMetadata();
                    parcel2.writeNoException();
                    if (metadata != null) {
                        parcel2.writeInt(1);
                        metadata.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case R$styleable.AppCompatTheme_actionModeStyle /* 28 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    PlaybackStateCompat playbackStateCompatE = e();
                    parcel2.writeNoException();
                    if (playbackStateCompatE != null) {
                        parcel2.writeInt(1);
                        playbackStateCompatE.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case R$styleable.AppCompatTheme_actionModeWebSearchDrawable /* 29 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    List listB0 = b0();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(listB0);
                    return true;
                case R$styleable.AppCompatTheme_actionOverflowButtonStyle /* 30 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    CharSequence charSequenceQ = Q();
                    parcel2.writeNoException();
                    if (charSequenceQ != null) {
                        parcel2.writeInt(1);
                        TextUtils.writeToParcel(charSequenceQ, parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 31:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Bundle extras = getExtras();
                    parcel2.writeNoException();
                    if (extras != null) {
                        parcel2.writeInt(1);
                        extras.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 32:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int iJ = j();
                    parcel2.writeNoException();
                    parcel2.writeInt(iJ);
                    return true;
                case R$styleable.AppCompatTheme_alertDialogButtonGroupStyle /* 33 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    O();
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_alertDialogCenterButtons /* 34 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    m(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_alertDialogStyle /* 35 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    N(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_alertDialogTheme /* 36 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    h(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_autoCompleteTextViewStyle /* 37 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int iE0 = e0();
                    parcel2.writeNoException();
                    parcel2.writeInt(iE0);
                    return true;
                case R$styleable.AppCompatTheme_borderlessButtonStyle /* 38 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zG = g();
                    parcel2.writeNoException();
                    parcel2.writeInt(zG ? 1 : 0);
                    return true;
                case R$styleable.AppCompatTheme_buttonBarButtonStyle /* 39 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    c0(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_buttonBarNegativeButtonStyle /* 40 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    D(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_buttonBarNeutralButtonStyle /* 41 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    M(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_buttonBarPositiveButtonStyle /* 42 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    u(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_buttonBarStyle /* 43 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    K(parcel.readInt() != 0 ? MediaDescriptionCompat.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_buttonStyle /* 44 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    x(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_buttonStyleSmall /* 45 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    boolean zY = y();
                    parcel2.writeNoException();
                    parcel2.writeInt(zY ? 1 : 0);
                    return true;
                case R$styleable.AppCompatTheme_checkboxStyle /* 46 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Y(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_checkedTextViewStyle /* 47 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    int iW = w();
                    parcel2.writeNoException();
                    parcel2.writeInt(iW);
                    return true;
                case R$styleable.AppCompatTheme_colorAccent /* 48 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    E(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_colorBackgroundFloating /* 49 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    U(parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case R$styleable.AppCompatTheme_colorButtonNormal /* 50 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    Bundle bundleN = n();
                    parcel2.writeNoException();
                    if (bundleN != null) {
                        parcel2.writeInt(1);
                        bundleN.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case R$styleable.AppCompatTheme_colorControlActivated /* 51 */:
                    parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
                    s(parcel.readInt() != 0 ? RatingCompat.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void A(String str, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper);

    void B();

    void C(long j);

    void D(boolean z);

    void E(int i);

    String F();

    void H(android.support.v4.media.session.a aVar);

    void I(RatingCompat ratingCompat);

    void J(int i, int i2, String str);

    void K(MediaDescriptionCompat mediaDescriptionCompat);

    boolean L();

    void M(MediaDescriptionCompat mediaDescriptionCompat);

    void N(String str, Bundle bundle);

    void O();

    CharSequence Q();

    void R(android.support.v4.media.session.a aVar);

    void S();

    void U(float f);

    void X(int i, int i2, String str);

    void Y(boolean z);

    void a0();

    List b0();

    void c0(int i);

    long d0();

    PlaybackStateCompat e();

    int e0();

    void f(String str, Bundle bundle);

    ParcelableVolumeInfo f0();

    boolean g();

    Bundle getExtras();

    MediaMetadataCompat getMetadata();

    String getTag();

    void h(Uri uri, Bundle bundle);

    PendingIntent i();

    int j();

    void m(String str, Bundle bundle);

    Bundle n();

    void next();

    void o(String str, Bundle bundle);

    void p(String str, Bundle bundle);

    void pause();

    void previous();

    void q(Uri uri, Bundle bundle);

    boolean r(KeyEvent keyEvent);

    void s(RatingCompat ratingCompat, Bundle bundle);

    void seekTo(long j);

    void stop();

    void u(MediaDescriptionCompat mediaDescriptionCompat, int i);

    int w();

    void x(int i);

    boolean y();
}
