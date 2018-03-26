package com.transport.taxi.bus.taxis.data.rest;

import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.ListTaxisData;
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

    public Observable<Void> ubdateToNet(ListTaxisData list) {
//        Log.e("RestService", "ubdateToNet- " + product.get(1).getId());
        return restApi.createTaxis(list);
    }

    public Observable<List<TaxisData>> getNameFromNet() {
        return restApi.getProfilesOnId();
    }


}

