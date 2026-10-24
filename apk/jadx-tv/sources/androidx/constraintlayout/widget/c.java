package androidx.constraintlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class c extends View {
    public c(Context context) {
        super(context);
        super.setVisibility(8);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setGuidelineBegin(int i) {
        ConstraintLayout.a aVar = (ConstraintLayout.a) getLayoutParams();
        aVar.a = i;
        setLayoutParams(aVar);
    }

    public void setGuidelineEnd(int i) {
        ConstraintLayout.a aVar = (ConstraintLayout.a) getLayoutParams();
        aVar.b = i;
        setLayoutParams(aVar);
    }

    public void setGuidelinePercent(float f) {
        ConstraintLayout.a aVar = (ConstraintLayout.a) getLayoutParams();
        aVar.c = f;
        setLayoutParams(aVar);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
    }
}
