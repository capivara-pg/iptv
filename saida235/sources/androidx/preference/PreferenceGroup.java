package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import androidx.collection.g;
import java.util.ArrayList;
import java.util.List;
import t.i;

/* JADX INFO: loaded from: classes.dex */
public abstract class PreferenceGroup extends Preference {
    final g D;
    private final Handler E;
    private List F;
    private boolean G;
    private int H;
    private boolean I;
    private int J;
    private final Runnable K;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                PreferenceGroup.this.D.clear();
            }
        }
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.D = new g();
        this.E = new Handler();
        this.G = true;
        this.H = 0;
        this.I = false;
        this.J = Integer.MAX_VALUE;
        this.K = new a();
        this.F = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Q, i, i2);
        int i3 = R$styleable.PreferenceGroup_orderingFromXml;
        this.G = i.b(typedArrayObtainStyledAttributes, i3, i3, true);
        int i4 = R$styleable.PreferenceGroup_initialExpandedChildrenCount;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            H(i.d(typedArrayObtainStyledAttributes, i4, i4, Integer.MAX_VALUE));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public Preference F(int i) {
        return (Preference) this.F.get(i);
    }

    public int G() {
        return this.F.size();
    }

    public void H(int i) {
        if (i != Integer.MAX_VALUE && !o()) {
            Log.e("PreferenceGroup", getClass().getSimpleName() + " should have a key defined if it contains an expandable preference");
        }
        this.J = i;
    }

    @Override // androidx.preference.Preference
    public void s(boolean z) {
        super.s(z);
        int iG = G();
        for (int i = 0; i < iG; i++) {
            F(i).w(this, z);
        }
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
