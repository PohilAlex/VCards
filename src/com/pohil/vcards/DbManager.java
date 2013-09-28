package com.pohil.vcards;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.pohil.vcards.dao.TagDao;
import com.pohil.vcards.dao.WordDao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DbManager {

    private final static String DB_NAME = "vcards.sqlite";
    Context context;
    SQLiteDatabase database;

    WordDao wordDao;
    TagDao tagDao;

    public DbManager(Context context) {
        this.context = context;
    }

    public void init() {
        File f = new File(getDBPath());
        if (!f.exists()) {
            copyDbFromAssetes();
        }
        database = SQLiteDatabase.openDatabase(getDBPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        wordDao = new WordDao(database);
        tagDao = new TagDao(database);
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    private void copyDbFromAssetes() {
        try {
            InputStream input = context.getAssets().open(DB_NAME);
            OutputStream output = new FileOutputStream(getDBPath());
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception ex) {
            Log.e("TEST", ex.toString());
        }
    }

    private String getDBPath() {
        File cacheDir;
        /*if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            cacheDir = context.getExternalCacheDir();
        } else {
            cacheDir = context.getCacheDir();
        }*/
        cacheDir = context.getCacheDir();
        return cacheDir.getPath() + File.separator + DB_NAME;
    }

    public WordDao getWordDao() {
        return wordDao;
    }

    public TagDao getTagDao() {
        return tagDao;
    }
}
