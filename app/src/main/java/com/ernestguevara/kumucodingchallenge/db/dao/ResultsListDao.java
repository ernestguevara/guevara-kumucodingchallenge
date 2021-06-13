package com.ernestguevara.kumucodingchallenge.db.dao;

import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.SearchResult;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Data access object for search results list
 * used to interact the app to the table
 */
@Dao
public interface ResultsListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSearchResults(List<SearchResult> searchResultList);

    @Query("SELECT * FROM itunes_items")
    LiveData<List<SearchResult>> getSearchResults();

    @Query("DELETE FROM itunes_items")
    void deleteAllResults();
}
