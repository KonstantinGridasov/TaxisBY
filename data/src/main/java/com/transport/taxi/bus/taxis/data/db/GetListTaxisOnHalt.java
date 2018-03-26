package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by GHome on 02.01.2018.
 */

public class GetListTaxisOnHalt {
    @Inject
    Context context;

    public GetListTaxisOnHalt(Context context) {
        this.context = context;
    }

    //Получение списка объектов(маршруток) по остановке
    public Observable<List<DbTaxis>> searchHalt(String halt) {
        List<DbTaxis> list = new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        List<DbTaxis> dbTaxisDataList = realm.copyFromRealm(
                realm.where(DbTaxis.class).findAll());
        realm.close();

        for (int i = 0; i < dbTaxisDataList.size(); i++) {
            for (int j = 0; j < dbTaxisDataList.get(i).getDbDirectHalt().size(); j++) {
                if (((dbTaxisDataList.get(i).getDbDirectHalt().get(j).getHaltName())
                        .toLowerCase()
                        .contains(halt.toLowerCase()))
                        | (dbTaxisDataList.get(i).getId()).contains(halt)) {

                    list.add(dbTaxisDataList.get(i));
                    break;
                }
            }
        }

        return Observable.fromArray(list);
    }
}
