package com.transport.taxi.bus.taxis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.transport.taxi.bus.taxis.main.MainActivity;
import com.transport.taxi.bus.taxis.settings.firstStart.FirstStartMainActivity;

/**
 * Created by GHome on 09.03.2018.
 */

public class SplashScreenActivity extends AppCompatActivity {

    public static final String SHARED_NAME_START = "com.transport.taxi.bus.taxis";
    public static final String KEY_NAME_START = "KEY_NAME_DZ";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getApplicationContext().
                getSharedPreferences(SHARED_NAME_START, Context.MODE_PRIVATE);

        if (sharedPreferences.getString(KEY_NAME_START, "").isEmpty()) {

            Intent intent = new Intent(SplashScreenActivity.this, FirstStartMainActivity.class);
            startActivity(intent);
            finish();

        } else {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}


