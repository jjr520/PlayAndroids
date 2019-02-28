package jjr.com.playandroids.module;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.beans.fivelistbean.TreeListBean;
import jjr.com.playandroids.http.BaseObserver;
import jjr.com.playandroids.http.HttpManager;
import jjr.com.playandroids.http.MyServer;
import jjr.com.playandroids.only.OnlyFive;
import jjr.com.playandroids.utils.RxUtils;

public class FiveModule {
    public interface FiveCallBack<T> extends BaseModel {
        void setData(T t, String onlyOne);
    }
    public void getDataFive(final FiveCallBack fiveCallBack, final String onlyOne, Object object){
        switch (onlyOne){
            case OnlyFive.FIVE:
            HttpManager.getInstance().getServer(MyServer.HOST,MyServer.class).getTreeListBean().compose(RxUtils.<TreeListBean>rxScheduleThread()).subscribe(new BaseObserver<TreeListBean>(fiveCallBack) {
                @Override
                public void onNext(TreeListBean value) {
                    fiveCallBack.setData(value,onlyOne);
                }
            });
                break;
        }
    }
}