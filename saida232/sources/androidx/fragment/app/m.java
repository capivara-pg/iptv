package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R$styleable;

/* JADX INFO: loaded from: classes.dex */
class m implements LayoutInflater.Factory2 {
    final o a;

    class a implements View.OnAttachStateChangeListener {
        final /* synthetic */ v a;

        a(v vVar) {
            this.a = vVar;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            Fragment fragmentK = this.a.k();
            this.a.m();
            j0.n((ViewGroup) fragmentK.mView.getParent(), m.this.a).j();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    m(o oVar) {
        this.a = oVar;
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        v vVarV;
        if (i.class.getName().equals(str)) {
            return new i(context, attributeSet, this.a);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.d);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(R$styleable.Fragment_android_name);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.Fragment_android_id, -1);
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.Fragment_android_tag);
        typedArrayObtainStyledAttributes.recycle();
        if (attributeValue == null || !k.b(context.getClassLoader(), attributeValue)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        Fragment fragmentG0 = resourceId != -1 ? this.a.g0(resourceId) : null;
        if (fragmentG0 == null && string != null) {
            fragmentG0 = this.a.h0(string);
        }
        if (fragmentG0 == null && id != -1) {
            fragmentG0 = this.a.g0(id);
        }
        if (fragmentG0 == null) {
            fragmentG0 = this.a.q0().a(context.getClassLoader(), attributeValue);
            fragmentG0.mFromLayout = true;
            fragmentG0.mFragmentId = resourceId != 0 ? resourceId : id;
            fragmentG0.mContainerId = id;
            fragmentG0.mTag = string;
            fragmentG0.mInLayout = true;
            o oVar = this.a;
            fragmentG0.mFragmentManager = oVar;
            fragmentG0.mHost = oVar.t0();
            fragmentG0.onInflate(this.a.t0().f(), attributeSet, fragmentG0.mSavedFragmentState);
            vVarV = this.a.g(fragmentG0);
            if (o.F0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Fragment ");
                sb.append(fragmentG0);
                sb.append(" has been inflated via the <fragment> tag: id=0x");
                sb.append(Integer.toHexString(resourceId));
            }
        } else {
            if (fragmentG0.mInLayout) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
            }
            fragmentG0.mInLayout = true;
            o oVar2 = this.a;
            fragmentG0.mFragmentManager = oVar2;
            fragmentG0.mHost = oVar2.t0();
            fragmentG0.onInflate(this.a.t0().f(), attributeSet, fragmentG0.mSavedFragmentState);
            vVarV = this.a.v(fragmentG0);
            if (o.F0(2)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Retained Fragment ");
                sb2.append(fragmentG0);
                sb2.append(" has been re-attached via the <fragment> tag: id=0x");
                sb2.append(Integer.toHexString(resourceId));
            }
        }
        fragmentG0.mContainer = (ViewGroup) view;
        vVarV.m();
        vVarV.j();
        View view2 = fragmentG0.mView;
        if (view2 == null) {
            throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
        }
        if (resourceId != 0) {
            view2.setId(resourceId);
        }
        if (fragmentG0.mView.getTag() == null) {
            fragmentG0.mView.setTag(string);
        }
        fragmentG0.mView.addOnAttachStateChangeListener(new a(vVarV));
        return fragmentG0.mView;
    }
}
