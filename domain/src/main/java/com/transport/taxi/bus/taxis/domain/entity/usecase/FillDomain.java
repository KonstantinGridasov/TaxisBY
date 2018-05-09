package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.db.FillInDb;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 23.03.2018.
 */

public class FillDomain extends UseCase<Void, Boolean> {
    @Inject
    FillInDb fillInDb;

    @Inject
    public FillDomain(FillInDb fillInDb) {
        this.fillInDb = fillInDb;
    }

    //Вызов метода для заполнения Базы данных
    @Override
    protected Observable<Boolean> buildUseCase(Void aVoid) {
        return fillInDb.fillDataBase();
    }


}
