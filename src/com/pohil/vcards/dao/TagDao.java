package com.pohil.vcards.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.pohil.vcards.model.Tag;

import java.util.ArrayList;

public class TagDao extends BaseDao {

    public TagDao(SQLiteDatabase database) {
        super(database);
    }

    public ArrayList<Tag> getAllTags() {
        String sql = "SELECT * FROM Tags";
        Cursor cursor = database.rawQuery(sql, null);
        ArrayList<Tag> list = new ArrayList<Tag>();
        if (cursor.moveToFirst()) {
            do {
                list.add(new Tag(cursor));
            } while (cursor.moveToNext());
        }
        return list;
    }



}
