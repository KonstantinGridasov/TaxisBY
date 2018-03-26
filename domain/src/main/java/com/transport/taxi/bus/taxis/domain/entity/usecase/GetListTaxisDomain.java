package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.db.GetListTaxis;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbHalt;
import com.transport.taxi.bus.taxis.data.db.baseDb.DbTaxis;
import com.transport.taxi.bus.taxis.domain.entity.base.HaltDomain;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * Created by GHome on 20.12.2017.
 */

public class GetListTaxisDomain extends UseCase<Void, List<TaxisDomain>> {

    @Inject
    GetListTaxis getListTaxis;

    public GetListTaxisDomain(GetListTaxis getListTaxis) {
        this.getListTaxis = getListTaxis;
    }


    //Получение всего списка маршуруток из базы данных
    @Override
    protected Observable<List<TaxisDomain>> buildUseCase(Void aVoid) {
        return getListTaxis
                .getList()
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
            haltDomain.setId(dbHalt.get(i).getId());
            haltDomain.setHaltName(dbHalt.get(i).getHaltName());
        }
        return haltList;
    }
}