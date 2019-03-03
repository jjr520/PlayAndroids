package jjr.com.playandroids.playandroid_frgment.knowdetail;


import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.knowledge.KnowWebActivity;
import jjr.com.playandroids.adapter.knowledge.DetailFraAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.knowbean.EventBusBean;
import jjr.com.playandroids.beans.knowbean.KnowDetailsBean;
import jjr.com.playandroids.contact.Global;
import jjr.com.playandroids.only.OnlyTwo;
import jjr.com.playandroids.persenter.TwoPresenter;
import jjr.com.playandroids.user_defined.CustomToast;
import jjr.com.playandroids.view.TwoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowDetailFragment extends BaseFragment<TwoView, TwoPresenter<TwoView>> implements TwoView {

    int page = 0;
    @BindView(R.id.knowledge_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mNormal;

    private ArrayList<KnowDetailsBean.DataBean.DatasBean> datasBeans = new ArrayList<>();

    private int mId;
    private DetailFraAdapter mDetailFraAdapter;
    private String mSuperChapterName;

    public KnowDetailFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public KnowDetailFragment(int id, String superChapterName) {
        mId = id;
        mSuperChapterName = superChapterName;
    }

    @Override
    public int createLayoutId() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_know_detail;
    }

    @Override
    protected void initData() {

        presenter.getDataTwoP(OnlyTwo.KnowDetails, page, mId);
        setRefresh();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        mDetailFraAdapter = new DetailFraAdapter(datasBeans, mActivity, mSuperChapterName);
        mRecyclerView.setAdapter(mDetailFraAdapter);

        //知识体系webview的跳转
        mDetailFraAdapter.setOnClickListener(new DetailFraAdapter.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClickListener(View v, int position) {
                Intent intent = new Intent(mActivity, KnowWebActivity.class);
                intent.putExtra("allWeb",mDetailFraAdapter.mDatas.get(position).getLink());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity, v, "shareNames").toBundle());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventbus(EventBusBean eventBusBean) {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void showDataTwo(Object o, String onlyTwo) {
        switch (onlyTwo) {
            case OnlyTwo.KnowDetails:
                KnowDetailsBean knowDetailsBean = (KnowDetailsBean) o;
                List<KnowDetailsBean.DataBean.DatasBean> datas = knowDetailsBean.getData().getDatas();
                datasBeans.addAll(datas);
                mDetailFraAdapter.notifyDataSetChanged();
                if (datas.size() == 0) {
                    int xOffset = 0, yOffset = 790;
                    CustomToast.makeText(mActivity, "没有多余的干货了(ﾉ≧∀≦)ﾉ",
                            Toast.LENGTH_SHORT, xOffset, yOffset).show();
                }
                break;
        }
    }

    private void setRefresh() {
        mNormal.setPrimaryColorsId(Global.BLUE_THEME, R.color.white);


        //刷新
        mNormal.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                datasBeans.clear();
                presenter.getDataTwoP(OnlyTwo.KnowDetails, page, mId);

                refreshLayout.finishRefresh();
            }
        });

        //加载更多
        mNormal.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getDataTwoP(OnlyTwo.KnowDetails, page, mId);

                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    public void showError(String error) {

        Toast.makeText(mActivity, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected TwoPresenter<TwoView> createPresenter() {
        return new TwoPresenter<>();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
