package com.example.japanese_app_android.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseStatus {
    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("timestamp")

    private String timestamp;


    public ResponseStatus() {
    }

    public ResponseStatus(String code, String message, String timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
