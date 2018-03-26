package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.base.Halt;
import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.GetListTaxisFromNet;
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

public class GetListTaxisNet extends UseCase<Void, List<TaxisDomain>> {

    @Inject
    GetListTaxisFromNet getListTaxis;

    public GetListTaxisNet(GetListTaxisFromNet getListTaxis) {
        this.getListTaxis = getListTaxis;
    }


    //Получение всего списка маршуруток из интернета
    @Override
    protected Observable<List<TaxisDomain>> buildUseCase(Void aVoid) {
        return getListTaxis
                .getList()
                .map(new Function<List<TaxisData>, List<TaxisDomain>>() {
                    @Override
                    public List<TaxisDomain> apply(List<TaxisData> dbtaxisData) throws Exception {
                        List<TaxisDomain> list = new ArrayList<>();
                        for (TaxisData taxisData : dbtaxisData)
                            list.add(convert(taxisData));
                        return list;
                    }
                });
    }

    private TaxisDomain convert(TaxisData taxisData) {
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


    private List<HaltDomain> convertHalt(List<Halt> halts) {
        List<HaltDomain> haltList = new ArrayList<>();
        HaltDomain haltDomain = new HaltDomain();
        for (int i = 0; i < halts.size(); i++) {
            haltDomain.setId(halts.get(i).getId());
            haltDomain.setHaltName(halts.get(i).getHaltName());
        }
        return haltList;
    }
}