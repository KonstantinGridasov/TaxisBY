package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.db.DeleteAllFromDb;
import com.transport.taxi.bus.taxis.data.db.FillInDb;
import com.transport.taxi.bus.taxis.data.db.GetFromDb;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by GHome on 23.03.2018.
 */

public class FillDomain extends UseCase<Void, Boolean> {
    @Inject
    FillInDb fillInDb;

    @Inject
    DeleteAllFromDb deleteDomain;

    @Inject
    GetFromDb getFromDb;

    @Inject
    public FillDomain(FillInDb fillInDb, DeleteAllFromDb deleteDomain, GetFromDb getFromDb) {
        this.fillInDb = fillInDb;
        this.deleteDomain = deleteDomain;
        this.getFromDb = getFromDb;
    }

    //Вызов метода для заполнения Базы данных
    @Override
    protected Observable<Boolean> buildUseCase(Void aVoid) {
        return getFromDb.isBase()
                .map(new Function<Boolean, Boolean>() {
            @Override
            public Boolean apply(Boolean aBoolean) {
                if (aBoolean) {
                    deleteDomain.clearRealm();
                }
                return true;
            }
        }).map(new Function<Boolean, Boolean>() {
            @Override
            public Boolean apply(Boolean aBoolean) {
                fillInDb.fillDataBase();
                return true;
            }
        });
    }


}
