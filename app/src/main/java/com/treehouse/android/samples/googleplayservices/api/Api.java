package com.treehouse.android.samples.googleplayservices.api;

import com.treehouse.android.samples.googleplayservices.model.ActiveListings;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by tiany on 2017/8/14.
 */

public interface Api {
    @GET("/listings/active")
    void activeListings(@Query("includes") String includes,
                        Callback<ActiveListings> callback);
}
