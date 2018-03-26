package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.db.baseDb.SearchHint;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by GHome on 19.12.2017.
 */

public class GetListHint {

    @Inject
    Context context;

    public GetListHint(Context context) {
        this.context = context;
    }

    //Выгрузка всей базы Realm подсказок(остановок)
    public Observable<List<SearchHint>> getList() {

        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        List<SearchHint> searchHints = realm.copyFromRealm(
                realm.where(SearchHint.class).findAll());
        realm.close();

        return Observable.fromArray(searchHints);
    }
}
