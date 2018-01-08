package com.transport.taxi.bus.taxis.searchOnDb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;

import java.util.List;

import static com.transport.taxi.bus.taxis.main.MainPresenter.KEY_SEARCH;

/**
 * Created by GHome on 03.01.2018.
 */

public class SearchActivity extends AppCompatActivity implements SearchView {
    private SearchPresenter searchPresenter;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        Intent intent = getIntent();
        String searchText = intent.getStringExtra(KEY_SEARCH);
        String res = "\"Поиск по " + searchText + "\"";

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarResult);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(res);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(R.drawable.arrow_back);

        searchPresenter = new SearchPresenter(this);
        searchAdapter = new SearchAdapter();

        recyclerView = (RecyclerView) findViewById(R.id.resultRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);

        searchPresenter.onSearchInDb(searchText);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void goToSearch(List<TaxisDomain> taxisDomains) {
        searchAdapter.setItemsTaxis(taxisDomains);
        searchAdapter.notifyDataSetChanged();
    }

}
