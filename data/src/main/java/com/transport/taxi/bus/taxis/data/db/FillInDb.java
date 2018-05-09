package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.settingsDb.WriterHint;
import com.transport.taxi.bus.taxis.data.settingsDb.WriterToDb;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by GHome on 20.12.2017.
 */

public class FillInDb {
    @Inject
    Context context;

    public FillInDb(Context context) {
        this.context = context;
    }


    public Observable<Boolean> fillDataBase() {

        //Вызов метода для записи  списка маршруток в базу данных
        WriterToDb writerToDb = new WriterToDb(context);
        writerToDb.WriteDb();

        //Вызов метода для записи  списка подсказок(остановок) в базу данных
        WriterHint writerHint = new WriterHint(context);
        writerHint.WriterHint();
        return Observable.just(true);
    }
}