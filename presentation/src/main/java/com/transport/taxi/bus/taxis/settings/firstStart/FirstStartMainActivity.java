package com.transport.taxi.bus.taxis.settings.firstStart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.splashscreen.SplashScreenActivity;

import static com.transport.taxi.bus.taxis.splashscreen.SplashScreenActivity.SHARED_BASE;
import static com.transport.taxi.bus.taxis.splashscreen.SplashScreenActivity.SHARED_KEY;

/**
 * Created by GHome on 23.03.2018.
 */

public class FirstStartMainActivity extends AppCompatActivity
        implements FirstStartMainView {

    private SharedPreferences sharedPreferences;
    private FirstStartMainPresenter firstStartMainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_start_main_activity);
        Context context = this;
        firstStartMainPresenter = new FirstStartMainPresenter(this);

        sharedPreferences = getApplicationContext().
                getSharedPreferences(SHARED_BASE, Context.MODE_PRIVATE);

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);

        builder.setTitle("Первый запуск")
                .setMessage("Для корректной работы необходимо упорядочить данные")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Thread timer = new Thread() {
                            public void run() {
                                try {
                                    sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } finally {
                                    firstStartMainPresenter.onFillDb();
                                }
                            }
                        };
                        timer.start();
                    }
                })
                .setCancelable(false)
                .show();


    }

    @Override
    public void gotoFirstStartMain() {
        sharedPreferences.edit().putString(SHARED_KEY, "2.0").apply();
        Intent intent = new Intent(FirstStartMainActivity.this, SplashScreenActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        firstStartMainPresenter.onDestroy();
        sharedPreferences = null;
        firstStartMainPresenter = null;
    }
}
