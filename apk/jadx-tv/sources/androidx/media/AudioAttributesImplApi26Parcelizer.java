package androidx.media;

import androidx.versionedparcelable.a;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(a aVar) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        audioAttributesImplApi26.a = e0.a.a(aVar.r(audioAttributesImplApi26.a, 1));
        audioAttributesImplApi26.b = aVar.p(audioAttributesImplApi26.b, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, a aVar) {
        aVar.x(false, false);
        aVar.H(audioAttributesImplApi26.a, 1);
        aVar.F(audioAttributesImplApi26.b, 2);
    }
}
