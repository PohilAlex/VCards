package com.pohil.vcards.dao;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.pohil.vcards.model.Word;

import java.util.ArrayList;

public class WordDao extends BaseDao {

    public static final String COLUMN_WORD = "Word";
    public static final String COLUMN_TRANSLATION = "Translation";


    public WordDao(SQLiteDatabase database) {
        super(database);
    }

    public ArrayList<Word> getAllWords() {
        String sql = "SELECT * FROM Words";
        Cursor cursor = database.rawQuery(sql, null);
        ArrayList<Word> list = new ArrayList<Word>();
        if (cursor.moveToFirst()) {
            do {
                list.add(new Word(cursor));
            } while (cursor.isAfterLast());
        }
        cursor.close();
        return list;
    }
}
