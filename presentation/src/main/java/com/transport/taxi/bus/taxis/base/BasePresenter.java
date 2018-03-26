package com.transport.taxi.bus.taxis.base;

import android.content.Context;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.entity.usecase.DeleteDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.UbdateListToBackendlessDomain;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 13.02.2018.
 */

public class BasePresenter {

    @Inject
    Context context;

    @Inject
    DeleteDomain deleteDomain;



    BasePresenter() {
        TaxisBY.appComponent.inject(this);
    }


    void onRemoveAllDb() {
        deleteDomain.execute(null, new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        });
    }


}
