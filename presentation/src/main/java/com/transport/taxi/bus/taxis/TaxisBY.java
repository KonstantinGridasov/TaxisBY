package com.transport.taxi.bus.taxis;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.transport.taxi.bus.taxis.settings.di.AppComponent;
import com.transport.taxi.bus.taxis.settings.di.AppModule;
import com.transport.taxi.bus.taxis.settings.di.DaggerAppComponent;

import io.fabric.sdk.android.Fabric;


/**
 * Created by GHome on 20.12.2017.
 */

public class TaxisBY extends Application {
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        final Fabric fabric = new Fabric.Builder(this)
//                .kits(new Crashlytics())
//                .debuggable(true)
//                .build();
//        Fabric.with(fabric);

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
