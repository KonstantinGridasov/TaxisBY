package com.transport.taxi.bus.taxis.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.transport.taxi.bus.taxis.R;

import static com.transport.taxi.bus.taxis.main.MainAdapter.KEY_ID;

/**
 * Created by GHome on 16.01.2018.
 */

public class InfoActivity extends AppCompatActivity implements InfoView {

    private InfoPresenter infoPresenter;
    private TextView textInterval;
    private TextView textWorkingTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        Intent intent = getIntent();
        String haltID = intent.getStringExtra(KEY_ID);
        textInterval = (TextView) findViewById(R.id.textInterval);
        textWorkingTime = (TextView) findViewById(R.id.textWorkingTime);

        infoPresenter = new InfoPresenter(this);
        infoPresenter.getHalt(haltID);
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
    public void nameToInfo(String interval, String working) {
        textInterval.setText(interval);
        textWorkingTime.setText(working);
    }
}
