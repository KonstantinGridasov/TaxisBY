package com.transport.taxi.bus.taxis.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.usecase.FillDataBase;
import com.transport.taxi.bus.taxis.domain.usecase.GetDataBase;
import com.transport.taxi.bus.taxis.domain.usecase.GetTaxisOnId;
import com.transport.taxi.bus.taxis.halt.HaltActivity;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 24.12.2017.
 */

public class MainPresenter {
    AdapterMain adapterMain = new AdapterMain();
    private Context context;
    private MainView mainView;
    private FillDataBase fillDataBase;
    private GetDataBase getDataBase;

    public MainPresenter(Context context) {
        this.context = context;
    }


    public void onGetListClick() {
        getDataBase = new GetDataBase(context);
        getDataBase.execute(null, new DisposableObserver<List<TaxisDomain>>() {
            @Override
            public void onNext(List<TaxisDomain> taxisDomains) {
                adapterMain.setItemsTaxis(taxisDomains);


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e("onCom", "true");
            }
        });
    }

    public void onFillDataBaseClick() {
//        mainView.showProgress();
        fillDataBase = new FillDataBase(context);
        fillDataBase.execute(null, new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                Log.e("MainAc", "TRUE");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
//                mainView.dismissProgress();
            }
        });
    }



}

