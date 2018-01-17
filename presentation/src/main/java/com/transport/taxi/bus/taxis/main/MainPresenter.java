package com.transport.taxi.bus.taxis.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.usecase.FillDb;
import com.transport.taxi.bus.taxis.domain.usecase.GetListDb;
import com.transport.taxi.bus.taxis.domain.usecase.RemoveALLDb;
import com.transport.taxi.bus.taxis.search.SearchActivity;
//import com.transport.taxi.bus.taxis.searchOnDb.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 24.12.2017.
 */

public class MainPresenter {
    public static final String KEY_SEARCH = "com.transport.taxi.bus.taxis.main";

    @Inject
    Context context;
    @Inject
    RemoveALLDb removeALLDb;
    @Inject
    FillDb fillDb;
    @Inject
    GetListDb getListDb;

    private MainView mainView;
    private List<TaxisDomain> taxisDomainsRes;

    @Inject
    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        TaxisBY.appComponent.inject(this);
    }


    public void onGetListClick() {
        taxisDomainsRes = new ArrayList<>();
        getListDb.execute(null, new DisposableObserver<List<TaxisDomain>>() {
            @Override
            public void onNext(List<TaxisDomain> taxisDomains) {
                taxisDomainsRes = taxisDomains;


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                mainView.goToMain(taxisDomainsRes);

            }
        });
    }

    public void onFillDataBaseClick() {
        fillDb.execute(null, new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                Log.e("MainAc", "TRUE");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }
        });
    }

    public void onClickSearch(String s) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(KEY_SEARCH, s);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void onRemoveAllDb() {
        removeALLDb.execute(null, new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {

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

