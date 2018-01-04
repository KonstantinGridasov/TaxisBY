package com.transport.taxi.bus.taxis.settings.di;

import com.transport.taxi.bus.taxis.halt.HaltPresenter;
import com.transport.taxi.bus.taxis.main.AdapterMain;
import com.transport.taxi.bus.taxis.main.MainPresenter;
import com.transport.taxi.bus.taxis.resultsID.ResultPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by GHome on 20.12.2017.
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(MainPresenter mainPresenter);

    void inject(HaltPresenter haltPresenter);

    void inject(ResultPresenter resultPresenter);
}
