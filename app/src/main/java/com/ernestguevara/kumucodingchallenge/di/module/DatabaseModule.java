package com.ernestguevara.kumucodingchallenge.di.module;


import android.app.Application;

import com.ernestguevara.kumucodingchallenge.db.AppDatabase;
import com.ernestguevara.kumucodingchallenge.db.dao.ResultsListDao;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

/**
 * Database module
 * Provides all the database dependencies for the app
 */
@Module
public class DatabaseModule {
    @Singleton
    @Provides
    AppDatabase providesRoomDatabase(Application application) {
    return Room.databaseBuilder(application, AppDatabase.class, "itunes.db")
    .fallbackToDestructiveMigration()
    .build();
    }

    @Singleton
    @Provides
    ResultsListDao providesResultsListDao(AppDatabase appDatabase) {
    return appDatabase.resultsListDao()  ;
    }
}
