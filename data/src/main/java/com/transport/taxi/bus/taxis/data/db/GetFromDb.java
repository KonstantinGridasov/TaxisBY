package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.base.Halt;
import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbHalt;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;
import com.transport.taxi.bus.taxis.data.db.baseDb.SearchHint;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by GHome on 01.05.2018.
 */

public class GetFromDb {
    @Inject
    Context context;

    public GetFromDb(Context context) {
        this.context = context;
    }

    //Выгрузка всей базы маршруток
    public Observable<List<DbTaxis>> getList() {
        List<DbTaxis> dbTaxisList = null;

        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        dbTaxisList = realm.copyFromRealm(
                realm.where(DbTaxis.class).findAll());
        return Observable.fromArray(dbTaxisList);

    }

    //Выгрузка всей базы Realm подсказок(остановок)
    public Observable<List<SearchHint>> getListHint() {

        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        List<SearchHint> searchHints = realm.copyFromRealm(
                realm.where(SearchHint.class).findAll());
        realm.close();

        return Observable.fromArray(searchHints);

    }


    //Получение списка объектов(маршруток) по остановке
    public Observable<List<DbTaxis>> searchHalt(String halt) {
        List<DbTaxis> list = new ArrayList<>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        List<DbTaxis> dbTaxisDataList = realm.copyFromRealm(
                realm.where(DbTaxis.class).findAll());
        realm.close();

        for (int i = 0; i < dbTaxisDataList.size(); i++) {

            //Объеденение 2ух массивов и поиск внутри их
            List<DbHalt> listRes = new ArrayList<>();
            listRes.addAll(dbTaxisDataList.get(i).getDbDirectHalt());
            listRes.addAll(dbTaxisDataList.get(i).getDbReverseHalt());

            for (int j = 0; j < listRes.size(); j++) {
                if (listRes.get(j).getHaltName().toLowerCase().contains(halt.toLowerCase())
                        | (listRes.get(j).getId()).contains(halt)) {
                    list.add(dbTaxisDataList.get(i));
                    break;
                }
            }

        }


        return Observable.fromArray(list);
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
