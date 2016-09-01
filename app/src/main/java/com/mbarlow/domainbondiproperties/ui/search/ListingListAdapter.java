package com.mbarlow.domainbondiproperties.ui.search;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbarlow.domainbondiproperties.R;
import com.mbarlow.domainbondiproperties.event.ListingItemSelectedEvent;
import com.mbarlow.domainbondiproperties.model.Listing;
import com.mbarlow.domainbondiproperties.ui.search.ListingListAdapter.ListingViewHolder;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by michael on 1/09/16.
 */
public class ListingListAdapter extends RecyclerView.Adapter<ListingViewHolder>{
    public final int VIEW_TYPE_NORMAL = 0;
    public final int VIEW_TYPE_ELITE = 1;

    private List<Listing> listings;

    public static class ListingViewHolder extends RecyclerView.ViewHolder implements OnClickListener{

        public long listingAdId;

        @BindView(R.id.retinaDisplayImage) public ImageView retinaDisplayImage;
        @Nullable
        @BindView(R.id.secondRetinaDisplayImage) public ImageView secondRetinaDisplayImage;
        @BindView(R.id.displayPrice) public TextView displayPrice;
        @BindView(R.id.bedBathCar) public TextView bedBathCar;
        @BindView(R.id.address) public TextView address;
        @BindView(R.id.agencyLogo) public ImageView agencyLogo;

        public ListingViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            EventBus.getDefault().post(new ListingItemSelectedEvent(listingAdId));
        }
    }

    public ListingListAdapter(List<Listing> listings) {
        this.listings = listings;
    }

    public void setListings(List<Listing> listings){
        this.listings = listings;
    }

    @Override
    public int getItemViewType(int position) {
        Listing listing = listings.get(position);
        return listing.isElite() ? VIEW_TYPE_ELITE : VIEW_TYPE_NORMAL;
    }

    @Override
    public ListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (VIEW_TYPE_NORMAL == viewType){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_normal, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_elite, parent, false);
        }
        ListingViewHolder listingViewHolder = new ListingViewHolder(view);
        return listingViewHolder;
    }

    @Override
    public void onBindViewHolder(ListingViewHolder holder, int position) {
        // Replace contents of viewholder
        Listing listing = listings.get(position);

        Picasso picasso = Picasso.with(holder.retinaDisplayImage.getContext());
        picasso.load(listing.getRetinaDisplayThumbUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.retinaDisplayImage);

        if (holder.secondRetinaDisplayImage != null) {
            String secondRetinaDisplayThumbUrl = listing.getSecondRetinaDisplayThumbUrl();
            if (secondRetinaDisplayThumbUrl != null && !secondRetinaDisplayThumbUrl.isEmpty()){
                picasso.load(secondRetinaDisplayThumbUrl)
                        .placeholder(R.drawable.placeholder)
                        .into(holder.secondRetinaDisplayImage);
            }else{
                holder.secondRetinaDisplayImage.setImageBitmap(null);
            }
        }

        holder.displayPrice.setText(listing.getDisplayPrice());

        String bedBathCarText = holder.bedBathCar.getContext()
                .getString(R.string.listing_features_template, listing.getBedrooms(), listing.getBathrooms(), listing.getCarspaces());
        holder.bedBathCar.setText(bedBathCarText);

        holder.address.setText(listing.getDisplayableAddress());

        holder.listingAdId = listing.getAdId();

        picasso.load(listing.getAgencyLogoUrl()).into(holder.agencyLogo);
    }

    @Override
    public int getItemCount() {
        return listings.size();
    }
}
