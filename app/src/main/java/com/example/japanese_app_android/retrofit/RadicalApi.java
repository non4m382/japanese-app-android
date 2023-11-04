package com.example.japanese_app_android.retrofit;

import com.example.japanese_app_android.model.CategoryEntity;
import com.example.japanese_app_android.model.RadicalEntity;
import com.example.japanese_app_android.model.response.GeneralResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RadicalApi {

    @GET("/jla-be/v1/user/radical/list_category")
    Call<GeneralResponse<List<CategoryEntity>>> getAllCategories();

    @GET("/jla-be/v1/user/radical/list_radical")
    Call<GeneralResponse<List<RadicalEntity>>> getRadicalByCategory(@Query("categoryId") Integer categoryId);

}
