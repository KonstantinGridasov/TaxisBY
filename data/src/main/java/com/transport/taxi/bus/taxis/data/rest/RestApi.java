package com.transport.taxi.bus.taxis.data.rest;


import com.transport.taxi.bus.taxis.data.base.Halt;
import com.transport.taxi.bus.taxis.data.base.ListTaxisData;
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
    Observable<Void> createTaxis(@Body ListTaxisData list);


    @GET("data/taxisby")
    Observable<List<TaxisData>> getProfilesOnId();

}
