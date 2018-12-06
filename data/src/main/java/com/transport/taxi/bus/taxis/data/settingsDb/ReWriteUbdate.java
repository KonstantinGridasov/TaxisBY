package com.transport.taxi.bus.taxis.data.settingsDb;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbHalt;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by GHome on 24.04.2018.
 */

public class ReWriteUbdate {
    @Inject
    Context context;
    private Realm realm;

    public ReWriteUbdate(Context context) {
        this.context = context;
    }

    public synchronized void reWrite() {
        ReaderJSON readerJSON = new ReaderJSON(context);
        try {
            ubdateRealm(readerJSON.readerFromUrl());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ubdateRealm(List<TaxisData> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUbdate().equals("ubdate")) {

                    deleteFromDb(list.get(i).getId());
                    ubdateToDb(list.get(i));
                } else if (list.get(i).getUbdate().equals("delete")) {
                    deleteFromDb(list.get(i).getId());
                }
            }
        }
    }

    private void deleteFromDb(String s) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        DbTaxis dbTaxisData = realm.where(DbTaxis.class).
                equalTo("id", s).findFirst();
        if (dbTaxisData != null)
            dbTaxisData.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

    }


    private void ubdateToDb(TaxisData taxisData) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        DbTaxis dbTaxisData = realm.where(DbTaxis.class).
                equalTo("id", taxisData.getId()).findFirst();

        if (dbTaxisData == null) {
            DbTaxis dbTaxis = realm.createObject(DbTaxis.class, taxisData.getId());

            for (int j = 0; j < taxisData.getDirectHalt().size(); j++) {
                DbHalt dbHaltInD = new DbHalt();
                dbHaltInD.setId(taxisData.getDirectHalt().get(j).getId());
                dbHaltInD.setHaltName(taxisData.getDirectHalt().get(j).getHaltName());

                dbHaltInD.setLat(taxisData.getDirectHalt().get(j).getLat());
                dbHaltInD.setLng(taxisData.getDirectHalt().get(j).getLng());

                final DbHalt dbHaltD = realm.copyToRealm(dbHaltInD);
                dbTaxis.getDbDirectHalt().add(dbHaltD);
            }
            realm.commitTransaction();

            realm.beginTransaction();
            for (int k = 0; k < taxisData.getReverseHalt().size(); k++) {
                DbHalt dbHaltInR = new DbHalt();
                dbHaltInR.setId(taxisData.getReverseHalt().get(k).getId());
                dbHaltInR.setHaltName(taxisData.getReverseHalt().get(k).getHaltName());

                dbHaltInR.setLat(taxisData.getReverseHalt().get(k).getLat());
                dbHaltInR.setLng(taxisData.getReverseHalt().get(k).getLng());

                final DbHalt dbHaltR = realm.copyToRealm(dbHaltInR);
                dbTaxis.getDbReverseHalt().add(dbHaltR);
            }
            realm.commitTransaction();

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(dbTaxis);
            realm.commitTransaction();

            realm.beginTransaction();
            dbTaxis.setInterval(taxisData.getInterval());
            realm.commitTransaction();

            realm.beginTransaction();
            dbTaxis.setInWeek(taxisData.getInWeek());
            realm.commitTransaction();

            realm.beginTransaction();
            dbTaxis.setWorkingTime(taxisData.getWorkingTime());
            realm.commitTransaction();

            realm.beginTransaction();
            dbTaxis.setDirectName(taxisData.getDirectName());
            realm.commitTransaction();

            realm.beginTransaction();
            dbTaxis.setReverseName(taxisData.getReverseName());
            realm.commitTransaction();
        } else
            realm.commitTransaction();
        realm.close();
    }
}

