package com.transport.taxi.bus.taxis.info;

/**
 * Created by GHome on 16.01.2018.
 */

public interface InfoView {
    void showProgress();

    void dismissProgress();

    void showError(String error);

    void nameToInfo(String interval, String working);
}
