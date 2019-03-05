package jjr.com.playandroids.activitys;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.io.IOException;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.activity.BaseActivity;
import jjr.com.playandroids.beans.sixlistbean.Login;
import jjr.com.playandroids.only.OnlySix;
import jjr.com.playandroids.persenter.SixPresenter;
import jjr.com.playandroids.view.SixView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity<SixView, SixPresenter<SixView>> implements SixView, View.OnClickListener {


    private static OnItemClickListener mListener;
    private static OnItemLongClickListener mListener2;
    private Toolbar mLoginToolbar;
    /**
     * 请输入用户名
     */
    private EditText mNameEdit;
    /**
     * 请输入密码
     */
    private EditText mPasswordEdit;
    /**
     * 登录
     */
    private Button mLoginBtn;
    /**
     * 注册
     */
    private Button mRegisterBtn;
    private RelativeLayout mLoginGroup;
    private Intent mIntent;

    @Override
    protected void initData() {
        initView();

    }

    @Override
    public int createLayoutId() {
        setstatus("白色", Color.parseColor("#ffffff"));
        return R.layout.activity_login;
    }

    @Override
    public void showData(Object object, String onlyOne) {
        switch (onlyOne) {
            case OnlySix.LOGIN:
                Login login = (Login) object;
                //  Log.e("tag", "==========>>>" + login.getErrorCode());
                if (login.getErrorCode() == 0) {
                    SharedPreferences nba = getSharedPreferences("nba", MODE_PRIVATE);
                    SharedPreferences.Editor edit = nba.edit();
                    edit.putBoolean("bool",true);
                    edit.putString("title",login.getData().getUsername());
                    edit.commit();
                    finish();
                    mListener.OnItemClick(login.getData().getUsername());

                } else if (login.getErrorCode() == -1) {
                    Toast.makeText(mActivity, "查无此用户", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected SixPresenter<SixView> createPresenter() {
        return new SixPresenter<>();
    }


    public void initView() {
        mIntent = getIntent();
        mLoginToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        mLoginToolbar.setTitle("");
        setSupportActionBar(mLoginToolbar);
        mLoginToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mListener2.OnItemLongClick();
            }
        });
        mNameEdit = (EditText) findViewById(R.id.name_edit);
        mPasswordEdit = (EditText) findViewById(R.id.password_edit);
        mLoginBtn = (Button) findViewById(R.id.login_btn);
        mLoginBtn.setOnClickListener(this);
        mRegisterBtn = (Button) findViewById(R.id.register_btn);
        mRegisterBtn.setOnClickListener(this);
        mLoginGroup = (RelativeLayout) findViewById(R.id.login_group);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_btn:

                String name = mNameEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {

                    if (name.matches("[1][3578][0-9]{9}") && password.matches("\\w{6,}")) {
                        RequestBody requestBody = new FormBody.Builder()
                                .add("username", name)
                                .add("password", password).build();

                        presenter.getDataP(OnlySix.LOGIN, requestBody);
                    } else {
                        Snackbar.make(mLoginBtn, "登录失败", Snackbar.LENGTH_LONG)
                                .setAction("知道了", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).show();
                    }

                } else {
                    Snackbar.make(mLoginBtn, "请输入正确的手机号或者密码", Snackbar.LENGTH_LONG)
                            .setAction("知道了", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();
                }

                break;
            case R.id.register_btn:
                startRegisterPager();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void startRegisterPager() {
        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(mRegisterBtn,
                mRegisterBtn.getWidth() / 2,
                mRegisterBtn.getHeight() / 2,
                0,
                0);
        startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
    }

    public interface OnItemClickListener {
        void OnItemClick(String s);
    }

    public static void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


      public interface OnItemLongClickListener {
          void OnItemLongClick();
      }
      public static void setOnItemLongClickListener(OnItemLongClickListener listener){
          mListener2  = listener;
      }
}
