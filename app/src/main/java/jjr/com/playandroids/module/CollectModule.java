package jjr.com.playandroids.module;

import android.util.Log;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.beans.collect.CollectListBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyCollect;
import jjr.com.playandroids.utils.RxUtils;
import retrofit2.http.PUT;

public class CollectModule {
    public interface CollectCallback<T> extends BaseModel{
        void setCollectList(T t,String only);
    }
    public void getCollectList(final CollectCallback collectCallback, final String onlyCollecy, Object o){
        switch (onlyCollecy){
            case OnlyCollect.COLLECT:
                int page = (int) o;
                MyServer server = HttpManager.getInstance().getServer(MyServer.HOST, MyServer.class);
                server.getCollect(page).compose(RxUtils.<CollectListBean>rxScheduleThread())
                        .subscribe(new BaseObserver<CollectListBean>(collectCallback) {
                            @Override
                            public void onNext(CollectListBean value) {
                                collectCallback.setCollectList(value,onlyCollecy);
                            }
                        });
                break;
        }
    }
}
