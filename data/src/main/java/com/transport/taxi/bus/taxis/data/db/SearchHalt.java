package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.TaxisData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;

/**
 * Created by GHome on 02.01.2018.
 */

public class SearchHalt {
    private TaxisData taxisData;
    private DbTaxisData dbTaxisData;
    private Realm realm;
    private Context context;
    private List<String> list;
    private String s = "null";
    private int n = 1;

    public SearchHalt(Context context) {
        this.context = context;
    }

    public Observable<List<TaxisData>> searchHalt(Context context, String halt) {   //Получени обеъкта по ID
        list = new ArrayList<>();
        List<TaxisData> listTaxis = new ArrayList<>();

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

        if (n == 1) {
            TaxisData taxisDataNull = new TaxisData();
            taxisDataNull.setName(s);
            listTaxis.add(0, taxisDataNull);
            return Observable.fromArray(listTaxis);
        } else {
            for (int i = 0; i < list.size(); i++) {

                listTaxis.add(getTaxis(list.get(i)));
            }
            return Observable.fromArray(listTaxis);
        }
    }

    public TaxisData getTaxis(String id) {   //Получени обеъкта по ID
        taxisData = new TaxisData();
        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        dbTaxisData = realm.where(DbTaxisData.class)                       //Поиск в базе по track(т.е по ID)
                .equalTo("id", id)
                .findFirst();
        Log.e("Data:getTaxisOnId", dbTaxisData.getName());

        if (dbTaxisData != null) {
            taxisData.setId(dbTaxisData.getId());
            taxisData.setName(dbTaxisData.getName());
            taxisData.setDirect_direction(dbTaxisData.getDirect_direction());
            taxisData.setReverse_direction(dbTaxisData.getReverse_direction());
            Log.e("inIf", taxisData.getName());
            dbTaxisData.addChangeListener(new RealmChangeListener<RealmModel>() {    //Подписка на изменения
                @Override
                public void onChange(RealmModel realmModel) {

                    Log.e("loadData", "  ChangeListener");
                    taxisData.setId(dbTaxisData.getId());
                    taxisData.setName(dbTaxisData.getName());
                    taxisData.setDirect_direction(dbTaxisData.getDirect_direction());
                    taxisData.setReverse_direction(dbTaxisData.getReverse_direction());
                }
            });

        }
        Log.e("Data:_return", "taxisdata=" + taxisData.getName());

        realm.close();
        return taxisData;
    }
}