package com.transport.taxi.bus.taxis.settings.di;

import android.content.Context;

import com.transport.taxi.bus.taxis.domain.usecase.FillDb;
import com.transport.taxi.bus.taxis.domain.usecase.GetListDb;
import com.transport.taxi.bus.taxis.domain.usecase.GetOnIdDb;
import com.transport.taxi.bus.taxis.domain.usecase.SearchHaltOnDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by GHome on 20.12.2017.
 */
@Module
public class AppModule {

    private Context appContext;

    public AppModule(Context context) {
        appContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }

    @Provides
    GetListDb provideGetDataBase() {
        return new GetListDb(appContext);
    }

    @Provides
    FillDb provideFillDataBase(Context context) {
        return new FillDb(appContext);
    }

    @Provides
    GetOnIdDb provideGetOnId(Context context) {
        return new GetOnIdDb(appContext);
    }

    @Provides
    SearchHaltOnDb provideSearchOnDb(Context context) {
        return new SearchHaltOnDb(appContext);
    }
}
