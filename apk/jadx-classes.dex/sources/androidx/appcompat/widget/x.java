package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class x extends Spinner implements androidx.core.view.g0 {
    private static final int[] i = {R.attr.spinnerMode};
    private final androidx.appcompat.widget.f a;
    private final Context b;
    private t1 c;
    private SpinnerAdapter d;
    private final boolean e;
    private g f;
    int g;
    final Rect h;

    class a extends t1 {
        final /* synthetic */ e j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(View view, e eVar) {
            super(view);
            this.j = eVar;
        }

        @Override // androidx.appcompat.widget.t1
        public androidx.appcompat.view.menu.p b() {
            return this.j;
        }

        @Override // androidx.appcompat.widget.t1
        public boolean c() {
            if (x.this.getInternalPopup().isShowing()) {
                return true;
            }
            x.this.b();
            return true;
        }
    }

    class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!x.this.getInternalPopup().isShowing()) {
                x.this.b();
            }
            ViewTreeObserver viewTreeObserver = x.this.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.removeOnGlobalLayoutListener(this);
            }
        }
    }

    class c implements g, DialogInterface.OnClickListener {
        androidx.appcompat.app.c a;
        private ListAdapter b;
        private CharSequence c;

        c() {
        }

        @Override // androidx.appcompat.widget.x.g
        public int a() {
            return 0;
        }

        @Override // androidx.appcompat.widget.x.g
        public void c(int i) {
            Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.x.g
        public CharSequence d() {
            return this.c;
        }

        @Override // androidx.appcompat.widget.x.g
        public void dismiss() {
            androidx.appcompat.app.c cVar = this.a;
            if (cVar != null) {
                cVar.dismiss();
                this.a = null;
            }
        }

        @Override // androidx.appcompat.widget.x.g
        public Drawable e() {
            return null;
        }

        @Override // androidx.appcompat.widget.x.g
        public void f(CharSequence charSequence) {
            this.c = charSequence;
        }

        @Override // androidx.appcompat.widget.x.g
        public void g(int i) {
            Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.x.g
        public void h(int i) {
            Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.x.g
        public void i(int i, int i2) {
            if (this.b == null) {
                return;
            }
            androidx.appcompat.app.c.a aVar = new androidx.appcompat.app.c.a(x.this.getPopupContext());
            CharSequence charSequence = this.c;
            if (charSequence != null) {
                aVar.setTitle(charSequence);
            }
            androidx.appcompat.app.c cVarCreate = aVar.setSingleChoiceItems(this.b, x.this.getSelectedItemPosition(), this).create();
            this.a = cVarCreate;
            ListView listView = cVarCreate.getListView();
            listView.setTextDirection(i);
            listView.setTextAlignment(i2);
            this.a.show();
        }

        @Override // androidx.appcompat.widget.x.g
        public boolean isShowing() {
            androidx.appcompat.app.c cVar = this.a;
            if (cVar != null) {
                return cVar.isShowing();
            }
            return false;
        }

        @Override // androidx.appcompat.widget.x.g
        public int j() {
            return 0;
        }

        @Override // androidx.appcompat.widget.x.g
        public void k(ListAdapter listAdapter) {
            this.b = listAdapter;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            x.this.setSelection(i);
            if (x.this.getOnItemClickListener() != null) {
                x.this.performItemClick(null, i, this.b.getItemId(i));
            }
            dismiss();
        }

        @Override // androidx.appcompat.widget.x.g
        public void setBackgroundDrawable(Drawable drawable) {
            Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }
    }

    private static class d implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter a;
        private ListAdapter b;

        public d(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.b = (ListAdapter) spinnerAdapter;
            }
            if (theme == null || Build.VERSION.SDK_INT < 23 || !y.a(spinnerAdapter)) {
                return;
            }
            ThemedSpinnerAdapter themedSpinnerAdapterA = z.a(spinnerAdapter);
            if (themedSpinnerAdapterA.getDropDownViewTheme() != theme) {
                themedSpinnerAdapterA.setDropDownViewTheme(theme);
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.a;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.a;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    class e extends y1 implements g {
        private CharSequence K;
        ListAdapter L;
        private final Rect M;
        private int N;

        class a implements AdapterView.OnItemClickListener {
            final /* synthetic */ x a;

            a(x xVar) {
                this.a = xVar;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                x.this.setSelection(i);
                if (x.this.getOnItemClickListener() != null) {
                    e eVar = e.this;
                    x.this.performItemClick(view, i, eVar.L.getItemId(i));
                }
                e.this.dismiss();
            }
        }

        class b implements ViewTreeObserver.OnGlobalLayoutListener {
            b() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                e eVar = e.this;
                if (!eVar.N(x.this)) {
                    e.this.dismiss();
                } else {
                    e.this.L();
                    e.super.show();
                }
            }
        }

        class c implements PopupWindow.OnDismissListener {
            final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener a;

            c(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
                this.a = onGlobalLayoutListener;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                ViewTreeObserver viewTreeObserver = x.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this.a);
                }
            }
        }

        public e(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.M = new Rect();
            w(x.this);
            C(true);
            H(0);
            E(new a(x.this));
        }

        void L() {
            int i;
            Drawable drawableE = e();
            if (drawableE != null) {
                drawableE.getPadding(x.this.h);
                i = y2.b(x.this) ? x.this.h.right : -x.this.h.left;
            } else {
                Rect rect = x.this.h;
                rect.right = 0;
                rect.left = 0;
                i = 0;
            }
            int paddingLeft = x.this.getPaddingLeft();
            int paddingRight = x.this.getPaddingRight();
            int width = x.this.getWidth();
            x xVar = x.this;
            int i2 = xVar.g;
            if (i2 == -2) {
                int iA = xVar.a((SpinnerAdapter) this.L, e());
                int i3 = x.this.getContext().getResources().getDisplayMetrics().widthPixels;
                Rect rect2 = x.this.h;
                int i4 = (i3 - rect2.left) - rect2.right;
                if (iA > i4) {
                    iA = i4;
                }
                y(Math.max(iA, (width - paddingLeft) - paddingRight));
            } else if (i2 == -1) {
                y((width - paddingLeft) - paddingRight);
            } else {
                y(i2);
            }
            c(y2.b(x.this) ? i + (((width - paddingRight) - s()) - M()) : i + paddingLeft + M());
        }

        public int M() {
            return this.N;
        }

        boolean N(View view) {
            return androidx.core.view.c1.P(view) && view.getGlobalVisibleRect(this.M);
        }

        @Override // androidx.appcompat.widget.x.g
        public CharSequence d() {
            return this.K;
        }

        @Override // androidx.appcompat.widget.x.g
        public void f(CharSequence charSequence) {
            this.K = charSequence;
        }

        @Override // androidx.appcompat.widget.x.g
        public void h(int i) {
            this.N = i;
        }

        @Override // androidx.appcompat.widget.x.g
        public void i(int i, int i2) {
            ViewTreeObserver viewTreeObserver;
            boolean zIsShowing = isShowing();
            L();
            B(2);
            super.show();
            ListView listViewM = m();
            listViewM.setChoiceMode(1);
            listViewM.setTextDirection(i);
            listViewM.setTextAlignment(i2);
            I(x.this.getSelectedItemPosition());
            if (zIsShowing || (viewTreeObserver = x.this.getViewTreeObserver()) == null) {
                return;
            }
            b bVar = new b();
            viewTreeObserver.addOnGlobalLayoutListener(bVar);
            D(new c(bVar));
        }

        @Override // androidx.appcompat.widget.y1, androidx.appcompat.widget.x.g
        public void k(ListAdapter listAdapter) {
            super.k(listAdapter);
            this.L = listAdapter;
        }
    }

    static class f extends View.BaseSavedState {
        public static final Parcelable.Creator<f> CREATOR = new a();
        boolean a;

        static class a implements Parcelable.Creator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public f createFromParcel(Parcel parcel) {
                return new f(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public f[] newArray(int i) {
                return new f[i];
            }
        }

        f(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.a ? (byte) 1 : (byte) 0);
        }

        f(Parcel parcel) {
            super(parcel);
            this.a = parcel.readByte() != 0;
        }
    }

    interface g {
        int a();

        void c(int i);

        CharSequence d();

        void dismiss();

        Drawable e();

        void f(CharSequence charSequence);

        void g(int i);

        void h(int i);

        void i(int i, int i2);

        boolean isShowing();

        int j();

        void k(ListAdapter listAdapter);

        void setBackgroundDrawable(Drawable drawable);
    }

    public x(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.spinnerStyle);
    }

    int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i2 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        drawable.getPadding(this.h);
        Rect rect = this.h;
        return iMax2 + rect.left + rect.right;
    }

    void b() {
        this.f.i(getTextDirection(), getTextAlignment());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            fVar.b();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        g gVar = this.f;
        return gVar != null ? gVar.a() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        g gVar = this.f;
        return gVar != null ? gVar.j() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.f != null ? this.g : super.getDropDownWidth();
    }

    final g getInternalPopup() {
        return this.f;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        g gVar = this.f;
        return gVar != null ? gVar.e() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.b;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        g gVar = this.f;
        return gVar != null ? gVar.d() : super.getPrompt();
    }

    @Override // androidx.core.view.g0
    public ColorStateList getSupportBackgroundTintList() {
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            return fVar.c();
        }
        return null;
    }

    @Override // androidx.core.view.g0
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            return fVar.d();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        g gVar = this.f;
        if (gVar == null || !gVar.isShowing()) {
            return;
        }
        this.f.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f == null || View.MeasureSpec.getMode(i2) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        f fVar = (f) parcelable;
        super.onRestoreInstanceState(fVar.getSuperState());
        if (!fVar.a || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new b());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public Parcelable onSaveInstanceState() {
        f fVar = new f(super.onSaveInstanceState());
        g gVar = this.f;
        fVar.a = gVar != null && gVar.isShowing();
        return fVar;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        t1 t1Var = this.c;
        if (t1Var == null || !t1Var.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        g gVar = this.f;
        if (gVar == null) {
            return super.performClick();
        }
        if (gVar.isShowing()) {
            return true;
        }
        b();
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            fVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            fVar.g(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i2) {
        g gVar = this.f;
        if (gVar == null) {
            super.setDropDownHorizontalOffset(i2);
        } else {
            gVar.h(i2);
            this.f.c(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i2) {
        g gVar = this.f;
        if (gVar != null) {
            gVar.g(i2);
        } else {
            super.setDropDownVerticalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i2) {
        if (this.f != null) {
            this.g = i2;
        } else {
            super.setDropDownWidth(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        g gVar = this.f;
        if (gVar != null) {
            gVar.setBackgroundDrawable(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i2) {
        setPopupBackgroundDrawable(d.b.d(getPopupContext(), i2));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        g gVar = this.f;
        if (gVar != null) {
            gVar.f(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    @Override // androidx.core.view.g0
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            fVar.i(colorStateList);
        }
    }

    @Override // androidx.core.view.g0
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        androidx.appcompat.widget.f fVar = this.a;
        if (fVar != null) {
            fVar.j(mode);
        }
    }

    public x(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, -1);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.e) {
            this.d = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.f != null) {
            Context context = this.b;
            if (context == null) {
                context = getContext();
            }
            this.f.k(new d(spinnerAdapter, context.getTheme()));
        }
    }

    public x(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(context, attributeSet, i2, i3, null);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x005d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:29:0x0060  */
    /* JADX WARN: Code duplicated, block: B:30:0x0097  */
    /* JADX WARN: Code duplicated, block: B:33:0x00af  */
    /* JADX WARN: Code duplicated, block: B:36:0x00c8  */
    public x(Context context, AttributeSet attributeSet, int i2, int i3, Resources.Theme theme) throws Throwable {
        TypedArray typedArrayObtainStyledAttributes;
        CharSequence[] charSequenceArrQ;
        SpinnerAdapter spinnerAdapter;
        super(context, attributeSet, i2);
        this.h = new Rect();
        r2 r2VarU = r2.u(context, attributeSet, R$styleable.H, i2, 0);
        this.a = new androidx.appcompat.widget.f(this);
        if (theme != null) {
            this.b = new f.d(context, theme);
        } else {
            int iN = r2VarU.n(R$styleable.Spinner_popupTheme, 0);
            if (iN != 0) {
                this.b = new f.d(context, iN);
            } else {
                this.b = context;
            }
        }
        TypedArray typedArray = null;
        if (i3 == -1) {
            try {
                typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i, i2, 0);
                try {
                    if (typedArrayObtainStyledAttributes.hasValue(0)) {
                        i3 = typedArrayObtainStyledAttributes.getInt(0, 0);
                    }
                } catch (Exception unused) {
                    if (typedArrayObtainStyledAttributes != null) {
                    }
                    if (i3 != 0) {
                        c cVar = new c();
                        this.f = cVar;
                        cVar.f(r2VarU.o(R$styleable.Spinner_android_prompt));
                    } else if (i3 == 1) {
                        e eVar = new e(this.b, attributeSet, i2);
                        r2 r2VarU2 = r2.u(this.b, attributeSet, R$styleable.H, i2, 0);
                        this.g = r2VarU2.m(R$styleable.Spinner_android_dropDownWidth, -2);
                        eVar.setBackgroundDrawable(r2VarU2.g(R$styleable.Spinner_android_popupBackground));
                        eVar.f(r2VarU.o(R$styleable.Spinner_android_prompt));
                        r2VarU2.v();
                        this.f = eVar;
                        this.c = new a(this, eVar);
                    }
                    charSequenceArrQ = r2VarU.q(R$styleable.Spinner_android_entries);
                    if (charSequenceArrQ != null) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.simple_spinner_item, charSequenceArrQ);
                        arrayAdapter.setDropDownViewResource(R$layout.support_simple_spinner_dropdown_item);
                        setAdapter((SpinnerAdapter) arrayAdapter);
                    }
                    r2VarU.v();
                    this.e = true;
                    spinnerAdapter = this.d;
                    if (spinnerAdapter != null) {
                        setAdapter(spinnerAdapter);
                        this.d = null;
                    }
                    this.a.e(attributeSet, i2);
                } catch (Throwable th) {
                    th = th;
                    typedArray = typedArrayObtainStyledAttributes;
                    if (typedArray != null) {
                        typedArray.recycle();
                    }
                    throw th;
                }
            } catch (Exception unused2) {
                typedArrayObtainStyledAttributes = null;
            } catch (Throwable th2) {
                th = th2;
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        if (i3 != 0) {
            c cVar2 = new c();
            this.f = cVar2;
            cVar2.f(r2VarU.o(R$styleable.Spinner_android_prompt));
        } else if (i3 == 1) {
            e eVar2 = new e(this.b, attributeSet, i2);
            r2 r2VarU3 = r2.u(this.b, attributeSet, R$styleable.H, i2, 0);
            this.g = r2VarU3.m(R$styleable.Spinner_android_dropDownWidth, -2);
            eVar2.setBackgroundDrawable(r2VarU3.g(R$styleable.Spinner_android_popupBackground));
            eVar2.f(r2VarU.o(R$styleable.Spinner_android_prompt));
            r2VarU3.v();
            this.f = eVar2;
            this.c = new a(this, eVar2);
        }
        charSequenceArrQ = r2VarU.q(R$styleable.Spinner_android_entries);
        if (charSequenceArrQ != null) {
            ArrayAdapter arrayAdapter2 = new ArrayAdapter(context, R.layout.simple_spinner_item, charSequenceArrQ);
            arrayAdapter2.setDropDownViewResource(R$layout.support_simple_spinner_dropdown_item);
            setAdapter((SpinnerAdapter) arrayAdapter2);
        }
        r2VarU.v();
        this.e = true;
        spinnerAdapter = this.d;
        if (spinnerAdapter != null) {
            setAdapter(spinnerAdapter);
            this.d = null;
        }
        this.a.e(attributeSet, i2);
    }
}
