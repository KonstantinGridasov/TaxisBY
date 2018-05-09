package com.transport.taxi.bus.taxis.data.db.baseDb;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by GHome on 10.01.2018.
 */

public class DbTaxis extends RealmObject {
    @PrimaryKey
    private String id;

    private String inWeek;
    private String workingTime;
    private String interval;
    private String directName;
    private String reverseName;
    private RealmList<DbHalt> dbDirectHalt;
    private RealmList<DbHalt> dbReverseHalt;


    public RealmList<DbHalt> getDbDirectHalt() {
        return dbDirectHalt;
    }

    public void setDbDirectHalt(RealmList<DbHalt> dbDirectHalt) {
        this.dbDirectHalt = dbDirectHalt;
    }

    public RealmList<DbHalt> getDbReverseHalt() {
        return dbReverseHalt;
    }

    public void setDbReverseHalt(RealmList<DbHalt> dbReverseHalt) {
        this.dbReverseHalt = dbReverseHalt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInWeek() {
        return inWeek;
    }

    public void setInWeek(String inWeek) {
        this.inWeek = inWeek;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getDirectName() {
        return directName;
    }

    public void setDirectName(String directName) {
        this.directName = directName;
    }

    public String getReverseName() {
        return reverseName;
    }

    public void setReverseName(String reverseName) {
        this.reverseName = reverseName;
    }


}

