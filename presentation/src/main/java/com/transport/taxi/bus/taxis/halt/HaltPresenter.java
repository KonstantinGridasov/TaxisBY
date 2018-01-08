package com.transport.taxi.bus.taxis.halt;

import android.util.Log;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.usecase.GetHaltOnDb;

import java.nio.file.DirectoryIteratorException;
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
    private String taxisDomainsR;

    public HaltPresenter(HaltView view) {
        this.haltView = view;
        TaxisBY.appComponent.inject(this);

    }

    public void getHalt(String s, final Boolean direct) {
        getHaltOnDb.execute(s, new DisposableObserver<TaxisDomain>() {
            @Override
            public void onNext(TaxisDomain taxisDomain) {
                if (direct)
                    taxisDomainsR = taxisDomain.getDirect_direction();
                else {
                    taxisDomainsR = taxisDomain.getReverse_direction();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                haltView.nameToHalt(returnHalt(taxisDomainsR));
            }
        });

    }


    public List<String> returnHalt(String s) { //Разборка всей строки из остановок на одельные строки
        int n = 0;
        List<String> halt = new ArrayList<>();
        for (int i = n; i < s.length(); i++) {
            char x = s.charAt(i);
            if (',' == x) {
                halt.add(s.substring(n, i));
                n = i + 1;
            }
        }
        return halt;
    }
}
