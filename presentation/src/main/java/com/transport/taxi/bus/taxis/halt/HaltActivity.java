package com.transport.taxi.bus.taxis.halt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.transport.taxi.bus.taxis.R;

import java.util.List;

import static com.transport.taxi.bus.taxis.main.MainAdapter.KEY_ID;
import static com.transport.taxi.bus.taxis.main.MainAdapter.KEY_NAME;

/**
 * Created by GHome on 24.12.2017.
 */

public class HaltActivity extends AppCompatActivity implements HaltView {
    private HaltPresenter presenter;
    private RecyclerView recyclerView;
    private TextView nameTaxis;
    private HaltAdapter haltAdapter;
    private Boolean direct;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halt_activity);
        Intent intent = getIntent();
        String haltID = intent.getStringExtra(KEY_ID);
        String haltNAME = "\"" + intent.getStringExtra(KEY_NAME) + "\"";

        nameTaxis = (TextView) findViewById(R.id.nameTaxis);
//        nameTaxis.setText(haltNAME);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarHalt);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Маршрутка № " + haltID);

        presenter = new HaltPresenter(this);
        haltAdapter = new HaltAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerHalt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(haltAdapter);

        direct = true;
        presenter.getHalt(haltID, getDirect());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final Intent intent = getIntent();
        presenter = new HaltPresenter(this);
        getMenuInflater().inflate(R.menu.halt_menu, menu);
        MenuItem reves = menu.findItem(R.id.reverse);
        reves.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.getHalt(intent.getStringExtra(KEY_ID), getDirect());
                return false;
            }
        });
        return true;
    }

    public Boolean getDirect() {
        if (direct) {
            direct = false;
            return true;
        } else {
            direct = true;
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.reverse) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    public void nameToHalt(List<String> halt, String direction) {
        nameTaxis.setText(direction);
        haltAdapter.setItemsTaxisHalt(halt);
        haltAdapter.notifyDataSetChanged();
    }
}

