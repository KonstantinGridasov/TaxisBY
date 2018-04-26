package com.transport.taxi.bus.taxis.settings.firstStart;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.entity.usecase.FillDomain;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 23.03.2018.
 */

public class FirstStartMainPresenter {

    @Inject
    FillDomain fillDomain;

    private FirstStartMainView view;

    public FirstStartMainPresenter(FirstStartMainView view) {
        this.view = view;
        TaxisBY.appComponent.inject(this);
    }

    void onFillDb() {
        fillDomain.execute(null, new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                view.gotoFirstStartMain();
            }
        });
    }

    void dispose() {
        fillDomain.dispose();
    }

    void onDestroy() {
        view = null;
    }
}
