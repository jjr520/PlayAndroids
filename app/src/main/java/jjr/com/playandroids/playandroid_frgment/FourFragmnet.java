package jjr.com.playandroids.playandroid_frgment;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.entityclass.NaviListBean;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.persenter.MyPersenter;
import jjr.com.playandroids.view.MyView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class FourFragmnet extends BaseFragment<MyView,MyPersenter<MyView>> implements MyView {
    @Override
    public void showError(String error) {

    }

    @Override
    public void showData(Object object, String onlyOne) {
        switch (onlyOne){
            case OnlyOne.NAVI:
                NaviListBean naviListBean = (NaviListBean) object;
                Log.e("导航数据", naviListBean.getData().toString());
                break;
        }
    }

    @Override
    protected MyPersenter<MyView> createPresenter() {
        return new MyPersenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.four_fragment;
    }

    @Override
    protected void initData() {
        presenter.getDataP(OnlyOne.NAVI,null);
    }
}
