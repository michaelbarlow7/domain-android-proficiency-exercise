package com.mbarlow.domainbondiproperties.ui.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mbarlow.domainbondiproperties.R;

public class SearchActivity extends AppCompatActivity implements SearchContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // TODO: Create and store presenter as field
        // TODO: Set self in presenter as view
    }

    @Override
    public void showSearchResults() {
        //TODO:
    }

    @Override
    public void showError(String message) {
        //TODO:
    }

    @Override
    public void showLoading() {
        //TODO:
    }

    @Override
    public void hideLoading() {
        //TODO:
    }
}
