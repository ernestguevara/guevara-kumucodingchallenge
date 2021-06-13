package com.ernestguevara.kumucodingchallenge.db.dao;

import com.ernestguevara.kumucodingchallenge.db.entity.LastFragment;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.SearchResult;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Data access object for fragments
 * used to interact the app to the table
 */
@Dao
public interface LastFragmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFragmentEntry(LastFragment fragment);

    @Query("SELECT * FROM fragment_table ORDER BY fragment_id DESC LIMIT 1")
    LiveData<LastFragment> getLastFragment();

    @Query("SELECT * FROM fragment_table")
    LiveData<List<LastFragment>> getAllFragmentEntry();

    @Query("DELETE FROM fragment_table")
    void deleteFragment();
}
