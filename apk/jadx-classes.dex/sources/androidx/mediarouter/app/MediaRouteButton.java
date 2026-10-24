package androidx.mediarouter.app;

import android.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.appcompat.widget.u2;
import androidx.fragment.app.o;
import androidx.fragment.app.y;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$string;
import androidx.mediarouter.R$styleable;
import h0.c1;
import h0.j0;
import h0.k0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /Users/marcelobogdanovicz/Documents/iptv/apk/dexdir/classes.dex */
public class MediaRouteButton extends View {
    private static a r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    static final SparseArray f28s = new SparseArray(2);
    private static final int[] t = {R.attr.state_checked};
    private static final int[] u = {R.attr.state_checkable};
    private final k0 a;
    private final b b;
    private j0 c;
    private e d;
    private boolean e;
    private int f;
    boolean g;
    c h;
    private Drawable i;
    private int j;
    private int k;
    private int l;
    private ColorStateList m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;

    private static final class a extends BroadcastReceiver {
        private final Context a;
        private boolean b = true;
        private List c = new ArrayList();

        a(Context context) {
            this.a = context;
        }

        public boolean a() {
            return this.b;
        }

        public void b(MediaRouteButton mediaRouteButton) {
            if (this.c.size() == 0) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                this.a.registerReceiver(this, intentFilter);
            }
            this.c.add(mediaRouteButton);
        }

        public void c(MediaRouteButton mediaRouteButton) {
            this.c.remove(mediaRouteButton);
            if (this.c.size() == 0) {
                this.a.unregisterReceiver(this);
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z;
            if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) || this.b == (z = !intent.getBooleanExtra("noConnectivity", false))) {
                return;
            }
            this.b = z;
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                ((MediaRouteButton) it.next()).c();
            }
        }
    }

    private final class b extends k0.a {
        b() {
        }

        public void onProviderAdded(k0 k0Var, k0.g gVar) {
            MediaRouteButton.this.b();
        }

        public void onProviderChanged(k0 k0Var, k0.g gVar) {
            MediaRouteButton.this.b();
        }

        public void onProviderRemoved(k0 k0Var, k0.g gVar) {
            MediaRouteButton.this.b();
        }

        public void onRouteAdded(k0 k0Var, k0.h hVar) {
            MediaRouteButton.this.b();
        }

        public void onRouteChanged(k0 k0Var, k0.h hVar) {
            MediaRouteButton.this.b();
        }

        public void onRouteRemoved(k0 k0Var, k0.h hVar) {
            MediaRouteButton.this.b();
        }

        public void onRouteSelected(k0 k0Var, k0.h hVar) {
            MediaRouteButton.this.b();
        }

        public void onRouteUnselected(k0 k0Var, k0.h hVar) {
            MediaRouteButton.this.b();
        }

        public void onRouterParamsChanged(k0 k0Var, c1 c1Var) {
            boolean z = c1Var != null ? c1Var.b().getBoolean("androidx.mediarouter.media.MediaRouterParams.FIXED_CAST_ICON") : false;
            MediaRouteButton mediaRouteButton = MediaRouteButton.this;
            if (mediaRouteButton.g != z) {
                mediaRouteButton.g = z;
                mediaRouteButton.refreshDrawableState();
            }
        }
    }

    private final class c extends AsyncTask {
        private final int a;
        private final Context b;

        c(int i, Context context) {
            this.a = i;
            this.b = context;
        }

        private void a(Drawable drawable) {
            if (drawable != null) {
                MediaRouteButton.f28s.put(this.a, drawable.getConstantState());
            }
            MediaRouteButton.this.h = null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Drawable doInBackground(Void... voidArr) {
            if (((Drawable.ConstantState) MediaRouteButton.f28s.get(this.a)) == null) {
                return d.b.d(this.b, this.a);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onCancelled(Drawable drawable) {
            a(drawable);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Drawable drawable) {
            if (drawable != null) {
                a(drawable);
            } else {
                Drawable.ConstantState constantState = (Drawable.ConstantState) MediaRouteButton.f28s.get(this.a);
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                MediaRouteButton.this.h = null;
            }
            MediaRouteButton.this.setRemoteIndicatorDrawableInternal(drawable);
        }
    }

    public MediaRouteButton(Context context) {
        this(context, null);
    }

    private void a() {
        if (this.j > 0) {
            c cVar = this.h;
            if (cVar != null) {
                cVar.cancel(false);
            }
            c cVar2 = new c(this.j, getContext());
            this.h = cVar2;
            this.j = 0;
            cVar2.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    private boolean e(int i) {
        o fragmentManager = getFragmentManager();
        if (fragmentManager == null) {
            throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
        }
        if (this.a.n().w()) {
            if (fragmentManager.h0("android.support.v7.mediarouter:MediaRouteChooserDialogFragment") != null) {
                return false;
            }
            androidx.mediarouter.app.b bVarOnCreateChooserDialogFragment = this.d.onCreateChooserDialogFragment();
            bVarOnCreateChooserDialogFragment.setRouteSelector(this.c);
            if (i == 2) {
                bVarOnCreateChooserDialogFragment.setUseDynamicGroup(true);
            }
            y yVarM = fragmentManager.m();
            yVarM.e(bVarOnCreateChooserDialogFragment, "android.support.v7.mediarouter:MediaRouteChooserDialogFragment");
            yVarM.i();
        } else {
            if (fragmentManager.h0("android.support.v7.mediarouter:MediaRouteControllerDialogFragment") != null) {
                return false;
            }
            d dVarOnCreateControllerDialogFragment = this.d.onCreateControllerDialogFragment();
            dVarOnCreateControllerDialogFragment.setRouteSelector(this.c);
            if (i == 2) {
                dVarOnCreateControllerDialogFragment.setUseDynamicGroup(true);
            }
            y yVarM2 = fragmentManager.m();
            yVarM2.e(dVarOnCreateControllerDialogFragment, "android.support.v7.mediarouter:MediaRouteControllerDialogFragment");
            yVarM2.i();
        }
        return true;
    }

    private boolean f() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 31) {
            boolean zH = h();
            return !zH ? g() : zH;
        }
        if (i == 30) {
            return g();
        }
        return false;
    }

    private boolean g() {
        ApplicationInfo applicationInfo;
        Context context = getContext();
        Intent intentPutExtra = new Intent().setAction("com.android.settings.panel.action.MEDIA_OUTPUT").putExtra("com.android.settings.panel.extra.PACKAGE_NAME", context.getPackageName()).putExtra("key_media_session_token", this.a.k());
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intentPutExtra, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && (applicationInfo = activityInfo.applicationInfo) != null && (applicationInfo.flags & 129) != 0) {
                context.startActivity(intentPutExtra);
                return true;
            }
        }
        return false;
    }

    private Activity getActivity() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    private o getFragmentManager() {
        Object activity = getActivity();
        if (activity instanceof androidx.fragment.app.e) {
            return ((androidx.fragment.app.e) activity).getSupportFragmentManager();
        }
        return null;
    }

    private boolean h() {
        ApplicationInfo applicationInfo;
        Context context = getContext();
        Intent intentPutExtra = new Intent().setAction("com.android.systemui.action.LAUNCH_MEDIA_OUTPUT_DIALOG").setPackage("com.android.systemui").putExtra("package_name", context.getPackageName()).putExtra("key_media_session_token", this.a.k());
        Iterator<ResolveInfo> it = context.getPackageManager().queryBroadcastReceivers(intentPutExtra, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && (applicationInfo = activityInfo.applicationInfo) != null && (applicationInfo.flags & 129) != 0) {
                context.sendBroadcast(intentPutExtra);
                return true;
            }
        }
        return false;
    }

    private void i() {
        int i;
        int i2 = this.l;
        if (i2 != 1) {
            i = i2 != 2 ? R$string.mr_cast_button_disconnected : R$string.mr_cast_button_connected;
        } else {
            i = R$string.mr_cast_button_connecting;
        }
        String string = getContext().getString(i);
        setContentDescription(string);
        if (!this.q || TextUtils.isEmpty(string)) {
            string = null;
        }
        u2.a(this, string);
    }

    void b() {
        k0.h hVarN = this.a.n();
        boolean z = true;
        boolean z2 = !hVarN.w();
        int iC = z2 ? hVarN.c() : 0;
        if (this.l != iC) {
            this.l = iC;
            i();
            refreshDrawableState();
        }
        if (iC == 1) {
            a();
        }
        if (this.e) {
            if (!this.p && !z2 && !this.a.q(this.c, 1)) {
                z = false;
            }
            setEnabled(z);
        }
    }

    void c() {
        super.setVisibility((this.f != 0 || this.p || r.a()) ? this.f : 4);
        Drawable drawable = this.i;
        if (drawable != null) {
            drawable.setVisible(getVisibility() == 0, false);
        }
    }

    public boolean d() {
        if (!this.e) {
            return false;
        }
        c1 c1VarL = this.a.l();
        if (c1VarL == null) {
            return e(1);
        }
        if (c1VarL.d() && k0.p() && f()) {
            return true;
        }
        return e(c1VarL.a());
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.i != null) {
            this.i.setState(getDrawableState());
            if (this.i.getCurrent() instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) this.i.getCurrent();
                int i = this.l;
                if (i == 1 || this.k != i) {
                    if (!animationDrawable.isRunning()) {
                        animationDrawable.start();
                    }
                } else if (i == 2 && !animationDrawable.isRunning()) {
                    animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
                }
            }
            invalidate();
        }
        this.k = this.l;
    }

    public e getDialogFactory() {
        return this.d;
    }

    public j0 getRouteSelector() {
        return this.c;
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.i;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        this.e = true;
        if (!this.c.f()) {
            this.a.a(this.c, this.b);
        }
        b();
        r.b(this);
    }

    @Override // android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.a == null || this.g) {
            return iArrOnCreateDrawableState;
        }
        int i2 = this.l;
        if (i2 == 1) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, u);
        } else if (i2 == 2) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, t);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        if (!isInEditMode()) {
            this.e = false;
            if (!this.c.f()) {
                this.a.s(this.b);
            }
            r.c(this);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.i != null) {
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            int intrinsicWidth = this.i.getIntrinsicWidth();
            int intrinsicHeight = this.i.getIntrinsicHeight();
            int i = paddingLeft + (((width - paddingLeft) - intrinsicWidth) / 2);
            int i2 = paddingTop + (((height - paddingTop) - intrinsicHeight) / 2);
            this.i.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
            this.i.draw(canvas);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i3 = this.n;
        Drawable drawable = this.i;
        int iMax = Math.max(i3, drawable != null ? drawable.getIntrinsicWidth() + getPaddingLeft() + getPaddingRight() : 0);
        int i4 = this.o;
        Drawable drawable2 = this.i;
        int iMax2 = Math.max(i4, drawable2 != null ? drawable2.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom() : 0);
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(size, iMax);
        } else if (mode != 1073741824) {
            size = iMax;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(size2, iMax2);
        } else if (mode2 != 1073741824) {
            size2 = iMax2;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    public boolean performClick() {
        boolean zPerformClick = super.performClick();
        if (!zPerformClick) {
            playSoundEffect(0);
        }
        a();
        return d() || zPerformClick;
    }

    public void setAlwaysVisible(boolean z) {
        if (z != this.p) {
            this.p = z;
            c();
            b();
        }
    }

    void setCheatSheetEnabled(boolean z) {
        if (z != this.q) {
            this.q = z;
            i();
        }
    }

    public void setDialogFactory(e eVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("factory must not be null");
        }
        this.d = eVar;
    }

    public void setRemoteIndicatorDrawable(Drawable drawable) {
        this.j = 0;
        setRemoteIndicatorDrawableInternal(drawable);
    }

    void setRemoteIndicatorDrawableInternal(Drawable drawable) {
        c cVar = this.h;
        if (cVar != null) {
            cVar.cancel(false);
        }
        Drawable drawable2 = this.i;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.i);
        }
        if (drawable != null) {
            if (this.m != null) {
                drawable = q.h.r(drawable.mutate());
                q.h.o(drawable, this.m);
            }
            drawable.setCallback(this);
            drawable.setState(getDrawableState());
            drawable.setVisible(getVisibility() == 0, false);
        }
        this.i = drawable;
        refreshDrawableState();
    }

    public void setRouteSelector(j0 j0Var) {
        if (j0Var == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.c.equals(j0Var)) {
            return;
        }
        if (this.e) {
            if (!this.c.f()) {
                this.a.s(this.b);
            }
            if (!j0Var.f()) {
                this.a.a(j0Var, this.b);
            }
        }
        this.c = j0Var;
        b();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        this.f = i;
        c();
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.i;
    }

    public MediaRouteButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.mediaRouteButtonStyle);
    }

    public MediaRouteButton(Context context, AttributeSet attributeSet, int i) {
        Drawable.ConstantState constantState;
        super(i.a(context), attributeSet, i);
        this.c = j0.c;
        this.d = e.getDefault();
        this.f = 0;
        Context context2 = getContext();
        int[] iArr = R$styleable.a;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, 0);
        androidx.core.view.c1.i0(this, context2, iArr, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        if (isInEditMode()) {
            this.a = null;
            this.b = null;
            this.i = d.b.d(context2, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0));
            return;
        }
        k0 k0VarJ = k0.j(context2);
        this.a = k0VarJ;
        this.b = new b();
        k0.h hVarN = k0VarJ.n();
        int iC = hVarN.w() ^ true ? hVarN.c() : 0;
        this.l = iC;
        this.k = iC;
        if (r == null) {
            r = new a(context2.getApplicationContext());
        }
        this.m = typedArrayObtainStyledAttributes.getColorStateList(R$styleable.MediaRouteButton_mediaRouteButtonTint);
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MediaRouteButton_android_minWidth, 0);
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MediaRouteButton_android_minHeight, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0);
        this.j = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MediaRouteButton_externalRouteEnabledDrawable, 0);
        typedArrayObtainStyledAttributes.recycle();
        int i2 = this.j;
        if (i2 != 0 && (constantState = (Drawable.ConstantState) f28s.get(i2)) != null) {
            setRemoteIndicatorDrawable(constantState.newDrawable());
        }
        if (this.i == null) {
            if (resourceId != 0) {
                Drawable.ConstantState constantState2 = (Drawable.ConstantState) f28s.get(resourceId);
                if (constantState2 != null) {
                    setRemoteIndicatorDrawableInternal(constantState2.newDrawable());
                } else {
                    c cVar = new c(resourceId, getContext());
                    this.h = cVar;
                    cVar.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
                }
            } else {
                a();
            }
        }
        i();
        setClickable(true);
    }
}
