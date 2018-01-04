package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.TaxisData;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;

/**
 * Created by GHome on 02.01.2018.
 */

public class GetOnId {
   private TaxisData taxisData;
    private DbTaxisData dbTaxisData;
    private Realm realm;
    private Context context;

    public GetOnId(Context context) {
        this.context = context;
    }

    public Observable<TaxisData> getTaxisOnId(Context context, String id) {   //Получени обеъкта по ID
        taxisData=new TaxisData();
        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        dbTaxisData = realm.where(DbTaxisData.class)                       //Поиск в базе по track(т.е по ID)
                .equalTo("id", id)
                .findFirst();
        Log.e("Data:getTaxisOnId", dbTaxisData.getName());

        if (dbTaxisData != null) {
            taxisData.setId(dbTaxisData.getId());
            taxisData.setName(dbTaxisData.getName());
            taxisData.setDirect_direction(dbTaxisData.getDirect_direction());
            taxisData.setReverse_direction(dbTaxisData.getReverse_direction());
            Log.e("inIf",taxisData.getName());
            dbTaxisData.addChangeListener(new RealmChangeListener<RealmModel>() {    //Подписка на изменения
                @Override
                public void onChange(RealmModel realmModel) {

                    Log.e("loadData", "  ChangeListener");
                    taxisData.setId(dbTaxisData.getId());
                    taxisData.setName(dbTaxisData.getName());
                    taxisData.setDirect_direction(dbTaxisData.getDirect_direction());
                    taxisData.setReverse_direction(dbTaxisData.getReverse_direction());
                }
            });

        }
        Log.e("Data:_return", "taxisdata="+taxisData.getName());

        realm.close();
        return Observable.just(taxisData);
    }

}
