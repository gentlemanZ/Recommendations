package com.treehouse.android.samples.googleplayservices.api;


import com.treehouse.android.samples.googleplayservices.model.ActiveListings;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by tiany on 2017/8/14.
 */

public class Etsy {

    private static final String API_KEY = "oc1cw6w18oxibm5h1m267u0c";

    private static RequestInterceptor getInterceptor(){
        return  new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addEncodedQueryParam("api_key", API_KEY);
            }
        };
    }
    private static Api getApi(){
        return new RestAdapter.Builder()
                .setEndpoint("https://openapi.etsy.com/v2")
                .setRequestInterceptor(getInterceptor())
                .build()
                .create(Api.class);
    }

    public static void getAcitiveListings(Callback<ActiveListings> callback){
        getApi().activeListings("Images,Shop", callback);
    }
}
