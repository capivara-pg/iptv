package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.f1;
import androidx.appcompat.widget.o2;
import androidx.appcompat.widget.q0;
import androidx.appcompat.widget.r;
import androidx.appcompat.widget.u;
import androidx.appcompat.widget.v;
import androidx.appcompat.widget.x;
import androidx.core.view.c1;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatViewInflater {
    private static final String LOG_TAG = "AppCompatViewInflater";
    private final Object[] mConstructorArgs = new Object[2];
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private static final int[] sOnClickAttrs = {R.attr.onClick};
    private static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new androidx.collection.a();

    private static class a implements View.OnClickListener {
        private final View a;
        private final String b;
        private Method c;
        private Context d;

        public a(View view, String str) {
            this.a = view;
            this.b = str;
        }

        private void a(Context context, String str) {
            String str2;
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.b, View.class)) != null) {
                        this.c = method;
                        this.d = context;
                        return;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
            int id = this.a.getId();
            if (id == -1) {
                str2 = "";
            } else {
                str2 = " with id '" + this.a.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.b + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.a.getClass() + str2);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.c == null) {
                a(this.a.getContext(), this.b);
            }
            try {
                this.c.invoke(this.d, view);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }
    }

    private void a(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if ((context instanceof ContextWrapper) && c1.L(view)) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sOnClickAttrs);
            String string = typedArrayObtainStyledAttributes.getString(0);
            if (string != null) {
                view.setOnClickListener(new a(view, string));
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private View b(Context context, String str, String str2) {
        String str3;
        Map<String, Constructor<? extends View>> map = sConstructorMap;
        Constructor<? extends View> constructor = map.get(str);
        if (constructor == null) {
            if (str2 != null) {
                try {
                    str3 = str2 + str;
                } catch (Exception unused) {
                    return null;
                }
            } else {
                str3 = str;
            }
            constructor = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(sConstructorSignature);
            map.put(str, constructor);
        }
        constructor.setAccessible(true);
        return constructor.newInstance(this.mConstructorArgs);
    }

    private View c(Context context, String str, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            Object[] objArr = this.mConstructorArgs;
            objArr[0] = context;
            objArr[1] = attributeSet;
            if (-1 != str.indexOf(46)) {
                return b(context, str, null);
            }
            int i = 0;
            while (true) {
                String[] strArr = sClassPrefixList;
                if (i >= strArr.length) {
                    return null;
                }
                View viewB = b(context, str, strArr[i]);
                if (viewB != null) {
                    return viewB;
                }
                i++;
            }
        } catch (Exception unused) {
            return null;
        } finally {
            Object[] objArr2 = this.mConstructorArgs;
            objArr2[0] = null;
            objArr2[1] = null;
        }
    }

    private static Context d(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.N, 0, 0);
        int resourceId = z ? typedArrayObtainStyledAttributes.getResourceId(R$styleable.View_android_theme, 0) : 0;
        if (z2 && resourceId == 0) {
            resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.View_theme, 0);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId != 0) {
            return ((context instanceof f.d) && ((f.d) context).c() == resourceId) ? context : new f.d(context, resourceId);
        }
        return context;
    }

    private void e(View view, String str) {
        if (view != null) {
            return;
        }
        throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
    }

    protected androidx.appcompat.widget.e createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.e(context, attributeSet);
    }

    protected androidx.appcompat.widget.g createButton(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.g(context, attributeSet);
    }

    protected androidx.appcompat.widget.h createCheckBox(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.h(context, attributeSet);
    }

    protected androidx.appcompat.widget.i createCheckedTextView(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.i(context, attributeSet);
    }

    protected androidx.appcompat.widget.l createEditText(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.l(context, attributeSet);
    }

    protected androidx.appcompat.widget.n createImageButton(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.n(context, attributeSet);
    }

    protected androidx.appcompat.widget.q createImageView(Context context, AttributeSet attributeSet) {
        return new androidx.appcompat.widget.q(context, attributeSet);
    }

    protected r createMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new r(context, attributeSet);
    }

    protected u createRadioButton(Context context, AttributeSet attributeSet) {
        return new u(context, attributeSet);
    }

    protected v createRatingBar(Context context, AttributeSet attributeSet) {
        return new v(context, attributeSet);
    }

    protected AppCompatSeekBar createSeekBar(Context context, AttributeSet attributeSet) {
        return new AppCompatSeekBar(context, attributeSet);
    }

    protected x createSpinner(Context context, AttributeSet attributeSet) {
        return new x(context, attributeSet);
    }

    protected q0 createTextView(Context context, AttributeSet attributeSet) {
        return new q0(context, attributeSet);
    }

    protected f1 createToggleButton(Context context, AttributeSet attributeSet) {
        return new f1(context, attributeSet);
    }

    protected View createView(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    final View createView(View view, String str, Context context, AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        View viewCreateRatingBar;
        Context context2 = (!z || view == null) ? context : view.getContext();
        if (z2 || z3) {
            context2 = d(context2, attributeSet, z2, z3);
        }
        if (z4) {
            context2 = o2.b(context2);
        }
        str.hashCode();
        switch (str) {
            case "RatingBar":
                viewCreateRatingBar = createRatingBar(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "CheckedTextView":
                viewCreateRatingBar = createCheckedTextView(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "MultiAutoCompleteTextView":
                viewCreateRatingBar = createMultiAutoCompleteTextView(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "TextView":
                viewCreateRatingBar = createTextView(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "ImageButton":
                viewCreateRatingBar = createImageButton(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "SeekBar":
                viewCreateRatingBar = createSeekBar(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "Spinner":
                viewCreateRatingBar = createSpinner(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "RadioButton":
                viewCreateRatingBar = createRadioButton(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "ToggleButton":
                viewCreateRatingBar = createToggleButton(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "ImageView":
                viewCreateRatingBar = createImageView(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "AutoCompleteTextView":
                viewCreateRatingBar = createAutoCompleteTextView(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "CheckBox":
                viewCreateRatingBar = createCheckBox(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "EditText":
                viewCreateRatingBar = createEditText(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            case "Button":
                viewCreateRatingBar = createButton(context2, attributeSet);
                e(viewCreateRatingBar, str);
                break;
            default:
                viewCreateRatingBar = createView(context2, str, attributeSet);
                break;
        }
        if (viewCreateRatingBar == null && context != context2) {
            viewCreateRatingBar = c(context2, str, attributeSet);
        }
        if (viewCreateRatingBar != null) {
            a(viewCreateRatingBar, attributeSet);
        }
        return viewCreateRatingBar;
    }
}
