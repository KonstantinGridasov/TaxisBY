package com.transport.taxi.bus.taxis.rest;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.transport.taxi.bus.taxis.R;

import io.reactivex.functions.Consumer;

public class RestActivity extends AppCompatActivity
        implements RestView {

    private Button ubdateToRest, getFromRest;
    private TextView textFromRest;
    private RestPresenter restPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        RxPermissions rxPermissions = new RxPermissions(RestActivity.this);
        rxPermissions
                .request(Manifest.permission.INTERNET)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean)
                            Toast.makeText(RestActivity.this, "True ", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(RestActivity.this, "False ", Toast.LENGTH_SHORT).show();
                    }
                });

        restPresenter = new RestPresenter(this);
        ubdateToRest = findViewById(R.id.ubdateToRest);
        getFromRest = findViewById(R.id.getFromRest);
        textFromRest = findViewById(R.id.textFromRest);

        ubdateToRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                restPresenter.ubdateToRest();
            }
        });

        getFromRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restPresenter.getListNet();
            }
        });
    }

    @Override
    public void gotoMainRest(String s) {
        Log.e("Rest", s);
        textFromRest.setText(s);

    }
}
