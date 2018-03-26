package com.transport.taxi.bus.taxis.data.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GHome on 10.01.2018.
 */

public class TaxisData {

    @SerializedName("id")
    private String id;

    @SerializedName("inweek")
    private String inWeek;

    @SerializedName("workingtime")
    private String workingTime;

    @SerializedName("interval_")
    private String interval;

    @SerializedName("directname")
    private String directName;

    @SerializedName("reversename")
    private String reverseName;

    @SerializedName("haltdirect")
    private ListHalt directHalt;

    @SerializedName("haltreverse")
    private ListHalt reverseHalt;

    public TaxisData() {
    }

    public TaxisData(TaxisData taxisData) {
        this.id = taxisData.getId();
        this.inWeek = taxisData.getInWeek();
        this.workingTime = taxisData.getWorkingTime();
        this.interval = taxisData.getInterval();
        this.directName = taxisData.getDirectName();
        this.reverseName = taxisData.getReverseName();
        this.directHalt = taxisData.directHalt;
        this.reverseHalt = taxisData.reverseHalt;
    }

    public ListHalt getDirectHalt() {
        return directHalt;
    }

    public void setDirectHalt(ListHalt halt) { this.directHalt = halt;
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

    public ListHalt getReverseHalt() {
        return reverseHalt;
    }

    public void setReverseHalt(ListHalt reverseHalt) {
        this.reverseHalt = reverseHalt;
    }
}


