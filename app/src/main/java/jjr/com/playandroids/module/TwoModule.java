package jjr.com.playandroids.module;

import jjr.com.playandroids.base.model.BaseModel;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.only.OnlyTwo;

public class TwoModule {
    public interface TwoCallBack<T> extends BaseModel {
        void setData(T t, String onlyOne);
    }
    public void getDataTwo(final TwoCallBack oneCallBack, final String onlyOne, Object object){
        switch (onlyOne){
            case OnlyTwo.TWO:

                break;
        }
    }
}
