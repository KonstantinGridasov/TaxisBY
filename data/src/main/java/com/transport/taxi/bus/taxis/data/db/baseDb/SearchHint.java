package com.transport.taxi.bus.taxis.data.db.baseDb;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by GHome on 29.01.2018.
 */

public class SearchHint extends RealmObject {

    @PrimaryKey
    private String hintSearch;

    public String getHintSearch() {
        return hintSearch;
    }

    public void setHintSearch(String hintSearch) {
        this.hintSearch = hintSearch;
    }
}
