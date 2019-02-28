package jjr.com.playandroids.persenter;

import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.module.MyMoudle;
import jjr.com.playandroids.only.OnlyOne;
import jjr.com.playandroids.view.MyView;

/**
 * Created by Administrator on 2019/2/27.
 */

public class MyPersenter<V extends MyView> extends IBasePresenter<V> implements MyMoudle.CallBack {
   private MyMoudle mMyMoudle =  new MyMoudle();

   public void getDataP(String onlyOne,Object object){
       mMyMoudle.getDataM(this,onlyOne,object);

   }

    @Override
    public void setshowProgressbar() {

    }

    @Override
    public void sethideProgressbar() {

    }

    @Override
    public void setshowError(String error) {

    }

    @Override
    public void setData(Object o, String onlyOne) {
        view.showData(o,onlyOne);
    }
}
