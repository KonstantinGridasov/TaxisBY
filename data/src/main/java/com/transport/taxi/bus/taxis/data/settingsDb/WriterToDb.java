package com.transport.taxi.bus.taxis.data.settingsDb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbHalt;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;

import java.io.IOException;
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
    private DbTaxis dbTaxis;


    public WriterToDb(Context context) {
        this.context = context;
    }

    public void WriteDb() {
        ReaderJSON readerJSON = new ReaderJSON(context);
        try {
            list = readerJSON.readJsonStream(context);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer n = list.size();
        Log.e("Data:WriteToDb", n.toString());
        Realm.init(context);
        realm = Realm.getDefaultInstance();

        for (int i = 0; i < list.size(); i++) {
            // Обернуть в условие (Проверка на существование записи)
            if (FindId(list.get(i).getId())) {

                realm.beginTransaction();
                dbTaxis = realm.createObject(DbTaxis.class, list.get(i).getId());

                for (int j = 0; j < list.get(i).getDirectHalt().size(); j++) {
                    DbHalt dbHaltInD = new DbHalt();
                    dbHaltInD.setHaltId(list.get(i).getDirectHalt().get(j).getHaltId());
                    dbHaltInD.setHaltName(list.get(i).getDirectHalt().get(j).getHaltName());
                    final DbHalt dbHaltD = realm.copyToRealm(dbHaltInD);
                    dbTaxis.getDbDirectHalt().add(dbHaltD);
                }
                realm.commitTransaction();

                realm.beginTransaction();
                for (int k = 0; k < list.get(i).getReverseHalt().size(); k++) {
                    DbHalt dbHaltInR = new DbHalt();
                    dbHaltInR.setHaltId(list.get(i).getReverseHalt().get(k).getHaltId());
                    dbHaltInR.setHaltName(list.get(i).getReverseHalt().get(k).getHaltName());
                    final DbHalt dbHaltR = realm.copyToRealm(dbHaltInR);
                    dbTaxis.getDbReverseHalt().add(dbHaltR);
                }
                realm.commitTransaction();


                realm.beginTransaction();
                realm.copyToRealmOrUpdate(dbTaxis);
                realm.commitTransaction();

                realm.beginTransaction();
                dbTaxis.setInterval(list.get(i).getInterval());
                realm.commitTransaction();

                realm.beginTransaction();
                dbTaxis.setInWeek(list.get(i).getInWeek());
                realm.commitTransaction();

                realm.beginTransaction();
                dbTaxis.setWorkingTime(list.get(i).getWorkingTime());
                realm.commitTransaction();

                realm.beginTransaction();
                dbTaxis.setDirectName(list.get(i).getDirectName());
                realm.commitTransaction();

                realm.beginTransaction();
                dbTaxis.setReverseName(list.get(i).getReverseName());
                realm.commitTransaction();

            }
        }
        realm.close();
    }

    private Boolean FindId(String id) {
        DbTaxis dbTaxisData = new DbTaxis();
        dbTaxisData = realm.where(DbTaxis.class)                       //Поиск в базе по ID
                .equalTo("id", id)
                .findFirst();
        if (dbTaxisData == null)
        return true;
        else
            return false;
    }


}

