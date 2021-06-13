package com.ernestguevara.kumucodingchallenge.network.retrofit.api;

import com.ernestguevara.kumucodingchallenge.network.ApiResult;
import com.ernestguevara.kumucodingchallenge.network.retrofit.Response.BaseResponse;

import androidx.lifecycle.LiveData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Where you put all data manipulation for the requests
 */
public interface ItunesApi {
    @GET("search")
    LiveData<ApiResult<BaseResponse>> getSearchResult(@Query("term") String name, @Query("country") String country, @Query("media") String type, @Query("explicit") String isExplicit);
}
