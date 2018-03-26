package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by GHome on 19.12.2017.
 */

public class GetListTaxis {
    @Inject
    Context context;


    public GetListTaxis(Context context) {
        this.context = context;
    }

    //Выгрузка всей базы маршруток
    public Observable<List<DbTaxis>> getList() {
        List<DbTaxis> dbTaxisList = null;

        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        dbTaxisList = realm.copyFromRealm(
                realm.where(DbTaxis.class).findAll());
        return Observable.fromArray(dbTaxisList);

    }
}
