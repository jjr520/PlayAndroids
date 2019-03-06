package jjr.com.playandroids.view;

import jjr.com.playandroids.base.view.BaseView;

public interface CollectView<T> extends BaseView {
    void setCollect(T t,String onlyCollect);
}
