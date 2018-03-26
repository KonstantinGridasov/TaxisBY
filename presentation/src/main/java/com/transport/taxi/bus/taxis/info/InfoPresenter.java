package com.transport.taxi.bus.taxis.info;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetTaxisOnHaltDomain;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 16.01.2018.
 */

public class InfoPresenter {
    @Inject
    GetTaxisOnHaltDomain getTaxisOnHaltOnDb;

    private InfoView infoView;
    private String interval;
    private String workingTime;
    private String inWeek;

    InfoPresenter(InfoView view) {
        this.infoView = view;
        TaxisBY.appComponent.inject(this);

    }

    void getHalt(String s) {
        getTaxisOnHaltOnDb.execute(s, new DisposableObserver<TaxisDomain>() {
            @Override
            public void onNext(TaxisDomain taxisDomain) {
                interval = taxisDomain.getInterval();
                workingTime = taxisDomain.getWorkingTime();
                inWeek = taxisDomain.getInWeek();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                infoView.nameToInfo(interval, workingTime, inWeek);
            }
        });

    }


    void onDestroy() {
        interval = null;
        workingTime = null;
        inWeek = null;
    }


}
