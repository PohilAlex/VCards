package com.pohil.vcards.model;


import android.database.Cursor;
import com.pohil.vcards.dao.WordDao;

public class Word {

    String word;
    String translation;

    public Word(Cursor cursor) {
        word = cursor.getString(cursor.getColumnIndex(WordDao.COLUMN_WORD));
        translation = cursor.getString(cursor.getColumnIndex(WordDao.COLUMN_TRANSLATION));
    }

}
