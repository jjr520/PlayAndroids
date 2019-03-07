package jjr.com.playandroids.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.knowledge.KnowWebActivity;
import jjr.com.playandroids.adapter.lt.SearchListAdapter;
import jjr.com.playandroids.base.activity.BaseActivity;
import jjr.com.playandroids.beans.collect.CollectDataList;
import jjr.com.playandroids.beans.fivelistbean.Demo;
import jjr.com.playandroids.beans.fivelistbean.SearchBean;
import jjr.com.playandroids.contact.Global;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.persenter.FivePresenter;
import jjr.com.playandroids.view.FiveView;

public class SearchDetailsActivity extends BaseActivity<FiveView, FivePresenter<FiveView>> implements FiveView {
    @BindView(R.id.searchDetails)
    ImageView searchDetails;
    @BindView(R.id.searchDetailsTitle)
    TextView searchDetailsTitle;
    @BindView(R.id.searchDetailsList)
    RecyclerView searchDetailsList;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    private int page = 0;
    private SearchListAdapter mSearchAdapters;
    private String mCids;
    private boolean mStates;

    @Override
    protected FivePresenter<FiveView> createPresenter() {
        return new FivePresenter<>();
    }

    @Override
    protected void initData() {
        final Intent intent = getIntent();
        normalView.setPrimaryColorsId(Global.BLUE_THEME, R.color.white);
        mCids = intent.getStringExtra("cid");
        searchDetailsTitle.setText(mCids);
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("cid", mCids);
        presenter.getDataFiveP(OnlyFive.SEARCHBEAN, map);
        searchDetailsList.setLayoutManager(new LinearLayoutManager(this));
        mSearchAdapters = new SearchListAdapter(this, new ArrayList<SearchBean.DataBean.DatasBean>());
        searchDetailsList.setAdapter(mSearchAdapters);
        searchDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchDetailsActivity.this, MainActivity.class));
                finish();

            }
        });
        mSearchAdapters.setOnItemClickListener(new SearchListAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(View v, int position) {

                Toast.makeText(mActivity, mSearchAdapters.list.get(position).getLink(), Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SearchDetailsActivity.this, KnowWebActivity.class);
                intent1.putExtra("allWeb", mSearchAdapters.list.get(position).getLink());
                intent1.putExtra("allTitle", mSearchAdapters.list.get(position).getTitle());
                boolean collect = mSearchAdapters.list.get(position).isCollect();
                String author = mSearchAdapters.list.get(position).getAuthor();
                intent1.putExtra("allCollect", collect);
                intent1.putExtra("allAuthor", author);
                intent1.putExtra("allId", mSearchAdapters.list.get(position).getId());
                // "https://mp.weixin.qq.com/s/bMDy8_Kwr1MKpL6-NrSYFQ"
                EventBus.getDefault().postSticky(new Demo(mSearchAdapters.list.get(position).getId() + "", mSearchAdapters.list.get(position).isCollect(), page));
                startActivity(intent1);

            }

            @Override
            public void onlistener(int postion) {

            }
        });


        normalView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                HashMap<String, String> map = new HashMap<>();
                map.put("page", page + "");
                map.put("cid", mCids);
                presenter.getDataFiveP(OnlyFive.SEARCHBEAN, map);
                refreshLayout.finishRefresh(1000);
            }
        });
        normalView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                HashMap<String, String> map = new HashMap<>();
                map.put("page", page + "");
                map.put("cid", mCids);
                presenter.getDataFiveP(OnlyFive.SEARCHBEAN, map);
                refreshLayout.finishLoadMore(1000);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("cid", mCids);
        presenter.getDataFiveP(OnlyFive.SEARCHBEAN, map);
    }

    @Override
    public int createLayoutId() {
        return R.layout.activity_search_details;
    }

    @Override
    public void showDataFive(Object o, String onlyOne) {


        switch (onlyOne) {
            case OnlyFive.CANCELCONTENT:
                CollectDataList collectDataList = (CollectDataList) o;
                if (collectDataList != null) {
                    Toast.makeText(mActivity, "取消成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case OnlyFive.CONTENT:
                CollectDataList collectDataList1 = (CollectDataList) o;
                if (collectDataList1 != null) {
                    Toast.makeText(mActivity, "收藏成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case OnlyFive.SEARCHBEAN:
                SearchBean searchBean = (SearchBean) o;
                if (searchBean != null) {
                    mSearchAdapters.setData(searchBean.getData().getDatas(), page);
                }
                break;
        }


    }

    @Override
    public void showError(String error) {
        Toast.makeText(mActivity, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
