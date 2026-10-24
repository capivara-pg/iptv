package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class SearchView extends u1 implements f.c {
    static final k d0 = new k();
    private boolean A;
    private CharSequence B;
    private boolean C;
    private boolean D;
    private int E;
    private boolean F;
    private CharSequence G;
    private CharSequence H;
    private boolean I;
    private int J;
    SearchableInfo K;
    private Bundle L;
    private final Runnable M;
    private Runnable N;
    private final WeakHashMap O;
    private final View.OnClickListener Q;
    View.OnKeyListener S;
    private final TextView.OnEditorActionListener V;
    private final AdapterView.OnItemClickListener W;
    final SearchAutoComplete a;
    private final View b;
    private final AdapterView.OnItemSelectedListener b0;
    private final View c;
    private TextWatcher c0;
    private final View d;
    final ImageView e;
    final ImageView f;
    final ImageView g;
    final ImageView h;
    private final View i;
    private p j;
    private Rect k;
    private Rect l;
    private int[] m;
    private int[] n;
    private final ImageView o;
    private final Drawable p;
    private final int q;
    private final int r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final Intent f15s;
    private final Intent t;
    private final CharSequence u;
    View.OnFocusChangeListener v;
    private View.OnClickListener w;
    private boolean x;
    private boolean y;
    a0.a z;

    public static class SearchAutoComplete extends androidx.appcompat.widget.e {
        private int d;
        private SearchView e;
        private boolean f;
        final Runnable g;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SearchAutoComplete.this.b();
            }
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i < 600) {
                return (i < 640 || i2 < 480) ? 160 : 192;
            }
            return 192;
        }

        boolean a() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        void b() {
            if (this.f) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.f = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.d <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.e, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f) {
                removeCallbacks(this.g);
                post(this.g);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        protected void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        protected void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.e.E();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.e.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.e.hasFocus() && getVisibility() == 0) {
                this.f = true;
                if (SearchView.r(getContext())) {
                    SearchView.d0.c(this, true);
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        protected void replaceText(CharSequence charSequence) {
        }

        void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.f = false;
                removeCallbacks(this.g);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.f = true;
                    return;
                }
                this.f = false;
                removeCallbacks(this.g);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        void setSearchView(SearchView searchView) {
            this.e = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.d = i;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.g = new a();
            this.d = getThreshold();
        }
    }

    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            SearchView.this.D(charSequence);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.K();
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a0.a aVar = SearchView.this.z;
            if (aVar instanceof l2) {
                aVar.a((Cursor) null);
            }
        }
    }

    class d implements View.OnFocusChangeListener {
        d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.v;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z);
            }
        }
    }

    class e implements View.OnLayoutChangeListener {
        e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            SearchView.this.g();
        }
    }

    class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.e) {
                searchView.A();
                return;
            }
            if (view == searchView.g) {
                searchView.w();
                return;
            }
            if (view == searchView.f) {
                searchView.B();
            } else if (view == searchView.h) {
                searchView.F();
            } else if (view == searchView.a) {
                searchView.m();
            }
        }
    }

    class g implements View.OnKeyListener {
        g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.K == null) {
                return false;
            }
            if (searchView.a.isPopupShowing() && SearchView.this.a.getListSelection() != -1) {
                return SearchView.this.C(view, i, keyEvent);
            }
            if (SearchView.this.a.a() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
                return false;
            }
            view.cancelLongPress();
            SearchView searchView2 = SearchView.this;
            searchView2.u(0, null, searchView2.a.getText().toString());
            return true;
        }
    }

    class h implements TextView.OnEditorActionListener {
        h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            SearchView.this.B();
            return true;
        }
    }

    class i implements AdapterView.OnItemClickListener {
        i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i, long j) {
            SearchView.this.x(i, 0, null);
        }
    }

    class j implements AdapterView.OnItemSelectedListener {
        j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            SearchView.this.y(i);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    private static class k {
        private Method a;
        private Method b;
        private Method c;

        k() {
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.c = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        void a(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void b(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void c(AutoCompleteTextView autoCompleteTextView, boolean z) {
            Method method = this.c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.valueOf(z));
                } catch (Exception unused) {
                }
            }
        }
    }

    public interface l {
    }

    public interface m {
    }

    public interface n {
    }

    static class o extends b0.a {
        public static final Parcelable.Creator<o> CREATOR = new a();
        boolean a;

        static class a implements Parcelable.ClassLoaderCreator {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public o createFromParcel(Parcel parcel) {
                return new o(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public o createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new o(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public o[] newArray(int i) {
                return new o[i];
            }
        }

        o(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.a + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.a));
        }

        public o(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    private static class p extends TouchDelegate {
        private final View a;
        private final Rect b;
        private final Rect c;
        private final Rect d;
        private final int e;
        private boolean f;

        public p(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.b = new Rect();
            this.d = new Rect();
            this.c = new Rect();
            a(rect, rect2);
            this.a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.b.set(rect);
            this.d.set(rect);
            Rect rect3 = this.d;
            int i = this.e;
            rect3.inset(-i, -i);
            this.c.set(rect2);
        }

        /* JADX WARN: Code duplicated, block: B:20:0x003d  */
        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z;
            boolean z2;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z3 = true;
            if (action != 0) {
                if (action == 1 || action == 2) {
                    z2 = this.f;
                    if (z2 && !this.d.contains(x, y)) {
                        z3 = z2;
                        z = false;
                    }
                } else if (action != 3) {
                    z = true;
                    z3 = false;
                } else {
                    z2 = this.f;
                    this.f = false;
                }
                z3 = z2;
                z = true;
            } else if (this.b.contains(x, y)) {
                this.f = true;
                z = true;
            } else {
                z = true;
                z3 = false;
            }
            if (!z3) {
                return false;
            }
            if (!z || this.c.contains(x, y)) {
                Rect rect = this.c;
                motionEvent.setLocation(x - rect.left, y - rect.top);
            } else {
                motionEvent.setLocation(this.a.getWidth() / 2, this.a.getHeight() / 2);
            }
            return this.a.dispatchTouchEvent(motionEvent);
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private void G() {
        post(this.M);
    }

    private void H(int i2) {
        Editable text = this.a.getText();
        Cursor cursorD = this.z.d();
        if (cursorD == null) {
            return;
        }
        if (!cursorD.moveToPosition(i2)) {
            setQuery(text);
            return;
        }
        CharSequence charSequenceB = this.z.b(cursorD);
        if (charSequenceB != null) {
            setQuery(charSequenceB);
        } else {
            setQuery(text);
        }
    }

    private void J() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.a.getText());
        if (!z2 && (!this.x || this.I)) {
            z = false;
        }
        this.g.setVisibility(z ? 0 : 8);
        Drawable drawable = this.g.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    private void L() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.a;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(o(queryHint));
    }

    private void M() {
        this.a.setThreshold(this.K.getSuggestThreshold());
        this.a.setImeOptions(this.K.getImeOptions());
        int inputType = this.K.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.K.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.a.setInputType(inputType);
        a0.a aVar = this.z;
        if (aVar != null) {
            aVar.a((Cursor) null);
        }
        if (this.K.getSuggestAuthority() != null) {
            l2 l2Var = new l2(getContext(), this, this.K, this.O);
            this.z = l2Var;
            this.a.setAdapter(l2Var);
            this.z.x(this.C ? 2 : 1);
        }
    }

    private void N() {
        this.d.setVisibility((s() && (this.f.getVisibility() == 0 || this.h.getVisibility() == 0)) ? 0 : 8);
    }

    private void O(boolean z) {
        this.f.setVisibility((this.A && s() && hasFocus() && (z || !this.F)) ? 0 : 8);
    }

    private void P(boolean z) {
        this.y = z;
        int i2 = z ? 0 : 8;
        boolean z2 = !TextUtils.isEmpty(this.a.getText());
        this.e.setVisibility(i2);
        O(z2);
        this.b.setVisibility(z ? 8 : 0);
        this.o.setVisibility((this.o.getDrawable() == null || this.x) ? 8 : 0);
        J();
        Q(!z2);
        N();
    }

    private void Q(boolean z) {
        int i2 = 8;
        if (this.F && !q() && z) {
            this.f.setVisibility(8);
            i2 = 0;
        }
        this.h.setVisibility(i2);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_width);
    }

    private Intent h(String str, Uri uri, String str2, String str3, int i2, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.H);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.L;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i2 != 0) {
            intent.putExtra("action_key", i2);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.K.getSearchActivity());
        return intent;
    }

    private Intent i(Cursor cursor, int i2, String str) {
        int position;
        String strO;
        try {
            try {
                String strO2 = l2.o(cursor, "suggest_intent_action");
                if (strO2 == null) {
                    strO2 = this.K.getSuggestIntentAction();
                }
                if (strO2 == null) {
                    strO2 = "android.intent.action.SEARCH";
                }
                String str2 = strO2;
                String strO3 = l2.o(cursor, "suggest_intent_data");
                if (strO3 == null) {
                    strO3 = this.K.getSuggestIntentData();
                }
                if (strO3 != null && (strO = l2.o(cursor, "suggest_intent_data_id")) != null) {
                    strO3 = strO3 + "/" + Uri.encode(strO);
                }
                return h(str2, strO3 == null ? null : Uri.parse(strO3), l2.o(cursor, "suggest_intent_extra_data"), l2.o(cursor, "suggest_intent_query"), i2, str);
            } catch (RuntimeException unused) {
                position = -1;
                StringBuilder sb = new StringBuilder();
                sb.append("Search suggestions cursor at row ");
                sb.append(position);
                sb.append(" returned exception.");
                return null;
            }
        } catch (RuntimeException unused2) {
            position = cursor.getPosition();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Search suggestions cursor at row ");
            sb2.append(position);
            sb2.append(" returned exception.");
            return null;
        }
    }

    private Intent j(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.L;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    private Intent k(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    private void l() {
        this.a.dismissDropDown();
    }

    private void n(View view, Rect rect) {
        view.getLocationInWindow(this.m);
        getLocationInWindow(this.n);
        int[] iArr = this.m;
        int i2 = iArr[1];
        int[] iArr2 = this.n;
        int i3 = i2 - iArr2[1];
        int i4 = iArr[0] - iArr2[0];
        rect.set(i4, i3, view.getWidth() + i4, view.getHeight() + i3);
    }

    private CharSequence o(CharSequence charSequence) {
        if (!this.x || this.p == null) {
            return charSequence;
        }
        double textSize = this.a.getTextSize();
        Double.isNaN(textSize);
        int i2 = (int) (textSize * 1.25d);
        this.p.setBounds(0, 0, i2, i2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.p), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private boolean p() {
        Intent intent;
        SearchableInfo searchableInfo = this.K;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        if (this.K.getVoiceSearchLaunchWebSearch()) {
            intent = this.f15s;
        } else {
            intent = this.K.getVoiceSearchLaunchRecognizer() ? this.t : null;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    static boolean r(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private boolean s() {
        return (this.A || this.F) && !q();
    }

    private void setQuery(CharSequence charSequence) {
        this.a.setText(charSequence);
        this.a.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    private void t(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e2) {
            Log.e("SearchView", "Failed launch activity: " + intent, e2);
        }
    }

    private boolean v(int i2, int i3, String str) {
        Cursor cursorD = this.z.d();
        if (cursorD == null || !cursorD.moveToPosition(i2)) {
            return false;
        }
        t(i(cursorD, i3, str));
        return true;
    }

    void A() {
        P(false);
        this.a.requestFocus();
        this.a.setImeVisibility(true);
        View.OnClickListener onClickListener = this.w;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    void B() {
        Editable text = this.a.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        if (this.K != null) {
            u(0, null, text.toString());
        }
        this.a.setImeVisibility(false);
        l();
    }

    boolean C(View view, int i2, KeyEvent keyEvent) {
        if (this.K != null && this.z != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i2 == 66 || i2 == 84 || i2 == 61) {
                return x(this.a.getListSelection(), 0, null);
            }
            if (i2 == 21 || i2 == 22) {
                this.a.setSelection(i2 == 21 ? 0 : this.a.length());
                this.a.setListSelection(0);
                this.a.clearListSelection();
                d0.c(this.a, true);
                return true;
            }
            if (i2 == 19) {
                this.a.getListSelection();
                return false;
            }
        }
        return false;
    }

    void D(CharSequence charSequence) {
        Editable text = this.a.getText();
        this.H = text;
        boolean z = !TextUtils.isEmpty(text);
        O(z);
        Q(!z);
        J();
        N();
        this.G = charSequence.toString();
    }

    void E() {
        P(q());
        G();
        if (this.a.hasFocus()) {
            m();
        }
    }

    void F() {
        SearchableInfo searchableInfo = this.K;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(k(this.f15s, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(j(this.t, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
        }
    }

    public void I(CharSequence charSequence, boolean z) {
        this.a.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.a;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.H = charSequence;
        }
        if (!z || TextUtils.isEmpty(charSequence)) {
            return;
        }
        B();
    }

    void K() {
        int[] iArr = this.a.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.c.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.d.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public void a() {
        if (this.I) {
            return;
        }
        this.I = true;
        int imeOptions = this.a.getImeOptions();
        this.J = imeOptions;
        this.a.setImeOptions(imeOptions | 33554432);
        this.a.setText("");
        setIconified(false);
    }

    public void c() {
        I("", false);
        clearFocus();
        P(true);
        this.a.setImeOptions(this.J);
        this.I = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.D = true;
        super.clearFocus();
        this.a.clearFocus();
        this.a.setImeVisibility(false);
        this.D = false;
    }

    void g() {
        if (this.i.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.c.getPaddingLeft();
            Rect rect = new Rect();
            boolean zB = y2.b(this);
            int dimensionPixelSize = this.x ? resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_text_padding_left) : 0;
            this.a.getDropDownBackground().getPadding(rect);
            this.a.setDropDownHorizontalOffset(zB ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            this.a.setDropDownWidth((((this.i.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    public int getImeOptions() {
        return this.a.getImeOptions();
    }

    public int getInputType() {
        return this.a.getInputType();
    }

    public int getMaxWidth() {
        return this.E;
    }

    public CharSequence getQuery() {
        return this.a.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.B;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.K;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.u : getContext().getText(this.K.getHintId());
    }

    int getSuggestionCommitIconResId() {
        return this.r;
    }

    int getSuggestionRowLayout() {
        return this.q;
    }

    public a0.a getSuggestionsAdapter() {
        return this.z;
    }

    void m() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.a.refreshAutoCompleteResults();
            return;
        }
        k kVar = d0;
        kVar.b(this.a);
        kVar.a(this.a);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.M);
        post(this.N);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.u1, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            n(this.a, this.k);
            Rect rect = this.l;
            Rect rect2 = this.k;
            rect.set(rect2.left, 0, rect2.right, i5 - i3);
            p pVar = this.j;
            if (pVar != null) {
                pVar.a(this.l, this.k);
                return;
            }
            p pVar2 = new p(this.l, this.k, this.a);
            this.j = pVar2;
            setTouchDelegate(pVar2);
        }
    }

    @Override // androidx.appcompat.widget.u1, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        if (q()) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            int i5 = this.E;
            size = i5 > 0 ? Math.min(i5, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.E;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i4 = this.E) > 0) {
            size = Math.min(i4, size);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof o)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        o oVar = (o) parcelable;
        super.onRestoreInstanceState(oVar.getSuperState());
        P(oVar.a);
        requestLayout();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.os.Parcelable, androidx.appcompat.widget.SearchView$o] */
    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        ?? oVar = new o(super.onSaveInstanceState());
        oVar.a = q();
        return oVar;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        G();
    }

    public boolean q() {
        return this.y;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i2, Rect rect) {
        if (this.D || !isFocusable()) {
            return false;
        }
        if (q()) {
            return super.requestFocus(i2, rect);
        }
        boolean zRequestFocus = this.a.requestFocus(i2, rect);
        if (zRequestFocus) {
            P(false);
        }
        return zRequestFocus;
    }

    public void setAppSearchData(Bundle bundle) {
        this.L = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            w();
        } else {
            A();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.x == z) {
            return;
        }
        this.x = z;
        P(z);
        L();
    }

    public void setImeOptions(int i2) {
        this.a.setImeOptions(i2);
    }

    public void setInputType(int i2) {
        this.a.setInputType(i2);
    }

    public void setMaxWidth(int i2) {
        this.E = i2;
        requestLayout();
    }

    public void setOnCloseListener(l lVar) {
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.v = onFocusChangeListener;
    }

    public void setOnQueryTextListener(m mVar) {
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.w = onClickListener;
    }

    public void setOnSuggestionListener(n nVar) {
    }

    public void setQueryHint(CharSequence charSequence) {
        this.B = charSequence;
        L();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.C = z;
        l2 l2Var = this.z;
        if (l2Var instanceof l2) {
            l2Var.x(z ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.K = searchableInfo;
        if (searchableInfo != null) {
            M();
            L();
        }
        boolean zP = p();
        this.F = zP;
        if (zP) {
            this.a.setPrivateImeOptions("nm");
        }
        P(q());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.A = z;
        P(q());
    }

    public void setSuggestionsAdapter(a0.a aVar) {
        this.z = aVar;
        this.a.setAdapter(aVar);
    }

    void u(int i2, String str, String str2) {
        getContext().startActivity(h("android.intent.action.SEARCH", null, null, str2, i2, str));
    }

    void w() {
        if (!TextUtils.isEmpty(this.a.getText())) {
            this.a.setText("");
            this.a.requestFocus();
            this.a.setImeVisibility(true);
        } else if (this.x) {
            clearFocus();
            P(true);
        }
    }

    boolean x(int i2, int i3, String str) {
        v(i2, 0, null);
        this.a.setImeVisibility(false);
        l();
        return true;
    }

    boolean y(int i2) {
        H(i2);
        return true;
    }

    void z(CharSequence charSequence) {
        setQuery(charSequence);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.k = new Rect();
        this.l = new Rect();
        this.m = new int[2];
        this.n = new int[2];
        this.M = new b();
        this.N = new c();
        this.O = new WeakHashMap();
        f fVar = new f();
        this.Q = fVar;
        this.S = new g();
        h hVar = new h();
        this.V = hVar;
        i iVar = new i();
        this.W = iVar;
        j jVar = new j();
        this.b0 = jVar;
        this.c0 = new a();
        r2 r2VarU = r2.u(context, attributeSet, R$styleable.G, i2, 0);
        LayoutInflater.from(context).inflate(r2VarU.n(R$styleable.SearchView_layout, R$layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R$id.search_src_text);
        this.a = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.b = findViewById(R$id.search_edit_frame);
        View viewFindViewById = findViewById(R$id.search_plate);
        this.c = viewFindViewById;
        View viewFindViewById2 = findViewById(R$id.submit_area);
        this.d = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(R$id.search_button);
        this.e = imageView;
        ImageView imageView2 = (ImageView) findViewById(R$id.search_go_btn);
        this.f = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R$id.search_close_btn);
        this.g = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R$id.search_voice_btn);
        this.h = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R$id.search_mag_icon);
        this.o = imageView5;
        androidx.core.view.c1.n0(viewFindViewById, r2VarU.g(R$styleable.SearchView_queryBackground));
        androidx.core.view.c1.n0(viewFindViewById2, r2VarU.g(R$styleable.SearchView_submitBackground));
        int i3 = R$styleable.SearchView_searchIcon;
        imageView.setImageDrawable(r2VarU.g(i3));
        imageView2.setImageDrawable(r2VarU.g(R$styleable.SearchView_goIcon));
        imageView3.setImageDrawable(r2VarU.g(R$styleable.SearchView_closeIcon));
        imageView4.setImageDrawable(r2VarU.g(R$styleable.SearchView_voiceIcon));
        imageView5.setImageDrawable(r2VarU.g(i3));
        this.p = r2VarU.g(R$styleable.SearchView_searchHintIcon);
        u2.a(imageView, getResources().getString(R$string.abc_searchview_description_search));
        this.q = r2VarU.n(R$styleable.SearchView_suggestionRowLayout, R$layout.abc_search_dropdown_item_icons_2line);
        this.r = r2VarU.n(R$styleable.SearchView_commitIcon, 0);
        imageView.setOnClickListener(fVar);
        imageView3.setOnClickListener(fVar);
        imageView2.setOnClickListener(fVar);
        imageView4.setOnClickListener(fVar);
        searchAutoComplete.setOnClickListener(fVar);
        searchAutoComplete.addTextChangedListener(this.c0);
        searchAutoComplete.setOnEditorActionListener(hVar);
        searchAutoComplete.setOnItemClickListener(iVar);
        searchAutoComplete.setOnItemSelectedListener(jVar);
        searchAutoComplete.setOnKeyListener(this.S);
        searchAutoComplete.setOnFocusChangeListener(new d());
        setIconifiedByDefault(r2VarU.a(R$styleable.SearchView_iconifiedByDefault, true));
        int iF = r2VarU.f(R$styleable.SearchView_android_maxWidth, -1);
        if (iF != -1) {
            setMaxWidth(iF);
        }
        this.u = r2VarU.p(R$styleable.SearchView_defaultQueryHint);
        this.B = r2VarU.p(R$styleable.SearchView_queryHint);
        int iK = r2VarU.k(R$styleable.SearchView_android_imeOptions, -1);
        if (iK != -1) {
            setImeOptions(iK);
        }
        int iK2 = r2VarU.k(R$styleable.SearchView_android_inputType, -1);
        if (iK2 != -1) {
            setInputType(iK2);
        }
        setFocusable(r2VarU.a(R$styleable.SearchView_android_focusable, true));
        r2VarU.v();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.f15s = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.t = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.i = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new e());
        }
        P(this.x);
        L();
    }
}
