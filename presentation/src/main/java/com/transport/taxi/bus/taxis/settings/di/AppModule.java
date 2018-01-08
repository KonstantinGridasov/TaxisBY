package com.transport.taxi.bus.taxis.settings.di;

import android.content.Context;

import com.transport.taxi.bus.taxis.domain.usecase.FillDb;
import com.transport.taxi.bus.taxis.domain.usecase.GetListDb;
import com.transport.taxi.bus.taxis.domain.usecase.GetHaltOnDb;
import com.transport.taxi.bus.taxis.domain.usecase.RemoveALLDb;
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
    GetHaltOnDb provideGetOnId(Context context) {
        return new GetHaltOnDb(appContext);
    }

    @Provides
    SearchHaltOnDb provideSearchOnDb(Context context) {
        return new SearchHaltOnDb(appContext);
    }

    @Provides
    RemoveALLDb provideRemoveALLDb(Context context) {
        return new RemoveALLDb(appContext);
    }
}
