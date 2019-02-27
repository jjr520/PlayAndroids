package jjr.com.playandroids.view;



import jjr.com.playandroids.base.view.BaseView;
import jjr.com.playandroids.only.OnlyOne;

/**
 * Created by Administrator on 2019/1/18.
 */

public interface MyView<T> extends BaseView {
    void showData(T t, OnlyOne onlyOne);

}
