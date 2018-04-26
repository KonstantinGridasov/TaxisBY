package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.db.GetVersionUbdate;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 17.04.2018.
 */

public class GetVersionUbdateDomain extends UseCase<Void, Boolean> {
    @Inject
    GetVersionUbdate vers;

    @Inject
    public GetVersionUbdateDomain(GetVersionUbdate vers) {
        this.vers = vers;
    }

    @Override
    protected Observable<Boolean> buildUseCase(Void aVoid) {
        return vers.comparationOfVersion();
    }


    @Override
    public void dispose() {
        super.dispose();

    }
}