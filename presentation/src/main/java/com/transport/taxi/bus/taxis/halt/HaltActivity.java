package com.transport.taxi.bus.taxis.halt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.transport.taxi.bus.taxis.R;

/**
 * Created by GHome on 24.12.2017.
 */

public class HaltActivity extends AppCompatActivity {

    private HaltPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halt_activity);
        presenter.getOnId();


    }
}
