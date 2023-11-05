package com.example.japanese_app_android.retrofit;

import com.example.japanese_app_android.model.request.LoginRequest;
import com.example.japanese_app_android.model.request.RegisterRequest;
import com.example.japanese_app_android.model.response.GeneralResponse;
import com.example.japanese_app_android.model.response.LoginResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenApi {

    @POST("/jla-be/v1/public/login")
    Call<GeneralResponse<LoginResponseDto>> login(@Body LoginRequest loginRequest);

    @POST("/jla-be/v1/public/register")
    Call<GeneralResponse<Object>> register(@Body RegisterRequest registerRequest);

}
