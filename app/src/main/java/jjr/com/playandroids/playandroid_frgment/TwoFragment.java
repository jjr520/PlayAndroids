package jjr.com.playandroids.playandroid_frgment;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.persenter.TwoPresenter;
import jjr.com.playandroids.view.TwoView;

public class TwoFragment extends BaseFragment<TwoView,TwoPresenter<TwoView>> implements TwoView{
    @Override
    protected TwoPresenter<TwoView> createPresenter() {
        return new TwoPresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.two_fragment;
    }

    @Override
    protected void initData() {
        //presenter.getDataTwoP();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showDataTwo(Object o, String onlyOne) {

    }
}
