package com.example.japanese_app_android.retrofit;

import com.example.japanese_app_android.model.request.AccountUpdateRequest;
import com.example.japanese_app_android.model.response.GeneralResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface AccountApi {
    @PUT("/jla-be/v1/profile")
    Call<GeneralResponse<Object>> update(@Body AccountUpdateRequest accountRequest);
}
