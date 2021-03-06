package jjr.com.playandroids.activitys.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.wechat.WxSimpleVpAdapter;
import jjr.com.playandroids.base.activity.BaseActivity;
import jjr.com.playandroids.beans.wechat.WeChatHistoryBean;
import jjr.com.playandroids.persenter.TherePresenter;
import jjr.com.playandroids.playandroid_frgment.wechat.WxSimpleFragment;
import jjr.com.playandroids.view.ThereView;

import static jjr.com.playandroids.only.OnlyThere.WCHISTORY;

public class WxShowSimpleActivity extends BaseActivity<ThereView, TherePresenter<ThereView>> implements ThereView {

    @BindView(R.id.tab_wx_show_simple)
    TabLayout mTabWxShowSimple;
    @BindView(R.id.vp_show_wx_simple)
    ViewPager mVpShowWxSimple;
    @BindView(R.id.back_wx_simple)
    ImageView mBackWxSimple;
    @BindView(R.id.toolbar_wx_simple)
    Toolbar mToolbarWxSimple;
    @BindView(R.id.Wx_show_title)
    TextView Mytitle;

    @Override
    protected void initData() {
        mToolbarWxSimple.setTitle("");
        setSupportActionBar(mToolbarWxSimple);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        if (!"".equals(title)){
            Mytitle.setText(title);
        }
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("id", 0);
        ArrayList<String> list = new ArrayList<>();
        list.add(name);
        mTabWxShowSimple.addTab(mTabWxShowSimple.newTab().setText(name));
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(WxSimpleFragment.newInstance(id, name));
        WxSimpleVpAdapter wxSimpleVpAdapter = new WxSimpleVpAdapter(getSupportFragmentManager(), list, fragments);
        mVpShowWxSimple.setAdapter(wxSimpleVpAdapter);
        mTabWxShowSimple.setupWithViewPager(mVpShowWxSimple);
    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_wx_show_simple;
    }

    @Override
    public void showDataThere(Object o, String onlyOne) {
        switch (onlyOne) {
            case WCHISTORY:
                WeChatHistoryBean data = (WeChatHistoryBean) o;
                List<WeChatHistoryBean.DataBean.DatasBean> datas = data.getData().getDatas();
                break;
        }
    }

    @Override
    public void showError(String error) {
        Log.i("gmc", "showSimpleshowError: " + error);
    }

    @Override
    protected TherePresenter<ThereView> createPresenter() {
        return new TherePresenter<>();
    }


    @OnClick(R.id.back_wx_simple)
    public void onViewClicked() {
        finish();
    }

}
