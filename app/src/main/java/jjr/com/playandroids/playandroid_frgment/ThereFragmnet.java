package jjr.com.playandroids.playandroid_frgment;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.persenter.TherePresenter;
import jjr.com.playandroids.view.ThereView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class ThereFragmnet extends BaseFragment<ThereView,TherePresenter<ThereView>> implements ThereView {
    @Override
    public void showError(String error) {

    }

    @Override
    protected TherePresenter<ThereView> createPresenter() {
        return null;
    }

    @Override
    public int createLayoutId() {
        return R.layout.there_fragment;
    }

    @Override
    protected void initData() {
        //presenter.getDataThereP();
    }

    @Override
    public void showDataThere(Object o, String onlyOne) {

    }
}
