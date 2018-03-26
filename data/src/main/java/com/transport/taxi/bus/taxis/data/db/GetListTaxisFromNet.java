package com.transport.taxi.bus.taxis.data.db;

import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.rest.RestService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 19.12.2017.
 */

public class GetListTaxisFromNet {
    @Inject
    RestService restService;

    public GetListTaxisFromNet(RestService restService) {
        this.restService = restService;
    }

    //Выгрузка всей базы маршруток из интернета
    public Observable<List<TaxisData>> getList() {
        return restService.getNameFromNet();

    }
}
