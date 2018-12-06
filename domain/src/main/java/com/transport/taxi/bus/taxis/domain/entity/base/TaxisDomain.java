package com.transport.taxi.bus.taxis.domain.entity.base;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GHome on 20.12.2017.
 */

public class TaxisDomain implements Comparable<TaxisDomain>, Serializable {
    private String id;
    private String inWeek;
    private String workingTime;
    private String interval;
    private String directName;
    private String reverseName;
    private List<HaltDomain> directHalt;
    private List<HaltDomain> reverseHalt;


    public List<HaltDomain> getDirectHalt() {
        return directHalt;
    }

    public void setDirectHalt(List<HaltDomain> halt) {
        this.directHalt = halt;
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

    public List<HaltDomain> getReverseHalt() {
        return reverseHalt;
    }

    public void setReverseHalt(List<HaltDomain> reverseHalt) {
        this.reverseHalt = reverseHalt;
    }

    @Override
    public int compareTo(@NonNull TaxisDomain o) {
        return id.compareTo(o.getId());
    }
}
