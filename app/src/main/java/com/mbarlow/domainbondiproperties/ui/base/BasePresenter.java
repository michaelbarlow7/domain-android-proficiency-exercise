package com.mbarlow.domainbondiproperties.ui.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by michael on 30/08/16.
 */
public abstract class BasePresenter<T extends MvpView> implements MvpPresenter<T>{

    // May need to be set to private and to implement a getter
    protected T view;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        compositeSubscription.clear();
        view = null;
    }

    protected void addSubscription(Subscription subscription){
        this.compositeSubscription.add(subscription);
    }

}
