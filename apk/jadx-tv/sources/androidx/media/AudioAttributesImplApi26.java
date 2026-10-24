package androidx.media;

import android.media.AudioAttributes;

/* JADX INFO: loaded from: classes.dex */
public class AudioAttributesImplApi26 extends AudioAttributesImplApi21 {

    static class a extends AudioAttributesImplApi21.a {
        a() {
        }

        @Override // androidx.media.AudioAttributesImplApi21.a, androidx.media.AudioAttributesImpl.a
        public AudioAttributesImpl build() {
            return new AudioAttributesImplApi26(this.a.build());
        }
    }

    public AudioAttributesImplApi26() {
    }

    AudioAttributesImplApi26(AudioAttributes audioAttributes) {
        super(audioAttributes, -1);
    }
}
