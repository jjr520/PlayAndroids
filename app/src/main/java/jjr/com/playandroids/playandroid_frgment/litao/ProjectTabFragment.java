package jjr.com.playandroids.playandroid_frgment.litao;


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
        setAdapter();
        //滑动置顶

    }

    public void isTop(int num) {
        if(num == 5){
            projectListRecyclerView.scrollToPosition(0);
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
        presenter.getDataFiveP(OnlyFive.LIST, nameId);
    }
}
