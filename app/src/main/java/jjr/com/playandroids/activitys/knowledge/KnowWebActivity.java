package jjr.com.playandroids.activitys.knowledge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jjr.com.playandroids.R;
import jjr.com.playandroids.base.activity.SimperActivity;
import jjr.com.playandroids.utils.SPUtils;
import jjr.com.playandroids.utils.ShareUtil;
import jjr.com.playandroids.utils.litao.CollectData;
import jjr.com.playandroids.utils.litao.CollectUtils;

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
    @BindView(R.id.web_progressBar)
    ProgressBar mWebProgressBar;
    private String mAllWeb;
    private String mAllTitle;


    @Override
    public int createLayoutId() {
        return R.layout.activity_know_web;
    }

    @Override
    protected void initData() {

        setstatus("白色", Color.parseColor("#23b0df"));

        Intent intent = getIntent();
        mAllWeb = intent.getStringExtra("allWeb");
        mAllTitle = intent.getStringExtra("allTitle");

       /* SharedPreferences collect = getSharedPreferences("title", MODE_PRIVATE);
        String title = collect.getString("title", "");
        if(title.equals(mAllTitle)){
            boolean s = SPUtils.getInstance(this).getBoolean("boolean");
            if (s == true) {
                mWebLike.setImageResource(R.drawable.icon_like_article_not_selected);
            } else {
                mWebLike.setImageResource(R.drawable.selector_toolbar_like);

            }
        }else{

        }*/


        CollectUtils collectUtils = CollectData.getCollectDataInstance().selectSingle(mAllWeb, mAllTitle);
        if (collectUtils != null) {

            if (collectUtils.getState() == true) {
                mWebLike.setImageResource(R.drawable.icon_like_article_not_selected);
            } else {
                mWebLike.setImageResource(R.drawable.selector_toolbar_like);
            }
        } else {
            mWebLike.setImageResource(R.drawable.selector_toolbar_like);
        }


        if (mAllTitle != null) {
            mWebTitle.setText(mAllTitle);
        }

        int biaoji = intent.getIntExtra("biaoji", 0);
        if (biaoji != 0 && biaoji == 1) {
            mWebLike.setVisibility(View.GONE);
        }
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);


        //避免本地浏览器打开
        mWebview.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //设置添加JavaScript等支持
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setAppCacheEnabled(true);
        //设置 缓存模式
        mWebview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        mWebview.getSettings().setDomStorageEnabled(true);

        mWebview.loadUrl(mAllWeb);


        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (mWebProgressBar != null) {
                    if (newProgress == 100) {
                        mWebProgressBar.setVisibility(View.GONE);//加载完网页进度条消失
                    } else {
                        mWebProgressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                        mWebProgressBar.setProgress(newProgress);//设置进度值
                    }
                }
            }
        });
        mWebLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                SharedPreferences collect = getSharedPreferences("title", MODE_PRIVATE);
                SharedPreferences.Editor edit = collect.edit();

                if (state == false) {

                    mWebLike.setImageResource(R.drawable.icon_like_article_not_selected);
                    state = true;
                    SPUtils.getInstance(KnowWebActivity.this).put("boolean", state);
                } else {

                    mWebLike.setImageResource(R.drawable.selector_toolbar_like);
                    state = false;
                    SPUtils.getInstance(KnowWebActivity.this).put("boolean", state);
                }
                edit.putString("title", mAllTitle);
                edit.commit();
*/

                CollectUtils collectUtils = CollectData.getCollectDataInstance().selectSingle(mAllWeb, mAllTitle);
                if (collectUtils == null) {
                    CollectUtils collectUtils1 = new CollectUtils(null, mAllWeb, mAllTitle, "", "", 0, true);
                    CollectData.getCollectDataInstance().insert(collectUtils1);
                    mWebLike.setImageResource(R.drawable.icon_like_article_not_selected);
                } else {
                    if (collectUtils.getState() == false) {
                        collectUtils.setState(true);
                        CollectData.getCollectDataInstance().update(collectUtils);
                        mWebLike.setImageResource(R.drawable.icon_like_article_not_selected);
                    } else {
                        collectUtils.setState(false);
                        CollectData.getCollectDataInstance().update(collectUtils);
                        mWebLike.setImageResource(R.drawable.selector_toolbar_like);
                    }
                }

            }
        });
    }

    @OnClick({R.id.web_back, R.id.web_like})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.web_back:
                finish();
                EventBus.getDefault().postSticky("刷新一下");
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

    //menu图标显示
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
                ShareUtil.shareText(mActivity, mAllWeb, "分享一篇文章");
                break;
            case R.id.action_web:
                Uri uri = Uri.parse(mAllWeb);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
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
