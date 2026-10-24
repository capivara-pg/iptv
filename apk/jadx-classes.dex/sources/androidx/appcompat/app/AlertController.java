package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.u1;
import androidx.core.view.c1;
import androidx.core.widget.NestedScrollView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
class AlertController {
    NestedScrollView A;
    private Drawable C;
    private ImageView D;
    private TextView E;
    private TextView F;
    private View G;
    ListAdapter H;
    private int J;
    private int K;
    int L;
    int M;
    int N;
    int O;
    private boolean P;
    Handler R;
    private final Context a;
    final j b;
    private final Window c;
    private final int d;
    private CharSequence e;
    private CharSequence f;
    ListView g;
    private View h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    Button o;
    private CharSequence p;
    Message q;
    private Drawable r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    Button f2s;
    private CharSequence t;
    Message u;
    private Drawable v;
    Button w;
    private CharSequence x;
    Message y;
    private Drawable z;
    private boolean n = false;
    private int B = 0;
    int I = -1;
    private int Q = 0;
    private final View.OnClickListener S = new a();

    public static class RecycleListView extends ListView {
        private final int a;
        private final int b;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.F);
            this.b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.RecycleListView_paddingTopNoTitle, -1);
        }

        public void a(boolean z, boolean z2) {
            if (z2 && z) {
                return;
            }
            setPadding(getPaddingLeft(), z ? getPaddingTop() : this.a, getPaddingRight(), z2 ? getPaddingBottom() : this.b);
        }
    }

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message messageObtain;
            Message message;
            Message message2;
            Message message3;
            AlertController alertController = AlertController.this;
            if (view == alertController.o && (message3 = alertController.q) != null) {
                messageObtain = Message.obtain(message3);
            } else if (view != alertController.f2s || (message2 = alertController.u) == null) {
                messageObtain = (view != alertController.w || (message = alertController.y) == null) ? null : Message.obtain(message);
            } else {
                messageObtain = Message.obtain(message2);
            }
            if (messageObtain != null) {
                messageObtain.sendToTarget();
            }
            AlertController alertController2 = AlertController.this;
            alertController2.R.obtainMessage(1, alertController2.b).sendToTarget();
        }
    }

    class b implements NestedScrollView.b {
        final /* synthetic */ View a;
        final /* synthetic */ View b;

        b(View view, View view2) {
            this.a = view;
            this.b = view2;
        }

        @Override // androidx.core.widget.NestedScrollView.b
        public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
            AlertController.g(nestedScrollView, this.a, this.b);
        }
    }

    class c implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ View b;

        c(View view, View view2) {
            this.a = view;
            this.b = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.g(AlertController.this.A, this.a, this.b);
        }
    }

    class d implements AbsListView.OnScrollListener {
        final /* synthetic */ View a;
        final /* synthetic */ View b;

        d(View view, View view2) {
            this.a = view;
            this.b = view2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            AlertController.g(absListView, this.a, this.b);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    }

    class e implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ View b;

        e(View view, View view2) {
            this.a = view;
            this.b = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertController.g(AlertController.this.g, this.a, this.b);
        }
    }

    public static class f {
        public int A;
        public int B;
        public int C;
        public int D;
        public boolean[] F;
        public boolean G;
        public boolean H;
        public DialogInterface.OnMultiChoiceClickListener J;
        public Cursor K;
        public String L;
        public String M;
        public boolean N;
        public AdapterView.OnItemSelectedListener O;
        public final Context a;
        public final LayoutInflater b;
        public Drawable d;
        public CharSequence f;
        public View g;
        public CharSequence h;
        public CharSequence i;
        public Drawable j;
        public DialogInterface.OnClickListener k;
        public CharSequence l;
        public Drawable m;
        public DialogInterface.OnClickListener n;
        public CharSequence o;
        public Drawable p;
        public DialogInterface.OnClickListener q;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public DialogInterface.OnCancelListener f3s;
        public DialogInterface.OnDismissListener t;
        public DialogInterface.OnKeyListener u;
        public CharSequence[] v;
        public ListAdapter w;
        public DialogInterface.OnClickListener x;
        public int y;
        public View z;
        public int c = 0;
        public int e = 0;
        public boolean E = false;
        public int I = -1;
        public boolean P = true;
        public boolean r = true;

        class a extends ArrayAdapter {
            final /* synthetic */ RecycleListView a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Context context, int i, int i2, CharSequence[] charSequenceArr, RecycleListView recycleListView) {
                super(context, i, i2, charSequenceArr);
                this.a = recycleListView;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                boolean[] zArr = f.this.F;
                if (zArr != null && zArr[i]) {
                    this.a.setItemChecked(i, true);
                }
                return view2;
            }
        }

        class b extends CursorAdapter {
            private final int a;
            private final int b;
            final /* synthetic */ RecycleListView c;
            final /* synthetic */ AlertController d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(Context context, Cursor cursor, boolean z, RecycleListView recycleListView, AlertController alertController) {
                super(context, cursor, z);
                this.c = recycleListView;
                this.d = alertController;
                Cursor cursor2 = getCursor();
                this.a = cursor2.getColumnIndexOrThrow(f.this.L);
                this.b = cursor2.getColumnIndexOrThrow(f.this.M);
            }

            @Override // android.widget.CursorAdapter
            public void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor.getString(this.a));
                this.c.setItemChecked(cursor.getPosition(), cursor.getInt(this.b) == 1);
            }

            @Override // android.widget.CursorAdapter
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return f.this.b.inflate(this.d.M, viewGroup, false);
            }
        }

        class c implements AdapterView.OnItemClickListener {
            final /* synthetic */ AlertController a;

            c(AlertController alertController) {
                this.a = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                f.this.x.onClick(this.a.b, i);
                if (f.this.H) {
                    return;
                }
                this.a.b.dismiss();
            }
        }

        class d implements AdapterView.OnItemClickListener {
            final /* synthetic */ RecycleListView a;
            final /* synthetic */ AlertController b;

            d(RecycleListView recycleListView, AlertController alertController) {
                this.a = recycleListView;
                this.b = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                boolean[] zArr = f.this.F;
                if (zArr != null) {
                    zArr[i] = this.a.isItemChecked(i);
                }
                f.this.J.onClick(this.b.b, i, this.a.isItemChecked(i));
            }
        }

        public f(Context context) {
            this.a = context;
            this.b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        private void b(AlertController alertController) {
            ListAdapter hVar;
            RecycleListView recycleListView = (RecycleListView) this.b.inflate(alertController.L, (ViewGroup) null);
            if (this.G) {
                hVar = this.K == null ? new a(this.a, alertController.M, R.id.text1, this.v, recycleListView) : new b(this.a, this.K, false, recycleListView, alertController);
            } else {
                int i = this.H ? alertController.N : alertController.O;
                if (this.K != null) {
                    hVar = new SimpleCursorAdapter(this.a, i, this.K, new String[]{this.L}, new int[]{R.id.text1});
                } else {
                    hVar = this.w;
                    if (hVar == null) {
                        hVar = new h(this.a, i, R.id.text1, this.v);
                    }
                }
            }
            alertController.H = hVar;
            alertController.I = this.I;
            if (this.x != null) {
                recycleListView.setOnItemClickListener(new c(alertController));
            } else if (this.J != null) {
                recycleListView.setOnItemClickListener(new d(recycleListView, alertController));
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.O;
            if (onItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.H) {
                recycleListView.setChoiceMode(1);
            } else if (this.G) {
                recycleListView.setChoiceMode(2);
            }
            alertController.g = recycleListView;
        }

        public void a(AlertController alertController) {
            View view = this.g;
            if (view != null) {
                alertController.n(view);
            } else {
                CharSequence charSequence = this.f;
                if (charSequence != null) {
                    alertController.s(charSequence);
                }
                Drawable drawable = this.d;
                if (drawable != null) {
                    alertController.p(drawable);
                }
                int i = this.c;
                if (i != 0) {
                    alertController.o(i);
                }
                int i2 = this.e;
                if (i2 != 0) {
                    alertController.o(alertController.d(i2));
                }
            }
            CharSequence charSequence2 = this.h;
            if (charSequence2 != null) {
                alertController.q(charSequence2);
            }
            CharSequence charSequence3 = this.i;
            if (charSequence3 != null || this.j != null) {
                alertController.l(-1, charSequence3, this.k, null, this.j);
            }
            CharSequence charSequence4 = this.l;
            if (charSequence4 != null || this.m != null) {
                alertController.l(-2, charSequence4, this.n, null, this.m);
            }
            CharSequence charSequence5 = this.o;
            if (charSequence5 != null || this.p != null) {
                alertController.l(-3, charSequence5, this.q, null, this.p);
            }
            if (this.v != null || this.K != null || this.w != null) {
                b(alertController);
            }
            View view2 = this.z;
            if (view2 != null) {
                if (this.E) {
                    alertController.v(view2, this.A, this.B, this.C, this.D);
                    return;
                } else {
                    alertController.u(view2);
                    return;
                }
            }
            int i3 = this.y;
            if (i3 != 0) {
                alertController.t(i3);
            }
        }
    }

    private static final class g extends Handler {
        private WeakReference a;

        public g(DialogInterface dialogInterface) {
            this.a = new WeakReference(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == -3 || i == -2 || i == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.a.get(), message.what);
            } else {
                if (i != 1) {
                    return;
                }
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    private static class h extends ArrayAdapter {
        public h(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, j jVar, Window window) {
        this.a = context;
        this.b = jVar;
        this.c = window;
        this.R = new g(jVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.g, R$attr.alertDialogStyle, 0);
        this.J = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_android_layout, 0);
        this.K = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.L = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listLayout, 0);
        this.M = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.N = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.O = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listItemLayout, 0);
        this.P = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AlertDialog_showTitle, true);
        this.d = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.AlertDialog_buttonIconDimen, 0);
        typedArrayObtainStyledAttributes.recycle();
        jVar.supportRequestWindowFeature(1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void A() {
        View viewFindViewById;
        ListAdapter listAdapter;
        View viewFindViewById2;
        View viewFindViewById3 = this.c.findViewById(R$id.parentPanel);
        int i = R$id.topPanel;
        View viewFindViewById4 = viewFindViewById3.findViewById(i);
        int i2 = R$id.contentPanel;
        View viewFindViewById5 = viewFindViewById3.findViewById(i2);
        int i3 = R$id.buttonPanel;
        View viewFindViewById6 = viewFindViewById3.findViewById(i3);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById3.findViewById(R$id.customPanel);
        y(viewGroup);
        View viewFindViewById7 = viewGroup.findViewById(i);
        View viewFindViewById8 = viewGroup.findViewById(i2);
        View viewFindViewById9 = viewGroup.findViewById(i3);
        ViewGroup viewGroupJ = j(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupJ2 = j(viewFindViewById8, viewFindViewById5);
        ViewGroup viewGroupJ3 = j(viewFindViewById9, viewFindViewById6);
        x(viewGroupJ2);
        w(viewGroupJ3);
        z(viewGroupJ);
        boolean z = viewGroup.getVisibility() != 8;
        boolean z2 = (viewGroupJ == null || viewGroupJ.getVisibility() == 8) ? 0 : 1;
        boolean z3 = (viewGroupJ3 == null || viewGroupJ3.getVisibility() == 8) ? false : true;
        if (!z3 && viewGroupJ2 != null && (viewFindViewById2 = viewGroupJ2.findViewById(R$id.textSpacerNoButtons)) != null) {
            viewFindViewById2.setVisibility(0);
        }
        if (z2 != 0) {
            NestedScrollView nestedScrollView = this.A;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View viewFindViewById10 = (this.f == null && this.g == null) ? null : viewGroupJ.findViewById(R$id.titleDividerNoCustom);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        } else if (viewGroupJ2 != null && (viewFindViewById = viewGroupJ2.findViewById(R$id.textSpacerNoTitle)) != null) {
            viewFindViewById.setVisibility(0);
        }
        ListView listView = this.g;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).a(z2, z3);
        }
        if (!z) {
            View view = this.g;
            if (view == null) {
                view = this.A;
            }
            if (view != null) {
                r(viewGroupJ2, view, z2 | (z3 ? 2 : 0), 3);
            }
        }
        ListView listView2 = this.g;
        if (listView2 == null || (listAdapter = this.H) == null) {
            return;
        }
        listView2.setAdapter(listAdapter);
        int i4 = this.I;
        if (i4 > -1) {
            listView2.setItemChecked(i4, true);
            listView2.setSelection(i4);
        }
    }

    private static boolean B(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogCenterButtons, typedValue, true);
        return typedValue.data != 0;
    }

    static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    private void b(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    static void g(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(view.canScrollVertically(1) ? 0 : 4);
        }
    }

    private ViewGroup j(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    private int k() {
        int i = this.K;
        if (i == 0) {
            return this.J;
        }
        return this.Q == 1 ? i : this.J;
    }

    private void r(ViewGroup viewGroup, View view, int i, int i2) {
        View viewFindViewById = this.c.findViewById(R$id.scrollIndicatorUp);
        View viewFindViewById2 = this.c.findViewById(R$id.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            c1.A0(view, i, i2);
            if (viewFindViewById != null) {
                viewGroup.removeView(viewFindViewById);
            }
            if (viewFindViewById2 != null) {
                viewGroup.removeView(viewFindViewById2);
                return;
            }
            return;
        }
        if (viewFindViewById != null && (i & 1) == 0) {
            viewGroup.removeView(viewFindViewById);
            viewFindViewById = null;
        }
        if (viewFindViewById2 != null && (i & 2) == 0) {
            viewGroup.removeView(viewFindViewById2);
            viewFindViewById2 = null;
        }
        if (viewFindViewById == null && viewFindViewById2 == null) {
            return;
        }
        if (this.f != null) {
            this.A.setOnScrollChangeListener(new b(viewFindViewById, viewFindViewById2));
            this.A.post(new c(viewFindViewById, viewFindViewById2));
            return;
        }
        ListView listView = this.g;
        if (listView != null) {
            listView.setOnScrollListener(new d(viewFindViewById, viewFindViewById2));
            this.g.post(new e(viewFindViewById, viewFindViewById2));
            return;
        }
        if (viewFindViewById != null) {
            viewGroup.removeView(viewFindViewById);
        }
        if (viewFindViewById2 != null) {
            viewGroup.removeView(viewFindViewById2);
        }
    }

    private void w(ViewGroup viewGroup) {
        int i;
        Button button = (Button) viewGroup.findViewById(R.id.button1);
        this.o = button;
        button.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.p) && this.r == null) {
            this.o.setVisibility(8);
            i = 0;
        } else {
            this.o.setText(this.p);
            Drawable drawable = this.r;
            if (drawable != null) {
                int i2 = this.d;
                drawable.setBounds(0, 0, i2, i2);
                this.o.setCompoundDrawables(this.r, null, null, null);
            }
            this.o.setVisibility(0);
            i = 1;
        }
        Button button2 = (Button) viewGroup.findViewById(R.id.button2);
        this.f2s = button2;
        button2.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.t) && this.v == null) {
            this.f2s.setVisibility(8);
        } else {
            this.f2s.setText(this.t);
            Drawable drawable2 = this.v;
            if (drawable2 != null) {
                int i3 = this.d;
                drawable2.setBounds(0, 0, i3, i3);
                this.f2s.setCompoundDrawables(this.v, null, null, null);
            }
            this.f2s.setVisibility(0);
            i |= 2;
        }
        Button button3 = (Button) viewGroup.findViewById(R.id.button3);
        this.w = button3;
        button3.setOnClickListener(this.S);
        if (TextUtils.isEmpty(this.x) && this.z == null) {
            this.w.setVisibility(8);
        } else {
            this.w.setText(this.x);
            Drawable drawable3 = this.r;
            if (drawable3 != null) {
                int i4 = this.d;
                drawable3.setBounds(0, 0, i4, i4);
                this.o.setCompoundDrawables(this.r, null, null, null);
            }
            this.w.setVisibility(0);
            i |= 4;
        }
        if (B(this.a)) {
            if (i == 1) {
                b(this.o);
            } else if (i == 2) {
                b(this.f2s);
            } else if (i == 4) {
                b(this.w);
            }
        }
        if (i != 0) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void x(ViewGroup viewGroup) {
        NestedScrollView nestedScrollView = (NestedScrollView) this.c.findViewById(R$id.scrollView);
        this.A = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.A.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroup.findViewById(R.id.message);
        this.F = textView;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.f;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.A.removeView(this.F);
        if (this.g == null) {
            viewGroup.setVisibility(8);
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) this.A.getParent();
        int iIndexOfChild = viewGroup2.indexOfChild(this.A);
        viewGroup2.removeViewAt(iIndexOfChild);
        viewGroup2.addView(this.g, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
    }

    private void y(ViewGroup viewGroup) {
        View viewInflate = this.h;
        if (viewInflate == null) {
            viewInflate = this.i != 0 ? LayoutInflater.from(this.a).inflate(this.i, viewGroup, false) : null;
        }
        boolean z = viewInflate != null;
        if (!z || !a(viewInflate)) {
            this.c.setFlags(131072, 131072);
        }
        if (!z) {
            viewGroup.setVisibility(8);
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.c.findViewById(R$id.custom);
        frameLayout.addView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
        if (this.n) {
            frameLayout.setPadding(this.j, this.k, this.l, this.m);
        }
        if (this.g != null) {
            ((u1.a) viewGroup.getLayoutParams()).a = 0.0f;
        }
    }

    private void z(ViewGroup viewGroup) {
        if (this.G != null) {
            viewGroup.addView(this.G, 0, new ViewGroup.LayoutParams(-1, -2));
            this.c.findViewById(R$id.title_template).setVisibility(8);
            return;
        }
        this.D = (ImageView) this.c.findViewById(R.id.icon);
        if (!(!TextUtils.isEmpty(this.e)) || !this.P) {
            this.c.findViewById(R$id.title_template).setVisibility(8);
            this.D.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.c.findViewById(R$id.alertTitle);
        this.E = textView;
        textView.setText(this.e);
        int i = this.B;
        if (i != 0) {
            this.D.setImageResource(i);
            return;
        }
        Drawable drawable = this.C;
        if (drawable != null) {
            this.D.setImageDrawable(drawable);
        } else {
            this.E.setPadding(this.D.getPaddingLeft(), this.D.getPaddingTop(), this.D.getPaddingRight(), this.D.getPaddingBottom());
            this.D.setVisibility(8);
        }
    }

    public Button c(int i) {
        if (i == -3) {
            return this.w;
        }
        if (i == -2) {
            return this.f2s;
        }
        if (i != -1) {
            return null;
        }
        return this.o;
    }

    public int d(int i) {
        TypedValue typedValue = new TypedValue();
        this.a.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView e() {
        return this.g;
    }

    public void f() {
        this.b.setContentView(k());
        A();
    }

    public boolean h(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.executeKeyEvent(keyEvent);
    }

    public boolean i(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.A;
        return nestedScrollView != null && nestedScrollView.executeKeyEvent(keyEvent);
    }

    public void l(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.R.obtainMessage(i, onClickListener);
        }
        if (i == -3) {
            this.x = charSequence;
            this.y = message;
            this.z = drawable;
        } else if (i == -2) {
            this.t = charSequence;
            this.u = message;
            this.v = drawable;
        } else {
            if (i != -1) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.p = charSequence;
            this.q = message;
            this.r = drawable;
        }
    }

    public void m(int i) {
        this.Q = i;
    }

    public void n(View view) {
        this.G = view;
    }

    public void o(int i) {
        this.C = null;
        this.B = i;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (i == 0) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.D.setImageResource(this.B);
            }
        }
    }

    public void p(Drawable drawable) {
        this.C = drawable;
        this.B = 0;
        ImageView imageView = this.D;
        if (imageView != null) {
            if (drawable == null) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.D.setImageDrawable(drawable);
            }
        }
    }

    public void q(CharSequence charSequence) {
        this.f = charSequence;
        TextView textView = this.F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void s(CharSequence charSequence) {
        this.e = charSequence;
        TextView textView = this.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void t(int i) {
        this.h = null;
        this.i = i;
        this.n = false;
    }

    public void u(View view) {
        this.h = view;
        this.i = 0;
        this.n = false;
    }

    public void v(View view, int i, int i2, int i3, int i4) {
        this.h = view;
        this.i = 0;
        this.n = true;
        this.j = i;
        this.k = i2;
        this.l = i3;
        this.m = i4;
    }
}
