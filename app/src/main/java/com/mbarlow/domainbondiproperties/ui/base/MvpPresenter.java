package com.mbarlow.domainbondiproperties.ui.base;

/**
 * A base interface for presenters
 *
 * Created by michael on 30/08/16.
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

}
