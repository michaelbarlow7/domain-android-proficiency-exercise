package com.mbarlow.domainbondiproperties.data;

import com.mbarlow.domainbondiproperties.model.Listing;

import java.util.List;

import rx.Observable;

/**
 * Created by michael on 31/08/16.
 */
public class ListingRepositoryImpl implements ListingRepository{

    public ListingRepositoryImpl(/* Pass in services for getting data from sources */){
        // REST service (retrofit)
        // Data service (Realm?)
    }

    @Override
    public Observable<List<Listing>> searchListings() {
        // Retrieve from db
        // Get from web to update
        return null;
    }
}
