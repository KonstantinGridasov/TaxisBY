package com.transport.taxi.bus.taxis.about;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.base.BaseActivity;
import com.transport.taxi.bus.taxis.splashscreen.SplashScreenActivity;

/**
 * Created by GHome on 25.02.2018.
 */

public class AboutActivity extends BaseActivity implements AboutView {
    private AboutPresenter aboutPresenter;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button ubdate = findViewById(R.id.searchUbdate);
        context = this;
        aboutPresenter = new AboutPresenter(this);

        //Кнопка для проверки обновлений
        ubdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetInfoInternet();
            }
        });

        FloatingActionButton backStack = findViewById(R.id.aboutButtonToBack);

        //Доподнительная кнопка Back
        backStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        TextView policy = findViewById(R.id.policy);
        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/minibus-minsk"));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //  Toolbar = О приложение
        getSupportActionBar().setTitle(R.string.aboutHeader);

    }

    void onGetInfoInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            aboutPresenter.ubdateToRest();
        } else {
            Toast.makeText(this, "Включите интернет",
                    Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void goToAboutUbdate(Boolean b) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        if (b) {
            builder.setTitle(R.string.ubdateHeader)
                    .setMessage(R.string.ubdateInfoTrue)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            aboutPresenter.ubdateDb();
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setCancelable(false)
                    .show();

        } else {
            builder.setTitle(R.string.ubdateHeader)
                    .setMessage(R.string.ubdateInfoFalse)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setCancelable(false)
                    .show();

        }
    }

    @Override
    public void restartApp() {
        Intent intent = new Intent(AboutActivity.this, SplashScreenActivity.class);
        finish();
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        context = null;
        aboutPresenter.onDestroy();
        aboutPresenter = null;

    }
}