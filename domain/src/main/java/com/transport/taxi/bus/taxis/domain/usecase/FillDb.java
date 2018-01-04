package com.transport.taxi.bus.taxis.domain.usecase;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.db.Fill;
import com.transport.taxi.bus.taxis.domain.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 20.12.2017.
 */

public class FillDb extends UseCase<Void, Boolean> {
    private Context context;
    private Fill fill;

    @Inject
    public FillDb(Context context) {
        this.context = context;
    }

    @Override
    protected Observable<Boolean> buildUseCase(Void aVoid) {
        Log.e("FillDb", "true");
        fill = new Fill(context);
        return fill.FillDataBase();
    }
}