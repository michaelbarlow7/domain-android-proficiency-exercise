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

public class ListingsActivity extends AppCompatActivity implements ListingsContract.View {

    private ListingsContract.Presenter searchPresenter;

    ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        searchPresenter = new ListingsPresenter(Injection.provideListingRepository(), Schedulers.io(), AndroidSchedulers.mainThread());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchPresenter.detachView();
    }
}
