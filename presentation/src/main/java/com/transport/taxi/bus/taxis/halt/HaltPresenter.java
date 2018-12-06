package com.transport.taxi.bus.taxis.halt;

import android.content.Context;
import android.content.Intent;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.entity.base.HaltDomain;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetTaxisOnIdDomain;
import com.transport.taxi.bus.taxis.info.InfoActivity;
import com.transport.taxi.bus.taxis.maps.MapsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

import static com.transport.taxi.bus.taxis.main.MainAdapter.KEY_ID;

/**
 * Created by GHome on 02.01.2018.
 */

public class HaltPresenter {
    @Inject
    GetTaxisOnIdDomain getTaxisOnHaltOnDb;

    private HaltView haltView;
    private List<HaltDomain> taxisDomainsR;
    private String directName;

    HaltPresenter(HaltView view) {
        this.haltView = view;
        TaxisBY.appComponent.inject(this);

    }

    void getHalt(String s, final Boolean direct) {
        getTaxisOnHaltOnDb.execute(s, new DisposableObserver<TaxisDomain>() {
            @Override
            public void onNext(TaxisDomain taxisDomain) {
                if (direct) {
                    taxisDomainsR = taxisDomain.getDirectHalt();
                    directName = taxisDomain.getDirectName();
                } else {
                    taxisDomainsR = taxisDomain.getReverseHalt();
                    directName = taxisDomain.getReverseName();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                haltView.nameToHalt(returnHalt(taxisDomainsR), directName);
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

    void getTaxisInfo(Context context, String stringExtra) {
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra(KEY_ID, stringExtra);
        context.startActivity(intent);
    }

    void getMaps(Context context, String stringExtra) {
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra(KEY_ID, stringExtra);
        context.startActivity(intent);
    }

    void onDestroy() {
        taxisDomainsR = null;
        directName = null;
        haltView = null;
    }
}
