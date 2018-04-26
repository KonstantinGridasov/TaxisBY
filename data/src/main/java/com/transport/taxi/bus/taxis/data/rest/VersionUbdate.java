package com.transport.taxi.bus.taxis.data.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GHome on 17.04.2018.
 */

public class VersionUbdate {
    @SerializedName("versionTaxis")
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
