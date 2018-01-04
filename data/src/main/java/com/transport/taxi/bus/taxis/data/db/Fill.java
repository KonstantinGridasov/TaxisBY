package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.settingsDb.WriterToDb;

import io.reactivex.Observable;

/**
 * Created by GHome on 20.12.2017.
 */

public class Fill {
    Context context;

    public Fill(Context context) {
        this.context = context;
    }

    public Observable<Boolean> FillDataBase() {
        WriterToDb writerToDb = new WriterToDb(context);
        Log.e("Fill", "true");
        writerToDb.WriteDb();
        return Observable.just(true);
    }
}