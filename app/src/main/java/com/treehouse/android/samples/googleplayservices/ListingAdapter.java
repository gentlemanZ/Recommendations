package com.treehouse.android.samples.googleplayservices;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.treehouse.android.samples.googleplayservices.api.Etsy;
import com.treehouse.android.samples.googleplayservices.google.GoogleServicesHelper;
import com.treehouse.android.samples.googleplayservices.model.ActiveListings;
import com.treehouse.android.samples.googleplayservices.model.Listing;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by tiany on 2017/8/14.
 */

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ListingHolder>
implements Callback<ActiveListings>, GoogleServicesHelper.GoogleServicesListener{

    private MainActivity activity;
    private LayoutInflater inflater;
    private ActiveListings activeListings;

    private boolean isGooglePlayServicesAvailable;

    public ListingAdapter(MainActivity activity){
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
        this.isGooglePlayServicesAvailable = false;
    }

    @Override
    public ListingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListingHolder(inflater.inflate(R.layout.layout_listing, parent, false));
    }

    @Override
    public void onBindViewHolder(ListingHolder holder, int position) {
        final Listing listing = activeListings.results[position];
        holder.titleView.setText(listing.title);
        holder.priceView.setText(listing.price);
        holder.shopeNameView.setText(listing.Shop.shop_name);

        if (isGooglePlayServicesAvailable){

        }else{

        }

        Picasso.with(holder.imageView.getContext())
                .load(listing.Images[0].url_570xN)
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        if (activeListings == null)
            return 0;

        if (activeListings.results == null)
            return 0;

        return activeListings.results.length;
    }

    @Override
    public void success(ActiveListings activeListings, Response response) {
        this.activeListings = activeListings;
        notifyDataSetChanged();
        this.activity.showList();
    }

    @Override
    public void failure(RetrofitError error) {
        this.activity.showError();
    }

    public ActiveListings getActiveListings(){
        return  activeListings;
    }

    @Override
    public void onConnected() {

        if (getItemCount() ==0){
            Etsy.getAcitiveListings(this);
        }
        isGooglePlayServicesAvailable = true;
        notifyDataSetChanged();

    }

    @Override
    public void onDisconnected() {
        if (getItemCount() ==0){
            Etsy.getAcitiveListings(this);
        }
        isGooglePlayServicesAvailable = false;
        notifyDataSetChanged();

    }

    public class ListingHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView titleView;
        public TextView shopeNameView;
        public TextView priceView;



        public ListingHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.listing_image);
            titleView = (TextView) itemView.findViewById(R.id.listing_title);
            shopeNameView = (TextView) itemView.findViewById(R.id.listing_shop_name);
            priceView = (TextView) itemView.findViewById(R.id.listing_price);
        }
    }
}
