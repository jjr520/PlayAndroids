package jjr.com.playandroids.activitys;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import jjr.com.playandroids.R;
import jjr.com.playandroids.beans.setting.NightModeEvent;
import jjr.com.playandroids.beans.sixlistbean.Login;
import jjr.com.playandroids.playandroid_frgment.FiveFragmnet;
import jjr.com.playandroids.playandroid_frgment.FourFragmnet;
import jjr.com.playandroids.playandroid_frgment.OneFragmnet;
import jjr.com.playandroids.playandroid_frgment.ThereFragmnet;
import jjr.com.playandroids.playandroid_frgment.TwoFragment;

import jjr.com.playandroids.sideslip_menu.AboutFragment;
import jjr.com.playandroids.sideslip_menu.CollectFragment;
import jjr.com.playandroids.sideslip_menu.PlayAndroidFragment;
import jjr.com.playandroids.sideslip_menu.SetFragment;
import jjr.com.playandroids.utils.BottomNavigationViewHelper;
import jjr.com.playandroids.utils.CircularAnimUtil;
import jjr.com.playandroids.utils.SPUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton mMainFloatingActionBtn;
    private BottomNavigationView mBottomNavigationView;
    private OneFragmnet mOneFragmnet;
    private TextView mTopTitle;
    private ImageView mUseful_sitess;
    private ImageView search;
    private DrawerLayout mDrawer;
    private MenuItem mItem;
    private TextView mUser;
    private PopupWindow mPopupWindow;
    private MenuItem mBottomItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);


        setContentView(R.layout.activity_main);
        setstatus("白色", Color.parseColor("#23b0df"));
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        boolean nightTrue = SPUtils.getInstance(this).getBoolean("Night");
        Log.d("MainActivity", "nightTrue:" + nightTrue);
        if (nightTrue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);//切换夜间模式
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//切换日间模式
        }

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = mDrawer.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }

        };
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlayAndroidFragment playAndroidFragment = new PlayAndroidFragment();
        mOneFragmnet = new OneFragmnet();
        fragmentTransaction.replace(R.id.fram, mOneFragmnet);
        fragmentTransaction.commit();
        navigationView.setNavigationItemSelectedListener(this);

        mItem = navigationView.getMenu().findItem(R.id.nav_logout);

        View headerView = navigationView.getHeaderView(0);

        mUser = headerView.findViewById(R.id.textView);

        SharedPreferences nba = getSharedPreferences("nba", MODE_PRIVATE);
        boolean bool = nba.getBoolean("bool", false);
        String title = nba.getString("title", "");
        Log.e("tag", "==========>>" + bool);
        if (bool) {
            mItem.setVisible(true);
            mUser.setText(title);
        } else {
            mItem.setVisible(false);
        }

        LoginActivity.setOnItemClickListener(new LoginActivity.OnItemClickListener() {
            @Override
            public void OnItemClick(String s) {
                mDrawer.openDrawer(Gravity.LEFT);
                mUser.setText(s);
                mItem.setVisible(true);
            }
        });
        LoginActivity.setOnItemLongClickListener(new LoginActivity.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick() {
                mDrawer.openDrawer(Gravity.LEFT);
            }
        });
        AboutActivity.setOnItemClickListener(new AboutActivity.OnItemClickListener() {
            @Override
            public void OnItemClick() {
                mDrawer.openDrawer(Gravity.LEFT);
            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (id == R.id.nav_wanAndroid) {
            mTopTitle.setText("首页");
            // Handle the camera action
            mDrawer.closeDrawer(GravityCompat.START);
            mBottomNavigationView.setVisibility(View.VISIBLE);
            mMainFloatingActionBtn.setVisibility(View.VISIBLE);
            mBottomItems.setChecked(true);
            fragmentTransaction.replace(R.id.fram, mOneFragmnet);
        } else if (id == R.id.nav_collect) {
            mTopTitle.setText("收藏");
            mDrawer.closeDrawer(GravityCompat.START);
            SharedPreferences nba = getSharedPreferences("nba", MODE_PRIVATE);
            boolean bool = nba.getBoolean("bool", false);
            if (bool) {
                mMainFloatingActionBtn.setVisibility(View.VISIBLE);

                fragmentTransaction.replace(R.id.fram, new CollectFragment());
            } else {
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(this, "请先登录~~", Toast.LENGTH_SHORT).show();
            }
            mBottomNavigationView.setVisibility(View.GONE);

        } else if (id == R.id.nav_setting) {
            mTopTitle.setText("设置");
            mDrawer.closeDrawer(GravityCompat.START);
            fragmentTransaction.replace(R.id.fram, new SetFragment());
            mBottomNavigationView.setVisibility(View.GONE);
            mMainFloatingActionBtn.setVisibility(View.GONE);
        } else if (id == R.id.nav_about_us) {
            mTopTitle.setText("关于我们");
            mDrawer.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, AboutActivity.class));
            mBottomNavigationView.setVisibility(View.GONE);
        } else if (id == R.id.nav_logout) {
            mTopTitle.setText("退出登录");
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha = 0.5f;
            getWindow().setAttributes(lp);
            mDrawer.openDrawer(GravityCompat.START);
            View view = LayoutInflater.from(this).inflate(R.layout.exit_deligo, null, false);
            mPopupWindow = new PopupWindow();
            mPopupWindow.setContentView(view);
            mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        /*    popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable());*/
            mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            mPopupWindow.setOutsideTouchable(false);
            mPopupWindow.setFocusable(false);

            view.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            view.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItem.setVisible(false);
                    mUser.setText("登录");
                    SharedPreferences nba = getSharedPreferences("nba", MODE_PRIVATE);
                    SharedPreferences.Editor edit = nba.edit();
                    edit.putBoolean("bool", false);
                    edit.commit();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    mPopupWindow.dismiss();
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);

                }
            });


        }
        fragmentTransaction.commit();


        return true;


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            return false;
        }
        return super.dispatchTouchEvent(event);

    }

    //设置状态栏
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

    private int a1 = 1;
    private int a=1;
    private boolean bo1 = true;
    private boolean bo2 = true;
    private boolean bo3 = true;
    private boolean bo4 = true;
    private boolean bo5 = true;

    private void initView() {
        mMainFloatingActionBtn = (FloatingActionButton) findViewById(R.id.main_floating_action_btn);
        mMainFloatingActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(a1);
            }
        });
        mMainFloatingActionBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#269378")));
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mBottomItems = mBottomNavigationView.getMenu().getItem(0);
        mTopTitle = (TextView) findViewById(R.id.top_title);
        mUseful_sitess = findViewById(R.id.useful_Sites);
        search = findViewById(R.id.search);
        mUseful_sitess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircularAnimUtil.startActivity(MainActivity.this, new Intent(MainActivity.this, Useful_sitessActivity.class), mUseful_sitess, R.color.colorCard);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircularAnimUtil.startActivity(MainActivity.this, new Intent(MainActivity.this, SearchActivity.class), mUseful_sitess, R.color.colorCard);
            }
        });
        mTopTitle.setText("首页");
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);


        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mTopTitle.setText(item.getTitle());
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        a1 = 1;
                        if (bo1) {
                            fragmentTransaction.replace(R.id.fram, mOneFragmnet);
                            bo1 = false;
                            bo2 = true;
                            bo3 = true;
                            bo4 = true;
                            bo5 = true;
                        }

                        break;
                    case R.id.tab_knowledge_hierarchy:
                        a1 = 2;
                        if (bo2) {
                            fragmentTransaction.replace(R.id.fram, new TwoFragment());
                            bo2 = false;
                            bo1 = true;
                            bo3 = true;
                            bo4 = true;
                            bo5 = true;
                        }

                        break;
                    case R.id.tab_wx_article:
                        a1 = 3;
                        if (bo3) {
                            fragmentTransaction.replace(R.id.fram, new ThereFragmnet());
                            bo3 = false;
                            bo2 = true;
                            bo1 = true;
                            bo4 = true;
                            bo5 = true;
                        }

                        break;
                    case R.id.tab_navigation:
                        a1 = 4;
                        if (bo4) {
                            fragmentTransaction.replace(R.id.fram, new FourFragmnet());
                            bo4 = false;
                            bo2 = true;
                            bo3 = true;
                            bo1 = true;
                            bo5 = true;
                        }

                        break;
                    case R.id.tab_project:
                        a1 = 5;
                        if (bo5) {
                            fragmentTransaction.replace(R.id.fram, new FiveFragmnet());
                            bo5 = false;
                            bo2 = true;
                            bo3 = true;
                            bo1 = true;
                            bo4 = true;
                        }

                        break;
                    default:

                        break;
                }
                fragmentTransaction.commit();
                return true;
            }
        });


    }

    private void jump(int a) {
        switch (a) {
            case 1:
                EventBus.getDefault().postSticky("1");
                break;
            case 2:
                EventBus.getDefault().postSticky("2");
                break;
            case 3:
                EventBus.getDefault().postSticky("3");
                break;
            case 4:
                EventBus.getDefault().postSticky("4");
                break;
            case 5:
                EventBus.getDefault().postSticky("5");
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getNightModeEvent(NightModeEvent nightModeEvent) {
        boolean nightTrue = SPUtils.getInstance(this).getBoolean("Night");
        Log.d("MainActivity", "nightTrue:" + nightTrue);
        if (nightTrue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);//切换夜间模式
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//切换日间模式
        }
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.animo_alph_close, R.anim.animo_alph_close);
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
