package jjr.com.playandroids.playandroid_frgment;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.persenter.OnePresenter;
import jjr.com.playandroids.view.OneView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class OneFragmnet extends BaseFragment<OneView,OnePresenter<OneView>> implements OneView {
    @Override
    public void showError(String error) {

    }

    @Override
    protected OnePresenter<OneView> createPresenter() {
        return new OnePresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.one_fragment;
    }

    @Override
    protected void initData() {
       // presenter.getDataOneP();
    }


    @Override
    public void showDataOne(Object o, String onlyOne) {

    }
}
