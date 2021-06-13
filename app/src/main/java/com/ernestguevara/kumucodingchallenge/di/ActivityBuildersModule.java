package com.ernestguevara.kumucodingchallenge.di;

import com.ernestguevara.kumucodingchallenge.di.main.MainFragmentBuildersModule;
import com.ernestguevara.kumucodingchallenge.di.main.MainModule;
import com.ernestguevara.kumucodingchallenge.di.main.MainViewModelModule;
import com.ernestguevara.kumucodingchallenge.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Let dagger know all activity is a dagger client
 * Codes are convention for dagger
 * All codes are on the java(generated folder) which dagger rewrite every rebuild
 */

@Module
public abstract class ActivityBuildersModule {

    // Contributes MainActivity
    @ContributesAndroidInjector(modules = {
            MainModule.class,
            MainViewModelModule.class,
            MainFragmentBuildersModule.class
    })
    abstract MainActivity mainActivity();
}
