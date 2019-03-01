package jjr.com.playandroids.playandroid_frgment.wechat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

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

    @Override
    public int createLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initData() {
        initView();
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
        WxRlvAdapter wxRlvAdapter = new WxRlvAdapter(getContext(), datas);
        mWeDetailListRecyclerView.setAdapter(wxRlvAdapter);
        mWeDetailListRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    public void initView() {
        mWxBeforeSearch = (LinearLayout) mView.findViewById(R.id.wx_before_search);
        mEdittextSearch = (EditText) mView.findViewById(R.id.edittext_search);
        mSearchAfter = (LinearLayout) mView.findViewById(R.id.search_after);
        mWeDetailListRecyclerView = (RecyclerView) mView.findViewById(R.id.we_detail_list_recycler_view);
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
