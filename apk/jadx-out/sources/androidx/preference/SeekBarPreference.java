package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class SeekBarPreference extends Preference {
    int D;
    int E;
    private int F;
    private int G;
    boolean H;
    SeekBar I;
    private TextView J;
    boolean K;
    private boolean L;
    boolean M;
    private SeekBar.OnSeekBarChangeListener N;
    private View.OnKeyListener Q;

    class a implements SeekBar.OnSeekBarChangeListener {
        a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                SeekBarPreference seekBarPreference = SeekBarPreference.this;
                if (seekBarPreference.M || !seekBarPreference.H) {
                    seekBarPreference.I(seekBar);
                    return;
                }
            }
            SeekBarPreference seekBarPreference2 = SeekBarPreference.this;
            seekBarPreference2.J(i + seekBarPreference2.E);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            SeekBarPreference.this.H = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            SeekBarPreference.this.H = false;
            int progress = seekBar.getProgress();
            SeekBarPreference seekBarPreference = SeekBarPreference.this;
            if (progress + seekBarPreference.E != seekBarPreference.D) {
                seekBarPreference.I(seekBar);
            }
        }
    }

    class b implements View.OnKeyListener {
        b() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 0) {
                return false;
            }
            SeekBarPreference seekBarPreference = SeekBarPreference.this;
            if ((!seekBarPreference.K && (i == 21 || i == 22)) || i == 23 || i == 66) {
                return false;
            }
            SeekBar seekBar = seekBarPreference.I;
            if (seekBar != null) {
                return seekBar.onKeyDown(i, keyEvent);
            }
            Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
            return false;
        }
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.N = new a();
        this.Q = new b();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.W, i, i2);
        this.E = typedArrayObtainStyledAttributes.getInt(R$styleable.SeekBarPreference_min, 0);
        F(typedArrayObtainStyledAttributes.getInt(R$styleable.SeekBarPreference_android_max, 100));
        G(typedArrayObtainStyledAttributes.getInt(R$styleable.SeekBarPreference_seekBarIncrement, 0));
        this.K = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_adjustable, true);
        this.L = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_showSeekBarValue, false);
        this.M = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SeekBarPreference_updatesContinuously, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void H(int i, boolean z) {
        int i2 = this.E;
        if (i < i2) {
            i = i2;
        }
        int i3 = this.F;
        if (i > i3) {
            i = i3;
        }
        if (i != this.D) {
            this.D = i;
            J(i);
            A(i);
            if (z) {
                r();
            }
        }
    }

    public final void F(int i) {
        int i2 = this.E;
        if (i < i2) {
            i = i2;
        }
        if (i != this.F) {
            this.F = i;
            r();
        }
    }

    public final void G(int i) {
        if (i != this.G) {
            this.G = Math.min(this.F - this.E, Math.abs(i));
            r();
        }
    }

    void I(SeekBar seekBar) {
        int progress = this.E + seekBar.getProgress();
        if (progress != this.D) {
            if (a(Integer.valueOf(progress))) {
                H(progress, false);
            } else {
                seekBar.setProgress(this.D - this.E);
                J(this.D);
            }
        }
    }

    void J(int i) {
        TextView textView = this.J;
        if (textView != null) {
            textView.setText(String.valueOf(i));
        }
    }

    @Override // androidx.preference.Preference
    protected Object v(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, 0));
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.seekBarPreferenceStyle);
    }
}
