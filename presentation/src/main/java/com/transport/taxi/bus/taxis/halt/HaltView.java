package com.transport.taxi.bus.taxis.halt;

/**
 * Created by GHome on 24.12.2017.
 */

public interface HaltView {
    void showProgress();

    void dismissProgress();

    void showError(String error);

    void nameToHalt(String name);
}