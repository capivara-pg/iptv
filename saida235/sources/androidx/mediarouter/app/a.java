package androidx.mediarouter.app;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.n;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$layout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import o0.k0;
import o0.l0;

/* JADX INFO: loaded from: classes.dex */
public class a extends n {
    static final int MSG_UPDATE_ROUTES = 1;
    static final String TAG = "MediaRouteChooserDialog";
    private static final long UPDATE_ROUTES_DELAY_MS = 300;
    private c mAdapter;
    private boolean mAttachedToWindow;
    private final b mCallback;
    private final Handler mHandler;
    private long mLastUpdateTime;
    private ListView mListView;
    private final l0 mRouter;
    private ArrayList<l0.h> mRoutes;
    private k0 mSelector;
    private TextView mTitleView;

    /* JADX INFO: renamed from: androidx.mediarouter.app.a$a, reason: collision with other inner class name */
    class HandlerC0045a extends Handler {
        HandlerC0045a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            a.this.updateRoutes((List) message.obj);
        }
    }

    private final class b extends l0.a {
        b() {
        }

        public void onRouteAdded(l0 l0Var, l0.h hVar) {
            a.this.refreshRoutes();
        }

        public void onRouteChanged(l0 l0Var, l0.h hVar) {
            a.this.refreshRoutes();
        }

        public void onRouteRemoved(l0 l0Var, l0.h hVar) {
            a.this.refreshRoutes();
        }

        public void onRouteSelected(l0 l0Var, l0.h hVar) {
            a.this.dismiss();
        }
    }

    private static final class c extends ArrayAdapter implements AdapterView.OnItemClickListener {
        private final LayoutInflater a;
        private final Drawable b;
        private final Drawable c;
        private final Drawable d;
        private final Drawable e;

        public c(Context context, List list) {
            super(context, 0, list);
            this.a = LayoutInflater.from(context);
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{R$attr.mediaRouteDefaultIconDrawable, R$attr.mediaRouteTvIconDrawable, R$attr.mediaRouteSpeakerIconDrawable, R$attr.mediaRouteSpeakerGroupIconDrawable});
            this.b = f.b.d(context, typedArrayObtainStyledAttributes.getResourceId(0, 0));
            this.c = f.b.d(context, typedArrayObtainStyledAttributes.getResourceId(1, 0));
            this.d = f.b.d(context, typedArrayObtainStyledAttributes.getResourceId(2, 0));
            this.e = f.b.d(context, typedArrayObtainStyledAttributes.getResourceId(3, 0));
            typedArrayObtainStyledAttributes.recycle();
        }

        private Drawable a(l0.h hVar) {
            int iF = hVar.f();
            if (iF == 1) {
                return this.c;
            }
            if (iF != 2) {
                return hVar.y() ? this.e : this.b;
            }
            return this.d;
        }

        private Drawable b(l0.h hVar) {
            Uri uriJ = hVar.j();
            if (uriJ != null) {
                try {
                    Drawable drawableCreateFromStream = Drawable.createFromStream(getContext().getContentResolver().openInputStream(uriJ), null);
                    if (drawableCreateFromStream != null) {
                        return drawableCreateFromStream;
                    }
                } catch (IOException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to load ");
                    sb.append(uriJ);
                }
            }
            return a(hVar);
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.a.inflate(R$layout.mr_chooser_list_item, viewGroup, false);
            }
            l0.h hVar = (l0.h) getItem(i);
            TextView textView = (TextView) view.findViewById(R$id.mr_chooser_route_name);
            TextView textView2 = (TextView) view.findViewById(R$id.mr_chooser_route_desc);
            textView.setText(hVar.m());
            String strD = hVar.d();
            boolean z = true;
            if (hVar.c() != 2 && hVar.c() != 1) {
                z = false;
            }
            if (!z || TextUtils.isEmpty(strD)) {
                textView.setGravity(16);
                textView2.setVisibility(8);
                textView2.setText("");
            } else {
                textView.setGravity(80);
                textView2.setVisibility(0);
                textView2.setText(strD);
            }
            view.setEnabled(hVar.x());
            ImageView imageView = (ImageView) view.findViewById(R$id.mr_chooser_route_icon);
            if (imageView != null) {
                imageView.setImageDrawable(b(hVar));
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return ((l0.h) getItem(i)).x();
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i, long j) {
            l0.h hVar = (l0.h) getItem(i);
            if (hVar.x()) {
                ImageView imageView = (ImageView) view.findViewById(R$id.mr_chooser_route_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.mr_chooser_route_progress_bar);
                if (imageView != null && progressBar != null) {
                    imageView.setVisibility(8);
                    progressBar.setVisibility(0);
                }
                hVar.I();
            }
        }
    }

    static final class d implements Comparator {
        public static final d a = new d();

        d() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(l0.h hVar, l0.h hVar2) {
            return hVar.m().compareToIgnoreCase(hVar2.m());
        }
    }

    public a(Context context) {
        this(context, 0);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.b(this.mSelector, this.mCallback, 1);
        refreshRoutes();
    }

    @Override // androidx.appcompat.app.n, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_chooser_dialog);
        this.mRoutes = new ArrayList<>();
        this.mAdapter = new c(getContext(), this.mRoutes);
        ListView listView = (ListView) findViewById(R$id.mr_chooser_list);
        this.mListView = listView;
        listView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnItemClickListener(this.mAdapter);
        this.mListView.setEmptyView(findViewById(R.id.empty));
        this.mTitleView = (TextView) findViewById(R$id.mr_chooser_title);
        updateLayout();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.mAttachedToWindow = false;
        this.mRouter.s(this.mCallback);
        this.mHandler.removeMessages(1);
        super.onDetachedFromWindow();
    }

    public boolean onFilterRoute(l0.h hVar) {
        return !hVar.w() && hVar.x() && hVar.E(this.mSelector);
    }

    public void onFilterRoutes(List<l0.h> list) {
        int size = list.size();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return;
            }
            if (!onFilterRoute(list.get(i))) {
                list.remove(i);
            }
            size = i;
        }
    }

    public void refreshRoutes() {
        if (this.mAttachedToWindow) {
            ArrayList arrayList = new ArrayList(this.mRouter.m());
            onFilterRoutes(arrayList);
            Collections.sort(arrayList, d.a);
            if (SystemClock.uptimeMillis() - this.mLastUpdateTime >= UPDATE_ROUTES_DELAY_MS) {
                updateRoutes(arrayList);
                return;
            }
            this.mHandler.removeMessages(1);
            Handler handler = this.mHandler;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.mLastUpdateTime + UPDATE_ROUTES_DELAY_MS);
        }
    }

    public void setRouteSelector(k0 k0Var) {
        if (k0Var == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.mSelector.equals(k0Var)) {
            return;
        }
        this.mSelector = k0Var;
        if (this.mAttachedToWindow) {
            this.mRouter.s(this.mCallback);
            this.mRouter.b(k0Var, this.mCallback, 1);
        }
        refreshRoutes();
    }

    @Override // androidx.appcompat.app.n, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        this.mTitleView.setText(charSequence);
    }

    void updateLayout() {
        getWindow().setLayout(f.b(getContext()), -2);
    }

    void updateRoutes(List<l0.h> list) {
        this.mLastUpdateTime = SystemClock.uptimeMillis();
        this.mRoutes.clear();
        this.mRoutes.addAll(list);
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a(Context context, int i) {
        Context contextB = i.b(context, i, false);
        super(contextB, i.c(contextB));
        this.mSelector = k0.c;
        this.mHandler = new HandlerC0045a();
        this.mRouter = l0.j(getContext());
        this.mCallback = new b();
    }

    @Override // androidx.appcompat.app.n, android.app.Dialog
    public void setTitle(int i) {
        this.mTitleView.setText(i);
    }
}
