package jjr.com.playandroids.playandroid_frgment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.adapter.FourconentAdapter;
import jjr.com.playandroids.adapter.fouradapter.FourTabAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.fourlistbean.NaviListBean;
import jjr.com.playandroids.only.OnlyFour;
import jjr.com.playandroids.persenter.FourPresenter;
import jjr.com.playandroids.view.FourView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class FourFragmnet extends BaseFragment<FourView, FourPresenter<FourView>> implements FourView {
    List<NaviListBean.DataBean> list = new ArrayList<>();
    List<NaviListBean.DataBean.ArticlesBean> list2 = new ArrayList<>();
    @BindView(R.id.rv_four_tab)
    RecyclerView rv_four_tab;

    Unbinder unbinder;
    @BindView(R.id.rv_four_content)
    RecyclerView rvFourContent;
    private FourTabAdapter fourTabAdapter;
    private FourconentAdapter fourconentAdapter;
    private LinearLayoutManager contentlinearLayoutManager;
    private LinearLayoutManager tablinearLayoutManager;

    @Override
    public void showError(String error) {

    }

    @Override
    public void showDataFour(Object object, String onlyOne) {
        switch (onlyOne) {
            case OnlyFour.NAVI:
                NaviListBean naviListBean = (NaviListBean) object;
                List<NaviListBean.DataBean> data = naviListBean.getData();
                list.addAll(data);
                fourconentAdapter.notifyDataSetChanged();
                fourTabAdapter.notifyDataSetChanged();

                Log.e("22222222", "list2:" + list2);
                Log.e("导航数据", naviListBean.getData().toString());
                break;
        }

    }

    @Override
    protected FourPresenter<FourView> createPresenter() {
        return new FourPresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.four_fragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getData(String str) {
        if ("4".equals(str)) {
            rv_four_tab.smoothScrollToPosition(0);
        }
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        fourTabAdapter = new FourTabAdapter(list);
        tablinearLayoutManager = new LinearLayoutManager(getContext());
        rv_four_tab.setLayoutManager(tablinearLayoutManager);
        rv_four_tab.setAdapter(fourTabAdapter);
        fourTabAdapter.setOnclickLienter(new FourTabAdapter.OnclickLienter() {
            @Override
            public void Click(int position) {
                fourTabAdapter.getColor(position);
                contentlinearLayoutManager.scrollToPositionWithOffset(position,0);
                Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        fourconentAdapter = new FourconentAdapter(list);
        contentlinearLayoutManager = new LinearLayoutManager(getContext());
        rvFourContent.setLayoutManager(contentlinearLayoutManager);
        rvFourContent.setAdapter(fourconentAdapter);
        presenter.getDataFourP(OnlyFour.NAVI, null);

        rvFourContent.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int lastVisibleItemPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    tablinearLayoutManager.scrollToPositionWithOffset(lastVisibleItemPosition,0);
                    fourTabAdapter.getColor(lastVisibleItemPosition);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("FourFragmnet", dx + "" + dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    lastVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                }
            }
        });
    }
}
