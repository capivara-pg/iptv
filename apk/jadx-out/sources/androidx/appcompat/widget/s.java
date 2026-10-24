package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import androidx.appcompat.R$attr;

/* JADX INFO: loaded from: classes.dex */
public class s extends RatingBar {
    private final q a;

    public s(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.ratingBarStyle);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Bitmap bitmapB = this.a.b();
        if (bitmapB != null) {
            setMeasuredDimension(View.resolveSizeAndState(bitmapB.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        }
    }

    public s(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d2.a(this, getContext());
        q qVar = new q(this);
        this.a = qVar;
        qVar.c(attributeSet, i);
    }
}
