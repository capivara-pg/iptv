package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
abstract class t {
    private static final boolean a;
    private static final boolean b;
    private static final boolean c;

    static {
        int i = Build.VERSION.SDK_INT;
        a = true;
        b = true;
        c = i >= 28;
    }

    static View a(ViewGroup viewGroup, View view, View view2) {
        Matrix matrix = new Matrix();
        matrix.setTranslate(-view2.getScrollX(), -view2.getScrollY());
        c0.i(view, matrix);
        c0.j(viewGroup, matrix);
        RectF rectF = new RectF(0.0f, 0.0f, view.getWidth(), view.getHeight());
        matrix.mapRect(rectF);
        int iRound = Math.round(rectF.left);
        int iRound2 = Math.round(rectF.top);
        int iRound3 = Math.round(rectF.right);
        int iRound4 = Math.round(rectF.bottom);
        ImageView imageView = new ImageView(view.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Bitmap bitmapB = b(view, matrix, rectF, viewGroup);
        if (bitmapB != null) {
            imageView.setImageBitmap(bitmapB);
        }
        imageView.measure(View.MeasureSpec.makeMeasureSpec(iRound3 - iRound, 1073741824), View.MeasureSpec.makeMeasureSpec(iRound4 - iRound2, 1073741824));
        imageView.layout(iRound, iRound2, iRound3, iRound4);
        return imageView;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0031  */
    /* JADX WARN: Code duplicated, block: B:22:0x0071  */
    /* JADX WARN: Code duplicated, block: B:23:0x0088  */
    private static Bitmap b(View view, Matrix matrix, RectF rectF, ViewGroup viewGroup) {
        boolean z;
        boolean zIsAttachedToWindow;
        boolean z2;
        Bitmap bitmapCreateBitmap;
        ViewGroup viewGroup2;
        int iIndexOfChild;
        int iRound;
        int iRound2;
        int iRound3;
        int iRound4;
        if (a) {
            z = !view.isAttachedToWindow();
            if (viewGroup != null) {
                zIsAttachedToWindow = viewGroup.isAttachedToWindow();
            }
            z2 = b;
            bitmapCreateBitmap = null;
            if (z2 || !z) {
                viewGroup2 = null;
                iIndexOfChild = 0;
            } else {
                if (!zIsAttachedToWindow) {
                    return null;
                }
                viewGroup2 = (ViewGroup) view.getParent();
                iIndexOfChild = viewGroup2.indexOfChild(view);
                viewGroup.getOverlay().add(view);
            }
            iRound = Math.round(rectF.width());
            iRound2 = Math.round(rectF.height());
            if (iRound > 0 && iRound2 > 0) {
                float fMin = Math.min(1.0f, 1048576.0f / (iRound * iRound2));
                iRound3 = Math.round(iRound * fMin);
                iRound4 = Math.round(iRound2 * fMin);
                matrix.postTranslate(-rectF.left, -rectF.top);
                matrix.postScale(fMin, fMin);
                if (c) {
                    Picture picture = new Picture();
                    Canvas canvasBeginRecording = picture.beginRecording(iRound3, iRound4);
                    canvasBeginRecording.concat(matrix);
                    view.draw(canvasBeginRecording);
                    picture.endRecording();
                    bitmapCreateBitmap = Bitmap.createBitmap(picture);
                } else {
                    bitmapCreateBitmap = Bitmap.createBitmap(iRound3, iRound4, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmapCreateBitmap);
                    canvas.concat(matrix);
                    view.draw(canvas);
                }
            }
            if (z2 && z) {
                viewGroup.getOverlay().remove(view);
                viewGroup2.addView(view, iIndexOfChild);
            }
            return bitmapCreateBitmap;
        }
        z = false;
        zIsAttachedToWindow = false;
        z2 = b;
        bitmapCreateBitmap = null;
        if (z2) {
            viewGroup2 = null;
            iIndexOfChild = 0;
        } else {
            viewGroup2 = null;
            iIndexOfChild = 0;
        }
        iRound = Math.round(rectF.width());
        iRound2 = Math.round(rectF.height());
        if (iRound > 0) {
            float fMin2 = Math.min(1.0f, 1048576.0f / (iRound * iRound2));
            iRound3 = Math.round(iRound * fMin2);
            iRound4 = Math.round(iRound2 * fMin2);
            matrix.postTranslate(-rectF.left, -rectF.top);
            matrix.postScale(fMin2, fMin2);
            if (c) {
                Picture picture2 = new Picture();
                Canvas canvasBeginRecording2 = picture2.beginRecording(iRound3, iRound4);
                canvasBeginRecording2.concat(matrix);
                view.draw(canvasBeginRecording2);
                picture2.endRecording();
                bitmapCreateBitmap = Bitmap.createBitmap(picture2);
            } else {
                bitmapCreateBitmap = Bitmap.createBitmap(iRound3, iRound4, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(bitmapCreateBitmap);
                canvas2.concat(matrix);
                view.draw(canvas2);
            }
        }
        if (z2) {
            viewGroup.getOverlay().remove(view);
            viewGroup2.addView(view, iIndexOfChild);
        }
        return bitmapCreateBitmap;
    }

    static Animator c(Animator animator, Animator animator2) {
        if (animator == null) {
            return animator2;
        }
        if (animator2 == null) {
            return animator;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator, animator2);
        return animatorSet;
    }
}
