package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.settingsDb.ReWriteUbdate;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 24.04.2018.
 */

public class GetUbdate {
    @Inject
    Context context;

    public GetUbdate(Context context) {
        this.context = context;
    }


    public Observable<Boolean> GetUbdate() {

        //Вызов метода для записи  списка маршруток в базу данных
        ReWriteUbdate writerToDb = new ReWriteUbdate(context);
        writerToDb.reWrite();

        return Observable.just(true);
    }
}

