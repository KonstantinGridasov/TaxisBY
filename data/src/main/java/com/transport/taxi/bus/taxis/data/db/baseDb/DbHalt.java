package com.transport.taxi.bus.taxis.data.db.baseDb;

import io.realm.RealmObject;

/**
 * Created by GHome on 10.01.2018.
 */

public class DbHalt extends RealmObject {
    private String id;
    private String haltName;
    private Double lat;
    private Double lng;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getHaltName() {
        return haltName;
    }

    public void setHaltName(String haltName) {
        this.haltName = haltName;
    }
}
