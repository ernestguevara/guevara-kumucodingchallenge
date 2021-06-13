package com.ernestguevara.kumucodingchallenge.network;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import retrofit2.Response;

/**
 * Parse the results of the API response
 */
public class ApiResult<T> {
    private static final String ERR_MSG = "System Error";

    public final int code;

    /**
     * Body of the network response
     */
    public final T body;

    public final String errorMessage;

    public ApiResult(Throwable error) {
        code = 500;
        body = null;
        errorMessage = error.getMessage();
    }

    public ApiResult(Response<T> response, Type responseType, Gson gson) {
        code = response.code();
        if (response.isSuccessful()) {
            body = response.body();
            errorMessage = null;
        } else {
            String message = null;
            T errorBody = null;
            if (response.errorBody() != null) {
                try {
                    String stringBody = response.errorBody().string();
                    errorBody = gson.fromJson(stringBody, responseType);
//                    if (errorBody instanceof BaseResponse) {
//                        message = ((BaseResponse) errorBody).getMessage().toString();
//                    } else {
//                        message = ERR_MSG;
//                    }
                    message = ERR_MSG;
                    Log.e("ApiError", stringBody);
                } catch (Exception e) {
                    message = ERR_MSG;
                    Log.e("ApiResult", "error parsing result", e);
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            errorMessage = message;
            body = errorBody;
        }
    }

    public boolean isCompleted() {
        return body != null;
    }
}
