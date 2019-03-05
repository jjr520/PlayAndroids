package jjr.com.playandroids.activitys;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.flyrefresh.FlyView;
import com.scwang.smartrefresh.header.flyrefresh.MountainSceneView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.activity.BaseActivity;
import jjr.com.playandroids.persenter.SixPresenter;
import jjr.com.playandroids.view.SixView;
import jjr.com.playandroids.widght.ElasticOutInterpolator;

public class AboutActivity extends BaseActivity<SixView, SixPresenter<SixView>> implements SixView {


    private static OnItemClickListener mListener;
    private MountainSceneView mBackgroudMountain;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private AppBarLayout mAppbarLayout;
    private FlyRefreshHeader mAboutFlyRefresh;
    /**
     * Awesome WanAndroid
     */
    private TextView mAboutVersion;
    private TextView mAboutContent;
    private NestedScrollView mScrollView;
    private SmartRefreshLayout mAboutSmartRefresh;
    private FloatingActionButton mAboutFloating;
    private FlyView mAboutFlyview;
    private View.OnClickListener mThemeListener;

    @Override
    protected void initData() {
        initView();
        initRefresh();
    }

    private void showAboutContent() {

        mAboutContent.setText(Html.fromHtml(getString(R.string.about_content)));
        mAboutContent.setMovementMethod(LinkMovementMethod.getInstance());
        try {
            String versionStr = getString(R.string.awesome_wan_android)
                    + " V" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            mAboutVersion.setText(versionStr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initRefresh() {
        showAboutContent();
        setSmartRefreshLayout();


        mAboutSmartRefresh.autoRefresh();
        mAboutFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAboutSmartRefresh.autoRefresh();
            }
        });

        mAppbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean misAppbarExpand = true;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int scrollRange = appBarLayout.getTotalScrollRange();
                float fraction = 1f * (scrollRange + verticalOffset) / scrollRange;
                double minFraction = 0.1;
                double maxFraction = 0.8;
                if (mScrollView == null || mAboutFloating == null || mAboutFlyview == null) {
                    return;
                }
                if (fraction < minFraction && misAppbarExpand) {
                    misAppbarExpand = false;
                    mAboutFloating.animate().scaleX(0).scaleY(0);
                    mAboutFlyview.animate().scaleX(0).scaleY(0);
                    ValueAnimator animator = ValueAnimator.ofInt(mScrollView.getPaddingTop(), 0);
                    animator.setDuration(300);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (mScrollView != null) {
                                mScrollView.setPadding(0, (int) animation.getAnimatedValue(), 0, 0);
                            }
                        }
                    });
                    animator.start();
                }
                if (fraction > maxFraction && !misAppbarExpand) {
                    misAppbarExpand = true;
                    mAboutFloating.animate().scaleX(1).scaleY(1);
                    mAboutFlyview.animate().scaleX(1).scaleY(1);
                    ValueAnimator animator = ValueAnimator.ofInt(mScrollView.getPaddingTop(), DensityUtil.dp2px(25));
                    animator.setDuration(300);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (mScrollView != null) {
                                mScrollView.setPadding(0, (int) animation.getAnimatedValue(), 0, 0);
                            }
                        }
                    });
                    animator.start();
                }
            }
        });

    }

    private void setSmartRefreshLayout() {
        mAboutFlyRefresh.setUp(mBackgroudMountain, mAboutFlyview);
        mAboutSmartRefresh.setReboundInterpolator(new ElasticOutInterpolator());
        mAboutSmartRefresh.setReboundDuration(800);
        mAboutSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                updateTheme();
                refreshLayout.finishRefresh(1000);
            }
        });

        mAboutSmartRefresh.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                refreshLayout.finishRefresh(2000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                refreshLayout.finishLoadMore(3000);
            }

            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                super.onHeaderMoving(header, isDragging, percent, offset, headerHeight, maxDragHeight);
                if (mAppbarLayout == null || mToolbar == null) {
                    return;
                }
                mAppbarLayout.setTranslationY(offset);
                mToolbar.setTranslationY(-offset);

            }
        });

    }

    private void updateTheme() {
        if (mThemeListener == null) {
            mThemeListener = new View.OnClickListener() {
                int index = 0;
                int[] ids = new int[]{
                        R.color.colorPrimary,
                        android.R.color.holo_green_light,
                        android.R.color.holo_red_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_blue_bright,
                };

                @Override
                public void onClick(View v) {
                    int color = ContextCompat.getColor(getApplication(), ids[index % ids.length]);
                    mAboutSmartRefresh.setPrimaryColors(color);
                    mAboutFloating.setBackgroundColor(color);
                    mAboutFloating.setBackgroundTintList(ColorStateList.valueOf(color));
                    mCollapsingToolbar.setContentScrimColor(color);
                    index++;
                }
            };
        }
        mThemeListener.onClick(null);

    }


    @Override
    public int createLayoutId() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        return R.layout.activity_about;
    }

    /**
     * 增加View的paddingTop,增加的值为状态栏高度 (智能判断，并设置高度)
     */
    public static void setPaddingSmart(Context context, View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            if (lp != null && lp.height > 0) {
                lp.height += getStatusBarHeight(context);//增高
            }
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(context),
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 24;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelSize(resId);
        } else {
            result = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    result, Resources.getSystem().getDisplayMetrics());
        }
        return result;
    }

    @Override
    public void showData(Object object, String onlyOne) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected SixPresenter<SixView> createPresenter() {
        return new SixPresenter<>();
    }


    public void initView() {
        mBackgroudMountain = (MountainSceneView) findViewById(R.id.backgroud_mountain);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        setPaddingSmart(this, mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mListener.OnItemClick();
            }
        });
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mAppbarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        mAboutFlyRefresh = (FlyRefreshHeader) findViewById(R.id.about_fly_refresh);
        mAboutVersion = (TextView) findViewById(R.id.aboutVersion);
        mAboutContent = (TextView) findViewById(R.id.aboutContent);
        mScrollView = (NestedScrollView) findViewById(R.id.about_us_content);
        mAboutSmartRefresh = (SmartRefreshLayout) findViewById(R.id.about_smart_refresh);
        mAboutFloating = (FloatingActionButton) findViewById(R.id.about_floating);
        mAboutFlyview = (FlyView) findViewById(R.id.about_flyview);


    }

    public interface OnItemClickListener {
        void OnItemClick();
    }

    public static void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


}
