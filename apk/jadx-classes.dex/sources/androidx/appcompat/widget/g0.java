package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
final class g0 {
    private TextView a;
    private TextClassifier b;

    g0(TextView textView) {
        this.a = (TextView) x.h.e(textView);
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.b;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManagerA = d0.a(this.a.getContext().getSystemService(c0.a()));
        return textClassificationManagerA != null ? textClassificationManagerA.getTextClassifier() : TextClassifier.NO_OP;
    }

    public void b(TextClassifier textClassifier) {
        this.b = textClassifier;
    }
}
