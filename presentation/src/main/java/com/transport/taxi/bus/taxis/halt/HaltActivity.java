package com.transport.taxi.bus.taxis.halt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.base.BaseActivity;

import java.util.List;

import static com.transport.taxi.bus.taxis.main.MainAdapter.KEY_ID;

/**
 * Created by GHome on 24.12.2017.
 */

public class HaltActivity extends BaseActivity
        implements HaltView {

    private HaltPresenter presenter;
    private TextView nameTaxis;
    private HaltAdapter haltAdapter;
    private Boolean direct;
    private Context context;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halt);

        nameTaxis = (TextView) findViewById(R.id.nameTaxis);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerHalt);

        context = this;

        haltAdapter = new HaltAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(haltAdapter);

        FloatingActionButton backStack = findViewById(R.id.haltButtonToBack);

        //Доподнительная кнопка Back
        backStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String haltID = intent.getStringExtra(KEY_ID);

        getSupportActionBar().setTitle("№ " + haltID);

        direct = true;

        presenter = new HaltPresenter(this);
        presenter.getHalt(haltID, getDirect());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.halt_menu, menu);
        MenuItem reverse = menu.findItem(R.id.reverse);
        MenuItem menu_maps = menu.findItem(R.id.menu_maps);
        final MenuItem info_halt = menu.findItem(R.id.info_halt);

        final Intent intent = getIntent();
        presenter = new HaltPresenter(this);


        reverse.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.getHalt(intent.getStringExtra(KEY_ID), getDirect());
                return false;
            }
        });

        info_halt.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.getTaxisInfo(context, intent.getStringExtra(KEY_ID));
                return false;
            }
        });

        menu_maps.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.getMaps(context, intent.getStringExtra(KEY_ID));
                return false;
            }
        });

        return true;
    }

    Boolean getDirect() {
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
    public void nameToHalt(List<String> halt, String direction) {
        nameTaxis.setText(direction);
        haltAdapter.setItemsTaxisHalt(halt);
        haltAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

    }
}

