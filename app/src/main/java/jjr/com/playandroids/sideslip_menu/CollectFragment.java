package jjr.com.playandroids.sideslip_menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.collect.CollectAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.collect.CollectListBean;
import jjr.com.playandroids.only.OnlyCollect;
import jjr.com.playandroids.persenter.CollectPresenter;
import jjr.com.playandroids.view.CollectView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class CollectFragment extends BaseFragment<CollectView, CollectPresenter<CollectView>> implements CollectView {
    @BindView(R.id.rv_collect)
    RecyclerView rvCollect;
    @BindView(R.id.smart_collect)
    SmartRefreshLayout smartCollect;
    private boolean isSmart = false;
    private int page = 0;
    Unbinder unbinder;
    private List<CollectListBean.DataBean.DatasBean> list = new ArrayList<>();
    private CollectAdapter collectAdapter;

    @Override
    public int createLayoutId() {
        return R.layout.collect_fragment;
    }

    @Override
    protected void initData() {
        collectAdapter = new CollectAdapter(list);
        rvCollect.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCollect.setAdapter(collectAdapter);
        presenter.getCollectP(OnlyCollect.COLLECT, page);
        smartCollect.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getCollectP(OnlyCollect.COLLECT, page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=0;
                presenter.getCollectP(OnlyCollect.COLLECT, page);
                /*if (isSmart){

                }else {


                    isSmart = true;
                }*/
            }
        });
    }

    @Override
    protected CollectPresenter<CollectView> createPresenter() {
        return new CollectPresenter<>();
    }

    @Override
    public void setCollect(Object o, String onlyCollect) {
        CollectListBean collectListBean = (CollectListBean) o;
        //Log.e("CollectFragment", "datas:" + collectListBean.getData().getDatas().get(0).getTitle());
        switch (onlyCollect) {
            case OnlyCollect.COLLECT:
                List<CollectListBean.DataBean.DatasBean> datas = collectListBean.getData().getDatas();
                   if (page == 0){
                       list.clear();
                       list.addAll(datas);
                       smartCollect.finishRefresh();
                   }else {
                       list.addAll(datas);
                       smartCollect.finishLoadMore();
                   }
                collectAdapter.notifyDataSetChanged();
                Log.e("CollectFragment", "datas:" + datas);
                break;
        }
    }

    @Override
    public void showError(String error) {
        Log.e("CollectFragment", error);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
