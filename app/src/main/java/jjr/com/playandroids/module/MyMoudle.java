package jjr.com.playandroids.module;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.only.OnlyOne;

/**
 * Created by Administrator on 2019/2/27.
 */

public class MyMoudle {
    public interface CallBack<T> extends BaseModel{
        void setData(T t, OnlyOne onlyOne);
    }
    public void getDataM(CallBack callBack,OnlyOne onlyOne,Object object){

    }
}
