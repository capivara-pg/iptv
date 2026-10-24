package androidx.transition;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {
    public static j a(ViewGroup viewGroup) {
        androidx.appcompat.app.r.a(viewGroup.getTag(R$id.transition_current_scene));
        return null;
    }

    static void b(ViewGroup viewGroup, j jVar) {
        viewGroup.setTag(R$id.transition_current_scene, jVar);
    }
}
