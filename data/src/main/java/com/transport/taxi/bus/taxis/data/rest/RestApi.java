package com.transport.taxi.bus.taxis.data.rest;


import com.transport.taxi.bus.taxis.data.base.TaxisData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by GHome on 16.08.2017.
 */

public interface RestApi {
    @POST("data/listtaxis")
    Observable<Void> createTaxis(@Body TaxisData taxisData);


    @GET("data/taxisby")
    Observable<List<TaxisData>> getProfilesOnId();

    @GET("data/TaxisBy/03CE08B1-7BA1-139C-FF0A-31C21F18BB00?props=versionTaxis")
    Observable<VersionUbdate> getVersionUbdate();

}
