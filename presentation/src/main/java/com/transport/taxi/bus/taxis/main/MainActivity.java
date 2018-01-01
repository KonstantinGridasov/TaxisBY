package com.transport.taxi.bus.taxis.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.transport.taxi.bus.taxis.R;

public class MainActivity extends AppCompatActivity implements MainView {
    RecyclerView recyclerView;
    AdapterMainRecycler adapterMainRecycler;
    TextView textid;
    Button button1;
    Button button2;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapterMainRecycler);
        AdapterMainRecycler adapterMainRecycler = new AdapterMainRecycler(mainPresenter.onGetListClick());


        mainPresenter = new MainPresenter(this);

        textid = (TextView) findViewById(R.id.texiID);

        button1 = findViewById(R.id.FillDb);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.onFillDataBaseClick();
            }
        });
        button2 = findViewById(R.id.setText);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void returnHalt(String s) { //Разборка всей строки из остановок на одельные строки
        int n = 0;

        for (int i = n; i < s.length(); i++) {
            char x = s.charAt(i);
            if (',' == x) {
                System.out.println(s.substring(n, i));
                n = i + 1;
            }
        }

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
