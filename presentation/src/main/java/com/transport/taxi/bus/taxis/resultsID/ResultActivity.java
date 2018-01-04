package com.transport.taxi.bus.taxis.resultsID;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transport.taxi.bus.taxis.R;

import static com.transport.taxi.bus.taxis.main.MainPresenter.KEY_SEARCH;

/**
 * Created by GHome on 03.01.2018.
 */

public class ResultActivity extends AppCompatActivity {
    ResultPresenter resultPresenter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        resultPresenter = new ResultPresenter();
        recyclerView = (RecyclerView) findViewById(R.id.resultRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(resultPresenter.adapterResult);
        Intent intent = getIntent();
        resultPresenter.onSearchInDb(intent.getStringExtra(KEY_SEARCH));

    }
}
