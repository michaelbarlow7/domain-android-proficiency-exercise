package com.mbarlow.domainbondiproperties.data.local;

import com.mbarlow.domainbondiproperties.model.Listing;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by michael on 2/09/16.
 */
public class DatabaseHelperImpl implements DatabaseHelper{
    @Override
    public void replaceListings(List<Listing> listings) {
        SugarRecord.deleteAll(Listing.class);
        SugarRecord.saveInTx(listings);
    }

    @Override
    public List<Listing> getAllListings() {
        return SugarRecord.listAll(Listing.class);
    }
}
