package com.transport.taxi.bus.taxis.data.settingsDb;

import android.content.Context;
import android.support.annotation.NonNull;

import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;
import com.transport.taxi.bus.taxis.data.db.baseDb.SearchHint;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by GHome on 29.01.2018.
 */
//Класс для чтения  Маршруток из Realm  и записи  подсказок в Realm

public class WriterHint {
    @Inject
    Context context;
    private Realm realm;
    private SearchHint searchHint;

    public WriterHint(Context context) {
        this.context = context;
    }

    public void WriterHint() {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        List<DbTaxis> dbTaxisList = realm.copyFromRealm(
                realm.where(DbTaxis.class).findAll());
        realm.close();


        Realm.init(context);
        realm = Realm.getDefaultInstance();
        for (int i = 0; i < dbTaxisList.size(); i++) {
            for (int j = 0; j < dbTaxisList.get(i).getDbDirectHalt().size(); j++) {
                if (dbTaxisList.get(i).getDbDirectHalt().get(j).getHaltName() != null
                        & findIt(dbTaxisList.get(i).getDbDirectHalt().get(j).getHaltName())) {

                    realm.beginTransaction();
                    searchHint = realm.createObject(SearchHint.class, dbTaxisList.get(i).getDbDirectHalt().get(j).getHaltName());
                    realm.commitTransaction();
                }

            }

        }
    }

    //Проверка на существование
    @NonNull
    private Boolean findIt(String it) {
        SearchHint dbSearchHint = realm.where(SearchHint.class)
                .equalTo("hintSearch", it)
                .findFirst();
        if (dbSearchHint == null)
            return true;
        else
            return false;
    }
}
