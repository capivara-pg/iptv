package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R$attr;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class c extends j implements DialogInterface {
    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;

    public static class a {
        private final AlertController.f P;
        private final int mTheme;

        public a(Context context) {
            this(context, c.resolveDialogTheme(context, 0));
        }

        public c create() {
            c cVar = new c(this.P.a, this.mTheme);
            this.P.a(cVar.mAlert);
            cVar.setCancelable(this.P.r);
            if (this.P.r) {
                cVar.setCanceledOnTouchOutside(true);
            }
            cVar.setOnCancelListener(this.P.f3s);
            cVar.setOnDismissListener(this.P.t);
            DialogInterface.OnKeyListener onKeyListener = this.P.u;
            if (onKeyListener != null) {
                cVar.setOnKeyListener(onKeyListener);
            }
            return cVar;
        }

        public Context getContext() {
            return this.P.a;
        }

        public a setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            return this;
        }

        public a setCancelable(boolean z) {
            this.P.r = z;
            return this;
        }

        public a setCursor(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
            AlertController.f fVar = this.P;
            fVar.K = cursor;
            fVar.L = str;
            fVar.x = onClickListener;
            return this;
        }

        public a setCustomTitle(View view) {
            this.P.g = view;
            return this;
        }

        public a setIcon(int i) {
            this.P.c = i;
            return this;
        }

        public a setIconAttribute(int i) {
            TypedValue typedValue = new TypedValue();
            this.P.a.getTheme().resolveAttribute(i, typedValue, true);
            this.P.c = typedValue.resourceId;
            return this;
        }

        @Deprecated
        public a setInverseBackgroundForced(boolean z) {
            this.P.N = z;
            return this;
        }

        public a setItems(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.v = fVar.a.getResources().getTextArray(i);
            this.P.x = onClickListener;
            return this;
        }

        public a setMessage(int i) {
            AlertController.f fVar = this.P;
            fVar.h = fVar.a.getText(i);
            return this;
        }

        public a setMultiChoiceItems(int i, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.f fVar = this.P;
            fVar.v = fVar.a.getResources().getTextArray(i);
            AlertController.f fVar2 = this.P;
            fVar2.J = onMultiChoiceClickListener;
            fVar2.F = zArr;
            fVar2.G = true;
            return this;
        }

        public a setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.l = fVar.a.getText(i);
            this.P.n = onClickListener;
            return this;
        }

        public a setNegativeButtonIcon(Drawable drawable) {
            this.P.m = drawable;
            return this;
        }

        public a setNeutralButton(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.o = fVar.a.getText(i);
            this.P.q = onClickListener;
            return this;
        }

        public a setNeutralButtonIcon(Drawable drawable) {
            this.P.p = drawable;
            return this;
        }

        public a setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.P.f3s = onCancelListener;
            return this;
        }

        public a setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.P.t = onDismissListener;
            return this;
        }

        public a setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
            this.P.O = onItemSelectedListener;
            return this;
        }

        public a setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.P.u = onKeyListener;
            return this;
        }

        public a setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.i = fVar.a.getText(i);
            this.P.k = onClickListener;
            return this;
        }

        public a setPositiveButtonIcon(Drawable drawable) {
            this.P.j = drawable;
            return this;
        }

        public a setRecycleOnMeasureEnabled(boolean z) {
            this.P.P = z;
            return this;
        }

        public a setSingleChoiceItems(int i, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.v = fVar.a.getResources().getTextArray(i);
            AlertController.f fVar2 = this.P;
            fVar2.x = onClickListener;
            fVar2.I = i2;
            fVar2.H = true;
            return this;
        }

        public a setTitle(int i) {
            AlertController.f fVar = this.P;
            fVar.f = fVar.a.getText(i);
            return this;
        }

        public a setView(int i) {
            AlertController.f fVar = this.P;
            fVar.z = null;
            fVar.y = i;
            fVar.E = false;
            return this;
        }

        public c show() {
            c cVarCreate = create();
            cVarCreate.show();
            return cVarCreate;
        }

        public a(Context context, int i) {
            this.P = new AlertController.f(new ContextThemeWrapper(context, c.resolveDialogTheme(context, i)));
            this.mTheme = i;
        }

        public a setIcon(Drawable drawable) {
            this.P.d = drawable;
            return this;
        }

        public a setMessage(CharSequence charSequence) {
            this.P.h = charSequence;
            return this;
        }

        public a setTitle(CharSequence charSequence) {
            this.P.f = charSequence;
            return this;
        }

        public a setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.v = charSequenceArr;
            fVar.x = onClickListener;
            return this;
        }

        public a setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.l = charSequence;
            fVar.n = onClickListener;
            return this;
        }

        public a setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.o = charSequence;
            fVar.q = onClickListener;
            return this;
        }

        public a setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.i = charSequence;
            fVar.k = onClickListener;
            return this;
        }

        public a setView(View view) {
            AlertController.f fVar = this.P;
            fVar.z = view;
            fVar.y = 0;
            fVar.E = false;
            return this;
        }

        public a setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.f fVar = this.P;
            fVar.v = charSequenceArr;
            fVar.J = onMultiChoiceClickListener;
            fVar.F = zArr;
            fVar.G = true;
            return this;
        }

        public a setSingleChoiceItems(Cursor cursor, int i, String str, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.K = cursor;
            fVar.x = onClickListener;
            fVar.I = i;
            fVar.L = str;
            fVar.H = true;
            return this;
        }

        @Deprecated
        public a setView(View view, int i, int i2, int i3, int i4) {
            AlertController.f fVar = this.P;
            fVar.z = view;
            fVar.y = 0;
            fVar.E = true;
            fVar.A = i;
            fVar.B = i2;
            fVar.C = i3;
            fVar.D = i4;
            return this;
        }

        public a setMultiChoiceItems(Cursor cursor, String str, String str2, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.f fVar = this.P;
            fVar.K = cursor;
            fVar.J = onMultiChoiceClickListener;
            fVar.M = str;
            fVar.L = str2;
            fVar.G = true;
            return this;
        }

        public a setSingleChoiceItems(CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.v = charSequenceArr;
            fVar.x = onClickListener;
            fVar.I = i;
            fVar.H = true;
            return this;
        }

        public a setSingleChoiceItems(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.f fVar = this.P;
            fVar.w = listAdapter;
            fVar.x = onClickListener;
            fVar.I = i;
            fVar.H = true;
            return this;
        }
    }

    protected c(Context context, int i) {
        super(context, resolveDialogTheme(context, i));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    static int resolveDialogTheme(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public Button getButton(int i) {
        return this.mAlert.c(i);
    }

    public ListView getListView() {
        return this.mAlert.e();
    }

    @Override // androidx.appcompat.app.j, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlert.f();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mAlert.h(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mAlert.i(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void setButton(int i, CharSequence charSequence, Message message) {
        this.mAlert.l(i, charSequence, null, message, null);
    }

    void setButtonPanelLayoutHint(int i) {
        this.mAlert.m(i);
    }

    public void setCustomTitle(View view) {
        this.mAlert.n(view);
    }

    public void setIcon(int i) {
        this.mAlert.o(i);
    }

    public void setIconAttribute(int i) {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(i, typedValue, true);
        this.mAlert.o(typedValue.resourceId);
    }

    public void setMessage(CharSequence charSequence) {
        this.mAlert.q(charSequence);
    }

    @Override // androidx.appcompat.app.j, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.s(charSequence);
    }

    public void setView(View view) {
        this.mAlert.u(view);
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.mAlert.l(i, charSequence, onClickListener, null, null);
    }

    public void setIcon(Drawable drawable) {
        this.mAlert.p(drawable);
    }

    public void setView(View view, int i, int i2, int i3, int i4) {
        this.mAlert.v(view, i, i2, i3, i4);
    }

    public void setButton(int i, CharSequence charSequence, Drawable drawable, DialogInterface.OnClickListener onClickListener) {
        this.mAlert.l(i, charSequence, onClickListener, null, drawable);
    }
}
