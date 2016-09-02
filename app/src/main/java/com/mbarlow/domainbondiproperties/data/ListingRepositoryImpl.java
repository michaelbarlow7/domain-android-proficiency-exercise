package com.mbarlow.domainbondiproperties.data;

import com.mbarlow.domainbondiproperties.data.local.DatabaseHelper;
import com.mbarlow.domainbondiproperties.data.remote.DomainRestService;
import com.mbarlow.domainbondiproperties.model.Listing;

import java.util.List;

import rx.Observable;

/**
 * Created by michael on 31/08/16.
 */
public class ListingRepositoryImpl implements ListingRepository {

    private DomainRestService domainRestService;
    private DatabaseHelper databaseHelper;

    public ListingRepositoryImpl(DomainRestService domainRestService, DatabaseHelper databaseHelper){
        this.domainRestService = domainRestService;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public Observable<List<Listing>> refreshListings() {
        return Observable.defer(() -> domainRestService.getListings()
                .concatMap(apiResponse -> Observable.just(apiResponse.getListingResults().getListings()))
                .doOnNext(listings -> databaseHelper.replaceListings(listings))
                );
    }

    @Override
    public Observable<List<Listing>> getListings() {
        return Observable.defer(() -> Observable.just(databaseHelper.getAllListings()));
    }
}
