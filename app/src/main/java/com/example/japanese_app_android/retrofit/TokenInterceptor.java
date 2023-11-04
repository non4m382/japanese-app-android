package com.example.japanese_app_android.retrofit;

import android.content.Context;

import com.example.japanese_app_android.util.SharedPref;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    Context context;

    public TokenInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        SharedPref.init(context);
        String token = SharedPref.read(SharedPref.TOKEN, "");
        //rewrite the request to add bearer token
        Request newRequest = chain.request().newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();

        return chain.proceed(newRequest);
    }
}
