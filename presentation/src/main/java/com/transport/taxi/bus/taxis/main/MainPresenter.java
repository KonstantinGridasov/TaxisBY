package com.transport.taxi.bus.taxis.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetListHintDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetListTaxisDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetUbdateFromRestDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetVersionUbdateDomain;
import com.transport.taxi.bus.taxis.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

//import com.transport.taxi.bus.taxis.searchOnDb.SearchActivity;

/**
 * Created by GHome on 24.12.2017.
 */

public class MainPresenter {
    public static final String KEY_SEARCH = "com.transport.taxi.bus.taxis.main";
    @Inject
    Context context;
    @Inject
    GetListTaxisDomain getListTaxisDomain;
    @Inject
    GetListHintDomain getListHintDomain;
    @Inject
    GetVersionUbdateDomain getVersionUbdateDomain;
    @Inject
    GetUbdateFromRestDomain getUbdateFromRestDomain;

    private Boolean ubdate;
    private List<String> hintHalts;
    private MainView mainView;
    private List<TaxisDomain> taxisDomainsRes;


    MainPresenter(MainView view) {
        this.mainView = view;
        TaxisBY.appComponent.inject(this);
    }


    void onGetList() {
        taxisDomainsRes = new ArrayList<>();
        getListTaxisDomain.execute(null, new DisposableObserver<List<TaxisDomain>>() {
            @Override
            public void onNext(List<TaxisDomain> taxisDomains) {
                taxisDomainsRes = taxisDomains;
                mainView.showProgress();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                mainView.goToMain(taxisDomainsRes);
                mainView.dismissProgress();
            }
        });
    }

    void onClickSearch(String s) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(KEY_SEARCH, s);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    void onHintHalt() {
        hintHalts = new ArrayList<>();
        getListHintDomain.execute(null, new DisposableObserver<List<String>>() {
            @Override
            public void onNext(List<String> searchHintDomains) {
                hintHalts = searchHintDomains;

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e("onComplete", "onHintHalt");
                mainView.goToMainHint(hintHalts);

            }
        });
    }

    void ubdateToRest() {
        getVersionUbdateDomain.execute(null, new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                Log.e("RestPresenter", aBoolean.toString());
                ubdate = aBoolean;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                mainView.gotoMainUbdate(ubdate);
            }
        });
    }

    void ubdateDb() {
        getUbdateFromRestDomain.execute(null, new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                mainView.restartApp();
            }
        });
    }

    void dispose() {
        getListTaxisDomain.dispose();
        getListHintDomain.dispose();
        getVersionUbdateDomain.dispose();
        getUbdateFromRestDomain.dispose();
    }

    void onDestroy() {
        mainView = null;
        hintHalts = null;
        taxisDomainsRes = null;
    }
}

