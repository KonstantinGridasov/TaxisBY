package com.transport.taxi.bus.taxis.data.rest;

import com.transport.taxi.bus.taxis.data.base.TaxisData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by GHome on 16.08.2017.
 */

public class RestService {


    private RestApi restApi;

    public RestService(RestApi restApi) {
        this.restApi = restApi;
    }

    public Observable<Void> ubdateToNet(List<TaxisData> list) {

//        Log.e("RestService", "ubdateToNet- " + product.get(1).getId());
        return restApi.createTaxis(list.get(1));
    }

    public Observable<List<TaxisData>> getNameFromNet() {
        return restApi.getProfilesOnId();
    }

    public Observable<VersionUbdate> getVersionUbdate() {
        return restApi.getVersionUbdate();
    }


}

