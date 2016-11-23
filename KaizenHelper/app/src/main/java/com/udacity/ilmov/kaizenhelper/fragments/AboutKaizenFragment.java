package com.udacity.ilmov.kaizenhelper.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.ilmov.kaizenhelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutKaizenFragment extends Fragment {

    @BindView(R.id.about_kaizen_image_view)
    ImageView kaizenImage;

    public AboutKaizenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_about_kaizen, container, false);
        ButterKnife.bind(this, rootView);

        Picasso.with(getContext()).load("http://www.vincentwee.com/wp-content/uploads/2016/02/Kaizen.png")
                .into(kaizenImage);

        return rootView;
    }

}
