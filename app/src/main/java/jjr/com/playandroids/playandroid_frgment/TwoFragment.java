package jjr.com.playandroids.playandroid_frgment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.knowledge.KnowDetailActivity;
import jjr.com.playandroids.adapter.knowledge.KnowLedgeAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.knowbean.KonwDataBean;
import jjr.com.playandroids.contact.Global;
import jjr.com.playandroids.only.OnlyTwo;
import jjr.com.playandroids.persenter.TwoPresenter;
import jjr.com.playandroids.user_defined.CustomToast;
import jjr.com.playandroids.view.TwoView;

public class TwoFragment extends BaseFragment<TwoView, TwoPresenter<TwoView>> implements TwoView {
    @BindView(R.id.knowledge_recycler_view)
    RecyclerView mKnowReView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mNormalView;
    @BindView(R.id.error_tv)
    TextView mErrorTv;
    @BindView(R.id.error_reload_tv)
    TextView mErrorReloadTv;
    @BindView(R.id.error_group)
    RelativeLayout mErrorGroup;

    private KnowLedgeAdapter mKnowLedge;

    private ArrayList<KonwDataBean.DataBean> dataBeans = new ArrayList<>();

    @Override
    public int createLayoutId() {
        return R.layout.two_fragment;
    }


    @Override
    protected void initData() {
        setRefresh();
        presenter.getDataTwoP(OnlyTwo.KonwData, 0, 0);

        mKnowReView.setLayoutManager(new LinearLayoutManager(mActivity));
        mKnowReView.setHasFixedSize(true);

        mKnowLedge = new KnowLedgeAdapter(mActivity, dataBeans);
        mKnowReView.setAdapter(mKnowLedge);

        mKnowLedge.setOnClickListener(new KnowLedgeAdapter.OnClickListener() {
            @Override
            public void onClickListener(View v, int position) {
                Intent intent = new Intent(mActivity, KnowDetailActivity.class);
                intent.putExtra("superChapterName", dataBeans.get(position).getName());
                List<KonwDataBean.DataBean.ChildrenBean> children = dataBeans.get(position).getChildren();
                //利用bundle传集合
                Bundle bundle = new Bundle();
                bundle.putSerializable("tabItemBeanList", (Serializable) children);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showError(String error) {
        if (mNormalView != null && mErrorGroup != null) {
            mNormalView.setVisibility(View.GONE);
            mErrorGroup.setVisibility(View.VISIBLE);
        }
        Log.d("TwoFragment", error);
    }


    @OnClick(R.id.error_reload_tv)
    public void onViewClicked() {
        Toast.makeText(context, "error_reload_tv:", Toast.LENGTH_SHORT).show();
        presenter.getDataTwoP(OnlyTwo.KonwData, 0, 0);
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
                dataBeans.clear();
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
                int xOffset = 0, yOffset = 790;
                CustomToast.makeText(mActivity, "没有多余的干货了(ﾉ≧∀≦)ﾉ",
                        Toast.LENGTH_SHORT, xOffset, yOffset).show();
                //Toast.makeText(context, "没有多余的干货了(ﾉ≧∀≦)ﾉ", Toast.LENGTH_SHORT).show();
                refreshLayout.finishLoadMore();
            }
        });

        mNormalView.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getDataTwoP(OnlyTwo.KonwData, 0, 0);
                refreshLayout.finishRefresh();
            }
        });


    }
}
