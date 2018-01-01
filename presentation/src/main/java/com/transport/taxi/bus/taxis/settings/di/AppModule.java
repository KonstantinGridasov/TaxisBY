package com.transport.taxi.bus.taxis.settings.di;

import android.content.Context;


import com.transport.taxi.bus.taxis.domain.usecase.FillDataBase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by GHome on 20.12.2017.
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public FillDataBase providerFill(Context context){
        return new FillDataBase(context);
    }
}