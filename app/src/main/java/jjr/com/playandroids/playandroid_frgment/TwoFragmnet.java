package jjr.com.playandroids.playandroid_frgment;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.persenter.MyPersenter;
import jjr.com.playandroids.view.MyView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class TwoFragmnet extends BaseFragment<MyView,MyPersenter<MyView>> implements MyView {
    @Override
    public void showError(String error) {

    }

    @Override
    public void showData(Object object, OnlyOne onlyOne) {

    }

    @Override
    protected MyPersenter<MyView> createPresenter() {
        return null;
    }

    @Override
    public int createLayoutId() {
        return R.layout.two_fragment;
    }

    @Override
    protected void initData() {

    }
}
