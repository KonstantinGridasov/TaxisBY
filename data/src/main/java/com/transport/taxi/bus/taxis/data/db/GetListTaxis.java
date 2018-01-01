package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.TaxisData;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by GHome on 19.12.2017.
 */

public class GetListTaxis {
    private TaxisData taxisData;
    private Realm realm;
    private Context context;

    public GetListTaxis(Context context) {
        this.context = context;
    }

    public Observable<List<TaxisData>> getProducts(Context context) {      //Выгрузка все базы Realm
        Log.e("Data", "GetListTaxis");
        Realm.init(context);
        realm = Realm.getDefaultInstance();
        List<TaxisData> dbTrackList = realm.copyFromRealm(
                realm.where(TaxisData.class).findAll());
        realm.close();

        return Observable.fromArray(dbTrackList);
    }
}
