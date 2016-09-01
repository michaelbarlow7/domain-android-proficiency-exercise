package com.mbarlow.domainbondiproperties.ui.search;

import com.mbarlow.domainbondiproperties.data.ListingRepository;
import com.mbarlow.domainbondiproperties.event.ListingItemSelectedEvent;
import com.mbarlow.domainbondiproperties.event.RefreshCalledEvent;
import com.mbarlow.domainbondiproperties.model.Listing;
import com.mbarlow.domainbondiproperties.ui.base.BasePresenter;
import com.mbarlow.domainbondiproperties.ui.search.SearchContract.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by michael on 30/08/16.
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter{

    private final Scheduler mainScheduler, ioScheduler;
    private final ListingRepository listingRepository;

    public SearchPresenter(ListingRepository listingRepository, Scheduler ioScheduler, Scheduler mainScheduler) {
        this.listingRepository = listingRepository;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    @Override
    public void attachView(View mvpView) {
        super.attachView(mvpView);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachView() {
        super.detachView();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Retrieves the listings stored on the device. This retrieves the listings from disk,
     * so this is an asynchronous operation.
     */
    @Override
    public void getListings() {
        addSubscription(listingRepository.getListings().subscribeOn(ioScheduler).observeOn(mainScheduler)
                .subscribe(new Subscriber<List<Listing>>() {
                    @Override
                    public void onCompleted() {
                        // Nothing
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Listing> listings) {
                        view.showListings(listings);
                    }
                }));
    }

    /**
     * Refreshes the listings via the API.
     */
    @Override
    public void refreshListings() {
        view.showLoading();
        addSubscription(listingRepository.refreshListings().subscribeOn(ioScheduler).observeOn(mainScheduler)
                .subscribe(new Subscriber<List<Listing>>() {
                    @Override
                    public void onCompleted() {
                        // Nothing
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideLoading();
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Listing> listings) {
                        view.hideLoading();
                        getListings();
                    }
                }));
    }

    @Subscribe
    public void onEventMainThread(ListingItemSelectedEvent event){
        view.showListing(event.getSelectedAdId());
    }

    @Subscribe
    public void onEvent(RefreshCalledEvent event){
        refreshListings();
    }
}
