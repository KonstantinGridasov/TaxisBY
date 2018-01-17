package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.Halt;
import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbHalt;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;

/**
 * Created by GHome on 02.01.2018.
 */

public class GetHalt {
    private TaxisData taxisData;
    private DbTaxis dbTaxisData;
    private Realm realm;
    private Context context;

    public GetHalt(Context context) {
        this.context = context;
    }

    public Observable<TaxisData> getTaxisOnId(Context context, String id) {   //Получени обеъкта по ID
        taxisData = new TaxisData();
        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        dbTaxisData = realm.where(DbTaxis.class)                       //Поиск в базе по track(т.е по ID)
                .equalTo("id", id)
                .findFirst();

        if (dbTaxisData != null) {
            taxisData.setId(dbTaxisData.getId());
            taxisData.setInterval(dbTaxisData.getInterval());
            taxisData.setInWeek(dbTaxisData.getInWeek());
            taxisData.setWorkingTime(dbTaxisData.getWorkingTime());
            taxisData.setDirectName(dbTaxisData.getDirectName());
            taxisData.setReverseName(dbTaxisData.getReverseName());


            taxisData.setDirectHalt(convertHalt(dbTaxisData.getDbDirectHalt()));
            taxisData.setReverseHalt(convertHalt(dbTaxisData.getDbReverseHalt()));

            dbTaxisData.addChangeListener(new RealmChangeListener<RealmModel>() {    //Подписка на изменения
                @Override
                public void onChange(RealmModel realmModel) {

                    Log.e("loadData", "  ChangeListener");
//                    taxisData.setId(dbTaxisData.getId());
//                    taxisData.setInterval(dbTaxisData.getInterval());
//                    taxisData.setInWeek(dbTaxisData.getInWeek());
//                    taxisData.setDirectName(dbTaxisData.getDirectName());
//                    taxisData.setReverseName(dbTaxisData.getReverseName());


//                    taxisData.setDirectHalt(convertHalt(dbTaxisData.getDbDirectHalt()));
//                    taxisData.setReverseHalt(convertHalt(dbTaxisData.getDbReverseHalt()));
                }
            });
        }

        realm.close();
        return Observable.just(taxisData);
    }


    private List<Halt> convertHalt(List<DbHalt> dbHalt) {
        List<Halt> haltList = new ArrayList<>();

        for (int i = 0; i < dbHalt.size(); i++) {
            final Halt halt = new Halt();
            halt.setHaltId(dbHalt.get(i).getHaltId());
            halt.setHaltName(dbHalt.get(i).getHaltName());
//            Log.e("data:convertHalt", dbHalt.get(i).getHaltName());
            haltList.add(halt);
        }
        return haltList;
    }


}
