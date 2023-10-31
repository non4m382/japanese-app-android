package com.example.japanese_app_android.retrofit;

import com.example.japanese_app_android.model.CategoryEntity;
import com.example.japanese_app_android.model.response.GeneralResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RadicalApi {

    @GET("/jla-be/v1/user/radical/list_radical_category")
    Call<GeneralResponse<List<CategoryEntity>>> getAllCategories();

//  @POST("/jla-be/v1/user/radical/list_radical?categoryId=1")
//  Call<Employee> save(@Body Employee employee);
}
