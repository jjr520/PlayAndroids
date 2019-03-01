package jjr.com.playandroids.playandroid_frgment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.wechat.WCVpAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.wechat.WeChatTabBean;
import jjr.com.playandroids.only.OnlyThere;
import jjr.com.playandroids.persenter.TherePresenter;
import jjr.com.playandroids.playandroid_frgment.wechat.AllFragment;
import jjr.com.playandroids.view.ThereView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class ThereFragmnet extends BaseFragment<ThereView<WeChatTabBean>, TherePresenter<ThereView<WeChatTabBean>>> implements ThereView<WeChatTabBean> {
    @BindView(R.id.tab_wechat)
    TabLayout mTabWechat;
    @BindView(R.id.vp_wechat)
    ViewPager mVpWechat;
    Unbinder unbinder;

    @Override
    public void showError(String error) {
        Log.i("gmc", "微信tabshowError: " + error);
    }

    @Override
    protected TherePresenter<ThereView<WeChatTabBean>> createPresenter() {
        return new TherePresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.there_fragment;
    }

    @Override
    protected void initData() {
        presenter.getDataThereP(OnlyThere.WECHATTAB, null);
    }

    @Override
    public void showDataThere(WeChatTabBean weChatTabBean, String onlyOne) {
        Log.i("gmc", "微信tabshowDataThere: " + weChatTabBean);
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < weChatTabBean.getData().size(); i++) {
            fragments.add(AllFragment.newInstance(weChatTabBean.getData().get(i).getId(), weChatTabBean.getData().get(i).getName()));
        }
        WCVpAdapter wcVpAdapter = new WCVpAdapter(getChildFragmentManager(), weChatTabBean.getData(), fragments);
        mVpWechat.setAdapter(wcVpAdapter);
        //关联
        mTabWechat.setupWithViewPager(mVpWechat);
        mVpWechat.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //什么都没干
                if (state == 0) {
                    //显示before
                    AllFragment.change(3);
                    //正在滑动
                } else if (state == 1) {
                    //显示before
                    AllFragment.change(2);
                    //滑动页面完毕
                } else if (state == 2) {
                    //显示after
                    AllFragment.change(3);
                }
            }
        });
    }
}
