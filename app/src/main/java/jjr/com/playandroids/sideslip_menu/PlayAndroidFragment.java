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


/**
 * Created by Administrator on 2019/2/27.
 */

public class PlayAndroidFragment extends SimperFragment {


    @Override
    public int createLayoutId() {
        return R.layout.playandroid_fragment;
    }

    @Override
    protected void initData() {

    }
}





