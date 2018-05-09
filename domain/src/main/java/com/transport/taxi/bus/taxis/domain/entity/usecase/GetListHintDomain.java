package com.transport.taxi.bus.taxis.domain.entity.usecase;

import com.transport.taxi.bus.taxis.data.db.GetFromDb;
import com.transport.taxi.bus.taxis.data.db.baseDb.SearchHint;
import com.transport.taxi.bus.taxis.domain.entity.base.UseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * Created by GHome on 20.12.2017.
 */

public class GetListHintDomain extends UseCase<Void, List<String>> {
    @Inject
    GetFromDb getFromDb;

    public GetListHintDomain(GetFromDb getFromDb) {
        this.getFromDb = getFromDb;
    }



   //Получение всего списка подсказок из базы данных
    @Override
    protected Observable<List<String>> buildUseCase(Void aVoid) {

        return getFromDb
                .getListHint()
                .map(new Function<List<SearchHint>, List<String>>() {
                    @Override
                    public List<String> apply(List<SearchHint> searchHints) throws Exception {
                        List<String> list = new ArrayList<>();
                        for (SearchHint searchHint : searchHints)
                            list.add(convert(searchHint));
                        return list;
                    }
                });
    }

    private String convert(SearchHint hint) {
        String hints;
        hints = hint.getHintSearch();
        return hints;
    }
    @Override
    public void dispose() {
        super.dispose();

    }

}





