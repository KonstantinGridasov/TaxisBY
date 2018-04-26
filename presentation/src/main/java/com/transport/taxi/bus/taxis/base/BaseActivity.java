package com.transport.taxi.bus.taxis.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.about.AboutActivity;
import com.transport.taxi.bus.taxis.main.MainActivity;
import com.transport.taxi.bus.taxis.searchDirect.SearchDirectActivity;


/**
 * Created by GHome on 13.02.2018.
 */

// Астивити для navigationView
public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        int id = item.getItemId();

        if ((id == R.id.nav_toMain) & (getClass() != MainActivity.class)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);

        } else if ((id == R.id.searchDirect) & (getClass() != SearchDirectActivity.class)) {
            Intent intent = new Intent(this, SearchDirectActivity.class);
            startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);

        } else if ((id == R.id.about) & (getClass() != AboutActivity.class)) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}