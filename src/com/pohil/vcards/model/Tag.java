package com.pohil.vcards.model;


import android.database.Cursor;
import com.pohil.vcards.dao.TagDao;
import com.pohil.vcards.dao.WordDao;

public class Tag {

    public String uid;
    public String name;

    public Tag(Cursor cursor) {
        uid = cursor.getString(cursor.getColumnIndex(TagDao.COLUMN_UID));
        name = cursor.getString(cursor.getColumnIndex(WordDao.COLUMN_NAME));
    }

}
