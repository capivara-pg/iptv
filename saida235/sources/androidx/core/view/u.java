package androidx.core.view;

import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
public abstract class u {
    public static int a(MotionEvent motionEvent, int i) {
        return motionEvent.findPointerIndex(i);
    }

    public static int b(MotionEvent motionEvent) {
        return motionEvent.getActionIndex();
    }

    public static int c(MotionEvent motionEvent) {
        return motionEvent.getActionMasked();
    }

    public static int d(MotionEvent motionEvent, int i) {
        return motionEvent.getPointerId(i);
    }

    public static float e(MotionEvent motionEvent, int i) {
        return motionEvent.getX(i);
    }

    public static float f(MotionEvent motionEvent, int i) {
        return motionEvent.getY(i);
    }

    public static boolean g(MotionEvent motionEvent, int i) {
        return (motionEvent.getSource() & i) == i;
    }
}
