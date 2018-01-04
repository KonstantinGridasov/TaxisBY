package com.transport.taxi.bus.taxis.resultsID;

import android.content.Intent;
import android.util.Log;

import com.transport.taxi.bus.taxis.TaxisBY;
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

    }

    public void onSearchInDb(String halt) {
        searchHaltOnDb.execute(halt, new DisposableObserver<List<String>>() {
            @Override
            public void onNext(List<String> strings) {
                Integer n=strings.size();
                Log.e("onResultsNext",n.toString());
                adapterResult.setItemsTaxis(strings);
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

