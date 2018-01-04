package com.transport.taxi.bus.taxis;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.transport.taxi.bus.taxis.settings.di.AppComponent;
import com.transport.taxi.bus.taxis.settings.di.AppModule;
import com.transport.taxi.bus.taxis.settings.di.DaggerAppComponent;


/**
 * Created by GHome on 20.12.2017.
 */

public class TaxisBY extends Application {
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
