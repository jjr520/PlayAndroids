package jjr.com.playandroids.sideslip_menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.MainActivity;
import jjr.com.playandroids.base.fragment.SimperFragment;
import jjr.com.playandroids.beans.setting.NightModeEvent;
import jjr.com.playandroids.contact.Global;
import jjr.com.playandroids.utils.ACache;
import jjr.com.playandroids.utils.SPUtils;
import jjr.com.playandroids.utils.ShareUtil;

/**
 * Created by Administrator on 2019/2/27.
 */

public class SetFragment extends SimperFragment {
    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox mCbCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox mCbImage;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox mCbNight;
    @BindView(R.id.ll_setting_feedback)
    TextView mLlFeedback;
    @BindView(R.id.tv_setting_clear)
    TextView mTvClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout mLlClear;

    private File cacheFile;

    @Override
    public int createLayoutId() {
        return R.layout.set_fragment;
    }

    @Override
    protected void initData() {
        state();
        cacheFile = new File(Global.PATH_CACHE);
        mTvClear.setText(ACache.getCacheSize(cacheFile));

        boolean nightTrue = SPUtils.getInstance(mActivity).getBoolean("Night");
        if (nightTrue) {
            mCbNight.setChecked(true);
        }

        mCbNight.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SPUtils.getInstance(mActivity).put("Night", true);
                    EventBus.getDefault().post(new NightModeEvent(true));
                } else {
                    SPUtils.getInstance(mActivity).put("Night", false);
                    EventBus.getDefault().post(new NightModeEvent(false));
                }
            }
        });
        SharedPreferences noImg = mActivity.getSharedPreferences("noImg", Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = noImg.edit();
        mCbImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit.putBoolean("no", isChecked);
                edit.commit();
            }
        });
    }

    private void state() {
        SharedPreferences noImg = getActivity().getSharedPreferences("noImg", Context.MODE_PRIVATE);
        boolean no = noImg.getBoolean("no", false);
        if (no) {
            mCbImage.setChecked(true);

        } else {
            mCbImage.setChecked(false);
        }
    }

    @OnClick({R.id.ll_setting_feedback, R.id.ll_setting_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_feedback:
                ShareUtil.sendEmail(mActivity, "send_email");
                break;
            case R.id.ll_setting_clear:
                ACache.deleteDir(cacheFile);
                mTvClear.setText(ACache.getCacheSize(cacheFile));
                break;
        }
    }
}
