package com.mbarlow.domainbondiproperties.ui.listing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbarlow.domainbondiproperties.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The fragment that shows the selected Listing Ad-Id.
 * Shown in ListingsActivity and ListingActivity
 *
 * Created by michael on 31/08/16.
 */
public class ListingFragment extends Fragment {

    public static final String LISTING_AD_ID_ARG = "LISTING_AD_ID_ARG";

    private long listingAdId;

    @BindView(R.id.listingAdIdView)
    TextView listingAdIdView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listing, container, false);
        ButterKnife.bind(this, view);

        // Restores data from bundle if fragment is being recreated
        if (savedInstanceState != null){
            listingAdId = savedInstanceState.getLong(LISTING_AD_ID_ARG, -1L);

            if (listingAdId != -1){
                listingAdIdView.setText(String.valueOf(listingAdId));
            }
        }

        return view;
    }

    /**
     * Sets the Listing's ad ID in memory and in the bundle
     */
    public void setListingAdId(long listingAdId){
        this.listingAdId = listingAdId;

        if (this.listingAdId != -1){
            listingAdIdView.setText(String.valueOf(listingAdId));
        }
    }

    /**
     * Stores data when screen is rotated or fragment is killed for some reason
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong(LISTING_AD_ID_ARG, listingAdId);
    }
}
