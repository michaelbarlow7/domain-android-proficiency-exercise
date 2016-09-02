package com.mbarlow.domainbondiproperties.event;

/**
 * An event signifying that a Listing has been selected. Contains the Listing's Ad ID
 *
 * Created by michael on 1/09/16.
 */
public class ListingItemSelectedEvent {

    private final long selectedAdId;

    public ListingItemSelectedEvent(long selectedAdId) {
        this.selectedAdId = selectedAdId;
    }

    public long getSelectedAdId() {
        return selectedAdId;
    }
}
