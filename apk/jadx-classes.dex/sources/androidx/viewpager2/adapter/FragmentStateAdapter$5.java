package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.d;
import androidx.lifecycle.e;
import androidx.lifecycle.g;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class FragmentStateAdapter$5 implements e {
    final /* synthetic */ Handler a;
    final /* synthetic */ Runnable b;

    @Override // androidx.lifecycle.e
    public void a(g gVar, d.b bVar) {
        if (bVar == d.b.ON_DESTROY) {
            this.a.removeCallbacks(this.b);
            gVar.getLifecycle().c(this);
        }
    }
}
