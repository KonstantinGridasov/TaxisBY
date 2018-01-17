package com.transport.taxi.bus.taxis.settings.di;

//import com.transport.taxi.bus.taxis.halt.HaltPresenter;

import com.transport.taxi.bus.taxis.halt.HaltPresenter;
import com.transport.taxi.bus.taxis.info.InfoPresenter;
import com.transport.taxi.bus.taxis.main.MainPresenter;
import com.transport.taxi.bus.taxis.search.SearchPresenter;

import javax.inject.Singleton;

import dagger.Component;

//import com.transport.taxi.bus.taxis.searchOnDb.SearchPresenter;

/**
 * Created by GHome on 20.12.2017.
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(MainPresenter mainPresenter);

    void inject(HaltPresenter haltPresenter);

    void inject(SearchPresenter searchPresenter);

    void inject(InfoPresenter infoPresenter);

}
