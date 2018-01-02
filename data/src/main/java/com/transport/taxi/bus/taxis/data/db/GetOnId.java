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
    private Realm realm;
    private Context context;

    public Observable<TaxisData> getTaxisOnId(Context context, String track) {   //Получени обеъкта по ID
        final TaxisData taxisData = new TaxisData();

        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        this.taxisData = realm.where(TaxisData.class)                       //Поиск в базе по track(т.е по ID)
                .equalTo("track", track)
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
