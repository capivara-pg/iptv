package androidx.core.view;

import android.os.Build;
import android.view.ViewGroup;
import androidx.core.R$id;

/* JADX INFO: loaded from: classes.dex */
public abstract class r1 {
    public static boolean a(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT >= 21) {
            return viewGroup.isTransitionGroup();
        }
        Boolean bool = (Boolean) viewGroup.getTag(R$id.tag_transition_group);
        return ((bool == null || !bool.booleanValue()) && viewGroup.getBackground() == null && c1.I(viewGroup) == null) ? false : true;
    }
}
