package com.transport.taxi.bus.taxis.domain.usecase;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.db.GetList;
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
                .map(new Function<List<DbTaxis>, List<TaxisDomain>>() {
                    @Override
                    public List<TaxisDomain> apply(List<DbTaxis> dbtaxisData) throws Exception {
                        List<TaxisDomain> list = new ArrayList<>();
                        for (DbTaxis dbTaxisData : dbtaxisData)
                            list.add(convert(dbTaxisData));
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