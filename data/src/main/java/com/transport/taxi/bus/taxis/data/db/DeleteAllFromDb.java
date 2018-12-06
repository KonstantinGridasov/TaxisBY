package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.db.baseDb.DbHalt;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;
import com.transport.taxi.bus.taxis.data.db.baseDb.SearchHint;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmConfiguration;
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
        Log.e("Delete", "deleteAll");
        Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm realm = Realm.getInstance(config);

        final RealmResults<DbTaxis> dbTaxisData = realm.where(DbTaxis.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                dbTaxisData.deleteAllFromRealm();
            }
        });
        realm.delete(DbTaxis.class);

        final RealmResults<DbHalt> dbHalts = realm.where(DbHalt.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                dbHalts.deleteAllFromRealm();
            }
        });
        realm.delete(DbHalt.class);

        final RealmResults<SearchHint> searchHints = realm.where(SearchHint.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                searchHints.deleteAllFromRealm();
            }
        });
        realm.delete(SearchHint.class);

        return Observable.just(true);
    }


}

