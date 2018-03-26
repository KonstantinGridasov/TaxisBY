package com.transport.taxi.bus.taxis.searchDirect;

import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;

import java.util.List;

/**
 * Created by GHome on 24.12.2017.
 */

public interface SearchDirectView {
    void showProgress();

    void dismissProgress();

    void showError(String error);

    void goToSearchDirect(List<TaxisDomain> taxisDomains);

    void goToSearchDirectHint(List<String> hints);
}
