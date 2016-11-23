package com.udacity.ilmov.kaizenhelper.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.udacity.ilmov.kaizenhelper.R;
import com.udacity.ilmov.kaizenhelper.data.KaizenContract;
import com.udacity.ilmov.kaizenhelper.data.KaizenProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImproveProcessActivity extends AppCompatActivity {

    public static final String PROCESS_ID = "com.udacity.ilmov.kaizenhelper_process_id";
    public static final String PROCESS = "com.udacity.ilmov.kaizenhelper_process_name";
    public static final String RATING = "com.udacity.ilmov.kaizenhelper_process_rating";
    public static final String DESCRIPTION = "com.udacity.ilmov.kaizenhelper_process_description";

    @BindView(R.id.process_name)
    EditText process_name;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.process_rating)
    TextView ratingTxt;
    @BindView(R.id.improve_edit_text)
    EditText improveEditTxt;
    @BindView(R.id.done_btn)
    Button doneBtn;


    private long process_id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_process);
        ButterKnife.bind(this);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingTxt.setText(String.valueOf(rating));
            }
        });

        //set default rating
        ratingBar.setRating(3.0f);

        getImprovement();

    }

    @OnClick({R.id.done_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.done_btn:
                if (process_name.getText().toString().trim().equals("")) {
                    process_name.setError(getString(R.string.process_name_warning));
                } else {
                    saveImprovement();
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }
    }

    private void saveImprovement() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KaizenContract.Improvements.COLUMN_NAME_IMPROVER, "illia");
        contentValues.put(KaizenContract.Improvements.COLUMN_NAME_PROCESS_NAME, process_name.getText().toString());
        contentValues.put(KaizenContract.Improvements.COLUMN_NAME_RATING, ratingTxt.getText().toString());
        contentValues.put(KaizenContract.Improvements.COLUMN_NAME_IMPROVEMENT_DESCRIPTION, improveEditTxt.getText().toString());

        if (process_id != -1) {
            contentValues.put(KaizenContract.Improvements._ID, process_id);
            String mSelection = KaizenContract.Improvements._ID + " = ?";
            String[] selectionArgs = {String.valueOf(process_id)};
            getContentResolver().update(KaizenProvider.KAIZEN_CONTENT_URI, contentValues, mSelection, selectionArgs);
        } else {
            getContentResolver().insert(KaizenProvider.KAIZEN_CONTENT_URI, contentValues);
        }

    }

    private void getImprovement() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            process_id = bundle.getLong(PROCESS_ID);
            process_name.setText(bundle.getString(PROCESS));
            ratingBar.setRating(bundle.getFloat(RATING));
            improveEditTxt.setText(bundle.getString(DESCRIPTION));
        }
    }
}
