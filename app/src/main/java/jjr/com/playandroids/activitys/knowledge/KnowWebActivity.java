package jjr.com.playandroids.activitys.knowledge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
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
import android.widget.Toast;


import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.OnClick;
import jjr.com.playandroids.R;
import jjr.com.playandroids.base.activity.BaseActivity;
import jjr.com.playandroids.beans.collect.CollectDataList;
import jjr.com.playandroids.beans.fivelistbean.Demo;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.persenter.FivePresenter;
import jjr.com.playandroids.utils.ShareUtil;
import jjr.com.playandroids.view.FiveView;

public class KnowWebActivity extends BaseActivity<FiveView, FivePresenter<FiveView>> implements FiveView {

    private static OnItemClickListener mListener;
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
    private boolean mAllCollects;
    private String mAllAuthors;
    private int mAllIds;
    private int page;


    @Override
    protected FivePresenter<FiveView> createPresenter() {
        return new FivePresenter<>();
    }

    @Override
    protected void initData() {
        setstatus("白色", Color.parseColor("#23b0df"));
        Intent intent = getIntent();
        mAllWeb = intent.getStringExtra("allWeb");
        mAllTitle = intent.getStringExtra("allTitle");
        mAllCollects = intent.getBooleanExtra("allCollect", false);
        mAllAuthors = intent.getStringExtra("allAuthor");
        mAllIds = intent.getIntExtra("allId", 0);
        page = intent.getIntExtra("allPage", 0);

        if (mAllCollects == true) {
            mWebLike.setImageResource(R.mipmap.ic_toolbar_like_p);
        } else {
            mWebLike.setImageResource(R.mipmap.ic_toolbar_like_n);
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

    }

    public interface OnItemClickListener {
        void onClickListener(Demo demo);

    }

    public static void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void showDataFive(Object o, String onlyOne) {
        CollectDataList collectDataList = (CollectDataList) o;
        switch (onlyOne) {
            case OnlyFive.CANCELCONTENT:
                if (collectDataList != null) {
                    Toast.makeText(mActivity, "取消收藏", Toast.LENGTH_SHORT).show();
                }
                break;
            case OnlyFive.CONTENTNI:
                if (collectDataList != null) {
                    Toast.makeText(mActivity, "收藏成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void showError(String error) {
      /*  Toast.makeText(mActivity, error, Toast.LENGTH_SHORT).show();*/
      if(mWebLike!=null){
          mWebLike.setImageResource(R.mipmap.ic_toolbar_like_p);
          // Toast.makeText(mActivity, "收藏ok", Toast.LENGTH_SHORT).show();
          Toast.makeText(mActivity, "操作无效", Toast.LENGTH_SHORT).show();
      }
    }


    @Override
    public int createLayoutId() {
        return R.layout.activity_know_web;
    }


    @OnClick({R.id.web_back, R.id.web_like})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.web_back:
                finish();

                break;
            case R.id.web_like:
                if (mAllCollects == true) {
                    mWebLike.setImageResource(R.mipmap.ic_toolbar_like_n);
                    presenter.getDataFiveP(OnlyFive.CANCELCONTENT, mAllIds);
                    mAllCollects = false;
                    if (mListener != null) {
                        mListener.onClickListener(new Demo(mAllIds + "", false, page));
                    }
                } else {
                    mWebLike.setImageResource(R.mipmap.ic_toolbar_like_p);
                    presenter.getDataFiveP(OnlyFive.CONTENT, mAllIds);
                    mAllCollects = true;
                    if (mListener != null) {
                        mListener.onClickListener(new Demo(mAllIds + "", true, page));
                    }

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
