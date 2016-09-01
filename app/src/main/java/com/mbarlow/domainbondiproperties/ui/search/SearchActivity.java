package com.mbarlow.domainbondiproperties.ui.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mbarlow.domainbondiproperties.R;
import com.mbarlow.domainbondiproperties.injection.Injection;
import com.mbarlow.domainbondiproperties.model.Listing;

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
        //TODO: Toast?
//        text.setText("Error: " + message);
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
}
