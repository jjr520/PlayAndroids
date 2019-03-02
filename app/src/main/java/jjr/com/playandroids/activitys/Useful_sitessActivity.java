package jjr.com.playandroids.activitys;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.project.UseAdapter;
import jjr.com.playandroids.base.activity.BaseActivity;
import jjr.com.playandroids.beans.fivelistbean.UseListBean;
import jjr.com.playandroids.lt.MyUseView;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.persenter.FivePresenter;
import jjr.com.playandroids.view.FiveView;

public class Useful_sitessActivity extends BaseActivity<FiveView, FivePresenter<FiveView>> implements FiveView {
    @BindView(R.id.useView)
    MyUseView myUseView;
    private UseAdapter mUseAdapters;

    @Override
    protected FivePresenter<FiveView> createPresenter() {
        return new FivePresenter<>();
    }

    @Override
    protected void initData() {
        presenter.getDataFiveP(OnlyFive.USE, "");
        /*useRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUseAdapters = new UseAdapter(new ArrayList<UseListBean.DataBean>(), this);
        useRecyclerView.setAdapter(mUseAdapters);*/
    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_useful_sitess;
    }

    @Override
    public void showDataFive(Object o, String onlyOne) {
        UseListBean useListBean = (UseListBean) o;
        List<UseListBean.DataBean> data = useListBean.getData();
        //mUseAdapters.setData(data);
        for (UseListBean.DataBean dataBean : data) {
            Button button = new Button(this);
            button.setPadding(5,5,5,5);
            button.setText(dataBean.getName());
            myUseView.addView(button);
        }

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
