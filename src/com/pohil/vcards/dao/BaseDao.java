package com.pohil.vcards.dao;

import android.database.sqlite.SQLiteDatabase;

public class BaseDao {

    public static final String COLUMN_UID = "Uid";
    public static final String COLUMN_NAME = "Name";

    protected SQLiteDatabase database;

    public BaseDao(SQLiteDatabase database) {
        this.database = database;
    }
}
