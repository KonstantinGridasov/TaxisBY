package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by GHome on 04.01.2018.
 */

public class RemoveAll {
    private Realm realm;
    private Context context;

    public RemoveAll(Context context) {
        this.context = context;
    }

    public Observable<Boolean> clearRealm(Context context) {        //Очистка всей базы

        Realm.init(context);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<DbTaxisData> dbTaxisData = realm.where(DbTaxisData.class).findAll();
        if (!dbTaxisData.isEmpty()) {
            for (int i = dbTaxisData.size() - 1; i >= 0; i--) {
                dbTaxisData.get(i).deleteFromRealm();
            }
        }
        realm.commitTransaction();

        return Observable.just(true);
    }
}
