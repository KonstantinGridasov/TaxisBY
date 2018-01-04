package com.transport.taxi.bus.taxis.domain.usecase;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.db.DbTaxisData;
import com.transport.taxi.bus.taxis.data.db.GetList;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.base.UseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * Created by GHome on 20.12.2017.
 */

public class GetListDb extends UseCase<Void, List<TaxisDomain>> {
    private GetList getList;

    private Context context;

    @Inject
    public GetListDb(Context context) {
        this.context = context;
    }

    @Override
    protected Observable<List<TaxisDomain>> buildUseCase(Void aVoid) {
        Log.e("domain", "GetListDb");
        getList = new GetList(context);
        return getList
                .getList(context)
                .map(new Function<List<DbTaxisData>, List<TaxisDomain>>() {
                    @Override
                    public List<TaxisDomain> apply(List<DbTaxisData> dbtaxisData) throws Exception {
                        List<TaxisDomain> list = new ArrayList<>();
                        for (DbTaxisData dbTaxisData : dbtaxisData)
                            list.add(convert(dbTaxisData));
                        return list;
                    }
                });
    }

    private TaxisDomain convert(DbTaxisData dbTaxisData) {
        TaxisDomain taxis = new TaxisDomain();
        taxis.setId(dbTaxisData.getId());
        taxis.setName(dbTaxisData.getName());
        taxis.setDirect_direction(dbTaxisData.getDirect_direction());
        taxis.setReverse_direction(dbTaxisData.getReverse_direction());
        return taxis;
    }
}