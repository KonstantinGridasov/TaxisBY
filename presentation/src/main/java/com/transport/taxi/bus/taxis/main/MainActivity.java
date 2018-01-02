package com.transport.taxi.bus.taxis.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.transport.taxi.bus.taxis.R;

import io.reactivex.annotations.Nullable;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(presenter.adapterMain);
        presenter.onGetListClick();


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
    public void goToMain() {

    }
}
