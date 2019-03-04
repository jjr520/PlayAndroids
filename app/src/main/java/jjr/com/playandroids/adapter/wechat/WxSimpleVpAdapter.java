package jjr.com.playandroids.adapter.wechat;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import jjr.com.playandroids.beans.wechat.WeChatTabBean;

/**
 * Created by 甘之如饴 on 2019/3/4.
 */

public class WxSimpleVpAdapter extends FragmentStatePagerAdapter {
    private List<String> mData;
    private ArrayList<Fragment> mFragments;

    public WxSimpleVpAdapter(FragmentManager fm, List<String> data, ArrayList<Fragment> fragments) {
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
        return mData.get(position);
    }
}
