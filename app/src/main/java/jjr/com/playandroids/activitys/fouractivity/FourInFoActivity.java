package jjr.com.playandroids.activitys.fouractivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
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

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import jjr.com.playandroids.R;
import jjr.com.playandroids.base.activity.SimperActivity;
import jjr.com.playandroids.utils.ShareUtil;

public class FourInFoActivity extends SimperActivity {

    @BindView(R.id.tool_bar_fourinfo)
    Toolbar toolBarFourinfo;
    @BindView(R.id.web_title_fourinfo)
    TextView webTitleFourinfo;
    @BindView(R.id.web_back_fourinfo)
    ImageView webBackFourinfo;
    @BindView(R.id.web_like_fourinfo)
    ImageView webLikeFourinfo;
    @BindView(R.id.rela)
    RelativeLayout rela;
    @BindView(R.id.web_progressBar_fourinfo)
    ProgressBar webProgressBarFourinfo;
    @BindView(R.id.web_fourinfo)
    WebView webFourinfo;
    private String url;
    private String title;
    boolean isLike = true;

    @Override
    protected void initData() {
        setstatus("白色", Color.parseColor("#23b0df"));
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        if (title !=null){
            webTitleFourinfo.setText(title);
        }
        toolBarFourinfo.setTitle("");
        setSupportActionBar(toolBarFourinfo);

        //避免本地浏览器打开
        webFourinfo.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //设置添加JavaScript等支持
        webFourinfo.getSettings().setJavaScriptEnabled(true);
        webFourinfo.getSettings().setAppCacheEnabled(true);
        //设置 缓存模式
        webFourinfo.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        webFourinfo.getSettings().setDomStorageEnabled(true);

        webFourinfo.loadUrl(url);

        webFourinfo.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (webProgressBarFourinfo != null) {
                    if (newProgress == 100) {
                        webProgressBarFourinfo.setVisibility(View.GONE);//加载完网页进度条消失
                    } else {
                        webProgressBarFourinfo.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                        webProgressBarFourinfo.setProgress(newProgress);//设置进度值
                    }
                }
            }
        });
        webLikeFourinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLike) {
                    webLikeFourinfo.setImageResource(R.mipmap.ic_toolbar_like_p);
                    isLike = false;
                } else {
                    webLikeFourinfo.setImageResource(R.mipmap.ic_toolbar_like_n);
                    isLike = true;
                }
            }
        });
        webBackFourinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

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
                ShareUtil.shareText(mActivity, url, "分享一篇文章");
                break;
            case R.id.action_web:
                Uri uri = Uri.parse(url);
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
    @Override
    public int createLayoutId() {
        return R.layout.activity_four_in_fo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
