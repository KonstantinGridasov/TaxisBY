package com.transport.taxi.bus.taxis.domain.usecase;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.Halt;
import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.GetHalt;
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

public class GetHaltOnDb extends UseCase<String, TaxisDomain> {
    private Context context;
    private GetHalt getHalt;

    @Inject
    public GetHaltOnDb(Context context) {
        this.context = context;
    }

    @Override
    protected Observable<TaxisDomain> buildUseCase(String s) {
        getHalt = new GetHalt(context);
        Log.e("Domain:GetHaltOnDb", s);
        return getHalt.getTaxisOnId(context, s).map(new Function<TaxisData, TaxisDomain>() {
            @Override
            public TaxisDomain apply(TaxisData taxisData) throws Exception {
                TaxisDomain taxis = new TaxisDomain();
                taxis.setId(taxisData.getId());
                taxis.setInterval(taxisData.getInterval());
                taxis.setInWeek(taxisData.getInWeek());
                taxis.setWorkingTime(taxisData.getWorkingTime());

                taxis.setDirectName(taxisData.getDirectName());
                taxis.setReverseName(taxisData.getReverseName());

//                Integer n = taxisData.getDirectHalt().size();
//                Log.e("getHalt", n.toString());

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
            halt.setHaltId(dbHalt.get(i).getHaltId());
            halt.setHaltName(dbHalt.get(i).getHaltName());
//            Log.e("domain:convertHalt", dbHalt.get(i).getHaltName());
            haltList.add(halt);
        }
        return haltList;
    }

}
