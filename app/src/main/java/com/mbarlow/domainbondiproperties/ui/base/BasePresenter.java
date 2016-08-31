package com.mbarlow.domainbondiproperties.ui.base;

import rx.Subscription;

/**
 * Created by michael on 30/08/16.
 */
public class BasePresenter<T extends MvpView> implements MvpPresenter<T>{

    // May need to be set to private and to implement a getter
    protected T view;

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
