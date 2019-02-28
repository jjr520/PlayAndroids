package jjr.com.playandroids.view;

import jjr.com.playandroids.base.view.BaseView;

public interface TwoView<T>  extends BaseView {
    void showDataTwo(T t, String onlyOne);
}
