package com.transport.taxi.bus.taxis.halt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transport.taxi.bus.taxis.R;

import static com.transport.taxi.bus.taxis.main.AdapterMain.KEY_INFO;

/**
 * Created by GHome on 24.12.2017.
 */

public class HaltActivity extends AppCompatActivity {

    private HaltPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halt_activity);
        presenter = new HaltPresenter();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerHalt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(presenter.adapterHalt);
        Intent intent = getIntent();
        presenter.getOnId(intent.getStringExtra(KEY_INFO));


    }
}
