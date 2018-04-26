package com.transport.taxi.bus.taxis.data.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.transport.taxi.bus.taxis.data.rest.RestService;
import com.transport.taxi.bus.taxis.data.rest.VersionUbdate;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by GHome on 17.04.2018.
 */

public class GetVersionUbdate {
    public static final String SHARED_UBDATE = "com.transport.taxi.bus.taxis.data.base.TaxisData";
    public static final String KEY_UBDATE = "KEY_UBDATE";

    @Inject
    Context context;
    private boolean b;
    private Integer myVersion;
    private RestService restService;

    public GetVersionUbdate(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }

    public synchronized Observable<Boolean> comparationOfVersion() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_UBDATE, Context.MODE_PRIVATE);
        myVersion = sharedPreferences.getInt(KEY_UBDATE, 0);


        return restService.
                getVersionUbdate()
                .map(new Function<VersionUbdate, Boolean>() {
                    @Override
                    public Boolean apply(VersionUbdate vers) throws Exception {
                        b = (myVersion < vers.getVersion());
                        return b;
                    }
                });
    }

}