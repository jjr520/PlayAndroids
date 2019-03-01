package jjr.com.playandroids.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import jjr.com.playandroids.R;
import jjr.com.playandroids.base.activity.BaseActivity;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.fivelistbean.UseListBean;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.persenter.FivePresenter;
import jjr.com.playandroids.view.FiveView;

public class Useful_sitessActivity extends BaseActivity<FiveView, FivePresenter<FiveView>> implements FiveView {
    @Override
    protected FivePresenter<FiveView> createPresenter() {
        return new FivePresenter<>();
    }

    @Override
    protected void initData() {
        presenter.getDataFiveP(OnlyFive.USE, "");

    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_useful_sitess;
    }

    @Override
    public void showDataFive(Object o, String onlyOne) {
        UseListBean useListBean = (UseListBean) o;
        Log.e("11111", useListBean.getData().get(0).getName());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mActivity, error, Toast.LENGTH_SHORT).show();
    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_sitess);
    }*/
}
