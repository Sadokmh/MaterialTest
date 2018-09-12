package com.example.sadokmm.materialtest;

import android.app.Application;
import android.content.Context;


//we create this class to use set the context in network.VolleySingleton class
//don't forget to add android:name=".MyApplication" in manifest file <application/>

public class MyApplication extends Application {

    private static MyApplication sInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }

    public static MyApplication getInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
