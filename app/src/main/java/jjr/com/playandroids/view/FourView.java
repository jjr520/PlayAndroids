package jjr.com.playandroids.view;

import jjr.com.playandroids.base.view.BaseView;

public interface FourView<T> extends BaseView {
    void showDataFour(T t, String onlyOne);
}
