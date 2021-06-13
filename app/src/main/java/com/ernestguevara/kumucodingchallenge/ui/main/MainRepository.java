package com.ernestguevara.kumucodingchallenge.ui.main;

import com.ernestguevara.kumucodingchallenge.db.AppDatabase;
import com.ernestguevara.kumucodingchallenge.db.entity.LastFragment;
import com.ernestguevara.kumucodingchallenge.db.entity.UserProfile;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.SearchResult;
import com.ernestguevara.kumucodingchallenge.network.retrofit.api.ItunesApi;
import com.ernestguevara.kumucodingchallenge.network.retrofit.api.LocationApi;
import com.ernestguevara.kumucodingchallenge.network.Resource;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.LocationData;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Response.BaseResponse;
import com.ernestguevara.kumucodingchallenge.util.AppExecutors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

/**
 * Repository for the app
 * Handles data operation for clean API handling
 */
public class MainRepository {

    private ItunesApi itunesApi;
    private LocationApi locationApi;
    private AppExecutors appExecutors;
    private AppDatabase appDatabase;

    public MainRepository(ItunesApi itunesApi, LocationApi locationApi, AppExecutors appExecutors, AppDatabase appDatabase) {
        this.itunesApi = itunesApi;
        this.locationApi = locationApi;
        this.appExecutors = appExecutors;
        this.appDatabase = appDatabase;
    }

    private MediatorLiveData<Resource<BaseResponse>> getSearchData = new MediatorLiveData<>();
    private MediatorLiveData<Resource<LocationData>> getLocationData = new MediatorLiveData<>();

    /**
     * Gets the data and add them to the DB for Room persistence
     * @param term = keyword
     * @param country = country code
     * @param type = category
     * @param isExplicit = explicitness
     * @return
     */
    public LiveData<Resource<BaseResponse>> getDataAndAddToDb(String term, String country, String type, String isExplicit) {
        getSearchData.addSource(itunesApi.getSearchResult(term, country, type, isExplicit), baseResponseApiResult -> {
            if (baseResponseApiResult.isCompleted()) {
                if (baseResponseApiResult.body != null) {
                    if (baseResponseApiResult.body.getResultCount() != 0 && baseResponseApiResult.body.getResults() != null) {
                        appExecutors.diskIO().execute(() -> {
                            appDatabase.resultsListDao().deleteAllResults();
                            appDatabase.resultsListDao().insertSearchResults(baseResponseApiResult.body.getResults());
                        });
                        getSearchData.postValue(Resource.success(baseResponseApiResult.body));
                    } else {
                        getSearchData.postValue(Resource.serverError(baseResponseApiResult.errorMessage, null));
                    }
                } else {
                    getSearchData.postValue(Resource.serverError(baseResponseApiResult.errorMessage, null));
                }
            } else {
                getSearchData.postValue(Resource.clientError(baseResponseApiResult.errorMessage, null));
            }
        });
        return getSearchData;
    }

    /**
     * get search results from the db and displays them
     */
    public LiveData<List<SearchResult>> getCachedResults() {
        return appDatabase.resultsListDao().getSearchResults();
    }

    /**
     * get location data and adds the user country code to the db along side with his account
     */
    public LiveData<Resource<LocationData>> getLocationDataAndAddToDb() {
        getLocationData.postValue(Resource.loading(null));
        getLocationData.addSource(locationApi.getLocationData(), locationDataApiResult -> {
            if (locationDataApiResult.isCompleted()) {
                if (locationDataApiResult.body != null) {
                    getLocationData.postValue(Resource.success(locationDataApiResult.body));
                    createDefaultUserProfile(locationDataApiResult.body.getCountryCode());
                } else {
                    getLocationData.postValue(Resource.serverError(locationDataApiResult.errorMessage, null));
                }
            } else {
                getLocationData.postValue(Resource.clientError(locationDataApiResult.errorMessage, null));
            }
        });
        return getLocationData;
    }

    /**
     * creates and delete user entry
     */
    public void createDefaultUserProfile(String countryCode) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEnableExplicit(true);
        userProfile.setLastVisit(getDate());
        userProfile.setCountryCode(countryCode);
        appExecutors.diskIO().execute(() -> {
            appDatabase.userInfoDao().deleteProfile();
            appDatabase.userInfoDao().addProfile(userProfile);
        });
    }

    /**
     * returns user profile
     */
    public LiveData<UserProfile> getUserProfile() {
        return appDatabase.userInfoDao().getUserProfile();
    }

    /**
     * add new fragment to the db
     * @param lastFragment = last fragment before exiting
     */
    public void addFragmentEntry(LastFragment lastFragment) {
        appExecutors.diskIO().execute(() -> {
            appDatabase.lastFragmentDao().addFragmentEntry(lastFragment);
        });
    }

    /**
     * returns the last fragment
     */
    public LiveData<LastFragment> getLastFragment() {
        return appDatabase.lastFragmentDao().getLastFragment();
    }

    public LiveData<List<LastFragment>> getAlLFragmentEntry() {
        return appDatabase.lastFragmentDao().getAllFragmentEntry();
    }

    /**
     * get current date locale of phone
     */
    public String getDate() {
        return new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
    }

}
