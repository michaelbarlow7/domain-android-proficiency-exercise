package com.mbarlow.domainbondiproperties.ui.listings;

import com.mbarlow.domainbondiproperties.model.Listing;
import com.mbarlow.domainbondiproperties.ui.base.MvpPresenter;
import com.mbarlow.domainbondiproperties.ui.base.MvpView;

import java.util.List;

/**
 * Created by michael on 31/08/16.
 *
 * This class houses the view and presenter interfaces used in the Listings screen.
 *
 */
interface ListingsContract {

    interface View extends MvpView {

        void showListings(List<Listing> listResults);

        void showError(String message);

        void showLoading();

        void hideLoading();

        void showListing(long listingId);
    }

    interface Presenter extends MvpPresenter<View> {
        void getListings();

        void refreshListings();
    }
}
