package com.transport.taxi.bus.taxis.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.base.BaseActivity;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;

import java.util.List;

import static com.transport.taxi.bus.taxis.main.MainPresenter.KEY_SEARCH;

/**
 * Created by GHome on 03.01.2018.
 */

public class SearchActivity extends BaseActivity
        implements SearchView {

    private RecyclerView recyclerViewS;
    private SearchAdapter searchAdapter;
    private TextView searchHaltOrId;
    private SearchPresenter searchPresenter;
    private ProgressBar progressSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        progressSearch = findViewById(R.id.progressSearch);

        searchHaltOrId = findViewById(R.id.searchHaltOrId);

        FloatingActionButton backStack = findViewById(R.id.searchButtonToBack);
        //Доподнительная кнопка Back
        backStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        searchHaltOrId.setVisibility(View.INVISIBLE);
        progressSearch.setVisibility(View.INVISIBLE);

        searchAdapter = new SearchAdapter();

        recyclerViewS = (RecyclerView) findViewById(R.id.resultRecycler);
        recyclerViewS.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewS.setAdapter(searchAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        searchPresenter = new SearchPresenter(this);

        Intent intent = getIntent();
        String searchText = intent.getStringExtra(KEY_SEARCH);

        searchPresenter.onSearchInDb(searchText);

        getSupportActionBar().setTitle("\"Поиск : " + searchText + " \"");

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {
    }


    @Override
    public void goToSearch(List<TaxisDomain> taxisDomains) {

        if (taxisDomains.size() != 0) {
            searchHaltOrId.setVisibility(View.GONE);
            searchAdapter.setItemsTaxis(taxisDomains);
            searchAdapter.notifyDataSetChanged();
        } else {
            recyclerViewS.setVisibility(View.GONE);
            searchHaltOrId.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onDestroy() {            //Очистка используемой памяти
        super.onDestroy();
        searchPresenter.onDestroy();

    }
}
