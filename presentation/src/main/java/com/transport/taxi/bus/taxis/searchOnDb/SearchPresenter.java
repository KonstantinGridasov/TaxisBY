package com.transport.taxi.bus.taxis.searchOnDb;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.usecase.SearchHaltOnDb;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 24.12.2017.
 */

public class SearchPresenter {

    @Inject
    SearchHaltOnDb searchHaltOnDb;

    private SearchView searchView;
    private List<TaxisDomain> taxisDomainsRes;

    public SearchPresenter(SearchView searchView) {
        this.searchView = searchView;
        TaxisBY.appComponent.inject(this);

    }

    public void onSearchInDb(String halt) {
        String res = obrez(halt);
        taxisDomainsRes = new ArrayList<>();
        searchHaltOnDb.execute(res, new DisposableObserver<List<TaxisDomain>>() {
            @Override
            public void onNext(List<TaxisDomain> taxisDomains) {
                taxisDomainsRes = taxisDomains;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                searchView.goToSearch(taxisDomainsRes);
            }
        });

    }

    private String obrez(String s) {
        String k;
        int nach = 0;
        int kon = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            if (' ' != s.charAt(i)) {
                nach = i;
                break;
            }
        }
        for (int i = s.length() - 1; i > 0; i--) {
            if (' ' != s.charAt(i)) {
                kon = i + 1;
                break;
            }
        }
        k = s.substring(nach, kon);
        return k;


    }
}

