package com.transport.taxi.bus.taxis.data.settingsDb;

import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.TaxisData;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;

/**
 * Created by GHome on 31.12.2017.
 */

public class SearchOnDb {
    private TaxisData taxisData;
    private Realm realm;

    public Observable<TaxisData> getIdTaxis(String id) {   //Получени обеъкта по ID
        realm = Realm.getDefaultInstance();
        taxisData = realm.where(TaxisData.class)                       //Поиск в базе по track(т.е по ID)
                .equalTo("id", id)
                .findFirst();

        if (taxisData != null) {
            taxisData.setId(taxisData.getId());
            taxisData.addChangeListener(new RealmChangeListener<RealmModel>() {    //Подписка на изменения
                @Override
                public void onChange(RealmModel realmModel) {

                    Log.e("loadData", "  ChangeListener");
                    taxisData.setId(taxisData.getId());
                }
            });

        }
        realm.close();
        return Observable.just(taxisData);
    }
}
