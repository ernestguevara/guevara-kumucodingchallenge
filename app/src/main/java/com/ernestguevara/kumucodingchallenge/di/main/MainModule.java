package com.ernestguevara.kumucodingchallenge.di.main;

import com.ernestguevara.kumucodingchallenge.db.AppDatabase;
import com.ernestguevara.kumucodingchallenge.network.retrofit.api.ItunesApi;
import com.ernestguevara.kumucodingchallenge.network.retrofit.api.LocationApi;
import com.ernestguevara.kumucodingchallenge.ui.main.MainRepository;
import com.ernestguevara.kumucodingchallenge.util.AppExecutors;

import dagger.Module;
import dagger.Provides;

/**
 * main module class
 * provides depedency, which later will be injected at main
 * this provide to the Application
 */
@Module
public class MainModule {

    @Provides
    public MainRepository provideMainRepository(ItunesApi itunesApi, LocationApi locationApi, AppExecutors appExecutors, AppDatabase appDatabase) {
        return new MainRepository(itunesApi, locationApi, appExecutors, appDatabase);
    }
}
