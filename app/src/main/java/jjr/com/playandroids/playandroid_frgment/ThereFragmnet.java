package jjr.com.playandroids.playandroid_frgment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.wechat.WCVpAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.beans.WeChatTabBean;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.persenter.MyPersenter;
import jjr.com.playandroids.view.MyView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class ThereFragmnet extends BaseFragment<MyView<WeChatTabBean>, MyPersenter<MyView<WeChatTabBean>>> implements MyView<WeChatTabBean> {


    @BindView(R.id.tab_wechat)
    TabLayout mTabWechat;
    @BindView(R.id.vp_wechat)
    ViewPager mVpWechat;
    Unbinder unbinder;

    @Override
    public void showError(String error) {

    }


    @Override
    public int createLayoutId() {
        return R.layout.there_fragment;
    }

    @Override
    protected void initData() {
        //解析数据

        //Fragment复用
        WCVpAdapter wcVpAdapter = new WCVpAdapter(getChildFragmentManager());
        mVpWechat.setAdapter(wcVpAdapter);
        //关联
        mTabWechat.setupWithViewPager(mVpWechat);
    }

    @Override
    public void showData(WeChatTabBean weChatTabBean, OnlyOne onlyOne) {

    }

    @Override
    protected MyPersenter<MyView<WeChatTabBean>> createPresenter() {
        return null;
    }
}
