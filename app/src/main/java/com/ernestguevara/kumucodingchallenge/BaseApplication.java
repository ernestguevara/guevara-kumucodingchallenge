package com.ernestguevara.kumucodingchallenge;

import com.ernestguevara.kumucodingchallenge.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Application level class
 * Makes the dagger graph application level (Add .baseapplication to manifest)
 * Will setup and create java generated codes for dagger
 */
public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
//        return null;
    }
}
