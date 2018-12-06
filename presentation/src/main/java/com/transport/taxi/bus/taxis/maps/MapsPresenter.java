package com.transport.taxi.bus.taxis.maps;

import android.util.Log;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetListIdDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetTaxisOnIdDomain;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class MapsPresenter {

    @Inject
    GetTaxisOnIdDomain getTaxisOnIdDomain;

    @Inject
    GetListIdDomain getListIdDomain;

    private MapsView view;

    private TaxisDomain taxis;

    private List<String> listResult = new ArrayList<>();

    MapsPresenter(MapsView view) {
        this.view = view;
        TaxisBY.appComponent.inject(this);

    }

    void getTaxis(String id) {
        getTaxisOnIdDomain.execute(id, new DisposableObserver<TaxisDomain>() {
            @Override
            public void onNext(TaxisDomain taxisDomain) {
                taxis = taxisDomain;
                Log.e("getTaxis",taxis.getId());

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                view.onReadyTaxis(taxis);
            }
        });

    }

    List<String> getListId() {
        getListIdDomain.execute(null, new DisposableObserver<List<String>>() {
            @Override
            public void onNext(List<String> strings) {
                listResult.addAll(strings);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        });
        return listResult;
    }

    void onDestroy() {
        view = null;
        taxis = null;
        listResult = null;
    }
}
