package com.udacity.ilmov.kaizenhelper.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.ilmov.kaizenhelper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvitePeopleFragment extends Fragment {


    public InvitePeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invite_people, container, false);
    }

}
