package com.transport.taxi.bus.taxis.data.base;

import java.util.List;

/**
 * Created by GHome on 10.01.2018.
 */

public class TaxisData {
    private String ubdate;
    private String id;
    private String inWeek;
    private String workingTime;
    private String interval;
    private String directName;
    private String reverseName;
    private List<Halt> directHalt;
    private List<Halt> reverseHalt;

    public String getUbdate() {
        return ubdate;
    }

    public void setUbdate(String ubdate) {
        this.ubdate = ubdate;
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

    public List<Halt> getDirectHalt() {
        return directHalt;
    }

    public void setDirectHalt(List<Halt> directHalt) {
        this.directHalt = directHalt;
    }

    public List<Halt> getReverseHalt() {
        return reverseHalt;
    }

    public void setReverseHalt(List<Halt> reverseHalt) {
        this.reverseHalt = reverseHalt;
    }
}


