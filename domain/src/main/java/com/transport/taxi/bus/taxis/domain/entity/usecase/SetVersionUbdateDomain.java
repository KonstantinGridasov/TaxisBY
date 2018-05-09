package com.transport.taxi.bus.taxis.domain.entity.usecase;

import android.util.Log;

import com.transport.taxi.bus.taxis.data.db.GetFromNet;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 17.04.2018.
 */

public class SetVersionUbdateDomain extends UseCase<Void, Boolean> {
    @Inject
    GetFromNet getFromNet;

    @Inject
    public SetVersionUbdateDomain(GetFromNet getFromNet) {
        this.getFromNet = getFromNet;
    }

    @Override
    protected Observable<Boolean> buildUseCase(Void aVoid) {
        Log.e("UseCaseSet", "true");

        return getFromNet.setVersion();
    }


    @Override
    public void dispose() {
        super.dispose();

    }
}