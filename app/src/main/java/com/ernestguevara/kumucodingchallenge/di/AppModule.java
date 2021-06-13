package com.ernestguevara.kumucodingchallenge.di;

import com.ernestguevara.kumucodingchallenge.util.AppExecutors;

import dagger.Module;
import dagger.Provides;

/**
 * module classes
 * provides depedency, which later will be injected
 * this provide to the Application
 */
@Module
public class AppModule {
    @Provides
    AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }
}
