package jjr.com.playandroids.playandroid_frgment;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.fouractivity.FourInFoActivity;
import jjr.com.playandroids.adapter.fouradapter.FourconentAdapter;
import jjr.com.playandroids.adapter.fouradapter.FourTabAdapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.fourlistbean.NaviListBean;
import jjr.com.playandroids.only.OnlyFour;
import jjr.com.playandroids.persenter.FourPresenter;
import jjr.com.playandroids.view.FourView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class FourFragmnet extends BaseFragment<FourView, FourPresenter<FourView>> implements FourView {
    List<NaviListBean.DataBean> list = new ArrayList<>();
    List<NaviListBean.DataBean.ArticlesBean> list2 = new ArrayList<>();
    @BindView(R.id.rv_four_tab)
    RecyclerView rv_four_tab;
    /*@BindView(R.id.navigation_tab_layout)
    VerticalTabLayout navigation_tab_layout;*/
    @BindView(R.id.error_group)
    RelativeLayout mErrorGroup;
    @BindView(R.id.four_linearlayout)
    LinearLayout four_linearlayout;
    Unbinder unbinder;
    @BindView(R.id.rv_four_content)
    RecyclerView rvFourContent;
    private int mCurrentPosition;
    private int mTitleHeight;
    private FourTabAdapter fourTabAdapter;
    private FourconentAdapter fourconentAdapter;
    private LinearLayoutManager contentlinearLayoutManager;
    private LinearLayoutManager tablinearLayoutManager;

    @Override
    public void showError(String error) {
        four_linearlayout.setVisibility(View.GONE);
        mErrorGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDataFour(Object object, String onlyOne) {
        switch (onlyOne) {
            case OnlyFour.NAVI:
                NaviListBean naviListBean = (NaviListBean) object;
                final List<NaviListBean.DataBean> data = naviListBean.getData();
                list.addAll(data);
                fourconentAdapter.notifyDataSetChanged();
                fourTabAdapter.notifyDataSetChanged();

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
            rvFourContent.smoothScrollToPosition(0);
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
        fourconentAdapter.setOnclickLienter(new FourconentAdapter.OnclickLienter() {
            @Override
            public void Click(int position, String name, String url,String author,int id) {
                Intent intent = new Intent(getContext(),FourInFoActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",name);
                intent.putExtra("id",id);
                intent.putExtra("author",author);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
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
        /*fourconentAdapter.setOnclickLienter(new FourconentAdapter.OnclickLienter() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void Click(int position, String name, String url,View textView) {
                Toast.makeText(context, name + url + position, Toast.LENGTH_SHORT).show();
                *//*Intent intent = new Intent();
                intent.setClass(getContext(), FourInFoActivity.class);
                CircularAnimUtil.startActivity(getActivity(),intent,textView,R.color.white);*//*
                Intent intent = new Intent(mActivity, KnowWebActivity.class);
                intent.putExtra("allWeb",url);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity, textView, "shareNames").toBundle());
            }
        });*/
    }
}
