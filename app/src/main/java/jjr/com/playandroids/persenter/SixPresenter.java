
package jjr.com.playandroids.persenter;

import jjr.com.playandroids.base.presenter.IBasePresenter;
import jjr.com.playandroids.module.SixMoudle;
import jjr.com.playandroids.view.SixView;

/**
 * Created by Administrator on 2019/3/1.
 */

public class SixPresenter<V extends SixView> extends IBasePresenter<V> implements SixMoudle.CallBack {
    private SixMoudle mSixMoudle = new SixMoudle();

    public void getDataP(String onlyOne, Object object) {
        mSixMoudle.getDataM(this, onlyOne, object);
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
    public void setData(Object object, String onlyOne) {
        view.showData(object, onlyOne);
    }
}
