package com.transport.taxi.bus.taxis.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.base.BaseActivity;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;

import java.util.List;

public class MainActivity extends BaseActivity
        implements MainView {

    private List<String> hints;
    private MainPresenter presenter;
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        mainAdapter = new MainAdapter();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().setTitle(R.string.name_rus_app);

        presenter = new MainPresenter(this);

        presenter.onGetList();           //Получение всего списка

        presenter.onHintHalt();         // Получение полного списка подсказок

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        // Адаптер необходимый для формирования подсказок

        final ArrayAdapterSearchView searchView = (ArrayAdapterSearchView) searchItem.getActionView();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.hint_spinner_item, hints);


        searchView.setAdapter(adapter);

        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchView.setText(adapter.getItem(position).toString());

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Вызов метода выполняющего поиск
                presenter.onClickSearch(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void dismissProgress() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void goToMain(List<TaxisDomain> taxisDomains) {
        //Передача элементов адаптеру
        mainAdapter.setItemsTaxis(taxisDomains);
        // Перерисовка
        mainAdapter.notifyDataSetChanged();


    }

    @Override
    public void goToMainHint(List<String> hints) {
        //Метод для заполнения полного списка подсказок
        this.hints = hints;
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        hints = null;
        presenter.onDestroy();

    }
}
