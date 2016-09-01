package com.mbarlow.domainbondiproperties.event;

/**
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
