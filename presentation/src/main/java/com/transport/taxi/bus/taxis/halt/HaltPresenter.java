package com.transport.taxi.bus.taxis.halt;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.base.HaltDomain;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.usecase.GetHaltOnDb;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 02.01.2018.
 */

public class HaltPresenter {
    @Inject
    GetHaltOnDb getHaltOnDb;

    private HaltView haltView;
    private List<HaltDomain> taxisDomainsR;
    private String interval;

    public HaltPresenter(HaltView view) {
        this.haltView = view;
        TaxisBY.appComponent.inject(this);

    }

    public void getHalt(String s, final Boolean direct) {
        getHaltOnDb.execute(s, new DisposableObserver<TaxisDomain>() {
            @Override
            public void onNext(TaxisDomain taxisDomain) {
                if (direct) {
                    taxisDomainsR = taxisDomain.getDirectHalt();
                    interval = taxisDomain.getDirectName();
                } else {
                    taxisDomainsR = taxisDomain.getReverseHalt();
                    interval = taxisDomain.getReverseName();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
//                Integer n = taxisDomainsR.size();
//                Log.e("onComplete", n.toString());
                haltView.nameToHalt(returnHalt(taxisDomainsR), interval);
            }
        });

    }


    private List<String> returnHalt(List<HaltDomain> haltDomains) {
        List<String> halt = new ArrayList<>();
        for (int i = 0; i < haltDomains.size(); i++) {
            halt.add(haltDomains.get(i).getHaltName());
        }
        return halt;
    }
}
