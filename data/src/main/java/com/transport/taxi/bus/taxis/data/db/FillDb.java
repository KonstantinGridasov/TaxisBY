package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.settingsDb.WriterToDb;

import io.reactivex.Observable;

/**
 * Created by GHome on 20.12.2017.
 */

public class FillDb {
    public Observable<Boolean> FillDataBase(Context context) {
        WriterToDb writerToDb = new WriterToDb(context);
        Log.e("FillDb", "true");
        writerToDb.WriteDb();
        return Observable.just(true);
    }
}