package jjr.com.playandroids.playandroid_frgment;

import android.util.Log;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.fivelistbean.TreeListBean;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.persenter.FivePresenter;
import jjr.com.playandroids.view.FiveView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class FiveFragmnet extends BaseFragment<FiveView,FivePresenter<FiveView>> implements FiveView {
    @Override
    public void showError(String error) {

    }

    @Override
    protected FivePresenter<FiveView> createPresenter() {
        return new FivePresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.five_fragment;
    }

    @Override
    protected void initData() {
        presenter.getDataFiveP(OnlyFive.FIVE,null);
    }

    @Override
    public void showDataFive(Object o, String onlyOne) {
        TreeListBean treeListBean = (TreeListBean) o;
        Log.e("111111",treeListBean.getData().get(0).getName());
    }
}
