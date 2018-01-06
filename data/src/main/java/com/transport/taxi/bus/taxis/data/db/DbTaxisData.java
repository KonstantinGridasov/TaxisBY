package com.transport.taxi.bus.taxis.data.db;

import com.transport.taxi.bus.taxis.data.base.TaxisData;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by GHome on 02.01.2018.
 */

public class DbTaxisData extends RealmObject {
    @PrimaryKey
    private String id;

    private String name;
    private String direct_direction;
    private String reverse_direction;

    public DbTaxisData() {
    }

    public DbTaxisData(TaxisData taxisData) {
        this.id = taxisData.getId();
        this.name = taxisData.getName();
        this.direct_direction = taxisData.getDirect_direction();
        this.reverse_direction = taxisData.getReverse_direction();
    }

    public DbTaxisData(String id, String name, String direct_direction, String reverse_direction) {
        this.id = id;
        this.name = name;
        this.direct_direction = direct_direction;
        this.reverse_direction = reverse_direction;
    }


    public String getDirect_direction() {
        return direct_direction;
    }

    public void setDirect_direction(String direct_direction) {
        this.direct_direction = direct_direction;
    }

    public String getReverse_direction() {
        return reverse_direction;
    }

    public void setReverse_direction(String reverse_direction) {
        this.reverse_direction = reverse_direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}