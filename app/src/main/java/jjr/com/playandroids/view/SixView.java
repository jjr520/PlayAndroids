package jjr.com.playandroids.view;

import jjr.com.playandroids.base.view.BaseView;

public interface SixView<T> extends BaseView {
    void showData(T t, String onlyOne);
}
