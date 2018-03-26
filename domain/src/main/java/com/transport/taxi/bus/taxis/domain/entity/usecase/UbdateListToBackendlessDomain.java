package com.transport.taxi.bus.taxis.domain.entity.usecase;

import android.util.Log;

import com.transport.taxi.bus.taxis.data.db.ListTaxisToBackendless;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 23.03.2018.
 */

public class UbdateListToBackendlessDomain extends UseCase<Void, Void> {
    @Inject
    ListTaxisToBackendless ubdate;

    @Inject
    public UbdateListToBackendlessDomain(ListTaxisToBackendless ubdate) {
        this.ubdate = ubdate;
    }

    //Вызов метода для ubdate
    @Override
    protected Observable<Void> buildUseCase(Void aVoid) {
        Log.e("UbdateList", "buildUseCase");

        return ubdate.ubdateList();
    }
}
