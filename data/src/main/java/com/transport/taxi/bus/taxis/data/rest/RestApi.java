package com.transport.taxi.bus.taxis.data.rest;


import com.transport.taxi.bus.taxis.data.base.VersionUbdate;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by GHome on 16.08.2017.
 */

public interface RestApi {
    @GET("data/TaxisVersion/D1C996AA-AF2F-0693-FF5D-82B58DC44B00?props=versionTaxis")
    Observable<VersionUbdate> getVersionUbdate();

}
