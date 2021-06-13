package com.ernestguevara.kumucodingchallenge.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Used to intercept and log api requests
 * Also this is where you put extra headers in your call
 */
public class ApiRequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .build();
        return chain.proceed(request);
    }
}
