package com.transport.taxi.bus.taxis.searchDirect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.base.BaseActivity;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;
import com.transport.taxi.bus.taxis.settings.FirstStartSearchDirect;

import java.util.List;

public class SearchDirectActivity extends BaseActivity
        implements SearchDirectView {

    private static final String SHARED_SEARCH_DIRECT = "com.transport.taxi.bus.taxis";
    private static final String KEY_SEARCH_DIRECT = "KEY_SEARCH_DIRECT";
    private Button buttonDirect;
    private AutoCompleteTextView firstHalt, secondHalt;
    private RecyclerView recyclerViewResult;
    private SearchDirectAdapter directAdapter;
    private SearchDirectPresenter directPresenter;
    private Context context;
    private TextView textSearchDirectResult;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_direct);

        directPresenter = new SearchDirectPresenter(this);
        directAdapter = new SearchDirectAdapter();

        SharedPreferences sharedPreferences = getApplicationContext().
                getSharedPreferences(SHARED_SEARCH_DIRECT, Context.MODE_PRIVATE);

        if (sharedPreferences.getString(KEY_SEARCH_DIRECT, "").isEmpty()) {

            Intent intent = new Intent(SearchDirectActivity.this, FirstStartSearchDirect.class);
            startActivity(intent);
            finish();
            sharedPreferences.edit().putString(KEY_SEARCH_DIRECT, "Yes").apply();


        }

        progressBar = findViewById(R.id.progressSearchDirect);
        textSearchDirectResult = findViewById(R.id.searchDirectResult);
        firstHalt = findViewById(R.id.firstHalt);
        secondHalt = findViewById(R.id.secondHalt);
        recyclerViewResult = findViewById(R.id.recyclerDirectSearch);
        buttonDirect = findViewById(R.id.buttonSearchDirect);


        FloatingActionButton backStack = findViewById(R.id.searchDirectButtonToBack); //Доподнительная кнопка Back
        backStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        context = getApplicationContext();

        recyclerViewResult.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewResult.setAdapter(directAdapter);

        progressBar.setVisibility(View.INVISIBLE);
        textSearchDirectResult.setVisibility(View.INVISIBLE);

        buttonDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstHalt.getText().toString().isEmpty() |
                        secondHalt.getText().toString().isEmpty()) {
                    Toast.makeText(v.getContext(),
                            " Заполните поля!", Toast.LENGTH_SHORT).show();
                } else
                    directPresenter.searchFirstHalt(firstHalt.getText().toString(),
                            secondHalt.getText().toString());


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        directPresenter = new SearchDirectPresenter(this);
        directPresenter.onHintHalt();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewResult.setVisibility(View.INVISIBLE);
        textSearchDirectResult.setVisibility(View.INVISIBLE);
    }

    @Override
    public void dismissProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void goToSearchDirect(List<TaxisDomain> taxisDomains) {
        if (taxisDomains.size() != 0) {
            textSearchDirectResult.setVisibility(View.GONE);
            recyclerViewResult.setVisibility(View.VISIBLE);

            directAdapter.setItemsTaxis(taxisDomains);
            directAdapter.notifyDataSetChanged();
        } else {
            recyclerViewResult.setVisibility(View.GONE);
            textSearchDirectResult.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void goToSearchDirectHint(List<String> hints) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                R.layout.hint_spinner_item, hints);
        firstHalt.setAdapter(adapter);
        secondHalt.setAdapter(adapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        directPresenter.onDestroy();
        firstHalt = null;
        secondHalt = null;
        buttonDirect = null;
        recyclerViewResult = null;
        directAdapter = null;
        context = null;
        textSearchDirectResult = null;
        directPresenter = null;

    }
}
