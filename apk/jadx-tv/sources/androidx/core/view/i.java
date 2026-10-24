package androidx.core.view;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
public final class i {
    private final a a;

    interface a {
        void a(boolean z);

        boolean onTouchEvent(MotionEvent motionEvent);
    }

    static class b implements a {
        private final GestureDetector a;

        b(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.a = new GestureDetector(context, onGestureListener, handler);
        }

        @Override // androidx.core.view.i.a
        public void a(boolean z) {
            this.a.setIsLongpressEnabled(z);
        }

        @Override // androidx.core.view.i.a
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return this.a.onTouchEvent(motionEvent);
        }
    }

    public i(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public boolean a(MotionEvent motionEvent) {
        return this.a.onTouchEvent(motionEvent);
    }

    public void b(boolean z) {
        this.a.a(z);
    }

    public i(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        this.a = new b(context, onGestureListener, handler);
    }
}
