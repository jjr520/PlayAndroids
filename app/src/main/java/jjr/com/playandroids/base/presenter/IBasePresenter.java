package jjr.com.playandroids.base.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by jjr on 2019/2/27.
 */

public class IBasePresenter<V> implements BasePresenter<V> {

    public V view;
    private WeakReference<V> mTWeakReference;

    @Override
    public void attchView(V v) {
        mTWeakReference = new WeakReference<V>(v);
        view = mTWeakReference.get();
    }

    @Override
    public void datechView() {
        if (mTWeakReference != null && mTWeakReference.get() != null) {
            mTWeakReference.clear();
            mTWeakReference = null;
        }
    }
}
