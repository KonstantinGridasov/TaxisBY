package com.transport.taxi.bus.taxis.domain.usecase;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.db.RemoveAll;
import com.transport.taxi.bus.taxis.domain.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 04.01.2018.
 */

public class RemoveALLDb extends UseCase<Void, Boolean> {
    private Context context;
    private RemoveAll removeAll;

    @Inject
    public RemoveALLDb(Context context) {
        this.context = context;
    }

    @Override
    protected Observable<Boolean> buildUseCase(Void aVoid) {
        removeAll = new RemoveAll(context);
        return removeAll.clearRealm(context);
    }
}
