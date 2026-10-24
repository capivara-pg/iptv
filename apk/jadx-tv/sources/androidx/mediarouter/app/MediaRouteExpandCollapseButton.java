package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.n;
import androidx.mediarouter.R$drawable;
import androidx.mediarouter.R$string;

/* JADX INFO: loaded from: classes.dex */
class MediaRouteExpandCollapseButton extends n {
    final AnimationDrawable a;
    final AnimationDrawable b;
    final String c;
    final String d;
    boolean e;
    View.OnClickListener f;

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
            boolean z = !mediaRouteExpandCollapseButton.e;
            mediaRouteExpandCollapseButton.e = z;
            if (z) {
                mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.a);
                MediaRouteExpandCollapseButton.this.a.start();
                MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton2 = MediaRouteExpandCollapseButton.this;
                mediaRouteExpandCollapseButton2.setContentDescription(mediaRouteExpandCollapseButton2.d);
            } else {
                mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.b);
                MediaRouteExpandCollapseButton.this.b.start();
                MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton3 = MediaRouteExpandCollapseButton.this;
                mediaRouteExpandCollapseButton3.setContentDescription(mediaRouteExpandCollapseButton3.c);
            }
            View.OnClickListener onClickListener = MediaRouteExpandCollapseButton.this.f;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f = onClickListener;
    }

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AnimationDrawable animationDrawable = (AnimationDrawable) androidx.core.content.a.getDrawable(context, R$drawable.mr_group_expand);
        this.a = animationDrawable;
        AnimationDrawable animationDrawable2 = (AnimationDrawable) androidx.core.content.a.getDrawable(context, R$drawable.mr_group_collapse);
        this.b = animationDrawable2;
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(i.f(context, i), PorterDuff.Mode.SRC_IN);
        animationDrawable.setColorFilter(porterDuffColorFilter);
        animationDrawable2.setColorFilter(porterDuffColorFilter);
        String string = context.getString(R$string.mr_controller_expand_group);
        this.c = string;
        this.d = context.getString(R$string.mr_controller_collapse_group);
        setImageDrawable(animationDrawable.getFrame(0));
        setContentDescription(string);
        super.setOnClickListener(new a());
    }
}
