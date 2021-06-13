package com.ernestguevara.kumucodingchallenge.db;

import com.ernestguevara.kumucodingchallenge.db.dao.LastFragmentDao;
import com.ernestguevara.kumucodingchallenge.db.dao.ResultsListDao;
import com.ernestguevara.kumucodingchallenge.db.dao.UserInfoDao;
import com.ernestguevara.kumucodingchallenge.db.entity.LastFragment;
import com.ernestguevara.kumucodingchallenge.db.entity.UserProfile;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.SearchResult;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Defines the database configuration
 * Serves as the main access point to the persisted data
 */

@Database(entities = {SearchResult.class, UserProfile.class, LastFragment.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ResultsListDao resultsListDao();
    public abstract UserInfoDao userInfoDao();
    public abstract LastFragmentDao lastFragmentDao();
}
