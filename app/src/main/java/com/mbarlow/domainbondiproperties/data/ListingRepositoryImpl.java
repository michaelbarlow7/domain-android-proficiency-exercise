package com.mbarlow.domainbondiproperties.data;

import com.mbarlow.domainbondiproperties.data.remote.DomainRestService;
import com.mbarlow.domainbondiproperties.model.Listing;

import java.util.List;

import rx.Observable;

/**
 * Created by michael on 31/08/16.
 */
public class ListingRepositoryImpl implements ListingRepository {

    private DomainRestService domainRestService;

    public ListingRepositoryImpl(DomainRestService domainRestService){
        this.domainRestService = domainRestService;
    }

    @Override
    public Observable<List<Listing>> refreshListings() {
        Observable<List<Listing>> observable = Observable.defer(() -> domainRestService.getListings()
                .concatMap(apiResponse -> Observable.just(apiResponse.getListingResults().getListings())));
        return observable;
    }

    @Override
    public Observable<List<Listing>> getListings() {
        // Get from db
        return null;
    }
}
