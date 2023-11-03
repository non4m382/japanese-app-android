package com.example.japanese_app_android.retrofit;

import com.example.japanese_app_android.model.request.LoginRequest;
import com.example.japanese_app_android.model.response.GeneralResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenApi {

    @POST("/jla-be/v1/public/login")
    Call<GeneralResponse<String>> login(@Body LoginRequest loginRequest);


}
