package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.db.GetUbdate;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 24.04.2018.
 */

public class GetUbdateFromRestDomain extends UseCase<Void, Boolean> {

    @Inject
    GetUbdate getUbdate;

    @Inject
    public GetUbdateFromRestDomain(GetUbdate getUbdate) {
        this.getUbdate = getUbdate;
    }

    @Override
    protected Observable<Boolean> buildUseCase(Void aVoid) {
        return getUbdate.GetUbdate();
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
