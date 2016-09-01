package com.mbarlow.domainbondiproperties.ui.search;

import com.mbarlow.domainbondiproperties.model.Listing;
import com.mbarlow.domainbondiproperties.ui.base.MvpPresenter;
import com.mbarlow.domainbondiproperties.ui.base.MvpView;

import java.util.List;

/**
 * Created by michael on 31/08/16.
 */
interface SearchContract {

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
