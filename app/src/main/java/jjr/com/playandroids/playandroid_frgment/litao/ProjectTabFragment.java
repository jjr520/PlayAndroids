package jjr.com.playandroids.playandroid_frgment.litao;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.knowledge.KnowWebActivity;
import jjr.com.playandroids.adapter.project.ProjectListAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.fivelistbean.ProjectListBean;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.persenter.FivePresenter;
import jjr.com.playandroids.view.FiveView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ProjectTabFragment extends BaseFragment<FiveView, FivePresenter<FiveView>> implements FiveView {


    @BindView(R.id.project_list_recycler_view)
    RecyclerView projectListRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    Unbinder unbinder;
    private String nameId;
    private List<ProjectListBean.DataBean.DatasBean> list = new ArrayList<>();
    private ProjectListAdapter mProjectListAdapters;

    public ProjectTabFragment(String string) {
        if (string != null) {
            this.nameId = string;
        }
    }

    @Override
    protected FivePresenter<FiveView> createPresenter() {
        return new FivePresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_project_tab;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        presenter.getDataFiveP(OnlyFive.LIST, nameId);
        setAdapter();
        //滑动置顶
        normalView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getDataFiveP(OnlyFive.LIST, nameId);
                refreshLayout.finishRefresh(1000);
            }
        });
        normalView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getDataFiveP(OnlyFive.LIST, nameId);
                refreshLayout.finishLoadMore(1000);
            }
        });
        mProjectListAdapters.setOnItemClickListener(new ProjectListAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(View v, int position) {
                Intent intent = new Intent(getActivity(), KnowWebActivity.class);
                intent.putExtra("allWeb", list.get(position).getLink());
                intent.putExtra("allTitle", list.get(position).getTitle());
                intent.putExtra("biaoji", 1);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getData(String str) {
        if ("5".equals(str)) {
            projectListRecyclerView.smoothScrollToPosition(0);
        }
    }


    @Override
    public void showDataFive(Object o, String onlyOne) {
        switch (onlyOne) {
            case OnlyFive.LIST:
                ProjectListBean projectListBean = (ProjectListBean) o;
                mProjectListAdapters.setData(projectListBean.getData().getDatas());
                break;
        }

    }

    private void setAdapter() {
        projectListRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mProjectListAdapters = new ProjectListAdapter(context, list);
        projectListRecyclerView.setAdapter(mProjectListAdapters);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void load() {
        super.load();
    }
}
