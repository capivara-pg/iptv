package androidx.transition;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.os.Build;
import android.util.Property;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
abstract class g {
    static ObjectAnimator a(Object obj, Property property, Path path) {
        return Build.VERSION.SDK_INT >= 21 ? ObjectAnimator.ofObject(obj, (Property<Object, V>) property, (TypeConverter) null, path) : ObjectAnimator.ofFloat(obj, new i(property, path), 0.0f, 1.0f);
    }
}
