package com.mbarlow.domainbondiproperties.data.local;

import com.mbarlow.domainbondiproperties.model.Listing;

import java.util.List;

/**
 * Created by michael on 2/09/16.
 *
 */
public interface DatabaseHelper {

     void replaceListings(List<Listing> listings);

     List<Listing> getAllListings();
}
