package com.transport.taxi.bus.taxis.data.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Created by GHome on 25.03.2018.
 */

public class ListTaxisData {
    @SerializedName("list")
    private List<TaxisData> list;

    public List<TaxisData> getList() {
        return list;
    }

    public void setList(List<TaxisData> list) {
        this.list = list;
    }
}
