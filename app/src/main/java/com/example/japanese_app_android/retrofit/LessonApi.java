package com.example.japanese_app_android.retrofit;

import com.example.japanese_app_android.model.LessonEntity;
import com.example.japanese_app_android.model.response.GeneralResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LessonApi {

    @GET("/jla-be/v1/user/lesson")
    Call<GeneralResponse<List<LessonEntity>>> getLessonByBookId(@Query("bookId") Integer bookId);

}
