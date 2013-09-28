package com.pohil.vcards.dao;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.pohil.vcards.model.Tag;
import com.pohil.vcards.model.Word;

import java.util.ArrayList;

public class WordDao extends BaseDao {

    public static final String UID_WORD = "Uid";
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

    public ArrayList<Word> getTagWord(Tag tag) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM Words w ");
        queryBuilder.append("   JOIN TagsWord tw on  w.Uid = tw.WordUid");
        queryBuilder.append("   JOIN Tags t on  t.Uid = tw.TagUid ");
        queryBuilder.append("WHERE  t.Uid = " + quoteString(tag.uid));
        Cursor cursor = database.rawQuery(queryBuilder.toString(), null);
        ArrayList<Word> list = new ArrayList<Word>();
        if (cursor.moveToFirst()) {
            do {
                list.add(new Word(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }


}
