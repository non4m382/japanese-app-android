package com.example.japanese_app_android.retrofit;

import com.example.japanese_app_android.model.AlphabetEntity;
import com.example.japanese_app_android.model.response.GeneralResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlphabetApi {

    @GET("/jla-be/v1/user/alphabet")
    Call<GeneralResponse<List<AlphabetEntity>>> getAllLesson();

}
