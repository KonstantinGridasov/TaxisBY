package com.transport.taxi.bus.taxis.resultsID;

import android.util.Log;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.usecase.SearchHaltOnDb;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 24.12.2017.
 */

public class ResultPresenter {
    AdapterResult adapterResult = new AdapterResult();

    @Inject
    SearchHaltOnDb searchHaltOnDb;

    public ResultPresenter() {
        TaxisBY.appComponent.inject(this);
//        Integer n=strings.size();
//        Log.e("onResultsNext",n.toString());

    }

    public void onSearchInDb(String halt) {
        String res = obrez(halt);
        searchHaltOnDb.execute(res, new DisposableObserver<List<TaxisDomain>>() {
            @Override
            public void onNext(List<TaxisDomain> taxisDomains) {
                adapterResult.setItemsTaxis(taxisDomains);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private String obrez(String s) {
        String k;
        int nach = 0;
        int kon = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            if (' ' != s.charAt(i)) {
                nach = i;
                break;
            }
        }
        for (int i = s.length() - 1; i > 0; i--) {
            if (' ' != s.charAt(i)) {
                kon = i + 1;
                break;
            }
        }
        System.out.println("s=" + s);
        System.out.println("kon=" + kon);
        System.out.println("nach=" + nach);
        System.out.println("dlin=" + s.length());

        k = s.substring(nach, kon);
        Log.e("obrez", k);
        return k;


    }
}

