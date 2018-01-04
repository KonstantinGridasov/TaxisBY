package com.transport.taxi.bus.taxis.domain.usecase;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.GetOnId;
import com.transport.taxi.bus.taxis.data.db.SearchHalt;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.base.UseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by GHome on 02.01.2018.
 */

public class SearchHaltOnDb extends UseCase<String, List<String>> {
    private Context context;
    private SearchHalt searchHalt;

    @Inject
    public SearchHaltOnDb(Context context) {
        this.context = context;
    }

    @Override
    protected Observable <List<String>> buildUseCase(String s) {
        searchHalt=new SearchHalt(context);
        Log.e("Domain:SearchHaltOnDb", s);
        return searchHalt.searchHalt(context,s);
    }
}
