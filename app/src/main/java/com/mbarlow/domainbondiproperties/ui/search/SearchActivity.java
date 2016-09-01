package com.mbarlow.domainbondiproperties.ui.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mbarlow.domainbondiproperties.R;
import com.mbarlow.domainbondiproperties.injection.Injection;
import com.mbarlow.domainbondiproperties.model.Listing;
import com.mbarlow.domainbondiproperties.ui.listing.ListingActivity;

import java.util.List;

import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity implements SearchContract.View{

    private SearchContract.Presenter searchPresenter;

    ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        searchPresenter = new SearchPresenter(Injection.provideListingRepository(), Schedulers.io(), AndroidSchedulers.mainThread());
        searchPresenter.attachView(this);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);

        if (listFragment.getListings() == null){
            searchPresenter.refreshListings();
        }

        searchPresenter.getListings();
    }

    @Override
    public void showListings(List<Listing> listingResults){
        listFragment.setListings(listingResults);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, "Error getting listings: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        // Probably should delegate to fragment with list
//        text.setText("Loading...");
    }

    @Override
    public void hideLoading() {
        // Probably should delegate to fragment with list
//        text.setText("Done loading...");
    }

    @Override
    public void showListing(long listingId) {
        // If listing fragment is available, set its listing id
        Intent intent = new Intent(this, ListingActivity.class);
        intent.putExtra(ListingActivity.LISTING_AD_ID_EXTRA, listingId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        // Else, show new activity
    }
}
