package com.transport.taxi.bus.taxis.data.db.baseDb;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by GHome on 10.01.2018.
 */

public class DbHalt extends RealmObject{
    private String haltId;

    private String haltName;


    public String getHaltId() {
        return haltId;
    }

    public void setHaltId(String haltId) {
        this.haltId = haltId;
    }

    public String getHaltName() {
        return haltName;
    }

    public void setHaltName(String haltName) {
        this.haltName = haltName;
    }
}
