package jjr.com.playandroids.module;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.only.OnlyThere;
import jjr.com.playandroids.only.OnlyTwo;

public class ThereModule {
    public interface ThereCallBack<T> extends BaseModel {
        void setData(T t, String onlyOne);
    }
    public void getDataThere(final ThereCallBack thereCallBack, final String onlyOne, Object object){
        switch (onlyOne){
            case OnlyThere.THERE:

                break;
        }
    }
}
