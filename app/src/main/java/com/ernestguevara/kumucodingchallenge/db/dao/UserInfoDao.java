package com.ernestguevara.kumucodingchallenge.db.dao;

import com.ernestguevara.kumucodingchallenge.db.entity.UserProfile;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Data access object for user profile
 * used to interact the app to the table
 */
@Dao
public interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProfile(UserProfile userProfile);

    @Query("SELECT * FROM profile_table")
    LiveData<UserProfile> getUserProfile();

    @Query("UPDATE profile_table SET enable_explicit= :isExplicit WHERE user_id=:mId")
    void updateExplicit(int mId, boolean isExplicit);

    @Update
    void updateLastLogin(UserProfile userProfile);

    @Query("DELETE FROM profile_table")
    void deleteProfile();
}
