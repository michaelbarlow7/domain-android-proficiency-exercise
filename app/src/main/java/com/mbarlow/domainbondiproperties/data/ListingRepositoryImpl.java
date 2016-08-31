package com.mbarlow.domainbondiproperties.data;

import com.mbarlow.domainbondiproperties.data.remote.DomainRestService;
import com.mbarlow.domainbondiproperties.model.Listing;
import com.orm.SugarRecord;

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
        return Observable.defer(() -> domainRestService.getListings()
                .concatMap(apiResponse -> Observable.just(apiResponse.getListingResults().getListings()))
                .doOnNext(listings -> {
                            SugarRecord.deleteAll(Listing.class);
                            SugarRecord.saveInTx(listings);
                        }
                ));
    }

    @Override
    public Observable<List<Listing>> getListings() {
        return Observable.defer(() -> Observable.just(SugarRecord.listAll(Listing.class)));
    }
}
