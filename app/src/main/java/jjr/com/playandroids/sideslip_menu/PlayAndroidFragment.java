package jjr.com.playandroids.sideslip_menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jjr.com.playandroids.R;
import jjr.com.playandroids.VPAdapter;
import jjr.com.playandroids.base.fragment.SimperFragment;
import jjr.com.playandroids.playandroid_frgment.FiveFragmnet;
import jjr.com.playandroids.playandroid_frgment.FourFragmnet;
import jjr.com.playandroids.playandroid_frgment.OneFragmnet;
import jjr.com.playandroids.playandroid_frgment.ThereFragmnet;
import jjr.com.playandroids.playandroid_frgment.TwoFragmnet;

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
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        title.add("安卓");
        title.add("安卓");
        title.add("安卓");
        title.add("安卓");
        title.add("安卓");
        fragments.add(new OneFragmnet());
        fragments.add(new TwoFragmnet());
        fragments.add(new ThereFragmnet());
        fragments.add(new FourFragmnet());
        fragments.add(new FiveFragmnet());

        VPAdapter adapter = new VPAdapter(getChildFragmentManager(), fragments,title);
        mVp.setAdapter(adapter);

        mTab.setupWithViewPager(mVp);


    }
}
