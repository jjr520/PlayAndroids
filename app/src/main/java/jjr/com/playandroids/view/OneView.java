package jjr.com.playandroids.view;

import jjr.com.playandroids.base.view.BaseView;

public interface OneView<T> extends BaseView {
    void showDataOne(T t, String onlyOne);
}
