package jjr.com.playandroids.activitys;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.activity.BaseActivity;
import jjr.com.playandroids.beans.sixlistbean.Register;
import jjr.com.playandroids.only.OnlySix;
import jjr.com.playandroids.persenter.SixPresenter;
import jjr.com.playandroids.view.SixView;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RegisterActivity extends BaseActivity<SixView, SixPresenter<SixView>> implements SixView, View.OnClickListener {


    private Toolbar mRegisterToolbar;
    /**
     * 输入密码
     */
    private EditText mRegisterPasswordEdit;
    /**
     * 邮箱/手机号
     */
    private EditText mRegisterAccountEdit;
    /**
     * 确认密码
     */
    private EditText mRegisterAnginPassword;
    /**
     * 注册
     */
    private Button mRegisterBtn;

    @Override
    protected void initData() {
        initView();
    }

    @Override
    public int createLayoutId() {
        setstatus("白色", Color.parseColor("#303c59"));
        return R.layout.activity_register;
    }

    @Override
    public void showData(Object object, String onlyOne) {
        Register register = (Register) object;
        Log.e("tag", "========>>" + register.getErrorCode());
        if (register.getErrorCode() == 0) {
            finish();
        } else if (register.getErrorCode() == -1) {
            Toast.makeText(mActivity, "该用户已经被注册", Toast.LENGTH_SHORT).show();
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
        mRegisterToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        mRegisterToolbar.setTitle("");
        setSupportActionBar(mRegisterToolbar);
        mRegisterToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(mActivity, " sfsdfdsfsd", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        mRegisterPasswordEdit = (EditText) findViewById(R.id.register_password_edit);
        mRegisterAccountEdit = (EditText) findViewById(R.id.register_account_edit);
        mRegisterAnginPassword = (EditText) findViewById(R.id.register_angin_password);
        mRegisterBtn = (Button) findViewById(R.id.register_btn);
        mRegisterBtn.setOnClickListener(this);

        mRegisterAccountEdit.setFocusable(true);
        mRegisterAccountEdit.setFocusableInTouchMode(true);
        mRegisterAccountEdit.requestFocus();
        //显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //如果上面的代码没有弹出软键盘 可以使用下面另一种方式
       /* InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mRegisterAccountEdit, 0);*/
    /*    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.register_btn:
                String user = mRegisterAccountEdit.getText().toString();
                String password = mRegisterPasswordEdit.getText().toString();
                String password2 = mRegisterAnginPassword.getText().toString();
                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(password2)) {

                    if (user.matches("[1][3578][0-9]{9}") && password.matches("\\w{6,}") && password.equals(password2)) {
                        RequestBody requestBody = new FormBody.Builder()
                                .add("username", user)
                                .add("password", password)
                                .add("repassword", password2)
                                .build();

                        presenter.getDataP(OnlySix.REGISTER, requestBody);
                    } else {
                        Snackbar.make(mRegisterBtn, "注册失败", Snackbar.LENGTH_LONG)
                                .setAction("知道了", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).show();
                    }

                } else {
                    Snackbar.make(mRegisterBtn, "用户名或密码不能为空", Snackbar.LENGTH_LONG)
                            .setAction("知道了", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();
                }

                break;
        }
    }
}
