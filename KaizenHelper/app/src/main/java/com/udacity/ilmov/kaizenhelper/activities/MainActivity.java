package com.udacity.ilmov.kaizenhelper.activities;

import android.database.Cursor;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.udacity.ilmov.kaizenhelper.R;
import com.udacity.ilmov.kaizenhelper.adapters.ImprovementListAdapter;
import com.udacity.ilmov.kaizenhelper.data.KaizenContract;
import com.udacity.ilmov.kaizenhelper.data.KaizenProvider;
import com.udacity.ilmov.kaizenhelper.models.Improvement;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_toolbar)
    Toolbar mToolBar;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolBar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        // Set the list's click listener
        NavigationDrawerController navigationDrawerController =
                new NavigationDrawerController(mDrawerLayout, navigationView, mToolBar, getSupportFragmentManager());





    }


}
