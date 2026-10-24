package androidx.constraintlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class f extends View {
    private boolean a;

    public f(Context context) {
        super(context);
        this.a = true;
        super.setVisibility(8);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setFilterRedundantCalls(boolean filter) {
        this.a = filter;
    }

    public void setGuidelineBegin(int margin) {
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        if (this.a && bVar.a == margin) {
            return;
        }
        bVar.a = margin;
        setLayoutParams(bVar);
    }

    public void setGuidelineEnd(int margin) {
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        if (this.a && bVar.b == margin) {
            return;
        }
        bVar.b = margin;
        setLayoutParams(bVar);
    }

    public void setGuidelinePercent(float ratio) {
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        if (this.a && bVar.c == ratio) {
            return;
        }
        bVar.c = ratio;
        setLayoutParams(bVar);
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
    }
}
