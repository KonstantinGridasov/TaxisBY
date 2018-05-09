package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.db.DeleteAllFromDb;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 04.01.2018.
 */

public class DeleteDomain extends UseCase<Void, Boolean> {
    @Inject
    DeleteAllFromDb deleteAllFromDb;

    public DeleteDomain(DeleteAllFromDb deleteAllFromDb) {
        this.deleteAllFromDb = deleteAllFromDb;
    }

    //Вызов метода для очистки базы данных
    @Override
    protected Observable<Boolean> buildUseCase(Void aVoid) {
        return deleteAllFromDb.clearRealm();
    }
}
