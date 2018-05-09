package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.base.Halt;
import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.GetFromDb;
import com.transport.taxi.bus.taxis.domain.entity.base.HaltDomain;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by GHome on 02.01.2018.
 */

public class GetTaxisOnHaltDomain extends UseCase<String, TaxisDomain> {

    @Inject
    GetFromDb getFromDb;

    public GetTaxisOnHaltDomain( GetFromDb getFromDb) {
        this.getFromDb = getFromDb;
    }

    //Получение одной маршурутки из базы данных по остановке
    @Override
    protected Observable<TaxisDomain> buildUseCase(String s) {
        return getFromDb.getTaxisOnId(s).map(new Function<TaxisData, TaxisDomain>() {
            @Override
            public TaxisDomain apply(TaxisData taxisData) throws Exception {
                TaxisDomain taxis = new TaxisDomain();
                taxis.setId(taxisData.getId());
                taxis.setInterval(taxisData.getInterval());
                taxis.setInWeek(taxisData.getInWeek());
                taxis.setWorkingTime(taxisData.getWorkingTime());
                taxis.setDirectName(taxisData.getDirectName());
                taxis.setReverseName(taxisData.getReverseName());
                taxis.setDirectHalt(convertHalt(taxisData.getDirectHalt()));
                taxis.setReverseHalt(convertHalt(taxisData.getReverseHalt()));
                return taxis;
            }
        });
    }

    private List<HaltDomain> convertHalt(List<Halt> dbHalt) {
        List<HaltDomain> haltList = new ArrayList<>();
        for (int i = 0; i < dbHalt.size(); i++) {
            final HaltDomain halt = new HaltDomain();
            halt.setId(dbHalt.get(i).getId());
            halt.setHaltName(dbHalt.get(i).getHaltName());
            haltList.add(halt);
        }
        return haltList;
    }

}
