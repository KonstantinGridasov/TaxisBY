package com.transport.taxi.bus.taxis.search;

import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;

import java.util.List;

/**
 * Created by GHome on 24.12.2017.
 */

public interface SearchView {
    void showProgress();

    void dismissProgress();

    void showError(String error);

    void goToSearch(List<TaxisDomain> taxisDomains);
}
