package jjr.com.playandroids.playandroid_frgment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.fourlistbean.NaviListBean;
import jjr.com.playandroids.only.OnlyFour;
import jjr.com.playandroids.persenter.FourPresenter;
import jjr.com.playandroids.view.FourView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class FourFragmnet extends BaseFragment<FourView, FourPresenter<FourView>> implements FourView {
    List<NaviListBean.DataBean> list = new ArrayList<>();
    List<String> strings = new ArrayList<>();

    Unbinder unbinder;

    @Override
    public void showError(String error) {

    }

    @Override
    public void showDataFour(Object object, String onlyOne) {
        switch (onlyOne) {
            case OnlyFour.NAVI:
                NaviListBean naviListBean = (NaviListBean) object;
                Log.e("导航数据", naviListBean.getData().toString());
                break;
        }
    }

    @Override
    protected FourPresenter<FourView> createPresenter() {
        return new FourPresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.four_fragment;
    }

    @Override
    public void load() {
        super.load();
        presenter.getDataFourP(OnlyFour.NAVI, null);
    }

    @Override
    protected void initData() {


    }
}
