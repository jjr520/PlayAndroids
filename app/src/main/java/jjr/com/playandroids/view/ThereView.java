package jjr.com.playandroids.view;

import jjr.com.playandroids.base.view.BaseView;

public interface ThereView<T> extends BaseView {
    void showDataThere(T t, String onlyOne);
}
