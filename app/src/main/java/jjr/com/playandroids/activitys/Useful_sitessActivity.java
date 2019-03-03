package jjr.com.playandroids.activitys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.useback)
    ImageView useback;
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
        MyUseView.LayoutParams myView = new MyUseView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < data.size(); i++) {
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.use, null);
            TextView textView = linearLayout.findViewById(R.id.commonItemTitle);
            textView.setText(data.get(i).getName());
            Random myRandom = new Random();
            int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
            textView.setBackgroundColor(ranColor);
            textView.setTextColor(0xffffffff);
            myUseView.addView(linearLayout, i, myView);
        }

    }

    @Override
    public void showError(String error) {
        Toast.makeText(mActivity, error, Toast.LENGTH_SHORT).show();

    }




    @OnClick(R.id.useback)
    public void onViewClicked() {
        finish();
    }





   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_sitess);
    }*/
}
