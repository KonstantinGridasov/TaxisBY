package com.transport.taxi.bus.taxis.resultsID;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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
        String res = "\"Результаты поиска\"";

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarResult);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(res);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(R.drawable.arrow_back);

//        toolbar.Click(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "s" , Toast.LENGTH_SHORT)
//                        .show();
//            }
//        });

        resultPresenter = new ResultPresenter();
        recyclerView = (RecyclerView) findViewById(R.id.resultRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(resultPresenter.adapterResult);
        Intent intent = getIntent();
        resultPresenter.onSearchInDb(intent.getStringExtra(KEY_SEARCH));

    }
}
