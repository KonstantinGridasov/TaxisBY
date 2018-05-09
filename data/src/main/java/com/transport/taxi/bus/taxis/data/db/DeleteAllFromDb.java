package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;
import com.transport.taxi.bus.taxis.data.db.baseDb.SearchHint;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by GHome on 04.01.2018.
 */

public class DeleteAllFromDb {
    @Inject
    Context context;

    public DeleteAllFromDb(Context context) {
        this.context = context;
    }

    //Очистка всей базы
    public Observable<Boolean> clearRealm() {

        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<DbTaxis> dbTaxisData = realm.where(DbTaxis.class).findAll();
        if (!dbTaxisData.isEmpty()) {
            for (int i = dbTaxisData.size() - 1; i >= 0; i--) {
                dbTaxisData.get(i).deleteFromRealm();
            }
        }
        realm.commitTransaction();

        realm.beginTransaction();
        RealmResults<SearchHint> dbHints = realm.where(SearchHint.class).findAll();
        if (!dbHints.isEmpty()) {
            for (int i = dbHints.size() - 1; i >= 0; i--) {
                dbHints.get(i).deleteFromRealm();
            }
        }
        realm.commitTransaction();
        clearPreferences();

        return Observable.just(true);
    }

    private void clearPreferences() {// clearing app data
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear TaxisBY");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
