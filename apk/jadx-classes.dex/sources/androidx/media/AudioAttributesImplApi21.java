package androidx.media;

import android.media.AudioAttributes;
import e0.b;
import e0.c;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class AudioAttributesImplApi21 implements AudioAttributesImpl {
    public AudioAttributes a;
    public int b;

    static class a implements AudioAttributesImpl.a {
        final AudioAttributes.Builder a = new AudioAttributes.Builder();

        a() {
        }

        @Override // androidx.media.AudioAttributesImpl.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public a a(int i) {
            this.a.setLegacyStreamType(i);
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.a
        public AudioAttributesImpl build() {
            return new AudioAttributesImplApi21(this.a.build());
        }
    }

    public AudioAttributesImplApi21() {
        this.b = -1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplApi21) {
            return b.a(this.a, ((AudioAttributesImplApi21) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return c.a(this.a);
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.a;
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes, int i) {
        this.a = audioAttributes;
        this.b = i;
    }
}
