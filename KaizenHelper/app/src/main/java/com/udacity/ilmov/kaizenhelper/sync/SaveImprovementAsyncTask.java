package com.udacity.ilmov.kaizenhelper.sync;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import com.udacity.ilmov.kaizenhelper.data.KaizenContract;
import com.udacity.ilmov.kaizenhelper.data.KaizenProvider;
import com.udacity.ilmov.kaizenhelper.models.Improvement;

/**
 * Created by ilmov.
 */

public class SaveImprovementAsyncTask extends AsyncTask<Improvement, Void, Void> {


    private Context mContext;

    public SaveImprovementAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected Void doInBackground(Improvement... params) {
        Improvement improvement = params[0];
        ContentValues contentValues = new ContentValues();
        contentValues.put(KaizenContract.Improvements.COLUMN_NAME_IMPROVER, "user");
        contentValues.put(KaizenContract.Improvements.COLUMN_NAME_PROCESS_NAME, improvement.getName());
        contentValues.put(KaizenContract.Improvements.COLUMN_NAME_RATING, improvement.getRating());
        contentValues.put(KaizenContract.Improvements.COLUMN_NAME_IMPROVEMENT_DESCRIPTION, improvement.getWhatToImprove());

        if (improvement.getId() != -1) {
            contentValues.put(KaizenContract.Improvements._ID, improvement.getId());
            String mSelection = KaizenContract.Improvements._ID + " = ?";
            String[] selectionArgs = {String.valueOf(improvement.getId())};
            mContext.getContentResolver().update(KaizenProvider.KAIZEN_CONTENT_URI, contentValues, mSelection, selectionArgs);
        } else {
            mContext.getContentResolver().insert(KaizenProvider.KAIZEN_CONTENT_URI, contentValues);
        }

        return null;
    }
}
