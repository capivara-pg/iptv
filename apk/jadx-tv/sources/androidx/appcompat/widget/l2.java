package androidx.appcompat.widget;

import android.R;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class l2 extends a0.c implements View.OnClickListener {
    private final SearchManager l;
    private final SearchView m;
    private final SearchableInfo n;
    private final Context o;
    private final WeakHashMap p;
    private final int q;
    private boolean r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f17s;
    private ColorStateList t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    private static final class a {
        public final TextView a;
        public final TextView b;
        public final ImageView c;
        public final ImageView d;
        public final ImageView e;

        public a(View view) {
            this.a = (TextView) view.findViewById(R.id.text1);
            this.b = (TextView) view.findViewById(R.id.text2);
            this.c = (ImageView) view.findViewById(R.id.icon1);
            this.d = (ImageView) view.findViewById(R.id.icon2);
            this.e = (ImageView) view.findViewById(R$id.edit_query);
        }
    }

    public l2(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), (Cursor) null, true);
        this.r = false;
        this.f17s = 1;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = -1;
        this.l = (SearchManager) ((a0.a) this).d.getSystemService("search");
        this.m = searchView;
        this.n = searchableInfo;
        this.q = searchView.getSuggestionCommitIconResId();
        this.o = context;
        this.p = weakHashMap;
    }

    private void A(String str, Drawable drawable) {
        if (drawable != null) {
            this.p.put(str, drawable.getConstantState());
        }
    }

    private void B(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    private Drawable k(String str) {
        Drawable.ConstantState constantState = (Drawable.ConstantState) this.p.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    private CharSequence l(CharSequence charSequence) {
        if (this.t == null) {
            TypedValue typedValue = new TypedValue();
            ((a0.a) this).d.getTheme().resolveAttribute(R$attr.textColorSearchUrl, typedValue, true);
            this.t = ((a0.a) this).d.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.t, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private Drawable m(ComponentName componentName) {
        PackageManager packageManager = ((a0.a) this).d.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid icon resource ");
            sb.append(iconResource);
            sb.append(" for ");
            sb.append(componentName.flattenToShortString());
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            e.toString();
            return null;
        }
    }

    private Drawable n(ComponentName componentName) {
        String strFlattenToShortString = componentName.flattenToShortString();
        if (!this.p.containsKey(strFlattenToShortString)) {
            Drawable drawableM = m(componentName);
            this.p.put(strFlattenToShortString, drawableM != null ? drawableM.getConstantState() : null);
            return drawableM;
        }
        Drawable.ConstantState constantState = (Drawable.ConstantState) this.p.get(strFlattenToShortString);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable(this.o.getResources());
    }

    public static String o(Cursor cursor, String str) {
        return w(cursor, cursor.getColumnIndex(str));
    }

    private Drawable p(Cursor cursor) {
        Drawable drawableN = n(this.n.getSearchActivity());
        return drawableN != null ? drawableN : ((a0.a) this).d.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable q(Uri uri) {
        try {
            if ("android.resource".equals(uri.getScheme())) {
                try {
                    return r(uri);
                } catch (Resources.NotFoundException unused) {
                    throw new FileNotFoundException("Resource does not exist: " + uri);
                }
            }
            InputStream inputStreamOpenInputStream = this.o.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            try {
                Drawable drawableCreateFromStream = Drawable.createFromStream(inputStreamOpenInputStream, null);
                try {
                    inputStreamOpenInputStream.close();
                } catch (IOException e) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e);
                }
                return drawableCreateFromStream;
            } catch (Throwable th) {
                try {
                    inputStreamOpenInputStream.close();
                } catch (IOException e2) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e2);
                }
                throw th;
            }
        } catch (FileNotFoundException e3) {
            StringBuilder sb = new StringBuilder();
            sb.append("Icon not found: ");
            sb.append(uri);
            sb.append(", ");
            sb.append(e3.getMessage());
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Icon not found: ");
        sb2.append(uri);
        sb2.append(", ");
        sb2.append(e3.getMessage());
        return null;
    }

    private Drawable s(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int i = Integer.parseInt(str);
            String str2 = "android.resource://" + this.o.getPackageName() + "/" + i;
            Drawable drawableK = k(str2);
            if (drawableK != null) {
                return drawableK;
            }
            Drawable drawable = androidx.core.content.a.getDrawable(this.o, i);
            A(str2, drawable);
            return drawable;
        } catch (Resources.NotFoundException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Icon resource not found: ");
            sb.append(str);
            return null;
        } catch (NumberFormatException unused2) {
            Drawable drawableK2 = k(str);
            if (drawableK2 != null) {
                return drawableK2;
            }
            Drawable drawableQ = q(Uri.parse(str));
            A(str, drawableQ);
            return drawableQ;
        }
    }

    private Drawable t(Cursor cursor) {
        int i = this.x;
        if (i == -1) {
            return null;
        }
        Drawable drawableS = s(cursor.getString(i));
        return drawableS != null ? drawableS : p(cursor);
    }

    private Drawable u(Cursor cursor) {
        int i = this.y;
        if (i == -1) {
            return null;
        }
        return s(cursor.getString(i));
    }

    private static String w(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }

    private void y(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    private void z(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    public void a(Cursor cursor) {
        if (this.r) {
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super/*a0.a*/.a(cursor);
            if (cursor != null) {
                this.u = cursor.getColumnIndex("suggest_text_1");
                this.v = cursor.getColumnIndex("suggest_text_2");
                this.w = cursor.getColumnIndex("suggest_text_2_url");
                this.x = cursor.getColumnIndex("suggest_icon_1");
                this.y = cursor.getColumnIndex("suggest_icon_2");
                this.z = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e);
        }
    }

    public CharSequence b(Cursor cursor) {
        String strO;
        String strO2;
        if (cursor == null) {
            return null;
        }
        String strO3 = o(cursor, "suggest_intent_query");
        if (strO3 != null) {
            return strO3;
        }
        if (this.n.shouldRewriteQueryFromData() && (strO2 = o(cursor, "suggest_intent_data")) != null) {
            return strO2;
        }
        if (!this.n.shouldRewriteQueryFromText() || (strO = o(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return strO;
    }

    public Cursor c(CharSequence charSequence) {
        String string = charSequence == null ? "" : charSequence.toString();
        if (this.m.getVisibility() == 0 && this.m.getWindowVisibility() == 0) {
            try {
                Cursor cursorV = v(this.n, string, 50);
                if (cursorV != null) {
                    cursorV.getCount();
                    return cursorV;
                }
            } catch (RuntimeException unused) {
            }
        }
        return null;
    }

    public void e(View view, Context context, Cursor cursor) {
        a aVar = (a) view.getTag();
        int i = this.z;
        int i2 = i != -1 ? cursor.getInt(i) : 0;
        if (aVar.a != null) {
            z(aVar.a, w(cursor, this.u));
        }
        if (aVar.b != null) {
            String strW = w(cursor, this.w);
            CharSequence charSequenceL = strW != null ? l(strW) : w(cursor, this.v);
            if (TextUtils.isEmpty(charSequenceL)) {
                TextView textView = aVar.a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    aVar.a.setMaxLines(2);
                }
            } else {
                TextView textView2 = aVar.a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    aVar.a.setMaxLines(1);
                }
            }
            z(aVar.b, charSequenceL);
        }
        ImageView imageView = aVar.c;
        if (imageView != null) {
            y(imageView, t(cursor), 4);
        }
        ImageView imageView2 = aVar.d;
        if (imageView2 != null) {
            y(imageView2, u(cursor), 8);
        }
        int i3 = this.f17s;
        if (i3 != 2 && (i3 != 1 || (i2 & 1) == 0)) {
            aVar.e.setVisibility(8);
            return;
        }
        aVar.e.setVisibility(0);
        aVar.e.setTag(aVar.a.getText());
        aVar.e.setOnClickListener(this);
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            return super/*a0.a*/.getDropDownView(i, view, viewGroup);
        } catch (RuntimeException e) {
            View viewG = g(((a0.a) this).d, ((a0.a) this).c, viewGroup);
            if (viewG != null) {
                ((a) viewG.getTag()).a.setText(e.toString());
            }
            return viewG;
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super/*a0.a*/.getView(i, view, viewGroup);
        } catch (RuntimeException e) {
            View viewH = h(((a0.a) this).d, ((a0.a) this).c, viewGroup);
            if (viewH != null) {
                ((a) viewH.getTag()).a.setText(e.toString());
            }
            return viewH;
        }
    }

    public View h(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewH = super.h(context, cursor, viewGroup);
        viewH.setTag(new a(viewH));
        ((ImageView) viewH.findViewById(R$id.edit_query)).setImageResource(this.q);
        return viewH;
    }

    public boolean hasStableIds() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void notifyDataSetChanged() {
        super/*android.widget.BaseAdapter*/.notifyDataSetChanged();
        B(d());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void notifyDataSetInvalidated() {
        super/*android.widget.BaseAdapter*/.notifyDataSetInvalidated();
        B(d());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.m.z((CharSequence) tag);
        }
    }

    Drawable r(Uri uri) throws FileNotFoundException {
        int identifier;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = ((a0.a) this).d.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    identifier = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else {
                if (size != 2) {
                    throw new FileNotFoundException("More than two path segments: " + uri);
                }
                identifier = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
            }
            if (identifier != 0) {
                return resourcesForApplication.getDrawable(identifier);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    Cursor v(SearchableInfo searchableInfo, String str, int i) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder builderFragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            builderFragment.appendEncodedPath(suggestPath);
        }
        builderFragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            builderFragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i > 0) {
            builderFragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return ((a0.a) this).d.getContentResolver().query(builderFragment.build(), null, suggestSelection, strArr2, null);
    }

    public void x(int i) {
        this.f17s = i;
    }
}
