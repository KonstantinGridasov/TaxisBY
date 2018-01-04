package com.transport.taxi.bus.taxis.data.settingsDb;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.DbTaxisData;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by GHome on 19.12.2017.
 */

public class WriterToDb {
    Context context;

    private List<TaxisData> list = new ArrayList<>();
    private Realm realm;
    private DbTaxisData dbtaxisData;

    public WriterToDb(Context context) {
        this.context = context;
    }

    public void WriteDb() {
        ReaderJSON readerJSON = new ReaderJSON(context);
        list = readerJSON.getTaxisList();
        Integer n = list.size();
        Log.e("Data:WriteToDb", n.toString());
        Realm.init(context);
        realm = Realm.getDefaultInstance();

        for (int i = 0; i < list.size(); i++) {
//            TaxisData taxisData = new TaxisData();

            // Обернуть в условие (Проверка на существование записи)
            if (FindId(list.get(i).getId())) {
                realm.beginTransaction();
                dbtaxisData = realm.createObject(DbTaxisData.class, list.get(i).getId());
                realm.copyToRealmOrUpdate(dbtaxisData);
                realm.commitTransaction();

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(dbtaxisData);
                realm.commitTransaction();

                realm.beginTransaction();
                dbtaxisData.setName(list.get(i).getName());
                realm.commitTransaction();

                realm.beginTransaction();
                dbtaxisData.setDirect_direction(list.get(i).getDirect_direction());
                realm.commitTransaction();

                realm.beginTransaction();
                dbtaxisData.setReverse_direction(list.get(i).getReverse_direction());
                realm.commitTransaction();
            }
        }
        realm.close();
    }

    private Boolean FindId(String id) {
        DbTaxisData dbTaxisData = new DbTaxisData();
        dbTaxisData = realm.where(DbTaxisData.class)                       //Поиск в базе по ID
                .equalTo("id", id)
                .findFirst();
        if (dbTaxisData == null)
            return true;
        else
            return false;
    }
}