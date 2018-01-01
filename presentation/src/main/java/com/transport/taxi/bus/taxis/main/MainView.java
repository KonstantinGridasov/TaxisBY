package com.transport.taxi.bus.taxis.main;

/**
 * Created by GHome on 24.12.2017.
 */

public interface MainView {
    void showProgress();

    void dismissProgress();

    void showError(String error);

    void goToMain();
}
