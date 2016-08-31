package com.mbarlow.domainbondiproperties.ui.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mbarlow.domainbondiproperties.R;
import com.mbarlow.domainbondiproperties.injection.Injection;
import com.mbarlow.domainbondiproperties.model.Listing;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity implements SearchContract.View{

    // Temporary
    @BindView(R.id.text)
    TextView text;

    private SearchContract.Presenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        searchPresenter = new SearchPresenter(Injection.provideListingRepository(), Schedulers.io(), AndroidSchedulers.mainThread());
        searchPresenter.attachView(this);

        searchPresenter.refreshListings();
        searchPresenter.getListings();
    }

    @Override
    public void showListings(List<Listing> listingResults){
        //TODO:
        text.setText("Show search results here");
    }

    @Override
    public void showError(String message) {
        text.setText("Error: " + message);
    }

    @Override
    public void showLoading() {
        text.setText("Loading...");
    }

    @Override
    public void hideLoading() {
        text.setText("Done loading...");
    }
}
