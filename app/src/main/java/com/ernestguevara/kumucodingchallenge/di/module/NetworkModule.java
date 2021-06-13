package com.ernestguevara.kumucodingchallenge.di.module;

import com.ernestguevara.kumucodingchallenge.BuildConfig;
import com.ernestguevara.kumucodingchallenge.network.ApiRequestInterceptor;
import com.ernestguevara.kumucodingchallenge.network.retrofit.api.ItunesApi;
import com.ernestguevara.kumucodingchallenge.network.LiveDataCallAdapterFactory;
import com.ernestguevara.kumucodingchallenge.network.retrofit.api.LocationApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ernestguevara.kumucodingchallenge.util.Constants.ITUNES_RETROFIT;
import static com.ernestguevara.kumucodingchallenge.util.Constants.ITUNES_URL;
import static com.ernestguevara.kumucodingchallenge.util.Constants.LOCATION_RETROFIT;
import static com.ernestguevara.kumucodingchallenge.util.Constants.LOCATION_URL;

/**
 * network module
 * Provides all the network dependencies for the app
 */
@Module
public class NetworkModule {

    @Provides
    public ItunesApi provideItunesApi(@Named(ITUNES_RETROFIT)Retrofit itunesRetrofit) {
        return itunesRetrofit.create(ItunesApi.class);
    }

    @Provides
    public LocationApi provideLocationApi(@Named(LOCATION_RETROFIT)Retrofit locationRetrofit) {
        return locationRetrofit.create(LocationApi.class);
    }

    @Singleton
    @Provides
    static Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Singleton
    @Provides
    static HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    static ApiRequestInterceptor apiRequestInterceptor() {
        return new ApiRequestInterceptor();
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient(ApiRequestInterceptor apiRequestInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(apiRequestInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);

        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            builder.addInterceptor(loggingInterceptor());
        }

        return builder.build();
    }

    // Retrofit for itunes
    @Singleton
    @Provides
    @Named(ITUNES_RETROFIT)
    static Retrofit provideItunesRetrofitInstance(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(ITUNES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory(gson))
                .client(okHttpClient)
                .build();
    }

    // Retrofit for ip-location
    @Singleton
    @Provides
    @Named(LOCATION_RETROFIT)
    static Retrofit provideLocationRetrofitInstance(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(LOCATION_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory(gson))
                .client(okHttpClient)
                .build();
    }

}
