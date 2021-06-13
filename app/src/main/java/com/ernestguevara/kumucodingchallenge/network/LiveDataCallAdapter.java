package com.ernestguevara.kumucodingchallenge.network;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Converts and serializes the Retrofit calls results to livedata as return type
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResult<R>>> {
    private final Type responseType;
    private Gson gson;

    public LiveDataCallAdapter(Type responseType, Gson gson) {
        this.responseType = responseType;
        this.gson = gson;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<ApiResult<R>> adapt(Call<R> call) {
        return new LiveData<ApiResult<R>>() {
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {
                            postValue(new ApiResult<>(response, responseType, gson));
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable throwable) {
                            postValue(new ApiResult<>(throwable));
                        }
                    });
                }
            }
        };
    }
}