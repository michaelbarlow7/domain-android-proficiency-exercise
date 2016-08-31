package com.mbarlow.domainbondiproperties.ui.search;

import com.mbarlow.domainbondiproperties.ui.base.BasePresenter;

import rx.Scheduler;

/**
 * Created by michael on 30/08/16.
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter{

    private final Scheduler mainScheduler, ioScheduler;

    public SearchPresenter(/* Repository, */ Scheduler ioScheduler, Scheduler mainScheduler) {
        // this.propertyRepository = propertyRepository
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    @Override
    public void search() {
        view.showLoading();
    }
}
