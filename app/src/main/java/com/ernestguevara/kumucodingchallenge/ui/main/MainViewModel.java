package com.ernestguevara.kumucodingchallenge.ui.main;

import com.ernestguevara.kumucodingchallenge.db.entity.LastFragment;
import com.ernestguevara.kumucodingchallenge.db.entity.UserProfile;
import com.ernestguevara.kumucodingchallenge.network.Resource;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.LocationData;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.SearchResult;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Response.BaseResponse;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Viewmodel for the app
 * Prepares and manage the data for an activity or fragment
 */
public class MainViewModel extends ViewModel {
    private MainRepository mainRepository;

    @Inject
    public MainViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    //get query results from itunes-api
    public LiveData<Resource<BaseResponse>> getSearchData(String term, String country, String media, String isExplicit) {
//        return mainRepository.getSearchData(term, country, media);
        return mainRepository.getDataAndAddToDb(term, country, media, isExplicit);
    }

    //get query results from the db
    public LiveData<List<SearchResult>> getCachedResults() {
        return mainRepository.getCachedResults();
    }

    //get location results from ip-api
    public LiveData<Resource<LocationData>> getLocationData() {
        return mainRepository.getLocationDataAndAddToDb();
    }

    //get user profile from the db
    public LiveData<UserProfile> getUserProfile() {
        return mainRepository.getUserProfile();
    }

    //creates default user profile
    public void createDefaultUserProfile(String countryCode) {
        mainRepository.createDefaultUserProfile(countryCode);
    }

    //add latest fragment
    public void addFragmentEntry(LastFragment lastFragment) {
        mainRepository.addFragmentEntry(lastFragment);
    }

    public LiveData<List<LastFragment>> getAlLFragmentEntry() {
        return mainRepository.getAlLFragmentEntry();
    }

    //gets the last fragment inserted
    public LiveData<LastFragment> getLastFragment() {
        return mainRepository.getLastFragment();
    }
}
