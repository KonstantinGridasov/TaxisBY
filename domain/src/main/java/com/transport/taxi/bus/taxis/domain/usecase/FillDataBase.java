package com.transport.taxi.bus.taxis.domain.usecase;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.db.FillDb;
import com.transport.taxi.bus.taxis.domain.base.UseCase;

import io.reactivex.Observable;

/**
 * Created by GHome on 20.12.2017.
 */

public class FillDataBase extends UseCase<Void, Boolean> {
    private Context context;
    private FillDb fillDb = new FillDb();

    public FillDataBase(Context context) {
        this.context = context;
    }

    @Override
    protected Observable<Boolean> buildUseCase(Void aVoid) {
        Log.e("FillDataBase", "true");

        return fillDb.FillDataBase(context);
    }
}