package com.transport.taxi.bus.taxis.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.searchDirect.SearchDirectActivity;

/**
 * Created by GHome on 23.03.2018.
 */

public class FirstStartSearchDirect extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_search_direct);


        Button button = findViewById(R.id.buttonFirstSearchDirect);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstStartSearchDirect.this, SearchDirectActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
