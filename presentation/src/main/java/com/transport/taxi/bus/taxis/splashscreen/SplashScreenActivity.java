package com.transport.taxi.bus.taxis.splashscreen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.transport.taxi.bus.taxis.main.MainActivity;
import com.transport.taxi.bus.taxis.settings.firstStart.FirstStartMainActivity;

/**
 * Created by GHome on 09.03.2018.
 */

public class SplashScreenActivity extends AppCompatActivity {

    public static final String SHARED_BASE = "com.transport.taxi.bus.taxis";
    public static final String SHARED_KEY = "SHARED_KEY";


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences =
                getSharedPreferences(SHARED_BASE, Context.MODE_PRIVATE);

        if (sharedPreferences.getString(SHARED_KEY, "").isEmpty()) {
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED)
                    startMainFirst();
                else ActivityCompat.requestPermissions(SplashScreenActivity.this, new String[]
                        {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else startMain();
        } else startMain();
    }


    void startMain() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    void startMainFirst() {
        Intent intent = new Intent(SplashScreenActivity.this, FirstStartMainActivity.class);
        startActivity(intent);
        finish();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startMainFirst();
                } else {
                    Toast.makeText(getApplicationContext(), "Приложение завершает работу", Toast.LENGTH_LONG).show();
                    finish();
                    break;
                }
        }

    }

}
