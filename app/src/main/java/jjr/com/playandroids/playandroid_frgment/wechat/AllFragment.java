package jjr.com.playandroids.playandroid_frgment.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.knowledge.KnowWebActivity;
import jjr.com.playandroids.activitys.wechat.WxShowSimpleActivity;
import jjr.com.playandroids.adapter.wechat.WxAllRlvAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.knowbean.EventBusBean;
import jjr.com.playandroids.beans.wechat.WeChatHistoryBean;
import jjr.com.playandroids.only.OnlyThere;
import jjr.com.playandroids.persenter.TherePresenter;
import jjr.com.playandroids.user_defined.CustomToast;
import jjr.com.playandroids.view.ThereView;

import static jjr.com.playandroids.only.OnlyThere.SEARCH;
import static jjr.com.playandroids.only.OnlyThere.WCHISTORY;

/**
 * A simple {@link Fragment} subclass.
 */

public class AllFragment extends BaseFragment<ThereView, TherePresenter<ThereView>> implements ThereView, View.OnClickListener {

    private static int sI;
    private int mPage = 0;
    private View view;
    private static LinearLayout mWxBeforeSearch;

    /**
     * 带你发现更多干货
     */

    private EditText mEdittextSearch;
    public static LinearLayout mSearchAfter;
    private RecyclerView mWeDetailListRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private WxAllRlvAdapter mWxRlvAdapter;
    private int mId;
    private ArrayList<WeChatHistoryBean.DataBean.DatasBean> datasBeans = new ArrayList<>();
    private int mPageCount;
    private int mCurPage;
    private boolean collection;

    /*
     * 发现更多干货
     */

    private TextView mAction_search;
    private int mSearchPage = 1;
    private String mName;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        initView();
        loadData();
        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setEnableLoadMore(true);
        mWxRlvAdapter = new WxAllRlvAdapter(getContext(), datasBeans);
        mWeDetailListRecyclerView.setAdapter(mWxRlvAdapter);
        mWeDetailListRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        //刷新加载
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                datasBeans.clear();
                loadData();
                refreshLayout.finishRefresh();
            }
        });

        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.i("gmc", "onLoadMore:PageCount " + mPageCount);
                Log.i("gmc", "onLoadMore:mPage " + mPage);
                if (mPage < mPageCount) {
                    mPage++;
                    loadData();
                } else {
                    int xOffset = 0, yOffset = 790;
                    CustomToast.makeText(mActivity, "没有多余的干货了(ﾉ≧∀≦)ﾉ",
                            Toast.LENGTH_SHORT, xOffset, yOffset).show();
                }
                refreshLayout.finishLoadMore();
            }
        });

        //点击监听
        mWxRlvAdapter.setOnClickListener(new WxAllRlvAdapter.onClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), KnowWebActivity.class);
                intent.putExtra("allWeb", datasBeans.get(position).getLink());
                intent.putExtra("allTitle", datasBeans.get(position).getTitle());
                startActivity(intent);
            }

            @Override
            public void onNameClick(int position) {
                //点击名字
                Intent intent = new Intent(getContext(), WxShowSimpleActivity.class);
                intent.putExtra("title","");
                intent.putExtra("name", getArguments().getString("name"));
                intent.putExtra("id", getArguments().getInt("id"));
                startActivity(intent);
            }

            @Override
            public void onCollection(int position) {
                //点击变色
                if (collection) {

                    collection = false;
                } else {

                    collection = true;
                }
            }
        });
    }@Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventbus(EventBusBean eventBusBean) {
        mWeDetailListRecyclerView.smoothScrollToPosition(0);
    }

    private void loadData() {
        String search = mEdittextSearch.getText().toString();
        mName = getArguments().getString("name");
        mId = getArguments().getInt("id");
        if (search.length() > 0) {
            HashMap<String, Object> map = new HashMap<>();
            if (mId != 0) {
                map.put("id", mId + "");
            }
            map.put("page", mSearchPage + "");
            map.put("k", search);
            presenter.getDataThereP(OnlyThere.SEARCH, map);
        } else {
            mEdittextSearch.setHint(mName + "带你发现更多干货");
            HashMap<String, Object> map = new HashMap<>();
            if (mId != 0) {
                map.put("id", mId + "");
            }
            if (mName != null) {
                map.put("page", mPage + "");
            }
            presenter.getDataThereP(OnlyThere.WCHISTORY, map);
        }
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
    protected TherePresenter<ThereView> createPresenter() {
        return new TherePresenter<>();
    }

    @Override
    public void showDataThere(Object o, String onlyOne) {
        switch (onlyOne) {
            case SEARCH:
                WeChatHistoryBean wxsearch = (WeChatHistoryBean) o;
                mPageCount = wxsearch.getData().getPageCount();
                mCurPage = wxsearch.getData().getCurPage();
                final List<WeChatHistoryBean.DataBean.DatasBean> datas = wxsearch.getData().getDatas();
                mWxRlvAdapter.addSearch(datas);
                break;
            case WCHISTORY:
                WeChatHistoryBean wxhistory = (WeChatHistoryBean) o;
                mPageCount = wxhistory.getData().getPageCount();
                mCurPage = wxhistory.getData().getCurPage();
                final List<WeChatHistoryBean.DataBean.DatasBean> datasa = wxhistory.getData().getDatas();
                mWxRlvAdapter.addData(datasa);
                mWxRlvAdapter.notifyDataSetChanged();
                break;
        }
    }

    public void initView() {
        mWxBeforeSearch = (LinearLayout) mView.findViewById(R.id.wx_before_search);
        mEdittextSearch = (EditText) mView.findViewById(R.id.edittext_search);
        mSearchAfter = (LinearLayout) mView.findViewById(R.id.search_after);
        mWeDetailListRecyclerView = (RecyclerView) mView.findViewById(R.id.we_detail_list_recycler_view);
        mRefreshLayout = (SmartRefreshLayout) mView.findViewById(R.id.refreshLayout);
        mAction_search = (TextView) mView.findViewById(R.id.action_search_wx);
        mAction_search.setOnClickListener(this);
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getData(String str) {
        if ("3".equals(str)) {
            mWeDetailListRecyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_search_wx:
                //点击搜索获取edit中的字
                loadData();
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}