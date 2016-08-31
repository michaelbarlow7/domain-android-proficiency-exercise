package com.mbarlow.domainbondiproperties.data;

import com.mbarlow.domainbondiproperties.model.Listing;

import java.util.List;

import rx.Observable;

/**
 * Created by michael on 31/08/16.
 */
public interface ListingRepository {
    Observable<List<Listing>> refreshListings();
    Observable<List<Listing>> getListings();
}
