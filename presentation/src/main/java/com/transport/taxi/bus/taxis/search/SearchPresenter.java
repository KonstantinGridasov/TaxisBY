package com.transport.taxi.bus.taxis.search;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetListTaxisOnHaltDomain;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by GHome on 24.12.2017.
 */

public class SearchPresenter {

    @Inject
    GetListTaxisOnHaltDomain getListTaxisOnHaltDomain;

    private SearchView searchView;
    private List<TaxisDomain> taxisDomainsRes;


    SearchPresenter(SearchView searchView) {
        this.searchView = searchView;
        TaxisBY.appComponent.inject(this);

    }

    void onSearchInDb(String halt) {
        String res = obrez(halt);
        taxisDomainsRes = new ArrayList<>();
        getListTaxisOnHaltDomain.execute(res, new DisposableObserver<List<TaxisDomain>>() {
            @Override
            public void onNext(List<TaxisDomain> taxisDomains) {
                searchView.showProgress();
                taxisDomainsRes = taxisDomains;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                searchView.dismissProgress();
                searchView.goToSearch(taxisDomainsRes);
            }
        });

    }

    private String obrez(String s) { //Метод для удаления пробелов после и перед словом поиска
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

    void onDestroy() {
        searchView=null;
        taxisDomainsRes = null;
    }
}

