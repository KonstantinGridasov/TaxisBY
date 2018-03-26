package com.transport.taxi.bus.taxis.data.db.baseDb;

import io.realm.RealmObject;

/**
 * Created by GHome on 10.01.2018.
 */

public class DbHalt extends RealmObject {
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
