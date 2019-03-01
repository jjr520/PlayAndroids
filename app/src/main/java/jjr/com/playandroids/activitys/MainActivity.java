package jjr.com.playandroids.activitys;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import jjr.com.playandroids.R;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton mMainFloatingActionBtn;
    private BottomNavigationView mBottomNavigationView;
    private OneFragmnet mOneFragmnet;
    private TextView mTopTitle;
    private ImageView mUseful_sitess;
    private ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setstatus("白色", Color.parseColor("#23b0df"));
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = drawer.getChildAt(0);
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
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlayAndroidFragment playAndroidFragment = new PlayAndroidFragment();
        mOneFragmnet = new OneFragmnet();
        fragmentTransaction.replace(R.id.fram, mOneFragmnet);
        fragmentTransaction.commit();
        navigationView.setNavigationItemSelectedListener(this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (id == R.id.nav_wanAndroid) {
            // Handle the camera action
            mBottomNavigationView.setVisibility(View.VISIBLE);
            fragmentTransaction.replace(R.id.fram, mOneFragmnet);
            Toast.makeText(this, "玩安卓", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_collect) {
            fragmentTransaction.replace(R.id.fram, new CollectFragment());
            mBottomNavigationView.setVisibility(View.GONE);
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_setting) {
            fragmentTransaction.replace(R.id.fram, new SetFragment());
            mBottomNavigationView.setVisibility(View.GONE);
            Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about_us) {
            fragmentTransaction.replace(R.id.fram, new AboutFragment());
            mBottomNavigationView.setVisibility(View.GONE);
            Toast.makeText(this, "关于我们", Toast.LENGTH_SHORT).show();
        }
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


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

    private int a;

    private void initView() {
        mMainFloatingActionBtn = (FloatingActionButton) findViewById(R.id.main_floating_action_btn);
        mMainFloatingActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(a);
            }
        });
        mMainFloatingActionBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#269378")));
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mTopTitle = (TextView) findViewById(R.id.top_title);
        mUseful_sitess = findViewById(R.id.useful_Sites);
        search = findViewById(R.id.search);
        mUseful_sitess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Useful_sitessActivity.class));
                Toast.makeText(MainActivity.this, "常用网站", Toast.LENGTH_SHORT).show();

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
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
                boolean bo = true;
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        a = 1;
                        fragmentTransaction.replace(R.id.fram, mOneFragmnet);

                        break;
                    case R.id.tab_knowledge_hierarchy:
                        a = 2;
                        fragmentTransaction.replace(R.id.fram, new TwoFragment());
                        break;
                    case R.id.tab_wx_article:
                        a = 3;
                        fragmentTransaction.replace(R.id.fram, new ThereFragmnet());
                        break;
                    case R.id.tab_navigation:
                        a = 4;
                        fragmentTransaction.replace(R.id.fram, new FourFragmnet());
                        break;
                    case R.id.tab_project:
                        a = 5;
                        fragmentTransaction.replace(R.id.fram, new FiveFragmnet());
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
}
