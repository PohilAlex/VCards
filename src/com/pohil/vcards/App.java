package com.pohil.vcards;


import android.app.Application;
import android.util.Log;

public class App extends Application {

    static DbManager dbManager;

    @Override
    public void onCreate() {
        super.onCreate();
        dbManager = new DbManager(this);
        dbManager.init();
        /*DeviceUuidFactory factory = new DeviceUuidFactory(this);
        String uuid = factory.getDeviceUuid().toString();
        Log.d("TEST", uuid);*/
    }

    public static DbManager getDbManager() {
        return dbManager;
    }


}
