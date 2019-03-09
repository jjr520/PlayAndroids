package jjr.com.playandroids.playandroid_frgment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jjr.com.playandroids.R;
import jjr.com.playandroids.activitys.knowledge.KnowWebActivity;
import jjr.com.playandroids.activitys.wechat.WxShowSimpleActivity;
import jjr.com.playandroids.adapter.lcadapter.Myadapter;
import jjr.com.playandroids.base.fragment.BaseFragment;
import jjr.com.playandroids.beans.collect.CollectDataList;
import jjr.com.playandroids.beans.one.Articlebean;
import jjr.com.playandroids.beans.one.Bannerbean;
import jjr.com.playandroids.persenter.OnePresenter;
import jjr.com.playandroids.view.OneView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class OneFragmnet extends BaseFragment<OneView,OnePresenter<OneView>> implements OneView{

    @BindView(R.id.main_pager_recycler_view)
    RecyclerView mainPagerRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    int arr = 0;
    private Intents intents;
    List<Bannerbean.DataBean> data;
    List<Articlebean.DataBean.DatasBean> datasBeans;
    private Myadapter myadapter;

    public void setIntents(Intents intents) {
        this.intents = intents;
    }

    @Override
    public void onStart() {
        super.onStart();
        arr=0;
        presenter.getDataOneP(getUrl(arr), "2");
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected OnePresenter<OneView> createPresenter() {
        return new OnePresenter<>();
    }

    @Override
    public int createLayoutId() {
        EventBus.getDefault().register(this);
        return R.layout.one_fragment;

    }

    @Override
    protected void initData() {
        mainPagerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        datasBeans = new ArrayList<>();
        presenter.getDataOneP("banner/json", "1");
        presenter.getDataOneP(getUrl(arr), "2");
        normalView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                arr = 0;
                //datasBeans= new ArrayList<>();
                presenter.getDataOneP(getUrl(arr), "2");
                refreshLayout.finishRefresh();
            }
        });
        normalView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                arr++;
                presenter.getDataOneP(getUrl(arr), "2");
                refreshLayout.finishLoadMore();
            }
        });
    }


    @Override
    public void showDataOne(Object o, String onlyOne) {
        if (o instanceof Articlebean) {
            if (arr==0){
                datasBeans.clear();
            }
            datasBeans.addAll(((Articlebean) o).getData().getDatas());
            Log.e("李涛",datasBeans.toString());
            if (myadapter != null) {
                myadapter.notifyDataSetChanged();
            }
        } else if (o instanceof Bannerbean) {
            data = ((Bannerbean) o).getData();
            if (getUserVisibleHint()) {
                myadapter = new Myadapter(datasBeans, data);
                if (mainPagerRecyclerView != null) {
                    mainPagerRecyclerView.setAdapter(myadapter);
                }
                myadapter.setLitao(new Myadapter.Litao() {
                    @Override
                    public void Mypoject() {
                        intents.Myintien();
                        Toast.makeText(context, "点击", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void IISSB(int position) {
                        MystartActivity(datasBeans.get(position).getTitle(), datasBeans.get(position).getLink(),datasBeans.get(position).isCollect(),datasBeans.get(position).getId());
                    }

                    @Override
                    public void LitaoISSB(int position) {
                        //banner点击事件
                        MystartActivity(data.get(position).getTitle(), data.get(position).getUrl(),datasBeans.get(position).isCollect(),datasBeans.get(position).getId());
                    }

                    @Override
                    public void YISSB(int position) {

                        boolean collect = datasBeans.get(position).isCollect();
                        if (collect) {
                            //取消收藏
                            datasBeans.get(position).setCollect(false);
                            myadapter.notifyItemChanged(position+1, false);
                            presenter.getDataOneP(datasBeans.get(position).getId()+"","4");
                            //Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                        } else {
                            //收藏成功
                            datasBeans.get(position).setCollect(true);
                            myadapter.notifyItemChanged(position+1, true);
                            presenter.getDataOneP(datasBeans.get(position).getId()+"","3");
                        }
                    }

                    @Override
                    public void Myname(int position) {
                        Intent intent = new Intent(context, WxShowSimpleActivity.class);
                        intent.putExtra("title",datasBeans.get(position).getSuperChapterName());
                        intent.putExtra("name",datasBeans.get(position).getChapterName());
                        intent.putExtra("id",datasBeans.get(position).getChapterId());
                        startActivity(intent);
                    }
                });
            }
        }else if (o instanceof CollectDataList){
            //收藏成功
            int errorCode = ((CollectDataList) o).getErrorCode();
            //Toast.makeText(context, ""+errorCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getData(String str) {
        if ("1".equals(str)) {
            mainPagerRecyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public String getUrl(int a) {
        return "article/list/" + a + "/json";
    }

    public void MystartActivity(String title, String url,boolean b,int id) {
        Intent intent = new Intent(context, KnowWebActivity.class);
        intent.putExtra("allWeb", url);
        intent.putExtra("allId",id);
        intent.putExtra("allTitle", title);
        intent.putExtra("allCollect",b);
        startActivity(intent);
    }
    public interface Intents{
        void Myintien();
    }
}
