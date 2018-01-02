package com.transport.taxi.bus.taxis.halt;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.usecase.GetTaxisOnId;

import io.reactivex.observers.DisposableObserver;

import static com.transport.taxi.bus.taxis.main.AdapterMain.KEY_INFO;

/**
 * Created by GHome on 02.01.2018.
 */

public class HaltPresenter {
    Context context;
    private GetTaxisOnId getTaxisOnId;


    public HaltPresenter(Context context) {
        this.context = context;
    }

    public void getOnId() {
        String s;
        Intent intent = new Intent();
        s = intent.getStringExtra(KEY_INFO);

        Log.e("MainPresenter", s);
        getTaxisOnId = new GetTaxisOnId(context);
        getTaxisOnId.execute(s, new DisposableObserver<TaxisDomain>() {
            @Override
            public void onNext(TaxisDomain taxisDomain) {

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
