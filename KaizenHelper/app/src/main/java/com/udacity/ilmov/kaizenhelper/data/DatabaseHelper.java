package com.udacity.ilmov.kaizenhelper.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ilmov
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "haizenhelper.db";

    public static final String SQL_CREATE_KAIZEN = "CREATE TABLE " +
            KaizenContract.Improvements.TABLE_NAME + " ( " +
            KaizenContract.Improvements._ID + " INTEGER PRIMARY KEY," +
            KaizenContract.Improvements.COLUMN_NAME_PROCESS_NAME + " STRING NOT NULL, " +
            KaizenContract.Improvements.COLUMN_NAME_IMPROVER + " INTEGER NOT NULL, " +
            KaizenContract.Improvements.COLUMN_NAME_RATING + " STRING NOT NULL, " +
            KaizenContract.Improvements.COLUMN_NAME_IMPROVEMENT_DESCRIPTION + " STRING" +
            " )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_KAIZEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DatabaseHelper", "ON UPGRADE NOT IMPLEMENTED");
    }
}
