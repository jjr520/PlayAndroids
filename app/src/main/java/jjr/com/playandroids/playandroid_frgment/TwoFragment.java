package jjr.com.playandroids.playandroid_frgment;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.persenter.MyPersenter;
import jjr.com.playandroids.view.MyView;

public class TwoFragment extends BaseFragment<MyView,MyPersenter<MyView>> implements MyView{
    @Override
    protected MyPersenter<MyView> createPresenter() {
        return new MyPersenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.two_fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showData(Object o, String onlyOne) {

    }

    @Override
    public void showError(String error) {

    }
}
