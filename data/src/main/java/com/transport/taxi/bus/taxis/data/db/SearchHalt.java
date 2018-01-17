package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by GHome on 02.01.2018.
 */

public class SearchHalt {
    private Realm realm;
    private Context context;
    private List<DbTaxis> list;
    private int n = 1;

    public SearchHalt(Context context) {
        this.context = context;
    }

    public Observable<List<DbTaxis>> searchHalt(String halt) {   //Получени обеъкта по ID
        list = new ArrayList<>();
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        List<DbTaxis> dbTaxisDataList = realm.copyFromRealm(
                realm.where(DbTaxis.class).findAll());
        realm.close();

        for (int i = 0; i < dbTaxisDataList.size(); i++) {
            for (int j = 0; j < dbTaxisDataList.get(i).getDbDirectHalt().size(); j++) {
                if (((dbTaxisDataList.get(i).getDbDirectHalt().get(j).getHaltName())
                        .toLowerCase()
                        .contains(halt.toLowerCase()))
                        | (dbTaxisDataList.get(i).getId()).contains(halt)) {
                    n += n;
                    list.add(dbTaxisDataList.get(i));
                    break;
                }
            }
            if (n == 1)
                for (int j = 0; j < dbTaxisDataList.get(i).getDbReverseHalt().size(); j++) {
                    if (((dbTaxisDataList.get(i).getDbReverseHalt().get(j).getHaltName())
                            .toLowerCase()
                            .contains(halt.toLowerCase()))
                            | (dbTaxisDataList.get(i).getId()).contains(halt)) {
                        n += n;
                        list.add(dbTaxisDataList.get(i));
                        break;
                    }
                }
        }

        if (n == 1) {
            DbTaxis taxisDataNull = new DbTaxis();
            taxisDataNull.setDirectName("null");

            list.add(0,taxisDataNull);
            return Observable.fromArray(list);
        } else {

            return Observable.fromArray(list);
        }
    }
}