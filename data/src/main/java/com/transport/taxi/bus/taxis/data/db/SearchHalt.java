package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.util.Log;

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
    private List<String> list;
    private String s = "null";
    private int n = 1;

    public SearchHalt(Context context) {
        this.context = context;
    }

    public Observable<List<String>> searchHalt(Context context, String halt) {   //Получени обеъкта по ID
        list = new ArrayList<>();
        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        List<DbTaxisData> dbTaxisDataList = realm.copyFromRealm(
                realm.where(DbTaxisData.class).findAll());
        realm.close();

        for (int i = 0; i < dbTaxisDataList.size(); i++) {
            Log.e("For", dbTaxisDataList.get(i).getName());
            if ((dbTaxisDataList.get(i).getDirect_direction()).contains(halt)
                    | (dbTaxisDataList.get(i).getId()).contains(halt)) {
                Log.e("if", dbTaxisDataList.get(i).getName());
                n += n;
                list.add(dbTaxisDataList.get(i).getId());
            }
        }
        if (n == 1)
            list.add(s);

        return Observable.fromArray(list);
    }

}
