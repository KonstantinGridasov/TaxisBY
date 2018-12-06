package com.transport.taxi.bus.taxis.data.settingsDb;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbHalt;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.transport.taxi.bus.taxis.data.db.GetFromNet.KEY_UBDATE;
import static com.transport.taxi.bus.taxis.data.db.GetFromNet.SHARED_UBDATE;

/**
 * Created by GHome on 19.12.2017.
 */
//Класс для чтения  Маршруток из Json  и записи  в Realm
public class WriterToDb {
    @Inject
    Context context;
    private List<TaxisData> list = new ArrayList<>();
    private Realm realm;

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

        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(config);

        for (int i = 0; i < list.size(); i++) {

            if (findId(list.get(i).getId())) {

                realm.beginTransaction();
                DbTaxis dbTaxis = realm.createObject(DbTaxis.class, list.get(i).getId());

                for (int j = 0; j < list.get(i).getDirectHalt().size(); j++) {
                    DbHalt dbHaltInD = new DbHalt();
                    dbHaltInD.setId(list.get(i).getDirectHalt().get(j).getId());
                    dbHaltInD.setHaltName(list.get(i).getDirectHalt().get(j).getHaltName());

                    dbHaltInD.setLat(list.get(i).getDirectHalt().get(j).getLat());
                    dbHaltInD.setLng(list.get(i).getDirectHalt().get(j).getLng());

                    final DbHalt dbHaltD = realm.copyToRealm(dbHaltInD);
                    dbTaxis.getDbDirectHalt().add(dbHaltD);
                }
                realm.commitTransaction();

                realm.beginTransaction();
                for (int k = 0; k < list.get(i).getReverseHalt().size(); k++) {
                    DbHalt dbHaltInR = new DbHalt();
                    dbHaltInR.setId(list.get(i).getReverseHalt().get(k).getId());
                    dbHaltInR.setHaltName(list.get(i).getReverseHalt().get(k).getHaltName());

                    dbHaltInR.setLat(list.get(i).getReverseHalt().get(k).getLat());
                    dbHaltInR.setLng(list.get(i).getReverseHalt().get(k).getLng());

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

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_UBDATE, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(KEY_UBDATE, 20).apply();
    }

    //Проверка на существование
    @NonNull
    private Boolean findId(String id) {
        DbTaxis dbTaxisData = realm.where(DbTaxis.class)
                .equalTo("id", id)
                .findFirst();
        return (dbTaxisData == null);
    }


}

