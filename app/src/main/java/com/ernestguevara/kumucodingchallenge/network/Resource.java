package com.ernestguevara.kumucodingchallenge.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A generic class that holds a value with its loading status.
 * Often used with LiveData.
 */
public class Resource<T> {
    public enum Status {
        SUCCESS,
        LOADING,
        CLIENT_ERROR,
        SERVER_ERROR,
        NOT_AUTHENTICATED
    }

    @NonNull
    public final Status status;

    @Nullable
    public final String message;

    @Nullable
    public final T data;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> serverError(String msg, @Nullable T data) {
        return new Resource<>(Status.SERVER_ERROR, data, msg);
    }

    public static <T> Resource<T> clientError(String msg, @Nullable T data) {
        return new Resource<>(Status.CLIENT_ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    public static <T> Resource<T> logout () {
        return new Resource<>(Status.NOT_AUTHENTICATED, null, null);
    }
}
