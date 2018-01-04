package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.util.Log;

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

    public Observable<List<DbTaxisData>> getList(Context context) {      //Выгрузка все базы Realm
        Log.e("Data", "GetList");
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        List<DbTaxisData> dbTaxisDataList = realm.copyFromRealm(
                realm.where(DbTaxisData.class).findAll());
        realm.close();

        return Observable.fromArray(dbTaxisDataList);
    }
}
