package jjr.com.playandroids.playandroid_frgment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.knowledge.KnowDetailActivity;
import jjr.com.playandroids.adapter.knowledge.KnowLedgeAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.knowbean.KonwDataBean;
import jjr.com.playandroids.contact.Global;
import jjr.com.playandroids.only.OnlyTwo;
import jjr.com.playandroids.persenter.TwoPresenter;
import jjr.com.playandroids.view.TwoView;

public class TwoFragment extends BaseFragment<TwoView, TwoPresenter<TwoView>> implements TwoView {
    @BindView(R.id.knowledge_recycler_view)
    RecyclerView mKnowReView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mNormalView;
    private KnowLedgeAdapter mKnowLedge;

    private ArrayList<KonwDataBean.DataBean> dataBeans = new ArrayList<>();

    @Override
    public int createLayoutId() {
        return R.layout.two_fragment;
    }


    @Override
    protected void initData() {
        setRefresh();
        presenter.getDataTwoP(OnlyTwo.KonwData);

        mKnowReView.setLayoutManager(new LinearLayoutManager(mActivity));
        mKnowReView.setHasFixedSize(true);

        mKnowLedge = new KnowLedgeAdapter(mActivity, dataBeans);
        mKnowReView.setAdapter(mKnowLedge);

        mKnowLedge.setOnClickListener(new KnowLedgeAdapter.OnClickListener() {
            @Override
            public void onClickListener(View v, int position) {
                Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show();
                ActivityOptions options = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation(mActivity, v, getString(R.string.share_view));
                    Intent intent = new Intent(mActivity, KnowDetailActivity.class);
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }

    /**
     * 机型适配
     *
     * @return 返回true表示非三星机型且Android 6.0以上
     */
    private boolean modelFiltering() {
        return !Build.MANUFACTURER.contains(Global.SAMSUNG) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected TwoPresenter<TwoView> createPresenter() {
        return new TwoPresenter<>();
    }

    @Override
    public void showDataTwo(Object o, String onlyOne) {
        switch (onlyOne) {
            case OnlyTwo.KonwData:
                KonwDataBean konwDataBean = (KonwDataBean) o;
                List<KonwDataBean.DataBean> data = konwDataBean.getData();
                dataBeans.addAll(data);
                mKnowLedge.notifyDataSetChanged();
                Log.d("TwoFragment", "data:" + data);

                break;
        }
    }

    private void setRefresh() {
        mNormalView.setPrimaryColorsId(Global.BLUE_THEME, R.color.white);
        mNormalView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(context, "LoadMore", Toast.LENGTH_SHORT).show();
                refreshLayout.finishLoadMore();
            }
        });

        mNormalView.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(context, "Refresh", Toast.LENGTH_SHORT).show();
                refreshLayout.finishRefresh();
            }
        });


    }
}
