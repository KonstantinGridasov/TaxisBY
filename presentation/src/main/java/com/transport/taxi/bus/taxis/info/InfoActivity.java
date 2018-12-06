package com.transport.taxi.bus.taxis.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.base.BaseActivity;

import static com.transport.taxi.bus.taxis.main.MainAdapter.KEY_ID;

/**
 * Created by GHome on 16.01.2018.
 */

public class InfoActivity extends BaseActivity
        implements InfoView {

    private InfoPresenter infoPresenter;
    private TextView textInterval, textWorkingTime, textInWeek;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        textInterval = (TextView) findViewById(R.id.textInterval);
        textWorkingTime = (TextView) findViewById(R.id.textWorkingTime);
        textInWeek = (TextView) findViewById(R.id.textInWeek);
        FloatingActionButton backStack = findViewById(R.id.infoButtonToBack);
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

        getSupportActionBar().setTitle(haltID);
        getSupportActionBar().setHomeButtonEnabled(true);

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
    public void nameToInfo(String interval, String working, String inWeek) {
        String s = "По данному маршруту информации нет";

        if (interval.isEmpty() | working.isEmpty() | inWeek.isEmpty())
            interval = working = inWeek = s;

        textInterval.setText(interval);
        textWorkingTime.setText(working);
        textInWeek.setText(inWeek);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        infoPresenter.onDestroy();
    }
}
