package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a();
    private final String a;
    private final CharSequence b;
    private final CharSequence c;
    private final CharSequence d;
    private final Bitmap e;
    private final Uri f;
    private final Bundle g;
    private final Uri h;
    private MediaDescription i;

    class a implements Parcelable.Creator {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            return Build.VERSION.SDK_INT < 21 ? new MediaDescriptionCompat(parcel) : MediaDescriptionCompat.a(MediaDescription.CREATOR.createFromParcel(parcel));
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MediaDescriptionCompat[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }
    }

    public static final class b {
        private String a;
        private CharSequence b;
        private CharSequence c;
        private CharSequence d;
        private Bitmap e;
        private Uri f;
        private Bundle g;
        private Uri h;

        public MediaDescriptionCompat a() {
            return new MediaDescriptionCompat(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }

        public b b(CharSequence charSequence) {
            this.d = charSequence;
            return this;
        }

        public b c(Bundle bundle) {
            this.g = bundle;
            return this;
        }

        public b d(Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        public b e(Uri uri) {
            this.f = uri;
            return this;
        }

        public b f(String str) {
            this.a = str;
            return this;
        }

        public b g(Uri uri) {
            this.h = uri;
            return this;
        }

        public b h(CharSequence charSequence) {
            this.c = charSequence;
            return this;
        }

        public b i(CharSequence charSequence) {
            this.b = charSequence;
            return this;
        }
    }

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.a = str;
        this.b = charSequence;
        this.c = charSequence2;
        this.d = charSequence3;
        this.e = bitmap;
        this.f = uri;
        this.g = bundle;
        this.h = uri2;
    }

    public static MediaDescriptionCompat a(Object obj) {
        int i;
        Bundle bundle = null;
        if (obj == null || (i = Build.VERSION.SDK_INT) < 21) {
            return null;
        }
        b bVar = new b();
        MediaDescription mediaDescriptionA = android.support.v4.media.a.a(obj);
        bVar.f(mediaDescriptionA.getMediaId());
        bVar.i(mediaDescriptionA.getTitle());
        bVar.h(mediaDescriptionA.getSubtitle());
        bVar.b(mediaDescriptionA.getDescription());
        bVar.d(mediaDescriptionA.getIconBitmap());
        bVar.e(mediaDescriptionA.getIconUri());
        Bundle extras = mediaDescriptionA.getExtras();
        if (extras != null) {
            extras = MediaSessionCompat.t(extras);
        }
        Uri uri = extras != null ? (Uri) extras.getParcelable("android.support.v4.media.description.MEDIA_URI") : null;
        if (uri == null) {
            bundle = extras;
        } else if (!extras.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") || extras.size() != 2) {
            extras.remove("android.support.v4.media.description.MEDIA_URI");
            extras.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
            bundle = extras;
        }
        bVar.c(bundle);
        if (uri != null) {
            bVar.g(uri);
        } else if (i >= 23) {
            bVar.g(mediaDescriptionA.getMediaUri());
        }
        MediaDescriptionCompat mediaDescriptionCompatA = bVar.a();
        mediaDescriptionCompatA.i = mediaDescriptionA;
        return mediaDescriptionCompatA;
    }

    public Bitmap b() {
        return this.e;
    }

    public Uri c() {
        return this.f;
    }

    public Object d() {
        int i;
        MediaDescription mediaDescription = this.i;
        if (mediaDescription != null || (i = Build.VERSION.SDK_INT) < 21) {
            return mediaDescription;
        }
        MediaDescription.Builder builder = new MediaDescription.Builder();
        builder.setMediaId(this.a);
        builder.setTitle(this.b);
        builder.setSubtitle(this.c);
        builder.setDescription(this.d);
        builder.setIconBitmap(this.e);
        builder.setIconUri(this.f);
        Bundle bundle = this.g;
        if (i < 23 && this.h != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
            }
            bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", this.h);
        }
        builder.setExtras(bundle);
        if (i >= 23) {
            builder.setMediaUri(this.h);
        }
        MediaDescription mediaDescriptionBuild = builder.build();
        this.i = mediaDescriptionBuild;
        return mediaDescriptionBuild;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CharSequence e() {
        return this.c;
    }

    public CharSequence f() {
        return this.b;
    }

    public String toString() {
        return ((Object) this.b) + ", " + ((Object) this.c) + ", " + ((Object) this.d);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            android.support.v4.media.a.a(d()).writeToParcel(parcel, i);
            return;
        }
        parcel.writeString(this.a);
        TextUtils.writeToParcel(this.b, parcel, i);
        TextUtils.writeToParcel(this.c, parcel, i);
        TextUtils.writeToParcel(this.d, parcel, i);
        parcel.writeParcelable(this.e, i);
        parcel.writeParcelable(this.f, i);
        parcel.writeBundle(this.g);
        parcel.writeParcelable(this.h, i);
    }

    MediaDescriptionCompat(Parcel parcel) {
        this.a = parcel.readString();
        this.b = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.c = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.d = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        ClassLoader classLoader = MediaDescriptionCompat.class.getClassLoader();
        this.e = (Bitmap) parcel.readParcelable(classLoader);
        this.f = (Uri) parcel.readParcelable(classLoader);
        this.g = parcel.readBundle(classLoader);
        this.h = (Uri) parcel.readParcelable(classLoader);
    }
}
