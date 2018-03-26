package com.transport.taxi.bus.taxis.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.base.BaseActivity;

/**
 * Created by GHome on 25.02.2018.
 */

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        FloatingActionButton backStack = findViewById(R.id.aboutButtonToBack);

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
        //  Toolbar = О приложение
        getSupportActionBar().setTitle("О Приложении");

    }

}