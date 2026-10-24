package androidx.core.view;

import android.view.VelocityTracker;

/* JADX INFO: loaded from: classes.dex */
public abstract class f0 {
    public static float a(VelocityTracker velocityTracker, int i) {
        return velocityTracker.getYVelocity(i);
    }
}
