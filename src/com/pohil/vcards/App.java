package com.pohil.vcards;


import android.app.Application;

public class App extends Application {

    static DbManager dbManager;

    @Override
    public void onCreate() {
        super.onCreate();
        dbManager = new DbManager(this);
        dbManager.init();
    }

    public static DbManager getDbManager() {
        return dbManager;
    }


}
