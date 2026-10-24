package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
final class d0 {
    private TextView a;
    private TextClassifier b;

    d0(TextView textView) {
        this.a = (TextView) c0.h.e(textView);
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.b;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManagerA = a0.a(this.a.getContext().getSystemService(z.a()));
        return textClassificationManagerA != null ? textClassificationManagerA.getTextClassifier() : TextClassifier.NO_OP;
    }

    public void b(TextClassifier textClassifier) {
        this.b = textClassifier;
    }
}
