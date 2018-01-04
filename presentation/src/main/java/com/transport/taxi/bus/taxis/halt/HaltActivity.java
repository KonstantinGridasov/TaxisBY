package com.transport.taxi.bus.taxis.halt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.transport.taxi.bus.taxis.R;

import static com.transport.taxi.bus.taxis.main.AdapterMain.KEY_ID;
import static com.transport.taxi.bus.taxis.main.AdapterMain.KEY_NAME;

/**
 * Created by GHome on 24.12.2017.
 */

public class HaltActivity extends AppCompatActivity implements HaltView {

    private HaltPresenter presenter;
    private RecyclerView recyclerView;
    private TextView nameTaxis;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halt_activity);
        Intent intent = getIntent();
        String haltID = intent.getStringExtra(KEY_ID);
        String haltNAME = "\"" + intent.getStringExtra(KEY_NAME) + "\"";

        nameTaxis = (TextView) findViewById(R.id.nameTaxis);
        nameTaxis.setText(haltNAME);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarHalt);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(haltID);

        presenter = new HaltPresenter();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerHalt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(presenter.adapterHalt);
        presenter.getOnId(haltID);

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
    public void nameToHalt(String name) {
    }
}

