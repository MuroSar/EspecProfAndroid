package com.curso.especprofandroid;

import android.app.Application;

import io.realm.Realm;

public class EspecProfApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
