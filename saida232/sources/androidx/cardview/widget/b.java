package androidx.cardview.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
class b extends d {

    class a implements i.a {
        a() {
        }

        @Override // androidx.cardview.widget.i.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            canvas.drawRoundRect(rectF, f, f, paint);
        }
    }

    b() {
    }

    @Override // androidx.cardview.widget.f
    public void l() {
        i.r = new a();
    }
}
