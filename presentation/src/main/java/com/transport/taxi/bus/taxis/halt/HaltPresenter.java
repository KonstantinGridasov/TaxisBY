package com.transport.taxi.bus.taxis.halt;

import android.util.Log;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.usecase.GetOnIdDb;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 02.01.2018.
 */

public class HaltPresenter {
    AdapterHalt adapterHalt = new AdapterHalt();
    @Inject
    GetOnIdDb getOnIdDb;


    public HaltPresenter() {
        TaxisBY.appComponent.inject(this);

    }

    public void getOnId(String s) {
        Log.e("HaltPresenter", s);
        getOnIdDb.execute(s, new DisposableObserver<TaxisDomain>() {
            @Override
            public void onNext(TaxisDomain taxisDomain) {
                Log.e("onNext", "s=" + taxisDomain.getName());
                adapterHalt.setItemsTaxisHalt(returnHalt(taxisDomain.getDirect_direction()));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        });
    }


    public List<String> returnHalt(String s) { //Разборка всей строки из остановок на одельные строки
        Log.e("returnHalt", s);
        int n = 0;
        List<String> halt = new ArrayList<>();
        for (int i = n; i < s.length(); i++) {
            char x = s.charAt(i);
            if (',' == x) {
//                System.out.println(s.substring(n, i));
                halt.add(s.substring(n, i));
                n = i + 1;
            }
        }
        return halt;
    }
}
