package jjr.com.playandroids.base.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jjr on 2019/2/27.
 */

public abstract class SimperActivity extends AppCompatActivity {
    public Activity mActivity;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(createLayoutId(), null);
        setContentView(viewGroup);
        bind = ButterKnife.bind(this, viewGroup);
        mActivity = this;
        viewCreated(viewGroup);
        initData();
    }

    public void viewCreated(View view) {

    }

    //初始化数据
    protected abstract void initData();

    //初始化布局
    public abstract int createLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
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
}
