package jjr.com.playandroids.sideslip_menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.fragment.SimperFragment;

/**
 * Created by Administrator on 2019/2/27.
 */

public class PlayAndroidFragment extends SimperFragment {
    private View view;
    private ViewPager mVp;
    private TabLayout mTab;

    @Override
    public int createLayoutId() {
        return R.layout.playandroid_fragment;
    }

    @Override
    protected void initData() {
        initView(view);

    }


    public void initView(View view) {
        mVp = (ViewPager) view.findViewById(R.id.vp);
        mTab = (TabLayout) view.findViewById(R.id.tab);
    }
}
