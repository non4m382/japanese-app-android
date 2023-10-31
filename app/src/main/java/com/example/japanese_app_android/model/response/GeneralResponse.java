package com.example.japanese_app_android.model.response;

import com.google.gson.annotations.SerializedName;

public class GeneralResponse<T> {
    @SerializedName("status")
    private ResponseStatus status;
    @SerializedName("data")
    private T data;

    public GeneralResponse(ResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public GeneralResponse() {
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
