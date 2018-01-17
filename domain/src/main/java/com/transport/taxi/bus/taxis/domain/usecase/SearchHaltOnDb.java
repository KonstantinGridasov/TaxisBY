package com.transport.taxi.bus.taxis.domain.usecase;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.db.SearchHalt;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbHalt;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;
import com.transport.taxi.bus.taxis.domain.base.HaltDomain;
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
        return searchHalt.searchHalt(s).map(new Function<List<DbTaxis>, List<TaxisDomain>>() {
            @Override
            public List<TaxisDomain> apply(List<DbTaxis> dbTaxisList) throws Exception {
                List<TaxisDomain> list = new ArrayList<>();
                for (DbTaxis taxis : dbTaxisList)
                    list.add(convert(taxis));
                return list;
            }
        });
    }

    private TaxisDomain convert(DbTaxis dbTaxisData) {
        TaxisDomain taxis = new TaxisDomain();
        taxis.setId(dbTaxisData.getId());
        taxis.setInterval(dbTaxisData.getInterval());
        taxis.setInWeek(dbTaxisData.getInWeek());
        taxis.setWorkingTime(dbTaxisData.getWorkingTime());
        taxis.setDirectName(dbTaxisData.getDirectName());
        taxis.setReverseName(dbTaxisData.getReverseName());
        taxis.setDirectHalt(convertHalt(dbTaxisData.getDbDirectHalt()));
        taxis.setReverseHalt(convertHalt(dbTaxisData.getDbReverseHalt()));


        return taxis;
    }


    private List<HaltDomain> convertHalt(List<DbHalt> dbHalt) {
        List<HaltDomain> haltList = new ArrayList<>();
        HaltDomain haltDomain = new HaltDomain();
        for (int i = 0; i < dbHalt.size(); i++) {
            haltDomain.setHaltId(dbHalt.get(i).getHaltId());
            haltDomain.setHaltName(dbHalt.get(i).getHaltName());
//            Log.e("domain:convertHalt", dbHalt.get(i).getHaltName());

        }

        return haltList;
    }

}
