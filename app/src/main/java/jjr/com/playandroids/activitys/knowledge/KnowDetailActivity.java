package jjr.com.playandroids.activitys.knowledge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.activity.BaseActivity;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.persenter.TwoPresenter;
import jjr.com.playandroids.view.TwoView;

public class KnowDetailActivity extends BaseActivity<TwoView, TwoPresenter<TwoView>> implements TwoView {


    @Override
    public int createLayoutId() {
        return R.layout.activity_know_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showDataTwo(Object o, String onlyTwo) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected TwoPresenter<TwoView> createPresenter() {
        return null;
    }
}
