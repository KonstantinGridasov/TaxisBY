package com.transport.taxi.bus.taxis.data.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GHome on 10.01.2018.
 */

public class Halt {

    @SerializedName("haltname")
    private String haltName;

    private String id;

    public Halt() {
    }

    public Halt(Halt halt) {
        this.haltName = halt.getHaltName();
        this.id = halt.getId();
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
