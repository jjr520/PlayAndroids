package jjr.com.playandroids.playandroid_frgment.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.knowledge.KnowWebActivity;
import jjr.com.playandroids.adapter.wechat.WxRlvAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.wechat.WeChatHistoryBean;
import jjr.com.playandroids.only.OnlyThere;
import jjr.com.playandroids.persenter.TherePresenter;
import jjr.com.playandroids.user_defined.CustomToast;
import jjr.com.playandroids.view.ThereView;

import static jjr.com.playandroids.only.OnlyThere.WCHISTORY;

/**
 * A simple {@link Fragment} subclass.
 */

public class WxSimpleFragment extends BaseFragment<ThereView, TherePresenter<ThereView>> implements ThereView {

    @BindView(R.id.rlv_wx_simple)
    RecyclerView mRlvWxSimple;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private int mPage = 0;
    private ArrayList<WeChatHistoryBean.DataBean.DatasBean> datasBeans = new ArrayList<>();
    private WxRlvAdapter mWxRlvAdapter;
    private int mPageCount;

    public WxSimpleFragment() {
        // Required empty public constructor
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_wx_simple;
    }

    @Override
    protected void initData() {
//        EventBus.getDefault().register(this);
        loads();
        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setEnableLoadMore(true);
        mWxRlvAdapter = new WxRlvAdapter(getContext(), datasBeans);
        mRlvWxSimple.setAdapter(mWxRlvAdapter);
        mRlvWxSimple.setLayoutManager(new LinearLayoutManager(mActivity));
        //刷新加载
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                datasBeans.clear();
                loads();
                refreshLayout.finishRefresh();
            }
        });

        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mPage < mPageCount) {
                    mPage++;
                    loads();
                } else {
                    int xOffset = 0, yOffset = 790;
                    CustomToast.makeText(mActivity, "没有多余的干货了(ﾉ≧∀≦)ﾉ",
                            Toast.LENGTH_SHORT, xOffset, yOffset).show();
                }
                refreshLayout.finishLoadMore();
            }
        });

        mWxRlvAdapter.setOnClickListener(new WxRlvAdapter.onClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), KnowWebActivity.class);
                intent.putExtra("allWeb", datasBeans.get(position).getLink());
                intent.putExtra("allTitle", datasBeans.get(position).getTitle());
                startActivity(intent);
            }

            @Override
            public void onNameClick(int position) {

            }

            @Override
            public void onCollection(int position) {

            }
        });
    }

    //    @Subscribe(threadMode = ThreadMode.MAIN)
    //    public void getEventbus(EventBusBean eventBusBean) {
    //        mRlvWxSimple.smoothScrollToPosition(0);
    //    }

    private void loads() {
        String name = getArguments().getString("name");
        int id = getArguments().getInt("id");
        HashMap<String, Object> map = new HashMap<>();
        if (id != 0) {
            map.put("id", id + "");
        }
        if (name != null) {
            map.put("page", mPage + "");
        }
        presenter.getDataThereP(OnlyThere.WCHISTORY, map);
    }

    @Override
    public void showDataThere(Object o, String onlyOne) {
        switch (onlyOne) {
            case WCHISTORY:
                WeChatHistoryBean data = (WeChatHistoryBean) o;
                List<WeChatHistoryBean.DataBean.DatasBean> datas = data.getData().getDatas();
                mPageCount = data.getData().getPageCount();
                //添加到适配器
                mWxRlvAdapter.addDatas(datas);
                break;
        }
    }

    @Override
    public void showError(String error) {
        Log.i("gmc", "SimpleShowError: " + error);
    }

    @Override
    protected TherePresenter<ThereView> createPresenter() {
        return new TherePresenter<>();
    }

    public static WxSimpleFragment newInstance(int id, String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("id", id);
        WxSimpleFragment fragment = new WxSimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void getData(String str) {
//        if ("3".equals(str)) {
//            mRlvWxSimple.smoothScrollToPosition(0);
//        }
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
}