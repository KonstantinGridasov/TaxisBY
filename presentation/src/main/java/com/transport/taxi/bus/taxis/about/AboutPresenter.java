package com.transport.taxi.bus.taxis.about;

import android.util.Log;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetUbdateFromRestDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetVersionUbdateDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.SetVersionUbdateDomain;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 21.04.2018.
 */

public class AboutPresenter {
    @Inject
    GetVersionUbdateDomain getVersionUbdateDomain;
    @Inject
    GetUbdateFromRestDomain getUbdateFromRestDomain;
    @Inject
    SetVersionUbdateDomain setVersionUbdateDomain;



    private Boolean ubdate;
    private AboutView view;

    AboutPresenter(AboutView view) {
        this.view = view;
        TaxisBY.appComponent.inject(this);
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
                view.goToAboutUbdate(ubdate);
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
                setVersionUbdate();
            }
        });
    }

    void setVersionUbdate() {
        setVersionUbdateDomain.execute(null, new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();

            }

            @Override
            public void onComplete() {
                view.restartApp();
            }
        });
    }

    void onDestroy() {

        view = null;
    }


}
