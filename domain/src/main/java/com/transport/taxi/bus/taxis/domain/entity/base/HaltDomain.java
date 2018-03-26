package com.transport.taxi.bus.taxis.domain.entity.base;

/**
 * Created by GHome on 10.01.2018.
 */

public class HaltDomain {
    private String id;
    private String haltName;

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
