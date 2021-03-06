package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.db.GetFromDb;
import com.transport.taxi.bus.taxis.data.db.GetFromNet;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 24.04.2018.
 */

public class GetUbdateFromRestDomain extends UseCase<Void, Boolean> {

    @Inject
    GetFromNet getFromNet;

    @Inject
    public GetUbdateFromRestDomain(GetFromNet getFromNet) {
        this.getFromNet = getFromNet;
    }

    @Override
    protected Observable<Boolean> buildUseCase(Void aVoid) {
        return getFromNet.getUbdate();
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
