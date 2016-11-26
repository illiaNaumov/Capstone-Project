package com.udacity.ilmov.kaizenhelper.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.ads.*;
import com.udacity.ilmov.kaizenhelper.R;
import com.udacity.ilmov.kaizenhelper.activities.ImproveProcessActivity;
import com.udacity.ilmov.kaizenhelper.adapters.ImprovementListAdapter;
import com.udacity.ilmov.kaizenhelper.data.KaizenContract;
import com.udacity.ilmov.kaizenhelper.data.KaizenProvider;
import com.udacity.ilmov.kaizenhelper.models.Improvement;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectProcessFragment extends Fragment implements  LoaderManager.LoaderCallbacks<Cursor>{


    @BindView(R.id.add_improvement_fab)
    FloatingActionButton addImprovementFAB;
    @BindView(R.id.processes_list)
    ListView improvementList;

    private AdView adView;

    private static final String [] mProjection = new String[]{
            KaizenContract.Improvements._ID,
            KaizenContract.Improvements.COLUMN_NAME_PROCESS_NAME,
            KaizenContract.Improvements.COLUMN_NAME_IMPROVER,
            KaizenContract.Improvements.COLUMN_NAME_RATING,
            KaizenContract.Improvements.COLUMN_NAME_IMPROVEMENT_DESCRIPTION
    };

    //these indices must match the projection
    private static final int INDEX_ID = 0;
    private static final int INDEX_PROCESS_NAME = 1;
    private static final int INDEX_IMPROVER = 2;
    private static final int INDEX_RATING = 3;
    private static final int INDEX_IMPROVEMENT_DESCRIPTION = 4;

    private ArrayList<Improvement> improvements;

    public SelectProcessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_select_process, container, false);
        ButterKnife.bind(this, rootView);

        getLoaderManager().initLoader(0, null, this);

        adView = (AdView)rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        return rootView;
    }

    @Override
    public void onPause() {
        adView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        adView.resume();
    }

    @Override
    public void onDestroy() {
        adView.destroy();
        super.onDestroy();
    }

    @OnClick({R.id.add_improvement_fab})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_improvement_fab:
                startActivity(new Intent(getContext(), ImproveProcessActivity.class));
                break;
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                KaizenProvider.KAIZEN_CONTENT_URI,
                mProjection,
                null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ArrayList<Improvement> currImprovements = new ArrayList<>();

        if(data != null){
            while(data.moveToNext()){
                currImprovements.add(new Improvement(data.getInt(INDEX_ID),
                        data.getString(INDEX_PROCESS_NAME),
                        data.getString(INDEX_IMPROVER),
                        data.getFloat(INDEX_RATING),
                        data.getString(INDEX_IMPROVEMENT_DESCRIPTION)));
            }
            data.close();
        }

        improvements = currImprovements;
        improvementList.setAdapter(new ImprovementListAdapter(getContext(), improvements));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
