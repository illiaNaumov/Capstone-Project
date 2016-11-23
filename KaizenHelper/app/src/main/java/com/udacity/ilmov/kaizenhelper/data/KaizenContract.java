package com.udacity.ilmov.kaizenhelper.data;

import android.provider.BaseColumns;

/**
 * Created by ilmov
 */

public final class KaizenContract {

    public KaizenContract() {
    }

    public static abstract class Improvements implements BaseColumns{
        public static final String TABLE_NAME = "kaizen_improvements";
        public static final String COLUMN_NAME_PROCESS_NAME = "name";
        public static final String COLUMN_NAME_IMPROVER = "improver";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_IMPROVEMENT_DESCRIPTION = "description";
    }
}
