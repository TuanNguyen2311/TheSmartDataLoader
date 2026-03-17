package com.android.thesmartdataloader.presentation;

public class Resource<T> {
    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }
    public Status status;
    public T data;
    public String message;

    private Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }
    public static <T> Resource<T> error(String errMsg) {
        return new Resource<>(Status.ERROR, null, errMsg);
    }
    public static <T> Resource<T> loading() {
        return new Resource<>(Status.LOADING, null, null);
    }


}
