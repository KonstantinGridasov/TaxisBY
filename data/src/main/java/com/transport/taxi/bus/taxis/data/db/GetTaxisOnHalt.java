package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.base.Halt;
import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbHalt;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by GHome on 02.01.2018.
 */

public class GetTaxisOnHalt {
    @Inject
    Context context;

    public GetTaxisOnHalt(Context context) {
        this.context = context;
    }

    //Получени обеъкта из базы данных  по номеру маршрутки(id)
    public Observable<TaxisData> getTaxisOnId(String id) {
        TaxisData taxisData = new TaxisData();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        DbTaxis dbTaxisData = realm.where(DbTaxis.class)
                .equalTo("id", id)
                .findFirst();

        if (dbTaxisData != null) {
            taxisData.setId(dbTaxisData.getId());
            taxisData.setInterval(dbTaxisData.getInterval());
            taxisData.setInWeek(dbTaxisData.getInWeek());
            taxisData.setWorkingTime(dbTaxisData.getWorkingTime());
            taxisData.setDirectName(dbTaxisData.getDirectName());
            taxisData.setReverseName(dbTaxisData.getReverseName());

            taxisData.setDirectHalt(listHalt(dbTaxisData.getDbDirectHalt()));
            taxisData.setReverseHalt(listHalt(dbTaxisData.getDbReverseHalt()));
        }
        realm.close();
        return Observable.just(taxisData);
    }

    //Метод для возвращения масива останок
    private List<Halt> listHalt(List<DbHalt> dbHalt) {
        List<Halt> haltList = new ArrayList<>();
        for (int i = 0; i < dbHalt.size(); i++) {
            final Halt halt = new Halt();
            halt.setId(dbHalt.get(i).getId());
            halt.setHaltName(dbHalt.get(i).getHaltName());
            haltList.add(halt);
        }
        return haltList;
    }


}
