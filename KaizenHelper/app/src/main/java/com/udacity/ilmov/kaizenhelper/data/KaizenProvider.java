package com.udacity.ilmov.kaizenhelper.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by ilmov
 */

public class KaizenProvider extends ContentProvider {


        private static final int URI_KAIZEN = 6;
        private static final int URI_KAIZEN_BY_ID = 7;

        private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        public static final String AUTHORITY = "com.udacity.ilmov.kaizenhelper";

        public static final Uri KAIZEN_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + KaizenContract.Improvements.TABLE_NAME);

        private DatabaseHelper dbHelper;

        @Override
        public boolean onCreate() {
            dbHelper = new DatabaseHelper(getContext());

            sUriMatcher.addURI(AUTHORITY, KaizenContract.Improvements.TABLE_NAME, URI_KAIZEN);
            sUriMatcher.addURI(AUTHORITY, KaizenContract.Improvements.TABLE_NAME + "/#", URI_KAIZEN_BY_ID);

            return true;
        }

        @Nullable
        @Override
        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

            switch (sUriMatcher.match(uri)) {
                case URI_KAIZEN:
                    if (TextUtils.isEmpty(sortOrder)) {
                        sortOrder = "_ID ASC";
                    }
                    break;
                case URI_KAIZEN_BY_ID:
                    selection = "_ID = " + uri.getLastPathSegment();
                    break;
                default:
                    break;
            }

            SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
            builder.setTables(KaizenContract.Improvements.TABLE_NAME);

            Cursor query = builder.query(dbHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
            query.setNotificationUri(getContext().getContentResolver(), uri);

            return query;
        }

        @Nullable
        @Override
        public String getType(Uri uri) {
            return null;
        }

        @Nullable
        @Override
        public Uri insert(Uri uri, ContentValues contentValues) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            try {
                db.beginTransaction();
                long rowID = db.insert(KaizenContract.Improvements.TABLE_NAME, KaizenContract.Improvements._ID, contentValues);
                db.setTransactionSuccessful();

                Uri resultUri = ContentUris.withAppendedId(uri, rowID);
                getContext().getContentResolver().notifyChange(resultUri, null);
                return resultUri;
            } catch (SQLException ex) {
                ex.printStackTrace();
                Log.e(this.getClass().getName(), "Insert query errors, see logs.");
            } finally {
                db.endTransaction();
            }

            return null;
        }

        @Override
        public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            try {
                db.beginTransaction();
                int updated = db.update(KaizenContract.Improvements.TABLE_NAME, contentValues, selection, selectionArgs);
                db.setTransactionSuccessful();


                getContext().getContentResolver().notifyChange(uri, null);

                return updated;
            } catch (SQLException ex) {
                ex.printStackTrace();
                Log.e(this.getClass().getName(), "Update query errors, see logs.");
            } finally {
                db.endTransaction();
            }

            return 0;
        }

        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            try {
                db.beginTransaction();
                int deleted = db.delete(KaizenContract.Improvements.TABLE_NAME, selection, selectionArgs);
                db.setTransactionSuccessful();

                getContext().getContentResolver().notifyChange(uri, null);

                return deleted;
            } catch (SQLException ex) {
                ex.printStackTrace();
                Log.e(this.getClass().getName(), "Delete query errors, see logs.");
            } finally {
                db.endTransaction();
            }

            return 0;
        }

    public static Uri buildLocationUri(long id) {
        return ContentUris.withAppendedId(KAIZEN_CONTENT_URI, id);
    }

}
