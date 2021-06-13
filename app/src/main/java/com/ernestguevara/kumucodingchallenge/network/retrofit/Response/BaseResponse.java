package com.ernestguevara.kumucodingchallenge.network.retrofit.Response;

import com.ernestguevara.kumucodingchallenge.network.retrofit.Model.SearchResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Base model for the data you get from Location API
 * If there's many API that has the same base response IE, results, status etc. It's a good way to have a base so less redundancy at the code
 */
public class BaseResponse {

    @SerializedName("resultCount")
    @Expose
    private int resultCount;
    @SerializedName("results")
    @Expose
    private List<SearchResult> results = null;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }

}
