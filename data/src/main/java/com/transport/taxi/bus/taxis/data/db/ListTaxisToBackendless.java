package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.Halt;
import com.transport.taxi.bus.taxis.data.base.ListTaxisData;
import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbHalt;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;
import com.transport.taxi.bus.taxis.data.rest.RestService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by GHome on 19.12.2017.
 */

public class ListTaxisToBackendless {
    @Inject
    Context context;

    @Inject
    RestService restService;

    public ListTaxisToBackendless(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }

    public Observable<Void> ubdateList() {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        //Выгрузка всей базы маршруток
        List<DbTaxis> dbTaxisList = realm.copyFromRealm(
                realm.where(DbTaxis.class).findAll());
        List<TaxisData> listTaxis = new ArrayList<>();

        for (DbTaxis dbTaxis : dbTaxisList) {
            listTaxis.add(convert(dbTaxis));
            Log.e("for", "convert");
        }
        //Отправка в интрнет
        ListTaxisData newlist = new ListTaxisData();
        newlist.setList(listTaxis);

        return restService.ubdateToNet(newlist);
//        return Observable.just(true);
    }

    TaxisData convert(DbTaxis dbTaxisData) {
        TaxisData taxis = new TaxisData();
        taxis.setId(dbTaxisData.getId());
        taxis.setInterval(dbTaxisData.getInterval());
        taxis.setInWeek(dbTaxisData.getInWeek());
        taxis.setWorkingTime(dbTaxisData.getWorkingTime());
        taxis.setDirectName(dbTaxisData.getDirectName());
        taxis.setReverseName(dbTaxisData.getReverseName());
        taxis.setDirectHalt(convertHalt(dbTaxisData.getDbDirectHalt()));
        taxis.setReverseHalt(convertHalt(dbTaxisData.getDbReverseHalt()));

        return taxis;
    }

    private List<Halt> convertHalt(List<DbHalt> dbHalt) {
        List<Halt> haltList = new ArrayList<>();
        Halt haltDomain = new Halt();
        for (int i = 0; i < dbHalt.size(); i++) {
            haltDomain.setId(dbHalt.get(i).getId());
            haltDomain.setHaltName(dbHalt.get(i).getHaltName());
        }
        return haltList;
    }
}
