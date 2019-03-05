package jjr.com.playandroids.sideslip_menu;

import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.base.fragment.SimperFragment;
import jjr.com.playandroids.contact.Global;
import jjr.com.playandroids.utils.ACache;
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
        cacheFile = new File(Global.PATH_CACHE);
        mTvClear.setText(ACache.getCacheSize(cacheFile));
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
