package jjr.com.playandroids.sideslip_menu;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.VpAdapter;
import jjr.com.playandroids.base.fragment.SimperFragment;
import jjr.com.playandroids.playandroid_frgment.FiveFragmnet;
import jjr.com.playandroids.playandroid_frgment.FourFragmnet;
import jjr.com.playandroids.playandroid_frgment.OneFragmnet;
import jjr.com.playandroids.playandroid_frgment.ThereFragmnet;
import jjr.com.playandroids.playandroid_frgment.KnowLedgeFragmnet;

/**
 * Created by Administrator on 2019/2/27.
 */

public class PlayAndroidFragment extends SimperFragment {
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.tab)
    TabLayout mTab;
    Unbinder unbinder;


    @Override
    public int createLayoutId() {
        return R.layout.playandroid_fragment;
    }

    @Override
    protected void initData() {
        initView();
    }


    public void initView() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        title.add("安卓");
        title.add("安卓");
        title.add("安卓");
        title.add("安卓");
        title.add("安卓");
        fragments.add(new OneFragmnet());
        fragments.add(new KnowLedgeFragmnet());
        fragments.add(new ThereFragmnet());
        fragments.add(new FourFragmnet());
        fragments.add(new FiveFragmnet());

        VpAdapter adapter = new VpAdapter(getChildFragmentManager(), fragments, title);
        mVp.setAdapter(adapter);

        mTab.setupWithViewPager(mVp);

        mVp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


    }




}
