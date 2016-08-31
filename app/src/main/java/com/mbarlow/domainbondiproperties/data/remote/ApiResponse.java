package com.mbarlow.domainbondiproperties.data.remote;

import com.mbarlow.domainbondiproperties.model.Listing;

import java.util.List;

/**
 * Created by michael on 31/08/16.
 */
public class ApiResponse {

    private ListingResults listingResults;

    public ListingResults getListingResults() {
        return listingResults;
    }

    public void setListingResults(ListingResults listingResults) {
        this.listingResults = listingResults;
    }

    public class ListingResults {
        private List<Listing> listings;

        public List<Listing> getListings() {
            return listings;
        }

        public void setListings(List<Listing> listings) {
            this.listings = listings;
        }
    }

}
