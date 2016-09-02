package com.mbarlow.domainbondiproperties.ui.listing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mbarlow.domainbondiproperties.R;

/**
 * The activity for displaying a Listing. Shown when the screen width is < 600dp.
 *
 * Created by michael on 1/09/16.
 */
public class ListingActivity extends AppCompatActivity {

    public static final String LISTING_AD_ID_EXTRA = "LISTING_AD_ID_EXTRA";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        long listingAdId = getIntent().getLongExtra(LISTING_AD_ID_EXTRA, -1L);

        ListingFragment listingFragment = (ListingFragment) getSupportFragmentManager().findFragmentById(R.id.listingFragment);

        listingFragment.setListingAdId(listingAdId);
    }

    /**
     * The up arrow just finishes the activity. Prevents loading of new data.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
