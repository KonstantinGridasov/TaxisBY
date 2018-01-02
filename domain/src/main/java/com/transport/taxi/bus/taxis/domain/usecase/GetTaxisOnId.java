package com.transport.taxi.bus.taxis.domain.usecase;

import android.content.Context;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.TaxisData;
import com.transport.taxi.bus.taxis.data.db.GetOnId;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.domain.base.UseCase;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by GHome on 02.01.2018.
 */

public class GetTaxisOnId extends UseCase<String, TaxisDomain> {
    private Context context;
    private GetOnId getOnId;

    public GetTaxisOnId(Context context) {
        this.context = context;
    }

    @Override
    protected Observable<TaxisDomain> buildUseCase(String s) {
        Log.e("Domain:GetTaxisOnId", s);
        return getOnId.getTaxisOnId(context, s).map(new Function<TaxisData, TaxisDomain>() {
            @Override
            public TaxisDomain apply(TaxisData taxisData) throws Exception {
                TaxisDomain taxis = new TaxisDomain();
                taxis.setId(taxisData.getId());
                taxis.setName(taxisData.getName());
                taxis.setDirect_direction(taxisData.getDirect_direction());
                taxis.setReverse_direction(taxisData.getReverse_direction());
                return taxis;
            }
        });
    }
}
