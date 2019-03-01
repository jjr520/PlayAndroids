package jjr.com.playandroids.adapter.wechat;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import jjr.com.playandroids.beans.wechat.WeChatTabBean;

/**
 * Created by 甘之如饴 on 2019/2/28.
 */

public class WCVpAdapter extends FragmentStatePagerAdapter {

    private List<WeChatTabBean.DataBean> mData;
    private ArrayList<Fragment> mFragments;

    public WCVpAdapter(FragmentManager fm, List<WeChatTabBean.DataBean> data, ArrayList<Fragment> fragments) {
        super(fm);
        mData = data;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position).getName();
    }
}
