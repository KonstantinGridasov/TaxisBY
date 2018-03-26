package com.transport.taxi.bus.taxis.settings.di;


import com.transport.taxi.bus.taxis.base.BasePresenter;
import com.transport.taxi.bus.taxis.halt.HaltPresenter;
import com.transport.taxi.bus.taxis.info.InfoPresenter;
import com.transport.taxi.bus.taxis.main.MainPresenter;
import com.transport.taxi.bus.taxis.rest.RestPresenter;
import com.transport.taxi.bus.taxis.search.SearchPresenter;
import com.transport.taxi.bus.taxis.searchDirect.SearchDirectPresenter;
import com.transport.taxi.bus.taxis.settings.firstStart.FirstStartMainPresenter;

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

    void inject(SearchPresenter searchPresenter);

    void inject(InfoPresenter infoPresenter);

    void inject(BasePresenter basePresenter);

    void inject(SearchDirectPresenter searchDirectPresenter);

    void inject(FirstStartMainPresenter firstStartMainPresenter);

    void inject(RestPresenter restPresenter);
}
