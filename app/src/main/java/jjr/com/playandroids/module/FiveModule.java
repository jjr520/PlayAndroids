package jjr.com.playandroids.module;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.beans.collect.CollectDataList;
import jjr.com.playandroids.beans.fivelistbean.HotSearch;
import jjr.com.playandroids.beans.fivelistbean.ProjectListBean;
import jjr.com.playandroids.beans.fivelistbean.SearchBean;
import jjr.com.playandroids.beans.fivelistbean.TreeListBean;
import jjr.com.playandroids.beans.fivelistbean.UseListBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.utils.RxUtils;

public class FiveModule {
    public interface FiveCallBack<T> extends BaseModel {
        void setData(T t, String onlyOne);
    }

    public void getDataFive(final FiveCallBack fiveCallBack, final String onlyOne, Object object) {
        switch (onlyOne) {
            case OnlyFive.FIVE:
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class).getTreeListBean().compose(RxUtils.<TreeListBean>rxScheduleThread()).subscribe(new BaseObserver<TreeListBean>(fiveCallBack) {
                    @Override
                    public void onNext(TreeListBean value) {
                        fiveCallBack.setData(value, onlyOne);

                    }
                });
                break;
            case OnlyFive.LIST:
                String s = (String) object;
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class).getProjectListBean(s).compose(RxUtils.<ProjectListBean>rxScheduleThread()).subscribe(new BaseObserver<ProjectListBean>(fiveCallBack) {
                    @Override
                    public void onNext(ProjectListBean value) {
                        fiveCallBack.setData(value, onlyOne);

                    }

                });
                break;
            case OnlyFive.USE:
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class).getUseListBean().compose(RxUtils.<UseListBean>rxScheduleThread()).subscribe(new BaseObserver<UseListBean>(fiveCallBack) {
                    @Override
                    public void onNext(UseListBean value) {
                        fiveCallBack.setData(value, onlyOne);

                    }
                });
                break;
            case OnlyFive.SEARCH:
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class).getHotSearch().compose(RxUtils.<HotSearch>rxScheduleThread()).subscribe(new BaseObserver<HotSearch>(fiveCallBack) {
                    @Override
                    public void onNext(HotSearch value) {
                        fiveCallBack.setData(value, onlyOne);
                    }
                });
                break;
            case OnlyFive.SEARCHBEAN:
                HashMap<String, String> map = (HashMap<String, String>) object;
                String page = map.get("page");
                String cid = map.get("cid");
                //Log.e("page页码", page);
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class).getSearchBean(page, cid).compose(RxUtils.<SearchBean>rxScheduleThread()).subscribe(new BaseObserver<SearchBean>(fiveCallBack) {
                    @Override
                    public void onNext(SearchBean value) {
                        fiveCallBack.setData(value, onlyOne);
                        if(value.getData().getDatas().size()==0){
                            fiveCallBack.setshowError("没有更多数据!");
                        }
                    }
                });
                break;
            case OnlyFive.CONTENT:

                int id = (int) object;
                Log.e("收藏", id + "");
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class).getCollectData(id).compose(RxUtils.<CollectDataList>rxScheduleThread()).subscribe(new BaseObserver<CollectDataList>(fiveCallBack) {
                    @Override
                    public void onNext(CollectDataList value) {
                        fiveCallBack.setData(value, onlyOne);
                    }
                });
                break;
            case OnlyFive.CANCELCONTENT:
                int id2 = (int) object;
                Log.e("取消收藏", id2 + "");
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class).getUncoolect(id2).compose(RxUtils.<CollectDataList>rxScheduleThread()).subscribe(new BaseObserver<CollectDataList>(fiveCallBack) {
                    @Override
                    public void onNext(CollectDataList value) {
                        fiveCallBack.setData(value, onlyOne);
                    }
                });
                break;
            case OnlyFive.CONTENTNI:
                HashMap<String, Object> map1 = (HashMap<String, Object>) object;
                HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class).getDataAdd(map1).compose(RxUtils.<CollectDataList>rxScheduleThread()).subscribe(new BaseObserver<CollectDataList>(fiveCallBack) {
                    @Override
                    public void onNext(CollectDataList value) {
                        fiveCallBack.setData(value, onlyOne);
                    }
                });
                break;
        }
    }
}
