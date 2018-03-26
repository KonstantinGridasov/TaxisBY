package com.transport.taxi.bus.taxis.main;

import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;

import java.util.List;

/**
 * Created by GHome on 24.12.2017.
 */

public interface MainView {
    void showProgress();

    void dismissProgress();

    void goToMain(List<TaxisDomain> taxisDomains);

    void goToMainHint(List<String> hints);
}
