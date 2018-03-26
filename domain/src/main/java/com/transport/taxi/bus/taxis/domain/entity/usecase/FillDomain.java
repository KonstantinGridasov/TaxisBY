package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.db.Fill;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 23.03.2018.
 */

public class FillDomain extends UseCase<Void, Boolean> {
    @Inject
    Fill fill;

    @Inject
    public FillDomain(Fill fill) {
        this.fill = fill;
    }

    //Вызов метода для заполнения Базы данных
    @Override
    protected Observable<Boolean> buildUseCase(Void aVoid) {
        return fill.FillDataBase();
    }
}
