package com.transport.taxi.bus.taxis.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private TextView searchHaltOrId;
    private FloatingActionButton backStack;
    private SearchPresenter searchPresenter;
    private ProgressBar progressSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = (RecyclerView) findViewById(R.id.resultRecycler);
        progressSearch = findViewById(R.id.progressSearch);
        searchHaltOrId = findViewById(R.id.searchHaltOrId);
        backStack = findViewById(R.id.searchButtonToBack);
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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);


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
        progressSearch.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        searchHaltOrId.setVisibility(View.INVISIBLE);
    }

    @Override
    public void dismissProgress() {
        progressSearch.setVisibility(View.GONE);
    }


    @Override
    public void goToSearch(List<TaxisDomain> taxisDomains) {

        if (taxisDomains.size() != 0) {
            searchHaltOrId.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            searchAdapter.setItemsTaxis(taxisDomains);
            searchAdapter.notifyDataSetChanged();
        } else {
            recyclerView.setVisibility(View.GONE);
            searchHaltOrId.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onDestroy() {            //Очистка используемой памяти
        super.onDestroy();
        searchPresenter.onDestroy();

    }
}
