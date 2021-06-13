package com.ernestguevara.kumucodingchallenge.network.retrofit.api;

import com.ernestguevara.kumucodingchallenge.network.ApiResult;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.LocationData;

import androidx.lifecycle.LiveData;
import retrofit2.http.GET;

/**
 * Where you put all data manipulation for the requests
 */
public interface LocationApi {
    @GET("/json")
    LiveData<ApiResult<LocationData>> getLocationData();
}
