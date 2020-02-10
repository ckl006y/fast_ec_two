package com.example.fast_ec;

import android.app.Application;

import com.example.ck_core.app.Latte;

public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://mock.fulingjie.com/mock-android/")
                .configure();
    }
}
