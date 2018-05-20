package com.transport.taxi.bus.taxis.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.SplashScreenActivity;
import com.transport.taxi.bus.taxis.base.BaseActivity;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;

import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity
        implements MainView, SwipeRefreshLayout.OnRefreshListener {

    private List<String> hints;
    private MainPresenter presenter;
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private ProgressBar progressBar;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        mainAdapter = new MainAdapter();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshMain);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorScheme(R.color.Blue, R.color.Green, R.color.Yellow, R.color.Red);
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

        searchView.setText("Остановка или номер маршрутки");
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
        Collections.sort(taxisDomains);
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
    public void onRefresh() {
        //Метод для обновления данных
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            swipeRefreshLayout.setRefreshing(true); // Показывает Progress
            presenter.ubdateToRest();
        } else {
            Toast.makeText(this, "Включите интернет",
                    Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void gotoMainUbdate(Boolean b) {
        swipeRefreshLayout.setRefreshing(false); //Убираем Progress
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        if (b) {
            builder.setTitle(R.string.ubdateHeader)
                    .setMessage(R.string.ubdateInfoTrue)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.ubdateDb();
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setCancelable(false)
                    .show();

        } else {
            builder.setTitle(R.string.ubdateHeader)
                    .setMessage(R.string.ubdateInfoFalse)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setCancelable(false)
                    .show();

        }
    }

    @Override
    public void restartApp() {
        Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
        finish();
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        context = null;
        hints = null;
        swipeRefreshLayout = null;
        presenter.onDestroy();
        presenter = null;
    }


}
