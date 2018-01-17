package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by GHome on 19.12.2017.
 */

public class GetList {

    private Realm realm;
    private Context context;

    public GetList(Context context) {
        this.context = context;
    }

    public Observable<List<DbTaxis>> getList(Context context) {      //Выгрузка все базы Realm

        Log.e("Data", "GetList");
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        List<DbTaxis> dbTaxisList = realm.copyFromRealm(
                realm.where(DbTaxis.class).findAll());
        realm.close();

        return Observable.fromArray(dbTaxisList);
    }
}
