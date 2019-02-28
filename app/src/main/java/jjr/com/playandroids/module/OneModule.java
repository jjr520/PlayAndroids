package jjr.com.playandroids.module;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.only.OnlyOne;

public class OneModule {
    public interface OneCallBack<T> extends BaseModel {
        void setData(T t, String onlyOne);
    }
    public void getDataOne(final OneCallBack oneCallBack, final String onlyOne, Object object){
        switch (onlyOne){

        }
    }
}
