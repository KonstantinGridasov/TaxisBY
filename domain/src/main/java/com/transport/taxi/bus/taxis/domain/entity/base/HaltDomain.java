package com.transport.taxi.bus.taxis.domain.entity.base;

import java.io.Serializable;

/**
 * Created by GHome on 10.01.2018.
 */

public class HaltDomain implements Serializable {
    private String id;
    private String haltName;
    private Double lat;

    private Double lng;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHaltName() {
        return haltName;
    }

    public void setHaltName(String haltName) {
        this.haltName = haltName;
    }
}
