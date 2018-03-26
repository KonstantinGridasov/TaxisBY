package com.transport.taxi.bus.taxis.rest;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetListTaxisNet;
import com.transport.taxi.bus.taxis.domain.entity.usecase.UbdateListToBackendlessDomain;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 25.03.2018.
 */

public class RestPresenter {
    @Inject
    UbdateListToBackendlessDomain ubdate;

    @Inject
    GetListTaxisNet getList;

    private String name;
    private RestView restView;

    public RestPresenter(RestView restView) {
        TaxisBY.appComponent.inject(this);
        this.restView = restView;
    }

    void ubdateToRest() {
        ubdate.execute(null, new DisposableObserver<Void>() {
            @Override
            public void onNext(Void aVoid) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    void getListNet() {
        getList.execute(null, new DisposableObserver<List<TaxisDomain>>() {
            @Override
            public void onNext(List<TaxisDomain> taxisDomains) {
                name = taxisDomains.get(0).getId();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                restView.gotoMainRest(name);
            }
        });
    }
}
