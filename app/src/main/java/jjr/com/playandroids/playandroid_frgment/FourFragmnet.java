package jjr.com.playandroids.playandroid_frgment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.fouradapter.FourTabAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.fourlistbean.NaviListBean;
import jjr.com.playandroids.only.OnlyFour;
import jjr.com.playandroids.persenter.FourPresenter;
import jjr.com.playandroids.view.FourView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class FourFragmnet extends BaseFragment<FourView, FourPresenter<FourView>> implements FourView {
    List<NaviListBean.DataBean> list = new ArrayList<>();
    List<NaviListBean.DataBean.ArticlesBean> list2 = new ArrayList<>();
    List<String> strings = new ArrayList<>();
    @BindView(R.id.rv_four_tab)
    RecyclerView rv_four_tab;
    Unbinder unbinder;
    private FourTabAdapter fourTabAdapter;

    @Override
    public void showError(String error) {

    }

    @Override
    public void showDataFour(Object object, String onlyOne) {
        switch (onlyOne) {
            case OnlyFour.NAVI:
                NaviListBean naviListBean = (NaviListBean) object;
                List<NaviListBean.DataBean> data = naviListBean.getData();
                for (int i = 0; i < data.size(); i++) {
                    list2.add(data.get(i).getArticles().get(0));
                }
                fourTabAdapter.notifyDataSetChanged();
                Log.e("22222222", "list2:" + list2);
                Log.e("导航数据", naviListBean.getData().toString());
                break;
        }

    }

    @Override
    protected FourPresenter<FourView> createPresenter() {
        return new FourPresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.four_fragment;
    }

    @Override
    protected void initData() {
        fourTabAdapter = new FourTabAdapter(list2);
        rv_four_tab.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_four_tab.setAdapter(fourTabAdapter);
        fourTabAdapter.setOnclickLienter(new FourTabAdapter.OnclickLienter() {
            @Override
            public void Click(int position) {
                fourTabAdapter.getColor(position);
                Toast.makeText(context, "QQQQQQ", Toast.LENGTH_SHORT).show();
            }
        });
        presenter.getDataFourP(OnlyFour.NAVI, null);


    }
}
