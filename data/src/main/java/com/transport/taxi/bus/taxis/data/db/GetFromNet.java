package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.transport.taxi.bus.taxis.data.base.VersionUbdate;
import com.transport.taxi.bus.taxis.data.rest.RestService;
import com.transport.taxi.bus.taxis.data.settingsDb.ReWriteUbdate;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by GHome on 01.05.2018.
 */

public class GetFromNet {
    public static final String SHARED_UBDATE = "com.transport.taxi.bus.taxis.data.base.TaxisData";
    public static final String KEY_UBDATE = "KEY_UBDATE";

    @Inject
    Context context;
    private boolean b;
    private Integer myVersion;
    private RestService restService;

    public GetFromNet(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }

    public Observable<Boolean> getUbdate() {

        //Вызов метода для записи  списка маршруток в базу данных
        ReWriteUbdate writerToDb = new ReWriteUbdate(context);
        writerToDb.reWrite();

        return Observable.just(true);
    }

    public synchronized Observable<Boolean> comparationOfVersion() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_UBDATE, Context.MODE_PRIVATE);
        myVersion = sharedPreferences.getInt(KEY_UBDATE, 20);


        return restService.
                getVersionUbdate()
                .map(new Function<VersionUbdate, Boolean>() {
                    @Override
                    public Boolean apply(VersionUbdate vers) throws Exception {
                        return myVersion < vers.getVersion();
                    }
                });
    }

    public Observable<Boolean> setVersion() {
        return restService.getVersionUbdate()
                .map(new Function<VersionUbdate, Integer>() {
                    @Override
                    public Integer apply(VersionUbdate versionUbdate) throws Exception {
                        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_UBDATE, Context.MODE_PRIVATE);
                        sharedPreferences.edit().putInt(KEY_UBDATE, versionUbdate.getVersion()).apply();
                        return versionUbdate.getVersion();
                    }
                })
                .map(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) throws Exception {
                        return true;
                    }
                });

    }

}
