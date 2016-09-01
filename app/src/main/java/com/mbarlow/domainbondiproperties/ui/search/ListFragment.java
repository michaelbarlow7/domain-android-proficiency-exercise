package com.mbarlow.domainbondiproperties.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mbarlow.domainbondiproperties.R;
import com.mbarlow.domainbondiproperties.model.Listing;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by michael on 31/08/16.
 */
public class ListFragment extends Fragment {

    private List<Listing> listings;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ListingListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;

        if (adapter == null){
            adapter = new ListingListAdapter(listings);
        }else{
            adapter.setListings(listings);
            adapter.notifyDataSetChanged();
        }

        recyclerView.setAdapter(adapter);
    }
}
