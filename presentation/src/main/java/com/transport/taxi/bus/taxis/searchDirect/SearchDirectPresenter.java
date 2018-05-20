package com.transport.taxi.bus.taxis.searchDirect;

import android.content.Context;

import com.transport.taxi.bus.taxis.TaxisBY;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetListHintDomain;
import com.transport.taxi.bus.taxis.domain.entity.usecase.GetListTaxisOnHaltDomain;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;


/**
 * Created by GHome on 24.12.2017.
 */

public class SearchDirectPresenter {
    //    public static final String KEY_SEARCH = "com.transport.taxi.bus.taxis.main";
    @Inject
    GetListTaxisOnHaltDomain getListTaxisOnHaltDomain;
    @Inject
    Context context;
    @Inject
    GetListHintDomain getListHintDomain;

    private List<String> hintHalts;
    private SearchDirectView searchDirectView;
    private List<TaxisDomain> taxisDomainsRes1, taxisDomainsRes2, resultList;

    SearchDirectPresenter(SearchDirectView view) {
        this.searchDirectView = view;
        TaxisBY.appComponent.inject(this);
    }


    List<String> onHintHalt() {
        hintHalts = new ArrayList<>();
        getListHintDomain.execute(null, new DisposableObserver<List<String>>() {
            @Override
            public void onNext(List<String> searchHintDomains) {
                hintHalts = searchHintDomains;

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                searchDirectView.goToSearchDirectHint(hintHalts);

            }
        });
        return hintHalts;
    }

    void searchFirstHalt(String halt1, final String halt2) {
        String res1 = obrez(halt1);
        taxisDomainsRes1 = new ArrayList<>();
        getListTaxisOnHaltDomain.execute(res1, new DisposableObserver<List<TaxisDomain>>() {
            @Override
            public void onNext(List<TaxisDomain> taxisDomains) {

                searchDirectView.showProgress();
                taxisDomainsRes1 = taxisDomains;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                searchSecondHalt(halt2, taxisDomainsRes1);
            }
        });


    }

    private void searchSecondHalt(String halt2, final List<TaxisDomain> taxisDomains) {
        String res2 = obrez(halt2);
        taxisDomainsRes2 = new ArrayList<>();
        getListTaxisOnHaltDomain.execute(res2, new DisposableObserver<List<TaxisDomain>>() {
            @Override
            public void onNext(List<TaxisDomain> taxisDomains) {
                taxisDomainsRes2 = taxisDomains;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                returnResult(taxisDomains, taxisDomainsRes2);
            }
        });
    }

    private void returnResult(List<TaxisDomain> res1, List<TaxisDomain> res2) {
        resultList = new ArrayList<>();
        for (int n = 0; n < res1.size(); n++) {
            for (int j = 0; j < res2.size(); j++) {
                if (res1.get(n).getId().equals(res2.get(j).getId())) {
                    resultList.add(res1.get(n));
                    break;
                }
            }
        }
        searchDirectView.dismissProgress();
        searchDirectView.goToSearchDirect(resultList);

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
        searchDirectView = null;
        hintHalts = null;
        taxisDomainsRes1 = null;
        taxisDomainsRes2 = null;
        resultList = null;
    }

}

