package com.igorkazakov.user.redminepro.application;

import android.support.multidex.MultiDexApplication;

import com.igorkazakov.user.redminepro.utils.PreferenceUtils;

import io.realm.Realm;

/**
 * Created by user on 11.07.17.
 */

public class RedmineApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        PreferenceUtils.createPreferenceUtils(this);
    }

    @Override
    public void onTerminate() {
        PreferenceUtils.releasePreferenceUtils();
        super.onTerminate();
    }
}
