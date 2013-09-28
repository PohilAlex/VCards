package com.pohil.vcards.model;


import android.database.Cursor;
import com.pohil.vcards.dao.WordDao;

public class Word {

    public String uid;
    public String word;
    public String translation;

    public Word(Cursor cursor) {
        word = cursor.getString(cursor.getColumnIndex(WordDao.COLUMN_UID));
        word = cursor.getString(cursor.getColumnIndex(WordDao.COLUMN_WORD));
        translation = cursor.getString(cursor.getColumnIndex(WordDao.COLUMN_TRANSLATION));
    }

}
