package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class e2 extends ContextWrapper {
    private static final Object c = new Object();
    private static ArrayList d;
    private final Resources a;
    private final Resources.Theme b;

    private e2(Context context) {
        super(context);
        if (!n2.b()) {
            this.a = new g2(this, context.getResources());
            this.b = null;
            return;
        }
        n2 n2Var = new n2(this, context.getResources());
        this.a = n2Var;
        Resources.Theme themeNewTheme = n2Var.newTheme();
        this.b = themeNewTheme;
        themeNewTheme.setTo(context.getTheme());
    }

    private static boolean a(Context context) {
        return ((context instanceof e2) || (context.getResources() instanceof g2) || (context.getResources() instanceof n2) || !n2.b()) ? false : true;
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (c) {
            ArrayList arrayList = d;
            if (arrayList == null) {
                d = new ArrayList();
            } else {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    WeakReference weakReference = (WeakReference) d.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        d.remove(size);
                    }
                }
                for (int size2 = d.size() - 1; size2 >= 0; size2--) {
                    WeakReference weakReference2 = (WeakReference) d.get(size2);
                    e2 e2Var = weakReference2 != null ? (e2) weakReference2.get() : null;
                    if (e2Var != null && e2Var.getBaseContext() == context) {
                        return e2Var;
                    }
                }
            }
            e2 e2Var2 = new e2(context);
            d.add(new WeakReference(e2Var2));
            return e2Var2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.a.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.b;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.b;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
