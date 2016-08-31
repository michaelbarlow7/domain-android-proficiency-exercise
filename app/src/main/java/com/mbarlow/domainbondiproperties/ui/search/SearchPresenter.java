package com.mbarlow.domainbondiproperties.ui.search;

import com.mbarlow.domainbondiproperties.data.ListingRepository;
import com.mbarlow.domainbondiproperties.model.Listing;
import com.mbarlow.domainbondiproperties.ui.base.BasePresenter;

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

    /**
     * Retrieves the listings stored on the device. This retrieves the listings from disk,
     * so this is an asynchronous operation.
     */
    @Override
    public void getListings() {
        //TODO
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
                        view.showListings(listings);
                    }
                }));
    }
}
