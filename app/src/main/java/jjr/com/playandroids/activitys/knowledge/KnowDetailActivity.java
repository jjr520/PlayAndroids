package jjr.com.playandroids.activitys.knowledge;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.knowledge.ViewPagerAdapter;
import jjr.com.playandroids.base.activity.SimperActivity;
import jjr.com.playandroids.beans.knowbean.EventBusBean;
import jjr.com.playandroids.beans.knowbean.KonwDataBean;
import jjr.com.playandroids.playandroid_frgment.knowdetail.KnowDetailFragment;

public class KnowDetailActivity extends SimperActivity {


    @BindView(R.id.knowledge_detail_back)
    ImageView mBack;
    @BindView(R.id.knowledge_detail_tab)
    TabLayout mlTab;
    @BindView(R.id.knowledge_detail_viewpager)
    ViewPager mlViewpager;
    @BindView(R.id.knowledge_floating)
    FloatingActionButton mFloat;
    @BindView(R.id.knowledge_detail_toobar)
    TextView mToobar;
    private String mSuperChapterName;


    @Override
    public int createLayoutId() {
        return R.layout.activity_know_detail;
    }

    @Override
    protected void initData() {
        setstatus("白色", Color.parseColor("#23b0df"));
        mFloat.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#269378")));

        Intent intent = getIntent();
        mSuperChapterName = intent.getStringExtra("superChapterName");

        List<KonwDataBean.DataBean.ChildrenBean> tabList = (List<KonwDataBean.DataBean.ChildrenBean>) intent.getSerializableExtra("tabItemBeanList");

        mToobar.setText(mSuperChapterName);

        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();

        for (KonwDataBean.DataBean.ChildrenBean children : tabList) {
            fragments.add(new KnowDetailFragment(children.getId(), mSuperChapterName));
            strings.add(children.getName());
        }
        mlTab.setupWithViewPager(mlViewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, strings);
        mlViewpager.setAdapter(viewPagerAdapter);
    }

    public void setstatus(String textcolortype, int background) {
        //这个是字体颜色
        if (textcolortype.equalsIgnoreCase("黑色")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }
        //这个是背景颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(background);
        }
    }


    @OnClick({R.id.knowledge_detail_back, R.id.knowledge_floating})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.knowledge_detail_back:
                finish();
                break;
            case R.id.knowledge_floating:
                EventBusBean eventBusBean=new EventBusBean();
                EventBus.getDefault().post(eventBusBean);
                break;
        }
    }
}
