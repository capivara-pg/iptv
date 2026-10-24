package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.runtime.R$id;

/* JADX INFO: loaded from: classes.dex */
public abstract class l0 {
    public static final void a(View view, m mVar) {
        kotlin.jvm.internal.m.g(view, "<this>");
        view.setTag(R$id.view_tree_lifecycle_owner, mVar);
    }
}
