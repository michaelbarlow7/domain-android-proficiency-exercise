package com.mbarlow.domainbondiproperties.ui.search;

import com.mbarlow.domainbondiproperties.ui.base.MvpPresenter;
import com.mbarlow.domainbondiproperties.ui.base.MvpView;

/**
 * Created by michael on 31/08/16.
 */
interface SearchContract {

    interface View extends MvpView {

        void showSearchResults(/* List<Result> searchResults */);

        void showError(String message);

        void showLoading();

        void hideLoading();
    }

    interface Presenter extends MvpPresenter<View> {
        void search();
    }
}
