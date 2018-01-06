package com.transport.taxi.bus.taxis.domain.usecase;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.SearchHalt;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.base.UseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by GHome on 02.01.2018.
 */

public class SearchHaltOnDb extends UseCase<String, List<TaxisDomain>> {
    private Context context;
    private SearchHalt searchHalt;

    @Inject
    public SearchHaltOnDb(Context context) {
        this.context = context;
    }

    @Override
    protected Observable<List<TaxisDomain>> buildUseCase(String s) {
        searchHalt = new SearchHalt(context);
        return searchHalt.searchHalt(context, s).map(new Function<List<TaxisData>, List<TaxisDomain>>() {
            @Override
            public List<TaxisDomain> apply(List<TaxisData> taxisData) throws Exception {
                List<TaxisDomain> list = new ArrayList<>();
                for (TaxisData taxis : taxisData)
                    list.add(convert(taxis));
                return list;
            }
        });
    }

    private TaxisDomain convert(TaxisData taxisData) {
        TaxisDomain taxis = new TaxisDomain();
        taxis.setId(taxisData.getId());
        taxis.setName(taxisData.getName());
        taxis.setDirect_direction(taxisData.getDirect_direction());
        taxis.setReverse_direction(taxisData.getReverse_direction());
        return taxis;
    }

}
