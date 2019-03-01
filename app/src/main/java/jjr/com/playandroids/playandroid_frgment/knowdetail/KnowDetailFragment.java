package jjr.com.playandroids.playandroid_frgment.knowdetail;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.knowledge.DetailFraAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.knowbean.KnowDetailsBean;
import jjr.com.playandroids.contact.Global;
import jjr.com.playandroids.only.OnlyTwo;
import jjr.com.playandroids.persenter.TwoPresenter;
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
        return R.layout.fragment_know_detail;
    }

    @Override
    protected void initData() {
        presenter.getDataTwoP(OnlyTwo.KnowDetails, page, mId);
        setRefresh();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        mDetailFraAdapter = new DetailFraAdapter(datasBeans, mActivity, mSuperChapterName);
        mRecyclerView.setAdapter(mDetailFraAdapter);

        mDetailFraAdapter.setOnClickListener(new DetailFraAdapter.OnClickListener() {
            @Override
            public void onClickListener(View v, int position) {

            }
        });
    }

    @Override
    public void showDataTwo(Object o, String onlyTwo) {
        switch (onlyTwo) {
            case OnlyTwo.KnowDetails:
                KnowDetailsBean knowDetailsBean = (KnowDetailsBean) o;
                List<KnowDetailsBean.DataBean.DatasBean> datas = knowDetailsBean.getData().getDatas();
                datasBeans.addAll(datas);
                mDetailFraAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void setRefresh() {
        mNormal.setPrimaryColorsId(Global.BLUE_THEME, R.color.white);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(mActivity, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected TwoPresenter<TwoView> createPresenter() {
        return new TwoPresenter<>();
    }

}
