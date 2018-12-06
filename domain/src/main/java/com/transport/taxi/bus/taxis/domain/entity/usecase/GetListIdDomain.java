package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.db.GetFromDb;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetListIdDomain extends UseCase<Void, List<String>> {
    @Inject
    GetFromDb getFromDb;

    public GetListIdDomain(GetFromDb getFromDb) {
        this.getFromDb = getFromDb;
    }

    @Override
    protected Observable<List<String>> buildUseCase(Void aVoid) {
        return getFromDb.getListId()
                ;
    }
}
