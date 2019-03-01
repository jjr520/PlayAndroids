package jjr.com.playandroids.playandroid_frgment.wechat;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.HashMap;
import java.util.List;

import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.wechat.WxRlvAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.wechat.WeChatHistoryBean;
import jjr.com.playandroids.only.OnlyThere;
import jjr.com.playandroids.persenter.TherePresenter;
import jjr.com.playandroids.view.ThereView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends BaseFragment<ThereView<WeChatHistoryBean>, TherePresenter<ThereView<WeChatHistoryBean>>> implements ThereView<WeChatHistoryBean> {

    private static int sI;
    private int mPage = 1;
    private View view;
    private static LinearLayout mWxBeforeSearch;

    /**
     * 带你发现更多干货
     */

    private EditText mEdittextSearch;
    private static LinearLayout mSearchAfter;
    private RecyclerView mWeDetailListRecyclerView;
    private SmartRefreshLayout mRefreshLayout;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initData() {
        initView();
        loadData();
    }

    private void loadData() {
        String name = getArguments().getString("name");
        mEdittextSearch.setHint(name + "带你发现更多干货");
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

    public static AllFragment newInstance(int id, String name) {
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putString("name", name);
        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showError(String error) {
        Log.i("gmc", "复用fragmentshowError: " + error);
    }

    @Override
    protected TherePresenter<ThereView<WeChatHistoryBean>> createPresenter() {
        return new TherePresenter<>();
    }

    @Override
    public void showDataThere(WeChatHistoryBean weChatHistoryBean, String onlyOne) {
        Log.i("gmc", "公众号历史showDataThere: " + weChatHistoryBean);
        //xlv
        List<WeChatHistoryBean.DataBean.DatasBean> datas = weChatHistoryBean.getData().getDatas();
        final WxRlvAdapter wxRlvAdapter = new WxRlvAdapter(getContext(), datas);
        mWeDetailListRecyclerView.setAdapter(wxRlvAdapter);
        mWeDetailListRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        //刷新加载
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.finishRefresh();
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                /*mPage++;
                loadData(mPage);
                wxRlvAdapter.notifyDataSetChanged();*/
                refreshLayout.finishLoadMore();
            }
        });

        //点击监听
        wxRlvAdapter.setOnClickListener(new WxRlvAdapter.onClickListener() {
            @Override
            public void onClick() {
                //点击整个条目
                Toast.makeText(context, "整个条目", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNameClick() {
                //点击名字
                Toast.makeText(context, "点击名字", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initView() {
        mWxBeforeSearch = (LinearLayout) mView.findViewById(R.id.wx_before_search);
        mEdittextSearch = (EditText) mView.findViewById(R.id.edittext_search);
        mSearchAfter = (LinearLayout) mView.findViewById(R.id.search_after);
        mWeDetailListRecyclerView = (RecyclerView) mView.findViewById(R.id.we_detail_list_recycler_view);
        mRefreshLayout = (SmartRefreshLayout) mView.findViewById(R.id.refreshLayout);
    }

    public static void change(int i) {
        sI = i;
        Log.i("gmc", "initData: SHOW" + sI);
        if (sI == 2) {
            //显示before
            mSearchAfter.setVisibility(View.GONE);
            mWxBeforeSearch.setVisibility(View.VISIBLE);
        } else if (sI == 3) {
            //显示after
            mSearchAfter.setVisibility(View.VISIBLE);
            mWxBeforeSearch.setVisibility(View.GONE);
        }
    }
}
