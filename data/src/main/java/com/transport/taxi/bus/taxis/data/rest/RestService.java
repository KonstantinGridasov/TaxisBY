package com.transport.taxi.bus.taxis.data.rest;

import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.base.VersionUbdate;

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

    public Observable<VersionUbdate> getVersionUbdate() {
        return restApi.getVersionUbdate();
    }


}

