package jjr.com.playandroids.activitys.knowledge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jjr.com.playandroids.R;
import jjr.com.playandroids.base.activity.SimperActivity;

public class KnowWebActivity extends SimperActivity {

    @BindView(R.id.web_back)
    ImageView mWebBack;
    @BindView(R.id.web_title)
    TextView mWebTitle;
    @BindView(R.id.web_like)
    ImageView mWebLike;
    @BindView(R.id.rela)
    RelativeLayout mRela;
    @BindView(R.id.webview)
    WebView mWebview;

    boolean isLike = true;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @Override
    public int createLayoutId() {
        return R.layout.activity_know_web;
    }

    @Override
    protected void initData() {
        setstatus("白色", Color.parseColor("#23b0df"));

        Intent intent = getIntent();
        String allWeb = intent.getStringExtra("allWeb");

        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.loadUrl(allWeb);

        mWebview.setWebChromeClient(new WebChromeClient() {
            //获取网页的标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mWebTitle.setText(title);
            }
        });

    }

    @OnClick({R.id.web_back, R.id.web_like})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.web_back:
                finish();
                break;
            case R.id.web_like:
                if (isLike) {
                    mWebLike.setImageResource(R.mipmap.ic_toolbar_like_p);
                    isLike = false;
                } else {
                    mWebLike.setImageResource(R.mipmap.ic_toolbar_like_n);
                    isLike = true;
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return true;
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {

                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                Toast.makeText(mActivity, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_web:
                Toast.makeText(mActivity, "用系统浏览器打开", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
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
