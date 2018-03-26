package com.transport.taxi.bus.taxis.halt;

import java.util.List;

/**
 * Created by GHome on 24.12.2017.
 */

public interface HaltView {
    void showProgress();

    void dismissProgress();

    void nameToHalt(List<String> halt, String direction);
}
