package com.mbarlow.domainbondiproperties.ui.listings;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mbarlow.domainbondiproperties.R;
import com.mbarlow.domainbondiproperties.injection.Injection;
import com.mbarlow.domainbondiproperties.model.Listing;
import com.mbarlow.domainbondiproperties.ui.listing.ListingActivity;
import com.mbarlow.domainbondiproperties.ui.listing.ListingFragment;

import java.util.List;

import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Activity for showing the listings.
 * When the screen's width is >= 600dp, this shows the listings fragment and the listing fragment in
 * a two-pane layout. Otherwise it shows just the listings fragment.
 */
public class ListingsActivity extends AppCompatActivity implements ListingsContract.View {

    private ListingsContract.Presenter searchPresenter;

    ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        searchPresenter = new ListingsPresenter(Injection.provideListingRepository(), Schedulers.io(), AndroidSchedulers.mainThread(), Injection.provideEventBusProvider());
        searchPresenter.attachView(this);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);

        if (listFragment.getListings() == null) {
            searchPresenter.refreshListings();
        }

        searchPresenter.getListings();
    }

    @Override
    public void showListings(List<Listing> listingResults) {
        listFragment.setListings(listingResults);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, "Error getting listings: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        listFragment.showLoading();
    }

    @Override
    public void hideLoading() {
        listFragment.hideLoading();
    }

    /**
     * Called when a Listing is selected by the user.
     * It updates the Listing Fragment if it exists and is currently been showing (when screen width is >= 600dp).
     * Otherwise it starts the Listing Activity housing just the Listing fragment.
     * @param listingId The selected Listing's adId
     */
    @Override
    public void showListing(long listingId) {
        Fragment listingFragment = getSupportFragmentManager().findFragmentById(R.id.listingFragment);
        if (listingFragment != null && listingFragment.isInLayout()) {
            ((ListingFragment) listingFragment).setListingAdId(listingId);
        }else{
            Intent intent = new Intent(this, ListingActivity.class);
            intent.putExtra(ListingActivity.LISTING_AD_ID_EXTRA, listingId);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    // This should probably be moved to a base class.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchPresenter.detachView();
    }
}
