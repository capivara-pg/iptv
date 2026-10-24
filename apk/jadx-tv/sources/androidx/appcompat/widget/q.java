package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class q extends ImageView implements androidx.core.view.g0, androidx.core.widget.h0 {
    private final f mBackgroundTintHelper;
    private final p mImageHelper;

    public q(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.b();
        }
        p pVar = this.mImageHelper;
        if (pVar != null) {
            pVar.b();
        }
    }

    @Override // androidx.core.view.g0
    public ColorStateList getSupportBackgroundTintList() {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            return fVar.c();
        }
        return null;
    }

    @Override // androidx.core.view.g0
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            return fVar.d();
        }
        return null;
    }

    @Override // androidx.core.widget.h0
    public ColorStateList getSupportImageTintList() {
        p pVar = this.mImageHelper;
        if (pVar != null) {
            return pVar.c();
        }
        return null;
    }

    @Override // androidx.core.widget.h0
    public PorterDuff.Mode getSupportImageTintMode() {
        p pVar = this.mImageHelper;
        if (pVar != null) {
            return pVar.d();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.mImageHelper.e() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.g(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        p pVar = this.mImageHelper;
        if (pVar != null) {
            pVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        p pVar = this.mImageHelper;
        if (pVar != null) {
            pVar.b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        p pVar = this.mImageHelper;
        if (pVar != null) {
            pVar.g(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        p pVar = this.mImageHelper;
        if (pVar != null) {
            pVar.b();
        }
    }

    @Override // androidx.core.view.g0
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.i(colorStateList);
        }
    }

    @Override // androidx.core.view.g0
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f fVar = this.mBackgroundTintHelper;
        if (fVar != null) {
            fVar.j(mode);
        }
    }

    @Override // androidx.core.widget.h0
    public void setSupportImageTintList(ColorStateList colorStateList) {
        p pVar = this.mImageHelper;
        if (pVar != null) {
            pVar.h(colorStateList);
        }
    }

    @Override // androidx.core.widget.h0
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        p pVar = this.mImageHelper;
        if (pVar != null) {
            pVar.i(mode);
        }
    }

    public q(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public q(Context context, AttributeSet attributeSet, int i) {
        super(o2.b(context), attributeSet, i);
        f fVar = new f(this);
        this.mBackgroundTintHelper = fVar;
        fVar.e(attributeSet, i);
        p pVar = new p(this);
        this.mImageHelper = pVar;
        pVar.f(attributeSet, i);
    }
}
