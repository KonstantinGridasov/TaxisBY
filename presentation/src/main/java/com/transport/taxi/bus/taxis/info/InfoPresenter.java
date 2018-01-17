package com.transport.taxi.bus.taxis.info;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.usecase.GetHaltOnDb;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 16.01.2018.
 */

public class InfoPresenter {
    @Inject
    GetHaltOnDb getHaltOnDb;

    private InfoView infoView;
    private String interval;
    private String workingTime;

    public InfoPresenter(InfoView view) {
        this.infoView = view;
        TaxisBY.appComponent.inject(this);

    }

    public void getHalt(String s) {
        getHaltOnDb.execute(s, new DisposableObserver<TaxisDomain>() {
            @Override
            public void onNext(TaxisDomain taxisDomain) {
                interval = taxisDomain.getInterval();
                workingTime = taxisDomain.getWorkingTime();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
//                Integer n = taxisDomainsR.size();
//                Log.e("onComplete", n.toString());
//                infoView.nameToHalt(returnHalt(taxisDomainsR), interval);
                infoView.nameToInfo(interval, workingTime);
            }
        });

    }


//    private List<String> returnHalt(List<HaltDomain> haltDomains) {
//        List<String> halt = new ArrayList<>();
//        for (int i = 0; i < haltDomains.size(); i++) {
//            halt.add(haltDomains.get(i).getHaltName());
//        }
//        return halt;
//    }
}
