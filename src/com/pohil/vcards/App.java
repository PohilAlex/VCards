package com.pohil.vcards;


import android.app.Application;

public class App extends Application {

    DbManager dbManager;

    @Override
    public void onCreate() {
        super.onCreate();
        dbManager = new DbManager(this);
        dbManager.init();
    }
}
