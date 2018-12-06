package com.transport.taxi.bus.taxis.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.base.BaseActivity;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;

import static com.transport.taxi.bus.taxis.main.MainAdapter.KEY_ID;

public class MapsActivity extends BaseActivity implements MapsView {

    private MapsAdapter mapsAdapter;
    private MapsPresenter presenter;

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.framecontainer, fragment);
        if (addToBackStack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        presenter = new MapsPresenter(this);

        Intent intent = getIntent();
        String haltID = intent.getStringExtra(KEY_ID);

        if (haltID != null)
            presenter.getTaxis(haltID);

        else onReadyTaxis(null);

        mapsAdapter = new MapsAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_item_maps);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mapsAdapter);

        mapsAdapter.setItemsTaxisHalt(presenter.getListId());
        mapsAdapter.notifyDataSetChanged();

    }


    @Override
    public void onReadyTaxis(TaxisDomain t) {
        if (t != null) {
            showFragment(getSupportFragmentManager(), FragmentMaps.newInstance(t), false);
            getSupportActionBar().setTitle("Карта маршрута: №" + t.getId());

        } else
            showFragment(getSupportFragmentManager(), new FragmentMaps(), false);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapsAdapter = null;
        presenter = null;
    }
}