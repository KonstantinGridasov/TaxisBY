package com.transport.taxi.bus.taxis.data.settingsDb;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.TaxisData;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by GHome on 19.12.2017.
 */

public class WriterToDb {
    private List<TaxisData> list = new ArrayList<>();
    private Realm realm;
    private Context context;
    private TaxisData taxisData;

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
                taxisData = realm.createObject(TaxisData.class, list.get(i).getId());
                realm.copyToRealmOrUpdate(taxisData);
                realm.commitTransaction();

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(taxisData);
                realm.commitTransaction();

                realm.beginTransaction();
                taxisData.setName(list.get(i).getName());
                realm.commitTransaction();

                realm.beginTransaction();
                taxisData.setDirect_direction(list.get(i).getDirect_direction());
                realm.commitTransaction();

                realm.beginTransaction();
                taxisData.setReverse_direction(list.get(i).getReverse_direction());
                realm.commitTransaction();
            }
        }
        realm.close();
    }

    private Boolean FindId(String id) {
        TaxisData taxisData = new TaxisData();
        taxisData = realm.where(TaxisData.class)                       //Поиск в базе по ID
                .equalTo("id", id)
                .findFirst();
        if (taxisData == null)
            return true;
        else
            return false;
    }
}